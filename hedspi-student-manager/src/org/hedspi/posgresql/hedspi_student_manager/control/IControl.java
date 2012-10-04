package org.hedspi.posgresql.hedspi_student_manager.control;

import java.util.logging.Logger;

import org.hedspi.posgresql.hedspi_student_manager.view.IView;

public interface IControl {
	void fire(String command, Object ... data);
	void fireByView(IView view, String command, Object ... data);
	Logger getLogger();
}
