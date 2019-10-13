package com.trimax.its.report.action;

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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

public class DepotWiseRevenueCummulative extends ActionSupport{

	 public String startdate;
	    public String enddate;
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
	@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub
	return "success";
}
public String depotCummulativeDepotRevenueReport(){
	 HttpServletRequest req = ServletActionContext.getRequest();
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
			 
			 sql="SELECT org_name,sum(ETIM_Coll_Amt)etm,sum((Bag_Coll_Amt-luggage_amount-daily_pass_amount))bag," +
			 		"sum(luggage_amount)lugg,sum(daily_pass_amount)pass,sum(etm_card_amt)card," +
			 		"sum((ETIM_Coll_Amt+Bag_Coll_Amt+etm_card_amt))gross FROM depot_revenue dr " +
			 		"inner join org_chart oc on oc.org_chart_id=dr.depot_id " +
			 		"WHERE audited_date between '"+date1+"' and '"+date2+"' group by depot_id order by org_name";	
				 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("etm").addScalar("bag").addScalar("lugg").addScalar("pass")
						 .addScalar("card").addScalar("gross");
			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
			 
				 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>DepotWise Revenue Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

			        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
			        
			        
			        
			    
			        
			        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
					regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Depot</th><th> Passenger Fare (ETM)</th><th>Passenger Fare (Manual)</th>" +
							"<th>Passenger Fare (Card)</th><th>Daily Pass Revenue (Manual)</th><th>Luggage Revenue (Manual)</th>" +
							"<th>Total (Gross)</th></tr></thead><tbody>";
				
			//	regionTypeAjaxString +="<tr><td colspan='22'>No Records Found</td></tr>";
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						int j=i+1;
						regionTypeAjaxString +="<tr>";
						Map<String, Object> list = aliasToValueMapList.get(i);
						regionTypeAjaxString +="<td>"+j+"</td>";
						regionTypeAjaxString +="<td>"+list.get("org_name").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("etm").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("bag").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("card").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("pass").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("lugg").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("gross").toString()+"</td>";
						
						regionTypeAjaxString +="</tr>";
					}
						
				 regionTypeAjaxString += "</tbody></table></div>        </div>"; 
				 HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
						out = response.getWriter();
						out.print(regionTypeAjaxString);
		}catch (Exception e) {
			e.printStackTrace();
		}
	 return null;
}
}
