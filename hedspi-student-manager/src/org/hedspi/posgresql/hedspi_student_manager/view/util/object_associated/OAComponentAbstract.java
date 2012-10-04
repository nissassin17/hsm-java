package org.hedspi.posgresql.hedspi_student_manager.view.util.object_associated;

/**
 * OA stands for Object Associated
 * 
 * @author haidang-ubuntu
 * 
 * @param <T1>
 * @param <T2>
 */
public abstract class OAComponentAbstract<T1, T2> {

	private T2 object;
	private IObjectUpdater<T2, T1> objectUpdater;

	public OAComponentAbstract(IObjectUpdater<T2, T1> objectUpdater) {
		this(objectUpdater, null);
	}

	public OAComponentAbstract(IObjectUpdater<T2, T1> objectUpdater, T2 object) {
		super();
		this.objectUpdater = objectUpdater;
		this.object = object;
	}

	public abstract T1 getValue();

	public T2 getObject() {
		return object;
	}

	public void setObject(T2 object) {
		this.object = object;
		setValue(objectUpdater.getValue(object));
	}

	public abstract void setValue(T1 value);

	public void updateObjectValue() {
		T1 value = getValue();
		if (value != null && objectUpdater != null && object != null)
			objectUpdater.setValue(object, value);
	}
}