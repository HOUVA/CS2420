package assign05;

import java.util.ArrayList;
import java.util.Random;

public class RandomPivotChooser implements PivotChooser{
	Random rnd = new Random();
	
	@Override
	public int getPivotIndex(ArrayList list, int leftIndex, int rightIndex) {
		return rnd.nextInt(leftIndex, rightIndex + 1);
	}

}
