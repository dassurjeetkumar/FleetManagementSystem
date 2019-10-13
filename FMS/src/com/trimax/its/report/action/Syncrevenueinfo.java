package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;

public class Syncrevenueinfo {

	
	public String execute() {
	
		return "success";
	}
	public String getdata(){

		

		JSONObject result = new JSONObject();
		Session session = null;
        BigDecimal total_transation_amount=BigDecimal.ZERO;
        BigInteger waybilltotal=BigInteger.ZERO;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		Date d=new Date();
		String today=sm.format(d);
		String date=req.getParameter("date");
 		Common common = new Common();
		 String date1=common.getDateFromPicker(date);
		 
			//System.out.println(HibernateUtilVtu.admin+"====================");
/*			if(HibernateUtilVtu.admin==null) {
				HibernateUtilVtu.getSession("").close();
				HibernateUtilVtu.admin=null;
			session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");

			}else {*/
			
		 HibernateUtilVtu.rebuildSessionFactory();
				session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");

		//	}
			
		String sql="";
		//if(today.equals(date1)) {
/*	sql	="select org_name,max_date,ifnull(b.collection_amt,0.00)collection_amt ,ifnull(b.wayb_count,0)wayb_count  " + 
			"from updated_depotdate ud " + 
			"left join (select count(distinct WAYBILL_NO) wayb_count,sum(ETIM_Coll_Amt) collection_amt,depot_id from waybill_trip_details where DUTY_DT ='"+today+"' group by  depot_id) b on b.depot_id=ud.depot_id " + 
			"inner join org_chart oc on oc.org_chart_id=ud.depot_id order by org_name";
		}else {
		sql="select org_name ,ifnull(b.collection_amt,0.00)collection_amt,ifnull(b.DUTY_DT,'')DUTY_DT,ifnull(b.wayb_count,0)wayb_count  from org_chart oc " + 
				"left join (select count(distinct WAYBILL_NO) wayb_count,sum(ETIM_Coll_Amt) collection_amt,depot_id,DUTY_DT from waybill_trip_details where DUTY_DT ='"+date1+"' group by  depot_id) b on b.depot_id=oc.org_chart_id " + 
				"where  deleted_status=0 and org_name like '%depot-%' and org_name !='Depot-Test' group by org_name order by org_name";
		}*/
			
			if(today.equals(date1)) {
			sql	="select org_name,date(max_date)max_date,ifnull(b.collection_amt,0.00)collection_amt ,ifnull(b.wayb_count,0)wayb_count  from updated_depotdate ud left join  " + 
					"(select depot_id,DUTY_DT,DEPOT_CD,sum(ETIM_Coll_Amt)collection_amt,count(WAYBILL_NO) wayb_count from ( " + 
					"SELECT  WAYBILL_NO,`ETIM_Coll_Amt`,depot_id,DUTY_DT,DEPOT_CD " + 
					"FROM `waybill_trip_details` " + 
					"WHERE `DUTY_DT` = '"+today+"'  and ETIM_Coll_Amt !=0 " + 
					"group by WAYBILL_NO) a group by depot_id " + 
					") b on b.depot_id=ud.depot_id inner join org_chart oc on oc.org_chart_id=ud.depot_id order by org_name";
				}else {
				sql="select org_name ,ifnull(b.collection_amt,0.00)collection_amt,ifnull(b.DUTY_DT,'')DUTY_DT,ifnull(b.wayb_count,0)wayb_count   " + 
						"from org_chart oc left join ( " + 
						"select depot_id,DUTY_DT,DEPOT_CD,sum(ETIM_Coll_Amt)collection_amt,count(WAYBILL_NO) wayb_count from ( " + 
						"SELECT  WAYBILL_NO,`ETIM_Coll_Amt`,depot_id,DUTY_DT,DEPOT_CD " + 
						"FROM `waybill_trip_details` " + 
						"WHERE `DUTY_DT` = '"+date1+"'  and ETIM_Coll_Amt !=0 " + 
						"group by WAYBILL_NO) a group by depot_id   " + 
						") b on b.depot_id=oc.org_chart_id where  deleted_status=0 and org_name like '%depot-%' and org_name !='Depot-Test' group by org_name order by org_name";
				}
		
		
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();
	
	   
	    
	      JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
			if(today.equals(date1)) {
				ja.add(list.get("org_name").toString().replace(".0",""));
				ja.add(list.get("max_date").toString());
			}else {
		ja.add(list.get("org_name").toString());
	ja.add(list.get("DUTY_DT").toString());
			}
	ja.add(list.get("collection_amt").toString());
	 total_transation_amount = total_transation_amount.add((BigDecimal) list.get("collection_amt"));
	 ja.add(list.get("wayb_count").toString());
	 waybilltotal = waybilltotal.add((BigInteger) list.get("wayb_count"));


			array.add(ja);
			
				
				 }
        	JSONArray ja = new JSONArray();
ja.add("Total");ja.add("");ja.add("");ja.add(total_transation_amount);ja.add(waybilltotal);
array.add(ja);

	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			HibernateUtilVtu.admin.close();
		}
		return null;
		
	
		
	
	}
	

}
