package org.hedspi.posgresql.hedspi_student_manager.view.contact;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.hedspi.posgresql.hedspi_student_manager.model.contact.Contact;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.City;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.SortedHedspiObjectsComboModel;
import org.hedspi.posgresql.hedspi_student_manager.view.util.list.DefaultListEditor;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.IObjectUpdater;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.OAComboBox;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.OAEditorPane;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.OATextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class ContactPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldHome;
	private JTextField textFieldLast;
	private JTextField textFieldFirst;
	private SortedHedspiObjectsComboModel<District> districtModel;
	private DefaultListEditor listPhone;
	private JSpinner spinnerDob;

	public DefaultListEditor getListPhone() {
		return listPhone;
	}

	public void setListPhone(DefaultListEditor listPhone) {
		this.listPhone = listPhone;
	}

	public DefaultListEditor getListEmail() {
		return listEmail;
	}

	public void setListEmail(DefaultListEditor listEmail) {
		this.listEmail = listEmail;
	}

	public DefaultListEditor getListImage() {
		return listImage;
	}

	public void setListImage(DefaultListEditor listImage) {
		this.listImage = listImage;
	}

	private DefaultListEditor listEmail;
	private DefaultListEditor listImage;
	private JEditorPane editorPanelNote;
	private JComboBox<City> comboBoxCity;
	private JComboBox<District> comboBoxDistrict;
	private JToggleButton toggleButtonSex;
	private OATextField<Contact> oaHome;
	private OATextField<Contact> oaLast;
	private OATextField<Contact> oaFirst;
	private OAEditorPane<Contact> oaNote;
	private OAComboBox<District, Contact> oaDistrict;

	private void setCity(City currentCity) {
		getComboBox_1().setSelectedItem(currentCity);
		getComboBox().setModel(currentCity.getDistricts().getComboBoxModel());
	}

	/**
	 * Create the panel.
	 */
	public ContactPane() {
		setBorder(new TitledBorder(null, "Contact information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel label_1 = new JLabel("Name");

		JLabel label_2 = new JLabel("First*");

		JLabel label_3 = new JLabel("Last*");

		JLabel label_4 = new JLabel("Sex*");

		JLabel label_5 = new JLabel("Birthday*");

		JLabel label_6 = new JLabel("District*");

		JLabel label_7 = new JLabel("City*");

		JLabel label_8 = new JLabel("Home");

		JLabel label_9 = new JLabel("Address");

		districtModel = new SortedHedspiObjectsComboModel<District>();
		oaDistrict = new OAComboBox<>(new IObjectUpdater<Contact, District>() {

			@Override
			public void setValue(Contact object, District value) {
				object.setDistrict(value);
			}

			@Override
			public District getValue(Contact object) {
				return object.getDistrict();
			}
		});
		comboBoxDistrict = oaDistrict.getComboBox();
		comboBoxDistrict.setModel(districtModel);

		toggleButtonSex = new JToggleButton("Male");
		toggleButtonSex.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				JToggleButton btn = (JToggleButton) arg0.getSource();
				if (btn.isSelected())
					btn.setText("Female");
				else
					btn.setText("Male");
			}
		});

		oaHome = new OATextField<Contact>(
				new IObjectUpdater<Contact, String>() {

					@Override
					public void setValue(Contact object, String value) {
						object.setHome(value);
					}

					@Override
					public String getValue(Contact object) {
						return object.getHome();
					}
				});
		textFieldHome = oaHome.getTextField();
		textFieldHome.setColumns(10);

		oaLast = new OATextField<>(new IObjectUpdater<Contact, String>() {

			@Override
			public void setValue(Contact object, String value) {
				object.setLastName(value);
			}

			@Override
			public String getValue(Contact object) {
				return object.getLastName();
			}

		});
		textFieldLast = oaLast.getTextField();
		textFieldLast.setColumns(10);

		oaFirst = new OATextField<Contact>(
				new IObjectUpdater<Contact, String>() {

					@Override
					public void setValue(Contact object, String value) {
						object.setFirstName(value);
					}

					@Override
					public String getValue(Contact object) {
						return object.getFirstName();
					}

				});
		textFieldFirst = oaFirst.getTextField();
		textFieldFirst.setColumns(10);

		// For city does not need updater associated with contact because it's
		// district's job.
		comboBoxCity = new JComboBox<>(City.getCities().getComboBoxModel());
		comboBoxCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox<City> cities = (JComboBox<City>) arg0.getSource();
				City currentCity = cities.getItemAt(cities.getSelectedIndex());
				setCity(currentCity);
			}

		});

		listPhone = new DefaultListEditor();
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(5dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:64px"),
				ColumnSpec.decode("113px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(6dlu;default)"), }, new RowSpec[] {
				RowSpec.decode("max(1dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("14px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("23px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("21px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("14px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("22px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("22px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("20px"),
				RowSpec.decode("114px"), RowSpec.decode("max(122px;default)"),
				RowSpec.decode("81dlu"), RowSpec.decode("53px:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(7dlu;default)"), }));
		add(label_1, "3, 3, left, top");
		add(label_2, "3, 5, left, center");
		add(textFieldFirst, "4, 5, fill, top");
		add(label_3, "3, 7, left, center");
		add(textFieldLast, "4, 7, fill, top");
		add(label_4, "3, 9, left, center");
		add(toggleButtonSex, "4, 9, left, top");
		add(label_5, "3, 11, left, center");

		spinnerDob = new JSpinner();
		spinnerDob.setModel(new SpinnerDateModel(new Date(1349197200000L),
				null, null, Calendar.DAY_OF_YEAR));
		add(spinnerDob, "4, 11");
		add(label_9, "3, 13, left, top");
		add(label_7, "3, 15, left, center");
		add(comboBoxCity, "4, 15, fill, top");
		add(label_8, "3, 19, left, center");

		JLabel lblPhones = new JLabel("Phones");
		add(lblPhones, "3, 20");
		add(listPhone, "4, 20, fill, fill");
		add(textFieldHome, "4, 19, fill, top");
		add(label_6, "3, 17, left, center");
		add(comboBoxDistrict, "4, 17, fill, top");

		JLabel lblEmails = new JLabel("Emails");
		add(lblEmails, "3, 21");

		listEmail = new DefaultListEditor();
		add(listEmail, "4, 21, fill, fill");

		JLabel lblImageUrls = DefaultComponentFactory.getInstance()
				.createLabel("Image urls");
		add(lblImageUrls, "3, 22");

		listImage = new DefaultListEditor();
		add(listImage, "4, 22, fill, fill");

		JLabel lblNotes = new JLabel("Notes");
		add(lblNotes, "3, 23");

		oaNote = new OAEditorPane<Contact>(
				new IObjectUpdater<Contact, String>() {

					@Override
					public void setValue(Contact object, String value) {
						object.setNote(value);
					}

					@Override
					public String getValue(Contact object) {
						return object.getNote();
					}
				});
		editorPanelNote = new JEditorPane();
		editorPanelNote.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(editorPanelNote, "4, 23, fill, fill");

	}

	private Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
		spinnerDob.setModel(new SpinnerDateModel(contact.getDob(), null, null,
				Calendar.DAY_OF_YEAR));
		oaFirst.setObject(contact);
		oaLast.setObject(contact);
		oaHome.setObject(contact);
		getListPhone().setHedspiObject(contact.getPhone());
		getListEmail().setHedspiObject(contact.getEmail());
		getListImage().setHedspiObject(contact.getImage());
		oaNote.setObject(contact);
		// this action must be called or be positioned inside comboBoxDistrict
		// updater
		setCity(contact.getDistrict().getCity());
		oaDistrict.setObject(contact);

		getToggleButtonSex().setSelected(!contact.isMan());
	}

	protected JComboBox<City> getComboBox_1() {
		return comboBoxCity;
	}

	protected JComboBox<District> getComboBox() {
		return comboBoxDistrict;
	}

	protected JToggleButton getToggleButtonSex() {
		return toggleButtonSex;
	}
}
