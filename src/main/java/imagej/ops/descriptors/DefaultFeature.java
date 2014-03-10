
package imagej.ops.descriptors;

import imagej.ops.Function;

import org.scijava.util.DoubleArray;

public class DefaultFeature<A, B extends DoubleArray> extends
	DefaultDescriptor<A, B> implements Function<A, B>
{

	/**
	 * Get the value of the feature
	 * @return
	 */
	public Double get() {
		return compute(getInput(), getOutput()).get(0);
	}

}
