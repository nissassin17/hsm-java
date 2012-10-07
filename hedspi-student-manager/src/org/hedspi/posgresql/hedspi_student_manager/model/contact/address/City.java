package org.hedspi.posgresql.hedspi_student_manager.model.contact.address;

import java.util.logging.Level;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;
import org.hedspi.posgresql.hedspi_student_manager.util.HedspiUtil;
import org.hedspi.posgresql.hedspi_student_manager.view.util.list.IObjectListIntegrator;

public class City extends HedspiObject {

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

	public String getInsertQuery() {
		return String.format("insert into\"City\" (\"CY#\", \"Name\") values(%s, '%s')", HedspiUtil.quoteConvert(super.getId()), HedspiUtil.quoteConvert(getName()));
	}

	public static IObjectListIntegrator<City> getCityGenner() {
		return new IObjectListIntegrator<City>() {
			
			@Override
			public boolean isRemovable(City object) {
				return object.getDistricts().isEmpty();
			}
			
			@Override
			public City getNewObject() {
				return new City(getCities().getNewId(), "No name");
			}
			
			@Override
			public void beforeRemove(City object) {
				if (!object.getDistricts().isEmpty())
					Control.getInstance().getLogger().log(Level.SEVERE, "Deleting city that still contains districts could make unhandled errors");
			}
		};
	}

}
