package diary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestDiary {
	// Set date today to test
	String dateToday = "20220620";

	@Test
	public void testSetDayTodayGetDate() {
		String date;
		Diary diary = new Diary(null, null, 0);
		diary.setDayToday();
		date = diary.getDate();
		assertEquals(dateToday, date);
	}

	@Test
	public void testSetGetPath() {
		String path;
		Diary diary = new Diary(null, null, 0);
		diary.setPath("./TestPath");
		path = diary.getPath();
		assertEquals("./TestPath", path);
	}

	@Test
	public void testSetGetStatus() {
		int status;
		Diary diary = new Diary(null, null, 0);
		diary.setStatus(2);
		status = diary.getStatus();
		assertEquals(2, status);
	}

	@Test
	public void testGetTodaysDate() {
		String date;
		date = Diary.getTodaysDate();
		assertEquals(dateToday, date);
	}

	@Test
	public void testGetYear() {
		String year;
		year = Diary.getYear();
		assertEquals("2022", year);
	}

	@Test
	public void testGetMonth() {
		String month;
		month = Diary.getMonth();
		assertEquals("06", month);
	}
}
