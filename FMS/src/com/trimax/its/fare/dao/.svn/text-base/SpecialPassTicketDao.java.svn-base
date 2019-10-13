package com.trimax.its.fare.dao;

import java.util.ArrayList;
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
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.fare.model.SpecialPassTicketModel;

import com.trimax.its.route.model.Route;
import com.trimax.its.util.HibernateUtil;

public class SpecialPassTicketDao {
	public JSONObject getData(int total,HttpServletRequest request, String cols,
			String dir) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			int totalAfterFilter = total;
			String searchSQL = "";
			String sql = " from Special_Pass_Ticket ";
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria ;
			
				 criteria = session.createCriteria(SpecialPassTicketModel.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			if (!request.getParameter("sSearch").equals("")) {
				Junction conditionGroup = Restrictions.disjunction();
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("Search Term-------"+search_term);					
					conditionGroup.add(Restrictions.like("ticket_type",
							"" + search_term+ "%"));
				criteria.add(conditionGroup);
				
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<SpecialPassTicketModel> list = criteria.list();
			JSONArray array = new JSONArray();
//			System.out.println("List size----->" + list.size() + "\t"
//					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()
						+ "' value='"
						+ list.get(i).getId() + "'/>");
				ja.add(list.get(i).getId());
				ja.add(list.get(i).getServicetype().getService_type_name());
				ja.add(list.get(i).getTicket_type());
				ja.add(list.get(i).getAmount());
				ja.add(list.get(i).getNotes()!=null ? list.get(i).getNotes().replaceAll(" ","&nbsp;") : "");
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
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			// Criteria criteria = session.createCriteria(HolidayType.class);
				//criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				
					 criteria = session.createCriteria(SpecialPassTicketModel.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				
				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					String search_term = request.getParameter("sSearch").trim();
					
						conditionGroup.add(Restrictions.like("ticket_type",
								"" + search_term + "%"));

					criteria.add(conditionGroup);
					
				}
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				
						List<SpecialPassTicketModel> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return cnt;
	}

	@SuppressWarnings("finally")
	public boolean deletespecialpassMaster(int parseInt) {
		Session session = null;
		boolean isSuccess = false;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			
			String Innersql = " UPDATE Special_Pass_Ticket set deleted_status='1' WHERE id='"+parseInt+"'";
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

	public Map<Integer, String> getservicedate() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `service_type_id`, `service_type_name` FROM `service_type` where deleted_status=0 and status='ACTIVE'").addScalar("service_type_id", Hibernate.INTEGER).addScalar("service_type_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			resultMap.put(0, "Select");
			System.out.println("Size of service type List : "+aliasToValueMapList.size());
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("service_type_id").toString()),rs.get("service_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}

	public int savespecialpassTicket(SpecialPassTicketModel specialPassTicketModel, ArrayList<String> servicetypelist) {
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			int userId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			int i=0;
			session.beginTransaction();
			for(i=0;i<servicetypelist.size();i++){
				String detailInsertQuery = "INSERT INTO  Special_Pass_Ticket (  service_type ,  ticket_type ,  " +
						" Amount ,  Notes, deleted_status, inserted_by, inserted_date  )" +
						" values('"+servicetypelist.get(i) +"', '"+specialPassTicketModel.getTicket_type()+"',"+specialPassTicketModel.getAmount()+"," +
						" '"+specialPassTicketModel.getNotes()+"', 0,'"+userId+"',now()) ";
						
				Query queryForInsert = session.createSQLQuery(detailInsertQuery);
				queryForInsert.executeUpdate();
				
			}
//			specialPassTicketModel.setDeleted_status(0);
//			specialPassTicketModel.setInserted_by(userId);
//			specialPassTicketModel.setInserted_date(new Date());
			
//			id=(Integer)session.save(specialPassTicketModel);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public SpecialPassTicketModel getEditedspecialpass(int parseInt) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		System.out.println("getEditedspecialpass................Dao class");
		SpecialPassTicketModel defect = (SpecialPassTicketModel) session.get(SpecialPassTicketModel.class, new Integer(parseInt));
       return defect;
	}

	
	public void updatespecialpassMaster(SpecialPassTicketModel specialPassTicketModel) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			SpecialPassTicketModel hspecpassmaster = (SpecialPassTicketModel) session.get(SpecialPassTicketModel.class, specialPassTicketModel.getId());

			hspecpassmaster.setServicetype(specialPassTicketModel.getServicetype());
			hspecpassmaster.setTicket_type(specialPassTicketModel.getTicket_type());
			hspecpassmaster.setAmount(specialPassTicketModel.getAmount());
			hspecpassmaster.setNotes(specialPassTicketModel.getNotes());
			
			
			session.update(hspecpassmaster);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		
		
	}


}



