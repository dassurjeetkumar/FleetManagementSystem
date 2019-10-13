package com.trimax.its.device.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.device.dao.SimCardDao;
import com.trimax.its.device.model.Battery;
import com.trimax.its.device.model.Simcard;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class SimCardAction extends ActionSupport implements Preparable {
	private Simcard simcard;
	private Map<Integer, String> vendorlist;
	SimCardDao daoObject = new SimCardDao();

	public Map<Integer, String> getVendorlist() {
		return vendorlist;
	}

	public void setVendorlist(Map<Integer, String> vendorlist) {
		this.vendorlist = vendorlist;
	}

	public Simcard getSimcard() {
		return simcard;
	}

	public void setSimcard(Simcard simcard) {
		this.simcard = simcard;
	}

	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;

	@Override
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepare() throws Exception {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			SimCardDao simdao = new SimCardDao();
			vendorlist = simdao.getVendorIDName();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] cols = { "", "simcard_id", "vendor.vendor_name","serial_number", "phone_number", "procured_date", "status","notes", "service_plan" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			total = simdao.getTotalRecords(total, request,cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = simdao.getData(total, request,cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

	}

	@SkipValidation
	public String getSimListAjax() {
		return "success";
	}

	@SkipValidation
	public String createSimcard() {
		return "success";
	}

	public String saveSimCard() {
		int id = 0;
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"SimViewAjaxAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y"))
		{
		try {
			if (!daoObject.checkSerilaNumber(simcard,null)) {
				if(!daoObject.checkPhoneNumber(simcard,null)){
					simcard.setCreated_by(request.getSession().getAttribute("userid").toString());
					id = daoObject.saveNewBattery(simcard);
					if(id>0){
						addActionMessage("SimCard Id " + id + " Inserted SuccessFully");
						return "success";
					}else{
						addActionMessage("Error in SimCard Creation Please Try again Later!!");
						return "input";
					}
				}else{
					addActionMessage("SimCard Phone Number already Exists!!");
					return "input";
				}
			} else {
				addActionMessage("SimCard Serial Number already Exists!!");
				return "input";
			}
		} catch (Exception ex) {
			addActionMessage("DataBase Error!!");

		} finally {

		}
		return "success";
		}else{
			return "input";
		}

	}

	@SkipValidation
	public String EditSimCard() {
		Common common = new Common();
		HttpServletRequest request = ServletActionContext.getRequest();
		int batteryID = Integer.parseInt(request.getParameter("value").toString());
		simcard = daoObject.getEditedBattery(batteryID);
		simcard.setProcuredDateString(common.getDateToDatePicker(simcard.getProcured_date()));
		return "success";
	}

	public String saveEditedBattery() {

		HttpServletRequest request = ServletActionContext.getRequest();
		DependencyChecker dc=new DependencyChecker();
		//String status=dc.getStatus(simcard.getSimcard_id(),"simcard");
		//System.out.println("status---"+status);
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"SimViewAjaxAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String sim=Integer.toString(simcard.getSimcard_id());
		if(edit.equalsIgnoreCase("Y")){
		if(daoObject.isSimCardFree(sim))
				{	
		if (!daoObject.checkSerilaNumber(simcard,"update")) {
			if(!daoObject.checkPhoneNumber(simcard, "update")){
				
				simcard.setUpdated_by((Integer.parseInt(request.getSession().getAttribute("userid").toString())));
				simcard.setUpdated_date(new java.util.Date());
				daoObject.updateBattery(simcard, simcard.getSimcard_id());
				
				addActionMessage("Simcard Id " + simcard.getSimcard_id()+ " Updated Successfully");
			}else{
				addActionMessage("Simcard Phone Number Already Exists!!");
				return "input";
			}
		} /*else if (!daoObject.checkSimCard(simcard.getSerial_number())) {
			simcard.setUpdated_by((Integer.parseInt(request.getSession()
					.getAttribute("userid").toString())));
			simcard.setUpdated_date(new java.util.Date());
			daoObject.updateBattery(simcard, simcard.getSimcard_id());
			addActionMessage("Simcard Id " + simcard.getSimcard_id()
					+ " Updated Successfully");
		} */else {
			addActionMessage("Simcard Serial Number Already Exists!!");
			return "input";
		}
		}else{
			addActionError("Simcard Id "+sim+" allocated to Device Id "+ServletActionContext.getRequest().getSession().getAttribute("deviceIdOfSim"));
			ServletActionContext.getRequest().removeAttribute("deviceIdOfSim");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
	}

	@SkipValidation
	public String DeleteSim() {
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"SimViewAjaxAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		try {
			String simacrdId = request.getParameter("value");
			if(daoObject.isSimCardFree(simacrdId)){
				daoObject.releaseSimCard(simacrdId);
				daoObject.deleteSimCard(simacrdId);
				addActionMessage("Simcard Id " + request.getParameter("value")+ " Deleted Successfully");
			}else{
				addActionError("Simcard Id "+simacrdId+" allocated to Device Id "+ServletActionContext.getRequest().getSession().getAttribute("deviceIdOfSim"));
				ServletActionContext.getRequest().removeAttribute("deviceIdOfSim");
			}
		} catch (Exception ex) {

		} finally {
			
		}
		return "success";
		}else{
			return "success";
		}
	}

	public void validate() {
		CommonValidation validation = new CommonValidation();
		Common common = new Common();
		HttpServletRequest request = ServletActionContext.getRequest();
		/*
		 * battery=daoObject.getEditedBattery(battery.getBattery_id());
		 * battery.setProcuredDateString
		 * (common.getDateToDatePicker(battery.getProcured_date()));
		 */
		if (simcard.getVendorDetailsSim().getId() == 0) {
			addFieldError("simcard.vendor", "Please enter  Vendor Name");
		}
		if (simcard.getSerial_number() != null && simcard.getSerial_number().equals("")) {
			addFieldError("simcard.serial_number","Please enter  serial number");
		}
		if (simcard.getSerial_number().trim().length()<14) {
			addFieldError("simcard.serial_number","Serial Number Must More Than 14 Digits");
		}
		if (simcard.getPhone_number() != null && simcard.getPhone_number().equals("")) {
			addFieldError("simcard.phone_number", "Please enter Phone number");
		}
		if (simcard.getPhone_number().length()<10) {
			addFieldError("simcard.phone_number", "Please enter The 10 Digit Phone number");
		}
		if (!validation.isSpecialChar(new String(simcard.getPhone_number()))) {
			addFieldError("simcard.phone_number","Special character for Phone number not allowed");
		}
		if (!validation.isSpecialChar(new String(simcard.getSerial_number()))) {
			addFieldError("simcard.serial_number","Special character for serial number not allowed");
		}
		if(simcard.getSerial_number().contains(" ")){
			addFieldError("simcard.serial_number",	"Space for serial number not allowed");
		}
		 if(simcard.getProcuredDateString()!=null  &&  simcard.getProcuredDateString().equals("") ){
			 addFieldError("simcard.procuredDateString", "Please enter procured date");
			 simcard.setProcuredDateString("blank");
		}
		 

	}
	public void releaseSimCard(int simcardId) {
		String qry = "select sim_vtu_id from sim_vtu where sim_id="+simcardId+" and status ='ACTIVE'";
		Session session=null;
		Transaction txn =null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query =session.createSQLQuery(qry);
			if(query.list().size()>0)
			{
				int sim_vtu_id=Integer.parseInt(query.uniqueResult().toString());
				//Update Sim_vtu
				String simvtuqry="update sim_vtu set status='INACTIVE' where sim_vtu_id="+sim_vtu_id;
				Query queryStr=session.createSQLQuery(simvtuqry);
				txn= HibernateUtil.getSession("").beginTransaction();
				queryStr.executeUpdate();
				txn.commit();
			}
		} catch (Exception ex) {
			txn.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
	}

}
