package com.zkfront.Interface;

public interface CommonConstant {

	// Login constant keys
	public static final String LOGIN_USER_ID = "loginUserID";
	public static final String LOGIN_USER_PWD = "loginPassword";
	
	// Response constant keys
	public static final String RESPONSE_STATUS = "status";
	public static final String RESPONSE_MESSAGE = "message";
	public static final String RESPONSE_SUCCESS = "Success";
	public static final String RESPONSE_FAILURE = "Failure";
	
	// Rest service constant keys
	public static final String POST = "POST";
	public static final String GET = "GET";
	
	// API's path constant keys
	public static final String LOGIN_API_PATH = "/login_info/login";
	public static final String EMPLOYEE_ALL_LIST_API_PATH = "/employee/all";
}
