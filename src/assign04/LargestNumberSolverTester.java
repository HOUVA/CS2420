package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains test cases for LargestNumberSolver.
 * 
 * @author Matthew Suggars and Barrett Carpenter
 * @version 02-04-26
 */
class LargestNumberSolverTester {
	private Integer[] integerArray;
	private Integer[] emptyArray;
	private Integer[] intArray;
	private Integer[] longArray;
	private List<Integer[]> kthLargestAndSumTestList;
	private Integer[] findLargestNumberTestArray;
	private Integer[] findLargestIntAndLongTestArray;

	@BeforeEach
	void setUp() throws Exception {
		emptyArray = new Integer[0];
		integerArray = new Integer[] { 11, 67, 79, 7, 22, 13 };
		intArray = new Integer[] { 10, 8, 6, 4, 2, 1 };
		longArray = new Integer[] { 20, 35, 1, 48, 64, 3, 5, 78, 10384, 654 };
		kthLargestAndSumTestList = new ArrayList<Integer[]>();
		kthLargestAndSumTestList.add(new Integer[] {7, 42, 97});
		kthLargestAndSumTestList.add(new Integer[] {88, 51});
		findLargestNumberTestArray = new Integer[] {1, 45, 9};
		findLargestIntAndLongTestArray = new Integer[] {999, 639, 1, 7, 58, 9};
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
	
	@Test
	public void testFileNonexistentReturnsEmptyList() {
		List<Integer[]> fakeFileList = LargestNumberSolver.readFile("src/assign04/bigIntegers.txt");
		assertEquals(0, fakeFileList.size());
	}

	// Test cases for
	// findLargestNumber-------------------------------------------------
	@Test
	public void testFindLargestNumber() {
		assertEquals(new BigInteger("0"), LargestNumberSolver.findLargestNumber(emptyArray));
		assertEquals(new BigInteger("79767221311"), LargestNumberSolver.findLargestNumber(integerArray));
		assertEquals(new BigInteger("9451"), LargestNumberSolver.findLargestNumber(findLargestNumberTestArray));

	}

	// Test cases for
	// findLargestInt----------------------------------------------------
	@Test
	public void testFindLargestIntTooLarge() {
		assertThrows(OutOfRangeException.class, () -> {
			LargestNumberSolver.findLargestInt(integerArray);
		});
		assertThrows(OutOfRangeException.class, () -> {
			LargestNumberSolver.findLargestInt(findLargestIntAndLongTestArray);
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
		assertEquals(99997639581L, LargestNumberSolver.findLargestLong(findLargestIntAndLongTestArray));
	}

	// Test cases for
	// sum----------------------------------------------------------------
	@Test
	public void testSum() {
		assertEquals(new BigInteger("106593"), LargestNumberSolver.sum(kthLargestAndSumTestList));
	}
	
	// Test cases for
	// findKthLargest-----------------------------------------------------
	@Test
	public void testKthLargestNumber() {
		assertEquals(kthLargestAndSumTestList.get(0), LargestNumberSolver.findKthLargest(kthLargestAndSumTestList, 0));
		assertEquals(kthLargestAndSumTestList.get(1), LargestNumberSolver.findKthLargest(kthLargestAndSumTestList, 1));
		assertThrows(IllegalArgumentException.class, () -> {
			LargestNumberSolver.findKthLargest(kthLargestAndSumTestList, 2);
		});
	}
}
