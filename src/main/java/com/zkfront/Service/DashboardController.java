package com.zkfront.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;

import com.zk.commonservice.CommonService;
import com.zkfront.Interface.CommonConstant;
import com.zkfront.Interface.EmployeeConstant;
import com.zkfront.model.EmployeeDTO;

public class DashboardController extends SelectorComposer implements CommonConstant, EmployeeConstant, Initiator, RowRenderer<EmployeeDTO> {
	
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
	EmployeeDTO empDTO;
	
	
	// Final Submit button from dashboard
	@Listen("onClick=#submitButton")
	public void finalSubmit() throws Exception {
		System.err.println("Final check final button is success ");
		
		/*String employeeName = empName.getValue();
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
		}*/
		
		/*empDTO.setEmpName(employeeName);
		empDTO.setEmpDesignation(employeeDesgn);
		empDTO.setEmpDOB(dob);
		empDTO.setGender(gender.getId());*/
//		empDTO.setEmpType(empTypevalue);
		
		
		CommonService commonService = new CommonService();
		StringBuilder stringBuilder = commonService.commonServiceForList(EMPLOYEE_ALL_LIST_API_PATH, null, GET);
		
		JSONObject jsonResponse = new JSONObject(stringBuilder.toString());
		if (jsonResponse != null && jsonResponse.length() != 0) {
			String response_Status = jsonResponse.getString(RESPONSE_STATUS);
			if (RESPONSE_SUCCESS.equals(response_Status)) {
				System.err.println("Employee Query list work successfully");
				/*List<EmployeeDTO> empList = (List<EmployeeDTO>) jsonResponse.get(EMPLOYEE_STRING);*/
				/*System.err.println(empList.toString());*/
			} else {
				Messagebox.show("Username and password are not correct", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
		
	}


	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		Session session = Sessions.getCurrent();
		if (session.hasAttribute(EMPLOYEE_LIST_STRING)) {
			System.err.println("check session " + session.getAttribute(LOGIN_USER_ID));
			List<EmployeeDTO> empList = (List<EmployeeDTO>) session.getAttribute(EMPLOYEE_LIST_STRING);
		}
	}


	@Override
	public void render(Row row, EmployeeDTO empDTO, int index) throws Exception {
		/*System.err.println("Render success ");
		row.appendChild(new Label("label1"));
        row.appendChild(new Label("label2"));*/		
	}
	
}
