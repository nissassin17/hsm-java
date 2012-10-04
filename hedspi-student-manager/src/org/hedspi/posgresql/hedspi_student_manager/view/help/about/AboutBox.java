package org.hedspi.posgresql.hedspi_student_manager.view.help.about;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JPanel;
import java.awt.Dimension;

public class AboutBox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AboutBox(JFrame parent) {
		super(parent);
		setVisible(true);
		setSize(new Dimension(780, 212));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("About");
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { ColumnSpec
						.decode("174px:grow"), }, new RowSpec[] {
						FormFactory.UNRELATED_GAP_ROWSPEC,
						RowSpec.decode("14px"),
						FormFactory.UNRELATED_GAP_ROWSPEC,
						RowSpec.decode("14px"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		JLabel lblHedspiStudentManager = DefaultComponentFactory.getInstance()
				.createLabel("Hedspi student manager version 1.0");
		getContentPane().add(lblHedspiStudentManager, "1, 2, center, top");

		JLabel lblDevelopTeam = DefaultComponentFactory.getInstance()
				.createLabel("Develop team");
		getContentPane().add(lblDevelopTeam, "1, 4, center, top");

		JPanel panel = new JPanel();
		getContentPane().add(panel, "1, 6, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("258px:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("229px:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("247px:grow"), }, new RowSpec[] { RowSpec
				.decode("120px:grow"), }));

		UserPanel panel_1 = new UserPanel("image/haidang001.jpg",
				"Name : Trần Văn Sáng.\r\nMSSV : 20102076.\r\nS_have fun! :)");
		panel.add(panel_1, "2, 1, fill, fill");

		UserPanel panel_2 = new UserPanel("image/html.jpg",
				"Name : Bùi Trung Hiếu.\r\nMSSV : unknown.\r\nI'm ht!");
		panel.add(panel_2, "4, 1, fill, fill");

		UserPanel panel_3 = new UserPanel("image/huydt.jpg",
				"Name : Phùng Nhật Huy.\r\nMSSV : unknown.\r\nHuy chồn am I!");
		panel.add(panel_3, "6, 1, fill, fill");

	}

}
