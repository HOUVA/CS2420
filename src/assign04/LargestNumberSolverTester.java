package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.List;
import java.lang.StringBuilder;
import java.math.BigInteger;

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
	private Integer[] intArray;
	private Integer[] longArray;

	@BeforeEach
	void setUp() throws Exception {
		emptyArray = new Integer[0];
		integerArray = new Integer[] { 11, 67, 79, 7, 22, 13 };
		intArray = new Integer[] { 10, 8, 6, 4, 2, 1 };
		longArray = new Integer[] { 20, 35, 1, 48, 64, 3, 5, 78, 10384, 654 };
	}

	// Test cases for
	// insertionSort---------------------------------------------------
	@Test
	void testInsertionSortLargestToSmallest() {
		LargestNumberSolver.insertionSort(integerArray, (int1, int2) -> int1 - int2);
		assertEquals(79, integerArray[0]);
	}

	// Test cases for
	// readFile---------------------------------------------------------

	@Test
	public void testReadFile() {
		List<Integer[]> actual = LargestNumberSolver.readFile("src/assign04/integers.txt");
		assertEquals(410, actual.get(0)[0]);

	}

	// Test cases for
	// findLargestNumber-------------------------------------------------
	@Test
	public void testFindLargestNumber() {
		assertEquals(new BigInteger("0"), LargestNumberSolver.findLargestNumber(emptyArray));
		assertEquals(new BigInteger("79767221311"), LargestNumberSolver.findLargestNumber(integerArray));

	}

	// Test cases for
	// findLargestInt----------------------------------------------------
	@Test
	public void testFindLargestIntTooLarge() {
		assertThrows(OutOfRangeException.class, () -> {
			LargestNumberSolver.findLargestInt(integerArray);
		});
	}

	@Test
	public void testFindLargestIntValid() {
		assertEquals(8642110, LargestNumberSolver.findLargestInt(intArray));
	}

	// Test cases for
	// findLargestLong---------------------------------------------------
	@Test
	public void testFindLargestLongTooLarge() {
		assertThrows(OutOfRangeException.class, () -> {
			LargestNumberSolver.findLargestLong(longArray);
		});
	}

	@Test
	public void testFindLargestLongValid() {
		assertEquals(79767221311L, LargestNumberSolver.findLargestLong(integerArray));
	}

	// Test cases for
	// sum----------------------------------------------------------------

	// Test cases for
	// findKthLargest-----------------------------------------------------

}
