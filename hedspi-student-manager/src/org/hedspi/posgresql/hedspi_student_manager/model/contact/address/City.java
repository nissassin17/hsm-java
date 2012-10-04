package org.hedspi.posgresql.hedspi_student_manager.model.contact.address;

import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;

public class City extends HedspiObject {

	public static final String ID_CODE = "CY#";
	public static final String NAME_CODE = "Name";

	private String name;

	private HedspiObjects<District> districts;

	private static HedspiObjects<City> cities;

	public static HedspiObjects<City> getCities() {
		return cities;
	}

	public static void setCities(HedspiObjects<City> cities) {
		City.cities = cities;
	}

	public City(String id) {
		super(id);
	}

	public City(String id, String name) {
		this(id);
		this.name = name;
	}

	public void addDistrict(District district) {
		getDistricts().put(district);
	}

	public HedspiObjects<District> getDistricts() {
		if (districts == null)
			districts = new HedspiObjects<>();
		return districts;
	}

	public String getName() {
		return name;
	}

	public void setDistricts(HedspiObjects<District> districts) {
		this.districts = districts;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}

}
