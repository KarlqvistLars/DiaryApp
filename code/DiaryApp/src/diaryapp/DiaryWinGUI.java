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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import diary.Day;
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
	JTextField textChoice = new JTextField();
	public static JTextField textFieldHMIOutputText = new JTextField();;
	JFormattedTextField fromDateFormatted = new JFormattedTextField();
	JFormattedTextField toDateFormatted = new JFormattedTextField();
	public static JTextArea textArea = new JTextArea();
	// Setup handling flags
	static Boolean newDay = false;

	// model reference

	// private Diary theModel;

	@SuppressWarnings("unused")
	private List<Diary> theModel;
	Library<Diary> days = new DiaryLibrary();
	String filename = "diarylist.txt";

	/**
	 * Create the application and initialize the contents of the frame.
	 */
	DiaryWinGUI(List<Diary> model) {

		// Programstart actions
		textFieldHMIOutputText.setText(DiaryLibrary.readItems(filename));

		// Är menad att hålla den öppna databasen i minnet.
		theModel = model;

		// Fonts
		// Font font1 = new Font("Calibri", Font.BOLD, 18);
		Font font2 = new Font("Calibri", Font.PLAIN, 16);
		Font font3 = new Font("Tahoma", Font.BOLD, 14);

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
		textArea.setFont(font2);
		textArea.setBorder(border);
		textArea.setBounds(80, 75, 795, 475);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setForeground(Color.black);
		textArea.setEnabled(false);
		frame.getContentPane().add(textArea);

		// Setting up the Input value area
		textChoice = new JTextField();
		textChoice.setFont(font3);
		textChoice.setColumns(20);
		textChoice.setBounds(6, 41, 150, 28);
		frame.getContentPane().add(textChoice);

		// Setting up Output INFO panel
		textFieldHMIOutputText = new JTextField();
		textFieldHMIOutputText.setBackground(Color.ORANGE);
		textFieldHMIOutputText.setFont(font3);
		textFieldHMIOutputText.setColumns(20);
		textFieldHMIOutputText.setBounds(160, 41, 715, 28);
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
		button.setBackground(new Color(230, 230, 230));
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		frame.getContentPane().add(button);
	}

	/**
	 * Inner class handling user interactions via buttons
	 *
	 */
	public class AppActionListener implements ActionListener {

		// Library<Diary> diaryList = new DiaryLibrary();
		DialogBox okPane = new DialogBox();
		@Override
		public void actionPerformed(ActionEvent ae) {

			JButton trigger = (JButton) ae.getSource();

			switch (trigger.getText()) {

				case "Reset" :
					newDay = false;
					Day.saveDay(okPane);
					if (textArea.getBackground() == Color.WHITE) {
						textFieldHMIOutputText.setText("  ALLA FÄLT RENSAS");
					}
					textArea.setEnabled(false);
					resetForm();
					break;

				case "Search" :
					// Denna funk implementeras senare.
					/**
					 * Pseudokod här: Steg 1: Sökning skall vara möjlig att göra
					 * i den textbaserad databasfilen. Steg 2: Sökning bör vara
					 * möjlig att göra i dagboksanteckningarna i textdokumenten.
					 * 
					 * Sökningen skall gå att begränsa med från och till datum.
					 * Söka efter sökord skall vara möjlig i textmassan.
					 */
					textArea.setBackground(Color.WHITE);
					textArea.setEnabled(true);
					// textArea.setText(filename);

					textArea.setText(null);
					textArea.append("\n DiaryLibrary daylist\n");
					textArea.append(makeLine("_", 98) + "\n");
					textArea.append(DiaryLibrary.showDaysOnTextArea());
					// System.out.print(Library.showDaysOnTextArea());
					textArea.append(makeLine("=", 98) + "\n");
					// resetForm();

					break;

				case "Open" :
					/**
					 * Pseudokod här: För att öppna behövs input av dag.
					 * Kontroll på om dagen existerar Kontroll på om det är
					 * någon i arbetsytan som först behöver sparas. När alla
					 * förusättingar är klara öppna önskat dokument.
					 * 
					 * Denna case sats öppnar enpart txt filen för angiven dag
					 * den ändrar eller öppnar inte databasfilen diarylist.
					 */
					if (textChoice.getText() != "") {
						textArea.setBackground(Color.WHITE);
						textArea.setEnabled(true);
						filename = DiaryLibrary
								.getInputFilePath(textChoice.getText());
						textArea.setText(DiaryLibrary.openTheDay(filename));
						textFieldHMIOutputText.setText(String.format(
								"  DAG %s ÖPPNAS", textChoice.getText()));
					} else {
						textFieldHMIOutputText.setText("  VAL AV DAG SAKNAS");
					}
					break;

				case "New" :
					if (textArea.getBackground() != Color.WHITE) {
						Day.newDay();
						// "Databas" funktionen
						String year = DiaryLibrary.getCurrentDateTime();
						String path = DiaryLibrary.getCurrentPath();
						Diary carpeDiem = new Diary(year, path, 0);
						DiaryLibrary.addItem(carpeDiem);
						newDay = true;
					} else {
						textFieldHMIOutputText
								.setText("  ÅTERSTÄLL FÄLT FÖRST");
					}
					break;

				case "Time" :
					if (textArea.getBackground() == Color.WHITE) {
						textFieldHMIOutputText
								.setText("  TIDSSTÄMPEL INFOGAD I DAGBOK");
						textArea.append("\n");
						textArea.append(String
								.format(DiaryLibrary.getCurrentDateTime()));
						textArea.append("\n");
						textArea.requestFocus();
					}
					break;

				case "Save" :
					Day.saveDay(okPane);
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

		// private void saveDay(DialogBox okPane) {
		// // TODO Auto-generated method stub
		//
		// }

		JFrame okBox;
		JFrame helpBox;
		public class DialogBox {
			public int OptionOkNoCancel() {
				okBox = new JFrame();
				okBox.setLocationRelativeTo(frame);
				okBox.setTitle("Spara");
				int t = JOptionPane.showConfirmDialog(okBox,
						"Spara öppen dagboks notering? ");
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
								+ "Men ifall ni ändå tycker att denna lilla app har potential?\n"
								+ "Kanske tycker till och med att den är så bra att ni har arbete att erbjuda mig?\n"
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
		toDateFormatted.setText(null);
		if (textArea.getBackground() != Color.WHITE) {
			textFieldHMIOutputText.setText(null);
		}
		textFieldSearch.setText(null);
		textChoice.setText(null);
		textArea.setText(null);
		fromDateFormatted.setText(null);
		textArea.setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * This method will read model attributes and force a visual update
	 */
	private void updateGUI() {
		textFieldSearch.setText(String.valueOf(textFieldSearch.getText()));
		textChoice.setText(String.valueOf(textChoice.getText()));
		textFieldHMIOutputText
				.setText(String.valueOf(textFieldHMIOutputText.getText()));
		fromDateFormatted.setText(String.valueOf(fromDateFormatted.getText()));
		toDateFormatted.setText(String.valueOf(fromDateFormatted.getText()));
	}
	static String makeLine(String sign, int signCount) {
		StringBuilder returnText = new StringBuilder();
		returnText.append(" ");
		for (int n = 0; n < signCount; n++) {
			returnText.append(sign);
		}
		return returnText.toString();
	}

}