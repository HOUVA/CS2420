package lab04;

import java.util.Arrays;
import java.util.Comparator;

public class MedianSolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static <T extends Comparable<T>> T findMedian(T[] arr) {
		Arrays.sort(arr);
		return arr[arr.length / 2];
	}
	
	public static <T> T findMedian(T[] arr, Comparator<T> cmp) {
		Arrays.sort(arr, cmp);
		return arr[arr.length / 2];
	}

}
