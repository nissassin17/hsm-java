package org.hedspi.posgresql.hedspi_student_manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Contact;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.NewLineListManipulator;

public class ContactService {

	public static HedspiObjects<Contact> getContacts() {
		HedspiObjects<Contact> contacts = new HedspiObjects<>();
		//get contact
		Control.getInstance().getLogger().log(Level.INFO, "Fetch raw data of contacts");
		String query = "select \"FirstName\", \"LastName\", \"Sex\", \"DOB\", \"Email\", \"Phone\", \"ImageUrl\", \"Notes\", \"Home\", \"CT#\", \"DT#\" \n" + 
				"from \"Contact\"";
		ArrayList<HashMap<String, Object>> rs = CoreService.getInstance().query(query);
		for(HashMap<String, Object> it : rs){
			String first = (String)it.get("FirstName");
			if (first == null)
				first = "";
			String last = (String)it.get("LastName");
			if (last == null)
				last = "";
			
			boolean sex = (boolean)it.get("Sex");
			Date dob = (Date)it.get("DOB");
			
			String strEmail = (String)it.get("Email");
			if (strEmail == null)
				strEmail = "";
			NewLineListManipulator emails = new NewLineListManipulator(strEmail);
			String strphone = (String)it.get("Phone");
			if (strphone == null)
				strphone = "";
			NewLineListManipulator phones = new NewLineListManipulator(strphone);
			String strImage = (String)it.get("Image");
			if (strImage == null)
				strImage = "";
			NewLineListManipulator images = new NewLineListManipulator(strImage);
			String note = (String)it.get("Notes");
			if (note == null)
				note = "";
			
			String home = (String)it.get("Home");
			if (home == null)
				home = "";
			
			int id = (int)it.get("CT#");
			String ct = String.valueOf(id);
			int dtid = (int)it.get("DT#");
			String dt = String.valueOf(dtid);
			
			District district = District.getDistricts().get(dt);
			
			Contact contact = new Contact(ct, note, images, phones, dob, sex, first, last, emails, home, district);
			
			contacts.put(contact);
		}
		
		return contacts;
	}

}
