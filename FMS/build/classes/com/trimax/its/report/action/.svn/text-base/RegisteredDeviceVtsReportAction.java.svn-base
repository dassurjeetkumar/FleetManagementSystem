package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class RegisteredDeviceVtsReportAction {

	
	private static final long serialVersionUID = 1L;
	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	 public String startdate;
	    public String enddate;
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


		public String getEnddate() {
			return enddate;
		}


		public void setEnddate(String enddate) {
			this.enddate = enddate;
		}


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
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		@SuppressWarnings("finally")
		public String getregisteredDeviceDetails()
		{
			HttpServletRequest req = ServletActionContext.getRequest();
					try {
					
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				   Session session1 = null;
					Transaction transaction  = null;
					Common common = new Common();
					String depot=req.getParameter("depotId");
					String division=req.getParameter("divId");
//					String statr1= req.getParameter("startdate");
//					String end1= req.getParameter("startdate");
//					String date1=common.getDateFromPicker(startdate);
//					String date2=common.getDateFromPicker(enddate);
					String queryyy;
					if(division.equalsIgnoreCase("0") && depot.equalsIgnoreCase("0")){
						queryyy="";
					}else if(division.equalsIgnoreCase("0")){
						queryyy= " and oc.org_chart_id='"+depot+"'";
					}else if(depot.equalsIgnoreCase("0")){
						queryyy= " and oc.parent_id='"+division+"'";
					}else{
						queryyy= " and oc.parent_id='"+division+"' and oc.org_chart_id='"+depot+"'";
					}
					
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					
					
					String sql = "";
					 sql="select org_name,v.license_number license_number,d.device_serial_number device_serial_number,date(vd.created_date) regdate from vehicle v inner join vehicle_device vd on v.vehicle_id = vd.vehicle_id " +
					 		"and vd.status='ACTIVE' " +
					 		"inner join device d on d.device_id = vd.device_id inner join device_org do on do.device_id=d.device_id  " +
					 		"inner join org_chart oc on oc.org_chart_id = do.org_id and do.status='ACTIVE' " +
					 		"where v.status='ACTIVE' and v.deleted_status=0 and d.status='ACTIVE' and d.deleted_status=0  "+queryyy+" order by org_name" ;
						  		
					 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					 Query query = session1.createSQLQuery(sql);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					
					  
					
					 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>VTS Registered Device Report</br></h4></div>";
				     regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				     
					 
                      
				      
						
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Depot No</th><th>Vehicle No</th><th>Device No</th><th>Register Date</th>"+"</tr></thead><tbody>";
						

						 for (int i = 0; i < aliasToValueMapList.size(); i++) {
								regionTypeAjaxString +="<tr>";
								Map<String, Object> list = aliasToValueMapList.get(i);
								JSONArray ja = new JSONArray();
								int j=i+1;
							
								regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
								regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
								regionTypeAjaxString +="<td align='right'>"+list.get("license_number").toString()+"</td>";
								regionTypeAjaxString +="<td align='right'>"+ list.get("device_serial_number").toString() +"</td>";
								regionTypeAjaxString +="<td align='right'>"+ list.get("regdate").toString() +"</td>";
							
								   regionTypeAjaxString +="</tr>";
							
							}
								   
							
					     
						    regionTypeAjaxString += "</tbody></table></div>        </div>"; 
						   
					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					
					
					
						
						//FileOutputStream FOS = new FileOutputStream(path);
						//PrintWriter PW = new PrintWriter(FOS);
						

						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (Exception e) {
						
						e.printStackTrace();
					}

					return null;
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
	
	
}
