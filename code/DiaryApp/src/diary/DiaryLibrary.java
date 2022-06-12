package diary;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import diaryapp.DiaryWinGUI;

public class DiaryLibrary implements Library<Diary> {
	int maxItemID = 1;
	public static List<Diary> diaryList = new ArrayList<>();
	public static String selectedDate = "";
	static String currentOpenPath = "";
	static String currentOpenFile = "";
	static String filename = "";

	// Load diary databas at start
	static public String loadDiary() {
		return readItems("diarylist.txt");
	}
	// Reset

	// Search
	static public void searchDiaryDays() {
		DiaryWinGUI.textArea.setBackground(Color.WHITE);
		DiaryWinGUI.textArea.setEnabled(true);
		DiaryWinGUI.textContainer.setBackground(Color.WHITE);
		DiaryWinGUI.textContainer.setEnabled(true);
		DiaryWinGUI.textContainer.setText(null);
		DiaryWinGUI.textContainer.append("\n DiaryLibrary daylist\n");
		DiaryWinGUI.textContainer.append(makeLine("_", 96) + "\n");
		DiaryWinGUI.textContainer.append(DiaryLibrary.showDaysOnTextArea());
		DiaryWinGUI.textContainer.append(makeLine("=", 96) + "\n");
	}

	// Open
	static public String openTheDay(String filename) {
		String dayText = "";
		currentOpenPath = filename;
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
		selectedDate = getCurrentDate();

		return getCurrentDateTime();
	}
	// Insert
	public static String insertDay() {
		DiaryWinGUI.textArea.setBackground(Color.WHITE);
		DiaryWinGUI.textArea.setEnabled(true);
		DiaryWinGUI.textContainer.setBackground(Color.WHITE);
		DiaryWinGUI.textContainer.setEnabled(true);

		selectedDate = DiaryWinGUI.textChoice.getText().trim();
		PathControl.setActiveDay(selectedDate);
		String date = selectedDate;
		String path = PathControl.convertInputDateToPath(selectedDate);
		// Insert historic day not todays date.
		Day.insertDay(selectedDate);
		Diary carpeDiem = new Diary(date, path, 0);
		DiaryLibrary.addItem(carpeDiem);
		return null;
	}

	// Time
	public static void insertTimeStamp(String timeStamp) {
		DiaryWinGUI.textContainer.append("\n");
		DiaryWinGUI.textContainer.append(timeStamp);
		DiaryWinGUI.textContainer.append("\n");
		DiaryWinGUI.textArea.requestFocus();
	}

	// "Save" moved to CLASS Day.java

	// "Help"

	// "Exit"

	// Utilities

	/**
	 * This Method is for making horisontal lines in the GUI textArea
	 * 
	 * @param sign
	 *            The char caracter to make the line with (*,-,_,= osv.)
	 * @param signCount
	 *            The number of caracters that will make the line.
	 * @return The textstring that represent the line.
	 */
	static String makeLine(String sign, int signCount) {
		StringBuilder returnText = new StringBuilder();
		returnText.append(" ");
		for (int n = 0; n < signCount; n++) {
			returnText.append(sign);
		}
		return returnText.toString();
	}
	/**
	 * Getter for current open day
	 * 
	 * @return current open day
	 */
	static public String currentOpenDay() {
		currentOpenFile = PathControl.convertInputDateToFileName(selectedDate);
		return currentOpenFile;
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
		String stamp = "";
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatDate = DateTimeFormatter
				.ofPattern("yyyy-MM-dd kk.mm:");
		stamp = myDateObj.format(myFormatDate);
		return stamp;
	}
	/**
	 * Getter for current date just now.
	 * 
	 * @return String of timestamp in format (YYYYMMDD)
	 */
	public static String getCurrentDate() {
		String currentDate = "";
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatDate = DateTimeFormatter
				.ofPattern("yyyyMMdd");
		currentDate = myDateObj.format(myFormatDate);
		return currentDate;
	}
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
	// public static String getCurrentPath() {
	// System.out.printf("Den aktulla datumen %s\n", selectedDate);
	// return Diary.getCPath(selectedDate);
	// }
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
}