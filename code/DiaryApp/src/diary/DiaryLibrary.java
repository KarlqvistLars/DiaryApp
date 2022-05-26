package diary;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DiaryLibrary implements Library<Diary> {

	int maxItemID = 1;
	public static List<Diary> diaryList = new ArrayList<>();
	static String filename = "";
	static String currentOpenDay = "";

	/**
	 * Constructor DiaryLibrary Tillsammans med Partslibrary : skapar en array
	 * av 'partslist'
	 */
	public DiaryLibrary() {
	}

	// "Reset" ?

	// "Search" Search Diary

	static public String loadDiary() {
		// String returMessage = "";
		return readItems("diarylist.txt");
	}

	// Open
	static public String openTheDay(String filename) {
		String dayText = "";
		System.out.println(filename);
		try {
			File myObj = new File(filename);
			@SuppressWarnings("resource")
			Scanner myOFReader = new Scanner(myObj);
			while (myOFReader.hasNextLine()) {
				String data = myOFReader.nextLine();
				dayText = String.format("%s%s\n", dayText, data);
			}
		} catch (FileNotFoundException err) {
			err.printStackTrace();
		}
		System.out.print("Text: ");
		System.out.println(dayText);
		return dayText;
	}
	// "New"
	public static String newDay() {
		return getCurrentDateTime();
	}

	public static String getInputFilePath(String filename) {
		try {
			String year = "";
			String month = "";
			month = (String) filename.subSequence(4, 6);
			year = (String) filename.subSequence(0, 4);
			return String.format("./%s/%s/%s.txt ", year, month, filename);
		} catch (StringIndexOutOfBoundsException err) {
			System.out.println("Exception occurred: " + err);
			return filename;
		}

	}

	static public String currentDay() {
		String year = Diary.getYear();
		String month = Diary.getMonth();
		String name = Diary.getTodaysDate();
		filename = String.format(".\\%s\\%s\\%s.txt", year, month, name);
		return filename;
	}

	public static boolean addItem(Diary item) {
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

	public static String getCurrentDate() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatDate = DateTimeFormatter
				.ofPattern("yyyyMMdd");
		String currentDate = myDateObj.format(myFormatDate);
		return currentDate;
	}

	// "Save" moved to CLASS Day.java

	// "Delete" Delete post
	@Override
	public boolean removeItem(Diary item) {
		// TODO Auto-generated method stub
		return false;
	}

	// "Help"

	// "Exit"

	public static String showDaysOnTextArea() {
		StringBuilder returnText = new StringBuilder();
		// Diary temp = new Diary("","",0);
		for (Diary temp : diaryList) {
			returnText.append(temp.dayToString());
			System.out.println(returnText);
			returnText.append("\n");
		}
		// System.out.println(returnText.toString());
		return returnText.toString();
	}

	public static String getCurrentPath() {
		return Diary.getCPath();
	}

	public static String readItems(String filename) {
		// TODO Auto-generated method stub
		String returMessage = "";
		try {
			File myObj = new File(filename);
			@SuppressWarnings("resource")
			Scanner myReader = new Scanner(myObj);
			List<Diary> fileInput = new ArrayList<>();
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				/**
				 * Parse data from filename to partslist
				 */
				String onePart = data.substring(data.indexOf('[') + 1,
						data.indexOf(']') + 1);
				String[] parts = onePart.split("=");
				String date = parts[1].substring(0, parts[1].indexOf(", Path"));
				String path = parts[2].substring(0,
						parts[2].indexOf(", Status"));
				String status = parts[3].substring(0, parts[3].indexOf("]"));

				// Clean and det values to variables
				date = date.strip();
				path = path.strip();
				status = status.strip();

				int stat = Integer.parseInt(status);
				// Keep track on highest uniqID(itemId) value
				// if (itemId > maxItemID) {
				// maxItemID = itemId;
				fileInput.add(new Diary(date, path, stat));

				returMessage = "   DAGBOK DATABAS LADDAD";
			}
			diaryList = fileInput;
			System.out.println(diaryList);
			for (Diary temp : diaryList) {
				System.out.println(temp.dayToString());
			}
			System.out.println(fileInput);
			for (Diary temp : fileInput) {
				System.out.println(temp.dayToString());
			}
		} catch (FileNotFoundException err) {
			returMessage = "   ETT FEL INTRÄFFADE";
			err.printStackTrace();
		}
		return returMessage;
	}

	@Override
	public String showLibraryOnGUI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeItems(String filename) {
		// TODO Auto-generated method stub

	}

	public String getPath() {
		// TODO Auto-generated method stub

		return null;
	}

	public boolean existingDay(String searchPattern) {
		boolean check = false;
		if (searchItem(searchPattern) != null) {
			check = true;
		}
		return check;
	}

	public List<Diary> searchItem(String searchPattern) {
		boolean ok = false;

		List<Diary> searchResult = new ArrayList<>();

		Iterator<Diary> iter = DiaryLibrary.diaryList.iterator();

		while (iter.hasNext()) {
			Diary temp = iter.next();
			if (temp.toDate().contains(searchPattern)) {
				searchResult.add(temp);
				ok = true;
			}
		}
		if (!ok) {
			System.out.format(" '%s' not found\n", searchPattern);
			searchResult = null;
		}
		return searchResult;
	}
}
