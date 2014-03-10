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

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

class LazyInjector extends AbstractOp<Object> {

	private final Field f;

	private final Op<?> mod;

	private Op<Object> parent;

	public LazyInjector(Op<Object> _parent, Op<?> _mod, final Field _f) {
		mod = _mod;
		f = _f;
		parent = _parent;
	}

	@Override
	public void update() {
		// special case for cached ops
		if (parent instanceof CachedOp) {
			((CachedOp<Object>) parent).checkAndMarkDirty();
		}

		AccessibleObject.setAccessible(new AccessibleObject[] { f }, true);
		try {
			f.set(mod, parent.get());
		}
		catch (final IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		AccessibleObject.setAccessible(new AccessibleObject[] { f }, false);

		mod.update();
	}

	@Override
	public Object get() {
		throw new UnsupportedOperationException(
			"there is no get defined for a lazy injector");
	}

	@Override
	public boolean isEquivalentOp(Op<?> op) {
		return false;
	}

	@Override
	public boolean hasCompatibleOutput(final Class<?> clazz) {
		return false;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
