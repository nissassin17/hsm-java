package org.hedspi.posgresql.hedspi_student_manager.model.contact;

import org.hedspi.posgresql.hedspi_student_manager.model.academic.HedspiClass;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;

public class Student extends HedspiObject {

	public static final String ID_CODE = "ST#";
	public static final String ENROLL_POINT_CODE = "EnrollPoint";
	public static final String ENROLL_YEAR_CODE = "EnrollYear";

	public static HedspiObjects<Student> getStudents() {
		return students;
	}

	public static void setStudents(HedspiObjects<Student> students) {
		Student.students = students;
	}

	private Contact contact;

	private double enrollPoint;

	private int enrollYear;

	private HedspiClass myClass;

	private String Mssv;

	private static HedspiObjects<Student> students;

	public Student(String id) {
		super(id);
	}

	public Student(String id, Contact contact, double enrollPoint,
			int enrollYear, HedspiClass myClass, String mssv) {
		super(id);
		this.contact = contact;
		this.enrollPoint = enrollPoint;
		this.enrollYear = enrollYear;
		this.myClass = myClass;
		Mssv = mssv;
	}
	public Contact getContact() {
		return contact;
	}
	public double getEnrollPoint() {
		return enrollPoint;
	}
	public int getEnrollYear() {
		return enrollYear;
	}
	public String getMssv() {
		return Mssv;
	}

	public HedspiClass getMyClass() {
		return myClass;
	}

	public String getName() {
		return getContact().getName();
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void setEnrollPoint(double enrollPoint) {
		this.enrollPoint = enrollPoint;
	}

	public void setEnrollYear(int enrollYear) {
		this.enrollYear = enrollYear;
	}

	public void setMssv(String mssv) {
		Mssv = mssv;
	}

	public void setMyClass(HedspiClass myClass) {
		this.myClass = myClass;
	}

	@Override
	public String toString() {
		return getName();
	}

}
