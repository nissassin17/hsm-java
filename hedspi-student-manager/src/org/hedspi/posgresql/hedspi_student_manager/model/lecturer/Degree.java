package org.hedspi.posgresql.hedspi_student_manager.model.lecturer;

import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;

public class Degree extends HedspiObject {

	@Override
	public String getClassName() {
		return "Degree";
	}

	public static final String ID_CODE = "DG#";

	public static final String NAME_CODE = "Name";
	private String name;

	public Degree(String id) {
		super(id);
	}

	public Degree(String id, String name) {
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
