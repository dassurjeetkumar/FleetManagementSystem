package com.trimax.its.report.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.pass.model.PassRate;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.report.model.ReportDetail;
import com.trimax.its.report.model.ReportFilter;
import com.trimax.its.report.model.ReportHeader;
import com.trimax.its.report.model.ReportRelation;
import com.trimax.its.util.HibernateUtil;

public class ReportDao {

	public Map<String,String> getTableName(){
		Map<String, String> resultMap = new HashMap<String, String>();
		Session session=null;
		
		try{
		String sql="select TABLE_NAME from information_schema.tables where table_schema='its'";
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		List<Map<String, Object>> aliasToValueMapList = query.list();
		
		if(aliasToValueMapList.size() > 0) {
		 for(int i = 0; i < aliasToValueMapList.size(); i++) {
			 Map<String, Object> rs = aliasToValueMapList.get(i);
				
			 if (rs != null) {
				resultMap.put(rs.get("TABLE_NAME").toString(),rs.get("TABLE_NAME").toString());
			 }
		 }
		}
		}
		catch(Exception e){}
		finally{
			if(session.isOpen()){
				session.close();	
			}	
		}
		
		return resultMap;
	}
	
	public List<ReportDetail>  getReportDetail(int id){
		List<ReportDetail> list=new ArrayList();
		Session session=null;
			
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 Query query = session.createQuery("from ReportDetail where reportHeaderId="+id +" and status='Active' order by sequence");
		 list = query.list();

		}
		catch(Exception e){}
		finally{
			if(session.isOpen()){
				session.close();	
			}	
		}
		
		return list;
	}
	public List<ReportDetail>  getReportDetailSelectColumns(int id){
		List<ReportDetail> list=new ArrayList();
		Session session=null;
			
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 Query query = session.createQuery("from ReportDetail where reportHeaderId="+id +" and status='Active' order by alias");
		 list = query.list();

		}
		catch(Exception e){}
		finally{
			if(session.isOpen()){
				session.close();	
			}	
		}
		 
		return list;
	}
	public List<ReportDetail> getReportDetailSortSearchGrp(int id,String key){
		List<ReportDetail> list=new ArrayList();
		Session session=null;
			
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 Query query = session.createQuery("from ReportDetail where status='Active' and reportHeaderId="+id+" and "+key+" order by alias");
		 list = query.list();

		}
		catch(Exception e){}
		finally{
			if(session.isOpen()){
				session.close();	
			}	
		}
		
		return list;
	}
	
	public List getReportHeaderById(int id){
		List<ReportHeader> list=new ArrayList();
		Session session=null;
			
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 Query query = session.createQuery("from ReportHeader where status='Active' and reportHeaderId="+id);
		 list = query.list();

		}
		catch(Exception e){}
		finally{
			if(session.isOpen()){
				session.close();	
			}
		}
		
		return list;
	}
	
	public int getReportHeaderByCode(String id){
		List<ReportHeader> list=new ArrayList();
		Session session=null;
		int rhId=0;	
		
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 Query query = session.createQuery("from ReportHeader where status='Active' and reportCode='"+id+"'");
		 list = query.list();
		 rhId=list.get(0).getReportHeaderId();
		}
		catch(Exception e){}
		finally{
			if(session.isOpen()){
				session.close();	
			}
		}
		
		return rhId;
	}
	
	public List getReportFilterById(int id){
		List<ReportFilter> list=new ArrayList();
		Session session=null;
			
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 Query query = session.createQuery("from ReportFilter where status='Active' and reportHeaderId="+id);
		 list = query.list();

		}
		catch(Exception e){e.printStackTrace();}
		finally{
			if(session.isOpen()){
				session.close();	
			}
		}
		
		return list;
	}
	
	public List getReportRelationById(int id){
		List<ReportRelation> list=new ArrayList();
		Session session=null;
			
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 Query query = session.createQuery("from ReportRelation where  status='Active' and  reportHeaderId="+id);
		 list = query.list();

		}
		catch(Exception e){e.printStackTrace();}
		finally{
			if(session.isOpen()){
				session.close();	
			}
		}
		
		return list;
	}
	
public int getTotalRecords(HttpServletRequest request, String col, String dir) {
		
		int cnt=0;
		try {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(ReportHeader.class);
		criteria.addOrder(Order.asc("reportTitle"));
		//criteria.add(Restrictions.eq("status", "Active"))
		criteria.add(Restrictions.eq("status", "Active"));
		
		if(!col.equals("")){
			if(dir.equals("asc")){
			    criteria.addOrder(Order.asc(col));
			}else{
				criteria.addOrder(Order.desc(col));	
			}
		}
		
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch");

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("reportTitle", "%"
					+ search_term + "%"));
		
			criteria.add(conditionGroup);
		}
		
		List<PassRate> list = criteria.list();
		cnt = list.size();
		}
		catch(Exception e){
			
		}
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir) {
		JSONObject result = new JSONObject();
		Session session=null;
		try {

			int totalAfterFilter = total;

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(ReportHeader.class);
			criteria.addOrder(Order.asc("reportTitle"));
			criteria.add(Restrictions.eq("status", "Active"));
			
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("reportTitle"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("reportTitle", "%"
						+ search_term + "%"));
			
				criteria.add(conditionGroup);
			}
			
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<ReportHeader> list = criteria.list();

			JSONArray array = new JSONArray();

			Common common=new Common();
			
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getReportHeaderId()
						+ "' value='"+ list.get(i).getReportCode() + "'/>");
				
				//ja.add(list.get(i).getReportHeaderId());
				ja.add(list.get(i).getReportTitle() != null ? list.get(i).getReportTitle().toString() : "");

				array.add(ja);

			}

			totalAfterFilter = getTotalRecords(request,col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if(session.isOpen()){
			session.close();	
		}
		return result;
	}
}