package com.zkfront.Service;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.Initiator;

public class AuthenticationSessionService implements Initiator {
	
	String user = null;
	public String getUserDetails() {
		return user;

	}
	
	public AuthenticationSessionService() {
		Session session = Executions.getCurrent().getSession();
		user = (String) session.getAttribute("user");
		System.out.println("user Id=" + user);
	}

	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		Session session = Executions.getCurrent().getSession();
		user = (String) session.getAttribute("user");
		System.out.println("user Id=" + user);

	}

}