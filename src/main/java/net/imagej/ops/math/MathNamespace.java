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

package net.imagej.ops.math;

import java.util.Random;

import net.imagej.ops.AbstractNamespace;
import net.imagej.ops.MathOps;
import net.imagej.ops.OpMethod;
import net.imglib2.IterableInterval;
import net.imglib2.IterableRealInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.basictypeaccess.array.ByteArray;
import net.imglib2.img.basictypeaccess.array.DoubleArray;
import net.imglib2.img.planar.PlanarImg;
import net.imglib2.type.numeric.NumericType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.type.numeric.real.DoubleType;

/**
 * The math namespace contains arithmetic operations.
 *
 * @author Curtis Rueden
 */
public class MathNamespace extends AbstractNamespace {

	// -- Math namespace ops --

	@OpMethod(op = net.imagej.ops.MathOps.Abs.class)
	public Object abs(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Abs.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerAbs.class)
	public int abs(final int a) {
		final int result =
			(Integer) ops()
				.run(net.imagej.ops.math.PrimitiveMath.IntegerAbs.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongAbs.class)
	public long abs(final long a) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongAbs.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatAbs.class)
	public float abs(final float a) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatAbs.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleAbs.class)
	public double abs(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleAbs.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealAbs.class)
	public <I extends RealType<I>, O extends RealType<O>> O abs(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealAbs.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Add.class)
	public Object add(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Add.class, args);
	}

	@OpMethod(ops = {
		net.imagej.ops.arithmetic.add.parallel.AddConstantToArrayByteImageP.class,
		net.imagej.ops.arithmetic.add.AddConstantToArrayByteImage.class })
	public ArrayImg<ByteType, ByteArray> add(
		final ArrayImg<ByteType, ByteArray> image, final byte value)
	{
		@SuppressWarnings("unchecked")
		final ArrayImg<ByteType, ByteArray> result =
			(ArrayImg<ByteType, ByteArray>) ops().run(MathOps.Add.NAME, image, value);
		return result;
	}

	@OpMethod(
		ops = {
			net.imagej.ops.arithmetic.add.parallel.AddConstantToArrayDoubleImageP.class,
			net.imagej.ops.arithmetic.add.AddConstantToArrayDoubleImage.class })
	public
		ArrayImg<DoubleType, DoubleArray> add(
			final ArrayImg<DoubleType, DoubleArray> image, final double value)
	{
		@SuppressWarnings("unchecked")
		final ArrayImg<DoubleType, DoubleArray> result =
			(ArrayImg<DoubleType, DoubleArray>) ops().run(MathOps.Add.NAME, image,
				value);
		return result;
	}

	@OpMethod(op = net.imagej.ops.onthefly.ArithmeticOp.AddOp.class)
	public Object add(final Object result, final Object a, final Object b) {
		final Object result_op =
			ops().run(net.imagej.ops.onthefly.ArithmeticOp.AddOp.class, result, a, b);
		return result_op;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerAdd.class)
	public int add(final int a, final int b) {
		final int result =
			(Integer) ops().run(net.imagej.ops.math.PrimitiveMath.IntegerAdd.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongAdd.class)
	public long add(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongAdd.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatAdd.class)
	public float add(final float a, final float b) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatAdd.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleAdd.class)
	public double add(final double a, final double b) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleAdd.class, a,
				b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealAdd.class)
	public <I extends RealType<I>, O extends RealType<O>> RealType<O> add(
		final RealType<O> out, final RealType<I> in, final double constant)
	{
		@SuppressWarnings("unchecked")
		final RealType<O> result =
			(RealType<O>) ops().run(net.imagej.ops.arithmetic.real.RealAdd.class,
				out, in, constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.RealBinaryMath.Add.class)
	public <T extends RealType<T>> T add(final T in, final T b) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(net.imagej.ops.math.RealBinaryMath.Add.class, in, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.RealBinaryMath.Add.class)
	public <T extends RealType<T>> T add(final T out, final T in, final T b) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(net.imagej.ops.math.RealBinaryMath.Add.class, out, in, b);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.arithmetic.add.AddRandomAccessibleIntervalToIterableInterval.class)
	public
		<T extends NumericType<T>> IterableInterval<T> add(
			final IterableInterval<T> a, final RandomAccessibleInterval<T> b)
	{
		@SuppressWarnings("unchecked")
		final IterableInterval<T> result =
			(IterableInterval<T>) ops()
				.run(
					net.imagej.ops.arithmetic.add.AddRandomAccessibleIntervalToIterableInterval.class,
					a, b);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.arithmetic.add.AddConstantToPlanarDoubleImage.class)
	public PlanarImg<DoubleType, DoubleArray> add(
		final PlanarImg<DoubleType, DoubleArray> image, final double value)
	{
		@SuppressWarnings("unchecked")
		final PlanarImg<DoubleType, DoubleArray> result =
			(PlanarImg<DoubleType, DoubleArray>) ops().run(
				net.imagej.ops.arithmetic.add.AddConstantToPlanarDoubleImage.class,
				image, value);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.add.AddConstantToImageInPlace.class)
	public <T extends NumericType<T>> IterableRealInterval<T> add(
		final IterableRealInterval<T> image, final T value)
	{
		@SuppressWarnings("unchecked")
		final IterableRealInterval<T> result =
			(IterableRealInterval<T>) ops().run(
				net.imagej.ops.arithmetic.add.AddConstantToImageInPlace.class, image,
				value);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.add.AddConstantToNumericType.class)
	public <T extends NumericType<T>> T add(final T in, final T value) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops()
				.run(net.imagej.ops.arithmetic.add.AddConstantToNumericType.class, in,
					value);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.add.AddConstantToNumericType.class)
	public <T extends NumericType<T>> T
		add(final T out, final T in, final T value)
	{
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(
				net.imagej.ops.arithmetic.add.AddConstantToNumericType.class, out, in,
				value);
		return result;
	}

	@OpMethod(
		op = net.imagej.ops.arithmetic.add.AddConstantToImageFunctional.class)
	public <T extends NumericType<T>> RandomAccessibleInterval<T> add(
		final RandomAccessibleInterval<T> out, final IterableInterval<T> in,
		final T value)
	{
		@SuppressWarnings("unchecked")
		final RandomAccessibleInterval<T> result =
			(RandomAccessibleInterval<T>) ops().run(
				net.imagej.ops.arithmetic.add.AddConstantToImageFunctional.class, out,
				in, value);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.AddNoise.class)
	public Object addNoise(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.AddNoise.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealAddNoise.class)
	public <I extends RealType<I>, O extends RealType<O>> O addNoise(final O out,
		final I in, final double rangeMin, final double rangeMax,
		final double rangeStdDev, final Random rng)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealAddNoise.class, out, in,
				rangeMin, rangeMax, rangeStdDev, rng);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.And.class)
	public Object and(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.And.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerAnd.class)
	public int and(final int a, final int b) {
		final int result =
			(Integer) ops().run(net.imagej.ops.math.PrimitiveMath.IntegerAnd.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongAnd.class)
	public long and(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongAnd.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealAndConstant.class)
	public <I extends RealType<I>, O extends RealType<O>> O and(final O out,
		final I in, final long constant)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealAndConstant.class, out,
				in, constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arccos.class)
	public Object arccos(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arccos.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleArccos.class)
	public double arccos(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleArccos.class,
				a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArccos.class)
	public <I extends RealType<I>, O extends RealType<O>> O arccos(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArccos.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arccosh.class)
	public Object arccosh(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arccosh.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArccosh.class)
	public <I extends RealType<I>, O extends RealType<O>> O arccosh(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArccosh.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arccot.class)
	public Object arccot(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arccot.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArccot.class)
	public <I extends RealType<I>, O extends RealType<O>> O arccot(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArccot.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arccoth.class)
	public Object arccoth(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arccoth.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArccoth.class)
	public <I extends RealType<I>, O extends RealType<O>> O arccoth(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArccoth.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arccsc.class)
	public Object arccsc(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arccsc.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArccsc.class)
	public <I extends RealType<I>, O extends RealType<O>> O arccsc(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArccsc.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arccsch.class)
	public Object arccsch(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arccsch.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArccsch.class)
	public <I extends RealType<I>, O extends RealType<O>> O arccsch(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArccsch.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arcsec.class)
	public Object arcsec(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arcsec.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArcsec.class)
	public <I extends RealType<I>, O extends RealType<O>> O arcsec(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArcsec.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arcsech.class)
	public Object arcsech(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arcsech.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArcsech.class)
	public <I extends RealType<I>, O extends RealType<O>> O arcsech(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArcsech.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arcsin.class)
	public Object arcsin(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arcsin.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleArcsin.class)
	public double arcsin(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleArcsin.class,
				a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArcsin.class)
	public <I extends RealType<I>, O extends RealType<O>> O arcsin(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArcsin.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arcsinh.class)
	public Object arcsinh(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arcsinh.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArcsinh.class)
	public <I extends RealType<I>, O extends RealType<O>> O arcsinh(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArcsinh.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arctan.class)
	public Object arctan(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arctan.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleArctan.class)
	public double arctan(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleArctan.class,
				a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArctan.class)
	public <I extends RealType<I>, O extends RealType<O>> O arctan(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArctan.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Arctanh.class)
	public Object arctanh(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Arctanh.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealArctanh.class)
	public <I extends RealType<I>, O extends RealType<O>> O arctanh(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealArctanh.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Ceil.class)
	public Object ceil(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Ceil.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleCeil.class)
	public double ceil(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleCeil.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealCeil.class)
	public <I extends RealType<I>, O extends RealType<O>> O ceil(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealCeil.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Complement.class)
	public Object complement(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Complement.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerComplement.class)
	public int complement(final int a) {
		final int result =
			(Integer) ops().run(
				net.imagej.ops.math.PrimitiveMath.IntegerComplement.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongComplement.class)
	public long complement(final long a) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongComplement.class,
				a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Copy.class)
	public Object copy(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Copy.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealCopy.class)
	public <I extends RealType<I>, O extends RealType<O>> O copy(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealCopy.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Cos.class)
	public Object cos(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Cos.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleCos.class)
	public double cos(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleCos.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealCos.class)
	public <I extends RealType<I>, O extends RealType<O>> O cos(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealCos.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Cosh.class)
	public Object cosh(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Cosh.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleCosh.class)
	public double cosh(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleCosh.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealCosh.class)
	public <I extends RealType<I>, O extends RealType<O>> O cosh(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealCosh.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Cot.class)
	public Object cot(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Cot.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealCot.class)
	public <I extends RealType<I>, O extends RealType<O>> O cot(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealCot.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Coth.class)
	public Object coth(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Coth.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealCoth.class)
	public <I extends RealType<I>, O extends RealType<O>> O coth(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealCoth.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Csc.class)
	public Object csc(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Csc.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealCsc.class)
	public <I extends RealType<I>, O extends RealType<O>> O csc(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealCsc.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Csch.class)
	public Object csch(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Csch.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealCsch.class)
	public <I extends RealType<I>, O extends RealType<O>> O csch(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealCsch.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.CubeRoot.class)
	public Object cubeRoot(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.CubeRoot.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleCubeRoot.class)
	public double cubeRoot(final double a) {
		final double result =
			(Double) ops().run(
				net.imagej.ops.math.PrimitiveMath.DoubleCubeRoot.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealCubeRoot.class)
	public <I extends RealType<I>, O extends RealType<O>> O cubeRoot(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealCubeRoot.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Divide.class)
	public Object divide(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Divide.class, args);
	}

	@OpMethod(op = net.imagej.ops.onthefly.ArithmeticOp.DivideOp.class)
	public Object divide(final Object result, final Object a, final Object b) {
		final Object result_op =
			ops().run(net.imagej.ops.onthefly.ArithmeticOp.DivideOp.class, result, a,
				b);
		return result_op;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerDivide.class)
	public int divide(final int a, final int b) {
		final int result =
			(Integer) ops().run(
				net.imagej.ops.math.PrimitiveMath.IntegerDivide.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongDivide.class)
	public long divide(final long a, final long b) {
		final long result =
			(Long) ops()
				.run(net.imagej.ops.math.PrimitiveMath.LongDivide.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatDivide.class)
	public float divide(final float a, final float b) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatDivide.class, a,
				b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleDivide.class)
	public double divide(final double a, final double b) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleDivide.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealDivide.class)
	public <I extends RealType<I>, O extends RealType<O>> O divide(final O out,
		final I in, final double constant, final double dbzVal)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealDivide.class, out, in,
				constant, dbzVal);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.RealBinaryMath.Divide.class)
	public <T extends RealType<T>> T divide(final T in, final T b) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(net.imagej.ops.math.RealBinaryMath.Divide.class, in, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.RealBinaryMath.Divide.class)
	public <T extends RealType<T>> T divide(final T out, final T in, final T b) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(net.imagej.ops.math.RealBinaryMath.Divide.class, out, in, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Exp.class)
	public Object exp(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Exp.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleExp.class)
	public double exp(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleExp.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealExp.class)
	public <I extends RealType<I>, O extends RealType<O>> O exp(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealExp.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.ExpMinusOne.class)
	public Object expMinusOne(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.ExpMinusOne.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealExpMinusOne.class)
	public <I extends RealType<I>, O extends RealType<O>> O expMinusOne(
		final O out, final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealExpMinusOne.class, out,
				in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Floor.class)
	public Object floor(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Floor.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleFloor.class)
	public double floor(final double a) {
		final double result =
			(Double) ops()
				.run(net.imagej.ops.math.PrimitiveMath.DoubleFloor.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealFloor.class)
	public <I extends RealType<I>, O extends RealType<O>> O floor(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealFloor.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Gamma.class)
	public Object gamma(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Gamma.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealGammaConstant.class)
	public <I extends RealType<I>, O extends RealType<O>> O gamma(final O out,
		final I in, final double constant)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealGammaConstant.class,
				out, in, constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Invert.class)
	public Object invert(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Invert.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealInvert.class)
	public <I extends RealType<I>, O extends RealType<O>> O invert(final O out,
		final I in, final double specifiedMin, final double specifiedMax)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealInvert.class, out, in,
				specifiedMin, specifiedMax);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.LeftShift.class)
	public Object leftShift(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.LeftShift.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerLeftShift.class)
	public int leftShift(final int a, final int b) {
		final int result =
			(Integer) ops().run(
				net.imagej.ops.math.PrimitiveMath.IntegerLeftShift.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongLeftShift.class)
	public long leftShift(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongLeftShift.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Log.class)
	public Object log(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Log.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleLog.class)
	public double log(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleLog.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealLog.class)
	public <I extends RealType<I>, O extends RealType<O>> O log(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealLog.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Log2.class)
	public Object log2(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Log2.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealLog2.class)
	public <I extends RealType<I>, O extends RealType<O>> O log2(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealLog2.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Log10.class)
	public Object log10(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Log10.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleLog10.class)
	public double log10(final double a) {
		final double result =
			(Double) ops()
				.run(net.imagej.ops.math.PrimitiveMath.DoubleLog10.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealLog10.class)
	public <I extends RealType<I>, O extends RealType<O>> O log10(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealLog10.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.LogOnePlusX.class)
	public Object logOnePlusX(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.LogOnePlusX.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleLogOnePlusX.class)
	public double logOnePlusX(final double a) {
		final double result =
			(Double) ops().run(
				net.imagej.ops.math.PrimitiveMath.DoubleLogOnePlusX.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealLogOnePlusX.class)
	public <I extends RealType<I>, O extends RealType<O>> O logOnePlusX(
		final O out, final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealLogOnePlusX.class, out,
				in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Max.class)
	public Object max(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Max.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerMax.class)
	public int max(final int a, final int b) {
		final int result =
			(Integer) ops().run(net.imagej.ops.math.PrimitiveMath.IntegerMax.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongMax.class)
	public long max(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongMax.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatMax.class)
	public float max(final float a, final float b) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatMax.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleMax.class)
	public double max(final double a, final double b) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleMax.class, a,
				b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealMaxConstant.class)
	public <I extends RealType<I>, O extends RealType<O>> O max(final O out,
		final I in, final double constant)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealMaxConstant.class, out,
				in, constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Min.class)
	public Object min(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Min.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerMin.class)
	public int min(final int a, final int b) {
		final int result =
			(Integer) ops().run(net.imagej.ops.math.PrimitiveMath.IntegerMin.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongMin.class)
	public long min(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongMin.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatMin.class)
	public float min(final float a, final float b) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatMin.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleMin.class)
	public double min(final double a, final double b) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleMin.class, a,
				b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealMinConstant.class)
	public <I extends RealType<I>, O extends RealType<O>> O min(final O out,
		final I in, final double constant)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealMinConstant.class, out,
				in, constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Multiply.class)
	public Object multiply(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Multiply.class, args);
	}

	@OpMethod(op = net.imagej.ops.onthefly.ArithmeticOp.MultiplyOp.class)
	public Object multiply(final Object result, final Object a, final Object b) {
		final Object result_op =
			ops().run(net.imagej.ops.onthefly.ArithmeticOp.MultiplyOp.class, result,
				a, b);
		return result_op;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerMultiply.class)
	public int multiply(final int a, final int b) {
		final int result =
			(Integer) ops().run(
				net.imagej.ops.math.PrimitiveMath.IntegerMultiply.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongMultiply.class)
	public long multiply(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongMultiply.class, a,
				b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatMultiply.class)
	public float multiply(final float a, final float b) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatMultiply.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleMultiply.class)
	public double multiply(final double a, final double b) {
		final double result =
			(Double) ops().run(
				net.imagej.ops.math.PrimitiveMath.DoubleMultiply.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealMultiply.class)
	public <I extends RealType<I>, O extends RealType<O>> O multiply(final O out,
		final I in, final double constant)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealMultiply.class, out, in,
				constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.RealBinaryMath.Multiply.class)
	public <T extends RealType<T>> T multiply(final T in, final T b) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(net.imagej.ops.math.RealBinaryMath.Multiply.class, in, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.RealBinaryMath.Multiply.class)
	public <T extends RealType<T>> T multiply(final T out, final T in, final T b) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(net.imagej.ops.math.RealBinaryMath.Multiply.class, out, in, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.NearestInt.class)
	public Object nearestInt(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.NearestInt.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealNearestInt.class)
	public <I extends RealType<I>, O extends RealType<O>> O nearestInt(
		final O out, final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealNearestInt.class, out,
				in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Negate.class)
	public Object negate(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Negate.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerNegate.class)
	public int negate(final int a) {
		final int result =
			(Integer) ops().run(
				net.imagej.ops.math.PrimitiveMath.IntegerNegate.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongNegate.class)
	public long negate(final long a) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongNegate.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatNegate.class)
	public float negate(final float a) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatNegate.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleNegate.class)
	public double negate(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleNegate.class,
				a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealNegate.class)
	public <I extends RealType<I>, O extends RealType<O>> O negate(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealNegate.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Or.class)
	public Object or(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Or.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerOr.class)
	public int or(final int a, final int b) {
		final int result =
			(Integer) ops().run(net.imagej.ops.math.PrimitiveMath.IntegerOr.class, a,
				b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongOr.class)
	public long or(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongOr.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealOrConstant.class)
	public <I extends RealType<I>, O extends RealType<O>> O or(final O out,
		final I in, final long constant)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealOrConstant.class, out,
				in, constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Power.class)
	public Object power(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Power.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoublePower.class)
	public double power(final double a, final double b) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoublePower.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealPowerConstant.class)
	public <I extends RealType<I>, O extends RealType<O>> O power(final O out,
		final I in, final double constant)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealPowerConstant.class,
				out, in, constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.RandomGaussian.class)
	public Object randomGaussian(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.RandomGaussian.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealRandomGaussian.class)
	public <I extends RealType<I>, O extends RealType<O>> O randomGaussian(
		final O out, final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealRandomGaussian.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealRandomGaussian.class)
	public <I extends RealType<I>, O extends RealType<O>> O randomGaussian(
		final O out, final I in, final long seed)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealRandomGaussian.class,
				out, in, seed);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.RandomUniform.class)
	public Object randomUniform(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.RandomUniform.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealRandomUniform.class)
	public <I extends RealType<I>, O extends RealType<O>> O randomUniform(
		final O out, final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealRandomUniform.class,
				out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealRandomUniform.class)
	public <I extends RealType<I>, O extends RealType<O>> O randomUniform(
		final O out, final I in, final long seed)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealRandomUniform.class,
				out, in, seed);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Reciprocal.class)
	public Object reciprocal(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Reciprocal.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealReciprocal.class)
	public <I extends RealType<I>, O extends RealType<O>> O reciprocal(
		final O out, final I in, final double dbzVal)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealReciprocal.class, out,
				in, dbzVal);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Remainder.class)
	public Object remainder(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Remainder.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerRemainder.class)
	public int remainder(final int a, final int b) {
		final int result =
			(Integer) ops().run(
				net.imagej.ops.math.PrimitiveMath.IntegerRemainder.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongRemainder.class)
	public long remainder(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongRemainder.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatRemainder.class)
	public float remainder(final float a, final float b) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatRemainder.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleRemainder.class)
	public double remainder(final double a, final double b) {
		final double result =
			(Double) ops().run(
				net.imagej.ops.math.PrimitiveMath.DoubleRemainder.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.RightShift.class)
	public Object rightShift(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.RightShift.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerRightShift.class)
	public int rightShift(final int a, final int b) {
		final int result =
			(Integer) ops().run(
				net.imagej.ops.math.PrimitiveMath.IntegerRightShift.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongRightShift.class)
	public long rightShift(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongRightShift.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Round.class)
	public Object round(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Round.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatRound.class)
	public float round(final float a) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatRound.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleRound.class)
	public double round(final double a) {
		final double result =
			(Double) ops()
				.run(net.imagej.ops.math.PrimitiveMath.DoubleRound.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealRound.class)
	public <I extends RealType<I>, O extends RealType<O>> O round(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealRound.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Sec.class)
	public Object sec(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Sec.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSec.class)
	public <I extends RealType<I>, O extends RealType<O>> O sec(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSec.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Sech.class)
	public Object sech(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Sech.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSech.class)
	public <I extends RealType<I>, O extends RealType<O>> O sech(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSech.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Signum.class)
	public Object signum(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Signum.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatSignum.class)
	public float signum(final float a) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatSignum.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleSignum.class)
	public double signum(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleSignum.class,
				a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSignum.class)
	public <I extends RealType<I>, O extends RealType<O>> O signum(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSignum.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Sin.class)
	public Object sin(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Sin.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleSin.class)
	public double sin(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleSin.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSin.class)
	public <I extends RealType<I>, O extends RealType<O>> O sin(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSin.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Sinc.class)
	public Object sinc(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Sinc.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSinc.class)
	public <I extends RealType<I>, O extends RealType<O>> O sinc(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSinc.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.SincPi.class)
	public Object sincPi(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.SincPi.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSincPi.class)
	public <I extends RealType<I>, O extends RealType<O>> O sincPi(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSincPi.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Sinh.class)
	public Object sinh(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Sinh.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleSinh.class)
	public double sinh(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleSinh.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSinh.class)
	public <I extends RealType<I>, O extends RealType<O>> O sinh(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSinh.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Sqr.class)
	public Object sqr(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Sqr.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSqr.class)
	public <I extends RealType<I>, O extends RealType<O>> O sqr(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSqr.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Sqrt.class)
	public Object sqrt(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Sqrt.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleSqrt.class)
	public double sqrt(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleSqrt.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSqrt.class)
	public <I extends RealType<I>, O extends RealType<O>> O sqrt(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSqrt.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Step.class)
	public Object step(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Step.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealStep.class)
	public <I extends RealType<I>, O extends RealType<O>> O step(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealStep.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Subtract.class)
	public Object subtract(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Subtract.class, args);
	}

	@OpMethod(op = net.imagej.ops.onthefly.ArithmeticOp.SubtractOp.class)
	public Object subtract(final Object result, final Object a, final Object b) {
		final Object result_op =
			ops().run(net.imagej.ops.onthefly.ArithmeticOp.SubtractOp.class, result,
				a, b);
		return result_op;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerSubtract.class)
	public int subtract(final int a, final int b) {
		final int result =
			(Integer) ops().run(
				net.imagej.ops.math.PrimitiveMath.IntegerSubtract.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongSubtract.class)
	public long subtract(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongSubtract.class, a,
				b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.FloatSubtract.class)
	public float subtract(final float a, final float b) {
		final float result =
			(Float) ops().run(net.imagej.ops.math.PrimitiveMath.FloatSubtract.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleSubtract.class)
	public double subtract(final double a, final double b) {
		final double result =
			(Double) ops().run(
				net.imagej.ops.math.PrimitiveMath.DoubleSubtract.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealSubtract.class)
	public <I extends RealType<I>, O extends RealType<O>> O subtract(final O out,
		final I in, final double constant)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealSubtract.class, out, in,
				constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.RealBinaryMath.Subtract.class)
	public <T extends RealType<T>> T subtract(final T in, final T b) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(net.imagej.ops.math.RealBinaryMath.Subtract.class, in, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.RealBinaryMath.Subtract.class)
	public <T extends RealType<T>> T subtract(final T out, final T in, final T b) {
		@SuppressWarnings("unchecked")
		final T result =
			(T) ops().run(net.imagej.ops.math.RealBinaryMath.Subtract.class, out, in, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Tan.class)
	public Object tan(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Tan.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleTan.class)
	public double tan(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleTan.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealTan.class)
	public <I extends RealType<I>, O extends RealType<O>> O tan(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealTan.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Tanh.class)
	public Object tanh(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Tanh.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.DoubleTanh.class)
	public double tanh(final double a) {
		final double result =
			(Double) ops().run(net.imagej.ops.math.PrimitiveMath.DoubleTanh.class, a);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealTanh.class)
	public <I extends RealType<I>, O extends RealType<O>> O tanh(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealTanh.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Ulp.class)
	public Object ulp(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Ulp.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealUlp.class)
	public <I extends RealType<I>, O extends RealType<O>> O ulp(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealUlp.class, out, in);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.UnsignedRightShift.class)
	public Object unsignedRightShift(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.UnsignedRightShift.class, args);
	}

	@OpMethod(
		op = net.imagej.ops.math.PrimitiveMath.IntegerUnsignedRightShift.class)
	public int unsignedRightShift(final int a, final int b) {
		final int result =
			(Integer) ops()
				.run(net.imagej.ops.math.PrimitiveMath.IntegerUnsignedRightShift.class,
					a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongUnsignedRightShift.class)
	public
		long unsignedRightShift(final long a, final long b) {
		final long result =
			(Long) ops().run(
				net.imagej.ops.math.PrimitiveMath.LongUnsignedRightShift.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Xor.class)
	public Object xor(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Xor.class, args);
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.IntegerXor.class)
	public int xor(final int a, final int b) {
		final int result =
			(Integer) ops().run(net.imagej.ops.math.PrimitiveMath.IntegerXor.class,
				a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.math.PrimitiveMath.LongXor.class)
	public long xor(final long a, final long b) {
		final long result =
			(Long) ops().run(net.imagej.ops.math.PrimitiveMath.LongXor.class, a, b);
		return result;
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealXorConstant.class)
	public <I extends RealType<I>, O extends RealType<O>> O xor(final O out,
		final I in, final long constant)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealXorConstant.class, out,
				in, constant);
		return result;
	}

	@OpMethod(op = net.imagej.ops.MathOps.Zero.class)
	public Object zero(final Object... args) {
		return ops().run(net.imagej.ops.MathOps.Zero.class, args);
	}

	@OpMethod(op = net.imagej.ops.arithmetic.real.RealZero.class)
	public <I extends RealType<I>, O extends RealType<O>> O zero(final O out,
		final I in)
	{
		@SuppressWarnings("unchecked")
		final O result =
			(O) ops().run(net.imagej.ops.arithmetic.real.RealZero.class, out, in);
		return result;
	}

	// -- Named methods --

	@Override
	public String getName() {
		return "math";
	}

}