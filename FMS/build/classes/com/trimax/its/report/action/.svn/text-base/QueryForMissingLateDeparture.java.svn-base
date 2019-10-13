package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

public class QueryForMissingLateDeparture<ProcedureCall> extends ActionSupport {
	private Map<Integer, String> divisionlist;
	public String startdate;
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String execute() throws Exception {
		try{
            if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage")==null){
			
		}else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("0")){
		}
		else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("2")){
			addActionMessage("Record Not Inserted");
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage","0");
		}else{
			String msg=ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString();
			if (msg.equals("1")){
			addActionMessage("Record Sussfully Inserted");}
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage","0");
		}
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
	}catch (Exception e) {
		e.printStackTrace();
	}
		return "success";
}

	
	public String getDepot() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
        
        if(orgtypeid.equals("2")){
//        	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
        		List<String> l1 = dao.getDepotIdDC(parentId,orgchartid);
        		List<String> l2 = dao.getDepotNameDC(parentId,orgchartid);
        		String regionTypeAjaxString = "";
        		regionTypeAjaxString += "<option value='-1'>------select------</option>";
        		for (int i = 0; i < l1.size(); i++) {
        			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
        					+ ">" + l2.get(i).toString() + "</option>";
        		}
        		HttpServletResponse response = ServletActionContext.getResponse();
        		PrintWriter out;
        		try {
        			out = response.getWriter();
        			out.print(regionTypeAjaxString);
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
//        		return null;
//        	}
		
        }
        
      
        else if(orgtypeid.equals("1") && orgchartid.equals("1")){
        	List<String> l1 = dao.getDepotId(parentId);
    		List<String> l2 = dao.getDepotName(parentId);
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='-1'>------select------</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
    		}
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out;
    		try {
    			out = response.getWriter();
    			out.print(regionTypeAjaxString);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }

        else {
        	List<String> l1 = dao.getDepotId(parentId,orgchartid);
    		List<String> l2 = dao.getDepotName(parentId,orgchartid);
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='-1'>------select------</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
    		}
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out;
    		try {
    			out = response.getWriter();
    			out.print(regionTypeAjaxString);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        
		return null;

	}
	@SuppressWarnings("deprecation")
	public String savemissinglate() throws SQLException{
		Session session=null;
		Transaction trasation=null;
		String status=null;
		try{
		
		HttpServletRequest req=ServletActionContext.getRequest();
		String division=req.getParameter("division");
		String depot=req.getParameter("depot");
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
	
		System.out.println("date is===="+date1);
		//session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
		HibernateUtilVtu.rebuildSessionFactory();
		session = HibernateUtilVtu.getSession("hibernate-vtu.cfg.xml");
		Transaction trasation1=session.beginTransaction();
		SQLQuery  query =(SQLQuery)session.createSQLQuery("CALL LateDeparture(:depot_id,:run_date)").setParameter("depot_id",depot).setParameter("run_date",date1);
		int result = query.executeUpdate();
		trasation1.commit();
	/*	  String rbKey = String.valueOf(getSysParameterForVts());
			 NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
				com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
				VtsResponseNew alertresult = port.insertmissinglatedeparture(depot,date1,rbKey);
				System.out.println("Size of files u geting--------"+alertresult.getVtsDatamodelnew().size());
	        	VtsDataModelNew list = alertresult.getVtsDatamodelnew().get(0);
	        	   status=list.getStatus();*/
	        	 System.out.println("status is------"+result);
	        	if(result !=0){ ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "1");
	  	       addActionMessage("Record inserted Successfully");
	        	}else{
	        		ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "2");
	 		       addActionMessage("No Data Available To Insert ");
	        	}
	  		//session.close();
/*		session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
		 trasation=session.beginTransaction();
		System.out.println("getsession---"+session);
		
		SQLQuery  query =(SQLQuery)session.createSQLQuery("CALL LateDeparture(:depot_id,:run_date)").setParameter("depot_id",depot).setParameter("run_date",date1);
		int result = query.executeUpdate();
	
		System.out.println("result is====="+result);
		trasation.commit();*/
		}catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "2");
		       addActionMessage("Record not inserted ");
			
			
			//session.close();
			return "success";
		}
		finally {
			session.close();
			HibernateUtilVtu.admin.close();
		}
		
		
		return "success";
		}
	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			session.close();
		}
		return param;
	}
}