
package com.trimax.its.utility.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.fare.model.Farechart_Master;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.PageRole;
import com.trimax.its.utility.model.Role;

public class RoleDao {
	/*public int getTotalRecords() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
		Query query = session.createSQLQuery(
				"select count(*) as count from menu_role_master ").addScalar(
				"count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		
		 cnt = list.get(0);
		//System.out.println(cnt); 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 if(session!=null){
	             session.close();
	         }
		}
		
		return cnt; 
	}*/
	public int getTotalRecordsForCount(String search_term,String viewdeletedrecord){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		Query	 query=null;
		try{
			
			
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				query= session.createSQLQuery("select count(*) as count  from menu_role_master where (role_id like '"+search_term+"%'  OR role_name like '%"+search_term+"%' OR status like '%"+search_term+"%')  order by 	role_id").addScalar("count", Hibernate.INTEGER);
			}else{
				query= session.createSQLQuery("select count(*) as count  from menu_role_master where deleted_status=0  and (role_id like '"+search_term+"%'  OR role_name like '%"+search_term+"%' OR status like '%"+search_term+"%')  order by 	role_id").addScalar("count", Hibernate.INTEGER);
			}
		
		List<Integer> list = query.list();
		cnt = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		
		return cnt;
	}
	public int getTotalPageRecords(int role_id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
		Query query = session.createSQLQuery(
				"select count(*) as count from (select rm.page_id,page_name,path,parent_id,level,sequence,pg.role_id,pg.status from menu_page_master rm inner join menu_page_role pg on rm.page_id=pg.page_id where pg.status='ACTIVE' group by pg.page_id) as a ").addScalar(
				"count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		 cnt = list.get(0);
		//System.out.println(cnt);
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			 if(session!=null){
	             session.close();
	         }
		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getpageRoleData(int total, HttpServletRequest request, int role_id) {
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = "SELECT distinct rm.page_id, rm.page_name,ifnull(pr.page_id,0) as chk " +
					"FROM menu_page_master rm left join menu_page_role pr on pr.page_id= rm.page_id " +
					"and pr.role_id='"+role_id+"'" +
					" AND pr.status = 'ACTIVE' " +
					"WHERE rm.deleted_status = '0' order by rm.page_name";
					
					
					
					
			
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			if (!request.getParameter("sSearch").equals("")) {
			 sql = " select rm.page_id,page_name,path,parent_id,level,sequence,pg.role_id from menu_page_master rm " +
						" inner join menu_page_role pg on rm.page_id=pg.page_id " +
						" where pg.status='ACTIVE' and page_id= ? group by pg.page_id";

				String search_term = request.getParameter("sSearch");
			
				query.setParameter("rm.page_name", "%"+ search_term + "%");
				
			}
			
			
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//System.out.println("Test----"+aliasToValueMapList);
			
			
			JSONArray array = new JSONArray();
			////System.out.println("List size----->" + list.size() + "\t"
			//		+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				//System.out.println("================>"+rs.get("role_id")+"\t"+rs.get("page_id"));
				if(Integer.parseInt(rs.get("chk").toString())!=0){
					ja.add("<input type='checkbox' class='group-checkable' checked='checked' name='chkbox' data-set='#sample_2 .checkboxes' id='"+ rs.get("page_id")+ "' value='"+ rs.get("page_id") + "'/>");
				
				}else{
					ja.add("<input type='checkbox' class='group-checkable' name='chkbox'  data-set='#sample_2 .checkboxes' id='"
							+ rs.get("page_id")
							+ "' value='"
							+ rs.get("page_id") + "'/>");
				}
				ja.add("<input type='hidden' name='pgrole.role_id' id='page_id' value='"+rs.get("page_id")+"' >"+rs.get("page_id"));
				ja.add("<input type='hidden'  id='page_name' value='"+rs.get("page_name")+"' >"+rs.get("page_name"));
				ja.add(rs.get("path"));
				ja.add(rs.get("parent_id"));

				ja.add(rs.get("level"));
				ja.add(rs.get("sequence"));
				ja.add(rs.get("group_id"));
				
				array.add(ja);
				//System.out.println("Array----->" + array);
			}
		
			totalAfterFilter = getTotalPageRecords(role_id);
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			//System.out.println("REsult ------>"+ request.getParameter("iDisplayStart"));
			
	
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col,String dir) {
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			int totalAfterFilter = total;
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String sql="";
			int k=0;
			
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				sql="select role_id,IFNULL(role_name,'NA') role_name,IFNULL(status,'NA') status,deleted_status from menu_role_master";
		         k++;
			}else{
				sql="select role_id,IFNULL(role_name,'NA') role_name,IFNULL(status,'NA') status,deleted_status from menu_role_master where deleted_status=0 ";	
			}
			
			
			if(!request.getParameter("sSearch").equals("")){
				if(k==1){
					String search_term = request.getParameter("sSearch").trim();
					//System.out.println("search_term---------"+search_term);
					sql += " where deleted_status in(0,1) and ( role_id like '"+search_term+"%'";
					sql += " OR role_name like '%"+search_term+"%'";
					sql += " OR status like '%"+search_term+"%')";
					k=0;
				}else{
				
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
				sql += " and ( role_id like '"+search_term+"%'";
				sql += " OR role_name like '%"+search_term+"%'";
				sql += " OR status like '%"+search_term+"%')";
				}
			}
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				   
					sql += " order by "+col+ " asc";
				}else{
					
					sql += " order by "+col+" desc";
				}
			}
			
			
			
			//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			//System.out.println("sql-------"+sql);
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();

				
				
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("role_id")
						+ "' value='"
						+ rs.get("role_id") + "'/>");
				ja.add(rs.get("role_id").toString());
				//ja.add(list.get(i).getPass_type_name() != null ? list.get(i).getPass_type_name().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(rs.get("role_name").toString()!=null ? rs.get("role_name").toString().replaceAll(" ","&nbsp;") : "");
				ja.add(rs.get("status").toString());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					String deletedstatus=rs.get("deleted_status").toString();
					System.out.println("deletedstatus---"+deletedstatus);
					
					if(deletedstatus.equalsIgnoreCase("1"))
					{
						ja.add("Deleted Record");	
						}else{
						ja.add("Record in Database");	
					}
					
					
				}else{
					
				}
				array.add(ja);
				//System.out.println("Array----->" + array);
			}

			
			result.put("aaData", array);
			int cnt=0;
			String search_term = request.getParameter("sSearch").trim();
			
			 cnt=getTotalRecordsForCount(search_term,viewdeletedrecord);
			 result.put("iTotalRecords", cnt);
			 result.put("iTotalDisplayRecords", cnt);

			
			//System.out.println("REsult ------>"+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	
	public int insertRole(Role role) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		try{
		Query	 query = session.createSQLQuery("select count(*) as count from menu_role_master where deleted_status = 0 and  role_name ='"+role.getRole_name()+"'").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		//System.out.println("cnt--------"+cnt);
		
		if(cnt==0)
		{
			role.setUpdated_by(0);
		 i = (Integer) session.save(role);
		session.getTransaction().commit();
		//session.close();
		
		
		}else{
			i=-1;
			
		}
		}catch(Exception e){e.printStackTrace();}
		finally{
			if(session!=null){
	             session.close();
	         }
		}
		return i;
	}
	public Role getEditedRole(int id) {
		// BusStops busstops = new BusStops();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Role role = (Role) session.get(Role.class, new Integer(id));
		return role;

	}
	public int updateRole(Role role,int id,int userid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int n=0;
	try{
		
		String query = "select role_id from menu_role_master where deleted_status=0 and role_name ='"+role.getRole_name()+"'and role_id NOT IN("+ id +")";
		//System.out.println("query---------"+query);
		List list=session.createSQLQuery(query).list();
		//System.out.println("size-------"+list.size());
		if(list.size()==0)
		{
		Role role1 = (Role) session.get(Role.class,id);
		role1.setRole_name(role.getRole_name());
		role1.setStatus(role.getStatus());
		role1.setUpdated_date(new java.util.Date());
		role1.setUpdated_by(userid);
		session.update(role1);
		session.getTransaction().commit();
		n=1;
		}else{
			n=-1;
			
		}
	}catch(Exception ex)
	{
		session.getTransaction().rollback();
		ex.printStackTrace();
	}finally{
		 if(session!=null){
             session.close();
         }
	}
       return n;
	}
	
	
	public int deleteRoleRecord(Role role,int id,int userid)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int n=0;
		try{
			Role role1 = (Role) session.get(Role.class,id);
			role1.setUpdated_by(userid);
			role1.setUpdated_date(new java.util.Date());
			role1.setDeleted_status(1);
			session.update(role1);
			session.getTransaction().commit();
			n=1;
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			 if(session!=null){
	             session.close();
	         }
		}
		return n;
	}
public String savePage_Role(PageRole page_role){
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	String flag="";
	try{
	session.beginTransaction();
	int i = (Integer) session.save(page_role);
	session.getTransaction().commit();
	session.close();
	if (i > 0) {
		flag = "success";
	} else {
		flag = "fail";
	}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		 if(session!=null){
             session.close();
         }
	}
	return flag;

}
public String updatePage_Role_M(int role_id){
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	String sql="update menu_page_role set status='INACTIVE' where role_id='"+role_id+"' and status='ACTIVE'";
	try{
	Query query=session.createSQLQuery(sql);
	int update=query.executeUpdate();
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
	return "success";
	}
public String updateroleMapping(int id){
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	String sql="update menu_page_master set status='ACTIVE' where page_id="+id+"";
	try{
	
	Query query=session.createSQLQuery(sql);
	int update=query.executeUpdate();
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
	return "success";
	}


public String  getpagelistForRole(int roleid){
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	String pagelist="";
	String pagename="";
	String pagelistdetails="";
	try{
		//String query = "select role_id from menu_role_master where deleted_status=0 and role_name ='"+role.getRole_name()+"'and role_id NOT IN("+ id +")";
		/*String query ="select mgr.group_id,mgm.group_name from menu_group_role mgr " + 
					  " INNER JOIN menu_group_master mgm ON mgr.group_id=mgm.group_id and mgm.deleted_status=0 and mgm.status='ACTIVE' " +
					  " where mgr.status='ACTIVE' and mgr.deleted_status=0 and mgr.role_id="+ roleid;
					  */
		String query1=" select mpr.page_id,mpm.page_name from menu_page_role mpr " +
					 " INNER JOIN menu_page_master mpm ON mpr.page_id=mpm.page_id and mpr.status='ACTIVE' and mpm.deleted_status=0 " +
					  " where mpr.role_id= " + roleid;
	/*	String query1="select mug.user_id ,mum.userloginname from  menu_user_master mum "+
					 " INNER JOIN menu_user_group mug ON  mum.user_id =mug.user_id " +
					 " where mug.group_id="+ +"and mug.status='ACTIVE' " +
					 " and mum.status='ACTIVE' and mum.deleted_status=0" ;	*/
		//System.out.println("query---------"+query1);
		Query query =session.createSQLQuery(query1);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		//System.out.println("Test----"+aliasToValueMapList);
		////System.out.println("list-------"+list);
		
		if(aliasToValueMapList.size()>0)
		{
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			 pagename=rs.get("page_name").toString();
			 pagelist+=pagename+",";
			 //pagelistdetails=pagelist.substring(0,pagelist.length()-1);
		}
		}else{
			pagelist="";
		}
		if(pagelist.length()>0){
			pagelistdetails=pagelist.substring(0,pagelist.length()-1);
		}
		//System.out.println("pagename-----"+pagename);
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		 if(session!=null){
             session.close();
         }
	}
	return pagelistdetails;
}

public String  getgrouplistForrole(int roleid){
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	String grouplist="";
	String groupname="";
	String grouplistdetails="";
	try{
		//String query = "select role_id from menu_role_master where deleted_status=0 and role_name ='"+role.getRole_name()+"'and role_id NOT IN("+ id +")";
		/*String query ="select mgr.group_id,mgm.group_name from menu_group_role mgr " + 
					  " INNER JOIN menu_group_master mgm ON mgr.group_id=mgm.group_id and mgm.deleted_status=0 and mgm.status='ACTIVE' " +
					  " where mgr.status='ACTIVE' and mgr.deleted_status=0 and mgr.role_id="+ roleid;
					  */
		String query1= " select mgr.group_id,mgm.group_name from menu_group_role mgr "+
						" INNER JOIN menu_group_master mgm ON mgr.group_id=mgm.group_id and mgm.deleted_status=0 and mgm.status='ACTIVE' "+
						" where mgr.status='ACTIVE' and mgr.deleted_status=0 and mgr.role_id = " +roleid;
		//System.out.println("query---------"+query1);
		Query query =session.createSQLQuery(query1);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		//System.out.println("Test----"+aliasToValueMapList);
		////System.out.println("list-------"+list);
		
		if(aliasToValueMapList.size()>0)
		{
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			groupname=rs.get("group_name").toString();
			grouplist+=groupname+",";
			
		}
		}else{
			grouplist="";
		}
		
		//System.out.println("grouplist-----"+grouplist);
		if(grouplist.length()>0){
			grouplistdetails=grouplist.substring(0,grouplist.length()-1);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		 if(session!=null){
             session.close();
         }
	}
	return grouplistdetails;
}


public String getUserlistforrole(int roleid){
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	String userlist="";
	String username="";
	String userlistdetails="";
	try{
		//String query = "select role_id from menu_role_master where deleted_status=0 and role_name ='"+role.getRole_name()+"'and role_id NOT IN("+ id +")";
				/*String query ="select mgr.group_id,mgm.group_name from menu_group_role mgr " + 
							  " INNER JOIN menu_group_master mgm ON mgr.group_id=mgm.group_id and mgm.deleted_status=0 and mgm.status='ACTIVE' " +
							  " where mgr.status='ACTIVE' and mgr.deleted_status=0 and mgr.role_id="+ roleid;
							  */
				String query1= " select mum.user_id,mum.userloginname from menu_user_master mum where mum.role_id = " + roleid +" and mum.status='ACTIVE' and mum.deleted_status=0";
				//System.out.println("query---------"+query1);
				Query query =session.createSQLQuery(query1);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				//System.out.println("Test----"+aliasToValueMapList);
				////System.out.println("list-------"+list);
				
				if(aliasToValueMapList.size()>0)
				{
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					username=rs.get("userloginname").toString();
					userlist+=username+",";
					
				}
				}else{
					userlist="";
				}
				
				//System.out.println("userlist-----"+userlist);
				if(userlist.length()>0){
					userlistdetails=userlist.substring(0,userlist.length()-1);
				}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		 if(session!=null){
             session.close();
         }
	}
	return userlistdetails;
}


	
	/*public List getRole_id() {
		List list = new ArrayList();
		//System.out.println("inside get id");
		String qry = "select role_id from menu_role_master where status ='ACTIVE'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("role_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}*/

	/*public List getRoleName() {
		List list = new ArrayList();
		//System.out.println("inside get id");
		String qry = "select role_name from menu_role_master where status='ACTIVE' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("role_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}*/
	/*public String getGroupRole(int role_id)
	{
		String group_id="987654";
		try{
		String qry = "select group_id from group_role gr" +
				" inner join role r on r.role_id=gr.role_id where r.role_id= "+role_id+" and gr.status='ACTIVE'  limit 1 ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		if(query.list().size()>0){
		group_id=query.uniqueResult().toString();
		}
		//query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String, Object>> aliasToValueMapList = query.list();
//		if (aliasToValueMapList.size() > 0) {
//
//			for (int i = 0; i < aliasToValueMapList.size(); i++) {
//				Map<String, Object> rs = aliasToValueMapList.get(i);
//				group_id=rs.get("group_role").toString();
//			}
//		}
		HibernateUtil.closeSession();
		
	
	}catch(Exception ex)
		{
		
		}
		return group_id;
	}*/
}
