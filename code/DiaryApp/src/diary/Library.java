package diary;

public interface Library<T> {

	/**
	 * Adds new item
	 *
	 * @param item
	 *            - Object of type T
	 * @return returns boolean true if item is added.
	 */
	boolean addItem(T item);

	/**
	 * Removes item or type Parts.
	 *
	 * @param item
	 *            - Object of type Parts.
	 * @return returns boolean true if object is removed.
	 */
	boolean removeItem(T item);

	/**
	 * Read contents from file to library from databasefile 'filename'
	 * diarylist.txt.
	 *
	 * @param filename
	 *            diarylist.txt
	 */
	void readItems(String filename);

	public static String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	String openDiary(String filename);

	String showDaysOnTextArea();

}
