package assign04;

import java.util.List;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.lang.StringBuilder;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class determines the largest number possible from the contents of a
 * given integer array
 * 
 * @author Matthew Suggars and Barrett Carpenter
 * @version 02-04-26
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

		for (int i = 1; i < arr.length; i++) {
			T temp = arr[i];

			int sortedIndex = i - 1;
			int numIterations = 0;
			while (sortedIndex >= 0 && cmp.compare(temp, arr[sortedIndex]) < 0) {

				arr[i - numIterations] = arr[sortedIndex];
				arr[sortedIndex] = temp;
				sortedIndex--;
				numIterations++;
			}

		}
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

		if (arr.length > 0) {
			Integer[] copyArr = arr.clone();
			insertionSort(copyArr, new OrderForLargestNumber());

			StringBuilder largestNumberString = new StringBuilder();
			for (Integer number : copyArr) {
				largestNumberString.append(number);
			}

			return new BigInteger(largestNumberString.toString());
		} else {

			return new BigInteger("0");
		}

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
		BigInteger result = findLargestNumber(arr);
		int max = (int) (Math.pow(2, 31) - 1);
		if (BigInteger.valueOf(max).compareTo(result) < 0)
			throw new OutOfRangeException("result is too large for int data type");
		return Integer.parseInt(result.toString());
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
		BigInteger result = findLargestNumber(arr);
		long max = (long) (Math.pow(2, 63) - 1);
		if (BigInteger.valueOf(max).compareTo(result) < 0)
			throw new OutOfRangeException("result is too large for int data type");
		return Long.parseLong(result.toString());

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
		BigInteger sum = new BigInteger("0");

		for (int i = 0; i < list.size(); i++) {
			sum = sum.add(findLargestNumber(list.get(i)));
		}
		return sum;
	}

	/**
	 * This method determines the k-th largest number that can be formed by each
	 * array in the given list.
	 * 
	 * @param list of Integer arrays
	 * @param k
	 * @return Integer array
	 * @throws IllegalArgumentException if k is not a valid position in the list.
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		if (k >= list.size())
			throw new IllegalArgumentException("K out of bounds for List. Recheck list and try again");

		Integer[][] copyList = list.toArray(new Integer[list.size()][]);
		insertionSort(copyList, new OrderForKthLargestNumber());

		return copyList[k];
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
		Scanner inputScanner = null;
		List<Integer[]> numbersList = new ArrayList<Integer[]>();

		try {
			inputScanner = new Scanner(file);

			while (inputScanner.hasNextLine()) {
				String numberLine = inputScanner.nextLine();
				Scanner lineScanner = new Scanner(numberLine);
				ArrayList<Integer> numberArrayList = new ArrayList<Integer>();

				while (lineScanner.hasNextInt()) {
					numberArrayList.add(lineScanner.nextInt());
				}

				Integer[] integerArray = new Integer[numberArrayList.size()];
				numbersList.add(numberArrayList.toArray(integerArray));
				lineScanner.close();

			}
			inputScanner.close();
		} catch (FileNotFoundException e) {
			return numbersList;
		}

		return numbersList;

	}

	/**
	 * Private helper functor class to implement a comparator to use for insertion
	 * sort such that we can derive the largest possible number from an array.
	 */
	private static class OrderForLargestNumber implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
			StringBuilder firstCombinedNum = new StringBuilder();
			StringBuilder secondCombinedNum = new StringBuilder();

			firstCombinedNum.append(o1);
			firstCombinedNum.append(o2);
			secondCombinedNum.append(o2);
			secondCombinedNum.append(o1);

			return Integer.parseInt(secondCombinedNum.toString()) - Integer.parseInt(firstCombinedNum.toString());
		}
	}

	/**
	 * Private helper functor class to implement a comparator to use for insertion
	 * sort such that we can sort the list by the array's largest number from
	 * biggest to smallest.
	 */
	private static class OrderForKthLargestNumber implements Comparator<Integer[]> {
		public int compare(Integer[] o1, Integer[] o2) {

			BigInteger Bo1 = LargestNumberSolver.findLargestNumber(o1);
			BigInteger Bo2 = LargestNumberSolver.findLargestNumber(o2);

			return Integer.parseInt(Bo2.toString())- Integer.parseInt(Bo1.toString());
		}
	}
}