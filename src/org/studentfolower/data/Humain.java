package org.studentfolower.data;

public class Humain {
	
	private String name;
	private String email;
	private String postal;
	private String number;
	private final int id;
	private static int counter = 0;

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
	}

	public int getId() {
		return id;
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
	}

	/**
	 * @param name
	 */
	public Humain(String name) {
		this.name = name;
		id = counter;
		counter++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
