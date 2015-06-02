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
package org.studentfolower.data.physical;

import java.util.ArrayList;
import java.util.List;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.management.Groupe;

public class Profesor extends Humain {

	private static List<Profesor> lsProf = new ArrayList<Profesor>();

	public static List<Profesor> getAll() {
		return lsProf;
	}

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
