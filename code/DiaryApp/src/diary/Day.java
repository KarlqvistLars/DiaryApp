package diary;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import diaryapp.DiaryWinGUI;

public class Day {
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
		day = DiaryLibrary.getInputFilePath(DiaryWinGUI.textChoice.getText());
		PathControl.setActiveDay(day);
		DiaryWinGUI.textContainer.setText(DiaryLibrary.openTheDay(day));
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
		DiaryWinGUI.textContainer.append(DiaryLibrary.newDay());
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
		System.out.println("saveDay(DialogBox okPane)\n");
		// Implamentera controll på om katalog som skall sparas till existerar?
		if (DiaryWinGUI.textArea.getBackground() == Color.WHITE) {
			System.out.println(
					"if (DiaryWinGUI.textArea.getBackground() == Color.WHITE)\n");
			DiaryWinGUI.textFieldHMIOutputText
					.setText("  SPARA ÖPPEN DAGBOKS NOTERING?");
			int val = okPane.OptionOkNoCancel();
			if (val == 0) {
				// Skapa en kontroll som ser om öppen dag sparas"
				// DiaryLibrary.saveTheDay("./2022/05/20220520.txt");
				System.out.println("if (val == 0)\n");
				PathControl.setActiveDay(DiaryLibrary.currentOpenDay());
				System.out.println("if (val == 0) efter setCurrentOpenDay\n");

				try {

					Path path = Paths.get(DiaryLibrary.currentOpenPath);

					Files.createDirectories(path);
					System.out.println("Directory is created!" + path);
				} catch (IOException e) {
					System.err.println(
							"Failed to create directory!" + e.getMessage());
				}
				saveTheDay(DiaryLibrary.currentOpenDay());
				DiaryWinGUI.textFieldHMIOutputText
						.setText("  DAG SPARAD TILL DAGBOK");
			} else {
				DiaryWinGUI.textFieldHMIOutputText.setText("  DAGEN EJ SPARAD");
			}
		}
		DiaryWinGUI.textFieldSearch.setText(DiaryLibrary.currentOpenPath);
		return true;
	}

	/**
	 * Saving actions to assist method saveDay()
	 * 
	 * @param filename
	 *            Name of the day being saved. ex. 20220515.txt
	 */
	public static void saveTheDay(String filename) {
		System.out.println("saveTheDay(String filename)");
		// Sparar textfilen YYMMDD.txt
		filename.trim();
		if (filename.length() == 8) {
			System.out.println("1." + filename);
			PathControl.setActiveDay(filename);
			System.out.println("2." + filename);
		} else {
			DiaryWinGUI.textFieldHMIOutputText
					.setText("   ETT FEL HAR INTRÄFFAT");
		}
		String saveText = DiaryWinGUI.textContainer.getText();
		if (true) { // Hitta villkor ifall biblioteket behöver skapas?
			try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
				pw.println(saveText);
			} catch (IOException ioe) {
				System.out.println("Exception occurred: " + ioe);
			}
		}
		// Lägg till kod för att spara databasfilen diarylist.txt
		filename = "diarylist.txt";
		try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
			for (Diary savedDay : DiaryLibrary.diaryList) {
				pw.println(savedDay.dayToString());
			}
		} catch (IOException ioe) {
			System.out.println("Exception occurred: " + ioe);
		}
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
