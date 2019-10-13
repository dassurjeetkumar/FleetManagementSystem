package com.trimax.its.device.dao;

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
import com.trimax.its.device.model.MaintenanceType;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;

public class MaintenanceTypeDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 //Criteria criteria = session.createCriteria(MaintenanceType.class);
				//criteria.add(Restrictions.eq("deleted_status", 0));
			     Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(MaintenanceType.class);
				}else{
					 criteria = session.createCriteria(MaintenanceType.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				}
				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					String search_term = request.getParameter("sSearch").trim();
					
					conditionGroup.add(Restrictions.like("maintenance_type",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("status",
							"" + search_term+ "%"));

					criteria.add(conditionGroup);
					
				}
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				
						List<MaintenanceType> list = criteria.list();
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

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from MaintenanceType ";
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(MaintenanceType.class);
			//criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(MaintenanceType.class);
				}else{
					 criteria = session.createCriteria(MaintenanceType.class);
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
					
					conditionGroup.add(Restrictions.like("maintenance_type",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("status",
							"" + search_term+ "%"));
				criteria.add(conditionGroup);
				
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<MaintenanceType> list = criteria.list();
			JSONArray array = new JSONArray();
//			System.out.println("List size----->" + list.size() + "\t"
//					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getMaintenance_type_id()
						+ "' value='"
						+ list.get(i).getMaintenance_type_id() + "'/>");
				ja.add(list.get(i).getMaintenance_type_id());
				//ja.add(list.get(i).getMaintenance_type());
				ja.add(list.get(i).getMaintenance_type()!=null ? list.get(i).getMaintenance_type().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getNotes());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getMaintenance_type_id()+"'/>Deleted Record");	

						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getMaintenance_type_id()+"'/>Record in Database");	

						//ja.add("Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				//System.out.println("Array----->" + array);
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
	
	public boolean getDuplicate(String type)
	{
		boolean flag=false;
		String qry = " select maintenance_type from maintenance_type where deleted_status=0 and maintenance_type='"+type+"' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		//System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	
	public int saveMaintenanceType(MaintenanceType maintenancetype)
	{
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			id=(Integer)session.save(maintenancetype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}
	
	
	public MaintenanceType getEditedMaintenanceType(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		MaintenanceType maintenancetype = (MaintenanceType) session.get(MaintenanceType.class, new Integer(
				id));

		return maintenancetype;
	}
	
	public int updateMaintenanceType(int id,MaintenanceType user){
		boolean flag=false;
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		HttpServletRequest request=ServletActionContext.getRequest();
		try{
		
			MaintenanceType user1=(MaintenanceType)session.load(MaintenanceType.class,new Integer(id));
		
		//System.out.println("test----"+user.getHoliday_type());
		user1.setMaintenance_type(user.getMaintenance_type());
		user1.setNotes(user.getNotes());
		user1.setStatus(user.getStatus());
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
	public boolean getUpdateDuplicate(String accName,int id)
	{
		boolean flag=false;
		String qry = " select maintenance_type from maintenance_type where deleted_status=0 and maintenance_type='"+accName+"' and maintenance_type_id="+id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0)
		{	
				flag=true;
		}
		else
		{
			String qry2 = " select maintenance_type from maintenance_type where deleted_status=0 and maintenance_type='"+accName+"'";
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
	
	public String deleteMaintenanceType(MaintenanceType maintenance, int maintenance_type_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		MaintenanceType maintenanceType = (MaintenanceType) session.get(MaintenanceType.class,maintenance_type_id);
		
		//devicetype.setStatus("INACTIVE");
		maintenanceType.setDeleted_status(1);
		maintenanceType.setUpdated_by(maintenance.getUpdated_by());
		maintenanceType.setUpdated_date(new java.util.Date());
		
		session.update(maintenanceType);
		session.getTransaction().commit();
	}catch(Exception ex)
	{
		session.getTransaction().rollback();
		ex.printStackTrace();
	}finally{
		session.close();
	}
		
		return null;
	}

}
