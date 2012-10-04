package org.hedspi.posgresql.hedspi_student_manager.model.contact;

import java.util.ArrayList;

import org.hedspi.posgresql.hedspi_student_manager.model.academic.HedspiClass;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;

public class Lecturer extends HedspiObject {

	public static final String ID_CODE = "LT#";

	private Contact contact;

	public Lecturer(String id, Contact contact) {
		super(id);
		this.contact = contact;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public ArrayList<HedspiClass> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<HedspiClass> classes) {
		this.classes = classes;
	}

	private ArrayList<HedspiClass> classes;

	public Lecturer(String id) {
		super(id);
	}

	public String getName() {
		return getContact().getName();
	}

	@Override
	public String toString() {
		return getName();
	}

}
