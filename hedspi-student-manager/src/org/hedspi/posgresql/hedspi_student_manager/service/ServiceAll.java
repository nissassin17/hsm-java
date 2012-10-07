package org.hedspi.posgresql.hedspi_student_manager.service;

import java.util.ArrayList;

import org.hedspi.posgresql.hedspi_student_manager.model.academic.HedspiClass;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Contact;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Student;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.City;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;

public class ServiceAll {

	public static void backup() {
		// TODO finding way to backup by jdbc rather than using extenal command.
		// See src/bin/pg_dump/pg_dump.c for document
	}

	public static void restoreLast() {
		// TODO view backup
	}

	public static boolean clearAll() {
		int cnt = CoreService.getInstance().update(
				new String[] { "delete from \"Student\"",
						"delete from \"Class\"", "delete from \"Contact\"",
						"delete from \"District\"", "delete from \"City\"" });

		return cnt == 5;
	}

	public static boolean commit() {
		if (!clearAll())
			return false;
		boolean result = false;

		ArrayList<String> queries = new ArrayList<>();
		for (City it : City.getCities().values())
			queries.add(it.getInsertQuery());
		for (District it : District.getDistricts().values())
			queries.add(it.getInsertQuery());
		for (Contact it : Contact.getContacts().values())
			queries.add(it.getInsertQuery());
		for (HedspiClass it : HedspiClass.getClasses().values())
			queries.add(it.getInsertQuery());
		for (Student it : Student.getStudents().values())
			queries.add(it.getInsertQuery());

		int cnt = CoreService.getInstance().update(
				queries.toArray(new String[queries.size()]));
		result = (cnt == queries.size());
		return result;
	}

}
