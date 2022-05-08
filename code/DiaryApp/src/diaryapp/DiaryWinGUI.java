package diaryapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import diary.Diary;
import diary.DiaryLibrary;

public class DiaryWinGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// The window frame
	public JFrame frame;
	// Setup text fields
	private JTextField textFieldSearch;
	private JTextField textChoice;
	private JTextField textFieldHMIOutputText;
	JFormattedTextField fromDateFormatted = new JFormattedTextField();
	JFormattedTextField toDateFormatted = new JFormattedTextField();
	JTextArea textArea = new JTextArea();
	// Setup handling flags
	static Boolean newDay = false;

	// model reference
	private Diary theModel;

	/**
	 * Create the application and initialize the contents of the frame.
	 */
	DiaryWinGUI(Diary model) {

		theModel = model;

		// Defining the canvas bottom frame
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Setting up the search function components
		// Define search button
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(80, 5, 75, 30);
		addButtonToFrame(btnSearch);
		// Input field search text
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(160, 6, 270, 28);
		frame.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(20);
		Border border = textFieldSearch.getBorder();
		// From Date
		fromDateFormatted.setBounds(520, 6, 140, 28);
		frame.getContentPane().add(fromDateFormatted);
		// To Date
		toDateFormatted.setBounds(735, 6, 140, 28);
		frame.getContentPane().add(toDateFormatted);

		// Setting up the diary textArea
		textArea.setBorder(border);
		textArea.setBounds(80, 75, 795, 475);
		frame.getContentPane().add(textArea);

		// Setting up the Input value area
		textChoice = new JTextField();
		textChoice.setColumns(20);
		textChoice.setBounds(6, 41, 68, 28);
		frame.getContentPane().add(textChoice);

		// Setting up Output INFO panel
		textFieldHMIOutputText = new JTextField();
		textFieldHMIOutputText.setBackground(Color.ORANGE);
		textFieldHMIOutputText.setColumns(20);
		textFieldHMIOutputText.setBounds(80, 41, 795, 28);
		frame.getContentPane().add(textFieldHMIOutputText);

		// Setting up GUI buttons
		// Open
		JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(5, 5, 70, 30);
		addButtonToFrame(btnOpen);
		// New
		JButton btnNew = new JButton("New");
		btnNew.setBounds(5, 75, 70, 30);
		addButtonToFrame(btnNew);
		// Reset
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(5, 110, 70, 30);
		addButtonToFrame(btnReset);
		// Time
		JButton btnTimeSTamp = new JButton("Time");
		btnTimeSTamp.setBounds(5, 145, 70, 30);
		addButtonToFrame(btnTimeSTamp);
		// Save
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(5, 180, 70, 30);
		addButtonToFrame(btnSave);
		// Delete
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(5, 215, 70, 30);
		addButtonToFrame(btnDelete);
		// Help
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(5, 485, 70, 30);
		addButtonToFrame(btnHelp);
		// Exit
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(5, 520, 70, 30);
		addButtonToFrame(btnExit);

		// Setting up lables
		JLabel lblToDate = new JLabel("To date:");
		lblToDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblToDate.setBounds(670, 5, 70, 30);
		frame.getContentPane().add(lblToDate);
		JLabel lblFromDate = new JLabel("From date:");
		lblFromDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFromDate.setBounds(435, 5, 90, 30);
		frame.getContentPane().add(lblFromDate);
	}

	public void addButtonToFrame(JButton button) {
		button.addActionListener(new AppActionListener());
		button.setBackground(Color.LIGHT_GRAY);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		frame.getContentPane().add(button);
	}

	/**
	 * Inner class handling user interactions via buttons
	 *
	 */
	class AppActionListener implements ActionListener {

		String filename = "dayslist.txt";
		List<Diary> diaryList;
		@Override
		public void actionPerformed(ActionEvent ae) {

			JButton trigger = (JButton) ae.getSource();

			switch (trigger.getText()) {

				case "Reset" :
					resetForm();
					break;

				case "Search" :
					// resetForm();
					// Denna funk implementeras senare.
					break;

				case "Open" :
					// resetForm();
					// textArea.setText(null);
					// showRemove = false;
					// showSearch = false;
					// showSave = false;
					// showLoad = false;
					break;

				case "New" :
					textArea.setText(null);
					newDay = true;
					textArea.append(" Dagboks anteckning ");
					Diary carpeDiem = new Diary(null, null, 0);
					textArea.append(parseString(
							String.format("%s%n", carpeDiem.toDate())));
					textArea.append(":\n ");
					textArea.requestFocus();
					break;

				case "Time" :
					textArea.append(
							String.format(DiaryLibrary.getCurrentDateTime()));
					break;

				case "Save" :

					break;

				case "Delete" :

					break;

				case "Help" :

					break;

				case "Exit" :
					System.exit(0);
					break;

			}
			// trigger update of the GUI when model has changed
			updateGUI();
		}

	}

	String parseString(String format) {
		String parts[] = format.split("=");
		format = parts[1];
		String part[] = format.split("]");
		format = part[0];
		// TODO Auto-generated method stub
		return format;
	}

	private void resetForm() {
		textFieldSearch.setText(null);
		textChoice.setText(null);
		textArea.setText(null);
		textFieldHMIOutputText.setText(null);
		fromDateFormatted.setText(null);
		toDateFormatted.setText(null);
	}

	/**
	 * This method will read model attributes and force a visual update
	 */
	private void updateGUI() {
		// textFieldSearch.setText(String.valueOf(theModel.toString()));
		// textChoise.setText(String.valueOf(theModel.getPartNo()));
		// textFieldHMIOutputText.setText(String.valueOf(theModel.getName()));
		// fromDateFormatted.setText(String.valueOf(theModel.getWhereToBuy()));
		// buyDateJTF.setText(String.valueOf(theModel.getBuyDate()));
		// toDateFormatted.setText(String.valueOf(theModel.getPrice()));
	}
}