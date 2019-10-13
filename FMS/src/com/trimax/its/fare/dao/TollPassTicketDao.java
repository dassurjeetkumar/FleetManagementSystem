package com.trimax.its.fare.dao;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.fare.model.TollPassTicketModel;

import com.trimax.its.route.model.Route;
import com.trimax.its.util.HibernateUtil;

public class TollPassTicketDao {
	public JSONObject getData(int total,HttpServletRequest request, String cols,
			String dir) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			int totalAfterFilter = total;
			String searchSQL = "";
			String sql = " SELECT id,Route_no,concat(route_number,'-',route_direction) as route_number, toll_name, Amount, Notes " +
					"FROM Toll_Pass_Ticket tp inner join route r on r.route_id=tp.Route_no " +
					"WHERE tp.deleted_status = '0' and if(effective_till IS NULL or effective_till = '0000-00-00 00:00:00', CURDATE(), " +
					"effective_till) >= CURDATE() and r.status='ACTIVE' and r.deleted_status='0'";
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (route_number like '%"+search_term+"%')";
			}
			
			if (!cols.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+cols+" asc";
				}else{
					sql += " order by "+cols+" desc";
				}
			}else{
				sql += " order by id desc";
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("Route_no", Hibernate.STRING)
				.addScalar("route_number", Hibernate.STRING)
				.addScalar("toll_name", Hibernate.STRING)
				.addScalar("Amount", Hibernate.STRING)
				.addScalar("Notes", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
		
			JSONArray array = new JSONArray();
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+  rs.get("id")
						+ "' value='"
						+ rs.get("id") + "'/>");
				ja.add(rs.get("id"));
				ja.add(rs.get("Route_no"));
				ja.add(rs.get("route_number"));
				ja.add(rs.get("toll_name"));
				ja.add(rs.get("Amount"));
				ja.add(rs.get("Notes")!=null ? ((String) rs.get("Notes")).replaceAll(" ","&nbsp;") : "");
				array.add(ja);
			}

			totalAfterFilter = total;//getTotalRecords(total,request,cols,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			
//			System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}
		return result;
	}

	public int getTotalRecords(int total, HttpServletRequest request,
			String cols, String dir) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		int cnt=0;
		try{
			String sql = " SELECT id,Route_no,concat(route_number,'-',route_direction) as route_number, toll_name, Amount, Notes " +
					"FROM Toll_Pass_Ticket tp inner join route r on r.route_id=tp.Route_no " +
					"WHERE tp.deleted_status = '0' and if(effective_till IS NULL or effective_till = '0000-00-00 00:00:00', CURDATE(), " +
					"effective_till) >= CURDATE() and r.status='ACTIVE' and r.deleted_status='0'";
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (route_number like '%"+search_term+"%')";
			}
			
			if (!cols.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+cols+" asc";
				}else{
					sql += " order by "+cols+" desc";
				}
			}else{
				sql += " order by id desc";
			}
			
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("Route_no", Hibernate.STRING)
					.addScalar("route_number", Hibernate.STRING)
					.addScalar("toll_name", Hibernate.STRING)
					.addScalar("Amount", Hibernate.STRING)
					.addScalar("Notes", Hibernate.STRING);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();			
			cnt = aliasToValueMapList.size();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return cnt;
	}

	@SuppressWarnings("finally")
	public boolean deletetollpassMaster(int parseInt) {
		Session session = null;
		boolean isSuccess = false;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			
			String Innersql = " UPDATE Toll_Pass_Ticket set deleted_status='1' WHERE id='"+parseInt+"'";
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

	public Map<Integer, String> getroutedate() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("select r.route_id,concat(route_number,'-',route_direction) as route_number from route r " +
							"inner join route_point rp on rp.route_id=r.route_id " +
							"inner join bus_stop bs on bs.bus_stop_id=rp.bus_stop_id " +
							"where if(effective_till IS NULL or effective_till = '0000-00-00 00:00:00', CURDATE(), " +
							"effective_till) >= CURDATE() and r.status='ACTIVE' and r.deleted_status='0' and bs.toll_zone='Y' " )
							.addScalar("route_id", Hibernate.INTEGER).addScalar("route_number", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(Route.class));
			List<Route> list = query.list();
			resultMap.put(0, "Select");
			System.out.println("Size of route List : "+list.size());
			for (Route type : list) {
				resultMap.put(type.getRoute_id(), type.getRoute_number());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}

	public int saveTollPassTicket(TollPassTicketModel ticPassTicketModel) {
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			int userId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			
			ticPassTicketModel.setDeleted_status(0);
			ticPassTicketModel.setInserted_by(userId);
			ticPassTicketModel.setInserted_date(new Date());
			session.beginTransaction();
			id=(Integer)session.save(ticPassTicketModel);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public TollPassTicketModel getEditedTollPass(int parseInt) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		TollPassTicketModel defect = (TollPassTicketModel) session.get(TollPassTicketModel.class, new Integer(parseInt));
       return defect;
	}

	
	public void updateTollpassMaster(TollPassTicketModel ticPassTicketModel) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			TollPassTicketModel htollpassmaster = (TollPassTicketModel) session.get(TollPassTicketModel.class, ticPassTicketModel.getId());

			htollpassmaster.setRouteno(ticPassTicketModel.getRouteno());
			htollpassmaster.setToll_name(ticPassTicketModel.getToll_name());
			htollpassmaster.setAmount(ticPassTicketModel.getAmount());
			htollpassmaster.setNotes(ticPassTicketModel.getNotes());
			
			
			session.update(htollpassmaster);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		
		
	}

	public boolean checkduplicate(TollPassTicketModel tollPassTicketModel) {
		Session session = null;
		boolean isSuccess = false;
		Common common = new Common();
		try{
			session = HibernateUtil.getSession("");
	        session.beginTransaction();
			System.out.println(" route toll name..............."+tollPassTicketModel.getToll_name());
			System.out.println(" route id..............."+tollPassTicketModel.getRouteno().getRoute_id());
			String sql11="SELECT COUNT(`Route_no`) as count FROM `Toll_Pass_Ticket` WHERE `Route_no` = '"+tollPassTicketModel.getRouteno().getRoute_id()+"' " +
					"AND `toll_name` = '"+tollPassTicketModel.getToll_name()+"' and deleted_status=0";
        	int count = common.getDBResultInt(session, sql11, "count");
        	if(count==0){
        		isSuccess = true;
        	}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}
	public boolean checkduplicate1(TollPassTicketModel tollPassTicketModel) {
		Session session = null;
		boolean isSuccess = false;
		Common common = new Common();
		try{
			session = HibernateUtil.getSession("");
	        session.beginTransaction();
			String sql11="SELECT COUNT(`Route_no`) as count FROM `Toll_Pass_Ticket` where `Route_no` = '"+tollPassTicketModel.getRouteno().getRoute_id()+"' " +
					"AND `toll_name` = '"+tollPassTicketModel.getToll_name()+"' and id not in ("+tollPassTicketModel.getId()+") and deleted_status=0";
        	int count = common.getDBResultInt(session, sql11, "count");
        	if(count!=0){
        		isSuccess = true;
        	}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}

}



