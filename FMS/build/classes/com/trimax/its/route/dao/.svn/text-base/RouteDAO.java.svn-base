package com.trimax.its.route.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.trimax.its.common.Common;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.BusStopRouteInfo;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.route.model.RouteMap;
import com.trimax.its.route.model.RoutePlatformRel;
import com.trimax.its.route.model.RoutePoint;
import com.trimax.its.route.model.RouteType;
import com.trimax.its.route.model.Route;
//import com.trimax.its.transport.model.RouteJson;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.RoadType;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.transport.model.ScheduleService;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class RouteDAO {
	Logger logger = TrimaxLogger.getTrimaxLogger();
	
	@SuppressWarnings("unchecked")
	public List<Route> getRouteList(){
//		System.out.println("in execute list");
		List<Route> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from Route where (effective_till >= DATE_FORMAT(now(),'%Y-%m-%d') or effective_till ='0000-00-00' or effective_till is null) and deleted_status='0' ");

		query.setMaxResults(100);
		list = (List<Route>) query.list();
		} catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Route> getRouteListDropDown(){
		//System.out.println("in execute");
		List<Route> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
		String today = formatter.format( new java.util.Date() );
		try{
			Criteria criteria = session.createCriteria(Route.class);
			Junction conditionGroup = Restrictions.disjunction();
			Junction conditionGroup1 = Restrictions.disjunction();
			conditionGroup1.add(Restrictions.le("effective_from",today)); 
			Junction conditionGroup2 = Restrictions.disjunction();
			conditionGroup2.add(Restrictions.ge("effective_till", today)); 
			conditionGroup2.add(Restrictions.eq("effective_till", "0000-00-00 00:00:00"));
			Junction conditionGroup3 = Restrictions.disjunction();
			conditionGroup3.add(Restrictions.eq("deleted_status", 0)); 
			LogicalExpression expression = Restrictions.and(conditionGroup, conditionGroup1);
			LogicalExpression expression2 = Restrictions.and(expression, conditionGroup2);
			LogicalExpression expression3 = Restrictions.and(expression2, conditionGroup3);
			criteria.add(expression3);
			list = criteria.list();
		} catch(Exception e){
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list;
		
	}
	
	public int getTotalRecords(String search_term, String datefil){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Integer> list=null ;
		try{
		Query query = session.createSQLQuery("select sql_cache count(*) as count from route where '"+datefil+"' between effective_from and if(effective_till = '0000-00-00 00:00:00','"+datefil+"',effective_till) and deleted_status='0' and (route_number like '%"+search_term+"%' or route_name like '%"+search_term+"%' or status like '%"+search_term+"%' or effective_from like '%"+search_term+"%' or effective_till like '%"+search_term+"%')").addScalar("count", Hibernate.INTEGER);
		list= query.list();
		} catch(Exception e){
			//System.out.println(e);
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
              //  session.close();
            }
		}
		int cnt = list.get(0);
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total , HttpServletRequest request, String col, String dir, String datefil){
		JSONObject result = new JSONObject();
		try{
		int totalAfterFilter = total;
//		System.out.println("in get data route dao");
		//JSONArray array = new JSONArray();
		String searchSQL = "";
		String sql ="from Route";
		
		//sql += " order by " + COL_NAME + " " + DIR;

		//sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Route> list=null;
		try{
		Criteria criteria = session.createCriteria(Route.class);
		//criteria.add(Restrictions.ne("status", "deleted"));
		//System.out.println("sSearch------->"+col+dir);
		if(!col.equals("")){
			if(dir.equals("asc")){
			    criteria.addOrder(Order.asc(col));
			}else{
				criteria.addOrder(Order.desc(col));	
			}
		} 
		
		Junction conditionGroup = Restrictions.disjunction();
		if(!request.getParameter("sSearch").equals("")){
			String search_term = request.getParameter("sSearch");
			
			
//			conditionGroup.add(Restrictions.like("busStopName","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias1","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias2","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias3","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias4","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias5","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias6","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("route_number","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("route_name","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("status","%"+search_term+"%" ));
			/*conditionGroup.add(Restrictions.like("effective_from","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("effective_till","%"+search_term+"%" ));*/
			
			//conditionGroup.add(Restrictions.eq("deleted_status",0 ));
			//criteria.add(conditionGroup);
			//criteria.add(Restrictions.or(Restrictions.like("route_number","%"+search_term+"%" ), Restrictions.like("route_name","%"+search_term+"%" ) , Restrictions.like("status","%"+search_term+"%" ) , Restrictions.like("route_name","%"+search_term+"%" )));
		}else{
			conditionGroup.add(Restrictions.like("route_number","%%" ));
			conditionGroup.add(Restrictions.like("route_name","%%" ));
			conditionGroup.add(Restrictions.like("status","%%" ));
			conditionGroup.add(Restrictions.like("effective_from","%%" ));
			conditionGroup.add(Restrictions.like("effective_till","%%" ));
		}
		Junction conditionGroup1 = Restrictions.disjunction();
		conditionGroup1.add(Restrictions.le("effective_from", datefil)); 
		//conditionGroup1.add(Restrictions.lt("effective_till", datefil));
		
		Junction conditionGroup2 = Restrictions.disjunction();
		conditionGroup2.add(Restrictions.ge("effective_till", datefil)); 
		conditionGroup2.add(Restrictions.eq("effective_till", "0000-00-00 00:00:00"));
		
		Junction conditionGroup3 = Restrictions.disjunction();
		conditionGroup3.add(Restrictions.eq("deleted_status", 0)); 
		
		LogicalExpression expression = Restrictions.and(conditionGroup, conditionGroup1);
		LogicalExpression expression2 = Restrictions.and(expression, conditionGroup2);
		LogicalExpression expression3 = Restrictions.and(expression2, conditionGroup3);

		//criteria.add(conditionGroup1);
		criteria.add(expression3);
		
		//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
		criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
		criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
		//System.out.println("My Criteria");
		list = criteria.list();
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		//System.out.println("list.size()------->"+list.size());
		JSONArray array = new JSONArray();
		int showroute=0;
		for(int i=0;i<list.size();i++){
			JSONArray ja = new JSONArray();
			showroute=0;
			
			if( list.get(i).getEffective_till()!=null){
			if(list.get(i).getEffective_till().length()>=10){
			if((list.get(i).getEffective_from().substring(0, 10)).equals(list.get(i).getEffective_till().substring(0, 10))){
				showroute=1;
			}else{
				showroute=0;
			}
			}
			}
			if(showroute==0){
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+list.get(i).getRoute_id()+"' value='"+list.get(i).getRoute_id()+"'/>");
			ja.add(list.get(i).getRoute_id());
			ja.add(list.get(i).getRoute_number());
			ja.add(list.get(i).getRoute_name());
		//	BusStops bs=list.get(i).getStart_stop();
			ja.add(list.get(i).getStart_stop().getBusStopName());
			ja.add(list.get(i).getEnd_stop().getBusStopName());
			ja.add(list.get(i).getRoute_type().getRoute_type_name());
			ja.add(list.get(i).getStatus());
			ja.add(list.get(i).getRoute_direction());
			ja.add(list.get(i).getVia());
			String startdate;
			String enddate;
			if(list.get(i).getEffective_from()!=""  && list.get(i).getEffective_from()!=null){
				startdate=list.get(i).getEffective_from().substring(0, 10);
				String []startdate1=startdate.split("-");
				ja.add(startdate1[2]+"/"+startdate1[1]+"/"+startdate1[0]);
			}else{
			ja.add(list.get(i).getEffective_from());
			}
			if(list.get(i).getEffective_till()!="" && list.get(i).getEffective_till()!=null){
				enddate=list.get(i).getEffective_till().substring(0, 10);
				String []enddate1=enddate.split("-");
				ja.add(enddate1[2]+"/"+enddate1[1]+"/"+enddate1[0]);
			}
			else{
			ja.add(list.get(i).getEffective_till());
			}
			ja.add(list.get(i).getRoute_geofence());
			array.add(ja);
			///System.out.println("Array----->"+array);
		}
		}
		//System.out.println("hiiiiiiiiiiii");
		totalAfterFilter = getTotalRecords(request.getParameter("sSearch"),datefil);
		
		
			
			result.put("aaData",array);
			result.put("iTotalRecords", total);
			
			result.put("iTotalDisplayRecords", totalAfterFilter);
		
		
		//System.out.println("REsult ------>"+result);
		
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public List<RouteType> getRouteType(){
//		System.out.println("in execute route type");
		List<RouteType> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		
		Query query = session.createQuery("from RouteType where status='Active'");
		query.setMaxResults(500);
		list = (List<RouteType>) query.list();
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		return list;
	}
	
	public String addRoute(Route route,Session session){

		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//session.beginTransaction();
		session.saveOrUpdate(route);
		//session.getTransaction().commit();
		//session.close();
		
		return "success";
		
	}
	
	public String addRoute(RoutePoint route,Session session){

		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//session.beginTransaction();
		session.saveOrUpdate(route);
		//session.getTransaction().commit();
		//session.close();
		
		return "success";
		
	}
	
	public String addRoute(RouteMap route,Session session){

		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//session.beginTransaction();
		session.saveOrUpdate(route);
		//session.getTransaction().commit();
		//session.close();
		
		return "success";
		
	}
	
	public String edit(Route route, int id){

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		session.beginTransaction();
		Route routes = (Route) session.get(Route.class, id);
		routes.setRoute_id(route.getRoute_id());
		routes.setGoogle_distance(route.getGoogle_distance());
		routes.setGoogle_time(route.getGoogle_time());
		routes.setTrimax_distance(route.getTrimax_distance());
		routes.setTrimax_time(route.getTrimax_time());
		session.update(routes);
		session.getTransaction().commit();
		
		//session.close();
	
		
		return "success";
		
	}
	public int editCoord( int id, String coord, Session session){
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//session.beginTransaction();
		Query query=null;
		int updated=0;
		try{
		String sql="update route set route_string=GeomFromText(' "+coord+" ') where route_id="+id+" ";
		query = session.createSQLQuery(sql);
		updated = query.executeUpdate();
		} catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
              //  session.close();
            }
		}
		//System.out.println(updated);
		//session.getTransaction().commit();
		return updated;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ScheduleService> getServiceType(){
		//System.out.println("in execute");
		List<ScheduleService> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query=null;
		try{
			query = session.createQuery("from ScheduleService where status='Active' and	deleted_status='0'");
			query.setMaxResults(500);
			list = (List<ScheduleService>) query.list();
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
              //  session.close();
            }
		}
		
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Platform> getPlatform(int id){
		//System.out.println("in execute");
		List<Platform> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from Platform where bay_id='"+id+"'  and status='Active' and deleted_status='0'");
		query.setMaxResults(500);
		list = (List<Platform>) query.list();
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		return list;
	}
	

	@SuppressWarnings("unchecked")
	public List<OrganisationChart> getOrgchart(){
		//System.out.println("in execute");
		List<OrganisationChart> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from OrganisationChart where orgType='3' and deleted_status='0'");
		
		list = (List<OrganisationChart>) query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
              //  session.close();
            }
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Floor> getFloor(int id){
		//System.out.println("in execute");
		List<Floor> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from Floor where parent_id='"+id+"' and status='Active' and deleted_status='0'");
		
		list = (List<Floor>) query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
             //   session.close();
            }
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Bay> getBay(int id){
		//System.out.println("in execute");
		List<Bay> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from Bay where 	floor_id='"+id+"' and status='Active' and deleted_status='0'");
		
		list = (List<Bay>) query.list();
		} catch(Exception e){
			e.printStackTrace();
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
              //  session.close();
            }
		}
		return list;
	}
	
	public String addPlatform(RoutePlatformRel routeplf)throws Exception{

		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		session1.beginTransaction();
		session1.saveOrUpdate(routeplf);
		session1.getTransaction().commit();
		//session1.evict(routeplf);
		} catch(Exception e){
			
			e.printStackTrace();
			throw e;
		}
		//session1.close();
		
		return "success";
		
	}
	
	public List<Route> getRouteInfo(int id){
		//System.out.println("in execute");
		List<Route> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from Route where route_id='"+id+"' and (effective_till >= DATE_FORMAT(now(),'%Y-%m-%d') or effective_till ='0000-00-00' or effective_till is null) and deleted_status='0'");
		query.setMaxResults(500);
		list = (List<Route>) query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		
		return list;
	}
	
	public List<RouteMap> getRouteMapInfo(int id){
		//System.out.println("in execute");
		List<RouteMap> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from RouteMap where route_id='"+id+"' order by route_map_id ");
		query.setMaxResults(500);
		list = (List<RouteMap>) query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
              //  session.close();
            }
		}
		
		return list;
	}
	
	public List<RoutePoint> getRoutePointInfo(int id){
		//System.out.println("in execute");
		List<RoutePoint> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from RoutePoint where route_id='"+id+"' and deleted_status=0 order by route_points_id");
		query.setMaxResults(100);
		list = (List<RoutePoint>) query.list();
		} catch(Exception e){
			//System.out.println(e);
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		return list;
	}
	
	public List<Route> routeUni(String routeno, String routedir, Session session){
		//System.out.println("in execute");
		List<Route> list=null;
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//try{
		Query query = session.createQuery("from Route where route_number='"+routeno+"' and route_direction ='"+routedir+"'  " +
				" and (effective_till >= DATE_FORMAT(now(),'%Y-%m-%d') or effective_till ='0000-00-00' or effective_till is null) " +
					" and deleted_status='0'");
		query.setMaxResults(500);
		list = (List<Route>) query.list();
		/*} catch(Exception e){
			System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}*/
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ScheduleService> getService(){
		//System.out.println("in execute");
		List<ScheduleService> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from ScheduleService where  status='Active' and deleted_status='0' order by 	service_type_id");
		
		list = (List<ScheduleService>) query.list();
		} catch(Exception e){
			e.printStackTrace();
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
             //   session.close();
            }
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RateMaster> getRate(){
		//System.out.println("in execute");
		List<RateMaster> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from RateMaster where  status='Active' and parent_rate_master='0' and deleted_status='0'  and (effective_start_date <=now() and(effective_end_date IS NULL or effective_end_date >=now()))  order by 	service_type_id");
		
		list = (List<RateMaster>) query.list();
		} catch(Exception e){
			e.printStackTrace();
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<RoadType> getRoadType(){
		//System.out.println("in execute");
		List<RoadType> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from RoadType where status='Active' and deleted_status='0'");

		query.setMaxResults(500);
		list = (List<RoadType>) query.list();
		} catch(Exception e){
			e.printStackTrace();
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<BusStops> getbusStopName(int id){
		//System.out.println("in execute");
		List<BusStops> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from BusStops where id='"+id+"' ");

		query.setMaxResults(500);
		list = (List<BusStops>) query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		return list;
		
	}
	
	public int updateRoute( int routeid, String endtdate, Session session, int upby){
		Query query=null;
		int updated=0;
		try{
		String sql="update route set effective_till=if(effective_from=date(now()) && '"+endtdate+"'<date(now()),now(),DATE_SUB(' "+endtdate+" ',INTERVAL 1 DAY)), 	updated_date=now(), updated_by= '"+upby+"' "  +
				" where route_id='"+routeid+"'";
		query = session.createSQLQuery(sql);
		updated = query.executeUpdate();
		
		String sql1="update farechart_master set effect_end_date=if(effect_start_date=date(now()) && '"+endtdate+"'<date(now()),now(),DATE_SUB(' "+endtdate+" ',INTERVAL 1 DAY)), 	updated_date=now(), updated_by= '"+upby+"' "  +
				" where route_id='"+routeid+"'";
		query = session.createSQLQuery(sql1);
		updated = query.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
            }
		}
		//System.out.println(updated);
		return updated;
	}
	
	public List<BusStops> getRoutePointType(int id){
		//System.out.println("in execute");
		List<BusStops> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from BusStops where bus_stop_id='"+id+"'  ");
		query.setMaxResults(500);
		list = (List<BusStops>) query.list();
		} catch(Exception e){
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		
		return list;
	}
	
	public List<RoutePlatformRel> getRoutePlatRelInfo(int id){
		//System.out.println("in execute");
		List<RoutePlatformRel> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from RoutePlatformRel where route_id='"+id+"' and status='Y' order by route_platform_id");
		query.setMaxResults(500);
		list = (List<RoutePlatformRel>) query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                //session.close();
            }
		}
		return list;
	}
	
	public List<Platform> getPlattformName(int id){
		//System.out.println("in execute");
		List<Platform> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from Platform where 	platform_id='"+id+"'");
		query.setMaxResults(500);
		list = (List<Platform>) query.list();
		} catch(Exception e){
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		return list;
	}
	
	public List<ScheduleService> getServiceName(int id){
		//System.out.println("in execute");
		List<ScheduleService> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from ScheduleService where 	service_type_id='"+id+"'");
		query.setMaxResults(500);
		list = (List<ScheduleService>) query.list();
		} catch(Exception e){
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		return list;
	}
	
	
	public String findBusStopRoute(int busstopid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List list = new ArrayList();
		String routeform="";
		try{
		String qry="select r.route_id,r.route_number,r.route_name,r.route_direction,r.effective_from,if(r.effective_till='0000-00-00 00:00:00','',r.effective_till) effective_till from route r " +
				" inner join route_point rp on rp.route_id=r.route_id " +
				" where r.deleted_status=0  " +
				" group by r.route_number,r.route_direction " +
				" order by r.route_number ";
		
		Query query = session.createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list(); 
		
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				/*list.add(rs.get("route_id").toString());
				list.add(rs.get("route_number").toString());
				list.add(rs.get("route_name").toString());*/
				routeform=routeform+"<tr><td><input type='radio' id='routeid' name='routeid' value="+rs.get("route_id").toString()+"></td><td>"+rs.get("route_number").toString()+"-"+rs.get("route_direction").toString()+"</td><td>"+rs.get("route_name").toString()+"</td></tr>";
			}
			//routeform=routeform+"<tr><td><a data-toggle='modal' class='btn white' id='busstopshowmodel' role='button' href='#busstopshowmodel' >Add Route</a></td></tr>";
		}
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally{
			//session.close();
		}
		//session.close();
		return routeform;
	}
	
	public List<ScheduleDetails> getRouteScheduleInfo(int id){
		//System.out.println("in execute");
		List<ScheduleDetails> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		//Query query = session.createQuery("from ScheduleDetails where routeNumber.route_id='"+id+"' and deletedStatus='0'  ");sss
		Query query = session.createQuery("from ScheduleDetails where routeNumber.route_id='"+id+"' and deletedStatus='0' and scheduleNumber.deletedStatus='0' and scheduleNumber.status ='NEW' and formFour.deletedStatus='0' AND formFour.currentStatus='active' group by scheduleNumber.scheduleNumber"); 
		//query.setMaxResults(500);
		list = (List<ScheduleDetails>) query.list();
		} catch(Exception e){
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		
		return list;
	}
	
	public List<ScheduleDetails> getdistRouteScheduleInfo(int id){
		//System.out.println("in execute");
		List<ScheduleDetails> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from ScheduleDetails where routeNumber.route_id='"+id+"' and deletedStatus='0' and scheduleNumber.deletedStatus='0' and scheduleNumber.status ='NEW' and formFour.deletedStatus='0' group by scheduleNumber.scheduleNumber order by scheduleNumber.scheduleNumber");
		//query.setMaxResults(500);
		list = (List<ScheduleDetails>) query.list();
		} catch(Exception e){
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		
		return list;
	}
	
	public String getdistRouteScheduleDetailsInfo(int id){
		//System.out.println("in execute");
		//List<ScheduleDetails> list=new ArrayList<ScheduleDetails>();
		String Scheduleno="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createSQLQuery("select s.schedule_number from schedule_details sd" +
				" inner join form_four ff on sd.form_four_id=ff.form_four_id" +
				" inner join schedule s on s.schedule_id=ff.schedule_number_id" +
				" where sd.route_number_id="+id+" and sd.deleted_status='0' and ff.deleted_status=0 and ff.current_status='Active'" +
				" and s.status='NEW' and s.current_status='CASE WORKER' and s.deleted_status=0 and" +
				" if(ff.effective_end_date IS NULL or ff.effective_end_date = '0000-00-00', CURDATE(),ff.effective_end_date) >= CURDATE()" +
				" group by sd.schedule_number ");
		//query.setMaxResults(500);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list(); 
		
		if (aliasToValueMapList.size() > 0) {
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				//ScheduleDetails details=new ScheduleDetails();
				//details.setScheduleNumber(rs.get("schedule_number").toString());
				//details.getScheduleNumber().setScheduleNumber(rs.get("schedule_number").toString());
				Scheduleno+=rs.get("schedule_number").toString()+",";
		}
		}
		//list = (List<ScheduleDetails>) query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		
		return Scheduleno;
	}
	
	public List<FormFour> getdistFormFourInfo(int id){
		//System.out.println("in execute");
		List<FormFour> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from FormFour where formFourRoute.route_id='"+id+"' AND scheduleNumber.status IN('NEW') and deletedStatus='0' group by scheduleNumberName order by scheduleNumberName");
		//query.setMaxResults(500);
		list = (List<FormFour>) query.list();
		} catch(Exception e){
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		
		return list;
	}
	
	public int updateRouteFareChartData( int routeid){
		Query query=null;
		int updated=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HttpServletRequest request=ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		try{
		String sql="update route set deleted_status=1, 	updated_date=now(), updated_by= '"+usrsessionid+"' "  +
				" where route_id='"+routeid+"'";
		query = session.createSQLQuery(sql);
		updated = query.executeUpdate();
		
		String sql1="update farechart_master set deleted_status=1, 	updated_date=now(), updated_by= '"+usrsessionid+"' "  +
				" where route_id='"+routeid+"'";
		query = session.createSQLQuery(sql1);
		updated = query.executeUpdate();
		} catch(Exception e){
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
            	//session.close();
            }
		}
		//System.out.println(updated);
		return updated;
	}
	
	
	
	public int updateScheduledata( int oldrouteid, int newrouteid, Session session){
		Query query=null;
		int updated=0;
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HttpServletRequest request=ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		try{
		String sql="update schedule_details set route_number_id='"+newrouteid+"', 	updated_date=now(), updated_by= '"+usrsessionid+"' "  +
				" where route_number_id='"+oldrouteid+"'";
		query = session.createSQLQuery(sql);
		updated = query.executeUpdate();
		
		String sql1="update form_four set route_id='"+newrouteid+"', 	updated_date=now(), updated_by= '"+usrsessionid+"' "  +
				" where route_id='"+oldrouteid+"'";
		query = session.createSQLQuery(sql1);
		updated = query.executeUpdate();
		
		
		String sql11="update Toll_Pass_Ticket set Route_no='"+newrouteid+"' "  +
				" where Route_no='"+oldrouteid+"'";
		query = session.createSQLQuery(sql11);
		updated = query.executeUpdate();
		
		
		} catch(Exception e){
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
            	//session.close();
            }
		}
		//System.out.println(updated);
		return updated;
	}
	
	
	public String findRouteStopList(int routeid, int busstopid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List list = new ArrayList();
		String routeform="";
		try{
		String qry="select rp.route_id, rp.bus_stop_id, bs.bus_stop_name, if(bs.stop_direction is null,'',bs.stop_direction) stop_direction from route_point rp " +
				"inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id " +
				"where  route_id	='"+routeid+"' " +
				"order by rp.route_points_id";
		
		Query query = session.createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list(); 
		
		if (aliasToValueMapList.size() > 0) {
			int showradiobutton=0;
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				/*list.add(rs.get("route_id").toString());
				list.add(rs.get("route_number").toString());
				list.add(rs.get("route_name").toString());*/
				showradiobutton=1;
				String checked="";
				if(i==0 || i==(aliasToValueMapList.size()-1))
				{
					checked="checked";
				}
				if(showradiobutton==0){
					routeform=routeform+"<tr><td></td><td>"+rs.get("bus_stop_name").toString()+" ( Towards:"+rs.get("stop_direction").toString()+" )</td></tr>";
				}else{
				routeform=routeform+"<tr><td><input type='checkbox' "+checked+" id='endbusstopid' class='selbusstopid' name='endbusstopid' value="+rs.get("bus_stop_id").toString()+"></td><td>"+rs.get("bus_stop_name").toString()+" ( Towards:"+rs.get("stop_direction").toString()+" )</td></tr>";
				}
				if(Integer.parseInt(rs.get("bus_stop_id").toString())==busstopid){
					showradiobutton=1;
				}
			}
			routeform=routeform+"<tr><td colspan='2'><button id='copyrouteinfo' class='btn blue' onclick='getcopyrouteinfo()' aria-hidden='true' type='button'>Copy</button></td></tr>";
			
		}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			//session.close();
		}
		//session.close();
		return routeform;
	}
	
	public int getDuplicateRouteCnt(String startdate, String endDate, int routeId, String routeNo, String routeDir, Session session){
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Integer> list=null ;
		try{
//		Query query = session.createSQLQuery("select count(*) cnt from route where ('"+startdate+"' between effective_from and if(effective_till='0000-00-00 00:00:00','"+startdate+"',effective_till) or '"+endDate+"' between effective_from and if(effective_till='0000-00-00 00:00:00','"+endDate+"',effective_till) or effective_till='0000-00-00 00:00:00' or effective_from > now() ) and route_number= '"+routeNo+"' and route_direction ='"+routeDir+"' and deleted_status=0 and route_id!='"+routeId+"'").addScalar("cnt", Hibernate.INTEGER);
			Query query = session.createSQLQuery("select count(*) cnt from route where (effective_from>= '"+startdate+"' or effective_till>='"+startdate+"' or effective_till='0000-00-00 00:00:00') and route_number= '"+routeNo+"' and route_direction ='"+routeDir+"' and deleted_status=0 and route_id!='"+routeId+"'").addScalar("cnt", Hibernate.INTEGER);
		list= query.list();
		} catch(Exception e){
			//System.out.println(e);
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
                //session.close();
            }
		}
		int cnt = list.get(0);
		return cnt;
		

	}
	
	
	public String getDeleteRouteId(){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<String> list=null ;
		try{
		Query query = session.createSQLQuery("select group_concat(route_id) route_id  from route where effective_from=effective_till and deleted_status=0").addScalar("route_id", Hibernate.STRING);
		list= query.list();
		} catch(Exception e){
			//System.out.println(e);
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                //session.close();
            }
		}
		String cnt = list.get(0);
		return cnt;
		

	}
	
	public String delRouteFareChart(String routeId){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query=null;
		HttpServletRequest request=ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		try{
			String sql="update route set  deleted_status=1,updated_date=now(), updated_by= '"+usrsessionid+"' "  +
					" where route_id in ("+routeId+")";
			query = session.createSQLQuery(sql);
			query.executeUpdate();
//			System.out.println("sql"+sql);
			String sql1="update farechart_master set deleted_status=1,updated_date=now(), updated_by= '"+usrsessionid+"' "  +
					" where route_id in ("+routeId+")";
			query = session.createSQLQuery(sql1);
			query.executeUpdate();
//			System.out.println("sql"+sql1);
		} catch(Exception e){
			//System.out.println(e);
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		finally{
            if(session!=null){
                //session.close();
            }
		}
		return null;
		

	}
	
	
	
	public int updatePrevPlatform(int routeId){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query=null;
		try{
			String sql="update route_platform_rel set  status='N'"  +
					" where route_id ='"+routeId+"'";
			query = session.createSQLQuery(sql);
			query.executeUpdate();
//			System.out.println("sql"+sql);
			
		} catch(Exception e){
			//System.out.println(e);
			e.printStackTrace();
			return 1;
		}
		
		finally{
            if(session!=null){
                //session.close();
            }
		}
		return 0;
		

	}
	
	
	//Get View Routes From Busstop Interface
	public int getTotalRecordsByBusStopId(String search_term, String datefil,int busstopId){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Integer> list=null ;
		try{
		Query query = session.createSQLQuery("select count(distinct r.route_id) as count from route r inner join route_point rp on r.route_id=rp.route_id  where rp.bus_stop_id='"+busstopId+"' and '"+datefil+"' " +
				" between effective_from and if(effective_till = '0000-00-00 00:00:00','"+datefil+"',effective_till) and r.deleted_status='0' " +
						" and (route_number like '%"+search_term+"%' or route_name like '%"+search_term+"%' or status like '%"+search_term+"%' " +
								" or effective_from like '%"+search_term+"%' or effective_till like '%"+search_term+"%')").addScalar("count", Hibernate.INTEGER);
		list= query.list();
		} catch(Exception e){
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
              //  session.close();
            }
		}
		int cnt = list.get(0);
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataByBusStopId(int total , HttpServletRequest request, String col, String dir, String datefil,String busstopId){
		JSONObject result = new JSONObject();
		try{
		int totalAfterFilter = total;
		
		//JSONArray array = new JSONArray();
		String searchSQL = "";
		String sql ="from Route";
		
		//sql += " order by " + COL_NAME + " " + DIR;

		//sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<BusStopRouteInfo> list=null;
		try{
		Criteria criteria = session.createCriteria(BusStopRouteInfo.class);
		//criteria.add(Restrictions.ne("status", "deleted"));
		//System.out.println("sSearch------->"+col+dir);
		if(!col.equals("")){
			if(dir.equals("asc")){
			    criteria.addOrder(Order.asc(col));
			}else{
				criteria.addOrder(Order.desc(col));	
			}
		}
		Junction conditionGroup = Restrictions.disjunction();
		if(!request.getParameter("sSearch").equals("")){
			String search_term = request.getParameter("sSearch");
			
			
//			conditionGroup.add(Restrictions.like("busStopName","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias1","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias2","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias3","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias4","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias5","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias6","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("route_number","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("route_name","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("status","%"+search_term+"%" ));
			/*conditionGroup.add(Restrictions.like("effective_from","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("effective_till","%"+search_term+"%" ));*/
			
			//conditionGroup.add(Restrictions.eq("deleted_status",0 ));
			//criteria.add(conditionGroup);
			//criteria.add(Restrictions.or(Restrictions.like("route_number","%"+search_term+"%" ), Restrictions.like("route_name","%"+search_term+"%" ) , Restrictions.like("status","%"+search_term+"%" ) , Restrictions.like("route_name","%"+search_term+"%" )));
		}else{
			conditionGroup.add(Restrictions.like("route_number","%%" ));
			conditionGroup.add(Restrictions.like("route_name","%%" ));
			conditionGroup.add(Restrictions.like("status","%%" ));
			conditionGroup.add(Restrictions.like("effective_from","%%" ));
			conditionGroup.add(Restrictions.like("effective_till","%%" ));
			
		}
		Junction conditionGroup1 = Restrictions.disjunction();
		conditionGroup1.add(Restrictions.le("effective_from", datefil)); 
		//conditionGroup1.add(Restrictions.lt("effective_till", datefil));
		
		Junction conditionGroup2 = Restrictions.disjunction();
		conditionGroup2.add(Restrictions.ge("effective_till", datefil)); 
		conditionGroup2.add(Restrictions.eq("effective_till", "0000-00-00 00:00:00"));
		
		Junction conditionGroup3 = Restrictions.disjunction();
		conditionGroup3.add(Restrictions.eq("deleted_status", 0)); 
		
		Junction conditionGroup4 = Restrictions.disjunction();
		conditionGroup4.add(Restrictions.eq("rp.bus_stop_id", Integer.parseInt(busstopId)));
		
		LogicalExpression expression = Restrictions.and(conditionGroup, conditionGroup1);
		LogicalExpression expression2 = Restrictions.and(expression, conditionGroup2);
		LogicalExpression expression3 = Restrictions.and(expression2, conditionGroup3);
		LogicalExpression expression4 = Restrictions.and(expression2, conditionGroup4);
	
		//criteria.add(conditionGroup1);
		criteria.add(expression3);
		
		criteria.add(expression4);
		criteria.createCriteria("routeinfo", "rp");
		//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
		criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
		criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
		//System.out.println("My Criteria");
		list = criteria.list();
		} catch(Exception e){
		}
		
		finally{
            if(session!=null){
               // session.close();
            }
		}
		//System.out.println("list.size()------->"+list.size());
		JSONArray array = new JSONArray();
		int showroute=0;
		for(int i=0;i<list.size();i++){
			JSONArray ja = new JSONArray();
			showroute=0;
			
			if( list.get(i).getEffective_till()!=null){
			if(list.get(i).getEffective_till().length()>=10){
			if((list.get(i).getEffective_from().substring(0, 10)).equals(list.get(i).getEffective_till().substring(0, 10))){
				showroute=1;
			}else{
				showroute=0;
			}
			}
			}
			if(showroute==0){
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+list.get(i).getRoute_id()+"' value='"+list.get(i).getRoute_id()+"'/>");
			ja.add(list.get(i).getRoute_id());
			ja.add(list.get(i).getRoute_number());
			ja.add(list.get(i).getRoute_name());
		//	BusStops bs=list.get(i).getStart_stop();
			ja.add(list.get(i).getStart_stop().getBusStopName());
			ja.add(list.get(i).getEnd_stop().getBusStopName());
			ja.add(list.get(i).getRoute_type().getRoute_type_name());
			ja.add(list.get(i).getStatus());
			ja.add(list.get(i).getRoute_direction());
			ja.add(list.get(i).getVia());
			String startdate;
			String enddate;
			if(list.get(i).getEffective_from()!=""  && list.get(i).getEffective_from()!=null){
				startdate=list.get(i).getEffective_from().substring(0, 10);
				String []startdate1=startdate.split("-");
				ja.add(startdate1[2]+"/"+startdate1[1]+"/"+startdate1[0]);
			}else{
			ja.add(list.get(i).getEffective_from());
			}
			if(list.get(i).getEffective_till()!="" && list.get(i).getEffective_till()!=null){
				enddate=list.get(i).getEffective_till().substring(0, 10);
				String []enddate1=enddate.split("-");
				ja.add(enddate1[2]+"/"+enddate1[1]+"/"+enddate1[0]);
			}
			else{
			ja.add(list.get(i).getEffective_till());
			}
			ja.add(list.get(i).getRoute_geofence());
			array.add(ja);
			///System.out.println("Array----->"+array);
		}
		}
		//System.out.println("hiiiiiiiiiiii");
		
		
		//String date=(String)request.getSession().getAttribute("route_filter_date");
		Date date1 = new Date();
		 datefil= new SimpleDateFormat("yyyy-MM-dd").format(date1);
		
		totalAfterFilter = getTotalRecordsByBusStopId(request.getParameter("sSearch"),datefil,Integer.parseInt(busstopId));
		
		
			
			result.put("aaData",array);
			result.put("iTotalRecords", total);
			
			result.put("iTotalDisplayRecords", totalAfterFilter);
		
		
		//System.out.println("REsult ------>"+result);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Route> getRouteListDropDown1(String routeno){

		
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		List<Route> list1 = new ArrayList<Route>();
		try{
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
			String today = formatter.format( new java.util.Date() );
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			/*Criteria criteria = session.createCriteria(Route.class);
			Junction conditionGroup = Restrictions.disjunction();
			Junction conditionGroup1 = Restrictions.disjunction();
			conditionGroup1.add(Restrictions.le("effective_from",today)); 
			
			Junction conditionGroup2 = Restrictions.disjunction();
			conditionGroup2.add(Restrictions.ge("effective_till", today)); 
			conditionGroup2.add(Restrictions.eq("effective_till", "0000-00-00 00:00:00"));
			
			Junction conditionGroup3 = Restrictions.disjunction();
			conditionGroup3.add(Restrictions.eq("deleted_status", 0)); 
			
			Junction conditionGroup4 = Restrictions.disjunction();
			conditionGroup4.add(Restrictions.like("route_number", routeno,MatchMode.ANYWHERE));
			
			LogicalExpression expression = Restrictions.and(conditionGroup, conditionGroup1);
			LogicalExpression expression2 = Restrictions.and(expression, conditionGroup2);
			LogicalExpression expression3 = Restrictions.and(expression2, conditionGroup3);
			LogicalExpression expression4 = Restrictions.and(expression3, conditionGroup4);
			criteria.add(expression4);
			//criteria.setMaxResults(50);
			list = criteria.list();*/
			
			String queryForRouteNos = "select route_number,route_id,route_direction,start_point_id,end_point_id from route where effective_from <= '"+today+"' and ( effective_till >='"+today+"' or effective_till ='0000-00-00 00:00:00') and route_number like'%"+routeno+"%' and deleted_status='0' order by route_number ";
			Query queryObject = session.createSQLQuery(queryForRouteNos).addScalar("route_number",Hibernate.STRING)
					.addScalar("route_id",Hibernate.INTEGER).addScalar("route_direction",Hibernate.STRING)
					.addScalar("start_point_id",Hibernate.INTEGER).addScalar("end_point_id",Hibernate.INTEGER)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String,String> map = aliasToValueMapList.get(i);
				Route route1 = new Route();
				route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
				route1.setValue(map.get("route_number"));
				//setValue
				route1.setRoute_name(String.valueOf(map.get("route_id")) +"-"+String.valueOf(map.get("start_point_id"))+"-"+String.valueOf(map.get("end_point_id")));
				list1.add(route1);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list1;
		
	}
	public static void main(String args[]){
		RouteDAO daoObject = new RouteDAO();
		Session session = HibernateUtil.getSession("");
	}
	
	
	public List<Route> getRouteNo() {
		List<Route> rotelist=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery(
							"select route_id,route_number,route_name from route where if(effective_till IS NULL or effective_till = '0000-00-00 00:00:00', CURDATE(), effective_till) >= CURDATE() and status='ACTIVE' and deleted_status='0' order by route_number")
					.addScalar("route_id", Hibernate.INTEGER)
					.addScalar("route_number", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(Route.class));
			rotelist = query.list();
			// resultMap.put(0, "ALL");
			
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return rotelist;
	}
	
	@SuppressWarnings("rawtypes")
	public List getBusstopId(int parentID) {
		List list = new ArrayList();
		String qry = "SELECT bp.bus_stop_id as bus_stop_id,bus_stop_name,r.route_id as route_id,og.org_chart_id FROM route r INNER JOIN route_point rp  ON rp.route_id = r.route_id INNER "
				+ "JOIN bus_stop bp ON bp.bus_stop_id = rp.bus_stop_id "
				+"INNER JOIN org_chart og ON og.org_chart_id = bp.org_chart_id"
				+ " WHERE r.route_id ="+parentID+" and point_type_id=6";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	public List getBusStopName(int parentID) {
		List list = new ArrayList();
		String qry = "SELECT bp.bus_stop_id as bus_stop_id,bus_stop_name,r.route_id as route_id FROM route r INNER JOIN route_point rp  ON rp.route_id = r.route_id INNER "
				+ "JOIN bus_stop bp ON bp.bus_stop_id = rp.bus_stop_id "
				+ " WHERE r.route_id ="+parentID+" and point_type_id=6";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				list.add(rs.get("bus_stop_name").toString());
				//list.add(rs.get("route_id").toString());
				//list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getFloorId(int parentID) {
		List list = new ArrayList();
		String qry ="select floor_id,floor_name from floor where parent_id='"+parentID+"' and status='Active' and deleted_status='0'";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				list.add(rs.get("floor_id").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getFloorName(int parentID) {
		List list = new ArrayList();
		String qry ="select floor_id,floor_name from floor where parent_id='"+parentID+"' and status='Active' and deleted_status='0'";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				//list.add(rs.get("floor_id").toString());
				list.add(rs.get("floor_name").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	
	public List getBaysId(int parentID) {
		List list = new ArrayList();
		String qry ="select bay_id,bay_name from bay where 	floor_id='"+parentID+"' and status='Active' and deleted_status='0'";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				list.add(rs.get("bay_id").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getBaysName(int parentID) {
		List list = new ArrayList();
		String qry ="select bay_id,bay_name from bay where 	floor_id='"+parentID+"' and status='Active' and deleted_status='0'";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				//list.add(rs.get("bay_id").toString());
				list.add(rs.get("bay_name").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getPlatFormId(int parentID) {
		List list = new ArrayList();
		String qry ="select platform_id,platform_name from  platform where bay_id='"+parentID+"'  and status='Active' and deleted_status='0'";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				list.add(rs.get("platform_id").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getPlatFormName(int parentID) {
		List list = new ArrayList();
		String qry ="select platform_id,platform_name from  platform where bay_id='"+parentID+"'  and status='Active' and deleted_status='0'";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				//list.add(rs.get("platform_id").toString());
				list.add(rs.get("platform_name").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	
	public List getServiceId(int parentID) {
		List list = new ArrayList();
		String qry ="select service_type_id,service_type_name from service_type ";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				list.add(rs.get("service_type_id").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getServiceNameType(int parentID) {
		List list = new ArrayList();
		String qry ="select service_type_id,service_type_name from service_type ";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				//list.add(rs.get("platform_id").toString());
				list.add(rs.get("service_type_name").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	
	
	public List getStopRouteId(int parentID) {
		List list = new ArrayList();
		String qry ="select bus_stop_id,bus_stop_name from bus_stop where org_chart_id= '"+parentID+"'";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				list.add(rs.get("bus_stop_id").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getStopRouteNameType(int parentID) {
		List list = new ArrayList();
		String qry ="select bus_stop_id,concat(bus_stop_name,'-Towards-(',stop_direction,')') stop_name from bus_stop where org_chart_id= '"+parentID+"'";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				//list.add(rs.get("platform_id").toString());
				list.add(rs.get("stop_name").toString());
				
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	
	public String insertRoutMapValues(int routeno,int bsstop,int ttmcid1,int floor1,int bay1,int platform1,ArrayList<String> getServiceid)throws Exception{
         
		boolean isSiuccess = true;
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	
		try{
			session1.beginTransaction();
			for(int i=0;i<getServiceid.size();i++){
				
			
				String detailInsertQuery = "INSERT INTO  route_platform_rel  (  route_id ,  bus_stop_id  , depo_id,floor_id ,bay_id, " +
						" platform_id ,  service_type_id,status  )" +
						" values('"+routeno +"', '"+bsstop+"' ,  "+ttmcid1+" ,   "+floor1+",   "+bay1+",   "+platform1+",   "+getServiceid.get(i)+",'Y') ";
						
				Query queryForInsert = session1.createSQLQuery(detailInsertQuery);
				queryForInsert.executeUpdate();
				
				
			}
			session1.getTransaction().commit();
			session1.close();
		//session1.evict(routeplf);
		} catch(Exception e){
			
			e.printStackTrace();
			
		}
		//session1.close();
		
		return "success";
		
	}


	public int getTotalRecordsForviewroute(String col, String dir) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			String sql="SELECT route_platform_id, route_number,route_direction,bus_stop_name,floor_name,bay_name,platform_name,service_type_name " +
					"FROM route_platform_rel rpl inner join route on route.route_id=rpl.route_id " +
					"inner join bus_stop on bus_stop.bus_stop_id=rpl.bus_stop_id inner join floor on floor.floor_id=rpl.floor_id " +
					"inner join bay on bay.bay_id=rpl.bay_id inner join platform on platform.platform_id=rpl.platform_id " +
					"inner join service_type on service_type.service_type_id=rpl.service_type_id WHERE rpl.status = 'Y' " +
					"AND if(route.effective_till IS NULL or route.effective_till = '0000-00-00 00:00:00', CURDATE(), route.effective_till) >= CURDATE() and route.status='ACTIVE' and route.deleted_status='0'";
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and  ( route_number like '"+search_term+"%'";
				sql += " OR bus_stop_name like '"+search_term+"%'";
				sql += " OR service_type_name like '%"+search_term+"%'";
				sql += " OR floor_name like '%"+search_term+"%'";
				sql += " OR bay_name like '%"+search_term+"%'";
				sql += " OR platform_name like '%"+search_term+"%')";
				}
			if(!col.equals("")){
				 if(dir.equals("asc")){
				sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		cnt=aliasToValueMapList.size();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
	}
	
	return cnt;
	}


	public JSONObject getrouteList(int total, HttpServletRequest request,
			String col, String dir) {
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="SELECT route_platform_id, route_number,route_name,route_direction,concat(bus_stop_name,'-Towards-(',stop_direction,')') stop_name,org_name,floor_name,bay_name,platform_name,service_type_name " +
					"FROM route_platform_rel rpl inner join route r on r.route_id=rpl.route_id " +
					"inner join bus_stop on bus_stop.bus_stop_id=rpl.bus_stop_id inner join org_chart on org_chart.org_chart_id=rpl.depo_id " +
					"inner join floor on floor.floor_id=rpl.floor_id inner join bay on bay.bay_id=rpl.bay_id " +
					"inner join platform on platform.platform_id=rpl.platform_id " +
					"inner join service_type on service_type.service_type_id=rpl.service_type_id WHERE rpl.status = 'Y' " +
					"AND if(r.effective_till IS NULL or r.effective_till = '0000-00-00 00:00:00', CURDATE(), r.effective_till) >= CURDATE() and r.status='ACTIVE' and r.deleted_status='0'";
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and  ( route_number like '"+search_term+"%'";
				sql += " OR bus_stop_name like '"+search_term+"%'";
//				sql += " OR org_name like '"+search_term+"%'";
				sql += " OR service_type_name like '%"+search_term+"%'" ;
				sql += " OR floor_name like '%"+search_term+"%'";
				sql += " OR bay_name like '%"+search_term+"%'";
				sql += " OR route_name like '%"+search_term+"%'";
				sql += " OR platform_name like '%"+search_term+"%')";
				}
			if(!col.equals("")){
				 if(dir.equals("asc")){
				sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
		if(!request.getParameter("iDisplayLength").equals("-1")){
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		}
		////System.out.println("sql----------"+sql);
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		JSONArray array = new JSONArray();
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
					+ rs.get("route_platform_id")
					+ "' value='"
					+ rs.get("route_platform_id") + "'/>");
			ja.add(rs.get("route_platform_id").toString());
			ja.add(rs.get("route_number").toString()+"-"+rs.get("route_direction").toString());
			ja.add(rs.get("route_name").toString());
			ja.add(rs.get("org_name").toString());
			ja.add(rs.get("stop_name").toString());
			ja.add(rs.get("floor_name").toString());
			ja.add(rs.get("bay_name").toString());
			ja.add(rs.get("platform_name").toString());
			ja.add(rs.get("service_type_name").toString());
			array.add(ja);
			
		}
		int cnt=0;
			//totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			
				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalRecordsForviewroute(col,dir);//getTotalRecordsForCount(search_term3);
		
			result.put("iTotalRecords",cnt);
			
			result.put("iTotalDisplayRecords", cnt);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				//session.close();
			}
		}
		return result;
	}


	public boolean deletepisroute(int vid) {
		Session session = null;
		boolean isSuccess = false;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			
			String Innersql = " UPDATE route_platform_rel set status='N' WHERE route_platform_id='"+vid+"'";
			Query qry2 = session.createSQLQuery(Innersql);
			qry2.executeUpdate();
			
			
			session.getTransaction().commit();
			isSuccess = true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}


	public boolean isDuplicate(int routeid, int bsstop, int ttmcid1,
			int floor1, int bay1, int platform1, ArrayList<String> serviceid) {
		boolean isSuccess = true;
		Session session = null;
		Common common = new Common();
		 int count=0;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Date myDate = new Date();
			String date=new SimpleDateFormat("yyyy-MM-dd").format(myDate);
			for(int i=0;i<serviceid.size();i++){
			String sql="SELECT COUNT(*) as count FROM `route_platform_rel` WHERE `route_id` = '"+routeid +"' AND `bus_stop_id` = '"+bsstop+"' AND " +
					"`depo_id` = '"+ttmcid1+"' AND `floor_id` = '"+floor1+",' AND `bay_id` = '"+bay1+"' AND `platform_id` = '"+platform1+"' " +
							"AND `service_type_id` = '"+serviceid.get(i)+"' AND `status` = 'y' ";
			  count = common.getDBResultInt(session, sql, "count");
			  count+=count;
			}
			
			if(count > 0){
				isSuccess = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = true;
		}finally{
			if(session!=null){
				session.close();
			}
			return isSuccess;
		}
	}


	public List<BusStops> getStartEndPoint(int routeID) {
		// TODO Auto-generated method stub
		Session session=null;
		List<BusStops> list=null;
		try{
			list= new ArrayList<BusStops>();
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery(" select start_point_id,end_point_id from route where route_id="+routeID);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					BusStops route=new BusStops();
				route.setId(Integer.parseInt(rs.get("start_point_id").toString()));
				route.setSetLocal_bus_stop_id(Integer.parseInt(rs.get("end_point_id").toString()));
				list.add(route);
				}
			}
		}catch(Exception ex){
			
		}
		finally{
			session.close();
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BusStops> tripPlanner(){
		//System.out.println("in execute");
		List<BusStops> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			Query query = session
					.createSQLQuery(
							"select bus_stop_group_id,bus_stop_name from bus_stop WHERE bus_stop_name IS NOT NULL and status='Approved'");
//							"select bus_stop_id,bus_stop_name from bus_stop WHERE bus_stop_name IS NOT NULL ");
		//query.setMaxResults(500);
		list = (List<BusStops>) query.list();
		} catch(Exception e){
			//logger.error(e.getMessage());
			e.printStackTrace();
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list;
		
	}

	/*
	@SuppressWarnings("unchecked")
	public List<BusStops> tripPlannerDownList1(String busstopname) {
		System.out.println("tripplannerdownlist1 is called......");
		Session session = null;
		List<Map<Integer,String>> aliasToValueMapList = null;
		List<BusStops> list1 = new ArrayList<BusStops>();
		try{
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
			String today = formatter.format( new java.util.Date() );
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			
			String queryForRouteNos = "select bus_stop_id,CONCAT(bus_stop_name,' towards - ',ifnull(stop_direction,''),'') stop from bus_stop WHERE bus_stop_name IS NOT NULL and bus_stop_name like'%"+busstopname+"%'";
		  System.out.println("queryForRouteNos"+queryForRouteNos);
			Query queryObject = session.createSQLQuery(queryForRouteNos)
					.addScalar("bus_stop_id",Hibernate.STRING).addScalar("stop",Hibernate.STRING)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<Integer,String> map = aliasToValueMapList.get(i);
				BusStops busstop1 = new BusStops();
				busstop1.setBus_stop_code(String.valueOf(map.get("bus_stop_id")));
				busstop1.setBusStopName(map.get("stop"));
				//setValue
				
				list1.add(busstop1);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list1;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<BusStops> tripPlannerDownList1(String routeno){

		
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		List<BusStops> list1 = new ArrayList<BusStops>();
		try{
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
			String today = formatter.format( new java.util.Date() );
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			/*Criteria criteria = session.createCriteria(Route.class);
			Junction conditionGroup = Restrictions.disjunction();
			Junction conditionGroup1 = Restrictions.disjunction();
			conditionGroup1.add(Restrictions.le("effective_from",today)); 
			
			Junction conditionGroup2 = Restrictions.disjunction();
			conditionGroup2.add(Restrictions.ge("effective_till", today)); 
			conditionGroup2.add(Restrictions.eq("effective_till", "0000-00-00 00:00:00"));
			
			Junction conditionGroup3 = Restrictions.disjunction();
			conditionGroup3.add(Restrictions.eq("deleted_status", 0)); 
			
			Junction conditionGroup4 = Restrictions.disjunction();
			conditionGroup4.add(Restrictions.like("route_number", routeno,MatchMode.ANYWHERE));
			
			LogicalExpression expression = Restrictions.and(conditionGroup, conditionGroup1);
			LogicalExpression expression2 = Restrictions.and(expression, conditionGroup2);
			LogicalExpression expression3 = Restrictions.and(expression2, conditionGroup3);
			LogicalExpression expression4 = Restrictions.and(expression3, conditionGroup4);
			criteria.add(expression4);
			//criteria.setMaxResults(50);
			list = criteria.list();*/
			
			//String queryForRouteNos ="select bus_stop_id,CONCAT(bus_stop_name,' towards - ',ifnull(stop_direction,''),'') stop from bus_stop WHERE bus_stop_name IS NOT NULL and bus_stop_name like'%"+routeno+"%'";
			//String queryForRouteNos = "select bus_stop_group_id,CONCAT(bus_stop_name,' towards - ',ifnull(stop_direction,''),'') stop " +
				//	" from bus_stop WHERE bus_stop_name IS NOT NULL and bus_stop_name like'%"+routeno+"%' and status='Approved' " +
					//		" and stop_direction!='' and point_type_id not in(2,13,5)";
			String queryForRouteNos="SELECT group_id bus_stop_group_id,group_name stop FROM `bus_stop_group` where group_name like'%"+routeno+"%'  " +
					" and `group_type_id` = '1' AND `deleted_status` = '0' AND `status` = 'ACTIVE'";
			Query queryObject = session.createSQLQuery(queryForRouteNos).addScalar("bus_stop_group_id",Hibernate.STRING)
					.addScalar("stop",Hibernate.STRING)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String,String> map = aliasToValueMapList.get(i);
				BusStops route1 = new BusStops();
				//route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
				route1.setBus_stop_code(map.get("bus_stop_group_id"));
				route1.setBusStopName(map.get("stop"));		
				route1.setValue(map.get("stop"));
				list1.add(route1);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list1;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BusStops> busStopDropDownList(String routeno){

		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		List<BusStops> list1 = new ArrayList<BusStops>();
		try{
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
			String today = formatter.format( new java.util.Date() );
			session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			String queryForRouteNos="SELECT bus_stop_id,bus_stop_name stop FROM bus_stop WHERE status IN('new','approved')  AND bus_stop_name like'%"+routeno+"%'  " +
					" and updated_by !=0 order by bus_stop_name";
			Query queryObject = session.createSQLQuery(queryForRouteNos).addScalar("bus_stop_id",Hibernate.STRING)
					.addScalar("stop",Hibernate.STRING)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String,String> map = aliasToValueMapList.get(i);
				BusStops route1 = new BusStops();
				//route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
				route1.setBus_stop_code(map.get("bus_stop_id"));
				route1.setBusStopName(map.get("stop"));		
				route1.setValue(map.get("stop"));
				list1.add(route1);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list1;
		
	}
	
	
	
	
	public List tripPlannerSourceMapDisplay(String sourcegroupid) {
		List list = new ArrayList();
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		String qry ="SELECT group_concat(bus_stop_id) bus_stop_id FROM bus_stop_group_stop WHERE group_id = '"+sourcegroupid+"' AND `status` = 'Y'";
				try{
		Query queryObject = session.createSQLQuery(qry)
				.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		aliasToValueMapList = queryObject.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, String> rs = aliasToValueMapList.get(i);
				
				//list.add(rs.get("platform_id").toString());
				list.add(rs.get("bus_stop_id").toString());
				
			}
		}
				}catch (Exception e) {
					e.printStackTrace();
				}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List tripPlannerDestMapDisplay(String destinatgroupid) {
		List list = new ArrayList();
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		String qry ="SELECT group_concat(bus_stop_id) bus_stop_id FROM bus_stop_group_stop WHERE group_id = '"+destinatgroupid+"' AND `status` = 'Y'";
			try{	
		Query queryObject = session.createSQLQuery(qry).addScalar("bus_stop_id",Hibernate.STRING)
				.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		aliasToValueMapList = queryObject.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, String> rs = aliasToValueMapList.get(i);
				
				//list.add(rs.get("platform_id").toString());
				list.add(rs.get("bus_stop_id").toString());
				
			}
		}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		HibernateUtil.closeSession();
		return list;
	}
	

	@SuppressWarnings("rawtypes")
	public List getStageName(int routeId) {
		List list = new ArrayList();
		Session session = HibernateUtil.getSession("");
		Query query = session
				.createSQLQuery("SELECT bs.bus_stop_id bus_stop_id ,bus_stop_name FROM `route_point` rp  " +
		             " inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id  " +
		             " WHERE `route_id` = '"+routeId+"' AND rp.`fare_stage` = 'Y' ");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("bus_stop_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getStageID(int routeId) {
		List list = new ArrayList();
		Session session = HibernateUtil.getSession("");
		Query query = session
				.createSQLQuery("SELECT bs.bus_stop_id bus_stop_id ,bus_stop_name FROM `route_point` rp  " +
		             " inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id  " +
		             " WHERE `route_id` = '"+routeId+"' AND rp.`fare_stage` = 'Y' ");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("bus_stop_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public String getRouteNameInfo(String route_id) {
		String list = "";
		String qry ="select route_number from route where route_id="+route_id+"";
				
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {
			list=aliasToValueMapList.get(0).get("route_number").toString();
			System.out.println("list=="+list);

//			for (int i = 0; i < aliasToValueMapList.size(); i++) {
//				Map<String, Object> rs = aliasToValueMapList.get(i);
//				
//				//list.add(rs.get("platform_id").toString());
//				list.add(rs.get("stop_name").toString());
//				
//			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public int saveRouteName(String route_id, String route_name){
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//session.beginTransaction();
		Session session = null;
		Query query=null;
		int updated=0;
		session =  HibernateUtil.getSession("");
		try{
			Transaction tx=session.beginTransaction();
		String sql="update route set route_number='"+route_name+"' where route_id="+route_id+" ";
		query = session.createSQLQuery(sql);
		updated = query.executeUpdate();
		System.out.println("Updated==="+updated);
		if(updated>0){
			tx.commit();
		}else {
			tx.rollback();
		}
		} catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
              //  session.close();
            }
		}
		//System.out.println(updated);
		//session.getTransaction().commit();
		return updated;
	}
	
	
}
