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
	static List<Diary> diaryList;
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
	@SuppressWarnings("unused")
	public static void saveTheDay() {
		String saveText = DiaryWinGUI.textArea.getText();
		String year = "2022";
		String month = "05";
		String name = "20220508";
		if (true) {
			filename = String.format(".\\%s\\%s\\%s.txt", year, month, name);
			try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
				pw.println(saveText);
			} catch (IOException ioe) {
				System.out.println("Exception occurred: " + ioe);
				System.out.println("Skapa funktion som skapar nytt bibliotek");
			}
		}
		filename = "diarylist.txt";
		try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
			for (Diary savedDay : diaryList) {
				pw.println(savedDay.dayToString());
			}
		} catch (IOException ioe) {
			System.out.println("Exception occurred: " + ioe);
		}

		// return "DAY SAVED";
	}

	// "Delete" Delete post
	@Override
	public boolean removeItem(Diary item) {
		// TODO Auto-generated method stub
		return false;
	}

	// "Help"

	// "Exit"

	public static String getCurrentPath() {
		return Diary.getPath();
	}

}
