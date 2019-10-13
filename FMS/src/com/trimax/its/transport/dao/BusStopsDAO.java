package com.trimax.its.transport.dao;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionContext;


import com.trimax.its.route.model.Route;
import com.trimax.its.route.model.RoutePoint;
import com.trimax.its.transport.model.BusStopGroup;
import com.trimax.its.transport.model.BusStopGroupStop;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.BusstopJson;
import com.trimax.its.transport.model.PointType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class BusStopsDAO {
	
	public static void main(String[] arg){
//		BusStopsDAO dao = new BusStopsDAO();
//		List<BusStops> list = dao.getBusStopList();
//		System.out.println("Size --->"+list.size());
//		
//		List<BusstopJson> list1 = new ArrayList<BusstopJson>();
//		for(BusStops bustop : list){
//			BusstopJson bus = new BusstopJson();
//			//bus.setId(bustop.getId());
//			bus.setValue(bustop.getBusStopName());
//			bus.setBusStopName(bustop.getBusStopName());
//			bus.setLongitude(bustop.getLongitude());
//			bus.setLatitude(bustop.getLatitude());
//			//bus.setId(bustop.getId());
//			list1.add(bus);
//			//i+=1;
//		}
//		
//		System.out.println("/*/*/*/*/*/*/"+new JSONArray(list1));
	}

	@SuppressWarnings("unchecked")
	public List<BusStops> getBusStopList(String lat, String lag, String idvald, double para){
		List<BusStops> list=null;
		double lat1 =Double.parseDouble(lat.substring(0, 5));
		double lag1 =Double.parseDouble(lag.substring(0, 5)); 
		double latmin =lat1-para;
		double latmax =lat1+para;
		double lngmin =lag1-para-0.01;
		double lngmax =lag1+para+0.01;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			//String idvald;
			String userorgtype=(String)request.getSession().getAttribute("userorgtype");
			int stop_type=Integer.parseInt((String)request.getSession().getAttribute("stop_type"));
			//System.out.println("sess valueis++++++++++++++++++++="+stop_type);
			/*if (idvald1 == null | idvald1.length() == 0) {
				 idvald="'0'";
			}
			else{
				idvald=idvald1;
			}*/
			try{
			if(userorgtype.equals("TRIMAX")){
				if(stop_type==0){
					query = session.createQuery("from BusStops where status in ('New')  and ((latitude_current	between '"+latmin+"'and '"+latmax+"') and (longitude_current between '"+lngmin+"'and '"+lngmax+"'))  and bus_stop_id not in ("+idvald+")");	
				}else{
				query = session.createQuery("from BusStops where status in ('New') and  point_type_id= '"+stop_type+"' and ((latitude_current	between '"+latmin+"'and '"+latmax+"') and (longitude_current between '"+lngmin+"'and '"+lngmax+"'))  and bus_stop_id not in ("+idvald+")");
				}
				}else{
				//query = session.createQuery("from BusStops where status in ('Approved','Corrected', 'Inactive') and (latitude_current	like '"+lat1+"%' or longitude_current like '"+lag1+"%') and bus_stop_id not in ("+idvald+")");
					if(stop_type==0){
					query = session.createQuery("from BusStops where status in ('New','Approved', 'Inactive')  and updated_by!='0' and ((latitude_current	between '"+latmin+"'and '"+latmax+"') and (longitude_current between '"+lngmin+"'and '"+lngmax+"')) and bus_stop_id not in ("+idvald+")");
					}else{
						query = session.createQuery("from BusStops where status in ('New','Approved', 'Inactive') and  point_type_id= '"+stop_type+"' and updated_by!='0' and ((latitude_current	between '"+latmin+"'and '"+latmax+"') and (longitude_current between '"+lngmin+"'and '"+lngmax+"')) and bus_stop_id not in ("+idvald+")");	
					}
					}
		
		//query.setMaxResults(175);
//		Criteria criteria = session.createCriteria(BusStops.class);
//		criteria.add(Restrictions.ne("status", "deleted"));
		list = (List<BusStops>) query.list();
			} catch(Exception e){
				System.out.println(e);
			}
			
			finally{
	            if(session!=null){
	                session.close();
	            }
			}
		
		List<BusstopJson> list1 = new ArrayList<BusstopJson>();
		for(BusStops bustop : list){
			BusstopJson bus = new BusstopJson();
			//bus.setId(bustop.getId());
			bus.setValue(bustop.getBusStopName());
			bus.setBusStopName(bustop.getBusStopName());
			bus.setLongitude(bustop.getLongitude());
			bus.setLatitude(bustop.getLatitude());
			//bus.setId(bustop.getId());
			list1.add(bus);
			//i+=1;
		}
		return list;
		//System.out.println("/*/*/*/*/*/*/"+new JSONArray(list1));
	}

	@SuppressWarnings("unchecked")
	public List<BusStops> getBusStopList(){
		List<BusStops> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			try{
			String userorgtype=(String)request.getSession().getAttribute("userorgtype");
			if(userorgtype.equals("TRIMAX")){
				query = session.createQuery("from BusStops where status in ('New') ");
			}else{
				query = session.createQuery("from BusStops where status in ('New','Approved', 'Inactive') and updated_by!='0'");
			}
			query.setMaxResults(100);
			list = (List<BusStops>) query.list();
			}catch(Exception e){
				System.out.println(e);
			}
			finally{
				  if(session!=null){
		                session.close();
		            }
			}
		
	//	session.close();
		return list;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Route> getListOfBusStop(String stopName){

		System.out.println("bus stop list");
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		List<Route> list1 = new ArrayList<Route>();
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			String qryforStop = "select bus_stop_id,bus_stop_name from bus_stop where status IN ('new','approved')  AND bus_stop_name LIKE '%"+stopName+"%' and updated_by!=0 order by bus_stop_name ";
			Query queryObject = session.createSQLQuery(qryforStop).addScalar("bus_stop_id",Hibernate.STRING)
					.addScalar("bus_stop_name",Hibernate.STRING)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String,String> map = aliasToValueMapList.get(i);
				Route bs= new Route();
				
				bs.setRoute_name(map.get("bus_stop_name"));
				bs.setRoute_number(map.get("bus_stop_id"));
				list1.add(bs);
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
	public List<BusStops> getBusStopList(int id){
		List<BusStops> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//System.out.println("id issssssss++++++++++++++++"+id);
		Query query;
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			int stop_type=Integer.parseInt((String)request.getSession().getAttribute("stop_type"));
			String userorgtype=(String)request.getSession().getAttribute("userorgtype");
			try{
			if(userorgtype.equals("TRIMAX")){
				if(stop_type==0){
				query = session.createQuery("from BusStops where status in ('New')   and 	bus_stop_id='"+id+"'");
				}else{
					query = session.createQuery("from BusStops where status in ('New') and  point_type_id= '"+stop_type+"'  and 	bus_stop_id='"+id+"'");	
				}
				}else{
					if(stop_type==0){
				query = session.createQuery("from BusStops where status in ('New','Approved', 'Inactive')   and updated_by!='0' and 	bus_stop_id='"+id+"'");
					}else{
						query = session.createQuery("from BusStops where status in ('New','Approved', 'Inactive') and  point_type_id= '"+stop_type+"'  and updated_by!='0' and 	bus_stop_id='"+id+"'");
					}
					}
		//Query query = session.createQuery("from BusStops where status != 'DELETED' and 	bus_stop_id='"+id+"'");
		query.setMaxResults(10);
		list = (List<BusStops>) query.list();
			} catch(Exception e){
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
	
	
	
	public List<BusStops> getBusStopDropList(String name){
		List<BusStops> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			int stop_type=Integer.parseInt((String)request.getSession().getAttribute("stop_type"));
			String userorgtype=(String)request.getSession().getAttribute("userorgtype");
			try{
			if(userorgtype.equals("TRIMAX")){
				if(stop_type==0){
				query = session.createQuery("from BusStops where status in ('New')  and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
				}else{
					query = session.createQuery("from BusStops where status in ('New') and  point_type_id= '"+stop_type+"' and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
				}
				}else{
					if(stop_type==0){
				query = session.createQuery("from BusStops where status in ('New','Approved', 'Inactive')  and updated_by!='0' and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
					}else{
						query = session.createQuery("from BusStops where status in ('New','Approved', 'Inactive') and  point_type_id= '"+stop_type+"' and updated_by!='0' and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
					}
					}
		
		//Query query = session.createQuery("from BusStops where status != 'DELETED' and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
		//query.setMaxResults(100);
		list = (List<BusStops>) query.list();
			} catch(Exception e){
				
			}
			finally{
				  if(session!=null){
		                session.close();
		            }
			}
		//session.close();
		return list;
		
	}
	
	public List<BusStops> getRouteStopDropList(String name){
		List<BusStops> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			int stop_type=Integer.parseInt((String)request.getSession().getAttribute("stop_type"));
			String userorgtype=(String)request.getSession().getAttribute("userorgtype");
			try{
			if(userorgtype.equals("TRIMAX")){
				if(stop_type==0){
				query = session.createQuery("from BusStops where status in ('New')  and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
				}else{
					query = session.createQuery("from BusStops where status in ('New') and  point_type_id= '"+stop_type+"' and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
				}
				}else{
					if(stop_type==0){
				query = session.createQuery("from BusStops where (status in ('Approved') or manual ='Y' or 	point_type_id='2') and status !='DELETED' and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
					}else{
						query = session.createQuery("from BusStops where (status in ('Approved') or manual ='Y' or 	point_type_id='2') and status !='DELETED' and updated_by!='0' and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
					}
					}
		
		//Query query = session.createQuery("from BusStops where status != 'DELETED' and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
		//query.setMaxResults(100);
		list = (List<BusStops>) query.list();
			} catch(Exception e){
				
			}
			finally{
				  if(session!=null){
		                session.close();
		            }
			}
		//session.close();
		return list;
		
	}
	
	public BusStops getBusStops(int id){
//		BusStops busstops = new BusStops();
		BusStops busstops=null;
		try{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		 busstops = (BusStops) session.get(BusStops.class, new Integer(id));
		}catch(Exception e){
			e.printStackTrace();
		}
		return busstops;
		
	}
	
	public String saveEdited(String requestType,int id, BusStops bus){
		System.out.println("saveEdited()====");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		BusStops busstops = (BusStops) session.get(BusStops.class, id);
		
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			 //System.out.println("usrsessiontype++++++++++++++++"+usrsessiontype);
		
		// busstops.setStatus(sessstatus);
			try{
		busstops.setBusStopName(bus.getBusStopName());
		busstops.setCode1(bus.getCode1());
		if(bus.getBus_stop_name_nudi()=="" || bus.getBus_stop_name_nudi().equals("")){
			
		}else{
		busstops.setBus_stop_name_nudi(bus.getBus_stop_name_nudi());
		}
		busstops.setBusStopNameKannada(bus.getBusStopNameKannada());
		busstops.setCode2(bus.getCode2());
		busstops.setBus_stop_code(bus.getBus_stop_code());
		busstops.setBus_stop_code_kannada(bus.getBus_stop_code_kannada());
		busstops.setStop_direction(bus.getStop_direction());
		busstops.setUpdated_by(usrsessionid);
		
		
		busstops.setDescription(bus.getDescription());
		busstops.setAlias1(bus.getAlias1());
		busstops.setAlias2(bus.getAlias2());
		busstops.setAlias3(bus.getAlias3());
		busstops.setAlias4(bus.getAlias4());
		busstops.setAlias5(bus.getAlias5());
		busstops.setAlias6(bus.getAlias6());
		busstops.setKalias1(bus.getKalias1());
		System.out.println("bus.getK_alias_2()"+bus.getK_alias_3());
		busstops.setK_alias_2(bus.getK_alias_2());
		busstops.setK_alias_3(bus.getK_alias_3());
		busstops.setK_alias_4(bus.getK_alias_4());
		busstops.setK_alias_5(bus.getK_alias_5());
		busstops.setK_alias_6(bus.getK_alias_6());
		
		
		busstops.setLocality(bus.getLocality());
		busstops.setLandmark(bus.getLandmark());
		//busstops.setStatus(usrsessiontype);
		busstops.setStatus(bus.getStatus());
		
		busstops.setDeviceCode(bus.getDeviceCode());
		busstops.setTollZone(bus.getTollZone());
		busstops.setAreaPopulation(bus.getAreaPopulation());
		
		busstops.setCityLimit(bus.getCityLimit());
		busstops.setWardNumber(bus.getWardNumber());
		busstops.setStopSize(bus.getStopSize());
		
		busstops.setFareStage(bus.getFareStage());
		busstops.setSheltered(bus.getSheltered());
		busstops.setTollZone(bus.getTollZone());
		busstops.setCityLimit(bus.getCityLimit());
		
		busstops.setUpdated_date(new Date());
		busstops.setLatitude(bus.getLatitude());
		busstops.setLongitude(bus.getLongitude());
		PointType point =new PointType();
	//	System.out.println("bbbbbbbbbbbbbbbbb"+bus.getPointtype().getPoint_type_id());
		point.setPoint_type_id(bus.getPointtype().getPoint_type_id());
		busstops.setPointtype(point);
		OrganisationChart org =new OrganisationChart();
		//System.out.println("aaaaaaaaaaaaaaaaaaa"+bus.getBus_stations().getOrg_chart_id());
		org.setOrg_chart_id(bus.getBus_stations().getOrg_chart_id());
		busstops.setBus_stations(org);
		busstops.setSub_stage(bus.getSub_stage());
		//busstops.setBus_stations(bus.getBus_stations().getOrg_chart_id());
		//System.out.println("BMTS STAUS---->"+bus.getBmtcStatus());
		busstops.setBmtcStatus(bus.getBmtcStatus());
		busstops.setToll_fee(bus.getToll_fee());
		busstops.setSetLocal_bus_stop_id(1);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		try{
		session.update(busstops);
		session.getTransaction().commit();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	                session.close();
	            }
		}
		if(requestType.equals("map")){
			 request.getSession().setAttribute("centerlate", busstops.getLatitude());
			 request.getSession().setAttribute("centerlange", busstops.getLongitude());
			// System.out.println("in session+++++"+request.getSession().getAttribute("centerlat"));
			return "successmap";
		}
		else{
		return "success";
		}
		
	}
	
	
public String addStop(BusStops bus,Session session){
		
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//session.beginTransaction();
//		BusStops busstops = (BusStops) session.get(BusStops.class, id);
//		
//		 HttpServletRequest request=ServletActionContext.getRequest();
//			HttpSession sess=request.getSession();
//			
//			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//			 //System.out.println("usrsessiontype++++++++++++++++"+usrsessiontype);
//		
//		// busstops.setStatus(sessstatus);
//		busstops.setBusStopName(bus.getBusStopName());
//		busstops.setCode1(bus.getCode1());
//		busstops.setBusStopNameKannada(bus.getBusStopNameKannada());
//		busstops.setCode2(bus.getCode2());
//		busstops.setBus_stop_code(bus.getBus_stop_code());
//		busstops.setBus_stop_code_kannada(bus.getBus_stop_code_kannada());
//		busstops.setStop_direction(bus.getStop_direction());
//		busstops.setCreatedBy(usrsessionid);
//		
//		
//		busstops.setDescription(bus.getDescription());
//		busstops.setAlias1(bus.getAlias1());
//		busstops.setAlias2(bus.getAlias2());
//		busstops.setAlias3(busstops.getAlias3());
//		busstops.setAlias4(bus.getAlias4());
//		busstops.setAlias4(bus.getAlias5());
//		busstops.setAlias4(bus.getAlias6());
//		busstops.setKalias1(bus.getKalias1());
//		busstops.setK_alias_2(bus.getK_alias_2());
//		
//		busstops.setLocality(bus.getLocality());
//		busstops.setLandmark(bus.getLandmark());
//		//busstops.setStatus(usrsessiontype);
//		busstops.setStatus(bus.getStatus());
//		
//		busstops.setDeviceCode(bus.getDeviceCode());
//		busstops.setTollZone(bus.getTollZone());
//		busstops.setAreaPopulation(bus.getAreaPopulation());
//		
//		busstops.setCityLimit(bus.getCityLimit());
//		busstops.setWardNumber(bus.getWardNumber());
//		busstops.setStopSize(bus.getStopSize());
//		
//		busstops.setFareStage(bus.getFareStage());
//		busstops.setSheltered(bus.getSheltered());
//		busstops.setTollZone(bus.getTollZone());
//		busstops.setCityLimit(bus.getCityLimit());
		bus.setStatus("Approved");
		bus.setManual('Y');
		bus.setSetLocal_bus_stop_id(1);
//		bus.setBus_stop_name_nudi(bus.getBus_stop_name_nudi());
		//bus.setUpdated_by(0);
		try{
		session.save(bus);
		//session.getTransaction().commit();
		} catch(Exception e){
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	           //     session.close();
	            }
		}
		
		return "success";
		
		
	}
	


	public String deleteBusStop(String requestType,int id){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		BusStops busstops = (BusStops) session.get(BusStops.class, id);
		busstops.setStatus("DELETED");
		busstops.setUpdated_date(new Date());
		try{
		session.update(busstops);
		session.getTransaction().commit();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			 if(session!=null){
	                session.close();
	            }
		}
		//System.out.println("Requset TYpe ------>"+requestType);
		if(requestType.equals("map")){
			return "successmap";
		}
		else{
		return "success";
		}
	}
	
	public boolean checkBusstopCode(String busStopCode, int busStopId){
		//System.out.println("Inside Check BusStop");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		List<BusStops> list=null;
		try{
		Query query = session.createQuery("from BusStops where bus_stop_code ='"+busStopCode+"' and bus_stop_id != '"+busStopId+"' and status != 'DELETED'");
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
		if(list.size()>0){
			return false;
		}
		else{
		return true;
		}
		
		
	}
	
	public int getTotalRecords(String searchterm){
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HttpSession sess=request.getSession();
		String userorgtype=(String)request.getSession().getAttribute("userorgtype");
		Query query;
		List<Integer> list =null;
		try{
		if(userorgtype.equals("TRIMAX")){
			 query = session.createSQLQuery("select count(*) as count from bus_stop where status = 'New' and (bus_stop_name like '%"+searchterm+"%' or alias1 like '%"+searchterm+"%' or alias2 like '%"+searchterm+"%' or alias3 like '%"+searchterm+"%' or alias4 like '%"+searchterm+"%' or alias5 like '%"+searchterm+"%' or alias6 like '%"+searchterm+"%' or locality like '%"+searchterm+"%' or status like '%"+searchterm+"%' or bus_stop_code like '%"+searchterm+"%')").addScalar("count", Hibernate.INTEGER); 
		}else{
			 query = session.createSQLQuery("select count(*) as count from bus_stop where status in ('New','Approved', 'Inactive') and (bus_stop_name like '%"+searchterm+"%' or alias1 like '%"+searchterm+"%' or alias2 like '%"+searchterm+"%' or alias3 like '%"+searchterm+"%' or alias4 like '%"+searchterm+"%' or alias5 like '%"+searchterm+"%' or alias6 like '%"+searchterm+"%' or locality like '%"+searchterm+"%' or status like '%"+searchterm+"%' or bus_stop_code like '%"+searchterm+"%')" ).addScalar("count", Hibernate.INTEGER);
		}

		
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
	List<PointType> listPoint;
	public List<PointType> getListPoint() {
		return listPoint;
	}

	public void setListPoint(List<PointType> listPoint) {
		this.listPoint = listPoint;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total , HttpServletRequest request, String col, String dir){
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		int totalAfterFilter = total;
		
		//JSONArray array = new JSONArray();
		String searchSQL = "";
		String sql ="from BusStops where status != 'DELETED'";
		HttpSession sess=request.getSession();
		String userorgtype=(String)request.getSession().getAttribute("userorgtype");
		
		//sql += " order by " + COL_NAME + " " + DIR;

		sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		
		Criteria criteria = session.createCriteria(BusStops.class);
		//criteria.add(Restrictions.ne("status", "deleted"));
		if(userorgtype.equals("TRIMAX")){
			criteria.add(Restrictions.eq("status", "New"));	
		}else{
			String[] statuscond = { "Approved", "Inactive" };
			criteria.add(Restrictions.in("status", statuscond));	
			//criteria.add(Restrictions.eq("status", "Inactive"));	
		}
	//	System.out.println("sSearch------->"+col+dir);
		if(!col.equals("")){
			if(dir.equals("asc")){
			    criteria.addOrder(Order.asc(col));
			}else{
				criteria.addOrder(Order.desc(col));	
			}
		}
		if(!request.getParameter("sSearch").equals("")){
			String search_term = request.getParameter("sSearch");
			//int searchstringint=Integer.parseInt(search_term);
			Junction conditionGroup = Restrictions.disjunction();
			conditionGroup.add(Restrictions.like("busStopName","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("alias1","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("alias2","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("alias3","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("alias4","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias5","%"+search_term+"%" ));
//			conditionGroup.add(Restrictions.like("alias6","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("locality","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("status","%"+search_term+"%" ));
			conditionGroup.add(Restrictions.like("bus_stop_code","%"+search_term+"%" ));
			/*if (isInteger(search_term)) {
				conditionGroup.add(Restrictions.like("id","%"+Integer.parseInt(search_term)+"%" ));  
	        }*/
			
			
			criteria.add(conditionGroup);
			//criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%" ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
		}
		
		
		//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
		criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
		criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
		//System.out.println("My Criteria");
		List<BusStops> list = criteria.list();
		JSONArray array = new JSONArray();
		BusStopsDAO busstopsdao = new BusStopsDAO();
		listPoint= busstopsdao.getPointList();
		for(int i=0;i<list.size();i++){
			JSONArray ja = new JSONArray();
			
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+list.get(i).getId()+"' value='"+list.get(i).getId()+"'/>");
			ja.add(list.get(i).getId());
			ja.add(list.get(i).getBusStopName());
			ja.add(list.get(i).getBusStopNameKannada());
			ja.add(list.get(i).getBus_stop_code());
			ja.add(list.get(i).getBus_stop_code_kannada());
			ja.add(list.get(i).getLocality());
			ja.add(list.get(i).getLandmark());
			ja.add(list.get(i).getSheltered());
			ja.add(list.get(i).getAlias1());
			ja.add(list.get(i).getKalias1());
			ja.add(list.get(i).getAlias2());
			ja.add(list.get(i).getK_alias_2());
			ja.add(list.get(i).getAlias3());
			ja.add(list.get(i).getK_alias_3());
			ja.add(list.get(i).getAlias4());
			ja.add(list.get(i).getK_alias_4());
			/*ja.add(list.get(i).getAlias5());
			ja.add(list.get(i).getK_alias_5());
			ja.add(list.get(i).getAlias6());
			ja.add(list.get(i).getK_alias_6());*/
			
			ja.add(list.get(i).getTollZone());
			ja.add(list.get(i).getCityLimit());
			
			ja.add(list.get(i).getWardNumber());
			ja.add(list.get(i).getAreaPopulation());
			ja.add(list.get(i).getStop_direction());
			ja.add(list.get(i).getStopSize());
			ja.add(list.get(i).getFareStage());
			ja.add(list.get(i).getSub_stage());
			ja.add(list.get(i).getDescription());
			ja.add(list.get(i).getStatus());
			
			/*for(int j=0;j<listPoint.size();j++){
				
			}*/
			
			
			array.add(ja);
			//System.out.println("Array----->"+array);
		}
		
		totalAfterFilter = getTotalRecords(request.getParameter("sSearch"));
		
		
			
			result.put("aaData",array);
			result.put("iTotalRecords", total);
			
			result.put("iTotalDisplayRecords", totalAfterFilter);
		
		
	//	System.out.println("REsult ------>"+result);
		//session.close();
		
		}
		catch(Exception e){
			//System.out.println("Exception---->"+e);
			e.printStackTrace();
		}
		finally{
			  if(session!=null){
				  //session.close();
	            }
		}
		return result;
	}
	
	public boolean checkCoordinate(String busStoplat, String busStoplang){
		//System.out.println("Inside Check BusStop");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String lat;
		String lang;
		List<BusStops> list =null;
		if(busStoplat.length()<11){
			lat=busStoplat;	
		}else{
			lat=busStoplat.substring(0, 11);
		}
		if(busStoplang.length()<11){
			lang=busStoplang;
		}else{
			lang=busStoplang.substring(0, 11);	
		}
		try{
		Query query = session.createQuery("from BusStops where latitude_current ='"+lat+"' and longitude_current = '"+lang+"' and status !='DELETED'");
		 list = query.list();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			  if(session!=null){
				  session.close();;
	            }
		}
		if(list.size()>0){
			return false;
		}
		else{
		return true;
		}
		
		
	}
	
	public boolean checkCoordinateEdit(String busStoplat, String busStoplang){
		//System.out.println("Inside Check BusStop");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String lat;
		String lang;
		List<BusStops> list=null;
		if(busStoplat.length()<11){
			lat=busStoplat;	
		}else{
			lat=busStoplat.substring(0, 11);
		}
		if(busStoplang.length()<11){
			lang=busStoplang;
		}else{
			lang=busStoplang.substring(0, 11);	
		}
		try{
		Query query = session.createQuery("from BusStops where latitude_current ='"+lat+"' and longitude_current = '"+lang+"' and status !='DELETED'");
		 list = query.list();
		}
		catch(Exception e){
			
		}
		finally{
			  if(session!=null){
	                session.close();
	            }
		}
		//session.close();
		if(list.size()>1){
			return false;
		}
		else{
		return true;
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<BusStops> getBusStopRouteList(String lat, String lag, double para){
		List<BusStops> list=null;
		//double lat1 =12.9;
		//double lag1 =77.5; 
		double lat1 =Double.parseDouble(lat.substring(0, 5));
		double lag1 =Double.parseDouble(lag.substring(0, 5)); 
		double latmin =lat1-para;
		double latmax =lat1+para;
		double lngmin =lag1-para-0.01;
		double lngmax =lag1+para+0.01;
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from BusStops where (status in ('Approved') or manual ='Y' or 	point_type_id='2') and status !='DELETED' and (latitude_current	between '"+latmin+"' and '"+latmax+"') and (longitude_current between '"+lngmin+"' and '"+lngmax+"' )");
		query.setMaxResults(200);
		list = (List<BusStops>) query.list();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	                session.close();
	            }
		}
		
		
		List<BusstopJson> list1 = new ArrayList<BusstopJson>();
		for(BusStops bustop : list){
			BusstopJson bus = new BusstopJson();
			//bus.setId(bustop.getId());
			bus.setValue(bustop.getBusStopName());
			bus.setBusStopName(bustop.getBusStopName());
			bus.setLongitude(bustop.getLongitude());
			bus.setLatitude(bustop.getLatitude());
			//bus.setId(bustop.getId());
			list1.add(bus);
			//i+=1;
		}
		return list;
		//System.out.println("/*/*/*/*/*/*/"+new JSONArray(list1));
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BusStops> getBusStopRouteList(String lat, String lag, double para, String busStopPoint){
		List<BusStops> list=null;
		//double lat1 =12.9;
		//double lag1 =77.5; 
		double lat1 =Double.parseDouble(lat.substring(0, 5));
		double lag1 =Double.parseDouble(lag.substring(0, 5)); 
		double latmin =lat1-para;
		double latmax =lat1+para;
		double lngmin =lag1-para-0.01;
		double lngmax =lag1+para+0.01;
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from BusStops where (status in ('Approved') or manual ='Y' or 	point_type_id='2') and status !='DELETED' and bus_stop_id in ("+busStopPoint+") ");
		query.setMaxResults(200);
		list = (List<BusStops>) query.list();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	                session.close();
	            }
		}
		
		
		List<BusstopJson> list1 = new ArrayList<BusstopJson>();
		for(BusStops bustop : list){
			BusstopJson bus = new BusstopJson();
			//bus.setId(bustop.getId());
			bus.setValue(bustop.getBusStopName());
			bus.setBusStopName(bustop.getBusStopName());
			bus.setLongitude(bustop.getLongitude());
			bus.setLatitude(bustop.getLatitude());
			//bus.setId(bustop.getId());
			list1.add(bus);
			//i+=1;
		}
		return list;
		//System.out.println("/*/*/*/*/*/*/"+new JSONArray(list1));
	}
	
	@SuppressWarnings("unchecked")
	public List<PointType> getPointList(){
		List<PointType> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from PointType where status = 'ACTIVE' and deleted_status='0'");
		//query.setMaxResults(20);
		list = (List<PointType>) query.list();
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
	
	@SuppressWarnings("unchecked")
	public List<BusStops> getBusStopRouteList(String lat, String lag, String idvald, double para){
		List<BusStops> list=null;
		double lat1 =Double.parseDouble(lat.substring(0, 5));
		double lag1 =Double.parseDouble(lag.substring(0, 5)); 
		double latmin =lat1-para;
		double latmax =lat1+para;
		double lngmin =lag1-para-0.01;
		double lngmax =lag1+para+0.01;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			//String idvald;
			String userorgtype=(String)request.getSession().getAttribute("userorgtype");
			/*if (idvald1 == null | idvald1.length() == 0) {
				 idvald="'0'";
			}
			else{
				idvald=idvald1;
			}*/
			try{
			if(userorgtype.equals("TRIMAX")){
				query = session.createQuery("from BusStops where status in ('New') and ((latitude_current	between '"+latmin+"'and '"+latmax+"') and (longitude_current between '"+lngmin+"'and '"+lngmax+"'))  and bus_stop_id not in ("+idvald+")");
			}else{
				query = session.createQuery("from BusStops where (status ='Approved' or manual='Y' or point_type_id='2') and status !='DELETED' and ((latitude_current	between '"+latmin+"'and '"+latmax+"') and (longitude_current between '"+lngmin+"'and '"+lngmax+"')) and bus_stop_id not in ("+idvald+")");
			}
		
		query.setMaxResults(175);
//		Criteria criteria = session.createCriteria(BusStops.class);
//		criteria.add(Restrictions.ne("status", "deleted"));
		list = (List<BusStops>) query.list();
			}
			catch(Exception e){
				System.out.println(e);
			}
			finally{
				  if(session!=null){
		                session.close();
		            }
			}
		
		List<BusstopJson> list1 = new ArrayList<BusstopJson>();
		for(BusStops bustop : list){
			BusstopJson bus = new BusstopJson();
			//bus.setId(bustop.getId());
			bus.setValue(bustop.getBusStopName());
			bus.setBusStopName(bustop.getBusStopName());
			bus.setLongitude(bustop.getLongitude());
			bus.setLatitude(bustop.getLatitude());
			//bus.setId(bustop.getId());
			list1.add(bus);
			//i+=1;
		}
		//session.close();
		return list;
		//System.out.println("/*/*/*/*/*/*/"+new JSONArray(list1));
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<BusStops> getBusStopReloadRouteList( String idvald){
		List<BusStops> list=null;
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			//String idvald;
			String userorgtype=(String)request.getSession().getAttribute("userorgtype");
			/*if (idvald1 == null | idvald1.length() == 0) {
				 idvald="'0'";
			}
			else{
				idvald=idvald1;
			}*/
			try{
			
				query = session.createQuery("from BusStops where  bus_stop_id  in ("+idvald+")");
			
		
		//query.setMaxResults(175);
//		Criteria criteria = session.createCriteria(BusStops.class);
//		criteria.add(Restrictions.ne("status", "deleted"));
		list = (List<BusStops>) query.list();
			}
			catch(Exception e){
				System.out.println(e);
			}
			finally{
				  if(session!=null){
		                session.close();
		            }
			}
		
		List<BusstopJson> list1 = new ArrayList<BusstopJson>();
		for(BusStops bustop : list){
			BusstopJson bus = new BusstopJson();
			//bus.setId(bustop.getId());
			bus.setValue(bustop.getBusStopName());
			bus.setBusStopName(bustop.getBusStopName());
			bus.setLongitude(bustop.getLongitude());
			bus.setLatitude(bustop.getLatitude());
			//bus.setId(bustop.getId());
			list1.add(bus);
			//i+=1;
		}
		//session.close();
		return list;
		//System.out.println("/*/*/*/*/*/*/"+new JSONArray(list1));
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OrganisationChart> getOrgChart(){
		List<OrganisationChart> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from OrganisationChart where 	org_type_id = 4 and deleted_status='0'");
		//query.setMaxResults(20);
		list = (List<OrganisationChart>) query.list();
		}
		catch(Exception e){
			//session.close();
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	                session.close();
	            }
		}
		return list;
		
	}
	
	
	public List<RoutePoint> checkStopStatus(int busStopId, Session session){
		//System.out.println("Inside Check delete");
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<RoutePoint> list=null;
		session.beginTransaction();
		try{
		Query query = session.createQuery("from RoutePoint  where bus_stop_id='"+busStopId+"' and routepoint.deleted_status='0' and point_status='ACTIVE' and (now()< effective_till or effective_till='0000-00-00 00:00:00' or effective_from > now()) and effective_from != effective_till");
		 list = query.list();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			  if(session!=null){
	               // session.close();
	            }
		}
		if(list.size()>0){
			return list;
		}
		else{
		return list;
		}
		
		
	}
	
	
	public List<BusStopGroupStop> checkStopingroup(int busStopId, Session session){
		//System.out.println("Inside Check delete");
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<BusStopGroupStop> list=null;
		//session.beginTransaction();
		try{
		Query query = session.createQuery("from BusStopGroupStop bs where bus_stop_group_stop.id='"+busStopId+"' and status='Y' and group_id.deleted_status=0");
		 list = query.list();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	             //   session.close();
	            }
		}
		if(list.size()>0){
			return list;
		}
		else{
		return list;
		}
		
		
	}
	
	
	public List<BusStopGroup> getgroupname(String groupid){
		//System.out.println("Inside Check delete");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<BusStopGroup> list=null;
		session.beginTransaction();
		try{
		Query query = session.createQuery("from BusStopGroup where group_id in ("+groupid+") and deleted_status='0' and status='ACTIVE'");
		 list = query.list();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	               // session.close();
	            }
		}
		if(list.size()>0){
			return list;
		}
		else{
		return list;
		}
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OrganisationChart> getOrgChart(int id){
		List<OrganisationChart> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from OrganisationChart where 	org_type_id = '"+id+"' and deleted_status='0'");
		//query.setMaxResults(20);
		list = (List<OrganisationChart>) query.list();
		}
		catch(Exception e){
			//session.close();
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	                session.close();
	            }
		}
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PointType> getPointList(int id){
		List<PointType> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from PointType where 	point_type_id='"+id+"' and status != 'DELETED' and deleted_status='0'");
		//query.setMaxResults(20);
		list = (List<PointType>) query.list();
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
	
	public List<Route> getRouteNumber(String routeid){
		//System.out.println("Inside Check delete");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Route> list=null;
		session.beginTransaction();
		try{
		Query query = session.createQuery("from Route where route_id in ("+routeid+") and deleted_status='0' and (effective_till>= now() or effective_till='0000-00-00 00:00:00')");
		 list = query.list();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			  if(session!=null){
	               // session.close();
	            }
		}
		if(list.size()>0){
			return list;
		}
		else{
		return list;
		}
		
		
	}
	
	public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
	
	public int getStopPointData(int busStopId){
		List<Integer> list=null ;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createSQLQuery("select count(*) cnt from route r " +
				" inner join route_point rp on rp.route_id= r.route_id" +
				" inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id" +
				" where bs.bus_stop_id='"+busStopId+"'   and r.deleted_status=0; ").addScalar("cnt", Hibernate.INTEGER);
		list= query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		int cnt = list.get(0);
		return cnt;

	}
	
	public int getTollRouteData(int busStopId){
		List<Integer> list=null ;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createSQLQuery("select count(*) cnt from route r " +
				" inner join route_point rp on rp.route_id= r.route_id" +
				" inner join bus_stop bs on rp.bus_stop_id=bs.bus_stop_id" +
				" where bs.bus_stop_id='"+busStopId+"' and r.deleted_status=0 and now() between r.effective_from and if(effective_till='0000-00-00 00:00:00',now(),effective_till); ").addScalar("cnt", Hibernate.INTEGER);
		list= query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		int cnt = list.get(0);
		return cnt;

	}
	
	public String getDragAuthUsers(){
		List<Integer> list=null ;
		Session session = null;
		String sys_value="";
		try{
		/*Query query = session.createSQLQuery("select sys_value from default_system_veriable dsv " +
				" where dsv.sys_key='AUTH_DRAG_USERS' ; ").addScalar("sys_value", Hibernate.STRING);
		list= query.list();*/
			String sql = "select sys_value from default_system_veriable dsv " +
				" where dsv.sys_key='AUTH_DRAG_USERS' ; ";

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				Map<String, Object> rs = aliasToValueMapList.get(0);
				 sys_value=rs.get("sys_value").toString();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		return sys_value;

	}

}
