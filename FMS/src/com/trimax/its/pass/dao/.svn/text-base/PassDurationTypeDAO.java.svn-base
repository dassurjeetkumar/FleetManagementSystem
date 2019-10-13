package com.trimax.its.pass.dao;

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
import com.trimax.its.pass.model.PassDurationType;
import com.trimax.its.pass.model.PassPurchaseType;
import com.trimax.its.pass.model.PassSubType;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.util.HibernateUtil;

public class PassDurationTypeDAO {

public int getTotalRecordsForPassDuration(String string, String sdir) {
		
		int cnt = 0;
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="SELECT count(*) count FROM pass_duration_type WHERE deleted_status = '0'";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	public JSONObject getPassDurationList(int total, HttpServletRequest request,String col,String dir) {
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="SELECT pass_duration_id,pass_duration_name,pass_type_id,pass_sub_type_id,pass_start_date,pass_expiry_date,status,remarks FROM pass_duration_type WHERE deleted_status = '0'";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (pass_duration_id like '%" + search_term + "%'";
				// sql += " OR gen_logsheet_id like '%"+search_term+"%'";
				// sql +=
				// " OR form_four.schedule_number_name like '"+search_term+"%'";
				 sql += " OR pass_duration_name like '"+search_term+"%')";
				
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
					.addScalar("pass_duration_id", Hibernate.STRING)
					.addScalar("pass_duration_name", Hibernate.STRING)
					.addScalar("pass_type_id", Hibernate.STRING)
					.addScalar("pass_sub_type_id", Hibernate.STRING)
					.addScalar("pass_start_date", Hibernate.STRING)
					.addScalar("pass_expiry_date", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("remarks", Hibernate.STRING);
					

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				int j = i + 1;
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("pass_duration_id").toString()
						+ "' value='"
						+ rs.get("pass_duration_id").toString()+ "'/>");
				ja.add(rs.get("pass_duration_id").toString());
				ja.add(rs.get("pass_duration_name").toString());
				String pass_type_name=getPassTypeName(rs.get("pass_type_id").toString());
				ja.add(pass_type_name);
				String pass_sub_type_name=getPassSubTypeName(rs.get("pass_sub_type_id").toString());
				ja.add(pass_sub_type_name);
				ja.add(rs.get("pass_start_date").toString());
				ja.add(rs.get("pass_expiry_date").toString());
				ja.add(rs.get("status").toString());
				ja.add(rs.get("remarks").toString());

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
			totalAfterFilter = getTotalRecordsForPassDuration( col,dir);
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
public String getPassTypeName(String id){
		
		Session session=null;
		Transaction tx=null;
		PassType passType=null;
		String pass_name="";
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassType where pass_type_id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		passType=(PassType)list.get(0);
		pass_name=passType.getPass_type_name();
		System.out.println("passType==="+passType.getPass_type_name());
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		
		return pass_name;
	}

public String getPassSubTypeName(String id){
	
	Session session=null;
	Transaction tx=null;
	PassSubType passType=null;
	String pass_sub_name="";
	
	try{
	session=HibernateUtil.getSession("");
	
	String query="from PassSubType where pass_subtype_id="+id+" and status='Y'";
	
	List list=session.createQuery(query).list();
	
	passType=(PassSubType)list.get(0);
	pass_sub_name=passType.getSub_type_name();
	System.out.println("passType==="+passType.getSub_type_name());
	
	}
	catch(Exception e){
		e.printStackTrace();

	}
	
	return pass_sub_name;
}


	
	
	public int addPassDurationType(PassDurationType passdurationtype){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from PassDurationType where pass_duration_name='"+passdurationtype.getPass_duration_name().trim()+"' and deleted_status=0";
				
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		
			
		 i=(Integer)session.save(passdurationtype);
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
	
	
public PassDurationType getPassDurationTypeById(int id){
		
		Session session=null;
		Transaction tx=null;
		PassDurationType passType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassDurationType where pass_duration_id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		passType=(PassDurationType)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return passType;
	}
	
	public int updatePassDurationType(PassDurationType passDuration){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from PassDurationType where pass_duration_name='"+passDuration.getPass_duration_name().trim()+"' and deleted_status=0";			
		
		List<PassDurationType> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getPass_duration_id()==passDuration.getPass_duration_id()){
			
			PassDurationType passDurationTypeNew = (PassDurationType) session.get(PassDurationType.class,passDuration.getPass_duration_id());

			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				passDurationTypeNew.setUpdated_by(Integer.parseInt(userid));	
			}
			
			passDurationTypeNew.setUpdated_date(new Date());
			passDurationTypeNew.setStatus(passDuration.getStatus());
			passDurationTypeNew.setRemarks(passDuration.getRemarks().trim());
			passDurationTypeNew.setPass_duration_name(passDuration.getPass_duration_name().trim());
			
            session.update(passDurationTypeNew);
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
	
	public String deletePassDurationType(int id) {

		Session session=null;
		String s="error";
		String status="error";

		try{
			
		session= HibernateUtil.getSession("");
		 DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"passdurationtype",id);
			System.out.println("&&&&&&&&&&"+status);
		if(status.trim().equalsIgnoreCase("")){
		session.beginTransaction();
		PassDurationType passDurationType=(PassDurationType)session.load(PassDurationType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		passDurationType.setUpdated_date(new java.util.Date());
		passDurationType.setUpdated_by(user.getUserId());
		passDurationType.setDeleted_status(1);
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
