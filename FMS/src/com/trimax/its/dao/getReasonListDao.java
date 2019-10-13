package com.trimax.its.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse;
import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;



public class getReasonListDao extends ActionSupport {

	
	public static String rbKey = String.valueOf(getSysParameterForVts());
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getLateDeptReasonID() {
		List list = new ArrayList();
		String qry = "select id from late_dept_reason where status='ACTIVE' order by id";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getLateDeptReasonName() {
		List list = new ArrayList();
		String qry = "select reason from late_dept_reason where status='ACTIVE' order by id";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("reason").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
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
			session.close();
		}
		return param;
	}
		 
	

public String insertLateDeptReasonValue() throws IOException {
	
	HttpServletRequest request=null;
	Session session=null;
	String istDate="";
	try {
		
	//	Common common=new Common();
		 
		//System.out.println("in insert    ");
		
request=ServletActionContext.getRequest();
    session=HibernateUtil.getSession("hibernate.cfg.xml");
    String vehicleId= request.getParameter("vehicleId");
    String reasonId = request.getParameter("reason");
    String reasonName = request.getParameter("reasonName");
    String deviceNo = request.getParameter("deviceNo");
    String desc = request.getParameter("desc");
         System.out.println("in inserts"+vehicleId+"=="+reasonName+"=="+desc+"=="+deviceNo);
        model.jaxb.xml.trimax.com.VtsResponse6 activeresult1 = null;
        com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
         com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
         int userID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
         if(!reasonId.equals("7") && desc.equalsIgnoreCase("")){    //Reason selected others
       	  activeresult1 = port.webInsertReasonIntoLateDept(vehicleId, reasonName,deviceNo,userID, rbKey); // calling Web service
        }else{
       	 activeresult1 = port.webInsertReasonIntoLateDept(vehicleId, desc,deviceNo,userID, rbKey); // calling Web service
        }
         
//         activeresult = port.webInsertReasonIntoLateDept(vehicleId, reasonName,desc, rbKey); // calling Web service
       /*  if(activeresult.getWaybillDetails().get(0).getSTATUS().equalsIgnoreCase("true")){
        	 addActionMessage("Reason inserted Successfully");
         }else
         {
        	 addActionError("Error ! Try Again Later");
         }*/
         
//HttpServletResponse response = ServletActionContext.getResponse();
//PrintWriter out;
//
//
//	out = response.getWriter();
      
	
	} catch (Exception e) {

		e.printStackTrace();
	}
finally{

}
	return null;
}

	

public String insertEarlyArrReasonValue() throws IOException {
	
	HttpServletRequest request=null;
	Session session=null;
	String istDate="";
	try {
		
	//	Common common=new Common();
		CollectionReportDAO dao=new CollectionReportDAO();
		//System.out.println("in insert    ");
		
request=ServletActionContext.getRequest();
    session=HibernateUtil.getSession("hibernate.cfg.xml");
    String vehicleId= request.getParameter("vehicleId");
    String reasonId = request.getParameter("reason");
    String reasonName = request.getParameter("reasonName");
    String desc = request.getParameter("desc");
         String deviceNo =request.getParameter("deviceNo");
         int userID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
         System.out.println("in early inserts"+vehicleId+"=="+reasonName+"=="+desc);
        model.jaxb.xml.trimax.com.VtsResponse6 activeresult2 = null;
        com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
         com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
         
         if(!reasonId.equals("7") && desc.equalsIgnoreCase("")){    //Reason selected others
        	  activeresult2 = port.webInsertReasonIntoEarlyArrival(vehicleId, reasonName,deviceNo,userID, rbKey); // calling Web service
         }else{
        	 activeresult2 = port.webInsertReasonIntoEarlyArrival(vehicleId, desc,deviceNo,userID, rbKey); // calling Web service
         }

//         if(activeresult.getWaybillDetails().get(0).getSTATUS().equalsIgnoreCase("true")){
//        	 addActionMessage("Reason inserted Successfully");
//         }else
//         {
//        	 addActionError("Error ! Try Again Later");
//         }
         
//HttpServletResponse response = ServletActionContext.getResponse();
//PrintWriter out;
//
//
//	out = response.getWriter();
      
	
	} catch (Exception e) {

		e.printStackTrace();
	}
finally{

}
	return null;
}



public String insertStationaryReasonValue() throws IOException {
	
	HttpServletRequest request=null;
	Session session=null;
	String istDate="";
	try {
		
	//	Common common=new Common();
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("in insert stationary   ");
		
request=ServletActionContext.getRequest();
    session=HibernateUtil.getSession("hibernate.cfg.xml");
    String vehicleId= request.getParameter("vehicleId");
    String reasonId = request.getParameter("reason");
    String reasonName = request.getParameter("reasonName");
    String desc = request.getParameter("desc");
    String deviceNo =request.getParameter("deviceNo");
    int userID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
         System.out.println("in inserts"+vehicleId+"=="+reasonName+"=="+desc);
        model.jaxb.xml.trimax.com.VtsResponse6 activeresult3 = null;
        com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
         com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();

         if(!reasonId.equals("3") && desc.equalsIgnoreCase("")){    //Reason selected others
        	 activeresult3 = port.webInsertReasonIntoStationary(vehicleId, reasonName,deviceNo,userID,rbKey); // calling Web service
        }else{
        	 activeresult3 = port.webInsertReasonIntoStationary(vehicleId,desc,deviceNo,userID, rbKey); // calling Web service
        }
         
        
//         if(activeresult.getWaybillDetails().get(0).getSTATUS().equalsIgnoreCase("true")){
//        	 addActionMessage("Reason inserted Successfully");
//         }else
//         {
//        	 addActionError("Error ! Try Again Later");
//         }
         
//HttpServletResponse response = ServletActionContext.getResponse();
//PrintWriter out;
//
//
//	out = response.getWriter();
      
	
	} catch (Exception e) {

		e.printStackTrace();
	}
finally{

}
	return null;
}
	


public String insertSkipStopReasonValue() throws IOException {
	
	HttpServletRequest request=null;
	Session session=null;
	String istDate="";
	try {
		
	//	Common common=new Common();
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("in insert skip stop");
		
    request=ServletActionContext.getRequest();
    session=HibernateUtil.getSession("hibernate.cfg.xml");
    String vehicleId= request.getParameter("vehicleId");
    String reasonId = request.getParameter("reason");
    String reasonName = request.getParameter("reasonName");
    String desc = request.getParameter("desc");
    String deviceNo =request.getParameter("deviceNo");
    int userID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
         System.out.println("in inserts"+vehicleId+"=="+reasonName+"=="+desc);
        model.jaxb.xml.trimax.com.VtsResponse6 activeresult4 = null;
        com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
         com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();

         if(!reasonId.equals("5") && desc.equalsIgnoreCase("")){    //Reason selected others
        	 activeresult4 = port.webInsertReasonIntoSkipStop(vehicleId, reasonName,deviceNo,userID,rbKey); // calling Web service
        }else{
        	 activeresult4 = port.webInsertReasonIntoSkipStop(vehicleId,desc,deviceNo,userID, rbKey); // calling Web service
        }
         
//         activeresult = port.webInsertReasonForEarlyArrival(vehicleId, reasonName,desc, rbKey); // calling Web service
//         if(activeresult.getWaybillDetails().get(0).getSTATUS().equalsIgnoreCase("true")){
//        	 addActionMessage("Reason inserted Successfully");
//         }else
//         {
//        	 addActionError("Error ! Try Again Later");
//         }
         
//HttpServletResponse response = ServletActionContext.getResponse();
//PrintWriter out;
//
//
//	out = response.getWriter();
      
	
	} catch (Exception e) {

		e.printStackTrace();
	}
finally{

}
	return null;
}


public String insertSOSReasonValue() throws IOException {
	
	HttpServletRequest request=null;
	Session session=null;
	String istDate="";
	try {
		
	//	Common common=new Common();
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("in insert sos");
		
    request=ServletActionContext.getRequest();
    session=HibernateUtil.getSession("hibernate.cfg.xml");
    String vehicleId= request.getParameter("vehicleId");
    String reasonId = request.getParameter("reason");
    String reasonName = request.getParameter("reasonName");
    String desc = request.getParameter("desc");
    String deviceNo =request.getParameter("deviceNo");
    int userID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
         System.out.println("in inserts"+vehicleId+"=="+reasonName+"=="+desc);
        model.jaxb.xml.trimax.com.VtsResponse6 activeresult5 = null;
        com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
         com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();

         if(!reasonId.equals("3") && desc.equalsIgnoreCase("")){    //Reason selected others
        	 activeresult5 = port.webInsertReasonIntoSOS(vehicleId, reasonName,deviceNo,userID,rbKey); // calling Web service
        }else{
        	 activeresult5 = port.webInsertReasonIntoSOS(vehicleId,desc,deviceNo,userID, rbKey); // calling Web service
        }
         
//         activeresult = port.webInsertReasonForEarlyArrival(vehicleId, reasonName,desc, rbKey); // calling Web service
//         if(activeresult.getWaybillDetails().get(0).getSTATUS().equalsIgnoreCase("true")){
//        	 addActionMessage("Reason inserted Successfully");
//         }else
//         {
//        	 addActionError("Error ! Try Again Later");
//         }
         
//HttpServletResponse response = ServletActionContext.getResponse();
//PrintWriter out;
//
//
//	out = response.getWriter();
      
	
	} catch (Exception e) {

		e.printStackTrace();
	}
finally{

}
	return null;
}


public String insertDeviatedReasonValue() throws IOException {
	
	HttpServletRequest request=null;
	Session session=null;
	String istDate="";
	try {
		
	//	Common common=new Common();
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("in insert deviated");
		
    request=ServletActionContext.getRequest();
    session=HibernateUtil.getSession("hibernate.cfg.xml");
    String vehicleId= request.getParameter("vehicleId");
    String reasonId = request.getParameter("reason");
    String reasonName = request.getParameter("reasonName");
    String desc = request.getParameter("desc");
    String deviceNo =request.getParameter("deviceNo");
    int userID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
         System.out.println("in inserts"+vehicleId+"=="+reasonName+"=="+desc);
        model.jaxb.xml.trimax.com.VtsResponse6 activeresult6 = null;
        com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
         com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();

         if(!reasonId.equals("4") && desc.equalsIgnoreCase("")){    //Reason selected others
        	 activeresult6 = port.webInsertReasonIntoDeviated(vehicleId, reasonName,deviceNo,userID,rbKey); // calling Web service
        }else{
        	 activeresult6 = port.webInsertReasonIntoDeviated(vehicleId,desc,deviceNo,userID, rbKey); // calling Web service
        }
//         activeresult = port.webInsertReasonForEarlyArrival(vehicleId, reasonName,desc, rbKey); // calling Web service
//         if(activeresult.getWaybillDetails().get(0).getSTATUS().equalsIgnoreCase("true")){
//        	 addActionMessage("Reason inserted Successfully");
//         }else
//         {
//        	 addActionError("Error ! Try Again Later");
//         }
         
//HttpServletResponse response = ServletActionContext.getResponse();
//PrintWriter out;
//
//
//	out = response.getWriter();
      
	
	} catch (Exception e) {

		e.printStackTrace();
	}
finally{

}
	return null;
}


public String insertSchNotDeptReasonValue() throws IOException {
	
	HttpServletRequest request=null;
	Session session=null;
	String istDate="";
	try {
		
	//	Common common=new Common();
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("in insert sch not deptd");
		
    request=ServletActionContext.getRequest();
    session=HibernateUtil.getSession("hibernate.cfg.xml");
    String vehicleId= request.getParameter("vehicleId");
    String reasonId = request.getParameter("reason");
    String reasonName = request.getParameter("reasonName");
    String desc = request.getParameter("desc");
    String deviceNo =request.getParameter("deviceNo");
    int userID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
         System.out.println("in inserts"+vehicleId+"=="+reasonName+"=="+desc);
        model.jaxb.xml.trimax.com.VtsResponse6 activeresult7 = null;
        com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
         com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
         if(!reasonId.equals("3") && desc.equalsIgnoreCase("")){    //Reason selected others
        	 activeresult7 = port.webInsertReasonIntoSchNotDeparted(vehicleId, reasonName,deviceNo,userID,rbKey); // calling Web service
        }else{
        	 activeresult7 = port.webInsertReasonIntoSchNotDeparted(vehicleId,desc,deviceNo,userID, rbKey); // calling Web service
        }

         
//HttpServletResponse response = ServletActionContext.getResponse();
//PrintWriter out;
//
//
//	out = response.getWriter();
      
	
	} catch (Exception e) {

		e.printStackTrace();
	}
finally{

}
	return null;
}

public String insertNRDReasonData() throws IOException {
	
	HttpServletRequest request=null;
	Session session=null;
	String istDate="";
	try {
		
	//	Common common=new Common();
		CollectionReportDAO dao=new CollectionReportDAO();
		System.out.println("in insert  not deptd");
		
    request=ServletActionContext.getRequest();
    session=HibernateUtil.getSession("hibernate.cfg.xml");
    String vehicleId= request.getParameter("vehicleId");
    String reasonId = request.getParameter("reason");
    String reasonName = request.getParameter("reasonName");
    String desc = request.getParameter("desc");
         System.out.println("in inserts"+vehicleId+"=="+reasonName+"=="+desc);
        model.jaxb.xml.trimax.com.VtsResponse6 activeresult = null;
        com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
         com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
         if(!reasonId.equals("3") && desc.equalsIgnoreCase("")){    //Reason selected others
        	 activeresult = port.webInsertReasonIntoNRD(vehicleId, reasonName,rbKey); // calling Web service
        }else{
        	 activeresult = port.webInsertReasonIntoNRD(vehicleId,desc, rbKey); // calling Web service
        }
//         activeresult = port.webInsertReasonIntoSchNotDeparted(vehicleId, reasonName,desc ,rbKey); // calling Web service
//         if(activeresult.getWaybillDetails().get(0).getSTATUS().equalsIgnoreCase("true")){
//        	 addActionMessage("Reason inserted Successfully");
//         }else
//         {
//        	 addActionError("Error ! Try Again Later");
//         }
         
//HttpServletResponse response = ServletActionContext.getResponse();
//PrintWriter out;
//
//
//	out = response.getWriter();
      
	
	} catch (Exception e) {

		e.printStackTrace();
	}
finally{

}
	return null;
}

/*public String checkReasonForStationary(String vehicleNo,String deviceID )
{
	String reason="";
	WsUtil_Service service = new WsUtil_Service();
	WsUtil port = service.getWsUtilPort();
	// TODO initialize WS operation arguments here
	String ismapped = "";
	System.out.println("vehicleNo==="+vehicleNo+"-----"+deviceID);
	
	try
	{
		VtsResponse alertresult = port.getReasonForStationaryVehicles(vehicleNo,deviceID,rbKey);
		
		for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			 JSONArray ja = new JSONArray();
			 reason =  alertresult.getVtsDatamodel().get(i).getReason();
		}
		if(alertresult.getVtsDatamodel().size()==0){
		System.out.println("size is-----"+alertresult.getVtsDatamodel().size());
		ismapped="";
}
		else 
		{
			ismapped=reason;
		}
		}
	catch(Exception e){
		e.printStackTrace();
		ismapped = reason;
	}
	finally{

	}	
	
	return ismapped;
}
*/

/*public String checkReasonForEarly(String vehicleNo,String deviceID )
{
	String reason="";
	WsUtil_Service service = new WsUtil_Service();
	WsUtil port = service.getWsUtilPort();
	// TODO initialize WS operation arguments here
	String ismapped = "";
	System.out.println("vehicleNo==="+vehicleNo+"-----"+deviceID);
	
	try
	{
		VtsResponse alertresult = port.getReasonForEarlyArr(vehicleNo,deviceID,rbKey);
		
		for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			 JSONArray ja = new JSONArray();
			 reason =  alertresult.getVtsDatamodel().get(i).getReason();
		}
		if(alertresult.getVtsDatamodel().size()==0){
		System.out.println("size is-----"+alertresult.getVtsDatamodel().size());
		ismapped="";
}
		else 
		{
			ismapped=reason;
		}
		}
	catch(Exception e){
		e.printStackTrace();
		ismapped = reason;
	}
	finally{

	}	
	
	return ismapped;
}
*/
/*public String checkReasonForLate(String vehicleNo,String deviceID )
{
	String reason="";
	WsUtil_Service service = new WsUtil_Service();
	WsUtil port = service.getWsUtilPort();
	// TODO initialize WS operation arguments here
	String ismapped = "";
	System.out.println("vehicleNo==="+vehicleNo+"-----"+deviceID);
	
	try
	{
		VtsResponse alertresult = port.getReasonForLate(vehicleNo,deviceID,rbKey);
		
		for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			 JSONArray ja = new JSONArray();
			 reason =  alertresult.getVtsDatamodel().get(i).getReason();
		}
		if(alertresult.getVtsDatamodel().size()==0){
		System.out.println("size is-----"+alertresult.getVtsDatamodel().size());
		ismapped="";
}
		else 
		{
			ismapped=reason;
		}
		}
	catch(Exception e){
		e.printStackTrace();
		ismapped = reason;
	}
	finally{

	}	
	
	return ismapped;
}*/

/*public String checkReasonForSOS(String vehicleNo,String deviceID )
{
	String reason="";
	WsUtil_Service service = new WsUtil_Service();
	WsUtil port = service.getWsUtilPort();
	// TODO initialize WS operation arguments here
	String ismapped = "";
	System.out.println("vehicleNo==="+vehicleNo+"-----"+deviceID);
	
	try
	{
		VtsResponse alertresult = port.getReasonDataForSOS(vehicleNo,deviceID,rbKey);
		
		for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			 JSONArray ja = new JSONArray();
			 reason =  alertresult.getVtsDatamodel().get(i).getReason();
		}
		if(alertresult.getVtsDatamodel().size()==0){
		System.out.println("size is-----"+alertresult.getVtsDatamodel().size());
		ismapped="";
}
		else 
		{
			ismapped=reason;
		}
		}
	catch(Exception e){
		e.printStackTrace();
		ismapped = reason;
	}
	finally{

	}	
	
	return ismapped;
}*/

/*public String checkReasonForNRD(String vehicleNo,String deviceID )
{
	String reason="";
	WsUtil_Service service = new WsUtil_Service();
	WsUtil port = service.getWsUtilPort();
	// TODO initialize WS operation arguments here
	String ismapped = "";
	System.out.println("vehicleNo==="+vehicleNo+"-----"+deviceID);
	
	try
	{
		VtsResponse alertresult = port.getReasonDataForNRDVeh(vehicleNo,deviceID,rbKey);
		
		for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			 JSONArray ja = new JSONArray();
			 reason =  alertresult.getVtsDatamodel().get(i).getReason();
		}
		if(alertresult.getVtsDatamodel().size()==0){
		System.out.println("size is-----"+alertresult.getVtsDatamodel().size());
		ismapped="";
}
		else 
		{
			ismapped=reason;
		}
		}
	catch(Exception e){
		e.printStackTrace();
		ismapped = reason;
	}
	finally{

	}	
	
	return ismapped;
}
*/
/*public String checkReasonForSchNotDept(String vehicleNo,String deviceID )
{
	String reason="";
	WsUtil_Service service = new WsUtil_Service();
	WsUtil port = service.getWsUtilPort();
	// TODO initialize WS operation arguments here
	String ismapped = "";
	System.out.println("vehicleNo==="+vehicleNo+"-----"+deviceID);
	
	try
	{
		VtsResponse alertresult = port.getReasonDataForScheduleNotDept(vehicleNo,deviceID,rbKey);
		
		for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			 JSONArray ja = new JSONArray();
			 reason =  alertresult.getVtsDatamodel().get(i).getReason();
		}
		if(alertresult.getVtsDatamodel().size()==0){
		System.out.println("size is-----"+alertresult.getVtsDatamodel().size());
		ismapped="";
}
		else 
		{
			ismapped=reason;
		}
		}
	catch(Exception e){
		e.printStackTrace();
		ismapped = reason;
	}
	finally{

	}	
	
	return ismapped;
}

*/
/*public String checkReasonForDeviate(String vehicleNo,String deviceID )
{
	String reason="";
	WsUtil_Service service = new WsUtil_Service();
	WsUtil port = service.getWsUtilPort();
	// TODO initialize WS operation arguments here
	String ismapped = "";
//	System.out.println("vehicleNo==="+vehicleNo+"-----"+deviceID);
	
	try
	{
		VtsResponse alertresult = port.getReasonDataForDeviation(vehicleNo,deviceID,rbKey);
		
		for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			 JSONArray ja = new JSONArray();
			 reason =  alertresult.getVtsDatamodel().get(i).getReason();
		}
		if(alertresult.getVtsDatamodel().size()==0){
//		System.out.println("size is-----"+alertresult.getVtsDatamodel().size());
		ismapped="";
}
		else 
		{
			ismapped=reason;
		}
		}
	catch(Exception e){
		e.printStackTrace();
		ismapped = reason;
	}
	finally{

	}	
	
	return ismapped;
}
*/


	}



