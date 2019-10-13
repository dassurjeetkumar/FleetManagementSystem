package com.trimax.its.report.action;

import java.io.FileOutputStream;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;

import com.opensymphony.xwork2.ActionSupport;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class CumulativeBagwiseTktPassIssueAction extends ActionSupport{
	/**
	 * 
	 */
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
		public String getBagwiseDetails()
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
					
					String statr1= req.getParameter("startdate");
					String end1= req.getParameter("startdate");
					String date1=common.getDateFromPicker(startdate);
					String date2=common.getDateFromPicker(enddate);
					String queryyy;
					
					
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					
					
					String sql = "";
//					 sql="select dat,sum(tckt_revenue) tckt_revenue ,sum(lugg_revenue) lugg_revenue,sum(sistyfive_pass) sistyfive_pass,sum(seventy_pass) seventy_pass,sum(incentive) incentive,sum(bata) bata,sum(misc) misc" +
//						  		" from (select dat,tckt_revenue,lugg_revenue,sistyfive_pass,seventy_pass," +
//						  		" incentive,bata,misc from cummulative_revenue WHERE dat between '"+date1+"' and '"+date2+"') a group by dat order by dat" ;
						  		
					 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
//					 Query query = session1.createSQLQuery(sql);
//				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//					List<Map<String, Object>> aliasToValueMapList = query.list();
					String filePath = "Ticketing/";

					String fileName = "BagwiseCumulativeRevenueReport.txt";
					  
					
					 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Bagwise Cumulative Revenue Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";
				     regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				     
					 
                      
				        String nwkr=""    +   "                                    Bagwise Cumulative Revenue   " +
			      		
				        		"\n                                    From Date:"+ startdate+"  End Date:"+ enddate+"                                              ";
						
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Date (dd-mm-yyyy)</th><th>ETM Rev</th><th>Manual Rev</th><th>Pass Rev Rs.65/-</th><th>Pass Rev Rs.70/-</th><th>Pass Rev Rs.140/-</th><th>Luggage ETM Rev</th><th>Luggage Manual Rev</th><th>Total Rev</th>" +
								"                          "+"</tr></thead><tbody>";
						

						String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n" 
								+add("SNo",3)+"|"+add("Date",10)+ "|"+add("ETM Rev",25)+ "|"+add("Manual Rev",7)+"|"+add("Dp-65",14)+"|"+add("Dp-70",10)+"|"+add("Dp-140",11)+"|"+add("Luggage ETM Rev",7)+"|"+add("Luggage Manual Rev",10)+"|"+add("Total Rev",14)+"|"+
								add("Net Total",14)+"|"+"\n"+
								    " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
						
							
						 
						    String path = realpath + filePath + fileName;
					        str+=ft+nwkr+add(headingprint,5);

							
					     
						    regionTypeAjaxString += "</tbody></table></div>        </div>"; 
						    
                             str+="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ____ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
						   ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					
					
					
						
						FileOutputStream FOS = new FileOutputStream(path);
						PrintWriter PW = new PrintWriter(FOS);
						
					String p=str;
					
					PW.println(p);
					PW.close();
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





	
	
	



