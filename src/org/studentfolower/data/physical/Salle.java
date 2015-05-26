package org.studentfolower.data.physical;

import java.util.ArrayList;
import java.util.List;

public class Salle {

	private static int counter = 0;
	private static List<Salle> lsSalle = new ArrayList<Salle>();

	public static List<Salle> getAll() {
		return new ArrayList<Salle>(lsSalle);
	}

	public static Salle getSalle(int id) {
		try {
			return lsSalle.get(id);
		} catch (Exception e) {
			return null;
		}
	}

	private final int id;

	private final String name;

	/**
	 * @param name
	 */
	public Salle(String name) {
		super();
		this.name = name;
		id = counter;
		counter++;
		lsSalle.add(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Salle other = (Salle) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Salle [id=" + id + ", name=" + name + "]";
	}

}
