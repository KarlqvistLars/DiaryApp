package diary;

import java.util.Collections;
import java.util.List;

public interface Library<T> {

	/**
	 * Adds new item
	 *
	 * @param item
	 *            - Object of type T
	 * @return returns boolean true if item is added.
	 */
	static boolean addItem(Diary item) {
		return false;
	}

	/**
	 * Programstart - Read contents from file to library from databasefile
	 * 'filename' partslibrarylist.txt.
	 *
	 * @param filename
	 *            diarylist.txt Read contents from file to library from
	 *            databasefile 'filename' diarylist.txt.
	 * @return Interface protype. null
	 */
	public static String readItems(String filename) {
		return null;
	}

	static String openDiary(String filename) {
		return null;
	}

	static String showDaysOnTextArea() {
		return null;
	}
	/**
	 * Returns the number of items
	 *
	 * @return returns the number of items
	 */
	default int getNoOfItems() {
		return 0;
	}

	default int getMaxItemID() {
		return 0;
	}

	default boolean existingDay(String searchPattern) {
		return false;
	}

	/**
	 * Searches in the 'librarylist' list for the search string serachPattern
	 *
	 * @param searchPattern
	 *            - The search string searchPattern is defined by user input.
	 * @return Returns the searchResult list
	 */
	default List<T> searchItem(String searchPattern) {
		return Collections.emptyList();
	}

	default T getItem(int itemId) {
		return null;
	}
}
