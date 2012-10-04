package org.hedspi.posgresql.hedspi_student_manager.model.lecturer;

import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;

public class Degree extends HedspiObject {

	public Degree(String id) {
		super(id);
	}

	public static final String ID_CODE = "DG#";
	public static final String NAME_CODE = "Name";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Degree(String id, String name) {
		super(id);
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}

}
