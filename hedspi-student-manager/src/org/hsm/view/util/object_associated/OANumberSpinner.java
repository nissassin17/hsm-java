package org.hsm.view.util.object_associated;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OANumberSpinner<T> extends OAComponentAbstract<String, T> {

	JSpinner spinner;

	SpinnerNumberModel model;

	public OANumberSpinner(IObjectUpdater<T, String> objectUpdater) {
		this(objectUpdater, null);
	}

	public OANumberSpinner(IObjectUpdater<T, String> objectUpdater,
			int defaultValue, int minValue, int maxValue, int step) {
		this(objectUpdater, null, defaultValue, minValue, maxValue, step);
	}

	public OANumberSpinner(IObjectUpdater<T, String> objectUpdater, T object) {
		this(objectUpdater, object, new SpinnerNumberModel());
	}

	public OANumberSpinner(IObjectUpdater<T, String> objectUpdater, T object,
			int defaultValue, int minValue, int maxValue, int step) {
		this(objectUpdater, object, new SpinnerNumberModel(defaultValue,
				minValue, maxValue, step));
	}

	private OANumberSpinner(IObjectUpdater<T, String> objectUpdater, T object,
			SpinnerNumberModel model) {
		super(objectUpdater, object);
		this.model = model;
		spinner = new JSpinner(model);
		model.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateObjectValue();
			}
		});
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	@Override
	public String getValue() {
		return String.valueOf(model.getNumber());
	}

	@Override
	public void setValue(String value) {
		try {
			int val = Integer.parseInt(value);
			model.setValue(val);
		} catch (Exception e) {

		}
	}
}
