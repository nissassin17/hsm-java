package org.hsm.model.contact;

import java.util.ArrayList;

import org.hsm.model.academic.HedspiClass;
import org.hsm.model.hedspi.HedspiObject;

public class Lecturer extends HedspiObject {

	@Override
	public String getClassName() {
		return "Lecturer";
	}

	public static final String ID_CODE = "LT#";

	private Contact contact;

	private ArrayList<HedspiClass> classes;

	public Lecturer(String id) {
		super(id);
	}

	public Lecturer(String id, Contact contact) {
		super(id);
		this.contact = contact;
	}

	public ArrayList<HedspiClass> getClasses() {
		return classes;
	}

	public Contact getContact() {
		return contact;
	}

	public String getName() {
		return getContact().getName();
	}

	public void setClasses(ArrayList<HedspiClass> classes) {
		this.classes = classes;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return getName();
	}

}
