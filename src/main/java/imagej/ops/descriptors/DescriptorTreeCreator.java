/*
 * #%L
 * SciJava OPS: a framework for reusable algorithms.
 * %%
 * Copyright (C) 2013 Board of Regents of the University of
 * Wisconsin-Madison, and University of Konstanz.
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
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of any organization.
 * #L%
 */

package imagej.ops.descriptors;

import imagej.ops.Op;
import imagej.ops.OpService;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.scijava.Context;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.PluginInfo;

/**
 * @author graumanna
 */
public class DescriptorTreeCreator {

	// Op service is nice
	private final OpService opService;

	// ALL operations (including hidden + output)
	private final List<Op> allOps = new ArrayList<Op>();

	// Ops of which the result will be available for the user
	private final List<Descriptor> outputOps = new ArrayList<Descriptor>();

	// Map to store the bidrectional relation between ops and listeners

	// Lazy descriptor classes
	private ArrayList<Class<? extends Descriptor>> m_lazyDescriptorClasses =
		new ArrayList<Class<? extends Descriptor>>();

	/**
	 * Constructor
	 */
	public DescriptorTreeCreator() {
		final Context context = new Context(OpService.class);
		opService = context.getService(OpService.class);
	}

	/**
	 * create the descriptor tree
	 */
	public void createTree() {
		// init lazy descriptor sets
		for (final Class<? extends Descriptor> clazz : m_lazyDescriptorClasses) {
			final Descriptor instance = (Descriptor) getInstance(clazz);
			outputOps.add(instance);
		}
	}

	/**
	 * get an instance of a descriptor
	 * 
	 * @param _descriptorClazz
	 * @return
	 */
	private Op getInstance(final Class<? extends Op> _descriptorClazz) {
		Op instance = null;

		for (final Op op : allOps) {
			if (_descriptorClazz.isAssignableFrom(op.getClass())) {
				// TODO Consider whether we really need to do this.
				final double priority = opService.info(op).getPriority();

				if ((instance != null && priority > opService.info(instance).getPriority()) ||
					instance == null) instance = op;
			}
		}

		if (instance == null) {
			@SuppressWarnings("unchecked")
			final PluginInfo<? extends Op> info =
				(PluginInfo<? extends Op>) opService.getPluginService().getPlugin(
					_descriptorClazz.getName());

			// TODO lazy set parameters? here we just need the OP without parameters
			instance = opService.op(_descriptorClazz);

			// we have to have fun with this op now
			addOp(instance);
			parse(instance);

			for (final Op op : allOps) {
				// TODO: Curtis Interface the same and inputs the same (maybe we can add
				// this to ops serivce?)
				if (op.isEquivalentOp(instance)) {
					updateListeners(instance, op);
				}
			}
		}

		return instance;
	}

	/**
	 * adds an instance check first if equivalent op already exists
	 * 
	 * @param _instance
	 */
	private void addOp(final Op _instance) {
		allOps.add(_instance);
	}

	/**
	 * parse op for dependencies
	 * 
	 * @param _parent
	 */
	private void parse(final Op _parent) {
		// visit all fields of parent op
		for (final Field annotatedField : _parent.getClass().getDeclaredFields()) {
			// is there a @Parameter annotation?
			if (annotatedField.isAnnotationPresent(Parameter.class)) {
				final Class<?> annotatedType = annotatedField.getType();

				// Parameter is another op
				if (Op.class.isAssignableFrom(annotatedType)) {
					// getInstance also parses the instance!
					@SuppressWarnings("unchecked")
					final Op op = getInstance((Class<? extends Op>) annotatedType);

					inject(_parent, annotatedField, op);
				}
				// Parameter is a source
				else {
					// Do we have a compatible op?
					Op requiredOp = null;

					// is there a provider for this source?
					for (final Op op : allOps) {
						// TODO Curtis: Ops Service? Checks a op has an output which is
						// compatible to annotatedType
						if (op.hasCompatibleOutput(annotatedType)) {
							if ((requiredOp != null && requiredOp.getPriority() < op
								.getPriority()) ||
								requiredOp == null)
							{
								requiredOp = op;
							}

							inject(_parent, annotatedField, requiredOp);
						}
					}

					// no provider, do we have a compatible op for it?
					if (requiredOp == null) {
						@SuppressWarnings("unchecked")
						// TODO: Is there anywhere an op which can do the job for me?
						final Op op = opService.op(Op.class, annotatedType);
						if (op == null) {
							throw new IllegalStateException("can not find an appropriate op");
						}
						else {
							addOp(op);
							parse(op);
							inject(_parent, annotatedField, op);
						}
					}
				}
			}
		}
	}

	/**
	 * Register the op to the op it is listen to
	 * 
	 * @param _parent
	 * @param op
	 */
	private void registerPredecessor(final Op _parent, final Op op) {
		_parent.registerPredecessor(op);
	}

	/**
	 * @param _new
	 * @param _old
	 */
	private void updateListeners(final Op _new, final Op _old) {
		// TODO how can we make this happen... well we wrap each and every OP in a
		// CachedOp
		if (_old.getPriority() < _new.getPriority()) {
			// register all listerners of op to instance
			for (final Op successor : _old.getSuccessors()) {
				_new.registerSuccessor(successor);
				successor.unregisterPredecessor(_old);
				successor.registerPredecessor(_new);
			}

			final List<Op> predecessors = _old.getPredecessors();
			for (final Op pre : predecessors) {
				pre.unregisterSuccessor(_old);
				pre.registerSuccessor(_new);
				_new.registerPredecessor(pre);
			}

			allOps.remove(_old);
		}
	}

	/**
	 * Register a descriptor set
	 * 
	 * @param _set
	 */
	public void registerDescriptorSet(final DescriptorSet _set) {
		// Write all output ops in a list
		for (final Class<? extends Descriptor> descriptor : _set.descriptors()) {
			m_lazyDescriptorClasses.add(descriptor);
		}
	}

	public void registerDescriptor(final Class<? extends Descriptor> descriptor) {
		m_lazyDescriptorClasses.add(descriptor);
	}

	public void registerProviderOp(final Op _op) {
		addOp(_op);
	}

	/**
	 * Retrieve the iterator over numeric features
	 */
	public Iterator<Descriptor> iterator() {
		return outputOps.iterator();
	}

	@SuppressWarnings("unchecked")
	private void inject(final Op _mod, final Field _f, final Op _injectedOp) {

		if (!Op.class.isAssignableFrom(_f.getType())) {
			// here we have a native type
			final LazyInjector lazyInjector =
				new LazyInjector((Op) _injectedOp, _mod, _f);
			_injectedOp.registerSuccessor(lazyInjector);
		}
		else {
			AccessibleObject.setAccessible(new AccessibleObject[] { _f }, true);
			try {
				_f.set(_mod, _injectedOp);
			}
			catch (final IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch (final IllegalAccessException e) {
				e.printStackTrace();
			}
			AccessibleObject.setAccessible(new AccessibleObject[] { _f }, false);
			registerPredecessor(_mod, _injectedOp);
			_injectedOp.registerSuccessor(_mod);
		}
	}
}
