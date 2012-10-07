package org.hedspi.posgresql.hedspi_student_manager.view.student;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.hedspi.posgresql.hedspi_student_manager.model.contact.Student;
import org.hedspi.posgresql.hedspi_student_manager.view.util.list.ObjectListPanel;

public class StudentPanel extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public StudentPanel() {

		StudentViewPane panel_1 = new StudentViewPane();

		JScrollPane scrollPane = new JScrollPane();
		setLeftComponent(scrollPane);

		ObjectListPanel<Student> panel = new ObjectListPanel<Student>(panel_1,
				Student.getStudents(),
				Student.getStudentGenner());
		scrollPane.setViewportView(panel);

		JScrollPane scrollPane_1 = new JScrollPane();
		setRightComponent(scrollPane_1);

		scrollPane_1.setViewportView(panel_1);

	}

}
