package com.trimax.its.transport.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.model.User;
import com.trimax.its.pass.model.PassDistanceType;
import com.trimax.its.pass.model.PassDurationType;
import com.trimax.its.transport.model.HappyHour;
import com.trimax.its.util.HibernateUtil;

public class HappyHourDao {
	
	public void deleteHappyHr(){

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();			
		session.getTransaction().rollback();		
		session.close();
	}

public int getTotalRecords(String string, String sdir) {
		
		int cnt = 0;
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="SELECT count(*) count FROM happy_hour WHERE deleted_status = '0'";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	public JSONObject getData(int total, HttpServletRequest request,String col,String dir) {
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="SELECT id,name,service_type_name,start_time,end_time,percentage,lumpsum_amount,Effective_start_date," +
					"ifnull(Effective_end_date,'') Effective_end_date FROM happy_hour hh inner join service_type st on st.service_type_id=hh.service_type_id  " +
					"WHERE hh.deleted_status = '0'";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (id like '%" + search_term + "%'";
				// sql += " OR gen_logsheet_id like '%"+search_term+"%'";
				// sql +=
				// " OR form_four.schedule_number_name like '"+search_term+"%'";
				 sql += " OR name like '"+search_term+"%')";
				
			}
			//String col = dbcol[index];
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			} else {
				//sql += " order by generated_date desc";
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.STRING)
					.addScalar("name", Hibernate.STRING)
					.addScalar("service_type_name", Hibernate.STRING)
//					.addScalar("schedule_type_name", Hibernate.STRING)
					.addScalar("start_time", Hibernate.STRING)
					.addScalar("end_time", Hibernate.STRING)
					.addScalar("percentage", Hibernate.STRING)
					.addScalar("lumpsum_amount", Hibernate.STRING)
					.addScalar("Effective_start_date", Hibernate.STRING)
					.addScalar("Effective_end_date", Hibernate.STRING);
					

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				int j = i + 1;
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("id").toString()
						+ "' value='"
						+ rs.get("id").toString()+ "'/>");
				ja.add(rs.get("id").toString());
				ja.add(rs.get("name").toString());
				ja.add(rs.get("service_type_name").toString());
//				ja.add(rs.get("schedule_type_name").toString());
				ja.add(rs.get("start_time").toString());
				ja.add(rs.get("end_time").toString());
				ja.add(rs.get("percentage").toString());
				ja.add(rs.get("lumpsum_amount").toString());
				ja.add(rs.get("Effective_start_date").toString());
				ja.add(rs.get("Effective_end_date").toString());
				

				array.add(ja);

			}
			int cnt = 0;
			result.put("aaData", array);
			// if (search_term1 != null && !search_term1.equals("")) {
			// totalAfterFilter = getTotalRecordsForSeacrch(total, request,col,
			// dir);
			// result.put("aaData", array);
			// result.put("iTotalRecords", totalAfterFilter);
			// result.put("iTotalDisplayRecords",totalAfterFilter);
			// } else {
			totalAfterFilter = getTotalRecords( col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	
	}
	
	
	public List<String> getServicetypeid() {
		Session session = null;
		List list = null;
		String status="ACTIVE";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();
		
			String qry = "select service_type_id from service_type where status='"+status+"' and deleted_status=0 and service_type_name!='NULL' order by service_type_name";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("service_type_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}

	public List<String> getServicetypeName() {
		Session session = null;
		List list = null;
		String status="ACTIVE";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			String qry = "select service_type_name from service_type where status='"+status+"' and deleted_status=0 and service_type_name!='NULL' order by service_type_name";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("service_type_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}
	
	public int addHappyHour(HappyHour happyhour){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from HappyHour where name='"+happyhour.getName().trim()+"' and deleted_status=0";
				
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		
			
		 i=(Integer)session.save(happyhour);
		}else{
			i=-1;
		}
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return i;
	}
	
	
public HappyHour getHappyHourId(int id){
		
		Session session=null;
		Transaction tx=null;
		HappyHour happyhour=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from HappyHour where id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		happyhour=(HappyHour)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return happyhour;
	}
	

public int updateHappyHourType(HappyHour happyhour){
	int i=0;
	Session session=null;
	Transaction tx=null;
	
	try{
	HttpServletRequest request = ServletActionContext.getRequest();
	session=HibernateUtil.getSession("");
	tx=session.beginTransaction();
	
	String qry="from HappyHour where name='"+happyhour.getName().trim()+"' and deleted_status=0";			
	
	List<HappyHour> list=session.createQuery(qry).list();

	
	if(list.size()<=0 || list.get(0).getId()==happyhour.getId()){
		
		HappyHour happyhourNew = (HappyHour) session.get(HappyHour.class,happyhour.getId());

		String userid=request.getSession().getAttribute("userid").toString();
		
		if(userid!=null || userid.equals("")){
			happyhourNew.setUpdated_by(Integer.parseInt(userid));	
		}
		
		happyhourNew.setUpdated_date(new Date());
	  happyhourNew.setLumpsum_amount(happyhour.getLumpsum_amount());
	  happyhourNew.setPercentage(happyhour.getPercentage());
	  happyhourNew.setIncrease_decrease(happyhour.getIncrease_decrease());
	  happyhourNew.setEffective_end_date(happyhour.getEffective_end_date());
	//	happyhourNew.setRemarks(passDistance.getRemarks().trim());
	//	happyhourNew.setName(happyhour.getName().trim());
		
        session.update(happyhourNew);
        i=1;
	}else{
		i=-1;
	}
	tx.commit();
	}
	catch(Exception e){
		e.printStackTrace();
		tx.rollback();
		i=0;
	}
	finally{
		 if(session.isOpen()){
			 session.close();
		 }
		}
	return i;
}

public String deleteHappyHourType(int id) {

	Session session=null;
	String s="error";
	String status="error";

	try{
		
	session= HibernateUtil.getSession("");
	 DependencyChecker dc=new DependencyChecker();
		status=dc.checkDependentEntities(session,"happytype",id);
		System.out.println("&&&&&&&&&&"+status);
	if(status.trim().equalsIgnoreCase("")){
	session.beginTransaction();
	HappyHour happy=(HappyHour)session.load(HappyHour.class,new Integer(id));

	HttpServletRequest request=ServletActionContext.getRequest();
	User user = (User) request.getSession().getAttribute("user");
	happy.setUpdated_date(new java.util.Date());
	happy.setUpdated_by(user.getUserId());
	happy.setDeleted_status(1);
	s="success";
	session.getTransaction().commit();
	}
	}
	catch(Exception ex){
		status="error:";
		ex.printStackTrace();
	}
	finally{
		 if(session.isOpen()){
			 session.close();
		 }
	}

	return status;
}

}
