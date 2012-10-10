/**
 * Filename: MiscPanel
 * Function: exporting data of students into html file.
 * Coder: HuyPN
 * Last modified: 08-10-2012
 */

package org.hedspi.posgresql.hedspi_student_manager.view.misc;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;

//import org.hedspi.posgresql.hedspi_student_manager.model.contact.Contact;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Student;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
//import javax.swing.JComboBox;

public class MiscPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MiscPanel() {
		setLayout(new MigLayout("",
				"[][181.00,grow][74.00,grow][79.00][][][][]",
				"[][][][][][][][][]"));

		JLabel lblFieldsToExport = new JLabel("Fields to export");
		add(lblFieldsToExport, "cell 1 1");

		final JCheckBox chckbxName = new JCheckBox("Name");
		add(chckbxName, "cell 2 1");

		final JCheckBox chckbxMssv = new JCheckBox("MSSV");
		add(chckbxMssv, "cell 3 1");

		final JCheckBox chckbxContact = new JCheckBox("Contact");
		add(chckbxContact, "cell 4 1");

		final JCheckBox chckbxEnrollYear = new JCheckBox("Enroll year");
		add(chckbxEnrollYear, "cell 2 2");

		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Get result from Checkboxes
				int _name = 0;
				int _enrollYear = 0;
				int _contact = 0;
				int _mssv = 0;

				// Create HTML header
				String content_html = "<html>\n";
				content_html += "<head><title> Students Information </title></head>\n"
						+ "<body>\n";
				// Create table
				content_html += "<table border=\"1\"\n";
				content_html += "<tr bgcolor=\"cccccc\">\n";

				// Checking list of fields to export
				if (chckbxName.isSelected() == true) {
					_name = 1;
					content_html += "<td>Fullname</td>\n";
				}

				if (chckbxMssv.isSelected() == true) {
					_mssv = 1;
					content_html += "<td>MSSV</td>\n";
				}

				if (chckbxEnrollYear.isSelected() == true) {
					_enrollYear = 1;
					content_html += "<td>Enroll year</td>\n";
				}

				if (chckbxContact.isSelected() == true) {
					_contact = 1;
					content_html += "<td>Contact</td>\n";
				}

				// Get info of students, write into content_html string
				for (Student student : Student.getStudents().values()) {
					content_html += "<tr>\n";
					if (_name == 1) {
						content_html += "<td>" + student.getName() + "</td>\n";
					}

					if (_mssv == 1) {
						content_html += "<td>" + student.getMssv() + "</td>\n";
					}

					if (_enrollYear == 1) {
						content_html += "<td>" + student.getEnrollYear()
								+ "</td>\n";
					}

					if (_contact == 1) {
						content_html += "<td>\n" + "<table border=\"1\">\n"
								+ "</tr bgcolor=\"cccccc\">\n";
						content_html += "<td>DOB</td>\n";
						content_html += "<td>Email</td>\n";
						content_html += "<td>PhoneNumber</td>\n";
						content_html += "<td>Address</td>\n";
						content_html += "</tr>\n";
						content_html += "<td>" + student.getContact().getDob()
								+ "</td>\n";
						content_html += "<td>"
								+ student.getContact().getEmail().toString()
								+ "</td>\n";
						content_html += "<td>"
								+ student.getContact().getPhone().toString()
								+ "</td>\n";
						content_html += "<td>" + student.getContact().getHome()
								+ "</td>\n";

						content_html += "</tr>\n" + "</table>\n" + "</td>\n";
					}
					content_html += "</tr>\n" + "</table>\n";
				}

				content_html += "</body>\n</html>\n";

				// Get filename from textField
				String filepath = textField.getText();

				// Open new file to write
				try {
					FileWriter fstream = new FileWriter(filepath);
					BufferedWriter out = new BufferedWriter(fstream);
					// Write content_html to new file
					out.write(content_html);
					out.close();
				} catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
				}
			}
		});

		JLabel lblFilepath = new JLabel("Filepath");
		add(lblFilepath, "cell 1 4");

		textField = new JTextField();
		add(textField, "cell 2 4 3 1,growx");
		textField.setColumns(10);
		add(btnExport, "cell 2 6,alignx center");

	}

}
