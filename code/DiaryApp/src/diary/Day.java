package diary;

import java.awt.Color;

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
				DiaryLibrary.saveTheDay(DiaryLibrary.currentDay());
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
}
