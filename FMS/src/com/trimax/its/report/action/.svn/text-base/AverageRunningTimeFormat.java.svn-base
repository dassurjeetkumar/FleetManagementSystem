package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse6;
import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

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
import com.trimax.its.route.model.Route;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.StageWiseTicketConsumptionDOA;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.vts.NewWsVts_Service;
import com.trimax.vts.VtsDataModelNew;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class AverageRunningTimeFormat extends ActionSupport{
	 public String sourcebus;
	 public String destbus;
	public String getDestbus() {
		return destbus;
	}
	public void setDestbus(String destbus) {
		this.destbus = destbus;
	}
	public String getSourcebus() {
		return sourcebus;
	}
	public void setSourcebus(String sourcebus) {
		this.sourcebus = sourcebus;
	}
	public String execute() {
	
		return "success";
	}
	@SuppressWarnings("unchecked")
	public List<Route> getRouteListDropDown1(String routeno){

		
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		List<Route> list1 = new ArrayList<Route>();
		try{
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
			String today = formatter.format( new java.util.Date() );
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			String queryForRouteNos = "SELECT route_id,route_number,route_direction FROM `route` WHERE `status` = 'active' AND route_number like'%"+routeno+"%' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
			Query queryObject = session.createSQLQuery(queryForRouteNos).addScalar("route_number",Hibernate.STRING)
					.addScalar("route_id",Hibernate.STRING).addScalar("route_direction",Hibernate.STRING)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String,String> map = aliasToValueMapList.get(i);
				Route route1 = new Route();
				route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
				route1.setValue(map.get("route_number"));
				//setValue
//				route1.setRoute_name(String.valueOf(map.get("route_id")) +"-"+String.valueOf(map.get("start_point_id"))+"-"+String.valueOf(map.get("end_point_id")));
				list1.add(route1);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                //session.close();
            }
		}
		
		return list1;
		
	}
	public String getRouteListData() {
		System.out.println("heree");
		
		
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//StageWiseTicketConsumptionDOA doa = new StageWiseTicketConsumptionDOA();
			String routename=req.getParameter("routename");
			System.out.println("route is------"+routename);
		List<Route> l1 =getRouteListDropDown1(routename);
		System.out.println("regionTypeAjaxString" + l1.size());
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		out = response.getWriter();
		System.out.println(new com.trimax.its.org.json.JSONArray(l1));
		out.print(new com.trimax.its.org.json.JSONArray(l1));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

public String header(){
	
	HttpServletResponse resp = ServletActionContext.getResponse();
	HttpServletRequest req = ServletActionContext.getRequest();
	PrintWriter out = null;
	JSONObject result = new JSONObject();
	JSONArray headrearray = new JSONArray();
	headrearray.add(req.getParameter("routeid"));
	headrearray.add(req.getParameter("startdate"));
	headrearray.add(req.getParameter("enddate"));
	
	result.put("bbData", headrearray);
	//return result;
	try {
		out = resp.getWriter();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	out.print(result);
	return null;
}
	
	public String getaveragerunnigtimedata()
	{
		JSONObject result = new JSONObject();
		PrintWriter out = null;
		//JSONObject result = new JSONObject();
		Session session1 = null;
		Session session = null;
		try{	
			String regionTypeAjaxString = "";
			StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	    VtsDataDAO dao= VtsDataDAO.getInstance();
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		//String source=req.getParameter("source");
		//String dest=req.getParameter("dest");
		String routeid=req.getParameter("routeid");
		String startdate=req.getParameter("startdate");
		String stringsplit[] = startdate.split("-");
		String enddate=req.getParameter("enddate");
		String stringsplit1[] = enddate.split("-");
		String timefrom=stringsplit[1].trim();
		String timeto=stringsplit1[1].trim();
		String route[]=routeid.split(":");
		/*System.out.println("date1==="+stringsplit[1]);
		System.out.println("date2==="+stringsplit1[1]);*/
		Common common = new Common();
		
		//String schedulelist=req.getParameter("schedulelist");
	
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);

		Common cm = new Common();
		//String formattedgivendate = cm.getDateFromPicker(stringsplit[0]);
		
			Date date1=new Date(stringsplit[0]);
			Date date2=new Date(stringsplit1[0]);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date1);
		String format1 = formatter.format(date2);
		System.out.println("date1----"+format+"---"+format1);
		
		ResultSet rs=null;
		Transaction transaction  = null;
		 SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		resp.setContentType("application/json");
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		// session1=HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
		 System.out.println("getsession========"+session1);
		
//	==============
			 String queryforrouteid="select route_id from route where route_number='"+route[0]+"' and route_direction='"+route[1]+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
		
				String routid=common.getDBResultStr(session, queryforrouteid, "route_id");
				System.out.println("route id-----"+routid);
				/*String sql="SELECT ROUTE_NO,count(DISTINCT(`DUTY_DT`)) as days,DISTANCE,START_BUS_STOP_NAME,END_BUS_STOP_NAME,count(*),"+
                            "ROUND(AVG(timestampdiff(MINUTE,START_TIME,END_TIME)),2) avg_form4_time,"+
                            "count(case when TRIP_STATUS='C' THEN TRIP_NUMBER end) TRIP_OPERATED,"+
                            "ROUND(avg(case when TRIP_STATUS='C' && ((ACT_START_TIME!='00:00:00' || ACT_START_TIME IS NOT NULL) && (ACT_END_TIME!='00:00:00' || ACT_END_TIME IS NOT NULL)) THEN timestampdiff(MINUTE,ACT_START_TIME,ACT_END_TIME) end),2)AVG_TIME_ACTUALRUN "+
                            "FROM `waybill_trip_details` "+
                            "WHERE `DUTY_DT` between '"+format+"' and '"+format1+"'  and START_TIME between '"+timefrom+":00' and '"+timeto+":00' AND `ROUTE_ID` = '"+routid+"' AND `ETIM_Coll_Amt` != '0' group by ROUTE_ID";
				Query query = session1.createSQLQuery(sql);
				System.out.println("create sql query====="+sql);
		        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		        System.out.println("getting values is---------"+query.list().size());
		        List<Map<String, Object>> aliasToValueMapList = query.list();*/
		        ///////////////////////////////////////////
		        String rbKey = String.valueOf(getSysParameterForVts());
		        System.out.println("rbkey is-------"+rbKey);
		        
		        
		        NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
				com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
				
				
			System.out.println("middle==========");
				VtsResponseNew alertresult = port.webGetDataFromAveragerunningtime(format,format1,routid,timefrom,timeto,rbKey);
		        /////////////////////////////////////////getVtsDatamodelnew
				System.out.println("ending======");
		        JSONArray array = new JSONArray();
		    	for (int i = 0; i < alertresult.getVtsDatamodelnew().size(); i++) {
             JSONArray ja = new JSONArray();
					VtsDataModelNew list = alertresult.getVtsDatamodelnew().get(i);
					ja.add(i + 1);
					ja.add(list.getROUTENO());
					ja.add(list.getDays());
					ja.add(list.getDISTANCE());
					ja.add(list.getSTARTBUSSTOPNAME());
					ja.add(list.getENDBUSSTOPNAME());
					ja.add(list.getFrom4Trips());
					ja.add(list.getAvgForm4Time());
					ja.add(list.getTRIPOPERATED());
					ja.add(list.getAVGTIMEACTUALRUN());
					array.add(ja);
		    	}
		    	System.out.println("=========");
		    	result.put("aaData", array);
				out = resp.getWriter();
				out.print(result);
				System.out.println("ending======="+result);
		   /*     regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Average Running Time Format </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

				regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
			        

				regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
				regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>ROUTE NO</th><th>NO OF DAYS</th><th>DIST</th><th>FROM</th><th>TO</th><th>NO OF TRIPS</th>" +
						"<th>AVG-IV TIME</th><th>NO OF TRIPS OPRATED</th><th>ACT AVG TIME</th>" +
							""+"</tr></thead><tbody>");
		        


				        HttpServletResponse response = ServletActionContext.getResponse();
				     
				        for (int i = 0; i < aliasToValueMapList.size(); i++) {
				        	int j=i+1;
				        	 Map<String, Object> list = aliasToValueMapList.get(i);
				        	
				        	 System.out.println("route no=="+list.get("ROUTE_NO").toString());
					   regionTypeAjaxString1.append("<tr>");
						regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
						regionTypeAjaxString1.append("<td align='right'>"+list.get("ROUTE_NO").toString() +"</td>");
						regionTypeAjaxString1.append("<td align='right'>"+list.get("days").toString() +"</td>");
					regionTypeAjaxString1.append("<td align='right'>"+list.get("DISTANCE").toString() +"</td>");
					regionTypeAjaxString1.append("<td align='right'>"+ list.get("START_BUS_STOP_NAME").toString() +"</td>");
					regionTypeAjaxString1.append("<td align='right'>"+ list.get("END_BUS_STOP_NAME").toString()  +"</td>");
					regionTypeAjaxString1.append("<td align='right'>"+ list.get("count(*)").toString() +"</td>");
					regionTypeAjaxString1.append("<td align='right'>"+ list.get("avg_form4_time").toString() +"</td>");
					regionTypeAjaxString1.append("<td align='right'>"+list.get("TRIP_OPERATED").toString()  +"</td>");
//					if(list.get("AVG_TIME_ACTUALRUN").equals("") || list.get("AVG_TIME_ACTUALRUN").equals(null)){
						regionTypeAjaxString1.append("<td align='right'>0</td>");
//					}else{
//						regionTypeAjaxString1.append("<td align='right'>"+ list.get("AVG_TIME_ACTUALRUN").toString()  +"</td>");
//					}
					
					 regionTypeAjaxString1.append("</tr>");
							
							 }
				        regionTypeAjaxString1.append("</tbody></table></div>");


				out = response.getWriter();
				out.print(regionTypeAjaxString1);
				//System.out.println(regionTypeAjaxString1);
*/		}catch (Exception e) {
			e.printStackTrace();
		}
finally{
	//session.close();
//	session.close();
}
			
			return null;
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

