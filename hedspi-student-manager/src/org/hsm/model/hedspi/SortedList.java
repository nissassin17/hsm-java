package org.hsm.model.hedspi;

import java.util.ArrayList;
import java.util.Collections;

public class SortedList<T extends HedspiObject> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(T arg0) {
		if (super.contains(arg0))
			return false;
		boolean ret = super.add(arg0);
		Collections.sort(this);
		return ret;
	}
}
