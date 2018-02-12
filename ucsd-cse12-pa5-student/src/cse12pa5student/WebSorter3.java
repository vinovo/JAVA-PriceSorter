/**
 * Source URL:
 * https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/sorts/BubbleSort.java
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 * 
 * License URL:
 * https://github.com/phishman3579/java-algorithms-implementation/blob/master/LICENSE
 * 
 * This sort algorithm is running well as it passed my test in TestSortacle. It is not buggy.
 * 
 * Worst case = O(n^2). The worst case is when every two elements in the list were swapped,
 * that obviously includes n^2 movements.
 */
package cse12pa5student;

import java.util.ArrayList;
import java.util.List;

public class WebSorter3 implements PriceSorter {

    public static <T extends Comparable<T>> T[] sort(T[] unsorted) {
        boolean swapped = true;
        int length = unsorted.length;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < length; i++) {
                if (unsorted[i].compareTo(unsorted[i - 1]) < 0) {
                    swap(i, i - 1, unsorted);
                    swapped = true;
                }
            }
            length--;
        }
        return unsorted;
    }

    private static <T extends Comparable<T>> void swap(int index1, int index2, T[] unsorted) {
        T value = unsorted[index1];
        unsorted[index1] = unsorted[index2];
        unsorted[index2] = value;
    }
    
    @Override
	public void sortByPrice(List<Item> items) {
		Integer[] arr = new Integer[items.size()];
		for (int i = 0; i < items.size(); i++){
			arr[i] = items.get(i).priceInCents;
		}
		Integer[] numbers = sort(arr);
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