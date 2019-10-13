package com.trimax.its.transport.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.classic.Validatable;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.route.model.RouteType;
import com.trimax.its.transport.dao.TripTypeDao;
import com.trimax.its.transport.model.TripType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;


public class TripTypeAction extends ActionSupport implements Preparable,Validatable{

	private TripType tripType;
	private List<RouteType> routeTypeList;
	public TripType getTripType() {
		return tripType;
	}

	public void setTripType(TripType tripType) {
		this.tripType = tripType;
	}
	
	public List<RouteType> getRouteTypeList() {
		return routeTypeList;
	}

	public void setRouteTypeList(List<RouteType> routeTypeList) {
		this.routeTypeList = routeTypeList;
	}

	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;
	
	@SkipValidation
	public String view() {
		return "view";
	}
	
	@SkipValidation
	public String add() {
		
		TripTypeDao tripTypeDao= new TripTypeDao();
		this.setRouteTypeList(tripTypeDao.getRouteTypeList());
		System.out.println("........"+getRouteTypeList());
		return "add";
	}
	
	@SkipValidation
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		TripTypeDao dao=new TripTypeDao();
		tripType=dao.getTripTypeById(id);
		this.setRouteTypeList(dao.getRouteTypeList());
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "TripTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		int id=Integer.parseInt(request.getParameter("id"));
		DependencyChecker dc=new DependencyChecker();
        String status=dc.getStatus(id,"trip_type");
        System.out.println("status---"+status);
        
       if(status.equals("")){
		TripTypeDao dao=new TripTypeDao();
		String s=dao.deleteTripType(id);

		if(s.equals("success")){
			addActionMessage("Trip Type id "+id+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
       }else{
            addActionError(status);
            //returnResult = "success";
        }
		return "success";}
		else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return  "success";
		}
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "TripTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		TripTypeDao dao=new TripTypeDao();
		if(create.equalsIgnoreCase("Y")){
		tripType.setCreatedDate(new java.util.Date());
		tripType.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		tripType.setDeletedStatus(0);
		tripType.setUpdatedBy(0);
		tripType.setUpdatedDate(null);
		
		int i=dao.addTripType(tripType);
		
		if(i>0){
			addActionMessage("Trip Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addActionError("Trip Type already exist...!");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;}
		else{
			return INPUT;
		}
	}
	
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "TripTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		TripTypeDao dao=new TripTypeDao();
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(tripType.getId(),"trip_type");
		System.out.println("status---"+status);
		if(status.equals("")||(!status.equals("") &&  tripType.getStatus().equals("Active"))){	
		tripType.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		tripType.setUpdatedDate(new java.util.Date());
		
		int i=dao.updateTripType(tripType);
		
		if(i>0){
			addActionMessage("Trip Type id "+tripType.getId()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("tripTypeName","Trip Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
		}else
		{
			
			if(tripType.getStatus().equals("Inactive"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
			
		}
		return SUCCESS;}
		else{
			return "input";
		}
	}
	
	@SkipValidation
	public String execute()
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			TripTypeDao dao = new TripTypeDao();
			//int cnt = dao.getTotalRecords(request.getParameter("sSearch"));

			String[] cols = { "","id","tripTypeName","routetypedetails.route_type_name","status"};
			String[] dbcol={"","id","tripTypeName","routetypedetails.route_type_name","status"};
						
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
			total = dao.getTotalRecords(request,dbcol[col],dir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[col],sdir);

			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}
	
	public void validate(){
		if(tripType.getTripTypeName()==null || tripType.getTripTypeName().length()<=0 || tripType.getTripTypeName().trim().equals("")){
			addFieldError("tripTypeName","Please enter Trip Type Name");
		}	
		else{

			CommonValidation cv=new CommonValidation();	
		 if (!cv.isSpecialChar(tripType.getTripTypeName())){//(!Pattern.matches("^[^()<>;\\*%@#?!$]*$", tripType.getTripTypeName())){
			addFieldError("tripTypeName","Special characters not allowed"); 
		 }
		}
		if(tripType.getRoutetypedetails().getRoute_type_id()==0){
			addFieldError("routetype", "Please Select Route Type Name");
		}
		if(hasFieldErrors()){
			TripTypeDao tripTypeDao= new TripTypeDao();
			this.setRouteTypeList(tripTypeDao.getRouteTypeList());
		}
	}
	
	public void prepare() throws Exception {

	}

}
