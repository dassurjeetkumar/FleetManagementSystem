package com.trimax.its.utility.dao;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.trimax.its.usermanagement.model.DesignationType;
import com.trimax.its.usermanagement.model.MenuUserRolePage;
import com.trimax.its.usermanagement.model.UserDetails;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.Group_Master;
import com.trimax.its.utility.model.UserGroup;

public class UserGroupDao {
	public List<String> getUserId() {
	
	List list=new ArrayList();
    //System.out.println("inside get id");
	String qry="select user_id from menu_user_master where deleted_status=0 and status='ACTIVE' ";
	Query query = HibernateUtil.getSession("").createSQLQuery(qry);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	if (aliasToValueMapList.size() > 0) {
		
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			list.add(rs.get("user_id").toString());
		}
	}
	HibernateUtil.closeSession();
	return list;
// TODO Auto-generated method stub

}
public List<String> getUserName() {
// TODO Auto-generated method stub
// TODO Auto-generated method stub
	 List list=new ArrayList();
        //System.out.println("inside get id");
		String qry="select userloginname from menu_user_master where deleted_status =0 and status='ACTIVE' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("userloginname").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;

}
public List<String> getGroupId() {
	
List list=new ArrayList();
//System.out.println("inside get id");
String qry="select group_id from menu_master_group where deleted_status=0 and status='ACTIVE'";
Query query = HibernateUtil.getSession("").createSQLQuery(qry);
query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
List<Map<String, Object>> aliasToValueMapList = query.list();
if (aliasToValueMapList.size() > 0) {
	
	for(int i=0;i<aliasToValueMapList.size();i++){
		Map<String, Object> rs = aliasToValueMapList.get(i);
		list.add(rs.get("group_id").toString());
	}
}
HibernateUtil.closeSession();
return list;
//TODO Auto-generated method stub

}
public List<String> getGroupName() {
//TODO Auto-generated method stub
//TODO Auto-generated method stub
 List list=new ArrayList();
    //System.out.println("inside get id");
	String qry="select group_name from menu_group_master where deleted_status=0 and status='ACTIVE'";
	Query query = HibernateUtil.getSession("").createSQLQuery(qry);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	if (aliasToValueMapList.size() > 0) {
		
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			list.add(rs.get("group_name").toString());
		}
	}
	HibernateUtil.closeSession();
	return list;

}

public int getTotalRecords(String search_term ) {
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Query query = session.createSQLQuery(
			"select count(*) as count from menu_group_master where status='ACTIVE' and deleted_status= 0  and (group_name like '"+search_term+"%') order by group_id").addScalar(
			"count", Hibernate.INTEGER);
	List<Integer> list = query.list();
	int cnt = list.get(0);
	//System.out.println(cnt);
	return cnt;
}
@SuppressWarnings("unchecked")
public JSONObject getData(int total, HttpServletRequest request,int userid,String col,String dir) {
	JSONObject result = new JSONObject();
	Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	try {
		int totalAfterFilter = total;
		String sql="SELECT gt.group_id, gt.group_name, ifnull(ug.user_id,0) as chk " +
				"FROM menu_group_master gt left join menu_user_group ug on ug.group_id = gt.group_id " +
				" and ug.user_id = '"+userid+"' AND ug.status = 'ACTIVE' "  +
				" WHERE gt.deleted_status = '0' AND gt.status = 'ACTIVE' ";
		
		
		
		if(!request.getParameter("sSearch").equals("")){
			String search_term = request.getParameter("sSearch").trim();
			//System.out.println("search_term---------"+search_term);
			//sql += " and (vendor_id like '%"+search_term+"%'";
			sql += " and ( group_name like '"+search_term+"%')";
			//sql += " OR vendor_contact_person like '%"+search_term+"%'";
			//sql += " OR note like '%"+search_term+"%'";
			//sql += " OR status like '"+search_term+"%')";
			
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
					+ rs.get("group_id")
					+ "' value='"
					+rs.get("group_id") + "'/>");}
			else{
				ja.add("<input type='checkbox' class='group-checkable'  name='chkbox' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("group_id")
						+ "' value='"
						+rs.get("group_id") + "'/>"+"<input type='hidden' id='group_id' value='"+rs.get("group_id")+"' >");
			}
			
			ja.add(rs.get("group_name"));
			array.add(ja);
			//System.out.println("Array----->" + array);
		}

		//totalAfterFilter = getTotalRecords();
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
public void mapUserGroupDetails(UserGroup usergroup) {
	
	// TODO Auto-generated method stub
	usergroup.setStatus("ACTIVE");
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	session.save(usergroup);
	session.getTransaction().commit();
	session.close();
}
public void updateUser(int userId) {
	// TODO Auto-generated method stub
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	String sql="update  menu_user_master set status='ACTIVE' where user_id="+userId+"";
	Query query=session.createSQLQuery(sql);
	query.executeUpdate();
	session.getTransaction().commit();
	session.close();
}

public void updateUserGroup(int userId,int value) {
	// TODO Auto-generated method stub
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	String sql="update  menu_user_master set usergroup_id="+value+" where user_id="+userId+"";
	Query query=session.createSQLQuery(sql);
	query.executeUpdate();
	session.getTransaction().commit();
	session.close();
}

public int getUserGroupById(int userId) {
	// TODO Auto-generated method stub
	int usergroupid=0;
	Session session = null;
	
	try{
	session = HibernateUtil.getSession("hibernate.cfg.xml");
	String sql="select usergroup_id from menu_user_master where user_id="+userId;
	Query query = session.createSQLQuery(sql)
			.addScalar("usergroup_id", Hibernate.INTEGER);
	
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> list = query.list();
	
	if(list.size()>0){
		Map<String, Object> rs = list.get(0);
		
		usergroupid=(Integer)(rs.get("usergroup_id"));
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
	
	return usergroupid;
}

public void updategroupId(int userId) {
	// TODO Auto-generated method stub
	
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	String sql="update menu_user_group set status='INACTIVE' where user_id="+userId+" and status='ACTIVE' ";
	Query query=session.createSQLQuery(sql);
	query.executeUpdate();
	session.getTransaction().commit();
	session.close();
}

public boolean getInactiveoldgroup(UserGroup usrgroup,int oldgroupid)
{
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	List<Map<String, Object>> list=null;
	int usergroupid=0;
	boolean flag=false;
	 session.beginTransaction();
	try{
		usrgroup.setGroup_id(oldgroupid);
		String sql1="select ugid from menu_user_group where user_id = "+usrgroup.getUser_id()+" and group_id="+oldgroupid+" and status='ACTIVE'";
		//System.out.println("sql1-------"+sql1);
		Query query = session.createSQLQuery(sql1);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		 list = query.list();
		
		 if (list.size() > 0) {
			 for(int i=0;i<list.size();i++){
					Map<String, Object> rs = list.get(i);
					usergroupid=Integer.parseInt(rs.get("ugid").toString());
					 //System.out.println("usergroupid---1---"+usergroupid);
				}
			 
		 }
		 //System.out.println("usergroupid---------"+usergroupid);
		 if(usergroupid==0){
			 //System.out.println("IN IF PART");
			 flag=true; 
		 }else{
			 UserGroup usrgroup1=(UserGroup)session.get(UserGroup.class,usergroupid);
			 //System.out.println("old id-------"+usrgroup1.getUgid());
			// usrgroup1.setGroup_id(oldgroupid);
			 usrgroup1.setStatus("INACTIVE");
			 session.update(usrgroup1);
		   session.getTransaction().commit();
			 //usrgroup1.set
				//int a=(Integer)session.save(usrgroup1);
				//if(a>0){
					flag=true;
				/*//}else{
					flag=false;
				}*/
			 
		 }
	}catch(Exception e){
		e.printStackTrace();
		}
	finally
	{
		//System.out.println("Test666666666666666666666");
		  if(session!=null){
                session.close();
            }
	}
	return flag;
}


public boolean addgroupId(UserGroup usrgroup){
	//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	List<Map<String, Object>> list=null;
	int usergroupid=0;
	boolean flag=false;
	 session.beginTransaction();
	try{
		
		String sql1="select ugid from menu_user_group where user_id = "+usrgroup.getUser_id()+" and group_id="+usrgroup.getGroup_id()+" and status='ACTIVE'";
		//System.out.println("sql1-------"+sql1);
		Query query = session.createSQLQuery(sql1);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		 list = query.list();
		
		 if (list.size() > 0) {
			 for(int i=0;i<list.size();i++){
					Map<String, Object> rs = list.get(i);
					usergroupid=Integer.parseInt(rs.get("ugid").toString());
					 //System.out.println("usergroupid---1---"+usergroupid);
				}
			 
		 }
		//System.out.println("usergroupid---------"+usergroupid);
		if(usergroupid==0){
			//System.out.println("user_rolepage_id********************"+usergroupid);
			/*menuuserrolepage.setCreated_by(userid);
			menuuserrolepage.setStatus("ACTIVE");
			menuuserrolepage.setCreated_date(new java.util.Date());
			int a = (Integer) session.save(menuuserrolepage);
			session.getTransaction().commit();*/
			usrgroup.setStatus("ACTIVE");
			int a=(Integer)session.save(usrgroup);
			
			if(a>0){
				flag=true;
			}else{
				flag=false;
			}
			
			
		}else{
			//System.out.println("test11111");
			
			//MenuUserRolePage menuuserrolepage1 = (MenuUserRolePage) session.get(MenuUserRolePage.class,userrolepageid);
			UserGroup usrgroup1=(UserGroup)session.get(UserGroup.class,usergroupid);
			
			/*menuuserrolepage1.setStatus("INACTIVE");
			menuuserrolepage1.setUpdated_by(userid);
			menuuserrolepage1.setUpdated_date(new java.util.Date());
			session.update(menuuserrolepage1);*/
			/*Previous group update as inactive and new add as ACTIVE*/
			usrgroup1.setStatus("INACTIVE");
			session.update(usrgroup1);
			session.getTransaction().commit();
			
			try
			{
				session.beginTransaction();
				usrgroup1.setStatus("ACTIVE");
				/*menuuserrolepage.setCreated_by(userid);
				menuuserrolepage.setCreated_date(new java.util.Date());*/
				int a = (Integer) session.save(usrgroup1);
				session.getTransaction().commit();
				
				if(a>0){
					flag=true;
				}else{
					flag=false;
				}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	finally
	{
		//System.out.println("Test666666666666666666666");
		  if(session!=null){
                session.close();
            }
	}
	return flag;
}



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

}


	


