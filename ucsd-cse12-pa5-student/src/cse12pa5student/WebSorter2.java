/**
 * Source URL:
 * http://www.vogella.com/tutorials/JavaAlgorithmsQuicksort/article.html#complexity
 * 
 * @author Lars Vogel
 * 
 * License URL:
 * http://www.vogella.com/license.html
 * 
 * This sort algorithm is running well as it passed my test in TestSortacle. It is not buggy.
 * 
 * In the worst case quicksort selects only one element in each iteration.
 * So it is O(n) + O(n-1) + (On-2).. O(1) which is equal to O(n^2).
 */
package cse12pa5student;

import java.util.ArrayList;
import java.util.List;

public class WebSorter2 implements PriceSorter {
    private int[] numbers;
    private int number;

    public void sort(int[] values) {
        // check for empty or null array
        if (values ==null || values.length==0){
            return;
        }
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
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