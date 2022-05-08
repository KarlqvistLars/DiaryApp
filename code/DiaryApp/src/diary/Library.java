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

}
