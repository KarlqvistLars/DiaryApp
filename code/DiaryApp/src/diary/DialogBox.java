package diary;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import diaryapp.DiaryWinGUI;

/**
 * DialogBox CLASS for: Method OptionOkNoCancel() decide to save day or not.
 * Method OptionHelp() dispay help info.
 * 
 * @author Lars
 */
public class DialogBox {
	public int OptionOkNoCancel() {
		JFrame okBox = new JFrame();
		okBox.setLocationRelativeTo(DiaryWinGUI.frame);
		okBox.setTitle("Spara");
		int t = JOptionPane.showConfirmDialog(okBox,
				"Spara �ppen dagboks notering? ");
		return t;
	}
	/**
	 * Present the Help button option Containing info about the app and link to
	 * my Linked In page.
	 */
	public void OptionHelp() {
		JFrame helpBox = new JFrame();
		helpBox.setLocationRelativeTo(DiaryWinGUI.frame);
		helpBox.setTitle("Help");
		JOptionPane.showMessageDialog(helpBox,
				"======================== DAGBOK Ver 1.1 ========================"
						+ "\n Detta �r ett dagboksprogram av Lars Karlqvist. Det �r �nnu inte n�gon f�rdig "
						+ "\n programvara utan en egen �vning i java programmering."
						+ "\n"
						+ "\n Men ifall ni �nd� tycker att denna lilla app har potential? Kanske tycker ni "
						+ "\n till och med att den �r s� bra att ni har arbete att erbjuda mig?"
						+ "\n Ja d� �r ni v�lkomna att kontakta mig p� min LinkedIN profil."
						+ "\n"
						+ "\n https://www.linkedin.com/in/lars-karlqvist-582b7a28/");
	}
}
