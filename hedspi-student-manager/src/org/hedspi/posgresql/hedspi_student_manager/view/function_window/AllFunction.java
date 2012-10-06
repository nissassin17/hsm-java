package org.hedspi.posgresql.hedspi_student_manager.view.function_window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;
import org.hedspi.posgresql.hedspi_student_manager.view.IView;
import org.hedspi.posgresql.hedspi_student_manager.view.contact.address.AddressPanel;
import org.hedspi.posgresql.hedspi_student_manager.view.help.about.AboutBox;
import org.hedspi.posgresql.hedspi_student_manager.view.student.StudentPanel;
import javax.swing.JMenuItem;

public class AllFunction extends JFrame implements IView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneMain;

	/**
	 * Create the frame.
	 */
	public AllFunction() {
		setTitle("Hedspi Student Manager");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Control.getInstance().fire("exit");
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				if (JOptionPane.showConfirmDialog(arg0.getWindow(),
						"Are you sure want to quit?", "Quit?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					arg0.getWindow().setVisible(false);
					arg0.getWindow().dispose();
				}
				;
			}
		});
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 774, 478);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnOperation = new JMenu("Operation");
		menuBar.add(mnOperation);
		
		JMenuItem mntmCommit = new JMenuItem("Commit");
		mnOperation.add(mntmCommit);
		
		JMenuItem mntmReload = new JMenuItem("Reload");
		mnOperation.add(mntmReload);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new AboutBox(getFrame())).setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneMain.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneMain);

		JTabbedPane tabbedPaneAll = new JTabbedPane(SwingConstants.LEFT);
		contentPaneMain.add(tabbedPaneAll, BorderLayout.CENTER);

		StudentPanel panel = new StudentPanel();
		tabbedPaneAll.addTab("Student", null, panel, null);

		AddressPanel panel_1 = new AddressPanel();
		tabbedPaneAll.addTab("Address", null, panel_1, null);

		org.hedspi.posgresql.hedspi_student_manager.view.classview.ClassPanel splitPane = new org.hedspi.posgresql.hedspi_student_manager.view.classview.ClassPanel();
		tabbedPaneAll.addTab("Class", null, splitPane, null);
	}

	@Override
	public void fire(String command, Object... data) {
		switch (command) {
		case "set-visible":
			super.setVisible((boolean) data[0]);
			break;

		default:
			Control.getInstance()
					.getLogger()
					.log(Level.WARNING,
							"You have called FunctionWindow an operation that is not supported.\nCommand: {0}",
							command);
			break;
		}
	}

	private JFrame getFrame() {
		return this;
	}
}
