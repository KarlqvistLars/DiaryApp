/**
 * 
 */
package diary;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * @author Lars
 *
 */
public class Diary implements Comparable<Diary> {
	String Date;
	String Path;
	static String cPath;
	int Status;

	/**
	 * Constructor of Diary day post. Containing date, relative path to file
	 * location and status. Filename should be be constructed of these date. Ex.
	 * 20220415.txt at location: root\2022\04 Status is to keep track if the
	 * file is new or edited.
	 * 
	 * @param date
	 *            Date "20220415"
	 * @param path
	 *            Path "root\2022\04"
	 * @param status
	 *            Status on the library post yet to be implemented.
	 */
	public Diary(String date, String path, int status) {
		this.Date = date;
		this.Path = path;
		this.Status = status;
	}

	public void setDayToday() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatDate = DateTimeFormatter
				.ofPattern("yyyyMMdd");
		DateTimeFormatter myFormatPath = DateTimeFormatter
				.ofPattern("./yyyy/MM/");
		Date = myDateObj.format(myFormatDate);
		Path = myDateObj.format(myFormatPath);
		Status = 0;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return Path;
	}

	static public String getCPath(String insertDate) {
		LocalDateTime myDateObj = LocalDateTime.now();
		if (insertDate != "" || insertDate != null) {
			int year = insertDate.indexOf(0, 3);
			int month = insertDate.indexOf(4, 5);
			int day = insertDate.indexOf(6, 7);
			int hour = 12;
			int min = 00;
			// LocalDateTime.of(year, month, month, month, day)
			myDateObj = LocalDateTime.of(year, month, day, hour, min);
		}
		DateTimeFormatter myFormatPath = DateTimeFormatter
				.ofPattern("./yyyy/MM/");
		cPath = myDateObj.format(myFormatPath);
		return cPath;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	void setPath(String path) {
		Path = path;
	}
	/**
	 * @return the status
	 */
	int getStatus() {
		return Status;
	}
	/**
	 * @param status
	 *            the status to set
	 */
	void setStatus(int status) {
		Status = status;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return Date;
	}

	/**
	 * Getter for current Year now.
	 * 
	 * @return String of current Year ex. "2022"
	 */
	public static String getTodaysDate() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatDate = DateTimeFormatter
				.ofPattern("yyyyMMdd");
		DiaryLibrary.selectedDate = myDateObj.format(myFormatDate);
		return DiaryLibrary.selectedDate;
	}

	/**
	 * Getter for current Year now.
	 * 
	 * @return String of current Year ex. "2022"
	 */
	static String getYear() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy");
		return myDateObj.format(myFormatDate);
	}
	/**
	 * Getter for current Month now.
	 * 
	 * @return String of current Month ex. "03"
	 */
	static String getMonth() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("MM");
		return myDateObj.format(myFormatDate);
	}

	/**
	 * Method for storing daliypost in "database" textfile.
	 * 
	 * @return Date in the format of a string. Ex."Diary [Date=20220508]"
	 */
	public String toDate() {
		return String.format(" Diary [Date=%s]", Date);
	}

	public String dayToString() {
		Date.substring(0, 8);
		return String.format(" Diary [Date=%s, Path=%s, Status=%s]", Date, Path,
				Status);
	}

	/**
	 * Search funktion
	 */
	@Override
	public int compareTo(Diary o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
