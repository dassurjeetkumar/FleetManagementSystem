package com.trimax.its.training.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.training.model.Training;
import com.trimax.its.training.model.TrainingType;
import com.trimax.its.util.HibernateUtil;

public class TrainingDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			// Criteria criteria = session.createCriteria(Training.class);
				//criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(Training.class);
				}else{
					 criteria = session.createCriteria(Training.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				}
				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					String search_term = request.getParameter("sSearch").trim();
					conditionGroup.add(Restrictions.like("status",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("emp.employee_name",
							"" + search_term+ "%"));
				
					conditionGroup.add(Restrictions.like("tra.training_type_name",
							"" + search_term+ "%"));
					

					criteria.add(conditionGroup);
					
				}
				criteria.createCriteria("employee", "emp");
				criteria.createCriteria("trainingtype", "tra");
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				
						List<Training> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			//System.out.println(e);
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
			String sql = " from Training ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(Training.class);
			//criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(Training.class);
				}else{
					 criteria = session.createCriteria(Training.class);
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

					conditionGroup.add(Restrictions.like("status",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("emp.employee_name",
							"" + search_term+ "%"));
				
					conditionGroup.add(Restrictions.like("tra.training_type_name",
							"" + search_term+ "%"));
				criteria.add(conditionGroup);
				
			}
			criteria.createCriteria("employee", "emp");
			criteria.createCriteria("trainingtype", "tra");
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<Training> list = criteria.list();
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getTraining_id()
						+ "' value='"
						+ list.get(i).getTraining_id() + "'/>");
				ja.add(list.get(i).getTraining_id());	
				ja.add(list.get(i).getTrainingtype().getTraining_type_name());	
				ja.add(common.getDateToDatePicker(list.get(i).getTraining_date()));
				ja.add(list.get(i).getEmployee().getEmployee_id());
				ja.add(list.get(i).getEmployee().getEmployee_name());
				ja.add(list.get(i).getDuration());
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getRemarks());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getTraining_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getTraining_id()+"'/>Record in Database");	
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
		} catch (Exception e) {
			//ex.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			//System.out.println(ex);
		}
		return result;
	}
	
	public Map<Integer, String> getEmployeeList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String qry="select employee_id,employee_name from employee where STATUS='ACTIVE' order by EMPLOYEE_NAME";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		int size=query.list().size();

         if (size>0)
         {
              Iterator itr=query.list().iterator();
              while ( itr.hasNext() ) {
               Object[] tuple = (Object[]) itr.next();
                           resultMap.put(Integer.parseInt(""+tuple[0]),""+tuple[1]);
                       }
         }
		return resultMap;
	}
	public Map<Integer, String> getTrainingTypeList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//Query query = session.createQuery("from TrainingType where status='Active' and deleted_status=0");
		Query query = session.createQuery("from TrainingType where status='Active' and deleted_status=0 order by training_type_name ");

		try{
		List<TrainingType> list = query.list();
		
		for (TrainingType type : list) {
			resultMap.put(type.getTraining_type_id(), type.getTraining_type_name());
		}
		}catch(Exception ex)
		{
//			ex.printStackTrace();
			
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
	}
	public boolean getDuplicate(int tid,String date,int eid,String Status)
	{
		boolean flag=false;
		String qry = " select training_type_id from training where deleted_status=0 and training_type_id="+tid+" and status='"+Status+"' and user_id="+eid+" and  DATE_FORMAT(training_date, '%d-%m-%Y')='"+date+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public int saveTraining(Training training)
	{
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			id=(Integer)session.save(training);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}
	
	public String deleteTrainingDetails(Training training, int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	try{
		//
		Training tra = (Training) session.get(Training.class,id);

		tra.setDeleted_status(1);
		tra.setUpdated_by(training.getUpdated_by());
		tra.setUpdated_date(new java.util.Date());
		
		session.update(tra);
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
	
	public Training getEditedTraining(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Training training = (Training) session.get(Training.class, new Integer(
				id));

		return training;
	}
	
	public boolean getUpdateDuplicate(int tid,String date,int eid,int id,String Status)
	{
		boolean flag=false;
		String qry = " select training_type_id from training where deleted_status=0 and training_type_id="+tid+" and  status='"+Status+"' and user_id="+eid+" and  DATE_FORMAT(training_date, '%d-%m-%Y')='"+date+"'and training_id="+id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0)
		{	
				flag=true;
		}
		else
		{
			String qry2 = " select training_type_id from training where deleted_status=0 and training_type_id="+tid+" and status='"+Status+"' and user_id="+eid+" and  DATE_FORMAT(training_date, '%d-%m-%Y')='"+date+"'";
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
	
	public int updateTrainingDetails(int id,Training user){
		boolean flag=false;
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HttpServletRequest request=ServletActionContext.getRequest();
		int i=0;
		try{
		
			Training user1=(Training)session.load(Training.class,new Integer(id));
		user1.setEmployee(user.getEmployee());
		user1.setDuration(user.getDuration());
		user1.setRemarks(user.getRemarks());
		user1.setStatus(user.getStatus());
		user1.setTraining_date(common.getDateFromDatePicker(user.getStringtrainingdate()));
		user1.setTrainingtype(user.getTrainingtype());
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

}
