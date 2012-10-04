package org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated;

import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class OADateSpinner<T> extends OAComponentAbstract<Date, T> {
	
	JSpinner spinner;
	SpinnerDateModel model;

	public JSpinner getSpinner() {
		return spinner;
	}

	public OADateSpinner(IObjectUpdater<T, Date> objectUpdater, T object) {
		super(objectUpdater, object);
		model = new SpinnerDateModel();
		spinner = new JSpinner(model);
	}

	public OADateSpinner(IObjectUpdater<T, Date> objectUpdater) {
		this(objectUpdater, null);
	}

	@Override
	public Date getValue() {
		return model.getDate();
	}

	@Override
	public void setValue(Date value) {
		model.setValue(value);
	}

}
