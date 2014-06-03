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

package net.imagej.ops.descriptors.descriptorsets;

import java.util.Iterator;

import net.imagej.ops.descriptors.ADescriptorSet;
import net.imagej.ops.histogram.HistogramCreate1D;
import net.imglib2.Pair;
import net.imglib2.histogram.Histogram1d;
import net.imglib2.type.numeric.real.DoubleType;
import net.imglib2.util.ValuePair;

import org.scijava.Context;
import org.scijava.module.Module;

/**
 * TODO: JavaDoc
 * 
 * @author Christian Dietz (University of Konstanz)
 */
public class HistogramDescriptorSet<I> extends ADescriptorSet<I> {

	public HistogramDescriptorSet(final Context context, final Class<I> type) {
		super(context, type);
		addOp(HistogramCreate1D.class);
	}

	public void updateNumBins(final int numBins) {
		getCompiledModules().get(HistogramCreate1D.class).setInput("numBins",
				numBins);
	}

	@Override
	protected Iterator<Pair<String, DoubleType>> createIterator() {

		final Module module = getCompiledModules().get(HistogramCreate1D.class);
		final DoubleType tmp = new DoubleType();

		module.run();

		return new Iterator<Pair<String, DoubleType>>() {

			private int idx = 0;
			private final Histogram1d<?> hist = ((HistogramCreate1D<?>) getCompiledModules()
					.get(HistogramCreate1D.class).getDelegateObject())
					.getOutput();

			@Override
			public boolean hasNext() {
				return idx < hist.getBinCount();
			}

			@Override
			public Pair<String, DoubleType> next() {
				tmp.set(hist.frequency(idx));
				return new ValuePair<String, DoubleType>("Histogram 1D [Bin:"
						+ idx++ + "]", tmp);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Remove not supported!");
			}
		};
	}

}