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
import com.trimax.its.fare.model.PenaltyTicket;
import com.trimax.its.fare.model.PassengerType;
import com.trimax.its.model.User;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.revenuesector.model.RevenueSector;
import com.trimax.its.util.HibernateUtil;

public class PenaltyTicketDao {
	public int addPenaltyTicket(PenaltyTicket penaltyTicket){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Common common=new Common();
		
		try{

		int masterId=-1;
		
		//check for duplicate
		masterId=duplicateInsertByQuery(penaltyTicket);
			
		if(masterId!=-1){
			return (-2);
		}

		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		PenaltyTicket penaltyTicketNew=penaltyTicket;
		
		penaltyTicketNew.setPenaltyTicketName(penaltyTicket.getPenaltyTicketName().trim());
		penaltyTicketNew.setNotes(penaltyTicket.getNotes().trim());
		penaltyTicketNew.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		penaltyTicketNew.setCreatedDate(new Date());
		penaltyTicketNew.setUpdatedBy(0);
		penaltyTicketNew.setUpdatedDate(null);
		penaltyTicketNew.setDeletedStatus(0);
		penaltyTicketNew.setStatus(penaltyTicket.getStatus());
		
		i=(Integer)session.save(penaltyTicketNew);
 
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
	
	public int updatePenaltyTicket(PenaltyTicket penaltyTicket){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Common common=new Common();
		
		try{
			//check for duplicate
			int masterId=-1;			
		
		    masterId=duplicateUpdateByQuery(penaltyTicket);
				
			if(masterId!=-1){
				return (-2);
			}

			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();
			
			PenaltyTicket penaltyTicketNew=(PenaltyTicket) session.get(PenaltyTicket.class,penaltyTicket.getId());
	
			penaltyTicketNew.setPenaltyTicketName(penaltyTicket.getPenaltyTicketName().trim());
			penaltyTicketNew.setNotes(penaltyTicket.getNotes().trim());
			penaltyTicketNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			penaltyTicketNew.setUpdatedDate(new Date());
			penaltyTicketNew.setDeletedStatus(0);
			penaltyTicketNew.setStatus(penaltyTicket.getStatus());
			
			session.update(penaltyTicketNew);
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
	
	
	
	public String deletePenaltyTicket(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		PenaltyTicket penaltyTicketNew=(PenaltyTicket)session.get(PenaltyTicket.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		penaltyTicketNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		penaltyTicketNew.setUpdatedDate(new Date());
		penaltyTicketNew.setDeletedStatus(1);

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
	
	public int duplicateUpdateByQuery(PenaltyTicket penaltyTicket){
		int masterId=-1;

		try{		
		String qry="from PenaltyTicket where penaltyTicketName='"+penaltyTicket.getPenaltyTicketName().trim()+"'"
				+" and id!="+penaltyTicket.getId()
				+" and deletedStatus=0";
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<PenaltyTicket> list=query.list();
				 
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
	
	public int duplicateInsertByQuery(PenaltyTicket penaltyTicket){
		int masterId=-1;

		try{
			String qry="from PenaltyTicket where penaltyTicketName='"+penaltyTicket.getPenaltyTicketName().trim()+"'"
				+" and deletedStatus=0";
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<PenaltyTicket> list=query.list();
				 
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
	
	public PenaltyTicket getPenaltyTicketById(int id){
		
		Session session=null;
		Transaction tx=null;
		PenaltyTicket penaltyTicket=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PenaltyTicket where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()>0){
		penaltyTicket=(PenaltyTicket)list.get(0);
		}
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return penaltyTicket;
	}
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		Session session=null;
		int cnt=0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			//Criteria criteria = session.createCriteria(PenaltyTicket.class);

			//criteria.add(Restrictions.ne("deletedStatus", 1));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(PenaltyTicket.class);
			}else{
				 criteria = session.createCriteria(PenaltyTicket.class);
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

				conditionGroup.add(Restrictions.like("penaltyTicketName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));

				criteria.add(conditionGroup);
			}
		
			List<PenaltyTicket> list = criteria.list();
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
			//Criteria criteria = session.createCriteria(PenaltyTicket.class);

			//criteria.add(Restrictions.ne("deletedStatus", 1));
			
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(PenaltyTicket.class);
			}else{
				 criteria = session.createCriteria(PenaltyTicket.class);
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

				conditionGroup.add(Restrictions.like("penaltyTicketName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));

				criteria.add(conditionGroup);
			}
			
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<PenaltyTicket> list = criteria.list();

			JSONArray array = new JSONArray();

			Common common=new Common();
			
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()
						+ "' value='"+ list.get(i).getId() + "'/>");
				
				ja.add(list.get(i).getId());//fare chart master id
				ja.add(list.get(i).getPenaltyTicketName() != null ? list.get(i).getPenaltyTicketName().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				
				System.out.println("name1="+list.get(i).getPenaltyTicketName());
				
				System.out.println("name2="+list.get(i).getPenaltyTicketName().toString());
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

			totalAfterFilter = total;//getTotalRecords(request,col,dir,viewdeletedrecord);
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
