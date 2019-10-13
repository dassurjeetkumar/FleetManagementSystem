package com.trimax.its.route.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.BusStopType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class PlatformDao {
	public int addPlatform(Platform platform){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from Platform where platform_name='"+platform.getPlatform_name().trim()+"' and deleted_status=0"
				+" and bay.bay_id="+platform.getBay().getBay_id();
		
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
			
			Bay bay=getBayById(session,platform.getBay().getBay_id());

			platform.setFloor_id(bay.getFloor().getFloor_id());
			platform.setParent_id(bay.getParent_id());
			
		 i=(Integer)session.save(platform);
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
	
	public int updateStopType(Platform platform){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from Platform where platform_name='"+platform.getPlatform_name().trim()+"' and deleted_status=0"
				+" and bay.bay_id="+platform.getBay().getBay_id();
		
		List<Platform> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getPlatform_id()==platform.getPlatform_id()){
			
			Platform platformNew = (Platform) session.get(Platform.class,platform.getPlatform_id());

			platformNew.setPlatform_name(platform.getPlatform_name());
			platformNew.setBay(platform.getBay());
			platformNew.setLatitude(platform.getLatitude());
			platformNew.setLongitude(platform.getLongitude());
			platformNew.setGeo_fence_data(platform.getGeo_fence_data());
			platformNew.setLandmark(platform.getLandmark());
			platformNew.setPlatform_description(platform.getPlatform_description());
			
			Bay bay=getBayById(session,platform.getBay().getBay_id());

			platformNew.setFloor_id(bay.getFloor().getFloor_id());
			platformNew.setParent_id(bay.getParent_id());
			
			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				platformNew.setUpdated_by(Integer.parseInt(userid));	
			}
			platformNew.setUpdated_date(new Date());
			platformNew.setStatus(platform.getStatus());
			
            session.update(platformNew);
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
	
	public Platform getPlatformById(int id){
	
		Session session=null;
		Transaction tx=null;
		Platform platform=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from Platform where platform_id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		platform=(Platform)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}finally{
			session.close();
		}
		return platform;
	}
	
	public String deletePlatform(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		Platform platform=(Platform)session.load(Platform.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		platform.setUpdated_date(new java.util.Date());
		platform.setUpdated_by(user.getUserId());
		platform.setDeleted_status(1);
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
//				"select count(*) as count from platform where deleted_status=0"
//				+" and (platform_name like '%"+searchterm+"%' or latitude like '%"+searchterm+"%'" +
//				" or longitude like '%"+searchterm+"%' or geo_fence_data like '%"+searchterm+"%' or landmark like '%"+searchterm+"%'" +
//			    " or platform_description like '%"+searchterm+"%'" +
//			    " or status like '%"+searchterm+"%')").addScalar(
//				"count", Hibernate.INTEGER);
//		
//		List<Integer> list = query.list();
		int cnt=0;
		try {


			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Platform.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			
			criteria.createAlias("bay","bay");
			criteria.createAlias("bay.floor","floor");
			criteria.createAlias("bay.floor.orgChart","orgChart");
			
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
				conditionGroup.add(Restrictions.like("platform_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("bay.bay_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("floor.floor_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("orgChart.org_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"+ search_term + "%"));
				criteria.add(conditionGroup);

			}
			
			List<Platform> list = criteria.list();
		
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
			Criteria criteria = session.createCriteria(Platform.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			
			criteria.createAlias("bay","bay");
			criteria.createAlias("bay.floor","floor");
			criteria.createAlias("bay.floor.orgChart","orgChart");
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("platform_id"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("platform_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("bay.bay_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("floor.floor_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("orgChart.org_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"+ search_term + "%"));
				criteria.add(conditionGroup);

			}
			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<Platform> list = criteria.list();
			
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				/*ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getPlatform_id()+ "' value='"
						+ list.get(i).getPlatform_id() + "'/>");*/
				
				ja.add(list.get(i).getPlatform_id());
				
				ja.add(list.get(i).getPlatform_name() != null ? list.get(i).getPlatform_name().toString() : "");
				ja.add(list.get(i).getBay().getBay_name() != null ? list.get(i).getBay().getBay_name() : "");
				ja.add(list.get(i).getBay().getFloor().getFloor_name() != null ? list.get(i).getBay().getFloor().getFloor_name() : "");
				ja.add(list.get(i).getBay().getFloor().getOrgChart().getOrg_name() != null ? list.get(i).getBay().getFloor().getOrgChart().getOrg_name() : "");
				
				/*ja.add(list.get(i).getLatitude());
				ja.add(list.get(i).getLongitude());
				ja.add(list.get(i).getGeo_fence_data() != null ? list.get(i).getGeo_fence_data() : "");
				ja.add(list.get(i).getLandmark() != null ? list.get(i).getLandmark() : "");
				ja.add(list.get(i).getPlatform_description() != null ? list.get(i).getPlatform_description() : "");*/
				
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
	
	public Map<Integer, String> getBayName() {

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Bay where deleted_status=0 order by bay_id");
		List<Bay> list = query.list();

		for (Bay bay : list) {
			
			resultMap.put(bay.getBay_id(),bay.getBay_name());
		}
		return resultMap;

	}
	
	public String getBayNameByByPid(int id) {

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Bay where floor.floor_id="+id+" and deleted_status=0 order by bay_name");
		List<Bay> list = query.list();

		String regionTypeAjaxString = "";
		
		regionTypeAjaxString += "<option value='0'>Select</option>";
		
		for (Bay bay : list) {
			
			regionTypeAjaxString += "<option value=" + bay.getBay_id()
					+ ">" + bay.getBay_name() + "</option>";
		}
		
		return regionTypeAjaxString;

	}
	
	public Bay getBayById(Session session,int id){
		Bay bay=null;

	 	try{

	 		bay = (Bay) session.get(Bay.class, new Integer(id));		
	 	}
	 	catch(Exception e){}
	 	
	 	return bay;
	}
	
	
	public int getTotalRecordsForBusstationBay(HttpServletRequest request, String col, String dir,int bayId) {
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
			Criteria criteria = session.createCriteria(Platform.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			criteria.add(Restrictions.eq("bay.bay_id", bayId));
			criteria.createAlias("bay","bay");
			//criteria.createAlias("floor.orgChart","orgChart");
			//criteria.createAlias("Floor", "floor");
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
			
			
						
			List<Platform> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			
		}
		
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForBusstationBay(int total, HttpServletRequest request, String col, String dir,int FloorId) {
		JSONObject result = new JSONObject();
		Session session=null;
		try {

			int totalAfterFilter = total;

			session= HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Platform.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			criteria.add(Restrictions.eq("bay.bay_id", FloorId));
			criteria.createAlias("bay","bay");
			//criteria.createAlias("floor.orgChart","orgChart");
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
				criteria.addOrder(Order.asc("bay.bay_id"));
			}
			
			
	
			
			
			
			List<Platform> list = criteria.list();
			
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				/*ja.add("<input type='hidden'  id='"
						+ list.get(i).getFloor_id()+ "' value='"
						+ list.get(i).getFloor_id() + "'/>");
				*/
				//ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+ list.get(i).getFloor_id()+ "' value='"+ list.get(i).getFloor_id() + "'/>");
				ja.add(list.get(i).getPlatform_id());
				ja.add(list.get(i).getPlatform_name() != null ? list.get(i).getPlatform_name().toString() : "");
				//ja.add(list.get(i).getOrgChart().getOrg_name() != null ? list.get(i).getOrgChart().getOrg_name() : "");
				ja.add(list.get(i).getStatus() != null ? list.get(i).getStatus() : "");
				ja.add("<a class='edit' href='' id='bay_edit_"+ list.get(i).getPlatform_id()+ "' value='"+ list.get(i).getPlatform_id() + "'> Edit</a>");
				ja.add("<a class='delete' href='' id='bay_delete_"+list.get(i).getPlatform_id()+ "' value='"+ list.get(i).getPlatform_id() + "'> Delete</a>");
				array.add(ja);
				//System.out.println("Array----->" + array);
			}

			//totalAfterFilter = getTotalRecordsForBusstationBay(request,col,dir,FloorId);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			//HibernateUtil.closeSession();
		}
		return result;
	}
	
	public int updateFloorDetails(String floorname,String status,int id,HttpServletRequest request)
	{
		int updateid=0;
		int userId=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		String sql="update platform set platform_name='"+floorname+"' , status='"+status+"',updated_by="+userId+",updated_date=now() where platform_id="+id;
		Session session=null;
		try{
			
			session= HibernateUtil.getSession("hibernate.cfg.xml");
			Transaction txn=session.beginTransaction();
			Query qry=session.createSQLQuery(sql);
			txn.commit();
			qry.executeUpdate();
		}catch(Exception ex)
		{
			session.getTransaction().rollback();
		}finally{
			/*if(session!=null){
                session.close();
            }*/
		}
		return id;
	}

	public void insertFloorDetails(String floor_name, String status, int busStationId,int floorId,int bayID, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Session session=null;
		int userId=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		session= HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="insert into platform (platform_name,bay_id,floor_id,status,parent_id,created_by,updated_by) values ('"+floor_name+"',"+bayID+","+floorId+",'"+status+"',"+busStationId+","+userId+",'0')";
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
			/*if(session!=null){
                session.close();
            }*/
		}
	}
	public void deleteFloor( int floortid,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Floor floor=new Floor();
		Session session=null;
		int userId=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		session= HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="update platform set deleted_status=1,updated_by="+userId+" where platform_id="+floortid;
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
			/*if(session!=null){
                session.close();
            }*/
		}
		
	}
	public boolean checkDuplcatePlatForm(String platform_name,int parent_id,int bayId)
	{
		boolean flag=false;
		try{
			String qry = " select platform_name from platform where platform_name='"+platform_name+"' and bay_id="+bayId+"";
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
	
	public boolean checkDuplcatePlatFormForUpdate(String floor_name,int bay_id,int platform_id)
	{
		boolean flag=false;
		try{
			String qry = " select platform_name from platform where platform_name='"+floor_name+"' and bay_id="+bay_id+" and platform_id="+platform_id;
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
	
}
