package com.trimax.its.training.dao;

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

import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.training.model.TrainingType;
import com.trimax.its.util.HibernateUtil;

public class TrainingTypeDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			// Criteria criteria = session.createCriteria(TrainingType.class);
				//criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(TrainingType.class);
				}else{
					 criteria = session.createCriteria(TrainingType.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				}
				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					String search_term = request.getParameter("sSearch").trim();
					conditionGroup.add(Restrictions.like("status",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("training_type_name",
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
				
						List<TrainingType> list = criteria.list();
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
			String sql = " from TrainingType ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(TrainingType.class);
			//criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(TrainingType.class);
				}else{
					 criteria = session.createCriteria(TrainingType.class);
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
					conditionGroup.add(Restrictions.like("training_type_name",
							"" + search_term+ "%"));
				criteria.add(conditionGroup);
				
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<TrainingType> list = criteria.list();
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getTraining_type_id()
						+ "' value='"
						+ list.get(i).getTraining_type_id() + "'/>");
				ja.add(list.get(i).getTraining_type_id());	
				//ja.add(list.get(i).getTraining_type_name());	
				ja.add(list.get(i).getTraining_type_name()!=null ? list.get(i).getTraining_type_name().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getNotes());

				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getTraining_type_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getTraining_type_id()+"'/>Record in Database");	
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
		} catch (Exception ex) {
			ex.printStackTrace();
			//System.out.println(ex);
		}
		return result;
	}
	public boolean getDuplicate(String tid)
	{
		boolean flag=false;
		String qry = " select training_type_id from training_type where deleted_status=0 and training_type_name='"+tid+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public int saveTrainingType(TrainingType trainingtype)
	{
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			id=(Integer)session.save(trainingtype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}
	
	public String deleteTrainingTypeDetails(TrainingType training, int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	try{
		//
		TrainingType tra = (TrainingType) session.get(TrainingType.class,id);

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
	
	public TrainingType getEditedTrainingType(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		TrainingType trainingtype = (TrainingType) session.get(TrainingType.class, new Integer(
				id));

		return trainingtype;
	}
	
	public boolean getUpdateDuplicate(int tid,String name)
	{
		boolean flag=false;
		String qry = " select training_type_id from training_type where deleted_status=0 and training_type_id="+tid+" and training_type_name='"+name+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0)
		{	
				flag=true;
		}
		else
		{
			String qry2 = " select training_type_id from training_type where deleted_status=0 and training_type_name='"+name+"'";
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
	
	public int updateTrainingTypeDetails(int id,TrainingType user){
		boolean flag=false;
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HttpServletRequest request=ServletActionContext.getRequest();
		int i=0;
		try{
		
			TrainingType user1=(TrainingType)session.load(TrainingType.class,new Integer(id));
		
		user1.setTraining_type_name(user.getTraining_type_name());
		
		user1.setNotes(user.getNotes());
		user1.setStatus(user.getStatus());
		session.update(user1);
		session.getTransaction().commit();		
		i=1;
		}
		catch(Exception e){
			session.getTransaction().rollback();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if (session != null) {
				session.close();
			}
		}

		return i;
}
	

}
