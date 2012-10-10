package org.hsm.model.hedspi;

public class Pair<T0, T1> {
	private T0 object0;
	private T1 object1;

	public Pair(T0 object0, T1 object1) {
		super();
		this.object0 = object0;
		this.object1 = object1;
	}

	public T0 getObject0() {
		return object0;
	}

	public T1 getObject1() {
		return object1;
	}

	public void setObject0(T0 object0) {
		this.object0 = object0;
	}

	public void setObject1(T1 object1) {
		this.object1 = object1;
	}

}
