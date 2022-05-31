package diary;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiaryLibrary implements Library<Diary> {
	int maxItemID = 1;
	public static List<Diary> diaryList = new ArrayList<>();
	static String filename = "";
	static String currentOpenDay = "";
	// Load diary databas at start
	static public String loadDiary() {
		return readItems("diarylist.txt");
	}

	// Open
	static public String openTheDay(String filename) {
		String dayText = "";
		currentOpenDay = filename;
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
		return dayText;
	}
	// "New"
	public static String newDay() {
		currentOpenDay = getCurrentDate();
		return getCurrentDateTime();
	}
	/**
	 * Parsing current open day to format ./Year/Month/YYYYMMDD.txt
	 * 
	 * @param currentOpen
	 *            Date on the currently open day.
	 * @return Value of currentOpenDay
	 */
	public static String setCurrentOpenDay(String currentOpen) {
		String year = "";
		String month = "";
		String name = "";
		if (currentOpen.length() == 8) {
			year = currentOpen.substring(0, 4);
			month = currentOpen.substring(4, 6);
			name = currentOpen.substring(0, 8);
		} else if (currentOpen == "") {
			currentOpenDay = "";
		} else {
			year = currentOpen.substring(10, 14);
			month = currentOpen.substring(14, 16);
			name = currentOpen.substring(10, 18);
		}
		return currentOpenDay = String.format(".\\%s\\%s\\%s.txt", year, month,
				name);
	}
	/**
	 * Getter for current open day
	 * 
	 * @return current open day
	 */
	static public String currentOpenDay() {
		return currentOpenDay;
	}
	/**
	 * 
	 * @param filename
	 *            Input file name
	 * @return Input filen path
	 */
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

	static public String dayToday() {
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

	/**
	 * Getter for current date time stamp just now.
	 * 
	 * @return String of timestamp in format (YYYY-MM-DD HH.MM:)
	 */
	public static String getCurrentDateTime() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatDate = DateTimeFormatter
				.ofPattern("yyyy-MM-dd kk.mm:");
		String stamp = myDateObj.format(myFormatDate);
		return stamp;
	}
	/**
	 * Getter for current date just now.
	 * 
	 * @return String of timestamp in format (YYYYMMDD)
	 */
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
	/**
	 * List function to print search list to textArea.
	 * 
	 * @return String containing search result from diarylist.
	 */
	public static String showDaysOnTextArea() {
		StringBuilder returnText = new StringBuilder();
		for (Diary temp : diaryList) {
			returnText.append(temp.dayToString());
			returnText.append("\n");
		}
		return returnText.toString();
	}
	/**
	 * Getter for current path now()
	 * 
	 * @return Path in format (./yyyy/MM/)
	 */
	public static String getCurrentPath() {
		return Diary.getCPath();
	}
	/**
	 * Reader for database file "filename" (diarylist.txt) Parsing functions.
	 * 
	 * @param filename
	 *            Name on file to read content
	 * @return "DAGBOK DATABAS LADDAD" if successful or "ETT FEL INTRÄFFADE" if
	 *         file not found.
	 */
	public static String readItems(String filename) {
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
}