package com.trimax.its.device.dao;

import java.util.HashMap;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.device.model.DataCard;
import com.trimax.its.device.model.RFIDCard;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.model.Vendor;
import com.trimax.its.util.HibernateUtil;

public class RFIDCardDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			// Criteria criteria = session.createCriteria(RFIDCard.class);
				//criteria.add(Restrictions.eq("deleted_status", 0));
			    Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(RFIDCard.class);
				}else{
					 criteria = session.createCriteria(RFIDCard.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				}
				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					String search_term = request.getParameter("sSearch").trim();
					conditionGroup.add(Restrictions.like("status",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("serial_number",
							"" + search_term+ "%"));
					
					conditionGroup.add(Restrictions.like("manufacturing_id",
							"" + search_term+ "%"));
					
					conditionGroup.add(Restrictions.like("ven.vendor_name",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("emp_token_no",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("emp_type",
							"" + search_term+ "%"));
					criteria.add(conditionGroup);
					
				}
				criteria.createCriteria("vendor", "ven");
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				
						List<RFIDCard> list = criteria.list();
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
			String sql = " from RFIDCard ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(RFIDCard.class);
			//criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(RFIDCard.class);
				}else{
					 criteria = session.createCriteria(RFIDCard.class);
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
					conditionGroup.add(Restrictions.like("serial_number",
							"" + search_term+ "%"));
					
					conditionGroup.add(Restrictions.like("ven.vendor_name",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("manufacturing_id",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("emp_token_no",
							"" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("emp_type",
							"" + search_term+ "%"));
				criteria.add(conditionGroup);
				
			}
			criteria.createCriteria("vendor", "ven");
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<RFIDCard> list = criteria.list();
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getRfid_card_id()
						+ "' value='"
						+ list.get(i).getRfid_card_id() + "'/>");
				ja.add(list.get(i).getRfid_card_id());				
				//ja.add(list.get(i).getSerial_number());
				ja.add(list.get(i).getSerial_number()!=null ? list.get(i).getSerial_number().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getStatus());
				ja.add(common.getDateToDatePicker(list.get(i).getProcured_date()));
				ja.add(list.get(i).getVendor().getVendor_name());	
				ja.add(list.get(i).getManufacturing_id());
				ja.add(list.get(i).getEmp_token_no());
				ja.add(list.get(i).getEmp_type());

				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getRfid_card_id()+"'/>Deleted Record");	

						//ja.add("Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getRfid_card_id()+"'/>Record in Database");	

						//ja.add("Record in Database");	
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
	public Map<Integer, String> getVendorList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Vendor where status='Active' and deleted_status=0");
		
		try{
		List<Vendor> list = query.list();
		
		for (Vendor vendor : list) {
			resultMap.put(vendor.getId(), vendor.getVendor_name());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
	}
	public boolean getDuplicate(String snum)
	{
		boolean flag=false;
		String qry = " select rfid_card_id from rfid_card where deleted_status=0 and serial_number='"+snum+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
//		System.out.println("-----"+qry);
//		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public int saveRFIDCard(RFIDCard rfidcard)
	{
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			id=(Integer)session.save(rfidcard);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}
	
	public RFIDCard getEditedRFIDCard(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		RFIDCard rfidcard = (RFIDCard) session.get(RFIDCard.class, new Integer(
				id));

		return rfidcard;
	}
	public boolean getUpdateDuplicate(String snum,int id)
	{
		boolean flag=false;
		String qry = " select rfid_card_id from rfid_card where deleted_status=0 and serial_number='"+snum+"' and rfid_card_id="+id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0)
		{	
				flag=true;
		}
		else
		{
			String qry2 = " select rfid_card_id from rfid_card where deleted_status=0 and serial_number='"+snum+"'";
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
	
	public int updateRFIDCard(int id,RFIDCard user){
		boolean flag=false;
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HttpServletRequest request=ServletActionContext.getRequest();
		int i=0;
		try{
		
			RFIDCard user1=(RFIDCard)session.load(RFIDCard.class,new Integer(id));
		
		user1.setSerial_number(user.getSerial_number());
		user1.setStatus(user.getStatus());
		user1.setVendor(user.getVendor());
		user1.setProcured_date(common.getDateFromDatePicker(user.getStringprocureddate()));
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
	public String deleteRFIDcard(RFIDCard rfidcard, int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	try{
		//
		RFIDCard datac = (RFIDCard) session.get(RFIDCard.class,id);

		datac.setDeleted_status(1);
		datac.setUpdated_by(rfidcard.getUpdated_by());
		datac.setUpdated_date(new java.util.Date());
		
		session.update(datac);
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
