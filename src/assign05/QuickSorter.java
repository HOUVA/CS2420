package assign05;

import java.util.ArrayList;

public class QuickSorter implements Sorter<Integer> {
	PivotChooser<Integer> chooser;

	public QuickSorter(PivotChooser<Integer> chooser) {
		this.chooser = chooser;

	}

	@Override
	public void sort(ArrayList list) {
		// TODO setup the driver method to call private quickSort method.

	}

	/**
	 * Private helper method to sort given list using a quicksort algorithm.
	 * 
	 * @param list - to sort
	 */
	private void quickSort(ArrayList list) {
		// TODO establish a pivot point
		// TODO start at the leftIndex, and iterate up the list until we find an element
		// which is larger than the pivot
		// TODO continue at the rightIndex, and iterate down the list until we find an
		// element which is smaller than the pivot
		// TODO once we have found an element for each index, we swap their places.
		// TODO continue this until the leftIndex and rightIndex are pointing to the
		// same element. Then swap the pivot with the element both indexes are pointing
		// at.

		// TODO continue these steps for the elements to the left of the pivot and to
		// the right. This step will require recursion.

	}

}
