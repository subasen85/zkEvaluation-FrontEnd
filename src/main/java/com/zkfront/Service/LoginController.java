package com.zkfront.Service;

import java.util.HashMap;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;

public class LoginController extends SelectorComposer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
    Textbox loginUserID;
	
    @Wire
    Textbox loginPassword;
    
	public String method1() {
		System.err.println("hello world");
		return "Hi";
	}
	
	 @Listen("onClick=#loginSubmit")
	    public void submit() {
		 System.err.println("hello world submit");

	        /*final HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("loginUserID", loginUserID.getValue());
	        map.put("loginPassword", loginPassword.getValue());
	        Executions.createComponents("dashboard.zul", null, map);
*/	        
	        Executions.sendRedirect("/dashboard.zul");
	    }
}
