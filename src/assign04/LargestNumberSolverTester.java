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
	

	@BeforeEach
	void setUp() throws Exception {
		emptyArray = new Integer[0];
		integerArray = new Integer[]{ 11, 67, 79, 7, 22, 13 };
	}

	@Test
	void testInsertionSort() {
		LargestNumberSolver.insertionSort(integerArray, (int1, int2) -> int1 - int2 );
		assertEquals(7, integerArray[0]);
	}



}
