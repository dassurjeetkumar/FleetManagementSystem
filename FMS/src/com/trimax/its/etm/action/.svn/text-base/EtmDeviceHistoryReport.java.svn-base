package com.trimax.its.etm.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.EtmDeviceHistoryDao;
import com.trimax.its.vts.dao.VtsDataDAO;


public class EtmDeviceHistoryReport extends ActionSupport {
	
	String path="";
	char ft = 15;
	String str="";
	public String startdate;
	public String depotid;
	public String type;
	
	String c=" ";

	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;
	
	String regionTypeAjaxString = "";
	
	public Map<Integer,String> depotlist1;
private Map<Integer, String> divisionlist;
	
	
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	
 

public Map<Integer, String> getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(Map<Integer, String> depotlist1) {
		this.depotlist1 = depotlist1;
	}

public String execute(){
	  
//	  System.out.println("Enter into execute()");
	  try {
			HttpServletRequest req = ServletActionContext.getRequest();
			 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
			VtsDataDAO vvt = VtsDataDAO.getInstance();
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
					parentId = vvt.getDepot1(orgtypeid,orgchartid);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	  return "success";
  }
	
  public String getEtmDeviceHistoryReport(){
	  
//	  System.out.println("Enter into EtmDeviceReport()");
	  
	  try {
//		   System.out.println("startdate==="+startdate);
//		   System.out.println("depot==="+depotid);
//		   System.out.println("Type==="+type);
			CollectionReportDAO dao=new CollectionReportDAO();
			 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
//			String date1=dao.getDateFromPickerDate(startdate);
//			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM");
//			String month=sdf1.format(startdate);
//			System.out.println("month===="+month);
			String data[]=startdate.split("-");
			String mont=data[0];
			String year=data[1];
			String month=""+year+"-"+mont;
//			System.out.println("month===="+month);
         int type1=Integer.parseInt(type);
//         System.out.println("Type1==="+type1);
			String month1="2016-12";
//			String date2=dao.getDateFromPickerDate(enddate);
			Common common = new Common();
			String orgname="";
			String depot="";
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 
			 String sql="select etm_number,division_id,depot_id,etm_issue,etm_pickup_date,etm_replace_date,remarks from etm_device_history "+
                         " where depot_id="+depotid+" and etm_pickup_date LIKE '%"+month+"%'";
			 if(type1==0){
				 System.out.println("Enter into type 0");
				 sql += " and etm_replace_date IS NOT NULL";
			 }else if(type1==1){
				 System.out.println("Enter into type 1");
				 sql += " and etm_replace_date IS NULL";
			 }
			 Query query = session1.createSQLQuery(sql).addScalar("etm_number").addScalar("division_id").addScalar("depot_id").addScalar("etm_issue").addScalar("etm_pickup_date").addScalar("etm_replace_date").addScalar("remarks");
			  
			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String,Object>> aliasToValueMapList = query.list();
				double Totalvalues=0.0;
//				double Totaletimservicetaxamount=0.0;
//			    double Totalbagservicetaxamount=0.0;
//			    double Totalpasssservicetaxamount=0.0;
//			    double Granttotalservicetaxamount=0.0;
//				String realpath = ServletActionContext.getRequest()
//						.getRealPath("/");
//				String filePath = "Ticketing/";
//				String fileName = "EtmDeviceHistoryReport.txt";
				
				regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Etm Device History Report</br>From Month:- "+startdate+"</h4></div>";

		        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
		    


				regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Etm No</th><th>Division Name</th><th>Depot Name</th><th>Etm Issues</th><th>Etm Pickup Date</th><th>Etm Submit Date</th><th>Remarke</th>" +
					"</tr></thead><tbody>";
//				 System.out.println("aliasToValueMapList.size()=="+aliasToValueMapList.size());
				
			        int j=0;  
			     for (int i = 0; i < aliasToValueMapList.size(); i++) {
			    	 regionTypeAjaxString +="<tr>";
				Map<String,Object> list = aliasToValueMapList.get(i);
	     		 j=i+1;
	     		String division_id=list.get("division_id").toString();
	     		String depot_id=list.get("depot_id").toString();
	     		String division_name=etmdao.getDevisionName(division_id);
				String depot_name=etmdao.getDepotName(division_id,depot_id);
	     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//				regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("etm_number").toString()+"</td>";
//				regionTypeAjaxString +="<td align='right'>"+ list.get("division_id").toString() +"</td>";
//				regionTypeAjaxString +="<td align='right'>"+list.get("depot_id").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+ division_name+"</td>";
				regionTypeAjaxString +="<td align='right'>"+depot_name+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("etm_issue").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("etm_pickup_date").toString()+"</td>";
				if(list.get("etm_replace_date")==null){
					regionTypeAjaxString +="<td align='right'></td>";
				}else{
				regionTypeAjaxString +="<td align='right'>"+list.get("etm_replace_date").toString()+"</td>";
				}
				if(list.get("remarks")==null){
					regionTypeAjaxString +="<td align='right'></td>";
				}else{
				regionTypeAjaxString +="<td align='right'>"+list.get("remarks").toString()+"</td>";
				}
				
				 System.out.println("Reached Here");
				String  etmno = list.get("etm_number").toString();
				String devision = list.get("division_id").toString();
				String depotid = list.get("depot_id").toString();
				String etmissue = list.get("etm_issue").toString();
				String etmpickup = list.get("etm_pickup_date").toString();
				String etmreplace="";
				if(list.get("etm_replace_date")==null){
					etmreplace="";
				}else{
				 etmreplace = list.get("etm_replace_date").toString();
				}
				String remark = "";
				if(list.get("remarks")==null){
					remark="";
				}else{
					remark = list.get("remarks").toString();
				}
//				Totalvalues=Double.parseDouble(etim)+Double.parseDouble(bag)+Double.parseDouble(pass);
//				
//				Totaletimservicetaxamount+=Double.parseDouble(list.get("ETIMServiceTax").toString());
//				Totalbagservicetaxamount+=Double.parseDouble(list.get("BagServiceTax").toString());
//				Totalpasssservicetaxamount+=Double.parseDouble(list.get("PassServiceTax").toString());
//				Granttotalservicetaxamount+=Totalvalues;
				
				
//				regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
				
				
			
				
				   regionTypeAjaxString +="</tr>";
			}
			     System.out.println("End Here1");
//					regionTypeAjaxString +="<tr><td colspan='2'><center><b>Sub Total</b></center></td><td align='right'><b>"+ Totaletimservicetaxamount+"</td>"+"<td align='right'><b>"+ Totalbagservicetaxamount+"</td><td align='right'><b>"+ Totalpasssservicetaxamount+"</td><td align='right'><b>"+ Granttotalservicetaxamount+"</td></tr>" +"\n";  
					
					 regionTypeAjaxString += "</table> </div> </div> ";
					
					 
					 
					 regionTypeAjaxString += "<div class='component'><b>Summary Of Etm Devic History</b><table border='2' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'>";
			   		 regionTypeAjaxString +="<tr>";
			            regionTypeAjaxString +="<th><center><b>Total No.Of Records</b></center></th><th><center><b> Resolved Records</b></center></th></tr>";
			   		 
			            int totalrecord=etmdao.getTotalMonthWiseRecord(depotid,month);
			            int totalresolvedrecord=etmdao.getTotalResolvedMonthWiseRecord(depotid,month);
			            
			         regionTypeAjaxString +="<tr><td align='right'>"+ totalrecord +"</td>";
			   		 regionTypeAjaxString +="<td align='right'>"+ totalresolvedrecord +"</td>";
//			   		 regionTypeAjaxString +="<td align='right'>No</td></tr>";  
//			         regionTypeAjaxString +="<tr><td align='left'>65</td>";
//			   		 regionTypeAjaxString +="<td align='right'>"+ sixtyfivetotal +"</td>";
//			   		 regionTypeAjaxString +="<td align='right'>"+sixtyfivetotalvalues+"</td></tr>";
//			   		 regionTypeAjaxString +="<tr><td align='left'>70</td>";
//			   		 regionTypeAjaxString +="<td align='right'>"+ seventytotal +"</td>";
//			   		 regionTypeAjaxString +="<td align='right'>"+seventytotalvalues+"</td></tr>";
//			   		regionTypeAjaxString +="<tr><td align='left'>Luggage</td>";
//			  		 regionTypeAjaxString +="<td align='right'>"+ Luggagetickettotal +"</td>";
//			  		 regionTypeAjaxString +="<td align='right'>"+Luggagetickettotalvalues+"</td></tr>";
			   		 
//			   		 regionTypeAjaxString +="<tr><td align='center'><b>Total</b></td><td align='right'>"+totalsixtyseventysum+"</td><td align='right'>"+totalsixtyseventysumvalues+"</td></tr>";
			   		 
//			   		 headingprint ="Summary Of Etm Devic History \n"
//			   		            +"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ __ _ _ _ _ _\n" 
//								+"|"+add("Total No.Of Records",20)+ "|"+add("Resolved Records",20)+ "|"+"\n"
//								+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ __ _ _ _ _ _\n";
//			   		 
//			   		str+=ft+add(headingprint,5);
//			        str+="|" + add(String.valueOf(totalrecord), 20) +"|" + add(String.valueOf(totalresolvedrecord),20)+ "|"+"\n"; 
					 
//					 str+= ""+add("",5)+"" + add("Sub Total", 22) + "|" + add(String.valueOf(Totaletimservicetaxamount), 16) +"|" + add(String.valueOf(Totalbagservicetaxamount),15)+ "|"+ add(String.valueOf(Totalpasssservicetaxamount),16)+"|"+ add(String.valueOf(Granttotalservicetaxamount),10)+ "|"+"\n";
					 
//					 System.out.println("End Here2");
			
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
