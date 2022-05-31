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
	 * Removes item or type Parts.
	 *
	 * @param item
	 *            - Object of type Parts.
	 * @return returns boolean true if object is removed.
	 */
	boolean removeItem(T item);

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

	public String getPath();

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

	/**
	 * Lists or displays the library contents on text Area
	 * 
	 * @return prototype null
	 */
	default String showLibraryOnGUI() {
		return null;
	};

	/**
	 * GUI button "save" - Writes content to the database file.
	 *
	 * @param filename
	 *            - The file diarylist.txt
	 */
	void storeItems(String filename);

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
