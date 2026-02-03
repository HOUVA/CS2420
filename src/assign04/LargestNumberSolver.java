package assign04;

import java.util.List;
import java.math.BigInteger;
import java.util.Comparator;
import java.io.File;

/**
 * This class determines the largest number possible from the contents of a
 * given integer array
 * 
 * @author Matthew Suggars and Barrett Carpenter
 * @version 02-02-26
 */
public class LargestNumberSolver {
	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 * 
	 * @param <T> - Object type of arr.
	 * @param arr - array to sort
	 * @param cmp - Comparator object to determine the order of sorting.
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {

	}

	/**
	 * This method returns the largest number that can be formed by arranging the
	 * integers of the given array, in any order. If the array is empty, the largest
	 * number that can be formed is 0.
	 * 
	 * @param arr - array of Integers
	 * @return 0 if array is empty, otherwise a BigInteger object representing the
	 *         largest number.
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		return null;

	}

	/**
	 * This method returns the largest int value that can be formed by arranging the
	 * integers of the given array, in any order. An OutOfRangeException is thrown
	 * if the largest number that can be formed is too large for the int data type.
	 * 
	 * @param arr - array of Integers
	 * @return int representing the largest number.
	 * @throws OutOfRangeException thrown if the largest number that can be formed
	 *                             is too large for the int data type.
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		return 0;
	}

	/**
	 * This method returns the largest long value that can be formed by arranging
	 * the Integers of the given array, in any order. An OutOfRangeException is
	 * thrown if the largest number that can be formed is too large for the long
	 * data type.
	 * 
	 * @param arr - array of Integers
	 * @return long representing the largest number.
	 * @throws OutOfRangeException thrown if the largest number that can be formed
	 *                             is too large for the long data type.
	 */
	public static long findLargestLong(Integer[] arr) {
		return 0;
	}

	/**
	 * This method sums the largest numbers that can be formed by each array in the
	 * given list.
	 * 
	 * @param list of Integer arrays
	 * @return a BigInteger object representing the sum of all largest numbers in
	 *         the list.
	 */
	public static BigInteger sum(List<Integer[]> list) {
		return null;
	}

	/**
	 * This method determines the k-th largest number that can be formed by each
	 * array in the given list.
	 * 
	 * @param list of Integer arrays
	 * @param k
	 * @return Integer array
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) {
		return null;

	}

	/**
	 * This method generates list of integer arrays from an input file, such that
	 * each line corresponds to one array of integers separated by blank spaces, and
	 * returns an empty list if the file does not exist.
	 * 
	 * @param filename - String representing the file.
	 * @return List object of Integer arrays.
	 */
	public static List<Integer[]> readFile(String filename) {
		File file = new File(filename);
		return null;

	}

}
