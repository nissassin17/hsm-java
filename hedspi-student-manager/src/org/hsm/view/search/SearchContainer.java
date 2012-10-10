package org.hsm.view.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.hsm.model.hedspi.HedspiObject;
import org.hsm.model.hedspi.HedspiObjects;
import org.hsm.view.util.list.SortBox;

public class SearchContainer {

	private ArrayList<HedspiObject> list;

	public SearchContainer() {
		list = new ArrayList<>();
	}

	public void addObjects(HedspiObjects<? extends HedspiObject> obj) {
		for (HedspiObject it : obj.values())
			list.add(it);
	}

	public HedspiObject[] search(final String searchString, int threshold,
			final boolean isCase) {
		int nResults = Math.max(Math.min(list.size(), threshold), 0);
		HedspiObject[] ret = new HedspiObject[nResults];

		Collections.sort(list, new Comparator<HedspiObject>() {

			@Override
			public int compare(HedspiObject o1, HedspiObject o2) {
				return SortBox.smartCompare(o1.getSearchString(),
						o2.getSearchString(), searchString, isCase);
			}
		});

		for (int i = 0; i < nResults; i++)
			ret[i] = list.get(i);

		return ret;
	}
}
