package cse12pa5student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sortacle {

	public static List<Item> generateInput(int n) {
		List<Item> lst = new ArrayList<Item>();
		Random r = new Random();
		lst.add(new Item("ball", 15, 10));
		lst.add(new Item("ball", 15, 10));
		lst.add(new Item("ball", 10, 10));
		lst.add(new Item("fakeball", 15, 10));
		for (int i = 0; i < n - 4; i++) {
			String name = "" + (char) (65 + Math.random() * 26);// + (char)
																// (65+Math.random()*57);
			lst.add(new Item(name, r.nextInt(1000000), r.nextInt(100000)));
		}
		if (lst.size() > n) {
			for (int i = 0; i < n - lst.size(); i++) {
				lst.remove(lst.size() - 1);
			}
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
			if (maybeSorted.get(i).priceInCents > maybeSorted.get(i + 1).priceInCents)
				return false;
		}
		return true;
	}

	public static Counterexample isGoodSorter(PriceSorter s) {
		int numToGenerate = 60;
		List<Item> original = generateInput(numToGenerate);
		while (isSortedVersionOf(original, original)) {
			original = generateInput(numToGenerate);
		}
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

}
