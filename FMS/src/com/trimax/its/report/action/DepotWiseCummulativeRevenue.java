package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DepotWiseCummulativeRevenue {
	
	
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

	int subtotalTickets=0;
	int subtotalValues=0;
	
	String regionTypeAjaxString = "";
	
	

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
		return "success";
	}
	
	
	
	public String depotWiseCummulativeRevenue(){
		try {
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			String date2=common.getDateFromPicker(enddate);
		      Date  ss1=new Date();
		    
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			
			Session session1 = null;
			Transaction transaction  = null;
			
			String sql="";
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 
			 sql="select round(sum(ETIM_Coll_Amt+Bag_Coll_Amt+etm_card_amt),2) reve,org_name from depot_revenue wd " +
			 		"inner join org_chart oc on oc.org_chart_id=wd.depot_id " +
			 		"where audited_date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' " +
			 		"group by wd.depot_id order by org_name";	
			 Query query = session1.createSQLQuery(sql).addScalar("reve").addScalar("org_name")
				 		;
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

						
						     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>DepotWise Cummulative Revenue Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

					        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
					        
					        
					        
					    
					        
					        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
							regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Depot Name</th><th>Revenue</th>"+"</tr></thead><tbody>";
							
							

							for (int i = 0; i < aliasToValueMapList.size(); i++) {
								int j=i+1;
								regionTypeAjaxString +="<tr>";
								Map<String, Object> list = aliasToValueMapList.get(i);
								regionTypeAjaxString +="<td>"+j+"</td>";
								regionTypeAjaxString +="<td>"+list.get("org_name").toString()+"</td>";
								regionTypeAjaxString +="<td>"+list.get("reve").toString()+"</td>";
							
								regionTypeAjaxString +="</tr>";
							}
								
						
						 
						 regionTypeAjaxString += "</tbody></table></div> </div>  " ;
						 		//"<div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";

						HttpServletResponse response = ServletActionContext.getResponse();
						PrintWriter out;
						
		
							out = response.getWriter();
							out.print(regionTypeAjaxString);
						} catch (IOException e) {
							
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
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
