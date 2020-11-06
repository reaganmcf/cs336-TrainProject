package com.cs336.pkg;

import java.sql.*;
import java.io.*;
import java.util.*;

public class ApplicationDB {
	private final String connectionUrl = "jdbc:mysql://cs336trainproject.chmy4phgdopi.us-east-1.rds.amazonaws.com:3306/?allowMultipleQueries=true";
	private final String adminUsername = "admin";
	private final String adminPassword = "SuperSecurePassword12345";
	
	
	private static final ApplicationDB instance = new ApplicationDB();
	private Connection connection = null;
	
	private ApplicationDB() {}
	public static ApplicationDB getInstance() {
		return instance;
	}

	private Connection getConnection(){
		
		//Create a connection string
		
		
		try {
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//Create a connection to your DB
			connection = DriverManager.getConnection(connectionUrl, adminUsername, adminPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * BeerDrinkTestCommad
	 * 
	 * Retrieve the data from the test database given to us in the setup. This can be used in place of the real
	 * commands in order to test our server is working as expected
	 * 
	 */
	public ResultSet BeerDrinkTestCommand() {
		if(connection == null) connection = getConnection();
		if(connection == null) return null;
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		ResultSet res;
		try {
			//use the test database
			stmt.execute("use BarBeerDrinkerSample");
			//run our query
			res = stmt.executeQuery("select * from beers");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return res;
	}
	
	/**
	 * CreateAccount
	 * 
	 * Creates a new account in the database
	 * 
	 * @param username the user name of the new user
	 * @param password the password of the new user
	 * 
	 * @return NULL if the action failed, ResultSet if the action succeeded
	 */
	public ResultSet CreateAccount(String username, String password) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		
		//Create a SQL statement
		try {
			Statement stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Get the selected radio button from the index.jsp
		
		return null;
		
	}
}
