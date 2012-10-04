package org.hedspi.posgresql.hedspi_student_manager.model.hedspi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortedList<T extends Comparable<Object>> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(T arg0) {
		boolean ret = super.add(arg0);
		Collections.sort(this, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				return o1.toString().compareToIgnoreCase(o2.toString());
			}
		});
		return ret;
	}
}
