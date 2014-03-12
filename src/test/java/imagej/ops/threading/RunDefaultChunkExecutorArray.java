
package imagej.ops.threading;

import imagej.ops.AbstractFunction;
import imagej.ops.Op;
import imagej.ops.OpService;
import imagej.ops.Parallel;

import org.scijava.Priority;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type = Op.class, name = "doNothing", priority = Priority.LOW_PRIORITY)
public class RunDefaultChunkExecutorArray<A> extends AbstractFunction<A[], A[]>
	implements Parallel
{

	@Parameter
	private OpService opService;

	@Override
	public A[] compute(final A[] input, final A[] output) {

		opService.run(DefaultChunkExecutor.class, new ChunkExecutable() {

			@Override
			public void
				execute(final int startIndex, final int stepSize, final int numSteps)
			{
				int i = startIndex;

				int ctr = 0;
				while (ctr < numSteps) {
					output[i] = input[i];
					i += stepSize;
					ctr++;
				}
			}
		}, input.length);

		return output;

	}
}
