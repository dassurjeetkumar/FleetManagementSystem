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
import com.trimax.its.pass.model.PassIssuerType;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.util.HibernateUtil;

public class PassIssuerTypeDAO {

	public int getTotalRecordsForPassIssuer(String string, String sdir) {
		
		int cnt = 0;
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="SELECT count(*) count FROM pass_issuer_type WHERE deleted_status = '0'";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	public JSONObject getPassIssuerList(int total, HttpServletRequest request,String col,String dir) {
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="SELECT pass_issuer_id,pass_issuer_name,status,remarks FROM pass_issuer_type WHERE deleted_status = '0'";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (pass_issuer_id like '%" + search_term + "%'";
				// sql += " OR gen_logsheet_id like '%"+search_term+"%'";
				// sql +=
				// " OR form_four.schedule_number_name like '"+search_term+"%'";
				 sql += " OR pass_issuer_name like '"+search_term+"%')";
				
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
					.addScalar("pass_issuer_id", Hibernate.STRING)
					.addScalar("pass_issuer_name", Hibernate.STRING)
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
						+ rs.get("pass_issuer_id").toString()
						+ "' value='"
						+ rs.get("pass_issuer_id").toString()+ "'/>");
				ja.add(rs.get("pass_issuer_id").toString());
				ja.add(rs.get("pass_issuer_name").toString());
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
			totalAfterFilter = getTotalRecordsForPassIssuer( col,dir);
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
	
	
	public int addPassIssuerType(PassIssuerType passissuerType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from PassIssuerType where pass_issuer_name='"+passissuerType.getPass_issuer_name().trim()+"' and deleted_status=0";
				
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		
			
		 i=(Integer)session.save(passissuerType);
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
	public PassIssuerType getPassIssuerTypeById(int id){
		
		Session session=null;
		Transaction tx=null;
		PassIssuerType passType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassIssuerType where pass_issuer_id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		passType=(PassIssuerType)list.get(0);
		
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
	
	public int updatePassIssuerType(PassIssuerType passIssuer){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from PassIssuerType where pass_issuer_name='"+passIssuer.getPass_issuer_name().trim()+"' and deleted_status=0";			
		
		List<PassIssuerType> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getPass_issuer_id()==passIssuer.getPass_issuer_id()){
			
			PassIssuerType passIssuerTypeNew = (PassIssuerType) session.get(PassIssuerType.class,passIssuer.getPass_issuer_id());

			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				passIssuerTypeNew.setUpdated_by(Integer.parseInt(userid));	
			}
			
			passIssuerTypeNew.setUpdated_date(new Date());
			passIssuerTypeNew.setStatus(passIssuer.getStatus());
			passIssuerTypeNew.setRemarks(passIssuer.getRemarks().trim());
			passIssuerTypeNew.setPass_issuer_name(passIssuer.getPass_issuer_name().trim());
			
            session.update(passIssuerTypeNew);
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
	
	public String deletePassIssuerType(int id) {

		Session session=null;
		String s="error";
		String status="error";

		try{
			
		session= HibernateUtil.getSession("");
		 DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"passissuertype",id);
			System.out.println("&&&&&&&&&&"+status);
		if(status.trim().equalsIgnoreCase("")){
		session.beginTransaction();
		PassIssuerType passIssuerType=(PassIssuerType)session.load(PassIssuerType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		passIssuerType.setUpdated_date(new java.util.Date());
		passIssuerType.setUpdated_by(user.getUserId());
		passIssuerType.setDeleted_status(1);
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
