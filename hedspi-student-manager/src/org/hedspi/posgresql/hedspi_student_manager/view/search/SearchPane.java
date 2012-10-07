package org.hedspi.posgresql.hedspi_student_manager.view.search;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import org.hedspi.posgresql.hedspi_student_manager.model.academic.HedspiClass;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Student;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.City;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SearchPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private JCheckBox chckbxCity;
	private JCheckBoxMenuItem chckbxmntmCityName;
	private JCheckBoxMenuItem chckbxmntmCityDistricts;
	private SpinnerNumberModel model;
	private DefaultTableModel resultModel;
	private JCheckBox chckbxCaseSensitive;
	private JCheckBoxMenuItem chckbxmntmStudentAddress;
	private JCheckBoxMenuItem chckbxmntmStudentBirthday;
	private JCheckBoxMenuItem chckbxmntmStudentClass;
	private JCheckBoxMenuItem chckbxmntmStudentEmail;
	private JCheckBoxMenuItem chckbxmntmStudentEnrollPoint;
	private JCheckBoxMenuItem chckbxmntmStudentEnrollYear;
	private JCheckBoxMenuItem chckbxmntmStudentImageUrls;
	private JCheckBoxMenuItem chckbxmntmStudentMssv;
	private JCheckBoxMenuItem chckbxmntmStudentName;
	private JCheckBoxMenuItem chckbxmntmStudentNotes;
	private JCheckBoxMenuItem chckbxmntmStudentPhones;
	private JCheckBox chckbxStudent;
	private JCheckBoxMenuItem chckbxmntmClassName;
	private JCheckBoxMenuItem chckbxmntmClassStudents;
	private JCheckBox chckbxDistrict;
	private JCheckBoxMenuItem chckbxmntmDistrictName;
	private JCheckBoxMenuItem chckbxmntmDistrictCity;

	/**
	 * Create the panel.
	 */
	public SearchPane() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel = new JPanel();
		add(panel, "2, 2, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblSearchString = DefaultComponentFactory.getInstance().createLabel("Search string");
		panel.add(lblSearchString, "2, 2");
		
		textField = new JTextField();
		panel.add(textField, "4, 2");
		textField.setColumns(10);
		
		JLabel lblNresultsThreshold = DefaultComponentFactory.getInstance().createLabel("NResults threshold");
		panel.add(lblNresultsThreshold, "2, 4");
		
		JSpinner spinner = new JSpinner();
		model = new SpinnerNumberModel(new Integer(10), new Integer(0), null, new Integer(1));
		spinner.setModel(model);
		panel.add(spinner, "4, 4");
		
		chckbxCaseSensitive = new JCheckBox("Case sensitive");
		panel.add(chckbxCaseSensitive, "4, 6");
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "2, 4, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		chckbxStudent = new JCheckBox("Student");
		panel_1.add(chckbxStudent, "4, 2");
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(chckbxStudent, popupMenu);
		
		chckbxmntmStudentName = new JCheckBoxMenuItem("Name");
		chckbxmntmStudentName.setSelected(true);
		popupMenu.add(chckbxmntmStudentName);
		
		chckbxmntmStudentClass = new JCheckBoxMenuItem("Class");
		chckbxmntmStudentClass.setSelected(true);
		popupMenu.add(chckbxmntmStudentClass);
		
		chckbxmntmStudentEnrollPoint = new JCheckBoxMenuItem("Enroll point");
		chckbxmntmStudentEnrollPoint.setSelected(true);
		popupMenu.add(chckbxmntmStudentEnrollPoint);
		
		chckbxmntmStudentEnrollYear = new JCheckBoxMenuItem("Enroll year");
		chckbxmntmStudentEnrollYear.setSelected(true);
		popupMenu.add(chckbxmntmStudentEnrollYear);
		
		chckbxmntmStudentMssv = new JCheckBoxMenuItem("MSSV");
		chckbxmntmStudentMssv.setSelected(true);
		popupMenu.add(chckbxmntmStudentMssv);
		
		chckbxmntmStudentEmail = new JCheckBoxMenuItem("Emails");
		chckbxmntmStudentEmail.setSelected(true);
		popupMenu.add(chckbxmntmStudentEmail);
		
		chckbxmntmStudentAddress = new JCheckBoxMenuItem("Address");
		chckbxmntmStudentAddress.setSelected(true);
		popupMenu.add(chckbxmntmStudentAddress);
		
		chckbxmntmStudentNotes = new JCheckBoxMenuItem("Notes");
		chckbxmntmStudentNotes.setSelected(true);
		popupMenu.add(chckbxmntmStudentNotes);
		
		chckbxmntmStudentImageUrls = new JCheckBoxMenuItem("Image urls");
		chckbxmntmStudentImageUrls.setSelected(true);
		popupMenu.add(chckbxmntmStudentImageUrls);
		
		chckbxmntmStudentPhones = new JCheckBoxMenuItem("Phones");
		chckbxmntmStudentPhones.setSelected(true);
		popupMenu.add(chckbxmntmStudentPhones);
		
		chckbxmntmStudentBirthday = new JCheckBoxMenuItem("Birthday");
		chckbxmntmStudentBirthday.setSelected(true);
		popupMenu.add(chckbxmntmStudentBirthday);
		
		JCheckBox chckbxClass = new JCheckBox("Class");
		panel_1.add(chckbxClass, "6, 2");
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(chckbxClass, popupMenu_1);
		
		chckbxmntmClassName = new JCheckBoxMenuItem("Name");
		chckbxmntmClassName.setSelected(true);
		popupMenu_1.add(chckbxmntmClassName);
		
		chckbxmntmClassStudents = new JCheckBoxMenuItem("Students");
		chckbxmntmClassStudents.setSelected(true);
		popupMenu_1.add(chckbxmntmClassStudents);
		
		chckbxDistrict = new JCheckBox("District");
		panel_1.add(chckbxDistrict, "8, 2");
		
		JPopupMenu popupMenu_2 = new JPopupMenu();
		addPopup(chckbxDistrict, popupMenu_2);
		
		chckbxmntmDistrictName = new JCheckBoxMenuItem("Name");
		chckbxmntmDistrictName.setSelected(true);
		popupMenu_2.add(chckbxmntmDistrictName);
		
		chckbxmntmDistrictCity = new JCheckBoxMenuItem("City");
		chckbxmntmDistrictCity.setSelected(true);
		popupMenu_2.add(chckbxmntmDistrictCity);
		
		chckbxCity = new JCheckBox("City");
		panel_1.add(chckbxCity, "10, 2");
		
		JPopupMenu popupMenu_3 = new JPopupMenu();
		addPopup(chckbxCity, popupMenu_3);
		
		chckbxmntmCityName = new JCheckBoxMenuItem("Name");
		chckbxmntmCityName.setSelected(true);
		popupMenu_3.add(chckbxmntmCityName);
		
		chckbxmntmCityDistricts = new JCheckBoxMenuItem("Districts");
		chckbxmntmCityDistricts.setSelected(true);
		popupMenu_3.add(chckbxmntmCityDistricts);
		
		JCheckBox chckbxLecturer = new JCheckBox("Lecturer");
		chckbxLecturer.setEnabled(false);
		panel_1.add(chckbxLecturer, "12, 2");
		
		JPopupMenu popupMenu_4 = new JPopupMenu();
		addPopup(chckbxLecturer, popupMenu_4);
		
		JCheckBoxMenuItem chckbxmntmLecturerName = new JCheckBoxMenuItem("Name");
		chckbxmntmLecturerName.setSelected(true);
		popupMenu_4.add(chckbxmntmLecturerName);
		
		JCheckBoxMenuItem chckbxmntmLecturerDegree = new JCheckBoxMenuItem("Degrees");
		chckbxmntmLecturerDegree.setSelected(true);
		popupMenu_4.add(chckbxmntmLecturerDegree);
		
		JCheckBoxMenuItem chckbxmntmLecturerFaculty = new JCheckBoxMenuItem("Faculty");
		chckbxmntmLecturerFaculty.setSelected(true);
		popupMenu_4.add(chckbxmntmLecturerFaculty);
		
		resultModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Ord", "Type", "Value", "String"
				}
			) {
				private static final long serialVersionUID = 1L;
				Class<?>[] columnTypes = new Class[] {
					int.class, String.class, Object.class, String.class
				};
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchContainer searchContainer = new SearchContainer();
				
				//City
				City.setSearchStringArg(chckbxmntmCityName.isSelected(), chckbxmntmCityDistricts.isSelected());
				if (chckbxCity.isSelected()){
					searchContainer.addObjects(City.getCities());
				}
				
				//student
				Student.setSearchStringArg(
						chckbxmntmStudentAddress.isSelected(),
						chckbxmntmStudentBirthday.isSelected(),
						chckbxmntmStudentClass.isSelected(),
						chckbxmntmStudentEmail.isSelected(),
						chckbxmntmStudentEnrollPoint.isSelected(),
						chckbxmntmStudentEnrollYear.isSelected(),
						chckbxmntmStudentImageUrls.isSelected(),
						chckbxmntmStudentMssv.isSelected(),
						chckbxmntmStudentName.isSelected(),
						chckbxmntmStudentNotes.isSelected(),
						chckbxmntmStudentPhones.isSelected());
				if (chckbxStudent.isSelected())
					searchContainer.addObjects(Student.getStudents());
				
				//class
				HedspiClass.setSearchStringArg(
						chckbxmntmClassName.isSelected(),
						chckbxmntmClassStudents.isSelected());
				
				//district
				District.setSearchStringArg(
						chckbxmntmDistrictName.isSelected(),
						chckbxmntmDistrictCity.isSelected());
				
				//lecturer
				//Not implemented
				
				//result
				HedspiObject[] rets = searchContainer.search(textField.getText(), model.getNumber().intValue(), chckbxCaseSensitive.isSelected());
				for(int i = resultModel.getRowCount() - 1; i >= 0; i--)
					resultModel.removeRow(i);
				int cnt = 1;
				for(HedspiObject it : rets){
					resultModel.addRow(new Object[]{
							cnt++, it.getClassName(), it, it.getSearchString()});
				}
			}
		});
		add(btnSearch, "2, 6, center, default");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 8, fill, fill");
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(resultModel);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		scrollPane.setViewportView(table);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
