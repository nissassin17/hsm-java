package org.hsm.model.hedspi;

import java.util.ArrayList;
import java.util.HashMap;

public class HedspiObjects<T extends HedspiObject> extends HashMap<String, T> {

	public void putAll(HedspiObjects<T> m) {
		super.putAll(m);
		for (IObjectsContainer<T> it : objectsContainersList) {
			it.addObjects(m.values());
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ID_CODE = "DT#";
	public static final String NAME_CODE = "Name";
	private ArrayList<IObjectsContainer<T>> objectsContainersList;

	private SortedHedspiObjectsComboModel<T> comboBoxModel;
	private SortedHedspiObjectsComboModel<T> comboBoxModel2;
	public SortedHedspiObjectsComboModel<T> getComboBoxModel2() {
		if (comboBoxModel2 == null){
			comboBoxModel2 = new SortedHedspiObjectsComboModel<>();
			registerObjectsContainer(comboBoxModel2);
		}
		return comboBoxModel2;
	}

	private SortedHedspiObjectsListModel<T> listModel;
	private SortedHedspiObjectsListModel<T> listModel2;

	public SortedHedspiObjectsListModel<T> getListModel2() {
		if (listModel2 == null){
			listModel2 = new SortedHedspiObjectsListModel<>();
			registerObjectsContainer(listModel2);
		}
		return listModel2;
	}

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

	@Override
	public void clear() {
		super.clear();
		for (IObjectsContainer<T> it : objectsContainersList)
			it.removeAll();
	}

	public T removeObject(T arg0) {
		T obj = super.remove(arg0.getId());
		if (obj != null)
			for (IObjectsContainer<T> it : objectsContainersList)
				it.removeObject(obj);
		return obj;
	}

	public String getNewId() {
		for (int i = 0; true; i++)
			if (!super.containsKey(String.valueOf(i)))
				return String.valueOf(i);
	}

	public T getDefaultValue() {
		T ret = null;
		for (T it : super.values())
			if (ret == null
					|| it.toString().compareToIgnoreCase(ret.toString()) < 0)
				ret = it;
		return ret;
	}
}
