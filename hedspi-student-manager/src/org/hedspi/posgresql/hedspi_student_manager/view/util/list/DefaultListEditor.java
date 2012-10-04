package org.hedspi.posgresql.hedspi_student_manager.view.util.list;

import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;

public class DefaultListEditor extends ListEditor<HedspiObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultListEditor() {
		super();
	}

	public DefaultListEditor(HedspiObjects<HedspiObject> hedspiObjectArg) {
		super(hedspiObjectArg);
	}

	@Override
	public HedspiObject getNewElement(String val) {
		return new HedspiObject(val);
	}

}
