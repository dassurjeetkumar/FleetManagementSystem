package com.trimax.its.report.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.report.dao.CancellationKMReportDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.VehicleDockingReportDao;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class ETMhourlycountreport extends ActionSupport{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
  
        public String startdate;
        private String depotlist1;
        public String divisionlist1;
        public String getDivisionlist1() {
            return divisionlist1;
        }


        public void setDivisionlist1(String divisionlist1) {
            this.divisionlist1 = divisionlist1;
        }


        private Map<Integer, String> divisionlist;
     


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


    public String getStartdate() {
            return startdate;
        }


        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }


StringBuffer regionTypeAjaxString=new StringBuffer();

		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}
		
	

    
       
        
        public String getDepotList() {	
        	
    		// get Depot List..
    		HttpServletRequest req = ServletActionContext.getRequest();
    		/*String runningkm = req.getParameter("dockingtype");
    		System.out.println("runningkm"+runningkm);*/
    		VehicleDockingReportDao dao=new VehicleDockingReportDao();
    		int parentId = Integer.parseInt(req.getParameter("division"));
    		System.out.println("-------------"+parentId);
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

    		return null;

    	}
        
        
          public String getDeviceForDepot() {	
        	
    		// get Depot List..
    		HttpServletRequest req = ServletActionContext.getRequest();
    		try {
 
    		int parentId = Integer.parseInt(req.getParameter("val"));
    		System.out.println("-------------"+parentId);
    		List<String> l1 = getDeviceID(parentId);
    		List<String> l2 = getDeviceName(parentId);
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='0'>------All------</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
    		}
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out;
    		
    			out = response.getWriter();
    			out.print(regionTypeAjaxString);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    		return null;

    	}
          
          
          @SuppressWarnings("rawtypes")
      	public List getDeviceID(int parentID) {
      		List list = new ArrayList();
      		String qry = "select d.device_id from device d inner join device_org do on d.device_id=do.device_id " +
      				" where d.deleted_status=0 and d.device_type_id=2 and d.status='active' and do.status='active' and  org_id="+parentID+"";
      		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
      		//System.out.println("id query  "+query);
      		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
      		List<Map<String, Object>> aliasToValueMapList = query.list();
      		if (aliasToValueMapList.size() > 0) {

      			for (int i = 0; i < aliasToValueMapList.size(); i++) {
      				Map<String, Object> rs = aliasToValueMapList.get(i);
      				list.add(rs.get("device_id").toString());
      			}
      		}
      		HibernateUtil.closeSession();
      		return list;
      	}
          
          
          
          @SuppressWarnings({ "rawtypes", "unchecked" })
      	public List getDeviceName(int parentID) {
      		List list = new ArrayList();
      		String qry = "select device_serial_number from device d inner join device_org do on d.device_id=do.device_id " +
      				" where d.deleted_status=0 and d.device_type_id=2 and d.status='active' and do.status='active' and  org_id="+parentID+"";
      		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
      		//System.out.println("id query namr "+query);
      		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
      		List<Map<String, Object>> aliasToValueMapList = query.list();
      		if (aliasToValueMapList.size() > 0) {

      			for (int i = 0; i < aliasToValueMapList.size(); i++) {
      				Map<String, Object> rs = aliasToValueMapList.get(i);
      				list.add(rs.get("device_serial_number").toString());
      			}
      		}
      		HibernateUtil.closeSession();
      		return list;
      	}
          
          
          
          
          @SuppressWarnings("finally")
          public String getETMhourlycountreport()
          {
             //System.out.println("gopi :we are in this method");
              HttpServletRequest req = ServletActionContext.getRequest();
              HttpServletResponse response = ServletActionContext.getResponse();
          	Session session1 = null;
                      try {
                    	  session1 = HibernateUtil.getSession("hibernate.cfg.xml");
                    	  JSONObject result = new JSONObject();
                      Date  ss1=new Date();
                      SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
                      String runDateTime = sdf.format(ss1);
                      int depot=Integer.parseInt(req.getParameter("depotId"));
//                      int division=Integer.parseInt(req.getParameter("divId"));
                      String deviceId=req.getParameter("deviceNo");
                 
                      String startdate=req.getParameter("date");
                      SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                      Date startDate = format.parse(startdate);
                      SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
                      String startDate1 = fomat2.format(startDate).toString();
                     
                     //System.out.println("gopi : in gt method=="+depot+"=="+deviceId+"="+startDate1+"===");
                    
                      String qry="";
                      if(deviceId.equals("------All------")||deviceId=="------All------"){
                    	  qry =" !=''";
                      }else{
                    	  qry =" = '"+deviceId+"' ";
                      }
                      response.setContentType("application/json");
          			response.setHeader("Cache-Control", "no-store");
          			PrintWriter out = response.getWriter();
          			result = getDataForEtmHourlyCount(depot, qry,startDate1,session1);
          			out.print(result);       // to print
              		} catch (Exception e) {
              			e.printStackTrace();
              		}
              		return null;
              	}
          
          
          @SuppressWarnings("unchecked")
      	public JSONObject getDataForEtmHourlyCount(int depotId,String qry,String fromDate,Session session){
      		JSONObject result = new JSONObject();
      		//System.out.println("gopi1");
      		try{
      		
      			String sql="select etim_no,sum(CASE WHEN hour(ticket_time)=0  THEN 1 ELSE 0 END) HR1,sum(CASE WHEN hour(ticket_time)=1  THEN 1 ELSE 0 END) HR2," + 
      					"sum(CASE WHEN hour(ticket_time)=2  THEN 1 ELSE 0 END) HR2,sum(CASE WHEN hour(ticket_time)=3 THEN 1 ELSE 0 END) HR3" + 
      					",sum(CASE WHEN hour(ticket_time)=4  THEN 1 ELSE 0 END) HR4,sum(CASE WHEN hour(ticket_time)=5  THEN 1 ELSE 0 END) HR5," + 
      					"sum(CASE WHEN hour(ticket_time)=6  THEN 1 ELSE 0 END) HR6,sum(CASE WHEN hour(ticket_time)=7 THEN 1 ELSE 0 END) HR7" + 
      					",sum(CASE WHEN hour(ticket_time)=8  THEN 1 ELSE 0 END) HR8,sum(CASE WHEN hour(ticket_time)=9  THEN 1 ELSE 0 END) HR9," + 
      					"sum(CASE WHEN hour(ticket_time)=10 THEN 1 ELSE 0 END) HR10,sum(CASE WHEN hour(ticket_time)=11 THEN 1 ELSE 0 END) HR11" + 
      					",sum(CASE WHEN hour(ticket_time)=12  THEN 1 ELSE 0 END) HR12,sum(CASE WHEN hour(ticket_time)=13  THEN 1 ELSE 0 END) HR13," + 
      					"sum(CASE WHEN hour(ticket_time)=14  THEN 1 ELSE 0 END) HR14,sum(CASE WHEN hour(ticket_time)=15 THEN 1 ELSE 0 END) HR15" + 
      					",sum(CASE WHEN hour(ticket_time)=16  THEN 1 ELSE 0 END) HR16,sum(CASE WHEN hour(ticket_time)=17  THEN 1 ELSE 0 END) HR17," + 
      					"sum(CASE WHEN hour(ticket_time)=18  THEN 1 ELSE 0 END) HR18,sum(CASE WHEN hour(ticket_time)=19 THEN 1 ELSE 0 END) HR19" + 
      					",sum(CASE WHEN hour(ticket_time)=20  THEN 1 ELSE 0 END) HR20,sum(CASE WHEN hour(ticket_time)=21  THEN 1 ELSE 0 END) HR21," + 
      					"sum(CASE WHEN hour(ticket_time)=22  THEN 1 ELSE 0 END) HR22,sum(CASE WHEN hour(ticket_time)=23 THEN 1 ELSE 0 END) HR23,sum(CASE WHEN hour(ticket_time)=24 THEN 1 ELSE 0 END) HR24 " +  
      					"from bmtcGprs.etim_gprs_ticket WHERE ticket_date ='"+fromDate+"' and depot_id="+ depotId+" and etim_no  "+qry+""+ 
      					"group by etim_no ";
        
      			System.out.println(sql);
		
      				Query hourly_qry = session.createSQLQuery(sql).addScalar("etim_no").addScalar("HR1").addScalar("HR2").addScalar("HR3").addScalar("HR4").addScalar("HR5").addScalar("HR5").addScalar("HR6").addScalar("HR7").addScalar("HR8").addScalar("HR9").addScalar("HR10").addScalar("HR11").addScalar("HR12").addScalar("HR13").addScalar("HR14").addScalar("HR15").addScalar("HR16").addScalar("HR17").addScalar("HR18").addScalar("HR19").addScalar("HR20").addScalar("HR21").addScalar("HR22").addScalar("HR23").addScalar("HR24");
      				hourly_qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
      				List<Map<String, Object>> maplist = hourly_qry.list();
      				//testing
      				//System.out.println("Gopi==>>>>"+maplist.size());
      				JSONArray array=new JSONArray();
      				for(int i= 0; i<maplist.size();i++) {
      				Map<String, Object> rs=maplist.get(i);
      				JSONArray ja=new JSONArray();
      			ja.add(i+1);	
      			ja.add(rs.get("etim_no"));
      			ja.add(Integer.parseInt(rs.get("HR1").toString())+Integer.parseInt(rs.get("HR2").toString())+Integer.parseInt(rs.get("HR3").toString())+Integer.parseInt(rs.get("HR4").toString())+Integer.parseInt(rs.get("HR5").toString())+Integer.parseInt(rs.get("HR6").toString())+Integer.parseInt(rs.get("HR7").toString())+Integer.parseInt(rs.get("HR8").toString())+Integer.parseInt(rs.get("HR9").toString())+Integer.parseInt(rs.get("HR10").toString())+Integer.parseInt(rs.get("HR11").toString())+Integer.parseInt(rs.get("HR12").toString())+Integer.parseInt(rs.get("HR13").toString())+Integer.parseInt(rs.get("HR14").toString())+Integer.parseInt(rs.get("HR15").toString())+Integer.parseInt(rs.get("HR16").toString())+Integer.parseInt(rs.get("HR17").toString())+Integer.parseInt(rs.get("HR18").toString())+Integer.parseInt(rs.get("HR19").toString())+Integer.parseInt(rs.get("HR20").toString())+Integer.parseInt(rs.get("HR21").toString())+Integer.parseInt(rs.get("HR22").toString())+Integer.parseInt(rs.get("HR23").toString())+Integer.parseInt(rs.get("HR24").toString()));

      			ja.add(rs.get("HR1"));
      			ja.add(rs.get("HR2"));
      			ja.add(rs.get("HR3"));
      			ja.add(rs.get("HR4"));
      			ja.add(rs.get("HR5"));
      			ja.add(rs.get("HR6"));
      			ja.add(rs.get("HR7"));
      			ja.add(rs.get("HR8")); 
      			ja.add(rs.get("HR9"));
      			ja.add(rs.get("HR10"));
      			ja.add(rs.get("HR11"));
      			ja.add(rs.get("HR12"));
      			ja.add(rs.get("HR13"));
      			ja.add(rs.get("HR14"));
      			ja.add(rs.get("HR15"));
      			ja.add(rs.get("HR16"));
      			ja.add(rs.get("HR17"));
      			ja.add(rs.get("HR18"));
      			ja.add(rs.get("HR19"));
      			ja.add(rs.get("HR20"));
      			ja.add(rs.get("HR21"));
      			ja.add(rs.get("HR22"));
      			ja.add(rs.get("HR23"));
      			ja.add(rs.get("HR24"));
      			
      			array.add(ja);
      			
      			}
      				
      			result.put("aaData", array);
      		} catch (Exception ex) {
      			ex.printStackTrace();
      		} 
      		finally {
      		}
      		return result;
      	}
          
          
          
          
        
       
 
        
   
      
}
       
