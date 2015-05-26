package org.studentfolower.data.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.studentfolower.data.physical.Etudiant;
import org.studentfolower.data.physical.Profesor;

public class Groupe {
	private static int counter = 0;
	private static final List<Groupe> lsGr = new ArrayList<Groupe>();

	public static List<Groupe> getAll() {
		return new ArrayList<Groupe>(lsGr);
	}

	public static List<String> getAllString() {
		List<String> lsStr = new ArrayList<String>();
		for (Groupe gr : lsGr) {
			lsStr.add(gr.getName());
		}
		return lsStr;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 9; i++) {
			String tmp = "abcdefghijk";
			for (int j = 0; j < tmp.length(); j++) {
				new Groupe(i + " : " + tmp.charAt(j));
			}
		}
		System.out.println(Groupe.getAll());
		System.out.println(Groupe.getAll().size());
	}

	private final int id;
	private Profesor ref;

	private final List<Etudiant> lsEtu = new ArrayList<Etudiant>();

	private final String name;

	/**
	 * @param name
	 */
	public Groupe(String name) {
		super();
		this.name = name;
		id = counter;
		counter++;
		lsGr.add(this);
	}

	/**
	 * @param ref
	 * @param name
	 */
	public Groupe(String name, Profesor ref) {
		super();
		this.ref = ref;
		this.name = name;
		id = counter;
		counter++;
		lsGr.add(this);
	}

	public boolean add(Etudiant arg0) {
		return lsEtu.add(arg0);
	}

	public boolean contains(Object arg0) {
		return lsEtu.contains(arg0);
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
		Groupe other = (Groupe) obj;
		if (id != other.id) {
			return false;
		}
		if (lsEtu == null) {
			if (other.lsEtu != null) {
				return false;
			}
		} else if (!lsEtu.equals(other.lsEtu)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (ref == null) {
			if (other.ref != null) {
				return false;
			}
		} else if (!ref.equals(other.ref)) {
			return false;
		}
		return true;
	}

	public Etudiant get(int arg0) {
		return lsEtu.get(arg0);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Profesor getRef() {
		return ref;
	}

	public Map<Etudiant, Integer> getStatAbs() {
		return null; // TODO a faire
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((lsEtu == null) ? 0 : lsEtu.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((ref == null) ? 0 : ref.hashCode());
		return result;
	}

	public int indexOf(Object arg0) {
		return lsEtu.indexOf(arg0);
	}

	public Etudiant remove(int arg0) {
		return lsEtu.remove(arg0);
	}

	public boolean remove(Object arg0) {
		return lsEtu.remove(arg0);
	}

	public void setRef(Profesor ref) {
		this.ref = ref;
	}

	public int size() {
		return lsEtu.size();
	}

	@Override
	public String toString() {

		return "Groupe [id=" + id + ", ref=" + ref + ", lsEtu=" + lsEtu
				+ ", name=" + name + "]";
	}
}
