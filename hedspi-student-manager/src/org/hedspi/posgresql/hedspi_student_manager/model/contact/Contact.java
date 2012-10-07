package org.hedspi.posgresql.hedspi_student_manager.model.contact;

import java.util.Date;

import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.NewLineListManipulator;
import org.hedspi.posgresql.hedspi_student_manager.util.HedspiUtil;

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

	public static HedspiObjects<Contact> getContacts() {
		if (contacts == null)
			contacts = new HedspiObjects<>();
		return contacts;
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

	public Contact(String id) {
		super(id);
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

	public District getDistrict() {
		return district;
	}

	public Date getDob() {
		return dob;
	}

	public NewLineListManipulator getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getHome() {
		return home;
	}

	public NewLineListManipulator getImage() {
		return image;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return getFirstName() + " " + getLastName();
	}

	public String getNote() {
		return note;
	}

	public NewLineListManipulator getPhone() {
		return phone;
	}

	public boolean isMan() {
		return isMan;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setEmail(NewLineListManipulator email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public void setImage(NewLineListManipulator image) {
		this.image = image;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMan(boolean isMan) {
		this.isMan = isMan;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setPhone(NewLineListManipulator phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public String getClassName() {
		return "Contact";
	}

	public String getInsertQuery() {
		return String
				.format("insert into \"Contact\" (\"FirstName\", \"LastName\", \"Sex\", \"DOB\", \"Email\", \"Phone\", \"ImageUrl\", \"Notes\", \"Home\", \"CT#\", \"DT#\")"
						+ "values('%s', '%s', %s, '%s', '%s', '%s', '%s', '%s', '%s', %s, %s)",
						HedspiUtil.quoteConvert(getFirstName()),
						HedspiUtil.quoteConvert(getLastName()),
						HedspiUtil.quoteConvert(isMan() ? "true" : "false"),
						HedspiUtil.quoteConvert(getDob().toString()),
						HedspiUtil.quoteConvert(getEmail().toString()),
						HedspiUtil.quoteConvert(getPhone().toString()),
						HedspiUtil.quoteConvert(getImage().toString()),
						HedspiUtil.quoteConvert(getNote()),
						HedspiUtil.quoteConvert(getHome()),
						HedspiUtil.quoteConvert(getId()),
						HedspiUtil.quoteConvert(getDistrict().getId()));
	}

	public static Contact getNewContact() {
		Contact contact = new Contact(getContacts().getNewId(), "",
				new NewLineListManipulator(""), new NewLineListManipulator(""),
				new Date(), true, "No", "Name", new NewLineListManipulator(""),
				"", District.getDistricts().getDefaultValue());
		getContacts().put(contact);
		return contact;
	}

	public String getSearchString(boolean isSearchAddress,
			boolean isSearchBirthday, boolean isSearchEmail,
			boolean isSearchImageUrls, boolean isSearchName,
			boolean isSearchNote, boolean isSearchPhone) {
		String ret = "";

		if (isSearchName)
			ret += getName();
		if (isSearchAddress)
			ret += " " + getHome() + " " + getDistrict().getName()
					+ getDistrict().getCity().getName();
		if (isSearchEmail)
			ret += " " + getEmail().getEndlnString().replace("\n", " ");
		if (isSearchBirthday)
			ret += " " + getDob().toString();
		if (isSearchNote)
			ret += " " + getNote();
		if (isSearchPhone)
			ret += " " + getPhone().getEndlnString().replace("\n", " ");
		if (isSearchImageUrls)
			ret += " " + getImage().getEndlnString().replace("\n", " ");

		return ret;
	}

}
