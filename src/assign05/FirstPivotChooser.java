package assign05;

import java.util.ArrayList;

public class FirstPivotChooser implements PivotChooser{

	@Override
	public int getPivotIndex(ArrayList list, int leftIndex, int rightIndex) {
		return leftIndex;
	}

}
