package org.hsm.model.contact.address;

import org.hsm.model.hedspi.HedspiObject;
import org.hsm.model.hedspi.HedspiObjects;
import org.hsm.util.HedspiUtil;

public class District extends HedspiObject {

	@Override
	public String getClassName() {
		return "District";
	}

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

	public District(String id, String name, City myCity) {
		super(id);
		this.name = name;
		this.city = myCity;
	}

	public City getCity() {
		return city;
	}

	public String getName() {
		return name;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getInsertQuery() {
		return String
				.format("insert into \"District\" (\"DT#\", \"CY#\", \"Name\") values(%s, %s,'%s')",
						HedspiUtil.quoteConvert(super.getId()),
						HedspiUtil.quoteConvert(getCity().getId()),
						HedspiUtil.quoteConvert(getName()));
	}

	private static boolean isSearchName;
	private static boolean isSearchCity;

	public static void setSearchStringArg(boolean isSearchName,
			boolean isSearchCity) {
		District.isSearchCity = isSearchCity;
		District.isSearchName = isSearchName;
	}

	@Override
	public String getSearchString() {
		String ret = "";

		if (isSearchName)
			ret = getName();
		if (isSearchCity)
			ret += " " + getCity().getName();

		return ret;
	}

}
