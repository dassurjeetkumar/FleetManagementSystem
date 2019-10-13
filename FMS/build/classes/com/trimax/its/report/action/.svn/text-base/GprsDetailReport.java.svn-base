package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class GprsDetailReport {
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
	private Map<Integer, String> divisionlist;

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
	public String getdata(){
		JSONObject result = new JSONObject();
		Session session = null;
		//Connection connection=null;
		//Statement stmt=null;
		//Session session1 = null;
		//ResultSet rs=null;
		//Transaction transaction  = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		Common common = new Common();
	
		String date1=common.getDateFromPicker(startdate);
	//	String date1=req.getParameter("startdate");
		String date2=common.getDateFromPicker(enddate);
		
		
		
		session = HibernateUtil.getSession("hibernate.cfg.xml");


	 
	 String sql="SELECT org_name,sum(totschcount)totschcount,sum(totschoper)totschoper,"
	 		+ "sum(etm_ticket_count)etm_ticket_count,sum(etm_passenger_count)etm_passenger_count,sum(ETIM_Coll_Amt+Bag_Coll_Amt+etm_card_amt)amt " + 
	 		"FROM depot_revenue dr right join org_chart oc on dr.depot_id=oc.org_chart_id and  `audited_date` between '"+date1+"' and '"+date2+"' " + 
	 		"  WHERE `org_type_id` = '3' AND `deleted_status` = '0'     and "
	 		+ "`org_name` LIKE '%Depot-%' AND `org_name` != 'Depot-test' group by org_name " + 
	 		"ORDER BY org_name";
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();
	    
	      JSONArray array = new JSONArray();
	      Format format = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
	      //NumberFormat format = NumberFormat.getCurrencyInstance(INDIA);

	      for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> rs = aliasToValueMapList.get(i);
		 
			ja.add(j);
			if(rs.get("etm_ticket_count")!=null || rs.get("etm_passenger_count")!=null || rs.get("amt")!=null) {
				 ja.add("<center>"+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal11'  onclick=javascript:getdepotwise('D"+rs.get("org_name").toString().substring(6,8)+"','"+date1+"','"+date2+"') title='Details'>"+rs.get("org_name").toString() + "</a>"+"<center>");

			}else {
				ja.add("<center>"+rs.get("org_name").toString()+"</center>");
			}
		
			
			if(rs.get("totschcount")==null) {
				ja.add("<center>"+"sync issue"+"</center>");
			}else {
			ja.add("<center>"+rs.get("totschcount").toString()+"</center>");
			}
			if(rs.get("totschoper")==null) {
				ja.add("<center>"+"sync issue"+"</center>");
			}else {
			ja.add("<center>"+rs.get("totschoper").toString()+"</center>");
			}
			
			
			
			//Number('15456778.00').toLocaleString('en-US', { style: 'currency', currency: 'INR' })
			//<p id="demo"></p><script>var n =Number('1017827.00').toLocaleString('en-US', { style: 'currency', currency: 'INR' });document.getElementById("demo").innerHTML = n;</script>
			if(rs.get("etm_ticket_count")==null) {
				ja.add("<center>"+"sync issue"+"</center>");
			}else {
			ja.add("<center>"+rs.get("etm_ticket_count").toString()+"</center>");
			}
			if(rs.get("etm_passenger_count")==null) {
				ja.add("<center>"+"sync issue"+"</center>");
			}else {
			ja.add("<center>"+rs.get("etm_passenger_count").toString()+"</center>");
			}
			if(rs.get("amt")==null) {
				ja.add("<center>"+"sync issue"+"</center>");
			}else {
			//ja.add("<center>Number('"+rs.get("amt").toString()+"').toLocaleString('en-US', { style: 'currency', currency: 'INR' })</center>");
				String s=format.format(new BigDecimal(rs.get("amt").toString()));
				ja.add("<center>"+s.replace("Rs.", "")+"</center>");

			}
			array.add(ja);
			
				
				 }
	
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
		
	}
	public String getdepotdata(){

		JSONObject result = new JSONObject();
		Session session = null;
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		String DB=req.getParameter("orgname");
		String date1=req.getParameter("date1");
		String date2=req.getParameter("date2");
		
		 Class.forName("com.mysql.jdbc.Driver");
		 //connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","revenuereport","ReporTCentraL");
		 //connection = DriverManager.getConnection("jdbc:mysql://10.30.1.162:23306/"+DB+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");
			 //connection = DriverManager.getConnection("jdbc:mysql://10.30.2.21:3306/bmtc_doa41?zeroDateTimeBehavior=convertToNull&autoReconnect=true","app_its_doa","Appl!cat!oN");
			 connection = DriverManager.getConnection("jdbc:mysql://10.30.1.162:23306/"+DB+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true","revenuereport","ReporTCentraL");

		 System.out.println("connection........."+connection);
	 stmt = connection.createStatement();
		
		//session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="SELECT generated_Date,schedule_number_name, schedule_type_name, ifnull(shift_type_name,'')shift_type_name,"
				+ "bag_code,(ETIM_Coll_Amt+Bag_Coll_Amt+etm_card_amt) as collection,wd.waybill_no FROM `Waybill_Details` wd "
				+ "left join form_four ff on wd.schedule_No = ff.form_four_id "
				+ "left join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No "
				+ "left join schedule_type st on st.schedule_type_id =  wd.Schedult_Type "
				+ "left join shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status='Closed'  "
				+ "AND Ticket_Audited_Date between '"+date1+" 00:00:00' and  '"+date2+" 23:59:59'   " 
				+ "order by schedule_type_name,shift_type_name";  
				
		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);
	    
	      JSONArray array = new JSONArray();
		    int i=0;
		      while (rs.next()) {
		        	JSONArray ja = new JSONArray();
		         i++;
		  
				ja.add(i);
				ja.add(rs.getString("waybill_no").toString());
				ja.add(rs.getString("generated_Date").toString());
		ja.add(rs.getString("schedule_number_name").toString());		
		ja.add(rs.getString("schedule_type_name").toString());
	ja.add(rs.getString("shift_type_name").toString());	
	ja.add(rs.getString("bag_code").toString());
ja.add(rs.getString("collection").toString());	

//ja.add(rs.getString("tot").toString());	
				array.add(ja);
				
					
					 }
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			stmt.close();
			connection.close();
			}catch (Exception e) {
			e.printStackTrace();
			}
		}
		return null;
		
		
	}
	
}
