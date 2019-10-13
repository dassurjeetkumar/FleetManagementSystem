package com.trimax.its.pass.dao;

import java.util.Date;
import java.util.LinkedHashMap;
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
import com.trimax.its.pass.model.PassIssuerCenter;
import com.trimax.its.pass.model.PassPurchaseType;
import com.trimax.its.pass.model.PassSubType;
import com.trimax.its.util.HibernateUtil;

public class PassSubTypeDAO {
	
public int getTotalRecordsForPassSubType(String string, String sdir) {
		
		int cnt = 0;
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="SELECT count(*) count FROM pass_sub_type inner join passtype on " +
					" passtype.pass_type_id=pass_sub_type.pass_type_id WHERE pass_sub_type.status = 'Y'";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	public JSONObject getPassSubTypeList(int total, HttpServletRequest request,String col,String dir) {
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="select pass_subtype_id,sub_type_name,remarks,pass_sub_type.status,pass_type_name FROM pass_sub_type inner join passtype on " +
					" passtype.pass_type_id=pass_sub_type.pass_type_id WHERE pass_sub_type.status = 'Y'";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (pass_subtype_id like '%" + search_term + "%'";
				// sql += " OR gen_logsheet_id like '%"+search_term+"%'";
				// sql +=
				// " OR form_four.schedule_number_name like '"+search_term+"%'";
				 sql += " OR sub_type_name like '"+search_term+"%')";
				
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
					.addScalar("pass_subtype_id", Hibernate.STRING)
					.addScalar("sub_type_name", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("pass_type_name", Hibernate.STRING)
					.addScalar("remarks", Hibernate.STRING);
					

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				int j = i + 1;
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("pass_subtype_id").toString()
						+ "' value='"
						+ rs.get("pass_subtype_id").toString()+ "'/>");
				ja.add(rs.get("pass_subtype_id").toString());
				ja.add(rs.get("sub_type_name").toString());
				ja.add(rs.get("pass_type_name").toString());
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
			totalAfterFilter = getTotalRecordsForPassSubType( col,dir);
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
	
	public int addPassSubtype(PassSubType passsubtype){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from PassSubType where sub_type_name='"+passsubtype.getSub_type_name().trim()+"' and  status = 'Y'";
				
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		
			
		 i=(Integer)session.save(passsubtype);
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
	
	
public PassSubType getPassSubTypeById(int id){
		
		Session session=null;
		Transaction tx=null;
		PassSubType passType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassSubType where pass_subtype_id="+id+" and status='Y'";
		
		List list=session.createQuery(query).list();
		
		passType=(PassSubType)list.get(0);
		
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
	
	public int updatePassSubType(PassSubType passsubtype){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from PassSubType where sub_type_name='"+passsubtype.getSub_type_name().trim()+"' and status='Y'";			
		
		List<PassSubType> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getPass_subtype_id()==passsubtype.getPass_subtype_id()){
			
			PassSubType passSubtypeNew = (PassSubType) session.get(PassSubType.class,passsubtype.getPass_subtype_id());

			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				passSubtypeNew.setUpdated_by(Integer.parseInt(userid));	
			}
			
			passSubtypeNew.setUpdated_date(new Date());
			passSubtypeNew.setStatus(passsubtype.getStatus());
			passSubtypeNew.setRemarks(passsubtype.getRemarks().trim());
			passSubtypeNew.setSub_type_name(passsubtype.getSub_type_name().trim());
			passSubtypeNew.setPass_type_id(passsubtype.getPass_type_id());
			
            session.update(passSubtypeNew);
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
	
	public String deletePassSubType(int id) {

		Session session=null;
		String s="error";
		String status="error";

		try{
			
		session= HibernateUtil.getSession("");
		 DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"passsubtype",id);
			System.out.println("&&&&&&&&&&"+status);
		if(status.trim().equalsIgnoreCase("")){
		session.beginTransaction();
		PassSubType passsubtype=(PassSubType)session.load(PassSubType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		passsubtype.setUpdated_date(new java.util.Date());
		passsubtype.setUpdated_by(user.getUserId());
		passsubtype.setStatus("N");
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
	
	
	public Map<Integer, String> getPassTypeData() {
		 Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	       // int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("org_chart_id").toString());

	        try {
	            Query query = session
	                    .createSQLQuery("SELECT pass_type_id,pass_type_name FROM passtype where deleted_status=0 and status='Active'").addScalar("pass_type_id", Hibernate.INTEGER).addScalar("pass_type_name", Hibernate.STRING);
	            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	            List<Map<String, Object>> aliasToValueMapList = query.list();
	            resultMap.put(0, "Select");
	            System.out.println("Size of usertype type List : " + aliasToValueMapList.size());
	            for (int i = 0; i < aliasToValueMapList.size(); i++) {
	                Map<String, Object> rs = aliasToValueMapList.get(i);
	                resultMap.put(Integer.parseInt(rs.get("pass_type_id").toString()), rs.get("pass_type_name").toString());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null) {
	                session.close();
	            }

	        }
	        return resultMap;
	}
	

}
