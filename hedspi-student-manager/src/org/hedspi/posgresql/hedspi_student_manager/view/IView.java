package org.hedspi.posgresql.hedspi_student_manager.view;

public interface IView {
	void fire(String command, Object ... data);
}
