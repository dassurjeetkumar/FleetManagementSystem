package com.trimax.its.memo.action;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.trimax.its.memo.dao.MemoDao;
import com.trimax.its.memo.model.Memo;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.transport.dao.PeakHourDao;
import com.trimax.its.transport.model.PeakHour;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class MemoAction extends ActionSupport implements Preparable{
	Memo memo;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	private  String updatestatus;;
	private String deletestaus;
	private String insertstatus;
	
	private Map<Integer, String> memotypelist;
	private Map<Integer, String> userlist;

public Memo getMemo() {
		return memo;
	}

	public void setMemo(Memo memo) {
		this.memo = memo;
	}

public Map<Integer, String> getUserlist() {
	return userlist;
	}

	public Map<Integer, String> getMemotypelist() {
	return memotypelist;
}

	public void setMemotypelist(Map<Integer, String> memotypelist) {
	this.memotypelist = memotypelist;
	}

	public void setUserlist(Map<Integer, String> userlist) {
		this.userlist = userlist;
	}

		public String getInsertstatus() {
			return insertstatus;
		}

		public void setInsertstatus(String insertstatus) {
			this.insertstatus = insertstatus;
		}
	

		public String getUpdatestatus() {
		return updatestatus;
	}

	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}

		public String getDeletestaus() {
		return deletestaus;
	}

	public void setDeletestaus(String deletestaus) {
		this.deletestaus = deletestaus;
	}

	 
	   @SkipValidation
	   public String execute() throws Exception {
		   return null;
	   } 
	    
	   @SkipValidation
	    public String insertData()
	    {
			return "success";
		}

	   	@SkipValidation
		public void prepare() throws Exception {
			// TODO Auto-generated method stub
	    	int i=1;
			//System.out.println("*****************************************2*insert dataprepare");
			try {
	    		HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				MemoDao devicedao=new MemoDao();
				memotypelist= devicedao.getMemoType();
				userlist=devicedao.getUserName();
				
				String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

				String[] dbcol = {"","memo_id","mem.memo_type_id","memu.employee_id","date","status","notes"};
				JSONObject result = new JSONObject();
				int amount = 10;
				int start = 0;
				int col = 0;
				String dir = "asc";
				String sStart = request.getParameter("iDisplayStart");
				String sAmount = request.getParameter("iDisplayLength");
				String sCol = request.getParameter("iSortCol_0");
				//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&sCol---"+sCol);
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
				String colName = dbcol[col];
				int total = -1;
				total =devicedao.getTotalRecords(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
				AMOUNT = amount;
				SEARCH_TERM = request.getParameter("sSearch");
				COL_NAME = colName;
				DIR = dir;
				START = start;
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();
				result = devicedao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
				//System.out.println("REsult of datatable------>" + result);
				out.print(result);
			
			}
		
		catch(Exception e){
			//e.printStackTrace();
		}
			
			
		}
	    @SkipValidation
		public String createMemo() {

			return "success";
		}
	
		public String addMemoNotice() throws ParseException{
			MemoDao dao = new MemoDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "memoNoticeAction.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(create.equalsIgnoreCase("Y")){
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			Common common = new Common();
			int id = 0;
			int hisid=0;
			//if (!dao.checkPeakName(peakhour.getPeak_Name())) {
			memo.setDate(common.getDateFromPicker(memo.getDate()));
			

			//System.out.println("*********************************");
			memo.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			memo.setCreated_date(new java.util.Date());
				

				
				try {
					id = dao.insertDeviceType(memo);
					
					
					
				} catch (Exception e) {
					Logger logger = TrimaxLogger.getTrimaxLogger();
					logger.error(this.getClass() + "$Exception in LoginAction action",
							e);
					SaveRequest.save(request);
					ErrorLog log = new ErrorLog();
					log.setMsg(e.getMessage());
					ErrorLogDAO.saveException(log);
				} finally {
					if(id>0){
					//setInsertstatus("success");
					addActionMessage("Memo Notice " + id + " Created Successfully");
					//}else{
						//setInsertstatus("database");
						//return "input";
				}
				}
//			} else {
//				setInsertstatus("duplicate");
//				return "input";
//			}
			return "success";
			}else{
				return "input";
			}
		}
				

	@SkipValidation
		public String deleteMemoAction()
		{
			
			
			MemoDao dao=new MemoDao();
			Memo memo=new Memo();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "memoNoticeAction.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(delete.equalsIgnoreCase("Y")){
			memo.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			try{
				dao.deleteDeviceType(memo,Integer.parseInt(request.getParameter("memoid")));
				//dao.deleteComplaint(complaint, Integer.parseInt(request.getParameter("complaintid")));
			}catch(Exception e)
			{
				
				Logger logger = TrimaxLogger.getTrimaxLogger();
				logger.error(this.getClass() + "$Exception in LoginAction action",
						e);
				SaveRequest.save(request);
				ErrorLog log = new ErrorLog();
				log.setMsg(e.getMessage());
				ErrorLogDAO.saveException(log);
				setDeletestaus("fail");
				return "input";
			}finally{
				setDeletestaus("success");
				addActionMessage("Memo Notice  "+request.getParameter("memoid")+" Deleted Successfully");
			}
			return "success";
			}else{
				return "input";
			}
			
		}

		@SkipValidation
		public String editMemo()
		{
			MemoDao dao=new MemoDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			memo=dao.getEditedDeviceType(Integer.parseInt(request.getParameter("memoidd")));

			return "success";
			
		}
		
		public String addeditedMemo ()throws ParseException
		{
			MemoDao dao=new MemoDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "memoNoticeAction.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(edit.equalsIgnoreCase("Y")){
			memo.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			
				try{
				dao.updateComplaint(memo,memo.getMemo_id());
				}catch(Exception e){
				
				Logger logger = TrimaxLogger.getTrimaxLogger();
				logger.error(this.getClass() + "$Exception in LoginAction action",
						e);
				SaveRequest.save(request);
				ErrorLog log = new ErrorLog();
				log.setMsg(e.getMessage());
				ErrorLogDAO.saveException(log);
				return "input";
			}finally{
				addActionMessage("Memo Id "+ memo.getMemo_id()+" Updated Successfully" );
			}

			return "success";
			}else{
				return "input";
			}
		}
		
		//validation method override
		@SkipValidation
		public void validate() {
			Common commons = new Common();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			
			CommonValidation common=new CommonValidation();
			if(memo.getMemotype().getMemo_type_id()==0)
			{
				addFieldError("memo_type_id", "Memo Type Name is Required");
				memo.setMemotype(memo.getMemotype());
				
			}
			
			if(memo.getUser().getEmployee_id()==0)
			{
				addFieldError("user_type_id", "User Name is Required");
				memo.setUser(memo.getUser());
				
			}

			if(memo.getDate().trim().equals("") || memo.getDate().equals(" "))
			{
				memo.setDate(commons.getDateToPicker(memo.getDate()));

				addFieldError("date", " Date  is Required");
				
			}

			
		
			if(memo.getNotes().length()>60)
			{
				addFieldError("note", " Notes Menu Notice Size Not More than 60 Words Allowed ");
				memo.setNotes(memo.getNotes());
				
			}
			
			
			
			}
		
		
		
		
		
		


}
