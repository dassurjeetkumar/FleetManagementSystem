package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class DepotWiseScheduleInformationListReport extends ActionSupport {
	
	String path="";
	char ft = 15;
	String str="";
	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	double earnAmmount=0.0;
	double dedtotal=0.0;
	int subtotalValues=0;
	double totalammount=0.0;

	String regionTypeAjaxString = "";
	
	public String execute() {
		
		return "success";
	}
	
	public String getDepotWiseScheduleListReport(){
		

		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname="";
				//dao.getOrgName();
		String depot="";
				//dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="";
		 
//		 Query query = session1.createSQLQuery(sql).addScalar("Date").addScalar("ETIMServiceTax").addScalar("BagServiceTax").addScalar("PassServiceTax");
//		  
//		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List<Map<String,Object>> aliasToValueMapList = query.list();
		 List<Map<String,Object>> aliasToValueMapList=null;
//			double Totalvalues=0.0;
//			double Totaletimservicetaxamount=0.0;
//		    double Totalbagservicetaxamount=0.0;
//		    double Totalpasssservicetaxamount=0.0;
//		    double Granttotalservicetaxamount=0.0;
		
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+"  </br>Depot wise Schedules Information List";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        
	       

//	        regionTypeAjaxString += "<tr><th>Depot</th><th>No Of</th><th>Schedule</th><th>Total</th><th>Over All</th><th>Depot Wise</th><th colspan='2' align='center'>O.T Details</th></tr>" +
//					      "<tr><th>No</th><th>Sch's</th><th>Kms</th><th>Trips</th><th>Veh.uti.</th><th>Ded.Kms</th><th>Sch's</th><th>Hrs</th> "+
//					"</tr>";
			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString += "<thead><tr><th>SlNo.</th><th>Sch No.</th><th>Division Or Sector</th><th>Brand Service</th><th>Depot Number</th><th>From</th><th>To</th><th>Route Length</th><th>Total</th><th>Total</th><th>Service</th><th>1st Shift/Day1/GS Total</th><th>2st Shift Total</th><th>Total Steering</th><th>Total OT</th><th>Charted Service</th><th>Departure Time</th><th>D/O Crew Change Time & Place</th><th>NO & NS Night Halt Time & Place</th><th>N/O & NS Morning Out time & Place</th><th>Depot In Time</th><th>Chartered  Schedule</th><th>Tr- No & Form-4 Effect Date</th></tr>" +
					"<tr><th></th><th>1 to ...</th><th></th><th>Type</th><th></th><th></th><th></th><th></th><th>KMS</th><th>Trips</th><th>DO/GS/NO/NS/SD/GNO/LNO</th><th>Steering Hrs</th><th>Steering Hrs</th><th>Hrs & Min</th><th>Hrs & Min</th><th>Yes/No</th><th></th><th></th><th></th><th></th><th></th><th>Y/ N </th><th></th></tr>" +     
					"</thead><tbody>";
			 
			
			 if(aliasToValueMapList==null){
//		       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
		            regionTypeAjaxString += "<tr>";
		            regionTypeAjaxString += "<td colspan='20' align='center'><b>No Result Found</b></td>";
		           
		            regionTypeAjaxString += "</tr>";
		       	
		       }else{
		
                  
//		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//		    	 regionTypeAjaxString +="<tr>";
//			Map<String,Object> list = aliasToValueMapList.get(i);
//     		int j=i+1;
//			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("ETIMServiceTax").toString()+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("BagServiceTax").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("PassServiceTax").toString()+"</td>";
//			
//			
//			String  date = list.get("Date").toString();
//			String etim = list.get("ETIMServiceTax").toString();
//			String bag = list.get("BagServiceTax").toString();
//			String pass = list.get("PassServiceTax").toString();
//			Totalvalues=Double.parseDouble(etim)+Double.parseDouble(bag)+Double.parseDouble(pass);
//			
//			Totaletimservicetaxamount+=Double.parseDouble(list.get("ETIMServiceTax").toString());
//			Totalbagservicetaxamount+=Double.parseDouble(list.get("BagServiceTax").toString());
//			Totalpasssservicetaxamount+=Double.parseDouble(list.get("PassServiceTax").toString());
//			Granttotalservicetaxamount+=Totalvalues;
//			
//			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			
//			
			
//			   regionTypeAjaxString +="</tr>";
//		}
//				regionTypeAjaxString +="<tr><td colspan='2'><center><b>Sub Total</b></center></td><td align='right'><b>"+ Totaletimservicetaxamount+"</td>"+"<td align='right'><b>"+ Totalbagservicetaxamount+"</td><td align='right'><b>"+ Totalpasssservicetaxamount+"</td><td align='right'><b>"+ Granttotalservicetaxamount+"</td></tr>" +"\n";  
//				
				 regionTypeAjaxString += "</table></div> </div>  ";
				 
		       }
		 
				 
		 
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	

}
