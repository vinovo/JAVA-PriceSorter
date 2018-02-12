package cse12pa5student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sortacle {

	public static List<Item> generateInput(int n) {
		List<Item> lst = new ArrayList<Item>();
		String name = "gadget";
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			lst.add(new Item(name, r.nextInt(4), r.nextInt(3)));
		}
		return lst;
	}

	public static boolean isSortedVersionOf(List<Item> original, List<Item> maybeSorted) {
		if (original.size() != maybeSorted.size())
			return false;
		for (Item i : original) {
			if (findItemCount(i, original) != (findItemCount(i, maybeSorted)))
				return false;
		}
		for (int i = 0; i < maybeSorted.size() - 1; i++) {
			if (maybeSorted.get(i + 1).priceInCents < maybeSorted.get(i).priceInCents)
				return false;
		}
		return true;
	}

	public static Counterexample isGoodSorter(PriceSorter s) {
		List<Item> original = generateInput(20);
		List<Item> input = new ArrayList<Item>(original);
		s.sortByPrice(input);
		if (isSortedVersionOf(original, input))
			return null;
		else {
			Counterexample ce = new Counterexample(original, input);
			return ce;
		}
	}

	// helper method
	public static int findItemCount(Item i, List<Item> lst) {
		int count = 0;
		for (Item item : lst) {
			if (item.equals(i))
				count++;
		}
		return count;
	}
	
	//for test use, delete when submit.
/*	public void sortByPrice(List<Item> input) {
		List<Item> lst = new ArrayList<Item>();
		lst.add(input.get(0));
		for (int i = 1; i < input.size(); i++) {
			boolean added = false;
			for (int j = lst.size() - 1; j >= 0; j--) {
				if (input.get(i).priceInCents > lst.get(j).priceInCents && added == false) {
					lst.add(j + 1, input.get(i));
					added = true;
				}
				if (j == 0 && added == false) {
					lst.add(0, input.get(i));
					added = true;
				}
			}
		}
		input.clear();
		for (int i = 0; i < lst.size(); i++){
			input.add(lst.get(i));
		}
	}
	
	public static void main(String[] args){
		List<Item> lst = generateInput(4);
		System.out.println(lst);
		Sortacle s = new Sortacle();
		s.sortByPrice(lst);
		System.out.println(lst);
	}*/
}
