package com.trimax.its.vts.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.revenuesector.model.RevenueSector;
import com.trimax.its.route.model.RouteMap;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleNumber;
import com.trimax.its.vts.model.SarthiSectorGeofence;
import com.trimax.its.vts.model.SectorVehicleRelation;

public class SarthiSectorGeofenceDao {



		public int getTotalRecords(HttpServletRequest request, String col,String dir) {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			int cnt = 0;
			String sql = "";
			try {

				

		
				sql = " select sector_id,sector_name,geo_fence, status, notes from sector where deleted_status=0 and status='ACTIVE' "; 
						

				String search_term1 = request.getParameter("sSearch");

				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					sql += " and  sector_id like '" + search_term
							+ "%'";
					sql += " OR  sector_name like '"
							+ search_term + "%' and deleted_status=0";
					/*sql += " OR v.license_number like '" + search_term
							+ "%' and sr.deleted_status=0";*/
				/*	sql += " OR status '"+search_term+"%' ";*/
					

				}

				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();

				cnt = aliasToValueMapList.size();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}
			return cnt;
			
			
		}

		@SuppressWarnings("unchecked")
		public JSONObject getData(int total, HttpServletRequest request,
				String col, String dir,String viewdeletedrecord) {

//			System.out.println("Hii in RevenueSector Doa");
			//System.out.println("\n \t IN JSONObject Method.........");
			Session session1 = null;
			Transaction transaction  = null;
			
			JSONObject result = new JSONObject();
			JSONArray array = new JSONArray();
			try {
			String 	sql = " select sector_id,sector_name,geo_fence, status, notes from sector where deleted_status=0 and status='ACTIVE' ";
				 
				session1 = HibernateUtil.getSession("hibernate.cfg.xml");
				 transaction = session1.beginTransaction();
				Query query = session1.createSQLQuery(sql);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				//System.out.println("aliasToValueMapList.size()"+aliasToValueMapList.size());
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> list = aliasToValueMapList.get(i);
					JSONArray ja = new JSONArray();
				//	System.out.println(list.get("sector_id").toString());
					ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
							+ list.get("sector_id").toString()
							+ "' value='"
							+ list.get("sector_id").toString()
							+ "'/>");
					ja.add(list.get("sector_name"));
					//ja.add(list.get("license_number")!=null?list.get("license_number"): " " );
					ja.add(list.get("geo_fence")!=null? "Available":"Not Available");
					ja.add(list.get("status").toString());
				    ja.add(list.get("notes").toString());
					
					
					array.add(ja);
				}
				result.put("aaData", array);
				result.put("iTotalRecords", aliasToValueMapList.size());

				result.put("iTotalDisplayRecords", aliasToValueMapList.size());
			//	System.out.println("REsult ------>" + request.getParameter("iDisplayStart"));
			} catch (Exception ex) {
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
				//ex.printStackTrace();
			}
			finally{
				if(session1!=null && session1.isOpen()){
					session1.close();
				}
			}
			return result;
		}

		@SuppressWarnings("unchecked")
		public JSONObject getList(int total, HttpServletRequest request,String col, String dir) {
			 int totalAfterFilter = total;
				JSONObject result = new JSONObject();
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				try {
//					String viewdeletedrecord = (String) request.getSession()
//							.getAttribute("viewdeletedrecord");
					String sql = "";
					String employeenamekannda = "";

					
					sql = " select sector_id,sector_name,geo_fence, status, notes from sector where deleted_status=0 and status='ACTIVE' ";
					
				// System.out.println("sql--------"+sql);

					String search_term1 = request.getParameter("sSearch");
					// //System.out.println("search_term-------"+search_term1);
					if (search_term1 != null && !search_term1.equals("")) {
						String search_term = request.getParameter("sSearch").trim();
						sql += " and  sector_id like '" + search_term
								+ "%'";
						sql += " OR  sector_name like '"
								+ search_term + "%' and deleted_status=0";
						
					/*	sql += " OR status '"+search_term+"%' ";*/
						

					}
					

					if (!col.equals("")) {
						if (dir.equals("asc")) {
							sql += " order by " + col + " asc";
						} else {
							sql += " order by " + col + " desc";
						}
					}

					/*
					 * int cntdetails=getTotalRecords(); if(cntdetails>10)
					 */
					// {
					sql += " limit " + request.getParameter("iDisplayStart") + ", "
							+ request.getParameter("iDisplayLength");
				// }
			     //  System.out.println("sql----------"+sql);
					Query query = session.createSQLQuery(sql);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> aliasToValueMapList = query.list();

					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						JSONArray ja = new JSONArray();
						ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
								+ rs.get("sector_id")
								+ "' value='"
								+ rs.get("sector_id")
								+ "'/>");
						ja.add(rs.get("sector_id").toString());
		 				ja.add(rs.get("sector_name").toString()); 
		 				/*if(rs.get("license_number")==null||rs.get("license_number").equals("")){
		 					ja.add("  ");
		 					
		 				}else{
		 					ja.add(rs.get("license_number").toString());
		 				}*/
						//ja.add(rs.get("license_number").toString()!=null?rs.get("license_number").toString(): " " );
						ja.add(rs.get("geo_fence").toString()!=null?"Available":"Not Available");
						ja.add(rs.get("status").toString());
						ja.add(rs.get("notes").toString()!=null?rs.get("notes").toString():" ");
					
						


						array.add(ja);

					}
					@SuppressWarnings("unused")
					int cnt = 0;
					 totalAfterFilter = aliasToValueMapList.size();
					result.put("aaData", array);
				@SuppressWarnings("unused")
				String search_term2 = request.getParameter("sSearch");

				@SuppressWarnings("unused")
				String search_term3 = request.getParameter("sSearch").trim();
					cnt = getTotalRecords(request, col, dir);// getTotalRecordsForCount(search_term3);

				result.put("iTotalRecords", totalAfterFilter);

				result.put("iTotalDisplayRecords", totalAfterFilter);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (session != null) {
						// session.close();
					}
				}
				return result;

			}
		public boolean checkSectorName(String sector_name) {
			boolean flag = false;
			try{
				
			
			
			String qry = " select sector_name from  sector where  deleted_status=0 and sector_name='"
					+ sector_name + "'  ";
			Query query = HibernateUtil.getSession("hibernate.cfg.xml")
					.createSQLQuery(qry);

			//System.out.println("QUery Size===>" + query.list().size());
			if (query.list().size() > 0) {
				flag = true;
			}}
			catch(Exception e){
				
			}finally{
				HibernateUtil.closeSession();
			}
			return flag;
		}

		public int insertRevenueSector(RevenueSector revenue) {
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			int i = 0;
			//System.out.println("000000000000000000");
			try {
				i = (Integer) session.save(revenue);
				session.getTransaction().commit();
			} catch (Exception e) {
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",e);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(e.getMessage());
	            ErrorLogDAO.saveException(log);
			} finally {
				session.close();
			}
			return i;

		}

		public String deleterevenue(SarthiSectorGeofence revenue, int device_type_id) {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
	        int updatedby=  Integer.parseInt(request.getSession()
					.getAttribute("userid").toString());
	       
	     
	       // String strTime = sdfTime.format(now);

			// System.out.println("=====Route id" + d1);
			try {
				//
				SarthiSectorGeofence revenue1 = (SarthiSectorGeofence) session.get(
						SarthiSectorGeofence.class, device_type_id);
                
				revenue1.setStatus("INACTIVE");
				revenue1.setDeleted_status(1);
				revenue1.setUpdated_by(revenue.getUpdated_by());
				revenue1.setUpdated_date(new java.util.Date());
				String sql = "update  sector_vehicle_rel set status='INACTIVE' , deleted_status='1',updated_by='"+updatedby+"',updated_date=now()  where sector_id=" + device_type_id;
//				System.out.println("Query Line" + sql);
			
				
			    	/*Transaction txn=null;

					
					txn = session.beginTransaction();*/
					Query query = session.createSQLQuery(sql);
				    session.update(revenue1);
				    query.executeUpdate();
				    session.getTransaction().commit();
			} catch (Exception ex) {
				session.getTransaction().rollback();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			} finally {
				session.close();
			}

			return null;
		}

		public String updateRevenuSector(SarthiSectorGeofence revenue, int sector_id) {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
//			System.out.println("=====Information" + revenue.getSector_id() + ""
//					+ revenue.getSector_name() + "" + revenue.getStatus() + ""
//					+ revenue.getNotes());
			try {
				
				SarthiSectorGeofence revenue1 = (SarthiSectorGeofence) session.load(
						SarthiSectorGeofence.class, sector_id);
				revenue1.setSector_name(revenue.getSector_name());
				revenue1.setNotes(revenue.getNotes());
				revenue1.setStatus(revenue.getStatus());
				//revenue1.setVehicle(revenue.getVehicle());
				revenue1.setUpdated_by(revenue.getUpdated_by());
				revenue1.setUpdated_date(new java.util.Date());
				// Code to Update FareChartMaster...
				session.update(revenue1);
				session.getTransaction().commit();
			} catch (Exception ex) {
				session.getTransaction().rollback();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			} finally {
				session.close();
			}

			return null;
		}

		public SarthiSectorGeofence getEditedRevenueSector(int id) {
			// BusStops busstops = new BusStops();
			Session session =null;
			SarthiSectorGeofence revenue=null;
			try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 revenue = (SarthiSectorGeofence) session.get(
					 SarthiSectorGeofence.class, new Integer(id));
			
			}catch(Exception e){
				
			}
			finally{
				if(session.isOpen() && session!=null){
					session.close();
				}
			}
			
			return revenue;

		}

		public boolean checkRevenueSectorTypeForUpdate(String devicename, int id) {
			boolean flag = false;
			String qry = " select sector_name from sector where  deleted_status=0 and sector_name='"
					+ devicename + "' and sector_id= " + id;
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);

//			System.out.println("QUery Size===>" + query.list().size());
			if (query.list().size() > 0) {
				flag = true;
			}
			return flag;
		}

		public int saveOrgChart(SarthiSectorGeofence revenue) {
			int i = 0;
			Session session=null;
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
	          session = HibernateUtil.getSession("hibernate.cfg.xml");
			try {
				revenue.setGeo_fence("");
				session.beginTransaction();
				i = (Integer) session.save(revenue);
				session.getTransaction().commit();
			} catch (Exception ex) {
				session.getTransaction().rollback();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			}finally{
				if(session.isOpen() && session!=null){
					session.close();
				}
				
				
			}
			return i;
		}

		public void upDateLineString(String lineString, int sector_id) {
			String sql = "update sector set geo_fence=GeomFromText(' "+lineString + " ') where sector_id=" + sector_id;
//			System.out.println("Query Line" + sql);
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
			Session session = null;
			Transaction txn = null;
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				txn = session.beginTransaction();
				Query query = session.createSQLQuery(sql);
				query.executeUpdate();
				txn.commit();
			} catch (Exception ex) {
				
				txn.rollback();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			} finally {
				if(session.isOpen() && session!=null){
					session.close();
				}
			}
		}

		public String getText(int sector_id) {
			String sql = "select asText(geo_fence) as geo_fence from  sector where sector_id="+sector_id;
			Session session = null;
			String lineString = "";
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				Query query = session.createSQLQuery(sql).addScalar("geo_fence",Hibernate.STRING);
				if (query.list().size() > 0) {
					//System.out.println("query.uniqueResult().toString()"+ query.uniqueResult().toString());
					lineString = query.uniqueResult().toString();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			} finally {
				if(session.isOpen() && session!=null){
					session.close();
				}
			}
			return lineString;
		}
	//}
		
	//vehicle list 
		public Map<Integer, String> getVehicleList(String orgChartId,String VehicleIdList) {
			// TODO Auto-generated method stub
			System.out.println("vehiclessssssss"+VehicleIdList);
			if(VehicleIdList==null||VehicleIdList.isEmpty()||VehicleIdList.equals("")){
				VehicleIdList="0";
			}else{
				VehicleIdList=VehicleIdList;
			}
			
			Map<Integer, String> resultMap = new HashMap<Integer, String>();
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery("select vehicle_id as vehicleId,license_number as vehicleRegistrationNumber" +
					" from vehicle where status='Active' and deleted_status=0 and org_chart_id in ("+orgChartId+") and vehicle_id not in ("+VehicleIdList+") order by license_number").addScalar("vehicleId", Hibernate.INTEGER).addScalar("vehicleRegistrationNumber", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(Vehicle.class));
			
			try{
			List<Vehicle> list = query.list();
			
			for (Vehicle vehicle : list) {
				resultMap.put(vehicle.getVehicleId(), vehicle.getVehicleRegistrationNumber().trim());
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
		
		@SuppressWarnings("unchecked")
		public List<VehicleNumber> getVehicleDropList(String name,String vehicleId){
			List<Vehicle> list=null;
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Query query;
			 HttpServletRequest request=ServletActionContext.getRequest();
				HttpSession sess=request.getSession();
				List<Map<String, Object>> aliasToValueMapList =null;
				List<VehicleNumber> list1 = new ArrayList<VehicleNumber>();
				try{
				
					//query = session.createQuery("from Vehicle where deleted_status=0");
					/* query = session.createQuery("from Vehicle where status='Active' and deleted_status=0 and (vehicleRegistrationNumber like '"+name+"%')");
					query.setResultTransformer(Transformers.aliasToBean(Vehicle.class));
					list = (List<Vehicle>) query.list();*/
					
					String qry = "select vehicle_id,license_number from vehicle where status = 'Active' and deleted_status = '0' and (license_number like '"+name+"%') and vehicle_id not in ('"+vehicleId+"')  order by license_number ";
					Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					 aliasToValueMapList = query.list();
						for (int i = 0; i < aliasToValueMapList.size(); i++) {
							Map<String, Object> rs = aliasToValueMapList.get(i);
							VehicleNumber vehiclenumberdetails = new VehicleNumber();
							vehiclenumberdetails.setId(Integer.parseInt(rs.get("vehicle_id").toString()));
							vehiclenumberdetails.setValue(rs.get("license_number").toString());
							vehiclenumberdetails.setVehicleNumber(rs.get("license_number").toString());
							list1.add(vehiclenumberdetails);
						}
					//System.out.println("list============"+aliasToValueMapList.size());
				} catch(Exception e){
					e.printStackTrace();
				}
				finally{
					  if(session!=null){
			                session.close();
			            }
				}
			//session.close();
			return list1;
			
		}
		//
		public String ListofVehicle() {
			String sql = "select  IFNULL(GROUP_CONCAT(vehicle_id),0) as vehicle_id from  sector_vehicle_rel where deleted_status=0 and status='ACTIVE'";
			Session session = null;
			String vehicleIdString = "";
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				Query query = session.createSQLQuery(sql).addScalar("vehicle_id",Hibernate.STRING);
				if (query.list().size() > 0) {
					//System.out.println("query.uniqueResult().toString()"+ query.uniqueResult().toString());
					vehicleIdString = query.uniqueResult().toString();
					
			
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			} finally {
				if(session.isOpen() && session!=null){
					session.close();
				}
			}
			return vehicleIdString;
		}
		/*public int saveSectorVehicleRelation(SectorVehicleRelation revenue) {
			int i = 0;
			Session session=null;
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
	          session = HibernateUtil.getSession("hibernate.cfg.xml");
			try {
				
				revenue.setCreated_by(request.getSession()
						.getAttribute("userid").toString());
				revenue.setCreated_date(new java.util.Date());
				//revenue.setGeo_fence("");
				session.beginTransaction();
				i = (Integer) session.save(revenue);
				session.getTransaction().commit();
			} catch (Exception ex) {
				session.getTransaction().rollback();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			}finally{
				if(session.isOpen() && session!=null){
					session.close();
				}
				
				
			}
			return i;
		}*/
		
		public String saveSectorVehicleRelation(SectorVehicleRelation revenue,Session session){

			//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//session.beginTransaction();
			session.saveOrUpdate(revenue);
			//session.getTransaction().commit();
			//session.close();
			
			return "success";
			
		}
		
		@SuppressWarnings("unchecked")
		public JSONObject getAlloacatedVehicles(int total, HttpServletRequest request,String col, String dir) {
			 int totalAfterFilter = total;
				JSONObject result = new JSONObject();
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				try {
				String sectorId = (String) request.getSession()
						.getAttribute("sectorId");
			    	//String sectorId=ServletActionContext.getRequest().getParameter("sectorId");
					String sql = "";
					String employeenamekannda = "";

					
					sql = " select s.sector_id,s.vehicle_id,v.license_number from sector_vehicle_rel s inner join vehicle v " +
							" on v.vehicle_id=s.vehicle_id  where s.deleted_status=0 and s.status='ACTIVE' and s.sector_id='"+sectorId+"' " ;
					
				// System.out.println("sql--------"+sql);

					String search_term1 = request.getParameter("sSearch");
					// //System.out.println("search_term-------"+search_term1);
					if (search_term1 != null && !search_term1.equals("")) {
						String search_term = request.getParameter("sSearch").trim();
						/*sql += " and  s.vehicle_id like '" + search_term
								+ "%'";*/
						sql += " and  v.license_number like '%"+search_term+"%'   and s.deleted_status=0 and s.sector_id='"+sectorId+"' ";
						
					/*	sql += " OR status '"+search_term+"%' ";*/
						

					}
					

					if (!col.equals("")) {
						if (dir.equals("asc")) {
							sql += " order by " + col + " asc";
						} else {
							sql += "order by " + col + " desc";
						}
					}

					/*
					 * int cntdetails=getTotalRecords(); if(cntdetails>10)
					 */
					// {
					sql += " limit " + request.getParameter("iDisplayStart") + ", "
							+ request.getParameter("iDisplayLength");
				// }
			     //  System.out.println("sql----------"+sql);
					Query query = session.createSQLQuery(sql);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> aliasToValueMapList = query.list();

					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						JSONArray ja = new JSONArray();
						ja.add("<input type='checkbox' class='checkbox1' data-set='#sample_2 .checkboxes' id='"
								+ rs.get("vehicle_id")
								+ "' value='"
								+ rs.get("vehicle_id")
								+ "'/>");
					//	ja.add(rs.get("vehicle_id").toString());
		 				ja.add(rs.get("license_number").toString()); 
		 				
						


						array.add(ja);

					}
					@SuppressWarnings("unused")
					int cnt = 0;
					 totalAfterFilter = aliasToValueMapList.size();
					result.put("aaData", array);
				@SuppressWarnings("unused")
				String search_term2 = request.getParameter("sSearch");

				@SuppressWarnings("unused")
				String search_term3 = request.getParameter("sSearch").trim();
					cnt = getTotalRecordsForVehicle(request, col, dir);// getTotalRecordsForCount(search_term3);

				result.put("iTotalRecords", totalAfterFilter);

				result.put("iTotalDisplayRecords", totalAfterFilter);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (session != null) {
						// session.close();
					}
				}
				return result;

			}
		public int getTotalRecordsForVehicle(HttpServletRequest request, String col,String dir) {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			int cnt = 0;
			String sql = "";
			try {

				
				String sectorId = (String) request.getSession()
						.getAttribute("sectorId");
		
				sql = " select s.sector_id,s.vehicle_id,v.license_number from sector_vehicle_rel s inner join vehicle v" +
						" on v.vehicle_id=s.vehicle_id  where s.deleted_status=0 and s.status='ACTIVE' and s.sector_id='"+sectorId+"'  " ;
						

				String search_term1 = request.getParameter("sSearch");

				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					/*sql += " and  s.vehicle_id like '" + search_term
							+ "%'";*/
					sql += " and  v.license_number like '%"+search_term+"%'  and  s.deleted_status=0 and s.sector_id='"+sectorId+"' ";
					

				}

				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();

				cnt = aliasToValueMapList.size();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}
			return cnt;
			
			
		}

		public void deleteAssignedVehicle(String vehicleId,String sector_id) {
			HttpServletRequest request=ServletActionContext.getRequest();
			 int updatedby=  Integer.parseInt(request.getSession()
						.getAttribute("userid").toString());
			
			String sql = "update sector_vehicle_rel  set deleted_status=1 ,status='INACTIVE' ,updated_by='"+updatedby +"',updated_date=now()  where sector_id=" + sector_id+ " and vehicle_id in ("+vehicleId+") ";
//			System.out.println("Query Line" + sql);
		
	        HttpServletResponse response = ServletActionContext.getResponse();
			Session session = null;
			Transaction txn = null;
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				txn = session.beginTransaction();
				Query query = session.createSQLQuery(sql);
				query.executeUpdate();
				txn.commit();
			} catch (Exception ex) {
				
				txn.rollback();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			} finally {
				if(session.isOpen() && session!=null){
					session.close();
				}
			}
		}
		
		public Map<Integer, String> getOrganizationUnit() {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub

			Map<Integer, String> resultMap = new HashMap<Integer, String>();
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session
					.createSQLQuery("select  org_type_id,IF(org_type  = 'CENTRAL OFFICE',CONCAT(org_type,' / SARTHI VEHICLES '),org_type) org_type   from org_type where status='ACTIVE' and org_type_id in (1,2,3)");

			try {

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);

					int key = Integer.parseInt(rs.get("org_type_id").toString());
					String employeename = rs.get("org_type").toString();
					//String pf = rs.get("PF").toString();
					String value = employeename ;
					// resultMap.put(key, rs.get("EMPLOYEE_NAME").toString());
					resultMap.put(key, value);

				}

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
				return resultMap;

			}
}
		
		public String ListofOrgChart(int org_chart) {
			String sql = "select  GROUP_CONCAT(org_chart_id) as org_chart_id  from org_chart where org_type_id="+org_chart+"  and deleted_status=0 ";
			Session session = null;
			String vehicleIdString = "";
			HttpServletRequest request = ServletActionContext.getRequest();
	        HttpServletResponse response = ServletActionContext.getResponse();
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				Query query = session.createSQLQuery(sql).addScalar("org_chart_id",Hibernate.STRING);
				if (query.list().size() > 0) {
					//System.out.println("query.uniqueResult().toString()"+ query.uniqueResult().toString());
					vehicleIdString = query.uniqueResult().toString();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			} finally {
				if(session.isOpen() && session!=null){
					session.close();
				}
			}
			return vehicleIdString;
		}
}