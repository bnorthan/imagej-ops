
package net.imagej.ops.threshold;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.imagej.ops.AbstractOpTest;
import net.imglib2.exception.IncompatibleTypeException;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.RandomAccess;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.integer.UnsignedShortType;

/**
 * Tests for Global Threshold ops
 * 
 * @author bnorthan
 */
public class GlobalThresholdTest extends AbstractOpTest {

	/**
	 * This test generates a 2D ramp image then threshold using the otsu method. A
	 * constant value of 1000.5 is added to the ramp to test that the threshold
	 * works with the offset. After thresholding the number of foreground pixels
	 * are counted. Testing on the same image using Imagej1 and Otsu threshold
	 * resulted in 45 foreground pixels. We verify that the op is producing the
	 * same result.
	 * 
	 * @throws IncompatibleTypeException
	 */
	@Test
	public void testThreshold() throws IncompatibleTypeException {

		int xSize = 10;
		int ySize = 10;

		long[] dimensions = new long[] { xSize, ySize };

		// create image and output
		Img<UnsignedShortType> in =
			new ArrayImgFactory<UnsignedShortType>().create(dimensions, new UnsignedShortType());
		Img<BitType> otsu =
			in.factory().imgFactory(new BitType()).create(in, new BitType());

		RandomAccess<UnsignedShortType> ra = in.randomAccess();

		// populate pixel values with a ramp function + a constant
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				ra.setPosition(new int[] { x, y });
				ra.get().setReal(x + y + 1000);
			}
		}

		// apply Otsu segmentation algorithm
		ops.run("threshold", otsu, in, new Otsu());

		// loop through the output pixels and count
		// the number that are above zero
		long count = 0;
		for (BitType b : otsu) {
			if (b.getRealFloat() > 0) {
				count++;
			}
		}

		// thresholding the same image using imagej1 Otsu produced an output with 55
		// foreground pixels
		// verify the result from the op also has 45 foreground pixels.
		assertEquals(45, count);

	}

}
