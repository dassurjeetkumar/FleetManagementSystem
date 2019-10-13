package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.transport.dao.PeakHourDao;
import com.trimax.its.transport.model.PeakHour;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.DockingHistoryDAO;
import com.trimax.its.vehicle.dao.VehicleDAO;
import com.trimax.its.vehicle.model.DockingHistory;
import com.trimax.its.vehicle.model.DockingType;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DockingHistoryAction extends ActionSupport {
	DockingHistory dochistory;

	DockingHistoryDAO daoObject = new DockingHistoryDAO();
	
	private Map<Integer, String> vehicleidlist;
	private Map<Integer, String> doctypelist;
	private Map<Integer, String> componentlist;
	private String checkreq;
	private String startdate;
	
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getCheckreq() {
		return checkreq;
	}
	public void setCheckreq(String checkreq) {
		this.checkreq = checkreq;
	}

	public Map<Integer, String> getVehicleidlist() {
		return vehicleidlist;
	}

	public void setVehicleidlist(Map<Integer, String> vehicleidlist) {
		this.vehicleidlist = vehicleidlist;
	}

	public Map<Integer, String> getDoctypelist() {
		return doctypelist;
	}

	public void setDoctypelist(Map<Integer, String> doctypelist) {
		this.doctypelist = doctypelist;
	}

	public Map<Integer, String> getComponentlist() {
		return componentlist;
	}

	public void setComponentlist(Map<Integer, String> componentlist) {
		this.componentlist = componentlist;
	}

	@SkipValidation
	public String execute()
	{
		
		return "success";
	}
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	@SkipValidation
	public String getDockingHistoryRecordsList() throws IOException{
		try{
			
			
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		Number cnt = daoObject.getTotalDockingHistoryRecords(viewdeletedrecord);
		System.out.println("Count------>"+cnt);
		
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		String search_term = request.getParameter("sSearch");
		
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

			int total = -1;
			
			total = daoObject.getTotalDockingHistoryRecords(viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out=response.getWriter();
			
			result=daoObject.getDockingHistoryData(total, request,Integer.parseInt(sCol),sdir,viewdeletedrecord);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	public DockingHistory getDochistory() {
		return dochistory;
	}

	public void setDochistory(DockingHistory dochistory) {
		this.dochistory = dochistory;
	}
	@SkipValidation
	public String createDockHistory()
	{
		
		Date date=new Date();
		Date date1=new Date();
		
		try{
		SimpleDateFormat sm2 = new SimpleDateFormat("dd MMM yyyy - HH:mm");
		setStartdate(sm2.format(date));
		//setEnddate(sm2.format(addedDate2));
		}catch(Exception ex)
		{
			
		}
		componentlist=daoObject.getComponentList();
		doctypelist=daoObject.getDocTypeListlist();
		vehicleidlist=daoObject.getVehicleList();
		return "success";
	}
	
	
	public String addDockHistory() throws ParseException{
		//System.out.println("inside add complaint"+peakhour.getName()+""+peakhour.getStarttimeString()+""+peakhour.getEndtimeString());
		//PeakHourDao dao = new PeakHourDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "DockingHistoryList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		Common common = new Common();
		int id = 0;
		int hisid=0;
		
				System.out.println("date is ----------------------->>>>>>" +dochistory.getStartDateString());
			dochistory.setCretaedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			dochistory.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			dochistory.setStartTime(common.getDateTimeFromDatePicker(dochistory.getStartDateString()));
			dochistory.setEndTime(common.getDateTimeFromDatePicker(dochistory.getEndDateString()));
			//date validation
			
			
			if(common.compareDatesTime(dochistory.getStartDateString(),dochistory.getEndDateString())==1){
	    		
	    		addActionMessage("End Date/Time should be greater than Start Date/Time");
	    		return "input";
	   	}
			else if(common.compareDatesTime(dochistory.getStartDateString(),dochistory.getEndDateString())==0){
	    		
	    		addActionMessage(" Start Date/Time should Not be Equal To End Date/Time");
	    		return "input";
			}
		
			
			try {
				
				String vehiclekm=daoObject.getVehicleRunningKm(dochistory.getVehicle().getVehicleId());
				String docktypkm=daoObject.getDockTypeKm(dochistory.getDockingType().getDocking_type_id());
				int diff=(Integer.parseInt(vehiclekm)-Integer.parseInt(docktypkm));
				System.out.println("&&&&&&&&&&&&&&&&&&&&"+diff);
				if(diff>=0)
				{
					String dock_km=Integer.toString(diff);
					dochistory.setDocking_kms(dock_km);
					
				}
				else{
					
					dochistory.setDocking_kms("0");
					
				}
				System.out.println("Diffrence kilometer"+diff);
				id = daoObject.insertDeviceType(dochistory);
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if(id>0){
				//setInsertstatus("success");
				addActionMessage("Docking History  " + id + " Created Successfully");
				
			}
			}
		
		return "success";
		}else{
			return "input";
		}
	}
	@SkipValidation	
	public String editDockingHistory()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		componentlist=daoObject.getComponentList();
		doctypelist=daoObject.getDocTypeListlist();
		vehicleidlist=daoObject.getVehicleList();
		dochistory=daoObject.getEditedDeviceType(Integer.parseInt(request.getParameter("dockingidd")));

		return "success";
		
	}
	
	public String addeditedDockhistory ()throws ParseException
	{
		Common common = new Common();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "DockingHistoryList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		System.out.println(dochistory.getStartDateString()+"^^"+dochistory.getEndDateString());
		dochistory.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		dochistory.setStartTime(common.getDateTimeFromDatePicker(dochistory.getStartDateString()));
		dochistory.setEndTime(common.getDateTimeFromDatePicker(dochistory.getEndDateString()));
		
		if(common.compareDatesTime(dochistory.getStartDateString(),dochistory.getEndDateString())==1){
    		
    		addActionMessage(" End Date/Time should be greater than Start Date/Time");
    		return "input";
		}
		else if(common.compareDatesTime(dochistory.getStartDateString(),dochistory.getEndDateString())==0){
    		
    		addActionMessage(" StartDateTime should Not be Equal To EndDateTime");
    		return "input";
		}
		try{
		
			daoObject.updateDockHistory(dochistory,dochistory.getDocking_id());
			
		}catch(Exception ex){
			//setUpdatestatus("fail");
			return "input";
		}finally{
			//setUpdatestaus("success");
			addActionMessage("Docking History Id  "+ dochistory.getDocking_id()+"  Updated Successfully" );
		}

		return "success";
		}else{
			return "input";
		}
	}
	@SkipValidation
	public String deleteDockHistoryAction()
	{
		DockingHistory dockhistory=new DockingHistory();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "DockingHistoryList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		dockhistory.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			daoObject.deleteDockHistory(dockhistory,Integer.parseInt(request.getParameter("dockid")));
		}catch(Exception ex)
		{
			//setDeletestaus("fail");
			return "input";
		}finally{
			//setDeletestaus("success");
			addActionMessage("Docking History  "+request.getParameter("dockid")+" Deleted Successfully");
		}
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");

			return "success";
		}
		
	}
	
	public void validate() {
		componentlist=daoObject.getComponentList();
		doctypelist=daoObject.getDocTypeListlist();
		vehicleidlist=daoObject.getVehicleList();
		Common commons = new Common();
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			DockingHistoryDAO doc=new DockingHistoryDAO();
		//dochistory.setStartDateString(commons.getDateFromDbToPicker(dochistory.getStartDateString()));	
		//dochistory.setEndDateString(commons.getDateFromDbToPicker(dochistory.getEndDateString()));	
		
		CommonValidation common=new CommonValidation();
		HttpServletRequest request = ServletActionContext.getRequest();
		 int check=Integer.parseInt(this.checkreq);
		System.out.println("%%%%%%%%%%%"+dochistory.getStartDateString());
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+check);
		
		if(check==1)
		{
			
			if(dochistory.getVehicle().getVehicleId()==0 )
			{
				addFieldError("vehicleId", "Vehicle Id  is Required");

				
				
			}
			
			if(dochistory.getStartDateString().trim().equals("") || dochistory.getStartDateString().equals(" "))
			{
				//dochistory.setStartDateString(doc.getDateTimeFromPickerToDB(dochistory.getStartDateString()));	
				//dochistory.setStartDateString(commons.getDateFromDbToPicker(dochistory.getStartDateString()));	
				addFieldError("startDateString", "Start Date Time is Required");
				//addActionError("DeviceType Name is Required");
							
			}
			if(dochistory.getDockingType().getDocking_type_id()==0)
			{
				//dochistory.setStartDateString(doc.getDateTimeFromPickerToDB(dochistory.getStartDateString()));	
				//dochistory.setStartDateString(commons.getDateFromDbToPicker(dochistory.getStartDateString()));	
				addFieldError("docking_type_id", "Docking Type  is Required");
				//addActionError("DeviceType Name is Required");
							
			}
			if(!common.isSpecialChar(dochistory.getDocking_batch_name()))
				{
					addFieldError("docking_batch_name", "Special Character For Docking Batch Name  is Not Allowed");
					
				}
			
			if(dochistory.getComponenetType().getComponentTypeId()==0 )
			{
				addFieldError("componentTypeId", "componentType  is Required");

				
				
			}
			
		}
		if(check==2)
		{
		
			if(dochistory.getEndDateString().trim().equals("") || dochistory.getEndDateString().equals(" "))
			{
				dochistory.setStartDateString(doc.getDateTimeFromPickerToDB(dochistory.getStartDateString()));	
				addFieldError("endDateString", "End Date Time is Required");
				
			}
			if(!common.isSpecialChar(dochistory.getDocking_batch_name()))
			{
				addFieldError("docking_batch_name", "Special Character For Docking Batch Name  is Not Allowed");
				
			}
//		if(!common.isSpecialChar(dochistory.getDocking_batch_name()))
//		{
//			addFieldError("docking_batch_name", "Special Character For Docking Batch Name  is Not Allowed");
//			
//		}
		//incident date
		

//		if(dochistory.getDockingType().getDocking_type_id()==0 )
//		{
//			addFieldError("docking_type_id", "Docking Type   is Required");
//
//			
//			
//		}
		if(dochistory.getVehicle().getVehicleId()==0 )
		{
			addFieldError("Registration No.", "Vehicle Id  is Required");

			
			
		}
		
		if(dochistory.getComponenetType().getComponentTypeId()==0 )
		{
			addFieldError("componentTypeId", "componentType  is Required");

			
			
		}

		if(dochistory.getDockingType().getDocking_type_id()==0)
		{
			dochistory.setStartDateString(commons.getDateFromDbToPicker(dochistory.getStartDateString()));	
			//dochistory.setStartDateString(commons.getDateFromDbToPicker(dochistory.getStartDateString()));	
			addFieldError("docking_type_id", "Docking Type  is Required");
			//addActionError("DeviceType Name is Required");
						
		}
		
//		if(dochistory.getStartDateString().trim().equals("") || dochistory.getStartDateString().equals(" "))
//		{
//			dochistory.setStartDateString(commons.getDateFromDbToPicker(dochistory.getStartDateString()));	
//			//dochistory.setStartDateString(commons.getDateFromDbToPicker(dochistory.getStartDateString()));	
//			addFieldError("startDateString", "Start Date Time is Required");
//			//addActionError("DeviceType Name is Required");
//						
//		}

		}
		try{
			Date date=new Date();
			Date date1=new Date();
			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMM yyyy - HH:mm");
			setStartdate(sm2.format(date));
			//setEnddate(sm2.format(addedDate2));
			}catch(Exception ex)
			{
				
			}
		
		
		
		String vehiclekm=daoObject.getVehicleRunningKm(dochistory.getVehicle().getVehicleId());
		String docktypkm=daoObject.getDockTypeKm(dochistory.getDockingType().getDocking_type_id());
		int vehcle_km=Integer.parseInt(vehiclekm);
		int doc_km=Integer.parseInt(docktypkm);
		int diff=doc_km-vehcle_km;
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+diff);
		if(diff==20000)
		{
			String dock_km=Integer.toString(diff);
			dochistory.setDocking_kms(dock_km);
			
		}
		else if(diff>=0 && diff<20000)
		{
			String dock_km=Integer.toString(diff);
			dochistory.setDocking_kms(dock_km);
			
		}
		else if(vehcle_km>=80000 && doc_km==20000)
		{
			String dock_km=Integer.toString(diff);
			dochistory.setDocking_kms(dock_km);
			
		}
		else{
			
			addFieldError("docking_type_id", "select correct docking Type");
		}
		
		
		
		}
	
	//ajax action to check docking type
	
	@SkipValidation
	 public String listDocktypeAction() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int vid = Integer.parseInt(req.getParameter("val"));
		System.out.println("vid"+vid);
		daoObject.getDockigKm(vid);
		//daoObject.getVehicleRunningKm(vid);
		DockingType dc_type=new DockingType();
		//String dock_type=daoObject.getDock_Type(vid);
		dochistory=daoObject.getDock_Type(vid);
			int doc_id=dochistory.getDockingType().getDocking_type_id();
		System.out.println(dochistory.getDockingType().getDocking_type_id()+"@@@"+doc_id);
		//List<DockingType> doctypelist=daoObject.getDocTypeList(vid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString+=doc_id;
//		for (DockingType doctype : doctypelist) {
//			
//			regionTypeAjaxString += "<option value=" + doctype.getDocking_type_id()
//					+ ">" + doctype.getDocking_type() + "</option>";
//		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	 
//	public String listDocktypeAction()
//	{
//		
//		
//		
//		return null;
//	}
//	
}
