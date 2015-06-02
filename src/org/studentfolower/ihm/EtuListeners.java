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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.studentfolower.data.management.Cour;
import org.studentfolower.data.physical.Etudiant;

public class EtuListeners implements MouseListener {
	private static final long msTime = 300;
	private Etudiant etu;

	private Cour cour;

	private long timeClick;

	public EtuListeners(Etudiant etu, Cour cour) {
		this.etu = etu;
		this.cour = cour;
	}

	private void longClick() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		timeClick = System.currentTimeMillis();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		long timeEnd = System.currentTimeMillis();
		if (timeEnd - timeClick > msTime) {
			longClick();
		} else {
			singleClick();
		}
	}

	private void singleClick() {
		cour.setNextStatus(etu);
	}

}

