package com.trimax.its.vehicle.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;

import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleDefects;
import com.trimax.its.vehicle.model.VehicleNumber;

public class VehicleDefectsDao {
	
	
	
	@SuppressWarnings("unchecked")
	public List<VehicleNumber> getVehicleDropList(String name){
		List<Vehicle> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//Query query;
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			List<Map<String, Object>> aliasToValueMapList =null;
			List<VehicleNumber> list1 = new ArrayList<VehicleNumber>();
			try{
			
				//query = session.createQuery("from Vehicle where deleted_status=0");
				/* query = session.createQuery("from Vehicle where status='Active' and deleted_status=0 and (vehicleRegistrationNumber like '"+name+"%')");
				query.setResultTransformer(Transformers.aliasToBean(Vehicle.class));
				list = (List<Vehicle>) query.list();*/
				
				String qry = "select vehicle_id,license_number from vehicle where status = 'Active' and deleted_status = '0' and (license_number like '"+name+"%') order by license_number ";
				Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				 aliasToValueMapList = query.list();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						VehicleNumber vehiclenumberdetails = new VehicleNumber();
						vehiclenumberdetails.setId(Integer.parseInt(rs.get("vehicle_id").toString()));
						vehiclenumberdetails.setValue(rs.get("license_number").toString());
						vehiclenumberdetails.setVehicleNumber(rs.get("license_number").toString());
						list1.add(vehiclenumberdetails);
					}
				//System.out.println("list============"+aliasToValueMapList.size());
			} catch(Exception e){
				e.printStackTrace();
			}
			finally{
				  if(session!=null){
		                session.close();
		            }
			}
		//session.close();
		return list1;
		
	}



	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			  //  Criteria criteria = session.createCriteria(VehicleDefects.class);
			//	criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(VehicleDefects.class);
				
				}else{
					 criteria = session.createCriteria(VehicleDefects.class);
					
				     criteria.add(Restrictions.eq("deleted_status", 0));
				    
				}

				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					System.out.println("before cond");
					String search_term = request.getParameter("sSearch").trim();
						conditionGroup.add(Restrictions.like("defect_type","" + search_term + "%"));
						
						conditionGroup.add(Restrictions.like("status","" + search_term+ "%"));
						
						//conditionGroup.add(Restrictions.like("veh.vehicleRegistrationNumber", ""+ search_term + "%"));	
						//conditionGroup.add(Restrictions.sqlRestriction(" this_.vehicle_id LIKE '%"+search_term+"%' "));
						conditionGroup.add(Restrictions.sqlRestriction(" defect_id LIKE '%"+search_term+"%' "));
					
					    criteria.add(conditionGroup);					
				}
				
				/*criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
				criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));*/
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
//				criteria.createCriteria("vehicle", "veh");
				
				List<VehicleDefects> list = criteria.list();
			    cnt = list.size();
		}
		catch(Exception e){
			//System.out.println(e);
			e.printStackTrace();
		}
		return cnt;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from VehicleDefects ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//	Criteria criteria = session.createCriteria(VehicleDefects.class);
			//criteria.add(Restrictions.eq("deleted_status", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(VehicleDefects.class);
			}else{
				 criteria = session.createCriteria(VehicleDefects.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				Junction conditionGroup = Restrictions.disjunction();
				String search_term = request.getParameter("sSearch").trim();				
					
					conditionGroup.add(Restrictions.like("defect_type","" + search_term+ "%"));
					conditionGroup.add(Restrictions.like("status","" + search_term+ "%"));					
					//conditionGroup.add(Restrictions.like("veh.vehicleRegistrationNumber","" + search_term+ "%"));
					//conditionGroup.add(Restrictions.sqlRestriction(" this_.vehicle_id LIKE '%"+search_term+"%' "));
					conditionGroup.add(Restrictions.sqlRestriction(" defect_id LIKE '%"+search_term+"%' "));
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
			//criteria.createCriteria("vehicle", "veh");
			
			List<VehicleDefects> list = criteria.list();
			JSONArray array = new JSONArray();
//			System.out.println("List size----->" + list.size() + "\t"
//					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getDefect_id()
						+ "' value='"
						+ list.get(i).getDefect_id() + "'/>");
				ja.add(list.get(i).getDefect_id());
				ja.add(list.get(i).getDefect_type());
				//ja.add(list.get(i).getVehicle().getVehicleId());
				//ja.add(list.get(i).getVehicle().getVehicleRegistrationNumber());				
				ja.add(list.get(i).getStatus());			
				ja.add(common.getDateFromDbToPicker(list.get(i).getDate()));
				ja.add(list.get(i).getNotes());
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getDefect_id()+"'/>Deleted Record");	

						//ja.add("Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getDefect_id()+"'/>Record in Database");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				//System.out.println("Array----->" + array);
			}

			totalAfterFilter = total;//getTotalRecords(total,request,cols,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		//	System.out.println(ex);
		}
		return result;
	}
	public boolean getDuplicate(String type,String defectdate)
	{
		boolean flag=false;
		String qry = " select date,defect_type from vehicle_defects " +
				"where deleted_status=0 and defect_type='"+type+"' and date='"+defectdate+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public int saveVehicleDefect(VehicleDefects vehicledefect)
	{
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			id=(Integer)session.save(vehicledefect);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		finally{
			if (session != null) {
				session.close();
			}
		}
		return id;
	}
	public Map<Integer, String> getVehicleList() {
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery("select vehicle_id as vehicleId,license_number as vehicleRegistrationNumber from vehicle where status='Active' and deleted_status=0 order by license_number").addScalar("vehicleId", Hibernate.INTEGER).addScalar("vehicleRegistrationNumber", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(Vehicle.class));
		
		try{
		List<Vehicle> list = query.list();
		
		for (Vehicle vehicle : list) {
			resultMap.put(vehicle.getVehicleId(), vehicle.getVehicleRegistrationNumber().trim());
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
	
	public String deletevehicledefect(VehicleDefects deviceType, int device_type_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		VehicleDefects defect = (VehicleDefects) session.get(VehicleDefects.class,device_type_id);

		defect.setDeleted_status(1);
		defect.setUpdated_by(deviceType.getUpdated_by());
		defect.setUpdated_date(new java.util.Date());
		
		session.update(defect);
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
	
	public VehicleDefects getEditedVehicleDefect(int id) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		VehicleDefects defect = (VehicleDefects) session.get(VehicleDefects.class, new Integer(id));
		defect.setDate(common.getDateToPicker(defect.getDate()));

		return defect;
	}

	public int updateVehicleDefect(int id,VehicleDefects user){
		boolean flag=false;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		try{
		
		VehicleDefects user1=(VehicleDefects)session.load(VehicleDefects.class,new Integer(id));
		HttpServletRequest request=ServletActionContext.getRequest();
		
		user1.setDefect_type(user.getDefect_type());
		user1.setNotes(user.getNotes());
		user1.setStatus(user.getStatus());
		//user1.setVehicle(user.getVehicle());
		user1.setDate(common.getDateFromPicker(user.getDate()));
		/*user1.setDate(user.getDate());
		System.out.println(user.getDate()+"datevvvvv");*/
		user1.setUpdated_by(user1.getUpdated_by());
		user1.setUpdated_date(new java.util.Date());
		session.update(user1);
		session.getTransaction().commit();		
		i=1;
		}
		catch(Exception ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		finally{
			if (session != null) {
				session.close();
			}
		}

		return i;
}
	public boolean checkVehicleDefect(String type,String vehicle_date) {
		boolean flag = false;
		String qry = " select date,defect_type from vehicle_defects" +
				" where deleted_status=0 and defect_type='"+type+"' and date='"+vehicle_date+"' ";
		
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean checkVehicleDefectForUpdate(String type,int id,String vehicle_date) {
		boolean flag = false;
		String qry = " select date,defect_type from vehicle_defects " +
				"where defect_type='"+type+"' and defect_id='"+id+"' and date='"+vehicle_date+"' ";
		//System.out.println("QueryIs"+qry);
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
/*	public List<String> getVehicleid() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			// String qry =
			// "select org_type_id from org_type where org_type in('CENTRAL OFFICE','DIVISION','STORE','ACCOUNT','DEPOT')";
			String qry = "select vehicle_id from vehicle where status = 'Active' and deleted_status = '0'";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("vehicle_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getnumberName() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select license_number from vehicle where status = 'Active' and deleted_status = '0'";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("license_number").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}
*/
	
	public String getDateFromDb(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat1.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}

	
	
}
