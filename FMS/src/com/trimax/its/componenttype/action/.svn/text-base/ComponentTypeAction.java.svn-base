package com.trimax.its.componenttype.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.componenttype.dao.ComponentTypeDao;
import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;




public class ComponentTypeAction extends ActionSupport implements Preparable {
	
	private ComponentType componentType;
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	String insertstaus;
	String updatestaus;
	String deletestaus;
		
	public ComponentType getComponentType() {
		return componentType;
	}
	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}
	public String getInsertstaus() {
		return insertstaus;
	}
	public void setInsertstaus(String insertstaus) {
		this.insertstaus = insertstaus;
	}
	public String getUpdatestaus() {
		return updatestaus;
	}
	public void setUpdatestaus(String updatestaus) {
		this.updatestaus = updatestaus;
	}
	public String getDeletestaus() {
		return deletestaus;
	}
	public void setDeletestaus(String deletestaus) {
		this.deletestaus = deletestaus;
	}
	@SkipValidation
	public String execute() {
		
		return null;
	}
	@SkipValidation
	public String showComponentType()
	{
		
		return "success";
	}
	@SkipValidation
	public String createComponentType()
	{
		return "success";
	}
	public String createComponentTypeAction()
	{
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "componentTypeView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
	//	HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ComponentTypeDao dao=new ComponentTypeDao();
		if(!dao.checkComponentType(componentType.getComponent_type_name())){
			componentType.setCreated_by(request.getSession().getAttribute("userid").toString());
			componentType.setCreated_date(new java.util.Date());
		String res="";
		int id=0;
		try{
		 id= dao.createComponentType(componentType);
		}catch(Exception ex)
		{
			setInsertstaus("fail");
			return "input";
		}finally{
			setInsertstaus("success");
			addActionMessage("Component Type "+id+" Created Successfully");
		}
		}
		else{
			setInsertstaus("duplicate");
			return "input";
		}
		return "success";}else{
			return "input";
		}
		
	}
	@SkipValidation
	public String editComponentType()
	{
		ComponentTypeDao dao=new ComponentTypeDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		componentType = dao.getEditedComponentType(Integer.parseInt(request.getParameter("componentid")));

		 
		return "success";
		
	}
	public String addeditedComponentType()
	{
		ComponentTypeDao dao=new ComponentTypeDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
	
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "componentTypeView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		DependencyChecker dc=new DependencyChecker();
		if(edit.equalsIgnoreCase("Y")){
		String status=dc.getStatus(componentType.getComponent_type_id(),"components_type");
		System.out.println("status---"+status);
		if(status.equals("")||(!status.equals("") &&  componentType.getStatus().equals("ACTIVE"))){	
		
		if(dao.checkComponentTypeForUpdate(componentType.getComponent_type_name(), componentType.getComponent_type_id())){
			componentType.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
		dao.updateComponentType(componentType,componentType.getComponent_type_id());
		}catch(Exception ex){
			setUpdatestaus("fail");
			return "input";
		}finally{
			setUpdatestaus("success");
			addActionMessage("Component Type Id "+ componentType.getComponent_type_id()+" Updated Successfully" );
		}
		}else if(!dao.checkComponentType(componentType.getComponent_type_name())){
			try{
				dao.updateComponentType(componentType,componentType.getComponent_type_id());
				}catch(Exception ex){
					setUpdatestaus("fail");
					return "input";
				}finally{
					setUpdatestaus("success");
					addActionMessage("Component Type Id "+ componentType.getComponent_type_id()+" Updated Successfully" );
				}
		}else
		{
			setUpdatestaus("duplicate");
			return "input";
		}
		}else
		{
			
			if(componentType.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
			
		}
		return "success";
		}else{
			return "input";
		}
		
	}
	@SkipValidation
	public String deleteComponentTypeAction()
	{
		ComponentTypeDao dao=new ComponentTypeDao();
		ComponentType component=new ComponentType();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
	
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "componentTypeView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		component.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			
			DependencyChecker dc=new DependencyChecker();
	        String status=dc.getStatus(Integer.parseInt(request.getParameter("delcomponentid")),"components_type");
	        System.out.println("status---"+status);
	       if(status.equals("")){
			dao.deleteComponentType(component,Integer.parseInt(request.getParameter("delcomponentid")));
			addActionMessage("Component Type Id "+request.getParameter("delcomponentid")+" Deleted Successfully");
	       }
	       else{
               addActionError(status);
               //returnResult = "success";
           }
		}catch(Exception ex)
		{
			setDeletestaus("fail");
			return "input";
		}finally{
			setDeletestaus("success");
			
		}
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");

			return "input";
		}
		
	}
	@SkipValidation
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
		if(componentType.getComponent_type_name().trim().equals("") || componentType.getComponent_type_name().equals(" "))
		{
			addFieldError("componentTypeName", "Component Type is Required");
			
			componentType.setComponent_type_name(componentType.getComponent_type_name());
			
		}
		if(!common.isSpecialChar(componentType.getComponent_type_name()))
		{
			addFieldError("componentTypeName", "Special Character For Component Type is Not Allowed");
			
			this.componentType.setComponent_type_name(this.componentType.getComponent_type_name());
		}
		/*if(componentType.getNotes().length()>100){
			addFieldError("componentTypenotes", "Notes size is very long");
		}*/
		
	}

	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			ComponentTypeDao componentdao = new ComponentTypeDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			
			String[] dbcol = { "","component_type_id", "component_type_name","notes","status"};
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
			total = componentdao.getTotalRecords(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
			
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = componentdao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
			
			out.print(result);
		} catch (Exception ex) {
		//ex.printStackTrace();	
		} finally {

		}

	}
	}


