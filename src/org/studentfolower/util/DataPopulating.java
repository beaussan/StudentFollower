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
package org.studentfolower.util;

import java.util.Date;
import java.util.List;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.management.Groupe;
import org.studentfolower.data.physical.Etudiant;
import org.studentfolower.data.physical.Profesor;

public class DataPopulating {

	private static String lsPre = "Nicolas-Jean-Marc-Samuel-Pierre-Arthur";

	private static String lsSur = "Chirak-Moulard-Beaussart-Dupond";

	public static void createAll() {
		createStudents();
		createProfs();
		createGroups();
		createCours();
	}

	@SuppressWarnings("deprecation")
	public static void createCours() {
		// TODO � fair � la main....

		List<Groupe> ls = Groupe.getAll();
		Profesor prof = Profesor.getAll().get(0);
		int cpt = 0;
		for (Groupe groupe : ls) {
			groupe.setRef(prof);
			new Cour(new Date(2015, 6, 2 + (cpt % 2), 8 + (cpt * 2 + 4), 0, 0),
					new Date(2015, 6, 2 + (cpt % 2), 10 + (cpt * 2 + 4), 0, 0),
					groupe, prof);
		}
		new Cour(new Date(2015, 6, 1, 4, 0, 0),
					new Date(2015, 6, 1, 20, 0, 0), ls.get(0), prof);
	}

	public static void createGroups() {
		for (int i = 0; i < 4; i++) {
			new Groupe("N" + i + "P" + i * 2);
		}
		List<Groupe> ls = Groupe.getAll();
		int cpt = 0;
		for (Etudiant etu : Etudiant.getAllEtu()) {
			ls.get(cpt % 4).add(etu);
			cpt++;
		}
	}

	public static void createProfs() {
		for (int i = 0; i < 1; i++) {
			new Profesor("Prof n�" + i + 1);
		}
	}

	public static void createStudents() {
		for (String stPre : lsPre.split("-")) {
			for (String stSur : lsSur.split("-")) {
				new Etudiant(stPre + " " + stSur);
			}
		}
	}

}
