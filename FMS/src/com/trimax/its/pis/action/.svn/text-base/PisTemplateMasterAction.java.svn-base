package com.trimax.its.pis.action;

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
import com.trimax.its.pis.dao.PisTemplateMasterDao;
import com.trimax.its.pis.model.PisTemplateMaster;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class PisTemplateMasterAction extends ActionSupport implements Preparable {
	private PisTemplateMaster pisTemplateMaster=new PisTemplateMaster();
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	String insertstaus;
	String updatestaus;
	String deletestaus;
	
	@SkipValidation
	public String execute() {
		
		return null;
	}
	
	public PisTemplateMaster getPisTemplateMaster() {
		return pisTemplateMaster;
	}

	public void setPisTemplateMaster(PisTemplateMaster pisTemplateMaster) {
		this.pisTemplateMaster = pisTemplateMaster;
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
	public String viewPisTemplate()
	{
		
		return "success";
	}
	
	@SkipValidation
	public String createPisTemplate()
	{
		return "success";
	}
	public String createPisTemplateAction()
	{
		System.out.println("IN createPisTemplateAction----------");
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewPisTemplate.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
	
		HttpServletResponse response = ServletActionContext.getResponse();
		PisTemplateMasterDao dao=new PisTemplateMasterDao();
		if(!dao.checkTemplateName(pisTemplateMaster.getTemplate_name())){
		String  name=request.getParameter("template_name");
		System.out.println("name----------"+name);
		pisTemplateMaster.setCreated_by(request.getSession().getAttribute("userid").toString());
		pisTemplateMaster.setCreated_date(new java.util.Date());
		//pisTemplateMaster.setTemplate_name(name);
		String res="";
		int id=0;
		try{
		 id= dao.createPisTemplateMaster(pisTemplateMaster);
		}catch(Exception ex)
		{
			setInsertstaus("fail");
			return "input";
		}finally{
			setInsertstaus("success");
			addActionMessage("Template "+id+" Created Successfully");
		}
		}
		else{
			setInsertstaus("duplicate");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
		
	}
	
	@SkipValidation
	public String editPisTemplate()
	{
		PisTemplateMasterDao dao=new PisTemplateMasterDao();
		HttpServletRequest request=ServletActionContext.getRequest();
		int template_id=Integer.parseInt(request.getParameter("templateid"));
		pisTemplateMaster=dao.getEditedPisTemplateMaster(template_id);
		return "success";
	}
	
	
	public String editPisTemplateAction(){
		
		PisTemplateMasterDao dao=new PisTemplateMasterDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
	
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewPisTemplate.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		DependencyChecker dc=new DependencyChecker();
		if(edit.equalsIgnoreCase("Y")){
		String status=dc.getStatus(pisTemplateMaster.getTemplate_id(),"pis_template_master");
		System.out.println("status---"+status);
		if(status.equals("")||(!status.equals("") &&  pisTemplateMaster.getStatus().equals("ACTIVE"))){	
		
		if(dao.checkTemplateNameforUpdate(pisTemplateMaster.getTemplate_name(), pisTemplateMaster.getTemplate_id())){
			pisTemplateMaster.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
		dao.updateTemplate(pisTemplateMaster,pisTemplateMaster.getTemplate_id());
		}catch(Exception ex){
			setUpdatestaus("fail");
			return "input";
		}finally{
			setUpdatestaus("success");
			addActionMessage("Template Id "+ pisTemplateMaster.getTemplate_id()+" Updated Successfully" );
		}
		}else if(!dao.checkTemplateName(pisTemplateMaster.getTemplate_name())){
			try{
				dao.updateTemplate(pisTemplateMaster,pisTemplateMaster.getTemplate_id());
				}catch(Exception ex){
					setUpdatestaus("fail");
					return "input";
				}finally{
					setUpdatestaus("success");
					addActionMessage("Template Id "+ pisTemplateMaster.getTemplate_id()+" Updated Successfully" );
				}
		}else
		{
			setUpdatestaus("duplicate");
			return "input";
		}
		}else
		{
			
			if(pisTemplateMaster.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
			
		}
		//return "success";
		}else{
			return "input";
		}
		return "success";
		
	}
	
	@SkipValidation
	public String deletePisTemplate()
	{
		PisTemplateMasterDao dao=new PisTemplateMasterDao();
		//ComponentType component=new ComponentType();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
	
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewPisTemplate.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		pisTemplateMaster.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			
			DependencyChecker dc=new DependencyChecker();
	        String status=dc.getStatus(Integer.parseInt(request.getParameter("delTemplateid")),"pis_template_master");
	        System.out.println("status---"+status);
	       if(status.equals("")){
			dao.deletePisTemplateid(pisTemplateMaster,Integer.parseInt(request.getParameter("delTemplateid")));
			addActionMessage("Template Id "+request.getParameter("delTemplateid")+" Deleted Successfully");
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
		if(pisTemplateMaster.getTemplate_name().trim().equals("") || pisTemplateMaster.getTemplate_name().equals(" "))
		{
			addFieldError("template_name", "Template Name is Required");
			
			pisTemplateMaster.setTemplate_name(pisTemplateMaster.getTemplate_name());
			
		}
		if(!common.isSpecialChar(pisTemplateMaster.getTemplate_name()))
		{
			addFieldError("template_name", "Special Character For Template Name is Not Allowed");
			
			pisTemplateMaster.setTemplate_name(pisTemplateMaster.getTemplate_name());
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
			PisTemplateMasterDao dao=new PisTemplateMasterDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			
			String[] dbcol = {"","template_id", "template_name"};
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
//			System.out.println("iDisplayStart==="+sStart);
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
			total = dao.getTotalRecords(total,request,dbcol[col],sdir,viewdeletedrecord);
			
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total,request,dbcol[col],sdir,viewdeletedrecord);
			
			out.print(result);
		} catch (Exception ex) {
		ex.printStackTrace();	
		} finally {

		}
	}
}
