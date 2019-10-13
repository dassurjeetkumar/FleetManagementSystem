package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;

public class CumCondEarReport {
	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	 public String startdate;
	    public String enddate;
		private Map<Integer, String> depotlist;
		private Map<Integer, String> cndtokenlist;
		private String ctoken;
		
		public Map<Integer, String> getCndtokenlist() {
			return cndtokenlist;
		}


		public void setCndtokenlist(Map<Integer, String> cndtokenlist) {
			this.cndtokenlist = cndtokenlist;
		}


		public String getCtoken() {
			return ctoken;
		}


		public void setCtoken(String ctoken) {
			this.ctoken = ctoken;
		}


		public Map<Integer, String> getDepotlist() {
			return depotlist;
		}


		public void setDepotlist(Map<Integer, String> depotlist) {
			this.depotlist = depotlist;
		}


		public String getDepotlist1() {
			return depotlist1;
		}


		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}


		private String depotlist1;
	    private Map<Integer, String> divisionlist;
	    private Map<Integer, String> divisionlist1;
	public Map<Integer, String> getDivisionlist1() {
			return divisionlist1;
		}


		public void setDivisionlist1(Map<Integer, String> divisionlist1) {
			this.divisionlist1 = divisionlist1;
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


	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	double totalAmmount=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";

	
		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		public String AjaxCumCondEarReport()
		{
					try {
					
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				   Session session1 = null;
					Transaction transaction  = null;
					String queryyy;
					System.out.println("depotlist1"+depotlist1); 
					System.out.println("ctoken"+ctoken); 
					
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					
					
					String sql = "";
					
					String filePath = "Ticketing/";

					String fileName = "CummulativeConductorWiseReport.txt";
					  
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Cumulative Conductorwise Earning Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></br></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
//				        String nwkr=""    +   "                                   Cumulative Conductorwise Earning Report   " +
//			      		
//				        		"\n                                    From Date:"+ startdate+"  End Date:"+ enddate+"                                              ";
						
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Month</th><th>Tckt Rev M</th><th>ETM Rev</th><th>Lug Rev</th><th>Dp Rev</th><th>Mp Rev</th><th>Schd Km</th><th>Extra Km</th><th>Dead Km</th><th>Can Km</th><th>Oprtd Km</th><th>Tot Rev</th><th>Batta</th><th>Incn</th><th>Misc</th><th>Total Ded</th><th>Net</th>" +
								"                                                      "+"</tr></thead><tbody>";
						

						String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n" 
								+add("SNo",3)+"|"+add("Month",10)+ "|"+add("Tckt Rev M",25)+ "|"+add("ETM Rev",7)+"|"+add("Lug Rev",14)+"|"+add("Dp Rev",10)+"|"+add("Mp Rev",11)+"|"+add("Schd Km",7)+"|"+add("Extra Km",10)+"|"+add("Dead Km",14)+"|"+add("Can Km",14)+"|"+add("Oprtd Km",14)+"|"+add("Tot Rev",14)+"|"+add("Batta",14)+"|"+
								add("Incn",14)+"|"+add("Misc",14)+"|"+add("Total Ded",14)+"|"+add("Net",14)+"|"
							 +" "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
						
							
						 
						    String path = realpath + filePath + fileName;
					   //     str+=ft+nwkr+add(headingprint,5);
						
						
							
					        regionTypeAjaxString +="<tr><td colspan='18'><center><h4><b><span style='color:red'>No Records Found<b></span></h4></center></td></</tr>";
						   
					    
					        
					     
						   // regionTypeAjaxString += "</tbody></table></div><table><tr><td>Tckt Rev M</td><td>Ticket Revenur Manual</td></tr><td>Dp</td><td>Daily Pass</td></tr><tr><td>Mp</td><td>Monthly Pass</td></tr><tr><td>Ded</td><td>Deduction</td></tr></table>";
						    		                    
						    
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
		
		
		public String getCondTokenNo() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
			int parentId = Integer.parseInt(req.getParameter("val"));
			//String date = req.getParameter("selectedDate");
//			Common cm = new Common();
//			String formattedgivendate = cm.getDateFromPicker(date);
//			System.out.println("formattedgivendate" + formattedgivendate);
			List<String> l2 = dao.getCondTokenNoName(parentId);
			List<String> l1 = dao.getCondTokenNoId(parentId);
			String regionTypeAjaxString = "<option value='0'>----Select----</option>";

			for (int i = 0; i < l1.size(); i++) {
				regionTypeAjaxString += "<option value=" + l1.get(i).toString()
						+ ">" + l2.get(i).toString() + "</option>";
			}
			System.out.println("regionTypeAjaxString" + regionTypeAjaxString);
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
		
		
	 }



