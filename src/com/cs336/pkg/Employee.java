package com.cs336.pkg;

public class Employee {
	private String SSN;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public Employee(
			String SSN,
			String username,
			String password,
			String firstName,
			String lastName) {
		this.SSN = SSN;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getSSN() {
		return SSN;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	@Override 
	public String toString() {
		return "<p>" +
				this.getSSN() + " " +
				this.getUsername() + " " +
				this.getPassword() + " " +
				this.getFirstName() + " " +
				this.getLastName() + " " +
				"</p>";
	}

}

