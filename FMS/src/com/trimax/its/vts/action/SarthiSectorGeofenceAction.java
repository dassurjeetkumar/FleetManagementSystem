package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.revenuesector.dao.RevenueSectorDao;
import com.trimax.its.revenuesector.model.RevenueSector;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.VehicleDefectsDao;
import com.trimax.its.vehicle.model.VehicleNumber;
import com.trimax.its.vts.dao.SarthiSectorGeofenceDao;
import com.trimax.its.vts.model.SarthiSectorGeofence;
import com.trimax.its.vts.model.SectorVehicleRelation;

public class SarthiSectorGeofenceAction extends ActionSupport implements Preparable  {

	private SarthiSectorGeofence revenue;
    
	private SectorVehicleRelation sectorVehicle;
    
	private Map<Integer, String> vehicleidlist;

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	String insertstaus;
	String deletestatus;
	String updatestatus;
	String newlineString;
    private String sectorName;
    private int sectorMapId;
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

	@Override
	@SkipValidation
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		SarthiSectorGeofenceDao doa=new SarthiSectorGeofenceDao();
		
		try {
			
		
			JSONObject result = new JSONObject();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
		
		
		String[] dbcol={"","sector_id","sector_name","geo_fence","status","notes"};
	
		
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
		//String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		//String colName = cols[col];
			int total = -1;
			//total = userdao.getTotalRecords(request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
			////System.out.println("total-------------"+total);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			//COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			
			result=doa.getList(total,request,dbcol[Integer.parseInt(sCol)],sdir);
			///System.out.println("result"+result);
			//result = userdao.getUserList(total,request,sdir);
			////System.out.println("result----------"+result);
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

	public SarthiSectorGeofence getRevenue() {
		return revenue;
	}

	public void setRevenue(SarthiSectorGeofence revenue) {
		this.revenue = revenue;
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

	@SkipValidation
	public String viewSarthiGeofence() {

		return "success";
	}

	@SkipValidation
	public String createSarthiGeofence() {
        try{
        	
        	SarthiSectorGeofenceDao doa=new SarthiSectorGeofenceDao();
        	//vehicleidlist=doa.getVehicleList();
        	
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
		return "success";
	}

//	

	public String createSarthiGeofenceAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		SarthiSectorGeofenceDao dao = new SarthiSectorGeofenceDao();
		try {
             
			if (!dao.checkSectorName(revenue.getSector_name())) {
				
				revenue.setCreated_by(request.getSession()
						.getAttribute("userid").toString());
				revenue.setCreated_date(new java.util.Date());
				String res = "";
				int id = 0;
				try {

					
			    	int result = dao.saveOrgChart(revenue);
				//	System.out.println("The result is" + result);
					dao.upDateLineString(newlineString, result);
					if (result != 0) {
						setInsertstaus("success");
						addActionMessage(" Sector  Id " + result
								+ " Created  Successfully");
					} else {
						setInsertstaus("fail");
						addActionMessage("Sector  Could Not Created Please Try After SomeTime");
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

					addActionMessage(" Sector Geofence Could Not Save");
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
		}/*}else{
			return "input";

		}*/
		return "success";
	}

	@SkipValidation
	public String deleteSarthiGeofenceAction() {
		SarthiSectorGeofenceDao dao = new SarthiSectorGeofenceDao();
		SarthiSectorGeofence revenue = new SarthiSectorGeofence();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		revenue.setUpdated_by(Integer.parseInt(request.getSession()
				.getAttribute("userid").toString()));
		try {
			dao.deleterevenue(revenue,
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
			addActionMessage(" Sector   Id "
					+ request.getParameter("delsectorid")
					+ " Deleted Successfully");
		}
		return "success";
		

	}

	@SkipValidation
	public String showSarthiGeofence() {
//		System.out.println("in Revernsu show");

		return "success";
	}

	@SkipValidation
	public String editSarthiGeofence() {
		try{
		SarthiSectorGeofenceDao dao = new SarthiSectorGeofenceDao();
		// HttpServletRequest request = ServletActionContext.getRequest();

		HttpServletRequest request = ServletActionContext.getRequest();

		//vehicleidlist=dao.getVehicleList();
		revenue = dao.getEditedRevenueSector(Integer.parseInt(request.getParameter("revenueSectorId")));
		revenue.setGeo_fence(dao.getText(Integer.parseInt(request.getParameter("revenueSectorId"))));
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
		return "success";

	}

	public String addeditedSarthiGeofence()

	{

////		
		SarthiSectorGeofenceDao dao = new SarthiSectorGeofenceDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		if (dao.checkRevenueSectorTypeForUpdate(revenue.getSector_name(),revenue.getSector_id()))
		
		{

			     revenue.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			//System.out.println("updatedBy:" + revenue.getUpdated_by());
			try {
				dao.updateRevenuSector(revenue,revenue.getSector_id());
				
				dao.upDateLineString(revenue.getGeo_fence(),revenue.getSector_id());
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
				addActionMessage(" Sector  Could Not Created Please Try After SomeTime");
				
				return "input";
			} finally {
				
				setUpdatestatus("success");
				addActionMessage("Sector  Id " + revenue.getSector_id()
						+ " Updated Successfully");
			}
		} else if (!dao.checkSectorName(revenue.getSector_name())) {
			//System.out.println("sectorName present");
			try {
				dao.updateRevenuSector(revenue, revenue.getSector_id());
				dao.upDateLineString(revenue.getGeo_fence(),revenue.getSector_id());
				setUpdatestatus("success");
				addActionMessage(" Sector Id " + revenue.getSector_id()
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
				addActionMessage(" SectorCould Not Created Please Try After SomeTime");
				return "input";
			} finally {
//				setUpdatestatus("success");
//				addActionMessage("Sarthi Sector Geofence Id " + revenue.getSector_id()
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
		/*}else{
			return "input";
		}
*/
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		SarthiSectorGeofenceDao doa=new SarthiSectorGeofenceDao();
		//vehicleidlist=doa.getVehicleList();
		CommonValidation common = new CommonValidation();
		revenue.setGeo_fence(getNewlineString());
		if (revenue.getSector_name().trim().equals("") ||revenue.getSector_name().equals(" ")) {
		
			//System.out.println("hi in validate sector");
			addFieldError("revenue_sector_name", "Sector Name is Required");
		
//		

		}
		if (!common.isSpecialChar(revenue.getSector_name())) {
			addFieldError("revenue_sector_name",
					"Special Character For Sector  Name is Not Allowed");
		
			this.revenue.setSector_name(this.revenue.getSector_name());
		}
				   
        
		if(newlineString.equals("")){
			
			
			addFieldError("revenue_geo_fence","Goe Fence is Required");
		}
		if(revenue.getNotes().length()>=100){
			addFieldError("revenue_note","Maximum Length of Notes is 100");
		}
//		 
	}

	public Map<Integer, String> getVehicleidlist() {
		return vehicleidlist;
	}

	public void setVehicleidlist(Map<Integer, String> vehicleidlist) {
		this.vehicleidlist = vehicleidlist;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public int getSectorMapId() {
		return sectorMapId;
	}

	public void setSectorMapId(int sectorMapId) {
		this.sectorMapId = sectorMapId;
	}

	public SectorVehicleRelation getSectorVehicle() {
		return sectorVehicle;
	}

	public void setSectorVehicle(SectorVehicleRelation sectorVehicle) {
		this.sectorVehicle = sectorVehicle;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
