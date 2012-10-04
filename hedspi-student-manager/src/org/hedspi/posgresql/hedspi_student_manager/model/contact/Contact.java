package org.hedspi.posgresql.hedspi_student_manager.model.contact;

import java.util.Date;

import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.NewLineListManipulator;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.IObjectUpdater;
import org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated.OATextField;

public class Contact extends HedspiObject {

	public static final String ID_CODE = "CT#";
	public static final String NAME_CODE = "Name";
	public static final String FIRSTNAME_CODE = "FirstName";
	public static final String LASTNAME_CODE = "LastName";
	public static final String SEX_CODE = "Sex";
	public static final String DOB_CODE = "DOB";
	public static final String EMAIL_CODE = "Email";
	public static final String PHONE_CODE = "Phone";
	public static final String IMAGE_CODE = "ImageUrl";
	public static final String NOTE_CODE = "Notes";
	public static final String HOME_CODE = "Home";

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getName() {
		return getFirstName() + " " + getLastName();
	}

	public Contact(String id, String note, NewLineListManipulator image,
			NewLineListManipulator phone, Date dob, boolean isMan,
			String firstName, String lastName, NewLineListManipulator email,
			String home, District district) {
		super(id);
		this.note = note;
		this.image = image;
		this.phone = phone;
		this.dob = dob;
		this.isMan = isMan;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.home = home;
		this.district = district;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public NewLineListManipulator getImage() {
		return image;
	}

	public void setImage(NewLineListManipulator image) {
		this.image = image;
	}

	public NewLineListManipulator getPhone() {
		return phone;
	}

	public void setPhone(NewLineListManipulator phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public NewLineListManipulator getEmail() {
		return email;
	}

	public void setEmail(NewLineListManipulator email) {
		this.email = email;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Date getDob() {
		return dob;
	}

	public boolean isMan() {
		return isMan;
	}

	private String note;
	private NewLineListManipulator image;
	private NewLineListManipulator phone;
	private Date dob;
	private boolean isMan;// true if is man
	private String firstName;
	private String lastName;
	private NewLineListManipulator email;
	private String home;
	private District district;

	private static HedspiObjects<Contact> contacts;

	public static void setContacts(HedspiObjects<Contact> contacts) {
		Contact.contacts = contacts;
	}

	public void setMan(boolean isMan) {
		this.isMan = isMan;
	}

	public static HedspiObjects<Contact> getContacts() {
		return contacts;
	}

	public Contact(String id) {
		super(id);
	}

	public String toString() {
		return getName();
	}

}
