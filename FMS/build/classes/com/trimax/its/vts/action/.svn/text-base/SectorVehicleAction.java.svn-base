package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.inventory.dao.StockViewDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.revenuesector.dao.RevenueSectorDao;
import com.trimax.its.revenuesector.model.RevenueSector;
import com.trimax.its.route.model.RouteMap;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleNumber;
import com.trimax.its.vts.dao.SarthiSectorGeofenceDao;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.SarthiSectorGeofence;
import com.trimax.its.vts.model.SectorVehicleRelation;

public class SectorVehicleAction  extends ActionSupport{
	private SarthiSectorGeofence revenue;

	private SectorVehicleRelation sectorVehicle;
	private String sectorName;
	private int sectorMapId;
	private Map<Integer, String> vehicleidlist;
	private Map<Integer ,String> organizationUnit;
 	public Map<Integer, String> getVehicleidlist() {
		return vehicleidlist;
	}
	private Map<Integer, String> divisionlist;
	public void setVehicleidlist(Map<Integer, String> vehicleidlist) {
		this.vehicleidlist = vehicleidlist;
	}

	public String getInsertstaus() {
		return insertstaus;
	}

	public void setInsertstaus(String insertstaus) {
		this.insertstaus = insertstaus;
	}
	String insertstaus;
     public SarthiSectorGeofence getRevenue() {
		return revenue;
	}

	public void setRevenue(SarthiSectorGeofence revenue) {
		this.revenue = revenue;
	}

	public SectorVehicleRelation getSectorVehicle() {
		return sectorVehicle;
	}

	public void setSectorVehicle(SectorVehicleRelation sectorVehicle) {
		this.sectorVehicle = sectorVehicle;
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
		private String SEARCH_TERM;

		private String COL_NAME;

		private String DIR;

		private int START;

		private int AMOUNT;
		private String sectorVehicleName;
		
	    public String getSectorVehicleName() {
			return sectorVehicleName;
		}

		public void setSectorVehicleName(String sectorVehicleName) {
			this.sectorVehicleName = sectorVehicleName;
		}
		String deletestatus;
	public String getDeletestatus() {
			return deletestatus;
		}

		public void setDeletestatus(String deletestatus) {
			this.deletestatus = deletestatus;
		}

	@SkipValidation
    public String mapVehicleToSector(){
   	 SarthiSectorGeofenceDao dao = new SarthiSectorGeofenceDao();
		

		HttpServletRequest request = ServletActionContext.getRequest();

		try{
			
			SarthiSectorGeofenceDao doa=new SarthiSectorGeofenceDao();
			organizationUnit = doa.getOrganizationUnit();
		String  vehicleid = ServletActionContext.getRequest().getParameter("sectorId");
		
		//System.out.println("SectorId"+vehicleid);
		//Object vehicleID = ServletActionContext.getRequest().getSession().getAttribute("vtuVehicleID");
		//revenue = dao.getEditedRevenueSector(Integer.parseInt(request.getParameter("sectorId")));
		//revenue.setGeo_fence(dao.getText(Integer.parseInt(request.getParameter("sectorId"))));
		//System.out.println("SectorId"+dao.getEditedRevenueSector(Integer.parseInt(request.getParameter("sectorId"))));
		//String vehicleId = (vehicleid != null) ? vehicleid : vehicleID.toString();
		//ServletActionContext.getRequest().getSession().removeAttribute("vtuVehicleID");
		//sectorVehicle=new SectorVehicleRelation();
		revenue=dao.getEditedRevenueSector(Integer.parseInt(request.getParameter("sectorId")));
		setSectorMapId(revenue.getSector_id());
		setSectorName(revenue.getSector_name());
	
		
	//	sectorVehicle.setSector(dao.getEditedRevenueSector(Integer.parseInt(request.getParameter("sectorId"))));
		//this.setRevenue(dao.getEditedRevenueSector(Integer.parseInt(request.getParameter("sectorId"))));
		
		}catch (Exception e) {
		e.printStackTrace();
		}
	//	this.setVehicle(daoObject.getParticularVehicleDetails(vehicleId));
		//this.setDeviceTypeList(daoObject.getDeviceTypeList());
		
		return "success";
	}
	
	@SkipValidation
	public String getVehicledetails(){
		//System.out.println("in get bus stop11111111111111111111111");
		SarthiSectorGeofenceDao dao = new SarthiSectorGeofenceDao();
		
		HttpServletRequest request=ServletActionContext.getRequest();
	    String idVehicleName = request.getParameter("id");
	    String orgChartId=request.getParameter("depotid");
	    String vehicleListId=dao.ListofVehicle();
	      String org_chart_List =dao.ListofOrgChart(Integer.parseInt(orgChartId));
	    // System.out.println("Srii"+vehicleListId);

	    String regionTypeAjaxString="";
	//    System.out.println("Srii"+vehicleListId);

	    //System.out.println("idVehicleName---"+idVehicleName);
	  //		 list = busstopsdao.getBusStopDropList();
//		 String busDet="";
	    vehicleidlist=dao.getVehicleList(org_chart_List, vehicleListId);
	    
	   // List<VehicleNumber> list = dao.getVehicleDropList(idVehicleName,vehicleListId);
		
			//System.out.println("Size --->"+list.size());
			//int i=1;
			//List<VehicleNumber> list1 = new ArrayList<VehicleNumber>();
				
		 HttpServletResponse response=ServletActionContext.getResponse();
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		PrintWriter out;
		try {
		//	regionTypeAjaxString += "<option value='0'> Select </option>" ;
			for (Map.Entry<Integer, String> entry : vehicleidlist.entrySet()) {
				
				
				regionTypeAjaxString += "<option value=" + entry.getKey()
						   + ">" +entry.getValue() + "</option>";
			}
			
			/*regionTypeAjaxString += "<option value='0'> Select </option>" +
					" <option value='all'> All </option>" ;
         	for (int i = 0; i < vehicleidlist.size(); i++) {
	   regionTypeAjaxString += "<option value=" + vehicleidlist.get(i).toString()
		   + ">" + vehicleidlist.get(i).toString() + "</option>"*/;
	//}
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list1.size());
			  out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
    	return null;
	}

	//@SkipValidation
	public String saveSectorRelationVehicle(){
	HttpServletRequest request = ServletActionContext.getRequest();
	SarthiSectorGeofenceDao doa=new SarthiSectorGeofenceDao();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	String res = "";
	int id = 0;
	 HttpServletResponse response=ServletActionContext.getResponse();
	PrintWriter out;
		
	try {
		session.getTransaction().setTimeout(600);
		session.getTransaction().begin();
	     out = response.getWriter();
		String SectorId=request.getParameter("sectorId");
		String[] VehicleId=request.getParameter("vehicleId").split(",");
		
		SectorVehicleRelation sectorVehicle =new SectorVehicleRelation();
		 for(int j=0;j<VehicleId.length;j++){
				//System.out.println("j====="+j);
			 sectorVehicle=new SectorVehicleRelation();
			 SarthiSectorGeofence sarthi =new SarthiSectorGeofence();
			 sarthi.setSector_id(Integer.parseInt(SectorId));
			 sectorVehicle.setSector(sarthi);
			 sectorVehicle.setCreated_by(request.getSession()
						.getAttribute("userid").toString());
			 sectorVehicle.setStatus("ACTIVE");
			 sectorVehicle.setDeleted_status(0);
			 Vehicle vehicle=new Vehicle();
			 vehicle.setVehicleId(Integer.parseInt(VehicleId[j]));
			 sectorVehicle.setVehicle(vehicle);
			 sectorVehicle.setCreated_date(new java.util.Date());
				
				
			  res=doa.saveSectorVehicleRelation(sectorVehicle, session);
		 }
		   session.getTransaction().commit();
  // 	int result =doa.saveSectorVehicleRelation(sectorVehicle);
	
		if (res.length()>0) {
			out.print("Vehicle Map To Sector Succesfully  ");
			setInsertstaus("success");
			addActionMessage("Vehicle Map To Sector Succesfully ");
			
		} else {
			setInsertstaus("fail");
			addActionMessage("Vehicle  Not Mapped to Sector Please Try After SomeTime");
			out.print("Vehicle  Not Mapped to Sector Please Try After SomeTime");
			
		}
	} catch (Exception ex) {
		session.getTransaction().rollback();
		Logger logger = TrimaxLogger.getTrimaxLogger();
       logger.error(this.getClass() + "$Exception in LoginAction action",
               ex);
       SaveRequest.save(request);
       ErrorLog log = new ErrorLog();
       log.setMsg(ex.getMessage());
       ErrorLogDAO.saveException(log);
		setInsertstaus("duplicate");

		addActionMessage("Sarthi Sector Geofence Could Not Save");
		//out.print("Vehicle  Not Mapped to Sector Please Try After SomeTime");
		//return "input";
	} finally {

	}
	return null;
	}
	public String getAllocatedVehicle()
	{ 	HttpServletRequest request = ServletActionContext.getRequest();
		String sectorId=ServletActionContext.getRequest().getParameter("sectorId");
		//System.out.print("Secot"+sectorId);
		SarthiSectorGeofenceDao doa=new SarthiSectorGeofenceDao();
		revenue=doa.getEditedRevenueSector(Integer.parseInt(request.getParameter("sectorId")));
		sectorVehicleName=revenue.getSector_name();
		
		request.getSession().setAttribute("sectorId", sectorId);
		request.getSession().setAttribute("sectorName", sectorVehicleName); 
		return "success";
	}
	
	public void getAllocatedVehicleList(){
		  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		SarthiSectorGeofenceDao doa=new SarthiSectorGeofenceDao();
		
		try {
			
		
			JSONObject result = new JSONObject();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
		
		
		String[] dbcol={"","v.license_number"};
	
		
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
			
			result=doa.getAlloacatedVehicles(total,request,dbcol[Integer.parseInt(sCol)],sdir);
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
	
	}
	public String deleteVehicleSectorAction() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		SarthiSectorGeofenceDao doa=new SarthiSectorGeofenceDao();
		
		
		String sectorId = (String) request.getSession().getAttribute("sectorId");
		String vehicles="";
		String VehicleIds="";	
		try {
			
			String vehicleId[]=(request.getParameter("delsectorid")).split(",");
			if(vehicleId.length==1){
				vehicles=vehicleId[0].trim();
			}else{
				
			   for(int i=0;i<vehicleId.length;i++){
				vehicles+=vehicleId[i].trim()+",";
			   }
			   VehicleIds=vehicles.substring(0,vehicles.length()-1);
			   vehicles=VehicleIds;
			   
			}
			doa.deleteAssignedVehicle(vehicles, sectorId);
	
				
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			//setDeletestatus("fail");
			return "input";
			
		} finally {
			setDeletestatus("success");
			addActionMessage(" Vehicle   Id "
					+ vehicles
					+ " Deleted Successfully");
		}
		return "success";
		}

	//}
	public void validate() {
		// TODO Auto-generated method stub
		SarthiSectorGeofenceDao doa=new SarthiSectorGeofenceDao();
	//	vehicleidlist=doa.getVehicleList();
		/*//if(sectorVehicle.getVehicle().getVehicleId() == 0){
			addFieldError("vehicleId", "Please select Vehicle Number");
			//vehicleidlist=doa.getVehicleList();
			this.sectorVehicle.setVehicle(sectorVehicle.getVehicle());
		}else if(sectorVehicle.getVehicle().getVehicleId() == -1){
			addFieldError("vehicleId", "Please select Valid Vehicle Number");
			//vehicleidlist=doa.getVehicleList();
			this.sectorVehicle.setVehicle(sectorVehicle.getVehicle());
		}
		*/
//		 
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public Map<Integer ,String> getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(Map<Integer ,String> organizationUnit) {
		this.organizationUnit = organizationUnit;
	}
}
