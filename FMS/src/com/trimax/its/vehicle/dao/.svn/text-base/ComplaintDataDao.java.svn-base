package com.trimax.its.vehicle.dao;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;

import com.trimax.its.vehicle.model.Complaint;
import com.trimax.its.model.User;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.componenttype.model.ComponentType;
public class ComplaintDataDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*Query query = session.createSQLQuery(
				"select count(*) as count from Complaint where deleted_status=0 ").addScalar(
				"count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);*/
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(Complaint.class);
		}else{
			 criteria = session.createCriteria(Complaint.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}
		
		
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("complaint_media", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("compliant_type", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
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
		
		List<Complaint> list = criteria.list();
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col,String dir,String viewdeletedrecord) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from Complaint  ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(Complaint.class);
			}else{
				 criteria = session.createCriteria(Complaint.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			 
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("complaint_media", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("compliant_type", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status",  search_term,MatchMode.START));
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
			List<Complaint> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getComplaint_id()
						+ "' value='"
						+ list.get(i).getComplaint_id() + "'/>");
				ja.add(list.get(i).getComplaint_id());
				ja.add(list.get(i).getCompliant_type());
				String mydate=list.get(i).getIncident_date().toString();
				//complaint.setComplaint_date(common.getDateToPicker(complaint.getComplaint_date()));
				String arr[]=mydate.split(" ");
				ja.add(common.getDateToPicker(list.get(i).getIncident_date()));
				String mycomplaintdate=list.get(i).getComplaint_date().toString();
				String arr1[]=mycomplaintdate.split(" ");
				ja.add(common.getDateToPicker(list.get(i).getComplaint_date()));
				ja.add(list.get(i).getIdentification_data());
				ja.add(list.get(i).getComplaint_description());
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getComplaint_media());
				ja.add(list.get(i).getTaken_by());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getComplaint_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getComplaint_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				//System.out.println("Array----->" + array);
			}
			
			totalAfterFilter = getTotalRecords(total,request,col,dir,viewdeletedrecord);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
			
			System.out.println("REsult ------>"
					+ request.getParameter("iDisplayStart"));
		} catch (Exception e) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			
			//ex.printStackTrace();
		}
		return result;
	}
	public int insertDeviceType(Complaint complaint) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		//System.out.println("000000000000000000rajesh");
		try{
		 i = (Integer) session.save(complaint);
		session.getTransaction().commit();
		}catch(Exception e)
		{
			
		}finally{
		session.close();
		}
		return i;

	}
	public Complaint getEditedDeviceType(int id) {
		// BusStops busstops = new BusStops();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Complaint complaint = (Complaint) session.get(Complaint.class, new Integer(id));
		complaint.setComplaint_date(common.getDateToPicker(complaint.getComplaint_date()));
		complaint.setIncident_date(common.getDateToPicker(complaint.getIncident_date()));
			//	+ "\t" + fareChart.getRoute_id());
		return complaint;

	}
	public void updateComplaint()
	{
		
		//return "null";
	}

	public String deleteDeviceType(Complaint complaint, int Complaint_id) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//System.out.println("deleteComplaintType");
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		Complaint complaints = (Complaint) session.get(Complaint.class,Complaint_id);
		
		//complaints.setStatus("INACTIVE");
		complaints.setDeleted_status(1);
		complaints.setUpdated_by(complaint.getUpdated_by());
		complaints.setUpdated_date(new java.util.Date());
		
		session.update(complaints);
		session.getTransaction().commit();
	}catch(Exception e)
	{
		Logger logger = TrimaxLogger.getTrimaxLogger();
		logger.error(this.getClass() + "$Exception in LoginAction action",
				e);
		SaveRequest.save(request);
		ErrorLog log = new ErrorLog();
		log.setMsg(e.getMessage());
		ErrorLogDAO.saveException(log);
		
		
		session.getTransaction().rollback();
		
		//ex.printStackTrace();
	}finally{
		session.close();
	}
		
		return null;
	}
	
	
	//Delete Complaint start
	
	
	public String deleteComplaint(Complaint complaint, int Complaint_id) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//System.out.println("deleteComplaintType");
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		Complaint complaints = (Complaint) session.get(Complaint.class,Complaint_id);
		
		complaints.setStatus("INACTIVE");
		complaints.setDeleted_status(1);
		complaints.setUpdated_by(complaint.getUpdated_by());
		complaints.setUpdated_date(new java.util.Date());
		
		session.update(complaints);
		session.getTransaction().commit();
	}catch(Exception e)
	{
		
		Logger logger = TrimaxLogger.getTrimaxLogger();
		logger.error(this.getClass() + "$Exception in LoginAction action",
				e);
		SaveRequest.save(request);
		ErrorLog log = new ErrorLog();
		log.setMsg(e.getMessage());
		ErrorLogDAO.saveException(log);
		
		session.getTransaction().rollback();

		
	
		
	}finally{
		session.close();
	}
		
		return null;
	}
	
	//end
	
	
	
	

	
	public String updateDeviceType(Complaint complaint, int complaintid) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
	//System.out.println("Inside edited data$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	try{
	
		Complaint complaints = (Complaint)session.get(Complaint.class,complaintid);
		complaints.setCompliant_type(complaint.getCompliant_type());
		//complaints.setIncident_date(common.getDateFromPicker(complaint.getIncident_date()));
		//complaints.setComplaint_date(common.getDateFromPicker(complaint.getComplaint_date()));
		complaints.setIdentification_data(complaint.getIdentification_data());
		complaints.setComplaint_description(complaint.getComplaint_description());
		complaints.setTaken_by(complaint.getTaken_by());
		complaints.setStatus(complaint.getStatus());
		complaints.setComplaint_media(complaint.getComplaint_media());
		
		session.update(complaints);
		session.getTransaction().commit();
	}catch(Exception e)
	{
		
		Logger logger = TrimaxLogger.getTrimaxLogger();
		logger.error(this.getClass() + "$Exception in LoginAction action",
				e);
		SaveRequest.save(request);
		ErrorLog log = new ErrorLog();
		log.setMsg(e.getMessage());
		ErrorLogDAO.saveException(log);
		
		session.getTransaction().rollback();

		
		
		
	}finally{
		session.close();
	}
		
		return null;
	}
//update complaint
	public String updateComplaint(Complaint complaint, int complaintid) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
	//System.out.println("Inside edited data$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	try{
		System.out.println(complaint.getIncident_date()+"tttttttttttttttt"+complaintid);
		Complaint complaints = (Complaint)session.get(Complaint.class,complaintid);
		complaints.setCompliant_type(complaint.getCompliant_type());
		complaints.setIncident_date(common.getDateFromPicker(complaint.getIncident_date()));
		
		//complaints.setIncident_date(common.getDateToPicker(complaint.getIncident_date()));

		complaints.setComplaint_date(common.getDateFromPicker(complaint.getComplaint_date()));
		complaints.setIdentification_data(complaint.getIdentification_data());
		complaints.setComplaint_description(complaint.getComplaint_description());
		complaints.setTaken_by(complaint.getTaken_by());
		complaints.setStatus(complaint.getStatus());
		complaints.setComplaint_media(complaint.getComplaint_media());
		complaints.setUpdated_by(complaint.getUpdated_by());
		session.update(complaints);
		session.getTransaction().commit();
	}catch(Exception e)
	{
		Logger logger = TrimaxLogger.getTrimaxLogger();
		logger.error(this.getClass() + "$Exception in LoginAction action",
				e);
		SaveRequest.save(request);
		ErrorLog log = new ErrorLog();
		log.setMsg(e.getMessage());
		ErrorLogDAO.saveException(log);
		
		session.getTransaction().rollback();

		
		
	}finally{
		session.close();
	}
		
		return null;
	}

	//end
	//get list of user for complaint
	public Map<Integer, String> getUserName() {
		// TODO Auto-generated method stub
		// TODO Auto-generated methoSession session=null;d stub
		
		Session session= null;
		Transaction transaction  = null;
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		
		try{
//		 session = HibernateUtil.getSession("hibernate.cfg.xml");
	String sql="select ms.userloginname as usname,ms.user_id as msuserid from menu_user_master ms order by ms.userloginname";
//		Query query = session.createQuery(query1);
//		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String, Object>> aliasToValueMapList = query.list();
//		
			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session.beginTransaction();
			Query query = session.createSQLQuery(sql).addScalar("usname")
					.addScalar("msuserid");
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
		
		
		 for (int i = 0; i < aliasToValueMapList.size(); i++) {
			 Map<String, Object> list = aliasToValueMapList.get(i);
			 resultMap.put(Integer.parseInt(list.get("msuserid").toString()),list.get("usname").toString());
			 
			 
		 }
		
		
//		List list = query.uniqueResult().toString();
//		for (User user : list) {
//			resultMap.put(user.getUserId(),user.getFirstname());
//		}
		}catch(Exception e)
		{
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			
			session.getTransaction().rollback();

			
						
		}finally{
			session.close();
		}
		return resultMap;
		

	}
	public boolean isGreaterThanCurrentDate(String date)
	{
		boolean isSuccess = false;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$"+date);
			Date date1 = sdf.parse(date);
			if(date1.compareTo(new Date())>0){
				isSuccess =true;
			}
			else{
				isSuccess = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isSuccess;
				
	}
	public boolean isGreaterThanCurrentDatet(String date)
	{
		boolean isSuccess = false;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$"+date);
			Date date1 = sdf.parse(date);
			if(date1.compareTo(new Date())>0){
				isSuccess =true;
			}
			else{
				isSuccess = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isSuccess;
				
	}
	
	
}


