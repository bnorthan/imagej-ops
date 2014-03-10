
package imagej.ops.descriptors;

import java.util.ArrayList;
import java.util.List;

import org.scijava.util.DoubleArray;

public class DefaultDescriptor<A, B extends DoubleArray> extends
	DefaultCachedFunction<A, B> implements Descriptor<A, B>
{

	private final List<Descriptor<A, B>> successors =
		new ArrayList<Descriptor<A, B>>();

	private final List<Descriptor<A, B>> predecessors =
		new ArrayList<Descriptor<A, B>>();

	@Override
	public void update() {
		notifySuccessor();
	}

	@Override
	public void notifySuccessor() {
		for (final Descriptor<A, B> successor : successors) {
			successor.update();
		}
	}

	@Override
	public void registerSuccessor(final Descriptor<A, B> successor) {
		successors.add(successor);
	}

	@Override
	public void unregisterSuccessor(final Descriptor<A, B> successor) {
		successors.remove(successor);
	}

	@Override
	public void registerPredecessor(final Descriptor<A, B> pre) {
		predecessors.add(pre);
	}

	@Override
	public void unregisterPredecessor(final Descriptor<A, B> pre) {
		predecessors.remove(pre);
	}

	@Override
	public List<Descriptor<A, B>> getSuccessors() {
		return successors;
	}

	@Override
	public List<Descriptor<A, B>> getPredecessors() {
		return predecessors;
	}
}
