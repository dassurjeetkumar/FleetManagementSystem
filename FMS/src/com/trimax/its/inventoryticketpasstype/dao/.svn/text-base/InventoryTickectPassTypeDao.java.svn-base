package com.trimax.its.inventoryticketpasstype.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.inventoryticketpasstype.model.InventoryTicketPassType;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.revenuesector.model.RevenueSector;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.Complaint;

public class InventoryTickectPassTypeDao {
	
	
	
	
	public int getTotalRecords(int total, HttpServletRequest request,
			String cols, String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*
		 * Query query = session.createSQLQuery(
		 * "select count(*) as count from device_type where deleted_status=0 "
		 * ).addScalar( "count", Hibernate.INTEGER); List<Integer> list =
		 * query.list(); int cnt = list.get(0); System.out.println(cnt);
		 */
		//System.out.println("Hii in Revenue Doa>>>");
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(InventoryTicketPassType.class);
		}else{
			 criteria = session.createCriteria(InventoryTicketPassType.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}
		
		
		
		
		
		//Criteria criteria = session.createCriteria(InventoryTicketPassType.class);
		//criteria.add(Restrictions.eq("deleted_status", 0));
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("day_month", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status", search_term,
					MatchMode.START));
			criteria.add(conditionGroup);
			// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
			// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}

		List<RevenueSector> list = criteria.list();
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return list.size();
		
		
		
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,
			String col, String dir,String viewdeletedrecord) {

//		System.out.println("Hii in RevenueSector Doa");
		JSONObject result = new JSONObject();
		Session session =null;
		
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from InventoryTicketPassType ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(InventoryTicketPassType.class);
			}else{
				 criteria = session.createCriteria(InventoryTicketPassType.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			
			
			
			//Criteria criteria = session.createCriteria( InventoryTicketPassType.class);
		//	criteria.add(Restrictions.eq("deleted_status", 0));
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("day_month","%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,
						MatchMode.START));
				criteria.add(conditionGroup);
				// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
				// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			List<InventoryTicketPassType> list = criteria.list();
			JSONArray array = new JSONArray();
		//	System.out.println("List size----->" + list.size() + "\t"
			//		+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getInventorypass_id()
						+ "' value='"+ list.get(i).getInventorypass_id() + "'/>");
						
				ja.add(list.get(i).getInventorypass_id());
				ja.add(list.get(i).getDay_month());
				ja.add(list.get(i).getPass_type() !=1? "Monthly Pass":"Daily Pass");
				ja.add(list.get(i).getStatus());
				
				
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				//System.out.println("Array----->" + array);
			}
				
				
				
				
				
				
				
				
				
				
//				if(list.get(i).getGeo_fence()==null)
//				{
//				ja.add("Geo Fence Not Available");
//				}else{
//					ja.add("Available");
				//	ja.add(list.get(i).getStatus());
					//ja.add(list.get(i).getNotes());
				//}
				

				// ja.add(list.get(i).getNotes());
				// ja.add(list.get(i).getStatus());

				//array.add(ja);
				//System.out.println("Array----->" + array);
		

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
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
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	//duplicate day_month
	public boolean checkDay_Month(String day_month,int pass_type) {
		boolean flag = false;
		try{
			
		
		
		String qry = " select day_month,pass_type from InventoryTicket_PassType where  deleted_status=0 and  day_month='"
				+ day_month + "' and pass_type='"+pass_type+"'";
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
	
	//save InventortyTicket Pass Type

	public int saveInventortyTicket(InventoryTicketPassType inventory) {
		int i = 0;
		Session session=null;
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
          session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			//revenue.setGeo_fence("");
			session.beginTransaction();
			i = (Integer) session.save(inventory);
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
	//Delete
	public String deleteinventorypass(InventoryTicketPassType revenue, int device_type_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
		// System.out.println("=====Route id" + role.getRole_id());
		try {
			//
			InventoryTicketPassType revenue1 = (InventoryTicketPassType) session.get(
					InventoryTicketPassType.class, device_type_id);

			revenue1.setStatus("INACTIVE");
			revenue1.setDeleted_status(1);
			revenue1.setUpdated_by(revenue.getUpdated_by());
			revenue1.setUpdated_date(new java.util.Date());

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

	//Edit List
	public InventoryTicketPassType getEditedInventoryTicketPassType(int id) {
		// BusStops busstops = new BusStops();
		Session session =null;
		InventoryTicketPassType revenue=null;
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 revenue = (InventoryTicketPassType) session.get(
				 InventoryTicketPassType.class, new Integer(id));
		
		}catch(Exception e){
			
		}
		finally{
			if(session.isOpen() && session!=null){
				session.close();
			}
		}
		
		return revenue;

	}
	public boolean checkDayMonthTypeForUpdate(String devicename, int id) {
		boolean flag = false;
		String qry = "select day_month from InventoryTicket_PassType where  deleted_status=0 and  day_month='"
				+ devicename + "' and inventorypass_id= " + id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

//		System.out.println("QUery Size===>" + query.list().size());
		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

	
	public String updateInventoryTicketPassType(InventoryTicketPassType revenue, int sector_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
//		System.out.println("=====Information" + revenue.getSector_id() + ""
//				+ revenue.getSector_name() + "" + revenue.getStatus() + ""
//				+ revenue.getNotes());
		try {
			
			InventoryTicketPassType revenue1 = (InventoryTicketPassType) session.load(
					InventoryTicketPassType.class, sector_id);
			revenue1.setDay_month((revenue.getDay_month()));
			revenue1.setPass_type(revenue.getPass_type());
			//revenue1.setNotes(revenue.getNotes());
			revenue1.setStatus(revenue.getStatus());
			//revenue1.setGeo_fence(revenue.getGeo_fence());
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

	
	public boolean checkSectorName(String sector_name) {
		boolean flag = false;
		try{
			
		
		
		String qry = " select sector_name from revenue_sector where  deleted_status=0 and sector_name='"
				+ sector_name + "' ";
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
}
