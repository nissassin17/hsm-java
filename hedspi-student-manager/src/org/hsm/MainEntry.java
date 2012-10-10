/**
 * 
 */
package org.hsm;

import javax.swing.JOptionPane;

import org.hsm.control.Control;

/**
 * @author haidang-ubuntu
 * 
 */
public final class MainEntry {

	/**
	 * Main entry for all project
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Control.getInstance().fire("start");
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,
					"An error has occured while running application.\nMessage: "
							+ e.getMessage());
		}
	}

}
