
package imagej.ops.descriptors;

import java.util.List;

public interface Descriptor<A, B> extends CachedFunction<A, B> {

	/** Update Descriptor<A, B> */
	void update();

	/**
	 * Notify mark as dirty
	 */
	void notifySuccessor();

	/**
	 * Register for listening if Descriptor<A, B> is marked as dirty
	 * 
	 * @param successor
	 */
	void registerSuccessor(Descriptor<A, B> successor);

	/**
	 * Deregister listener
	 * 
	 * @param listener
	 */
	void unregisterSuccessor(Descriptor<A, B> listener);

	/**
	 * 
	 */
	void registerPredecessor(Descriptor<A, B> pre);

	/**
	 * 
	 */
	void unregisterPredecessor(Descriptor<A, B> suc);

	/**
	 * @return
	 */
	List<Descriptor<A, B>> getSuccessors();

	/**
	 * @return
	 */
	List<Descriptor<A, B>> getPredecessors();
}
