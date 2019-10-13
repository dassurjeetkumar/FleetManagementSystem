package com.trimax.its.vts.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class OTACommandInsertAction extends ActionSupport {

	private String buttonName;

	private Map<Integer, String> divisionlist;

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String checkboxvalues[] = request.getParameterValues("deviceid");
		VtsDataDAO vtsDao = VtsDataDAO.getInstance();
		String inString = "";
		
		buttonName =request.getParameter("buttonName");
//		System.out.println("buttonName"+buttonName);
		if(checkboxvalues.length>0){
		if ("SET_ODOM".equals(buttonName)) {
			// <01725001001RLE0123455F
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				//System.out.println("FENCE VALUE"+request.getParameter("lat_"+checkboxvalues[i]));
				inString = "##set$";
				inString += checkboxvalues[i]+getCommand(buttonName)+"\r\n";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("ADD_GF".equals(buttonName)) {// Code For 28 Related
													// Data...
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				String lat = request.getParameter("lat_"+checkboxvalues[i]);
				String lng = request.getParameter("lng_"+checkboxvalues[i]);
				String id  = request.getParameter("bus_id_"+checkboxvalues[i]);
				String radius=request.getParameter("rad_"+checkboxvalues[i]);
				inString = "##set$";
				inString += checkboxvalues[i]+getCommand(buttonName);
				inString +=1+","+lat+","+lng+","+radius+"\r\n";;
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("FOTA".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "##set$";
				inString += checkboxvalues[i]+getCommand(buttonName)+"\r\n";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("ERRASEGF".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "##set$";
				inString += checkboxvalues[i]+getCommand(buttonName)+"\r\n";
				vtsDao.insertpollSms(inString, checkboxvalues[i]); 
				inString="";
			}
		} else if ("ACCLIMIT".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "##set$";
				inString += checkboxvalues[i]+getCommand(buttonName)+"\r\n";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("DEACCLIMIT".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "##set$";
				inString += checkboxvalues[i]+getCommand(buttonName)+"\r\n";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} 
		else if ("LIST_GF".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "##set$";
				inString += checkboxvalues[i]+getCommand(buttonName)+"\r\n";
//				System.out.println("instring--"+inString+"---"+checkboxvalues[i]);
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		}
		else if ("ADD_DOMAIN".equals(buttonName)){
			for (int i = 0; i < checkboxvalues.length; i++) {
//				System.out.println("button name==="+buttonName);
				// Code To Insert In checkboxvalues
				inString = "##set$";
				inString += checkboxvalues[i]+getCommand(buttonName)+"\r\n";
//				System.out.println("instring--"+inString+"---"+checkboxvalues[i]);
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
		}
		}
		else if ("restart".equals(buttonName)){
			for (int i = 0; i < checkboxvalues.length; i++) {
//				System.out.println("button name==="+buttonName);
				// Code To Insert In checkboxvalues
				inString = "##set$";
//				inString += checkboxvalues[i]+getCommand(buttonName)+"\r\n";
				inString = getCommand(buttonName)+"\r\n";
//				System.out.println("for restart--"+inString+"---"+checkboxvalues[i]);
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
		}
		}
		else if ("Power On".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "<021";
				inString += checkboxvalues[i] + "E11234502315B";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("Speed Violation Event".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "<030";
				inString += checkboxvalues[i] + "E112345029105400001061";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("Speed Normalized".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "<022";
				inString += checkboxvalues[i] + "E1123450301369";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("Read Configuration".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "<022";
				inString += checkboxvalues[i] + "E1123450301369";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("Get Read Configuration".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "<020";
				inString += checkboxvalues[i] + "E21234500168";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("Enable Disable VMU".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "<018";
				inString += checkboxvalues[i] + "E312345162";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("Factory Resetting".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "<017";
				inString += checkboxvalues[i] + "E4123455B";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} else if ("Store Current Configuration".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "<017";
				inString += checkboxvalues[i] + "EF1234557";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		}
		else if ("FOTA".equals(buttonName)) {
			for (int i = 0; i < checkboxvalues.length; i++) {
				// Code To Insert In checkboxvalues
				inString = "<029";
				inString += checkboxvalues[i] + "F112345343456563F5647";
				vtsDao.insertpollSms(inString, checkboxvalues[i]);
				inString="";
			}
		} 
			else if ("frequency_set".equals(buttonName)) {
				for (int i = 0; i < checkboxvalues.length; i++) {
					inString = "set$";
					inString += checkboxvalues[i]+getCommand(buttonName)+"\r\n";
//					inString = getCommand(buttonName)+"\r\n";
//					System.out.println("for freq set--"+inString);
					vtsDao.insertpollSms(inString, checkboxvalues[i]);
					inString="";
							}
		}
		
			else if ("CHECK_CFG".equals(buttonName)) {
				for (int i = 0; i < checkboxvalues.length; i++) {
					inString = getCommand(buttonName)+"\r\n";
					System.out.println("for chk cfg set--"+inString);
					vtsDao.insertpollSms(inString, checkboxvalues[i]);
					inString="";
							}
			}
		
		addActionMessage("Command Fired SuccessFully");
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return super.execute();
		}else{
			addActionMessage("Please Select Device");
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return super.execute();
		}
		
		
		
	}
	public static String getCommand(String key){
		String command= "";
		Session session = null;
		try{
			session=HibernateUtil.getSession("");
			command=session.createSQLQuery("select command from vtu_version_master where version_name='"+key+"' and STATUS='ACTIVE' ")
					.uniqueResult().toString();
		}catch(Exception ex){
			
		}
		return command;
	}

}
