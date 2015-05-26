package org.studentfolower.data.physical;

import java.util.ArrayList;
import java.util.List;

public class Humain {

	private static int counter = 0;
	private static final List<Humain> lsHum = new ArrayList<Humain>();

	public static List<Humain> getAllHumains() {
		return new ArrayList<Humain>(lsHum);
	}

	public static Humain getHumById(int id) {
		return lsHum.get(id);
	}

	private String name;
	private String email;
	private String postal;
	private String number;
	private final int id;

	/**
	 * @param name
	 */
	public Humain(String name) {
		this.name = name;
		id = counter;
		counter++;
		lsHum.add(this);
	}

	/**
	 * @param name
	 * @param email
	 * @param number
	 */
	public Humain(String name, String email, String number) {
		this.name = name;
		this.email = email;
		this.number = number;
		id = counter;
		counter++;
		lsHum.add(this);
	}

	/**
	 * @param name
	 * @param email
	 * @param postal
	 * @param number
	 */
	public Humain(String name, String email, String postal, String number) {
		this.name = name;
		this.email = email;
		this.postal = postal;
		this.number = number;
		id = counter;
		counter++;
		lsHum.add(this);
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
		Humain other = (Humain) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		if (postal == null) {
			if (other.postal != null) {
				return false;
			}
		} else if (!postal.equals(other.postal)) {
			return false;
		}
		return true;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getPostal() {
		return postal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((postal == null) ? 0 : postal.hashCode());
		return result;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	@Override
	public String toString() {
		return "Humain [name=" + name + ", email=" + email + ", postal="
				+ postal + ", number=" + number + ", id=" + id + "]";
	}

}
