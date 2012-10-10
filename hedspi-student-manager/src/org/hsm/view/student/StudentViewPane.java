package org.hsm.view.student;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.hsm.model.contact.Student;
import org.hsm.view.contact.ContactPane;
import org.hsm.view.util.list.IObjectViewPanel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class StudentViewPane extends JPanel implements
		IObjectViewPanel<Student> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentOtherInfoPanel studentOtherInfoPanel;
	private ContactPane panel;

	/**
	 * Create the panel.
	 */
	public StudentViewPane() {
		setAutoscrolls(true);
		setPreferredSize(new Dimension(491, 700));
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("18px"),
						ColumnSpec.decode("257px:grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("193px:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC,
						RowSpec.decode("fill:454px:grow"), }));

		panel = new ContactPane();
		add(panel, "2, 2, fill, fill");

		JPanel panel_1 = new JPanel();
		add(panel_1, "4, 2, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(89dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC, }));

		studentOtherInfoPanel = new StudentOtherInfoPanel();
		panel_1.add(studentOtherInfoPanel, "2, 2, fill, top");

	}

	@Override
	public void setObject(Student obj) {
		setStudent(obj);
	}

	private void setStudent(Student obj) {
		studentOtherInfoPanel.setStudent(obj);
		panel.setContact(obj.getContact());
	}

	// public Student cloneStudent(){
	// Student student = new Student(id, contact, enrollPoint, enrollYear,
	// myClass);
	// return student;
	// }

}
