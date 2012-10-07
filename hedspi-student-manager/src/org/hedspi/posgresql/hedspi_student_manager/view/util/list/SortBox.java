package org.hedspi.posgresql.hedspi_student_manager.view.util.list;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SortBox<T extends Object> extends JPanel {
	
	public static int smartCompare(String arg0, String arg1, String text, boolean isCase){
		int t1 = getDistance(arg0.toString(), text, isCase);
		int t2 = getDistance(arg1.toString(), text, isCase);
		if (t1 != t2)
			return t2 - t1;
		if (isCase)
			return arg0.toString().compareTo(arg1.toString());
		return arg0.toString().compareToIgnoreCase(arg1.toString());
	}
	
	private static int getDistance(String string, String text, boolean isCase) {
		int[][] f = new int[string.length() + 1][text.length() + 1];
		for (int i = 0; i <= string.length(); i++)
			for (int j = 0; j <= text.length(); j++)
				f[i][j] = 0;
		for (int i = 1; i <= string.length(); i++)
			for (int j = 1; j <= text.length(); j++) {
				f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
				char c1 = string.charAt(i - 1);
				char c2 = text.charAt(j - 1);
				if (!isCase) {
					c1 = Character.toLowerCase(c1);
					c2 = Character.toLowerCase(c2);
				}
				if (c1 == c2)
					f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
			}
		return f[string.length()][text.length()];
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private DefaultListModel<T> model;
	private JRadioButton rdbtnCase;
	private JRadioButton rdbtnNocase;
	private JRadioButton rdbtnSmart;

	/**
	 * Create the frame.
	 */
	public SortBox(DefaultListModel<T> model) {
		this.model = model;
		setBounds(100, 100, 326, 67);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JPanel panel = new JPanel();
		add(panel, "2, 2, fill, top");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("58px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px:grow"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("20px"), }));

		JLabel lblSortBox = DefaultComponentFactory.getInstance().createLabel(
				"Sort box");
		panel.add(lblSortBox, "1, 2, left, center");

		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				resort(textField.getText(), getCase(textField.getText()));
			}

			private boolean getCase(String text) {
				if (rdbtnCase.isSelected())
					return true;
				if (rdbtnNocase.isSelected())
					return false;
				for (char c : text.toCharArray()) {
					if (Character.isUpperCase(c))
						return true;
				}
				return false;
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				insertUpdate(arg0);
			}
		});
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				((JTextField) arg0.getComponent()).selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				((JTextField) e.getComponent()).select(0, 0);
			}
		});
		panel.add(textField, "3, 2, fill, top");
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		add(panel_1, "2, 4, fill, top");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("49px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("61px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("53px:grow"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("23px"), }));

		rdbtnCase = new JRadioButton("Case");
		panel_1.add(rdbtnCase, "1, 2, right, top");

		rdbtnNocase = new JRadioButton("Nocase");
		panel_1.add(rdbtnNocase, "3, 2, center, top");

		rdbtnSmart = new JRadioButton("Smart");
		rdbtnSmart.setSelected(true);
		panel_1.add(rdbtnSmart, "5, 2, left, top");

		ButtonGroup caseGroup = new ButtonGroup();
		caseGroup.add(rdbtnCase);
		caseGroup.add(rdbtnNocase);
		caseGroup.add(rdbtnSmart);

	}

	private void resort(final String text, final boolean isCase) {
		ArrayList<T> list = new ArrayList<>();
		for (int i = 0; i < model.getSize(); i++)
			list.add(model.getElementAt(i));
		model.removeAllElements();
		Collections.sort(list, new Comparator<Object>() {

			@Override
			public int compare(Object arg0, Object arg1) {
				return smartCompare(arg0.toString(), arg1.toString(), text, isCase);
			}
		});

		for (T it : list)
			model.addElement(it);
	}

}
