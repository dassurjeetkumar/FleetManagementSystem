package com.trimax.its.transport.dao;


import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.model.User;

import com.trimax.its.transport.model.TicketType;
import com.trimax.its.util.HibernateUtil;

public class TicketTypeDao {
	public int addTicketType(TicketType ticketType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
	
		String query="from TicketType where ticketTypeName='"+ticketType.getTicketTypeName().trim()+"' and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		 i=(Integer)session.save(ticketType);
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
			session.close();
		}
		return i;
	}
	
	public int updateTicketType(TicketType ticketType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from TicketType where ticketTypeName='"+ticketType.getTicketTypeName().trim()+"' and deletedStatus=0";

		List<TicketType> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getId()==ticketType.getId()){
			
			TicketType ticketTypeNew = (TicketType) session.get(TicketType.class,ticketType.getId());

			ticketTypeNew.setTicketTypeName(ticketType.getTicketTypeName().trim());
			ticketTypeNew.setStatus(ticketType.getStatus());
			//ticketTypeNew.setNotes(ticketType.getNotes().trim());
			
			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				ticketTypeNew.setUpdatedBy(Integer.parseInt(userid));	
			}
			
			ticketTypeNew.setUpdatedDate(new Date());
			
            session.update(ticketTypeNew);
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
		}finally{
			session.close();
		}
		return i;
	}
	
	public TicketType getTicketTypeById(int id){
	
		Session session=null;
		Transaction tx=null;
		TicketType ticketType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from TicketType where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		ticketType=(TicketType)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}finally{
			session.close();
		}
		return ticketType;
	}
	
	public String deleteTicketType(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		TicketType ticketType=(TicketType)session.load(TicketType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		ticketType.setUpdatedDate(new java.util.Date());
		ticketType.setUpdatedBy(user.getUserId());
		ticketType.setDeletedStatus(1);
		ticketType.setStatus("Deleted");
		s="success";
		session.getTransaction().commit();
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
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir) {
		Session session =null;
		int cnt=0;
		
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(TicketType.class);
		
		//criteria.add(Restrictions.ne("deletedStatus", 1));
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
		
		}else{
			
			criteria.add(Restrictions.eq("deletedStatus", 0));
		}
		
		
		if(!col.equals("")){
			if(dir.equals("asc")){
			    criteria.addOrder(Order.asc(col));
			}else{
				criteria.addOrder(Order.desc(col));	
			}
		}
		
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();
			conditionGroup.add(Restrictions.like("ticketTypeName", "%"+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status", "%"+ search_term + "%"));
			criteria.add(conditionGroup);
		}
		
		
		List<TicketType> list = criteria.list();
		cnt = list.size();
		}
		catch(Exception e){
			
		}
		finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir) {
		JSONObject result = new JSONObject();
		Session session =null;

		try {

			int totalAfterFilter = total;

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(TicketType.class);
			
			//criteria.add(Restrictions.ne("deletedStatus", 1));
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
			
			}else{
				
				criteria.add(Restrictions.eq("deletedStatus", 0));
			}
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("ticketTypeName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"+ search_term + "%"));
				criteria.add(conditionGroup);
			}
			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<TicketType> list = criteria.list();
			
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()+ "' value='"
						+ list.get(i).getId() + "'/>");
				
				ja.add(list.get(i).getId());
				ja.add(list.get(i).getTicketTypeName() != null ? list.get(i).getTicketTypeName().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getStatus() != null ? list.get(i).getStatus() : "");
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					String deletedstatus=String.valueOf(list.get(i).getDeletedStatus());
					System.out.println("deletedstatus---"+deletedstatus);
					
					if(deletedstatus.equalsIgnoreCase("1"))
					{
						//ja.add("Deleted Record");	
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getId()+"'/>Deleted Record");	

						}else{
						//ja.add("Record in Database");	
							ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getId()+"'/>Record in Database");	

					}
					
					
				}else{
					
				}
				array.add(ja);
			}

			totalAfterFilter = getTotalRecords(request,col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	
}
