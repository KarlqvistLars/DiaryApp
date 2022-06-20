package diary;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import diaryapp.DiaryWinGUI;

public class Day implements DayInterface {
	/**
	 * Method to open day.
	 * 
	 * @param day
	 *            To be opened (day= YYYYMMDD) insert date in frame textChoice.
	 * @return If successful true
	 */
	public static boolean openDay(String day) {
		// kod för att ladda dagen (day= YYYYMMDD) angiven i textChoice.
		DiaryWinGUI.textArea.setEnabled(true);
		DiaryWinGUI.textArea.setBackground(Color.WHITE);
		DiaryWinGUI.textContainer.setEnabled(true);
		DiaryWinGUI.textContainer.setBackground(Color.WHITE);
		String dayPath = DiaryDB.getInputFilePath(day);
		PathControl.setActiveDay(dayPath);
		DiaryWinGUI.textContainer.setText(DiaryDB.openTheDay(day));
		DiaryWinGUI.textFieldHMIOutputText.setText(String
				.format("  DAG %s ÖPPNAS", DiaryWinGUI.textChoice.getText()));
		return true;
	}
	/**
	 * Appends new day to textArea on GUI
	 * 
	 * @return Boolean true if succeful
	 */
	public static boolean newDay() {
		// Texthantering av textArea
		DiaryWinGUI.textContainer.setText(null);
		DiaryWinGUI.textArea.setBackground(Color.WHITE);
		DiaryWinGUI.textArea.setEnabled(true);
		DiaryWinGUI.textFieldHMIOutputText
				.setText("  NY DAG TILLAGD TILL DAGBOK");
		DiaryWinGUI.textContainer.setText("=== Dagboks anteckning ===\n");
		DiaryWinGUI.textContainer.append(DiaryDB.newDay());
		DiaryWinGUI.textContainer.append("\n");
		DiaryWinGUI.textArea.requestFocus();
		return true;
	}
	/**
	 * 
	 * @param dateInsert
	 * @return
	 */
	public static boolean insertDay(String dateInsert) {
		// Texthantering av textArea
		DiaryWinGUI.textContainer.setText(null);
		DiaryWinGUI.textArea.setBackground(Color.WHITE);
		DiaryWinGUI.textArea.setEnabled(true);
		DiaryWinGUI.textFieldHMIOutputText
				.setText("  NY DAG TILLAGD TILL DAGBOK");
		DiaryWinGUI.textContainer.setText("=== Dagboks anteckning ===\n");
		String year = dateInsert.substring(0, 4);
		String month = String.format("%2s", dateInsert.substring(4, 6))
				.replace(' ', '0');
		String day = String.format("%2s", dateInsert.substring(6, 8))
				.replace(' ', '0');
		DiaryWinGUI.textContainer
				.append(String.format("%s-%s-%s 12.00:", year, month, day));
		DiaryWinGUI.textContainer.append("\n");
		DiaryWinGUI.textArea.requestFocus();
		return true;
	}
	/**
	 * 
	 * @param okPane
	 *            Canvas for Dialogbox Yes/No/Cancel
	 * @return Boolean true if succeful
	 */
	public static boolean saveDay(DialogBox okPane) {
		// Implamentera controll på om katalog som skall sparas till existerar?
		if (DiaryWinGUI.textArea.getBackground() == Color.WHITE) {
			DiaryWinGUI.textFieldHMIOutputText
					.setText("  SPARA ÖPPEN DAGBOKS NOTERING?");
			int val = okPane.OptionOkNoCancel();
			if (val == 0) {
				// Skapa en kontroll som ser om öppen dag sparas"
				// DiaryLibrary.saveTheDay("./2022/05/20220520.txt");
				PathControl.setActiveDay(DiaryDB.currentOpenDay());
				try {
					Path path = Paths.get(DiaryDB.currentOpenPath);
					Files.createDirectories(path);
				} catch (IOException e) {
					System.err.println(
							"Failed to create directory!" + e.getMessage());
				}
				savingTheDay(DiaryDB.currentOpenDay());
				DiaryWinGUI.textFieldHMIOutputText
						.setText("  DAG SPARAD TILL DAGBOK");
			} else {
				DiaryWinGUI.textFieldHMIOutputText.setText("  DAGEN EJ SPARAD");
			}
		}
		DiaryWinGUI.textFieldSearch.setText(DiaryDB.currentOpenPath);
		return true;
	}
	/**
	 * Saving actions to assist method saveDay()
	 * 
	 * @param filename
	 *            Name of the day being saved. ex. 20220515.txt
	 * @return
	 */
	public static boolean savingTheDay(String filename) {
		boolean status = false;
		// Sparar textfilen YYMMDD.txt
		filename.trim();
		if (filename.length() == 8) {
			PathControl.setActiveDay(filename);
			status = true;
		} else {
			DiaryWinGUI.textFieldHMIOutputText
					.setText("   ETT FEL HAR INTRÄFFAT");
			status = false;
		}
		String saveText = DiaryWinGUI.textContainer.getText();
		if (true) { // Hitta villkor ifall biblioteket behöver skapas?
			try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
				pw.println(saveText);
				status = true;
			} catch (IOException ioe) {
				System.out.println("Exception occurred: " + ioe);
				status = false;
			}
		}
		// Lägg till kod för att spara databasfilen diarylist.txt
		filename = "diarylist.txt";
		try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
			for (Diary savedDay : DiaryDB.diaryList) {
				pw.println(savedDay.dayToString());
				status = true;
			}
		} catch (IOException ioe) {
			System.out.println("Exception occurred: " + ioe);
			status = false;
		}
		return status;
	}
	/**
	 * Search method not yet implemented
	 * 
	 * @param day
	 *            Search for day?
	 * @return If succeful true.
	 */
	public boolean searchDays(String day) {
		// kod för att ladda dag
		return true;
	}
}
