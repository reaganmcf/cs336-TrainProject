package com.cs336.pkg;

import java.sql.*;
import java.io.*;
import java.util.*;

import javax.servlet.http.HttpSession;

public class ApplicationDB {
	
	
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
			connection = DriverManager.getConnection(Constants.CONNECTION_URL, Constants.ADMIN_USERNAME, Constants.ADMIN_PASSWORD);
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
	 * CheckLogin
	 * 
	 * Queries the database to see if a user exists
	 * If it does, then we also set HttpSession.user with populated instance of CustomerMakes
	 * 
	 * @param session the current HttpSession
	 * @param username the user name of the already existing user
	 * @param password the password of the already existing user
	 * 
	 * @return boolean whether or not we successfully logged in or not
	 */
	public boolean CheckLogin(HttpSession session, String username, String password) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[CheckLogin] Connected to Database");
		Statement stmt;
		//Create a SQL statement
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		ResultSet res;
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			res = stmt.executeQuery(String.format("SELECT * FROM %s WHERE username = '%s' AND password = '%s'",Constants.CUSTOMER_DATABASE, username, password));
			if(res.next()) {
				System.out.println("[CheckLogin] Query successfully has data");
				//if there is data in here, create a new CustomerMakes Object and attach it to HttpSession
				CustomerMakes newUser = new CustomerMakes(
						res.getString("username"),
						res.getString("email"),
						res.getString("password"),
						res.getString("firstName"),
						res.getString("lastName"),
						res.getInt("phone"),
						res.getInt("zipcode"),
						res.getString("address"),
						res.getString("city"),
						res.getString("state"),
						res.getInt("resNum"));
				System.out.println("[CheckLogin] HttpSession." + Constants.HTTP_SESSION_USER_KEY + " now has " + newUser);
				session.setAttribute(Constants.HTTP_SESSION_USER_KEY, newUser);
				return true;
			} else {
				System.out.println("[CheckLogin] Query returned no data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;	
	}

	
	/**
	 * CreateAccount
	 * 
	 * Creates a new account in the database
	 * If it does, it also sets HttpSession.user representing a successful login
	 *
	 * @param session the current HttpSession
	 * @param username the user name of the new user
	 * @param password the password of the new user
	 * 
	 * @return boolean whether or not the action was successful
	 */
	public boolean CreateAccount(HttpSession session, String username, String password) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[CreateAccount] Connected to Database");
		Statement stmt;
		//Create a SQL statement
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		boolean res = false;
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("INSERT INTO %s (username, password) VALUES ('%s', '%s')", Constants.CUSTOMER_DATABASE, username, password);
			System.out.println("[CreateAccount] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			System.out.println(res);
			if(res) {
				System.out.println("[CreateAccount] Query successfully has data");
				//if there is data in here, create a new CustomerMakes Object and attach it to HttpSession
				CustomerMakes newUser = new CustomerMakes(username,password);
				System.out.println("[CreateAccount] HttpSession." + Constants.HTTP_SESSION_USER_KEY + " now has " + newUser);
				session.setAttribute(Constants.HTTP_SESSION_USER_KEY, newUser);
			} else {
				System.out.println("[CreateAccount] Failed to create new CustomerMakes");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return res;
	}
}
