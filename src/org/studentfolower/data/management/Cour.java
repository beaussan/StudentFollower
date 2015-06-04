/*
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.studentfolower.data.management;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.studentfolower.data.physical.Etudiant;
import org.studentfolower.data.physical.Humain;
import org.studentfolower.data.physical.Profesor;

public class Cour {
	private static int counter = 0;
	private static Map<Jour, Map<Profesor, List<Cour>>> courParJour = new HashMap<Jour, Map<Profesor, List<Cour>>>();
	private static List<Cour> lsCour = new ArrayList<Cour>();

	public static List<Cour> getAll() {
		return new ArrayList<Cour>(lsCour);
	}

	public static List<Cour> getAllBy(Groupe gr) {
		List<Cour> retVal = new ArrayList<>();
		for (Cour cour : lsCour) {
			if (cour.gr.equals(gr)) {
				retVal.add(cour);
			}
		}
		return retVal;
	}

	public static Map<Cour, String> getAllStrStr() {
		Map<Cour, String> lsStr = new HashMap<Cour, String>();
		for (Cour cr : lsCour) {
			lsStr.put(cr, cr.toGoodStr());
		}
		return lsStr;
	}

	public static List<Cour> getBy(Date date, Profesor prof) {
		Jour j = new Jour(date);
		if (!courParJour.containsKey(j)) {
			return null;
		}
		if (!courParJour.get(j).containsKey(prof)) {
			return null;
		}
		return courParJour.get(j).get(prof);
	}

	public static Map<Cour, String> getBy(Groupe gr) {
		Map<Cour, String> retVal = new HashMap<>();
		for (Cour cour : lsCour) {
			if (cour.gr.equals(gr)) {
				retVal.put(cour, cour.toGoodStr());
			}
		}
		return retVal;
	}

	public static List<Cour> getBy(Jour j, Profesor prof) {

		if (!courParJour.containsKey(j)) {
			return null;
		}
		if (!courParJour.get(j).containsKey(prof)) {
			return null;
		}
		return courParJour.get(j).get(prof);
	}

	public static Cour getCour(int id) {
		try {
			return lsCour.get(id);
		} catch (Exception e) {
			return null;
		}
	}

	private final int id;

	private final Jour jCour;

	private final Date heurDebut;
	private final Date heurFin;

	private final Groupe gr;
	private final Profesor prof;

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
		jCour = new Jour(heurDebut);

		if (!courParJour.containsKey(jCour)) {
			courParJour.put(jCour, new HashMap<Profesor, List<Cour>>());
		}
		if (!courParJour.get(jCour).containsKey(prof)) {
			courParJour.get(jCour).put(prof, new ArrayList<Cour>());
		}
		courParJour.get(jCour).get(prof).add(this);

		gr.addCour(this);

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

	public Status getStatuEtu(Etudiant etu) {
		return gr.getStatusEtu(this, etu);
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

	public void setAbs(Etudiant etu) {
		gr.setStatusEtu(this, etu, Status.ABSENT);
	}

	public void setAbs(int idEtu) {
		setAbs((Etudiant) Humain.getAllHumains().get(idEtu));
	}

	public void setNextStatus(Etudiant etu) {
		gr.setNextStatusEtu(this, etu);
		switch (gr.getStatusEtu(this, etu)) {
		case ABSENT:

			break;

		case RETARD:
			break;
		case PRESENT:

			break;
		default:
			break;
		}
	}

	public void setPresent(Etudiant etu) {
		gr.setStatusEtu(this, etu, Status.PRESENT);
	}

	public void setRetard(Etudiant etu) {
		gr.setStatusEtu(this, etu, Status.RETARD);
	}

	public String toGoodStr() {
		return heurDebut.getHours() + ":" + heurDebut.getMinutes() + " "
				+ heurFin.getHours() + ":" + heurFin.getMinutes();
	}

	@Override
	public String toString() {
		return "Cour [id=" + id + ", heurDebut=" + heurDebut + ", heurFin="
				+ heurFin + ", gr=" + gr + ", prof=" + prof + "]";
	}

}
