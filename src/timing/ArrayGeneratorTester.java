package timing;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ArrayGenerator
 */
import org.junit.jupiter.api.Test;

class ArrayGeneratorTester {

	@Test
	void test() {
		Integer[] actualArray = ArrayGenerator.generateDescendingArray(50);
		assertTrue(actualArray[0] > actualArray[1]);
	}

}
