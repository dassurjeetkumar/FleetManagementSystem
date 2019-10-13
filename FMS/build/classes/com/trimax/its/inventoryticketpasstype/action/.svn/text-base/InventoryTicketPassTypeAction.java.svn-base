package com.trimax.its.inventoryticketpasstype.action;

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
import com.trimax.its.inventoryticketpasstype.dao.InventoryTickectPassTypeDao;
import com.trimax.its.inventoryticketpasstype.model.InventoryTicketPassType;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.revenuesector.dao.RevenueSectorDao;
import com.trimax.its.revenuesector.model.RevenueSector;







public class InventoryTicketPassTypeAction  extends ActionSupport implements Preparable {
	private InventoryTicketPassType inventoryticketpasstype;

	public InventoryTicketPassType getInventoryticketpasstype() {
		return inventoryticketpasstype;
	}

	public void setInventoryticketpasstype(
			InventoryTicketPassType inventoryticketpasstype) {
		this.inventoryticketpasstype = inventoryticketpasstype;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	String insertstaus;
	String deletestatus;
	String updatestatus;
	
	
	
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

	public String getUpdatestatus() {
		return updatestatus;
	}

	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}

	
	@SkipValidation
	public String viewInventoryPass() {

		return "success";
	}
	
	@SkipValidation
	public String createInventoryPass() {

		return "success";
	}
	
	@SkipValidation
	public String editInventoryPass() {

		return "success";
	}
	
	public String deleteInventoryPass() {

		return "success";
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@SkipValidation
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			
			InventoryTickectPassTypeDao inventorypassdao = new InventoryTickectPassTypeDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			/*
			 * int cnt = devicedao.getTotalRecords();
			 * System.out.println("Count------>" + cnt);
			 */
			String[] dbcol = {"","inventorypass_id","day_month","pass_type",
					"status" };
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
			total = inventorypassdao.getTotalRecords(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = inventorypassdao.getData(total, request,
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
	public String createInventoryTicketPassType() {
		HttpServletRequest request = ServletActionContext.getRequest();

	//	System.out.println("The GeoCode Data is" + newlineString);
		InventoryTickectPassTypeDao dao = new InventoryTickectPassTypeDao();
		try {

			if (!dao.checkDay_Month(inventoryticketpasstype.getDay_month(),inventoryticketpasstype.getPass_type())) {
				inventoryticketpasstype.setCreated_by(request.getSession()
						.getAttribute("userid").toString());
				inventoryticketpasstype.setCreated_date(new java.util.Date());
				String res = "";
				int id = 0;
				try {

					
					int result = dao.saveInventortyTicket(inventoryticketpasstype);
				
					//dao.upDateLineString(newlineString, result);
					if (result != 0) {
						setInsertstaus("success");
						addActionMessage(" Ticket Inventory Pass Day Id " + result
								+ " Created  Successfully");
					} else {
						setInsertstaus("fail");
						addActionMessage(" Ticket Inventory Pass Day Id Could Not Created Please Try After SomeTime");
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

					addActionMessage(" Ticket Inventory Pass Day Id");
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
		return "success";
	}

	

	@SkipValidation
	public String deleteInventoryTicketPassType() {
	//	RevenueSectorDao dao = new RevenueSectorDao();
		//RevenueSector revenue = new RevenueSector();
		
		InventoryTickectPassTypeDao dao = new InventoryTickectPassTypeDao();
		InventoryTicketPassType revenue=new InventoryTicketPassType();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		revenue.setUpdated_by(Integer.parseInt(request.getSession()
				.getAttribute("userid").toString()));
		try {
			dao.deleteinventorypass(revenue,Integer.parseInt(request.getParameter("delsectorid")));
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
			addActionMessage("  Ticket Inventory Pass Day Id "
					+ request.getParameter("delsectorid")
					+ " Deleted Successfully");
		}
		return "success";

	}

	@SkipValidation
	public String showInventoryTicketPassType() {
//		System.out.println("in Revernsu show");

		return "success";
	}

	@SkipValidation
	public String editInventoryTicketPassType() {
	//	RevenueSectorDao dao = new RevenueSectorDao();
		// HttpServletRequest request = ServletActionContext.getRequest();
		InventoryTickectPassTypeDao dao = new InventoryTickectPassTypeDao();
		HttpServletRequest request = ServletActionContext.getRequest();

	
		inventoryticketpasstype = dao.getEditedInventoryTicketPassType(Integer.parseInt(request.getParameter("chartid")));
		//revenue.setGeo_fence(dao.getText(Integer.parseInt(request.getParameter("chartid"))));
		
		return "success";

	}

	public String addeditedInventoryTicketPassType()

	{

		InventoryTickectPassTypeDao dao = new InventoryTickectPassTypeDao();
		////	RevenueSectorDao dao = new RevenueSectorDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//System.out.println("================>" + "\t" + revenue.getNotes());
		if (dao.checkDayMonthTypeForUpdate(inventoryticketpasstype .getDay_month(),inventoryticketpasstype .getInventorypass_id()))
		
		{

			//inventoryticketpasstype.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			//System.out.println("updatedBy:" + revenue.getUpdated_by());
			try {
				dao.updateInventoryTicketPassType(inventoryticketpasstype,inventoryticketpasstype.getInventorypass_id());
				
				//dao.upDateLineString(revenue.getGeo_fence(),revenue.getSector_id());
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
				addActionMessage("Ticket Inventory  Pass Day Id Could Not Created Please Try After SomeTime");
				
				return "input";
			} finally {
				
				setUpdatestatus("success");
				addActionMessage("Inventory Ticket PassType Id " + inventoryticketpasstype.getInventorypass_id()
						+ " Updated Successfully");
			}
		} 
		else if (!dao.checkDay_Month(inventoryticketpasstype.getDay_month(),inventoryticketpasstype.getPass_type())){
			//System.out.println("sectorName present");
			try {
				dao.updateInventoryTicketPassType(inventoryticketpasstype, inventoryticketpasstype.getInventorypass_id());
				//dao.upDateLineString(revenue.getGeo_fence(),revenue.getSector_id());
				setUpdatestatus("success");
				addActionMessage("Ticket Inventory  Pass Day Id " + inventoryticketpasstype.getInventorypass_id()
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
				addActionMessage("Ticket Inventory  Pass Day Id Could Not Created Please Try After SomeTime");
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

	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
		CommonValidation common = new CommonValidation();
		//revenue.setGeo_fence(getNewlineString());
		if (inventoryticketpasstype.getDay_month().trim().equals("") ||inventoryticketpasstype.getDay_month().equals(" ")) {
		
	
			addFieldError("inventoryticketpasstype_daymonth", "Day_Month is Required");
		
//		

		}
		if (!common.isSpecialChar(inventoryticketpasstype.getDay_month())) {
			addFieldError("inventoryticketpasstype_daymonth",
					"Special Character For Day_ Month is Not Allowed");
		
			this.inventoryticketpasstype.setDay_month(this.inventoryticketpasstype.getDay_month());
		}
		
//             .
//        
//		if(newlineString.equals("")){
//			
//			
//			addFieldError("revenue_geo_fence","Goe Fence is Required");
//		}
//		if(revenue.getNotes().length()>=100){
//			addFieldError("revenue_note","Maximum Length of Notes is 100");
//		}
//		 
	}
	
	
	
}
