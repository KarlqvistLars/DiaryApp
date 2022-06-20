package diary;

public class PathControl {
	// Convert from 20220520 to ./2022/05/20220520.txt
	public static String convertInputDateToFileName(String inputDate) {
		String year = "";
		String month = "";
		String name = "";
		year = inputDate.substring(0, 4);
		month = inputDate.substring(4, 6);
		name = inputDate.substring(0, 8);
		String outputPath = String.format(".\\%s\\%s\\%s.txt", year, month,
				name);
		return outputPath;
	}
	// Convert from 20220520 to ./2022/05/
	public static String convertInputDateToPath(String inputDate) {
		String year = "";
		String month = "";
		year = inputDate.substring(0, 4);
		month = inputDate.substring(4, 6);
		String outputPath = String.format("./%s/%s/", year, month);
		return outputPath;
	}
	/**
	 * Parsing current open day to format ./Year/Month/YYYYMMDD.txt
	 * 
	 * @param currentOpen
	 *            Date on the currently open day.
	 * @return Value of currentOpenPath
	 */
	public static void setActiveDay(String activeDay) {
		String year = "";
		String month = "";
		String name = "";
		activeDay.trim();
		if (activeDay == "") {
			DiaryDB.currentOpenPath = "";
			DiaryDB.selectedDate = "";
		} else if (activeDay.length() == 8) {
			year = activeDay.substring(0, 4);
			month = activeDay.substring(4, 6);
			name = activeDay.substring(0, 8);
		} else {
			year = activeDay.substring(10, 14);
			month = activeDay.substring(14, 16);
			name = activeDay.substring(10, 18);
		}
		DiaryDB.selectedDate = name;
		DiaryDB.currentOpenPath = String.format("./%s/%s/", year, month);
		DiaryDB.filename = String.format("./%s/%s/%s.txt", year, month, name);
	}
}
