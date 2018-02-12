package cse12pa5student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sortacle {

	public static List<Item> generateInput(int n) {
		List<Item> lst = new ArrayList<Item>();
		String name1 = "gadget";
		String name2 = "toy";
		String name;
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			if ((int)Math.random() * 2 % 2 == 0)
				name = name1;
			else
				name = name2;
			lst.add(new Item(name, r.nextInt(20), r.nextInt(3)));
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
		int numToGenerate = 4;
		List<Item> original = generateInput(numToGenerate);
		while (isSortedVersionOf(original, original)){
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
