package org.hsm.view.util.object_associated;

public interface IObjectUpdater<T1, T2> {
	T2 getValue(T1 object);

	void setValue(T1 object, T2 value);
}
