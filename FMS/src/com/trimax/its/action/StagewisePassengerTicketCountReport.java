package com.trimax.its.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.trimax.its.common.Common;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.Route;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.StageWiseTicketConsumptionDOA;
import com.trimax.its.vts.dao.VtsDataDAO;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

public class StagewisePassengerTicketCountReport {

	private Map<Integer, String> divisionlist;
	OrganisationChart orgchart;
	private String depotlist1;
	private Map<Integer, String> divisionlist1;
	private List<Route> routeList;
	private String route;
	public String startdate;
	public String endate;

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	public List<Route> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEndate() {
		return endate;
	}

	public void setEndate(String endate) {
		this.endate = endate;
	}
	
	public String execute() throws Exception {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDepotWithoutAll();
         return "success";
	}
	
	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString1=new StringBuffer();
	
    @SuppressWarnings("finally")
    public String getStageWiseCountData() throws SQLException
    {
    	String rowNo="";
    	Statement stmt=null;
		Session session2 = null;
		ResultSet rs1=null;
    	Session session1=null;
    	Session session=null;
    	Connection connection =null;
        HttpServletRequest req = ServletActionContext.getRequest();
                System.out.println("HI I NDFDF");
                try {
               
        			Date ss1 = new Date();
        			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
        			String runDateTime = sdf.format(ss1);
        			String date1 = req.getParameter("date1");
        			String date2=req.getParameter("date2");
        			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        			Date st1 = format.parse(date1);
        			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");      // change date in sql format
        			Date st2 = format.parse(date2);
        			
        			String startDate = fomat2.format(st1).toString();
        			String todate=fomat2.format(st2).toString();
        			System.out.println("start--" + startDate+"end==="+todate);
        			
        			String depotId= req.getParameter("depotId");
        			String routeName=req.getParameter("routeId");
        			int routeId = getRouteID(routeName);     // route Id
        			
        			String stage1Id=req.getParameter("fromStageId");   // from stage Id
        			String stage2Id=req.getParameter("toStageId");     // To stage id
        			
        			System.out.println("bus stop--"+routeName+"=id = " +routeId+"-st1id-"+stage1Id+"--st2--"+stage2Id+"=dp=="+depotId);
    				
    				session=HibernateUtil.getSession("hibernate.cfg.xml");
    				
    				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotId+"' ";
    				Query qry=session.createSQLQuery(sql1).
    						addScalar("depotname")
    						.addScalar("central_ip")
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
    				// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

    				 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
    				 stmt = connection.createStatement();
    				 String sql="";
    				 
    				 // to get row value(sequence no)
    			 ResultSet rs = stmt.executeQuery("SELECT (@row/*'*/:=/*'*/@row+1) AS ROW,bs.bus_stop_id bstop,bus_stop_name FROM `route_point` rp  " +
    		    			" inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id ,(SELECT @row /*'*/:=/*'*/ 0) r " +
    		    			" WHERE `route_id` = '"+routeId+"' AND rp.`fare_stage` = 'Y'");
    		    				String bstopId ="";
    		    				 String rowValue ="";
    		    				 String bsRow[] = null;
    		    				 String rowId[]=null;
    		    				 String val="";
    		    				while (rs.next()) {
    		    					   rowValue = rs.getString("ROW");
    		    					   bstopId = rs.getString("bstop");
    		    					   bsRow = bstopId.split("\n");
    		    					   rowId = rowValue.split("\n");
    		    					   for (int k =0; k < bsRow.length; k++){
    		    					   if(bsRow[k].equalsIgnoreCase(stage1Id) || bsRow[k].equalsIgnoreCase(stage2Id))
    		    	    				{
    		    						   val += rowId[k]+",";
    		    	    				}
    		    					}
    		    				}
    		    				String rowIds[] = val.split(",");
    		    				String rowId1 = rowIds[0];
    		    				String rowId2 = rowIds[1];
    		    				int rowCheck = Integer.parseInt(rowId2);
    		    				
    		    				// to get bus stop id not in(?) <--  value
    		    				 ResultSet rss = stmt.executeQuery("SELECT (@row/*'*/:=/*'*/@row+1) AS ROW,bs.bus_stop_id bstop,bus_stop_name FROM `route_point` rp  " +
    		     		    			" inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id ,(SELECT @row /*'*/:=/*'*/ 0) r " +
    		     		    			" WHERE `route_id` = '"+routeId+"' AND rp.`fare_stage` = 'Y'");
    		    				 String stagesId="";
    		    				 String busStp[]=null;
    		    				 String bstpId ="";
    		    				 String rowsVal ="";
    		    				 String rowId22[]=null;
    		    				 while (rss.next()) {
    		    					 rowsVal = rss.getString("ROW");
    		    					 bstpId = rss.getString("bstop");
    		    					 busStp = bstpId.split("\n");
  		    					 rowId22 = rowsVal.split("\n");
  		    					   for (int k =0; k < rowId22.length; k++){
  		    					   if(Integer.parseInt(rowId22[k]) > rowCheck )
  		    	    				{
  		    						 stagesId += busStp[k]+",";
  		    	    				}
  		    					}
  		    				}
    		    			String rowsIn = remove(stagesId);      // remove , from last
    		    			System.out.println("rows in value---"+rowsIn);
    		    			
    		    			// if last stage is selected
    		    			if(rowsIn.equals("")){     
    		    				 sql="SELECT ticket_date date,ticket_from_stop_id,bs.bus_stop_name bs1,ticket_till_stop_id,bs1.bus_stop_name bs2,sum(px_total_amount) PxAmt," +
    		    	    			 		" sum(px_count) PxCount,`stage_ticket` stageTkt,stage_count sc FROM `ticket` t " +
    		    	    			 		" inner join bus_stop bs on bs.bus_stop_id=t.ticket_from_stop_id  " +
    		    	    			 		" inner join bus_stop bs1 on bs1.bus_stop_id=t.ticket_till_stop_id " +
    		    	    			 		" WHERE `ticket_date` between '"+startDate+"' and '"+todate+"' AND `ticket_no` != '0' AND `pass_id_no` = ''" +
    		    	    			 		"  and t.route_id="+routeId+" AND `stage_ticket` " +
    		    	    			 		" between '"+rowId1+"' and '"+rowId2+"' and ticket_till_stop_id " +
//    		    	    			 		" not in("+rowsIn+") " +
    		    	    			 		"  group by stage_ticket,stage_count ORDER BY ticket_date,`stage_ticket`,stage_count";
    		    				 
    		    			}else{         
    		    				 sql="SELECT ticket_date date,ticket_from_stop_id,bs.bus_stop_name bs1,ticket_till_stop_id,bs1.bus_stop_name bs2,sum(px_total_amount) PxAmt," +
    		    	    			 		" sum(px_count) PxCount,`stage_ticket` stageTkt,stage_count sc FROM `ticket` t " +
    		    	    			 		" inner join bus_stop bs on bs.bus_stop_id=t.ticket_from_stop_id  " +
    		    	    			 		" inner join bus_stop bs1 on bs1.bus_stop_id=t.ticket_till_stop_id " +
    		    	    			 		" WHERE `ticket_date` between '"+startDate+"' and '"+todate+"' AND `ticket_no` != '0' AND `pass_id_no` = ''" +
    		    	    			 		"  and t.route_id="+routeId+" AND `stage_ticket` " +
    		    	    			 		" between '"+rowId1+"' and '"+rowId2+"' and ticket_till_stop_id not in("+rowsIn+") " +
    		    	    			 		"  group by stage_ticket,stage_count ORDER BY ticket_date,`stage_ticket`,stage_count";
    		    			}

    			
    				rs1=stmt.executeQuery(sql);
    			        System.out.println("Query---- "+sql); 
    				regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'><h4>Stage Wise Passenger/Ticket Count</br>From Date:- "+startDate+" To Date:-"+ todate+"</h4></br></div>");
    				
			        regionTypeAjaxString1.append("<div align='right'><b> Date:-</b>"+runDateTime+"</div></div>");
			        regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
					regionTypeAjaxString1.append("<thead><tr><th>SNo</th><th>Start Bus Stop</th><th>End Bus Stop</th><th>Total Amount</th><th>Total passenger</th><th>Stage Tickets</th><th>Stage Count</th>"+"</tr></thead><tbody>");
    				
    				        int i=0;
    				        
    				        if (!rs1.isBeforeFirst() ) {   
    				        	
    						    		regionTypeAjaxString1.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
    						    		regionTypeAjaxString1.append("<tr>");
    									regionTypeAjaxString1.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
    									regionTypeAjaxString1.append("</tr>");	 
    				        } 
    				        
    				        
    				    	
    				        while(rs1.next()){
    				        	
    				        	System.out.println("size ===="+rs1.getRow());     // total result
    				        	regionTypeAjaxString1.append("<tr>");
    				
    					regionTypeAjaxString1.append("<td align='right'>"+ (++i) +"</td>");
    					//regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("date").toString() +"</td>");
    					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("bs1").toString()+"</td>");
    					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("bs2").toString()+"</td>");
    					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("PxAmt").toString()+"</td>");
    					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("PxCount").toString()+"</td>");
    					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("stageTkt").toString()+"</td>");
    					regionTypeAjaxString1.append("<td align='right'>"+rs1.getString("sc").toString()+"</td>");
    					
    					regionTypeAjaxString1.append("</tr>");
    				 }
    				regionTypeAjaxString1.append("</table></div> </div>  ");
    			 
//    				
//						 }

   			 HttpServletResponse response = ServletActionContext.getResponse();
   			PrintWriter out;

   			out = response.getWriter();
   			out.print(regionTypeAjaxString1);
    			
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}finally{
    				 connection.close();
    	            if (rs1 != null) {
    	            	rs1.close();
    	            }
    	            
    	            if (stmt != null) {
    	            	stmt.close();
    	            }

    	            if (connection != null) {
    	                connection.close();
    	            }
    	            if(session2 !=null){
    	            	 session2.close();
    	             }
    			}

    			return null;
    		}
                 

   
    @SuppressWarnings("rawtypes")
    public List getRowId(int routeId, String depotId) throws  SQLException{
    	Session ses=null;
    	Session ses2=null;
    	Statement stm=null;
    	Connection connection=null;
    	ResultSet rs1=null;
    	List list = new ArrayList();
    	try{
    	ses=HibernateUtil.getSession("hibernate.cfg.xml");
		String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotId+"' ";
		Query qry=ses.createSQLQuery(sql1).
				addScalar("dbname")
				.addScalar("ip")
				.addScalar("uname")
				.addScalar("password");
		
		qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qry.list();	
			String depotdb="";
			String depotIp="";
			String user="";
			String password="";
			for (int j = 0; j < aliasToValueMapList.size(); j++) {
				Map<String, Object> aliasValue = aliasToValueMapList.get(j);
				 depotdb=aliasValue.get("dbname").toString();
				 depotIp=aliasValue.get("ip").toString();
				 user=aliasValue.get("uname").toString();
				 password=aliasValue.get("password").toString();
			}
		 Class.forName("com.mysql.jdbc.Driver");				 
		 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":3306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
		 stm = connection.createStatement();
	System.out.println("connection =="+connection);
	Transaction transaction  = null;
	ses2= HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = ses2.beginTransaction();
		
		String qqry=" SELECT (@row/*'*/:=/*'*/@row+1) AS ROW,bs.bus_stop_id bstop,bus_stop_name FROM `route_point` rp  " +
    			" inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id ,(SELECT @row /*'*/:=/*'*/ 0) r " +
    			" WHERE `route_id` = '"+routeId+"' AND rp.`fare_stage` = 'Y'";
		
		rs1=stm.executeQuery(qqry);
		
		 while(rs1.next()){

				String rs = rs1.getString("ROW").toString();
				list.add(rs1);
			}
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}finally{
			 if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stm != null) {
	            	stm.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(ses2 !=null){
	            	 ses2.close();
	             }
	            
	            if(ses !=null){
	            	ses.close();
	            }
		}
		return list;
	}
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List BusStopId(int routeId, String depotId) throws  SQLException{
    	Session ses=null;
    	Session ses2=null;
    	Statement stm=null;
    	Connection connection=null;
    	ResultSet rs1=null;
    	List list = new ArrayList();
    	try{
    	ses=HibernateUtil.getSession("hibernate.cfg.xml");
		String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotId+"' ";
		Query qry=ses.createSQLQuery(sql1).
				addScalar("dbname")
				.addScalar("ip")
				.addScalar("uname")
				.addScalar("password");
		
		qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qry.list();	
			String depotdb="";
			String depotIp="";
			String user="";
			String password="";
			for (int j = 0; j < aliasToValueMapList.size(); j++) {
				Map<String, Object> aliasValue = aliasToValueMapList.get(j);
				 depotdb=aliasValue.get("dbname").toString();
				 depotIp=aliasValue.get("ip").toString();
				 user=aliasValue.get("uname").toString();
				 password=aliasValue.get("password").toString();
			}
		 Class.forName("com.mysql.jdbc.Driver");				 
		 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":3306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
		 stm = connection.createStatement();
	Transaction transaction  = null;
	ses2= HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = ses2.beginTransaction();
		
		String qqry=" SELECT (@row/*'*/:=/*'*/@row+1) AS ROW,bs.bus_stop_id bstop,bus_stop_name FROM `route_point` rp  " +
    			" inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id ,(SELECT @row /*'*/:=/*'*/ 0) r " +
    			" WHERE `route_id` = '"+routeId+"' AND rp.`fare_stage` = 'Y'";
		
		rs1=stm.executeQuery(qqry);
		
		 while(rs1.next()){
				String rs = rs1.getString("bstop").toString();
				list.add(rs1);
			}
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}finally{
			 if (rs1 != null) {
	            	rs1.close();
	            }
	            
	            if (stm != null) {
	            	stm.close();
	            }

	            if (connection != null) {
	                connection.close();
	            }
	            if(ses2 !=null){
	            	 ses2.close();
	             }
		}
		return list;
	}
	
	static String add(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
		}
		a1 = a1 - m1;
		for (int i = 0; i <= a1; i++) {
			sb.append(" ");
		}
		String sb1 = sb.toString();
		return sb1;
	}

/*	static String addMoney(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		StringBuilder sb2 = new StringBuilder();

		// String sb1 =
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
			return str1;
		}
		a1 = a1 - m1;
		for (int i = 0; i < a1; i++) {
			sb2.append(" ");
		}
		sb2.append(sb);
		String sb1 = sb2.toString();
		return sb1;
	}*/
	

	public String getRouteListData() {
		System.out.println("heree");
		
		
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			StageWiseTicketConsumptionDOA doa = new StageWiseTicketConsumptionDOA();
			String routename=req.getParameter("routename");
//			System.out.println("route is------"+routename);
		List<Route> l1 = doa.getRouteListDropDown1(routename);
//		System.out.println("regionTypeAjaxString" + l1.size());
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		out = response.getWriter();
		out.print(new JSONArray(l1));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// get Stage List..
    @SuppressWarnings("unchecked")
	public String getStageList(){
        
        HttpServletRequest req = ServletActionContext.getRequest();
        //VtsDataDAO dao = new VtsDataDAO();
        RouteDAO dao = new RouteDAO();
        
        String busstopname=(req.getParameter("routename"));
		int routeId = getRouteID(busstopname);
//		System.out.println("bus stop--"+busstopname+"=id = " +routeId);
		
        List<Integer> l1 = dao.getStageID(routeId);
        List<String> l2 = dao.getStageName(routeId);
//        System.out.println("l1.size"+l1.size());
//        System.out.println("l2.size"+l2.size());
        try {
            for (int i = 0; i < l1.size(); i++) {

                regionTypeAjaxString += "<option value=" + l1.get(i)
                        + ">" + l2.get(i).toString() + "</option>";
            }
            HttpServletResponse response = ServletActionContext.getResponse();
            PrintWriter out;

            out = response.getWriter();
            out.print(regionTypeAjaxString);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }
	
public int getRouteID(String routeno) {
		
		Session session = null;
		int id=0;
		
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String routename[]=routeno.split(":");
			String route=routename[0];
			String direction=routename[1];
//			System.out.println("name---direction"+route+"-----"+direction);
			
			Query qry=session.createSQLQuery("SELECT route_id FROM `route`  WHERE `status` = 'active' AND route_number='"+route+"' AND route_direction='"+direction+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'");
			id=(Integer)qry.setMaxResults(1).uniqueResult();   // For unique result if their is 2 id of Route
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		     if(session!=null){
		    	 session.close();
		     }
		}
		return id;
	}


public String remove(String str) {
    if (str != null && str.length() > 0 && str.charAt(str.length()-1)==',') {
      str = str.substring(0, str.length()-1);
    }
    return str;
}

}
