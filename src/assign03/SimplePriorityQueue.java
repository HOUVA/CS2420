package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A simple priority queue that can accommodate any type of item. Supports
 * access to of the maximum element exclusively.
 * 
 * @author Barrett Carpenter and Matthew Suggars
 * @version January 28, 2026.
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {
	private E[] array;
	private int elementCount;
	private Comparator<? super E> cmp;

	/**
	 * When this constructor is used to create the priority queue, it is assumed
	 * that the elements are ordered using their natural ordering
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		array = (E[]) new Object[16];
		elementCount = 0;
	}

	/**
	 * If this constructor is used to create the priority queue, it is assumed that
	 * the elements are ordered using the provided Comparator object.
	 * 
	 * @param cmp
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		array = (E[]) new Object[16];
		elementCount = 0;
		this.cmp = cmp;
	}

	/**
	 * Removes all of the elements from this priority Queue. The queue will be empty
	 * when this call returns.
	 */
	@Override
	public void clear() {
		elementCount = 0;
	}

	/**
	 * Indicates whether this queue contains the specified element.
	 * 
	 * @param item - the element to be checked for.
	 */
	@Override
	public boolean contains(Object item) {
		return binarySearch(item) >= 0;
	}

	/**
	 * Indicates whether this priority contains all of the specified elements.
	 * 
	 * @param coll - the collection of elements to be checked for
	 * @return true if the queue contains all of the elements from coll, otherwise
	 *         returns false.
	 */
	@Override
	public boolean containsAll(Collection<? extends E> coll) {
		if (coll.size() == 0)
			return true;

		for (Object item : coll) {
			if (binarySearch(item) < 0)
				return false;
		}

		return true;
	}

	/**
	 * Retrieves and deletes the maximum element in the priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty.
	 */
	@Override
	public E deleteMax() throws NoSuchElementException {
		if (elementCount == 0)
			throw new NoSuchElementException("No elements in queue");

		int maxElement = elementCount - 1;
		elementCount -= 1;

		return array[maxElement];
	}

	/**
	 * Retrieves the maximum element in the priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E findMax() throws NoSuchElementException {
		if (elementCount == 0)
			throw new NoSuchElementException("No elements in queue");
		return array[elementCount - 1];
	}

	/**
	 * Inserts the specified item into the priority queue.
	 * 
	 * @param item - the element to insert.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void insert(Object item) {
		if (array.length == elementCount)
			doubleArraySize();

		int result = binarySearch(item);

		if (result < 0)
			result = (result + 1) * -1;

		for (int i = elementCount; i > result; i--)
			array[i] = array[i - 1];
		array[result] = (E) item;

		elementCount++;

	}

	/**
	 * Inserts all of the elements in the collection into this priority queue.
	 * 
	 * @param coll - the collection of elements
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		if (array.length == elementCount)
			doubleArraySize();

		for (Object item : coll) {
			insert(item);
		}

	}

	/**
	 * Indicates whether the priority queue is empty.
	 * 
	 * @return true if the queue is empty, otherwise returns false
	 */
	@Override
	public boolean isEmpty() {
		return elementCount == 0;
	}

	/**
	 * Returns the number of elements in the priority queue.
	 * 
	 * @return the total number of elements.
	 */
	@Override
	public int size() {
		return elementCount;
	}

	/**
	 * Private helper method that doubles the size of backing array.
	 */
	@SuppressWarnings("unchecked")
	private void doubleArraySize() {
		E[] doubledArray = (E[]) new Object[elementCount * 2];

		for (int i = 0; i < elementCount; i++)
			doubledArray[i] = array[i];

		array = doubledArray;
	}

	/**
	 * Private helper method that runs a binary search on array to return the index
	 * of a specific item. If the item does not exist in the array, returns a
	 * negative number representing the proper index + 1 to insert the item in the
	 * array.
	 * 
	 * @param item to check in array
	 * @return the index of the item in array. If the item does not exist, returns a
	 *         negative number.
	 */
	@SuppressWarnings("unchecked")
	private int binarySearch(Object item) {
		int middle, foundValue = -1;
		int start = 0;
		int end = elementCount - 1;

		while (start <= end) {

			middle = (start + end) / 2;

			if (this.cmp != null) {

				if (cmp.compare(array[middle], (E) item) == 0) {
					foundValue = middle;
					break;
				} else if (cmp.compare(array[middle], (E) item) > 0)
					end = middle - 1;
				else
					start = middle + 1;

			} else {

				if (((Comparable<? super E>) array[middle]).compareTo((E) item) == 0) {
					foundValue = middle;
					break;
				} else if (((Comparable<? super E>) array[middle]).compareTo((E) item) > 0)
					end = middle - 1;
				else
					start = middle + 1;
			}
		}

		if (foundValue >= 0)
			return foundValue;
		else
			return (start + 1) * -1;

	}

}
