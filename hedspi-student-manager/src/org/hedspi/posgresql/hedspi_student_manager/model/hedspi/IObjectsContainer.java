package org.hedspi.posgresql.hedspi_student_manager.model.hedspi;

import java.util.Collection;

public interface IObjectsContainer<T extends Object> {
	
	void addObjects(Collection<T> arr);
	
	void addObject(T obj);

	void removeAll();

	void removeObject(T obj);
}
