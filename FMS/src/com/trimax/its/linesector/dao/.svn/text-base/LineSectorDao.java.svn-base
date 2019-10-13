package com.trimax.its.linesector.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.linesector.Model.LineSector;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.revenuesector.model.RevenueSector;
import com.trimax.its.util.HibernateUtil;

public class LineSectorDao {
	
	
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
			 criteria = session.createCriteria(LineSector.class);
		}else{
			 criteria = session.createCriteria(LineSector.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}
//		Criteria criteria = session.createCriteria(RevenueSector.class);
//		criteria.add(Restrictions.eq("deleted_status", 0));
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("sector_name", "%"
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

		List<LineSector> list = criteria.list();
		System.out.println("List size in total"+list.size());
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return list.size();
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getData1(int total, HttpServletRequest request,
			String col, String dir,String viewdeletedrecord) {

		System.out.println("Hii in LineSector Doa getData1()");
		JSONObject result = new JSONObject();
		Session session =null;
		
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from LineSector ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			
			
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(LineSector.class);
			}else{
				 criteria = session.createCriteria(LineSector.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			     System.out.println("Enter into else"+criteria);
			}
//			Criteria criteria = session.createCriteria(RevenueSector.class);
//			criteria.add(Restrictions.eq("deleted_status", 0));
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("sector_name","%"
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
			List<LineSector> list = criteria.list();
			JSONArray array = new JSONArray();
		//	System.out.println("List size----->" + list.size() + "\t"
			//		+ request.getParameter("iDisplayStart"));
			System.out.println("Size list==="+list.size());
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getSector_id()
						+ "' value='"
						+ list.get(i).getSector_id() + "'/>");
				ja.add(list.get(i).getSector_id());
				ja.add(list.get(i).getSector_name());
				ja.add(list.get(i).getGeo_fence()!=null? "Available":"Not Available");
//				if(list.get(i).getGeo_fence()==null)
//				{
//				ja.add("Geo Fence Not Available");
//				}else{
//					ja.add("Available");
					ja.add(list.get(i).getStatus());
					ja.add(list.get(i).getNotes());
				//}
					if(viewdeletedrecord.equalsIgnoreCase("Y"))
					{
						int deletedstatus=list.get(i).getDeleted_status();
											
						if(deletedstatus==1)
						{
							ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getSector_id()+"'/>Deleted Record");	
							//ja.add(" ");
						}else{
							//ja.add(" ");
							ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getSector_id()+"'/>Record in Database");	
						}
						
						//ja.add(rs.get("vendor_name").toString());	
					}else{
						
					}
					array.add(ja);
					//System.out.println("Array----->" + array);
				}
					

				// ja.add(list.get(i).getNotes());
				// ja.add(list.get(i).getStatus());

				//array.add(ja);
				//System.out.println("Array----->" + array);
			//}

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
          System.out.println("Result====="+result);
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
	
	public boolean checkLineSectorName(String sector_name) {
		boolean flag = false;
		try{
			
		System.out.println("sector_name=="+sector_name);
		
		String qry = " select sector_name from line_sector where  deleted_status=0 and sector_name='"
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
		System.out.println("Flag==="+flag);
		return flag;
	}
	
	public int saveOrgChart(LineSector linesec) {
		int i = 0;
		Session session=null;
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
          session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			linesec.setGeo_fence("");
			session.beginTransaction();
			i = (Integer) session.save(linesec);
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
		String sql = "update line_sector set geo_fence=GeomFromText(' "+lineString + " ') where sector_id=" + sector_id;
//		System.out.println("Query Line" + sql);
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
	
	public String deletelinesector(LineSector line, int device_type_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
		// System.out.println("=====Route id" + role.getRole_id());
		try {
			//
			LineSector linesec1 = (LineSector) session.get(
					LineSector.class, device_type_id);

			linesec1.setStatus("INACTIVE");
			linesec1.setDeleted_status(1);
			linesec1.setUpdated_by(line.getUpdated_by());
			linesec1.setUpdated_date(new java.util.Date());

			session.update(linesec1);
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
	
	public LineSector getEditedLineSector(int id) {
		// BusStops busstops = new BusStops();
		Session session =null;
		LineSector line=null;
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 line = (LineSector) session.get(
				LineSector.class, new Integer(id));
		
		}catch(Exception e){
			
		}
		finally{
			if(session.isOpen() && session!=null){
				session.close();
			}
		}
		
		return line;

	}
	
	public String getTextData(int sector_id) {
		String sql = "select asText(geo_fence) as geo_fence from line_sector where sector_id="+sector_id;
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

	public boolean checkLineSectorTypeForUpdate(String devicename, int id) {
		boolean flag = false;
		String qry = " select sector_name from line_sector where  deleted_status=0 and sector_name='"
				+ devicename + "' and sector_id= " + id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

//		System.out.println("QUery Size===>" + query.list().size());
		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	public String updateLineSector(LineSector line, int sector_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
//		System.out.println("=====Information" + revenue.getSector_id() + ""
//				+ revenue.getSector_name() + "" + revenue.getStatus() + ""
//				+ revenue.getNotes());
		try {
			
			LineSector line1 = (LineSector) session.load(
					LineSector.class, sector_id);
			line1.setSector_name(line.getSector_name());
			line1.setNotes(line.getNotes());
			line1.setStatus(line.getStatus());
			//revenue1.setGeo_fence(revenue.getGeo_fence());
			line1.setUpdated_by(line.getUpdated_by());
			line1.setUpdated_date(new java.util.Date());
			// Code to Update FareChartMaster...
			session.update(line1);
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

	
	
	
	
	
	

}
