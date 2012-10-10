package org.hsm.model;

import java.util.Properties;
import java.util.logging.Level;

import org.hsm.control.Control;
import org.hsm.model.academic.HedspiClass;
import org.hsm.model.contact.Contact;
import org.hsm.model.contact.Student;
import org.hsm.model.contact.address.City;
import org.hsm.model.contact.address.District;
import org.hsm.model.hedspi.HedspiObjects;
import org.hsm.model.hedspi.Pair;
import org.hsm.service.AddressService;
import org.hsm.service.ClassService;
import org.hsm.service.ContactService;
import org.hsm.service.CoreService;
import org.hsm.service.ServiceAll;
import org.hsm.service.StudentService;

public class Model implements IModel {

	private static Model instance;

	public static Model getInstance() {
		if (instance == null)
			instance = new Model();
		return instance;
	}

	private Model() {
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
	public boolean setData(String command, Object... data) {
		switch (command) {
		case "cloneDatabase":
			appendDatabase();
			return true;

		case "reload":
			reload();
			return true;

		case "commit":
			return commit();

		default:
			Control.getInstance()
					.getLogger()
					.log(Level.WARNING,
							"Unsupported setData operation  - " + command);
			return false;
		}
	}

	private boolean commit() {
		return ServiceAll.commit();
	}

	private void reload() {
		Contact.getContacts().clear();
		City.getCities().clear();
		District.getDistricts().clear();
		HedspiClass.getClasses().clear();
		Student.getStudents().clear();
		appendDatabase();
	}

	private void appendDatabase() {
		Pair<HedspiObjects<City>, HedspiObjects<District>> val = AddressService
				.getAddresses();
		City.getCities().putAll(val.getObject0());
		District.getDistricts().putAll(val.getObject1());
		Contact.getContacts().putAll(ContactService.getContacts());
		HedspiClass.getClasses().putAll(ClassService.getClasses());
		Student.getStudents().putAll(StudentService.getStudentList());

		// check class and city
		if (HedspiClass.getClasses().isEmpty())
			Control.getInstance()
					.getLogger()
					.log(Level.SEVERE,
							"Class database is empty. Create at least one to avoid severe errors");
		if (City.getCities().isEmpty())
			Control.getInstance()
					.getLogger()
					.log(Level.SEVERE,
							"City database is empty. Create at least one to avoid severe errors");
	}
}
