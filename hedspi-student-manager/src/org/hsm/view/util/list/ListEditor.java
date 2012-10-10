package org.hsm.view.util.list;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.hsm.model.hedspi.HedspiObject;
import org.hsm.model.hedspi.HedspiObjects;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public abstract class ListEditor<T extends HedspiObject> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JList<T> list;

	public void setSelectedValue(Object anObject, boolean shouldScroll) {
		list.setSelectedValue(anObject, shouldScroll);
	}

	private HedspiObjects<T> hedspiObject;

	public ListEditor() {
		this(new HedspiObjects<T>());
	}

	public abstract T getNewObject(String val);

	public abstract boolean isRemovable(T object);

	public abstract void beforeRemove(T object);

	/**
	 * Create the panel.
	 */
	public ListEditor(HedspiObjects<T> hedspiObjectArg) {
		super();
		this.hedspiObject = hedspiObjectArg;

		setLayout(new MigLayout("",
				"[82.00px:211.00px:100.00%,grow][49.00:52.00:423.00]",
				"[:24.00px:33.00px][241.00,grow]"));

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				JTextField textField = (JTextField) arg0.getComponent();
				textField.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField textField = (JTextField) e.getComponent();
				textField.select(0, 0);
			}
		});
		add(textField, "cell 0 0,grow");
		textField.setColumns(10);

		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String val = textField.getText();
				if (!val.equals("")) {
					T newEl = getNewObject(val);
					if (newEl != null) {
						hedspiObject.put(newEl);
						textField.setText("");
						list.setSelectedValue(newEl, true);
					}
				}
			}
		});
		add(btnAdd, "cell 1 0,growx,aligny center");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");

		list = new JList<>(this.hedspiObject.getListModel());
		scrollPane.setViewportView(list);

		JPanel panel = new JPanel();
		add(panel, "flowx,cell 1 1,grow");
		panel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec
				.decode("39px:grow"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("23px"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JButton button = new JButton("{}");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				list.setSelectionInterval(0, list.getModel().getSize() - 1);
			}
		});
		panel.add(button, "1, 2, fill, top");

		JButton btnRemove = new JButton("-");
		panel.add(btnRemove, "1, 4, fill, top");
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<T> arr = list.getSelectedValuesList();
				for (T it : arr) {
					if (isRemovable(it)) {
						beforeRemove(it);
						hedspiObject.removeObject(it);
					}
				}
				T defaultValue = hedspiObject.getDefaultValue();
				if (defaultValue != null)
					list.setSelectedValue(defaultValue, true);
			}
		});
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] {
				textField, btnAdd, btnRemove, scrollPane }));

	}

	public HedspiObjects<T> getHedspiObject() {
		return hedspiObject;
	}

	public void setHedspiObject(HedspiObjects<T> hedspiObject) {
		this.hedspiObject = hedspiObject;
		list.setModel(hedspiObject.getListModel());
	}
}