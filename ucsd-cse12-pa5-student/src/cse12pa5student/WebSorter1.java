/**
 * Source URL:
 * http://www.vogella.com/tutorials/JavaAlgorithmsMergesort/article.html
 * 
 * @author Lars Vogel
 * 
 * License URL:
 * http://www.vogella.com/license.html
 * 
 * Changes made:
 * I implemented an additional sortByPrice method to get a sorted list of Item.
 * 
 * This sort algorithm is running well as it passed my test in TestSortacle. It is not buggy.
 * 
 * The worst case takes O(n*log(n)) time. (The algorithm keeps dividing the array into halves,
 * and re-merge them into a sorted list of n elements.
 */
package cse12pa5student;

import java.util.ArrayList;
import java.util.List;

public class WebSorter1 implements PriceSorter {
	
    private int[] numbers;
    private int[] helper;

    private int number;

    public void sort(int[] values) {
        this.numbers = values;
        number = values.length;
        this.helper = new int[number];
        mergesort(0, number - 1);
    }

    private void mergesort(int low, int high) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.

    }
    
    @Override
	public void sortByPrice(List<Item> items) {
		int[] arr = new int[items.size()];
		for (int i = 0; i < items.size(); i++){
			arr[i] = items.get(i).priceInCents;
		}
		this.sort(arr);
		List<Item> sorted = new ArrayList<Item>();
		for (int i = 0; i < numbers.length; i++){
			boolean added = false;
			for (int j = 0; j < items.size(); j++){
				if (!added && arr[i] == items.get(j).priceInCents){
					sorted.add(items.remove(j));
					added = true;
				}
			}
		}
		items.clear();
		for (int i = 0; i < sorted.size(); i++){
			items.add(sorted.get(i));
		}
	}
}
