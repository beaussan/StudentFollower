package org.studentfolower.data.physical;

import java.util.ArrayList;
import java.util.List;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.management.Groupe;

public class Profesor extends Humain {

	private static List<Profesor> lsProf = new ArrayList<Profesor>();

	public static Profesor getById(int id) {
		return (Profesor) Humain.getAllHumains().get(id);
	}

	private final List<Cour> lsCour = new ArrayList<Cour>();

	public Profesor(String name) {
		super(name);
		lsProf.add(this);
	}

	public Profesor(String name, String email, String number) {
		super(name, email, number);
		lsProf.add(this);
	}

	public Profesor(String name, String email, String postal, String number) {
		super(name, email, postal, number);
		lsProf.add(this);
	}

	public boolean addCour(Cour cour) {
		return lsCour.add(cour);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Profesor other = (Profesor) obj;
		if (lsCour == null) {
			if (other.lsCour != null) {
				return false;
			}
		} else if (!lsCour.equals(other.lsCour)) {
			return false;
		}
		return true;
	}

	public boolean hasCour(Cour cour) {
		return lsCour.contains(cour);
	}

	public boolean hasGroup(Groupe group) {
		if (lsCour.isEmpty()) {
			return false;
		}
		for (Cour cour : lsCour) {
			if (cour.getGrId() == group.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lsCour == null) ? 0 : lsCour.hashCode());
		return result;
	}

}
