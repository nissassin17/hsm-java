package org.hedspi.posgresql.hedspi_student_manager.view.classview;

import javax.swing.JScrollPane;

import org.hedspi.posgresql.hedspi_student_manager.model.academic.HedspiClass;
import org.hedspi.posgresql.hedspi_student_manager.view.util.list.ObjectListPanel;

public class ClassPanel extends javax.swing.JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ClassPanel() {

		JScrollPane scrollPane = new JScrollPane();
		setLeftComponent(scrollPane);

		ClassViewPanel panel_1 = new ClassViewPanel();
		ObjectListPanel<HedspiClass> panel = new ObjectListPanel<HedspiClass>(
				panel_1, HedspiClass.getClasses(), HedspiClass.getClassGenner());
		scrollPane.setViewportView(panel);

		JScrollPane scrollPane_1 = new JScrollPane();
		setRightComponent(scrollPane_1);

		scrollPane_1.setViewportView(panel_1);

	}

}
