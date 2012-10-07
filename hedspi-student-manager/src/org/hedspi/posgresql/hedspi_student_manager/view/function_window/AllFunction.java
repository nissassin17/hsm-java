package org.hedspi.posgresql.hedspi_student_manager.view.function_window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JList;
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
import org.hedspi.posgresql.hedspi_student_manager.view.search.SearchPane;
import org.hedspi.posgresql.hedspi_student_manager.view.student.StudentPanel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

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
		mntmCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.getInstance().fireByView(getFrame(), "commit");
			}
		});
		mnOperation.add(mntmCommit);
		
		JMenuItem mntmReload = new JMenuItem("Reload");
		mntmReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.getInstance().fireByView(getFrame(), "reload");
			}
		});
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
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPaneAll.addTab("Search", null, scrollPane, null);
		
		SearchPane searchPanel = new SearchPane();
		scrollPane.setViewportView(searchPanel);
	}

	@Override
	public void fire(String command, Object... data) {
		switch (command) {
		case "set-visible":
			super.setVisible((boolean) data[0]);
			break;
		case "repaint":
			super.revalidate();
			super.repaint();
			Control.getInstance().getLogger().log(Level.INFO, "UI repainted ok");
			JOptionPane.showMessageDialog(this, "Reload database ok!", "Reload ok", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case "commitResult":
			if ((boolean)data[0])
				JOptionPane.showMessageDialog(this, "Commit done", "Commit done", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "Commit failed", "Commit failed", JOptionPane.ERROR_MESSAGE);
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

	private AllFunction getFrame() {
		return this;
	}
}
