package assign01;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel"
 * brightness values: 255 is "white", 127 is "gray", and 0 is "black" with
 * intermediate values in between.
 * 
 * @author CS 2420 course staff and Matthew Suggars.
 * @version January 8, 2026.
 */
public class GrayscaleImage {

	private double[][] imageData; // array of pixel brightness values

	/**
	 * Initializes an image from a 2D array of doubles. This constructor creates a
	 * copy of the input array.
	 * 
	 * @param data initial pixel values
	 * @throws IllegalArgumentException if the input array is empty or "jagged"
	 *                                  meaning not all rows are the same length
	 */
	public GrayscaleImage(double[][] data) {
		if (data.length == 0 || data[0].length == 0) {
			throw new IllegalArgumentException("Image is empty");
		}

		imageData = new double[data.length][data[0].length];
		for (int row = 0; row < imageData.length; row++) {
			if (data[row].length != imageData[row].length) {
				throw new IllegalArgumentException("All rows must have the same length");
			}
			for (int col = 0; col < imageData[row].length; col++) {
				imageData[row][col] = data[row][col];
			}
		}
	}

	/**
	 * Fetches an image from the specified URL and converts it to grayscale. Uses
	 * the AWT Graphics2D class to do the conversion, so it may add an item to your
	 * dock/menu bar as if you are loading a GUI program.
	 * 
	 * @param url from which to download the image
	 * @throws IOException if the image cannot be downloaded for some reason
	 */
	public GrayscaleImage(URL url) throws IOException {
		BufferedImage inputImage = ImageIO.read(url);
		// Converts input image to grayscale based on information from
		// https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models
		BufferedImage grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2d = grayImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, null);
		g2d.dispose();
		imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

		// Raster is basically a width x height x 1 3D array
		WritableRaster grayRaster = grayImage.getRaster();
		for (int row = 0; row < imageData.length; row++) {
			for (int col = 0; col < imageData[0].length; col++) {
				// getSample parameters are x (our column) and y (our row); i.e., "backwards"
				imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
			}
		}
	}

	/**
	 * Saves the image as a PNG file.
	 * 
	 * @param filename of the created image file
	 * @throws IOException if the file cannot be written
	 */
	public void savePNG(File filename) throws IOException {
		BufferedImage outputImage = new BufferedImage(imageData[0].length, imageData.length,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = outputImage.getRaster();
		for (int row = 0; row < imageData.length; row++) {
			for (int col = 0; col < imageData[0].length; col++) {
				raster.setSample(col, row, 0, imageData[row][col]);
			}
		}
		ImageIO.write(outputImage, "png", filename);
	}

	/**
	 * Returns the pixel brightness value at the specified x and y coordinates.
	 * 
	 * @param x coordinate of pixel.
	 * @param y coordinate of pixel.
	 * @return the pixel value at the given coordinates.
	 */
	public double getPixel(int x, int y) {
		validateCoordinates(x, y);
		return imageData[y][x];
	}

	/**
	 * Sets the pixel value at the specified x and y coordinates.
	 * 
	 * @param x               coordinate of pixel.
	 * @param y               coordinate of pixel.
	 * @param brightnessValue to replace current pixel value.
	 */
	public void setPixel(int x, int y, double brightnessValue) {
		validateCoordinates(x, y);
		imageData[y][x] = brightnessValue;
	}

	/**
	 * Checks if the object passed is equal to this GrayscaleImage object.
	 * 
	 * @param other object to compare to.
	 * @return true if both objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof GrayscaleImage))
			return false;

		GrayscaleImage otherImage = (GrayscaleImage) other;

		for (int row = 0; row < this.imageData.length; row++)
			for (int col = 0; col < this.imageData[row].length; col++)
				if (this.imageData[row][col] != otherImage.getPixel(col, row))
					return false;
		return true;

	}

	/**
	 * Creates and returns a new, similar GrayscaleImage where the average
	 * brightness is changed to 127.
	 * 
	 * @return GrayscaleImage object with normalized values.
	 */
	public GrayscaleImage normalized() {

		GrayscaleImage normalImage = new GrayscaleImage(this.imageData);
		double scalar = 127 / averageBrightness();

		for (int row = 0; row < this.imageData.length; row++)
			for (int col = 0; col < this.imageData[row].length; col++)
				normalImage.setPixel(col, row, normalImage.getPixel(col, row) * scalar);

		return normalImage;
	}

	/**
	 * Computes and returns the average of all brightness values in the image.
	 * 
	 * @return the average of all the brightness values.
	 */
	public double averageBrightness() {
		int quantityCounter = 0;
		double valueSum = 0.0;

		for (int row = 0; row < this.imageData.length; row++) {
			for (int col = 0; col < this.imageData[row].length; col++) {
				quantityCounter++;
				valueSum += this.imageData[row][col];
			}
		}

		return valueSum / quantityCounter;
	}

	/**
	 * This instance method modifies the GrayscaleImage by inverting the brightness
	 * values. Manipulates the non-static imageData field.
	 */
	public void invert() {
		for (int row = 0; row < this.imageData.length; row++) {
			for (int col = 0; col < this.imageData[row].length; col++) {
				double value = this.imageData[row][col];
				imageData[row][col] = 255 - value;
			}
		}
	}

	/**
	 * Updates the given GrayscaleImage such that it is mirrored across the y-axis.
	 * 
	 * @param image to mirror.
	 */
	public static void mirrored(GrayscaleImage image) {
		int rowCount = 0, colCount = 0;

		try {
			while (true) {
				image.getPixel(0, rowCount);
				rowCount++;
			}
		} catch (IllegalArgumentException e) {
		}

		try {
			while (true) {
				image.getPixel(colCount, 0);
				colCount++;
			}
		} catch (IllegalArgumentException e) {
		}

		for (int height = 0; height < rowCount; height++) {
			for (int width = 0; width < colCount / 2; width++) {
				double firstValue = image.getPixel(width, height);
				image.setPixel(width, height, image.getPixel(colCount - 1 - width, height));
				image.setPixel(colCount - 1 - width, height, firstValue);
			}
		}
	}

	/**
	 * Helper method to determine if the given coordinates are within range of the
	 * image. If coordinates are not in range, throws an Exception.
	 * 
	 * @param x coordinate to validate.
	 * @param y coordinate to validate.
	 * @throws IllegalArgumentException when either coordinate is outside of range.
	 */
	private void validateCoordinates(int x, int y) throws IllegalArgumentException {
		if (y >= imageData.length || x >= imageData[0].length)
			throw new IllegalArgumentException("Coordinates provided are outside range.");
	}

}