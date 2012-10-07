package org.hedspi.posgresql.hedspi_student_manager.view.util.list;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ObjectListPanel<T extends HedspiObject> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IObjectViewPanel<T> viewPanel;
	private HedspiObjects<T> hedspiObjects;
	private IObjectListIntegrator<T> integrator;
	private JList<T> list_1;

	/**
	 * Create the panel.
	 */
	public ObjectListPanel(IObjectViewPanel<T> viewPanelArg,
			HedspiObjects<T> hedspiObjectArg,
			IObjectListIntegrator<T> integratorArg) {
		this.integrator = integratorArg;
		this.hedspiObjects = hedspiObjectArg;
		this.viewPanel = viewPanelArg;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(91dlu;default):grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(97dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblCitiesList = DefaultComponentFactory.getInstance()
				.createLabel("Values list");
		add(lblCitiesList, "2, 4");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 6, fill, fill");
		list_1 = new JList<T>(hedspiObjects.getListModel());
		scrollPane.setViewportView(list_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					T val = getFocusedValue();
					if (val != null)
						viewPanel.setObject(val);
				}
			}
		});

		SortBox<T> panel_1 = new SortBox<>(hedspiObjects.getListModel());
		add(panel_1, "2, 2, fill, top");

		JPanel panel = new JPanel();
		add(panel, "2, 8, center, top");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				T newObject = integrator.getNewObject();
				if (newObject != null) {
					hedspiObjects.put(newObject);
					list_1.setSelectedValue(newObject, true);
				}
			}
		});
		panel.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				T val = getFocusedValue();
				if (val != null && integrator.isRemovable(val)) {
					integrator.beforeRemove(val);
					hedspiObjects.removeObject(val);
				} else
					JOptionPane.showMessageDialog(null, "Cannot delete object",
							"Delete failed", JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(btnRemove);

		JButton btnRemoveAll = new JButton("Remove all");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<T> values = new ArrayList<>(hedspiObjects.values());
				int cnt = 0;
				for (T it : values) {
					if (integrator.isRemovable(it)) {
						integrator.beforeRemove(it);
						T ret = hedspiObjects.removeObject(it);
						if (ret != null)
							cnt++;
					}
				}
				JOptionPane.showMessageDialog(null, "Deleted / all : " + cnt
						+ "/" + values.size(), "Delete result",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(btnRemoveAll);

		T defaultValue = hedspiObjects.getDefaultValue();
		if (defaultValue != null)
			list_1.setSelectedValue(defaultValue, true);
	}

	private T getFocusedValue() {
		T val = list_1.getSelectedValue();
		return val;
	}
}
