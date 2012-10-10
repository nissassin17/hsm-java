package org.hsm.view.util.list;

import org.hsm.model.hedspi.HedspiObject;

public class DefaultListEditor extends ListEditor<HedspiObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultListEditor() {
		super();
	}

	@Override
	public HedspiObject getNewObject(String val) {
		return new HedspiObject(val);
	}

	@Override
	public boolean isRemovable(HedspiObject object) {
		return true;
	}

	@Override
	public void beforeRemove(HedspiObject object) {
	}
}
