package org.hsm.model.contact.address;

import java.util.logging.Level;

import org.hsm.control.Control;
import org.hsm.model.hedspi.HedspiObject;
import org.hsm.model.hedspi.HedspiObjects;
import org.hsm.util.HedspiUtil;
import org.hsm.view.util.list.IObjectListIntegrator;

public class City extends HedspiObject {

	@Override
	public String getClassName() {
		return "City";
	}

	public static final String ID_CODE = "CY#";
	public static final String NAME_CODE = "Name";

	private String name;

	private HedspiObjects<District> districts;

	private static HedspiObjects<City> cities;

	public static HedspiObjects<City> getCities() {
		if (cities == null)
			cities = new HedspiObjects<>();
		return cities;
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

	public String getInsertQuery() {
		return String.format(
				"insert into\"City\" (\"CY#\", \"Name\") values(%s, '%s')",
				HedspiUtil.quoteConvert(super.getId()),
				HedspiUtil.quoteConvert(getName()));
	}

	public static IObjectListIntegrator<City> getCityGenner() {
		return new IObjectListIntegrator<City>() {

			@Override
			public boolean isRemovable(City object) {
				if (!object.getDistricts().isEmpty())
					return false;
				return getCities().size() > 1;
			}

			@Override
			public City getNewObject() {
				return new City(getCities().getNewId(), "No name");
			}

			@Override
			public void beforeRemove(City object) {
				if (!object.getDistricts().isEmpty())
					Control.getInstance()
							.getLogger()
							.log(Level.SEVERE,
									"Deleting city that still contains districts could make unhandled errors");
				if (getCities().size() <= 1)
					Control.getInstance()
							.getLogger()
							.log(Level.SEVERE,
									"Delete last class. Create at least one itermidiately to avoid potential errors");
			}
		};
	}

	private static boolean isSearchName = true;
	private static boolean isSearchDistricts = true;

	public static void setSearchStringArg(boolean selected, boolean selected2) {
		isSearchName = selected;
		isSearchDistricts = selected2;
	}

	@Override
	public String getSearchString() {
		String ret = "";
		if (isSearchName)
			ret = getName();
		if (isSearchDistricts) {
			ret += "\n";
			for (District it : getDistricts().values())
				ret += it.getName() + ", ";
		}
		return ret;
	}

}
