package com.cs336.pkg;

public class Constants {
	//Cannot be instantiated
	private Constants() {}
	
	//Database Connection Information and Table Names
	public static final String CONNECTION_URL = "jdbc:mysql://cs336trainproject.chmy4phgdopi.us-east-1.rds.amazonaws.com:3306/?allowMultipleQueries=true";
	public static final String ADMIN_USERNAME = "admin";
	public static final String ADMIN_PASSWORD = "SuperSecurePassword12345";
	public static final String CUSTOMER_DATABASE = "CustomerMakes";
	
	//Redirect Strings
	public static final String PROJECT_PATH_NAME = "/cs336Sample";
	public static final String LOGIN_PAGE_REDIRECT_URL = PROJECT_PATH_NAME + "/login.jsp";
	public static final String MAIN_PAGE_REDIRECT_URL = PROJECT_PATH_NAME + "/main.jsp";
	
	//Table Headers so we don't have to write them out manually all the time
	public static final String USER_TABLE_HEADERS = 
			"<th>username</th>"+
			"<th>email</th>"+
			"<th>password</th>"+
			"<th>firstName</th>"+
			"<th>lastName</th>"+
			"<th>phone</th>"+
			"<th>zipcode</th>"+
			"<th>address</th>"+
			"<th>city</th>"+
			"<th>state</th>"+
			"<th>resNum</th>";
	
	//HttpSession Attributes
	public static final String HTTP_SESSION_USER_KEY = "user";
	
	


}
