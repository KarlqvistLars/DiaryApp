package diary;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import diaryapp.DiaryWinGUI;

public class DiaryLibrary implements Library<Diary> {

	int maxItemID = 1;
	private List<Diary> diaryList;
	static String filename = "";

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
	public static String newDay() {

		return getCurrentDateTime();
	}

	@Override
	public boolean addItem(Diary item) {
		diaryList.add(item);
		return true;
	}

	// "Time" Put Time Stamp in text. Format:[ 2022-05-08 10.18:]
	public static String getCurrentDateTime() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatDate = DateTimeFormatter
				.ofPattern("yyyy-MM-dd kk.mm:");
		String stamp = myDateObj.format(myFormatDate);
		return stamp;
	}
	// "Save" Save Diary
	public static String saveTheDay() {
		String saveText = DiaryWinGUI.textArea.getText();

		try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
			pw.println(saveText);

			// for (Parts savedPart : this.partsList) {
			// pw.println(savedPart);
			// }
		} catch (IOException ioe) {
			System.out.println("Exception occurred: " + ioe);
		}

		return "DAY SAVED";
	}

	// "Delete" Delete post
	@Override
	public boolean removeItem(Diary item) {
		// TODO Auto-generated method stub
		return false;
	}

	// "Help"

	// "Exit"

}
