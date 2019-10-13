package com.trimax.its.lookup.sync;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.util.HibernateUtil;


public class ComplaintDao {

	public String createComplaintAuto( Date date2) {
		Session session=null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			/*String sql="SELECT route_number,vehicle_number,depot_name,travel_datetime,mobileno,email_id,floor_clean,seats_clean," +
					"windows_clean,doors_clean,lighting_inside,seats_damage,AC_working,crew_behavior,crew_helpful,crew_response," +
					"conductor_rating,driver_rating,trans_date,complaint_ref_no,BMTC_rating,complaint_raised,subcategory_id," +
					"caller_name,caller_remarks,count(*) cnt FROM `customer_feedback` where date(travel_datetime) = date(now()) " +
					"group by vehicle_number having cnt >10";*/
			String sql="SELECT route_number,vehicle_number,depot_name,travel_datetime,mobileno,email_id,floor_clean,seats_clean," + 
					"windows_clean,doors_clean,lighting_inside,seats_damage,AC_working,crew_behavior,crew_helpful,crew_response," + 
					"conductor_rating,driver_rating,trans_date,complaint_ref_no,BMTC_rating,complaint_raised,subcategory_id," + 
					"caller_name,caller_remarks,count(*) cnt FROM `customer_feedback` where date(travel_datetime) = date(now()) " + 
					"and complaint_raised='no' and (floor_clean='no' or seats_clean='no' or windows_clean='no' or doors_clean='no' or lighting_inside='no' " + 
					"or seats_damage='no' or AC_working='no' or crew_behavior='no' or crew_helpful='no') " + 
					"group by vehicle_number having cnt >9";
			
			String queryToInsertComplaint = "INSERT INTO `customer_complaint` ( `route_number`, `vehicle_number`,`depot_name`, `travel_datetime`," +
					"`mobileno`,  `email_id`,`floor_clean`, `seats_clean`,`windows_clean`, `doors_clean`, `lighting_inside`, `seats_damage`, " +
					"`AC_working`, `crew_behavior`, `crew_helpful`, `crew_response`,`conductor_rating`, `driver_rating`,`trans_date`, " +
					" `complaint_ref_no`, `BMTC_rating`,`complaint_raised`,`subcategory_id`,`caller_name`,`caller_remarks`)  VALUES ";
			
			Query query= session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				System.out.println("inside");
				Map<String, Object> rs = aliasToValueMapList.get(i);
				queryToInsertComplaint+="('"+rs.get("route_number").toString()+"','"+rs.get("vehicle_number").toString()+"'," +
						"'"+rs.get("depot_name").toString()+"','"+rs.get("travel_datetime").toString()+"','"+rs.get("mobileno").toString()+"'," +
						"'"+rs.get("email_id").toString()+"','"+rs.get("floor_clean").toString()+"','"+rs.get("seats_clean").toString()+"'," +
						"'"+rs.get("windows_clean").toString()+"','"+rs.get("doors_clean").toString()+"','"+rs.get("lighting_inside").toString()+"'," +
						"'"+rs.get("seats_damage").toString()+"','"+rs.get("AC_working").toString()+"','"+rs.get("crew_behavior").toString()+"'," +
						"'"+rs.get("crew_helpful").toString()+"','"+rs.get("crew_response").toString()+"','"+rs.get("conductor_rating").toString()+"'," +
						"'"+rs.get("driver_rating").toString()+"','"+rs.get("trans_date").toString()+"','"+rs.get("complaint_ref_no").toString()+"'," +
						"'"+rs.get("BMTC_rating").toString()+"','"+rs.get("complaint_raised").toString()+"','"+rs.get("subcategory_id").toString()+"'," +
						"'"+rs.get("caller_name").toString()+"','"+rs.get("caller_remarks").toString()+"')";
				
			}
			Query queryObjectForInsertComplaint = session.createSQLQuery(queryToInsertComplaint);
			queryObjectForInsertComplaint.executeUpdate();
			session.beginTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
		if(islastdate()) {
			System.out.println("inlast");
			try {
				createmonthlycomplient(monthfstdate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "success";
	}
public String monthfstdate() throws ParseException {

	Calendar c=null;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	try{
	
	Date convertedDate = new Date();
	 c = Calendar.getInstance();
	c.setTime(convertedDate);
	c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum((Calendar.DAY_OF_MONTH)));
    return dateFormat.format(c.getTime());
	}catch (Exception e) {
		e.printStackTrace();
	}
	 return dateFormat.format(c.getTime());
    

}
public Boolean islastdate() {
	boolean res=false;
    Calendar calendar = Calendar.getInstance();

    int lastDate = calendar.getActualMaximum(Calendar.DATE);
      if(calendar.getTime().getDate()==lastDate) {
    	  res=true;
      }
    System.out.println("Date     : " + calendar.getTime().getDate());
    System.out.println("Last Date: " + lastDate);
   return res;
}
public void createmonthlycomplient(String fdate) {

	Session session=null;
	try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*String sql="SELECT route_number,vehicle_number,depot_name,travel_datetime,mobileno,email_id,floor_clean,seats_clean," +
				"windows_clean,doors_clean,lighting_inside,seats_damage,AC_working,crew_behavior,crew_helpful,crew_response," +
				"conductor_rating,driver_rating,trans_date,complaint_ref_no,BMTC_rating,complaint_raised,subcategory_id," +
				"caller_name,caller_remarks,count(*) cnt FROM `customer_feedback` where date(travel_datetime) = date(now()) " +
				"group by vehicle_number having cnt >10";*/
		String sql="SELECT route_number,vehicle_number,depot_name,travel_datetime,mobileno,email_id,floor_clean,seats_clean," + 
				"windows_clean,doors_clean,lighting_inside,seats_damage,AC_working,crew_behavior,crew_helpful,crew_response," + 
				"conductor_rating,driver_rating,trans_date,complaint_ref_no,BMTC_rating,complaint_raised,subcategory_id," + 
				"caller_name,caller_remarks,count(*) cnt FROM `customer_feedback` where travel_datetime between '"+fdate+"' and  date(now()) " + 
				"and complaint_raised='no' and (floor_clean='no' or seats_clean='no' or windows_clean='no' or doors_clean='no' or lighting_inside='no' " + 
				"or seats_damage='no' or AC_working='no' or crew_behavior='no' or crew_helpful='no') and vehicle_number not in (" + 
				"select vehicle_number from customer_complaint where travel_datetime between '"+fdate+"'  and now() )" + 
				"group by vehicle_number having cnt >9"; 
		
		String queryToInsertComplaint = "INSERT INTO `customer_complaint` ( `route_number`, `vehicle_number`,`depot_name`, `travel_datetime`," +
				"`mobileno`,  `email_id`,`floor_clean`, `seats_clean`,`windows_clean`, `doors_clean`, `lighting_inside`, `seats_damage`, " +
				"`AC_working`, `crew_behavior`, `crew_helpful`, `crew_response`,`conductor_rating`, `driver_rating`,`trans_date`, " +
				" `complaint_ref_no`, `BMTC_rating`,`complaint_raised`,`subcategory_id`,`caller_name`,`caller_remarks`)  VALUES ";
		
		Query query= session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			queryToInsertComplaint+="('"+rs.get("route_number").toString()+"','"+rs.get("vehicle_number").toString()+"'," +
					"'"+rs.get("depot_name").toString()+"','"+rs.get("travel_datetime").toString()+"','"+rs.get("mobileno").toString()+"'," +
					"'"+rs.get("email_id").toString()+"','"+rs.get("floor_clean").toString()+"','"+rs.get("seats_clean").toString()+"'," +
					"'"+rs.get("windows_clean").toString()+"','"+rs.get("doors_clean").toString()+"','"+rs.get("lighting_inside").toString()+"'," +
					"'"+rs.get("seats_damage").toString()+"','"+rs.get("AC_working").toString()+"','"+rs.get("crew_behavior").toString()+"'," +
					"'"+rs.get("crew_helpful").toString()+"','"+rs.get("crew_response").toString()+"','"+rs.get("conductor_rating").toString()+"'," +
					"'"+rs.get("driver_rating").toString()+"','"+rs.get("trans_date").toString()+"','"+rs.get("complaint_ref_no").toString()+"'," +
					"'"+rs.get("BMTC_rating").toString()+"','"+rs.get("complaint_raised").toString()+"','"+rs.get("subcategory_id").toString()+"'," +
					"'"+rs.get("caller_name").toString()+"','"+rs.get("caller_remarks").toString()+"')";
			
		}
		Query queryObjectForInsertComplaint = session.createSQLQuery(queryToInsertComplaint);
		queryObjectForInsertComplaint.executeUpdate();
		session.beginTransaction().commit();
	}catch (Exception e) {
		e.printStackTrace();
		session.beginTransaction().rollback();
	}
	

}
/*public static void main(String[] args) {
	new ComplaintDao().get();
}
void get() {createComplaintAuto(new Date());}*/
}
