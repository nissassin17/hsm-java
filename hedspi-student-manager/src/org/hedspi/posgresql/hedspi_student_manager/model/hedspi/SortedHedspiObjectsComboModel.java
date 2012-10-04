package org.hedspi.posgresql.hedspi_student_manager.model.hedspi;

import javax.swing.DefaultComboBoxModel;

public class SortedHedspiObjectsComboModel<T extends Comparable<Object>>
		extends DefaultComboBoxModel<T> implements IObjectsContainer<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SortedList<T> sortedList;

	public SortedHedspiObjectsComboModel() {
		sortedList = new SortedList<>();
	}

	@Override
	public void addObject(T obj) {
		sortedList.add(obj);
		updateModel();
	}

	@Override
	public void removeAll() {
		sortedList.clear();
		updateModel();
	}

	@Override
	public void removeObject(T obj) {
		sortedList.remove(obj);
		updateModel();
	}

	private void updateModel() {
		super.removeAllElements();
		for (T it : sortedList)
			super.addElement(it);
	}

}
