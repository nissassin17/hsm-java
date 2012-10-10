package org.hsm.model.hedspi;

public class HedspiObject implements Comparable<Object> {

	private String id;

	public HedspiObject(String id) {
		this.id = id;
	}

	@Override
	public int compareTo(Object arg0) {
		return this.toString().compareTo(arg0.toString());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HedspiObject other = (HedspiObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}

	public String getSearchString() {
		return toString();
	}

	public String getClassName() {
		return "Hedspi Object";
	}

}
