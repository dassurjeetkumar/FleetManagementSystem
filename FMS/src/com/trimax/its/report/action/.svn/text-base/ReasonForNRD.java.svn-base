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

import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.vts.NewWsVts_Service;
import com.trimax.vts.VtsDataModelNew;

public class ReasonForNRD extends ActionSupport {
	private Map<Integer, String> depotlist;
	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}

	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
	}
	private Map<Integer, String> divisionlist;
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	private String startdate;
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String execute() {
	try {
		
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
		

		HttpServletRequest req = ServletActionContext.getRequest();
		System.out.println(req.getSession().getAttribute("orgtypeid")+"------"+req.getSession().getAttribute("orgchartid"));
		 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
//		VtsDataDAO vvt = VtsDataDAO.getInstance();
		if(orgtypeid.equals("2")){
        	//Our Logic
		int parentId=0;
		try {
		} catch (Exception ex) {

		}
		try {
			parentId = vvt.getDepot1DC(orgtypeid,orgchartid);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId);
		//Ends..
        }else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
		divisionlist = vvt.getDivisionName();
		//schedulelist = vvt.getScheduleName();
        }else{
        	int parentId=0;
			try {
			} catch (Exception ex) {

			}
			try {
				System.out.println(orgtypeid+"====="+orgchartid);
				parentId = vvt.getDepot1(orgtypeid,orgchartid);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
        }
	
	
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "success";
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
        		regionTypeAjaxString += "<option value='0'>------All------</option>";
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
    		regionTypeAjaxString += "<option value='0'>------All------</option>";
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
    		regionTypeAjaxString += "<option value='0'>------All------</option>";
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
	
	public String getNrdSubReason() {
		
		Map<Integer, String> EmplyeeMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("");
		HttpServletRequest req=ServletActionContext.getRequest();
			String chartid=req.getParameter("val");
			String regionTypeAjaxString = "";
			regionTypeAjaxString += "<option value=" + 0
					+ ">" + "--select--" + "</option>";
		try {
			Query query = session
					.createSQLQuery("select reason_name,	nrd_reason_id from nrd_reason where deleted_status=0");
			System.out.println(query);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<Object, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			System.out.println(aliasToValueMapList.size());
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<Object, Object> rs = aliasToValueMapList.get(i);
				regionTypeAjaxString += "<option value='" + rs.get("reason_name").toString()+"'>" + rs.get("reason_name").toString() + "</option>";
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
		//session.close();
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getVehicle() {
		
		Map<Integer, String> EmplyeeMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
		HttpServletRequest req=ServletActionContext.getRequest();
			String chartid=req.getParameter("val");
			Common common = new Common();
			String date1=req.getParameter("startdate");
			System.out.println(date1);
			String regionTypeAjaxString = "";
			regionTypeAjaxString += "<option value=" + 0
					+ ">" + "--select--" + "</option>";
		try {
			Query query = session
					.createSQLQuery("select DEVICE_ID,lICENCE_NUMBER from dash_board_Nrd_vehicle_daily where DEPOT_ID="+chartid+" and IST_DATE like '%"+date1+"%'");//.addScalar("DEVICE_ID", Hibernate.INTEGER).addScalar("lICENCE_NUMBER", Hibernate.STRING);
			System.out.println(query);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<Object, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			System.out.println(aliasToValueMapList.size());
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<Object, Object> rs = aliasToValueMapList.get(i);
				//EmplyeeMap.put(Integer.parseInt(rs.get("EMPLOYEE_ID").toString()),rs.get("EMPLOYEE_NAME").toString());
				regionTypeAjaxString += "<option value=" + rs.get("DEVICE_ID").toString()+":"+rs.get("lICENCE_NUMBER").toString()
    					+ ">" + rs.get("lICENCE_NUMBER").toString() + "</option>";
				//System.out.println(rs.get("schedule_id"));
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
		//session.close();
		return null;
	}
	public void getdataforshow() {
		Session session=null;
		try {
		
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
		 session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
		HttpServletRequest req=ServletActionContext.getRequest();
			String chartid=req.getParameter("depot");
			Common common = new Common();
			String startdate1=common.getDateFromPicker(startdate);
			String date1=req.getParameter("startdate");
			System.out.println("in get method  ====="+date1);
			/////////////////////////////////
			int chart=Integer.parseInt(chartid);
			String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
	        int or=Integer.parseInt(orgtypeid);
	    	VtsDataDAO dao = VtsDataDAO.getInstance();
			int parentId = Integer.parseInt(req.getParameter("div"));
			String qry=" in(";
			if(chart==0 && or !=1 && or !=2){
	        List<String> l1 = dao.getDepotId(parentId,orgchartid);
	        System.out.println("-------------------"+l1);
	        for(int i=0;i<l1.size();i++){
	        	if(i>0){qry=qry+",";}
	        	qry=qry+l1.get(i);
	        }qry=qry+")";
			}
			else if(chart==0 && or ==1 || or ==2){
				List<String> l1 = dao.getDepotId(parentId);
//				 System.out.println("-------------------"+l1);
			        for(int i=0;i<l1.size();i++){
			        	if(i>0){qry=qry+",";}
			        	qry=qry+l1.get(i);
			        }qry=qry+")";
			}else{qry=qry+chart+")";}
			////////////////////////////////
			/*String sql="select org_name,lICENCE_NUMBER,ndd.DEVICE_ID,dr.reason,ifnull(sub_reason,'')sub_reason,ifnull(description,'')description,"+
					"ifnull(reason_date,'')reason_date,ifnull(solved_date,'')solved_date "+
                       "from dash_board_Nrd_vehicle_daily ndd "+
                       "left join dashboard_reason dr on ndd.DEVICE_ID=dr.device_id where ndd.DEPOT_ID="+chartid+" "+
                        "and ndd.IST_DATE like '%"+date1+"%' "+
                        "group by ndd.DEVICE_ID";*/
			/*String sql="select org_name,lICENCE_NUMBER,ndd.DEVICE_ID,dr.reason,ifnull(sub_reason,'')sub_reason,ifnull(description,'')description,"+
			"ifnull(reason_date,'')reason_date,ifnull(solved_date,'')solved_date "+ 
			"from dash_board_Nrd_vehicle_daily ndd left join dashboard_reason dr on ndd.DEVICE_ID=dr.device_id "+ 
			"where ndd.DEPOT_ID="+chartid+" and ndd.record_date like '%"+startdate1+"%'";
			Query query = session.createSQLQuery(sql);
			System.out.println(sql);
		    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		    List<Map<String, Object>> aliasToValueMapList = query.list();*/
		    regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>OrgName</th><th>Vehicle No</th><th>Device Id</th><th>Reason</th><th>Sub Reason</th>" +
					"<th>Description</th><th>Reason Date</th><th>Solved Date</th>" +
						""+"</tr></thead><tbody>");
		    HttpServletResponse response = ServletActionContext.getResponse();
		    ///////////////////////////////////
		    String rbKey = String.valueOf(getSysParameterForVts());
	        System.out.println("rbkey is-------"+rbKey);
	        
	        
	        NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
			com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
			
			
		System.out.println("middle=========="+qry);
			VtsResponseNew alertresult = port.webGetreasonnrddata(qry, startdate1,date1, rbKey);
		    ////////////////////////////////////
		       
	        for (int i = 0; i < alertresult.getVtsDatamodelnew().size();i++) {
	        	int j=i+1;
	        	VtsDataModelNew list = alertresult.getVtsDatamodelnew().get(i);
	        
	/*	   regionTypeAjaxString1.append("<tr>");
			regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("org_name").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("lICENCE_NUMBER").toString() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+list.get("DEVICE_ID").toString() +"</td>");
		if(list.get("reason")==null){
			regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
			System.out.println("else--"+list.get("DEVICE_ID").toString());
			regionTypeAjaxString1.append("<td align='right'>"
				+"<a data-toggle='modal' class='btn green' role='button' href='#mymodal1'  onclick=javascript:getdepotwiseexceptionreport('"
				+ list.get("lICENCE_NUMBER").toString()
                + "','"
                + startdate1
                +"','"
               +list.get("DEVICE_ID").toString()
				+ "') title='Write a Reason' id='alert_details"
			
				+ "'>"
				+ "Write Reason"+""
				+ "</a>"+
		"</td>"); // System.out.println("inside---");
		}
		else {System.out.println("null is--"+list.get("reason").toString());
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("reason").toString() +"</td>");}
		regionTypeAjaxString1.append("<td align='right'>"+ list.get("sub_reason").toString()  +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.get("description").toString() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.get("reason_date").toString() +"</td>");
		if(list.get("reason")==null){	
			
			System.out.println("else--"+list.get("DEVICE_ID").toString());
		regionTypeAjaxString1.append("<td align='right'>"
			+"<a data-toggle='modal' class='btn green' role='button' href='#mymodal1'  onclick=javascript:getdepotwiseexceptionreport('"
			+ list.get("lICENCE_NUMBER").toString()
            + "','"
            + startdate1
            +"','"
           +list.get("DEVICE_ID").toString()
			+ "') title='Write a Reason' id='alert_details"
		
			+ "'>"
			+ "Write Reason"+""
			+ "</a>"+
	"</td>"); // System.out.println("inside---");
	}
	else {System.out.println("null is--"+list.get("reason").toString());
		regionTypeAjaxString1.append("<td align='right'>"+list.get("solved_date").toString() +"</td>");}
		//regionTypeAjaxString1.append("<td align='right'>"+list.get("solved_date").toString()  +"</td>");
		//regionTypeAjaxString1.append("<td align='right'>"+ list.get("status").toString()  +"</td>");
		 regionTypeAjaxString1.append("</tr>");*/
	        	   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.getOrgName()+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.getLICENCENUMBER() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.getDEVICEID() +"</td>");
			if(list.getReason()==null){
				regionTypeAjaxString1.append("<td align='right'>"+""+"</td>");
				/*System.out.println("else--"+list.getDEVICEID());
				regionTypeAjaxString1.append("<td align='right'>"
					+"<a data-toggle='modal' class='btn green' role='button' href='#mymodal1'  onclick=javascript:getdepotwiseexceptionreport('"
					+ list.getLICENCENUMBER()
	                + "','"
	                + startdate1
	                +"','"
	               +list.getDEVICEID()
					+ "') title='Write a Reason' id='alert_details"
				
					+ "'>"
					+ "Write Reason"+""
					+ "</a>"+
			"</td>"); // System.out.println("inside---");
*/			}
			else {//System.out.println("null is--"+list.getReason());
				regionTypeAjaxString1.append("<td align='right'>"+ list.getReason() +"</td>");}
			regionTypeAjaxString1.append("<td align='right'>"+ list.getSubReason()  +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.getDescription() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.getReasonDate() +"</td>");
			if(list.getReason()==null){	
				
				//System.out.println("else--"+list.getDEVICEID());
			regionTypeAjaxString1.append("<td align='right'>"
				+"<a data-toggle='modal' class='btn green' role='button' href='#mymodal1'  onclick=javascript:getdepotwiseexceptionreport('"
				+ list.getLICENCENUMBER()
	            + "','"
	            + startdate1
	            +"','"
	           +list.getDEVICEID().replaceAll(" ", "_")
				+ "') title='Write a Reason' id='alert_details"
			
                + "'>"
				+ "Write Reason"+""
				+ "</a>"+
		"</td>"); // System.out.println("inside---");
		}
		else {//System.out.println("null is--"+list.getReason());
			regionTypeAjaxString1.append("<td align='right'>"+list.getSolvedDate() +"</td>");}
			//regionTypeAjaxString1.append("<td align='right'>"+list.get("solved_date").toString()  +"</td>");
			//regionTypeAjaxString1.append("<td align='right'>"+ list.get("status").toString()  +"</td>");
			 regionTypeAjaxString1.append("</tr>");
				
				 }
	   ;
				 PrintWriter out;
            

	out = response.getWriter();
	out.print(regionTypeAjaxString1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//session.close();
		}
	}
	public void savenrd(){
		Session session=null;
		//============
		int i=0;
		String status=null;
			try {
			//System.out.println("HIIIIII");
			HttpServletRequest req=ServletActionContext.getRequest();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			String strDate = simpleDateFormat.format(now);
//			String actiondate=req.getParameter("dateaction");
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			date1=date1+" 00:00:00";
			String reason=req.getParameter("reasonname");
	     String devstatus=req.getParameter("devstatus");

			String subreason="";
			String descrip=req.getParameter("description").trim();
			System.out.println("======="+descrip);
			String vsor="";
//			System.out.println("on road"+req.getParameter("vsor"));
			if(devstatus.equals("Gps Not Fitted")){
			subreason="";
			vsor ="Gps Not Fitted";
			}else {
//				System.out.println("vsr--"+vsor+"==reason=="+reason+"==subresas=="+subreason);
				 vsor=reason;
				subreason=req.getParameter("vsor");
			}
		
//			System.out.println("printing fault======"+subreason+"-----"+reason+"======"+devstatus);
			String deviceid=req.getParameter("deviceid").replaceAll("_", " ");
			String vehicleno=req.getParameter("vehno");
			//System.out.println("device id and number is======"+deviceid);
			//String remark=req.getParameter("remark");
			//String actiontaken=req.getParameter("actiontaken");
	
			//date = simpleDateFormat1.parse(dateaction);
			//System.out.println(date);
			//String insertdate=simpleDateFormat1.format(date);
			//System.out.println(monthofperform+"=="+dateaction);
		
				String regionTypeAjaxString = "Record successfully Saved";
		
				 //session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
				HttpServletResponse res=ServletActionContext.getResponse();
				PrintWriter out;
				out=res.getWriter();
			String userid=req.getSession().getAttribute("userid").toString();
				
				/*session.beginTransaction();
				String qry="insert into dashboard_reason(reason,device_id,vehicle_id,reason_type,sub_reason,description,reason_date,reason_by,created_date) 
				values('"+reason+"','"+deviceid+"','"+vehicleno+"',3,'"+subreason+"','"+descrip+"','"+date1+" 00:00:00',"+userid+",'"+strDate+"')";
				System.out.println("query for inserting-----"+qry);
				Query query=session.createSQLQuery(qry);
				 i=query.executeUpdate();*/
			/*	ActiontoTakenBasedonITSReportspojo atb=new ActiontoTakenBasedonITSReportspojo();
	rs.get("lICENCE_NUMBER").toString()			atb.setDivision(division);
				atb.setDepot(depot);
				System.out.println(empno);
				atb.setEmpno(empno);
				System.out.println(atb.getEmpno());
				atb.setMonthofperform(monthofperform);
				atb.setFault(fault);
				atb.setRemarks(remark);
				atb.setActiontaken(actiontaken);
				atb.setRefno(refno);
				atb.setDateofaction(dateaction);
				atb.setStatus("Active");
				atb.setTakenby(0);
				atb.setCreated_by(Integer.parseInt(req.getSession().getAttribute("userid").toString()));
				atb.setCreated_date(new java.util.Date());
				atb.setUpdated_by(Integer.parseInt(req.getSession().getAttribute("userid").toString()));
				atb.setUpdated_date(null);
				atb.setDeleted_status(0);
				Serializable i=session.save(atb);*/
				//session.getTransaction().commit();
				/*HttpServletResponse res=ServletActionContext.getResponse();
				PrintWriter out;
				out=res.getWriter();*/
				//out.print(regionTypeAjaxString);
				//req.getRequestDispatcher("Reports/actiontakenonits.jsp");
				/*System.out.println(regionTypeAjaxString);
				session.close();*/
			   String rbKey = String.valueOf(getSysParameterForVts());
			 NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
				com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
				
//				parms are==Vehicle Status On Road===150220845===KA01F4671===subreabattery wire connected======2019-01-13 00:00:00===2===2019-01-16 12:47:01-----Vehicle Status On Road

						
				System.out.println("parms are=="+reason+"==="+deviceid+"==="+vehicleno+"===subrea"+subreason+"==="+descrip+"==="+date1+"==="+userid+"==="+strDate+"-----"+vsor);
				
			//System.out.println("middle==========");
				VtsResponseNew alertresult = port.webinsertreasonnrddata(vsor, deviceid,vehicleno,subreason,descrip,date1,userid,strDate,rbKey);
				System.out.println("Size of files u geting--------"+alertresult.getVtsDatamodelnew().size());
		        	VtsDataModelNew list = alertresult.getVtsDatamodelnew().get(0);
		        	 status=list.getStatus();
		        	 System.out.println("status is------"+status);
				HttpServletResponse response = ServletActionContext.getResponse();
				// String regionTypeAjaxString1="send sussfully";
				 //PrintWriter out;
				  out = response.getWriter();
				 out.print(regionTypeAjaxString);
				
			  
			    
			} catch (Exception e) {
			
				/* ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "2");
			       addActionMessage("Record not inserted ");*/
				
			   	//session.close();
				e.printStackTrace();
				
			} 
			/* if(status.equals("true")){ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "1");
		       addActionMessage("Record inserted Successfully");}
			 else{
				 ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "2");
			       addActionMessage("Record not inserted "); 
			 }*/
			
			//redirect
		
			//return "success";
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

	
