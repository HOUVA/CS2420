package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A simple priority queue that can accommodate any type of item.
 * 
 * @author Barrett Carpenter and Matthew Suggars
 * @version January 24, 2026.
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

	@Override
	public void clear() {
		elementCount = 0;
	}

	@Override
	public boolean contains(Object item) {
		return binarySearch(item) >= 0;
	}

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

	@Override
	public E deleteMax() throws NoSuchElementException {
		if (elementCount == 0)
			throw new NoSuchElementException("No elements in queue");

		int maxElement = elementCount - 1;
		elementCount -= 1;

		return array[maxElement];
	}

	@Override
	public E findMax() throws NoSuchElementException {
		if (elementCount == 0)
			throw new NoSuchElementException("No elements in queue");
		return array[elementCount - 1];
	}

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

	@Override
	public void insertAll(Collection<? extends E> coll) {
		if (array.length == elementCount)
			doubleArraySize();

		for (Object item : coll) {
			insert(item);
		}

	}

	@Override
	public boolean isEmpty() {
		return elementCount == 0;
	}

	@Override
	public int size() {
		return elementCount;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void doubleArraySize() {
		E[] doubledArray = (E[]) new Object[elementCount * 2];

		for (int i = 0; i < elementCount; i++)
			doubledArray[i] = array[i];

		array = doubledArray;
	}

	/**
	 * This helper method runs a binary search on array to return the index of a
	 * specific item. If the item does not exist in the array, returns a negative
	 * number representing the proper index + 1 to insert the item in the array. 
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

//		if (elementCount == 0) {
//			return 0;
//		}

		while (start <= end) {
			
			middle = (start + end) / 2;

			if (this.cmp != null) {

				if (cmp.compare(array[middle], (E) item) == 0) {
					foundValue = middle;
					break;
			}
				else if (cmp.compare(array[middle], (E) item) > 0)
					end = middle - 1;
				else
					start = middle + 1;

			} else {
				
				if (((Comparable<? super E>) array[middle]).compareTo((E) item) == 0) {
					foundValue = middle;
					break;
				}
				else if (((Comparable<? super E>) array[middle]).compareTo((E) item) > 0)
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
