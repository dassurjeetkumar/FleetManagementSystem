package com.trimax.its.transport.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.Role;

public class ShiftTypeDetailDao {
	
	
	
	
	public Map<Integer, String> getScheduletypeList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from ScheduleType where status='ACTIVE' and deleted_status=0 order by schedule_type_name");
		
		try{
		List<ScheduleType> list = query.list();
		
		for (ScheduleType shedule : list) {
			//resultMap.put(vendor.getId(),vendor.getVendor_name());
			resultMap.put(shedule.getSchedule_type_id(), shedule.getScheduleName());
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
	
	public int getTotalRecords(HttpServletRequest request,String col,String dir,String viewdeletedrecord)
	{
	
		//JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			String sql;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				sql=" select shift_type.shift_type_id,IFNULL(shift_type.shift_type_name,'NA') shift_type_name ,shift_type.deleted_status , "+
						" IFNULL(shift_type.duration,'NA') duration ,IFNULL(schedule_type.schedule_type_name,'NA')schedule_type_name, " +
						" IFNULL(shift_type.notes,'NA') notes,IFNULL(shift_type.status,'NA') status from shift_type " +
						" INNER JOIN schedule_type ON shift_type.schedule_type_id= schedule_type.schedule_type_id  " 
						 ;
			}else{
				sql=" select shift_type.shift_type_id,IFNULL(shift_type.shift_type_name,'NA') shift_type_name ,shift_type.deleted_status , "+
						" IFNULL(shift_type.duration,'NA') duration ,IFNULL(schedule_type.schedule_type_name,'NA')schedule_type_name, " +
						" IFNULL(shift_type.notes,'NA') notes,IFNULL(shift_type.status,'NA') status from shift_type " +
						" INNER JOIN schedule_type ON shift_type.schedule_type_id= schedule_type.schedule_type_id and schedule_type.deleted_status=0 " +
						" where shift_type.deleted_status= 0";	
			}
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and ( shift_type_id like '%"+search_term+"%'";
				sql += " OR shift_type_name like '%"+search_term+"%'";
				sql += " OR duration like '%"+search_term+"%'";
				sql += " OR schedule_type_name like '%"+search_term+"%'";
				sql += " OR shift_type.notes like '%"+search_term+"%'";
				sql += " OR shift_type.status like '%"+search_term+"%')";
				
			}
			System.out.println("%%%%%"+sql );
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			 cnt =	aliasToValueMapList.size();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (session != null) {
					session.close();
				}
			}
			return cnt;
		
	}
	
	
	public JSONObject getShiftypelist(int total, HttpServletRequest request,String col,String dir,String viewdeletedrecord){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				sql=" select shift_type.shift_type_id,IFNULL(shift_type.shift_type_name,'NA') shift_type_name ,shift_type.deleted_status , "+
						" IFNULL(shift_type.duration,'NA') duration ,IFNULL(schedule_type.schedule_type_name,'NA')schedule_type_name, " +
						" IFNULL(shift_type.notes,'NA') notes,IFNULL(shift_type.status,'NA') status from shift_type " +
						" INNER JOIN schedule_type ON shift_type.schedule_type_id= schedule_type.schedule_type_id  " 
						 ;
			}else{
				sql=" select shift_type.shift_type_id,IFNULL(shift_type.shift_type_name,'NA') shift_type_name ,shift_type.deleted_status , "+
						" IFNULL(shift_type.duration,'NA') duration ,IFNULL(schedule_type.schedule_type_name,'NA')schedule_type_name, " +
						" IFNULL(shift_type.notes,'NA') notes,IFNULL(shift_type.status,'NA') status from shift_type " +
						" INNER JOIN schedule_type ON shift_type.schedule_type_id= schedule_type.schedule_type_id and schedule_type.deleted_status=0 " +
						" where shift_type.deleted_status= 0";	
			}
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and ( shift_type_id like '%"+search_term+"%'";
				sql += " OR shift_type_name like '%"+search_term+"%'";
				sql += " OR duration like '%"+search_term+"%'";
				sql += " OR schedule_type_name like '%"+search_term+"%'";
				sql += " OR shift_type.notes like '%"+search_term+"%'";
				sql += " OR shift_type.status like '%"+search_term+"%')";
				
			}
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
			
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//String[] cols={"","shift_type_id", "shift_type_name","schedule_type_name","duration","notes","status"};
			JSONArray array = new JSONArray();
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("shift_type_id")
						+ "' value='"
						+ rs.get("shift_type_id") + "'/>");
				ja.add(rs.get("shift_type_id").toString());
				ja.add(rs.get("shift_type_name").toString());
				ja.add(rs.get("duration").toString());
				ja.add(rs.get("schedule_type_name").toString());
			
				ja.add(rs.get("notes").toString());
				ja.add(rs.get("status").toString());
				//ja.add(rs.get("deleted_status").toString());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					String deletedstatus=rs.get("deleted_status").toString();
					System.out.println("deletedstatus---"+deletedstatus);
					
					if(deletedstatus.equalsIgnoreCase("1"))
					{
						//ja.add("Deleted Record");	
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ rs.get("shift_type_id")+"'/>Deleted Record");	

						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ rs.get("shift_type_id")+"'/>Record in Database");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				
				
				array.add(ja);
			}
			
			int cnt=0;
			totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			String search_term2= request.getParameter("sSearch");
			
				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalRecords(request,col,dir,viewdeletedrecord);//getTotalRecordsForCount(search_term3);
		
			result.put("iTotalRecords",cnt);
			
			result.put("iTotalDisplayRecords", cnt);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	
	public int addShiftype(ShiftType shiftype,int userid)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		try{
			Query	 query = session.createSQLQuery("select count(*) as count from shift_type where deleted_status = 0 and  shift_type_name = '"+shiftype.getShiftTypeName()+"' and schedule_type_id= "+ shiftype.getSchedule_type_id().getSchedule_type_id()).addScalar("count", Hibernate.INTEGER);
			List<Integer> list = query.list();
			int cnt=list.get(0);
			if(cnt==0)
			{
				shiftype.setCreated_by(userid);
				shiftype.setShift_code("0");
				shiftype.setShift_Type_code("0");
				shiftype.setCreated_date(new java.util.Date());
				 i = (Integer) session.save(shiftype);
				session.getTransaction().commit();
				
			}else{
				i=-1;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}	finally{
			if(session!=null){
	             session.close();
	         }
		}
		
		return i;
	}
	
	public ShiftType getEditShifttype(int id) {
		// BusStops busstops = new BusStops();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		ShiftType shiftype = (ShiftType) session.get(ShiftType.class, new Integer(id));
		return shiftype;

	}
	
	
	public int updateShiftype(ShiftType shiftype,int userid)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int n=0;
		try{
			Query	 query = session.createSQLQuery("select count(*) as count from shift_type where deleted_status = 0 and  shift_type_name = '"+shiftype.getShiftTypeName()+"' and schedule_type_id= "+ shiftype.getSchedule_type_id().getSchedule_type_id() +" and  shift_type_id NOT IN ( "+ shiftype.getId() +")").addScalar("count", Hibernate.INTEGER);
			List<Integer> list = query.list();
			int cnt=list.get(0);
			if(cnt==0)
			{
				ShiftType shiftype1 = (ShiftType) session.get(ShiftType.class,shiftype.getId());
				shiftype1.setShiftTypeName(shiftype.getShiftTypeName());
				shiftype1.setSchedule_type_id(shiftype.getSchedule_type_id());
				shiftype1.setStatus(shiftype.getStatus());
				shiftype1.setNotes(shiftype.getNotes());
				shiftype1.setUpdated_by(userid);
				shiftype1.setUpdated_date(new java.util.Date());
				shiftype1.setDuration(shiftype.getDuration());
				session.update(shiftype1);
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
	
	public boolean getDeletedRecord(ShiftType shiftype,int userid,int id)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		try{
			
			ShiftType shiftype1 = (ShiftType) session.get(ShiftType.class,id);
			shiftype1.setDeleted_status(1);
			//shiftype1.setStatus("INACTIVE");
			shiftype1.setUpdated_by(userid);
			shiftype1.setUpdated_date(new java.util.Date());
			session.update(shiftype1);
			session.getTransaction().commit();
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	

}
