/**
 * 
 */
package diary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Lars
 *
 */
public class TestDay {

	@Test
	public void testInsertDay() {
		boolean test;
		test = Day.insertDay("20220508");
		assertTrue(test);
	}

	@Test
	public void testOpenDay() {
		boolean open;
		open = Day.openDay("20220508");
		assertTrue(open);
	}

	@Test
	public void testNewDay() {
		boolean test;
		test = Day.newDay();
		assertTrue(test);
	}

	@Test
	public void testSaveDay() {
		DialogBox okPane = new DialogBox();
		boolean test;
//		test = Day.saveDay(okPane);
//		assertTrue(test);
	}

	@Test
	public void testSaveTheDay() {
		boolean test;
		test = Day.savingTheDay("./2022/05/20220508.txt");
		assertTrue(test);
	}

	@Test
	public void testSaveTheDayErr1() {
		boolean test;
		test = Day.savingTheDay("");
		assertFalse(test);
	}

	@Test
	public void testSaveTheDayErr2() {
		boolean test;
		test = Day.savingTheDay("20220508");
		assertTrue(test);
	}
}
