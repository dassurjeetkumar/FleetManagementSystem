package com.trimax.its.vehicle.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.model.User;
import com.trimax.its.route.model.Floor;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.BreakDownTypeDetails;

public class BreakDownTypeDetailsDao {
	
	
	
	
	public int addbreakdowntypedeails(BreakDownTypeDetails breakdowntype){
		int i=0;
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();
			//String query=" from BreakDownTypeDetails where breakdown_category= '"+breakdowntype.getBreakdown_category()+"' and breakdown_system_type= '"+breakdowntype.getBreakdown_system_type() +"' and breakdown_system_sub_type= '"+ breakdowntype.getBreakdown_system_sub_type()+"' and deleted_status=0";
			String query ="from BreakDownTypeDetails where breakdown_name ='"+breakdowntype.getBreakdown_name()+"'and deleted_status=0 ";
			List list=session.createQuery(query).list();
			//System.out.println("list--------"+list.size());
			if(list.size()<=0){
			 i=(Integer)session.save(breakdowntype);
			}else{
				i=-1;
			}
				tx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return i;
	}
	
	public int getTotalRecords(){
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
		Query	 query = session.createSQLQuery("select count(*) as count from breakdown_type_details where deleted_status = 0 ").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		 cnt = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}
	
	public int getTotalRecordsForCount(String search_term,String viewdeletedrecord){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		String sql="";
		Query query=null;
		try{
		
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
			
			//sql="select breakdown_type_Id,IFNULL(breakdown_name,'NA')breakdown_name ,IFNULL(breakdown_category,'NA') breakdown_category ,IFNULL(breakdown_system_type,'NA')breakdown_system_type ,IFNULL(breakdown_system_sub_type,'NA') breakdown_system_sub_type ,IFNULL(notes,'NA') notes,deleted_status from breakdown_type_details ";
				query= session.createSQLQuery("select count(*) as count  from breakdown_type_details where (breakdown_type_Id like '"+search_term+"%' OR breakdown_name  like '"+search_term+"%' OR breakdown_category like '"+search_term+"%' OR  breakdown_system_type like '"+search_term+"%'  OR breakdown_system_sub_type like '"+search_term+"%' OR notes like '"+search_term+"%' )  order by breakdown_type_Id").addScalar("count", Hibernate.INTEGER);
			}else{
			//sql="select breakdown_type_Id,IFNULL(breakdown_name,'NA')breakdown_name ,IFNULL(breakdown_category,'NA') breakdown_category ,IFNULL(breakdown_system_type,'NA')breakdown_system_type ,IFNULL(breakdown_system_sub_type,'NA') breakdown_system_sub_type ,IFNULL(notes,'NA') notes,deleted_status from breakdown_type_details  where deleted_status = 0";
				query= session.createSQLQuery("select count(*) as count  from breakdown_type_details where deleted_status=0  and (breakdown_type_Id like '"+search_term+"%' OR breakdown_name  like '"+search_term+"%' OR breakdown_category like '"+search_term+"%' OR  breakdown_system_type like '"+search_term+"%'  OR breakdown_system_sub_type like '"+search_term+"%' OR notes like '"+search_term+"%' )  order by breakdown_type_Id").addScalar("count", Hibernate.INTEGER);
			}
			
		List<Integer> list = query.list();
		 cnt = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}
	
	public JSONObject getBreakdownTypeList(int total, HttpServletRequest request,String col,String dir){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		try{
			String sql="";
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
			
			sql="select breakdown_type_Id,IFNULL(breakdown_name,'NA')breakdown_name ,IFNULL(breakdown_category,'NA') breakdown_category ,IFNULL(breakdown_system_type,'NA')breakdown_system_type ,IFNULL(breakdown_system_sub_type,'NA') breakdown_system_sub_type ,IFNULL(notes,'NA') notes,deleted_status from breakdown_type_details where deleted_status in(0,1)";
			}else{
				sql="select breakdown_type_Id,IFNULL(breakdown_name,'NA')breakdown_name ,IFNULL(breakdown_category,'NA') breakdown_category ,IFNULL(breakdown_system_type,'NA')breakdown_system_type ,IFNULL(breakdown_system_sub_type,'NA') breakdown_system_sub_type ,IFNULL(notes,'NA') notes,deleted_status from breakdown_type_details  where deleted_status=0";	
			}
			//System.out.println("sql--------"+sql);
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			String search_term1 = request.getParameter("sSearch");
			System.out.println("search_term-------"+search_term1);
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				System.out.println("search_term---------"+search_term);
				//sql += " and ( breakdown_type_Id like '"+search_term+"%'";
				sql += " and breakdown_name like '"+search_term+"%'";
				sql += " OR breakdown_category like '"+search_term+"%'";
				sql += " OR breakdown_system_type like '"+search_term+"%'";
				sql += " OR breakdown_system_sub_type like '"+search_term+"%'";
				sql += " OR notes like '"+search_term+"%'";
				
			}
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
		
			/*int cntdetails=getTotalRecords();
			if(cntdetails>10)*/
			//{
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			//}
		System.out.println("sql----------"+sql);
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		
		JSONArray array = new JSONArray();
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
					+ rs.get("breakdown_type_Id")
					+ "' value='"
					+ rs.get("breakdown_type_Id") + "'/>");
			ja.add(rs.get("breakdown_type_Id").toString());
			//ja.add(rs.get("breakdown_name").toString());
			ja.add(rs.get("breakdown_name").toString() != null ? rs.get("breakdown_name").toString().replaceAll(" ","&nbsp;") : "");
			ja.add(rs.get("breakdown_category").toString());
			ja.add(rs.get("breakdown_system_type").toString());
			ja.add(rs.get("breakdown_system_sub_type").toString());
			//ja.add(rs.get("notes").toString());
			ja.add(rs.get("notes").toString() != null ? rs.get("notes").toString() : "");
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				String deletedstatus=rs.get("deleted_status").toString();
				System.out.println("deletedstatus---"+deletedstatus);
				
				if(deletedstatus.equalsIgnoreCase("1"))
				{
					//ja.add("Deleted Record");
					ja.add("<input type='hidden' value='N' id='isRocordEligible"+ rs.get("breakdown_type_Id")+"'/>Deleted Record");	

					}else{
					//ja.add("Record in Database");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+rs.get("breakdown_type_Id")+"'/>Record in Database");	

				}
				
				
			}else{
				
			}
			
		
			array.add(ja);
			
		}
		int cnt=0;
			totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			String search_term2= request.getParameter("sSearch");
			//if (search_term2 != null && !search_term2.equals("")) {
				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalRecordsForCount(search_term3,viewdeletedrecord);
			//}
			result.put("iTotalRecords",cnt);

			result.put("iTotalDisplayRecords", cnt);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	
	public BreakDownTypeDetails getmodifydetails(int id){
		Session session=null;
		Transaction tx=null;
		BreakDownTypeDetails breakdowntype=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from BreakDownTypeDetails where breakdown_type_Id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		breakdowntype=(BreakDownTypeDetails)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}finally{
			if (session != null) {
				session.close();
			}
		}
		return breakdowntype;
	}
	
	public int updateBreakDownType(int id,BreakDownTypeDetails breakdowntype){
				boolean flag=false;
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				session.beginTransaction();
				int i=0;
				try{
				
				//BreakDownTypeDetails breakdowntype=(BreakDownTypeDetails)session.load(BreakDownTypeDetails.class,new Integer(id));
				//String query=" from BreakDownTypeDetails where breakdown_category= '"+breakdowntype.getBreakdown_category()+"' and breakdown_system_type= '"+breakdowntype.getBreakdown_system_type() +"' and breakdown_system_sub_type= '"+ breakdowntype.getBreakdown_system_sub_type()+"' and deleted_status=0 and breakdown_type_Id NOT IN("+breakdowntype.getBreakdown_type_Id()+")";
					String query= " from BreakDownTypeDetails where breakdown_name = '"+breakdowntype.getBreakdown_name()+"' and deleted_status=0 and breakdown_type_Id NOT IN("+breakdowntype.getBreakdown_type_Id()+")";
				List list=session.createQuery(query).list();
				//System.out.println("size-------"+list.size());
				if(list.size()<=0)
				{
					BreakDownTypeDetails breakdowntype1=(BreakDownTypeDetails)session.load(BreakDownTypeDetails.class,new Integer(id));
				HttpServletRequest request=ServletActionContext.getRequest();
				User user = (User) request.getSession().getAttribute("user");
				breakdowntype1.setBreakdown_name(breakdowntype.getBreakdown_name());
				breakdowntype1.setBreakdown_category(breakdowntype.getBreakdown_category());
				breakdowntype1.setBreakdown_system_type(breakdowntype.getBreakdown_system_type());
				breakdowntype1.setBreakdown_system_sub_type(breakdowntype.getBreakdown_system_sub_type());
				breakdowntype1.setNotes(breakdowntype.getNotes());
				breakdowntype1.setUpdated_date(new java.util.Date());
				breakdowntype1.setUpdated_by(user.getUserId());
				breakdowntype1.setDeleted_status(0);
				session.update(breakdowntype1);
				session.getTransaction().commit();
				i=1;
				}else{
					i=-1;
				}
				}
				catch(Exception ex){
					session.getTransaction().rollback();
					ex.printStackTrace();
				}
				finally{
					if (session != null) {
						session.close();
					}
				}

				return i;
	}
	
	public boolean deletedBreakDownType(int id){
		//Session session=null;
		//String s="error";
		boolean flag=false;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try{
		BreakDownTypeDetails breakdowntype=(BreakDownTypeDetails)session.load(BreakDownTypeDetails.class,new Integer(id));
		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		breakdowntype.setUpdated_date(new java.util.Date());
		breakdowntype.setUpdated_by(user.getUserId());
		breakdowntype.setDeleted_status(1);
		session.update(breakdowntype);
		session.getTransaction().commit();
		flag=true;
		}
		catch(Exception ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		finally{
			if (session != null) {
				session.close();
			}
		}

		return flag;
	}

}
