package org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated;

import javax.swing.JEditorPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class OAEditorPane<T> extends OAComponentAbstract<String, T> {

	JEditorPane editorPane;

	public OAEditorPane(IObjectUpdater<T, String> objectUpdater) {
		this(objectUpdater, null);
	}

	public OAEditorPane(IObjectUpdater<T, String> objectUpdater, T object) {
		super(objectUpdater, object);
		editorPane = new JEditorPane();
		editorPane.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updateObjectValue();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}
		});

	}

	public JEditorPane getEditorPane() {
		return editorPane;
	}

	@Override
	public String getValue() {
		return editorPane.getText();
	}

	@Override
	public void setValue(String value) {
		editorPane.setText(value);
	}

}
