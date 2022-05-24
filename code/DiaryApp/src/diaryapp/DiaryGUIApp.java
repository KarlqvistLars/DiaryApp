package diaryapp;

import java.awt.EventQueue;

import diary.Diary;
import diary.DiaryLibrary;
import diary.Library;

public abstract class DiaryGUIApp implements Library<DiaryLibrary> {

	public static void main(String[] args) {
		Runnable guiRun = new Runnable() {
			public void run() {
				try {
					Diary model = new Diary("", "", 0);
					DiaryWinGUI window = new DiaryWinGUI(model);
					window.frame.setVisible(true);
					window.frame.setTitle("DAGBOK v1.0");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		EventQueue.invokeLater(guiRun);
	}
}
