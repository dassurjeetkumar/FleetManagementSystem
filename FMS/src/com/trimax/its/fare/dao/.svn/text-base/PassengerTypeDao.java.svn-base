package com.trimax.its.fare.dao;

import java.util.Date;
import java.util.List;

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
import com.trimax.its.fare.model.PassengerType;
import com.trimax.its.model.User;
import com.trimax.its.util.HibernateUtil;

public class PassengerTypeDao {
	public int addPassenger(PassengerType passengerType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Common common=new Common();
		
		try{

		int masterId=-1;
		
		//check for duplicate
		masterId=duplicateInsertByQuery(passengerType);
			
		if(masterId!=-1){
			return (-2);
		}

		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		PassengerType passengerTypeNew=passengerType;
		
		passengerTypeNew.setPassengerTypeName(passengerType.getPassengerTypeName().trim());
		passengerTypeNew.setEffectiveStartDate(common.getDateTimeFromPicker2(passengerType.getEffectiveStartDate()));
		passengerTypeNew.setEffectiveEndDate(common.getDateTimeFromPicker2(passengerType.getEffectiveEndDate()));		
		passengerTypeNew.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passengerTypeNew.setCreatedDate(new Date());
		passengerTypeNew.setUpdatedBy(0);
		passengerTypeNew.setUpdatedDate(null);
		passengerTypeNew.setDeletedStatus(0);
		
		i=(Integer)session.save(passengerTypeNew);

		 
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
	
	public int updatePassenger(PassengerType passengerType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Common common=new Common();
		
		try{
			//check for duplicate
			int masterId=-1;			
		
		    masterId=duplicateUpdateByQuery(passengerType);
				
			if(masterId!=-1){
				return (-2);
			}


			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();
			
			PassengerType passengerTypeNew=(PassengerType) session.get(PassengerType.class,passengerType.getId());
			
			passengerTypeNew.setPassengerTypeName(passengerType.getPassengerTypeName().trim());
			passengerTypeNew.setEffectiveStartDate(common.getDateTimeFromPicker2(passengerType.getEffectiveStartDate()));
			passengerTypeNew.setEffectiveEndDate(common.getDateTimeFromPicker2(passengerType.getEffectiveEndDate()));	
			passengerTypeNew.setStatus(passengerType.getStatus());
			passengerTypeNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			passengerTypeNew.setUpdatedDate(new Date());
			passengerTypeNew.setDeletedStatus(0);
			
			session.update(passengerTypeNew);
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
	
	
	
	public String deletePassengerType(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		PassengerType passengerTypeNew=(PassengerType)session.get(PassengerType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		passengerTypeNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passengerTypeNew.setUpdatedDate(new Date());
		passengerTypeNew.setDeletedStatus(1);

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
	
	public int duplicateUpdateByQuery(PassengerType passengerType){
		int masterId=-1;
		Common common=new Common();
		try{

		String strDate=common.getDateTimeFromPicker2(passengerType.getEffectiveStartDate());
		String endDate=common.getDateTimeFromPicker2(passengerType.getEffectiveEndDate());
		
		String qry="from PassengerType where passengerTypeName='"+passengerType.getPassengerTypeName().trim()+"'"
				+" and id!="+passengerType.getId()
				+" and deletedStatus=0 and ((('"+strDate+"' between effectiveStartDate and  effectiveEndDate) "
				+" or '"+strDate+"'=effectiveEndDate or '"+strDate+"'=effectiveStartDate )";
				
				qry+=" or ('"+endDate+"' between effectiveStartDate and ifnull(effectiveEndDate,effectiveStartDate)"
			    	+" or (('"+strDate+"' <= effectiveStartDate and '"+endDate+"' between effectiveStartDate and ifnull(effectiveEndDate,effectiveStartDate) )"	
			         +" or ('"+strDate+"'<=effectiveStartDate and '"+endDate+"' >= ifnull(effectiveEndDate,effectiveStartDate))))";
				
				qry+=")";
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<PassengerType> list=query.list();
				 
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
	
	public int duplicateInsertByQuery(PassengerType passengerType){
		int masterId=-1;
		Common common=new Common();
		try{
		    String strDate=common.getDateTimeFromPicker2(passengerType.getEffectiveStartDate());
			String endDate=common.getDateTimeFromPicker2(passengerType.getEffectiveEndDate());
			
			String qry="from PassengerType where passengerTypeName='"+passengerType.getPassengerTypeName().trim()+"'"
				+" and deletedStatus=0 and ((('"+strDate+"' between effectiveStartDate and  effectiveEndDate) "
				+" or '"+strDate+"'=effectiveEndDate or '"+strDate+"'=effectiveStartDate )";
				
				qry+=" or ('"+endDate+"' between effectiveStartDate and ifnull(effectiveEndDate,effectiveStartDate)"
			    	+" or (('"+strDate+"' <= effectiveStartDate and '"+endDate+"' between effectiveStartDate and ifnull(effectiveEndDate,effectiveStartDate) )"	
			         +" or ('"+strDate+"'<=effectiveStartDate and '"+endDate+"' >= ifnull(effectiveEndDate,effectiveStartDate))))";
				
				qry+=")";
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<PassengerType> list=query.list();
				 
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
	
	public PassengerType getPassengerTypeById(int id){
		
		Session session=null;
		Transaction tx=null;
		PassengerType passengerType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassengerType where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()>0){
		passengerType=(PassengerType)list.get(0);
		}
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return passengerType;
	}
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		Session session=null;
		int cnt=0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(PassengerType.class);

			//criteria.add(Restrictions.ne("deletedStatus", 1));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(PassengerType.class);
			}else{
				 criteria = session.createCriteria(PassengerType.class);
			     criteria.add(Restrictions.eq("deletedStatus", 0));
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

				conditionGroup.add(Restrictions.like("passengerTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));
			
				criteria.add(conditionGroup);
			}
		
			List<PassengerType> list = criteria.list();
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
			//Criteria criteria = session.createCriteria(PassengerType.class);

			//criteria.add(Restrictions.ne("deletedStatus", 1));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(PassengerType.class);
			}else{
				 criteria = session.createCriteria(PassengerType.class);
			     criteria.add(Restrictions.eq("deletedStatus", 0));
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

				conditionGroup.add(Restrictions.like("passengerTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));
			
				criteria.add(conditionGroup);
			}
			
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<PassengerType> list = criteria.list();

			JSONArray array = new JSONArray();

			Common common=new Common();
			
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()
						+ "' value='"+ list.get(i).getId() + "'/>");
				
				ja.add(list.get(i).getId());//fare chart master id
				ja.add(list.get(i).getPassengerTypeName() != null ? list.get(i).getPassengerTypeName().toString().replaceAll(" ","&nbsp;") : "");
				

				String startDate = list.get(i).getEffectiveStartDate() != null ? common.getDateFromDateTime_(list
						.get(i).getEffectiveStartDate()): "";
				ja.add(startDate);

				ja.add(list.get(i).getEffectiveEndDate() != null ? common.getDateFromDateTime_(list.get(i)
						.getEffectiveEndDate()) : "");

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
