package com.trimax.its.dashboard.doa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.dashboard.model.ChartMapping;
import com.trimax.its.dashboard.model.DashBoardUserMapping;
import com.trimax.its.dashboard.model.DashboardModel;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.memo.model.Memo;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.usermanagement.model.UserDetails;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.UserGroup;
import com.trimax.its.vehicle.model.Complaint;

public class DashboardMappingDoa {

	
	public Map<Integer, String> getUserlist() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from UserDetails where status='ACTIVE' and deleted_status=0 order by user_id");
		
		try{
			//System.out.println("Hii");
		List<UserDetails> list = query.list();
		
		for (UserDetails userdetails : list) {
			
			resultMap.put(userdetails.getUser_id(), userdetails.getUserloginname());
		}
		//System.out.println("resultMap--------"+resultMap);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
		

	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,int userid,String col,String dir) {
		JSONObject result = new JSONObject();
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			int totalAfterFilter = total;
			
			
			String sql="Select d.chart_id,d.chart_name,ifnull( dm.user_id,0) as chk " +
					" from chart_master d left join chart_mapping dm on dm.chart_id=d.chart_id" +
					" and dm.user_id='"+userid+"'  AND dm.status='ACTIVE'" +
					" where d.deleted_status=0 and d.status='ACTIVE'";
			
			if(!request.getParameter("sSearch").equals("")){
				String search_term = request.getParameter("sSearch").trim();
			
				sql += " and ( chart_name like '%"+search_term+"%')";
			
				
			}
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    //criteria.addOrder(Order.asc(col));
					sql += " order by "+col+ " asc";
				}else{
					//criteria.addOrder(Order.desc(col));	
					sql += " order by "+col+" desc";
				}
			}
			
			
		
			//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		
		//System.out.println("sql----------"+sql);
			
			
			
			
		
			Query query = session1.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
		
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				if(Integer.parseInt(rs.get("chk")!=null ? rs.get("chk").toString():"0")!=0){
				ja.add("<input type='checkbox' class='group-checkable'  name='chkbox' checked='checked' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("chart_id")
						+ "' value='"
						+rs.get("chart_id") + "'/>");}
				else{
					ja.add("<input type='checkbox' class='group-checkable'  name='chkbox' data-set='#sample_2 .checkboxes' id='"
							+ rs.get("chart_id")
							+ "' value='"
							+rs.get("chart_id") + "'/>"+"<input type='hidden' id='group_id' value='"+rs.get("group_id")+"' >");
				}
				
				ja.add(rs.get("chart_name"));
				array.add(ja);
				
			}

		
			result.put("aaData", array);
			String search_term = request.getParameter("sSearch").trim();
			totalAfterFilter=getTotalRecords(search_term);
			
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	public int getTotalRecords(String search_term ) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(
				"select count(*) as count from chart_master where status='ACTIVE' and deleted_status= 0  and (chart_name like '"+search_term+"%') order by chart_id").addScalar(
				"count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		//System.out.println(cnt);
		return cnt;
	}
	
	public void updateDashboardId(int userId) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		int updatedBy=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String sql="update chart_mapping set status='INACTIVE' , deleted_status=1, updated_date=now(), updated_by="+updatedBy +" where user_id="+userId+" and status='ACTIVE' ";
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	public void mapUserDashboard(ChartMapping usergroup) {
		
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		usergroup.setCreated_by(request.getSession()
				.getAttribute("userid").toString());
		usergroup.setCreated_date(new java.util.Date());
		usergroup.setStatus("ACTIVE");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		session.save(usergroup);
		session.getTransaction().commit();
		session.close();
	}

	
	//create dashboard master
	
	public int insertDashboardModel(DashboardModel DashboardModel) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		//System.out.println("000000000000000000rajesh");
		try{
		 i = (Integer) session.save(DashboardModel);
		 session.flush();
		session.getTransaction().commit();
		}catch(Exception e)
		{
			
		}finally{
		
		session.close();
		
		}
		return i;

	}
	
	//end
	//create dashboard list start
	
	public Map<Integer, String> getDashboardlist() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String query1="select id, dashboardname from Dashboard_master where status='ACTIVE' and deleted_status='0' order by id";
		Query query = session.createSQLQuery(query1);
		
		try{
			//System.out.println("Hii");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
		
		System.out.println("DashboardModel...."+aliasToValueMapList.size());
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			resultMap.put(Integer.parseInt(rs.get("id").toString()), rs.get("dashboardname").toString());	 
		}
		//System.out.println("resultMap--------"+resultMap);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
		

	}
	
	//end dashboard list
	//dashboard start
	@SuppressWarnings("unchecked")
	public JSONObject getDashboardData(int total, HttpServletRequest request,int userid,String col,String dir) {
		JSONObject result = new JSONObject();
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			int totalAfterFilter = total;
			
			
			String sql="Select d.id,d.dashboardname as dashboardname,ifnull( dm.user_id,0) as chk " +
					" from Dashboard_master d left join DashboardUsermapping dm on dm.dashboard_id=d.id" +
					" and dm.user_id='"+userid+"'  AND dm.status='ACTIVE'" +
					" where d.deleted_status=0 and d.status='ACTIVE'";
			
			if(!request.getParameter("sSearch").equals("")){
				String search_term = request.getParameter("sSearch").trim();
			
				sql += " and ( dashboardname like '%"+search_term+"%')";
			
				
			}
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    //criteria.addOrder(Order.asc(col));
					sql += " order by "+col+ " asc";
				}else{
					//criteria.addOrder(Order.desc(col));	
					sql += " order by "+col+" desc";
				}
			}
			
			
		
			//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		
		System.out.println("sqldashboard----------"+sql);
			
			
			
			
		
			Query query = session1.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
		
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				System.out.println(" aliasToValueMapList.size()"+ aliasToValueMapList.size());
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				if(Integer.parseInt(rs.get("chk")!=null ? rs.get("chk").toString():"0")!=0){
				ja.add("<input type='checkbox' class='group-checkable'  name='chkbox' checked='checked' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("id")
						+ "' value='"
						+rs.get("id") + "'/>");}
				else{
					ja.add("<input type='checkbox' class='group-checkable'  name='chkbox' data-set='#sample_2 .checkboxes' id='"
							+ rs.get("id")
							+ "' value='"
							+rs.get("id") + "'/>"+"<input type='hidden' id='group_id' value='"+rs.get("id")+"' >");
				}
				
				ja.add(rs.get("dashboardname"));
				array.add(ja);
				
			}

		
			result.put("aaData", array);
			String search_term = request.getParameter("sSearch").trim();
			totalAfterFilter=getTotalRecordsDashboard(search_term);
			
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	public int getTotalRecordsDashboard(String search_term ) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(
				"select count(*) as count from Dashboard_master where  deleted_status= 0  and (dashboardname like '"+search_term+"%') order by id").addScalar(
				"count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		//System.out.println(cnt);
		return cnt;
	}
	
	//dashboard end
	
	//update dashboard mapping start
	
	public void updateDashboardUserId(int userId) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		int updatedBy=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String sql="update DashboardUsermapping set status='INACTIVE' , deleted_status=1, updated_date=now(), updated_by="+updatedBy +" where user_id="+userId+" and status='ACTIVE' ";
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	//end
	//mapuserDashboard
	
public void mapUserDashboardMapping(DashBoardUserMapping usergroup) {
		
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		usergroup.setCreated_by(request.getSession()
				.getAttribute("userid").toString());
		usergroup.setCreated_date(new java.util.Date());
		usergroup.setStatus("ACTIVE");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		session.save(usergroup);
		session.getTransaction().commit();
		session.close();
	}

	//end

//start json list
@SuppressWarnings("unchecked")
public JSONObject getData1(int total, HttpServletRequest request,String col,String dir) {
	JSONObject result = new JSONObject();
	Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	try {
		int totalAfterFilter = total;
		
		
		String sql="Select id,dashboardname,status,ifnull(dashboard_desc,'') as dashboard_desc" +
				" from Dashboard_master where  deleted_status=0";
		
		if(!request.getParameter("sSearch").equals("")){
			String search_term = request.getParameter("sSearch").trim();
		
			sql += " and ( dashboardname like '%"+search_term+"%')";
		
			sql += " OR status like '%"+search_term+"%'";
			sql += " OR dashboard_desc like '%"+search_term+"%'";
		}
		
		if(!col.equals("")){
			if(dir.equals("asc")){
			    //criteria.addOrder(Order.asc(col));
				System.out.println("col....123"+col);
				sql += " order by "+col+ " asc";
				System.out.println("asc..."+sql);
			}else{
				//criteria.addOrder(Order.desc(col));	
				sql += " order by "+col+" desc";
				System.out.println("desc..."+sql);
			}
		}
		
		
	
		//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
		sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
	
	//System.out.println("sql----------"+sql);
		
		
		
		
	
		Query query = session1.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		JSONArray array = new JSONArray();
	
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			
				ja.add("<input type='checkbox' class='group-checkable'  name='chkbox' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("id")
						+ "' value='"
						+rs.get("id") + "'/>"+"<input type='hidden' id='group_id' value='"+rs.get("id")+"' >");
				ja.add( rs.get("id"));
				ja.add(rs.get("dashboardname"));
				ja.add(rs.get("dashboard_desc"));
				ja.add(rs.get("status"));
				array.add(ja);
			
		}

	
		result.put("aaData", array);
		String search_term = request.getParameter("sSearch").trim();
		totalAfterFilter=getTotalRecords1(search_term);
		
		result.put("iTotalRecords", totalAfterFilter);

		result.put("iTotalDisplayRecords", totalAfterFilter);

		
	} catch (Exception ex) {
		ex.printStackTrace();
	}finally{
		
	}
	return result;
}

public int getTotalRecords1(String search_term ) {
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Query query = session.createSQLQuery(
			"select count(*) as count from Dashboard_master where status='ACTIVE' and deleted_status= 0  and (dashboardname like '"+search_term+"%') order by id").addScalar(
			"count", Hibernate.INTEGER);
	List<Integer> list = query.list();
	int cnt = list.get(0);
	//System.out.println(cnt);
	return cnt;
}

//end json list
//deleted dashboard


public String deleteDeviceType(DashboardModel memo, int id) {
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
//System.out.println("=====Route id" + role.getRole_id());
try{
	//
	DashboardModel dasboardmodel = (DashboardModel) session.get(DashboardModel.class,id);
	
	dasboardmodel.setDeleted_status(1);
	dasboardmodel.setUpdated_by(memo.getUpdated_by());
	dasboardmodel.setUpdated_date(new java.util.Date());
	
	session.update(dasboardmodel);
	session.getTransaction().commit();
}catch(Exception e)
{
	
	session.getTransaction().rollback();
	
}finally{
	session.close();
}
	
	return null;
}
//end deleted dashboard
//edit dashboard
public DashboardModel getEditedDashboard(int id) {
	// BusStops busstops = new BusStops();
	DashboardModel dashmodel=new DashboardModel();
	Session session=null;
	try{
 session = HibernateUtil.getSession("hibernate.cfg.xml");
	dashmodel =(DashboardModel)session.get(DashboardModel.class,new Integer(id));
	System.out.println("dashmodel"+dashmodel.getDashboardname());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	finally{
		session.close();
		
	}
	return dashmodel;

}
//end edit dashboard

//edited dashboard
public DashboardModel updateDashboard(DashboardModel memo, int id) {
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Common common = new Common();
	DashboardModel dashboardmodel=new DashboardModel();
try{
	 dashboardmodel = (DashboardModel)session.get(DashboardModel.class,id);
	
	dashboardmodel.setDashboardname(memo.getDashboardname());
	dashboardmodel.setDashboardnamedesc(memo.getDashboardnamedesc());
	
	dashboardmodel.setStatus(memo.getStatus());
	
	dashboardmodel.setUpdated_by(memo.getUpdated_by());
	session.update(dashboardmodel);
	session.getTransaction().commit();
}catch(Exception e)
{
	session.getTransaction().rollback();
}finally{
	session.close();
}
	return dashboardmodel;
}
//edited dashboard end


//role list
public Map<String, String> roleList() {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	
	Map<String, String> resultMap = new HashMap<String, String>();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	//Query query = session.createQuery("from WaybillDetails where Status='closed'");
	try{
	String sql="select role_id as role_id,role_name as role_name from  menu_role_master where Status ='ACTIVE' and deleted_status=0";
	System.out.println("sql:"+sql);
	Query query=session.createSQLQuery(sql).addScalar("role_name").addScalar("role_id");
	
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	 for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> lists = aliasToValueMapList.get(i);
		
		 resultMap.put(lists.get("role_id").toString(), lists.get("role_name").toString());
	 }
	
	
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally{
		if (session != null) {
			session.close();
		}
	}
	return resultMap;
	
	
}
//role list end

//role userrole list
public Map<String, String> roleUserList(String userid) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	
	Map<String, String> resultMap = new HashMap<String, String>();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	//Query query = session.createQuery("from WaybillDetails where Status='closed'");
	try{
	String sql="select user_id as user_id,userloginname as userloginname from  menu_user_master where Status ='ACTIVE' and role_id='userid'";
	System.out.println("sql:"+sql);
	Query query=session.createSQLQuery(sql).addScalar("role_name").addScalar("role_id");
	
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	 for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> lists = aliasToValueMapList.get(i);
		
		 resultMap.put(lists.get("user_id").toString(), lists.get("userloginname").toString());
	 }
	
	
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally{
		if (session != null) {
			session.close();
		}
	}
	return resultMap;
	
	
}

//end userrole list

//start


public String roleUserLista(String userid) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	String selectstring="";
	Map<String, String> resultMap = new HashMap<String, String>();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	//Query query = session.createQuery("from WaybillDetails where Status='closed'");
	try{
	String sql="select user_id as user_id,userloginname as userloginname from  menu_user_master where Status ='ACTIVE' and role_id="+userid;
	System.out.println("sql:"+sql);
	Query query=session.createSQLQuery(sql).addScalar("user_id").addScalar("userloginname");
	
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	
	List<Map<String, Object>> aliasToValueMapList = query.list();
	 for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> lists = aliasToValueMapList.get(i);
		
		 resultMap.put(lists.get("user_id").toString(), lists.get("userloginname").toString());
		 selectstring+="<option value="+lists.get("user_id").toString()+">"+lists.get("userloginname").toString()+"</option>";
	 }
	
	
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally{
		if (session != null) {
			session.close();
		}
	}
	return selectstring;
	
	
}
//end
public String CheckDuplicateRecord(String name )
{
	String result="";
	Session session=HibernateUtil.getSession("hibernate.cfg.xml");
	
	try{
		String sql="select * from Dashboard_master where dashboardname='"+name+"' and deleted_status ='0'";
		Query query=session.createSQLQuery(sql);
		List querylist=query.list();
		if(querylist.size()>0)
		{
			result="duplicate";
			
		}
		if(querylist.size()>1)
		{
			result="duplicate";
			
		}
		if(querylist.size()==0)
		{
			result="singlerecord";
			
		}
		
	}
	catch(Exception e)
	{
	e.printStackTrace();	
		
	}
return result;
}

//edit duplicate
public boolean checkDashboardForUpdate(String dashboardname, int id) {
	boolean flag = false;
	String qry = " select dashboardname from Dashboard_master where  deleted_status=0 and dashboardname='"
			+ dashboardname + "' and id= " + id;
	Query query = HibernateUtil.getSession("").createSQLQuery(qry);

//	System.out.println("QUery Size===>" + query.list().size());
	if (query.list().size() > 0) {
		flag = true;
	}
	return flag;
}
//end

//duplicate

public boolean checkDashboardName(String dashboardname) {
	boolean flag = false;
	try{
		
	
	
	String qry = " select dashboardname from Dashboard_master where  deleted_status=0 and dashboardname='"
			+ dashboardname + "' ";
	Query query = HibernateUtil.getSession("hibernate.cfg.xml")
			.createSQLQuery(qry);

	//System.out.println("QUery Size===>" + query.list().size());
	if (query.list().size() > 0) {
		flag = true;
	}
	}
	catch(Exception e){
		
	}finally{
		HibernateUtil.closeSession();
	}
	return flag;
}
//end
}
