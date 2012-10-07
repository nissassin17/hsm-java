package org.hedspi.posgresql.hedspi_student_manager.model.contact;

import org.hedspi.posgresql.hedspi_student_manager.model.academic.HedspiClass;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;
import org.hedspi.posgresql.hedspi_student_manager.util.HedspiUtil;
import org.hedspi.posgresql.hedspi_student_manager.view.util.list.IObjectListIntegrator;

public class Student extends HedspiObject {

	@Override
	public String getClassName() {
		return "Student";
	}

	public static final String ID_CODE = "ST#";
	public static final String ENROLL_POINT_CODE = "EnrollPoint";
	public static final String ENROLL_YEAR_CODE = "EnrollYear";
	protected static final double DEFAULT_ENROLLPOINT = 0;
	protected static final int DEFAULT_ENROLLYEAR = 2010;
	protected static final String DEFAULT_MSSV = "";

	public static HedspiObjects<Student> getStudents() {
		if (students == null)
			students = new HedspiObjects<>();
		return students;
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

	public String getInsertQuery() {
		return String
				.format("insert into \"Student\" (\"EnrollPoint\", \"EnrollYear\", \"CT#\", \"CL#\", \"MSSV\") "
						+ "values(%s, %s, %s, %s, '%s')",
						HedspiUtil.quoteConvert(String
								.valueOf(getEnrollPoint())),
						HedspiUtil.quoteConvert(String.valueOf(getEnrollYear())),
						HedspiUtil.quoteConvert(super.getId()), HedspiUtil
								.quoteConvert(getMyClass().getId()), HedspiUtil
								.quoteConvert(getMssv()));
	}

	public static IObjectListIntegrator<Student> getStudentGenner() {
		return new IObjectListIntegrator<Student>() {

			@Override
			public boolean isRemovable(Student object) {
				return true;
			}

			@Override
			public Student getNewObject() {
				HedspiClass val = HedspiClass.getClasses().getDefaultValue();
				if (val != null){
					Contact contact = Contact.getNewContact();
					Student st = new Student(getStudents().getNewId(),
							contact, DEFAULT_ENROLLPOINT,
							DEFAULT_ENROLLYEAR, val, DEFAULT_MSSV);
					val.getStudents().put(st);
					return st;
				}
				return null;
			}

			@Override
			public void beforeRemove(Student object) {
				object.getMyClass().getStudents().removeObject(object);
				Contact.getContacts().removeObject(object.getContact());
			}
		};
	}

	private static boolean isSearchAddress;
	private static boolean isSearchBirthday;
	private static boolean isSearchClass;
	private static boolean isSearchEmail;
	private static boolean isSearchEnrollPoint;
	private static boolean isSearchEnrollYear;
	private static boolean isSearchImageUrls;
	private static boolean isSearchMssv;
	private static boolean isSearchName;
	private static boolean isSearchNote;
	private static boolean isSearchPhone;

	public static void setSearchStringArg(boolean isSearchAddress,
			boolean isSearchBirthday, boolean isSearchClass,
			boolean isSearchEmail, boolean isSearchEnrollPoint,
			boolean isSearchEnrollYear, boolean isSearchImageUrls,
			boolean isSearchMssv, boolean isSearchName, boolean isSearchNote,
			boolean isSearchPhone) {
		Student.isSearchAddress = isSearchAddress;
		Student.isSearchBirthday = isSearchBirthday;
		Student.isSearchClass = isSearchClass;
		Student.isSearchEmail = isSearchEmail;
		Student.isSearchEnrollPoint = isSearchEnrollPoint;
		Student.isSearchEnrollYear = isSearchEnrollYear;
		Student.isSearchImageUrls = isSearchImageUrls;
		Student.isSearchMssv = isSearchMssv;
		Student.isSearchName = isSearchName;
		Student.isSearchNote = isSearchNote;
		Student.isSearchPhone = isSearchPhone;
	}

	@Override
	public String getSearchString() {
		String ret = getContact().getSearchString(isSearchAddress,
				isSearchBirthday, isSearchEmail, isSearchImageUrls,
				isSearchName, isSearchNote, isSearchPhone);
		if (isSearchClass)
			ret += " " + getMyClass().getName();
		if (isSearchMssv)
			ret += " " + getMssv();
		if (isSearchEnrollYear)
			ret += " " + String.valueOf(getEnrollYear());
		if (isSearchEnrollPoint)
			ret += " " + String.valueOf(getEnrollPoint());

		return ret;
	}

}
