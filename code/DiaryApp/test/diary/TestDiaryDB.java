package diary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestDiaryDB {

	@Test
	public void testLoadDiary() {
		String textDB1;
		String textDB2;
		textDB1 = DiaryDB.loadDiary();
		textDB2 = DiaryDB.loadDiary();
		assertEquals(textDB2, textDB1);
	}

}
