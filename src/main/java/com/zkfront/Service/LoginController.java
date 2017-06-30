package com.zkfront.Service;

import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.zk.commonservice.CommonService;
import com.zkfront.Interface.CommonConstant;

public class LoginController extends SelectorComposer implements CommonConstant {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		StringBuilder stringBuilder = commonService.commonServiceForListPostType(LOGIN_API_PATH, jsonObject);
		
		JSONObject jsonResponse = new JSONObject(stringBuilder.toString());
		if (jsonResponse != null && jsonResponse.length() != 0) {
			String response_Status = jsonResponse.getString(RESPONSE_STATUS);
			if (RESPONSE_SUCCESS.equals(response_Status)) {
				LoginAuthenticationSuccess(loginUserID.getValue());
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
	private void LoginAuthenticationSuccess(String loginUserID) {
		Session session = Sessions.getCurrent();
		session.setAttribute(LOGIN_USER_ID, loginUserID);
		Executions.sendRedirect("/zulPages/dashboard.zul");

		// temp needed only
		if (session.hasAttribute(LOGIN_USER_ID)) {
			System.err.println("check session " + session.getAttribute(LOGIN_USER_ID));
		}
	}
	
}
