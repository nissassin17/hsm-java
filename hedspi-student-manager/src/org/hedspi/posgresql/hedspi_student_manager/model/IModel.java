package org.hedspi.posgresql.hedspi_student_manager.model;

public interface IModel {
	Object getData(String command, Object... data);

	boolean setData(String command, Object... data);
}
