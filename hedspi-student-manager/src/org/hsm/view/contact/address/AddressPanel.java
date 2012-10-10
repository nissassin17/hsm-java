package org.hsm.view.contact.address;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.hsm.model.contact.address.City;
import org.hsm.view.util.list.ObjectListPanel;

public class AddressPanel extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CityPanel panel;

	/**
	 * Create the panel.
	 */
	public AddressPanel() {

		panel = new CityPanel();

		JScrollPane scrollPane = new JScrollPane();
		setLeftComponent(scrollPane);

		ObjectListPanel<City> panel_1 = new ObjectListPanel<City>(panel,
				City.getCities(), City.getCityGenner());
		scrollPane.setViewportView(panel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		setRightComponent(scrollPane_1);

		scrollPane_1.setViewportView(panel);

	}

}
