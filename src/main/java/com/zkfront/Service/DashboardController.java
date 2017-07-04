package com.zkfront.Service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

import com.zkfront.model.EmployeeInfo;

public class DashboardController extends SelectorComposer {
	
	public static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	
	@Wire
	private Textbox empName;
	
	@Wire
	private Textbox empDesignation;
	
	@Wire
	private Datebox empdob;
	
	@Wire
	private Radiogroup genderValue;
	
	@Wire
	private Checkbox Permanent;
	
	@Wire
	private Checkbox Temporary;
	
	@Wire
	private Checkbox Contractual;
	
	@Wire
	private EmployeeInfo empInfo;
	
	
	// Final Submit button from dashboard
	@Listen("onClick=#submitButton")
	public void finalSubmit() throws Exception {
		System.err.println("Final check final button is success ");
		
		String employeeName = empName.getValue();
		String employeeDesgn = empDesignation.getValue();
		
		Date dob = empdob.getValue();
		Radio gender = genderValue.getSelectedItem();
		System.err.println(gender);
		
		String empTypevalue = "";
		boolean commaAccept = false;
		if (!Permanent.getValue().toString().isEmpty()) {
			empTypevalue = Permanent.getId();
			commaAccept = true;
		}
		
		if (!Temporary.getValue().toString().isEmpty()) {
			empTypevalue = (commaAccept == true ? "," : "") + Temporary.getId();
			if (commaAccept == false) 
					commaAccept = true;
		}
		if (!Contractual.getValue().toString().isEmpty()) {
			empTypevalue = (commaAccept == true ? "," : "") + Contractual.getId();
		}
		
		empInfo.setEmpName(employeeName);
		empInfo.setEmpDesignation(employeeDesgn);
		empInfo.setEmpDOB(dob);
		empInfo.setGender(gender.getId());
		empInfo.setEmpType(empTypevalue);
		
		
	}
}
