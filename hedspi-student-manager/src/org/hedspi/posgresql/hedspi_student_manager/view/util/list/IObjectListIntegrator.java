package org.hedspi.posgresql.hedspi_student_manager.view.util.list;

public interface IObjectListIntegrator<T> {
	T getNewObject();

	boolean isRemovable(T object);

	void beforeRemove(T object);
}
