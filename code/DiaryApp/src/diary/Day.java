package diary;

import java.awt.Color;

import diaryapp.DiaryWinGUI;
import diaryapp.DiaryWinGUI.AppActionListener.DialogBox;

public class Day {
	/**
	 * Constructor beh�vs det? public class Day() { }
	 */

	static Boolean newDay = false;

	/**
	 * @param day
	 * @return
	 */
	public boolean loadDay(String day) {
		// kod f�r att ladda dag
		return true;
	}
	/**
	 * 
	 * @param day
	 * @return
	 */
	public static boolean saveDay(DialogBox okPane) {
		// kod f�r att ladda dag
		if (DiaryWinGUI.textArea.getBackground() == Color.WHITE) {
			if (newDay == true) {
				DiaryLibrary.saveTheDay(DiaryLibrary.currentDay());
				DiaryWinGUI.textFieldHMIOutputText
						.setText("  DAG SPARAD TILL DAGBOK");
				newDay = false;
			} else {
				DiaryWinGUI.textFieldHMIOutputText
						.setText("  SPARA �PPEN DAGBOKS NOTERING?");
				int val = okPane.OptionOkNoCancel();

				if (val == 0) {
					DiaryLibrary.saveTheDay(DiaryLibrary.currentDay());
					DiaryWinGUI.textFieldHMIOutputText
							.setText("  DAG SPARAD TILL DAGBOK");
					newDay = false;
				} else {
					DiaryWinGUI.textFieldHMIOutputText
							.setText("  DAGEN EJ SPARAD");
					newDay = false;
				}
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
		// kod f�r att ladda dag
		return true;
	}
}
