package com.trimax.its.utility.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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

import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.fare.model.Farechart_Master;
import com.trimax.its.fare.model.LuggageType;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.model.User;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.usermanagement.model.DesignationType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.utility.model.Page_Master;

public class PageMasterDao {

	public List<String> getParentId() {
		// TODO Auto-generated method stub
		List list=new ArrayList();
        //System.out.println("inside get id");
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        try{
		String qry="select page_id from menu_page_master where status='ACTIVE' and deleted_status=0";
		//Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		Query query=session.createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("page_id").toString());
			}
		}
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	if (session != null) {
				session.close();
			}
        }
		
		return list;
	
	}

	public List<String> getParentName() {	
		// TODO Auto-generated method stub
		 List list=new ArrayList();
	        //System.out.println("inside get id");
	        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	        try{
			String qry="select page_name from menu_page_master where status ='ACTIVE'";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("page_name").toString());
				}
			}
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally{
	        	if (session != null) {
					session.close();
				}	
	        }
			//HibernateUtil.closeSession();
			return list;
	
	}
	
	public String getName(int id)
	{
		String name="";
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Query query = session.createSQLQuery("select page_name as pagename from  menu_page_master where page_id="+id).addScalar("pagename", Hibernate.STRING);
	List<String> list = query.list();
	if(list.size()>0){
	 name = list.get(0);
	}else{
		name="Main menu";
				}
	//System.out.println("name-------"+name);
	return name;
	}
	public Map<Integer, String> getParentPagedetails() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Page_Master where status='ACTIVE' and deleted_status=0 order by page_id");
		
		try{
		List<Page_Master> list = query.list();
		
		for (Page_Master pagemaster : list) {
			resultMap.put(pagemaster.getPage_id(),pagemaster.getPage_name()+"("+ (pagemaster.getPage_type().trim().equalsIgnoreCase("C") ? "ITS" : "DOA" ) +")");
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		//System.out.println("resultMap-------"+resultMap);
		return resultMap;
		

	}
	
	
	public List<String> getPageid(String pagetype) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			/*String qry = "select org_chart_id from org_chart where org_name!='(NULL)' and org_type_id="
					+ orgtypeid + "";*/
			String qry="Select page_id from  menu_page_master  where status='ACTIVE' and deleted_status=0 and page_type= '"+ pagetype +"' order by page_id";

			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
		
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("page_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}
	
	public List<String> getPageName(String pagetype) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			/*String qry = "select org_chart_id from org_chart where org_name!='(NULL)' and org_type_id="
					+ orgtypeid + "";*/
			String qry="Select page_name,page_type  from  menu_page_master  where status='ACTIVE' and deleted_status=0 and page_type= '"+ pagetype +"' order by page_id";

			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			String pagedetails="";
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					String pagename=rs.get("page_name").toString();
					String page_type=rs.get("page_type").toString();
					
					if(page_type.equalsIgnoreCase("C"))
					{
						pagedetails=pagename+"[ITS]";
						//pagename + "|";
					}else{
						pagedetails=pagename+"[DOA]";
					}
					
					list.add(pagedetails);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}
	
	public List<Page_Master> getParentPagelistdetails(String pagetype){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Page_Master> list=null;
		Query query = session.createQuery("from Page_Master where status='ACTIVE' and deleted_status=0 and page_type="+ pagetype +"order by page_id");
		try{
			 list = query.list();	
		}catch(Exception e){e.printStackTrace();}
		finally{
			if (session != null) {
				session.close();
			}
		}
		return list;
	}
	
	
	public int getTotalRecords() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		HttpServletRequest request = ServletActionContext.getRequest();	
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		try{
			String sql="";
			//sql="select vendor_id,IFNULL(vendor_name,'NA')vendor_name,IFNULL(vendor_contact_person,'NA')vendor_contact_person,IFNULL(note,'NA')note,IFNULL(status,'NA')status from vendor where deleted_status=0 ";
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				sql="select * from ( select page_id,IFNULL(page_name,'NA')page_name,IFNULL(path,'NA')path,status,parent_id,level,sequence,deleted_status,if(page_type = 'D','DOA','ITS') as page_type  from menu_page_master  ) a  where deleted_status in (1,0)  ";
			}else{
			sql="select * from ( select page_id,IFNULL(page_name,'NA')page_name,IFNULL(path,'NA')path,status,parent_id,level,sequence,deleted_status,if(page_type = 'D','DOA','ITS') as page_type  from menu_page_master  ) a where deleted_status=0  ";
			}
			
			
			if(!request.getParameter("sSearch").equals("")){
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
				//sql += " and (vendor_id like '%"+search_term+"%'";
				sql += " and ( page_id like '%"+search_term+"%'";
				sql += " OR page_name like '%"+search_term+"%'";
				sql += " OR path like '%"+search_term+"%'";
				sql += " OR status like '%"+search_term+"%'";
				sql += " OR level like '%"+search_term+"%'";
				sql += " OR page_type like '%"+search_term+"%'";
				sql += " OR sequence like '%"+search_term+"%')";
				
			}
			//System.out.println("dir---------"+dir);
		/*	if(!col.equals("asc")){
				if(dir.equals("asc")){
				    //criteria.addOrder(Order.asc(col));
					sql += " order by "+col+ " asc";
				}else{
					//criteria.addOrder(Order.desc(col));	
					sql += " order by "+col+" desc";
				}
			}*/
			
			
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			 cnt=aliasToValueMapList.size();
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			
				//session.close();
			
		}
		return cnt;
	}
	
	/*public int getTotalRecordsForPage(String search_term,String viewdeletedrecord ) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query=null;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			query = session.createSQLQuery("select count(*) as count from menu_page_master where (page_id like '"+search_term+"%' OR page_name like '"+search_term+"%' OR path like '"+search_term+"%' OR status like '"+search_term+"%' OR level like '"+search_term+"%' OR sequence like '"+search_term+"%' OR page_type like '"+search_term+"%') order by page_id").addScalar("count", Hibernate.INTEGER);
		}else{
			query = session.createSQLQuery("select count(*) as count from menu_page_master where  deleted_status= 0  and (page_id like '"+search_term+"%' OR page_name like '"+search_term+"%' OR path like '"+search_term+"%' OR status like '"+search_term+"%' OR level like '"+search_term+"%' OR sequence like '"+search_term+"%' OR page_type like '"+search_term+"%' ) order by page_id").addScalar("count", Hibernate.INTEGER);	
		}
		//Query query = session.createSQLQuery("select count(*) as count from menu_page_master where  deleted_status= 0  and (page_id like '"+search_term+"%' OR page_name like '"+search_term+"%' OR path like '"+search_term+"%' OR status like '"+search_term+"%' OR level like '"+search_term+"%' OR sequence like '"+search_term+"%' ) order by page_id").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		//System.out.println(cnt);
		return cnt;
	}*/
	
	/*@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir) {
		JSONObject result = new JSONObject();
		Session session=null;
		try {
			int totalAfterFilter = total;

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Page_Master.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("id"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				
				conditionGroup.add(Restrictions.like("page_name", "%"+ search_term + "%"));
				//conditionGroup.add(Restrictions.like("page_id",  search_term + "%"));
				//conditionGroup.add(Restrictions.like("page_name", ""+ search_term + "%"));
				//conditionGroup.add(Restrictions.like("path",  search_term + "%"));
				//conditionGroup.add(Restrictions.like("level",  search_term + "%"));
				//conditionGroup.add(Restrictions.like("sequence",  search_term + "%"));
				conditionGroup.add(Restrictions.like("status",search_term,MatchMode.START ));
				
				criteria.add(conditionGroup);

			}
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<Page_Master> list = criteria.list();
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getPage_id()
						+ "' value='"
						+ list.get(i).getPage_id() + "'/>");
				ja.add(list.get(i).getPage_id());
				ja.add(list.get(i).getPage_name());
				ja.add(list.get(i).getPath());

				ja.add(list.get(i).getStatus());

				String parentpathname=getName(list.get(i).getParent_id());
				
				ja.add(parentpathname);
				ja.add(list.get(i).getLevel());
				ja.add(list.get(i).getSequence());

				array.add(ja);
				//System.out.println("Array----->" + array);
			}

			
			result.put("aaData", array);
			String search_term = request.getParameter("sSearch").trim();
			totalAfterFilter = getTotalRecordsForPage(search_term);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if(session!=null && session.isOpen()){
			 session.close();
		 }
		return result;
	}
	*/
	
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir) {
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		try{
			String sql="";
			//sql="select vendor_id,IFNULL(vendor_name,'NA')vendor_name,IFNULL(vendor_contact_person,'NA')vendor_contact_person,IFNULL(note,'NA')note,IFNULL(status,'NA')status from vendor where deleted_status=0 ";
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				sql="select * from ( select page_id,IFNULL(page_name,'NA')page_name,IFNULL(path,'NA')path,status,parent_id,level,sequence,deleted_status,if(page_type = 'D','DOA','ITS') as page_type  from menu_page_master  ) a  where deleted_status in (1,0)  ";
			}else{
			sql="select * from ( select page_id,IFNULL(page_name,'NA')page_name,IFNULL(path,'NA')path,status,parent_id,level,sequence,deleted_status,if(page_type = 'D','DOA','ITS') as page_type  from menu_page_master  ) a where deleted_status=0  ";
			}
			
			
			if(!request.getParameter("sSearch").equals("")){
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
				//sql += " and (vendor_id like '%"+search_term+"%'";
				sql += " and ( page_id like '%"+search_term+"%'";
				sql += " OR page_name like '%"+search_term+"%'";
				sql += " OR path like '%"+search_term+"%'";
				sql += " OR status like '%"+search_term+"%'";
				sql += " OR level like '%"+search_term+"%'";
				sql += " OR page_type like '%"+search_term+"%'";
				sql += " OR sequence like '%"+search_term+"%')";
				
			}
			//System.out.println("dir---------"+dir);
			if(!col.equals("asc")){
				if(dir.equals("asc")){
				    //criteria.addOrder(Order.asc(col));
					sql += " order by "+col+ " asc";
				}else{
					//criteria.addOrder(Order.desc(col));	
					sql += " order by "+col+" desc";
				}
			}
			
			
			//int cntdetails=getTotalRecords();
			//if(cntdetails>10){
			//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		//}
		//System.out.println("sql----------"+sql);
		
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		JSONArray array = new JSONArray();
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
					+ rs.get("page_id")
					+ "' value='"
					+ rs.get("page_id") + "'/>");
			ja.add(rs.get("page_id").toString());
			//ja.add(rs.get("page_name").toString());
			ja.add(rs.get("page_name").toString()!=null ? rs.get("page_name").toString().replaceAll(" ","&nbsp;") : "");
			//ja.add(rs.get("path").toString());
			ja.add(rs.get("path").toString()!=null ? rs.get("path").toString().replaceAll(" ","&nbsp;") : "");
			ja.add(rs.get("status").toString());
			String parentpathname=getName(Integer.parseInt(rs.get("parent_id").toString()));
			ja.add(parentpathname);
			ja.add(rs.get("level").toString());
			ja.add(rs.get("sequence").toString());
		    /*if(rs.get("page_type").toString().equalsIgnoreCase("C"))
		    {
		    	ja.add("ITS");
		    }else{
		    	ja.add("DAO");
		    }*/
			ja.add(rs.get("page_type").toString());
					
					
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				String deletedstatus=rs.get("deleted_status").toString();
				System.out.println("deletedstatus---"+deletedstatus);
				
				if(deletedstatus.equalsIgnoreCase("1"))
				{
					ja.add("<input type='hidden' value='N' id='isRocordEligible"+rs.get("page_id")+"'/>Deleted Record");	
					}else{
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+rs.get("page_id")+"'/>Record in Database");	
				}
				
				
			}else{
				
			}
			
			
			
			array.add(ja);
			////System.out.println("Array----->"+array);
		}
		

			
			//totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			//if(!request.getParameter("sSearch").equals("")){
			String search_term = request.getParameter("sSearch").trim();
			 cnt=getTotalRecords();
			//}
			result.put("iTotalRecords", cnt);
			result.put("iTotalDisplayRecords", cnt);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				 session.close();
			 }
		}
		return result;
	}
	
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir) {
		Session session=null;
		int cnt=0;
		try {
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Page_Master.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("id"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("page_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("path", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status",search_term,MatchMode.START ));
				
				criteria.add(conditionGroup);

			}
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<Page_Master> list = criteria.list();
			cnt = list.size();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
			 if(session!=null && session.isOpen()){
				 session.close();
			 }
			}
			return cnt;
	}
	

	public int insertPageMaster(Page_Master page,int userid,int parentpageid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		//page.setStatus("DACTIVE");
		int i=0;
		try{
		//Query	 query = session.createSQLQuery("select count(*) as count from menu_page_master where deleted_status = 0 and  page_name ='"+page.getPage_name()+"'").addScalar("count", Hibernate.INTEGER);
			//Query	 query = session.createSQLQuery("select count(*) as count from menu_page_master where deleted_status = 0 and  page_name ='"+page.getPage_name()+"' and page_type='"+page.getPage_type()+"'").addScalar("count", Hibernate.INTEGER);
			Query	 query = session.createSQLQuery("select count(*) as count from menu_page_master where deleted_status = 0 and  page_name ='"+page.getPage_name()+"' and page_type='"+page.getPage_type()+"' and parent_id="+parentpageid+" ").addScalar("count", Hibernate.INTEGER);
			System.out.println("query------1111111111111111----------"+query);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		//System.out.println("cnt--------"+cnt);
	
		if(cnt==0){
			page.setParent_id(parentpageid);
			page.setUpdated_by(0);
			page.setCreated_by(userid);
		 i = (Integer) session.save(page);
		session.getTransaction().commit();
		
		//session.close();
		}else{
			i=-1;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(session!=null){
	             session.close();
	         }
		}
			return i;

	}
	/*public int insertPageRole(Page_Master page,String userid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		try
		{
		String qry="insert into page_role(page_id,role_id,status,deleted_status,created_by) values('"+page.getPage_id()+"','0','ACTIVE','0','"+userid+"')";
		Query query=session.createSQLQuery(qry);
		i= query.executeUpdate();
		
		session.getTransaction().commit();
		}
		catch(Exception e)
		{
			session.getTransaction().rollback();

		}
		session.close();
		return i;
		if (i > 0) {
			return "success";
		} else {
			return "fail";
		}

	}*/

	public int saveEDitedPageDetails(String requestType, int pageid, Page_Master pageMaster) {
		
		   Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		   session.beginTransaction();
		   int n=0;
		try{
		 if(pageMaster.getPath().length()==0){
			 pageMaster.setPath("#");
		 }
			String query = "select page_id from  menu_page_master where deleted_status=0 and page_name ='"+pageMaster.getPage_name()+"' and page_type='"+pageMaster.getPage_type()+"' and page_id NOT IN("+ pageid +")";
			//System.out.println("query---------"+query);
			List list=session.createSQLQuery(query).list();
			//System.out.println("size-------"+list.size());
			if(list.size()==0)
			{
		    //System.out.println("hi in dao"+pageMaster.getParent_id());
		    HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			Page_Master page_master=(Page_Master)session.get(Page_Master.class, pageid);
			//System.out.println("hi in dao"+page_master.getParent_id());
			page_master.setPage_name(pageMaster.getPage_name());
			page_master.setParent_id(pageMaster.getParent_id());
			page_master.setPath(pageMaster.getPath());
			page_master.setLevel(pageMaster.getLevel());
			page_master.setStatus(pageMaster.getStatus());
			page_master.setSequence(pageMaster.getSequence());
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			page_master.setUpdated_by(usrsessionid);
			page_master.setUpdated_date(new Date());
			page_master.setPage_type(pageMaster.getPage_type());
			session.update(page_master);
			session.getTransaction().commit();
			//session.close();
			n=1;
			}else{
				n=-1;
			}
		 }
		catch(Exception e ){
	}finally{
		if(session!=null){
            session.close();
        }
	}
		// TODO Auto-generated method stub
		return n;
	}
	
public int getDeletePageRecord(int pageid, Page_Master pageMaster,int userid){
	   Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	   session.beginTransaction();
	   int n=0;
	   try{
		   Page_Master page_master=(Page_Master)session.get(Page_Master.class, pageid);
		   page_master.setDeleted_status(1);
		   page_master.setUpdated_by(userid);
		   page_master.setUpdated_date(new java.util.Date());
		   session.update(page_master);
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
	
	public Page_Master getPageDetails(int pageid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Page_Master pagemaster = (Page_Master) session.get(
				Page_Master.class, new Integer(pageid));
		//System.out.println("--------->>>" + pagemaster.getPage_name());
		return pagemaster;
	
	}
	public String getParentName(int pageId)
	{
		
		Query query=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String parentName="";
		try{
		String sql="select page_name from menu_page_master where page_id="+pageId;
		
	 	org.hibernate.Transaction tx=session.beginTransaction();
		query = session.createSQLQuery(sql);
		
		if(query.list().size()>0){
			parentName=query.uniqueResult().toString();
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
	            session.close();
	        }
		}
		return parentName;
	}
	

	public int getMaxSeqId(int parentid) {
		// TODO Auto-generated method stub
		//System.out.println("hi i am in getMax ID");
		Query query=null;
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		 int maxseqid=0;
		try{
		String sql = "select IFNULL(max(sequence),0)+1 from menu_page_master where status='ACTIVE' AND parent_id="+parentid+"";
		
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	 	org.hibernate.Transaction tx=session.beginTransaction();
		query = session.createSQLQuery(sql);
		 maxseqid=Integer.parseInt(query.uniqueResult().toString());
		//System.out.println("maxseq===>"+maxseqid);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
	            session.close();
	        }
		}
		return maxseqid;
	}

	public int getLevel(int parentid) {
		//System.out.println("hi i am in getMax ID");
		Query query=null;
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		 int level=0;
		try{
		String sql ="select IFNULL(max(level),0)+1 from menu_page_master where status='ACTIVE' AND page_id="+parentid+"";
		
		//System.out.println("sql--------"+sql);
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	 	org.hibernate.Transaction tx=session.beginTransaction();
		query = session.createSQLQuery(sql);
		 level=Integer.parseInt(query.uniqueResult().toString());
		//System.out.println("level of child===>"+level);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
	            session.close();
	        }
		}
		return level;
		// TODO Auto-generated method stub
		
	}
	
	
	public String  getuserlistForPage(int pageid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String userlist="";
		String username="";
		String userlistdetails="";
		try{
			
			String query1=" select murp.user_id,mum.userloginname from  menu_user_role_page murp " +
						  " INNER JOIN menu_user_master mum ON mum.user_id=murp.user_id and  mum.status='ACTIVE' and mum.deleted_status=0 " +
						  " where murp.page_id=" + pageid + " and murp.deleted_status=0 and murp.status='ACTIVE' ";
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
	
	
	public String  getrolelistForPage(int pageid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String rolelist="";
		String rolename="";
		String rolelistdetails="";
		try{
			
			
			String query1 =" select mrm.role_id, mrm.role_name from menu_page_role mpr " +
						  " INNER JOIN menu_role_master mrm ON mpr.role_id=mrm.role_id and mrm.status='ACTIVE' and mrm.deleted_status=0 " +
						  " where mpr.page_id = " + pageid +" and mpr.deleted_status=0 and mpr.status='ACTIVE'" ;
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
				
			}
			}else{
				rolelist="";
			}
			
			//System.out.println("rolelist-----"+rolelist);
			if(rolelist.length()>0){
				rolelistdetails=rolelist.substring(0,rolelist.length()-1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 if(session!=null){
	             session.close();
	         }
		}
		return rolelistdetails;
	}
	
	
	public String getchildlist(int pageid,int parentid)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String pagelist="";
		String pagename="";
		String childpagelistdetails="";
		
		/*if(parentid==0)
		{*/
		try{
			String query1="select page_id,page_name from menu_page_master where parent_id= "+ pageid +" and deleted_status=0 and status='ACTIVE'";
		//System.out.println("query----///////////////-----"+query1);
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
			
		}
		}else{
			pagelist="";
			
		}
		
		//System.out.println("pagelist-----"+pagelist);
		if(pagelist.length()>0){
			childpagelistdetails=pagelist.substring(0,pagelist.length()-1);
		}else{
			childpagelistdetails="0";
		}
		
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 if(session!=null){
	             session.close();
	         }
		}
		/*}else{
			childpagelistdetails="0";
		}*/
		return childpagelistdetails;
	}
	
	public int parentiddetails(int pageid){
		int parentid=0;
		try{
			
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery("select parent_id as parent_id from menu_page_master where page_id="+pageid).addScalar("parent_id", Hibernate.INTEGER);
			List<Integer> list = query.list();
			if(list.size()>0){
				parentid = list.get(0);
			}else{
				parentid=0;
						}
			//System.out.println("name-------"+parentid);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return parentid;
	}

	
	
	public String getMasterPageRoleRecords(){
		Session session = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Map<String, Object>> aliasToValueMapList=null;
		String roleidvalue="";
		try{
			int userid=Integer.parseInt(request.getSession().getAttribute("userid").toString());
			System.out.println("userid--------------"+userid);
			String sql=" select sys_value  from default_system_veriable where sys_key='MASTER_PAGE_ROLE' and deleted_status='0' and status= 'Y' ";
			//session = HibernateUtil.getSession("");
			System.out.println("sql--------"+sql);
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 aliasToValueMapList = query.list();
			System.out.println("aliasToValueMapList------"+aliasToValueMapList.size());
			if(aliasToValueMapList.size()>0){
				Map<String, Object> rs = aliasToValueMapList.get(0);
				 roleidvalue=rs.get("sys_value").toString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return roleidvalue;
	}
	

	
}
