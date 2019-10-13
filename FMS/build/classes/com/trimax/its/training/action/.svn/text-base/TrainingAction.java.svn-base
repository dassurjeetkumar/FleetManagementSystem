package com.trimax.its.training.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.training.dao.TrainingDao;
import com.trimax.its.training.model.Training;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;


public class TrainingAction extends ActionSupport implements Preparable{
	Training training;
	String insertstatus;
	String deletestatus;
	String updatestatus;
	Common common = new Common();
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public String getInsertstatus() {
		return insertstatus;
	}
	public void setInsertstatus(String insertstatus) {
		this.insertstatus = insertstatus;
	}
	public String getDeletestatus() {
		return deletestatus;
	}
	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
	}
	public String getUpdatestatus() {
		return updatestatus;
	}
	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	private String stringtrainingdate;
	public String getStringtrainingdate() {
		return stringtrainingdate;
	}
	public void setStringtrainingdate(String stringtrainingdate) {
		this.stringtrainingdate = stringtrainingdate;
	}
	private Map<Integer, String> employeelist;
	private Map<Integer, String> trainingtypelist;
	public Map<Integer, String> getEmployeelist() {
		return employeelist;
	}
	public void setEmployeelist(Map<Integer, String> employeelist) {
		this.employeelist = employeelist;
	}
	public Map<Integer, String> getTrainingtypelist() {
		return trainingtypelist;
	}
	public void setTrainingtypelist(Map<Integer, String> trainingtypelist) {
		this.trainingtypelist = trainingtypelist;
	}
	
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SkipValidation
	public String viewTraining() {
		return "success";
	}
	
	@SkipValidation
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

		try {
			
			TrainingDao trainingdao = new TrainingDao();

			String[] cols = { "", "training_id", "tra.training_type_name","training_date",
					"emp.employee_id","emp.employee_name","duration","status","remarks"};
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
			total = trainingdao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = trainingdao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception e) {
			// ex.printStackTrace();
			//System.out.println(e);
		} finally {

		}
	}
	
	@SkipValidation
	public String createtraining() {
		TrainingDao trainingdao = new TrainingDao();
		employeelist=trainingdao.getEmployeeList();
		trainingtypelist=trainingdao.getTrainingTypeList();
		return "success";
	}
	
	public String createTrainingAction()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "traininglist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		TrainingDao trainingdao = new TrainingDao();
		employeelist=trainingdao.getEmployeeList();
		trainingtypelist=trainingdao.getTrainingTypeList();
		int id=0;

		if(!trainingdao.getDuplicate(training.getTrainingtype().getTraining_type_id(),training.getStringtrainingdate(),training.getEmployee().getEmployee_id(),training.getStatus()))
		{
		try{
			training.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			training.setTraining_date(common.getDateFromDatePicker(training.getStringtrainingdate()));
			id=trainingdao.saveTraining(training);
			addActionMessage("Training Id "+id+ " Created Successfully");
			setInsertstatus("success");
		}catch(Exception e)
		{
			addActionMessage("DataBase Error!!");
			setInsertstatus("fail");
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}
		finally{
			
		}
		}else{
			setInsertstatus("duplicate");
			addActionMessage("Training Details already Exist!!");
			employeelist=trainingdao.getEmployeeList();
			trainingtypelist=trainingdao.getTrainingTypeList();
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
	}
	
	@SkipValidation
	public String deleteTraining()
	{
		TrainingDao dao=new TrainingDao();
		Training training=new Training();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "traininglist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		training.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			dao.deleteTrainingDetails(training,Integer.parseInt(request.getParameter("deltrainingid")));
			setDeletestatus("success");
		}catch(Exception e)
		{
			//System.out.println(ex);
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			return "input";
		}finally{			
			addActionMessage("Training Id "+request.getParameter("deltrainingid")+ " Deleted SuccessFully");
		}
		return "success";
		}else{
			return "success";
		}
	}
	
	@SkipValidation
	public String getEditDetails() {
		TrainingDao trainingdao = new TrainingDao();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		training = trainingdao.getEditedTraining(Integer.parseInt(request
				.getParameter("edittrainingid")));
		employeelist=trainingdao.getEmployeeList();
		trainingtypelist=trainingdao.getTrainingTypeList();	
		training.setStringtrainingdate(common.getDateToDatePicker(training.getTraining_date()));
		//System.out.println(vehicledefect.getDefectdate()+"          "+vehicledefect.getDate());
		return "success";
		
	}
	public String updateTrainingDetails(){
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();	
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "traininglist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		TrainingDao trainingdao = new TrainingDao();
		int useriddetails=training.getTraining_id();
		boolean flagdetails=true;
		training.setTraining_date(common.getDateFromDatePicker(training.getStringtrainingdate()));
		employeelist=trainingdao.getEmployeeList();
		trainingtypelist=trainingdao.getTrainingTypeList();	
		flagdetails=trainingdao.getUpdateDuplicate(training.getTrainingtype().getTraining_type_id(),training.getStringtrainingdate(),training.getEmployee().getEmployee_id(),training.getTraining_id(),training.getStatus());
		if(flagdetails==true)
		{		
		 int i=trainingdao.updateTrainingDetails(useriddetails, training);	
		addActionMessage("Training Id "+useriddetails+ " Updated SuccessFully");		
		setUpdatestatus("success");
		flag="success";
		}
		else{
			setInsertstatus("duplicate");
			addActionError("Training Details already Exist!!");
			return "input";
		}		
		return flag;
		}else{
			return "input";
		}
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
		TrainingDao trainingdao = new TrainingDao();
		
		if(training.getDuration().trim().equals("") || training.getDuration().equals(" "))
		{
			addFieldError("duration", "Training Duration is Required");
			employeelist=trainingdao.getEmployeeList();
			trainingtypelist=trainingdao.getTrainingTypeList();
			training.setDuration(training.getDuration());
			
		}
		if(!common.isSpecialChar(training.getDuration()))
		{
			addFieldError("duration", "Special Character For Training Duration is Not Allowed");
			employeelist=trainingdao.getEmployeeList();
			trainingtypelist=trainingdao.getTrainingTypeList();
			this.training.setDuration(training.getDuration());
		}
		
		if(training.getEmployee().getEmployee_id() == 0){
			addFieldError("empId","Please select Employee");
			employeelist=trainingdao.getEmployeeList();
			trainingtypelist=trainingdao.getTrainingTypeList();	
			this.training.setEmployee(training.getEmployee());
		}
		if(training.getTrainingtype().getTraining_type_id() == 0){
			addFieldError("trainingtype","Please select Training Type");
			employeelist=trainingdao.getEmployeeList();
			trainingtypelist=trainingdao.getTrainingTypeList();	
			this.training.setTrainingtype(training.getTrainingtype());
		}
		if(training.getTraining_date()==null && training.getStringtrainingdate().equals(""))
		{
			addFieldError("tdate", "Training Date is Required");
			employeelist=trainingdao.getEmployeeList();
			trainingtypelist=trainingdao.getTrainingTypeList();			
		}
	}
	
	
}
