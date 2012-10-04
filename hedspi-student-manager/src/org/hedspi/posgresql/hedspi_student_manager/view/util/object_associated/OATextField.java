package org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class OATextField <T extends Object> extends OAComponentAbstract<String, T> {
	
	public OATextField(IObjectUpdater<T, String> textFieldUpdater){
		this(textFieldUpdater, null);
	}
	
	private JTextField textField;
	
	public JTextField getTextField() {
		return textField;
	}

	public OATextField(IObjectUpdater<T, String> textFieldUpdater, T objectArg) {
		super(textFieldUpdater, objectArg);
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updateObjectValue();
			}
		});
	}

	@Override
	public String getValue() {
		return textField.getText();
	}

	@Override
	public void setValue(String value) {
		textField.setText(value);
	}
}
