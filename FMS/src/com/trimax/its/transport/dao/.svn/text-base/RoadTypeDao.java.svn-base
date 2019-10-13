package com.trimax.its.transport.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.transport.model.RoadType;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.Page_Master;

public class RoadTypeDao {
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Criteria criteria = null;
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from RoadType ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "	+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(RoadType.class);
			}else{
				 criteria = session.createCriteria(RoadType.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			/* = session.createCriteria(RoadType.class);
			criteria.add(Restrictions.in("status",Arrays.asList("ACTIVE","INACTIVE")));*/
		/*if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				//conditionGroup.add(Restrictions.like("road_type_id", "%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("road_type_name", "%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("note", "%" + search_term + "%"));
				
				criteria.add(conditionGroup);
				
			}*/
			String search_term = request.getParameter("sSearch").trim();
		if (!request.getParameter("sSearch").equals("")) {
			

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("road_type_name", search_term,	MatchMode.ANYWHERE));
			conditionGroup.add(Restrictions.like("status", search_term,	MatchMode.START));
			criteria.add(conditionGroup);

		}
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			List<RoadType> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart")+"lp--"+list);
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable'  name='check' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getRoad_type_id()
						+ "' value='"
						+ list.get(i).getRoad_type_id() + "'/>");
				ja.add(list.get(i).getRoad_type_id());
				
				ja.add(list.get(i).getRoad_type_name()!=null?list.get(i).getRoad_type_name().replaceAll(" ", "&nbsp;"):"");
				ja.add(list.get(i).getNote());
				
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getRoad_type_id()+"'/>Deleted Record");	

						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getRoad_type_id()+"'/>Record in Database");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}

				array.add(ja);
				System.out.println("Array----->" + array);
			}
			if (!request.getParameter("sSearch").equals("")) {
				totalAfterFilter = list.size();
				result.put("aaData", array);
				result.put("iTotalRecords", list.size());

				result.put("iTotalDisplayRecords", list.size());
			} else {
				totalAfterFilter = getTotalRecords(total, request, col, dir, viewdeletedrecord);
				result.put("aaData", array);
				result.put("iTotalRecords", total);

				result.put("iTotalDisplayRecords", totalAfterFilter);
			}

			/*totalAfterFilter = getTotalRecords();
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);*/

			System.out.println("REsult ------>"
					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	public int getTotalRecords(int total, HttpServletRequest request,String cols, String dir,String viewdeletedrecord) {
		//String viewdeletedrecord = null;
		Criteria criteria ;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(RoadType.class);
		}else{
			 criteria = session.createCriteria(RoadType.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("road_type_name", "%"+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status", search_term,	MatchMode.START));
			criteria.add(conditionGroup);

		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}

		List<ComponentType> list = criteria.list();
		return list.size();
	}
	/*public int getTotalRecords(String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery("select count(*) as count from   road_type where status in('ACTIVE','INACTIVE')").addScalar(
				"count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);
		return cnt;
	}*/
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public List<String> getRoadId() {
		List list=new ArrayList();
        System.out.println("inside get id");
		String qry="select road_type_id from road_type where status='ACTIVE'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("road_type_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	
	}
	public List<String> getRoadName() {
		// TODO Auto-generated method stub
		 List list=new ArrayList();
	        System.out.println("inside get id");
			String qry="select road_type_name from road_type where status ='ACTIVE'";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("road_type_name").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
	
		
	}
	public int saveRoadDetails(RoadType roadType) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		roadType.setCreated_date(new Date());
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		roadType.setCreated_by(usrsessionid);
		roadType.setDeleted_status(0);
		int i = (Integer) session.save(roadType);
		session.getTransaction().commit();
		session.close();
		
			return i;

		}
	public RoadType getRoadTypeDetails(int roadid) {
		// TODO Auto-generated method stub
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				RoadType road = (RoadType) session.get(
						RoadType.class, new Integer(roadid));
				System.out.println("--------->>>" + road.getRoad_type_name());
				return road;
		// TODO Auto-generated method stub
	
	}
	public int saveEDitedPageDetails(String requestType, int roadtypeid,
			RoadType roadType) {
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		 //  try
		   //{
		   session.beginTransaction();
		   //Page_Master page_Master= (BusStops) session.get(Page_Master.class, id);
		    System.out.println("hi in dao"+roadType.getRoad_type_id()+"status is--"+roadType.getStatus());
		    HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			RoadType road_master=(RoadType)session.get(RoadType.class,roadtypeid);
			
			road_master.setRoad_type_name(roadType.getRoad_type_name());
			road_master.setNote(roadType.getNote());
			road_master.setStatus(roadType.getStatus());
			
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			road_master.setUpdated_by(usrsessionid);
			road_master.setUpdated_date(new Date());
			session.update(road_master);
			session.getTransaction().commit();
			session.close();
		// }
		 //catch(Exception e)
		  //{
			//session.getTransaction().rollback();

		//  }
		 // finally
		 //  {
		//	
		 //  }
					

		// TODO Auto-generated method stub
		return 1;
	}
	public int checkForDuplicateRoadType(String roadTypeName, String note) {
	   Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select count(*) from road_type where road_type_name='"+roadTypeName+"' and note='"+note+"' and deleted_status=0").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
		
	}
	public int checkForDuplicateRoadTypeName(String roadname, int roadtypeid) {
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select count(*) from road_type where road_type_name='"+roadname+"' and road_type_id not in ("+roadtypeid+") and deleted_status=0").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
	
	}
	public int checkForDuplicateRoadTypeNote(String note, int roadtypeid) {
     Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select count(*) from road_type where note='"+note+"' and road_type_id="+roadtypeid+"").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
	}
	public int deleteRoadtype(int roadid) {
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		RoadType road = (RoadType) session.get(RoadType.class,roadid);
		
		//road.setStatus("INACTIVE");
		road.setDeleted_status(1);
		HttpServletRequest request = ServletActionContext.getRequest();
		road.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		road.setUpdated_date(new java.util.Date());
		
		session.update(road);
		session.getTransaction().commit();
	}catch(Exception ex)
	{
		session.getTransaction().rollback();
		ex.printStackTrace();
	}finally{
		
            if(session!=null){
                session.close();
           
        } 
	}
		return 1;
	
		// TODO Auto-generated method stub
		
	}
	public int getMaxRoadTypeID() {
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select max(road_type_id) from road_type").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
		
		// TODO Auto-generated method stub
		
	}
	public boolean getDuplicate(String name)
	{
		boolean flag=false;
		String qry = " select road_type_id from road_type where deleted_status=0 and road_type_name='"+name+"' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public int checkForDuplicateRoadTypeStatus(String status, int roadtypeid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select count(*) from road_type where status='"+status+"' and road_type_id="+roadtypeid+"").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
	}
}
