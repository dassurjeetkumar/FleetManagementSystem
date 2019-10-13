package com.trimax.its.utility.dao;

import java.io.PrintWriter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.MenuPageRole;
import com.trimax.its.utility.model.Role;

public class PageRoleDao {
	
	
	
	//public JSONObject getPageRoleList(int total, HttpServletRequest request,int roleid,String col,String dir){
	public JSONObject getPageRoleList(int total,HttpServletRequest request,int roleid){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try{
			String sql="";
			sql=" select menu_page_role.page_role_id,menu_page_role.page_id,menu_page_master.page_name,menu_page_role.readaccess,menu_page_role.createaccess,menu_page_master.deleted_status "
				  +" from menu_page_role INNER JOIN menu_page_master ON menu_page_master.page_id=menu_page_role.page_id "
					+" where menu_page_role.role_id="+ roleid +" and menu_page_master.status='ACTIVE' and menu_page_role.deleted_status=0 and menu_page_master.deleted_status=0 and menu_page_role.status='ACTIVE' and menu_page_master.page_type = 'C' ";
			//System.out.println("sql--------"+sql);
			
				
			/*if(!request.getParameter("sSearch").equals("")){
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
				//sql += " and (vendor_id like '%"+search_term+"%'";
				sql += " and ( page_id like '"+search_term+"%'";
				//sql += " OR page_name like '%"+search_term+"%'";
				//sql += " OR note like '%"+search_term+"%'";
				sql += " OR page_name like '"+search_term+"%')";
				
			}*/
			
			/*if(!col.equals("")){
				if(dir.equals("asc")){
				    //criteria.addOrder(Order.asc(col));
					sql += " order by "+col+ " asc";
				}else{
					//criteria.addOrder(Order.desc(col));	
					sql += " order by "+col+" desc";
				}
			}*/
			
			////System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
			//sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");	
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		
		JSONArray array = new JSONArray();
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			ja.add(rs.get("page_id").toString());
			ja.add(rs.get("page_name").toString());
			String readaccess=rs.get("readaccess").toString();
			String createaccess=rs.get("createaccess").toString();
				if(readaccess.equalsIgnoreCase("0")){
					ja.add("<input type='checkbox' class='group-checkable'  disabled='disabled'   data-set='#sample_2 .checkboxes' id='"+ rs.get("readaccess") + "' value='"+ rs.get("readaccess") + "'/>");	
				}else{
					ja.add("<input type='checkbox' class='group-checkable' checked='checked' disabled='disabled' data-set='#sample_2 .checkboxes' id='"+ rs.get("readaccess")+ "' value='"+ rs.get("readaccess") + "'/>");
				}
if(createaccess.equalsIgnoreCase("0")){
	ja.add("<input type='checkbox' class='group-checkable' disabled='disabled' data-set='#sample_2 .checkboxes' id='"+ rs.get("createaccess")+ "' value='"+ rs.get("createaccess") + "'/>");
				}else{
					ja.add("<input type='checkbox' class='group-checkable'  checked='checked' disabled='disabled' data-set='#sample_2 .checkboxes' id='"+ rs.get("createaccess")+ "' value='"+ rs.get("createaccess") + "'/>");
				}
			ja.add(" ");
			ja.add("<a class='delete' id='pageroleid_delete_"+ rs.get("page_role_id").toString()+ "' value='"+ rs.get("page_role_id") + "'   href=''> Delete</a>");
			array.add(ja);
			
		}
		int cnt=0;
			totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			result.put("iTotalRecords",totalAfterFilter);
			result.put("iTotalDisplayRecords", totalAfterFilter);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	
	public String getuserpagelist(int userid){
		List<Map<String, Object>> rolepagelist=null;
		String result="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String pagelist="";
		String pagelistdetails="";
		String pageid="";
		try{
			String sql="select page_id from  menu_user_role_page where user_id ="+ userid +" and deleted_status=0 and status='ACTIVE'";
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 rolepagelist = query.list();
			 if(rolepagelist.size()>0)
			 {
			 for(int i=0;i<rolepagelist.size();i++){
					Map<String, Object> rs = rolepagelist.get(i);
					pageid=rs.get("page_id").toString();
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
	
	public String getrolepagelist(int roleid){
		List<Map<String, Object>> rolepagelist=null;
		String result="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String pagelist="";
		String pagelistdetails="";
		String pageid="";
		try{
			String sql="select page_id from  menu_page_role where role_id ="+ roleid +" and deleted_status=0 and status='ACTIVE'";
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 rolepagelist = query.list();
			 if(rolepagelist.size()>0)
			 {
			 for(int i=0;i<rolepagelist.size();i++){
					Map<String, Object> rs = rolepagelist.get(i);
					pageid=rs.get("page_id").toString();
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
	
	public String pageList(String pagerolelist){
		List<Map<String, Object>> pagelist=null;
		String result="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String sql="";
		try{
			if(pagerolelist.length()>0){
			 sql=" select page_id,page_name from menu_page_master where deleted_status=0 and status='ACTIVE' and page_id not in("+ pagerolelist +") and page_type='C' order by page_name ";
			}else{
				 sql=" select page_id,page_name from menu_page_master where deleted_status=0 and status='ACTIVE'  and page_type='C' order by page_name ";	
			}
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 pagelist = query.list();
			
			 if (pagelist.size() > 0) {
					
					for(int i=0;i<pagelist.size();i++){
						Map<String, Object> rs = pagelist.get(i);
						String pageid=rs.get("page_id").toString();
						String pagename=rs.get("page_name").toString();
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
	
	
	public void getDeletRecord(int pageroleid,int userid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		/*String sql=" update menu_page_role set deleted_status = 1 " +
				    " where page_role_id = "+pageroleid;*/
		//System.out.println("pageroleid-------"+pageroleid);
		MenuPageRole menupagerole1 = (MenuPageRole) session.get(MenuPageRole.class,pageroleid);
		//menupagerole1.setStatus("DEACTIVE");
		//System.out.println("status--------"+menupagerole1.getDeleted_status());
		menupagerole1.setDeleted_status(1);
		menupagerole1.setUpdated_by(userid);
		menupagerole1.setUpdated_date(new java.util.Date());
		session.update(menupagerole1);
		session.getTransaction().commit();
		
	}
	
	public int getPageId(int pageroleid,int userid)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int pageid=0;
		try{
		/*String sql=" update menu_page_role set deleted_status = 1 " +
				    " where page_role_id = "+pageroleid;*/
		//System.out.println("pageroleid-------"+pageroleid);
		MenuPageRole menupagerole1 = (MenuPageRole) session.get(MenuPageRole.class,pageroleid);
		pageid=menupagerole1.getPage_id();
		//System.out.println("pageid------"+pageid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pageid;
	}
	
	//public boolean addPageId(int pageid,int roleid,int readaccess,int writeaccess,int userid){
		public String addPageId(MenuPageRole menupagerole,int userid){
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Map<String, Object>> list=null;
		int pageroleid=0;
		boolean flag=false;
		 session.beginTransaction();
		 String msg="";
		 int pageid=0;
		try{
			pageid=menupagerole.getPage_id();
			String sql1="select page_role_id as page_role_id from menu_page_role where page_id="+ menupagerole.getPage_id() +" and role_id="+ menupagerole.getRole_id() + " and status='ACTIVE' and deleted_status=0";
			Query query = session.createSQLQuery(sql1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 list = query.list();
			
			 if (list.size() > 0) {
				 for(int i=0;i<list.size();i++){
						Map<String, Object> rs = list.get(i);
						pageroleid=Integer.parseInt(rs.get("page_role_id").toString());
						 //System.out.println("pageroleid---1/////////////---"+pageroleid);
					}
				 
			 }
			
			if(pageroleid==0){
				
				menupagerole.setCreated_by(userid);
				menupagerole.setStatus("ACTIVE");
				menupagerole.setCreated_date(new java.util.Date());
				int a = (Integer) session.save(menupagerole);
				session.getTransaction().commit();
				//msg="Page Id "+ pageid +"Added Successfully";
				
				if(a>0){
					msg="Page  Id "+pageid +" Added Successfully";
				}else{
					msg="Page Id "+pageid+" Not Created";
				}
				
			}else{
			
				MenuPageRole menupagerole1 = (MenuPageRole) session.get(MenuPageRole.class,pageroleid);
				menupagerole1.setStatus("INACTIVE");
				menupagerole1.setUpdated_by(userid);
				menupagerole1.setUpdated_date(new java.util.Date());
				session.update(menupagerole1);
				
				try
				{
					menupagerole.setStatus("ACTIVE");
					menupagerole.setCreated_by(userid);
					menupagerole.setCreated_date(new java.util.Date());
					int a = (Integer) session.save(menupagerole);
					session.getTransaction().commit();
					
					if(a>0){
						msg="Page  Id "+pageid +" Added Successfully";
					}else{
						msg="Page Id "+pageid+" Not Created";
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
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return msg;
	}
	
	
	
	

}
