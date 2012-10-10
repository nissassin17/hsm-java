package org.hsm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.hsm.control.Control;
import org.hsm.model.academic.HedspiClass;
import org.hsm.model.contact.Contact;
import org.hsm.model.contact.Student;
import org.hsm.model.hedspi.HedspiObjects;

public class StudentService {

	public static HedspiObjects<Student> getStudentList() {
		HedspiObjects<Student> students = new HedspiObjects<>();

		// get student
		Control.getInstance().getLogger()
				.log(Level.INFO, "Fetch raw data of students' list");
		String query = "select \"EnrollPoint\", \"EnrollYear\", \"CT#\", \"CL#\", \"MSSV\" from \"Student\"";
		ArrayList<HashMap<String, Object>> rs = CoreService.getInstance()
				.query(query);
		for (HashMap<String, Object> it : rs) {
			double point = (double) it.get("EnrollPoint");
			int year = (int) it.get("EnrollYear");
			int ct = (int) it.get("CT#");
			int cl = (int) it.get("CL#");
			String mssv = (String) it.get("MSSV");
			if (mssv == null)
				mssv = "";
			HedspiClass cla = HedspiClass.getClasses().get(String.valueOf(cl));
			Student st = new Student(String.valueOf(ct), Contact.getContacts()
					.get(String.valueOf(ct)), point, year, cla, mssv);
			students.put(st);
			cla.getStudents().put(st);
		}

		return students;
	}

}
