package com.zkfront.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.google.gson.Gson;
import com.zk.commonservice.CommonService;
import com.zkfront.Interface.CommonConstant;
import com.zkfront.Interface.EmployeeConstant;
import com.zkfront.model.EmployeeDTO;

public class LoginController extends SelectorComposer implements CommonConstant, EmployeeConstant {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@Wire
	Textbox loginUserID;

	@Wire
	Textbox loginPassword;


	@Listen("onClick=#loginSubmit")
	public void submit() throws JSONException {
		System.err.println("hello world submit");
		String userName = loginUserID.getValue();
		String password = loginPassword.getValue();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", userName);
		jsonObject.put("password", password);
		
		CommonService commonService = new CommonService();
		StringBuilder stringBuilder = commonService.commonServiceForList(LOGIN_API_PATH, jsonObject, POST);
		
		JSONObject jsonResponse = new JSONObject(stringBuilder.toString());
		if (jsonResponse != null && jsonResponse.length() != 0) {
			String response_Status = jsonResponse.getString(RESPONSE_STATUS);
			if (RESPONSE_SUCCESS.equals(response_Status)) {
				LoginAuthenticationSuccess(loginUserID.getValue(), jsonResponse);
			} else {
				Messagebox.show("Username and password are not correct", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
		
		/*
		 * final HashMap<String, Object> map = new HashMap<String, Object>();
		 * map.put("loginUserID", loginUserID.getValue());
		 * map.put("loginPassword", loginPassword.getValue());
		 * Executions.createComponents("dashboard.zul", null, map);
		 */

	}

	
	// Login Authentication Success
	private void LoginAuthenticationSuccess(String loginUserID, JSONObject jsonResponse) throws JSONException {
		Session session = Sessions.getCurrent();
		session.setAttribute(LOGIN_USER_ID, loginUserID);
		session.setAttribute(EMPLOYEE_LIST_STRING, convertJSONArrayToList(jsonResponse));
		Executions.sendRedirect("/zulPages/dashboard.zul");

		// temp needed only
		if (session.hasAttribute(LOGIN_USER_ID)) {
			System.err.println("check session " + session.getAttribute(LOGIN_USER_ID));
		}
	}

	
	// convert an JSONArray To List
	private List<EmployeeDTO> convertJSONArrayToList(JSONObject jsonResponse) throws JSONException {
		List<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		JSONArray jsonArray  = jsonResponse.getJSONArray(EMPLOYEE_STRING);
		
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				EmployeeDTO empDTO = new Gson().fromJson(jsonArray.get(i).toString(), EmployeeDTO.class);
				list.add(empDTO);
			}
		}
		return list;
	}
	
}
