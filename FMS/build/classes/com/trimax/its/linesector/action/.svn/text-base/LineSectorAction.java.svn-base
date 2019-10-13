package com.trimax.its.linesector.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.linesector.Model.LineSector;
import com.trimax.its.linesector.dao.LineSectorDao;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.revenuesector.dao.RevenueSectorDao;
import com.trimax.its.revenuesector.model.RevenueSector;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class LineSectorAction extends ActionSupport implements Preparable {

	private LineSector linesec;
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	String insertstaus;
	String deletestatus;
	String updatestatus;
	String newlineString;

	public LineSector getLinesec() {
		return linesec;
	}

	public void setLinesec(LineSector linesec) {
		this.linesec = linesec;
	}

	public String getNewlineString() {
		return newlineString;
	}

	public void setNewlineString(String newlineString) {
		this.newlineString = newlineString;
	}

	public String getUpdatestatus() {
		return updatestatus;
	}
	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}

	
	public String getInsertstaus() {
		return insertstaus;
	}

	public void setInsertstaus(String insertstaus) {
		this.insertstaus = insertstaus;
	}

	public String getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
	}

	@Override
	@SkipValidation
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("Enter into LineSector Execute()");
		try {
			
//			RevenueSectorDao revenuedao = new RevenueSectorDao();
			LineSectorDao linedao=new LineSectorDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			System.out.println(viewdeletedrecord);
			/*
			 * int cnt = devicedao.getTotalRecords();
			 * System.out.println("Count------>" + cnt);
			 */
			String[] dbcol = {"","sector_id","sector_name","geo_fence",
					"status","notes" };
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

			String colName = dbcol[col];
			int total = -1;
			total = linedao.getTotalRecords(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = linedao.getData1(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			// System.out.println("=====?" + ex);
			// ex.printStackTrace();
		} finally {

		}
        return null;
	}
	
	
	@SkipValidation
	public String viewLine() {
     System.out.println("Enter into viewLine()");
		return "success";
	}
	
	@SkipValidation
	public String createLine() {

		return "success";
	}
	
	
	public String createLineAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"viewlinesector.action");
//		String access=accessrights.getACCESS();
//		String create=accessrights.getCREATE();

		

//		System.out.println("The GeoCode Data is" + create);
//		if(create.equalsIgnoreCase("Y")){
//		RevenueSectorDao dao = new RevenueSectorDao();
			LineSectorDao linedao=new LineSectorDao();
		try {

			if (!linedao.checkLineSectorName(linesec.getSector_name())) {
				linesec.setCreated_by(request.getSession()
						.getAttribute("userid").toString());
				linesec.setCreated_date(new java.util.Date());
				String res = "";
				int id = 0;
				try {

					
					int result = linedao.saveOrgChart(linesec);
					System.out.println("The result is" + result);
					linedao.upDateLineString(newlineString, result);
					if (result != 0) {
						setInsertstaus("success");
						addActionMessage("Line Sector Id " + result
								+ " Created  Successfully");
					} else {
						setInsertstaus("fail");
						addActionMessage("Line Sector Could Not Created Please Try After SomeTime");
						return "input";
					}
				} catch (Exception ex) {
					Logger logger = TrimaxLogger.getTrimaxLogger();
	                logger.error(this.getClass() + "$Exception in LoginAction action",
	                        ex);
	                SaveRequest.save(request);
	                ErrorLog log = new ErrorLog();
	                log.setMsg(ex.getMessage());
	                ErrorLogDAO.saveException(log);
					setInsertstaus("duplicate");

					addActionMessage("Line Sector Could Not Save");
					return "input";
				} finally {

				}
			} else {
				setInsertstaus("duplicate");

				return "input";
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		}else{
//			return "input";

//		}
		return "success";
	}
	@SkipValidation
	public String showLineSector(){
		
		
		return "success";
	}
	
	@SkipValidation
	public String deleteLineSectorAction() {
//		RevenueSectorDao dao = new RevenueSectorDao();
//		RevenueSector revenue = new RevenueSector();
		LineSectorDao linedao=new LineSectorDao();
		LineSector linesec=new LineSector();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
//		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewlinesector.action");
//		String access=accessrights.getACCESS();
//		String create=accessrights.getCREATE();
//		String edit=accessrights.getEDIT();
//		String delete=accessrights.getDELETE();
//		if(delete.equalsIgnoreCase("Y")){
			linesec.setUpdated_by(Integer.parseInt(request.getSession()
				.getAttribute("userid").toString()));
		try {
			linedao.deletelinesector(linesec,
					Integer.parseInt(request.getParameter("delsectorid")));
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			setDeletestatus("fail");
			return "input";
			
		} finally {
			setDeletestatus("success");
			addActionMessage(" Line Sector  Id "
					+ request.getParameter("delsectorid")
					+ " Deleted Successfully");
		}
		return "success";
//		}else{
//			setDeletestatus("success");
//			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			//Access Denied - User Does Not Have Access Privileges
//			return "success";
//		}

	}
	@SkipValidation
	public String editLineSector() {
		try{
//		RevenueSectorDao dao = new RevenueSectorDao();
			LineSectorDao linedao=new LineSectorDao();
		// HttpServletRequest request = ServletActionContext.getRequest();

		HttpServletRequest request = ServletActionContext.getRequest();

	
		linesec = linedao.getEditedLineSector(Integer.parseInt(request.getParameter("lineSectorId")));
		linesec.setGeo_fence(linedao.getTextData(Integer.parseInt(request.getParameter("lineSectorId"))));
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
		return "success";

	}
	
	public String addeditedline()

	{

////		
//		RevenueSectorDao dao = new RevenueSectorDao();
		LineSectorDao linedao=new LineSectorDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
//		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewrevenuesector.action");
//		String access=accessrights.getACCESS();
//		String create=accessrights.getCREATE();
//		String edit=accessrights.getEDIT();
//		String delete=accessrights.getDELETE();
//		if(edit.equalsIgnoreCase("Y")){
		//System.out.println("================>" + "\t" + revenue.getNotes());
		if (linedao.checkLineSectorTypeForUpdate(linesec.getSector_name(),linesec.getSector_id()))
		
		{

			     linesec.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			//System.out.println("updatedBy:" + revenue.getUpdated_by());
			try {
				linedao.updateLineSector(linesec,linesec.getSector_id());
				
				linedao.upDateLineString(linesec.getGeo_fence(),linesec.getSector_id());
//				
			} catch (Exception ex) {
				
				//System.out.println("The err2"+ex);
				
				Logger logger = TrimaxLogger.getTrimaxLogger();
                logger.error(this.getClass() + "$Exception in LoginAction action",
                        ex);
                SaveRequest.save(request);
                ErrorLog log = new ErrorLog();
                log.setMsg(ex.getMessage());
                ErrorLogDAO.saveException(log);
		
				setUpdatestatus("fail");
				addActionMessage("Line Sector Could Not Created Please Try After SomeTime");
				
				return "input";
			} finally {
				
				setUpdatestatus("success");
				addActionMessage("Line Sector Id " + linesec.getSector_id()
						+ " Updated Successfully");
			}
		} else if (!linedao.checkLineSectorName(linesec.getSector_name())) {
			//System.out.println("sectorName present");
			try {
				linedao.updateLineSector(linesec, linesec.getSector_id());
				linedao.upDateLineString(linesec.getGeo_fence(),linesec.getSector_id());
				setUpdatestatus("success");
				addActionMessage("Line Sector Id " + linesec.getSector_id()
						+ " Updated Successfully");
			} catch (Exception ex) {
				//System.out.println("The err1"+ex);
				Logger logger = TrimaxLogger.getTrimaxLogger();
                logger.error(this.getClass() + "$Exception in LoginAction action",
                        ex);
                SaveRequest.save(request);
                ErrorLog log = new ErrorLog();
                log.setMsg(ex.getMessage());
                ErrorLogDAO.saveException(log);
				setUpdatestatus("fail");
				addActionMessage("Line Sector Could Not Created Please Try After SomeTime");
				return "input";
			} finally {
//				setUpdatestatus("success");
//				addActionMessage("Revenue Sector Id " + revenue.getSector_id()
//						+ " Updated Successfully");
			}
		} else {
			setUpdatestatus("duplicate");
			return "input";
		}
		
		try{
			//execute();
			
		}catch(Exception e){
			
		}
		
		
		return "success";
//		}else{
//			return "input";
//		}

	}
	
	@SkipValidation
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	// @SkipValidation
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
		CommonValidation common = new CommonValidation();
		linesec.setGeo_fence(getNewlineString());
		if (linesec.getSector_name().trim().equals("") ||linesec.getSector_name().equals(" ")) {
		
			System.out.println("hi in validate sector");
			addFieldError("linesec_sector_name", "Sector Name is Required");
		
//		

		}
		if (!common.isSpecialChar(linesec.getSector_name())) {
			addFieldError("linesec.sector_name",
					"Special Character For Sector  Name is Not Allowed");
		
			this.linesec.setSector_name(this.linesec.getSector_name());
		}
		
//             .
        
		if(newlineString.equals("")){
			
			
			addFieldError("linesec_geo_fence","Goe Fence is Required");
		}
		if(linesec.getNotes().length()>=100){
			addFieldError("linesec_note","Maximum Length of Notes is 100");
		}
//		 
	}
	
	
	
	
	
	
}
