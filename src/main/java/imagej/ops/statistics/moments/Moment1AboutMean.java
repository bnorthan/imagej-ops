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

package imagej.ops.statistics.moments;

import imagej.ops.AbstractThreadableFunction;
import imagej.ops.Op;
import imagej.ops.OpService;
import imagej.ops.misc.Size;
import imagej.ops.statistics.Mean;

import java.util.Iterator;

import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.integer.LongType;
import net.imglib2.type.numeric.real.DoubleType;

import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type = Op.class, name = "moment1aboutmean")
public class Moment1AboutMean<T extends RealType<T>> extends
	AbstractThreadableFunction<Iterable<T>, DoubleType>
{

	@Parameter(required = false)
	private Mean<Iterable<T>, DoubleType> mean;

	@Parameter(required = false)
	private Size<Iterable<T>> size;

	@Parameter
	private OpService ops;

	@Override
	public DoubleType compute(final Iterable<T> input, final DoubleType output) {
		if (mean == null) {
			mean = (Mean<Iterable<T>, DoubleType>) ops.op(Mean.class, output, input);
		}
		if (size == null) {
			size = (Size<Iterable<T>>) ops.op(Size.class, output, input);
		}

		final double mean = this.mean.compute(input, new DoubleType()).get();
		final double area = this.size.compute(input, new LongType()).get();
		double res = 0.0;

		final Iterator<T> it = input.iterator();
		while (it.hasNext()) {
			final double val = it.next().getRealDouble() - mean;
			res += val;
		}
		output.set(res / area);
		return output;
	}
}
