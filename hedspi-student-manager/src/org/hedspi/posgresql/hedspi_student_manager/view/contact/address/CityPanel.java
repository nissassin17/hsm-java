package org.hedspi.posgresql.hedspi_student_manager.view.contact.address;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.City;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;
import org.hedspi.posgresql.hedspi_student_manager.view.util.list.DistrictListEditor;
import org.hedspi.posgresql.hedspi_student_manager.view.util.list.IObjectViewPanel;
import org.hedspi.posgresql.hedspi_student_manager.view.util.list.ListEditor;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class CityPanel extends JPanel implements IObjectViewPanel<City> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_1;
	private City city;
	private ListEditor<District> panel;

	/**
	 * Create the panel.
	 */
	public CityPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("136dlu:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("top:4dlu"), FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"), }));

		JPanel panel_1 = new JPanel();
		add(panel_1, "2, 2, fill, fill");
		panel_1.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("56px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("86px:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("20px"), }));

		JLabel lblName = DefaultComponentFactory.getInstance().createLabel(
				"City name");
		panel_1.add(lblName, "1, 2, left, center");

		textField_1 = new JTextField();
		panel_1.add(textField_1, "3, 2, fill, top");
		textField_1.setColumns(10);

		JButton btnSave = new JButton("Save");
		panel_1.add(btnSave, "5, 2");

		JLabel lblDistrictList = DefaultComponentFactory.getInstance()
				.createLabel("Districts list");
		add(lblDistrictList, "2, 4, left, top");

		panel = new DistrictListEditor();
		add(panel, "2, 6, fill, fill");
	}

	public City getCity() {
		return city;
	}

	protected ListEditor<District> getPanel_3() {
		return panel;
	}

	public void setCity(City city) {
		this.city = city;
		textField_1.setText(city.getName());
		getPanel_3().setHedspiObject(city.getDistricts());
	}

	@Override
	public void setObject(City obj) {
		setCity(obj);
	}
}
