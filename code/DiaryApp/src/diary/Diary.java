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
	int Status;

	/*
	 * Constructor of Diary day post. Containing date, relative path to file
	 * location and status. Filename should be be constructed of these date. Ex.
	 * 20220415.txt at location: root\2022\04 Status is to keep track if the
	 * file is new or edited.
	 */
	public Diary(String date, String path, int status) {
		LocalDateTime myDateObj = LocalDateTime.now();
		System.out.println("Before formatting: " + myDateObj);

		DateTimeFormatter myFormatDate = DateTimeFormatter
				.ofPattern("yyyyMMdd");
		DateTimeFormatter myFormatPath = DateTimeFormatter
				.ofPattern("./yyyy/MM/");
		// .ofPattern(".\\yyyy\\MM\\");
		date = myDateObj.format(myFormatDate);
		path = myDateObj.format(myFormatPath);
		status = 0;
		this.Date = date;
		this.Path = path;
		this.Status = status;
		System.out.println("Datum: " + date);
		System.out.println("Path: " + path);
	}

	/**
	 * @return the path
	 */
	String getPath() {
		return Path;
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
	String getDate() {
		return Date;
	}

	@Override
	public int compareTo(Diary o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toDate() {
		return String.format("Diary [Date=%s]", Date);
	}

	public String dayToString() {
		return String.format("Diary [Date=%s, Path=%s, Status=%s]", Date, Path,
				Status);
	}

}
