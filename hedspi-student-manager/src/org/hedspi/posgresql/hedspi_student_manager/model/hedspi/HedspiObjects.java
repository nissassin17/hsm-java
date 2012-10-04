package org.hedspi.posgresql.hedspi_student_manager.model.hedspi;

import java.util.ArrayList;
import java.util.HashMap;

public class HedspiObjects<T extends HedspiObject> extends HashMap<String, T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ID_CODE = "DT#";
	public static final String NAME_CODE = "Name";
	private ArrayList<IObjectsContainer<T>> objectsContainersList;

	private SortedHedspiObjectsComboModel<T> comboBoxModel;

	// public ArrayList<T> getSortedList(){
	// ArrayList<T> arr = new ArrayList<>();
	// for(T it : super.values())
	// arr.add(it);
	// Collections.sort(arr, new Comparator<T>(){
	//
	// @Override
	// public int compare(T arg0, T arg1) {
	// return arg0.toString().compareTo(arg1.toString());
	// }});
	// return arr;
	// }
	// public ArrayList<T> getSortedListIgnoreCase(){
	// ArrayList<T> arr = new ArrayList<>();
	// for(T it : super.values())
	// arr.add(it);
	// Collections.sort(arr, new Comparator<T>(){
	//
	// @Override
	// public int compare(T arg0, T arg1) {
	// return arg0.toString().compareToIgnoreCase(arg1.toString());
	// }});
	// return arr;
	// }
	private SortedHedspiObjectsListModel<T> listModel;

	public HedspiObjects() {
		super();
		objectsContainersList = new ArrayList<>();
		listModel = new SortedHedspiObjectsListModel<>();
		registerObjectsContainer(listModel);
		comboBoxModel = new SortedHedspiObjectsComboModel<>();
		registerObjectsContainer(comboBoxModel);
	}

	public SortedHedspiObjectsComboModel<T> getComboBoxModel() {
		return comboBoxModel;
	}

	public SortedHedspiObjectsListModel<T> getListModel() {
		return listModel;
	}

	// private void unregisterObjectsContainer(IObjectsContainer<T> container){
	// objectsContainersList.remove(container);
	// }
	@Override
	public T put(String arg0, T arg1) {
		T val = super.put(arg0, arg1);
		if (val != null)
			for (IObjectsContainer<T> it : objectsContainersList)
				it.removeObject(val);
		if (arg1 != null)
			for (IObjectsContainer<T> it : objectsContainersList)
				it.addObject(arg1);
		return val;
	}

	public void put(T value) {
		this.put(value.getId(), value);
	}

	private void registerObjectsContainer(IObjectsContainer<T> container) {
		objectsContainersList.add(container);
		container.removeAll();
		for (T it : super.values())
			container.addObject(it);
	}

	public T removeObject(T arg0) {
		T obj = super.remove(arg0.getId());
		if (obj != null)
			for (IObjectsContainer<T> it : objectsContainersList)
				it.removeObject(obj);
		return obj;
	}
}
