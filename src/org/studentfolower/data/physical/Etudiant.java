package org.studentfolower.data.physical;

import java.util.ArrayList;
import java.util.List;

import org.studentfolower.data.management.Groupe;

public class Etudiant extends Humain {

	private static List<Etudiant> lsEtu = new ArrayList<Etudiant>();

	public static List<Etudiant> getAllEtu() {
		return new ArrayList<Etudiant>(lsEtu);
	}

	public static Etudiant getById(int id) {
		return (Etudiant) Humain.getAllHumains().get(id);
	}

	private Groupe group;

	public Etudiant(String name) {
		super(name);
		lsEtu.add(this);
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

	public void setGroup(Groupe group) {
		this.group = group;
	}

}
