package org.hedspi.posgresql.hedspi_student_manager.model;

public interface IModel {
	void setData(String command, Object ... data);
	Object getData(String command, Object ... data);
}
