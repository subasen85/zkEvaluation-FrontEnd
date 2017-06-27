package com.zkfront.Service;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

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

	/*@Wire
	public Label welcomeId;*/

	
	/*@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception {
	  super.doAfterCompose(comp); 
	  if (comp.getId().equals("loginUserID")) { 
		  welcomeId.setValue("Hello: " +Sessions.getCurrent().getAttribute("user").toString()+"!");
		  Sessions.getCurrent().removeAttribute("loginUserID"); 
	  } 
	}*/
	 

	@Listen("onClick=#loginSubmit")
	public void submit() {
		System.err.println("hello world submit");
		String userName = loginUserID.getValue();
		String password = loginPassword.getValue();

		if (userName.equals(password)) {
			Session session = Sessions.getCurrent();
			session.setAttribute(LOGIN_USER_ID, loginUserID.getValue());
			Executions.sendRedirect("/zulPages/dashboard.zul");

			// temp needed only
			if (session.hasAttribute(LOGIN_USER_ID)) {
				System.err.println("check session " + session.getAttribute(LOGIN_USER_ID));
			}
		} else {
			Messagebox.show("Username and password are not same", "Error", Messagebox.OK, Messagebox.ERROR);
		}

		/*
		 * final HashMap<String, Object> map = new HashMap<String, Object>();
		 * map.put("loginUserID", loginUserID.getValue());
		 * map.put("loginPassword", loginPassword.getValue());
		 * Executions.createComponents("dashboard.zul", null, map);
		 */

	}
}
