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
import javax.servlet.http.HttpSession;

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
import com.trimax.its.transport.model.TripType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class CasualContractDetailsReport extends ActionSupport {
	
	private String startdate;
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
	private String enddate;
    private Map<Integer, String> depotlist;
    private Map<Integer, String> charttypelist;
	public Map<Integer, String> getCharttypelist() {
		return charttypelist;
	}
	public void setCharttypelist(Map<Integer, String> charttypelist) {
		this.charttypelist = charttypelist;
	}
	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}
	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
	}
	public String execute() throws Exception {
		try {
		HttpSession session=ServletActionContext.getRequest().getSession();
		System.out.println("==============="+session.getAttribute("message"));
		if(session.getAttribute("message") !=null) {
	String result=	(String) session.getAttribute("message");
	addActionMessage(result);
	session.setAttribute("message",null);
		}}catch (Exception e) {
			e.printStackTrace();
		}
depotlist = getDepotName();
//charttypelist=getChartType();
return "success";
}
	public Map<Integer, String> getDepotName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=3 and deleted_status=0 order by orgchart.org_name");
		try {
		
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	
	public Map<Integer, String> getChartType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		try {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from TripType triptype  where route_type_id=5 and trip_type_id !=18 and deleted_status=0  and status='active' ");
		
			
			List<TripType> list = query.list();
			System.out.println("list ----"+list.size());
			for (TripType tt : list) {
				resultMap.put(tt.getId(), tt.getTripTypeName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	public void getdata() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String depot=req.getParameter("depot");
		//String chart=req.getParameter("chart");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		JSONObject result=new JSONObject();
		Common common = new Common();
		
		String date=common.getDateFromPicker(startdate);
		String str="";
		try{
//			str="select s.schedule_number,st.schedule_code,r.route_number,trip_number,s.schedule_id,s.schedule_type,r.route_id,shift.shift_type_name,shift.shift_type_id,"
//					+ "c_cname,sd.customer_id,sd.distance from schedule s " + 
//					"inner join form_four ff on s.schedule_id=ff.schedule_number_id " + 
//					"inner join schedule_details sd  on sd.form_four_id=ff.form_four_id and sd.schedule_number=s.schedule_id  " + 
//					"inner join trip_type tt on tt.trip_type_id=sd.trip_type " + 
//					"inner join route_type rt on rt.route_type_id=tt.route_type_id " + 
//					"inner join schedule_type st on schedule_type_id=s.schedule_type " + 
//					"inner join route r on r.route_id=sd.route_number_id " + 
//					"inner join shift_type shift on shift.shift_type_id=sd.shift_type_id " + 
//					"inner join customer  cus on cus.c_cid=sd.customer_id " + 
//					"where s.status='new' and s.current_status='CASE WORKER' and s.deleted_status=0 and ff.current_status='active'  " + 
//					"and ff.deleted_status=0 and rt.route_type_id=5 and tt.trip_type_id !=18 and sd.deleted_status=0 and tt.trip_type_id="+chart+"  " + 
//					"and depot_id="+depot+" and s.schedule_id not in (select schedule_no from chartedrevenue where '"+date+"' between from_date and 	to_date) "
//							+ "order by s.schedule_type,shift_type_name ";
			
			
			Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			Transaction transaction  = null;
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
		
			 
//			 String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
//				 Class.forName("com.mysql.jdbc.Driver");
//			 connection = DriverManager.getConnection("jdbc:mysql://10.30.1.158:23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","temp","temp");
//			 
//			 stmt = connection.createStatement();
			 
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
				// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

				 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
			
			
			
			str=" select v.license_number,gen_logsheet_no,e1.TOKEN driver_token,e.TOKEN cond_token from gen_logsheet gl "+
				  " inner join Waybill_Details wd on wd.waybil_Id=gl.waybill_id "+
				  " inner join vehicle v on v.vehicle_id=wd.Vehicle_No and v.status='ACTIVE' and deleted_status=0 "+
				  " inner join employee e on e.EMPLOYEE_ID=gl.conductor1_id and e.STATUS='ACTIVE' "+
				  " inner join employee e1 on e1.EMPLOYEE_ID=gl.driver1_id and e1.STATUS='ACTIVE' "+
				  " where gen_logsheet_date='"+date+"' ";
			
			 System.out.println(str);
			 rs = stmt.executeQuery(str);
			
			Query query=session.createSQLQuery(str)	;
				/*	.addScalar("schedule_number")
					.addScalar("schedule_code")
					.addScalar("route_number")
					.addScalar("trip_number");*/
			
//			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List<Map<String, Object>> aliasMapValueList=query.list();
//			System.out.println("alsize----------->"+aliasMapValueList.size());
			    
		int j=1;
			JSONArray array=new JSONArray();
//			for(int i=0;i<aliasMapValueList.size();i++){
//				Map<String, Object> rs=aliasMapValueList.get(i);
//				JSONArray ja=new JSONArray();
		while(rs.next()){
			JSONArray ja=new JSONArray();
		//	ja.add("<input type='hidden'  id='chart' name='chart' readonly='readonly' value='"+chart+ "'/>"); 
			/*	ja.add("<input type='hidden'  id='s_type' name='s_type' readonly='readonly' value='"+ rs.get("schedule_type")+ "'/>"); 
				ja.add("<input type='hidden'  id='route' name='s_no' readonly='readonly' value='"+ rs.get("route_id")+ "'/>"); 
				ja.add("<input type='hidden'  id='trip' name='s_no' readonly='readonly' value='"+ rs.get("trip_number")+ "'/>"); 
				*/
		ja.add("<input type='hidden'  id='chart' name='chart' readonly='readonly' value='cc'/>"+"<center>"+j+"<center>");
                    j++;
                    ja.add("<input type='hidden'  id='depot_no' name='depot_no' readonly='readonly' value='"+ depot+ "'/>"+"<center>"+depotdb+"<center>");
				ja.add("<input type='hidden'  id='license_number' name='license_number' readonly='readonly' value='"+ rs.getString("license_number")+ "'/>"+"<center>"+rs.getString("license_number")+"<center>");
				ja.add("<input type='hidden'  id='gen_logsheet_no' name='gen_logsheet_no' readonly='readonly' value='"+ rs.getString("gen_logsheet_no")+ "'/>"+"<center>"+rs.getString("gen_logsheet_no")+"<center>");
				ja.add("<input type='hidden'  id='driver_token' name='driver_token' readonly='readonly' value='"+ rs.getString("driver_token")+ "'/>"+"<center>"+rs.getString("driver_token")+"<center>");

//				ja.add("<input type='hidden'  id='route' name='route' readonly='readonly' value='"+ rs.get("route_id")+ "'/>"+"<center>"+rs.get("c_cname")+"<center>");
//				ja.add("<input type='hidden'  id='customer' name='customer' readonly='readonly' value='"+ rs.get("customer_id")+ "'/>"+"<center>"+rs.get("route_number")+"<center>");
//				ja.add("<input type='hidden'  id='trip' name='trip' readonly='readonly' value='"+ rs.get("trip_number")+ "'/>"+"<center>"+rs.get("trip_number")+"<center>");
				//ja.add("<center>"+ rs.get("distance")+"<center>");
				ja.add("<input type='text' name='departure_date'  id='"+rs.getString("license_number")+"_"+rs.getString("gen_logsheet_no")+"_departure' name='departure_date'  value='0'/>"); 			   
				ja.add("<input type='text'  name='departure_time'  id='"+rs.getString("license_number")+"_"+rs.getString("gen_logsheet_no")+"_departuretime' name='departure_time'  value='0'/>"); 	
				
				ja.add("<input type='text'  name='arrival_date'  id='"+rs.getString("license_number")+"_"+rs.getString("gen_logsheet_no")+"_arrivaldate' name='arrival_date'  value='0'/>"); 	
				ja.add("<input type='text'  name='arrival_time'  id='"+rs.getString("license_number")+"_"+rs.getString("gen_logsheet_no")+"_arrivaltime' name='arrival_time'  value='0'/>"); 	
				ja.add("<input type='text'  name='hsr'  id='"+rs.getString("license_number")+"_"+rs.getString("gen_logsheet_no")+"_hsr' name='hsr'  value='0'/>"); 	
				ja.add("<input type='text'  name='totalkm'  id='"+rs.getString("license_number")+"_"+rs.getString("gen_logsheet_no")+"_totalkm' name='totalkm'  value='0'/>"); 	
				ja.add("<input type='text'  name='rent'  id='"+rs.getString("license_number")+"_"+rs.getString("gen_logsheet_no")+"_rent' name='rent'  value='0'/>"); 	
				ja.add("<input type='text'  name='ot'  id='"+rs.getString("license_number")+"_"+rs.getString("gen_logsheet_no")+"_ot' name='ot'  value='0'/>"); 	
				ja.add("<input type='text'  name='gst'  id='"+rs.getString("license_number")+"_"+rs.getString("gen_logsheet_no")+"_gst' name='gst'  value='0'/>"); 	

			    
			    array.add(ja);
				
				
			}
		
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
		
		
	
	}
	public String getsavedata() {
		int result=0;
		int i=0;
		HttpSession session=ServletActionContext.getRequest().getSession();
		HttpServletRequest request=ServletActionContext.getRequest();
		try{
			String depotid=request.getParameter("depotname");
					//String[] depot_no=request.getParameterValues("depot_no");
					String[] license_number=request.getParameterValues("license_number");
//					System.out.println("ARR SIZE---"+arr22.length);
//					System.out.println("length is"+arr1.length);
					String[] gen_logsheet_no=request.getParameterValues("gen_logsheet_no");
					//System.out.println("arr2------"+arr2);
					String[] driver_token=request.getParameterValues("driver_token");
					String[] departure_date=request.getParameterValues("departure_date");
					
					String[] departure_time=request.getParameterValues("departure_time");
					String[] arrival_date=request.getParameterValues("arrival_date");
					String[] arrival_time=request.getParameterValues("arrival_time");
					String[] hsr=request.getParameterValues("hsr");
					String[] totalkm=request.getParameterValues("totalkm");
					String[] rent=request.getParameterValues("rent");
					String[] ot=request.getParameterValues("ot");
					String[] gst=request.getParameterValues("gst");
//					String[] trip=request.getParameterValues("trip");
//					String[] kms=request.getParameterValues("kms");
					//System.out.println("batch size--------"+dutybatch.length);
					
//					String[] rate=request.getParameterValues("rate");
				/*	System.out.println(arr5);
					System.out.println(arr3);
					System.out.println(arr2);
					System.out.println(arr1);*/
//					System.out.println("arr1.length"+arr1.length);
//					System.out.println("arr2.length"+arr2.length);
//					System.out.println("arr3.length"+arr3.length);
//					System.out.println("arr5.length"+arr5.length);
//					
				
					Common common = new Common();
					
					String date1=common.getDateFromPicker(startdate);
				
//					String date2=common.getDateFromPicker(enddate);
					String depott=depotid;
//					String chart=request.getParameter("chartname");
					System.out.println("startdate==="+startdate);
//					System.out.println("enddate==="+enddate);
					// String empid="";
					 int loop=0;
					 int loop1=0;
			for(String logsheet:gen_logsheet_no){
		String logsheet_no=logsheet;
		String vehicle_no=license_number[loop];
		String depot_id=depott;
		String  drivertoken=driver_token[loop];
		String  dep_date=departure_date[loop];
		String dep_time=departure_time[loop];
		String ari_date=arrival_date[loop];
		String hour=hsr[loop];
		String km=totalkm[loop];
		String rent_price=rent[loop];
		String ot_time=ot[loop];
		String gst_amt=gst[loop];
		
		String ari_time=arrival_time[loop];
		String departure=dep_date+" "+dep_time;
		String arrival=ari_date+" "+ari_time;
//		String kmss=kms[loop].trim();
//		String ratee=rate[loop].trim();
		
		double total_amt=Double.parseDouble(rent_price)+Double.parseDouble(gst_amt);
		
		
		
		
	
			System.out.println("logsheet_no=="+logsheet_no);
			System.out.println("vehicle_no=="+vehicle_no);
			System.out.println("drivertoken=="+drivertoken);
			System.out.println("dep_date=="+dep_date);
			System.out.println("dep_time=="+dep_time);
			System.out.println("ari_date=="+ari_date);
			System.out.println("total_amt==="+total_amt);
					   /*
					    Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
						session1.beginTransaction();*/
						
							try{
					if(!dep_date.equals("0") || !ari_date.equals("0")) {
					result=savedata(date1,depot_id,vehicle_no,logsheet_no,drivertoken,departure,arrival,hour,km,rent_price,ot_time,gst_amt,total_amt);
					}
						
							}catch (Exception e) {
								e.printStackTrace();
								
							}/*finally{
						
							}*/
							loop++;
						}
			
			if(result !=0 ){
				session.setAttribute("message", "Charted Service  Created Successfully");
				addActionMessage("Charted Service  Created Successfully");
				}else{
					session.setAttribute("message", "Charted Service  Creation Failed");
					addActionError("Charted Service  Creation Failed");
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.setAttribute("message", "Error Happend");
				addActionError("Error Happend");

			}
		return "redir";
		
	
	}
	public int savedata(String date1,String depot_id,String vehicle_no,String logsheet_no,String drivertoken,String dep_date,String ari_date,String hour,String km,String rent_price,String ot_time,String gst_amt,double total_amt) {
		
		int userid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());



		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
int i=0;
	
		try{
String sql="INSERT INTO `casualcontractrevenue` (depot_no,vehicle_no,logsheet_no,d_tocken,departure_date,arrival_date,hrs_per_day,tot_kms,rent,ot_or_it,gst,tot_rev,created_by) "
		+ "values('"+depot_id+"','"+vehicle_no+"','"+logsheet_no+"','"+drivertoken+"','"+dep_date+"','"+ari_date+"',"+hour+",'"+km+"','"+rent_price+"','"+ot_time+"','"+gst_amt+"',"+total_amt+",'"+userid+"')";
			Query query=session.createSQLQuery(sql);

	i=query.executeUpdate();
		
	System.out.println("i===="+i);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	
		
	
	
		
		
		return i;
		
	}

}
