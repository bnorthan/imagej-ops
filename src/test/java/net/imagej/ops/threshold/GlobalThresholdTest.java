
package net.imagej.ops.threshold;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.imagej.ops.AbstractOpTest;
import net.imglib2.Point;
import net.imglib2.algorithm.region.hypersphere.HyperSphere;
import net.imglib2.exception.IncompatibleTypeException;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.integer.ByteType;

public class GlobalThresholdTest extends AbstractOpTest {

	@Test
	public void testThreshold() throws IncompatibleTypeException {

		long[] dimensions = new long[] { 10, 10 };

		// define image and output
		Img<ByteType> in =
			new ArrayImgFactory<ByteType>().create(dimensions, new ByteType());
		Img<BitType> otsu =
			in.factory().imgFactory(new BitType()).create(in, new BitType());

		// the next few lines of code put a little circle at the center
		final Point center = new Point(in.numDimensions());

		for (int d = 0; d < in.numDimensions(); d++)
			center.setPosition(in.dimension(d) / 2, d);

		HyperSphere<ByteType> hyperSphere =
			new HyperSphere<ByteType>(in, center, 2);

		// loop through the circle coordinates, fill
		// in the circle, and count the number of pixels
		int numSpherePixels = 0;
		for (final ByteType value : hyperSphere) {
			value.setReal(75);
			numSpherePixels++;
		}

		// apply otsu segmentation algorithm
		ops.run("threshold", otsu, in, new Otsu());

		// loop through the output pixels and count
		// the number that are above zero
		long count = 0;
		for (BitType b : otsu) {
			if (b.getRealFloat() > 0) {
				count++;
			}
		}

		// number of pixels in the sphere should equal number of output pixels
		assertEquals(numSpherePixels, count);

	}

}
