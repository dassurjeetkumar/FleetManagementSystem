package com.trimax.its.holiday.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.holiday.model.HolidayType;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;

public class HolidayTypeDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			// Criteria criteria = session.createCriteria(HolidayType.class);
				//criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(HolidayType.class);
				}else{
					 criteria = session.createCriteria(HolidayType.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				}
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
				
						List<HolidayType> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return cnt;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			int totalAfterFilter = total;

			String searchSQL = "";
			String sql = " from HolidayType ";
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(HolidayType.class);
			//criteria.add(Restrictions.eq("deleted_status", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(HolidayType.class);
			}else{
				 criteria = session.createCriteria(HolidayType.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
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
			
			List<HolidayType> list = criteria.list();
			JSONArray array = new JSONArray();
//			System.out.println("List size----->" + list.size() + "\t"
//					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getHoliday_id()
						+ "' value='"
						+ list.get(i).getHoliday_id() + "'/>");
				ja.add(list.get(i).getHoliday_id());
				ja.add(list.get(i).getHoliday_type()!=null ? list.get(i).getHoliday_type().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getNotes());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getHoliday_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getHoliday_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}

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

	public boolean getDuplicate(String accName)
	{
		boolean flag=false;
		String qry = " select holiday_type from holiday_type where deleted_status=0 and holiday_type='"+accName+"' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	
	public int saveHolidayType(HolidayType holidaytype)
	{
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			id=(Integer)session.save(holidaytype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}
	
	public HolidayType getEditedHolidayType(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HolidayType defect = (HolidayType) session.get(HolidayType.class, new Integer(
				id));

		return defect;
	}
	
	public int updateHolidayType(int id,HolidayType user){
		boolean flag=false;
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HttpServletRequest request=ServletActionContext.getRequest();
		int i=0;
		try{
		
		HolidayType user1=(HolidayType)session.load(HolidayType.class,new Integer(id));
		user1.setHoliday_type(user.getHoliday_type());
		user1.setNotes(user.getNotes());
		user1.setUpdated_by(user1.getUpdated_by());
		user1.setUpdated_date(new java.util.Date());
		session.update(user1);
		session.getTransaction().commit();		
		i=1;
		}
		catch(Exception e){
			session.getTransaction().rollback();
			//ex.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}
		finally{
			if (session != null) {
				session.close();
			}
		}

		return i;
	}
	
	public String deleteholidayType(HolidayType holidaytype, int holiday_type_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	try{
		HolidayType holiday = (HolidayType) session.get(HolidayType.class,holiday_type_id);

		holiday.setDeleted_status(1);
		holiday.setUpdated_by(holidaytype.getUpdated_by());
		holiday.setUpdated_date(new java.util.Date());
		
		session.update(holiday);
		session.getTransaction().commit();
	}catch(Exception ex)
	{
		session.getTransaction().rollback();
		//ex.printStackTrace();
		
	}finally{
		session.close();
	}
		
		return null;
	}
	public boolean getUpdateDuplicate(String accName,int id)
	{
		boolean flag=false;
		String qry = " select holiday_type from holiday_type where holiday_type='"+accName+"' and holiday_id="+id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0)
		{	
				flag=true;
		}
		else
		{
			String qry2 = " select holiday_type from holiday_type where holiday_type='"+accName+"'";
			Query query2 = HibernateUtil.getSession("").createSQLQuery(qry2);
			if(query2.list().size()>0)
			{
				flag=false;
			}
			else
			{
				flag=true;
			}
		}
		return flag;
	}
	

}
