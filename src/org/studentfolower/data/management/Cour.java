package org.studentfolower.data.management;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.studentfolower.data.physical.Etudiant;
import org.studentfolower.data.physical.Humain;
import org.studentfolower.data.physical.Profesor;

public class Cour {
	private static int counter = 0;
	private static List<Cour> lsCour = new ArrayList<Cour>();

	public static List<Cour> getAll() {
		return new ArrayList<Cour>(lsCour);
	}

	public static Cour getCour(int id) {
		try {
			return lsCour.get(id);
		} catch (Exception e) {
			return null;
		}
	}

	private final int id;

	private final Date heurDebut;
	private final Date heurFin;

	private final Groupe gr;
	private final Profesor prof;

	private final List<Etudiant> retards = new ArrayList<Etudiant>();
	private final List<Etudiant> abs = new ArrayList<Etudiant>();

	/**
	 * @param heurDebut
	 * @param heurFin
	 * @param gr
	 * @param prof
	 */
	public Cour(Date heurDebut, Date heurFin, Groupe gr, Profesor prof) {
		super();
		this.heurDebut = heurDebut;
		this.heurFin = heurFin;
		this.gr = gr;
		this.prof = prof;
		id = counter;
		counter++;
		lsCour.add(this);
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
		Cour other = (Cour) obj;
		if (gr == null) {
			if (other.gr != null) {
				return false;
			}
		} else if (!gr.equals(other.gr)) {
			return false;
		}
		if (heurDebut == null) {
			if (other.heurDebut != null) {
				return false;
			}
		} else if (!heurDebut.equals(other.heurDebut)) {
			return false;
		}
		if (heurFin == null) {
			if (other.heurFin != null) {
				return false;
			}
		} else if (!heurFin.equals(other.heurFin)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (prof == null) {
			if (other.prof != null) {
				return false;
			}
		} else if (!prof.equals(other.prof)) {
			return false;
		}
		return true;
	}

	public Groupe getGr() {
		return gr;
	}

	public int getGrId() {
		return gr.getId();
	}

	public Date getHeurDebut() {
		return heurDebut;
	}

	public Date getHeurFin() {
		return heurFin;
	}

	public int getId() {
		return id;
	}

	public Profesor getProf() {
		return prof;
	}

	public int getProfId() {
		return prof.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gr == null) ? 0 : gr.hashCode());
		result = prime * result
				+ ((heurDebut == null) ? 0 : heurDebut.hashCode());
		result = prime * result + ((heurFin == null) ? 0 : heurFin.hashCode());
		result = prime * result + id;
		result = prime * result + ((prof == null) ? 0 : prof.hashCode());
		return result;
	}

	public boolean setAbs(Etudiant etu) {
		return abs.add(etu);
	}

	public boolean setAbs(int idEtu) {
		return abs.add((Etudiant) Humain.getAllHumains().get(idEtu));
	}

	public boolean setRetard(Etudiant etu) {
		return retards.add(etu);
	}

	@Override
	public String toString() {
		return "Cour [id=" + id + ", heurDebut=" + heurDebut + ", heurFin="
				+ heurFin + ", gr=" + gr + ", prof=" + prof + "]";
	}

}
