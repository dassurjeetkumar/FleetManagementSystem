package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class TripOperatedReport {
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		//this.setSchedulelist(getSchedulelistdata());
		return "success";
	}
	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	 public String startdate;
	    public String enddate;
	 private List<Schedule> schedulelist;
	    private String schedule;
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


	public List<Schedule> getSchedulelist() {
			return schedulelist;
		}


		public void setSchedulelist(List<Schedule> schedulelist) {
			this.schedulelist = schedulelist;
		}


	public String getSchedule() {
			return schedule;
		}


		public void setSchedule(String schedule) {
			this.schedule = schedule;
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
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	
	
	
	 public String getScheduleNoForDepotWise() {
			HttpServletRequest request = ServletActionContext.getRequest();
			try {
				int depot=Integer.parseInt(request.getParameter("val")) ;
				String regionTypeAjaxString = "";
				List<Schedule> schedulelist=getSchedulelist(depot);
				regionTypeAjaxString +="<option value=0 id='0'>Select</option>";
				for (int i = 0; i < schedulelist.size(); i++) {
					regionTypeAjaxString +="<option value=" + schedulelist.get(i).getSchedule_id() + " id='"+ schedulelist.get(i).getSchedule_id()+"'>" + schedulelist.get(i).getScheduleNumber() + "</option>";
				}
			    HttpServletResponse response = ServletActionContext.getResponse();
			    PrintWriter out;


				out = response.getWriter();
				out.print(regionTypeAjaxString);
				
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
				return null;
			}

		 }
	 
	 
	 public List<Schedule> getSchedulelist(int depot) {
			List<Schedule> schedulelist = null;
			Session session = null;
			try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
					" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and depot_id='"+depot+"'";
	         Query query = session.createSQLQuery(sql)
					.addScalar("schedule_id", Hibernate.STRING)
					.addScalar("scheduleNumber", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(Schedule.class));
			schedulelist=query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(session!=null){
					session.close();
				}
				return schedulelist;
			}
		}
	 
	 
	 public String showTripOperatedReport(){
			try {
			
			Common common = new Common();
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			Session session1 = null;
			Transaction transaction  = null;
			double totalamount=0;
			String sql="";
			
//			sql="select dte,snumber,token,ename," +
//			  		"sum(manualpassenger+manualdailypass+manualmonthlypass+manualluggage+etmpassenger+etmluggage+collection_amount)total,bno,Waybill_No from " +
//			  		"(SELECT wd.generated_Date as dte,wd.waybil_Id as id,ifnull(block.manualpassenger,0) manualpassenger," +
//			  		"ifnull(block.manualdailypass,0) manualdailypass,ifnull(block.manualmonthlypass,0) manualmonthlypass," +
//			  		"ifnull(block.manualluggage,0) manualluggage,sum(ifnull(px_total_amount,0)) etmpassenger," +
//			  		"sum(ifnull(luggage_total_amount,0)) etmluggage,s.schedule_number as snumber,e.EMPLOYEE_NAME as ename," +
//			  		"e.TOKEN as token,ttbag.bag_code as bno,wd.Waybill_No,ifnull(wpc.collection_amount,0)collection_amount FROM Waybill_Details wd LEFT JOIN " +
//			  		"(SELECT a1.waybill_cwa_block_master_id, sum(if(ticket_type_manual_id IN (1), value, 0)) AS " +
//			  		"manualpassenger,sum(if(ticket_type_manual_id=2 , value, 0)) AS manualdailypass," +
//			  		"sum(if(ticket_type_manual_id =3, value, 0)) AS manualmonthlypass," +
//			  		"sum(if(ticket_type_manual_id IN (4), value, 0)) AS manualluggage   FROM waybill_cwa_block_master a1 " +
//			  		"INNER JOIN Waybill_cwa_receipt_details a2 ON " +
//			  		"a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id GROUP BY a1.waybill_cwa_block_master_id) " +
//			  		"block ON block.waybill_cwa_block_master_id = wd.param2 " +
//			  		"INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No " +
//			  		"INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " +
//			  		"LEFT JOIN ticket t ON t.waybil_Id=wd.waybil_Id AND fare_type Not IN ('NINC')   AND ticket_printed_flag ='Y'" +
//			  		"LEFT JOIN ticket_type tt ON tt.ticket_type_id=t.ticket_type_short_code  AND " +
//			  		"tt.ticket_type_name NOT IN ('Line Checking', 'Penalty Ticket', 'Trip Status') " +
//			  		"left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id " +
//			  		"left join waybill_pending_collection wpc on wpc.waybill_no=wd.Waybill_No "+
//			  		"INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type " +
//			  		"LEFT JOIN shift_type STYPE ON STYPE.shift_type_id=wd.Shift_Type " +
//			  		"LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id " +
//			  		"WHERE wd.Ticket_Audited_Date BETWEEN '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59' "+bagCondition+" " +
//			  		"and (wd.status='AUDITED' or wd.status='CLOSED') GROUP BY s.schedule_number," +
//			  		"wd.waybill_No) a group by Waybill_No HAVING total>0 order by dte,CAST(bno as UNSIGNED)";	
			
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	transaction = session1.beginTransaction();
	//Query query = session1.createSQLQuery(sql).addScalar("dte").addScalar("snumber")
//			.addScalar("token").addScalar("ename").addScalar("total",Hibernate.DOUBLE).addScalar("bno");
	//query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	//List<Map<String, Object>> aliasToValueMapList = query.list();			
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			String count="";
			
					
		
			String filePath = "Ticketing/";

			String fileName = "cashremittancereport.txt";
			

			     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4> </br> Daywise Trip operated report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

			    


		        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
		        
		        
		        
		       
				
				regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Total Passenger</th><th>Total Revenue</th>"+"</tr></thead><tbody>";
				
				
				
//	         for (int i = 0; i < aliasToValueMapList.size(); i++) {
//					int j=i+1;
//					regionTypeAjaxString +="<tr>";
//					Map<String, Object> list = aliasToValueMapList.get(i);
//					regionTypeAjaxString +="<td>"+j+"</td>";
//					regionTypeAjaxString +="<td>"+common.getDateToPicker(list.get("dte").toString())+"</td>";
//					regionTypeAjaxString +="<td>"+list.get("bno").toString()+"</td>";
//					regionTypeAjaxString +="<td>"+list.get("snumber").toString()+"</td>";
//					regionTypeAjaxString +="<td>"+list.get("token").toString()+"</td>";
//					regionTypeAjaxString +="<td>"+list.get("ename").toString()+"</td>";
//					regionTypeAjaxString +="<td>"+list.get("total").toString()+"</td>";
//					totalamount+=Double.parseDouble(list.get("total").toString());
//					System.out.println(list.get("total").toString()+"\t"+totalamount);
//					
//					String date = list.get("dte").toString();
//					date=common.getDateToPicker(date);
//					String dno = list.get("bno").toString();
//					String schduleNo = list.get("snumber").toString();
//					String tokenNo = list.get("token").toString();
//					String name = list.get("ename").toString();
//					String total = list.get("total").toString();
//					
//					 str+=""+add(String.valueOf(j),5)+"|" + add(date,15) + "|" + add(dno,7) +"|" + add(schduleNo, 14) +"|" + add(tokenNo,12)+"|" + add(name,30)+"|" + add(total,10)+ "|"+"\n";
//					 if(j%55==0){
//						   str+=f2+add(headingprint,5);
//					   }
//					regionTypeAjaxString +="</tr>";
//				}
				
	         str+=" _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n";
			  
				    ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
					 
				    regionTypeAjaxString += "</tbody></table></div>"; 
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			
			
			
				
				FileOutputStream FOS = new FileOutputStream(path);
				PrintWriter PW = new PrintWriter(FOS);
				
	            //	System.out.println(str);
			String p=str;
			//System.out.println("string..@@"+p);

			PW.println(p);
			PW.close();
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
	

}
