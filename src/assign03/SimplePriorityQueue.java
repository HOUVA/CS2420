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
public class SimplePriorityQueue<E> implements PriorityQueue {

	/**
	 * If this constructor is used to create the priority queue, it is assumed that
	 * the elements are ordered using their natural ordering
	 */
	public SimplePriorityQueue() {

	}

	/**
	 * If this constructor is used to create the priority queue, it is assumed that
	 * the elements are ordered using their natural ordering
	 * 
	 * @param cmp
	 */
	public SimplePriorityQueue(Comparator<? super E> cmp) {

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection coll) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object deleteMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Object item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertAll(Collection coll) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
