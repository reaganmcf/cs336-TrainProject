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
//		closeConnection();
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
		//closeConnection();
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
//		closeConnection();
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
//		closeConnection();	
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
//		closeConnection();
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
			String query = String.format("");
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
//		closeConnection();
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
//		closeConnection();
		return res;
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
//		closeConnection();
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
//		closeConnection();
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
//		closeConnection();
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
//		closeConnection();
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
		//closeConnection();
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
		//closeConnection();
		return 0;
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
//		closeConnection();
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
//		closeConnection();
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
//		closeConnection();
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
						res.getDate("date"),
						res.getInt("originID"),
						res.getInt("destinationID"),
						res.getString("lineName"),
						res.getString("username"),
						res.getBoolean("isChild"),
						res.getBoolean("isSenior"),
						res.getBoolean("isDisabled"));
				ret.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		closeConnection();
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
						res.getDate("date"),
						res.getInt("originID"),
						res.getInt("destinationID"),
						res.getString("lineName"),
						res.getString("username"),
						res.getBoolean("isChild"),
						res.getBoolean("isSenior"),
						res.getBoolean("isDisabled"));
				ret.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		closeConnection();
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
	 * 
	 * @return ArrayList<SpecialSchedule> list of SpecialSchedule objects
	 */
	public ArrayList<SpecialSchedule> SearchSchedules(String originName, String destinationName, java.sql.Date date) {
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
			res = stmt.executeQuery("select s.schedID, s.tID, DATE_ADD(s.startTime, INTERVAL @addOriginTime MINUTE) as start, DATE_ADD(s.startTime, INTERVAL @addDestinationTime MINUTE) as end, @fare as fare from Schedule s where s.lineName = @line AND s.startTime LIKE @selectedDate;");
			//run our query
			ret = new ArrayList<SpecialSchedule>();
		
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
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		closeConnection();
		return ret;
	}
	
	

}
