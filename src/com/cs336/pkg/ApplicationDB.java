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
			connection = null;
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
		closeConnection();
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
			res = stmt.executeQuery(String.format("SELECT * FROM %s WHERE username = '%s' AND password = '%s'",Constants.CUSTOMER_TABLE, username, password));
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
		closeConnection();
		return false;	
	}
	
	
	
	/**
	 * LoginEmployee
	 * 
	 * Queries the database to see if an employee exists
	 * If it does, then we also set HttpSession.employee with populated instance of Employee
	 * 
	 * @param session the current HttpSession
	 * @param username the user name of the already existing customer
	 * @param password the password of the already existing customer
	 * 
	 * @return boolean whether or not we successfully logged in or not
	 */
	public boolean LoginEmployee(HttpSession session, String username, String password) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[LoginEmployee] Connected to Database");
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
			res = stmt.executeQuery(String.format("SELECT * FROM %s WHERE username = '%s' AND password = '%s'",Constants.EMPLOYEE_TABLE, username, password));
			if(res.next()) {
				System.out.println("[LoginEmployee] Query successfully has data");
				//if there is data in here, create a new CustomerMakes Object and attach it to HttpSession
				Employee employee = new Employee(
						res.getString("SSN"),
						res.getString("username"),
						res.getString("password"),
						res.getString("firstName"),
						res.getString("lastName"));
				System.out.println("[LoginEmployee] HttpSession." + Constants.HTTP_SESSION_EMPLOYEE + " now has " + employee.getSSN());
				session.setAttribute(Constants.HTTP_SESSION_EMPLOYEE, employee);
				return true;
			} else {
				System.out.println("[LoginEmployee] Query returned no data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return false;	
	}

	
	
	/**
	 * CreateCustomer
	 * 
	 * Creates a new Customer 
	 * Returns the status of the operation (success or failure)
	 * 
	 * @param String username
	 * @param String password
	 * @param String email 
	 * @param String firstName
	 * @param String lastName
	 * 
	 * @return boolean whether or not the action was successful
	 */
	public boolean CreateCustomer(String username, String password, String email, String firstName, String lastName) {
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
			String query = String.format("INSERT INTO %s (username, password, email, firstName, lastName) VALUES ('%s', '%s', '%s', '%s', '%s');", Constants.CUSTOMER_TABLE, username, password, email, firstName, lastName);
			System.out.println("[CreateCustomer] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			System.out.println(res);
			if(res) {
				System.out.println("[CreateCustomer] Query successfully has data");
			} else {
				System.out.println("[CreateCustomer] Failed to create new Customer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();	
		return res;
	}

	
	
	/**
	 * CreateEmployee
	 * 
	 * Creates a new Employee 
	 * @param String SSN
	 * @param String username
	 * @param String password 
	 * @param String firstName
	 * @param String lastName 
	 * 
	 * @return boolean whether or not the action was successful
	 */
	public boolean CreateEmployee(String SSN, String username, String password, String firstName, String lastName) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[CreateEmployee] Connected to Database");
		Statement stmt;
		//Create a SQL statement
		if(SSN.length() != 11) return false;
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
			String query = String.format("INSERT INTO %s (SSN, username, password, firstName, lastName) VALUES ('%s', '%s', '%s', '%s', '%s');", Constants.EMPLOYEE_TABLE, SSN, username, password, firstName, lastName);
			System.out.println("[CreateEmployee] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			System.out.println(res);
			if(res) {
				System.out.println("[CreateEmployee] Query successfully has data");
			} else {
				System.out.println("[CreateEmployee] Failed to create new Employee");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	
	
	/**
	 * DeleteEmployee
	 * 
	 * Deletes an Employee given the SSN
	 * @param String SSN
	 * 
	 * @return boolean whether or not the action was successful
	 */
	public boolean DeleteEmployee(String SSN) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[DeleteEmployee] Connected to Database");
		Statement stmt;
		//Create a SQL statement
		if(SSN.length() != 11) return false;
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
			String query = String.format("delete from Employee e where '%s' = e.SSN", SSN);
			System.out.println("[DeleteEmployee] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			System.out.println(res);
			if(res) {
				System.out.println("[DeleteEmployee] Query successfully has data");
			} else {
				System.out.println("[DeleteEmployee] Failed to delete employee");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	

	/**
	 * DeleteSchedule
	 * 
	 * Deletes a Schedule given the schedID
	 * @param String schedID
	 * 
	 * @return boolean whether or not the action was successful
	 */
	public boolean DeleteSchedule(String schedID) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[DeleteSchedule] Connected to Database");
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
			String query = String.format("delete from Schedule where schedID = '%s'", schedID);
			System.out.println("[DeleteSchedule] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			System.out.println(res);
			if(res) {
				System.out.println("[DeleteSchedule] Query successfully has data");
			} else {
				System.out.println("[DeleteSchedule] Failed to delete Schedule");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	
	
	
	/**
	 * EditEmployee
	 * 
	 * Edits an employee with new information
	 * 
	 * @param String SSN
	 * @param String username
	 * @param String password 
	 * @param String firstName
	 * @param String lastName 
	 * 
	 * @return boolean whether or not the action was successful
	 */
	public boolean EditEmployee(String SSN, String username, String password, String firstName, String lastName) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[EditEmployee] Connected to Database");
		Statement stmt;
		//Create a SQL statement
		if(SSN.length() != 11) return false;
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
			String query = String.format("UPDATE %s SET SSN='%s', username='%s', password='%s', firstName='%s', lastName='%s' WHERE SSN = '%s'",
						Constants.EMPLOYEE_TABLE, SSN, username, password, firstName, lastName, SSN);
			System.out.println("[EditEmployee] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			if(res) {
				System.out.println("[EditEmployee] Query successfully has data");
			} else {
				System.out.println("[EditEmployee] Failed to edit employee");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	
	
	/**
	 * EditSchedule
	 * 
	 * Edits a Schedule with new information
	 * 
	 * @return boolean whether or not the action was successful
	 */
	public boolean EditSchedule(String schedID, String date, String tID) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[EditSchedule] Connected to Database");
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
			
			System.out.println(date);
			
			String query = String.format("update %s set startTime = '%s', tID = '%s' where schedID = '%s'", Constants.SCHEDULE_TABLE, date, tID, schedID);
			System.out.println("[EditSchedule] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			if(res) {
				System.out.println("[EditSchedule] Query successfully has data");
			} else {
				System.out.println("[EditSchedule] Failed to edit schedule");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	
	
	/**
	 * CreateSchedule
	 * 
	 * Creates a new Schedule
	 * 
	 * @return boolean whether or not the action was successful
	 */
	public boolean CreateSchedule(String trainLine, String date, String tID) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[CreateSchedule] Connected to Database");
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
			stmt.execute(String.format("set @lineName = '%s'", trainLine));
			stmt.execute(String.format("set @startDateTime = '%s'", date));
			stmt.execute(String.format("set @scheduleID = (select MAX(s.schedID)+1 from Schedule s)"));
			stmt.execute(String.format("set @originID = (select t.originID from TrainLine t where t.lineName = @lineName)"));
			stmt.execute(String.format("set @destinationID = (select t.destinationID from TrainLine t where t.lineName = @lineName)"));
			stmt.execute(String.format("set @tID = %s", tID));
			String query = String.format("INSERT INTO Schedule VALUES (@scheduleID, @originID, @destinationID, @lineName, @startDateTime, @tID );");
			
			System.out.println("[CreateSchedule] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			if(res) {
				System.out.println("[CreateSchedule] Query successfully has data");
			} else {
				System.out.println("[CreateSchedule] Failed to Create schedule");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	
	
	/**
	 * GetStopsOnSchedule
	 * 
	 * gets the names of all stops on a schedule and returns a list of them
	 * 
	 * @param originStationName name of the origin station
	 * @param destinationStationName name of the destination station
	 * 
	 * @return ArrayList<String> list of all the names of stops
	 */
	public ArrayList<String> GetStopsOnSchedule(String originStationName, String destinationStationName) {
		//If we haven't established a connection, establish one
		ArrayList<String> ret = new ArrayList<String>();
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return ret;
		System.out.println("[GetStopsOnSchedule] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ret;
		}
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			stmt.execute(String.format("set @originID = (select s.stationID from Station s where s.name = '%s')", originStationName));
			stmt.execute(String.format("set @destinationID = (select s.stationID from Station s where s.name = '%s')", destinationStationName));
		
			String query = String.format("select s.name as Stops from Station s where ((@originID < s.stationID AND @destinationID > s.stationID) OR (@originID  > s.stationID AND @destinationID < s.stationID))");
			System.out.println("[GetStopsOnSchedule] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetStopsOnSchedule] Query successfully has data");
			while(res.next()) {
				ret.add(res.getString("Stops"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	
	/**
	 * getEmployees
	 * 
	 * gets all info about employees and returns a list of Employee objects
	 * 
	 * @return ArrayList<Employee> list of Employee objects
	 */
	public ArrayList<Employee> getEmployees() {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[getEmployees] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<Employee> ret = new ArrayList<Employee>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM %s", Constants.EMPLOYEE_TABLE);
			System.out.println("[getEmployees] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[getEmployees] Query successfully has data");
			while(res.next()) {
				Employee employee = new Employee(
						res.getString("SSN"),
						res.getString("username"),
						res.getString("password"),
						res.getString("firstName"),
						res.getString("lastName"));
				ret.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * GetQuestions
	 * 
	 * gets all QA objects in the database and returns a list of QA objects
	 * 
	 * @return ArrayList<QA> list of QA objects
	 */
	public ArrayList<QA> GetQuestions() {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[GetQuestions] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<QA> ret = new ArrayList<QA>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM %s", Constants.QA_TABLE);
			System.out.println("[GetQuestions] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetQuestions] Query successfully has data");
			while(res.next()) {
				System.out.println(res.getString("answer"));
				QA qa = new QA(
						res.getString("question"),
						res.getString("answer"));
				ret.add(qa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * GetReservationsByUsername
	 * 
	 * Gets all the reservations for a particular user
	 * 
	 * @return ArrayList<Reservation> list of Reservation objects
	 */
	public ArrayList<Reservation> GetReservationsByUsername(String username) {
		ArrayList<Reservation> ret = new ArrayList<Reservation>();
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return ret;
		System.out.println("[GetReservationsByUsername] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM Reservation r where r.date>NOW() AND r.username = '%s'", username);
			System.out.println("[GetReservationsByUsername] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetReservationsByUsername] Query successfully has data");
			while(res.next()) {
				Reservation r = new Reservation(
					res.getInt("resNum"),
					res.getFloat("totalFare"),
					res.getString("passenger"),
					res.getString("date"),
					res.getInt("originID"),
					res.getInt("destinationID"),
					res.getString("lineName"),
					res.getString("username"),
					res.getBoolean("isChild"),
					res.getBoolean("isSenior"),
					res.getBoolean("isDisabled"),
					res.getInt("schedID"));
				ret.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	


	/**
	 * GetPastReservationsByUsername
	 * 
	 * Gets all past the reservations for a particular user
	 * 
	 * @return ArrayList<Reservation> list of Reservation objects
	 */
	public ArrayList<Reservation> GetPastReservationsByUsername(String username) {
		ArrayList<Reservation> ret = new ArrayList<Reservation>();
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return ret;
		System.out.println("[GetPastReservationsByUsername] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM Reservation r where r.date<NOW() AND r.username = '%s'", username);
			System.out.println("[GetPastReservationsByUsername] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetPastReservationsByUsername] Query successfully has data");
			while(res.next()) {
				Reservation r = new Reservation(
					res.getInt("resNum"),
					res.getFloat("totalFare"),
					res.getString("passenger"),
					res.getString("date"),
					res.getInt("originID"),
					res.getInt("destinationID"),
					res.getString("lineName"),
					res.getString("username"),
					res.getBoolean("isChild"),
					res.getBoolean("isSenior"),
					res.getBoolean("isDisabled"),
					res.getInt("schedID"));
				ret.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * GetTrains
	 * 
	 * gets all Train objects in the database and returns a list of Train objects
	 * 
	 * @return ArrayList<Train> list of Train objects
	 */
	public ArrayList<Train> GetTrains() {
		ArrayList<Train> ret = new ArrayList<Train>();
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return ret;
		System.out.println("[GetTrains] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ret;
		}
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM %s", Constants.TRAIN_TABLE);
			System.out.println("[GetQuestions] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetQuestions] Query successfully has data");
			while(res.next()) {
				Train t = new Train(
						res.getInt("tID"),
						res.getInt("numSeats"),
						res.getInt("numCars"));
				ret.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * GetQuestionsByKeyword
	 * 
	 * gets all QA objects in the database and returns a list of QA objects
	 * 
	 * @return ArrayList<QA> list of QA objects
	 */
	public ArrayList<QA> GetQuestionsByKeyword(String keyword) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[GetQuestionsByKeyword] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<QA> ret = new ArrayList<QA>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			stmt.execute(String.format("set @keyword = CONCAT('%s', '%s');", "%" + keyword + "%", "%"));
			String query = String.format("SELECT * FROM %s q WHERE q.question like @keyword;", Constants.QA_TABLE);
			System.out.println("[GetQuestionsByKeyword] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetQuestionsByKeyword] Query successfully has data");
			while(res.next()) {
				System.out.println(res.getString("question"));
				QA qa = new QA(
						res.getString("question"),
						res.getString("answer"));
				ret.add(qa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * SendQuestion
	 * 
	 * Insert new question into database
	 * 
	 * @return boolean status of the operation
	 */
	public boolean SendQuestion(String question) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[SendQuestion] Connected to Database");
		Statement stmt;
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
			String query = String.format("INSERT INTO %s (question) VALUES ('%s');", Constants.QA_TABLE, question);
			System.out.println("[SendQuestion] running : " + query);
			
			res = stmt.executeUpdate(query) > 0;
			if(res) {
				System.out.println("[SendQuestion] Query successfully has data");
			} else {
				System.out.println("[SendQuestion] Query failed to insert new question into table");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	
	
	/**
	 * CancelReservation
	 * 
	 * Cancel a reservation given a resNum
	 * 
	 * @return boolean status of the operation
	 */
	public boolean CancelReservation(int resNum) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[CancelReservation] Connected to Database");
		Statement stmt;
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
			String query = String.format("DELETE from Reservation r where '%s' = r.resNum", resNum);
			System.out.println("[CancelReservation] running : " + query);
			
			res = stmt.executeUpdate(query) > 0;
			if(res) {
				System.out.println("[CancelReservation] Query successfully has data");
			} else {
				System.out.println("[CancelReservation] Query failed to insert new question into table");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	
	
	/**
	 * AnswerQuestion
	 * 
	 * Answers a given question in the QA table
	 * 
	 * @return boolean status of the operation
	 */
	public boolean AnswerQuestion(String question, String answer) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[AnswerQuestion] Connected to Database");
		Statement stmt;
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
			String query = String.format("UPDATE %s SET answer = '%s' WHERE question = '%s';", Constants.QA_TABLE, answer, question);
			System.out.println("[AnswerQuestion] running : " + query);
			
			res = stmt.executeUpdate(query) > 0;
			if(res) {
				System.out.println("[AnswerQuestion] Query successfully has data");
			} else {
				System.out.println("[AnswerQuestion] Query failed to update question with new answer in the table");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	
	
	/**
	 * MakeReservation
	 * 
	 * Makes reservation for a customer
	 * 
	 * @return boolean status of the operation
	 */
	public boolean MakeReservation(float fare, int isRoundTrip, int isDisabled, int isSenior, int isChild, String passengerName, String startDate, int originID, int destinationID, String lineName, String username, int schedID) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return false;
		System.out.println("[MakeReservation] Connected to Database");
		Statement stmt;
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
			stmt.execute("set @resID = (select MAX(resNum) + 1 from Reservation)");
			stmt.execute(String.format("set @Fare = '%f'", fare));
			stmt.execute(String.format("set @Fare = (IF(%s , 2 * @Fare, @Fare))", isRoundTrip));
			String query = String.format("set @Fare = (IF(%s , 0.5 * @Fare, IF(%s , .65*@Fare, IF(%s , .75*@Fare, @Fare))))", isDisabled, isSenior, isChild);
			System.out.println(query);
			stmt.execute(query);
			query = String.format("INSERT INTO Reservation VALUES (@resID, @Fare,'%s', '%s', %s, %s, '%s', '%s',%s, %s, %s, %s);",
						passengerName, startDate, originID, destinationID, lineName, username, isChild, isSenior, isDisabled, schedID);
			System.out.println("[MakeReservation] running : " + query);
			res = stmt.executeUpdate(query) > 0;
			if(res) {
				System.out.println("[MakeReservation] Query successfully has data");
			} else {
				System.out.println("[MakeReservation] Query failed to make a reservation");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return res;
	}
	
	
	
	/**
	 * GetMonthlySales
	 * 
	 * Gets total sales for a given month
	 * 
	 * @return boolean status of the operation
	 */
	public float GetMonthlySales(String year, String month) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return 0;
		System.out.println("[GetMonthlySales] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("select sum(r.totalFare) as totalSum from %s r where r.date like '%s';", Constants.RESERVATION_TABLE, year + "-" + month + "%");
			System.out.println("[GetMonthlySales] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			if(res.next()) {
				System.out.println("[GetMonthlySales] Query successfully has data");
				return res.getFloat("totalSum");
			} else {
				System.out.println("[GetMonthlySales] Query failed to update question with new answer in the table");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return 0;
	}
	
	
	
	/**
	 * BestCustomer
	 * 
	 * Gets best customer
	 * 
	 * @return ArrayList<String> username and amount of money spent returned as a list
	 */
	public ArrayList<String> BestCustomer() {
		ArrayList<String> ret = new ArrayList<String>();
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return ret;
		System.out.println("[BestCustomer] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ret;
		}
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT username, SUM(totalFare) as magnitude from %s group by username order by magnitude desc limit 1", Constants.RESERVATION_TABLE);
			System.out.println("[BestCustomer] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			if(res.next()) {
				System.out.println("[BestCustomer] Query successfully has data");
				ret.add(res.getString("username"));
				ret.add(res.getString("magnitude"));
			} else {
				System.out.println("[BestCustomer] Query failed to retrieve best customer");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	/**
	 * getTopActiveLines
	 * 
	 * gets top 5 most active train lines
	 * 
	 * @return ArrayList<ArrayList<String>> row strings
	 */
	public ArrayList<ArrayList<String>> getTopActiveLines() {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return ret;
		System.out.println("[getTopActiveLines] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ret;
		}
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT lineName, COUNT(*) as magnitude from %s group by lineName order by magnitude desc limit 5", Constants.RESERVATION_TABLE);
			System.out.println("[getTopActiveLines] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			while(res.next()) {
				ArrayList<String> t = new ArrayList<String>();
				t.add(res.getString("lineName"));
				t.add(res.getString("magnitude"));
				ret.add(t);
			}
			System.out.println("[getTopActiveLines] Query successfully has data");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	
	
	/**
	 * TotalRevenueByCustomer
	 * 
	 * groups revenue by customer
	 * 
	 * @return ArrayList<ArrayList<String>> list of rows of the result table
	 */
	public ArrayList<ArrayList<String>> TotalRevenueByCustomer() {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[TotalRevenueByCustomer] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("select sum(r.totalFare) as total, c.firstName as firstName, c.lastName as lastName, c.username as username from %s r, %s c where c.username = r.username group by c.username", Constants.RESERVATION_TABLE, Constants.CUSTOMER_TABLE);
			System.out.println("[TotalRevenueByCustomer] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			while(res.next()) {
				ArrayList<String> t = new ArrayList<String>();
				t.add(res.getString("total"));
				t.add(res.getString("firstName"));
				t.add(res.getString("lastName"));
				t.add(res.getString("username"));
				ret.add(t);
			}
			System.out.println("[TotalRevenueByCustomer] Query successfully has data");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * TotalRevenueByTransitLine
	 * 
	 * groups revenue by train line
	 * 
	 * @return ArrayList<ArrayList<String>> list of rows of the result table
	 */
	public ArrayList<ArrayList<String>> TotalRevenueByTransitLine() {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[TotalRevenueByTransitLine] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("select sum(r.totalFare) as total, t.lineName as lineName from %s r, %s t where t.lineName = r.lineName group by t.lineName", Constants.RESERVATION_TABLE, Constants.TRAIN_LINE_TABLE);
			System.out.println("[TotalRevenueByTransitLine] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			while(res.next()) {
				ArrayList<String> t = new ArrayList<String>();
				t.add(res.getString("total"));
				t.add(res.getString("lineName"));
				ret.add(t);
			}
			System.out.println("[TotalRevenueByTransitLine] Query successfully has data");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	
	
	/**
	 * GetStations
	 * 
	 * gets all Station objects in the database and returns a list of Station objects
	 * 
	 * @return ArrayList<Station> list of Station objects
	 */
	public ArrayList<Station> GetStations() {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[GetStations] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<Station> ret = new ArrayList<Station>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM %s", Constants.STATION_TABLE);
			System.out.println("[GetStations] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetStations] Query successfully has data");
			while(res.next()) {
				Station station = new Station(
						res.getInt("stationID"),
						res.getString("name"),
						res.getString("city"),
						res.getString("state"));
				ret.add(station);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * GetSchedulesOnStationName
	 * 
	 * gets all Schedule objects in the database based on the train statioin and returns a list of Schedule objects
	 * 
	 * @return ArrayList<Schedule> list of Station objects
	 */
	public ArrayList<Schedule> GetSchedulesOnStationName(String stationName) {
		ArrayList<Schedule> ret = new ArrayList<Schedule>();
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return ret;
		System.out.println("[GetSchedulesOnStationName] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ret;
		}
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			stmt.execute(String.format("set @stationID = (select s.stationID from Station s where s.name = '%s')", stationName));
			stmt.execute(String.format("set @id = CONCAT(%s, @stationID, %s)", "'%,'", "',%'")); 
			String query = String.format("select * from Schedule s where s.lineName IN (select t.lineName from TrainLine t where t.listStops like @id)");
			System.out.println("[GetSchedulesOnStationName] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetSchedulesOnStationName] Query successfully has data");
			while(res.next()) {
				Schedule schedule = new Schedule(
						res.getInt("schedID"),
						res.getInt("originID"),
						res.getInt("destinationID"),
						res.getString("lineName"),
						res.getString("startTime"),
						res.getInt("tID"));
				ret.add(schedule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	/**
	 * GetTrainLines
	 * 
	 * gets all TrainLine objects in the database and returns a list of TrainLine objects
	 * 
	 * @return ArrayList<TrainLine> list of TrainLine objects
	 */
	public ArrayList<TrainLine> GetTrainLines() {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[GetTrainLines] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<TrainLine> ret = new ArrayList<TrainLine>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM %s", Constants.TRAIN_LINE_TABLE);
			System.out.println("[GetTrainLines] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetTrainLines] Query successfully has data");
			while(res.next()) {
				TrainLine trainline = new TrainLine(
						res.getString("lineName"),
						res.getInt("travelTimeBetweenStops"),
						res.getInt("totalTime"),
						res.getString("listStops"),
						res.getFloat("farePerStop"),
						res.getInt("originID"),
						res.getInt("destinationID"));
				ret.add(trainline);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	/**
	 * SearchCustomersByTrainLineAndDate
	 * 
	 * gets usernames of all customers that meet the query and returns a list of them
	 * 
	 * @return ArrayList<String> usernames of all users
	 */
	public ArrayList<String> SearchCustomersByTrainLineAndDate(String lineName, java.sql.Date date) {
		ArrayList<String> ret = new ArrayList<String>();
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return ret;
		System.out.println("[SearchCustomersByTrainLineAndDate] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ret;
		}
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			stmt.execute(String.format("set @inputLine = '%s'", lineName));
			stmt.execute(String.format("set @inputDate = CONCAT('%s', '%s')", date, "%"));
			String query = String.format("SELECT r.username from %s r where r.lineName = @inputLine and r.date like @inputDate group by r.username", Constants.RESERVATION_TABLE);
			System.out.println("[SearchCustomersByTrainLineAndDate] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[SearchCustomersByTrainLineAndDate] Query successfully has data");
			while(res.next()) {
				ret.add(res.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * GetCustomers
	 * 
	 * gets all Customers and returns list of Customer objects
	 * 
	 * @return ArrayList<Customer> list of Customer objects
	 */
	public ArrayList<Customer> GetCustomers() {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[GetCustomers] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<Customer> ret = new ArrayList<Customer>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM %s", Constants.CUSTOMER_TABLE);
			System.out.println("[GetCustomers] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetCustomers] Query successfully has data");
			while(res.next()) {
				Customer customer = new Customer(
						res.getString("username"),
						res.getString("password"),
						res.getString("email"),
						res.getString("firstName"),
						res.getString("lastName"));
				ret.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * SearchReservationsByUsername
	 * 
	 * gets all Reservation objects by user name in the database and returns a list of SearchReservationsByCustomerName objects
	 * 
	 * @return ArrayList<Reservation> list of TrainLine objects
	 */
	public ArrayList<Reservation> SearchReservationsByUsername(String username) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[SearchReservationsByUsername] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<Reservation> ret = new ArrayList<Reservation>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM %s r where r.username = '%s'", Constants.RESERVATION_TABLE, username);
			System.out.println("[SearchReservationsByUsername] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[SearchReservationsByUsername] Query successfully has data");
			while(res.next()) {
				Reservation reservation = new Reservation(
						res.getInt("resNum"),
						res.getFloat("totalFare"),
						res.getString("passenger"),
						res.getString("date"),
						res.getInt("originID"),
						res.getInt("destinationID"),
						res.getString("lineName"),
						res.getString("username"),
						res.getBoolean("isChild"),
						res.getBoolean("isSenior"),
						res.getBoolean("isDisabled"),
						res.getInt("schedID"));
				ret.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * SearchReservationsByTransitLine
	 * 
	 * gets all Reservation objects by transit line in the database and returns a list of SearchReservationsByCustomerName objects
	 * 
	 * @return ArrayList<Reservation> list of TrainLine objects
	 */
	public ArrayList<Reservation> SearchReservationsByTransitLine(String transit_line) {
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return null;
		System.out.println("[SearchReservationsByTransitLine] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ArrayList<Reservation> ret = new ArrayList<Reservation>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("SELECT * FROM %s r where r.lineName = '%s'", Constants.RESERVATION_TABLE, transit_line);
			System.out.println("[SearchReservationsByTransitLine] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[SearchReservationsByTransitLine] Query successfully has data");
			while(res.next()) {
				Reservation reservation = new Reservation(
						res.getInt("resNum"),
						res.getFloat("totalFare"),
						res.getString("passenger"),
						res.getString("date"),
						res.getInt("originID"),
						res.getInt("destinationID"),
						res.getString("lineName"),
						res.getString("username"),
						res.getBoolean("isChild"),
						res.getBoolean("isSenior"),
						res.getBoolean("isDisabled"),
						res.getInt("schedID"));
				ret.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * GetSchedules
	 * 
	 * gets all the schedules in the database and returns a list of Schedule objects
	 * 
	 * @return ArrayList<Schedule> list of Schedule objects
	 */
	public ArrayList<Schedule> GetSchedules() {
		ArrayList<Schedule> ret = new ArrayList<Schedule>();
		//If we haven't established a connection, establish one
		if(connection == null) connection = getConnection();
		//If we failed to establish a connection, return false
		if(connection == null) return ret;
		System.out.println("[GetSchedules] Connected to Database");
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ret;
		}
		try {
			//use the production database
			stmt.execute("use TrainProject");
			//run our query
			String query = String.format("select * from %s", Constants.SCHEDULE_TABLE);
			System.out.println("[GetSchedules] running : " + query);
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println("[GetSchedules] Query successfully has data");
			while(res.next()) {
				Schedule sched = new Schedule(
						res.getInt("schedID"),
						res.getInt("originID"),
						res.getInt("destinationID"),
						res.getString("lineName"),
						res.getString("startTime"),
						res.getInt("tID"));
				ret.add(sched);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	
	
	/**
	 * SearchSchedules
	 * 
	 * Returns the result of searching for all schedules given the parameters
	 * 
	 * @param String originName
	 * @param String destinationName
	 * @param java.sql.Date date
	 * @param String lineName
	 * @param int sortToggle index means the sorting method (0 - default, 1 - sort by desc, 2 - sort by asc)
	 * @return ArrayList<SpecialSchedule> list of SpecialSchedule objects
	 */
	public ArrayList<SpecialSchedule> SearchSchedules(String originName, String destinationName, java.sql.Date date, int sort_toggle) {
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
		ArrayList<SpecialSchedule> ret = new ArrayList<SpecialSchedule>();
		try {
			//use the production database
			stmt.execute("use TrainProject");
			stmt.execute(String.format("set @originID = (select s.stationID from Station s where s.name = '%s')", originName));
			stmt.execute(String.format("set @destinationID = (select s.stationID from Station s where s.name = '%s');", destinationName));
			stmt.execute(String.format("set @stops = CONCAT('%s', @originID, '%s', @destinationID, '%s')", "%,", ",%,", ",%")); 	
			stmt.execute(String.format("set @selectedDate = CONCAT('%s', '%s');", date.toString(), "%"));
			stmt.execute(String.format("set @line = (select lineName from TrainLine where listStops LIKE @stops)"));
			stmt.execute("set @timePerStop = (select t.travelTimeBetweenStops from TrainLine t where t.lineName = @line) + 2;");
			stmt.execute("set @farePerStop = (select t.farePerStop from TrainLine t where t.lineName = @line);");
			stmt.execute("set @differenceOrigin= ABS(@originID - (select t.originID from TrainLine t where t.lineName = @line));");
			stmt.execute("set @differenceDest = ABS(@destinationID - (select t.originID from TrainLine t where t.lineName = @line));");
			stmt.execute("set @numStops= ABS(@originID-@destinationID);");
			stmt.execute("set @addOriginTime  = @timePerStop * @differenceOrigin;");
			stmt.execute("set @addDestinationTime = @timePerStop * @differenceDest;");
			stmt.execute("set @fare = @farePerStop * @numStops;");
			String sortSuffix = "";
			if(sort_toggle == 1) {
				sortSuffix = "order by start asc";
			} else if(sort_toggle == 2) {
				sortSuffix = "order by start desc";
			}
			res = stmt.executeQuery("select s.schedID, s.originID, s.lineName, s.destinationID, s.tID, DATE_ADD(s.startTime, INTERVAL @addOriginTime MINUTE) as start, DATE_ADD(s.startTime, INTERVAL @addDestinationTime MINUTE) as end, @fare as fare from Schedule s where s.lineName = @line AND s.startTime LIKE @selectedDate " + sortSuffix);
			//run our query
			ret = new ArrayList<SpecialSchedule>();
		
			while(res.next()) {
				System.out.println("[SearchSchedules] Query successfully has data");
				//if there is data in here, create a new CustomerMakes Object and attach it to HttpSession
				SpecialSchedule spedspec = new SpecialSchedule(
						res.getInt("schedID"),
						res.getInt("originID"),
						res.getInt("destinationID"),
						res.getInt("tID"),
						res.getString("start"),
						res.getString("end"),
						res.getString("lineName"),
						res.getFloat("fare"));
				ret.add(spedspec);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return ret;
	}
	
	

}
