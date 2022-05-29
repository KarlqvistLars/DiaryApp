package diaryapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
	public static JTextField textFieldSearch = new JTextField();
	public static JTextField textChoice = new JTextField();
	public static JTextField textFieldHMIOutputText = new JTextField();;
	JFormattedTextField fromDateFormatted = new JFormattedTextField();
	JFormattedTextField toDateFormatted = new JFormattedTextField();
	public static JTextArea textContainer = new JTextArea();
	// Setup scroll capacity to textArea
	public static JScrollPane textArea = new JScrollPane(textContainer);

	// Setup handling flags
	static Boolean newDay = false;
	Boolean dayLoaded = false;
	Boolean saveFlag = false;
	Boolean serchActive = false;

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
		String StartMessage = DiaryLibrary.readItems(filename);
		textArea.setEnabled(false);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setEnabled(false);
		textContainer.setBackground(Color.LIGHT_GRAY);
		// Scrollable textArea
		textArea.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// �r menad att h�lla den �ppna databasen i minnet.
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
		textContainer.setFont(font2);
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
		// textChoice.setText(" ");

		// Setting up Output INFO panel
		textFieldHMIOutputText = new JTextField();
		textFieldHMIOutputText.setBackground(Color.ORANGE);
		textFieldHMIOutputText.setFont(font3);
		textFieldHMIOutputText.setColumns(20);
		textFieldHMIOutputText.setBounds(160, 41, 715, 28);
		frame.getContentPane().add(textFieldHMIOutputText);
		textFieldHMIOutputText.setText(StartMessage);

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
		// JButton btnDelete = new JButton("Delete");
		// btnDelete.setBounds(5, 215, 70, 30);
		// addButtonToFrame(btnDelete);
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

		DialogBox okPane = new DialogBox();
		@Override
		public void actionPerformed(ActionEvent ae) {

			JButton trigger = (JButton) ae.getSource();

			switch (trigger.getText()) {
				case "Reset" :
					// System.out.println("DEBUG: Reset");
					newDay = false;
					if (saveFlag == true && serchActive == false) {
						Day.saveDay(okPane);
					}
					if (textArea.getBackground() == Color.WHITE) {
						textFieldHMIOutputText.setText("  ALLA F�LT RENSAS");
						DiaryLibrary.setCurrentOpenDay("");
					}
					textArea.setEnabled(false);
					resetForm();
					dayLoaded = false;
					saveFlag = false;
					break;

				case "Search" :
					// System.out.println("DEBUG: Search");
					/**
					 * Pseudokod h�r: Steg 1: S�kning skall vara m�jlig att g�ra
					 * i den textbaserad databasfilen. Steg 2: S�kning b�r vara
					 * m�jlig att g�ra i dagboksanteckningarna i textdokumenten.
					 * 
					 * S�kningen skall g� att begr�nsa med fr�n och till datum.
					 * S�ka efter s�kord skall vara m�jlig i textmassan.
					 */
					if (dayLoaded == false) {
						serchActive = true;
						textArea.setBackground(Color.WHITE);
						textArea.setEnabled(true);
						textContainer.setBackground(Color.WHITE);
						textContainer.setEnabled(true);
						textContainer.setText(null);
						textContainer.append("\n DiaryLibrary daylist\n");
						textContainer.append(makeLine("_", 96) + "\n");
						textContainer.append(DiaryLibrary.showDaysOnTextArea());
						textContainer.append(makeLine("=", 96) + "\n");
						textFieldHMIOutputText.setText("  S�KURVAL VISAS");
						saveFlag = false;
					} else {
						Day.saveDay(okPane);
						textArea.setBackground(Color.WHITE);
						textArea.setEnabled(true);
						textContainer.setBackground(Color.WHITE);
						textContainer.setEnabled(true);
						textContainer.setText(null);
						textContainer.append("\n DiaryLibrary daylist\n");
						textContainer.append(makeLine("_", 96) + "\n");
						textContainer.append(DiaryLibrary.showDaysOnTextArea());
						textContainer.append(makeLine("=", 96) + "\n");
						textFieldHMIOutputText.setText("  S�KURVAL VISAS");
						dayLoaded = false;
						saveFlag = false;
					}
					break;

				case "Open" :
					// System.out.println("DEBUG: Open");
					if (dayLoaded == false) {
						if (textChoice.getSelectionEnd() > 7) {
							// DiaryLibrary.readItems(filename);
							if (existingDay(textChoice.getText().trim())) {
								Day.openDay(textChoice.getText().trim());
								serchActive = false;
								dayLoaded = true;
							} else {
								textFieldHMIOutputText
										.setText("  VALT DATUM SAKNAS");
								dayLoaded = false;
							}
						} else {
							textFieldHMIOutputText
									.setText("  VAL AV DAG SAKNAS");
							dayLoaded = false;
						}
						saveFlag = true;
					} else {
						textFieldHMIOutputText
								.setText("  RESET DAG F�RE NY KAN LADDAS");
						dayLoaded = false;
					}
					break;
				case "New" :
					// System.out.println("DEBUG: New");
					if (textArea.getBackground() != Color.WHITE) {
						if (existingDay(DiaryLibrary.getCurrentDate()
								.trim()) == false) {
							// "Databas" funktionen
							textArea.setBackground(Color.WHITE);
							textArea.setEnabled(true);
							textContainer.setBackground(Color.WHITE);
							textContainer.setEnabled(true);
							Day.newDay();
							String year = DiaryLibrary.getCurrentDate();
							String path = DiaryLibrary.getCurrentPath();
							Diary carpeDiem = new Diary(year, path, 0);
							DiaryLibrary.addItem(carpeDiem);
							dayLoaded = true;
							saveFlag = true;
						} else {
							textFieldHMIOutputText.setText(
									"  ANTECKNING F�R IDAG HAR REDAN SKAPATS");
							saveFlag = false;
						}
					} else {
						textFieldHMIOutputText
								.setText("  �TERST�LL F�RE �PPNA NY DAG");
						saveFlag = false;
					}
					break;

				case "Time" :
					// System.out.println("DEBUG: Time");
					if (textArea.getBackground() == Color.WHITE
							&& serchActive == false) {
						textFieldHMIOutputText
								.setText("  TIDSST�MPEL INFOGAD I DAGBOK");
						textContainer.append("\n");
						textContainer.append(String
								.format(DiaryLibrary.getCurrentDateTime()));
						textContainer.append("\n");
						textArea.requestFocus();
					}
					break;

				case "Save" :
					// System.out.println("DEBUG: Save");
					if (serchActive == false && dayLoaded == true) {
						Day.saveDay(okPane);
						saveFlag = false;
					} else {
						saveFlag = true;
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
					// System.out.println("DEBUG: Exit");
					if (saveFlag == true) {
						Day.saveDay(okPane);
					}
					System.exit(0);
					break;

			}
			// trigger update of the GUI when model has changed
			updateGUI();
		}

		JFrame okBox;
		JFrame helpBox;
		/**
		 * DialogBox
		 * 
		 * @author Lars
		 *
		 */
		public class DialogBox {
			public int OptionOkNoCancel() {
				okBox = new JFrame();
				okBox.setLocationRelativeTo(frame);
				okBox.setTitle("Spara");
				int t = JOptionPane.showConfirmDialog(okBox,
						"Spara �ppen dagboks notering? ");
				return t;
			}
			void OptionHelp() {
				helpBox = new JFrame();
				helpBox.setLocationRelativeTo(frame);
				helpBox.setTitle("Help");
				JOptionPane.showMessageDialog(helpBox,
						"Detta �r ett dagboksprogram av Lars Karlqvist.\n"
								+ "Det �r �nnu inte n�gon f�rdig programvara utan \n"
								+ "en egen �vning i java programmering.\n\n"
								+ "Men ifall ni �nd� tycker att denna lilla app har potential?\n"
								+ "Kanske tycker till och med att den �r s� bra att ni har arbete att erbjuda mig?\n"
								+ "Ja d� �r ni v�lkomna att h�ra av er till mig p� min LinkedIN profil.\n\n"
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
		if (textArea.getBackground() != Color.WHITE) {
			textFieldHMIOutputText.setText(null);
		}
		textFieldSearch.setText(null);
		textChoice.setText(textChoice.getText());
		textContainer.setText(null);
		// textArea.setText(null);
		toDateFormatted.setText(null);
		fromDateFormatted.setText(null);
		textContainer.setBackground(Color.LIGHT_GRAY);
		textArea.setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * This method will read model attributes and force a visual update
	 */
	private void updateGUI() {
		textChoice.setText(String.valueOf(textChoice.getText()));
		textFieldHMIOutputText
				.setText(String.valueOf(textFieldHMIOutputText.getText()));
		textContainer.setText(String.valueOf(textContainer.getText()));
		textFieldSearch.setText("N/A");
		fromDateFormatted.setText("N/A");
		toDateFormatted.setText("N/A");
	}
	/**
	 * This Method is for making horisontal lines in the GUI textArea
	 * 
	 * @param sign
	 *            The char caracter to make the line with (*,-,_,= osv.)
	 * @param signCount
	 *            The number of caracters that will make the line.
	 * @return The textstring that represent the line.
	 */
	static String makeLine(String sign, int signCount) {
		StringBuilder returnText = new StringBuilder();
		returnText.append(" ");
		for (int n = 0; n < signCount; n++) {
			returnText.append(sign);
		}
		return returnText.toString();
	}

	public boolean existingDay(String searchPattern) {
		boolean check = true;
		if (searchItem(searchPattern) == null) {
			check = false;
		}
		// System.out.println(check);
		return check;
	}

	private List<Diary> searchItem(String searchPattern) {
		boolean ok = false;
		List<Diary> searchResult = new ArrayList<>();
		Iterator<Diary> iter = DiaryLibrary.diaryList.iterator();
		while (iter.hasNext()) {
			Diary temp = iter.next();
			if (temp.toDate().contains(searchPattern)) {
				searchResult.add(temp);
				ok = true;
			}
		}
		if (!ok) {
			// System.out.format(" '%s' not found\n", searchPattern);
			searchResult = null;
		}
		return searchResult;
	}

}