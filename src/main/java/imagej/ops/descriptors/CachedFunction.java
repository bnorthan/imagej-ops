
package imagej.ops.descriptors;

import imagej.ops.Function;

public interface CachedFunction<A, B> extends Function<A, B> {

	Function<A, B> getFunction();

}
