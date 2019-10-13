package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class LogSheetDashbord extends ActionSupport{
	private String startdate;
	private Map<Integer, String> divisionlist;
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
    public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String execute() throws Exception {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
}

	 //  List list = new ArrayList();
	public void getdata() {
		JSONObject result = new JSONObject();
			Map list=new LinkedHashMap();
	   Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
		
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			 Common common = new Common();
			String date=common.getDateFromPicker(startdate);
			String div=req.getParameter("div");
			try {
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");

			

			String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info "
					+ " where division_id="+div+" order by depotname ";

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
			      JSONArray array = new JSONArray();
				for (int j = 0; j < aliasToValueMapList.size(); j++) {
					Map<String, Object> aliasValue = aliasToValueMapList.get(j);
					 depotdb=aliasValue.get("depotname").toString();
					 depotIp=aliasValue.get("central_ip").toString();
					 user=aliasValue.get("central_uname").toString();
					 password=aliasValue.get("central_pwd").toString();
				try {
			 Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			 //connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

			 
			 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();

			 String qry1="SELECT count(distinct wd.schedule_No)waybill,ifnull(count(distinct scc.form_four_id),0) plan,  " + 
			 		"ifnull((select count(distinct form_four_id) from logsheet_cancelled_trip_new lct where lct.logsheet_date=date(wd.Ticket_Audited_Date)),0)statentry,  " + 
			 		"ifnull(count(cdk.gen_logsheet_id),0)dead,ifnull(sum(if(fuel is null,0,1)),0) hsd, " + 
			 		"(select d.org_name from depot d)depot " + 
			 		"FROM Waybill_Details wd left join gen_logsheet gl on gl.waybill_id=wd.waybil_Id "
			 		+ "left join causewise_dead_km cdk on cdk.gen_logsheet_id=gl.gen_logsheet_id  "
			 		+ "left join schedule_cancellation_cause scc on scc.form_four_id=wd.schedule_No and date(Ticket_Audited_Date) between from_date and to_date  "
			 		+ "WHERE date(Ticket_Audited_Date)= '"+date+"'";
			 
			System.out.println("qry1"+qry1);
			rs=stmt.executeQuery(qry1);
		
			 while(rs.next()){
				 JSONArray ja = new JSONArray();
				 int count=j+1;
				 int waycount=Integer.parseInt(rs.getString("waybill").toString());
					ja.add(count);
		    		ja.add(rs.getString("depot").toString());
		    		if(waycount>0) {
		    		ja.add(rs.getString("waybill").toString());
		    		ja.add(rs.getString("plan").toString());
		    		ja.add(rs.getString("statentry").toString());
		    		ja.add(rs.getString("dead").toString());
		    		ja.add(rs.getString("hsd").toString());
		    		}else {
			    		ja.add("Sync Issue");
			    		ja.add("Sync Issue");
			    		ja.add("Sync Issue");
			    		ja.add("Sync Issue");
			    		ja.add("Sync Issue");
		    		}
		    		
				array.add(ja);
			}}catch (Exception e) {
                    e.printStackTrace();
			}
			 }
			     HttpServletResponse response = ServletActionContext.getResponse();

			   	 PrintWriter out;

			   		    	result.put("aaData",array);
			   				out = response.getWriter();
			   				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
				
}
}
