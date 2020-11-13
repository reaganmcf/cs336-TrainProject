package com.cs336.pkg;

public class CustomerMakes {
	
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private int phone;
	private int zipcode;
	private String address;
	private String city;
	private String state;
	private int resNum;
	public CustomerMakes(
			String username,
			String email,
			String password,
			String firstName,
			String lastName,
			int phone,
			int zipcode,
			String address,
			String city,
			String state,
			int resNum) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.zipcode = zipcode;
		this.address = address;
		this.city = city;
		this.state = state;
		this.resNum = resNum;
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
	
	public int getPhone() {
		return this.phone;
	}
	
	public int getZipcode() {
		return this.zipcode;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getState() {
		return this.state;
	}
	
	public int getResNum() {
		return this.resNum;
	}
	
	@Override
	public String toString() {
		return "{username: 'this.getUsername()', password: 'this.getPassword()'}";
	}
	
	public String toTableString() {
		return "<tr>"+
				"<td>"+this.getUsername()+"</td>"+
				"<td>"+this.getEmail()+"</td>"+
				"<td>"+this.getPassword()+"</td>"+
				"<td>"+this.getFirstName()+"</td>"+
				"<td>"+this.getLastName()+"</td>"+
				"<td>"+this.getPhone()+"</td>"+
				"<td>"+this.getZipcode()+"</td>"+
				"<td>"+this.getAddress()+"</td>"+
				"<td>"+this.getCity()+"</td>"+
				"<td>"+this.getState()+"</td>"+
				"<td>"+this.getResNum()+"</td>"+
				"</tr>";
	}
	
	
}
