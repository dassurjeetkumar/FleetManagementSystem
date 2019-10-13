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
import com.trimax.its.fare.model.Concession;
import com.trimax.its.fare.model.PassengerType;
import com.trimax.its.model.User;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.util.HibernateUtil;

public class ConcessionDao {
	public int addConcession(Concession concession){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Common common=new Common();
		
		try{

		int masterId=-1;
		
		//check for duplicate
		masterId=duplicateInsertByQuery(concession);
			
		if(masterId!=-1){
			return (-2);
		}

		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		Concession concessionNew=concession;
		
		concessionNew.setConcessionName(concession.getConcessionName().trim());
		concessionNew.setNote(concession.getNote().trim());
		concessionNew.setEffectiveStartDate(common.getDateTimeFromPicker2(concession.getEffectiveStartDate()));
		concessionNew.setEffectiveEndDate(common.getDateTimeFromPicker2(concession.getEffectiveEndDate()));		
		concessionNew.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		concessionNew.setCreatedDate(new Date());
		concessionNew.setUpdatedBy(0);
		concessionNew.setUpdatedDate(null);
		concessionNew.setDeletedStatus(0);
		concessionNew.setStatus(concession.getStatus());
		
		i=(Integer)session.save(concessionNew);
 
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
	
	public int updateConcession(Concession concession){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Common common=new Common();
		
		try{
			//check for duplicate
			int masterId=-1;			
		
		    masterId=duplicateUpdateByQuery(concession);
				
			if(masterId!=-1){
				return (-2);
			}

			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();
			
			Concession concessionNew=(Concession) session.get(Concession.class,concession.getId());
	
			concessionNew.setConcessionName(concession.getConcessionName().trim());
			concessionNew.setPassengerType(concession.getPassengerType());
			concessionNew.setPercentage(concession.getPercentage());
			concessionNew.setNote(concession.getNote().trim());
			concessionNew.setEffectiveStartDate(common.getDateTimeFromPicker2(concession.getEffectiveStartDate()));
			concessionNew.setEffectiveEndDate(common.getDateTimeFromPicker2(concession.getEffectiveEndDate()));	
			concessionNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			concessionNew.setUpdatedDate(new Date());
			concessionNew.setDeletedStatus(0);
			concessionNew.setStatus(concession.getStatus());
			
			session.update(concessionNew);
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
	
	
	
	public String deleteConcession(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		Concession concessionNew=(Concession)session.get(Concession.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		concessionNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		concessionNew.setUpdatedDate(new Date());
		concessionNew.setDeletedStatus(1);

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
	
	public int duplicateUpdateByQuery(Concession concession){
		int masterId=-1;
		Common common=new Common();
		try{

		String strDate=common.getDateTimeFromPicker2(concession.getEffectiveStartDate());
		String endDate=common.getDateTimeFromPicker2(concession.getEffectiveEndDate());
		
		String qry="from Concession where concessionName='"+concession.getConcessionName().trim()+"'"
				+"and passengerType.id="+concession.getPassengerType().getId()+" and id!="+concession.getId()
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
				 List<Concession> list=query.list();
				 
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
	
	public int duplicateInsertByQuery(Concession concession){
		int masterId=-1;
		Common common=new Common();
		try{
		    String strDate=common.getDateTimeFromPicker2(concession.getEffectiveStartDate());
			String endDate=common.getDateTimeFromPicker2(concession.getEffectiveEndDate());
			
			String qry="from Concession where concessionName='"+concession.getConcessionName().trim()+"'"
					+"and passengerType.id="+concession.getPassengerType().getId()
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
				 List<Concession> list=query.list();
				 
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
	
	public Concession getConcessionById(int id){
		
		Session session=null;
		Transaction tx=null;
		Concession concession=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from Concession where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()>0){
		concession=(Concession)list.get(0);
		}
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return concession;
	}
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		Session session=null;
		int cnt=0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(Concession.class);
			//criteria.createAlias("passengerType","passengerType");

			//criteria.add(Restrictions.ne("deletedStatus", 1));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(Concession.class);
			}else{
				 criteria = session.createCriteria(Concession.class);
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

				conditionGroup.add(Restrictions.like("concessionName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("passengerType.passengerTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));
				
				criteria.add(conditionGroup);
			}
		
			List<Concession> list = criteria.list();
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
		//	Criteria criteria = session.createCriteria(Concession.class);
		//	criteria.createAlias("passengerType","passengerType");

			//criteria.add(Restrictions.ne("deletedStatus", 1));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(Concession.class);
			}else{
				 criteria = session.createCriteria(Concession.class);
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

				conditionGroup.add(Restrictions.like("concessionName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("passengerType.passengerTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));

				criteria.add(conditionGroup);
			}
			
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<Concession> list = criteria.list();

			JSONArray array = new JSONArray();

			Common common=new Common();
			
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()
						+ "' value='"+ list.get(i).getId() + "'/>");
				
				ja.add(list.get(i).getId());//fare chart master id
				ja.add(list.get(i).getConcessionName() != null ? list.get(i).getConcessionName().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getPassengerType().getPassengerTypeName());
				ja.add(list.get(i).getPercentage());
				ja.add(list.get(i).getNote());

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
	
	public Map<Integer,String> getPassengerType(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		
		Session session = null;
		
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from PassengerType where deletedStatus=0 and status='Active' order by passengerTypeName desc");
			List<PassengerType> list = query.list();
	
			for (PassengerType pType : list) {
				
				resultMap.put(pType.getId(),pType.getPassengerTypeName());
			}
		}
		catch(Exception e){
			
		}
		finally{
			if(session!=null && session.isOpen()){
				 session.close();
			 }
		}
		return resultMap;
	}
}
