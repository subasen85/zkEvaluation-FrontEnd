package com.zkfront.Service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Initiator;

public class MenuController implements Initiator {

	public static final Logger log = LoggerFactory.getLogger(MenuController.class);
	
	/*@Listen("onClick=#newemp")
	public void anotherMethod() {
		System.err.println("Another method ");
		Executions.sendRedirect("/zulPages/addEmployee.zul");
	}*/
	
	public void anotherMethod() {
		System.err.println("Another method ");
		Executions.sendRedirect("/zulPages/addEmployee.zul");
	}

	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		System.err.println("Menu controller started ");
	}

}
