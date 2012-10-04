package org.hedspi.posgresql.hedspi_student_manager.view.util.list;

import org.hedspi.posgresql.hedspi_student_manager.model.contact.address.District;
import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObjects;

public class DistrictListEditor extends ListEditor<District> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DistrictListEditor() {
		super();
	}

	public DistrictListEditor(HedspiObjects<District> hedspiObjectArg) {
		super(hedspiObjectArg);
	}

	@Override
	public District getNewElement(String val) {
		// TODO: not implemented
		return new District(val);
	}

}
