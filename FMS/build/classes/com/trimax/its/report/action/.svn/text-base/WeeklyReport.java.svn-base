package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class WeeklyReport extends ActionSupport {

	

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
	
	public String startdate;
	public String orderno;
   
	
	private Map<Integer, String> divisionlist;
	
	private Map<Integer,String> orderlist1;
	private Map<String,String> orderlist;
	
	
	

	

	public Map<String, String> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(Map<String, String> orderlist) {
		this.orderlist = orderlist;
	}

	public Map<Integer, String> getOrderlist1() {
		return orderlist1;
	}

	public void setOrderlist1(Map<Integer, String> orderlis1t) {
		this.orderlist1 = orderlist1;
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
	      		List<String> l1 = dao.getFormfourid();
	      		List<String> l2 = dao.getTrafficOrder();
	      		String regionTypeAjaxString = "";
	      		//regionTypeAjaxString += "<option value='0'>------select------</option>";
	      		Map<String, String> resultMap = new LinkedHashMap<String, String>();
	      		for (int i = 0; i < l1.size(); i++) {
	      			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
	      					+ ">" + l2.get(i).toString() + "</option>";
	      			resultMap.put(l2.get(i).toString(), l2.get(i).toString());
	      		}
	      		HttpServletResponse response = ServletActionContext.getResponse();
	      		PrintWriter out;
	      		try {
	      			orderlist=resultMap;
//	      			out = response.getWriter();
//	      			out.print(regionTypeAjaxString);
	      		} catch (Exception e) {
	      			// TODO Auto-generated catch block
	      			e.printStackTrace();
	      		}
		  
		
		return "success";
	}
	
	
	public String getweeklyData(){
		
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
		String date=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
		String depot="";
				//dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		SimpleDateFormat sm2 = new SimpleDateFormat("dd/MM/yyyy");
//        Common cm = new Common();
//        String formatteddate= cm.getDateFromPicker(date);
//        System.out.println("---"+formatteddate);
          Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date); 
//            System.out.println(formatteddate+"\t"+date1); 
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
            String dayOfWeek1 = dateFormat.format(date1).toLowerCase();
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select SUM(CASE WHEN service_type_name='Ordinary' THEN tot_sch ELSE 0 END) Ordinary_sch, "+
				   " SUM(CASE WHEN service_type_name='Ordinary' THEN dista ELSE 0 END) Ordinary_dista, "+
				   " SUM(CASE WHEN service_type_name='Ordinary' THEN trip ELSE 0 END) Ordinary_trip, "+
				   " SUM(CASE WHEN service_type_name='Vajra' THEN tot_sch ELSE 0 END) Vajra_sch, "+
				   " SUM(CASE WHEN service_type_name='Vajra' THEN dista ELSE 0 END) Vajra_dista, "+
				   " SUM(CASE WHEN service_type_name='Vajra' THEN trip ELSE 0 END) Vajra_trip, "+
				   " SUM(CASE WHEN service_type_name='Vayu Vajra' THEN tot_sch ELSE 0 END) VVajra_sch, "+
				   " SUM(CASE WHEN service_type_name='Vayu Vajra' THEN dista ELSE 0 END) VVajra_dista, "+
				   " SUM(CASE WHEN service_type_name='Vayu Vajra' THEN trip ELSE 0 END) VVajra_trip, "+
				   " SUM(CASE WHEN service_type_name='Corona AC' THEN tot_sch ELSE 0 END) Corona_sch, "+
				   " SUM(CASE WHEN service_type_name='Corona AC' THEN dista ELSE 0 END) Corona_dista, "+
				   " SUM(CASE WHEN service_type_name='Corona AC' THEN trip ELSE 0 END) Corona_trip, "+
				   " sum(distadead) distadead,depot_id from( "+
				   " select sum(tot_sch) tot_sch,round(sum(dista),2) dista,sum(trip) trip,round(sum(distadeadshift1)) distadead,service_type_name,form_four_Id,depot_id from ( "+
				   " select traffic_order_no,f.form_four_Id,tot_sch,dista,trip,distadeadshift1,service_type_name,s.depot_id from form_four_traffic_order fr "+
				   " inner join form_four f on f.form_four_Id=fr.form_four_Id "+
				   " inner join weeklyChart w on w.form_four_id=f.form_four_id "+
				   " inner join schedule s on s.schedule_id=f.schedule_number_id "+ 
				   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+ 
				   " inner join service_type st on st.service_type_id=s.schedule_service_type "+
				   " inner join (SELECT count(schedule_number) as tot_sch,ifnull(sum(distance),'0.0')  as dista,max(trip_number) as trip,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' "+
				   " group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
				   " inner join (SELECT ifnull(sum(distance),'0.0')  as distadeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
				   " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id "+
				   " where traffic_order_no='"+orderno+"' and f.current_status ='ACTIVE' and f.deleted_status='0' and w."+dayOfWeek1+"=1 ) b group by b.service_type_name ) d  group by d.depot_id ";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("Ordinary_sch").addScalar("Ordinary_dista").addScalar("Ordinary_trip").addScalar("Vajra_sch").addScalar("Vajra_dista").addScalar("Vajra_trip").addScalar("VVajra_sch").addScalar("VVajra_dista").addScalar("VVajra_trip").addScalar("Corona_sch").addScalar("Corona_dista").addScalar("Corona_trip").addScalar("distadead").addScalar("depot_id");	  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totalsch=0.0;
			double Totalekms=0.0;
			double Totaletrip=0.0;
		    double Totalcorsch=0.0;
		    double Totalcorkms=0.0;
		    double Totalcortrip=0.0;
		    double Totalvolsch=0.0;
		    double Totalvolkms=0.0;
		    double Totalvoltrip=0.0;
		    double Totalveh_uti=0.0;
		    double Totaldead=0.0;
		    double total_kms=0.0;
		    double total_sch=0.0;
		    String Totalveh_uti1="";
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>Form-V  As On "+date+"  After Issue Of Traffic Order No "+orderno+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	                
	       


			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>Depot No</th><th>No Of SCH'S</th><th>Schedule KMS</th><th>Total Trips</th><th>CORONA Sch's</th><th>Schedule Kms</th><th>Total Trips</th><th>Volvo Sch's</th><th>Schedule Kms</th><th>Total Tips</th><th>Over All Veh.Uti.</th><th>Depot Wise Dead Kms</th></tr>" +
					      "</thead><tbody>";
			 
			if(aliasToValueMapList.size()==0){
		       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
		            regionTypeAjaxString += "<tr>";
		            regionTypeAjaxString += "<td colspan='12' align='center'><b>No Result Found</b></td>";
		           
		            regionTypeAjaxString += "</tr>";
		       	
		       }else{
		
                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
			String depot_name=getDepotNames(list.get("depot_id").toString());
			
			double vajra_sch=Double.parseDouble(list.get("Vajra_sch").toString());
			double vajra_dista=Double.parseDouble(list.get("Vajra_dista").toString());
			double vajra_trip=Double.parseDouble(list.get("Vajra_trip").toString());
			double vvajra_sch=Double.parseDouble(list.get("VVajra_sch").toString());
			double vvajra_dista=Double.parseDouble(list.get("VVajra_dista").toString());
			double vvajra_trip=Double.parseDouble(list.get("VVajra_trip").toString());
			double volvo_sch=vajra_sch+vvajra_sch;
			double volvo_dista=vajra_dista+vvajra_dista;
			double volvo_trip=vajra_trip+vvajra_trip;
			
     		regionTypeAjaxString +="<td align='right'>"+depot_name+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("Ordinary_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("Ordinary_dista").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("Ordinary_trip").toString()+"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+list.get("Corona_sch").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("Corona_dista").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("Corona_trip").toString()+"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+volvo_sch+"</td>";
			regionTypeAjaxString +="<td align='right'>"+volvo_dista+"</td>";
			regionTypeAjaxString +="<td align='right'>"+volvo_trip+"</td>";
			
			
			
			 total_kms=Double.parseDouble(list.get("Ordinary_dista").toString());
			 total_sch=Double.parseDouble(list.get("Ordinary_sch").toString());
			double veh_uti=0.0;
			if(total_sch==0.0){
				veh_uti=0.0;
			}else{
			 veh_uti=total_kms/total_sch;
			}
			String veh_uti1=String.format("%.2f", veh_uti);
			regionTypeAjaxString +="<td align='right'>"+veh_uti1+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("distadead").toString()+"</td>";
			
			Totalsch+=total_sch;
			Totalekms+=total_kms;
			Totaletrip+=Double.parseDouble(list.get("Ordinary_trip").toString());
			Totalcorsch+=Double.parseDouble(list.get("Corona_sch").toString());
			Totalcorkms+=Double.parseDouble(list.get("Corona_dista").toString());
			Totalcortrip+=Double.parseDouble(list.get("Corona_trip").toString());
			Totalvolsch+=volvo_sch;
			Totalvolkms+=volvo_dista;
			Totalvoltrip+=volvo_trip;
			Totalveh_uti+=veh_uti;
			Totalveh_uti1=String.format("%.2f", Totalveh_uti);
			Totaldead+=Double.parseDouble(list.get("distadead").toString());
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			

			
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Totalsch+"</td>"+"<td align='right'><b>"+ Totalekms+"</td><td align='right'><b>"+ Totaletrip+"</td><td align='right'><b>"+ Totalcorsch+"</td>" +
						"<td align='right'><b>"+ Totalcorkms+"</td><td align='right'><b>"+ Totalcortrip+"</td><td align='right'><b>"+ Totalvolsch+"</td>" +
								"<td align='right'><b>"+ Totalvolkms+"</td><td align='right'><b>"+ Totalvoltrip+"</td><td align='right'><b>"+ Totalveh_uti1+"</td><td align='right'><b>"+ Totaldead+"</td></tr>" +"\n";  
//				
				
				 regionTypeAjaxString += "</table></div> </div>  ";
				 
				 
				 regionTypeAjaxString += "<div class='component'></b><table border='2' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'>";
		   		 regionTypeAjaxString +="<tr>";
		            regionTypeAjaxString +="<th><center><b>Particular</b></center></th><th><center><b>Sch's</b></center></th><th><center><b>Total Kms </b></center></th><th><center><b>Total Trip </b></center></th></tr>";
		   		 
		           double Gtotal_sch=Totalvolsch+Totalcorsch+Totalsch;
		           double Gtotal_kms=Totalvolkms+Totalcorkms+Totalekms;
		           double Gtotal_trip=Totalvoltrip+Totalcortrip+Totaletrip;
		            
		         regionTypeAjaxString +="<tr><td align='right'>Volvo</td>";
		   		 regionTypeAjaxString +="<td align='right'>"+ Totalvolsch +"</td>";
		   		 regionTypeAjaxString +="<td align='right'>"+ Totalvolkms +"</td>";
		   		regionTypeAjaxString +="<td align='right'>"+ Totalvoltrip +"</td></tr>";
		   		regionTypeAjaxString +="<tr><td align='right'>CRN</td>";
		   		regionTypeAjaxString +="<td align='right'>"+ Totalcorsch +"</td>";
		   		 regionTypeAjaxString +="<td align='right'>"+ Totalcorkms +"</td>";
		   		regionTypeAjaxString +="<td align='right'>"+ Totalcortrip +"</td></tr>";
		   		regionTypeAjaxString +="<tr><td align='right'>Ordinary</td>";
		   		regionTypeAjaxString +="<td align='right'>"+ Totalsch +"</td>";
		   		 regionTypeAjaxString +="<td align='right'>"+ Totalekms +"</td>";
		   		regionTypeAjaxString +="<td align='right'>"+ Totaletrip +"</td></tr>";
		   		
		   		regionTypeAjaxString +="<tr><td align='right'>Total</td>";
		   		regionTypeAjaxString +="<td align='right'>"+ Gtotal_sch +"</td>";
		   		 regionTypeAjaxString +="<td align='right'>"+ Gtotal_kms +"</td>";
		   		regionTypeAjaxString +="<td align='right'>"+ Gtotal_trip +"</td></tr>";
		   		

		   		 
//		   		 regionTypeAjaxString +="<tr><td align='center'><b>Total</b></td><td align='right'>"+totalsixtyseventysum+"</td><td align='right'>"+totalsixtyseventysumvalues+"</td></tr>";
		   		 
				 
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
}
