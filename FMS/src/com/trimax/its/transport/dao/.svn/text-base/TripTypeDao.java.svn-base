package com.trimax.its.transport.dao;


import java.util.ArrayList;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.model.User;
import com.trimax.its.route.model.RouteType;
import com.trimax.its.transport.model.TripType;
import com.trimax.its.util.HibernateUtil;


public class TripTypeDao {
	public int addTripType(TripType tripType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
	
		String query="from TripType where tripTypeName='"+tripType.getTripTypeName().trim()+"' and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		 i=(Integer)session.save(tripType);
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
	
	public int updateTripType(TripType tripType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from TripType where tripTypeName='"+tripType.getTripTypeName().trim()+"' and deletedStatus=0";

		List<TripType> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getId()==tripType.getId()){
			
			TripType tripTypeNew = (TripType) session.get(TripType.class,tripType.getId());

			tripTypeNew.setTripTypeName(tripType.getTripTypeName().trim());
			tripTypeNew.setRoutetypedetails(tripType.getRoutetypedetails());
			tripTypeNew.setStatus(tripType.getStatus());
			tripTypeNew.setNotes(tripType.getNotes().trim());
			
			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				tripTypeNew.setUpdatedBy(Integer.parseInt(userid));	
			}
			
			tripTypeNew.setUpdatedDate(new Date());
			
            session.update(tripTypeNew);
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
			if(session.isOpen()){
			session.close();
			}
		}
		return i;
	}
	
	public TripType getTripTypeById(int id){
	
		Session session=null;
		Transaction tx=null;
		TripType tripType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from TripType where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		tripType=(TripType)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}finally{
			if(session.isOpen()){
			session.close();
			}
		}
		return tripType;
	}
	
	public String deleteTripType(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		TripType tripType=(TripType)session.load(TripType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		tripType.setUpdatedDate(new java.util.Date());
		tripType.setUpdatedBy(user.getUserId());
		tripType.setDeletedStatus(1);
		//tripType.setStatus("Deleted");
		s="success";
		session.getTransaction().commit();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if(session.isOpen()){
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
		
		Criteria criteria = session.createCriteria(TripType.class);
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
		
		}else{
			//criteria.add(Restrictions.ne("deletedStatus", 1));
			criteria.add(Restrictions.eq("deletedStatus", 0));
		}
		//criteria.add(Restrictions.ne("deletedStatus", 1));
		
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
			conditionGroup.add(Restrictions.like("tripTypeName", "%"+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));
			criteria.add(conditionGroup);
		}
		
		List<TripType> list = criteria.list();
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
			Criteria criteria = session.createCriteria(TripType.class);
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
			
			}else{
				//criteria.add(Restrictions.ne("deletedStatus", 1));
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
				conditionGroup.add(Restrictions.like("tripTypeName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));
				criteria.add(conditionGroup);
			}
			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<TripType> list = criteria.list();
			
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()+ "' value='"
						+ list.get(i).getId() + "'/>");
				
				ja.add(list.get(i).getId());
				ja.add(list.get(i).getTripTypeName() != null ? list.get(i).getTripTypeName().toString().replaceAll(" ","&nbsp;") : "");
				if(list.get(i).getRoutetypedetails()==null){
					ja.add(" ");
				}else{
					ja.add(list.get(i).getRoutetypedetails().getRoute_type_name() != null ? list.get(i).getRoutetypedetails().getRoute_type_name() : "");
				}
				ja.add(list.get(i).getStatus() != null ? list.get(i).getStatus() : "");
				ja.add(list.get(i).getNotes() != null ? list.get(i).getNotes() : "");
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					String deletedstatus=String.valueOf(list.get(i).getDeletedStatus());
					System.out.println("deletedstatus---"+deletedstatus);
					
					if(deletedstatus.equalsIgnoreCase("1"))
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getId()+"'/>Deleted Record");	

						//ja.add("Deleted Record");	
						}else{
							ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getId()+"'/>Record in Database");	

						//ja.add("Record in Database");	
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
		}
		finally{
			if(session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	@SuppressWarnings("finally")
	public List<RouteType> getRouteTypeList()
	{
		List<RouteType> routeTypeList = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(RouteType.class);
			criteria.add(Restrictions.ilike("status", "ACTIVE",MatchMode.EXACT));
			routeTypeList = new ArrayList<RouteType>();

			List<Object> det = criteria.list();
			RouteType initialrouteType = new RouteType();
			initialrouteType.setRoute_type_name("Select");
			initialrouteType.setRoute_type_id(0);
			routeTypeList.add(initialrouteType);

			for (int i = 0; i < det.size(); i++) {

				RouteType routeType = (RouteType) det.get(i);
				routeTypeList.add(routeType);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			return routeTypeList;
		}
		
	}
	
}
