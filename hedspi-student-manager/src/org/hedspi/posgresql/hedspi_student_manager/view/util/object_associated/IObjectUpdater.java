package org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated;

public interface IObjectUpdater<T1, T2> {
	void setValue(T1 object, T2 value);
	T2 getValue(T1 object);
}
