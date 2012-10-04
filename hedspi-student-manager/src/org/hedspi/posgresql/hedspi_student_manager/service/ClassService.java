package org.hedspi.posgresql.hedspi_student_manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;
import org.hedspi.posgresql.hedspi_student_manager.model.academic.HedspiClass;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;

public class ClassService {

	public static HedspiObjects<HedspiClass> getClasses() {
		Control.getInstance()
				.getLogger()
				.log(Level.INFO,
						"Fetch class list. Do not get student list now, because it could make infinite recursive");
		HedspiObjects<HedspiClass> classes = new HedspiObjects<>();
		String query = "select \"CL#\", \"Name\" from \"Class\"";
		ArrayList<HashMap<String, Object>> rs = CoreService.getInstance()
				.query(query);
		for (HashMap<String, Object> it : rs) {
			int cl1 = (int) it.get("CL#");
			String cla = String.valueOf(cl1);
			String name = (String) it.get("Name");
			if (name == null)
				name = "";
			HedspiClass cl = new HedspiClass(cla, name);
			classes.put(cl);
		}
		return classes;
	}

}
