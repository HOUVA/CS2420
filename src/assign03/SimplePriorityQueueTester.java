package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for SimplePriorityQueue.
 * 
 * @author Barrett Carpenter and Matthew Suggars
 * @version January 29, 2026.
 */
class SimplePriorityQueueTester {
	private SimplePriorityQueue<Integer> integerQueue, integerQueueCmp;
	private SimplePriorityQueue<String> stringQueue, stringQueueCmp;
	private SimplePriorityQueue<Character> charQueue, charQueueCmp;
	private SimplePriorityQueue<Integer> emptyQueue;

	@BeforeEach
	void setup() {
		integerQueue = new SimplePriorityQueue<Integer>();
		stringQueue = new SimplePriorityQueue<String>();
		charQueue = new SimplePriorityQueue<Character>();
		emptyQueue = new SimplePriorityQueue<Integer>();

		integerQueueCmp = new SimplePriorityQueue<Integer>((int1, int2) -> int2 - int1);
		stringQueueCmp = new SimplePriorityQueue<String>((string1, string2) -> string1.length() - string2.length());
		charQueueCmp = new SimplePriorityQueue<Character>((char1, char2) -> char2 - char1);

		String[] baseStringArray = { "below", "a", "eat", "do", "clap", };
		char[] baseCharArray = { 'a', 'b', 'c', 'd', 'e' };
		for (int i = 0; i < 5; i++) {
			integerQueue.insert(i);
			stringQueue.insert(baseStringArray[i]);
			charQueue.insert(baseCharArray[i]);

			integerQueueCmp.insert(i);
			stringQueueCmp.insert(baseStringArray[i]);
			charQueueCmp.insert(baseCharArray[i]);
		}
	}

	// Testing clear Method
	@Test
	void testClearInteger() {
		integerQueue.clear();
		assertEquals(0, integerQueue.size());
	}

	@Test
	void testClearIntegerCmp() {
		integerQueueCmp.clear();
		assertEquals(0, integerQueueCmp.size());
	}

	@Test
	void testClearString() {
		stringQueue.clear();
		assertEquals(0, stringQueue.size());
	}

	@Test
	void testClearStringCmp() {
		stringQueueCmp.clear();
		assertEquals(0, stringQueueCmp.size());
	}

	@Test
	void testClearCharacter() {
		charQueue.clear();
		assertEquals(0, charQueue.size());
	}

	@Test
	void testClearCharacterCmp() {
		charQueueCmp.clear();
		assertEquals(0, charQueueCmp.size());
	}

	@Test
	void testClearEmptyQueue() {
		emptyQueue.clear();
		assertEquals(0, emptyQueue.size());
	}

	// Testing contains method
	@Test
	void testContainsInteger() {
		assertTrue(integerQueue.contains(1));
		assertFalse(integerQueue.contains(6));
	}

	@Test
	void testContainsIntegerCmp() {
		assertTrue(integerQueueCmp.contains(1));
		assertFalse(integerQueueCmp.contains(6));
	}

	@Test
	void testContainsString() {
		assertTrue(stringQueue.contains("eat"));
		assertFalse(stringQueue.contains("Not in Queue"));
	}

	@Test
	void testContainsStringCmp() {
		assertTrue(stringQueueCmp.contains("eat"));
		assertFalse(stringQueueCmp.contains("Not in Queue"));
	}

	@Test
	void testContainsCharacter() {
		assertTrue(charQueue.contains('a'));
		assertFalse(charQueue.contains('A'));
	}

	@Test
	void testContainsCharacterCmp() {
		assertTrue(charQueueCmp.contains('a'));
		assertFalse(charQueueCmp.contains('A'));
	}

	@Test
	void testContainsEmptyQueue() {
		assertFalse(emptyQueue.contains(1));
	}

//	// Testing containsAll method
//	@Test
//	void testContainsAllInteger() {
//	}
//
//	@Test
//	void testContainsAllIntegerCmp() {
//	}
//	
//	@Test
//	void testContainsAllString() {
//	}
//
//	@Test
//	void testContainsAllStringCmp() {
//	}
//	
//	@Test
//	void testContainsAllCharacter() {
//	}
//
//	@Test
//	void testContainsAllCharacterCmp() {
//	}
//	
//	@Test
//	void testContainsAllEmptyQueue() {
//	}

	// Testing insert Method
	
	// Testing insertAll Method
	
	// Testing isEmpty Method
	@Test
	void testIsEmptyInteger() {
		assertFalse(integerQueue.isEmpty());
		integerQueue.clear();
		assertTrue(integerQueue.isEmpty());
	}

	@Test
	void testIsEmptyIntegerCmp() {
		assertFalse(integerQueueCmp.isEmpty());
		integerQueueCmp.clear();
		assertTrue(integerQueueCmp.isEmpty());
	}

	@Test
	void testIsEmptyString() {
		assertFalse(stringQueue.isEmpty());
		stringQueue.clear();
		assertTrue(stringQueue.isEmpty());
	}

	@Test
	void testIsEmptyStringCmp() {
		assertFalse(stringQueueCmp.isEmpty());
		stringQueueCmp.clear();
		assertTrue(stringQueueCmp.isEmpty());
	}

	@Test
	void testIsEmptyCharacter() {
		assertFalse(charQueue.isEmpty());
		charQueue.clear();
		assertTrue(charQueue.isEmpty());
	}

	@Test
	void testIsEmptyCharacterCmp() {
		assertFalse(charQueueCmp.isEmpty());
		charQueueCmp.clear();
		assertTrue(charQueueCmp.isEmpty());
	}

	@Test
	void testIsEmptyEmptyQueue() {
		assertTrue(emptyQueue.isEmpty());
	}
	
	// Testing size method
	@Test
	void testSizeInteger() {
		assertEquals(5, integerQueue.size());
		integerQueue.clear();
		assertEquals(0, integerQueue.size());
	}

	@Test
	void testSizeIntegerCmp() {
		assertEquals(5, integerQueueCmp.size());
		integerQueueCmp.clear();
		assertEquals(0, integerQueueCmp.size());
	}

	@Test
	void testSizeString() {
		assertEquals(5, stringQueue.size());
		stringQueue.clear();
		assertEquals(0, stringQueue.size());
	}

	@Test
	void testSizeStringCmp() {
		assertEquals(5, stringQueueCmp.size());
		stringQueueCmp.clear();
		assertEquals(0, stringQueueCmp.size());
	}
	@Test
	void testSizeCharacter() {
		assertEquals(5, charQueue.size());
		charQueue.clear();
		assertEquals(0, charQueue.size());
	}

	@Test
	void testSizeCharacterCmp() {
		assertEquals(5, charQueueCmp.size());
		charQueueCmp.clear();
		assertEquals(0, charQueueCmp.size());
	}
	
	// Testing findMax method
	@Test
	void testFindMaxInteger() {
		assertEquals(4, integerQueue.findMax());
		assertEquals(4, integerQueue.findMax());
	}

	@Test
	void testFindMaxIntegerCmp() {
		assertEquals(0, integerQueueCmp.findMax());
		assertEquals(0, integerQueueCmp.findMax());
	}

	@Test
	void testFindMaxString() {
		assertEquals("eat", stringQueue.findMax());
		assertEquals("eat", stringQueue.findMax());
	}

	@Test
	void testFindMaxStringCmp() {
		assertEquals("below", stringQueueCmp.findMax());
		assertEquals("below", stringQueueCmp.findMax());

	}
	@Test
	void testFindMaxCharacter() {
		assertEquals('e', charQueue.findMax());
		assertEquals('e', charQueue.findMax());
	}

	@Test
	void testFindMaxCharacterCmp() {
		assertEquals('a', charQueueCmp.findMax());
		assertEquals('a', charQueueCmp.findMax());
	}
	
	@Test
	void testFindMaxEmptyQueue() {
		assertThrows(NoSuchElementException.class, () -> {emptyQueue.findMax();});
	}
	
	// Testing deleteMax method
	@Test
	void testDeleteMaxInteger() {
		assertEquals(4, integerQueue.deleteMax());
		assertEquals(3, integerQueue.deleteMax());
	}

	@Test
	void testDeleteMaxIntegerCmp() {
		assertEquals(0, integerQueueCmp.deleteMax());
		assertEquals(1, integerQueueCmp.deleteMax());
	}

	@Test
	void testDeleteMaxString() {
		assertEquals("eat", stringQueue.deleteMax());
		assertEquals("do", stringQueue.deleteMax());
	}

	@Test
	void testDeleteMaxStringCmp() {
		assertEquals("below", stringQueueCmp.deleteMax());
		assertEquals("clap", stringQueueCmp.deleteMax());

	}
	@Test
	void testDeleteMaxCharacter() {
		assertEquals('e', charQueue.deleteMax());
		assertEquals('d', charQueue.deleteMax());
	}

	@Test
	void testDeleteMaxCharacterCmp() {
		assertEquals('a', charQueueCmp.deleteMax());
		assertEquals('b', charQueueCmp.deleteMax());
	}
	
	@Test
	void testDeleteMaxEmptyQueue() {
		assertThrows(NoSuchElementException.class, () -> {emptyQueue.deleteMax();});
	}
}
