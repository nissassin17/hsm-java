package org.hedspi.posgresql.hedspi_student_manager.model.lecturer;

import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;

public class Faculty extends HedspiObject {

	public static final String ID_CODE = "FC#";
	public static final String NAME_CODE = "Name";

	private String name;

	public Faculty(String id) {
		super(id);
	}

	public Faculty(String id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}

}
