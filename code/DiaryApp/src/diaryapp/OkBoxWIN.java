package diaryapp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class OkBoxWIN {

	// private static final long
	public JFrame okBoxFrame;

	OkBoxWIN() {
		okBoxFrame = new JFrame();
		okBoxFrame.setBounds(300, 300, 700, 400);
		okBoxFrame.getContentPane().setLayout(null);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(20, 20, 75, 30);
		addButtonToFrame(btnOK);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(220, 20, 75, 30);
		addButtonToFrame(btnCancel);

	}

	public void addButtonToFrame(JButton button) {
		button.addActionListener(new AppActionListener());
		button.setBackground(Color.LIGHT_GRAY);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		okBoxFrame.getContentPane().add(button);
	}

	class AppActionListener implements ActionListener {

		public void actionPerformed(ActionEvent ae) {

			JButton trigger = (JButton) ae.getSource();

			switch (trigger.getText()) {

				case "OK" :
					// resetForm();
					break;

				case "Cancel" :
					OkBoxWIN.this.okBoxFrame.setVisible(false);
					break;
			}
		}
	}
}
