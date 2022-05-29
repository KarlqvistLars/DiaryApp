package diary;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import diaryapp.DiaryWinGUI;
import diaryapp.DiaryWinGUI.AppActionListener.DialogBox;

public class Day {
	/**
	 * Constructor behövs det? public class Day() { }
	 *
	 * Pseudokod här: För att öppna behövs input av dag. Kontroll på om dagen
	 * existerar Kontroll på om det är någon i arbetsytan som först behöver
	 * sparas. När alla förusättingar är klara öppna önskat dokument.
	 * 
	 * Denna case sats öppnar enpart txt filen för angiven dag den ändrar eller
	 * öppnar inte databasfilen diarylist.
	 * 
	 * @param day
	 * @return
	 */
	public static boolean openDay(String day) {
		// kod för att ladda dagen (day= YYYYMMDD) angiven i textChoice.
		DiaryWinGUI.textArea.setEnabled(true);
		DiaryWinGUI.textArea.setBackground(Color.WHITE);
		DiaryWinGUI.textContainer.setEnabled(true);
		DiaryWinGUI.textContainer.setBackground(Color.WHITE);
		day = DiaryLibrary.getInputFilePath(DiaryWinGUI.textChoice.getText());
		DiaryLibrary.setCurrentOpenDay(day);
		DiaryWinGUI.textContainer.setText(DiaryLibrary.openTheDay(day));
		DiaryWinGUI.textFieldHMIOutputText.setText(String
				.format("  DAG %s ÖPPNAS", DiaryWinGUI.textChoice.getText()));
		return true;
	}
	/**
	 * Tillsvidare tom flytta kod från
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
	 * @param day
	 * @return
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
				DiaryLibrary.setCurrentOpenDay(DiaryLibrary.currentOpenDay());
				try {
					Path path = Paths.get(DiaryLibrary.getCurrentPath());
					Files.createDirectories(path);
					// System.out.println("Directory is created!");
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
		DiaryWinGUI.textFieldSearch.setText(DiaryLibrary.currentOpenDay);
		return true;
	}

	/**
	 * Saving actions to assist method saveDay()
	 * 
	 * @param filename
	 *            Name of the day being saved. ex. 20220515-.txt
	 */
	public static void saveTheDay(String filename) {
		// Sparar textfilen YYMMDD.txt
		if (filename.length() == 9) {
			filename = DiaryLibrary.setCurrentOpenDay(filename);
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
	 * @return
	 */
	public boolean searchDays(String day) {
		// kod för att ladda dag
		return true;
	}
}
