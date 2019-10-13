
	package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.report.dao.ScheduleWiseOperaDataDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;
import org.hibernate.Session;
import org.json.simple.JSONObject;


public class ScheduleWiseOperationalDetails extends ActionSupport {
		
		String str="";
		String path="";
		char ft = 15;
	
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

		
	double totalAmmount=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
		public String execute() {
			System.out.println("in execute");
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
//			System.out.println("division........"+divisionlist);	
			//this.setSchedulelist(getSchedulelistdata());
			return "success";
		}
		
		public String getDepotlist1() {
			return depotlist1;
		}

		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}

		double mnthTotal=0.0;
		Double netTotal=0.0;
		
		
		public String getScheduleWiseOperationalDetailsData() throws IOException
		{
					HttpServletRequest request = ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();	
					
					try {
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
					Session session1 = null;
					JSONObject result = new JSONObject();
					String queryyy;
					Common common = new Common();
					ScheduleWiseOperaDataDao dao=new ScheduleWiseOperaDataDao();
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
						 Calendar cal = Calendar.getInstance();
						 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
						 Date startDate = format.parse(startdate);
						 Date endDateO = format.parse(enddate);
						 SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
						 String startDate1 = fomat2.format(startDate).toString();
						 String endDate1 = fomat2.format(endDateO).toString();
						int total = -1;
//						total=dao.getTotalRecords(request,startDate1,endDate1,depotlist1);
						
						SEARCH_TERM = request.getParameter("sSearch");
					
						response.setContentType("application/json");
						response.setHeader("Cache-Control", "no-store");
						PrintWriter out = response.getWriter();
						
						
						result=dao.getSchedulewiseOperData(total,request,startDate1,endDate1,depotlist1);
						out.print(result);
							
						}catch(Exception e){
							e.printStackTrace();
						}
						return null;

				}
		
			 }
	




	
	
	





	

