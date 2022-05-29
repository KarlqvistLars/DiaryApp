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
	 * Constructor beh�vs det? public class Day() { }
	 *
	 * Pseudokod h�r: F�r att �ppna beh�vs input av dag. Kontroll p� om dagen
	 * existerar Kontroll p� om det �r n�gon i arbetsytan som f�rst beh�ver
	 * sparas. N�r alla f�rus�ttingar �r klara �ppna �nskat dokument.
	 * 
	 * Denna case sats �ppnar enpart txt filen f�r angiven dag den �ndrar eller
	 * �ppnar inte databasfilen diarylist.
	 * 
	 * @param day
	 * @return
	 */
	public static boolean openDay(String day) {
		// kod f�r att ladda dagen (day= YYYYMMDD) angiven i textChoice.
		DiaryWinGUI.textArea.setEnabled(true);
		DiaryWinGUI.textArea.setBackground(Color.WHITE);
		DiaryWinGUI.textContainer.setEnabled(true);
		DiaryWinGUI.textContainer.setBackground(Color.WHITE);
		day = DiaryLibrary.getInputFilePath(DiaryWinGUI.textChoice.getText());
		DiaryLibrary.setCurrentOpenDay(day);
		DiaryWinGUI.textContainer.setText(DiaryLibrary.openTheDay(day));
		DiaryWinGUI.textFieldHMIOutputText.setText(String
				.format("  DAG %s �PPNAS", DiaryWinGUI.textChoice.getText()));
		return true;
	}
	/**
	 * Tillsvidare tom flytta kod fr�n
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
		// Implamentera controll p� om katalog som skall sparas till existerar?
		if (DiaryWinGUI.textArea.getBackground() == Color.WHITE) {
			DiaryWinGUI.textFieldHMIOutputText
					.setText("  SPARA �PPEN DAGBOKS NOTERING?");
			int val = okPane.OptionOkNoCancel();
			if (val == 0) {
				// Skapa en kontroll som ser om �ppen dag sparas"
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
					.setText("   ETT FEL HAR INTR�FFAT");
		}
		String saveText = DiaryWinGUI.textContainer.getText();
		if (true) { // Hitta villkor ifall biblioteket beh�ver skapas?
			try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
				pw.println(saveText);
			} catch (IOException ioe) {
				System.out.println("Exception occurred: " + ioe);
			}
		}
		// L�gg till kod f�r att spara databasfilen diarylist.txt
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
		// kod f�r att ladda dag
		return true;
	}
}
