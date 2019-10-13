package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class EmployeeStatus extends ActionSupport{
	private Map<Integer, String> divisionlist;
	public String startdate;
    public String enddate;
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String execute() {
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
	public String getPerticularDepot() {
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
        		regionTypeAjaxString += "<option value='0'>------select------</option>";
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
    		regionTypeAjaxString += "<option value='0'>------select------</option>";
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
    		regionTypeAjaxString += "<option value='0'>------select------</option>";
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
	public String getemplyeename() {
		Map<Integer, String> EmplyeeMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HttpServletRequest req=ServletActionContext.getRequest();
			String id=req.getParameter("val");
			String regionTypeAjaxString = "";
			/*regionTypeAjaxString += "<option value=" + 0
					+ ">" + "--select--" + "</option>";*/
		try {
			Query query = session
					.createSQLQuery("select EMPLOYEE_ID,EMPLOYEE_NAME from employee where EMPLOYEE_ID="+id+"").addScalar("EMPLOYEE_ID", Hibernate.INTEGER).addScalar("EMPLOYEE_NAME", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			System.out.println(aliasToValueMapList.size());
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				//EmplyeeMap.put(Integer.parseInt(rs.get("EMPLOYEE_ID").toString()),rs.get("EMPLOYEE_NAME").toString());
			//regionTypeAjaxString += rs.get("EMPLOYEE_NAME").toString() + "</option>";
				regionTypeAjaxString +=rs.get("EMPLOYEE_NAME").toString();
				//System.out.println(rs.get("EMPLOYEE_ID"));
			}	
			HttpServletResponse res=ServletActionContext.getResponse();
			PrintWriter out;
			out=res.getWriter();
			out.print(regionTypeAjaxString);
			System.out.println(regionTypeAjaxString);
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		} 
		session.close();
		return null;
	}
	public String getempdesg() {
		Map<Integer, String> EmplyeeMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HttpServletRequest req=ServletActionContext.getRequest();
			String id=req.getParameter("val");
			String regionTypeAjaxString = "";
			/*regionTypeAjaxString += "<option value=" + 0
					+ ">" + "--select--" + "</option>";*/
		try {
			Query query = session
					.createSQLQuery("select EMPLOYEE_ID,EMPLOYEE_DESIGNATION from employee where EMPLOYEE_ID="+id+"").addScalar("EMPLOYEE_ID", Hibernate.INTEGER).addScalar("EMPLOYEE_DESIGNATION", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			System.out.println(aliasToValueMapList.size());
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				//EmplyeeMap.put(Integer.parseInt(rs.get("EMPLOYEE_ID").toString()),rs.get("EMPLOYEE_NAME").toString());
			//regionTypeAjaxString += rs.get("EMPLOYEE_NAME").toString() + "</option>";
				regionTypeAjaxString +=rs.get("EMPLOYEE_DESIGNATION").toString();
				//System.out.println(rs.get("EMPLOYEE_ID"));
			}	
			HttpServletResponse res=ServletActionContext.getResponse();
			PrintWriter out;
			out=res.getWriter();
			out.print(regionTypeAjaxString);
			System.out.println(regionTypeAjaxString);
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		} 
		session.close();
		return null;
	}
	public String gettokenno() {
		Map<Integer, String> EmplyeeMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HttpServletRequest req=ServletActionContext.getRequest();
			String chartid=req.getParameter("val");
			String regionTypeAjaxString = "";
			regionTypeAjaxString += "<option value=" + 0
					+ ">" + "--select--" + "</option>";
		try {
			Query query = session
					.createSQLQuery("select EMPLOYEE_ID,TOKEN from employee where org_chart_id="+chartid+" and status='ACTIVE'").addScalar("EMPLOYEE_ID", Hibernate.INTEGER).addScalar("TOKEN", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			System.out.println(aliasToValueMapList.size());
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				//EmplyeeMap.put(Integer.parseInt(rs.get("EMPLOYEE_ID").toString()),rs.get("EMPLOYEE_NAME").toString());
				regionTypeAjaxString += "<option value=" + rs.get("EMPLOYEE_ID").toString()
    					+ ">" + rs.get("TOKEN").toString() + "</option>";
				//System.out.println(rs.get("EMPLOYEE_ID"));
			}	
			HttpServletResponse res=ServletActionContext.getResponse();
			PrintWriter out;
			out=res.getWriter();
			out.print(regionTypeAjaxString);
			System.out.println(regionTypeAjaxString);
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		} 
		session.close();
		return null;
	}
	public String updateempstatus(){
		Session session=null;
	//============
		try {
		System.out.println("HIIIIII");
		HttpServletRequest req=ServletActionContext.getRequest();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-mm-yyyy");
        Date date = new Date();
		int division=Integer.parseInt(req.getParameter("division"));
		int depot=Integer.parseInt(req.getParameter("depot"));
		int  empno=Integer.parseInt(req.getParameter("tokennumber"));
		String status=req.getParameter("status");
		System.out.println("empno is++++"+empno);
	String remark=req.getParameter("remark").trim();
			String regionTypeAjaxString = "Record Sussfully Saved";
	
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			HttpServletResponse res=ServletActionContext.getResponse();
			PrintWriter out;
			out=res.getWriter();
			Transaction tx=session.beginTransaction();
	Employee employee=(Employee) session.get(Employee.class,empno);
              
			employee.setSTATUS(status);
			employee.setStatus_remark(remark);
			employee.setUpdated_by(req.getSession().getAttribute("userid").toString());
			employee.setUpdated_date(new Date());
			session.save(employee);
			tx.commit();
		
			
			
		  
		    
		} catch (Exception e) {
		
			 ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "2");
		       addActionMessage("Record not inserted ");
			
		   	session.close();
			e.printStackTrace();
			
		} 
		 ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "1");
	       addActionMessage("Record inserted Successfully");
		session.close();
		//redirect
	
		return "success";
	}
}
