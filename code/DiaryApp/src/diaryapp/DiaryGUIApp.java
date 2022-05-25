package diaryapp;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import diary.Diary;
import diary.DiaryLibrary;
import diary.Library;

public abstract class DiaryGUIApp implements Library<DiaryLibrary> {

	public static void main(String[] args) {
		Runnable guiRun = new Runnable() {
			public void run() {
				try {
					List<Diary> model = new ArrayList<>();
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
