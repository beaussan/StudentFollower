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

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.studentfolower.data.management.Groupe;
import org.studentfolower.util.PersonUtil;

public class Etudiant extends Humain {

	private static List<Etudiant> lsEtu = new ArrayList<Etudiant>();

	public static List<Etudiant> getAllEtu() {
		return new ArrayList<Etudiant>(lsEtu);
	}

	public static Etudiant getById(int id) {
		return (Etudiant) Humain.getAllHumains().get(id);
	}

	private Groupe group;
	private BufferedImage img;

	public Etudiant(String name) {
		super(name);
		lsEtu.add(this);
		setEmail(name + "@etudiant.univ-lille1.fr");
	}

	public Etudiant(String name, String email, String number) {
		super(name, email, number);
		lsEtu.add(this);
	}

	public Etudiant(String name, String email, String postal, String number) {
		super(name, email, postal, number);
		lsEtu.add(this);
	}

	public Groupe getGroup() {
		return group;
	}

	public BufferedImage getImg() {
		if (img == null) {
			return PersonUtil.getImage(getEmail() + "-" + getName(), 255);
		}
		return img;
	}

	public void setGroup(Groupe group) {
		this.group = group;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

}
