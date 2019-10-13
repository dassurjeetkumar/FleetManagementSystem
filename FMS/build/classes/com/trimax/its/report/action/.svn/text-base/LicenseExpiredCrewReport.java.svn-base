package com.trimax.its.report.action;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/*
import com.trimax.its.ticketing.dao.WaybillDAO;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
*/

import com.trimax.its.OnlineWaybillLC.action.OnlineWaybillLCDAO;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

import com.trimax.its.vehicle.model.DockingType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

public class LicenseExpiredCrewReport {
	String regionTypeAjaxString = "";
	public OnlineWaybillLCDAO getWaybillShowDao() {
		return waybillShowDao;
	}

	public void setWaybillShowDao(OnlineWaybillLCDAO waybillShowDao) {
		this.waybillShowDao = waybillShowDao;
	}
	private OnlineWaybillLCDAO waybillShowDao = new OnlineWaybillLCDAO();
	
	private String divisionId;
	
	private String depotId;
	
	
	private Map<Integer,String> divisionMap;
	int j=1;
String path="";
char ft = 15;
String str="";
public String startdate;
public String enddate;
String c=" ";

char fl = 18;
char f2 = 12;
int pagi = 1;

int FULL_LEN = 120;
int HALF_LEN = 60;

	public String getEnddate() {
	return enddate;
}

public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

public void setEnddate(String enddate) {
	this.enddate = enddate;
}

	public String execute() {
		divisionMap=waybillShowDao.getDivisionLincence();
		return "success";
	}

	public String LicenseCrewReportMethod()
	{
		
		
	  CollectionReportDAO dao=new CollectionReportDAO();
		String date1=dao.getDateFromPicker(startdate);
		//String date2=dao.getDateFromPicker(enddate);
		
		/*String orgname=dao.getOrgName();
		String depot=dao.getDepotName();
		*/
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		//Common common = new Common();
		HttpServletRequest req = ServletActionContext.getRequest();
		
		Session session1 = null;
		Transaction transaction  = null;
String sql1 = null;
		
		String depotid="";
		String ticketdate="";
		String divisionid="";
		divisionid=req.getParameter("divisionId");
		setDivisionId(divisionid);
		if(req.getParameter("divisionId").equals("1"))
		{
			
			 sql1="select ifnull(emp.EMPLOYEE_NAME,'') as EMPLOYEE_NAME,ifnull(emp.TOKEN,'') as TOKEN,ifnull(emp.DRIVING_LC_NO,'') as DRIVING_LC_NO,ifnull(emp.DL_EXPIRY_DT,'') as DL_EXPIRY_DT  from employee emp where  emp.DL_EXPIRY_DT<='" +date1+ "' and EMPLOYEE_DESIGNATION='DRIVER' and status='ACTIVE'";	

			
		}
		
		
		else if(req.getParameter("depotId").equals("all")){
			depotid = req.getParameter("depotId");
			setDepotId(depotid);
			ticketdate = req.getParameter("startdate");
			setStartdate(startdate);
			String divid=getDivisionId();
			divisionid=req.getParameter("divisionId");
	 
			divisionMap=waybillShowDao.getDivision();
			
			
			 sql1="select ifnull(emp.EMPLOYEE_NAME,'') as EMPLOYEE_NAME,ifnull(emp.TOKEN,'') as TOKEN,ifnull(emp.DRIVING_LC_NO,'') as DRIVING_LC_NO,ifnull(emp.DL_EXPIRY_DT,'') as DL_EXPIRY_DT  from employee emp where  emp.DL_EXPIRY_DT<='" +date1+ "' and emp.DIVISION_ID='"+divisionid+"' and EMPLOYEE_DESIGNATION='DRIVER' and status='ACTIVE'";	
		
		}
		
		else
		{
			depotid = req.getParameter("depotId");
		 	setDepotId(depotid);
			setStartdate(ticketdate);
			ticketdate = req.getParameter("startdate");
			setStartdate(startdate);
			String divid=getDivisionId();
			divisionid=req.getParameter("divisionId");                                                                                                                                                                              	
		 
			divisionMap=waybillShowDao.getDivision();
			 sql1="select ifnull(emp.EMPLOYEE_NAME,'') as EMPLOYEE_NAME,ifnull(emp.TOKEN,'') as TOKEN,ifnull(emp.DRIVING_LC_NO,'') as DRIVING_LC_NO,ifnull(emp.DL_EXPIRY_DT,'') as DL_EXPIRY_DT  from employee emp where  emp.DL_EXPIRY_DT<='" +date1+"' and emp.DIVISION_ID='"+divisionid+"' and org_chart_id='"+depotid+"'  and EMPLOYEE_DESIGNATION='DRIVER' and status='ACTIVE'";	

			
			
		}
		
				
		//String sql1="select ifnull(emp.EMPLOYEE_NAME,'') as EMPLOYEE_NAME,ifnull(emp.TOKEN,'') as TOKEN ,ifnull(emp.PF,'') as PF,ifnull(emp.DL_EXPIRY_DT,'') as DL_EXPIRY_DT  from employee emp where  emp.CONDUCTOR_LC_EXPDATE<='" +date1+ "' and status='ACTIVE'";	
		
		
		
		
		
		
		
		
		
		
		
		
		
//		System.out.println("query"+sql1);	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		Query query = session1.createSQLQuery(sql1);
		
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		String denom="";
		double Tickettotal=0;
		double Totalvalues=0;
		double tTickettotal=0;
		double tTotalvalues=0;
		String realpath = ServletActionContext.getRequest()
				.getRealPath("/");
		
	
		String filePath = "Ticketing/";

		String fileName = "LicenseExpiredCrewReport.txt";
		
		     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"/*+orgname+" </br>"*//*+depot+" </br>Daily Ticket Consumption</br> Date:- "*/+startdate+"</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	        String nwkr=/*"                                     "+orgname+"                                                                          \n"
      		  +   "                                            "+depot+"                                                                                  \n" */
      		   "                                   Driver License Expiry Report                                                              					 \n"
      		  +   "                                  	Date :-"+startdate+ "                                                     \n "
      		  +   "                                                             Run Date:-"+runDateTime+"               \n";
        
	        
	        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table  border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:80%;border-collapse: collapse;'></thead>";
			regionTypeAjaxString +="<tr><td align='center' width='10%'><b>Sr No.</b></td><td align='center' width='25%'><b>Driver Name</b></td><td align='center' width='10%'><b>Driver Token No.</b></td><td align='center' width='10%'><b>Driver License No.</b></td><td align='center' width='25%'><b>Expiry Date</b></td></tr>";
			String headingprint =" "+"-------------------------------------------------------------------------------------\n" 
					+add("Sr No",5)+"|"+add("Driver Name",20)+ "|"+add("Driver Token No.",15)+ "|"+add("Driver License No.",15)+"|"+add("Expiry Date",20)+"|"+"\n"
					+ " "+ "-------------------------------------------------------------------------------------\n";
			 
			    String path = realpath + filePath + fileName;
		       
		        str+=ft+nwkr+add(headingprint,5);
		        
		   regionTypeAjaxString +="<tr><td colspan='5'><b>Designation:Driver</b> </td></tr>";
	/*	   str+=add("Designation:Conductor", 10) + ""+add("", 45)+""+"\n\n";*/
		   str+="-------------------------------------------------------------------------------------\n";

		    for (int i = 0; i < aliasToValueMapList.size(); i++) {
			regionTypeAjaxString +="<tr>";
			Map<String, Object> list = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			 j=j+i;
			regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
			regionTypeAjaxString +="<td align='left'>"+ list.get("EMPLOYEE_NAME").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("TOKEN").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("DRIVING_LC_NO").toString()+"</td>";
			regionTypeAjaxString +="<td align='center'>"+list.get("DL_EXPIRY_DT").toString()+"</td>";
			
			 String EMPLOYEE_NAME=list.get("EMPLOYEE_NAME").toString();
			 String TOKEN=list.get("TOKEN").toString();
			 String DRIVING_LC_NO=list.get("DRIVING_LC_NO").toString();
			 String DL_EXPIRY_DT=list.get("DL_EXPIRY_DT").toString();
			  str+=""+add(String.valueOf(j),5)+ "|" + add(EMPLOYEE_NAME, 20) + "|" + add(TOKEN, 15) +"|" + add(DRIVING_LC_NO,15)+ "|"+ add(DL_EXPIRY_DT, 20) +"|"+"\n"; 
			  str+="-------------------------------------------------------------------------------------\n";
			
			
			   regionTypeAjaxString +="</tr>";
		
		}
		    
	
		
		// LicenseCrewReportMethod1();
		 
		 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span><input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div>";

		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
		try {
			
			FileOutputStream FOS = new FileOutputStream(path);
			PrintWriter PW = new PrintWriter(FOS);
		String p=str;

		PW.println(p);
		PW.close();
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void LicenseCrewReportMethod1()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		j++;
		str+="\n";
		
		CollectionReportDAO dao=new CollectionReportDAO();
		String date1=dao.getDateFromPicker(startdate);
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		
		Session session1 = null;
		Transaction transaction  = null;
		
		
		String sql1 = null;
		
		String depotid="";
		String ticketdate="";
		String divisionid="";
		divisionid=request.getParameter("divisionId");
		setDivisionId(divisionid);
		if(req.getParameter("divisionId").equals("1"))
		{
			
		 sql1="select ifnull(emp.EMPLOYEE_NAME,'') as EMPLOYEE_NAME,ifnull(emp.TOKEN,'') as TOKEN,ifnull(emp.PF,'') as PF,ifnull(emp.DL_EXPIRY_DT,'') as DL_EXPIRY_DT  from employee emp where  emp.DL_EXPIRY_DT<='" +date1+ "' and EMPLOYEE_DESIGNATION='DRIVER' and status='ACTIVE'";	

			
		}
		
		else if(request.getParameter("depotId").equals("all")){
			depotid = request.getParameter("depotId");
			setDepotId(depotid);
			ticketdate = request.getParameter("startdate");
			setStartdate(startdate);
			String divid=getDivisionId();
			divisionid=request.getParameter("divisionId");
	 
			divisionMap=waybillShowDao.getDivision();
			
			
			 sql1="select ifnull(emp.EMPLOYEE_NAME,'') as EMPLOYEE_NAME,ifnull(emp.TOKEN,'') as TOKEN,ifnull(emp.PF,'') as PF,ifnull(emp.DL_EXPIRY_DT,'') as DL_EXPIRY_DT  from employee emp where  emp.DL_EXPIRY_DT<='" +date1+ "' and emp.DIVISION_ID='"+divisionid+"' and EMPLOYEE_DESIGNATION='DRIVER' and status='ACTIVE'";	
		
		}
		
		else
		{
			depotid = request.getParameter("depotId");
		 	setDepotId(depotid);
			setStartdate(ticketdate);
			ticketdate = request.getParameter("startdate");
			setStartdate(startdate);
			String divid=getDivisionId();
			divisionid=request.getParameter("divisionId");                                                                                                                                                                              	
		 
			divisionMap=waybillShowDao.getDivision();
			 sql1="select ifnull(emp.EMPLOYEE_NAME,'') as EMPLOYEE_NAME,ifnull(emp.TOKEN,'') as TOKEN,ifnull(emp.PF,'') as PF,ifnull(emp.DL_EXPIRY_DT,'') as DL_EXPIRY_DT  from employee emp where  emp.DL_EXPIRY_DT<='" +date1+"' and emp.DIVISION_ID='"+divisionid+"' and org_chart_id='"+depotid+"'  and EMPLOYEE_DESIGNATION='DRIVER' and status='ACTIVE'";	

			
			
		}
		
		
		
		
		
		
		
		
		
		
				
		//String sql1="select ifnull(emp.EMPLOYEE_NAME,'') as EMPLOYEE_NAME,ifnull(emp.TOKEN,'') as TOKEN,ifnull(emp.PF,'') as PF,ifnull(emp.DL_EXPIRY_DT,'') as DL_EXPIRY_DT  from employee emp where  emp.DL_EXPIRY_DT<='" +date1+ "' and status='ACTIVE'";	
//		System.out.println("query"+sql1);	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		Query query = session1.createSQLQuery(sql1);
		
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		String denom="";
		
		String realpath = ServletActionContext.getRequest()
				.getRealPath("/");
		

					regionTypeAjaxString +="<tr><td colspan='5'><b>Designation:Driver</b></td></tr>";
					
			str+= add("Designation:Driver", 15) + ""+add("", 450)+""+"\n\n";
			str+="-------------------------------------------------------------------------------------\n";

		    for (int i = 0; i < aliasToValueMapList.size(); i++) {
			regionTypeAjaxString +="<tr>";
			Map<String, Object> list = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			 j=j+i;
			regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
			regionTypeAjaxString +="<td align='left'>"+ list.get("EMPLOYEE_NAME").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("TOKEN").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("PF").toString()+"</td>";
			regionTypeAjaxString +="<td align='center'>"+list.get("DL_EXPIRY_DT").toString()+"</td>";
			
			
			 String EMPLOYEE_NAME1=list.get("EMPLOYEE_NAME").toString();
			 String TOKEN1=list.get("TOKEN").toString();
			 String PF1=list.get("PF").toString();
			 String DL_EXPIRY_DT1=list.get("DL_EXPIRY_DT").toString();
			 str+=""+add(String.valueOf(j),5)+ "|" + add(EMPLOYEE_NAME1, 20) + "|" + add(TOKEN1, 15) +"|" + add(PF1,15)+ "|"+ add(DL_EXPIRY_DT1, 20) +"|"+"\n";
			  str+="-------------------------------------------------------------------------------------\n";

			   regionTypeAjaxString +="</tr>";
		
		}
		    
		
		
		 
		
		
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
	
	static String addMoney(String str1, int a1) {
        StringBuilder sb = new StringBuilder(str1);
        StringBuilder sb2 = new StringBuilder();

        //String sb1 =
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
    }
	
	
	
	
	public String getPerticularDepotName() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		
		List<String> l1 = waybillShowDao.getDepotId(parentId);
		List<String> l2 = waybillShowDao.getDepotName(parentId);
		req.getSession().setAttribute("Depotid",l1);
		List <String> depid=(List<String>)req.getSession().getAttribute("Depotid");
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------Select------</option>" +
								" <option value='all'>------ALL------</option>"               ;
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
	
	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public Map<Integer,String> getDivisionMap() {
		return divisionMap;
	}

	public void setDivisionMap(Map<Integer,String> divisionMap) {
		this.divisionMap = divisionMap;
	}

	public String getDepotId() {
		return depotId;
	}

	public void setDepotId(String depotId) {
		this.depotId = depotId;
	}

	



}
