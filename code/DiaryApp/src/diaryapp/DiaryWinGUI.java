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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import diary.Day;
import diary.DialogBox;
import diary.Diary;
import diary.DiaryDB;
import diary.DiaryDBInterface;
import diary.PathControl;

public class DiaryWinGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	// The window frame
	public static JFrame frame;
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

	// Debugfields
//	public static JTextField statusNewDay = new JTextField();
//	public static JTextField statusDayLoaded = new JTextField();
//	public static JTextField statusSaveFlag = new JTextField();
//	public static JTextField statusSearchActive = new JTextField();

	// model reference
	// private Diary theModel;

	@SuppressWarnings("unused")
	private List<Diary> theModel;
	DiaryDBInterface<Diary> days = new DiaryDB();
	String filename = "diarylist.txt";

	/**
	 * Create the application and initialize the contents of the frame.
	 */
	DiaryWinGUI(List<Diary> model) {
		// Programstart actions
		String StartMessage = DiaryDB.readItems(filename);
		textArea.setEnabled(false);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setEnabled(false);
		textContainer.setBackground(Color.LIGHT_GRAY);
		// Scrollable textArea
		textArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// ToolTipTexts för Fields
		textFieldSearch.setToolTipText("Listar alla sparade dagboksanteckningar hittills.");
		textContainer.setToolTipText("Textruta för anteckning samt listruta för dagar.");
		textChoice.setToolTipText("Indatafält för önskat datum i formatet [YYYYMMDD].");
		textFieldHMIOutputText.setToolTipText("Infofält för utfärda kommado samt ev. felinformation");

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
		btnSearch.setToolTipText("Listar alla sparade dagboksanteckningar hittills.");
		addButtonToFrame(btnSearch);

		// Input field search text
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(160, 6, 270, 28);
		frame.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(20);
		Border border = textFieldSearch.getBorder();

		// Debug field flag status
//		statusNewDay = new JTextField();
//		statusNewDay.setBounds(10, 260, 70, 28);
//		frame.getContentPane().add(statusNewDay);
//		statusNewDay.setColumns(20);
//		JLabel lblStatusNewDay = new JLabel("StatusNewDay:");
//		lblStatusNewDay.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		lblStatusNewDay.setBounds(10, 250, 100, 10);
//		frame.getContentPane().add(lblStatusNewDay);
//
//		statusDayLoaded = new JTextField();
//		statusDayLoaded.setBounds(10, 300, 70, 28);
//		frame.getContentPane().add(statusDayLoaded);
//		statusDayLoaded.setColumns(20);
//		JLabel lblStatusDayLoaded = new JLabel("StatusDayLoaded:");
//		lblStatusDayLoaded.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		lblStatusDayLoaded.setBounds(10, 290, 100, 10);
//		frame.getContentPane().add(lblStatusDayLoaded);
//
//		statusSaveFlag = new JTextField();
//		statusSaveFlag.setBounds(10, 340, 70, 28);
//		frame.getContentPane().add(statusSaveFlag);
//		statusSaveFlag.setColumns(20);
//		JLabel lblStatusSaveFlag = new JLabel("StatusSaveFlag:");
//		lblStatusSaveFlag.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		lblStatusSaveFlag.setBounds(10, 330, 100, 10);
//		frame.getContentPane().add(lblStatusSaveFlag);
//
//		statusSearchActive = new JTextField();
//		statusSearchActive.setBounds(10, 380, 70, 28);
//		frame.getContentPane().add(statusSearchActive);
//		statusSearchActive.setColumns(20);
//		JLabel lblStatusSearchActive = new JLabel("StatusSearchActive:");
//		lblStatusSearchActive.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		lblStatusSearchActive.setBounds(10, 370, 100, 10);
//		frame.getContentPane().add(lblStatusSearchActive);

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
		btnOpen.setToolTipText(
				"Öppnar dokument men hjälp av att man anger önskad dag att öppna i den vita rutan nedanför.");
		addButtonToFrame(btnOpen);
		// New
		JButton btnNew = new JButton("New");
		btnNew.setBounds(5, 75, 70, 30);
		btnNew.setToolTipText("Skapar en ny anteckning för den aktuella dagen då den skapas.");
		addButtonToFrame(btnNew);
		// Reset
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(5, 110, 70, 30);
		btnReset.setToolTipText("Tömmer alla fält på info.");
		addButtonToFrame(btnReset);
		// Time
		JButton btnTimeSTamp = new JButton("Time");
		btnTimeSTamp.setBounds(5, 145, 70, 30);
		btnTimeSTamp.setToolTipText(
				"Infogar en tidstämpel för den aktuella tiden och underlättar att avgränsa olika anteckningar och händelser.");
		addButtonToFrame(btnTimeSTamp);
		// Save
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(5, 180, 70, 30);
		btnSave.setToolTipText("Sparar öppen daganteckning.");
		addButtonToFrame(btnSave);
		// Insert day
		JButton btnInsert = new JButton("Insert");
		btnInsert.setToolTipText("Infogar en dag med det datum som anges i [ Inmatningsfältet ].");
		btnInsert.setBounds(5, 215, 70, 30);
		addButtonToFrame(btnInsert);
		// Help
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(5, 485, 70, 30);
		btnHelp.setToolTipText("Info om appen.");
		addButtonToFrame(btnHelp);
		// Exit
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(5, 520, 70, 30);
		btnExit.setToolTipText("Avsluta programmet.");
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

		// Init status
		updateGUI();
	}

	/**
	 * Method for adding new button to frame.
	 * 
	 * @param button Add button name to add button to frame.
	 */
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

		@Override
		public void actionPerformed(ActionEvent ae) {
			JButton trigger = (JButton) ae.getSource();
			textContainer.setLineWrap(true);
			switch (trigger.getText()) {

			case "Reset":
				// System.out.println("DEBUG: Reset");
				newDay = false;
				int value = 0;
				if (saveFlag == false && serchActive == false) {
					value = saveAction();
				}
				if (textArea.getBackground() == Color.WHITE && value != 2) {
					textFieldHMIOutputText.setText("  ALLA FÄLT RENSAS");
					PathControl.setActiveDay("");
					textArea.setEnabled(false);
					resetForm();
					dayLoaded = false;
				}
				break;

			case "Search":
				// System.out.println("DEBUG: Search");
				/**
				 * Pseudokod här: Steg 1: Sökning skall vara möjlig att göra i den textbaserad
				 * databasfilen. Steg 2: Sökning bör vara möjlig att göra i
				 * dagboksanteckningarna i textdokumenten.
				 * 
				 * Sökningen skall gå att begränsa med från och till datum. Söka efter sökord
				 * skall vara möjlig i textmassan.
				 */
				if (dayLoaded == false) {
					serchActive = true;
					DiaryDB.searchDiaryDays();
					textFieldHMIOutputText.setText("  SÖKURVAL VISAS");
					saveFlag = false;
				} else {
					saveAction();
					DiaryDB.searchDiaryDays();
					textFieldHMIOutputText.setText("  SÖKURVAL VISAS");
					dayLoaded = false;
				}
				break;

			case "Open":
				// System.out.println("DEBUG: Open");
				if (dayLoaded == false) {
					saveFlag = true;
					if (textChoice.getSelectionEnd() > 7) {
						// DiaryLibrary.readItems(filename);
						if (existingDay(textChoice.getText().trim())) {
							Day.openDay(textChoice.getText().trim());
							serchActive = false;
							dayLoaded = true;
							saveFlag = false;
						} else {
							textFieldHMIOutputText.setText("  VALT DATUM SAKNAS");
							dayLoaded = false;
						}
					} else {
						textFieldHMIOutputText.setText("  VAL AV DAG SAKNAS");
						dayLoaded = false;
					}
				} else {
					textFieldHMIOutputText.setText("  RESET DAG FÖRE NY KAN LADDAS");
					dayLoaded = false;
				}
				break;
			case "New":
				// System.out.println("DEBUG: New");
				if (textArea.getBackground() != Color.WHITE) {
					if (existingDay(DiaryDB.getCurrentDate().trim()) == false) {
						// "Databas" funktionen
						textArea.setBackground(Color.WHITE);
						textArea.setEnabled(true);
						textContainer.setBackground(Color.WHITE);
						textContainer.setEnabled(true);

						PathControl.setActiveDay(Diary.getTodaysDate());
						// DiaryLibrary.newDay();
						Day.newDay();
						String year = DiaryDB.getCurrentDate();
						String path = PathControl.convertInputDateToPath(DiaryDB.selectedDate);
						textChoice.setText(DiaryDB.selectedDate);
						Diary carpeDiem = new Diary(year, path, 0);
						DiaryDB.addItem(carpeDiem);
						dayLoaded = true;
						saveFlag = false;
					} else {
						textFieldHMIOutputText.setText("  ANTECKNING FÖR IDAG HAR REDAN SKAPATS");
						saveFlag = true;
					}
				} else {
					textFieldHMIOutputText.setText("  ÅTERSTÄLL FÖRE ÖPPNA NY DAG");
					saveFlag = false;
				}
				break;

			case "Time":
				// System.out.println("DEBUG: Time");
				if (textArea.getBackground() == Color.WHITE && serchActive == false) {
					DiaryDB.insertTimeStamp(String.format(DiaryDB.getCurrentDateTime()));
					textFieldHMIOutputText.setText("  TIDSSTÄMPEL INFOGAD I DAGBOK");
				}
				break;

			case "Save":
				saveAction();
				break;

			case "Insert":
				// System.out.println("DEBUG: Insert day");
				if (dayLoaded == false) {
					if (textChoice.getText().length() > 7) {
						// Kontroll ifall dag redan existerar
						if (!existingDay(textChoice.getText().trim())) {
							DiaryDB.insertDay();
							dayLoaded = true;
							saveFlag = true;
							textFieldHMIOutputText.setText(
									"  ANTECKNING FÖR DAG " + textChoice.getText().trim() + " HAR " + "SKAPATS");
						} else {
							textFieldHMIOutputText.setText("  ANTECKNING FÖR IDAG HAR REDAN SKAPATS");
							// saveFlag = false;
						}
					} else {
						textFieldHMIOutputText.setText("  INGEN GILTIG DAG");
						// saveFlag = false;
					}
				}
				break;

			case "Help":
				DialogBox helpPane = new DialogBox();
				textFieldHMIOutputText.setText("  INFO OM APPEN OCH DESS SYFTE");
				helpPane.OptionHelp();
				break;

			case "Exit":
				// System.out.println("DEBUG: Exit");
				int x = 0;
				if (saveFlag == false) {
					x = saveAction();
				}
				if (x == 0 || x == 1) {
					System.exit(0);
				}
				System.out.println(x);
				break;
			}
			// trigger update of the GUI when model has changed
			updateGUI();
		}
	}

	// Helper funcs

	private int saveAction() {
		DialogBox okPane = new DialogBox();
		int value = Day.saveDay(okPane);
		if (value == 0) {
			saveFlag = true;
		} else if (value == 1) {
			saveFlag = false;
		} else if (value == 2) {
			textFieldHMIOutputText.setText("  ÅTGÄRDEN ABRUTEN");
			saveFlag = false;
		}
		return value;
	}

	/**
	 * 
	 * @param format
	 * @return
	 */
	String parseString(String format) {
		String parts[] = format.split("=");
		format = parts[1];
		String part[] = format.split("]");
		format = part[0];
		return format;
	}

	/**
	 * Reset function for form.
	 */
	private void resetForm() {
		if (textArea.getBackground() != Color.WHITE) {
			textFieldHMIOutputText.setText(null);
		}
		textFieldSearch.setText(null);
//		textChoice.setText(textChoice.getText());
		textChoice.setText(null);
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
		DiaryDB.selectedDate = textChoice.getText();
		textFieldHMIOutputText.setText(String.valueOf(textFieldHMIOutputText.getText()));
		textContainer.setText(String.valueOf(textContainer.getText()));
		textFieldSearch.setText("N/A");
		fromDateFormatted.setText("N/A");
		toDateFormatted.setText("N/A");
		textArea.setBounds(80, 75, 795 + (frame.getWidth() - 900), 475 + (frame.getHeight() - 600));

//		statusNewDay.setText(newDay.toString());
//		statusDayLoaded.setText(dayLoaded.toString());
//		statusSaveFlag.setText(saveFlag.toString());
//		statusSearchActive.setText(serchActive.toString());

	}

	/**
	 * 
	 * @param searchPattern
	 * @return
	 */
	private boolean existingDay(String searchPattern) {
		boolean check = true;
		if (searchItem(searchPattern) == null) {
			check = false;
		}
		// System.out.println(check);
		return check;
	}

	/**
	 * 
	 * @param searchPattern
	 * @return
	 */
	private List<Diary> searchItem(String searchPattern) {
		boolean ok = false;
		List<Diary> searchResult = new ArrayList<>();
		Iterator<Diary> iter = DiaryDB.diaryList.iterator();
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