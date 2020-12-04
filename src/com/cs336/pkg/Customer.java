package com.cs336.pkg;

public class Customer {
	
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	
	public Customer(
			String username,
			String email,
			String password,
			String firstName,
			String lastName) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	@Override
	public String toString() {
		return "<p>" +
				this.getUsername() + " " +
				this.getEmail() + " " +
				this.getPassword() + " " +
				this.getFirstName() + " " +
				this.getLastName() + " " +
				"</p>";
	}
	
	public String toTableString() {
		return "<tr>"+
				"<td>"+this.getUsername()+"</td>"+
				"<td>"+this.getEmail()+"</td>"+
				"<td>"+this.getPassword()+"</td>"+
				"<td>"+this.getFirstName()+"</td>"+
				"<td>"+this.getLastName()+"</td>"+
				"</tr>";
	}
	
	
}
