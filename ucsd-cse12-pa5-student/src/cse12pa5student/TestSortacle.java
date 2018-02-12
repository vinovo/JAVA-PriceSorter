package cse12pa5student;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class TestSortacle {

	// Sample Sorter that does nothing
	static class UselessSort implements PriceSorter {
		@Override
		public void sortByPrice(List<Item> input) {
			return;
		}
	}
	// Should be a good sorter
	static class GoodSort implements PriceSorter {
		@Override
		public void sortByPrice(List<Item> input) {
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
	}
	
	static class BadSort implements PriceSorter {
		@Override
		public void sortByPrice(List<Item> input) {
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
			//Here it should be lst.size(), not lst.size() - 1.
			input.clear();
			for (int i = 0; i < lst.size() - 1; i++){
				input.add(lst.get(i));
			}
		}
	}
	
	static class GoodSort2 implements PriceSorter {
		@Override
		public void sortByPrice(List<Item> input) {
			for (int i = 0; i < input.size(); i++){
				int min = input.get(i).priceInCents;
				int index = i;
				for (int j = i; j < input.size(); j++){
					if (input.get(j).priceInCents < min){
						min = input.get(j).priceInCents;
						index = j;
					}
				}
				input.add(i, input.remove(index));
			}
		}
	}
	
	static class BadSort2 implements PriceSorter {
		@Override
		public void sortByPrice(List<Item> input) {
			for (int i = 0; i < input.size(); i++){
				int min = input.get(i).priceInCents;
				int index = i;
				for (int j = i; j < input.size(); j++){
					if (input.get(j).priceInCents < min){
						min = input.get(j).priceInCents;
						index = j;
					}
				}
				input.add(i, input.remove(index));
			}
			//Add a list that's identical to input at the end of input.
			List<Item> lst = new ArrayList<Item>(input);
			for (int i = 0; i < lst.size(); i++){
				input.add(lst.get(0));
			}
		}
	}

	/*
	 * A Haiku ------------------------- This test fails at first Should give
	 * you an idea how to write your tests. -------------------------
	 */
	@Test
	public void testGoodSort1() {
		assertNotNull(Sortacle.isGoodSorter(new UselessSort()));
	}

	// Manual test for isSortedVersionOf
	@Test
	public void testIsSortedVersionOf() {
		Item a = new Item("ball", 15, 50);
		Item b = new Item("soap", 2, 10);
		Item c = new Item("diamond", 10000, 10);
		Item d = new Item("diamond", 10000, 10);
		List<Item> ori = new ArrayList<Item>();
		List<Item> sorted = new ArrayList<Item>();
		assertTrue(Sortacle.isSortedVersionOf(ori, sorted));
		ori.add(c);
		assertFalse(Sortacle.isSortedVersionOf(ori, sorted));
		sorted.add(d);
		assertTrue(Sortacle.isSortedVersionOf(ori, sorted));
		sorted.add(d);
		assertFalse(Sortacle.isSortedVersionOf(ori, sorted));
		ori.add(a);
		ori.add(b);
		ori.add(d);
		sorted.clear();
		sorted.add(b);
		sorted.add(a);
		sorted.add(d);
		sorted.add(d);
		assertTrue(Sortacle.isSortedVersionOf(ori, sorted));
		sorted.remove(d);
		sorted.add(0, d);
		assertFalse(Sortacle.isSortedVersionOf(ori, sorted));
	}
	
	@Test
	public void testIsSortedVersionOf2(){
		Item a = new Item("ball", 15, 50);
		Item b = new Item("fakeball", 15, 50);
		Item c = new Item("human", 1000000, 50);
		List<Item> ori = new ArrayList<Item>();
		List<Item> sorted = new ArrayList<Item>();
		ori.add(c); ori.add(c); ori.add(a); ori.add(a);
		sorted.add(b);sorted.add(b);sorted.add(c);sorted.add(c);
		assertFalse(Sortacle.isSortedVersionOf(ori, sorted));
	}
	
	//Tests for isGoodSorter
	@Test
	public void testIsGoodSorterForGoodSorters1(){
		assertNull(Sortacle.isGoodSorter(new GoodSort()));
	}
	
	@Test
	public void testIsGoodSorterForGoodSorters2(){
		assertNull(Sortacle.isGoodSorter(new GoodSort2()));
	}
	
	@Test
	public void testIsGoodSorterForBadSorters1(){
		assertNotNull(Sortacle.isGoodSorter(new UselessSort()));
	}
	
	@Test
	public void testIsGoodSorterForBadSorters2(){
		assertNotNull(Sortacle.isGoodSorter(new BadSort()));
	}
	
	@Test
	public void testIsGoodSorterForBadSorters3(){
		assertNotNull(Sortacle.isGoodSorter(new BadSort2()));
	}
}
