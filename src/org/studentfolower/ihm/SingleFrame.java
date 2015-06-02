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
package org.studentfolower.ihm;

import javax.swing.JFrame;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.physical.Etudiant;

public class SingleFrame extends JFrame {

	private Etudiant etu;

	private Cour cour;

	private final EtuListeners etList;

	public SingleFrame(Etudiant etu, Cour cour) {
		super();
		this.etu = etu;
		this.cour = cour;
		etList = new EtuListeners(etu, cour);
		addMouseListener(etList);
	}

}
