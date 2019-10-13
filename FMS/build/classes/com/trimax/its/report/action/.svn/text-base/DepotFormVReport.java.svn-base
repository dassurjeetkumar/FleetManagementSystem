package com.trimax.its.report.action;

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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;


import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class DepotFormVReport extends ActionSupport{
	
	
	String path="";
	char ft = 15;
	String str="";
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
	
    public String divisonNo;
    public String depotName;
    public String selectedDate;
    public String scheduleNo;
	
	private Map<Integer, String> divisionlist;
	
	private Map<Integer,String> depotlist1;
	
	
	

	public Map<Integer, String> getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(Map<Integer, String> depotlist1) {
		this.depotlist1 = depotlist1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String execute() {
		
		 HttpServletRequest req = ServletActionContext.getRequest();
//			VtsDataDAO dao = VtsDataDAO.getInstance();
			CollectionReportDAO dao=new CollectionReportDAO();
//			int parentId = Integer.parseInt(req.getParameter("val"));
//			String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
//	        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
	      
//	      if(orgtypeid.equals("2")){
//	      	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
	      		List<String> l1 = dao.getDepotIdDC();
	      		List<String> l2 = dao.getDepotNameDC();
	      		String regionTypeAjaxString = "";
	      		//regionTypeAjaxString += "<option value='0'>------select------</option>";
	      		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	      		for (int i = 0; i < l1.size(); i++) {
	      			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
	      					+ ">" + l2.get(i).toString() + "</option>";
	      			resultMap.put(Integer.parseInt(l1.get(i)), l2.get(i).toString());
	      		}
	      		HttpServletResponse response = ServletActionContext.getResponse();
	      		PrintWriter out;
	      		try {
	      			depotlist1=resultMap;
//	      			out = response.getWriter();
//	      			out.print(regionTypeAjaxString);
	      		} catch (Exception e) {
	      			// TODO Auto-generated catch block
	      			e.printStackTrace();
	      		}
		  
		
		
		
		return "success";
	}
	
	public String getCityDayoutData(){
		
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
		String depot="";
				//dao.getDepotName();
		String depot_name=getDepotNames(depotName);
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select s.schedule_number,s.schedule_id,ff.form_four_id,ff.schedule_number_name,sl.service_limit,a.dista,a.trips,b.distashift2,b.tripsshift2,c.distadeadshift1,c.tripsdeadshift1,"+ 
				 " d.distadeadshift2,d.tripsdeadshift2,(dista+distashift2) tot,(trips+tripsshift2)tottrips,(distadeadshift1+distadeadshift2) totdead, "+
				 " ff.steering1,ff.steering2,ff.ot1,ff.ot2,ifnull(sf.start_point,'0') startpoint,ifnull(sg.end_point,'0') endpoint,if(ifnull(count,0)=0,'N','Y') chr,"+
				 " ifnull(screw.crewchangelocation,'') crewchangelocation,ifnull(st.start_time,'') start_time,ifnull(et.end_time,'') end_time "+
				 " from schedule s inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				 " inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
				 " inner join (SELECT ifnull(sum(distance),'0.0')  as dista,max(trip_number) trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 " on ff1.form_four_id=schedule_details.form_four_id "+
				 " WHERE trip_type='2' and shift_type_id='4' "+
				 " group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+
				 " inner join (SELECT ifnull(sum(distance),'0.0')  as distashift2,max(trip_number) tripsshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id "+
				" WHERE trip_type='2' and shift_type_id='5' "+
				" group by ff1.form_four_id) b on b.form_four_id=ff.form_four_id "+
				" inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id "+
				" WHERE trip_type='3' and shift_type_id='4' "+
				" group by ff1.form_four_id) c on c.form_four_id=ff.form_four_id "+
				" inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift2,max(trip_number) tripsdeadshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id "+
				" WHERE trip_type='3' and shift_type_id='5' "+
				" group by ff1.form_four_id) d on d.form_four_id=ff.form_four_id "+
				" left join (select start_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) sf on sf.form_four_id=ff.form_four_id "+
				" left join (select end_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) sg on sg.form_four_id=ff.form_four_id "+
				" left join (select concat(schedule_details.start_time,'-',schedule_details.crew_change_location) crewchangelocation,ff1.form_four_id from  schedule_details  left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where crew_change='1' group by schedule_details.form_four_id) screw on screw.form_four_id=ff.form_four_id "+
				" left join (select count(*) count,ff1.form_four_id from schedule_details left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
				" where trip_type in ('19','20') group by ff1.form_four_id) pp "+
				" on pp.form_four_id=ff.form_four_id "+
				" left join (select schedule_details.start_time start_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) st on st.form_four_id=ff.form_four_id "+
				" left join (select schedule_details.end_time end_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) et on et.form_four_id=ff.form_four_id "+
				" where sl.service_limit='city'  and ff.current_status ='ACTIVE' and ff.deleted_status='0' and depot_id="+depotName+" group by ff.form_four_id";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("schedule_number").addScalar("schedule_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("service_limit").addScalar("dista",Hibernate.STRING).addScalar("trips").addScalar("distashift2",Hibernate.STRING).addScalar("tripsshift2").addScalar("distadeadshift1",Hibernate.STRING).addScalar("tripsdeadshift1").addScalar("distadeadshift2",Hibernate.STRING).addScalar("tripsdeadshift2").addScalar("tot").addScalar("tottrips").addScalar("totdead").addScalar("steering1").addScalar("steering2").addScalar("ot1",Hibernate.STRING).addScalar("ot2",Hibernate.STRING).addScalar("startpoint")
				       .addScalar("endpoint").addScalar("chr").addScalar("crewchangelocation").addScalar("start_time").addScalar("end_time") ;
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totaldista=0.0;
			double Totaldista_shift2=0.0;
		    double Total_kms=0.0;
		    double Total_dead1=0.0;
		    double Total_dead2=0.0;
		    double Total_trip1=0.0;
		    double Total_trip2=0.0;
		    double Total_trip=0.0;
		    double Toatal_dead_trip1=0.0;
		    double Total_dead_trip2=0.0;
		    double Total_streering_hrs1=0.0;
		    double Total_streering_min1=0.0;
		    double Total_streering_hrs2=0.0;
		    double Total_streering_min2=0.0;
		    double Total_hrs=0.0;
		    double Total_mins=0.0;
		    double Total_ot1=0.0;
		    double Total_ot2=0.0;
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			String filePath = "Ticketing/";
			String fileName = "DetailsOfServiceTaxAmount.txt";
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+"</br>Depot Form-V</br>"+depot_name+"</br>";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th>S.No</th><th>Sch No</th><th colspan='3' align='center'>KMS</th><th colspan='2' align='center'>Dead KMS</th><th colspan='3' align='center'>No Of TRIPS</th><th colspan='2' align='center'>Dead TRIPS</th><th colspan='6' align='center'>Steering Hours</th><th></th><th></th><th></th><th></th><th></th><th></th></tr>" +
					      "<tr><th></th><th></th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift Hrs</th><th>I Shift Mins</th><th>II Shift Hrs</th><th>II Shift Mins</th><th>Total Hours</th><th>Total Mins</th><th>OT1</th><th>OT2</th><th>Departure Time</th><th>D/O Crew change Time </th><th>Arrival Time</th><th>Chartered  Schedule </th> "+
					"</tr>";
//			regionTypeAjaxString += "<div class='component'><lable>CITY DAY OUT</lable><table class='overflow-y' border='1'>";
//			regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Sch No</th><th colspan='3' align='center'>KMS</th><th colspan='2' align='center'>Dead KMS</th><th colspan='3' align='center'>No Of TRIPS</th><th colspan='2' align='center'>Dead TRIPS</th><th colspan='13' align='center'>Steering Hours</th></tr>" +
//					      "<tr><th></th><th></th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift Hrs</th><th>I Shift Mins</th><th>II Shift Hrs</th><th>II Shift Mins</th><th>Total Hours</th><th>Total Mins</th><th>OT In Mins</th><th>Departure Time</th><th>D/O Crew change Time </th><th>Night Halt Time</th><th>N/O Morning Oute </th><th>In Time</th><th>Chartered  Schedule </th> "+
//					"</tr></thead><tbody>";
			 
			
	        
	        if(aliasToValueMapList.size()==0){
	        	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	             regionTypeAjaxString += "<tr>";
	             regionTypeAjaxString += "<td colspan='24' align='center'><b>No Result Found</b></td>";
	            
	             regionTypeAjaxString += "</tr>";
	        	
	        }else{
		
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("schedule_number").toString()+"</td>";
			System.out.println("list.get(dista).toString()==="+list.get("dista"));
			regionTypeAjaxString +="<td align='right'>"+ list.get("dista").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("distashift2").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tot").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("trips").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsshift2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tottrips").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift2").toString()+"</td>";
			String steering1=list.get("steering1").toString();
			String steering[]=steering1.split(":");
			String steering_hrs=steering[0];
			String streering_mins=steering[1];
			System.out.println("steering_hrs1==="+steering_hrs+" streering_mins1=="+streering_mins);
			
			regionTypeAjaxString +="<td align='right'>"+steering_hrs+"</td>";
			regionTypeAjaxString +="<td align='right'>"+streering_mins+"</td>";
			String steering2=list.get("steering2").toString();
			String steerings[]=steering2.split(":");
			String steering_hrs1=steerings[0];
			String streering_mins1=steerings[1];
			System.out.println("steering_hrs2==="+steering_hrs1+" streering_mins2=="+streering_mins1);

			regionTypeAjaxString +="<td align='right'>"+steering_hrs1+"</td>";
			regionTypeAjaxString +="<td align='right'>"+streering_mins1+"</td>";
			int total_hrs=Integer.parseInt(steering_hrs)+Integer.parseInt(steering_hrs1);
			int total_min=Integer.parseInt(streering_mins)+Integer.parseInt(streering_mins1);
			regionTypeAjaxString +="<td align='right'>"+total_hrs+"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_min+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("start_time").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("crewchangelocation").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("end_time").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("chr").toString()+"</td>";
			
//			double dista=Double.parseDouble(list.get("dista").toString());
			
			Totaldista+=Double.parseDouble(list.get("dista").toString());;
			Totaldista_shift2+=Double.parseDouble(list.get("distashift2").toString());
			Total_kms+=Double.parseDouble(list.get("tot").toString());
			Total_dead1+=Double.parseDouble(list.get("distadeadshift1").toString());
			Total_dead2+=Double.parseDouble(list.get("distadeadshift2").toString());
			Total_trip1+=Double.parseDouble(list.get("trips").toString());
			Total_trip2+=Double.parseDouble(list.get("tripsshift2").toString());
			Total_trip+=Double.parseDouble(list.get("tottrips").toString());
			Toatal_dead_trip1+=Double.parseDouble(list.get("tripsdeadshift1").toString());
			Total_dead_trip2+=Double.parseDouble(list.get("tripsdeadshift2").toString());
			Total_streering_hrs1+=Double.parseDouble(steering_hrs);
			Total_streering_min1+=Double.parseDouble(streering_mins);
			Total_streering_hrs2+=Double.parseDouble(steering_hrs1);
			Total_streering_min2+=Double.parseDouble(streering_mins1);
			Total_hrs+=total_hrs;
			Total_mins+=total_min;
//			Total_ot1+=Double.parseDouble(list.get("ot1").toString());
//			Total_ot2+=Double.parseDouble(list.get("ot2").toString());
			
//			Totaletimservicetaxamount+=Double.parseDouble(list.get("ETIMServiceTax").toString());
//			Totalbagservicetaxamount+=Double.parseDouble(list.get("BagServiceTax").toString());
//			Totalpasssservicetaxamount+=Double.parseDouble(list.get("PassServiceTax").toString());
//			Granttotalservicetaxamount+=Totalvalues;
			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
			
			

			
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ Totaldista+"</td>"+"<td align='right'><b>"+Totaldista_shift2+"</td><td align='right'><b>"+Total_kms+"</td>" +
						"<td align='right'><b>"+ Total_dead1+"</td><td align='right'><b>"+ Total_dead2+"</td><td align='right'><b>"+ Total_trip1+"</td><td align='right'><b>"+ Total_trip2+"</td><td align='right'><b>"+Total_trip+"</td><td align='right'><b>"+Toatal_dead_trip1+"</td>" +
								"<td align='right'><b>"+Total_dead_trip2+"</td><td align='right'><b>"+Total_streering_hrs1+"</td><td align='right'><b>"+Total_streering_min1+"</td><td align='right'><b>"+Total_streering_hrs2+"</td><td align='right'><b>"+Total_streering_min2+"</td><td align='right'><b>"+Total_hrs+"</td>" +
										"<td align='right'><b>"+Total_mins+"</td></tr>" +"\n";  
//				
				 regionTypeAjaxString += "</table></div> </div>  ";
				 
	        }
		 
				 
		 
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
public String getCityNightoutData(){
		
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
		
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
		String depot="";
//		dao.getDepotName();
		String depot_name=getDepotNames(depotName);
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select s.schedule_number,s.schedule_id,ff.form_four_id,ff.schedule_number_name,sl.service_limit,a.dista,a.trips,b.distashift2,b.tripsshift2,c.distadeadshift1,c.tripsdeadshift1,"+ 
				 " d.distadeadshift2,d.tripsdeadshift2,(dista+distashift2) tot,(trips+tripsshift2)tottrips,(distadeadshift1+distadeadshift2) totdead, "+
				 " ff.steering1,ff.steering2,ff.ot1,ff.ot2,ifnull(sf.start_point,'0') startpoint,ifnull(sg.end_point,'0') endpoint,if(ifnull(count,0)=0,'N','Y') chr,"+
				 " ifnull(screw.crewchangelocation,'') crewchangelocation,ifnull(st.start_time,'') start_time,ifnull(et.end_time,'') end_time "+
				 " from schedule s inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				 " inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
				 " inner join (SELECT ifnull(sum(distance),'0.0')  as dista,max(trip_number) trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 " on ff1.form_four_id=schedule_details.form_four_id "+
				 " WHERE trip_type='2' and shift_type_id='2' "+
				 " group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+
				 " inner join (SELECT ifnull(sum(distance),'0.0')  as distashift2,max(trip_number) tripsshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id "+
				" WHERE trip_type='2' and shift_type_id='3' "+
				" group by ff1.form_four_id) b on b.form_four_id=ff.form_four_id "+
				" inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id "+
				" WHERE trip_type='3' and shift_type_id='2' "+
				" group by ff1.form_four_id) c on c.form_four_id=ff.form_four_id "+
				" inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift2,max(trip_number) tripsdeadshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id "+
				" WHERE trip_type='3' and shift_type_id='3' "+
				" group by ff1.form_four_id) d on d.form_four_id=ff.form_four_id "+
				" left join (select start_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) sf on sf.form_four_id=ff.form_four_id "+
				" left join (select end_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) sg on sg.form_four_id=ff.form_four_id "+
				" left join (select concat(schedule_details.start_time,'-',schedule_details.crew_change_location) crewchangelocation,ff1.form_four_id from  schedule_details  left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where crew_change='1' group by schedule_details.form_four_id) screw on screw.form_four_id=ff.form_four_id "+
				" left join (select count(*) count,ff1.form_four_id from schedule_details left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
				" where trip_type in ('19','20') group by ff1.form_four_id) pp "+
				" on pp.form_four_id=ff.form_four_id "+
				" left join (select schedule_details.start_time start_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) st on st.form_four_id=ff.form_four_id "+
				" left join (select schedule_details.end_time end_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) et on et.form_four_id=ff.form_four_id "+
				" where sl.service_limit='city'  and ff.current_status ='ACTIVE' and ff.deleted_status='0' and depot_id="+depotName+" group by ff.form_four_id";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("schedule_number").addScalar("schedule_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("service_limit").addScalar("dista",Hibernate.STRING).addScalar("trips").addScalar("distashift2",Hibernate.STRING).addScalar("tripsshift2").addScalar("distadeadshift1",Hibernate.STRING).addScalar("tripsdeadshift1").addScalar("distadeadshift2",Hibernate.STRING).addScalar("tripsdeadshift2").addScalar("tot").addScalar("tottrips").addScalar("totdead").addScalar("steering1").addScalar("steering2").addScalar("ot1",Hibernate.STRING).addScalar("ot2",Hibernate.STRING).addScalar("startpoint")
				       .addScalar("endpoint").addScalar("chr").addScalar("crewchangelocation").addScalar("start_time").addScalar("end_time") ;
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totaldista=0.0;
			double Totaldista_shift2=0.0;
		    double Total_kms=0.0;
		    double Total_dead1=0.0;
		    double Total_dead2=0.0;
		    double Total_trip1=0.0;
		    double Total_trip2=0.0;
		    double Total_trip=0.0;
		    double Toatal_dead_trip1=0.0;
		    double Total_dead_trip2=0.0;
		    double Total_streering_hrs1=0.0;
		    double Total_streering_min1=0.0;
		    double Total_streering_hrs2=0.0;
		    double Total_streering_min2=0.0;
		    double Total_hrs=0.0;
		    double Total_mins=0.0;
			
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot_name+"</br>CITY NIGHT OUT";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        
	      
	       
       
			 regionTypeAjaxString += "<tr><th>S.No</th><th>Sch No</th><th colspan='3' align='center'>KMS</th><th colspan='2' align='center'>Dead KMS</th><th colspan='3' align='center'>No Of TRIPS</th><th colspan='2' align='center'>Dead TRIPS</th><th colspan='6' align='center'>Steering Hours</th><th></th><th></th><th></th><th></th><th></th><th></th></tr>" +
				      "<tr><th></th><th></th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift Hrs</th><th>I Shift Mins</th><th>II Shift Hrs</th><th>II Shift Mins</th><th>Total Hours</th><th>Total Mins</th><th>OT1</th><th>OT2</th><th>Departure Time</th><th>D/O Crew change Time </th><th>Arrival Time</th><th>Chartered  Schedule </th> "+
				"</tr>";
	        
              
			 if(aliasToValueMapList.size()==0){
	        	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	             regionTypeAjaxString += "<tr>";
	             regionTypeAjaxString += "<td colspan='24' align='center'><b>No Result Found</b></td>";
	            
	             regionTypeAjaxString += "</tr>";
	        	
	        }else{
                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("schedule_number").toString()+"</td>";
			System.out.println("list.get(dista).toString()==="+list.get("dista"));
			regionTypeAjaxString +="<td align='right'>"+ list.get("dista").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("distashift2").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tot").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("trips").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsshift2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tottrips").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift2").toString()+"</td>";
			String steering1=list.get("steering1").toString();
			String steering[]=steering1.split(":");
			String steering_hrs=steering[0];
			String streering_mins=steering[1];
//			System.out.println("steering_hrs1==="+steering_hrs+" streering_mins1=="+streering_mins);
			
			regionTypeAjaxString +="<td align='right'>"+steering_hrs+"</td>";
			regionTypeAjaxString +="<td align='right'>"+streering_mins+"</td>";
			String steering2=list.get("steering2").toString();
			String steerings[]=steering2.split(":");
			String steering_hrs1=steerings[0];
			String streering_mins1=steerings[1];
//			System.out.println("steering_hrs2==="+steering_hrs1+" streering_mins2=="+streering_mins1);

			regionTypeAjaxString +="<td align='right'>"+steering_hrs1+"</td>";
			regionTypeAjaxString +="<td align='right'>"+streering_mins1+"</td>";
			int total_hrs=Integer.parseInt(steering_hrs)+Integer.parseInt(steering_hrs1);
			int total_min=Integer.parseInt(streering_mins)+Integer.parseInt(streering_mins1);
			regionTypeAjaxString +="<td align='right'>"+total_hrs+"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_min+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("start_time").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("crewchangelocation").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("end_time").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("chr").toString()+"</td>";
			
//			double dista=Double.parseDouble(list.get("dista").toString());
			
			Totaldista+=Double.parseDouble(list.get("dista").toString());;
			Totaldista_shift2+=Double.parseDouble(list.get("distashift2").toString());
			Total_kms+=Double.parseDouble(list.get("tot").toString());
			Total_dead1+=Double.parseDouble(list.get("distadeadshift1").toString());
			Total_dead2+=Double.parseDouble(list.get("distadeadshift2").toString());
			Total_trip1+=Double.parseDouble(list.get("trips").toString());
			Total_trip2+=Double.parseDouble(list.get("tripsshift2").toString());
			Total_trip+=Double.parseDouble(list.get("tottrips").toString());
			Toatal_dead_trip1+=Double.parseDouble(list.get("tripsdeadshift1").toString());
			Total_dead_trip2+=Double.parseDouble(list.get("tripsdeadshift2").toString());
			Total_streering_hrs1+=Double.parseDouble(steering_hrs);
			Total_streering_min1+=Double.parseDouble(streering_mins);
			Total_streering_hrs2+=Double.parseDouble(steering_hrs1);
			Total_streering_min2+=Double.parseDouble(streering_mins1);
			Total_hrs+=total_hrs;
			Total_mins+=total_min;
			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
			
			
			
			   
			
			   regionTypeAjaxString +="</tr>";
		}
		     regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ Totaldista+"</td>"+"<td align='right'><b>"+Totaldista_shift2+"</td><td align='right'><b>"+Total_kms+"</td>" +
						"<td align='right'><b>"+ Total_dead1+"</td><td align='right'><b>"+ Total_dead2+"</td><td align='right'><b>"+ Total_trip1+"</td><td align='right'><b>"+ Total_trip2+"</td><td align='right'><b>"+Total_trip+"</td><td align='right'><b>"+Toatal_dead_trip1+"</td>" +
								"<td align='right'><b>"+Total_dead_trip2+"</td><td align='right'><b>"+Total_streering_hrs1+"</td><td align='right'><b>"+Total_streering_min1+"</td><td align='right'><b>"+Total_streering_hrs2+"</td><td align='right'><b>"+Total_streering_min2+"</td><td align='right'><b>"+Total_hrs+"</td>" +
										"<td align='right'><b>"+Total_mins+"</td></tr>" +"\n";  
//				
				 regionTypeAjaxString += "</table></div> </div>  ";
		 
				 
	        }
		 

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
public String getCityGeneralShiftData(){
	
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
	
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
	String depot="";
//			dao.getDepotName();
	String depot_name=getDepotNames(depotName);
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="select s.schedule_number,s.schedule_id,ff.form_four_id,ff.schedule_number_name,sl.service_limit,a.dista,a.trips,c.distadeadshift1,c.tripsdeadshift1,"+ 
			 " (dista) tot,(trips)tottrips,(distadeadshift1) totdead, "+
			"  ff.steering1,ff.ot1,ff.ot2,ifnull(sf.start_point,'0') startpoint,ifnull(sg.end_point,'0') endpoint,if(ifnull(count,0)=0,'N','Y') chr, "+
			 " ifnull(screw.crewchangelocation,'') crewchangelocation,ifnull(st.start_time,'') start_time,ifnull(et.end_time,'') end_time "+
			 " from schedule s inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
			 " inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
			 " inner join (SELECT ifnull(sum(distance),'0.0')  as dista,max(trip_number) trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			 " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' and shift_type_id='1' "+
			 " group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+
			 " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			 " on ff1.form_four_id=schedule_details.form_four_id "+
			 " WHERE trip_type='3' and shift_type_id='1' group by ff1.form_four_id) c on c.form_four_id=ff.form_four_id "+
			 " left join (select start_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) sf on sf.form_four_id=ff.form_four_id "+
			" left join (select end_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) sg on sg.form_four_id=ff.form_four_id "+
			" left join (select concat(schedule_details.start_time,'-',schedule_details.crew_change_location) crewchangelocation,ff1.form_four_id from  schedule_details  left join form_four ff1 "+
		    " on ff1.form_four_id=schedule_details.form_four_id where crew_change='1' group by schedule_details.form_four_id) screw on screw.form_four_id=ff.form_four_id "+
		    " left join (select count(*) count,ff1.form_four_id from schedule_details left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
		    " where trip_type in ('19','20') group by ff1.form_four_id) pp on pp.form_four_id=ff.form_four_id "+
		    " left join (select schedule_details.start_time start_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
		    " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) st on st.form_four_id=ff.form_four_id "+
		    " left join (select schedule_details.end_time end_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
		    " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) et on et.form_four_id=ff.form_four_id "+
		    " where sl.service_limit='city'  "+
		    " and ff.current_status ='ACTIVE' and ff.deleted_status='0' and depot_id="+depotName+" group by ff.form_four_id";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("schedule_number").addScalar("schedule_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("service_limit").addScalar("dista",Hibernate.STRING).addScalar("trips").addScalar("distadeadshift1",Hibernate.STRING).addScalar("tripsdeadshift1").addScalar("tot").addScalar("tottrips").addScalar("totdead").addScalar("steering1").addScalar("ot1",Hibernate.STRING).addScalar("ot2",Hibernate.STRING).addScalar("startpoint")
		       .addScalar("endpoint").addScalar("chr").addScalar("crewchangelocation").addScalar("start_time").addScalar("end_time") ;
	  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
		double Totaldista=0.0;
	    double Total_dead1=0.0;
	    double Total_trip1=0.0;
	    double Toatal_dead_trip1=0.0;
	    double Total_streering_hrs1=0.0;
	    double Total_streering_min1=0.0;
		
		
		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot_name+"</br>GENERAL SHIFT";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

       

        regionTypeAjaxString +="<tr><th>S.No</th><th>Sch No</th><th>KMS</th><th>Dead KMS</th><th>No Of TRIPS</th><th>Dead TRIPS</th><th>Steering Hours</th><th>Steering Mins</th><th>OT1</th><th>OT2</th><th>Departure Time</th><th>Arrival Time</th><th>Chartered  Schedule </th></tr>" +
				"</tr>";
        


		 
        if(aliasToValueMapList.size()==0){
       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
            regionTypeAjaxString += "<tr>";
            regionTypeAjaxString += "<td colspan='13' align='center'><b>No Result Found</b></td>";
           
            regionTypeAjaxString += "</tr>";
       	
       }else{

		  
	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
		
 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("schedule_number").toString()+"</td>";
		
		regionTypeAjaxString +="<td align='right'>"+ list.get("dista").toString() +"</td>";
		
		
		regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift1").toString()+"</td>";
		
		regionTypeAjaxString +="<td align='right'>"+list.get("trips").toString()+"</td>";
		
		
		regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift1").toString()+"</td>";
		
		String steering1=list.get("steering1").toString();
		String steering[]=steering1.split(":");
		String steering_hrs=steering[0];
		String streering_mins=steering[1];
		System.out.println("steering_hrs1==="+steering_hrs+" streering_mins1=="+streering_mins);
		
		regionTypeAjaxString +="<td align='right'>"+steering_hrs+"</td>";
		regionTypeAjaxString +="<td align='right'>"+streering_mins+"</td>";
		
		
		
		regionTypeAjaxString +="<td align='right'>"+list.get("ot1").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("ot2").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("start_time").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("crewchangelocation").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("end_time").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("chr").toString()+"</td>";
		
//		double dista=Double.parseDouble(list.get("dista").toString());
		
		Totaldista+=Double.parseDouble(list.get("dista").toString());;
//		Totaldista_shift2+=Double.parseDouble(list.get("distashift2").toString());
//		Total_kms+=Double.parseDouble(list.get("tot").toString());
		Total_dead1+=Double.parseDouble(list.get("distadeadshift1").toString());
//		Total_dead2+=Double.parseDouble(list.get("distadeadshift2").toString());
		Total_trip1+=Double.parseDouble(list.get("trips").toString());
//		Total_trip2+=Double.parseDouble(list.get("tripsshift2").toString());
//		Total_trip+=Double.parseDouble(list.get("tottrips").toString());
		Toatal_dead_trip1+=Double.parseDouble(list.get("tripsdeadshift1").toString());
//		Total_dead_trip2+=Double.parseDouble(list.get("tripsdeadshift2").toString());
		Total_streering_hrs1+=Double.parseDouble(steering_hrs);
		Total_streering_min1+=Double.parseDouble(streering_mins);
//		Total_streering_hrs2+=Double.parseDouble(steering_hrs1);
//		Total_streering_min2+=Double.parseDouble(streering_mins1);
//		Total_hrs+=total_hrs;
//		Total_mins+=total_min;
		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
		
		

		
		   regionTypeAjaxString +="</tr>";
	}
	     
	     regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ Totaldista+"</td>" +
					"<td align='right'><b>"+ Total_dead1+"</td><td align='right'><b>"+ Total_trip1+"</td><td align='right'><b>"+Toatal_dead_trip1+"</td>" +
							"<td align='right'><b>"+Total_streering_hrs1+"</td><td align='right'><b>"+Total_streering_min1+"</td>" +
									"</tr>" +"\n";  

			 regionTypeAjaxString += "</table></div> </div>  ";
	 
       }
			 
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	

		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return null;
}
public String getCityNightServiceData(){
	
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
	
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	String depot_name=getDepotNames(depotName);
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="select s.schedule_number,s.schedule_id,ff.form_four_id,ff.schedule_number_name,sl.service_limit,a.dista,a.trips,b.distashift2,b.tripsshift2,c.distadeadshift1,c.tripsdeadshift1,"+ 
			 " d.distadeadshift2,d.tripsdeadshift2,(dista+distashift2) tot,(trips+tripsshift2)tottrips,(distadeadshift1+distadeadshift2) totdead,"+
			 " ff.steering1,ff.steering2,ff.ot1,ff.ot2,ifnull(sf.start_point,'0') startpoint,ifnull(sg.end_point,'0') endpoint,if(ifnull(count,0)=0,'N','Y') chr, "+
			 " ifnull(screw.crewchangelocation,'') crewchangelocation,ifnull(st.start_time,'') start_time,ifnull(et.end_time,'') end_time,night_halt_location,trip_number "+
			  " from schedule s inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
			 "  inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
			 "  inner join (SELECT ifnull(sum(distance),'0.0')  as dista,max(trip_number) trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			  " on ff1.form_four_id=schedule_details.form_four_id "+
			 "  WHERE trip_type='2' and shift_type_id='2' "+
			  " group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+
			  " left join (SELECT form_four_id,night_halt_location FROM schedule_details where  night_halt='1') s "+ 
			  " on s.form_four_id=ff.form_four_id    " +
			  "left join (SELECT form_four_id,trip_number FROM schedule_details where  break_type_id='9') s1 "+
               " on s1.form_four_id=ff.form_four_id"+
			 "  inner join (SELECT ifnull(sum(distance),'0.0')  as distashift2,max(trip_number) tripsshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			 "  on ff1.form_four_id=schedule_details.form_four_id "+
			  " WHERE trip_type='2' and shift_type_id='3' "+
			  " group by ff1.form_four_id) b on b.form_four_id=ff.form_four_id "+
			  " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			 "  on ff1.form_four_id=schedule_details.form_four_id "+
			 "  WHERE trip_type='3' and shift_type_id='2' "+
			  " group by ff1.form_four_id) c on c.form_four_id=ff.form_four_id "+
			  " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift2,max(trip_number) tripsdeadshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			  " on ff1.form_four_id=schedule_details.form_four_id "+
			  " WHERE trip_type='3' and shift_type_id='3' "+
			  " group by ff1.form_four_id) d on d.form_four_id=ff.form_four_id "+
			  " left join (select start_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
			  " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) sf on sf.form_four_id=ff.form_four_id "+
			  " left join (select end_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
			  " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) sg on sg.form_four_id=ff.form_four_id "+
			  " left join (select concat(schedule_details.start_time,'-',schedule_details.crew_change_location) crewchangelocation,ff1.form_four_id from  schedule_details  left join form_four ff1 "+
			  " on ff1.form_four_id=schedule_details.form_four_id where crew_change='1' group by schedule_details.form_four_id) screw on screw.form_four_id=ff.form_four_id "+
			  " left join (select count(*) count,ff1.form_four_id from schedule_details left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
			  " where trip_type in ('19','20') group by ff1.form_four_id) pp "+
			  " on pp.form_four_id=ff.form_four_id "+
			  " left join (select schedule_details.start_time start_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
			  " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) st on st.form_four_id=ff.form_four_id "+
			  " left join (select schedule_details.end_time end_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
			 "  on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) et on et.form_four_id=ff.form_four_id "+
			  " where sl.service_limit='city'  "+
			  " and ff.current_status ='ACTIVE' and ff.deleted_status='0' and depot_id="+depotName+" group by ff.form_four_id";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("schedule_number").addScalar("schedule_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("service_limit").addScalar("dista",Hibernate.STRING).addScalar("trips").addScalar("distadeadshift1",Hibernate.STRING).addScalar("tripsdeadshift1").addScalar("tot").addScalar("tottrips").addScalar("totdead").addScalar("steering1").addScalar("ot1",Hibernate.STRING).addScalar("ot2",Hibernate.STRING).addScalar("startpoint")
		       .addScalar("endpoint").addScalar("chr").addScalar("crewchangelocation").addScalar("start_time").addScalar("end_time").addScalar("night_halt_location").addScalar("trip_number") ;  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
		double Totaldista=0.0;
	    double Total_dead1=0.0;
	    double Total_trip1=0.0;
	    double Toatal_dead_trip1=0.0;
	    double Total_streering_hrs1=0.0;
	    double Total_streering_min1=0.0;
		
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        
      
       

//        regionTypeAjaxString += "<tr><th>Denom</th><th>No. of Tickets</th><th>Value</th></tr>";
        
        regionTypeAjaxString +="<tr><th>S.No</th><th>Sch No</th><th>KMS</th><th>Dead KMS</th><th>No Of TRIPS</th><th>Dead TRIPS</th><th>Steering Hours</th><th>Steering Mins</th><th>OT1</th><th>OT2</th><th>Departure Time</th><th>Halt Time & Place</th><th>Morning Out Time</th><th>Arrival  Time</th></tr>";
        

		 
        if(aliasToValueMapList.size()==0){
       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
            regionTypeAjaxString += "<tr>";
            regionTypeAjaxString += "<td colspan='14' align='center'><b>No Result Found</b></td>";
           
            regionTypeAjaxString += "</tr>";
       	
       }else{

	
              
	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("schedule_number").toString()+"</td>";
		
		regionTypeAjaxString +="<td align='right'>"+ list.get("dista").toString() +"</td>";
		
		
		regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift1").toString()+"</td>";
		
		regionTypeAjaxString +="<td align='right'>"+list.get("trips").toString()+"</td>";
		
		
		regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift1").toString()+"</td>";
		
		String steering1=list.get("steering1").toString();
		String steering[]=steering1.split(":");
		String steering_hrs=steering[0];
		String streering_mins=steering[1];
		System.out.println("steering_hrs1==="+steering_hrs+" streering_mins1=="+streering_mins);
		
		regionTypeAjaxString +="<td align='right'>"+steering_hrs+"</td>";
		regionTypeAjaxString +="<td align='right'>"+streering_mins+"</td>";
		
		
		
		regionTypeAjaxString +="<td align='right'>"+list.get("ot1").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("ot2").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("start_time").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("night_halt_location").toString()+"</td>";
		String start_time="";
		if(list.get("trip_number")==null || list.get("trip_number")==""){
			start_time=" ";
		}else{
		int trip_number=Integer.parseInt(list.get("trip_number").toString());
		String form_four_id=list.get("form_four_id").toString();
		 start_time=getMorningStartTime(trip_number,form_four_id);
		}
		regionTypeAjaxString +="<td align='right'>"+start_time+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("end_time").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("chr").toString()+"</td>";
		
		
		Totaldista+=Double.parseDouble(list.get("dista").toString());;

		Total_dead1+=Double.parseDouble(list.get("distadeadshift1").toString());
		Total_trip1+=Double.parseDouble(list.get("trips").toString());
		Toatal_dead_trip1+=Double.parseDouble(list.get("tripsdeadshift1").toString());
		Total_streering_hrs1+=Double.parseDouble(steering_hrs);
		Total_streering_min1+=Double.parseDouble(streering_mins);

		
		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
		
		

		
		   regionTypeAjaxString +="</tr>";
	}
	     regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ Totaldista+"</td>" +
					"<td align='right'><b>"+ Total_dead1+"</td><td align='right'><b>"+ Total_trip1+"</td><td align='right'><b>"+Toatal_dead_trip1+"</td>" +
							"<td align='right'><b>"+Total_streering_hrs1+"</td><td align='right'><b>"+Total_streering_min1+"</td>" +
									"</tr>" +"\n";  			
			 regionTypeAjaxString += "</table></div> </div>  ";
	 
			 
       }
	 
	 
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return null;
}

public String getCitySubDayoutData(){
	
	
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
	String depot="";
			//dao.getDepotName();
	String depot_name=getDepotNames(depotName);
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="select s.schedule_number,s.schedule_id,ff.form_four_id,ff.schedule_number_name,sl.service_limit,a.dista,a.trips,b.distashift2,b.tripsshift2,c.distadeadshift1,c.tripsdeadshift1,"+ 
			 " d.distadeadshift2,d.tripsdeadshift2,(dista+distashift2) tot,(trips+tripsshift2)tottrips,(distadeadshift1+distadeadshift2) totdead, "+
			 " ff.steering1,ff.steering2,ff.ot1,ff.ot2,ifnull(sf.start_point,'0') startpoint,ifnull(sg.end_point,'0') endpoint,if(ifnull(count,0)=0,'N','Y') chr,"+
			 " ifnull(screw.crewchangelocation,'') crewchangelocation,ifnull(st.start_time,'') start_time,ifnull(et.end_time,'') end_time "+
			 " from schedule s inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
			 " inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
			 " inner join (SELECT ifnull(sum(distance),'0.0')  as dista,max(trip_number) trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			 " on ff1.form_four_id=schedule_details.form_four_id "+
			 " WHERE trip_type='2' and shift_type_id='4' "+
			 " group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+
			 " inner join (SELECT ifnull(sum(distance),'0.0')  as distashift2,max(trip_number) tripsshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id "+
			" WHERE trip_type='2' and shift_type_id='5' "+
			" group by ff1.form_four_id) b on b.form_four_id=ff.form_four_id "+
			" inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id "+
			" WHERE trip_type='3' and shift_type_id='4' "+
			" group by ff1.form_four_id) c on c.form_four_id=ff.form_four_id "+
			" inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift2,max(trip_number) tripsdeadshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id "+
			" WHERE trip_type='3' and shift_type_id='5' "+
			" group by ff1.form_four_id) d on d.form_four_id=ff.form_four_id "+
			" left join (select start_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) sf on sf.form_four_id=ff.form_four_id "+
			" left join (select end_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) sg on sg.form_four_id=ff.form_four_id "+
			" left join (select concat(schedule_details.start_time,'-',schedule_details.crew_change_location) crewchangelocation,ff1.form_four_id from  schedule_details  left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where crew_change='1' group by schedule_details.form_four_id) screw on screw.form_four_id=ff.form_four_id "+
			" left join (select count(*) count,ff1.form_four_id from schedule_details left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
			" where trip_type in ('19','20') group by ff1.form_four_id) pp "+
			" on pp.form_four_id=ff.form_four_id "+
			" left join (select schedule_details.start_time start_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) st on st.form_four_id=ff.form_four_id "+
			" left join (select schedule_details.end_time end_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) et on et.form_four_id=ff.form_four_id "+
			" where sl.service_limit='Suburban'  and ff.current_status ='ACTIVE' and ff.deleted_status='0' and depot_id="+depotName+" group by ff.form_four_id";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("schedule_number").addScalar("schedule_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("service_limit").addScalar("dista",Hibernate.STRING).addScalar("trips").addScalar("distashift2",Hibernate.STRING).addScalar("tripsshift2").addScalar("distadeadshift1",Hibernate.STRING).addScalar("tripsdeadshift1").addScalar("distadeadshift2",Hibernate.STRING).addScalar("tripsdeadshift2").addScalar("tot").addScalar("tottrips").addScalar("totdead").addScalar("steering1").addScalar("steering2").addScalar("ot1",Hibernate.STRING).addScalar("ot2",Hibernate.STRING).addScalar("startpoint")
			       .addScalar("endpoint").addScalar("chr").addScalar("crewchangelocation").addScalar("start_time").addScalar("end_time") ;
//	  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
		double Totaldista=0.0;
		double Totaldista_shift2=0.0;
	    double Total_kms=0.0;
	    double Total_dead1=0.0;
	    double Total_dead2=0.0;
	    double Total_trip1=0.0;
	    double Total_trip2=0.0;
	    double Total_trip=0.0;
	    double Toatal_dead_trip1=0.0;
	    double Total_dead_trip2=0.0;
	    double Total_streering_hrs1=0.0;
	    double Total_streering_min1=0.0;
	    double Total_streering_hrs2=0.0;
	    double Total_streering_min2=0.0;
	    double Total_hrs=0.0;
	    double Total_mins=0.0;
	    double Total_ot1=0.0;
	    double Total_ot2=0.0;
		
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+"</br>Depot Form-V</br>CITY DAY OUT";
		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

       

        regionTypeAjaxString += "<tr><th>S.No</th><th>Sch No</th><th colspan='3' align='center'>KMS</th><th colspan='2' align='center'>Dead KMS</th><th colspan='3' align='center'>No Of TRIPS</th><th colspan='2' align='center'>Dead TRIPS</th><th colspan='6' align='center'>Steering Hours</th><th></th><th></th><th></th><th></th><th></th><th></th></tr>" +
				      "<tr><th></th><th></th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift Hrs</th><th>I Shift Mins</th><th>II Shift Hrs</th><th>II Shift Mins</th><th>Total Hours</th><th>Total Mins</th><th>OT1</th><th>OT2</th><th>Departure Time</th><th>D/O Crew change Time </th><th>Arrival Time</th><th>Chartered  Schedule </th> "+
				"</tr>";
//		regionTypeAjaxString += "<div class='component'><lable>CITY DAY OUT</lable><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Sch No</th><th colspan='3' align='center'>KMS</th><th colspan='2' align='center'>Dead KMS</th><th colspan='3' align='center'>No Of TRIPS</th><th colspan='2' align='center'>Dead TRIPS</th><th colspan='13' align='center'>Steering Hours</th></tr>" +
//				      "<tr><th></th><th></th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift Hrs</th><th>I Shift Mins</th><th>II Shift Hrs</th><th>II Shift Mins</th><th>Total Hours</th><th>Total Mins</th><th>OT In Mins</th><th>Departure Time</th><th>D/O Crew change Time </th><th>Night Halt Time</th><th>N/O Morning Oute </th><th>In Time</th><th>Chartered  Schedule </th> "+
//				"</tr></thead><tbody>";
		
        if(aliasToValueMapList.size()==0){
       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
            regionTypeAjaxString += "<tr>";
            regionTypeAjaxString += "<td colspan='24' align='center'><b>No Result Found</b></td>";
           
            regionTypeAjaxString += "</tr>";
       	
       }else{
		
	
              
	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
		
 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("schedule_number").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("dista").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("distashift2").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("tot").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift1").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift2").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("trips").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("tripsshift2").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("tottrips").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift1").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift2").toString()+"</td>";
		String steering1=list.get("steering1").toString();
		String steering[]=steering1.split(":");
		String steering_hrs=steering[0];
		String streering_mins=steering[1];
//		System.out.println("steering_hrs1==="+steering_hrs+" streering_mins1=="+streering_mins);
		
		regionTypeAjaxString +="<td align='right'>"+steering_hrs+"</td>";
		regionTypeAjaxString +="<td align='right'>"+streering_mins+"</td>";
		String steering2=list.get("steering2").toString();
		String steerings[]=steering2.split(":");
		String steering_hrs1=steerings[0];
		String streering_mins1=steerings[1];
//		System.out.println("steering_hrs2==="+steering_hrs1+" streering_mins2=="+streering_mins1);

		regionTypeAjaxString +="<td align='right'>"+steering_hrs1+"</td>";
		regionTypeAjaxString +="<td align='right'>"+streering_mins1+"</td>";
		int total_hrs=Integer.parseInt(steering_hrs)+Integer.parseInt(steering_hrs1);
		int total_min=Integer.parseInt(streering_mins)+Integer.parseInt(streering_mins1);
		regionTypeAjaxString +="<td align='right'>"+total_hrs+"</td>";
		regionTypeAjaxString +="<td align='right'>"+total_min+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("ot1").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("ot2").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("start_time").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("crewchangelocation").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("end_time").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("chr").toString()+"</td>";
		
//		double dista=Double.parseDouble(list.get("dista").toString());
		
		Totaldista+=Double.parseDouble(list.get("dista").toString());;
		Totaldista_shift2+=Double.parseDouble(list.get("distashift2").toString());
		Total_kms+=Double.parseDouble(list.get("tot").toString());
		Total_dead1+=Double.parseDouble(list.get("distadeadshift1").toString());
		Total_dead2+=Double.parseDouble(list.get("distadeadshift2").toString());
		Total_trip1+=Double.parseDouble(list.get("trips").toString());
		Total_trip2+=Double.parseDouble(list.get("tripsshift2").toString());
		Total_trip+=Double.parseDouble(list.get("tottrips").toString());
		Toatal_dead_trip1+=Double.parseDouble(list.get("tripsdeadshift1").toString());
		Total_dead_trip2+=Double.parseDouble(list.get("tripsdeadshift2").toString());
		Total_streering_hrs1+=Double.parseDouble(steering_hrs);
		Total_streering_min1+=Double.parseDouble(streering_mins);
		Total_streering_hrs2+=Double.parseDouble(steering_hrs1);
		Total_streering_min2+=Double.parseDouble(streering_mins1);
		Total_hrs+=total_hrs;
		Total_mins+=total_min;
//		Total_ot1+=Double.parseDouble(list.get("ot1").toString());
//		Total_ot2+=Double.parseDouble(list.get("ot2").toString());
		
//		Totaletimservicetaxamount+=Double.parseDouble(list.get("ETIMServiceTax").toString());
//		Totalbagservicetaxamount+=Double.parseDouble(list.get("BagServiceTax").toString());
//		Totalpasssservicetaxamount+=Double.parseDouble(list.get("PassServiceTax").toString());
//		Granttotalservicetaxamount+=Totalvalues;
		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
		
		

		
		   regionTypeAjaxString +="</tr>";
	}
			regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ Totaldista+"</td>"+"<td align='right'><b>"+Totaldista_shift2+"</td><td align='right'><b>"+Total_kms+"</td>" +
					"<td align='right'><b>"+ Total_dead1+"</td><td align='right'><b>"+ Total_dead2+"</td><td align='right'><b>"+ Total_trip1+"</td><td align='right'><b>"+ Total_trip2+"</td><td align='right'><b>"+Total_trip+"</td><td align='right'><b>"+Toatal_dead_trip1+"</td>" +
							"<td align='right'><b>"+Total_dead_trip2+"</td><td align='right'><b>"+Total_streering_hrs1+"</td><td align='right'><b>"+Total_streering_min1+"</td><td align='right'><b>"+Total_streering_hrs2+"</td><td align='right'><b>"+Total_streering_min2+"</td><td align='right'><b>"+Total_hrs+"</td>" +
									"<td align='right'><b>"+Total_mins+"</td></tr>" +"\n";  
//			
			 regionTypeAjaxString += "</table></div> </div>  ";
	 
       }
			 
			 
	 
	
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return null;
}

public String getCitySubNightoutData(){
	
	
	try {
		CollectionReportDAO dao=new CollectionReportDAO();
		
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
		String depot="";
//		dao.getDepotName();
		String depot_name=getDepotNames(depotName);
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select s.schedule_number,s.schedule_id,ff.form_four_id,ff.schedule_number_name,sl.service_limit,a.dista,a.trips,b.distashift2,b.tripsshift2,c.distadeadshift1,c.tripsdeadshift1,"+ 
				 " d.distadeadshift2,d.tripsdeadshift2,(dista+distashift2) tot,(trips+tripsshift2)tottrips,(distadeadshift1+distadeadshift2) totdead, "+
				 " ff.steering1,ff.steering2,ff.ot1,ff.ot2,ifnull(sf.start_point,'0') startpoint,ifnull(sg.end_point,'0') endpoint,if(ifnull(count,0)=0,'N','Y') chr,"+
				 " ifnull(screw.crewchangelocation,'') crewchangelocation,ifnull(st.start_time,'') start_time,ifnull(et.end_time,'') end_time "+
				 " from schedule s inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				 " inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
				 " inner join (SELECT ifnull(sum(distance),'0.0')  as dista,max(trip_number) trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 " on ff1.form_four_id=schedule_details.form_four_id "+
				 " WHERE trip_type='2' and shift_type_id='2' "+
				 " group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+
				 " inner join (SELECT ifnull(sum(distance),'0.0')  as distashift2,max(trip_number) tripsshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id "+
				" WHERE trip_type='2' and shift_type_id='3' "+
				" group by ff1.form_four_id) b on b.form_four_id=ff.form_four_id "+
				" inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id "+
				" WHERE trip_type='3' and shift_type_id='2' "+
				" group by ff1.form_four_id) c on c.form_four_id=ff.form_four_id "+
				" inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift2,max(trip_number) tripsdeadshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id "+
				" WHERE trip_type='3' and shift_type_id='3' "+
				" group by ff1.form_four_id) d on d.form_four_id=ff.form_four_id "+
				" left join (select start_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) sf on sf.form_four_id=ff.form_four_id "+
				" left join (select end_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) sg on sg.form_four_id=ff.form_four_id "+
				" left join (select concat(schedule_details.start_time,'-',schedule_details.crew_change_location) crewchangelocation,ff1.form_four_id from  schedule_details  left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where crew_change='1' group by schedule_details.form_four_id) screw on screw.form_four_id=ff.form_four_id "+
				" left join (select count(*) count,ff1.form_four_id from schedule_details left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
				" where trip_type in ('19','20') group by ff1.form_four_id) pp "+
				" on pp.form_four_id=ff.form_four_id "+
				" left join (select schedule_details.start_time start_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) st on st.form_four_id=ff.form_four_id "+
				" left join (select schedule_details.end_time end_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) et on et.form_four_id=ff.form_four_id "+
				" where sl.service_limit='Suburban'  and ff.current_status ='ACTIVE' and ff.deleted_status='0' and depot_id="+depotName+" group by ff.form_four_id";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("schedule_number").addScalar("schedule_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("service_limit").addScalar("dista",Hibernate.STRING).addScalar("trips").addScalar("distashift2",Hibernate.STRING).addScalar("tripsshift2").addScalar("distadeadshift1",Hibernate.STRING).addScalar("tripsdeadshift1").addScalar("distadeadshift2",Hibernate.STRING).addScalar("tripsdeadshift2").addScalar("tot").addScalar("tottrips").addScalar("totdead").addScalar("steering1").addScalar("steering2").addScalar("ot1",Hibernate.STRING).addScalar("ot2",Hibernate.STRING).addScalar("startpoint")
				       .addScalar("endpoint").addScalar("chr").addScalar("crewchangelocation").addScalar("start_time").addScalar("end_time") ;
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totaldista=0.0;
			double Totaldista_shift2=0.0;
		    double Total_kms=0.0;
		    double Total_dead1=0.0;
		    double Total_dead2=0.0;
		    double Total_trip1=0.0;
		    double Total_trip2=0.0;
		    double Total_trip=0.0;
		    double Toatal_dead_trip1=0.0;
		    double Total_dead_trip2=0.0;
		    double Total_streering_hrs1=0.0;
		    double Total_streering_min1=0.0;
		    double Total_streering_hrs2=0.0;
		    double Total_streering_min2=0.0;
		    double Total_hrs=0.0;
		    double Total_mins=0.0;
		    String Totaldista_shift22="";
			
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot_name+"</br>SUB NIGHT OUT";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        
	      
	       
       
			 regionTypeAjaxString += "<tr><th>S.No</th><th>Sch No</th><th colspan='3' align='center'>KMS</th><th colspan='2' align='center'>Dead KMS</th><th colspan='3' align='center'>No Of TRIPS</th><th colspan='2' align='center'>Dead TRIPS</th><th colspan='6' align='center'>Steering Hours</th><th></th><th></th><th></th><th></th><th></th><th></th></tr>" +
				      "<tr><th></th><th></th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift</th><th>II Shift</th><th>Total</th><th>I Shift</th><th>II Shift</th><th>I Shift Hrs</th><th>I Shift Mins</th><th>II Shift Hrs</th><th>II Shift Mins</th><th>Total Hours</th><th>Total Mins</th><th>OT1</th><th>OT2</th><th>Departure Time</th><th>D/O Crew change Time </th><th>Arrival Time</th><th>Chartered  Schedule </th> "+
				"</tr>";
	    
			 if(aliasToValueMapList.size()==0){
	        	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	             regionTypeAjaxString += "<tr>";
	             regionTypeAjaxString += "<td colspan='24' align='center'><b>No Result Found</b></td>";
	            
	             regionTypeAjaxString += "</tr>";
	        	
	        }else{

                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("schedule_number").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("dista").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("distashift2").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tot").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("trips").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsshift2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tottrips").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift2").toString()+"</td>";
			String steering1=list.get("steering1").toString();
			String steering[]=steering1.split(":");
			String steering_hrs=steering[0];
			String streering_mins=steering[1];
//			System.out.println("steering_hrs1==="+steering_hrs+" streering_mins1=="+streering_mins);
			
			regionTypeAjaxString +="<td align='right'>"+steering_hrs+"</td>";
			regionTypeAjaxString +="<td align='right'>"+streering_mins+"</td>";
			String steering2=list.get("steering2").toString();
			String steerings[]=steering2.split(":");
			String steering_hrs1=steerings[0];
			String streering_mins1=steerings[1];
//			System.out.println("steering_hrs2==="+steering_hrs1+" streering_mins2=="+streering_mins1);

			regionTypeAjaxString +="<td align='right'>"+steering_hrs1+"</td>";
			regionTypeAjaxString +="<td align='right'>"+streering_mins1+"</td>";
			int total_hrs=Integer.parseInt(steering_hrs)+Integer.parseInt(steering_hrs1);
			int total_min=Integer.parseInt(streering_mins)+Integer.parseInt(streering_mins1);
			regionTypeAjaxString +="<td align='right'>"+total_hrs+"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_min+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("start_time").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("crewchangelocation").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("end_time").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("chr").toString()+"</td>";
			
//			double dista=Double.parseDouble(list.get("dista").toString());
			
			Totaldista+=Double.parseDouble(list.get("dista").toString());;
			Totaldista_shift2+=Double.parseDouble(list.get("distashift2").toString());
			Totaldista_shift22 = String.format("%.2f", Totaldista_shift2);
			Total_kms+=Double.parseDouble(list.get("tot").toString());
			Total_dead1+=Double.parseDouble(list.get("distadeadshift1").toString());
			Total_dead2+=Double.parseDouble(list.get("distadeadshift2").toString());
			Total_trip1+=Double.parseDouble(list.get("trips").toString());
			Total_trip2+=Double.parseDouble(list.get("tripsshift2").toString());
			Total_trip+=Double.parseDouble(list.get("tottrips").toString());
			Toatal_dead_trip1+=Double.parseDouble(list.get("tripsdeadshift1").toString());
			Total_dead_trip2+=Double.parseDouble(list.get("tripsdeadshift2").toString());
			Total_streering_hrs1+=Double.parseDouble(steering_hrs);
			Total_streering_min1+=Double.parseDouble(streering_mins);
			Total_streering_hrs2+=Double.parseDouble(steering_hrs1);
			Total_streering_min2+=Double.parseDouble(streering_mins1);
			Total_hrs+=total_hrs;
			Total_mins+=total_min;
			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
			
			
			
			   
			
			   regionTypeAjaxString +="</tr>";
		}
		     regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ Totaldista+"</td>"+"<td align='right'><b>"+Totaldista_shift22+"</td><td align='right'><b>"+Total_kms+"</td>" +
						"<td align='right'><b>"+ Total_dead1+"</td><td align='right'><b>"+ Total_dead2+"</td><td align='right'><b>"+ Total_trip1+"</td><td align='right'><b>"+ Total_trip2+"</td><td align='right'><b>"+Total_trip+"</td><td align='right'><b>"+Toatal_dead_trip1+"</td>" +
								"<td align='right'><b>"+Total_dead_trip2+"</td><td align='right'><b>"+Total_streering_hrs1+"</td><td align='right'><b>"+Total_streering_min1+"</td><td align='right'><b>"+Total_streering_hrs2+"</td><td align='right'><b>"+Total_streering_min2+"</td><td align='right'><b>"+Total_hrs+"</td>" +
										"<td align='right'><b>"+Total_mins+"</td></tr>" +"\n";  
//				
				 regionTypeAjaxString += "</table></div> </div>  ";
		 
				 
	        }
		 

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	return null;
}

public String getCitySubUrbanGeneralShiftData(){
	
	
	try {
		CollectionReportDAO dao=new CollectionReportDAO();
		
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
		String depot="";
//				dao.getDepotName();
		String depot_name=getDepotNames(depotName);
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select s.schedule_number,s.schedule_id,ff.form_four_id,ff.schedule_number_name,sl.service_limit,a.dista,a.trips,c.distadeadshift1,c.tripsdeadshift1,"+ 
				 " (dista) tot,(trips)tottrips,(distadeadshift1) totdead, "+
				"  ff.steering1,ff.ot1,ff.ot2,ifnull(sf.start_point,'0') startpoint,ifnull(sg.end_point,'0') endpoint,if(ifnull(count,0)=0,'N','Y') chr, "+
				 " ifnull(screw.crewchangelocation,'') crewchangelocation,ifnull(st.start_time,'') start_time,ifnull(et.end_time,'') end_time "+
				 " from schedule s inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				 " inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
				 " inner join (SELECT ifnull(sum(distance),'0.0')  as dista,max(trip_number) trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' and shift_type_id='1' "+
				 " group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+
				 " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 " on ff1.form_four_id=schedule_details.form_four_id "+
				 " WHERE trip_type='3' and shift_type_id='1' group by ff1.form_four_id) c on c.form_four_id=ff.form_four_id "+
				 " left join (select start_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) sf on sf.form_four_id=ff.form_four_id "+
				" left join (select end_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) sg on sg.form_four_id=ff.form_four_id "+
				" left join (select concat(schedule_details.start_time,'-',schedule_details.crew_change_location) crewchangelocation,ff1.form_four_id from  schedule_details  left join form_four ff1 "+
			    " on ff1.form_four_id=schedule_details.form_four_id where crew_change='1' group by schedule_details.form_four_id) screw on screw.form_four_id=ff.form_four_id "+
			    " left join (select count(*) count,ff1.form_four_id from schedule_details left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
			    " where trip_type in ('19','20') group by ff1.form_four_id) pp on pp.form_four_id=ff.form_four_id "+
			    " left join (select schedule_details.start_time start_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
			    " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) st on st.form_four_id=ff.form_four_id "+
			    " left join (select schedule_details.end_time end_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
			    " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) et on et.form_four_id=ff.form_four_id "+
			    " where sl.service_limit='Suburban'  "+
			    " and ff.current_status ='ACTIVE' and ff.deleted_status='0' and depot_id="+depotName+" group by ff.form_four_id";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("schedule_number").addScalar("schedule_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("service_limit").addScalar("dista",Hibernate.STRING).addScalar("trips").addScalar("distadeadshift1",Hibernate.STRING).addScalar("tripsdeadshift1").addScalar("tot").addScalar("tottrips").addScalar("totdead").addScalar("steering1").addScalar("ot1",Hibernate.STRING).addScalar("ot2",Hibernate.STRING).addScalar("startpoint")
			       .addScalar("endpoint").addScalar("chr").addScalar("crewchangelocation").addScalar("start_time").addScalar("end_time") ;
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totaldista=0.0;
		    double Total_dead1=0.0;
		    double Total_trip1=0.0;
		    double Toatal_dead_trip1=0.0;
		    double Total_streering_hrs1=0.0;
		    double Total_streering_min1=0.0;
		    String Total_dead="";
			
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot_name+"</br>SUB-URBAN GENERAL SHIFT";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString +="<tr><th>S.No</th><th>Sch No</th><th>KMS</th><th>Dead KMS</th><th>No Of TRIPS</th><th>Dead TRIPS</th><th>Steering Hours</th><th>Steering Mins</th><th>OT1</th><th>OT2</th><th>Departure Time</th><th>Arrival Time</th><th>Chartered  Schedule </th></tr>" +
					"</tr>";
	        


	        if(aliasToValueMapList.size()==0){
	        	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	             regionTypeAjaxString += "<tr>";
	             regionTypeAjaxString += "<td colspan='13' align='center'><b>No Result Found</b></td>";
	            
	             regionTypeAjaxString += "</tr>";
	        	
	        }else{
			

			  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
	 		int j=i+1;
			
	 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("schedule_number").toString()+"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("dista").toString() +"</td>";
			
			
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift1").toString()+"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+list.get("trips").toString()+"</td>";
			
			
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift1").toString()+"</td>";
			
			String steering1=list.get("steering1").toString();
			String steering[]=steering1.split(":");
			String steering_hrs=steering[0];
			String streering_mins=steering[1];
//			System.out.println("steering_hrs1==="+steering_hrs+" streering_mins1=="+streering_mins);
			
			regionTypeAjaxString +="<td align='right'>"+steering_hrs+"</td>";
			regionTypeAjaxString +="<td align='right'>"+streering_mins+"</td>";
			
			
			
			regionTypeAjaxString +="<td align='right'>"+list.get("ot1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("start_time").toString()+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("crewchangelocation").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("end_time").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("chr").toString()+"</td>";
			
			
			Totaldista+=Double.parseDouble(list.get("dista").toString());
			Total_dead1+=Double.parseDouble(list.get("distadeadshift1").toString());
			 Total_dead = String.format("%.2f", Total_dead1);
			Total_trip1+=Double.parseDouble(list.get("trips").toString());
			Toatal_dead_trip1+=Double.parseDouble(list.get("tripsdeadshift1").toString());
			Total_streering_hrs1+=Double.parseDouble(steering_hrs);
			Total_streering_min1+=Double.parseDouble(streering_mins);
//			
			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
			
			

			
			   regionTypeAjaxString +="</tr>";
		}
		     
		     regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ Totaldista+"</td>" +
						"<td align='right'><b>"+ Total_dead+"</td><td align='right'><b>"+ Total_trip1+"</td><td align='right'><b>"+Toatal_dead_trip1+"</td>" +
								"<td align='right'><b>"+Total_streering_hrs1+"</td><td align='right'><b>"+Total_streering_min1+"</td>" +
										"</tr>" +"\n";  

				 regionTypeAjaxString += "</table></div> </div>  ";
		 
	        }
				
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		

			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	return null;
}

public String getCitySubUrbanNightServiceData(){
	
	
	try {
		CollectionReportDAO dao=new CollectionReportDAO();
		
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
//		String depot=dao.getDepotName();
		String depot_name=getDepotNames(depotName);
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select s.schedule_number,s.schedule_id,ff.form_four_id,ff.schedule_number_name,sl.service_limit,a.dista,a.trips,b.distashift2,b.tripsshift2,c.distadeadshift1,c.tripsdeadshift1,"+ 
				 " d.distadeadshift2,d.tripsdeadshift2,(dista+distashift2) tot,(trips+tripsshift2)tottrips,(distadeadshift1+distadeadshift2) totdead,"+
				 " ff.steering1,ff.steering2,ff.ot1,ff.ot2,ifnull(sf.start_point,'0') startpoint,ifnull(sg.end_point,'0') endpoint,if(ifnull(count,0)=0,'N','Y') chr, "+
				 " ifnull(screw.crewchangelocation,'') crewchangelocation,ifnull(st.start_time,'') start_time,ifnull(et.end_time,'') end_time,night_halt_location,trip_number "+
				  " from schedule s inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				 "  inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
				 "  inner join (SELECT ifnull(sum(distance),'0.0')  as dista,max(trip_number) trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				  " on ff1.form_four_id=schedule_details.form_four_id "+
				 "  WHERE trip_type='2' and shift_type_id='2' "+
				  " group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+
				  " left join (SELECT form_four_id,night_halt_location FROM schedule_details where  night_halt='1') s "+ 
				  " on s.form_four_id=ff.form_four_id    " +
				  "left join (SELECT form_four_id,trip_number FROM schedule_details where  break_type_id='9') s1 "+
                   " on s1.form_four_id=ff.form_four_id"+
				 "  inner join (SELECT ifnull(sum(distance),'0.0')  as distashift2,max(trip_number) tripsshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 "  on ff1.form_four_id=schedule_details.form_four_id "+
				  " WHERE trip_type='2' and shift_type_id='3' "+
				  " group by ff1.form_four_id) b on b.form_four_id=ff.form_four_id "+
				  " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 "  on ff1.form_four_id=schedule_details.form_four_id "+
				 "  WHERE trip_type='3' and shift_type_id='2' "+
				  " group by ff1.form_four_id) c on c.form_four_id=ff.form_four_id "+
				  " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift2,max(trip_number) tripsdeadshift2,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				  " on ff1.form_four_id=schedule_details.form_four_id "+
				  " WHERE trip_type='3' and shift_type_id='3' "+
				  " group by ff1.form_four_id) d on d.form_four_id=ff.form_four_id "+
				  " left join (select start_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				  " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) sf on sf.form_four_id=ff.form_four_id "+
				  " left join (select end_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
				  " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) sg on sg.form_four_id=ff.form_four_id "+
				  " left join (select concat(schedule_details.start_time,'-',schedule_details.crew_change_location) crewchangelocation,ff1.form_four_id from  schedule_details  left join form_four ff1 "+
				  " on ff1.form_four_id=schedule_details.form_four_id where crew_change='1' group by schedule_details.form_four_id) screw on screw.form_four_id=ff.form_four_id "+
				  " left join (select count(*) count,ff1.form_four_id from schedule_details left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
				  " where trip_type in ('19','20') group by ff1.form_four_id) pp "+
				  " on pp.form_four_id=ff.form_four_id "+
				  " left join (select schedule_details.start_time start_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
				  " on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) st on st.form_four_id=ff.form_four_id "+
				  " left join (select schedule_details.end_time end_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
				 "  on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) et on et.form_four_id=ff.form_four_id "+
				  " where sl.service_limit='Suburban'  "+
				  " and ff.current_status ='ACTIVE' and ff.deleted_status='0' and depot_id="+depotName+" group by ff.form_four_id";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("schedule_number").addScalar("schedule_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("service_limit").addScalar("dista",Hibernate.STRING).addScalar("trips").addScalar("distadeadshift1",Hibernate.STRING).addScalar("tripsdeadshift1").addScalar("tot").addScalar("tottrips").addScalar("totdead").addScalar("steering1").addScalar("ot1",Hibernate.STRING).addScalar("ot2",Hibernate.STRING).addScalar("startpoint")
			       .addScalar("endpoint").addScalar("chr").addScalar("crewchangelocation").addScalar("start_time").addScalar("end_time").addScalar("night_halt_location").addScalar("trip_number") ;  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totaldista=0.0;
		    double Total_dead1=0.0;
		    double Total_trip1=0.0;
		    double Toatal_dead_trip1=0.0;
		    double Total_streering_hrs1=0.0;
		    double Total_streering_min1=0.0;
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        
	      
	       

//	        regionTypeAjaxString += "<tr><th>Denom</th><th>No. of Tickets</th><th>Value</th></tr>";
	        
	        regionTypeAjaxString +="<tr><th>S.No</th><th>Sch No</th><th>KMS</th><th>Dead KMS</th><th>No Of TRIPS</th><th>Dead TRIPS</th><th>Steering Hours</th><th>Steering Mins</th><th>OT1</th><th>OT2</th><th>Departure Time</th><th>Halt Time & Place</th><th>Morning Out Time</th><th>Arrival  Time</th></tr>";
	        

			 
	        if(aliasToValueMapList.size()==0){
	        	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	             regionTypeAjaxString += "<tr>";
	             regionTypeAjaxString += "<td colspan='14' align='center'><b>No Result Found</b></td>";
	            
	             regionTypeAjaxString += "</tr>";
	        	
	        }else{

		
	              
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
	 		int j=i+1;
	 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("schedule_number").toString()+"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("dista").toString() +"</td>";
			
			
			regionTypeAjaxString +="<td align='right'>"+list.get("distadeadshift1").toString()+"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+list.get("trips").toString()+"</td>";
			
			
			regionTypeAjaxString +="<td align='right'>"+list.get("tripsdeadshift1").toString()+"</td>";
			
			String steering1=list.get("steering1").toString();
			String steering[]=steering1.split(":");
			String steering_hrs=steering[0];
			String streering_mins=steering[1];
			System.out.println("steering_hrs1==="+steering_hrs+" streering_mins1=="+streering_mins);
			
			regionTypeAjaxString +="<td align='right'>"+steering_hrs+"</td>";
			regionTypeAjaxString +="<td align='right'>"+streering_mins+"</td>";
			
			
			
			regionTypeAjaxString +="<td align='right'>"+list.get("ot1").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("ot2").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("start_time").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("night_halt_location").toString()+"</td>";
			String start_time="";
			if(list.get("trip_number")==null || list.get("trip_number")==""){
				start_time=" ";
			}else{
			int trip_number=Integer.parseInt(list.get("trip_number").toString());
			String form_four_id=list.get("form_four_id").toString();
			 start_time=getMorningStartTime(trip_number,form_four_id);
			}
			regionTypeAjaxString +="<td align='right'>"+start_time+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("end_time").toString()+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("chr").toString()+"</td>";
			
			
			Totaldista+=Double.parseDouble(list.get("dista").toString());;

			Total_dead1+=Double.parseDouble(list.get("distadeadshift1").toString());
			Total_trip1+=Double.parseDouble(list.get("trips").toString());
			Toatal_dead_trip1+=Double.parseDouble(list.get("tripsdeadshift1").toString());
			Total_streering_hrs1+=Double.parseDouble(steering_hrs);
			Total_streering_min1+=Double.parseDouble(streering_mins);

			
			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
			
			

			
			   regionTypeAjaxString +="</tr>";
		}
		     regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ Totaldista+"</td>" +
						"<td align='right'><b>"+ Total_dead1+"</td><td align='right'><b>"+ Total_trip1+"</td><td align='right'><b>"+Toatal_dead_trip1+"</td>" +
								"<td align='right'><b>"+Total_streering_hrs1+"</td><td align='right'><b>"+Total_streering_min1+"</td>" +
										"</tr>" +"\n";  			
				 regionTypeAjaxString += "</table></div> </div>  ";
		 
				 
				 
	        }
		 
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	return null;
}


public String getSummaryOfEachDepotData(){
	
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
	
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="SELECT count(schedule_number) tot_sch,round(SUM(dista),2) tot_dista,round(SUM(trips),2) tot_trip,round(SUM(distadeadshift1),2) tot_distadead,round(SUM(tripsdeadshift1),2) tot_deadtrip, "+
			" SUM(steering1) tot_steer1,SUM(steering2) tot_steer2,(SUM(steering1)+SUM(steering2)) tot_steer,schedule_type_name from( "+
			" select s.schedule_number,a.dista,a.trips,c1.distadeadshift1,c1.tripsdeadshift1, "+
			" (dista) tot,(trips)tottrips,(distadeadshift1) totdead,sp2.schedule_type_name,sp2.schedule_type_id, "+
			" time_to_sec(ff.steering1) as steering1 ,time_to_sec(ff.steering2) as steering2 "+
			" from schedule s inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
			" inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
			" inner join (SELECT ifnull(sum(distance),'0.0')  as dista,max(trip_number) trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' "+
			" group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+
			" inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
			" group by ff1.form_four_id) c1 on c1.form_four_id=ff.form_four_id "+
			" inner join schedule_type sp2 on sp2.schedule_type_id=s.schedule_type "+
			" left join (select start_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) sf on sf.form_four_id=ff.form_four_id "+
			" left join (select end_point,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) sg on sg.form_four_id=ff.form_four_id "+
			" left join (select concat(schedule_details.start_time,'-',schedule_details.crew_change_location) crewchangelocation,ff1.form_four_id from  schedule_details  left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where crew_change='1' group by schedule_details.form_four_id) screw on screw.form_four_id=ff.form_four_id "+
			" left join (select count(*) count,ff1.form_four_id from schedule_details left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
			" where trip_type in ('19','20') group by ff1.form_four_id) pp "+
			" on pp.form_four_id=ff.form_four_id "+
			" left join (select schedule_details.start_time start_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select min(trip_number) from schedule_details)) st on st.form_four_id=ff.form_four_id "+
			" left join (select schedule_details.end_time end_time,ff1.form_four_id from schedule_details left join form_four ff1 "+
			" on ff1.form_four_id=schedule_details.form_four_id where trip_number in (select max(trip_number) from schedule_details)) et on et.form_four_id=ff.form_four_id "+
			" where  ff.current_status ='ACTIVE' and ff.deleted_status='0' and depot_id="+depotName+" ) bb group by bb.schedule_type_name";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("tot_sch").addScalar("tot_dista").addScalar("tot_trip").addScalar("tot_distadead").addScalar("tot_deadtrip").addScalar("tot_steer1",Hibernate.STRING).addScalar("tot_steer2").addScalar("tot_steer",Hibernate.STRING).addScalar("schedule_type_name",Hibernate.STRING) ;  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
		double Total_sch=0.0;
		double Totaldista=0.0;
		double TotalDeadkms=0.0;
	    double Total_tripkms=0.0;
	    double Total_schkms=0.0;
	    double Total_trip1=0.0;
	    double Toatal_dead_trip1=0.0;
	    double Total_streering_hrs1=0.0;
	    double Total_streering_min1=0.0;
	    String TotalDeadkms1="";
	
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        
      

        
       
        regionTypeAjaxString +="<tr><th></th><th>Total Schedule</th><th>Sch Kms</th><th>Dead Km</th><th>Total Kms</th><th>Trips</th><th>D.Trips</th><th>Total Trips</th><th colspan='2' align='center'>Total Steering Hours</th></tr>" +
				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>Hours</th><th>Minutes</th></tr>";

//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Sch No</th><th>KMS</th><th>Dead KMS</th><th>No Of TRIPS</th><th>Dead TRIPS</th><th>Steering Hours</th><th>Steering Mins</th><th>OT Hrs</th><th>OT Mins</th><th>Departure Time</th><th>D/O Crew change Time </th><th>Night Halt Time</th><th>N/O Morning Oute </th><th>In Time</th><th>Chartered  Schedule </th></tr>" +
//				"</tr></thead><tbody>";
		 
		

        if(aliasToValueMapList.size()==0){
       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
            regionTypeAjaxString += "<tr>";
            regionTypeAjaxString += "<td colspan='10' align='center'><b>No Result Found</b></td>";
           
            regionTypeAjaxString += "</tr>";
       	
       }else{
              
	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
		
// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("schedule_type_name").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("tot_sch").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("tot_dista").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("tot_distadead").toString()+"</td>";
		double tot_km=Double.parseDouble(list.get("tot_dista").toString())+Double.parseDouble(list.get("tot_distadead").toString());
		regionTypeAjaxString +="<td align='right'>"+tot_km+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("tot_trip").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("tot_deadtrip").toString()+"</td>";
		double tot_tripkm=Double.parseDouble(list.get("tot_trip").toString())+Double.parseDouble(list.get("tot_deadtrip").toString());
		regionTypeAjaxString +="<td align='right'>"+tot_tripkm+"</td>";
		int tot_steering=Integer.parseInt(list.get("tot_steer").toString());
		int min = (tot_steering / 60)%60;
	    int hours = (int)(tot_steering/60)/60;
//	    System.out.println("hours==="+hours+"min==="+min);
	    regionTypeAjaxString +="<td align='right'>"+hours+"</td>";
	    regionTypeAjaxString +="<td align='right'>"+min+"</td>";
		
	    Total_sch+=Double.parseDouble(list.get("tot_sch").toString());
	    Totaldista+=Double.parseDouble(list.get("tot_dista").toString());
	    TotalDeadkms+=Double.parseDouble(list.get("tot_distadead").toString());
	     TotalDeadkms1=String.format("%.2f", TotalDeadkms);
	    Total_schkms+=tot_km;
//		Total_dead1+=Double.parseDouble(list.get("tot_trip").toString());
		Total_trip1+=Double.parseDouble(list.get("tot_trip").toString());
		Toatal_dead_trip1+=Double.parseDouble(list.get("tot_deadtrip").toString());
		Total_tripkms+=tot_tripkm;
		Total_streering_hrs1+=hours;
		Total_streering_min1+=min;
		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
		
		


		
		   regionTypeAjaxString +="</tr>";
	}

	     regionTypeAjaxString +="<tr><td colspan='1'><center><b>Total</b></center></td><td align='right'><b>"+ Total_sch+"</td>" +
					"<td align='right'><b>"+ Totaldista+"</td><td align='right'><b>"+TotalDeadkms1+"</td><td align='right'><b>"+ Total_schkms+"</td><td align='right'><b>"+Total_trip1+"</td>" +
							"<td align='right'><b>"+Toatal_dead_trip1+"</td><td align='right'><b>"+Total_tripkms+"</td><td align='right'><b>"+Total_streering_hrs1+"</td><td align='right'><b>"+Total_streering_min1+"</td>" +
									"</tr>" +"\n";  			
			 regionTypeAjaxString += "</table></div> </div>  ";
	 
       }

	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return null;
}

public String getVehicleUtilizationData(){
	
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
	
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="";
	 
//	 Query query = session1.createSQLQuery(sql).addScalar("Date").addScalar("ETIMServiceTax").addScalar("BagServiceTax").addScalar("PassServiceTax");
//	  
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String,Object>> aliasToValueMapList = query.list();
	 List<Map<String,Object>> aliasToValueMapList=null;
//		double Totalvalues=0.0;
//		double Totaletimservicetaxamount=0.0;
//	    double Totalbagservicetaxamount=0.0;
//	    double Totalpasssservicetaxamount=0.0;
//	    double Granttotalservicetaxamount=0.0;
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        
      

        
       
        regionTypeAjaxString +="<tr><th></th><th>D/O</th><th>N/O</th><th>GS</th><th>NS</th><th>TOTAL</th></tr>" ;
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Sch No</th><th>KMS</th><th>Dead KMS</th><th>No Of TRIPS</th><th>Dead TRIPS</th><th>Steering Hours</th><th>Steering Mins</th><th>OT Hrs</th><th>OT Mins</th><th>Departure Time</th><th>D/O Crew change Time </th><th>Night Halt Time</th><th>N/O Morning Oute </th><th>In Time</th><th>Chartered  Schedule </th></tr>" +
//				"</tr></thead><tbody>";
		 
		


        if(aliasToValueMapList==null){
       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
            regionTypeAjaxString += "<tr>";
            regionTypeAjaxString += "<td colspan='6' align='center'><b>No Result Found</b></td>";
           
            regionTypeAjaxString += "</tr>";
       	
       }else{
	
		    
              
//	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//	    	 regionTypeAjaxString +="<tr>";
//		Map<String,Object> list = aliasToValueMapList.get(i);
// 		int j=i+1;
//		
// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("ETIMServiceTax").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("BagServiceTax").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("PassServiceTax").toString()+"</td>";
//		
//		
//		String  date = list.get("Date").toString();
//		String etim = list.get("ETIMServiceTax").toString();
//		String bag = list.get("BagServiceTax").toString();
//		String pass = list.get("PassServiceTax").toString();
//		Totalvalues=Double.parseDouble(etim)+Double.parseDouble(bag)+Double.parseDouble(pass);
//		
//		Totaletimservicetaxamount+=Double.parseDouble(list.get("ETIMServiceTax").toString());
//		Totalbagservicetaxamount+=Double.parseDouble(list.get("BagServiceTax").toString());
//		Totalpasssservicetaxamount+=Double.parseDouble(list.get("PassServiceTax").toString());
//		Granttotalservicetaxamount+=Totalvalues;
		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
		
		


		
		   regionTypeAjaxString +="</tr>";
//	}

//			regionTypeAjaxString +="<tr><td colspan='2'><center><b>Sub Total</b></center></td><td align='right'><b>"+ Totaletimservicetaxamount+"</td>"+"<td align='right'><b>"+ Totalbagservicetaxamount+"</td><td align='right'><b>"+ Totalpasssservicetaxamount+"</td><td align='right'><b>"+ Granttotalservicetaxamount+"</td></tr>" +"\n";  
//			
		   
//       }
			 regionTypeAjaxString += "</table></div> </div>  ";
       }
			 

       
	 

	
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;

		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return null;
}

public String getDepotNames(String depot_id){
	
	String depotname="";
	Session session =null;
	try{
	String sql = " select org_name from org_chart where deleted_status=0 and org_chart_id="+depot_id+" and org_type_id=3  order by org_name";

	// sql += " order by " + COL_NAME + " " + DIR;
	Query query = HibernateUtil.getSession("").createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	if (aliasToValueMapList.size() > 0) {

		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			depotname=rs.get("org_name").toString();
		}
	}
	HibernateUtil.closeSession();
	

	}catch(Exception e){
		e.printStackTrace();
	}

return depotname;
}

public String getMorningStartTime(int trip,String form_id){
	
	String starttime="";
	int trip_id=trip+1;
	Session session =null;
	try{
	String sql = " select start_time from schedule_details where trip_number="+trip_id+" and form_four_id='"+form_id+"'";

	// sql += " order by " + COL_NAME + " " + DIR;
	Query query = HibernateUtil.getSession("").createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	if (aliasToValueMapList.size() > 0) {

		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			starttime=rs.get("start_time").toString();
		}
	}
	HibernateUtil.closeSession();
	

	}catch(Exception e){
		e.printStackTrace();
	}

return starttime;
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
