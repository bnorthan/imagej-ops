/*
 * #%L
 * ImageJ OPS: a framework for reusable algorithms.
 * %%
 * Copyright (C) 2014 Board of Regents of the University of
 * Wisconsin-Madison and University of Konstanz.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imagej.ops.threshold;

import net.imagej.ops.Op;
import net.imglib2.histogram.Histogram1d;
import net.imglib2.type.numeric.RealType;

import org.scijava.plugin.Plugin;

@Plugin(type = Op.class, name = "maxentropy")
public class Triangle<T extends RealType<T>> extends GlobalThresholdMethod<T> {

	@Override
	protected final void
		getThreshold(final Histogram1d<T> hist, final T threshold)
	{

		final long[] longdata = hist.toLongArray();
		int[] data=new int[longdata.length];
		
		for (int i=0;i<longdata.length;i++) {
			data[i]=(int)longdata[i];
		}
		
		final int maxValue = (int) hist.getBinCount() - 1;
		
		// Zack, G. W., Rogers, W. E. and Latt, S. A., 1977,
		// Automatic Measurement of Sister Chromatid Exchange Frequency,
		// Journal of Histochemistry and Cytochemistry 25 (7), pp. 741-753
		//
		// modified from Johannes Schindelin plugin
		//
		// find min and max
		int min = 0, dmax = 0, max = 0, min2 = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] > 0) {
				min = i;
				break;
			}
		}
		if (min > 0) min--; // line to the (p==0) point, not to data[min]
		// The Triangle algorithm cannot tell whether the data is skewed to one side
		// or another.
		// This causes a problem as there are 2 possible thresholds between the max
		// and the 2 extremes
		// of the histogram.
		// Here I propose to find out to which side of the max point the data is
		// furthest, and use that as
		// the other extreme.
		for (int i = 255; i > 0; i--) {
			if (data[i] > 0) {
				min2 = i;
				break;
			}
		}
		if (min2 < 255) min2++; // line to the (p==0) point, not to data[min]
		for (int i = 0; i < 256; i++) {
			if (data[i] > dmax) {
				max = i;
				dmax = data[i];
			}
		}
		// find which is the furthest side
		// IJ.log(""+min+" "+max+" "+min2);
		boolean inverted = false;
		if ((max - min) < (min2 - max)) {
			// reverse the histogram
			// IJ.log("Reversing histogram.");
			inverted = true;
			int left = 0; // index of leftmost element
			int right = 255; // index of rightmost element
			while (left < right) {
				// exchange the left and right elements
				int temp = data[left];
				data[left] = data[right];
				data[right] = temp;
				// move the bounds toward the center
				left++;
				right--;
			}
			min = 255 - min2;
			max = 255 - max;
		}
		if (min == max) {
			// IJ.log("Triangle: min == max.");
			hist.getCenterValue(min, threshold);
			return;
		}
		// describe line by nx * x + ny * y - d = 0
		double nx, ny, d;
		// nx is just the max frequency as the other point has freq=0
		nx = data[max]; // -min; // data[min]; // lowest value bmin = (p=0)% in the
										// image
		ny = min - max;
		d = Math.sqrt(nx * nx + ny * ny);
		nx /= d;
		ny /= d;
		d = nx * min + ny * data[min];
		// find split point
		int split = min;
		double splitDistance = 0;
		for (int i = min + 1; i <= max; i++) {
			double newDistance = nx * i + ny * data[i] - d;
			if (newDistance > splitDistance) {
				split = i;
				splitDistance = newDistance;
			}
		}
		split--;
		if (inverted) {
			// The histogram might be used for something else, so let's reverse it
			// back
			int left = 0;
			int right = 255;
			while (left < right) {
				int temp = data[left];
				data[left] = data[right];
				data[right] = temp;
				left++;
				right--;
			}
			threshold.setReal(255 - split);
			hist.getCenterValue(255-split, threshold);
			return;
		}
		hist.getCenterValue(split, threshold);

	}

}
