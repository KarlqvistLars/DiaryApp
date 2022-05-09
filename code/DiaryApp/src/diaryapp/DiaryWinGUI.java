package diaryapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import diary.Diary;
import diary.DiaryLibrary;
import diary.Library;

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
	public static JTextArea textArea = new JTextArea();
	// Setup handling flags
	static Boolean newDay = false;

	// model reference
	// Diary kanske bör ändras till FormFields
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
		textFieldHMIOutputText.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		// button.setBackground(Color.LIGHT_GRAY);
		// button.setBorder(BorderFactory.createRaisedBevelBorder());
		frame.getContentPane().add(button);
	}

	/**
	 * Inner class handling user interactions via buttons
	 *
	 */
	class AppActionListener implements ActionListener {

		String filename = "dayslist.txt";
		Library<Diary> diaryList = new DiaryLibrary();
		@Override
		public void actionPerformed(ActionEvent ae) {

			JButton trigger = (JButton) ae.getSource();

			switch (trigger.getText()) {

				case "Reset" :
					resetForm();
					textFieldHMIOutputText.setText("  ALLA FÄLT RENSAS");
					break;

				case "Search" :
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
					String year = DiaryLibrary.getCurrentDateTime();
					String path = DiaryLibrary.getCurrentPath();
					textFieldHMIOutputText
							.setText("  NY DAG TILLAGD TILL DAGBOK");
					Diary carpeDiem = new Diary(year, path, 0);
					diaryList.addItem(carpeDiem);
					textArea.setText("=== Dagboks anteckning ===\n");
					textArea.append(DiaryLibrary.newDay());
					newDay = true;
					textArea.append("\n");
					textArea.requestFocus();
					break;

				case "Time" :
					textFieldHMIOutputText
							.setText("  TIDSSTÄMPEL INFOGAD I DAGBOK");
					textArea.append("\n");
					textArea.append(
							String.format(DiaryLibrary.getCurrentDateTime()));
					textArea.append("\n");
					textArea.requestFocus();
					break;

				case "Save" :
					if (newDay == true) {
						DiaryLibrary.saveTheDay();
						textFieldHMIOutputText
								.setText("  DAG SPARAD TILL DAGBOK");
						newDay = false;
					} else {
						DialogBox okPane = new DialogBox();
						textFieldHMIOutputText.setText(
								"  SPARA ÖVER TIDIGARE DAGBOKS NOTERING?");
						int val = okPane.OptionOkNoCancel();

						if (val == 0) {
							DiaryLibrary.saveTheDay();
							textFieldHMIOutputText
									.setText("  DAG SPARAD TILL DAGBOK");
							newDay = false;
						} else {
							textFieldHMIOutputText.setText("  DAGEN EJ SPARAD");
							newDay = false;
						}
					}
					break;

				case "Delete" :
					// Denna funk implementeras senare.
					break;

				case "Help" :
					DialogBox helpPane = new DialogBox();
					textFieldHMIOutputText
							.setText("  INFO OM APPEN OCH DESS SYFTE");
					helpPane.OptionHelp();
					break;

				case "Exit" :
					System.exit(0);
					break;

			}
			// trigger update of the GUI when model has changed
			updateGUI();
		}

		JFrame okBox;
		JFrame helpBox;
		public class DialogBox {
			int OptionOkNoCancel() {
				okBox = new JFrame();
				okBox.setLocationRelativeTo(frame);
				okBox.setTitle("Spara");
				int t = JOptionPane.showConfirmDialog(okBox,
						"Spara över tidigare dagboks notering? ");
				// System.out.println(t);
				return t;
			}
			void OptionHelp() {
				helpBox = new JFrame();
				helpBox.setLocationRelativeTo(frame);
				helpBox.setTitle("Help");
				JOptionPane.showMessageDialog(helpBox,
						"Detta är ett dagboksprogram av Lars Karlqvist.\n"
								+ "Det är ännu inte någon färdig programvara utan \n"
								+ "en egen övning i java programmering.\n\n"
								+ "Men ifall ni ändå tycker att denna lilla app har potential,\n"
								+ "och kankse tycker att den är så bra att ni har arbete att erbjuda mig?\n"
								+ "Ja då är ni välkomna att höra av er till mig på min LinkedIN profil.\n\n"
								+ "https://www.linkedin.com/in/lars-karlqvist-582b7a28/");
			}
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