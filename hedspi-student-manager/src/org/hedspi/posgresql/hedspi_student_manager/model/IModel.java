package org.hedspi.posgresql.hedspi_student_manager.model;

public interface IModel {
	Object getData(String command, Object... data);

	void setData(String command, Object... data);
}
