package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.VehicleStatusModel;
import com.trimax.its.vts.dao.VtsDataDAO;

public class VehicleStatusAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private Map<Integer, String> divisionlist;
	private Map<Integer,String> statuslist;
	public Map<Integer, String> getStatuslist() {
		return statuslist;
	}

	public void setStatuslist(Map<Integer, String> statuslist) {
		this.statuslist = statuslist;
	}

	private String causestatus;
	private String vehiclelist;
	private Map<Integer, String> depotlist;
	private String depotlist1;
	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	private Map<Integer, String> divisionlist1;
	private Map<Integer, String> vehiclelistname;
	
	

	private String remarksVehicle;
	
	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}

	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
	}

	public String getRemarksVehicle() {
		return remarksVehicle;
	}

	public void setRemarksVehicle(String remarksVehicle) {
		this.remarksVehicle = remarksVehicle;
	}

	public Map<Integer, String> getVehiclelistname() {
		return vehiclelistname;
	}

	public void setVehiclelistname(Map<Integer, String> vehiclelistname) {
		this.vehiclelistname = vehiclelistname;
	}

	public Map<Integer, String> getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	boolean isSuccess = false;
	
	String orgtypeid;
	String orgchartid;
	
	public String getOrgtypeid() {
		return orgtypeid;
	}

	public void setOrgtypeid(String orgtypeid) {
		this.orgtypeid = orgtypeid;
	}

	public String getOrgchartid() {
		return orgchartid;
	}

	public void setOrgchartid(String orgchartid) {
		this.orgchartid = orgchartid;
	}


	public String getCausestatus() {
		return causestatus;
	}

	public void setCausestatus(String causestatus) {
		this.causestatus = causestatus;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
       
	public String execute(){
		
		return "success";
	}
	private String reason;
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String myCurrentStatus;
	
	public String getMyCurrentStatus() {
		return myCurrentStatus;
	}

	public void setMyCurrentStatus(String myCurrentStatus) {
		this.myCurrentStatus = myCurrentStatus;
	}

	int count=0;
	String vehicle="";
	private String status;
public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

public String vehicleStatusSave(){
	
	HttpServletRequest req = ServletActionContext.getRequest();
	String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	Session session = null;
	Common common = new Common();
	String vno="";
	 String vehicle_status="";

	try {
	
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		int usrid = Integer.parseInt(ServletActionContext
				.getRequest().getSession().getAttribute("userid")
				.toString());
		vehiclelist = req.getParameter("vehiclelist");
		 status = req.getParameter("causestatus");
	     String place =req.getParameter("placeId");
	     
	     System.out.println("reason is-----  "+place+"====="+status+"====="+reason);
		 ServletActionContext.getRequest().getSession().setAttribute("status", status);
		 ServletActionContext.getRequest().getSession().setAttribute("valueforChangeVehicle", vno);
		 
		
		 switch (Integer.parseInt(status)) {
		case 0:
			reason="";
			status="Normal";
			vehicle_status="N";
			break;
	case 1:
		reason=req.getParameter("CWReasonName");
		  status="CW";
		  vehicle_status="CW";
		  place = req.getParameter("CWPlace");
			break;
	case 2:
		 status="DW";
		 vehicle_status="DW";
		 reason=req.getParameter("DWReason");
		break;
	case 3:
		 status="Accident";
		 vehicle_status="A";
		 reason=req.getParameter("accidentReason");
		break;
	case 4:
		status="Breakdown";
		vehicle_status="B";
		 reason=req.getParameter("breakdownReason");
		break;
	case 5:
		status="Scrap";
		reason="";
		vehicle_status="S";
		break;
	case 6:
		status="Road Worthy Spare";
		vehicle_status="RW";
		 reason=req.getParameter("roadWorthyReason");
		break;
	case 7:
		 reason="";
		 vehicle_status="AD";
		 status="Vehicle Held at Authorized Dealer";
		break;
	case 8:
		reason="";
		vehicle_status="PS";
		 status="Vehicle Held at Police Station";
		break;
	case 9:
		reason="";
		vehicle_status="D";
		 status="Docking";
		break;
	case 10:
		reason="";
		vehicle_status="SP";
		 status="Scrap Proposal";
		break;
		default:
			reason="";
			vehicle_status="CC";
			 status="Vehicle Went on CC";
			break;
		}
		 
	     System.out.println("reason is-----  "+place+"====="+status+"====="+reason);
	 
		remarksVehicle = req.getParameter("notes");
		session.beginTransaction();
		
		  String hql1 = "select license_number as license_number from vehicle where vehicle_id="+vehiclelist;
	         vno = common.getDBResultStr(session, hql1, "license_number");
	        System.out.println("vno"+vno);
		
		
		if(!checkVehicleAlreadyinSameStatus(vno,status,common ,session)) {
			
		String hql = "update vehicle set cause_status ='"+vehicle_status +"',scrapped_date=null,scrapped='No',updated_date=now() , updated_by= "+usrid+" where vehicle_id= "+vehiclelist +" and status = 'ACTIVE'";
        Query qry3= session.createSQLQuery(hql);
        qry3.executeUpdate();
        
        System.out.println("updated");
      
       VehicleStatusModel vsm = new VehicleStatusModel();
       
       if(!vehicle_status.equalsIgnoreCase("S")) {
    	   if(vehAlreadyScrap(Integer.parseInt(vehiclelist),common,session)) {
    		 	String scrapQuery = "update vehicle_scrap set deleted_status =1,updated_date=now() , updated_by= "+usrid+" where vehicle_id= "+vehiclelist +" ";
    		    Query scQry= session.createSQLQuery(scrapQuery);
    		    scQry.executeUpdate();
    	   }
       }
       
       try{
       vsm.setVehicleno(vno);
       vsm.setStatuscause(status);
       vsm.setFrom_date(currentDate);
        vsm.setTo_date("0000-00-00 00:00:00");	
       vsm.setRemarksvehicle(remarksVehicle.trim());
       vsm.setReason(reason);
       vsm.setPlace(place);
       ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "1");
       addActionMessage("Record for '"+vno+"' inserted Successfully");
       }catch (Exception e) {
		
    	   e.printStackTrace();
	}
       
        //String hql2 = "select count(*) as count from Vehicle_Status where vehicle_no='"+vno+"' AND to_date IS NULL";
        String hql2 = "select count(*) as count from Vehicle_Status where vehicle_no='"+vno+"' ";
         count = common.getDBResultInt(session, hql2, "count");
        
        
        if(count==0){
        	session.save(vsm);
//        	 System.out.println("data saved in table");
        	
        }else{
        	
//        	String veh="select vehicle_no vehicleno from Vehicle_Status where vehicle_no="+vehiclelist;
//        	vehicle = common.getDBResultStr(session, veh, "vehicleno");
    		System.out.println("status is------"+status);
    		System.out.println("status is------"+vsm.getTo_date());
//    			if(status.equalsIgnoreCase("N"))
//    			{
    			    String QRY = "select max(status_id) as status_id from Vehicle_Status where vehicle_no='"+vno+"' ";
    		         int maxid = common.getDBResultInt(session, QRY, "status_id");
    				System.out.println("inside-------------------");
    				 String hql3 = "update Vehicle_Status set remarks='"+remarksVehicle.trim()+"',to_date=now(), reason='"+reason+"' where "
    				 		+ " status_id="+maxid+"";
    	        	 System.out.println(hql3);
    	        	 Query qry4= session.createSQLQuery(hql3);
    	             qry4.executeUpdate();
//    			}
//    			else
//    			{ 
    				System.out.println("before date----------"+vsm.getTo_date());
    				session.save(vsm);
//    			}
    				
        	 session.getTransaction().commit();
    	isSuccess = true;
       
        }
        ServletActionContext.getRequest().getSession().setAttribute("valueforChangeVehicle", vno);
    				ServletActionContext.getRequest().getSession().setAttribute("status", status);
		}else {
			System.out.println("Alreaddy avail");
		       ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "22");
		       ServletActionContext.getRequest().getSession().setAttribute("valueforChangeVehicle", vno);
				ServletActionContext.getRequest().getSession().setAttribute("status", status);
		}
		} catch (Exception ex) {
			
			ServletActionContext.getRequest().getSession().setAttribute("valueforChangeVehicle", vno);
			ServletActionContext.getRequest().getSession().setAttribute("status", status);
			ex.printStackTrace();
			isSuccess = false;
		} finally {
			
//			if(session != null && session.isOpen()){
				session.close();
//			}
			
		}
	
	return "success";
	}
	   public boolean vehAlreadyScrap(int vehicleId , Common com,Session sess) {
		   boolean flag=false;
		   String countRow = "Select count(*) total from vehicle_scrap where vehicle_id="+vehiclelist+" and deleted_status=0";
	         int noofRow = com.getDBResultInt(sess, countRow, "total");
	         if(noofRow >0) {
	        	 flag=true;
	         }
	         
		return flag;
		
	   }
	   
	 
	   public boolean checkVehicleAlreadyinSameStatus(String vehicleNo ,String vehicleStatus, Common com,Session sess) {
		   boolean flag=false;
		   String totalRecords = "select count(*) total from Vehicle_Status where vehicle_no ='"+vehicleNo+"' and status_cause='"+vehicleStatus+"' and date(from_date)=curdate() and to_date='0000-00-00 00:00:00'";
	         int noofRows = com.getDBResultInt(sess, totalRecords, "total");
	         if(noofRows >0) {
	        	 flag=true;
	         }
	         
		return flag;
		
	   }
	   
	   
public String vehicleStatus(){
	
	try {
		String vehicleNo = "";
		String vehStatus = "";
		System.out.println("action-----"+ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage"));
	
//		if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage")==null){
//				
//		}
//
//		else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("0")){
//		}
//		else{
////			vehStatus = ServletActionContext.getRequest().getSession().getAttribute("valueForstaus").toString();
////			vehicleNo = ServletActionContext.getRequest().getSession().getAttribute("status").toString();
////			
//			addActionMessage("Record inserted successfully with "+vehicleNo+" for "+vehStatus+" ");
//			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage","0");
//		}
//		else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("2")){
//			
//			vehStatus = ServletActionContext.getRequest().getSession().getAttribute("status").toString();
//			vehicleNo = ServletActionContext.getRequest().getSession().getAttribute("valueforChangeVehicle").toString();
//			
//			addActionMessage(" "+vehStatus+" is Already available for "+vehicleNo+" ");
//			
//			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage",null);
//
//		}
		
		if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage")==null){
			
		}else 
			if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("0")){
			}
			else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("22")){
				
//				vehStatus = ServletActionContext.getRequest().getSession().getAttribute("status").toString();
//				vehicleNo = ServletActionContext.getRequest().getSession().getAttribute("valueforChangeVehicle").toString();
				
//				addActionMessage(" "+vehicleNo+" is Already available in "+vehStatus+"  ");
				addActionMessage("Record is already Available");
				ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage",null);
	
			}
			else {
				vehStatus = ServletActionContext.getRequest().getSession().getAttribute("status").toString();
			vehicleNo = ServletActionContext.getRequest().getSession().getAttribute("valueforChangeVehicle").toString();
			
			addActionMessage("Record inserted successfully with "+vehicleNo+" for "+vehStatus+" ");
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage","0");
			}
		
		
		
		
		
				 
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		
		//divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
		//Ends..
		
		divisionlist = vvt.getDivisionName();
	}catch (Exception e) {
		e.printStackTrace();
	}
		return SUCCESS;
}




//public String getStatusList() {
//
//	HttpServletRequest req = ServletActionContext.getRequest();
//    
//	List<String> l1 = getVehicleStatusList();
////	List<String> l2 = getStatusName();
//	String regionTypeAjaxString = "";
//	regionTypeAjaxString += "<option value='0'>------select------</option>";
//	//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
//	for (int i = 0; i < l1.size(); i++) {
//		//String vehiclearr[] = l1.get(i).toString().split("#");
//	//System.out.println(l1.get(0));
//		regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
//				+ l2.get(i).toString()  + "</option>";
//	}
//	HttpServletResponse response = ServletActionContext.getResponse();
//	PrintWriter out;
//	try {
//		out = response.getWriter();
//		out.print(regionTypeAjaxString);
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//
//	return null;
//}
/*@SuppressWarnings("finally")
public List getVehicleStatusList() {
	List statusList = new ArrayList();
	String qry = "select 	cause_id,status_name from cause_status where deleted_status=0  and parent_id=0";
	try {
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				// list.add(rs.get("device_serial_number").toString()) ;
				statusList.add(rs.get("cause_id").toString());
				statusList.add( rs.get("status_name").toString());
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		HibernateUtil.closeSession();
		return statusList;
	}
}*/



public String getVehicleStatusList() {
	Map<Integer, Object> statusList = new HashMap<Integer, Object>();
	StringBuffer regionTypeAjaxString = new StringBuffer();
	String qry = "select 	cause_id,status_name from cause_status where deletd_status=0  and parent_id=0";
	try {
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<Integer, Object>> aliasToValueMapList = query.list();
		regionTypeAjaxString.append("<option value='0'>--Select--</option>");
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<Integer, Object> rs = aliasToValueMapList.get(i);
//				statusList.put(Integer.parseInt(rs.get("cause_id").toString()), rs.get("status_name").toString());
				regionTypeAjaxString.append( "<option value='" + rs.get("cause_id").toString() + "'>"
						+ rs.get("status_name").toString()  + "</option>");
			}
			System.out.println("status dropdown---"+regionTypeAjaxString);
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} 
	
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	try {
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	finally {
		HibernateUtil.closeSession();
		//return statusList;
	}
	
	return null;
}


@SuppressWarnings("unlikely-arg-type")
public String getCWPlaces() {
	Map<Integer, Object> statusList = new HashMap<Integer, Object>();
	StringBuffer regionTypeAjaxString  =new StringBuffer();
//	String qry = "select 	cause_id,status_name from cause_status where deletd_status=0  and parent_id=0";
	String qry="select cause_id	,status_name from cause_status where deletd_status=0 and parent_id=2 and sub_Type=1";
	try {
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<Integer, Object>> aliasToValueMapList = query.list();
		regionTypeAjaxString.append("<option value='0'>--Select--</option>");

		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<Integer, Object> rs = aliasToValueMapList.get(i);
//				statusList.put(Integer.parseInt(rs.get("cause_id").toString()), rs.get("status_name").toString());
				regionTypeAjaxString.append("<option value='" + rs.get("status_name").toString() + "'>"
						+ rs.get("status_name").toString()  + "</option>");
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} 
	
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	try {
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	finally {
		HibernateUtil.closeSession();
		//return statusList;
	}
	
	return null;
}



public String getPerticularVehicle() {

	HttpServletRequest req = ServletActionContext.getRequest();
	VtsDataDAO dao = VtsDataDAO.getInstance();
	int parentId = Integer.parseInt(req.getParameter("val"));
	
	String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
    String orgchartid=(String)req.getSession().getAttribute("orgchartid");
    
	List<String> l1 = getVehicleID1(parentId);
	List<String> l2 = getVehicleName1(parentId);
	String regionTypeAjaxString = "";
	regionTypeAjaxString += "<option value='0'>------select------</option>";
	//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
	for (int i = 0; i < l1.size(); i++) {
		//String vehiclearr[] = l1.get(i).toString().split("#");
	//System.out.println(l1.get(0));
		regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
				+ l2.get(i).toString()  + "</option>";
	}
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	try {
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (IOException e) {
		e.printStackTrace();
	}

	return null;
}


public List getVehicleID1(int parentid) {
	String qry="";
	int usrroleid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("roleid").toString());
	System.out.println("--------------------------"+usrroleid);
   if((usrroleid !=5 && usrroleid !=1)) {
		 qry = "select vehicle_id from vehicle where deleted_status=0 and cause_status !='S'  and org_chart_id= "+parentid+" and status='ACTIVE'   order by vehicle_id";
   }else {
		 qry = "select vehicle_id from vehicle where deleted_status=0 and org_chart_id= "+parentid+" and status='ACTIVE'   order by vehicle_id";
   }
	List list = new ArrayList();
	try {
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				// list.add(rs.get("device_serial_number").toString()) ;
				list.add(rs.get("vehicle_id").toString());
				// list.add( rs.get("license_number").toString());
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		HibernateUtil.closeSession();
		return list;
	}
}

public List getVehicleName1(int parentid) {
	String qry="";
	int usrroleid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("roleid").toString());
	   if((usrroleid !=5 && usrroleid !=1)) {
			 qry = "select license_number from vehicle where deleted_status=0 and cause_status !='S'  and org_chart_id= "+parentid+" and status='ACTIVE'   order by vehicle_id";
	   }else {
			 qry = "select license_number from vehicle where deleted_status=0 and org_chart_id= "+parentid+" and status='ACTIVE'   order by vehicle_id";
	   }
	List list = new ArrayList();
	try {
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				// list.add(rs.get("device_serial_number").toString()) ;
				// list.add(rs.get("vehicle_id").toString());
				list.add(rs.get("license_number").toString());
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		HibernateUtil.closeSession();
		return list;
	}

}


}
