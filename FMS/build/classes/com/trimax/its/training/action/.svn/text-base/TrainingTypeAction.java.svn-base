package com.trimax.its.training.action;

import java.io.PrintWriter;

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
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.training.dao.TrainingDao;
import com.trimax.its.training.dao.TrainingTypeDao;
import com.trimax.its.training.model.TrainingType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class TrainingTypeAction extends ActionSupport implements Preparable{
	TrainingType trainingtype;
	String insertstatus;
	String deletestatus;
	String updatestatus;
	Common common = new Common();
	public TrainingType getTrainingtype() {
		return trainingtype;
	}
	public void setTrainingtype(TrainingType trainingtype) {
		this.trainingtype = trainingtype;
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
	
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SkipValidation
	public String viewTrainingType() {
		return "success";
	}
	
	@SkipValidation
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();

			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			TrainingTypeDao trainingtypedao = new TrainingTypeDao();
			String[] cols = { "", "training_type_id", "training_type_name","status",
					"notes",""};
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
			total = trainingtypedao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = trainingtypedao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println(ex);
		} finally {

		}
	}
	
	@SkipValidation
	public String createtrainingtype() {
		return "success";
	}
	
	public String createTrainingTypeAction()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "trainingtypelist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		TrainingTypeDao trainingtypedao = new TrainingTypeDao();
		int id=0;

		if(!trainingtypedao.getDuplicate(trainingtype.getTraining_type_name()))
		{
		try{
		//	System.out.println("............."+vehicledefect.getVehicle());
			trainingtype.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			id=trainingtypedao.saveTrainingType(trainingtype);
			addActionMessage("Training Type Id "+id+ " Created Successfully");
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
			addActionMessage("Training Type Name already Exist!!");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
	}
	
	@SkipValidation
	public String deleteTrainingType()
	{
		TrainingTypeDao dao=new TrainingTypeDao();
		TrainingType training=new TrainingType();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "trainingtypelist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		training.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			DependencyChecker dc=new DependencyChecker();
	        String status=dc.getStatus(Integer.parseInt(request.getParameter("deltrainingtypeid")),"training_type");
	       if(status.equals("")){
			dao.deleteTrainingTypeDetails(training,Integer.parseInt(request.getParameter("deltrainingtypeid")));
			//System.out.println("Defect Id-------"+Integer.parseInt(request.getParameter("deldefectid")));
			
			addActionMessage("Training Type Id "+request.getParameter("deltrainingtypeid")+ " Deleted SuccessFully");
			setDeletestatus("success");

	       }
	       else{
               addActionError(status);
               //returnResult = "success";
           }
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
			
			//addActionMessage("Training Type Id "+request.getParameter("deltrainingtypeid")+ " Deleted SuccessFully");
		}
		return "success";
		
		}else{
			return "input";
		}
	}
	
	@SkipValidation
	public String getEditDetails() {
		TrainingTypeDao trainingtypedao = new TrainingTypeDao();		
		HttpServletRequest request = ServletActionContext.getRequest();
		trainingtype = trainingtypedao.getEditedTrainingType(Integer.parseInt(request
				.getParameter("edittrainingtypeid")));
		return "success";
		
	}
	public String updateTrainingTypeDetails(){
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "trainingtypelist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(trainingtype.getTraining_type_id(),"training_type");
		if(status.equals("")||(!status.equals("") &&  trainingtype.getStatus().equals("ACTIVE"))){
			
			
		
		TrainingTypeDao trainingtypedao = new TrainingTypeDao();
		int useriddetails=trainingtype.getTraining_type_id();
		boolean flagdetails=true;
		boolean groupflag=false;
		flagdetails=trainingtypedao.getUpdateDuplicate(trainingtype.getTraining_type_id(),trainingtype.getTraining_type_name());
		if(flagdetails==true)
		{		
		 int i=trainingtypedao.updateTrainingTypeDetails(useriddetails, trainingtype);	
		addActionMessage("Training Type Id "+useriddetails+ " Updated SuccessFully");		
		setUpdatestatus("success");
		flag="success";
		}
		else{
			setInsertstatus("duplicate");
			addActionError("Training Type Name already Exist!!");
			return "input";
		}
		}else{
			if(trainingtype.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}
		
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

		
		if(trainingtype.getTraining_type_name().trim().equals("") || trainingtype.getTraining_type_name().equals(" "))
		{
			addFieldError("typename", "Training Type Name is Required");
			trainingtype.setTraining_type_name(trainingtype.getTraining_type_name());
			
		}
		if(!common.isSpecialChar(trainingtype.getTraining_type_name()))
		{
			addFieldError("typename", "Special Character For Training Type Name is Not Allowed");
			//addActionError("Special Character For Device Type Name is Not Allowed");
			this.trainingtype.setTraining_type_name(trainingtype.getTraining_type_name());
		}
	}
	

}
