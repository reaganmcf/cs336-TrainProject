package com.cs336.pkg;

public class Constants {
	//Cannot be instantiated
	private Constants() {}
	
	//Database Connection Information and Table Names
	public static final String CONNECTION_URL = "jdbc:mysql://cs336trainproject.chmy4phgdopi.us-east-1.rds.amazonaws.com:3306/?allowMultipleQueries=true";
	public static final String ADMIN_USERNAME = "admin";
	public static final String ADMIN_PASSWORD = "SuperSecurePassword12345";
	public static final String CUSTOMER_DATABASE = "Customer";
	public static final String ADMIN_TABLE = "Admin";
	
	//Redirect Strings
	public static final String PROJECT_PATH_NAME = "/cs336Sample";
	public static final String LOGIN_PAGE_REDIRECT_URL = PROJECT_PATH_NAME + "/login.jsp";
	public static final String INDEX_PATH_REDIRECT_URL = PROJECT_PATH_NAME + "/index.jsp";
	public static final String CUSTOMER_INDEX_REDIRECT_URL = PROJECT_PATH_NAME + "/customer_index.jsp";
	public static final String CUSTOMER_LOGIN_PAGE_REDIRECT_URL = PROJECT_PATH_NAME + "/customer_login_ui.jsp";
	public static final String ADMIN_INDEX_REDIRECT_URL = PROJECT_PATH_NAME + "/admin_index.jsp";
	public static final String ADMIN_LOGIN_PAGE_REDIRECT_URL = PROJECT_PATH_NAME + "/admin_login_ui.jsp";
	public static final String EMPLOYEE_INDEX_REDIRECT_URL = PROJECT_PATH_NAME + "/employee_index.jsp";
	public static final String EMPLOYEE_LOGIN_PAGE_REDIRECT_URL = PROJECT_PATH_NAME + "/employee_login_ui.jsp";
	
	//Table Headers so we don't have to write them out manually all the time
	public static final String CUSTOMER_TABLE_HEADERS = 
			"<th>username</th>"+
			"<th>email</th>"+
			"<th>password</th>"+
			"<th>firstName</th>"+
			"<th>lastName</th>";
	
	public static final String SPECIAL_SCHEDULE_TABLE_HEADERS = 
			"<th>Select</th>"+
			"<th>Schedule ID</th>"+
			"<th>Train ID</th>"+
			"<th>Start Time</th>"+
			"<th>End Time</th>"+
			"<th>Fare</th>";
					
	
	//Get Params
	public static final String LOGIN_ACCOUNT_PARAMETER = "type";
	
	//HttpSession Attributes
	public static final String HTTP_SESSION_CUSTOMER = "customer";
	public static final String HTTP_SESSION_ADMIN = "admin";
	public static final String HTTP_SESSION_EMPLOYEE = "employee";
	


}
