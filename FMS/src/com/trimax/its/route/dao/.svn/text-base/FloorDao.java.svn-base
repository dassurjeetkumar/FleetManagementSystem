package com.trimax.its.route.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
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

import com.trimax.its.device.model.Device;
import com.trimax.its.model.User;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.BusStopType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class FloorDao {
	public int addStopType(Floor floor){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		System.out.println(floor.getOrgChart().getParent_id()+"::"+floor.getOrgChart().getOrg_chart_id());
		
		String query="from Floor where floor_name='"+floor.getFloor_name().trim()+"' and deleted_status=0"
				+" and orgChart.org_chart_id="+floor.getOrgChart().getOrg_chart_id();
		
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		 i=(Integer)session.save(floor);
		}else{
			i=-1;
		}
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
		return i;
	}
	
	
	public int updateStopType(Floor floor){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from Floor where floor_name='"+floor.getFloor_name().trim()+"' and deleted_status=0"
				+" and orgChart.org_chart_id="+floor.getOrgChart().getOrg_chart_id();
		
		List<Floor> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getFloor_id()==floor.getFloor_id()){
			
			Floor floorNew = (Floor) session.get(Floor.class,floor.getFloor_id());

			floorNew.setFloor_name(floor.getFloor_name());
			floorNew.setOrgChart(floor.getOrgChart());
			
			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				floorNew.setUpdated_by(Integer.parseInt(userid));	
			}
			
			floorNew.setStatus(floor.getStatus());
			
            session.update(floorNew);
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
	
	public Floor getFloorById(int id){
	
		Session session=null;
		Transaction tx=null;
		Floor floor=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from Floor where floor_id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		floor=(Floor)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}finally{
			session.close();
		}
		return floor;
	}
	
	public String deleteFloor(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		Floor floor=(Floor)session.load(Floor.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		floor.setUpdated_date(new java.util.Date());
		floor.setUpdated_by(user.getUserId());
		floor.setDeleted_status(1);
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
//				"select count(*) as count from floor where deleted_status=0").addScalar(
//				"count", Hibernate.INTEGER);
//		
//		List<Integer> list = query.list();
//		int cnt = list.get(0);
		int cnt=0;
		try{
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Floor.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			
			criteria.createAlias("orgChart","orgChart");
			
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
				conditionGroup.add(Restrictions.like("floor_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("orgChart.org_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"+ search_term + "%"));
				criteria.add(conditionGroup);

			}
						
			List<Floor> list = criteria.list();
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

			int totalAfterFilter = total;

			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Floor.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			
			criteria.createAlias("orgChart","orgChart");
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("floor_id"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("floor_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("orgChart.org_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"+ search_term + "%"));
				criteria.add(conditionGroup);

			}
			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<Floor> list = criteria.list();
			
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
/*
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getFloor_id()+ "' value='"
						+ list.get(i).getFloor_id() + "'/>");
				*/
				ja.add(list.get(i).getFloor_id());
				ja.add(list.get(i).getFloor_name() != null ? list.get(i).getFloor_name().toString() : "");
				ja.add(list.get(i).getOrgChart().getOrg_name() != null ? list.get(i).getOrgChart().getOrg_name() : "");
				ja.add(list.get(i).getStatus() != null ? list.get(i).getStatus() : "");
				
				
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
	
	public Map<Integer, String> getOrganizationName() {

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from OrganisationChart where orgType.org_type_id=4 and deleted_status=0 order by org_name");
		List<OrganisationChart> list = query.list();

		for (OrganisationChart org : list) {
			
			resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
		}
		return resultMap;

	}

	public Map<Integer, String> getOrganizationName(int busstation_id) {
		// TODO Auto-generated method stub
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from OrganisationChart where orgType.org_type_id=4 and org_chart_id="+busstation_id+" and deleted_status=0 order by org_name");
		List<OrganisationChart> list = query.list();

		for (OrganisationChart org : list) {
			
			resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
		}
		return resultMap;

	}

	public int getTotalRecordsForBusstation(HttpServletRequest request, String col, String dir,int busid) {
//		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		
//		Query query = session.createSQLQuery(
//				"select count(*) as count from floor where deleted_status=0").addScalar(
//				"count", Hibernate.INTEGER);
//		
//		List<Integer> list = query.list();
//		int cnt = list.get(0);
		int cnt=0;
		Session session=null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Floor.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			criteria.add(Restrictions.eq("orgChart.org_chart_id", busid));
			criteria.createAlias("orgChart","orgChart");
			//criteria.createAlias("Floor", "floor");
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
			
			
						
			List<Floor> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			
		}finally{
			if(session!=null){
	               session.close();
	               //HibernateUtil.closeSession();
	            }
		}
		
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForBusstation(int total, HttpServletRequest request, String col, String dir,int busid) {
		JSONObject result = new JSONObject();
		Session session1=null;
		try {

			int totalAfterFilter = total;

			session1= HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria = session1.createCriteria(Floor.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			criteria.add(Restrictions.eq("orgChart.org_chart_id", busid));
			criteria.createAlias("orgChart","orgChart");
			//criteria.createAlias("Floor", "floor");
			//criteria.createAlias("orgChart","orgChart");
			//criteria.createAlias("floor","Chart");
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("floor_id"));
			}
			
			
	
			
			
			
			List<Floor> list = criteria.list();
			
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				/*ja.add("<input type='hidden'  id='"
						+ list.get(i).getFloor_id()+ "' value='"
						+ list.get(i).getFloor_id() + "'/>");
				*/
				//ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+ list.get(i).getFloor_id()+ "' value='"+ list.get(i).getFloor_id() + "'/>");
				ja.add(list.get(i).getFloor_id());
				ja.add(list.get(i).getFloor_name() != null ? list.get(i).getFloor_name().toString() : "");
				//ja.add(list.get(i).getOrgChart().getOrg_name() != null ? list.get(i).getOrgChart().getOrg_name() : "");
				ja.add(list.get(i).getStatus() != null ? list.get(i).getStatus() : "");
				ja.add("<a class='edit' href='' id='floor_edit_"+ list.get(i).getFloor_id()+ "' value='"+ list.get(i).getFloor_id() + "'> Edit</a>");
				ja.add("<a class='delete' href='' id='floor_delete_"+ list.get(i).getFloor_id()+ "' value='"+ list.get(i).getFloor_id() + "'> Delete</a>");
				array.add(ja);
			}

			//totalAfterFilter = getTotalRecordsForBusstation(request,col,dir,busid);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			if(session1.isOpen()){
			if(session1!=null){
				
	               session1.close();
	               //HibernateUtil.closeSession();
	            }
			}
			//HibernateUtil.closeSession();
		}
		return result;
	}
	
	public int updateFloorDetails(String floorname,String status,int id,HttpServletRequest request)
	{
		int updateid=0;
		int userId=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		String sql="update floor set floor_name='"+floorname+"' , status='"+status+"',updated_by="+userId+",updated_date=now() where floor_id="+id;
		Session session=null;
		
		try{
			
			session= HibernateUtil.getSession("hibernate.cfg.xml");
			Transaction txn=session.beginTransaction();
			Query qry=session.createSQLQuery(sql);
			qry.executeUpdate();
			txn.commit();
			
		}catch(Exception ex)
		{
			session.getTransaction().rollback();
		}finally{
			if(session.isOpen()){
			if(session!=null){
               session.close();
               //HibernateUtil.closeSession();
            }
			}
		}
		return id;
	}

	public void insertFloorDetails(String floor_name, String status, int parentid,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Floor floor=new Floor();
		Session session=null;
		int userId=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		session= HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="insert into floor (floor_name,parent_id,status,created_by,updated_by) values ('"+floor_name+"',"+parentid+",'"+status+"',"+userId+",'0')";
		try{
		/*floor.setFloor_name(floor_name);
		floor.setStatus(status);
		floor.getOrgChart().setParent_id(parentid);*/
		Transaction txn=session.beginTransaction();
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
		//session.save(floor);
		
		txn.commit();
		}
		catch(Exception ex)
		{
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		finally{
			if(session.isOpen()){
				if(session!=null){
	               session.close();
	               //HibernateUtil.closeSession();
	            }
				}
		}
	}
	public void deleteFloor( int floortid,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Floor floor=new Floor();
		Session session=null;
		int userId=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		session= HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="update floor set deleted_status=1,updated_by="+userId+" where floor_id="+floortid;
		try{
		/*floor.setFloor_name(floor_name);
		floor.setStatus(status);
		floor.getOrgChart().setParent_id(parentid);*/
		Transaction txn=session.beginTransaction();
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
		//session.save(floor);
		
		txn.commit();
		}
		catch(Exception ex)
		{
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			if(session.isOpen()){
				if(session!=null){
	               session.close();
	               //HibernateUtil.closeSession();
	            }
				}
		}
		
	}
	
	public boolean checkDuplcateFloor(String floor_name,int parent_id,int floorId)
	{
		boolean flag=false;
		try{
			String qry = " select floor_name from floor where floor_name='"+floor_name+"' and parent_id="+parent_id+" and deleted_status=0 ";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			if(query.list().size()>0)
			{
				flag=true;
			}
		}catch(Exception ex)
		{
			
		}finally{
			
		}
		return flag;
	}
	
	public boolean checkDuplcateFloorForUpdate(String floor_name,int parent_id,int floorId)
	{
		boolean flag=false;
		try{
			String qry = " select floor_name from floor where floor_name='"+floor_name+"' and parent_id="+parent_id+" and and deleted_status=0 floor_id="+floorId;
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			if(query.list().size()>0)
			{
				flag=true;
			}
		}catch(Exception ex)
		{
			
		}finally{
			
		}
		return flag;
	}
	public int getFloorId(){
		Transaction transaction=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");		
		Query query2 = session.createSQLQuery("select MAX(floor_id)floor_id  from  floor").addScalar("floor_id", Hibernate.INTEGER);
		List<Integer> list = query2.list();
		int employee_id = list.get(0);
		
		return employee_id;
		
	}

}
