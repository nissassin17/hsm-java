package org.hedspi.posgresql.hedspi_student_manager.model;

import java.util.Properties;
import java.util.logging.Level;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;
import org.hedspi.posgresql.hedspi_student_manager.model.academic.HedspiClass;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Contact;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Student;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.City;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.Pair;
import org.hedspi.posgresql.hedspi_student_manager.service.AddressService;
import org.hedspi.posgresql.hedspi_student_manager.service.ClassService;
import org.hedspi.posgresql.hedspi_student_manager.service.ContactService;
import org.hedspi.posgresql.hedspi_student_manager.service.CoreService;
import org.hedspi.posgresql.hedspi_student_manager.service.StudentService;

public class Model implements IModel {

	private static Model instance;

	public static Model getInstance() {
		if (instance == null)
			instance = new Model();
		return instance;
	}

	private Model() {
	}

	private void cloneDatabase() {
		Pair<HedspiObjects<City>, HedspiObjects<District>> val = AddressService
				.getAddresses();
		City.setCities(val.getObject0());
		District.setDistricts(val.getObject1());
		Contact.setContacts(ContactService.getContacts());
		HedspiClass.setClasses(ClassService.getClasses());
		Student.setStudents(StudentService.getStudentList());
	}

	@Override
	public Object getData(String command, Object... data) {
		switch (command) {
		case "check-login":
			Properties loginInfo = (Properties) data[0];
			return CoreService.isGoodLogin(loginInfo);

		default:
			Control.getInstance()
					.getLogger()
					.log(Level.WARNING,
							"Unsupported getData operation  - " + command
									+ ". Return null");
			return null;
		}
	}

	@Override
	public void setData(String command, Object... data) {
		switch (command) {
		case "cloneDatabase":
			cloneDatabase();
			break;
		default:
			Control.getInstance()
					.getLogger()
					.log(Level.WARNING,
							"Unsupported setData operation  - " + command);
		}
	}

}
