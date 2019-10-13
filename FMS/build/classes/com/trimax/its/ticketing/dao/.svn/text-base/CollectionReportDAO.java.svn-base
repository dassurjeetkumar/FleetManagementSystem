package com.trimax.its.ticketing.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.transport.model.ScheduleService;

public class CollectionReportDAO {
	
	@SuppressWarnings("unchecked")
	public JSONObject getData() {
		
		Session session1 = null;
		Transaction transaction  = null;
		
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String sql=" select s.schedule_number,ifnull(wd.Bag_No,0) as Bag_No,e.EMPLOYEE_NAME,e.TOKEN,wd.waybill_No,sum(case when tt.ticket_type_id=1 then px_total_amount else 0 end) passenger,sum(case when tt.ticket_type_id=4 then px_total_amount else 0 end) pass,sum(case when tt.ticket_type_id=2 then px_total_amount else 0 end) luggage,wd.Excess_Amt,wd.Shortage_Amt,wd.Excess_Amt, (wd.cond_bata_amount+wd.cond_incentive_amount+wd.driver_bata_amount+wd.driver_incentive_amount) incbata,(wd.cond_other_deduction+wd.driver_other_deduction) othded,wd.ETIM_Coll_Amt from Waybill_Details wd " +
					"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
					"inner join schedule s on s.schedule_id=ff.schedule_number_id " +
					"inner join ticket t on t.waybil_Id=wd.waybil_Id " +
					"inner join ticket_type tt on tt.ticket_type_id=t.ticket_type_short_code " +
					"inner join employee e on e.EMPLOYEE_ID=wd.conductor_Id " +
					"group by s.schedule_number,e.EMPLOYEE_ID,wd.waybill_No";
			 
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			Query query = session1.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> list = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add(i+1);
				ja.add(list.get("schedule_number").toString());
				String bagNo=list.get("Bag_No")!=null?list.get("Bag_No").toString():"";
				ja.add(bagNo);
				ja.add(list.get("EMPLOYEE_NAME").toString()); 
				ja.add(list.get("TOKEN").toString());
				ja.add(list.get("waybill_No").toString()); 
				ja.add(list.get("passenger").toString());
				ja.add(list.get("pass").toString());
				ja.add(list.get("pass").toString());
				ja.add(list.get("passenger").toString());
				ja.add(list.get("luggage").toString());
				ja.add(list.get("Excess_Amt").toString());
				ja.add(list.get("ETIM_Coll_Amt").toString());
				
				double totalRemit=Double.parseDouble(list.get("ETIM_Coll_Amt").toString())-
						Double.parseDouble(list.get("incbata").toString())-
						Double.parseDouble(list.get("othded").toString());
				
				ja.add(list.get("incbata").toString());
				ja.add(list.get("othded").toString());
				ja.add(totalRemit);
				ja.add(totalRemit);
				ja.add(list.get("Shortage_Amt").toString());
				array.add(ja);
			}
			result.put("aaData", array);
			result.put("iTotalRecords", aliasToValueMapList.size());

			result.put("iTotalDisplayRecords", aliasToValueMapList.size());
		//	System.out.println("REsult ------>" + request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	public String getDateFromPicker(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/mm/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	//ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
public String getOrgName(){
		
		String orgname="";
	
		Query query=null;
		String sql = "select org_name from default_system_veriable s left join org_chart o on s.sys_value=o.org_chart_id where s.sys_key ='REPORT_TITLE_ORG_ID' and s.deleted_status=0";
		Session session = null;
		try {
			
		 	session = HibernateUtil.getSession("hibernate.cfg.xml");
		 	query = session.createSQLQuery(sql);
		 	orgname=query.uniqueResult().toString();

		} catch (Exception e) {
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return orgname;
	}
	public String getDepotName(){

		String depot="";
		Query query=null;
		String sql = "Select org_name from depot where deleted_status=0";
		Session session = null;
		try {
			
		 	session = HibernateUtil.getSession("hibernate.cfg.xml");
		 	query = session.createSQLQuery(sql);
			depot=query.uniqueResult().toString();

		} catch (Exception e) {
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return depot;
	}
	
	public String getDateFromPickerDate(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-mm-yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	public String getDateFromPickerDate1(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public List getDepotIdDC() {
		List list = new ArrayList();
//		System.out.println("org---"+orgchart_id);
		String qry = "select org_chart_id from org_chart where deleted_status=0 " 
				+ " and org_type_id=3 order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getDepotNameDC() {
		List list = new ArrayList();
		String qry = "select org_name from org_chart where deleted_status=0 "
				+ " and org_type_id=3  order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getFormfourid() {
		List list = new ArrayList();
//		System.out.println("org---"+orgchart_id);
		String qry = "select form_four_Id,traffic_order_no from form_four_traffic_order order by traffic_order_no";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("form_four_Id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTrafficOrder() {
		List list = new ArrayList();
		String qry = "select form_four_Id,traffic_order_no from form_four_traffic_order order by traffic_order_no";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("traffic_order_no").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public String getDataFromGprs(String date) {
		String data="";
		try{
		List list = new ArrayList();
		String qry = "select count(*) count,round(sum(px_total_amount+convinence_fee),2) amount from bmtcGprs.etim_gprs_ticket060717 where ticket_date = '"+date+"' AND `payment_mode` = 1;";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry).addScalar("amount").addScalar("count");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				data=rs.get("count").toString();
				System.out.println("Data=="+data);
				
			}
		}
//		HibernateUtil.closeSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public String getDataFromAxies(String date) {
		String data="";
		try{
		List list = new ArrayList();
		String qry = "select count(*) count,sum(transaction_amount) amount from bmtcGprs.axis_reconcillation WHERE transaction_date = '"+date+"';";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry).addScalar("amount").addScalar("count");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				data=rs.get("count").toString();
				System.out.println("Data1=="+data);
				
			}
		}
//		HibernateUtil.closeSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
}
