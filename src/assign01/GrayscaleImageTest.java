package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * For testing the GrayscaleImage class.
 * 
 * @author CS 2420 course staff and Matthew Suggars
 * @version January 11, 2025.
 */
public class GrayscaleImageTest {

	private GrayscaleImage smallSquare;
	private GrayscaleImage smallWide;
	private GrayscaleImage smallTall;

	/**
	 * A helper method that checks each pixel against an expected array of values.
	 * This assumes that the getPixel method is working correctly.
	 * 
	 * @param expected array of values
	 * @param actual GrayscaleImage to compare to the expected values
	 */
	private void assertPixelValuesEqual(double[][] expected, GrayscaleImage actual) {
		for(int row = 0; row < expected.length; row++) {
			for(int col = 0; col < expected[0].length; col++) {
				assertEquals(expected[row][col], actual.getPixel(col, row), expected[row][col] * .0001,
						"pixel at row: " + row + " col: " + col + " incorrect");
			}
		}
	}

	@BeforeEach
	public void setUp() {
		smallSquare = new GrayscaleImage(new double[][] { { 1, 2 }, { 3, 4 } });
		smallWide = new GrayscaleImage(new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
		smallTall = new GrayscaleImage(new double[][] { {1, 2}, {3, 4}, {5, 6} });
	}

	/*
	 * Test cases for getPixel and setPixel.
	 */
	
	@Test
	public void testGetPixel() {
		assertEquals(1, smallWide.getPixel(0, 0));
		assertEquals(2, smallWide.getPixel(1, 0));
		assertEquals(3, smallWide.getPixel(2, 0));
		assertEquals(4, smallWide.getPixel(0, 1));
		assertEquals(5, smallWide.getPixel(1, 1));
		assertEquals(6, smallWide.getPixel(2, 1));
	}
	
	@Test
	public void testGetPixelOutOfBounds() {
		assertThrows(IllegalArgumentException.class, () -> {smallWide.getPixel(3, 0);});
		assertThrows(IllegalArgumentException.class, () -> {smallWide.getPixel(0, 3);});
	}

	
	@Test
	public void testSetPixel() {
		smallWide.setPixel(0, 0, 2.0);
		assertEquals(2.0, smallWide.getPixel(0, 0), 0.1);
		smallWide.setPixel(1, 0, 3.0);
		assertEquals(3.0, smallWide.getPixel(1, 0), 0.1);
		smallWide.setPixel(2, 0, 4.0);
		assertEquals(4.0, smallWide.getPixel(2, 0), 0.1);
		smallWide.setPixel(0, 1, 5.0);
		assertEquals(5.0, smallWide.getPixel(0, 1), 0.1);
		smallWide.setPixel(1, 1, 6.0);
		assertEquals(6.0, smallWide.getPixel(1, 1), 0.1);
		smallWide.setPixel(2, 1, 7.0);
		assertEquals(7.0, smallWide.getPixel(2, 1), 0.1);
	}

	@Test
	public void testSetPixelOutOfBounds() {
		assertThrows(IllegalArgumentException.class, () -> {smallWide.setPixel(3, 0, 12.0);});
		assertThrows(IllegalArgumentException.class, () -> {smallWide.setPixel(0, 3, 12.0);});
	}
	
	@Test
	public void testSetPixelIntAsBrightnessValue() {
		smallWide.setPixel(0, 0, 2);
		assertEquals(2, smallWide.getPixel(0, 0));
	}
	
	/*
	 * Test cases for Equals.
	 */
	@Test
	public void testEqualsSelf() {
		assertEquals(smallSquare, smallSquare, "Image was not equal to itself");
	}

	@Test
	public void testEqualsEquivalent() {
		GrayscaleImage equivalent = new GrayscaleImage(new double[][] { { 1, 2 }, { 3, 4 } });
		assertEquals(smallSquare, equivalent, "Image was not equal to a distinct but equivalent image");
	}
	
	@Test
	public void testEqualsDifferentObject() {
		assertNotEquals(smallSquare, new String(), "Image was equal to a different object type");
	}
	
	@Test
	public void testEqualsNotEquivalent() {
		GrayscaleImage notEquivalent = new GrayscaleImage(new double[2][2]);
		assertNotEquals(smallSquare, notEquivalent);
	}

	/*
	 * Test cases for Normalized.
	 */
	
	@Test
	public void testNormalizedSquareMatrix() {
		GrayscaleImage smallNorm = smallSquare.normalized();
		double scale = 127 / 2.5;
		double[][] expected = new double[][] { { scale, 2 * scale }, { 3 * scale, 4 * scale } };
		assertPixelValuesEqual(expected, smallNorm);
	}
	
	@Test
	public void testNormalizedWideMatrix() {
		GrayscaleImage wideNorm = smallWide.normalized();
		double scale = 127/3.5;
		double[][] expected = new double[][] { {scale, 2 * scale, 3 * scale}, {4 * scale, 5 * scale, 6 * scale} };
		assertPixelValuesEqual(expected, wideNorm);
	}
	
	@Test
	public void testNormalizedTallMatrix() {
		GrayscaleImage wideNorm = smallTall.normalized();
		double scale = 127/3.5;
		double[][] expected = new double[][] { {scale, 2 * scale}, {3 * scale, 4 * scale}, {5 * scale, 6 * scale} };
		assertPixelValuesEqual(expected, wideNorm);
	}

	/*
	 * Test cases for Invert.
	 */
	
	@Test
	public void testInvert() {
		smallSquare.invert();
		double[][] expected = new double[][] { { 254, 253 }, { 252, 251 } };
		assertPixelValuesEqual(expected, smallSquare);
	}
	
	public void testInvertWideMatrix() {
		smallWide.invert();
		double[][] expected = new double[][] { {254, 253, 252}, {251, 250, 249} };
		assertPixelValuesEqual(expected, smallWide);
	}
	
	public void testInvertTallMatrix() {
		smallTall.invert();
		double[][] expected = new double[][] { {254, 253}, {252, 251}, {250, 249} };
		assertPixelValuesEqual(expected, smallTall);
	}
	
	/*
	 * Test cases for Mirrored.
	 */
	@Test
	public void testMirrored() {
		double[][] expected = new double[][] { { 2, 1 }, { 4, 3 } };
		GrayscaleImage.mirrored(smallSquare);
		assertPixelValuesEqual(expected, smallSquare);
	}
	
	public void testMirroredWideMatrix() {
		double[][] expected = new double[][] { {3, 2, 1}, {6, 5, 4} };
		GrayscaleImage.mirrored(smallWide);
		assertPixelValuesEqual(expected, smallWide);
	}
	
	public void testMirroredTallMatrix() {
		double[][] expected = new double[][] { {2, 1}, {4, 3}, {6, 5} };
		GrayscaleImage.mirrored(smallTall);
		assertPixelValuesEqual(expected, smallTall);
	}
}