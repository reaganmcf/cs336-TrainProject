package com.cs336.pkg;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date;

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
	 * LoginAdmin
	 * 
	 * Queries the database to see if an admin exists
	 * If it does, then we also set HttpSession.admin with populated instance of Admin
	 * 
	 * @param session the current HttpSession
	 * @param username the user name of the already existing admin
	 * @param password the password of the already existing admin
	 * 
	 * @return boolean whether or not we successfully logged in or not
	 */
	public boolean LoginAdmin(HttpSession session, String username, String password) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[LoginAdmin] Connected to Database");
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
			res = stmt.executeQuery(String.format("SELECT * FROM %s WHERE username = '%s' AND password = '%s'",Constants.ADMIN_TABLE, username, password));
			if(res.next()) {
				System.out.println("[LoginAdmin] Query successfully has data");
				//if there is data in here, create a new CustomerMakes Object and attach it to HttpSession
				Admin admin = new Admin(
						res.getString("username"),
						res.getString("password"));
				System.out.println("[LoginAdmin] HttpSession." + Constants.HTTP_SESSION_ADMIN + " now has " + admin);
				session.setAttribute(Constants.HTTP_SESSION_ADMIN, admin);
				return true;
			} else {
				System.out.println("[LoginCustomer] Query returned no data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;	
	}
	
	/**
	 * LoginCustomer
	 * 
	 * Queries the database to see if a customer exists
	 * If it does, then we also set HttpSession.customer with populated instance of Customer
	 * 
	 * @param session the current HttpSession
	 * @param username the user name of the already existing customer
	 * @param password the password of the already existing customer
	 * 
	 * @return boolean whether or not we successfully logged in or not
	 */
	public boolean LoginCustomer(HttpSession session, String username, String password) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[LoginCustomer] Connected to Database");
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
				System.out.println("[LoginCustomer] Query successfully has data");
				//if there is data in here, create a new CustomerMakes Object and attach it to HttpSession
				Customer customer = new Customer(
						res.getString("username"),
						res.getString("email"),
						res.getString("password"),
						res.getString("firstName"),
						res.getString("lastName"));
				System.out.println("[LoginCustomer] HttpSession." + Constants.HTTP_SESSION_CUSTOMER + " now has " + customer);
				session.setAttribute(Constants.HTTP_SESSION_CUSTOMER, customer);
				return true;
			} else {
				System.out.println("[LoginCustomer] Query returned no data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;	
	}

	
	/**
	 * CreateCustomer
	 * 
	 * Creates a new Customer 
	 * If it does, it also sets HttpSession.customer representing a successful login
	 *
	 * @param session the current HttpSession
	 * @param username the user name of the new user
	 * @param password the password of the new user
	 * 
	 * @return boolean whether or not the action was successful
	 */
	public boolean CreateCustomer(HttpSession session, String username, String password) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[CreateCustomer] Connected to Database");
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
			System.out.println("[CreateCustomer] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			System.out.println(res);
			if(res) {
				System.out.println("[CreateCustomer] Query successfully has data");
				//if there is data in here, create a new CustomerMakes Object and attach it to HttpSession
				Customer newUser = new Customer(username,password);
				System.out.println("[CreateCustomer] HttpSession." + Constants.HTTP_SESSION_CUSTOMER + " now has " + newUser);
				session.setAttribute(Constants.HTTP_SESSION_CUSTOMER, newUser);
			} else {
				System.out.println("[CreateCustomer] Failed to create new CustomerMakes");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return res;
	}
	
	
	/**
	 * SearchSchedules
	 * 
	 * Queries the database to see if a customer exists
	 * If it does, then we also set HttpSession.customer with populated instance of Customer
	 * 
	 * @param session the current HttpSession
	 * @param username the user name of the already existing customer
	 * @param password the password of the already existing customer
	 * 
	 * @return boolean whether or not we successfully logged in or not
	 */
	public ArrayList<SpecialSchedule> SearchSchedules(HttpSession session, String originName, String destinationName, java.sql.Date date, String lineName) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[SearchSchedules] Connected to Database");
		Statement stmt;
		//Create a SQL statement
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ResultSet res;
		ArrayList<SpecialSchedule> ret = null;
		try {
			//use the production database
			stmt.execute("use TrainProject");
			stmt.execute(String.format("set @originID = (select s.stationID from Station s where s.name = '%s')", originName));
			stmt.execute(String.format("set @destinationID = (select s.stationID from Station s where s.name = '%s');", destinationName));
			String temp = String.format("set @selectedDate = CONCAT('%s', '%s');", date.toString(), "%");
			System.out.println(temp);
			stmt.execute(temp);
			stmt.execute(String.format("set @line = '%s';", lineName));
			stmt.execute("set @timePerStop = (select t.travelTimeBetweenStops from TrainLine t where t.lineName = @line) + 2;");
			stmt.execute("set @farePerStop = (select t.farePerStop from TrainLine t where t.lineName = @line);");
			stmt.execute("set @differenceOrigin= ABS(@originID - (select t.originID from TrainLine t where t.lineName = @line));");
			stmt.execute("set @differenceDest = ABS(@destinationID - (select t.originID from TrainLine t where t.lineName = @line));");
			stmt.execute("set @numStops= ABS(@originID-@destinationID);");
			stmt.execute("set @addOriginTime  = @timePerStop * @differenceOrigin;");
			stmt.execute("set @addDestinationTime = @timePerStop * @differenceDest;");
			stmt.execute("set @fare = @farePerStop * @numStops;");
			res = stmt.executeQuery("select s.schedID, s.tID, DATE_ADD(s.startTime, INTERVAL @addOriginTime MINUTE) as start, DATE_ADD(s.startTime, INTERVAL @addDestinationTime MINUTE) as end, @fare as fare from Schedule s where s.lineName = @line AND s.startTime LIKE @selectedDate;");
			//run our query
			ret = new ArrayList<SpecialSchedule>();
			
//			if(res.last()) {
//				System.out.println("[SearchSchedules] Query returned no data");
//			} else {	
				while(res.next()) {
					System.out.println("[SearchSchedules] Query successfully has data");
					//if there is data in here, create a new CustomerMakes Object and attach it to HttpSession
					SpecialSchedule spedspec = new SpecialSchedule(
							res.getInt("schedID"),
							res.getInt("tID"),
							res.getString("start"),
							res.getString("end"),
							res.getFloat("fare"));
					ret.add(spedspec);
				}
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return ret;
	}

}
