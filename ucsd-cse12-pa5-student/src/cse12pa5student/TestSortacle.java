package cse12pa5student;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;


public class TestSortacle {
	
	//Sample Sorter that does nothing
	static class UselessSort implements PriceSorter{
		@Override
		public void sortByPrice(List<Item> input) {
			return;
		}		
	}

	/*  A Haiku, by Nik:
	 * -----------------------
	 * This test always fails
	 * Should give you an idea 
	 * how to write your tests.
	 * -----------------------
	 */
	@Test
	public void testGoodSort1() {
		assertNotNull(Sortacle.isGoodSorter(new UselessSort()));
	}
}
