package org.hedspi.posgresql.hedspi_student_manager.model.academic;

import org.hedspi.posgresql.hedspi_student_manager.model.contact.Lecturer;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Student;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;

public class HedspiClass extends HedspiObject {

	public static final String ID_CODE = "CL#";
	public static final String NAME_CODE = "Name";
	private static HedspiObjects<HedspiClass> classes;

	public static HedspiObjects<HedspiClass> getClasses() {
		return classes;
	}

	public static void setClasses(HedspiObjects<HedspiClass> classes) {
		HedspiClass.classes = classes;
	}

	private String name;

	private Lecturer lecturer;

	private HedspiObjects<Student> students;

	public HedspiClass(String id) {
		super(id);
	}

	public HedspiClass(String id, String name) {
		super(id);
		this.name = name;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public String getName() {
		return name;
	}

	public HedspiObjects<Student> getStudents() {
		if (students == null)
			students = new HedspiObjects<>();
		return students;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setStudents(HedspiObjects<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return getName();
	}
}
