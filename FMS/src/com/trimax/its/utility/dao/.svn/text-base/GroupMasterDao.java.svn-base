package com.trimax.its.utility.dao;

import java.util.ArrayList;
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

import com.trimax.its.fare.model.Farechart_Master;
import com.trimax.its.model.User;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.GroupRole;
import com.trimax.its.utility.model.Group_Master;
import com.trimax.its.utility.model.Page_Master;
//import com.trimax.its.utility.model.Role;

public class GroupMasterDao {
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col,String dir) {
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			//int totalAfterFilter = total;
			String deletedcheck="";
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			int k=0;
			
			String sql="";
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				sql="select group_id,IFNULL(group_name,'NA') group_name,IFNULL(status,'NA') status,deleted_status from menu_group_master ";
				k++;
			}else{
				sql="select group_id,IFNULL(group_name,'NA') group_name,IFNULL(status,'NA') status,deleted_status from menu_group_master where deleted_status=0 ";
				//deletedcheck="n";
			}
			if(!request.getParameter("sSearch").equals("")){
				if(k==1){
					String search_term = request.getParameter("sSearch").trim();
					//System.out.println("search_term---------"+search_term);
					sql += "where deleted_status in(0,1) and ( group_id like '"+search_term+"%'";
					sql += " OR group_name like '%"+search_term+"%'";
					sql += " OR status like '%"+search_term+"%')";
				}else{
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
				sql += " and ( group_id like '"+search_term+"%'";
				sql += " OR group_name like '%"+search_term+"%'";
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
			
			List<Map<String, Object>> list = query.list();
			JSONArray array = new JSONArray();
			
		
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> rs  = list.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("group_id")
						+ "' value='"
						+ rs.get("group_id") + "'/>");
				ja.add(rs.get("group_id").toString());
				//ja.add(rs.get("group_name").toString());
				ja.add(rs.get("group_name").toString()!=null ? rs.get("group_name").toString().replaceAll(" ","&nbsp;") : "");
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

			
			String search_term = request.getParameter("sSearch").trim();
			int totalAfterFilter = getTotalRecords(search_term,viewdeletedrecord);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			//System.out.println("REsult ------>"+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			//System.out.println("getData");
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	
	public String deleteGroup(int id) {
		String status="error";
		Session session=null;
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		Group_Master gm=(Group_Master)session.load(Group_Master.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");

		
		gm.setUpdated_date(new java.util.Date());
		gm.setUpdated_by(user.getUserId());
		gm.setDeleted_status(1);
		session.getTransaction().commit();
		status="success";
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
				
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return status;
	}

	
	
	@SuppressWarnings("unchecked")
	public JSONObject getRoleData(int total, HttpServletRequest request,int group_id) {
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			int totalAfterFilter = total;
			//String sql="SELECT distinct r.role_id, r.role_name,ifnull(gr.role_id,0) as chk FROM role r left join menu_menu_group_role gr on gr.role_id = r.role_id and gr.group_id="+group_id+" AND gr.status = 'ACTIVE'WHERE r.deleted_status = '0' order by r.role_name";
			//String sql="select role_id,role_name from role where deleted_status=0 ";
			String sql=" select menu_group_role.grid ,menu_group_role.role_id ,menu_role_master.role_name  from  menu_group_role " +
					   " INNER JOIN menu_role_master ON menu_role_master.role_id= menu_group_role.role_id where  menu_group_role.group_id= " + group_id + " and menu_role_master.status='ACTIVE' and  menu_group_role.status='ACTIVE' and menu_group_role.deleted_status = 0 and menu_role_master.deleted_status = 0";	
		/*	Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			String [] role_id=null;*/

			//sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			String search_term1 = request.getParameter("sSearch");
			////System.out.println("search_term-------"+search_term1);
			/*if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
				sql += " and (  menu_group_role.role_id like '%"+search_term+"%'";
				sql += " OR role.role_name like '%"+search_term+"%')";
				
				
			}
			//System.out.println("sql----"+sql);
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}*/
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			// JSONArray array = new JSONArray();
			JSONArray array = new JSONArray();
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				
				/*	ja.add("<input type='checkbox' class='group-checkable'  name='chkbox' data-set='#sample_2 .checkboxes' id='"
							+ rs.get("role_id")
							+ "' value='"
							+ rs.get("role_id") + "'/>");*/
				
				/*ja.add("<input type='hidden' name='grouprole.role_id' id='role_id' value='"+rs.get("role_id")+"' >"+rs.get("role_id"));*/
				
				ja.add(rs.get("role_id").toString());
				ja.add(rs.get("role_name").toString());
				/*ja.add("<a class='delete' id='roleid_delete_"+ rs.get("grid").toString()+ "' value='"+ rs.get("grid") + "'> Add</a>");*/
				ja.add(" ");
				ja.add("<a class='delete' id='roleid_delete_"+ rs.get("grid").toString()+ "' value='"+ rs.get("grid") + "' href=''  > Delete</a>");
				//ja.add("act");
				//ja.add("delst");
				
				array.add(ja);
				//System.out.println("Array----->" + array);
				}
			
			totalAfterFilter = getTotalRoleRecords();
			result.put("aaData", array);
			result.put("iTotalRecords", aliasToValueMapList);

			result.put("iTotalDisplayRecords", aliasToValueMapList);

			//System.out.println("REsult ------>"	+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			/*  if(session!=null){
	                session.close();
	            }*/
		}
		return result;
	}
	public int getTotalRecords(String search_term,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query=null;
		 if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
			 query = session.createSQLQuery("select count(*) as count from  menu_group_master where ( group_id like '"+search_term+"%' OR group_name like '%"+search_term+"%'  OR status like '%"+search_term+"%')").addScalar("count", Hibernate.INTEGER);
			}else{
				query = session.createSQLQuery("select count(*) as count from  menu_group_master where deleted_status=0  and ( group_id like '"+search_term+"%' OR group_name like '%"+search_term+"%'  OR status like '%"+search_term+"%')").addScalar("count", Hibernate.INTEGER);
			}
		List<Integer> list = query.list();
		int cnt = list.get(0);
		//System.out.println(cnt);
		return cnt;
	}
	public int getTotalRoleRecords() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery("select count(*) as count from  menu_role_master where status='ACTIVE' and  deleted_status=0 ").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		//System.out.println(cnt);
		return cnt;
	}
	public int insertGroupMaster(Group_Master group) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		try{
		Query	 query = session.createSQLQuery("select count(*) as count from  menu_group_master where deleted_status = 0 and  group_name ='"+group.getGroup_name()+"'").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		//System.out.println("cnt--------"+cnt);
		if(cnt==0){
		 i = (Integer) session.save(group);
		 //System.out.println("Integer--------"+i);
		session.getTransaction().commit();
		session.close();
		}else{
			i=-1;
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//System.out.println("insertGroupMaster");
			/* if(session!=null){
	                session.close();
	            }*/
		}
		return i;
		
	}
	public Group_Master getGroupDetails(int groupid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Group_Master groupmaster = (Group_Master) session.get(Group_Master.class, new Integer(groupid));
		//System.out.println("--------->>>" + groupmaster.getGroup_name());
		return groupmaster;
		
	}
	public int saveEDitedPageDetails(String requestType, int groupid,
			Group_Master groupMaster) {
		int i=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try
		{
		session.beginTransaction();
		String query = "select group_id from  menu_group_master where group_name ='"+groupMaster.getGroup_name()+"'and group_id NOT IN("+ groupid +") and deleted_status= 0";
		//System.out.println("query---------"+query);
		List list=session.createSQLQuery(query).list();
		//System.out.println("size-------"+list.size());
		
		if(list.size()<=0){
	
		    HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			Group_Master group_master=(Group_Master)session.get(Group_Master.class, groupid);
			//System.out.println("hi in dao"+group_master.getGroup_id());
			group_master.setGroup_name(groupMaster.getGroup_name());
			group_master.setStatus(groupMaster.getStatus());
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			group_master.setUpdated_by(usrsessionid);
			group_master.setUpdated_date(new Date());
			session.update(group_master);
			session.getTransaction().commit();
			i=1;
	       }else{
	    	   i=-1;
	       }
		}
	       catch(Exception e)
	       {
	    	   session.getTransaction().rollback();   
	       }
		   finally
		   {
				//System.out.println("insertGroupMaster");
			   if(session!=null){
	                session.close();
	            }
		   }
		
		// TODO Auto-generated method stub
		return i;
	}

	
	public List<String> getGroupId() {
		// TODO Auto-generated method stub
				List list=new ArrayList();
		        //System.out.println("inside get id");
				String qry="select group_id from  menu_group_master where deleted_status=0";
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
		// TODO Auto-generated method stub
		
	}
	public List<String> getGroupName() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 List list=new ArrayList();
			        //System.out.println("inside get id");
					String qry="select group_name from  menu_group_master where deleted_status =0";
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
	
	/*public String updateGroupRole(String parseInt, GroupRole grouprole) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String sql="update menu_group_role set status='DEACTIVE' where group_id="+parseInt+" ";
		try{
		Query query=session.createSQLQuery(sql);
		int update=query.executeUpdate();
		session.getTransaction().commit();
		
		}catch(Exception ex)
		{
			session.close();
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			session.close();
			session.close();
		}
		return "success";
		
	}*/
	public String saveGroup_Role(GroupRole grouprole) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = (Integer) session.save(grouprole);
		session.getTransaction().commit();
		session.close();
		if (i > 0) {
			return "success";
		} else {
			return "fail";
		}
		
	}
	/*public int updateGroupId(int groupId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String sql="update group_role set status = 'DEACTIVE' " +
				"where group_id = '"+groupId+"' and status = 'ACTIVE'";
         int update = 0;
		try
		{
		Query query=session.createSQLQuery(sql);
		update=query.executeUpdate();
		session.getTransaction().commit();
		
		}catch(Exception ex)
		{
			session.close();
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally
		{
			//System.out.println("updateGroupId");
			  if(session!=null){
	                session.close();
	            }
		}
		return update;
	}*/
	
	public String getrolegrouplist(int groupid){
		List<Map<String, Object>> rolepagelist=null;
		String result="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String pagelist="";
		String pagelistdetails="";
		String pageid="";
		try{
		
			//String sql="select page_id from  menu_page_role where role_id ="+ roleid +" and deleted_status=0 and status='ACTIVE'";
			String sql="select role_id from menu_group_role where group_id="+ groupid +" and deleted_status=0 and status='ACTIVE'  ";
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 rolepagelist = query.list();
			 if(rolepagelist.size()>0)
			 {
			 for(int i=0;i<rolepagelist.size();i++){
					Map<String, Object> rs = rolepagelist.get(i);
					pageid=rs.get("role_id").toString();
					pagelist+=pageid+",";
			 }
			 }else{
				 pagelist="";
			 }
			 if(pagelist.length()>0){
					pagelistdetails=pagelist.substring(0,pagelist.length()-1);
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pagelistdetails;
	}
	public String roleList(String grouprolelist){
		List<Map<String, Object>> pagelist=null;
		String result="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String sql="";
		try{
			if(grouprolelist.length()>0){
			 //sql=" select page_id,page_name from menu_page_master where deleted_status=0 and status='ACTIVE' and page_id not in("+ pagerolelist +") and page_type='C' order by page_name ";
			  sql="select role_id,role_name from menu_role_master where role_id not in("+ grouprolelist +") and deleted_status=0 and status='ACTIVE' order by role_name ";
			}else{
				sql="select role_id,role_name from menu_role_master where deleted_status=0 and status='ACTIVE' order by role_name "; 
				//sql=" select page_id,page_name from menu_page_master where deleted_status=0 and status='ACTIVE'  and page_type='C' order by page_name ";	
			}
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 pagelist = query.list();
			
			 if (pagelist.size() > 0) {
					
					for(int i=0;i<pagelist.size();i++){
						Map<String, Object> rs = pagelist.get(i);
						String pageid=rs.get("role_id").toString();
						String pagename=rs.get("role_name").toString();
						result+= pageid +"@";
						result+= pagename+",";
						//System.out.println("result---1---"+result);
					}
				}
			//System.out.println("result--2----"+result);
		}catch(Exception e){e.printStackTrace();}
		finally{
			//System.out.println("getRolelist");
			  if(session!=null){
	                session.close();
	            }
		}
		return result;
	}
	public String getRolelist(){
		List<Map<String, Object>> rolelist=null;
		String result="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="select role_id,role_name from menu_role_master where deleted_status=0 and status='ACTIVE' order by role_name ";
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 rolelist = query.list();
			
			 if (rolelist.size() > 0) {
					
					for(int i=0;i<rolelist.size();i++){
						Map<String, Object> rs = rolelist.get(i);
						String roleid=rs.get("role_id").toString();
						String rolename=rs.get("role_name").toString();
						result+= roleid +"@";
						result+= rolename+",";
						//System.out.println("result---1---"+result);
					}
				}
			//System.out.println("result--2----"+result);
		}catch(Exception e){e.printStackTrace();}
		finally{
			//System.out.println("getRolelist");
			  if(session!=null){
	                session.close();
	            }
		}
		return result;
	}
	
	/*created by ashwini*/
	public boolean getDeletRecord(int grouproleid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String sql=" update menu_group_role set  deleted_status = 1 " +
				    " where grid = "+grouproleid;
		 int update = 0;
		 boolean flag=false;
		try
		{
		Query query=session.createSQLQuery(sql);
		update=query.executeUpdate();
		if(update>0){
			flag=true;
		}else{
			flag=false;
		}
		session.getTransaction().commit();
		
		}catch(Exception ex)
		{
			//session.close();
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally
		{
			  if(session!=null){
	                session.close();
	            }
		}
		return flag;
	}
	
	public int getRoleId(int grouproleid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery("select role_id as role_id from  menu_group_role where grid= "+grouproleid +" and  status='ACTIVE'").addScalar("role_id", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int roleid = list.get(0);
		//System.out.println(roleid);
		return roleid;
	}
	
	public String addRoleid(int roleid,int groupid){
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Map<String, Object>> list=null;
		int grouproleid=0;
		boolean flag=false;
		session.beginTransaction();
		String msg="";
		try{
			
			String sql1="select grid from menu_group_role where group_id="+ groupid +" and role_id="+ roleid + " and status='ACTIVE' and deleted_status=0";
			Query query = session.createSQLQuery(sql1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 list = query.list();
			
			 if (list.size() > 0) {
				 for(int i=0;i<list.size();i++){
						Map<String, Object> rs = list.get(i);
						 grouproleid=Integer.parseInt(rs.get("grid").toString());
						 //System.out.println("grouproleid---1---"+grouproleid);
				
					}
				 
			 }
		
			if(grouproleid==0){
				
				String q=" INSERT INTO `menu_group_role` (`group_id`, `role_id`, `status`, `deleted_status`) VALUES "
						+ "("+groupid+","+roleid+",'ACTIVE',0)";							
				//System.out.println("q---"+q);
				Query query1=session.createSQLQuery(q);
				int a=query1.executeUpdate();
				session.getTransaction().commit();
				/*if(a>0){
					flag=true;
				}else{
					flag=false;
				}*/
				if(a>0)
				{
				msg="Role Id "+roleid+ " Added Successfully";
				}else{
				msg="Role Id "+roleid+ " Not Added ";
				}
			}else{
			
				String sql=" update menu_group_role set status = 'INACTIVE'" +
					    " where grid = "+grouproleid;
			 int update = 0;
				try
				{
				Query query1=session.createSQLQuery(sql);
				update=query1.executeUpdate();
				if(update>0){
					String q=" INSERT INTO `menu_group_role` (`group_id`, `role_id`, `status`, `deleted_status`) VALUES "
							+ "("+groupid+","+roleid+",'ACTIVE',0)";							
					//System.out.println("q---"+q);
					Query query2=session.createSQLQuery(q);
					int a=query2.executeUpdate();
					session.getTransaction().commit();
					/*if(a>0){
						flag=true;
					}else{
						flag=false;
					}*/
					if(a>0)
					{
						msg="Role Id "+roleid+ " Added Successfully";
					}else{
						msg="Role Id "+roleid+ " Not Added ";
					}
				}else{
					//flag=false;
					msg="Role Id "+roleid+ " Not Added ";
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
		return msg;
	}
	
	
	
	public String  getrolelistForGroup(int groupid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String rolelist="";
		String rolename="";
		String rolelistdetails="";
		try{
			String query1=" select mgr.role_id,mrm.role_name from menu_group_role mgr " +
							" INNER JOIN menu_role_master mrm ON mgr.role_id=mrm.role_id and mrm.status='ACTIVE' and mrm.deleted_status=0 " +
							" where mgr.status='ACTIVE' and mgr.deleted_status=0 and mgr.group_id = "+groupid;
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
				rolename=rs.get("role_name").toString();
				rolelist+=rolename+",";
				 //pagelistdetails=pagelist.substring(0,pagelist.length()-1);
			}
			}else{
				rolelist="";
			}
			if(rolelist.length()>0){
				rolelistdetails=rolelist.substring(0,rolelist.length()-1);
			}
			//System.out.println("rolename-----"+rolename);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 if(session!=null){
	             session.close();
	         }
		}
		return rolelistdetails;
	}

	
	public String getUserlist(int groupid){
		String userlistdetails="";
	
		try{
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String userlist="";
			String username="";
		
			try{
				
				String query1="select mug.user_id ,mum.userloginname from  menu_user_master mum "+
						 " INNER JOIN menu_user_group mug ON  mum.user_id =mug.user_id " +
						 " where mug.group_id= "+ groupid  +" and mug.status='ACTIVE' " +
						 " and mum.status='ACTIVE' and mum.deleted_status=0" ;
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
					 //pagelistdetails=pagelist.substring(0,pagelist.length()-1);
				}
				}else{
					userlist="";
				}
				if(userlist.length()>0){
					userlistdetails=userlist.substring(0,userlist.length()-1);
				}
				//System.out.println("userlistdetails-----"+userlistdetails);
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}catch(Exception e){
		e.printStackTrace();
	}
	
		return userlistdetails;
	}
	
}
	
	

