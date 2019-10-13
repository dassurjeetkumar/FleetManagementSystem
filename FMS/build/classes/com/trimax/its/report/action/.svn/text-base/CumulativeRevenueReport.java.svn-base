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

public class CumulativeRevenueReport extends ActionSupport{
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
		 private Map<Integer, String> divisionlist;
	 		private Map<Integer, String> depotlist;
	 		private String depotlist1;
	 		private String divisionlist1;
		
		public Map<Integer, String> getDivisionlist() {
				return divisionlist;
			}


			public void setDivisionlist(Map<Integer, String> divisionlist) {
				this.divisionlist = divisionlist;
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


			public String getDivisionlist1() {
				return divisionlist1;
			}


			public void setDivisionlist1(String divisionlist1) {
				this.divisionlist1 = divisionlist1;
			}


		@SuppressWarnings("finally")
		public String AjaxCumulativeRevenueReport()
		{
			
			HttpServletRequest req = ServletActionContext.getRequest();
					try {
					
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				   Session session1 = null;
					Transaction transaction  = null;
					Common common = new Common();
					String statr1= req.getParameter("startdate");
					String end1= req.getParameter("startdate");
					String date1=common.getDateFromPicker(startdate);
					String date2=common.getDateFromPicker(enddate);
					String division= req.getParameter("div");
					String depotlist1= req.getParameter("depotlist1");
					String query1="";
					if(division.equalsIgnoreCase("0") && depotlist1.equalsIgnoreCase("0")){
						query1="";
					}else if(!division.equalsIgnoreCase("0") && depotlist1.equalsIgnoreCase("0")){
						query1="and division_id='"+division+"'";
					}else if(division.equalsIgnoreCase("0") && !depotlist1.equalsIgnoreCase("0")){
						query1="and depot_id='"+depotlist1+"'";
					}else{
						query1="and depot_id='"+depotlist1+"'and division_id='"+division+"'";
					}
					String sql = "";
					 sql="select dat,sum(tckt_revenue) tckt_revenue ,sum(lugg_revenue) lugg_revenue,sum(sistyfive_pass) sistyfive_pass," +
					 		"sum(seventy_pass) seventy_pass,sum(ninghtyfive_pass) ninghtyfive_pass,sum(hundread_pass) hundread_pass," +
					 		"sum(onefourty_pass) onefourty_pass,sum(oneseventy_pass) oneseventy_pass,sum(incentive) incentive,sum(bata) bata," +
					 		"sum(misc) misc from (select dat,tckt_revenue,lugg_revenue,sistyfive_pass,seventy_pass,ninghtyfive_pass,hundread_pass," +
					 		"onefourty_pass,oneseventy_pass,incentive,bata,misc from cummulative_revenue WHERE dat between " +
					 		"'"+date1+"' and '"+date2+"' "+query1+") a group by dat order by dat" ;
						  		
					 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					 Query query = session1.createSQLQuery(sql);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					
					 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Cumulative Revenue Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";
				     regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				     
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Date (dd-mm-yyyy)</th><th>Tckt Rev</th><th>Lug Rev</th><th>Dp-65</th><th>Dp-70</th><th>Dp-95</th><th>Dp-100</th><th>Dp-140</th><th>Dp-170</th>" +
								"                          <th>Mp-1050</th><th>Mp-1500</th><th>Mp-2250</th><th>Mp-2300</th><th>Mp-3650(3350+300)</th><th>Id Card</th><th>Earn Total</th><th>Incentive</th><th>Batta</th><th>Misc</th><th>Ded Total</th><th>Net Total</th>"+"</tr></thead><tbody>";
						

					        if(aliasToValueMapList.size()==0){
					        regionTypeAjaxString +="<tr><td colspan='22'>No Records Found</td></tr>";
					        }
					        for (int i = 0; i < aliasToValueMapList.size(); i++) {
								int j=i+1;
								regionTypeAjaxString +="<tr>";
								Map<String, Object> list = aliasToValueMapList.get(i);
								
								double tcktrev = Double.parseDouble(list.get("tckt_revenue").toString());
								BigDecimal tcktRev=new BigDecimal(Double.parseDouble(list.get("tckt_revenue").toString()));
								tcktRev = tcktRev.setScale(2, BigDecimal.ROUND_HALF_UP);
							
								double luggrev = Double.parseDouble(list.get("lugg_revenue").toString());
								BigDecimal luggageRev=new BigDecimal(Double.parseDouble(list.get("lugg_revenue").toString()));
								luggageRev = luggageRev.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								double sixtyfive = Double.parseDouble(list.get("sistyfive_pass").toString());
								BigDecimal sixtyFive=new BigDecimal(Double.parseDouble(list.get("sistyfive_pass").toString()));
								sixtyFive = sixtyFive.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								double seventy = Double.parseDouble(list.get("seventy_pass").toString());
								BigDecimal seventyFive=new BigDecimal(Double.parseDouble(list.get("seventy_pass").toString()));
								seventyFive = seventyFive.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								double ninghtyfive_pass = Double.parseDouble(list.get("ninghtyfive_pass").toString());
								BigDecimal ninghtyFive=new BigDecimal(Double.parseDouble(list.get("ninghtyfive_pass").toString()));
								ninghtyFive = ninghtyFive.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								double hundread_pass = Double.parseDouble(list.get("hundread_pass").toString());
								BigDecimal hundredPass=new BigDecimal(Double.parseDouble(list.get("hundread_pass").toString()));
								hundredPass = hundredPass.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								double onefourty_pass = Double.parseDouble(list.get("onefourty_pass").toString());
								BigDecimal oneFourty=new BigDecimal(Double.parseDouble(list.get("hundread_pass").toString()));
								oneFourty = oneFourty.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								double oneseventy_pass = Double.parseDouble(list.get("oneseventy_pass").toString());
								BigDecimal oneSeventy=new BigDecimal(Double.parseDouble(list.get("oneseventy_pass").toString()));
								oneSeventy = oneSeventy.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								
								regionTypeAjaxString +="<td>"+j+"</td>";
								String d=common.getDateToPicker(list.get("dat").toString());
								regionTypeAjaxString +="<td>"+d+"</td>";
								regionTypeAjaxString +="<td>"+tcktRev+"</td>";
								regionTypeAjaxString +="<td>"+luggageRev+"</td>";
								regionTypeAjaxString +="<td>"+sixtyFive+"</td>";
								regionTypeAjaxString +="<td>"+seventyFive+"</td>";
								regionTypeAjaxString +="<td>"+ninghtyFive+"</td>";
								regionTypeAjaxString +="<td>"+hundredPass+"</td>";
								regionTypeAjaxString +="<td>"+oneFourty+"</td>";
								regionTypeAjaxString +="<td>"+oneSeventy+"</td>";
								regionTypeAjaxString +="<td>"+"0"+"</td>";
								regionTypeAjaxString +="<td>"+"0"+"</td>";
								regionTypeAjaxString +="<td>"+"0"+"</td>";
								regionTypeAjaxString +="<td>"+"0"+"</td>";
								regionTypeAjaxString +="<td>"+"0"+"</td>";
								regionTypeAjaxString +="<td>"+"0"+"</td>";
								
								earnAmmount+=tcktrev+luggrev+sixtyfive+seventy+ninghtyfive_pass+hundread_pass+onefourty_pass+oneseventy_pass;
								BigDecimal earnAmt=new BigDecimal(earnAmmount);
								earnAmt = earnAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								regionTypeAjaxString +="<td>"+earnAmt+"</td>";
								double incentive = Double.parseDouble(list.get("incentive").toString());
								BigDecimal incentiveVal=new BigDecimal(Double.parseDouble(list.get("incentive").toString()));
								incentiveVal = incentiveVal.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								double bata = Double.parseDouble(list.get("bata").toString());
								BigDecimal bataVal=new BigDecimal(Double.parseDouble(list.get("bata").toString()));
								bataVal = bataVal.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								double misc = Double.parseDouble(list.get("misc").toString());
								BigDecimal miscVal=new BigDecimal(Double.parseDouble(list.get("misc").toString()));
								miscVal = miscVal.setScale(2, BigDecimal.ROUND_HALF_UP);
								
//								incentive=Math.rint(incentive*100)/100;
//								bata=Math.rint(bata*100)/100;
//								misc=Math.rint(misc*100)/100;
								regionTypeAjaxString +="<td>"+incentiveVal+"</td>";
								regionTypeAjaxString +="<td>"+bataVal+"</td>";
								regionTypeAjaxString +="<td>"+miscVal+"</td>";
								dedtotal+=incentive+bata+misc;
								dedtotal=Math.rint(dedtotal*100)/100;
								BigDecimal dedtot=new BigDecimal(dedtotal);
								dedtot = dedtot.setScale(2, BigDecimal.ROUND_HALF_UP);
								System.out.println("ded tot "+dedtot);
								regionTypeAjaxString +="<td>"+dedtot+"</td>";
								
								totalammount=earnAmmount-dedtotal;
								BigDecimal total=new BigDecimal(totalammount);
								total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
								
//								 MathContext mc = new MathContext(12);
								
								regionTypeAjaxString +="<td>"+total+"</td>";
								regionTypeAjaxString +="</tr>";
							}
					        
						    regionTypeAjaxString += "</tbody></table></div>        </div>"; 
						    
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




	
	
	



