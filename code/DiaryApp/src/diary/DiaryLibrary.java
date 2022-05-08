package diary;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaryLibrary implements Library<Diary> {

	int maxItemID = 1;
	private List<Diary> diaryList;

	/**
	 * Tillsammans med Partslibrary : skapar en array av 'partslist'
	 */
	public DiaryLibrary() {
		diaryList = new ArrayList<>();
	}

	// "Reset" ?

	// "Search" Search Diary

	// "Open" Open/Edit diary post

	// "New"
	@Override
	public boolean addItem(Diary item) {
		diaryList.add(item);
		return true;
	}

	// "Time" Put Time Stamp in text
	public static String getCurrentDateTime() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date date = new Date(ts.getTime());
		String stamp = date.toString();
		System.out.println(stamp);

		return stamp;
	}

	// "Save" Save Diary

	// "Delete" Delete post
	@Override
	public boolean removeItem(Diary item) {
		// TODO Auto-generated method stub
		return false;
	}

	// "Help"

	// "Exit"

}
