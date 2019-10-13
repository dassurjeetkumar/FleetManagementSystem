package com.trimax.its.transport.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.BusStopGroup;
import com.trimax.its.transport.model.BusStopGroupStop;
import com.trimax.its.transport.model.GroupType;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.util.HibernateUtil;

public class BusStopsGroupDAO {

	
	public int getTotalRecords(String searchterm){
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HttpSession sess=request.getSession();
		String userorgtype=(String)request.getSession().getAttribute("userorgtype");
		Query query;
		List<Integer> list =null;
		try{
		
			 query = session.createSQLQuery("select count(*) as count from bus_stop_group bsg inner join bus_stop bs on bs.bus_stop_id=bsg.bus_stop_id " +
			 		"inner join group_type gt on gt.group_type_id=bsg.group_type_id where bsg.group_name like '%"+searchterm+"%' " +
			 				" or bs.bus_stop_name like '%"+searchterm+"%' or gt.group_type_name like '%"+searchterm+"%'  or bsg.group_id = '"+searchterm+"'")
			 				.addScalar("count", Hibernate.INTEGER);
		

		
		 list = query.list();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		//session.close();
		int cnt = list.get(0);
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total , HttpServletRequest request, String col, String dir){
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		int totalAfterFilter = total;
		
		//JSONArray array = new JSONArray();
		String searchSQL = "";
		String sql ="from BusStopGroup";
		HttpSession sess=request.getSession();
		String userorgtype=(String)request.getSession().getAttribute("userorgtype");
		
		//sql += " order by " + COL_NAME + " " + DIR;

		sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		
		Criteria criteria = session.createCriteria(BusStopGroup.class);
		criteria.add(Restrictions.ne("status", "deleted"));
		criteria.add(Restrictions.ne("group_stop.status", "deleted"));
		criteria.createCriteria("group_stop", "group_stop");
		criteria.createCriteria("group_type_id", "group_type_id");
		criteria.add(Restrictions.eq("deleted_status", 0));
		System.out.println("sSearch------->"+col+dir);
		if(!col.equals("")){
			if(dir.equals("asc")){
			    criteria.addOrder(Order.asc(col));
			}else{
				criteria.addOrder(Order.desc(col));	
			}
		}
		if(!request.getParameter("sSearch").equals("")){
			String search_term = request.getParameter("sSearch");
			
			Junction conditionGroup = Restrictions.disjunction();
			conditionGroup.add(Restrictions.like("group_name","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("group_stop.busStopName","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("group_type_id.group_type_name","%"+search_term+"%" ));
			if(isInteger(search_term))
			{
			int srch=Integer.parseInt(search_term);
			conditionGroup.add(Restrictions.eq("group_id",srch ));
			}
			//conditionGroup.add(Restrictions.like("bus_stop_count","%"+search_term+"%" ));
			criteria.add(conditionGroup);
			
		}
		
		
		System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
		criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
		criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
		System.out.println("My Criteria");
		List<BusStopGroup> list = criteria.list();
		JSONArray array = new JSONArray();
		BusStopsGroupDAO busStopsGroupDAO = new BusStopsGroupDAO();
		for(int i=0;i<list.size();i++){
			JSONArray ja = new JSONArray();
			
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+list.get(i).getGroup_id()+"' value='"+list.get(i).getGroup_id()+"'/>");
			ja.add(list.get(i).getGroup_id());
			if(list.get(i).getGroup_type_id().getGroup_type_id()==1){
				ja.add("");
			}else{
				ja.add(list.get(i).getGroup_stop().getBusStopName()+"(Towards: "+list.get(i).getGroup_stop().getStop_direction()+")"); 
			}
			
			ja.add(list.get(i).getGroup_type_id().getGroup_type_name());
			ja.add(list.get(i).getGroup_name());
			ja.add(list.get(i).getBus_stop_count());
			
			/*for(int j=0;j<listPoint.size();j++){
				
			}*/
			
			
			array.add(ja);
			System.out.println("Array----->"+array);
		}
		
		totalAfterFilter = getTotalRecords(request.getParameter("sSearch"));
		
		
			
			result.put("aaData",array);
			result.put("iTotalRecords", total);
			
			result.put("iTotalDisplayRecords", totalAfterFilter);
		
		
		System.out.println("REsult ------>"+result);
		//session.close();
		
		}
		catch(Exception e){
			System.out.println("Exception---->"+e);
		}
		finally{
			  if(session!=null){
				  //session.close();
	            }
		}
		return result;
	}
	
/*	criteria.add(Restrictions.eq("status", "ACTIVE"));
	//criteria.add(Restrictions.eq("group_stop.status", "approved"));
//	criteria.add(Restrictions.eq("deleted_status", "0"));
//	criteria.add(Restrictions.eq("group_stop_id.status", "Y"));
	criteria.createCriteria("group_stop", "group_stop");
	criteria.createCriteria("group_type_id", "group_type_id");
	criteria.add(Restrictions.eq("deleted_status", 0));*/
	
	@SuppressWarnings("unchecked")
	public List<GroupType> getGroupTypeList(){
		List<GroupType> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from GroupType");
		//query.setMaxResults(20);
		list = (List<GroupType>) query.list();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	                session.close();
	            }	
		}
		
		
		//session.close();
		return list;
		
	}
	
	public String addBus_stop_group(BusStopGroup busStopGroup,Session session){
		session.save(busStopGroup);
		return "success";
		
	}
	public String addBus_stop_group_stop(BusStopGroupStop busStopGroupStop,Session session){
		session.save(busStopGroupStop);
		return "success";
		
	}
	
	public String updateBus_stop_group(int id,String groupname,int upby,int countstop,Session session){
		//session.update(busStopGroup);
		int updated=0;
		Query query=null;
		String sql="update bus_stop_group set bus_stop_count='"+countstop+"',group_name='"+groupname+"' ,updated_date=now(), 	updated_by= '"+upby+"' "  +
				" where group_id='"+id+"' and status='Active'";
		query = session.createSQLQuery(sql);
		updated = query.executeUpdate();
		return "success";
		
	}
	
	public String updateBus_stop_group_stop(int id,int upby,Session session){
		int updated=0;
		Query query=null;
		String sql="update bus_stop_group_stop set status='N', updated_date=now(), 	updated_by= '"+upby+"' "  +
				" where group_id='"+id+"' and status='Y'";
		query = session.createSQLQuery(sql);
		updated = query.executeUpdate();
		return "success";
		
	}
	
	
	public List<BusStopGroup> getBusStopGroupInfo(int id){
		System.out.println("in execute");
		List<BusStopGroup> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from BusStopGroup where group_id='"+id+"'");
		query.setMaxResults(50);
		list = (List<BusStopGroup>) query.list();
		} catch(Exception e){
			System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list;
	}
	
	public List<BusStopGroupStop> getBusStopGroupStopInfo(int id){
		System.out.println("in execute");
		List<BusStopGroupStop> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from BusStopGroupStop where group_id='"+id+"' and status='Y' and bus_stop_group_stop.status!='DELETED' ");
		query.setMaxResults(50);
		list = (List<BusStopGroupStop>) query.list();
		} catch(Exception e){
			System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list;
	}
	
	public List<BusStopGroup> check_trip(int id, Session session){
		System.out.println("in execute");
		List<BusStopGroup> list=null;
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from BusStopGroup where 	bus_stop_id='"+id+"' and 	status='Active' and deleted_status='0' ");
		query.setMaxResults(50);
		list = (List<BusStopGroup>) query.list();
		} catch(Exception e){
			System.out.println(e);
		}
		
		finally{
            if(session!=null){
          //      session.close();
            }
		}
		
		return list; 
	}
	
	
	public List<BusStopGroup> check_Group_Name(String group_name, Session session){
		System.out.println("in execute");
		List<BusStopGroup> list=null;
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from BusStopGroup where 	group_name='"+group_name+"' and 	status='Active' and deleted_status='0'");
		query.setMaxResults(50);
		list = (List<BusStopGroup>) query.list();
		} catch(Exception e){
			System.out.println(e);
		}
		
		finally{
            if(session!=null){
          //      session.close();
            }
		}
		
		return list; 
	}
	
	
	
	
	public List<ScheduleDetails> getScheduleDetails(int busstopid){
		/*Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(ScheduleDetails.class).setProjection(Projections.projectionList().add( Projections.groupProperty("schno.scheduleNumber") ));
		criteria.createAlias("scheduleNumber", "schno");
		Junction conditionGroup = Restrictions.disjunction();
		conditionGroup.add(Restrictions.like("endPoint",busstopid ));
		String[] statuscond = { "NEW", "ACTIVE" };
		conditionGroup.add(Restrictions.in("schno.status", statuscond));	
		conditionGroup.add(Restrictions.eq("schno.deletedStatus", 0));	
		//conditionGroup.add(Restrictions.like("group_stop.busStopName","%"+search_term+"%" ));
		criteria.add(conditionGroup);
		
		//criteria.add(Restrictions.eq("schno.schedule_id", scheduleid));
		List<ScheduleDetails> list = criteria.list();
		return list;*/
		
		List<ScheduleDetails> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from ScheduleDetails where (start_point='"+busstopid+"' or end_point='"+busstopid+"') and deletedStatus='0' group by scheduleNumber.scheduleNumber");
		//query.setMaxResults(500);
		list = (List<ScheduleDetails>) query.list();
		} catch(Exception e){
			System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list;
		
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public String UpdateGropStatus(int groupid, int usrsessionid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int updated=0;
		Query query=null;
		String sql="update bus_stop_group set deleted_status='1', updated_date=now(), 	updated_by= '"+usrsessionid+"' "  +
				" where group_id='"+groupid+"' ";
		query = session.createSQLQuery(sql);
		updated = query.executeUpdate();
		return "success";
	}
	
}
