package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class TripWisePassengerCount extends ActionSupport{
	private Map<Integer, String> divisionlist;
	private Map<Integer, String> shifttype;
	private String depotlist1;
		private String divisionlist1;
		 public String startdate;
		    public String getStartdate() {
			return startdate;
		}
		public String getDivisionlist1() {
				return divisionlist1;
			}
			public void setDivisionlist1(String divisionlist1) {
				this.divisionlist1 = divisionlist1;
			}
		public String getDepotlist1() {
				return depotlist1;
			}
			public void setDepotlist1(String depotlist1) {
				this.depotlist1 = depotlist1;
			}
		public String getEnddate() {
				return enddate;
			}
			public void setEnddate(String enddate) {
				this.enddate = enddate;
			}
		public void setStartdate(String startdate) {
			this.startdate = startdate;
		}
			public String enddate;
	public Map<Integer, String> getShifttype() {
		return shifttype;
	}
	public void setShifttype(Map<Integer, String> shifttype) {
		this.shifttype = shifttype;
	}
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		/* this.setShifttype(getdutytype());*/
		return "success";
	}
	public Map<Integer, String> getdutytype() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		
		try {
			Query query = session
					.createSQLQuery("select shift_type_id,shift_type_name from `shift_type` where status='ACTIVE' and deleted_status='0'").addScalar("shift_type_id", Hibernate.INTEGER).addScalar("shift_type_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("shift_type_id").toString()),rs.get("shift_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getShiftTypeName(int parentID) {
		  List list = new ArrayList();
			
        Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			try {
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
			 Common common = new Common();
				//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
				 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//					String depotdb=common.getDBResultStr(session1, sql1, "depotname");
					Query qry=session1.createSQLQuery(sql1).
							addScalar("depotname")
							.addScalar("central_ip",Hibernate.STRING)
							.addScalar("central_uname")
							.addScalar("central_pwd");
					
					qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = qry.list();	
						String depotdb="";
						String depotIp="";
						String user="";
						String password="";
						for (int j = 0; j < aliasToValueMapList.size(); j++) {
							Map<String, Object> aliasValue = aliasToValueMapList.get(j);
							 depotdb=aliasValue.get("depotname").toString();
							 depotIp=aliasValue.get("central_ip").toString();
							 user=aliasValue.get("central_uname").toString();
							 password=aliasValue.get("central_pwd").toString();
						}
					 Class.forName("com.mysql.jdbc.Driver");
					 System.out.println("username=="+user+"pwd=="+password);
					 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
					 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			
//			 String qry1 = "select sf.shift_type_id shift_type_id,sf.shift_type_name shift_type_name,s.schedule_number from schedule s " +
//						"inner join schedule_type st on st.schedule_type_id=s.schedule_type " +
//						"inner join shift_type sf on sf.schedule_type_id=st.schedule_type_id where s.schedule_number='"+scheduleno+"' " +
//						"and sf.status='Active' and sf.deleted_status='0'";
			 String qry1 = "select sf.shift_type_id shift_type_id,sf.shift_type_name shift_type_name,s.schedule_number from schedule s " +
						"inner join schedule_type st on st.schedule_type_id=s.schedule_type " +
						"inner join shift_type sf on sf.schedule_type_id=st.schedule_type_id where sf.status='Active' " +
						"and sf.deleted_status='0' group by sf.shift_type_id";
			rs=stmt.executeQuery(qry1);
//		System.out.println("rs==="+rs);
			 while(rs.next()){
//			System.out.println("hiiii--------"+rs.getString("shift_type_name"));
						list.add(rs.getString("shift_type_name"));
//					System.out.println("++++++"+list.size());
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
			
	}
	@SuppressWarnings("rawtypes")
	public List getShiftTypeId(int parentID) {
      List list = new ArrayList();
		
      Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		
		Transaction transaction  = null;
		HttpServletRequest req=ServletActionContext.getRequest();
		try {
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
		 Common common = new Common();
	//	String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
			Query qry=session1.createSQLQuery(sql1).
					addScalar("depotname")
					.addScalar("central_ip",Hibernate.STRING)
					.addScalar("central_uname")
					.addScalar("central_pwd");
			
			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = qry.list();	
				String depotdb="";
				String depotIp="";
				String user="";
				String password="";
				for (int j = 0; j < aliasToValueMapList.size(); j++) {
					Map<String, Object> aliasValue = aliasToValueMapList.get(j);
					 depotdb=aliasValue.get("depotname").toString();
					 depotIp=aliasValue.get("central_ip").toString();
					 user=aliasValue.get("central_uname").toString();
					 password=aliasValue.get("central_pwd").toString();
				}
			 Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			 System.out.println("connection........."+connection);
		 stmt = connection.createStatement();
		
//		String qry1 = "select sf.shift_type_id shift_type_id,sf.shift_type_name shift_type_name,s.schedule_number from schedule s " +
//				"inner join schedule_type st on st.schedule_type_id=s.schedule_type " +
//				"inner join shift_type sf on sf.schedule_type_id=st.schedule_type_id where s.schedule_number='"+scheduleno+"' " +
//				"and sf.status='Active' and sf.deleted_status='0'";
		String qry1 = "select sf.shift_type_id shift_type_id,sf.shift_type_name shift_type_name,s.schedule_number from schedule s " +
				"inner join schedule_type st on st.schedule_type_id=s.schedule_type " +
				"inner join shift_type sf on sf.schedule_type_id=st.schedule_type_id where sf.status='Active' " +
				"and sf.deleted_status='0' group by sf.shift_type_id";
		System.out.println("qry1"+qry1);
		rs=stmt.executeQuery(qry1);
	
		 while(rs.next()){
//		System.out.println("--------"+rs.getString("shift_type_id"));
					list.add(rs.getString("shift_type_id"));
//				System.out.println("++++++"+list.size());
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			return list;
			
	}
	public String getShiftType() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
	
		int parentId = Integer.parseInt(req.getParameter("depot"));
		//String scheduleno= req.getParameter("scheduleid");
		 //String dat = req.getParameter("startdate");
		 //System.out.println("dat==="+dat);
		 Common comm = new Common();
	//	String dat1=comm. getDateFromPicker(dat);
        		List<String> l1 = this.getShiftTypeId(parentId);
        		List<String> l2 = this.getShiftTypeName(parentId);
        		String regionTypeAjaxString = "";
        		regionTypeAjaxString += "<option value='0'>All</option>";
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
		
        		return null;
        }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getScheduleName(int parentID,String shiftid) {
		  List list = new ArrayList();
			
          Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			try {
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
			 Common common = new Common();
			//	String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
				 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//					String depotdb=common.getDBResultStr(session1, sql1, "depotname");
					Query qry=session1.createSQLQuery(sql1).
							addScalar("depotname")
							.addScalar("central_ip",Hibernate.STRING)
							.addScalar("central_uname")
							.addScalar("central_pwd");
					
					qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = qry.list();	
						String depotdb="";
						String depotIp="";
						String user="";
						String password="";
						for (int j = 0; j < aliasToValueMapList.size(); j++) {
							Map<String, Object> aliasValue = aliasToValueMapList.get(j);
							 depotdb=aliasValue.get("depotname").toString();
							 depotIp=aliasValue.get("central_ip").toString();
							 user=aliasValue.get("central_uname").toString();
							 password=aliasValue.get("central_pwd").toString();
						}
					 Class.forName("com.mysql.jdbc.Driver");
					 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
					 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			
//			String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
//				" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0";
			 String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule s " +
				 		"inner join  schedule_type st on st.schedule_type_id=s.schedule_type " +
				 		"inner join shift_type sft on sft.schedule_type_id=st.schedule_type_id  " +
				 		"where (s.status='ACTIVE' or s.status='NEW') and (s.current_Status='APPROVED' or s.current_Status='CASE WORKER')  " +
				 		"and s.deleted_Status=0 and sft.shift_type_id='"+shiftid+"'";
			 System.out.println(qry1);
			rs=stmt.executeQuery(qry1);
//		System.out.println("rs==="+rs);
			 while(rs.next()){
//			System.out.println("hiiii--------"+rs.getString("scheduleNumber"));
						list.add(rs.getString("scheduleNumber"));
//					System.out.println("++++++"+list.size());
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
			
	}
	@SuppressWarnings("rawtypes")
	public List getScheduleId(int parentID,String shiftid) {
      List list = new ArrayList();
		
      Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		
		Transaction transaction  = null;
		HttpServletRequest req=ServletActionContext.getRequest();
		try {
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
		 Common common = new Common();
		//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
			Query qry=session1.createSQLQuery(sql1).
					addScalar("depotname")
					.addScalar("central_ip",Hibernate.STRING)
					.addScalar("central_uname")
					.addScalar("central_pwd");
			
			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = qry.list();	
				String depotdb="";
				String depotIp="";
				String user="";
				String password="";
				for (int j = 0; j < aliasToValueMapList.size(); j++) {
					Map<String, Object> aliasValue = aliasToValueMapList.get(j);
					 depotdb=aliasValue.get("depotname").toString();
					 depotIp=aliasValue.get("central_ip").toString();
					 user=aliasValue.get("central_uname").toString();
					 password=aliasValue.get("central_pwd").toString();
				}
			 Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			 System.out.println("connection........."+connection);
		 stmt = connection.createStatement();
		
//		String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
//				" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0";

		 String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule s " +
		 		"inner join  schedule_type st on st.schedule_type_id=s.schedule_type " +
		 		"inner join shift_type sft on sft.schedule_type_id=st.schedule_type_id  " +
		 		"where (s.status='ACTIVE' or s.status='NEW') and (s.current_Status='APPROVED' or s.current_Status='CASE WORKER')  " +
		 		"and s.deleted_Status=0 and sft.shift_type_id='"+shiftid+"'";
//		System.out.println("qry1"+qry1);
		rs=stmt.executeQuery(qry1);
	
		 while(rs.next()){
//		System.out.println("--------"+rs.getString("scheduleNumber"));
					list.add(rs.getString("scheduleNumber"));
//				System.out.println("++++++"+list.size());
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			return list;
			
	}
	public String getScheduleNo() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
	
		int parentId = Integer.parseInt(req.getParameter("val"));
		String shiftid = req.getParameter("shiftid");
		 //System.out.println("dat==="+dat);
		 Common comm = new Common();
	//	String dat1=comm. getDateFromPicker(dat);
        		List<String> l1 = this.getScheduleId(parentId,shiftid);
        		List<String> l2 = this.getScheduleName(parentId,shiftid);
        		String regionTypeAjaxString = "";
        		regionTypeAjaxString += "<option value='0'>All</option>";
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
		
        		return null;
        }
	String regionTypeAjaxString = "";

	public String getTripWisePassengerCount(){
		try {
			Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			Transaction transaction  = null;
			Common common = new Common();
			HttpServletRequest req=ServletActionContext.getRequest();
			String date1=common.getDateFromPicker(startdate);
			 String enddate = req.getParameter("enddate");
			String date2=common.getDateFromPicker(enddate);
		    String division1= divisionlist1;
		    String depot1= depotlist1;
		    String scheduleid = req.getParameter("scheduleid");
		    String days = req.getParameter("days");
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);

	    String depot=req.getParameter("depotlist1");
       
       String shiftid = req.getParameter("shiftid");
	    int dayoutshift1=0;
	   // int dayoutshift2=0;
	    if(!division1.equalsIgnoreCase("5")){
		    if(shiftid.equalsIgnoreCase("4")){
		    	dayoutshift1=1;
		    }
		    if(shiftid.equalsIgnoreCase("5")){
		    	dayoutshift1=2;
		    }
		    if(shiftid.equalsIgnoreCase("2")){
		    	dayoutshift1=1;
		    }
		    if(shiftid.equalsIgnoreCase("3")){
		    	dayoutshift1=2;
		    }
	    }else{
	    	dayoutshift1=Integer.parseInt(shiftid);
	    }
       String qry="";
       
       if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")){
   		qry=" and ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and ticket.shift_no ='"+dayoutshift1+"'  ";
   		
   	}else if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0")){
   		qry=" and ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"'  ";
   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0")){
   		qry=" and DAYNAME(ticket_date)='"+days+"' and ticket.shift_no ='"+dayoutshift1+"'";
   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& shiftid.equalsIgnoreCase("0")){
   		qry=" and DAYNAME(ticket_date)='"+days+"'  ";
   	}
   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0")){
   		qry=" and ticket.schedule_no ='"+scheduleid+"' and ticket.shift_no ='"+dayoutshift1+"'  ";
   	}
   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
   		qry=" and ticket.schedule_no ='"+scheduleid+"'  ";
   	
   	}
   	else if(!shiftid.equalsIgnoreCase("0") ){
		qry=" and ticket.shift_no ='"+dayoutshift1+"' ";
	
	}
   	else {
   		qry="";
   	}
       
//       if(scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
//   		qry="";
//   		
//   	}else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
//   		qry="and ticket.schedule_no ='"+scheduleid+"'  ";
//   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")){
//   		qry="and DAYNAME(ticket_date)='"+days+"'  ";
//   	}else{
//   		qry="and ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"'  ";
//   	}
	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	
		//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
//			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
			Query qry1=session1.createSQLQuery(sql1).
					addScalar("depotname")
					.addScalar("central_ip",Hibernate.STRING)
					.addScalar("central_uname")
					.addScalar("central_pwd");
			
			qry1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = qry1.list();	
				String depotdb="";
				String depotIp="";
				String user="";
				String password="";
				for (int j = 0; j < aliasToValueMapList.size(); j++) {
					Map<String, Object> aliasValue = aliasToValueMapList.get(j);
					 depotdb=aliasValue.get("depotname").toString();
					 depotIp=aliasValue.get("central_ip").toString();
					 user=aliasValue.get("central_uname").toString();
					 password=aliasValue.get("central_pwd").toString();
				}
			 Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			 System.out.println("connection........."+connection);
		 stmt = connection.createStatement();
		
		/* String sql="SELECT ticket_date,DAYNAME(ticket_date) weekda,ticket.schedule_no,cnt,case  DAYNAME(ticket_date)  when '' then -2  when 'monday' then 1  when 'tuesday' then 2 "+  
		 "when 'wednesday' then 3  when 'thursday' then 4 when 'friday' then 5  when 'saturday' then 6  when 'sunday' then -1 end as day_nr,"+
		 "SUM(CASE WHEN trip_no=1   THEN px_total_amount ELSE 0 END) T1,SUM(CASE WHEN trip_no=1   THEN px_count ELSE 0 END) T1p,"+
		 "SUM(CASE WHEN trip_no=2   THEN px_total_amount ELSE 0 END) T2,SUM(CASE WHEN trip_no=2   THEN px_count ELSE 0 END) T2p,"+
		 "SUM(CASE WHEN trip_no=3   THEN px_total_amount ELSE 0 END) T3,SUM(CASE WHEN trip_no=3   THEN px_count ELSE 0 END) T3p,"+
		 "SUM(CASE WHEN trip_no=4   THEN px_total_amount ELSE 0 END) T4,SUM(CASE WHEN trip_no=4   THEN px_count ELSE 0 END) T4p,"+
		 "SUM(CASE WHEN trip_no=5   THEN px_total_amount ELSE 0 END) T5,SUM(CASE WHEN trip_no=5   THEN px_count ELSE 0 END) T5p,"+
		 "SUM(CASE WHEN trip_no=6   THEN px_total_amount ELSE 0 END) T6,SUM(CASE WHEN trip_no=6   THEN px_count ELSE 0 END) T6p,"+
		 "SUM(CASE WHEN trip_no=7   THEN px_total_amount ELSE 0 END) T7,SUM(CASE WHEN trip_no=7   THEN px_count ELSE 0 END) T7p,"+
		 "SUM(CASE WHEN trip_no=8   THEN px_total_amount ELSE 0 END) T8,SUM(CASE WHEN trip_no=8   THEN px_count ELSE 0 END) T8p,"+
		 "SUM(CASE WHEN trip_no=9   THEN px_total_amount ELSE 0 END) T9,SUM(CASE WHEN trip_no=9   THEN px_count ELSE 0 END) T9p,"+
		 "SUM(CASE WHEN trip_no=10   THEN px_total_amount ELSE 0 END) T10,SUM(CASE WHEN trip_no=10   THEN px_count ELSE 0 END) T10p,"+
		 "SUM(CASE WHEN trip_no=11   THEN px_total_amount ELSE 0 END) T11,SUM(CASE WHEN trip_no=11   THEN px_count ELSE 0 END) T11p,"+
		 "SUM(CASE WHEN trip_no=12   THEN px_total_amount ELSE 0 END) T12,SUM(CASE WHEN trip_no=12   THEN px_count ELSE 0 END) T12p,"+
		 "SUM(CASE WHEN trip_no=13   THEN px_total_amount ELSE 0 END) T13,SUM(CASE WHEN trip_no=13   THEN px_count ELSE 0 END) T13p,"+
		 "SUM(CASE WHEN trip_no=14   THEN px_total_amount ELSE 0 END) T14,SUM(CASE WHEN trip_no=14   THEN px_count ELSE 0 END) T14p,"+
		 "SUM(CASE WHEN trip_no=15   THEN px_total_amount ELSE 0 END) T15,SUM(CASE WHEN trip_no=15   THEN px_count ELSE 0 END) T15p,"+
		 "SUM(CASE WHEN trip_no=16   THEN px_total_amount ELSE 0 END) T16,SUM(CASE WHEN trip_no=16   THEN px_count ELSE 0 END) T16p,"+
		 "SUM(CASE WHEN trip_no=17   THEN px_total_amount ELSE 0 END) T17,SUM(CASE WHEN trip_no=17   THEN px_count ELSE 0 END) T17p,"+
		 "SUM(CASE WHEN trip_no=18   THEN px_total_amount ELSE 0 END) T18,SUM(CASE WHEN trip_no=18   THEN px_count ELSE 0 END) T18p,"+
		 "SUM(CASE WHEN trip_no=19   THEN px_total_amount ELSE 0 END) T19,SUM(CASE WHEN trip_no=19   THEN px_count ELSE 0 END) T19p,"+
		 "SUM(CASE WHEN trip_no=20   THEN px_total_amount ELSE 0 END) T20,SUM(CASE WHEN trip_no=20   THEN px_count ELSE 0 END) T20p,"+
		 "SUM(CASE WHEN trip_no=21   THEN px_total_amount ELSE 0 END) T21,SUM(CASE WHEN trip_no=21   THEN px_count ELSE 0 END) T21p,"+
		 "SUM(CASE WHEN trip_no=22   THEN px_total_amount ELSE 0 END) T22,SUM(CASE WHEN trip_no=22   THEN px_count ELSE 0 END) T22p,"+
		 "SUM(CASE WHEN trip_no=23   THEN px_total_amount ELSE 0 END) T23,SUM(CASE WHEN trip_no=23   THEN px_count ELSE 0 END) T23p,"+
		 "SUM(CASE WHEN trip_no=24   THEN px_total_amount ELSE 0 END) T24,SUM(CASE WHEN trip_no=24   THEN px_count ELSE 0 END) T24p,"+
		 "SUM(CASE WHEN trip_no=25   THEN px_total_amount ELSE 0 END) T25,SUM(CASE WHEN trip_no=25   THEN px_count ELSE 0 END) T25p,"+
		 "SUM(CASE WHEN trip_no=26   THEN px_total_amount ELSE 0 END) T26,SUM(CASE WHEN trip_no=26   THEN px_count ELSE 0 END) T26p,"+
		 "SUM(CASE WHEN trip_no=27   THEN px_total_amount ELSE 0 END) T27,SUM(CASE WHEN trip_no=27   THEN px_count ELSE 0 END) T27p,"+
		 "SUM(CASE WHEN trip_no=28   THEN px_total_amount ELSE 0 END) T28,SUM(CASE WHEN trip_no=28   THEN px_count ELSE 0 END) T28p,"+
		 "SUM(CASE WHEN trip_no=29   THEN px_total_amount ELSE 0 END) T29,SUM(CASE WHEN trip_no=29   THEN px_count ELSE 0 END) T29p,"+
		 "SUM(CASE WHEN trip_no=30   THEN px_total_amount ELSE 0 END) T30,SUM(CASE WHEN trip_no=30   THEN px_count ELSE 0 END) T30p "+
		 "FROM `ticket` left join (SELECT DAYNAME(ticket_date) weekda,schedule_no,count(distinct(trip_no)) cnt FROM `ticket`  " +
	 		"WHERE  ticket_date between '"+date1+"' and '"+date2+"' "+qry+" and ticket_no!=0 and px_total_amount!=0 group by schedule_no,weekda) cc " +
	 		"on cc.schedule_no=ticket.schedule_no and cc.weekda=DAYNAME(ticket_date) " +
	 		"WHERE  ticket_date between '"+date1+"' and '"+date2+"' "+qry+"  and ticket_no!=0 group by schedule_no,weekda order by  " +
	 		"schedule_no,day_nr,weekda";*/
		 String sql="select schedule_no,no_days,shift_type_name,cnt,T1,T1p,ifnull(T1distance,0)T1distance,ifnull(T1/T1distance,0) T1epkm,"+
				 "T2,T2p,ifnull(T2distance,0)T2distance,ifnull(T2/T2distance,0) T2epkm,T3,T3p,ifnull(T3distance,0)T3distance,ifnull(T3/T3distance,0) T3epkm,"+
				 "T4,T4p,ifnull(T4distance,0)T4distance,ifnull(T4/T4distance,0) T4epkm,T5,T5p,ifnull(T5distance,0)T5distance,ifnull(T5/T5distance,0) T5epkm,"+
				 "T6,T6p,ifnull(T6distance,0)T6distance,ifnull(T6/T6distance,0) T6epkm,T7,T7p,ifnull(T7distance,0)T7distance,ifnull(T7/T7distance,0) T7epkm,"+
				 "T8,T8p,ifnull(T8distance,0)T8distance,ifnull(T8/T8distance,0) T8epkm,T9,T9p,ifnull(T9distance,0)T9distance,ifnull(T9/T9distance,0) T9epkm,"+
				 "T10,T10p,ifnull(T10distance,0)T10distance,ifnull(T10/T10distance,0) T10epkm,"+
				 "T11,T11p,ifnull(T11distance,0)T11distance,ifnull(T11/T11distance,0) T11epkm,T12,T12p,ifnull(T12distance,0)T12distance,ifnull(T12/T12distance,0) T12epkm,"+
				 "T13,T13p,ifnull(T13distance,0)T13distance,ifnull(T13/T13distance,0) T13epkm,"+
				 "T14,T14p,ifnull(T14distance,0)T14distance,ifnull(T14/T14distance,0) T14epkm,T15,T15p,ifnull(T15distance,0)T15distance,ifnull(T15/T15distance,0) T15epkm,"+
				 "T16,T16p,ifnull(T16distance,0)T16distance,ifnull(T16/T16distance,0) T16epkm,"+
				 "T17,T17p,ifnull(T17distance,0)T17distance,ifnull(T17/T17distance,0) T17epkm,T18,T18p,ifnull(T18distance,0)T18distance,ifnull(T18/T18distance,0) T18epkm,"+
				 "T19,T19p,ifnull(T19distance,0)T19distance,ifnull(T19/T19distance,0) T19epkm,"+
				 "T20,T20p,ifnull(T20distance,0)T20distance,ifnull(T20/T20distance,0) T20epkm,T21,T21p,ifnull(T21distance,0)T21distance,ifnull(T21/T21distance,0) T21epkm,"+
				 /*"select schedule_no,no_days,shift_type_name,cnt,T1,T1p,T1distance,T1/T1distance T1epkm,T2,T2p,T2distance,T2/T2distance T2epkm,T3,T3p,T3distance,T3/T3distance T3epkm,"+
		 "T4,T4p,T4distance,T4/T4distance T4epkm,T5,T5p,T5distance,T5/T5distance T5epkm,T6,T6p,T6distance,T6/T6distance T6epkm,T7,T7p,T7distance,T7/T7distance T7epkm,"+
		 "T8,T8p,T8distance,T8/T8distance T8epkm,T9,T9p,T9distance,T9/T9distance T9epkm,T10,T10p,T10distance,T10/T10distance T10epkm,"+
		 "T11,T11p,T11distance,T11/T11distance T11epkm,T12,T12p,T12distance,T12/T12distance T12epkm,T13,T13p,T13distance,T13/T13distance T13epkm,"+
		 "T14,T14p,T14distance,T14/T14distance T14epkm,T15,T15p,T15distance,T15/T15distance T15epkm,T16,T16p,T16distance,T16/T16distance T16epkm,"+
		 "T17,T17p,T17distance,T17/T17distance T17epkm,T18,T18p,T18distance,T18/T18distance T18epkm,T19,T19p,T19distance,T19/T19distance T19epkm,"+
		 "T20,T20p,T20distance,T20/T20distance T20epkm,T21,T21p,T21distance,T21/T21distance T21epkm," +*/
		 "T22,T22p,ifnull(T22distance,0)T22distance,ifnull(T22/T22distance,0) T22epkm,"+
		 "T23,T23p,ifnull(T23distance,0)T23distance,ifnull(T23/T23distance,0) T23epkm,T24,T24p,ifnull(T24distance,0)T24distance,ifnull(T24/T24distance,0) T24epkm,"+
		 "T25,T25p,ifnull(T25distance,0)T25distance,ifnull(T25/T25distance,0) T25epkm,T26,T26p,ifnull(T26distance,0)T26distance,ifnull(T26/T26distance,0) T26epkm,"+
		 "T27,T27p,ifnull(T27distance,0)T27distance,ifnull(T27/T27distance,0) T27epkm,T28,T28p,ifnull(T28distance,0)T28distance,ifnull(T28/T28distance,0) T28epkm,"+
		 "T29,T29p,ifnull(T29distance,0)T29distance,ifnull(T29/T29distance,0) T29epkm,T30,T30p,ifnull(T30distance,0)T30distance,ifnull(T30/T30distance,0) T30epkm "+
		 "from(SELECT ticket.schedule_no,count(DISTINCT(ticket_date)) as no_days,shift_type.shift_type_name,ifnull(cnt,0)as cnt,"+
		 "SUM(CASE WHEN trip_no=1   THEN px_total_amount ELSE 0 END) T1,SUM(CASE WHEN trip_no=1   THEN px_count ELSE 0 END) T1p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=1 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T1distance,"+
		 "SUM(CASE WHEN trip_no=2   THEN px_total_amount ELSE 0 END) T2,SUM(CASE WHEN trip_no=2   THEN px_count ELSE 0 END) T2p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=2 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T2distance,"+
		 "SUM(CASE WHEN trip_no=3   THEN px_total_amount ELSE 0 END) T3,SUM(CASE WHEN trip_no=3   THEN px_count ELSE 0 END) T3p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=3 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T3distance,"+
		 "SUM(CASE WHEN trip_no=4   THEN px_total_amount ELSE 0 END) T4,SUM(CASE WHEN trip_no=4   THEN px_count ELSE 0 END) T4p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=4 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T4distance,"+
		 "SUM(CASE WHEN trip_no=5   THEN px_total_amount ELSE 0 END) T5,SUM(CASE WHEN trip_no=5   THEN px_count ELSE 0 END) T5p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=5 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T5distance,"+
		 "SUM(CASE WHEN trip_no=6   THEN px_total_amount ELSE 0 END) T6,SUM(CASE WHEN trip_no=6   THEN px_count ELSE 0 END) T6p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=6 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T6distance,"+
		 "SUM(CASE WHEN trip_no=7   THEN px_total_amount ELSE 0 END) T7,SUM(CASE WHEN trip_no=7   THEN px_count ELSE 0 END) T7p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=7 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T7distance,"+
		 "SUM(CASE WHEN trip_no=8   THEN px_total_amount ELSE 0 END) T8,SUM(CASE WHEN trip_no=8   THEN px_count ELSE 0 END) T8p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=8 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T8distance,"+
		 "SUM(CASE WHEN trip_no=9   THEN px_total_amount ELSE 0 END) T9,SUM(CASE WHEN trip_no=9   THEN px_count ELSE 0 END) T9p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=9 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T9distance,"+
		 "SUM(CASE WHEN trip_no=10   THEN px_total_amount ELSE 0 END) T10,SUM(CASE WHEN trip_no=10   THEN px_count ELSE 0 END) T10p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=10 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T10distance,"+
		 "SUM(CASE WHEN trip_no=11   THEN px_total_amount ELSE 0 END) T11,SUM(CASE WHEN trip_no=11   THEN px_count ELSE 0 END) T11p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=11 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T11distance,"+
		 "SUM(CASE WHEN trip_no=12   THEN px_total_amount ELSE 0 END) T12,SUM(CASE WHEN trip_no=12   THEN px_count ELSE 0 END) T12p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=12 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T12distance,"+
		 "SUM(CASE WHEN trip_no=13   THEN px_total_amount ELSE 0 END) T13,SUM(CASE WHEN trip_no=13   THEN px_count ELSE 0 END) T13p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=13 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T13distance,"+
		 "SUM(CASE WHEN trip_no=14   THEN px_total_amount ELSE 0 END) T14,SUM(CASE WHEN trip_no=14   THEN px_count ELSE 0 END) T14p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=14 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T14distance,"+
		 "SUM(CASE WHEN trip_no=15   THEN px_total_amount ELSE 0 END) T15,SUM(CASE WHEN trip_no=15   THEN px_count ELSE 0 END) T15p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=15 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T15distance,"+
		 "SUM(CASE WHEN trip_no=16   THEN px_total_amount ELSE 0 END) T16,SUM(CASE WHEN trip_no=16   THEN px_count ELSE 0 END) T16p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=16 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T16distance,"+
		 "SUM(CASE WHEN trip_no=17   THEN px_total_amount ELSE 0 END) T17,SUM(CASE WHEN trip_no=17   THEN px_count ELSE 0 END) T17p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=17 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T17distance,"+
		 "SUM(CASE WHEN trip_no=18   THEN px_total_amount ELSE 0 END) T18,SUM(CASE WHEN trip_no=18   THEN px_count ELSE 0 END) T18p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=18 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T18distance,"+
		 "SUM(CASE WHEN trip_no=19   THEN px_total_amount ELSE 0 END) T19,SUM(CASE WHEN trip_no=19   THEN px_count ELSE 0 END) T19p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=19 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T19distance,"+
		 "SUM(CASE WHEN trip_no=20   THEN px_total_amount ELSE 0 END) T20,SUM(CASE WHEN trip_no=20   THEN px_count ELSE 0 END) T20p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=20 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T20distance,"+
		 "SUM(CASE WHEN trip_no=21   THEN px_total_amount ELSE 0 END) T21,SUM(CASE WHEN trip_no=21   THEN px_count ELSE 0 END) T21p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=21 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T21distance,"+
		 "SUM(CASE WHEN trip_no=22   THEN px_total_amount ELSE 0 END) T22,SUM(CASE WHEN trip_no=22   THEN px_count ELSE 0 END) T22p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=22 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T22distance,"+
		 "SUM(CASE WHEN trip_no=23   THEN px_total_amount ELSE 0 END) T23,SUM(CASE WHEN trip_no=23   THEN px_count ELSE 0 END) T23p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=23 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T23distance,"+
		 "SUM(CASE WHEN trip_no=24   THEN px_total_amount ELSE 0 END) T24,SUM(CASE WHEN trip_no=24   THEN px_count ELSE 0 END) T24p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=24 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T24distance,"+
		 "SUM(CASE WHEN trip_no=25   THEN px_total_amount ELSE 0 END) T25,SUM(CASE WHEN trip_no=25   THEN px_count ELSE 0 END) T25p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=25 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T25distance,"+
		 "SUM(CASE WHEN trip_no=26   THEN px_total_amount ELSE 0 END) T26,SUM(CASE WHEN trip_no=26   THEN px_count ELSE 0 END) T26p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=26 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T26distance,"+
		 "SUM(CASE WHEN trip_no=27   THEN px_total_amount ELSE 0 END) T27,SUM(CASE WHEN trip_no=27   THEN px_count ELSE 0 END) T27p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=27 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T27distance,"+
		 "SUM(CASE WHEN trip_no=28   THEN px_total_amount ELSE 0 END) T28,SUM(CASE WHEN trip_no=28   THEN px_count ELSE 0 END) T28p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=28 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T28distance,"+
		 "SUM(CASE WHEN trip_no=29   THEN px_total_amount ELSE 0 END) T29,SUM(CASE WHEN trip_no=29   THEN px_count ELSE 0 END) T29p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=29 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T29distance,"+
		 "SUM(CASE WHEN trip_no=30   THEN px_total_amount ELSE 0 END) T30,SUM(CASE WHEN trip_no=30   THEN px_count ELSE 0 END) T30p,"+
		 "(select distance from schedule_details where form_four_id=ticket.form_four_id and shift_type_id=ticket.shift_no  and trip_number=30 AND deleted_status=0)*count(DISTINCT(ticket_date)) as T30distance "+
		 "FROM `ticket` inner join shift_type on ticket.shift_no=shift_type.shift_type_id "+
		 "inner join schedule_details on ticket.form_four_Id=schedule_details.form_four_id "+
		 "left join (SELECT DAYNAME(ticket_date) weekda,schedule_no,count(distinct(trip_no)) cnt FROM `ticket` "+ 
		 "WHERE  ticket_date between '"+date1+"' and '"+date2+"' "+qry+" "+
		 "and ticket_no!=0 and "+
		 "px_total_amount!=0 group by ticket.shift_no,ticket.schedule_no) cc on cc.schedule_no=ticket.schedule_no and cc.weekda=DAYNAME(ticket_date) "+
		 "WHERE  ticket_date between '"+date1+"' and '"+date2+"' "+qry+" "+ 
		 "and ticket_no!=0 "+
		 "group by ticket.shift_no,ticket.schedule_no order by  schedule_no,ticket.shift_no)vv";
		 
		// System.out.println("QRY======="+qry);
		/* String sql="SELECT ticket.schedule_no,count(DISTINCT(ticket_date)) as no_days,shift_type.shift_type_name,ifnull(cnt,0)as cnt,"+
		 "SUM(CASE WHEN trip_no=1   THEN px_total_amount ELSE 0 END) T1,SUM(CASE WHEN trip_no=1   THEN px_count ELSE 0 END) T1p,"+
		 "SUM(CASE WHEN trip_no=2   THEN px_total_amount ELSE 0 END) T2,SUM(CASE WHEN trip_no=2   THEN px_count ELSE 0 END) T2p,"+
		 "SUM(CASE WHEN trip_no=3   THEN px_total_amount ELSE 0 END) T3,SUM(CASE WHEN trip_no=3   THEN px_count ELSE 0 END) T3p,"+
		 "SUM(CASE WHEN trip_no=4   THEN px_total_amount ELSE 0 END) T4,SUM(CASE WHEN trip_no=4   THEN px_count ELSE 0 END) T4p,"+
		 "SUM(CASE WHEN trip_no=5   THEN px_total_amount ELSE 0 END) T5,SUM(CASE WHEN trip_no=5   THEN px_count ELSE 0 END) T5p,"+	
		 "SUM(CASE WHEN trip_no=6   THEN px_total_amount ELSE 0 END) T6,SUM(CASE WHEN trip_no=6   THEN px_count ELSE 0 END) T6p,"+
		 "SUM(CASE WHEN trip_no=7   THEN px_total_amount ELSE 0 END) T7,SUM(CASE WHEN trip_no=7   THEN px_count ELSE 0 END) T7p,"+
		 "SUM(CASE WHEN trip_no=8   THEN px_total_amount ELSE 0 END) T8,SUM(CASE WHEN trip_no=8   THEN px_count ELSE 0 END) T8p,"+
		 "SUM(CASE WHEN trip_no=9   THEN px_total_amount ELSE 0 END) T9,SUM(CASE WHEN trip_no=9   THEN px_count ELSE 0 END) T9p,"+
		 "SUM(CASE WHEN trip_no=10   THEN px_total_amount ELSE 0 END) T10,SUM(CASE WHEN trip_no=10   THEN px_count ELSE 0 END) T10p,"+
		 "SUM(CASE WHEN trip_no=11   THEN px_total_amount ELSE 0 END) T11,SUM(CASE WHEN trip_no=11   THEN px_count ELSE 0 END) T11p,"+
		 "SUM(CASE WHEN trip_no=12   THEN px_total_amount ELSE 0 END) T12,SUM(CASE WHEN trip_no=12   THEN px_count ELSE 0 END) T12p,"+
		 "SUM(CASE WHEN trip_no=13   THEN px_total_amount ELSE 0 END) T13,SUM(CASE WHEN trip_no=13   THEN px_count ELSE 0 END) T13p,"+
		 "SUM(CASE WHEN trip_no=14   THEN px_total_amount ELSE 0 END) T14,SUM(CASE WHEN trip_no=14   THEN px_count ELSE 0 END) T14p,"+
		 "SUM(CASE WHEN trip_no=15   THEN px_total_amount ELSE 0 END) T15,SUM(CASE WHEN trip_no=15   THEN px_count ELSE 0 END) T15p,"+
		 "SUM(CASE WHEN trip_no=16   THEN px_total_amount ELSE 0 END) T16,SUM(CASE WHEN trip_no=16   THEN px_count ELSE 0 END) T16p,"+
		 "SUM(CASE WHEN trip_no=17   THEN px_total_amount ELSE 0 END) T17,SUM(CASE WHEN trip_no=17   THEN px_count ELSE 0 END) T17p,"+
		 "SUM(CASE WHEN trip_no=18   THEN px_total_amount ELSE 0 END) T18,SUM(CASE WHEN trip_no=18   THEN px_count ELSE 0 END) T18p,"+
		 "SUM(CASE WHEN trip_no=19   THEN px_total_amount ELSE 0 END) T19,SUM(CASE WHEN trip_no=19   THEN px_count ELSE 0 END) T19p,"+
		 "SUM(CASE WHEN trip_no=20   THEN px_total_amount ELSE 0 END) T20,SUM(CASE WHEN trip_no=20   THEN px_count ELSE 0 END) T20p,"+
		 "SUM(CASE WHEN trip_no=21   THEN px_total_amount ELSE 0 END) T21,SUM(CASE WHEN trip_no=21   THEN px_count ELSE 0 END) T21p,"+
		 "SUM(CASE WHEN trip_no=22   THEN px_total_amount ELSE 0 END) T22,SUM(CASE WHEN trip_no=22   THEN px_count ELSE 0 END) T22p,"+
		 "SUM(CASE WHEN trip_no=23   THEN px_total_amount ELSE 0 END) T23,SUM(CASE WHEN trip_no=23   THEN px_count ELSE 0 END) T23p,"+
		 "SUM(CASE WHEN trip_no=24   THEN px_total_amount ELSE 0 END) T24,SUM(CASE WHEN trip_no=24   THEN px_count ELSE 0 END) T24p,"+
		 "SUM(CASE WHEN trip_no=25   THEN px_total_amount ELSE 0 END) T25,SUM(CASE WHEN trip_no=25   THEN px_count ELSE 0 END) T25p,"+
		 "SUM(CASE WHEN trip_no=26   THEN px_total_amount ELSE 0 END) T26,SUM(CASE WHEN trip_no=26   THEN px_count ELSE 0 END) T26p,"+
		 "SUM(CASE WHEN trip_no=27   THEN px_total_amount ELSE 0 END) T27,SUM(CASE WHEN trip_no=27   THEN px_count ELSE 0 END) T27p,"+
		 "SUM(CASE WHEN trip_no=28   THEN px_total_amount ELSE 0 END) T28,SUM(CASE WHEN trip_no=28   THEN px_count ELSE 0 END) T28p,"+
		 "SUM(CASE WHEN trip_no=29   THEN px_total_amount ELSE 0 END) T29,SUM(CASE WHEN trip_no=29   THEN px_count ELSE 0 END) T29p,"+
		 "SUM(CASE WHEN trip_no=30   THEN px_total_amount ELSE 0 END) T30,SUM(CASE WHEN trip_no=30   THEN px_count ELSE 0 END) T30p "+
		 "FROM `ticket` inner join shift_type on ticket.shift_no=shift_type.shift_type_id left join (SELECT DAYNAME(ticket_date) weekda,schedule_no,count(distinct(trip_no)) cnt "+
		 "FROM `ticket`  WHERE  ticket_date between '"+date1+"' and '"+date2+"' "+qry+" "+  
		 "and ticket_no!=0 and px_total_amount!=0 group by schedule_no) cc on cc.schedule_no=ticket.schedule_no and cc.weekda=DAYNAME(ticket_date) WHERE "+
		 "ticket_date between '"+date1+"' and '"+date2+"' "+qry+" "+   
		 "and ticket_no!=0 group by schedule_no order by  schedule_no";*/
		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);

		System.out.println("exquted query=============");
		// System.out.println("condition111==="+rs.next());
		// System.out.println("condition1==="+rs.next());
		String filePath = "Ticketing/";
		// System.out.println("condition11==="+rs.next());
		String fileName = "STTWO.txt";
		 // System.out.println("condition2==="+rs.next());
		     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br>Trip Wise Passenger Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	       // System.out.println("condition22==="+rs.next());
        System.out.println("=====read table header=========");
      
	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>Sr.No</th><th>Sch no</th><th>Shift Type</th><th>days</th>" +
					"               <th>T1 rev</th><th>T1 psngr</th><th>T1 Dist</th><th>T1 EPKM</th><th>T2 rev</th><th>T2 psngr</th><th>T2 Dist</th><th>T2 EPKM</th><th>T3 rev</th><th>T3 psngr</th><th>T3 Dist</th><th>T3 EPKM</th>" +
					"               <th>T4 rev</th><th>T4 psngr</th><th>T4 Dist</th><th>T4 EPKM</th><th>T5 rev</th><th>T5 psngr</th><th>T5 Dist</th><th>T5 EPKM</th><th>T6 rev</th><th>T6 psngr</th><th>T6 Dist</th><th>T6 EPKM</th>" +
					"<th>T7 rev</th><th>T7 psngr</th><th>T7 Dist</th><th>T7 EPKM</th><th>T8 rev</th><th>T8 psngr</th><th>T8 Dist</th><th>T8 EPKM</th><th>T9 rev</th><th>T9 psngr</th><th>T9 Dist</th><th>T9 EPKM</th><th>T10 rev</th><th>T10 psngr</th><th>T10 Dist</th><th>T10 EPKM</th>" +
					"<th>T11 rev</th><th>T11 psngr</th><th>T11 Dist</th><th>T11 EPKM</th><th>T12 rev</th><th>T12 psngr</th><th>T12 Dist</th><th>T12 EPKM</th><th>T13 rev</th><th>T13 psngr</th><th>T13 Dist</th><th>T13 EPKM</th>" +
					"<th>T14 rev</th><th>T14 psngr</th><th>T14 Dist</th><th>T14 EPKM</th><th>T15 rev</th><th>T15 psngr</th><th>T15 Dist</th><th>T15 EPKM</th><th>T16 rev</th><th>T16 psngr</th><th>T16 Dist</th><th>T16 EPKM</th><th>T17 rev</th><th>T17 psngr</th><th>T17 Dist</th><th>T17 EPKM</th><th>T18 rev</th><th>T18 psngr</th><th>T18 Dist</th><th>T18 EPKM</th>" +
						"<th>T19 rev</th><th>T19 psngr</th><th>T19 Dist</th><th>T19 EPKM</th><th>T20 rev</th><th>T20 psngr</th><th>T20 Dist</th><th>T20 EPKM</th><th>T21 rev</th><th>T21 psngr</th><th>T21 Dist</th><th>T21 EPKM</th>" +
						"<th>T22 rev</th><th>T22 psngr</th><th>T22 Dist</th><th>T22 EPKM</th><th>T23 rev</th><th>T23 psngr</th><th>T23 Dist</th><th>T23 EPKM</th><th>T24 rev</th><th>T24 psngr</th><th>T24 Dist</th><th>T24 EPKM</th><th>T25 rev</th><th>T25 psngr</th><th>T25 Dist</th><th>T25 EPKM</th>" +
						"<th>T26 rev</th><th>T26 psngr</th><th>T26 Dist</th><th>T26 EPKM</th><th>T27 rev</th><th>T27 psngr</th><th>T27 Dist</th><th>T27 EPKM</th><th>T28 rev</th><th>T28 psngr</th><th>T28 Dist</th><th>T28 EPKM</th>" +
						"<th>T29 rev</th><th>T29 psngr</th><th>T29 Dist</th><th>T29 EPKM</th><th>T30 rev</th><th>T30 psngr</th><th>T30 Dist</th><th>T30 EPKM</th><th>Total rev</th></tr></thead><tbody>";
			//regionTypeAjaxString +="<td colspan='9'></td><td align='center' width='10%' ><b>Cr Remitted</b></td> <td align='center' width='10%'><b>Dedicated/Chartered</b></td><td align='center' width='10%'><b>Total Revenue</b></td></tr>";
			//System.out.println("condition3==="+rs.next());
			 String path = realpath + filePath + fileName;
		   System.out.println("====calculateing total=====");
//	        
			 int i=1;
			// System.out.println("condition4==="+rs.next());
				 while (rs.next()) {
					 
					 
						Double total=Double.parseDouble(rs.getString("T1").toString())+(Double.parseDouble(rs.getString("T2").toString()))
								+Double.parseDouble(rs.getString("T3").toString())+Double.parseDouble(rs.getString("T4").toString())
								+Double.parseDouble(rs.getString("T5").toString())+Double.parseDouble(rs.getString("T6").toString())
								+Double.parseDouble(rs.getString("T7").toString())+Double.parseDouble(rs.getString("T8").toString())
								+Double.parseDouble(rs.getString("T9").toString())+Double.parseDouble(rs.getString("T10").toString())
								+Double.parseDouble(rs.getString("T11").toString())+Double.parseDouble(rs.getString("T12").toString())
								+Double.parseDouble(rs.getString("T13").toString())+Double.parseDouble(rs.getString("T14").toString())
								+Double.parseDouble(rs.getString("T15").toString())+Double.parseDouble(rs.getString("T16").toString())
								+Double.parseDouble(rs.getString("T17").toString())+Double.parseDouble(rs.getString("T18").toString())
								+Double.parseDouble(rs.getString("T19").toString())+Double.parseDouble(rs.getString("T20").toString())
								+Double.parseDouble(rs.getString("T21").toString())+Double.parseDouble(rs.getString("T22").toString())
								+Double.parseDouble(rs.getString("T23").toString())+Double.parseDouble(rs.getString("T24").toString())
								+Double.parseDouble(rs.getString("T25").toString())+Double.parseDouble(rs.getString("T26").toString())
								+Double.parseDouble(rs.getString("T27").toString())+Double.parseDouble(rs.getString("T28").toString())
								+Double.parseDouble(rs.getString("T29").toString())+Double.parseDouble(rs.getString("T30").toString());
					// double avg=total/Double.parseDouble(rs.getString("cnt").toString());
					 //System.out.println("==total"+avg);
					// String weekday=rs.getString("weekda").toString();
					 System.out.println("======table start=====");
										 regionTypeAjaxString +="<tr>";
					regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
					 System.out.println("======1strow=====");
					regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_no").toString()+"</td>";
					
					regionTypeAjaxString +="<td align='right'>"+rs.getString("shift_type_name").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("no_days").toString()+"</td>";
					System.out.println("==========enter table data");
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T1").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T1p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T1distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T1epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T2").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T2p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T2distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T2epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T3").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T3p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T3distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T3epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T4").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T4p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T4distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T4epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T5").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T5p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T5distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T5epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T6").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T6p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T6distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T6epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T7").toString()+"</td>";
			
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T7p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T7distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T7epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T8").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T8p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T8distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T8epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T9").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T9p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T9distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T9epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T10").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T10p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T10distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T10epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T11").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T11p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T11distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T11epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T12").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T12p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T12distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T12epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T13").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T13p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T13distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T13epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T14").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T14p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T14distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T14epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T15").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T15p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T15distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T15epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T16").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T16p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T16distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T16epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T17").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T17p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T17distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T17epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T18").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T18p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T18distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T18epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T19").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T19p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T19distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T19epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T20").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T20p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T20distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T20epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T21").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T21p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T21distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T21epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T22").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T22p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T22distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T22epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T23").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T23p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T23distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T23epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T24").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T24p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T24distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T24epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T25").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T25p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T25distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T25epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T26").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T26p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T26distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T26epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T27").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T27p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T27distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T27epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T28").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T28p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T28distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T28epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T29").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T29p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T29distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T29epkm").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T30").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T30p").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T30distance").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T30epkm").toString()+"</td>";
					System.out.println("=====table data end=====");
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T2").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T2").toString())/total*100)+")" +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+rs.getString("T3").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T3").toString())/total*100)+")" +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T4").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T4").toString())/total*100)+")" +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T5").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T5").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+rs.getString("T6").toString() +" ("+Math.rint(Double.parseDouble(rs.getString("T6").toString())/total*100)+")"+"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T7").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T7").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T8").toString() +" ("+Math.rint(Double.parseDouble(rs.getString("T8").toString())/total*100)+")"+"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T9").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T9").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T10").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T10").toString())/total*100)+")" +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T11").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T11").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+rs.getString("T12").toString() +" ("+Math.rint(Double.parseDouble(rs.getString("T12").toString())/total*100)+")"+"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T13").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T13").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T14").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T14").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T15").toString() +" ("+Math.rint(Double.parseDouble(rs.getString("T15").toString())/total*100)+")"+"</td>" ;
//					
					regionTypeAjaxString +="<td align='right'>"+ total +"</td>" ;
					//regionTypeAjaxString +="<td align='right'>"+ total/Integer.parseInt(rs.getString("cnt").toString())+"</td>" ;
					
					
				
				
					}
					//regionTypeAjaxString +="<td align='right'>"+ rs.getString("driver").toString() +"</td>" ;
					
					   regionTypeAjaxString +="</tr>";
				
				
		
		 
		 regionTypeAjaxString += "</tbody></table></div> </div> ";

		// ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
		// System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
		
			
			FileOutputStream FOS = new FileOutputStream(path);
			PrintWriter PW = new PrintWriter(FOS);
			
            	
		
		PW.close();
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	public String getAlertTripwiserepasenger() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Session session = null;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			Date FromDate = new Date();
			String schedulename = req.getParameter("schedulename");
			String trip = req.getParameter("cnt");
			String weekday = req.getParameter("weekday");
			String startdate = req.getParameter("startdate");
			String enddate = req.getParameter("enddate");
			String depot = req.getParameter("depot");
			Common cm = new Common();
			
		
			out = resp.getWriter();
			
			result = getAlertTripwisepasengerreportDetails(schedulename,trip,weekday,startdate,enddate,depot);
			out.print(result);
		} catch (Exception ex) {
         ex.printStackTrace();
		} finally {
//			session.close();
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getAlertTripwisepasengerreportDetails( String schedulename,String trip,String weekday,
			String selectedstartDate,String selectedendDate,String depot) {
       JSONObject result =new JSONObject();
       Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		
		Transaction transaction  = null;
		
		
		Session session = null;
		try {
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
		//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
			 Common common = new Common();
			 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
				Query qry1=session1.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip",Hibernate.STRING)
						.addScalar("central_uname")
						.addScalar("central_pwd");
				
				qry1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = qry1.list();	
					String depotdb="";
					String depotIp="";
					String user="";
					String password="";
					for (int j = 0; j < aliasToValueMapList.size(); j++) {
						Map<String, Object> aliasValue = aliasToValueMapList.get(j);
						 depotdb=aliasValue.get("depotname").toString();
						 depotIp=aliasValue.get("central_ip").toString();
						 user=aliasValue.get("central_uname").toString();
						 password=aliasValue.get("central_pwd").toString();
					}
				 Class.forName("com.mysql.jdbc.Driver");
				 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			 String qry = "SELECT DAYNAME(ticket_date) weekda,schedule_no," +
						"trip_no,count(distinct((ticket_date))) ndays FROM `ticket`  WHERE  ticket_date between '"+selectedstartDate+"' and '"+selectedendDate+"'  " +
						"and ticket_no!=0 and px_total_amount!=0 and schedule_no='"+schedulename.replace("@", " ")+"' and DAYNAME(ticket_date)='"+weekday+"' and trip_no="+trip ;
//				System.out.println(qry);
			JSONArray array = new JSONArray();
			
			rs=stmt.executeQuery(qry);
			int i=1;
			 while(rs.next()){
				 JSONArray ja = new JSONArray();
				 ja.add(i+1);
				 ja.add(rs.getString("schedule_no"));
				 ja.add(rs.getString("weekda"));
				 ja.add(rs.getString("ndays"));
				 array.add(ja);
								//list.add(rs.getString("scheduleNumber"));
							//System.out.println("++++++"+list.size());
							result.put("aaData", array);
					}
//			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return result;
		}
	}

	
}
