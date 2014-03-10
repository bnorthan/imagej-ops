
package imagej.ops.descriptors;

import imagej.ops.AbstractFunction;
import imagej.ops.Function;

import java.lang.ref.WeakReference;

import org.scijava.plugin.Parameter;
import org.scijava.util.DoubleArray;

public class DefaultCachedFunction<A, B extends DoubleArray> extends
	AbstractFunction<A, B> implements CachedFunction<A, B>
{

	@Parameter
	private Function<A, B> func;

	private long hash;

	private WeakReference<B> cachedOutput = new WeakReference<B>(null);

	@Override
	public B compute(final A input, final B output) {
		final B b = cachedOutput.get();
		if (b == null || hash != input.hashCode()) {
			final B res = func.compute(input, output);
			cachedOutput = new WeakReference<B>(res);
			return res;
		}

		output.setArray(b.getArray());
		return output;
	}

	public void clearOutput() {
		// TODO: Check if this sets everything to null
		cachedOutput.clear();
	}

	@Override
	public boolean equals(final Object obj) {
		return obj.hashCode() == hashCode();
	}

	public boolean isOutputSet() {
		return cachedOutput.get() == null;
	}

	@Override
	public Function<A, B> getFunction() {
		return func;
	}
}
