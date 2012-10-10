package org.hsm.view.classview;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.hsm.model.academic.HedspiClass;
import org.hsm.model.contact.Student;
import org.hsm.view.util.list.IObjectViewPanel;
import org.hsm.view.util.object_associated.IObjectUpdater;
import org.hsm.view.util.object_associated.OATextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ClassViewPanel extends JPanel implements
		IObjectViewPanel<HedspiClass> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldClassName;
	private JList<Student> listClass;
	private OATextField<HedspiClass> oaClassName;
	private JComboBox<HedspiClass> comboBox;
	private HedspiClass currentClass;

	public void setCurrentClass(HedspiClass currentClass) {
		this.currentClass = currentClass;
	}

	public HedspiClass getCurrentClass() {
		return currentClass;
	}

	/**
	 * Create the panel.
	 */
	public ClassViewPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"), }));

		JPanel panel = new JPanel();
		add(panel, "2, 2, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("71px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px:grow"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("20px"), }));

		JLabel lblClassName = DefaultComponentFactory.getInstance()
				.createLabel("Class name");
		panel.add(lblClassName, "1, 2, left, center");

		oaClassName = new OATextField<HedspiClass>(
				new IObjectUpdater<HedspiClass, String>() {

					@Override
					public String getValue(HedspiClass object) {
						return object.getName();
					}

					@Override
					public void setValue(HedspiClass object, String value) {
						object.setName(value);
					}
				});
		textFieldClassName = oaClassName.getTextField();
		panel.add(textFieldClassName, "3, 2, fill, top");
		textFieldClassName.setColumns(10);

		JLabel lblStudentList = new JLabel("Student list");
		add(lblStudentList, "2, 4");

		JPanel panel_1 = new JPanel();
		add(panel_1, "2, 6, fill, fill");
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JButton btnMoveTo = new JButton("Move to");
		btnMoveTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HedspiClass selectedClass = (HedspiClass) comboBox
						.getSelectedItem();
				HedspiClass currentClass = getCurrentClass();
				if (selectedClass == null || selectedClass == currentClass)
					return;

				Student[] arr = new Student[listClass.getSelectedIndices().length];
				listClass.getSelectedValuesList().toArray(arr);
				for (Student it : arr)
					currentClass.getStudents().removeObject(it);
				for (Student it : arr) {
					selectedClass.getStudents().put(it);
					it.setMyClass(selectedClass);
				}
			}
		});
		GridBagConstraints gbc_btnMoveTo = new GridBagConstraints();
		gbc_btnMoveTo.insets = new Insets(0, 0, 0, 5);
		gbc_btnMoveTo.gridx = 0;
		gbc_btnMoveTo.gridy = 0;
		panel_1.add(btnMoveTo, gbc_btnMoveTo);

		comboBox = new JComboBox<>(HedspiClass.getClasses().getComboBoxModel());
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel_1.add(comboBox, gbc_comboBox);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 8, fill, fill");

		listClass = new JList<Student>();
		scrollPane.setViewportView(listClass);

	}

	protected JList<Student> getListClass() {
		return listClass;
	}

	protected JTextField getTextFieldClassName() {
		return textFieldClassName;
	}

	@Override
	public void setObject(HedspiClass obj) {
		oaClassName.setObject(obj);
		listClass.setModel(obj.getStudents().getListModel());
		Student st = obj.getStudents().getDefaultValue();
		if (st != null)
			listClass.setSelectedValue(st, true);
		setCurrentClass(obj);
	}
}
