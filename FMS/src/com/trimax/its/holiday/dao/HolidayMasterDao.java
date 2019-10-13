package com.trimax.its.holiday.dao;

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
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.holiday.model.HolidayMasterModel;
import com.trimax.its.holiday.model.HolidayType;

import com.trimax.its.transport.model.OrgType;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.util.HibernateUtil;

public class HolidayMasterDao {

	public JSONObject getData(int total,HttpServletRequest request, String cols,
			String dir) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			int totalAfterFilter = total;
			String searchSQL = "";
			String sql = " from holiday_master ";
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria ;
			
				 criteria = session.createCriteria(HolidayMasterModel.class);
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
					conditionGroup.add(Restrictions.like("holiday_type",
							"" + search_term+ "%"));
				criteria.add(conditionGroup);
				
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<HolidayMasterModel> list = criteria.list();
			JSONArray array = new JSONArray();
//			System.out.println("List size----->" + list.size() + "\t"
//					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getHoliday_master_id()
						+ "' value='"
						+ list.get(i).getHoliday_master_id() + "'/>");
				ja.add(list.get(i).getHoliday_master_id());
				ja.add(list.get(i).getHoliday_date());
				ja.add(list.get(i).getHoliday_day());
				ja.add(list.get(i).getHoliday_name()!=null ? list.get(i).getHoliday_name().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getHolydaytype().getHoliday_type());
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
				
					 criteria = session.createCriteria(HolidayMasterModel.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				
				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					String search_term = request.getParameter("sSearch").trim();
					
						conditionGroup.add(Restrictions.like("holiday_type",
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
				
						List<HolidayMasterModel> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return cnt;
	}

	@SuppressWarnings("finally")
	public boolean deleteholidayMaster(int parseInt) {
		Session session = null;
		boolean isSuccess = false;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			
			String Innersql = " UPDATE holiday_master set deleted_status='1' WHERE holiday_master_id='"+parseInt+"'";
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

	public Map<Integer, String> getholidaytypedate() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `holiday_id`, `holiday_type` FROM `holiday_type` where deleted_status='0' ").addScalar("holiday_id", Hibernate.INTEGER).addScalar("holiday_type", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(HolidayType.class));
			List<HolidayType> list = query.list();
			resultMap.put(0, "Select");
			System.out.println("Size of holiday List : "+list.size());
			for (HolidayType type : list) {
				resultMap.put(type.getHoliday_id(), type.getHoliday_type());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}

	public int saveHolidayMaster(HolidayMasterModel holidayMasterModel) {
		int id=0;
		HttpServletRequest request=ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			 int userid = (Integer) request.getSession().getAttribute("userid");
			holidayMasterModel.setStatus("ACTIVE");
			holidayMasterModel.setDeleted_status(0);
			holidayMasterModel.setCreate_date(new Date());
			holidayMasterModel.setCreated_by(userid);
			session.beginTransaction();
			id=(Integer)session.save(holidayMasterModel);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public HolidayMasterModel getEditedHolidayType(int parseInt) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HolidayMasterModel defect = (HolidayMasterModel) session.get(HolidayMasterModel.class, new Integer(parseInt));
       return defect;
	}

	
	public void updateHolidayMaster(HolidayMasterModel holidayMasterModel) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			HolidayMasterModel holidaymaster = (HolidayMasterModel) session.get(HolidayMasterModel.class, holidayMasterModel.getHoliday_master_id());

			holidaymaster.setHoliday_date(holidayMasterModel.getHoliday_date());
			holidaymaster.setHoliday_day(holidayMasterModel.getHoliday_day());
			holidaymaster.setHoliday_name(holidayMasterModel.getHoliday_name());
			holidaymaster.setHolydaytype(holidayMasterModel.getHolydaytype());
			
			
			session.update(holidaymaster);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		
		
	}


}
