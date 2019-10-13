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
import com.trimax.its.pass.model.PassDistanceType;
import com.trimax.its.pass.model.PassPurchaseType;
import com.trimax.its.util.HibernateUtil;

public class PassPurchaseTypeDAO {
	
public int getTotalRecordsForPassPurchase(String string, String sdir) {
		
		int cnt = 0;
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="SELECT count(*) count FROM pass_purchase_type WHERE deleted_status = '0'";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	public JSONObject getPassPurchaseList(int total, HttpServletRequest request,String col,String dir) {
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="SELECT pass_purchase_id,pass_purchase_name,status,remarks FROM pass_purchase_type WHERE deleted_status = '0'";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (pass_purchase_id like '%" + search_term + "%'";
				// sql += " OR gen_logsheet_id like '%"+search_term+"%'";
				// sql +=
				// " OR form_four.schedule_number_name like '"+search_term+"%'";
				 sql += " OR pass_purchase_name like '"+search_term+"%')";
				
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
					.addScalar("pass_purchase_id", Hibernate.STRING)
					.addScalar("pass_purchase_name", Hibernate.STRING)
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
						+ rs.get("pass_purchase_id").toString()
						+ "' value='"
						+ rs.get("pass_purchase_id").toString()+ "'/>");
				ja.add(rs.get("pass_purchase_id").toString());
				ja.add(rs.get("pass_purchase_name").toString());
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
			totalAfterFilter = getTotalRecordsForPassPurchase( col,dir);
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
	
	public int addPassPurchaseType(PassPurchaseType passpurchasetype){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from PassPurchaseType where pass_purchase_name='"+passpurchasetype.getPass_purchase_name().trim()+"' and deleted_status=0";
				
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		
			
		 i=(Integer)session.save(passpurchasetype);
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
	
	
public PassPurchaseType getPassPurchaseTypeById(int id){
		
		Session session=null;
		Transaction tx=null;
		PassPurchaseType passType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassPurchaseType where pass_purchase_id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		passType=(PassPurchaseType)list.get(0);
		
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
	
	public int updatePassPurchaseType(PassPurchaseType passPurchase){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from PassPurchaseType where pass_purchase_name='"+passPurchase.getPass_purchase_name().trim()+"' and deleted_status=0";			
		
		List<PassPurchaseType> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getPass_purchase_id()==passPurchase.getPass_purchase_id()){
			
			PassPurchaseType passPurchaseTypeNew = (PassPurchaseType) session.get(PassPurchaseType.class,passPurchase.getPass_purchase_id());

			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				passPurchaseTypeNew.setUpdated_by(Integer.parseInt(userid));	
			}
			
			passPurchaseTypeNew.setUpdated_date(new Date());
			passPurchaseTypeNew.setStatus(passPurchase.getStatus());
			passPurchaseTypeNew.setRemarks(passPurchase.getRemarks().trim());
			passPurchaseTypeNew.setPass_purchase_name(passPurchase.getPass_purchase_name().trim());
			
            session.update(passPurchaseTypeNew);
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
	
	public String deletePassPurchaseType(int id) {

		Session session=null;
		String s="error";
		String status="error";

		try{
			
		session= HibernateUtil.getSession("");
		 DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"passdistancetype",id);
			System.out.println("&&&&&&&&&&"+status);
		if(status.trim().equalsIgnoreCase("")){
		session.beginTransaction();
		PassPurchaseType passPurchaseType=(PassPurchaseType)session.load(PassPurchaseType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		passPurchaseType.setUpdated_date(new java.util.Date());
		passPurchaseType.setUpdated_by(user.getUserId());
		passPurchaseType.setDeleted_status(1);
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
