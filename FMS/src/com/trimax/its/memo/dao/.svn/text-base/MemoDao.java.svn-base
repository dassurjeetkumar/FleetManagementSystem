package com.trimax.its.memo.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.trimax.its.usermanagement.model.Employee;
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

import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.memo.model.Memo;
import com.trimax.its.memo.model.MemoType;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.transport.model.PeakHour;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.Complaint;
import com.trimax.its.vehicle.model.ServiceType;

public class MemoDao {
	
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(Memo.class);
		}else{
			 criteria = session.createCriteria(Memo.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}
		
		
//		Criteria criteria = session.createCriteria(Memo.class);
//		 criteria.add(Restrictions.eq("deleted_status", 0));
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

//			conditionGroup.add(Restrictions.like("memo_type_name",
//					"%" + search_term + "%"));
		/*conditionGroup.add(Restrictions.like("device_serial_number",
				"%" + search_term + "%"));*/
			conditionGroup.add(Restrictions.like("memu.employee_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("mem.memo_type_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
							criteria.add(conditionGroup);
		
		//criteria.add(conditionGroup);
			
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
		criteria.createCriteria("user","memu");
		criteria.createCriteria("memotype","mem");
		List<Memo> list =criteria.list();
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col,String dir,String viewdeletedrecord) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			int totalAfterFilter = total;
			// JSONArray array = new JSONArray();
			String sql = "from Memo";
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(Memo.class);
			}else{
				 criteria = session.createCriteria(Memo.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			
//			Criteria criteria = session.createCriteria(Memo.class);
//			 criteria.add(Restrictions.eq("deleted_status", 0));
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("memu.employee_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("mem.memo_type_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
								criteria.add(conditionGroup);
				
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
			
			criteria.createCriteria("memotype","mem");
			criteria.createCriteria("user","memu");
			List<Memo> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getMemo_id()
						+ "' value='"
						+ list.get(i).getMemo_id() + "'/>");
				ja.add(list.get(i).getMemo_id());
				ja.add(list.get(i).getMemotype().getMemo_type_name());
				ja.add(list.get(i).getUser().getEmployee_name());
				ja.add(common.getDateToPicker(list.get(i).getDate()));
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getNotes()!=null ? list.get(i).getNotes().replaceAll(" ","&nbsp;"):"");
				//ja.add(list.get(i).getStatus()!=null ? list.get(i).getStatus().replaceAll(" ","&nbsp;"):"");

				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getMemo_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getMemo_id()+"'/>Record in Database");	
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	public int insertDeviceType(Memo memo) {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		//System.out.println("000000000000000000rajesh");
		try{
		 i = (Integer) session.save(memo);
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
			
		}finally{
		session.close();
		}
		return i;

	}
	public Memo getEditedDeviceType(int id) {
		// BusStops busstops = new BusStops();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Memo memo =(Memo)session.get(Memo.class,new Integer(id));
		memo.setDate(common.getDateToPicker(memo.getDate()));
		
			//	+ "\t" + fareChart.getRoute_id());
		return memo;

	}
	public void updateComplaint()
	{
		
		//return "null";
	}

	public String deleteDeviceType(Memo memo, int peak_id) {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		Memo memos = (Memo) session.get(Memo.class,peak_id);
		
		
		memos.setDeleted_status(1);
		memos.setUpdated_by(memo.getUpdated_by());
		memos.setUpdated_date(new java.util.Date());
		
		session.update(memos);
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
	
	
	//Delete Complaint start
	
	
	public String deleteComplaint(Complaint complaint, int Complaint_id) {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
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
		//ex.printStackTrace();
	}finally{
		session.close();
	}
		
		return null;
	}
	
	
	
	
	
	

	
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
		//ex.printStackTrace();
	}finally{
		session.close();
	}
		
		return null;
	}
//update complaint
	public String updateComplaint(Memo memo, int memoid) {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
	try{
		Memo memos = (Memo)session.get(Memo.class,memoid);
		
		memos.setMemotype(memo.getMemotype());
		memos.setUser(memo.getUser());
		
		memos.setNotes(memo.getNotes());
		memos.setStatus(memo.getStatus());
		
		
		memos.setDate(common.getDateFromPicker(memo.getDate()));
		
		memos.setUpdated_by(memo.getUpdated_by());
		session.update(memos);
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
	public Map<Integer, String> getMemoType() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from MemoType memo where deleted_status=0 order by memo.memo_type_name");
		try{
		List<MemoType> list = query.list();
		//System.out.println("memeo type size"+list.size());
		for (MemoType memotype : list) {
			resultMap.put(memotype.getMemo_type_id(),memotype.getMemo_type_name());
		}
		}catch(Exception ex)
		{
			System.out.println(ex);
		}finally{
			session.close();
		}
		return resultMap;
		

	}
	public boolean checkPeakName(String peakname)
	{
		boolean flag=false;
		String qry = " select peak_Name from PeakHour where peak_Name='"+peakname+"' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		//System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public Map<Integer, String> getUserName() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Employee user where STATUS='ACTIVE' order by user.employee_name");
		try{
		List<Employee> list = query.list();
		//System.out.println("memeo getUserName"+list.size());
		for (Employee user : list) {
			resultMap.put(user.getEmployee_id(),user.getEmployee_name());
		}
		}catch(Exception ex)
		{
			
		}finally{
			session.close();
		}
		return resultMap;
		

	}
	

}
