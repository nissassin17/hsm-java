package org.hedspi.posgresql.hedspi_student_manager.view.student;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.hedspi.posgresql.hedspi_student_manager.model.academic.HedspiClass;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Student;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.IObjectUpdater;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.OAComboBox;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.OANumberSpinner;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.OATextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class StudentOtherInfoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldEnrollPoint;
	private JTextField textFieldID;
	private JSpinner spinnerEnrollYear;
	private JComboBox<HedspiClass> comboBoxClass;
	private OATextField<Student> oaTextFieldID;
	private OATextField<Student> oaEnrollPoint;
	private OAComboBox<HedspiClass, Student> oaComboBox;
	private OANumberSpinner<Student> oaEnrollYear;

	/**
	 * Create the panel.
	 */
	public StudentOtherInfoPanel() {
		setBorder(new TitledBorder(null, "Student other information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(9dlu;default)"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblStudentIdentity = DefaultComponentFactory.getInstance()
				.createLabel("ID (8 chars)*");
		lblStudentIdentity.setDisplayedMnemonic('i');
		add(lblStudentIdentity, "2, 2, right, default");

		oaTextFieldID = new OATextField<Student>(
				new IObjectUpdater<Student, String>() {

					@Override
					public String getValue(Student object) {
						return object.getMssv();
					}

					@Override
					public void setValue(Student object, String value) {
						object.setMssv(value);
					}
				});
		textFieldID = oaTextFieldID.getTextField();
		add(textFieldID, "4, 2, fill, default");
		textFieldID.setColumns(10);

		JLabel lblClass = DefaultComponentFactory.getInstance().createLabel(
				"Class*");
		lblClass.setDisplayedMnemonic('c');
		add(lblClass, "2, 4, right, default");

		oaComboBox = new OAComboBox<>(
				new IObjectUpdater<Student, HedspiClass>() {

					@Override
					public HedspiClass getValue(Student object) {
						return object.getMyClass();
					}

					@Override
					public void setValue(Student object, HedspiClass value) {
						object.getMyClass().getStudents().removeObject(object);
						object.setMyClass(value);
						value.getStudents().put(object);
					}
				});
		comboBoxClass = oaComboBox.getComboBox();
		comboBoxClass.setModel(HedspiClass.getClasses().getComboBoxModel());

		add(comboBoxClass, "4, 4, fill, default");

		JLabel lblEntrollPoint = DefaultComponentFactory.getInstance()
				.createLabel("Entroll point*");
		add(lblEntrollPoint, "2, 6, right, default");

		oaEnrollPoint = new OATextField<Student>(
				new IObjectUpdater<Student, String>() {

					@Override
					public String getValue(Student object) {
						return String.valueOf(object.getEnrollPoint());
					}

					@Override
					public void setValue(Student object, String value) {
						double val;
						try {
							val = Double.parseDouble(value);
							object.setEnrollPoint(val);
						} catch (Exception e) {
						}
					}
				});
		textFieldEnrollPoint = oaEnrollPoint.getTextField();
		add(textFieldEnrollPoint, "4, 6, fill, default");
		textFieldEnrollPoint.setColumns(10);

		JLabel lblEntrollYear = DefaultComponentFactory.getInstance()
				.createLabel("Entroll year*");
		add(lblEntrollYear, "2, 8, right, default");

		oaEnrollYear = new OANumberSpinner<Student>(
				new IObjectUpdater<Student, String>() {

					@Override
					public String getValue(Student object) {
						return String.valueOf(object.getEnrollYear());
					}

					@Override
					public void setValue(Student object, String value) {
						try {
							int year = Integer.parseInt(value);
							object.setEnrollYear(year);
						} catch (Exception e) {

						}
					}
				}, 2000, 0, 3000, 1);
		spinnerEnrollYear = oaEnrollYear.getSpinner();
		add(spinnerEnrollYear, "4, 8");

	}

	public void setStudent(Student obj) {
		oaEnrollYear.setObject(obj);
		oaTextFieldID.setObject(obj);
		oaEnrollPoint.setObject(obj);
		oaComboBox.setObject(obj);
	}

}
