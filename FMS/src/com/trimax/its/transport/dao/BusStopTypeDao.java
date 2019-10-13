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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.fare.model.Farechart_Master;
import com.trimax.its.model.User;
import com.trimax.its.transport.model.BusStopType;
import com.trimax.its.util.HibernateUtil;

public class BusStopTypeDao {

	public int addStopType(BusStopType busStopType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from BusStopType where pointTypeName='"+busStopType.getPointTypeName().trim()+"' and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		 i=(Integer)session.save(busStopType);
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
	
	public int updateStopType(BusStopType busStopType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from BusStopType where pointTypeName='"+busStopType.getPointTypeName().trim()+"' and "
		 +"deletedStatus=0";
		
		List<BusStopType> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getId()==busStopType.getId()){
			String queryString = "update BusStopType set pointTypeName='"+busStopType.getPointTypeName()
					+"',updatedBy="+busStopType.getUpdatedBy()+",updatedDate='"+(new Date())
					+"',status='"+busStopType.getStatus()+"' where id="+busStopType.getId();
			

			BusStopType busStopTypeNew = (BusStopType) session.get(BusStopType.class,busStopType.getId());

			busStopTypeNew.setPointTypeName(busStopType.getPointTypeName());
			
			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				busStopTypeNew.setUpdatedBy(Integer.parseInt(userid));	
			}
			
			busStopTypeNew.setStatus(busStopType.getStatus());
			
            session.update(busStopTypeNew);
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
			session.close();
		}
		return i;
	}
	
	public BusStopType getStopTypeById(int id){
	
		Session session=null;
		Transaction tx=null;
		BusStopType busStopType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from BusStopType where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		busStopType=(BusStopType)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}finally{
			session.close();
		}
		return busStopType;
	}
	
	public String deleteStopType(int id) {
		String s="error";
		Session session=null;
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		BusStopType busStopType=(BusStopType)session.load(BusStopType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		busStopType.setUpdatedDate(new java.util.Date());
		busStopType.setUpdatedBy(user.getUserId());
		busStopType.setDeletedStatus(1);
		s="success";
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			session.getTransaction().commit();	
			HibernateUtil.closeSession();
		}

		return s;
	}
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir) {
//		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		
//		Query query = session.createSQLQuery(
//				"select count(*) as count from point_type where deleted_status=0").addScalar(
//				"count", Hibernate.INTEGER);
//		
//		List<Integer> list = query.list();
//		int cnt = list.get(0);
		int cnt=0;
		try{
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(BusStopType.class);
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				
			}else{
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
				String search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("pointTypeName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));
				criteria.add(conditionGroup);

			}
			
			List<BusStopType> list = criteria.list();	
			cnt = list.size();
		}
		catch(Exception e){
			
		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir) {
		JSONObject result = new JSONObject();

		try {
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			int totalAfterFilter = total;

			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(BusStopType.class);
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				
			}else{
				criteria.add(Restrictions.eq("deletedStatus", 0));
			//criteria.add(Restrictions.ne("deletedStatus", 0));
			
			}
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
			
			if (!request.getParameter("sSearch").trim().equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("pointTypeName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));
				criteria.add(conditionGroup);

			}
			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<BusStopType> list = criteria.list();
			
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()+ "' value='"
						+ list.get(i).getId() + "'/>");
				
				ja.add(list.get(i).getId());
				ja.add(list.get(i).getPointTypeName() != null ? list.get(i).getPointTypeName().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getStatus() != null ? list.get(i).getStatus().toString() : "");
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					String deletedstatus=String.valueOf(list.get(i).getDeletedStatus());
					System.out.println("deletedstatus---"+deletedstatus);
					
					if(deletedstatus.equalsIgnoreCase("1"))
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getId()+"'/>Deleted Record");	
						}else{
							ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getId()+"'/>Record in Database");	
					}
					
					
				}else{
					
				}
				
				array.add(ja);
				//System.out.println("Array----->" + array);
			}

			totalAfterFilter = getTotalRecords(request,col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
