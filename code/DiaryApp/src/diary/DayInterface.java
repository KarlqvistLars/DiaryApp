package diary;

public interface DayInterface {

	public static boolean openDay(String day) {
		return false;
	}

	static boolean newDay() {
		return false;
	}

	static boolean insertDay() {
		return false;
	}

	public static boolean saveDay(DialogBox okPane) {
		return false;
	}

	public static void savingTheDay(String filename) {
	}

	public default boolean searchDays(String day) {
		return false;
	}

}
