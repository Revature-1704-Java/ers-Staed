package com.staed.ers.beans;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private int superId;
	private boolean isManager;
	
	public Employee(int id, String firstName, String lastName, String username,
			int superId, boolean isManager) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.superId = superId;
		this.isManager = isManager;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getSuperId() {
		return superId;
	}

	public void setSuperId(int superId) {
		this.superId = superId;
	}
	
	public boolean isManager() {
		return isManager;
	}

	@Override
	public String toString() {
		return "Employee: {id:" + id + ", firstName:" + firstName + ", lastName:" +
				lastName + ", username:" + username + ", super:" + superId +
				", manager:" + isManager + "}";
	}
}
