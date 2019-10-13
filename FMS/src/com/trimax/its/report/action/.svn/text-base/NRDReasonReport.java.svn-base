package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.vts.NewWsVts_Service;
import com.trimax.vts.VtsDataModelNew;

public class NRDReasonReport extends ActionSupport{
	private Map<Integer, String> divisionlist;
	public String startdate;
    public String enddate;
    private String sloveingdate;
	public String getSloveingdate() {
		return sloveingdate;
	}

	public void setSloveingdate(String sloveingdate) {
		this.sloveingdate = sloveingdate;
	}

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

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
//		System.out.println("division........"+divisionlist);	
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
	public void getnrdreason(){
		//Session session1=null;
		try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
	   
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot=req.getParameter("depot");
	//String divid=req.getParameter("divison");
	

	int chart=Integer.parseInt(depot);
	String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
    String orgchartid=(String)req.getSession().getAttribute("orgchartid");
    int or=Integer.parseInt(orgtypeid);
	VtsDataDAO dao = VtsDataDAO.getInstance();
	int parentId = Integer.parseInt(req.getParameter("divison"));
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
		 System.out.println("-------------------"+l1);
	        for(int i=0;i<l1.size();i++){
	        	if(i>0){qry=qry+",";}
	        	qry=qry+l1.get(i);
	        }qry=qry+")";
	}else{qry=qry+chart+")";}
	
	/*Connection connection=null;
	Statement stmt=null;*/
	
/*	ResultSet rs=null;
	Transaction transaction  = null;*/
	//session1 = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
	/*String sql="select org_name,ifnull(vehicle_id,'')vehicle_id,dr.device_id,reason,sub_reason,ifnull(description,'') description,reason_date,ifnull(solved_date,'') solved_date from dashboard_reason dr "+
                  "inner join nrd_devices_daily ndd on dr.device_id=ndd.DEVICE_ID "+
                  "where ndd.DEPOT_ID="+depot+" and dr.reason_date between '"+date1+"' and '"+date2+"' and reason_type=3";*/
	
	/*String sql="select org_name,ifnull(vehicle_id,'')vehicle_id,dr.device_id,reason,sub_reason,ifnull(description,'') description,reason_date,"+
	           "ifnull(solved_date,'') solved_date from dashboard_reason dr "+
	           "inner join nrd_devices_daily ndd on dr.vehicle_id=ndd.lICENCE_NUMBER or ndd.DEVICE_ID=dr.device_id "+
	            "where ndd.DEPOT_ID="+depot+" and "+
	           "dr.reason_date between '"+date1+"' and '"+date2+"' and reason_type=3";
	Query query = session1.createSQLQuery(sql);
	System.out.println(sql);
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();*/
	String rbKey = String.valueOf(getSysParameterForVts());
	NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
	com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
	VtsResponseNew alertresult = port.webGetnrdreport(qry, date1,date2, rbKey);

	regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>NRD REASON Report </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

	regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
        

	regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>OrgName</th><th>Vehicle No</th><th>Device Id</th><th>Reason</th><th>Sub Reason</th>" +
			"<th>Description</th><th>Reason Date</th><th>Solved_Remark</th><th>Solved Date</th>" +
				""+"</tr></thead><tbody>");
	

	        HttpServletResponse response = ServletActionContext.getResponse();
	       
	        for (int i = 0; i < alertresult.getVtsDatamodelnew().size(); i++) {
	        	int j=i+1;
	        	VtsDataModelNew list = alertresult.getVtsDatamodelnew().get(i);
	        
		   regionTypeAjaxString1.append("<tr>");
			regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.getOrgName() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.getLICENCENUMBER() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+list.getDEVICEID() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.getReason() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.getSubReason()  +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.getDescription() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.getReasonDate() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.getSloveRemark() +"</td>");
		if(list.getSolvedDate().equals("not solved")){	
			
			//System.out.println("else--"+list.getDEVICEID());
		regionTypeAjaxString1.append("<td align='right'>"
			+"<a data-toggle='modal' class='btn green' role='button' href='#mymodal1'  onclick=javascript:getdepotwiseexceptionreport('"
			+ list.getLICENCENUMBER()
            + "','"
            + list.getDEVICEID()
            + "','"
            + list.getReason().replace(" ", "_")
            + "','"
            + list.getSubReason().replace(" ", "_")
            + "','"
            + list.getDescription().replace(" ", "_")
            +"','"
           +list.getReasonDate().substring(0,10)
			+ "') title='Write a Reason' id='alert_details"
		
			+ "'>"
			+ "Write solved details"+""
			+ "</a>"+
	"</td>"); // System.out.println("inside---");
	}else{
		regionTypeAjaxString1.append("<td align='right'>"+list.getSolvedDate()  +"</td>");}
	
		 regionTypeAjaxString1.append("</tr>");
				
				 }
	   ;
				 PrintWriter out;
            

	out = response.getWriter();
	out.print(regionTypeAjaxString1);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void savenrd(){
		Session session=null;
		//============
		int i=0;
		String status=null;
			try {
			//System.out.println("HIIIIII");
			HttpServletRequest req=ServletActionContext.getRequest();
			//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//Date now = new Date();
			//String strDate = simpleDateFormat.format(now);
			//String actiondate=req.getParameter("dateaction");
			Common common = new Common();
			String date1=common.getDateFromPicker(sloveingdate);
			//date1=date1+" 00:00:00";
			String resdescrip=req.getParameter("resdescrip").trim();
	     	String devid=req.getParameter("devid");
	     	String vehid=req.getParameter("vehid");
	     	String readate1=req.getParameter("readate");
	     	String readate=common.getDateFromPicker(readate1);
	     	String userid=req.getSession().getAttribute("userid").toString();
			   String rbKey = String.valueOf(getSysParameterForVts());
			 NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
				com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
				System.out.println("parms are=="+date1+"==="+resdescrip+"==="+devid+"==="+vehid+"==="+readate);
				
			//System.out.println("middle==========");
				//VtsResponseNew alertresult = port.webinsertslovenrddata(devid,vehid,resdescrip,date1,readate,userid,rbKey);
				VtsResponseNew alertresult=port.webinsertslovenrddata(devid,vehid,resdescrip,date1,readate,userid,rbKey);
				System.out.println("Size of files u geting--------"+alertresult.getVtsDatamodelnew().size());
		        	VtsDataModelNew list = alertresult.getVtsDatamodelnew().get(0);
		        	 status=list.getStatus();
		        	 System.out.println("status is------"+status);
				HttpServletResponse response = ServletActionContext.getResponse();
				 String regionTypeAjaxString1="Record successfully Inserted";
				PrintWriter out;
				  out = response.getWriter();
				 out.print(regionTypeAjaxString1);
				
			  
			    
			} catch (Exception e) {
			
			/*	 ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "2");
			       addActionMessage("Record not inserted ");*/
				
			   	//session.close();
				e.printStackTrace();
				
			} 
			/* if(status.equals("true")){ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "1");
		       addActionMessage("Record inserted Successfully");}
			 else{
				 ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "2");
			       addActionMessage("Record not inserted "); */
			 //}
			
			//redirect
		
			//return "success";
	}
}
