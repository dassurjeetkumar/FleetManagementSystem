package com.trimax.its.device.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.device.model.SimVtu;
import com.trimax.its.device.model.Simcard;
import com.trimax.its.model.Vendor;
import com.trimax.its.util.HibernateUtil;

public class SimCardDao {
	Common common = new Common();
	public Map<Integer, String> getVendorIDName() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Vendor where status='Active' and deleted_status=0 and device_type_id=3 order by vendor_name ");
		
		try{
		List<Vendor> list = query.list();
		for (Vendor vendor : list) {
			resultMap.put(vendor.getId(),vendor.getVendor_name());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return resultMap;
		

	}
	
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		Query query = session.createSQLQuery(
//				"select count(*) as count from device where deleted_status=0 ")
//				.addScalar("count", Hibernate.INTEGER);
//		List<Integer> list = query.list();
//		int cnt = list.get(0);
//		System.out.println(cnt);
//		return cnt;
		int cnt=0;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(Simcard.class);
				 criteria.createCriteria("vendorDetailsSim", "vendor");
			}else
			{
			
		 criteria = session.createCriteria(Simcard.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			criteria.createCriteria("vendorDetailsSim", "vendor");
			}
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				Junction conditionGroup = Restrictions.disjunction();
				if(isInteger(search_term)){
					int srch=Integer.parseInt(search_term);
					conditionGroup.add(Restrictions.eq("simcard_id",srch));
					conditionGroup.add(Restrictions.ilike("serial_number", String.valueOf(srch),MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.ilike("phone_number", String.valueOf(srch),MatchMode.ANYWHERE));
				}
				else{
					conditionGroup.add(Restrictions.like("vendor.vendor_name","%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START ));
					conditionGroup.add(Restrictions.ilike("phone_number", search_term,MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.ilike("serial_number", search_term,MatchMode.ANYWHERE));
				}
				criteria.add(conditionGroup);
			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			List<Simcard> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			
		}
		return cnt;
	}
	

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(Simcard.class);
				 criteria.createCriteria("vendorDetailsSim", "vendor");
			}else{
			
			 criteria = session.createCriteria(Simcard.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			criteria.createCriteria("vendorDetailsSim", "vendor");
		}
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				Junction conditionGroup = Restrictions.disjunction();
				if(isInteger(search_term)){
					int srch=Integer.parseInt(search_term);
					conditionGroup.add(Restrictions.eq("simcard_id",srch));
					conditionGroup.add(Restrictions.ilike("serial_number", String.valueOf(srch),MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.ilike("phone_number", String.valueOf(srch),MatchMode.ANYWHERE));
				}
				else{
					conditionGroup.add(Restrictions.like("vendor.vendor_name","%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START ));
					conditionGroup.add(Restrictions.ilike("phone_number", search_term,MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.ilike("serial_number", search_term,MatchMode.ANYWHERE));
				}
				criteria.add(conditionGroup);
			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			List<Simcard> list = criteria.list();
			
			JSONArray array = new JSONArray();
			for (int i = 0; i <list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getSimcard_id()
						+ "' value='"
						+ list.get(i).getSimcard_id() + "'/>");
				ja.add(list.get(i).getSimcard_id());
				ja.add(list.get(i).getVendorDetailsSim().getVendor_name());
				ja.add(list.get(i).getSerial_number());
				ja.add(list.get(i).getPhone_number());
				ja.add(common.getDateToDatePicker(list.get(i).getProcured_date()));
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getService_plan());
				
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						//ja.add(" ");
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getSimcard_id() +"'/>Deleted Record");	

					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getSimcard_id() +"'/>Record in Database1");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
			}
			
			totalAfterFilter = getTotalRecords(total,request,cols,dir, viewdeletedrecord);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

		public static boolean isInteger(String s) {
		    try { 
		        Integer.parseInt(s); 
		    } catch(Exception e) { 
		        return false; 
		    }
		    // only got here if we didn't return false
		    return true;
		}
		
		public int saveNewBattery(Simcard batteryObject) throws Exception{
			{
				boolean isSuccess = false;
				Session session = null;
				Transaction transaction = null;
				int id=0;
				try{
					session = HibernateUtil.getSession("hibernate.cfg.xml");
					transaction = (Transaction) session.beginTransaction();
					batteryObject.setSerial_number(batteryObject.getSerial_number());
					batteryObject.setProcured_date(common.getDateFromDatePicker((batteryObject.getProcuredDateString())));
					batteryObject.setStatus(batteryObject.getStatus().trim());
					batteryObject.setService_plan(batteryObject.getService_plan());
					batteryObject.setNotes(batteryObject.getNotes().trim());
					batteryObject.setCreated_by(batteryObject.getCreated_by());
					batteryObject.setCreated_date(new java.util.Date());
					id=(Integer)session.save(batteryObject);
					session.getTransaction().commit();
					isSuccess = true;
					
					
				}catch(Exception e){
					e.printStackTrace();
					session.getTransaction().rollback();
					isSuccess = false;
				}finally{
					if(session != null){
						session.close();
					}
					return id;
				}
				
			}
			}		
		public Simcard getEditedBattery(int parseInt) {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Simcard battery = (Simcard) session.get(Simcard.class, new Integer(
					parseInt));
			return battery;
		}
		public String  updateBattery(Simcard device, int device_id) {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			int id=0;
			try {
				//
				Simcard deviceobj = (Simcard) session.get(Simcard.class, device_id);

				
				deviceobj.setStatus(device.getStatus());
				deviceobj.setUpdated_by(device.getUpdated_by());
				deviceobj.setUpdated_date(new java.util.Date());
				deviceobj.setSerial_number(device.getSerial_number());
				deviceobj.setPhone_number(device.getPhone_number());
				//deviceobj.setCapacity(device.getCapacity());
				//deviceobj.setModel(device.getModel());
				//deviceobj.getDevice().setDevice_type_id(device.getDevice().getDevice_type_id());
				deviceobj.setProcured_date(common.getDateFromDatePicker((device.getProcuredDateString())));
				//deviceobj.getVendorid().setId(device.getVendorid().getId());
				//deviceobj.setDevice(device.getDevice());
				deviceobj.setVendorDetailsSim(device.getVendorDetailsSim());
				deviceobj.setNotes(device.getNotes());
				session.update(deviceobj);
				session.getTransaction().commit();
			} catch (Exception ex) {
				session.getTransaction().rollback();
				ex.printStackTrace();
			} finally {
				session.close();
			}

			return null;
		}
		@SuppressWarnings("finally")
		public boolean checkSerilaNumber(Simcard simCardObject,String operationType)
		{
			boolean flag=false;
			String qry = null;
			Session session = null;
			try{
				session = HibernateUtil.getSession("");
				if(operationType==null){
					qry = " select count(*) count from simcard where deleted_status=0 and serial_number='"+simCardObject.getSerial_number()+"' ";
				}else{
					qry = " select count(*) count from simcard where serial_number='"+ simCardObject.getSerial_number().trim()+"' and simcard_id not in ('"+simCardObject.getSimcard_id()+"') and deleted_status='0'";
				}
				int count = common.getDBResultInt(session, qry, "count");
				
				if(count>0){
					flag=true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(session != null){
					session.close();
				}
				return flag;
			}
		}
		@SuppressWarnings("finally")
		public boolean checkPhoneNumber(Simcard simCardObject,String operationType)
		{
			boolean flag=false;
			String qry = null;
			Session session = null;
			try{
				session = HibernateUtil.getSession("");
				if(operationType==null){
					qry = " select count(*) count from simcard where deleted_status=0 and phone_number='"+simCardObject.getPhone_number()+"' ";
				}else{
					qry = " select count(*) count from simcard where phone_number='"+ simCardObject.getPhone_number().trim()+"' and simcard_id not in ('"+simCardObject.getSimcard_id()+"') and deleted_status='0'";
				}
				int count = common.getDBResultInt(session, qry, "count");
				
				if(count>0){
					flag=true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(session != null){
					session.close();
				}
				return flag;
			}
		}
		@SuppressWarnings("finally")
		public boolean isSimCardFree(String simacrdId){
			boolean isFree = true;
			Session session = null;
			try{
				session  = HibernateUtil.getSession("");
				String queryToCheck = "select count(*) count from sim_vtu where sim_id='"+simacrdId+"' and status ='ACTIVE' ";
				int count = common.getDBResultInt(session, queryToCheck, "count");
				if(count>0){
					isFree = false;
					String queryForDevice = "select d.device_id device from device d inner join sim_vtu sv on sv.device_id = d.device_id where sim_id ='"+simacrdId+"'  and sv.status='ACTIVE'";
					String deviceID = common.getDBResultStr(session, queryForDevice, "device");
					ServletActionContext.getRequest().getSession().setAttribute("deviceIdOfSim",deviceID);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(session != null){
					session.close();
				}
				return isFree;
			}
			
		}
		public boolean checkBattery(BigInteger serial_number)
		{
			boolean flag=false;
			String qry = " select simcard_id from simcard where deleted_status=0 and serial_number='"+serial_number+"' ";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			
			System.out.println("QUery Size===>"+query.list().size());
			if(query.list().size()>0)
			{
				flag=true;
			}
			return flag;
		}
		public String deleteSimCard(String device_id) {
			// TODO Auto-generated method stub
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			session.beginTransaction();
			try {
				//
				Simcard deviceobj = (Simcard) session.get(Simcard.class, Integer.parseInt(device_id));

				//deviceobj.setStatus("INACTIVE");
				deviceobj.setDeleted_status(1);
				deviceobj.setUpdated_by(userId);
				deviceobj.setUpdated_date(new java.util.Date());

				session.update(deviceobj);
				session.getTransaction().commit();
			} catch (Exception ex) {
				session.getTransaction().rollback();
				ex.printStackTrace();
			} finally {
				session.close();
			}

			return null;
		}
		public void releaseSimCard(String simcardId) {
			String qry = "select sim_vtu_id from sim_vtu where sim_id="+simcardId+" and status ='ACTIVE'";
			Session session=null;
			Transaction txn =null;
			String userId = ServletActionContext.getRequest().getSession().getAttribute("userid").toString();
			String date = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date());
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				Query query =session.createSQLQuery(qry);
				if(query.list().size()>0)
				{
					int sim_vtu_id=Integer.parseInt(query.uniqueResult().toString());
					//Update Sim_vtu
					String simvtuqry="update sim_vtu set status='INACTIVE',updated_by='"+userId+"',updated_date='"+date+"' where sim_vtu_id="+sim_vtu_id;
					Query queryStr=session.createSQLQuery(simvtuqry);
					queryStr.executeUpdate();
					txn= HibernateUtil.getSession("").beginTransaction();
					txn.commit();
				}
			} catch (Exception ex) {
				txn.rollback();
				ex.printStackTrace();
			} finally {
				session.close();
			}
			
		}
		
		public boolean checkSimDevice(int deviceid, int simid) {
			String qry = " select device_id from sim_vtu where status='ACTIVE' and sim_id="
					+ simid + " limit 1";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			boolean flag = false;
			try {
				int deviceId = Integer.parseInt(query.uniqueResult().toString());
				if (query.list().size() > 0 && deviceId != -1) {
					flag = true;
				}
			} catch (Exception ex) {

			}
			return flag;
		}
		@SuppressWarnings("finally")
		public String mapSimVtu(int device_id, int simid,HttpServletRequest request ) {
			Session session = null;
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				SimVtu sv = new SimVtu();
				sv.setDevice_id(device_id);
				sv.setSim_id(simid);
				sv.setStatus("ACTIVE");
				sv.setUpdated_by(0);
				sv.setCreated_date(new java.util.Date());
				sv.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
				session.beginTransaction();
				session.save(sv);
				session.getTransaction().commit();
			} catch (Exception ex) {
				session.getTransaction().rollback();
				ex.printStackTrace();
			} finally {
				if(session != null){
					session.close();
				}
				return "success";
			}
		}
		
		@SuppressWarnings("finally")
		public int getSimId(String phone_no) {
			
			int sim_id = 0;
			Session session = null;
			try {
				session = HibernateUtil.getSession("");
				String qry = " select simcard_id from simcard where  phone_number='"+ phone_no + "' and deleted_status = 0 limit 1 ";
				Query query = session.createSQLQuery(qry);
				if (query.list().size() > 0) {
					sim_id = Integer.parseInt(query.uniqueResult().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(session != null){
					session.close();
				}
				return sim_id;
			}
			
		}
}
