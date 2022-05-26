package diary;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import diaryapp.DiaryWinGUI;
import diaryapp.DiaryWinGUI.AppActionListener.DialogBox;

public class Day {
	/**
	 * Constructor behövs det? public class Day() { }
	 */
	static Boolean newDay = false;
	/**
	 * @param day
	 * @return
	 */
	public boolean loadDay(String day) {
		// kod för att ladda dag
		return true;
	}

	public static boolean newDay() {
		// Texthantering av textArea
		DiaryWinGUI.textArea.setText(null);
		DiaryWinGUI.textArea.setBackground(Color.WHITE);
		DiaryWinGUI.textArea.setEnabled(true);
		DiaryWinGUI.textFieldHMIOutputText
				.setText("  NY DAG TILLAGD TILL DAGBOK");
		DiaryWinGUI.textArea.setText("=== Dagboks anteckning ===\n");
		DiaryWinGUI.textArea.append(DiaryLibrary.newDay());
		DiaryWinGUI.textArea.append("\n");
		DiaryWinGUI.textArea.requestFocus();
		return true;
	}

	/**
	 * 
	 * @param day
	 * @return
	 */
	public static boolean saveDay(DialogBox okPane) {
		//
		if (DiaryWinGUI.textArea.getBackground() == Color.WHITE) {
			DiaryWinGUI.textFieldHMIOutputText
					.setText("  SPARA ÖPPEN DAGBOKS NOTERING?");
			int val = okPane.OptionOkNoCancel();
			if (val == 0) {
				// Skapa en kontroll som ser om öppen dag sparas"
				// DiaryLibrary.saveTheDay("./2022/05/20220520.txt");
				saveTheDay(DiaryLibrary.currentDay());
				DiaryWinGUI.textFieldHMIOutputText
						.setText("  DAG SPARAD TILL DAGBOK");
			} else {
				DiaryWinGUI.textFieldHMIOutputText.setText("  DAGEN EJ SPARAD");
			}
		}
		return true;
	}
	/**
	 * 
	 * @param day
	 * @return
	 */
	public boolean searchDay(String day) {
		// kod för att ladda dag
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
		String saveText = DiaryWinGUI.textArea.getText();
		if (true) {
			try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
				pw.println(saveText);
			} catch (IOException ioe) {
				System.out.println("Exception occurred: " + ioe);
				System.out.println("Skapa funktion som skapar nytt bibliotek");
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
}
