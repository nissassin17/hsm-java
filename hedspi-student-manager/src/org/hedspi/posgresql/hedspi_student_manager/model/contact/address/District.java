package org.hedspi.posgresql.hedspi_student_manager.model.contact.address;

import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;
import org.hedspi.posgresql.hedspi_student_manager.util.HedspiUtil;

public class District extends HedspiObject {

	public static final String ID_CODE = "DT#";
	public static final String NAME_CODE = "Name";
	private static HedspiObjects<District> districts;

	public static HedspiObjects<District> getDistricts() {
		if (districts == null)
			districts = new HedspiObjects<>();
		return districts;
	}

	private String name;

	private City city;

	public District(String id) {
		super(id);
	}
	public District(String id, City myCity, String name) {
		super(id);
		this.city = myCity;
		this.name = name;
	}

	public District(String id, String name, City myCity) {
		super(id);
		this.name = name;
		this.city = myCity;
	}

	public City getCity() {
		return city;
	}

	public City getMyCity() {
		return city;
	}

	public String getName() {
		return name;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setMyCity(City myCity) {
		this.city = myCity;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getInsertQuery() {
		return String.format("insert into \"District\" (\"DT#\", \"CY#\", \"Name\") values(%s, %s,'%s')",
				HedspiUtil.quoteConvert(super.getId()),
				HedspiUtil.quoteConvert(getCity().getId()),
				HedspiUtil.quoteConvert(getName()));
	}

}
