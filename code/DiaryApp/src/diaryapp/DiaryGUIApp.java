package diaryapp;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import diary.Diary;
import diary.DiaryDB;
import diary.DiaryDBInterface;

public abstract class DiaryGUIApp implements DiaryDBInterface<DiaryDB> {

	public static void main(String[] args) {
		Runnable guiRun = new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					List<Diary> model = new ArrayList<>();
					DiaryWinGUI window = new DiaryWinGUI(model);
					window.frame.setVisible(true);
					window.frame.setTitle("DAGBOK v1.1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		EventQueue.invokeLater(guiRun);
	}
}
