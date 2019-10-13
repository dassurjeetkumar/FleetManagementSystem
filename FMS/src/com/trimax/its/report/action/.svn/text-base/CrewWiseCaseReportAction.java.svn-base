package com.trimax.its.report.action;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;

public class CrewWiseCaseReportAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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




	String regionTypeAjaxString = "";

	
		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		 Session session1 = null;
		public String getCrewWiseData()
		{
			HttpServletRequest req = ServletActionContext.getRequest();
					try {
					
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				  
					Transaction transaction  = null;
					Common common = new Common();
					String depot=req.getParameter("depotId");
					String division=req.getParameter("divId");
					
					String statr1= req.getParameter("startdate");
					String end1= req.getParameter("startdate");
					String date1=common.getDateFromPicker(startdate);
					String date2=common.getDateFromPicker(enddate);
					String queryyy;
					String sql = "";
//					 sql="select dat,sum(tckt_revenue) tckt_revenue ,sum(lugg_revenue) lugg_revenue,sum(sistyfive_pass) sistyfive_pass,sum(seventy_pass) seventy_pass,sum(incentive) incentive,sum(bata) bata,sum(misc) misc" +
//						  		" from (select dat,tckt_revenue,lugg_revenue,sistyfive_pass,seventy_pass," +
//						  		" incentive,bata,misc from cummulative_revenue WHERE dat between '"+date1+"' and '"+date2+"') a group by dat order by dat" ;
						  		
					 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
//					 Query query = session1.createSQLQuery(sql);
//				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//					List<Map<String, Object>> aliasToValueMapList = query.list();
					
					 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Crew Wise Case Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";
				     regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				     
					 
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Conductor Name</th><th>Cond Token No</th><th>Total no of Cases</th>"+"</tr></thead><tbody>";
						

						    regionTypeAjaxString += "</tbody></table></div>        </div>"; 
						    
					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					
						
						

						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (Exception e) {
						
						e.printStackTrace();
						
					}finally{
						if(session1 !=null){
							session1.close();
						}
					}

					return null;
			}
		

	
		
		
		public String getCaseDetails() throws IOException{
			
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();	
			PrintWriter out = null;
			ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
			JSONObject result = new JSONObject();
				
				String formattedgivendate=request.getParameter("date").toString();
				String depotId=request.getParameter("depotId");
				String Scheduleno=request.getParameter("scheduleNo");
				Common cm = new Common();
//				String formattedgivendate = cm.getDateFromPicker(fromdate);
				try{
					// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					response.setContentType("application/json");
					out = response.getWriter();
					result = dao.getDataForScheduleTripStatusReport(1, request, "", "",
							formattedgivendate, Scheduleno,depotId);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
				return null;
			}
		
		
		
			 }





	
	
	
