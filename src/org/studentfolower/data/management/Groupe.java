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
import java.util.HashMap;
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

	public static List<String> getAllStr() {
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
	private Map<Cour, Map<Etudiant, Status>> mapStatus = new HashMap<Cour, Map<Etudiant, Status>>();

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

	public void addCour(Cour cour) {
		mapStatus.put(cour, new HashMap<Etudiant, Status>());
		for (Etudiant etudiant : lsEtu) {
			mapStatus.get(cour).put(etudiant, Status.PRESENT);
		}
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

	public Map<Etudiant, Map<Status, Integer>> getStatAbs() {
		HashMap<Etudiant, Map<Status, Integer>> retVal = new HashMap<Etudiant, Map<Status, Integer>>();
		for (Etudiant etudiant : lsEtu) {
			int pres = 0, abs = 0, retard = 0;
			retVal.put(etudiant, new HashMap<Status, Integer>());
			for (Cour cour : mapStatus.keySet()) {
				switch (mapStatus.get(cour).get(etudiant)) {
				case PRESENT:
					pres++;
					break;
				case ABSENT:
					abs++;
					break;
				case RETARD:
					retard++;
					break;
				default:
					break;
				}
			}
			retVal.get(etudiant).put(Status.PRESENT, pres);
			retVal.get(etudiant).put(Status.RETARD, retard);
			retVal.get(etudiant).put(Status.ABSENT, abs);
		}
		return retVal;
	}

	public Map<Etudiant, Map<Status, Integer>> getStatAbs(Profesor prof) {
		HashMap<Etudiant, Map<Status, Integer>> retVal = new HashMap<Etudiant, Map<Status, Integer>>();
		for (Etudiant etudiant : lsEtu) {
			int pres = 0, abs = 0, retard = 0;
			retVal.put(etudiant, new HashMap<Status, Integer>());
			for (Cour cour : mapStatus.keySet()) {
				if (cour.getProfId() != prof.getId()) {
					continue;
				}
				switch (mapStatus.get(cour).get(etudiant)) {
				case PRESENT:
					pres++;
					break;
				case ABSENT:
					abs++;
					break;
				case RETARD:
					retard++;
					break;
				default:
					break;
				}
			}
			retVal.get(etudiant).put(Status.PRESENT, pres);
			retVal.get(etudiant).put(Status.RETARD, retard);
			retVal.get(etudiant).put(Status.ABSENT, abs);
		}
		return retVal;
	}

	public Status getStatusEtu(Cour cour, Etudiant etu) {
		if (!mapStatus.containsKey(cour)) {
			return null;
		}
		if (!mapStatus.get(cour).containsKey(etu)) {
			return null;
		}
		return mapStatus.get(cour).get(etu);
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

	public void setNextStatusEtu(Cour cour, Etudiant etu) {
		if (!mapStatus.containsKey(cour)) {
			return;
		}
		if (!mapStatus.get(cour).containsKey(etu)) {
			return;
		}
		Status[] tmp = Status.values();
		int nmb = mapStatus.get(cour).get(etu).ordinal();
		mapStatus.get(cour).put(etu, tmp[(nmb + 1) % tmp.length]);
	}

	public void setRef(Profesor ref) {
		this.ref = ref;
	}

	public void setStatusEtu(Cour cour, Etudiant etu, Status sta) {
		if (!mapStatus.containsKey(cour)) {
			return;
		}
		if (!mapStatus.get(cour).containsKey(etu)) {
			return;
		}
		mapStatus.get(cour).put(etu, sta);
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
