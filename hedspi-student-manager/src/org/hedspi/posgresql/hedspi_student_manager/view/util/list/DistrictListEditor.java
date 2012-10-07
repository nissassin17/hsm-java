package org.hedspi.posgresql.hedspi_student_manager.view.util.list;

import org.hedspi.posgresql.hedspi_student_manager.model.contact.Contact;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.City;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;

public class DistrictListEditor extends ListEditor<District> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private City city;

	public DistrictListEditor() {
		super();
	}

	@Override
	public boolean isRemovable(District object) {
		for (Contact it : Contact.getContacts().values())
			if (it.getDistrict() == object)
				return false;
		return true;
	}

	@Override
	public void beforeRemove(District object) {
		object.getCity().getDistricts().removeObject(object);
		District.getDistricts().removeObject(object);
	}

	public void setCity(City obj) {
		city = obj;
		super.setHedspiObject(obj.getDistricts());
		District tt = obj.getDistricts().getDefaultValue();
		if (tt != null)
			super.setSelectedValue(tt, true);
	}

	@Override
	public District getNewObject(String val) {
		if (city == null)
			return null;
		District district = new District(District.getDistricts().getNewId(), val, city);
		District.getDistricts().put(district);
		//Not need to do this city.getDistricts().put(district);
		return district;
	}
}
