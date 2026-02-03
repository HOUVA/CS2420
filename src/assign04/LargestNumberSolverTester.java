package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.lang.StringBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains test cases for LargestNumberSolver.
 * 
 * @author Matthew Suggars and Barrett Carpenter
 * @version 02-02-26
 */
class LargestNumberSolverTester {
	private Integer[] integerArray;
	private Integer[] emptyArray;
	private Comparator insertionSortCmp;
	
	class OrderForLargestNumber implements Comparator<Integer>() {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		emptyArray = new Integer[0];
		integerArray = new Integer[]{ 11, 67, 79, 7, 22, 13 };
		insertionSortCmp = new OrderForLargestNumber();
	}

	@Test
	void testInsertionSort() {
		LargestNumberSolver.insertionSort(integerArray, null);
	}

	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		StringBuilder st1 = new StringBuilder();
		StringBuilder st2 = new StringBuilder();
		
		st1.append(o1);
		st1.append(o2);
		
		st2.append(o2);
		st2.append(o1);
		
		return Integer.parseInt(st1.toString()) - Integer.parseInt(st2.toString());
		return 0;
	}

}
