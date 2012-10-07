package org.hedspi.posgresql.hedspi_student_manager.model.academic;

import java.util.logging.Level;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Lecturer;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Student;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;
import org.hedspi.posgresql.hedspi_student_manager.util.HedspiUtil;
import org.hedspi.posgresql.hedspi_student_manager.view.util.list.IObjectListIntegrator;

public class HedspiClass extends HedspiObject {

	public static final String ID_CODE = "CL#";
	public static final String NAME_CODE = "Name";
	private static HedspiObjects<HedspiClass> classes;

	public static HedspiObjects<HedspiClass> getClasses() {
		if (classes == null)
			classes = new HedspiObjects<>();
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

	public String getInsertQuery() {
		return String.format("insert into \"Class\" (\"CL#\", \"Name\") values (%s, '%s')",
				HedspiUtil.quoteConvert(super.getId()),
				HedspiUtil.quoteConvert(getName()));
	}

	public static IObjectListIntegrator<HedspiClass> getClassGenner() {
		return new IObjectListIntegrator<HedspiClass>() {
			
			@Override
			public boolean isRemovable(HedspiClass object) {
				return object.getStudents().isEmpty();
			}
			
			@Override
			public HedspiClass getNewObject() {
				return new HedspiClass(getClasses().getNewId(), "No name");
			}
			
			@Override
			public void beforeRemove(HedspiClass object) {
				if (!object.getStudents().isEmpty())
					Control.getInstance().getLogger().log(Level.SEVERE, "Deleting class that still contains students could make future unhandled errors");
			}
		};
	}
}
