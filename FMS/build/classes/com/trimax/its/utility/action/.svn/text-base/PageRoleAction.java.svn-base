package com.trimax.its.utility.action;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;


import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.GroupMasterDao;
import com.trimax.its.utility.dao.PageRoleDao;
import com.trimax.its.utility.dao.RoleDao;
import com.trimax.its.utility.model.MenuPageRole;
import com.trimax.its.utility.model.Role;

public class PageRoleAction {
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	Role role;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDisplayRolePage(){
		RoleDao dao = new RoleDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		//System.out.println("rolename---"+request.getParameter("roleiddetails"));
		//Role role=new Role();
		role = dao.getEditedRole(Integer.parseInt(request.getParameter("roleiddetails")));
		return "success";
	}
	public String getpagelistdetailsforuser(){
		  HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();	
			PageRoleDao pageroledao = new PageRoleDao();
			String data="";
			try{
				PrintWriter out = response.getWriter();
				int useriddetails=Integer.parseInt(request.getParameter("useriddetails"));
				String userpagelist=pageroledao.getuserpagelist(useriddetails);
				String pagelist=pageroledao.pageList(userpagelist);
				 data=pagelist;
					//data="Ashwini";
			 out.println(data);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}
	
public String getPagelist(){
		
		String data="";
		
			try{
				//rolid
				
				//System.out.println("pagelist---");
				  HttpServletResponse response = ServletActionContext.getResponse();
					HttpServletRequest request = ServletActionContext.getRequest();
					int roleid=Integer.parseInt(request.getParameter("rolid"));
					
					PrintWriter out = response.getWriter();
					PageRoleDao pageroledao = new PageRoleDao();
					String pagerolelist=pageroledao.getrolepagelist(roleid);
					String pagelist=pageroledao.pageList(pagerolelist);
					
					//System.out.println("pagelist---"+pagelist);
			 data=pagelist;
					//data="Ashwini";
			 out.println(data);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
	
	public String pageRoleList() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PageRoleDao roledao = new PageRoleDao();
			int role_id=Integer.parseInt(request.getParameter("role_id"));
			request.getSession().setAttribute("role_id", role_id);
			//System.out.println("Count------>" +role_id);
			//int cnt = roledao.getTotalPageRecords(role_id);
			//int group_id=Integer.parseInt(roledao.getGroupRole(role_id));
			////System.out.println("Count------>" + cnt);
			String[] cols = {"", "page_id", "page_name","path", "status", "parent_id","level","sequence"};
			String[] dbcol= {"", "page_id", "page_name","path", "status", "parent_id","level","sequence"};
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			/*String sStart = request.getParameter("iDisplayStart");
			
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
			//total = roledao.getTotalPageRecords(role_id);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;*/
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			////System.out.println("sCol---"+sCol);
			//String sCol="4";
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
				//total = roledao.getTotalPageRecords(role_id);
				AMOUNT = amount;
				SEARCH_TERM = request.getParameter("sSearch");
				COL_NAME = colName;
				DIR = dir;
				START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			//int total, HttpServletRequest request,int roleid, String col,String dir
			////System.out.println("test----"+dbcol[Integer.parseInt(sCol)]);
			//result = roledao.getPageRoleList(total,request,role_id,dbcol[Integer.parseInt(sCol)],dir);
			result = roledao.getPageRoleList(total,request,role_id);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			//System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}
	private int pageroleid;
	public int getPageroleid() {
		return pageroleid;
	}

	public void setPageroleid(int pageroleid) {
		this.pageroleid = pageroleid;
	}

	private String roleiddetails;
	public String getRoleiddetails() {
		return roleiddetails;
	}

	public void setRoleiddetails(String roleiddetails) {
		this.roleiddetails = roleiddetails;
	}

	public String getPageRoleDetailsDisplayData(){
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("getPageRoleDetailsDisplayData() called"+pageroleid);
		setPageroleid(pageroleid);
		RoleDao dao = new RoleDao();
		role = dao.getEditedRole(pageroleid);
		//int roleid = Integer.parseInt(request.getParameter("roleiddetails"));
		//System.out.println("roleid"+roleid);
		return "success";
	}
	

	
	private String pageIdSuccessMessage;
	private String pageIdErrorMessage;
	private String onlySuccessMessage;
	public String getOnlySuccessMessage() {
		return onlySuccessMessage;
	}

	public void setOnlySuccessMessage(String onlySuccessMessage) {
		this.onlySuccessMessage = onlySuccessMessage;
	}

	public String getPageIdErrorMessage() {
		return pageIdErrorMessage;
	}

	public void setPageIdErrorMessage(String pageIdErrorMessage) {
		this.pageIdErrorMessage = pageIdErrorMessage;
	}

	public String getPageIdSuccessMessage() {
		return pageIdSuccessMessage;
	}

	public void setPageIdSuccessMessage(String pageIdSuccessMessage) {
		this.pageIdSuccessMessage = pageIdSuccessMessage;
	}
	public String saveMultiplePageRole(){
		System.out.println("saveMultiplePageRole() called"+pageroleid);
		setRoleiddetails("" + pageroleid);
		roleiddetails = "" + pageroleid;
		
		System.out.println("roleiddetailsData : " + roleiddetails);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("roleiddetails", pageroleid);
        System.out.println("getAddPagesRole#############");
		//HttpServletResponse response = ServletActionContext.getResponse();
        ArrayList<MenuPageRole> menuPageRoleList = new ArrayList<MenuPageRole>();
        List<Integer> pageIdList = new ArrayList<Integer>();
		//PrintWriter out = response.getWriter();
		//GroupMasterDao groupdao = new GroupMasterDao();
        try{
		//int roleid = Integer.parseInt(request.getParameter("pageroleId"));
        //System.out.println("roleid---1--"+roleid);
		int noOfRecords = Integer.parseInt(request.getParameter("noOfRecords"));
		System.out.println("noOfRecords--2---"+noOfRecords);
		
		//System.out.println("writeright---4-----"+writeright);
		int userid=(Integer)request.getSession().getAttribute("userid");
		
		pageIdSuccessMessage = "PageId ";
		onlySuccessMessage ="Page";
		for(int i=0; i<=noOfRecords; i++){
			//System.out.println("roleid---1--"+roleid);
			int pageid = Integer.parseInt(request.getParameter("pageid"+i));
			
			
			//System.out.println("pageid" + i + " : " +pageid);
			//System.out.println("pageid--2---"+pageid);
			int readright = 0;
			int writeright = 0;
			if(request.getParameter("readCB" + i) != null){
				readright = Integer.parseInt(request.getParameter("readCB" +i));
			}
			
			if(request.getParameter("writeCB" + i) != null){
				writeright = Integer.parseInt(request.getParameter("writeCB" + i));
			}
			
			if(readright != 0 || writeright != 0){
				if(i == noOfRecords){
					onlySuccessMessage +="";
					//pageIdSuccessMessage += "" + pageid;
				}
				else{
					//pageIdSuccessMessage += pageid + ", ";
					onlySuccessMessage +="";
				}
			}
			
			String roleData = request.getParameter("roleData" +i);
		
			
			//System.out.println("readright" + i + " : " +readright);
			//System.out.println("readright-----3---"+readright);
			//System.out.println("writeright" + i + " : " +writeright);
			
			if(readright == 1 || writeright == 1){
//				System.out.println("roleData : " + roleData + ", readright : " + readright + ", writeright : " + writeright);
				MenuPageRole menupage =new MenuPageRole();
				menupage.setRole_id(pageroleid);
				menupage.setPage_id(pageid);
				menupage.setReadaccess(readright);
				menupage.setCreateaccess(writeright);
				menupage.setDeleteaccess(writeright);
				menupage.setEditaccess(writeright);
				menupage.setViewdelete(writeright);				
				menupage.setCreated_by(userid);
				menupage.setStatus("ACTIVE");
				menupage.setCreated_date(new java.util.Date());
				menuPageRoleList.add(menupage);
				pageIdList.add(pageid);
			}	
			
		}
//		pageIdSuccessMessage += " Added Successfully.";
		onlySuccessMessage +=" Added Successfully.";
		
       
        
        
        System.out.println("No of edited records : " + menuPageRoleList.size());
//		for(MenuPageRole menuRoleObj : menuPageRoleList){
//			System.out.println("roleid : " + menuRoleObj.getRole_id());
//			System.out.println("pageid : " + menuRoleObj.getPage_id());
//			System.out.println("readaccess : " + menuRoleObj.getReadaccess());
//			System.out.println("writeaccess : " + menuRoleObj.getEditaccess());
//			System.out.println("**********************************");
//		}
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Map<String, Object>> list=null;
		session.beginTransaction();
		
		String sql1="select page_role_id as page_role_id from menu_page_role where page_id in (" ;
		for(int i = 0; i<pageIdList.size(); i++){
			if(i != pageIdList.size() - 1){
				sql1 += pageIdList.get(i) + ",";
			}
			else{
				sql1 += pageIdList.get(i);
			}
		}
		sql1 += ") and role_id=" + pageroleid + " and status='ACTIVE' and deleted_status=0";
		Query query = session.createSQLQuery(sql1);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		 list = query.list();
		if (list.size() > 0) {
			 for(int i=0;i<list.size();i++){
					Map<String, Object> rs = list.get(i);
					pageroleid=Integer.parseInt(rs.get("page_role_id").toString());
					System.out.println("pageroleid---1/////////////---"+pageroleid);
				}
			 
		}
		else{
			System.out.println("No existing page role id exist...its a new record...");	
			int noOfRowsInserted = 0;
			for(MenuPageRole menuPageRoleObj : menuPageRoleList){
				noOfRowsInserted += (Integer) session.save(menuPageRoleObj);
			}
			
			if(noOfRowsInserted == 0){
				//pageIdSuccessMessage = "";
				onlySuccessMessage = "";
			}
			
			session.getTransaction().commit();
			System.out.println( "pageIdSuccessMessage Saved successfully : " + pageIdSuccessMessage);
			System.out.println( "onlySuccessMessage Saved successfully : " + onlySuccessMessage);
		}
		}catch (Exception e) {
			//pageIdSuccessMessage = "";
			onlySuccessMessage = "";
			pageIdErrorMessage = "Failed To Save PageId";
			e.printStackTrace();
		}
		RoleDao dao = new RoleDao();
		role = dao.getEditedRole(pageroleid);
		return "success";
	}

	public String cancelMultiplePageRole(){
		System.out.println("cancelMultiplePageRole() called"+pageroleid);
		setRoleiddetails("" + pageroleid);
		roleiddetails = "" + pageroleid;
		
		System.out.println("roleiddetailsData : " + roleiddetails);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("roleiddetails", pageroleid);
		RoleDao dao = new RoleDao();
		role = dao.getEditedRole(pageroleid);
		return "success";
	}

}
