package org.hsm.view.contact;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.hsm.model.contact.Contact;
import org.hsm.model.contact.address.City;
import org.hsm.model.contact.address.District;
import org.hsm.model.hedspi.SortedHedspiObjectsComboModel;
import org.hsm.view.util.list.DefaultListEditor;
import org.hsm.view.util.object_associated.IObjectUpdater;
import org.hsm.view.util.object_associated.OAComboBox;
import org.hsm.view.util.object_associated.OADateSpinner;
import org.hsm.view.util.object_associated.OAEditorPane;
import org.hsm.view.util.object_associated.OATextField;
import org.hsm.view.util.object_associated.OAToggleButton;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

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
	private Contact contact;
	private OAToggleButton<Contact> oaToggleButtonSex;
	private OADateSpinner<Contact> oaSpinnerDob;

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
			public District getValue(Contact object) {
				return object.getDistrict();
			}

			@Override
			public void setValue(Contact object, District value) {
				object.setDistrict(value);
			}
		});
		comboBoxDistrict = oaDistrict.getComboBox();
		comboBoxDistrict.setModel(districtModel);

		oaToggleButtonSex = new OAToggleButton<Contact>(
				new IObjectUpdater<Contact, String>() {

					@Override
					public String getValue(Contact object) {
						if (object.isMan())
							return OAToggleButton.FALSE;
						return OAToggleButton.TRUE;
					}

					@Override
					public void setValue(Contact object, String value) {
						object.setMan(value.equals(OAToggleButton.FALSE));
					}
				});
		toggleButtonSex = oaToggleButtonSex.getToggleButton();
		toggleButtonSex.setText("Male");
		toggleButtonSex.addItemListener(new ItemListener() {
			@Override
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
					public String getValue(Contact object) {
						return object.getHome();
					}

					@Override
					public void setValue(Contact object, String value) {
						object.setHome(value);
					}
				});
		textFieldHome = oaHome.getTextField();
		textFieldHome.setColumns(10);

		oaLast = new OATextField<>(new IObjectUpdater<Contact, String>() {

			@Override
			public String getValue(Contact object) {
				return object.getLastName();
			}

			@Override
			public void setValue(Contact object, String value) {
				object.setLastName(value);
			}

		});
		textFieldLast = oaLast.getTextField();
		textFieldLast.setColumns(10);

		oaFirst = new OATextField<Contact>(
				new IObjectUpdater<Contact, String>() {

					@Override
					public String getValue(Contact object) {
						return object.getFirstName();
					}

					@Override
					public void setValue(Contact object, String value) {
						object.setFirstName(value);
					}

				});
		textFieldFirst = oaFirst.getTextField();
		textFieldFirst.setColumns(10);

		// For city does not need updater associated with contact because it's
		// district's job.
		comboBoxCity = new JComboBox<>(City.getCities().getComboBoxModel());
		comboBoxCity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox<City> cities = (JComboBox<City>) arg0.getSource();
				City currentCity = cities.getItemAt(cities.getSelectedIndex());
				if (currentCity != null)
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

		oaSpinnerDob = new OADateSpinner<Contact>(
				new IObjectUpdater<Contact, Date>() {

					@Override
					public Date getValue(Contact object) {
						return object.getDob();
					}

					@Override
					public void setValue(Contact object, Date value) {
						object.setDob(value);
					}
				});
		spinnerDob = oaSpinnerDob.getSpinner();
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
					public String getValue(Contact object) {
						return object.getNote();
					}

					@Override
					public void setValue(Contact object, String value) {
						object.setNote(value);
					}
				});
		editorPanelNote = oaNote.getEditorPane();
		editorPanelNote.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(editorPanelNote, "4, 23, fill, fill");

	}

	protected JComboBox<District> getComboBox() {
		return comboBoxDistrict;
	}

	protected JComboBox<City> getComboBox_1() {
		return comboBoxCity;
	}

	public Contact getContact() {
		return contact;
	}

	public DefaultListEditor getListEmail() {
		return listEmail;
	}

	public DefaultListEditor getListImage() {
		return listImage;
	}

	public DefaultListEditor getListPhone() {
		return listPhone;
	}

	private void setCity(City currentCity) {
		getComboBox_1().setSelectedItem(currentCity);
		getComboBox().setModel(currentCity.getDistricts().getComboBoxModel());
	}

	public void setContact(Contact contact) {
		this.contact = contact;
		oaSpinnerDob.setObject(contact);
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

		oaToggleButtonSex.setObject(contact);
	}

	public void setListEmail(DefaultListEditor listEmail) {
		this.listEmail = listEmail;
	}

	public void setListImage(DefaultListEditor listImage) {
		this.listImage = listImage;
	}

	public void setListPhone(DefaultListEditor listPhone) {
		this.listPhone = listPhone;
	}
}
