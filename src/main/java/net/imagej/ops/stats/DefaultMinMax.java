/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2015 Board of Regents of the University of
 * Wisconsin-Madison, University of Konstanz and Brian Northan.
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

package net.imagej.ops.stats;

import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import net.imagej.ops.Op;
import net.imagej.ops.Ops.Stats.MinMax;
import net.imglib2.type.numeric.RealType;

/**
 * {@link Op} to calculate the {@link MinMax}
 * 
 * @author Daniel Seebacher, University of Konstanz.
 * @author Christian Dietz, University of Konstanz.
 * @param <I> input type
 */
@Plugin(type = StatOp.class, name = MinMax.NAME, label = "Statistics: MinMax")
public class DefaultMinMax<I extends RealType<I>> implements MinMax {

	@Parameter
	private Iterable<I> input;

	@Parameter(type = ItemIO.OUTPUT)
	private I min;

	@Parameter(type = ItemIO.OUTPUT)
	private I max;

	@Override
	public void run() {
		double tmpMin = Double.MAX_VALUE;
		double tmpMax = Double.MIN_VALUE;

		for (final I in : this.input) {
			final double n = in.getRealDouble();

			if (tmpMin > n) {
				tmpMin = n;
			}

			if (tmpMax < n) {
				tmpMax = n;
			}
		}

		this.min = input.iterator().next().createVariable();
		this.min.setReal(tmpMin);

		this.max = input.iterator().next().createVariable();
		this.max.setReal(tmpMax);
	}

}