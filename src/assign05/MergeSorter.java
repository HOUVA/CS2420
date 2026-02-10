package assign05;

import java.util.ArrayList;

public class MergeSorter implements Sorter {

	public MergeSorter(int threshold) throws IllegalArgumentException {
		// TODO The parameter indicates the sublist size at which the sorting must
		// switch over to insertion sort.
		if (threshold <= 0)
			throw new IllegalArgumentException();

	}

	@Override
	public void sort(ArrayList list) {
		// TODO The sort method inherited from the Sorter interface should be a "driver"
		// method, which calls your private recursive merge sort method. If the sort
		// method is passed a list whose size is smaller than the threshold previously
		// set, then the size of the list should be used as the threshold instead.

	}

}
