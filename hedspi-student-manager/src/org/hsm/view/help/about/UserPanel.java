package org.hsm.view.help.about;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class UserPanel extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public UserPanel(String imageUrl, String info) {
		setSize(new Dimension(380, 250));

		JScrollPane scrollPane = new JScrollPane();
		setLeftComponent(scrollPane);

		JTextArea textArea = new JTextArea(info);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		setRightComponent(scrollPane_1);

		ImagePanel panel = new ImagePanel(imageUrl);
		scrollPane_1.setViewportView(panel);

	}

}
