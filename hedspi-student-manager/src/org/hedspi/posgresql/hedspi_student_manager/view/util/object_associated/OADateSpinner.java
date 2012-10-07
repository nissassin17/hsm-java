package org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated;

import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OADateSpinner<T> extends OAComponentAbstract<Date, T> {

	JSpinner spinner;
	SpinnerDateModel model;

	public OADateSpinner(IObjectUpdater<T, Date> objectUpdater) {
		this(objectUpdater, null);
	}

	public OADateSpinner(IObjectUpdater<T, Date> objectUpdater, T object) {
		super(objectUpdater, object);
		model = new SpinnerDateModel();
		spinner = new JSpinner(model);
		model.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				updateObjectValue();
			}
		});
	}

	public JSpinner getSpinner() {
		return spinner;
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
