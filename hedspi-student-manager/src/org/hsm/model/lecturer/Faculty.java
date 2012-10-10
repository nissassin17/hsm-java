package org.hsm.model.lecturer;

import org.hsm.model.hedspi.HedspiObject;

public class Faculty extends HedspiObject {

	@Override
	public String getClassName() {
		return "Faculty";
	}

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
