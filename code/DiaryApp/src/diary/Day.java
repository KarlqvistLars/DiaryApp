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
