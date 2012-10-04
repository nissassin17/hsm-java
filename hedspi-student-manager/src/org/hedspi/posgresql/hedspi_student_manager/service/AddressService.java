package org.hedspi.posgresql.hedspi_student_manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.hedspi.posgresql.hedspi_student_manager.control.Control;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.City;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.Pair;

public class AddressService {
	public static Pair<HedspiObjects<City>, HedspiObjects<District>> getAddresses() {
		Control.getInstance().getLogger().log(Level.INFO, "Fetch list of cities from database");

		//list of cities
		Control.getInstance().getLogger().log(Level.INFO, "Fetch list of cities");
		HedspiObjects<City> cities = new HedspiObjects<>();
		String query = "SELECT \"CY#\", \"Name\" FROM \"City\"";
		ArrayList<HashMap<String, Object>> rs = CoreService.getInstance().query(query);
		for(HashMap<String, Object> it : rs){
			String name = (String)it.get("Name");
			if (name ==null)
				name = "";
			City city = new City(String.valueOf((int)it.get("CY#")), name);
			cities.put(city);
		}
		
		//list of district and connect to city
		Control.getInstance().getLogger().log(Level.INFO, "Fetch list of districts");
		HedspiObjects<District> districts = new HedspiObjects<>();
		query = "SELECT \"DT#\", \"Name\", \"CY#\" FROM \"District\"";
		rs = CoreService.getInstance().query(query);
		for(HashMap<String, Object> it : rs){
			int cy = (int)it.get("CY#");
			City city = cities.get(String.valueOf(cy));
			String name = (String)it.get("Name");
			if (name == null)
				name = "";
			District district = new District(String.valueOf((int)it.get("DT#")), name, city);
			city.getDistricts().put(district);
			districts.put(district);
		}
		
		return new Pair<HedspiObjects<City>, HedspiObjects<District>>(cities, districts);
	}

}
