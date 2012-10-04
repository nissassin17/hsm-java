package org.hedspi.posgresql.hedspi_student_manager.model.hedspi;

public interface IObjectsContainer<T extends Object> {
	void removeObject(T obj);

	void addObject(T obj);

	void removeAll();
}
