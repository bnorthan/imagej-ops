/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2016 Board of Regents of the University of
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

package net.imagej.ops.deconvolve;

import net.imagej.ops.OpService;
import net.imagej.ops.Ops;
import net.imagej.ops.deconvolve.accelerate.VectorAccelerator;
import net.imagej.ops.filter.AbstractFFTFilterF;
import net.imagej.ops.special.computer.BinaryComputerOp;
import net.imagej.ops.special.computer.Computers;
import net.imagej.ops.special.computer.UnaryComputerOp;
import net.imagej.ops.special.inplace.UnaryInplaceOp;
import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.outofbounds.OutOfBoundsConstantValueFactory;
import net.imglib2.outofbounds.OutOfBoundsMirrorFactory;
import net.imglib2.outofbounds.OutOfBoundsMirrorFactory.Boundary;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.ComplexType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.util.Util;

import org.scijava.Priority;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * Richardson Lucy function op that operates on (@link RandomAccessibleInterval)
 * (Lucy, L. B. (1974).
 * "An iterative technique for the rectification of observed distributions".)
 * 
 * @author Brian Northan
 * @param <I>
 * @param <O>
 * @param <K>
 * @param <C>
 */
@Plugin(type = Ops.Deconvolve.RichardsonLucy.class,
	priority = Priority.HIGH_PRIORITY)
public class RichardsonLucyF<I extends RealType<I>, O extends RealType<O> & NativeType<O>, K extends RealType<K>, C extends ComplexType<C> & NativeType<C>>
	extends AbstractFFTFilterF<I, O, K, C> implements
	Ops.Deconvolve.RichardsonLucy
{

	@Parameter
	private OpService ops;

	/**
	 * max number of iterations
	 */
	@Parameter
	private int maxIterations;

	/**
	 * indicates whether to use non-circulant edge handling
	 */
	@Parameter(required = false)
	private boolean nonCirculant = false;

	/**
	 * indicates whether to use acceleration
	 */
	@Parameter(required = false)
	private boolean accelerate = false;

	private UnaryComputerOp<RandomAccessibleInterval<O>, RandomAccessibleInterval<O>> computeEstimateOp;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize() {

		// TODO: try and figure out if there is a better place to set the default
		// OBF

		// the out of bounds factory will be different depending on wether we are
		// using circulant or non-circulant
		if (this.getOBFInput() == null) {

			if (!nonCirculant) {
				setOBFInput(new OutOfBoundsMirrorFactory<>(Boundary.SINGLE));
			}
			else if (nonCirculant) {
				setOBFInput(new OutOfBoundsConstantValueFactory<>(Util
					.getTypeFromInterval(in()).createVariable()));
			}
		}

		computeEstimateOp = (UnaryComputerOp) Computers.unary(ops(),
			RichardsonLucyUpdate.class, RandomAccessibleInterval.class,
			RandomAccessibleInterval.class);

		super.initialize();

	}

	/**
	 * create a richardson lucy filter
	 */
	@Override
	@SuppressWarnings("unchecked")
	public
		BinaryComputerOp<RandomAccessibleInterval<I>, RandomAccessibleInterval<K>, RandomAccessibleInterval<O>>
		createFilterComputer(RandomAccessibleInterval<I> raiExtendedInput,
			RandomAccessibleInterval<K> raiExtendedKernel,
			RandomAccessibleInterval<C> fftImg, RandomAccessibleInterval<C> fftKernel,
			RandomAccessibleInterval<O> output, Interval imgConvolutionInterval)
	{
		UnaryInplaceOp<O> accelerator = null;

		if (accelerate) {
			accelerator = ops.op(VectorAccelerator.class, output);
		}

		if (nonCirculant) {
			return Computers.binary(ops(), RichardsonLucyNonCirculantC.class, output,
				raiExtendedInput, raiExtendedKernel, fftImg, fftKernel, true, true,
				maxIterations, imgConvolutionInterval, accelerator, in(),
				in2(), computeEstimateOp);	
		}
		
		return Computers.binary(ops(), RichardsonLucyC.class, output,
			raiExtendedInput, raiExtendedKernel, fftImg, fftKernel, true, true,
			maxIterations, imgConvolutionInterval, accelerator,
			null, computeEstimateOp);
	}

	public void setComputeEstimateOp(
		UnaryComputerOp<RandomAccessibleInterval<O>, RandomAccessibleInterval<O>> computeEstimateOp)
	{
		this.computeEstimateOp = computeEstimateOp;
	}

}