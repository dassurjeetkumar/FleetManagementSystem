package com.trimax.its.fare.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.fare.model.RevenueType;
import com.trimax.its.fare.model.PassengerType;
import com.trimax.its.model.User;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.util.HibernateUtil;

public class RevenueTypeDao {
	public int addRevenueType(RevenueType revenueType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();

		try{

		int masterId=-1;
		
		//check for duplicate
		masterId=duplicateInsertByQuery(revenueType);
			
		if(masterId!=-1){
			return (-2);
		}

		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		RevenueType revenueTypeNew=revenueType;
		
		revenueTypeNew.setRevenueTypeName(revenueType.getRevenueTypeName().trim());
		revenueTypeNew.setNotes(revenueType.getNotes().trim());
		revenueTypeNew.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		revenueTypeNew.setCreatedDate(new Date());
		revenueTypeNew.setUpdatedBy(0);
		revenueTypeNew.setUpdatedDate(null);
		revenueTypeNew.setDeletedStatus(0);
		revenueTypeNew.setStatus(revenueType.getStatus());
		
		i=(Integer)session.save(revenueTypeNew);
 
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			i=-1;
		}
		finally{
			 if(session!=null && session.isOpen()){
				 session.close();
			 }
		}
		
		return i;
	}
	
	public int updateRevenueType(RevenueType revenueType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		try{
			//check for duplicate
			int masterId=-1;			
		
		    masterId=duplicateUpdateByQuery(revenueType);
				
			if(masterId!=-1){
				return (-2);
			}

			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();
			
			RevenueType revenueTypeNew=(RevenueType) session.get(RevenueType.class,revenueType.getId());
	
			revenueTypeNew.setRevenueTypeName(revenueType.getRevenueTypeName().trim());
			revenueTypeNew.setNotes(revenueType.getNotes().trim());revenueTypeNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			revenueTypeNew.setUpdatedDate(new Date());
			revenueTypeNew.setDeletedStatus(0);
			revenueTypeNew.setStatus(revenueType.getStatus());
			
			session.update(revenueTypeNew);
			i=1;
			
			tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			i=-1;
		}
		finally{
			 if(session!=null && session.isOpen()){
				 session.close();
			 }
		}
		return i;
	}
	
	
	
	public String deleteRevenueType(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		RevenueType revenueTypeNew=(RevenueType)session.get(RevenueType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		revenueTypeNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		revenueTypeNew.setUpdatedDate(new Date());
		revenueTypeNew.setDeletedStatus(1);
		
		session.getTransaction().commit();
		s="success";
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			 if(session!=null && session.isOpen()){
				 session.close();
			 }
		}

		return s;
	}
	
	public int duplicateUpdateByQuery(RevenueType revenueType){
		int masterId=-1;

		try{		
		String qry="from RevenueType where revenueTypeName='"+revenueType.getRevenueTypeName().trim()+"'"
				+" and id!="+revenueType.getId()
				+" and deletedStatus=0";
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<RevenueType> list=query.list();
				 
				 if(list.size()>0){
				  masterId=list.get(0).getId();
				 }
				}
				catch(Exception e){
				e.printStackTrace();	
				}
				finally{
					if(session!=null && session.isOpen()){
						session.close();
					}
				}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
				
		return masterId;
	}
	
	public int duplicateInsertByQuery(RevenueType revenueType){
		int masterId=-1;

		try{
			String qry="from RevenueType where revenueTypeName='"+revenueType.getRevenueTypeName().trim()+"'"
				+" and deletedStatus=0";
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<RevenueType> list=query.list();
				 
				 if(list.size()>0){
				 masterId=list.get(0).getId();
				 }
				}
				catch(Exception e){
				e.printStackTrace();	
				}
				finally{
					if(session!=null && session.isOpen()){
						session.close();
					}
				}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return masterId;
	}
	
	public RevenueType getRevenueTypeById(int id){
		
		Session session=null;
		Transaction tx=null;
		RevenueType revenueType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from RevenueType where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()>0){
		revenueType=(RevenueType)list.get(0);
		}
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return revenueType;
	}
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		Session session=null;
		int cnt=0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(RevenueType.class);

			//criteria.add(Restrictions.ne("deletedStatus", 1));
			
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(RevenueType.class);
			}else{
				 criteria = session.createCriteria(RevenueType.class);
			     criteria.add(Restrictions.ne("deletedStatus", 1));
			}

			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("id"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("revenueTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));

				criteria.add(conditionGroup);
			}
		
			List<RevenueType> list = criteria.list();
			cnt = list.size();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
			 if(session!=null && session.isOpen()){
				 session.close();
			 }
			}
			return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Session session=null;

		try {

			int totalAfterFilter = total;

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(RevenueType.class);

			//criteria.add(Restrictions.ne("deletedStatus", 1));
			
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(RevenueType.class);
			}else{
				 criteria = session.createCriteria(RevenueType.class);
			     criteria.add(Restrictions.ne("deletedStatus", 1));
			}

			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("id"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("revenueTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));

				criteria.add(conditionGroup);
			}
			
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<RevenueType> list = criteria.list();

			JSONArray array = new JSONArray();

			Common common=new Common();
			
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()
						+ "' value='"+ list.get(i).getId() + "'/>");
				
				ja.add(list.get(i).getId());//fare chart master id
				ja.add(list.get(i).getRevenueTypeName() != null ? list.get(i).getRevenueTypeName().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getId()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getId()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}

				array.add(ja);

			}

			totalAfterFilter = total;//getTotalRecords(request,col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			 if(session!=null && session.isOpen()){
				 session.close();
			 }
			}
		return result;
	}
}
