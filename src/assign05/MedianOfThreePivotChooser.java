package assign05;

import java.util.ArrayList;

public class MedianOfThreePivotChooser implements PivotChooser{

	@Override
	public int getPivotIndex(ArrayList list, int leftIndex, int rightIndex) {
		return (leftIndex + rightIndex + list.size() / 2) / 3;
	}

}
