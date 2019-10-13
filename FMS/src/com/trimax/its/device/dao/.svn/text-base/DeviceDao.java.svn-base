package com.trimax.its.device.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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

import com.trimax.its.common.Common;
import com.trimax.its.device.model.Battery;
import com.trimax.its.device.model.Device;
import com.trimax.its.device.model.DeviceHistory;
import com.trimax.its.device.model.DeviceOrg;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.device.model.SimVtu;
import com.trimax.its.device.model.Simcard;
import com.trimax.its.model.Vendor;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.OrganisationChart;

public class DeviceDao {
	Common common = new Common();
	HttpServletRequest request = ServletActionContext.getRequest();
	boolean isSuccess = false;

	@SuppressWarnings("finally")
	public int getTotalRecordsSearch(int total, HttpServletRequest request,	String cols, String dir,String viewdeletedrecord) {
		int cnt = 0;
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			Criteria  criteria = session.createCriteria(Device.class);
			criteria.createCriteria("device", "device");
			criteria.createCriteria("vendor", "vendor");
			
			if(!viewdeletedrecord.equalsIgnoreCase("Y")){
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}

			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				try{conditionGroup.add(Restrictions.eq("device_id",Integer.parseInt(search_term)));}catch(Exception e){}
				conditionGroup.add(Restrictions.like("device.device_type_name",	"%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("device_serial_number","%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));

				criteria.add(conditionGroup);

			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			List<Device> list = criteria.list();
			cnt = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close(); 
			}
			return cnt;
		}
		
	}
	
	@SuppressWarnings("finally")
	public int getTotalRecords(String viewdeletedrecord) {
		int cnt = 0;
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria  criteria = session.createCriteria(Device.class);
			criteria.createCriteria("device", "device");
			criteria.createCriteria("vendor", "vendor");
			if(!viewdeletedrecord.equalsIgnoreCase("Y")){
			    criteria.add(Restrictions.eq("deleted_status", 0));
			}
			List<Device> list = criteria.list();
			cnt = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close(); 
			}
			return cnt;
		}
		
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public JSONObject getData(int total, HttpServletRequest request,String cols, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Session session = null;
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria  criteria = session.createCriteria(Device.class);
			
			if(!viewdeletedrecord.equalsIgnoreCase("Y")){
			    criteria.add(Restrictions.eq("deleted_status", 0));
				
			}
			criteria.createCriteria("vendor", "vendor");
			criteria.createCriteria("device", "device");
			criteria.createCriteria("model", "model");
			if (!request.getParameter("sSearch").equals("")) {
				
				String search_term = request.getParameter("sSearch").trim();
				Junction conditionGroup = Restrictions.disjunction();
				try{conditionGroup.add(Restrictions.eq("device_id",Integer.parseInt(search_term)));}catch(Exception e){}
				conditionGroup.add(Restrictions.like("device.device_type_name",	"%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("device_serial_number","%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
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
			List<Device> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+ list.get(i).getDevice_id()+ "' value='"+ list.get(i).getDevice_id() + "'/>");
				ja.add(list.get(i).getDevice_id());
				ja.add(list.get(i).getDevice_serial_number());
				ja.add(list.get(i).getDevice().getDevice_type_name());
				ja.add(list.get(i).getModel().getModel_type_name());
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getVendor().getVendor_name());
				ja.add(common.getDateToDatePicker(list.get(i).getPurchase_date()));
				ja.add(list.get(i).getNotes());
				if(viewdeletedrecord.equalsIgnoreCase("Y")){
					int deletedstatus=list.get(i).getDeleted_status();
					if(deletedstatus==1){
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getDevice_id()+"'/>Deleted Record");	
					}else{
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getDevice_id()+"'/>Record in Database");	
					}
				}
				array.add(ja);
			}
			String search_term = request.getParameter("sSearch");
			
			if (search_term != null && !search_term.equals("")) {
				totalAfterFilter =getTotalRecordsSearch(total,request,cols,dir,viewdeletedrecord);
				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}
			else{
				totalAfterFilter = getTotalRecords(viewdeletedrecord);
				result.put("aaData", array);
				result.put("iTotalRecords", total);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally{
			return result;
		}
	}

	

	@SuppressWarnings("finally")
	public List<Device_Type> getDeviceTypeNames() {
		List<Device_Type> resultList = new ArrayList<Device_Type>();
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from Device_Type devicetype where status='Active' and deleted_status=0 and device_type_id !=3 order by devicetype.device_type_name");
			Device_Type headerForDeviceTye = new Device_Type();
			headerForDeviceTye.setDevice_type_id(0);
			headerForDeviceTye.setDevice_type_name("Select");
			resultList.add(headerForDeviceTye);
			List<Device_Type> list = query.list();
			for (Device_Type device : list) {
				resultList.add(device);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session != null){
				session.close();
			}
			return resultList;
		}
	}

	@SuppressWarnings("finally")
	public List<ModelType> getModelName() {
		List<ModelType> resultList = new ArrayList<ModelType>();
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			ModelType headerForModelType = new ModelType();
			headerForModelType.setModel_type_id(0);
			headerForModelType.setModel_type_name("Select");
			resultList.add(headerForModelType);
			Query query = session.createQuery("from ModelType modeltype where status='Active' and deleted_status=0 order by modeltype.model_type_name");
			List<ModelType> list = query.list();
			for (ModelType model : list) {
				resultList.add(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session != null){
				session.close();
			}
			return resultList;
		}
	}

	@SuppressWarnings("finally")
	public List<String> getVendorId() {

		List list = new ArrayList();
		Session session = null;
		try{
		String qry = "select vendor_id from vendor where status='ACTIVE' group by vendor_name  ";
		session= HibernateUtil.getSession("");
		Query query = session.createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("vendor_id").toString());
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return list;
		}
	}
//for select device type
	@SuppressWarnings("finally")
	public List<Vendor> getVendorName(String devicetype) {

		List<Vendor> resultList = new ArrayList<Vendor>();
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from Vendor where status='Active' and deleted_status=0 and device_type_id="+devicetype+"  order by vendor_name ");
			
			Vendor headerForVendor = new Vendor();
			headerForVendor.setId(0);
			headerForVendor.setVendor_name("Select");
			
			List<Vendor> list = query.list();
			resultList.add(headerForVendor);
			for (Vendor vendor : list) {
				resultList.add(vendor);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session != null)
			session.close();
			return resultList;
		}
	}
	//for edit time
/*	@SuppressWarnings("finally")
	public List<Vendor> getVendorName(int devicetype) {

		List<Vendor> resultList = new ArrayList<Vendor>();
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from Vendor where status='Active' and deleted_status=0 and device_type_id=(select from device )  order by vendor_name ");
			
			Vendor headerForVendor = new Vendor();
			headerForVendor.setId(0);
			headerForVendor.setVendor_name("Select");
			
			List<Vendor> list = query.list();
			resultList.add(headerForVendor);
			for (Vendor vendor : list) {
				resultList.add(vendor);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session != null)
			session.close();
			return resultList;
		}
	}*/
	@SuppressWarnings("finally")
	public List<Vendor> getVendorName() {

		List<Vendor> resultList = new ArrayList<Vendor>();
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from Vendor where status='Active' and deleted_status=0   order by vendor_name ");
			
			Vendor headerForVendor = new Vendor();
			headerForVendor.setId(0);
			headerForVendor.setVendor_name("Select");
			
			List<Vendor> list = query.list();
			resultList.add(headerForVendor);
			for (Vendor vendor : list) {
				resultList.add(vendor);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session != null)
			session.close();
			return resultList;
		}
	}
	@SuppressWarnings("finally")
	public boolean addDevice(Device device) {
	
		device.setPurchase_date(common.getDateFromDatePicker((device.getPurchasedateString().trim())));
		device.setCreated_by(request.getSession().getAttribute("userid").toString());
		device.setCreated_date(new java.util.Date());
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			int id=(Integer) session.save(device);
			ServletActionContext.getRequest().getSession().setAttribute("createdDeviceId",id);
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			isSuccess = false;
		} finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
		
	}

	public int addDeviceHistory(DeviceHistory device, int divid,HttpServletRequest request) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {

			session.beginTransaction();
			device = new DeviceHistory();
			device.setStatus("NEW");
			device.setActivity("Device Created");
			device.setActivity_done_by(Integer.parseInt(request.getSession()
					.getAttribute("userid").toString()));
			// device.getDeviceHis().setDevice_id(divid);
			id = (Integer) session.save(device);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public int updateDeviceidHistory(DeviceHistory device, int divid,HttpServletRequest request, int hisid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {

			session.beginTransaction();
			String sql = "update devicehistory set device_id=" + divid
					+ " where device_history_id=" + hisid;
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}


	@SuppressWarnings("finally")
	public boolean deleteDevice(int device_id) {
		Session session = null;
		int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
		try {
			//
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			Device deviceobj = (Device) session.get(Device.class, device_id);
			deviceobj.setDeleted_status(1);
			deviceobj.setUpdated_by(userId);
			deviceobj.setUpdated_date(new java.util.Date());
			session.update(deviceobj);
			session.getTransaction().commit();
			isSuccess = true;
			
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
			isSuccess = false;
		} finally {
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}

	public int updateDeviceHistory(DeviceHistory device, int divid,HttpServletRequest request) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {

			session.beginTransaction();
			device = new DeviceHistory();
			device.setStatus("Deleted");
			device.setActivity("Device Deleted");
			device.setActivity_done_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			device.setDate_of_activity((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			// device.getDeviceHis().setDevice_id(divid);
			id = (Integer) session.save(device);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public int updateDevicedeletedidHistory(DeviceHistory device, int divid,HttpServletRequest request, int hisid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {
			session.beginTransaction();
			String sql = "update devicehistory set device_id=" + divid+ " where device_history_id=" + hisid;
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public int updateDeviceHistoryForSim(DeviceHistory device, int divid,int sid, HttpServletRequest request) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {

			session.beginTransaction();
			device = new DeviceHistory();
			device.setStatus("Sim Assigned");
			device.setActivity("Sim Device Allocation");
			device.setSim_id(sid);
			device.setActivity_done_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			device.setDate_of_activity((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			// device.getDeviceHis().setDevice_id(divid);
			id = (Integer) session.save(device);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public int updateDevicidHistoryForSim(DeviceHistory device, int divid,HttpServletRequest request, int hisid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {
			session.beginTransaction();
			String sql = "update devicehistory set device_id=" + divid	+ " where device_history_id=" + hisid;
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public int updateDeviceHistoryForOrg(DeviceHistory device, int divid,int sid, HttpServletRequest request) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {
			session.beginTransaction();
			device = new DeviceHistory();
			device.setStatus("Organization Assigned");
			device.setActivity("Organization Device Allocation");
			device.setSim_id(sid);
			device.setActivity_done_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			device.setDate_of_activity((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			// device.getDeviceHis().setDevice_id(divid);
			id = (Integer) session.save(device);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public int updateDevicidHistoryFororg(DeviceHistory device, int divid,
			HttpServletRequest request, int hisid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {

			session.beginTransaction();
			String sql = "update devicehistory set device_id=" + divid + " where device_history_id=" + hisid;
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public Device getEditedDevice(int parseInt) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Device devicetype = (Device) session.get(Device.class, new Integer(parseInt));
		return devicetype;
	}


	// Release Battery
	public int updateReleaseDeviceHistoryForBat(DeviceHistory device,
			int divid, int sid, HttpServletRequest request) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {

			session.beginTransaction();
			device = new DeviceHistory();
			device.setStatus("Battery Released");
			device.setActivity("Battery Device Released");
			device.setSim_id(sid);
			device.setActivity_done_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			device.setDate_of_activity((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			device.setUpdated_date(new java.util.Date());

			// device.getDeviceHis().setDevice_id(divid);
			id = (Integer) session.save(device);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return id;
	}

	// Release Simcard
	public int updateReleaseDeviceHistoryForSim(DeviceHistory device,
			int divid, int sid, HttpServletRequest request) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id = 0;
		try {

			session.beginTransaction();
			device = new DeviceHistory();
			device.setStatus("SimCard Released");
			device.setActivity("SimCard Device Released");
			device.setSim_id(sid);
			device.setActivity_done_by(Integer.parseInt(request.getSession()
					.getAttribute("userid").toString()));
			device.setDate_of_activity((new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss")).format(new Date()));

			// device.getDeviceHis().setDevice_id(divid);
			id = (Integer) session.save(device);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return id;
	}

	@SuppressWarnings("finally")
	public boolean updateDevice(Device device, int device_id) {

		Session session = null;
		device.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		device.setUpdated_date(new java.util.Date());
		try {
			//
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			Device deviceobj = (Device) session.get(Device.class, device_id);
			deviceobj.setStatus(device.getStatus());
			deviceobj.setUpdated_by(device.getUpdated_by());
			deviceobj.setUpdated_date(new java.util.Date());
			deviceobj.setDevice_serial_number(device.getDevice_serial_number());
			deviceobj.setModel(device.getModel());
			// deviceobj.getDevice().setDevice_type_id(device.getDevice().getDevice_type_id());
			deviceobj.setPurchase_date(common.getDateFromDatePicker((device.getPurchasedateString().trim())));
			// deviceobj.getVendorid().setId(device.getVendorid().getId());
			deviceobj.setDevice(device.getDevice());
			deviceobj.setVendor(device.getVendor());
			deviceobj.setNotes(device.getNotes());
			session.update(deviceobj);
			session.getTransaction().commit();
			isSuccess = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			isSuccess = false;
		} finally {
			if(session != null){
				session.close();
			}
			return isSuccess;
		}

	
	}

	@SuppressWarnings("finally")
	public List<Simcard> getSimcard() {
		List<Simcard> list = new ArrayList<Simcard>();
		Simcard card = null;
		Session session = null;
		try {
			String qry = "select simcard_id,phone_number from simcard where simcard_id not in(select simcard_id from simcard sm" +
					" inner join sim_vtu sv on sm.simcard_id=sv.sim_id " +
					" where sv.status='ACTIVE' )  and status='ACTIVE' and deleted_status= '0' and phone_number IS NOT NULL group by phone_number";
			session = HibernateUtil.getSession("");
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					card = new Simcard();
					Map<String, Object> rs = aliasToValueMapList.get(i);
					card.setSimcard_id(Integer.parseInt(rs.get("simcard_id").toString()));
					card.setPhone_number(rs.get("phone_number").toString()!=null ?rs.get("phone_number").toString():"");
					list.add(card);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session != null)
				session.close();
			return list;
		}
	}

	@SuppressWarnings("finally")
	public Simcard assignedSimCard(int deviceId) {
		Simcard assignedSimCard = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			String qry = "select simcard_id,phone_number from simcard sm inner join  sim_vtu sv on sv.sim_id=sm.simcard_id where sv.status='ACTIVE' and sm.status='ACTIVE' and sv.device_id="+ deviceId;
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				assignedSimCard = new Simcard();
				Map<String, Object> rs = aliasToValueMapList.get(0);
				assignedSimCard.setSimcard_id(Integer.parseInt(rs.get("simcard_id").toString()));
				assignedSimCard.setPhone_number(rs.get("phone_number").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return assignedSimCard;
		}
	}

	@SuppressWarnings("finally")
	public List<OrganisationChart> getorganization() {
		Session session = null;
		List<OrganisationChart> list = new ArrayList<OrganisationChart>();
		try{
			session = HibernateUtil.getSession("");
			String qry = "select distinct org_chart_id,org_name from org_chart where parent_id IS NOT NULL and deleted_status=0 group by   org_name order by org_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			OrganisationChart org = null;
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					org = new OrganisationChart();
					Map<String, Object> rs = aliasToValueMapList.get(i);
					org.setOrg_chart_id(Integer.parseInt(rs.get("org_chart_id").toString()));
					org.setOrg_name(rs.get("org_name").toString());
					list.add(org);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return list;
		}
	}

	@SuppressWarnings("finally")
	public OrganisationChart assignedOrg(int deviceId) {
		OrganisationChart assignedOrg = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			String qry = "select org.org_chart_id,org.org_name from org_chart org inner join  device_org do on do.org_id=org.org_chart_id where do.status='ACTIVE' and do.device_id="+ deviceId+"";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			OrganisationChart org = null;
			if (aliasToValueMapList.size() > 0) {
				assignedOrg = new OrganisationChart();
				Map<String, Object> rs = aliasToValueMapList.get(0);
				assignedOrg.setOrg_chart_id(Integer.parseInt(rs.get("org_chart_id").toString()));
				assignedOrg.setOrg_name(rs.get("org_name").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return assignedOrg;
		}
	}

	public List<Battery> getBatteryList() {
		List<Battery> list = new ArrayList<Battery>();
		String qry = "select ba.battery_id battery_id,ba.serial_number serial_number from battery ba " +
				" where battery_id not in(select b.battery_id from battery b " +
				" inner join battery_device bd on b.battery_id=bd.battery_id  where bd.status='ACTIVE' )and ba.status='ACTIVE' and ba.deleted_status='0' ;";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		Battery org = null;
		if (aliasToValueMapList.size() > 0) {
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				org = new Battery();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				org.setBattery_id(Integer.parseInt(rs.get("battery_id").toString()));
				org.setSerial_number(rs.get("serial_number").toString());
				list.add(org);
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings("finally")
	public Battery assignedBattery(int deviceId) {
		Battery battery = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			String qry = "select bat.battery_id,bat.serial_number from battery bat inner join  battery_device bd on bat.battery_id = bd.battery_id where bd.status='ACTIVE' and bd.device_id="+ deviceId;
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				battery = new Battery();
				Map<String, Object> rs = aliasToValueMapList.get(0);
				battery.setBattery_id(Integer.parseInt(rs.get("battery_id").toString()));
				battery.setSerial_number(rs.get("serial_number").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return battery;
		}
	}

	@SuppressWarnings("finally")
	public boolean isDeviceTypeCanConatinSimCardAndBattery(int deviceId){
		boolean isDeviceTypeCanContainSimacrdAndBattery = false;
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			String query = "select count(*) count from device_type dt " +
					" inner join device d on d.device_type_id = dt.device_type_id " +
					" inner join default_system_veriable dsv  on dsv.sys_value = dt.device_type_id " +
					" where sys_key in('ETM','VTU') and dt.device_type_id=d.device_type_id and d.device_id='"+deviceId+"' ";
			int count = common.getDBResultInt(session, query, "count");
			if(count > 0){
				isDeviceTypeCanContainSimacrdAndBattery = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return isDeviceTypeCanContainSimacrdAndBattery;
		}
	}
	@SuppressWarnings("finally")
	public boolean isDeviceContainsSimcard(int deviceId){
		
		boolean isDeviceContainsSimcard = false;
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			String query = "select count(*) count from sim_vtu sv  inner join device d on d.device_id = sv.device_id  " +
						 " where sv.device_id='"+deviceId+"' and sv.status ='ACTIVE';";
			int count = common.getDBResultInt(session, query, "count");
			if(count > 0){
				isDeviceContainsSimcard = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return isDeviceContainsSimcard;
		}
	}
	@SuppressWarnings("finally")
	public boolean isDeviceContainsBattery(int deviceId){
		
		boolean isDeviceContainsBattery = false;
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			String query = "select count(*) count from battery_device bd  inner join device d on d.device_id = bd.device_id  " +
						 " where bd.device_id='"+deviceId+"' and bd.status ='ACTIVE';";
			int count = common.getDBResultInt(session, query, "count");
			if(count > 0){
				isDeviceContainsBattery = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return isDeviceContainsBattery;
		}
	}
	public int getSimId(String phone_no) {
		String qry = " select simcard_id from simcard where  phone_number='"
				+ phone_no + "' and deleted_status = 0 limit 1 ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		int sim_id = 0;
		try {

			if (query.list().size() > 0) {
				sim_id = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception e) {
		} finally {
			HibernateUtil.closeSession();
		}
		return sim_id;
	}

	public int updateDeviceId(int deviceId, int sim_id) {
		int record = 0;
		String qry = "update sim_vtu set   sim_id=" + sim_id
				+ ",updated_date=now()  where  device_id='"+ deviceId + "' and status='ACTIVE'";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery(qry);
			record = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return record;
	}

	public int getorgId(String orgName) {
		String qry = " select org_chart_id from org_chart where org_name='"
				+ orgName + "' limit 1";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		int org_id = 0;
		try {
			if (query.list().size() > 0) {
				org_id = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return org_id;
	}

	public int updateOrgId(int deviceId) {
		int record = 0;
		String qry = "update device_org set status='INACTIVE' where device_id="
				+ deviceId;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery(qry);
			record = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return record;
	}
	
	public int getSimId(int deviceId) {
		int record = 0;
		String qry = "select sim_id from sim_vtu where device_id=" + deviceId
				+ " and status='ACTIVE' limit 1 ";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session.createSQLQuery(qry);
			record = Integer.parseInt(query.uniqueResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return record;
	}



	public String mapSimVtu(int device_id, int simid,HttpServletRequest request ) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
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
			session.close();
		}
		return "success";
	}

	public String mapOrgVtu(int device_id, int orgid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			DeviceOrg dorg = new DeviceOrg();
			dorg.setDevice_id(device_id);
			dorg.setOrg_id(orgid);
			dorg.setStatus("ACTIVE");
			dorg.setUpdated_by(0);
			session.beginTransaction();
			session.save(dorg);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return "success";
	}


/*	public boolean checkBatDevice(int deviceid, int batid) {
		String qry = " select battery_id from battery_device where status='ACTIVE' and  battery_id="
				+ batid;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		boolean flag = false;
		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}*/

/*	public boolean checkBatDevicePerticular(int deviceid, int batid) {
		String qry = " select device_id from battery_device where status='ACTIVE' and  device_id="
				+ deviceid;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		boolean flag = false;
		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
*/
/*	public boolean checkBatDeviceNew(int batid) {
		String qry = " select battery_id from battery_device where  battery_id="
				+ batid;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		boolean flag = false;
		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}*/

/*public int updateBatteryId(int deviceId, int batID, int batdeviceID) {
	int record = 0;
	String qry = "update battery_device set battery_id=" + batID
			+ ",updated_date=now()  where device_battery_id=" + batdeviceID;
	update sim_vtu set   sim_id=" + sim_id
			+ ",updated_date=now()  where  device_id='"+ deviceId + "' and status='ACTIVE'
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	try {
		session.beginTransaction();
		Query query = session.createSQLQuery(qry);
		record = query.executeUpdate();
		session.getTransaction().commit();
	} catch (Exception e) {
		session.getTransaction().rollback();
	} finally {
		session.close();
	}

	return record;
}*/
/*	public String mapBatVtu(int device_id, int battery_id,
			HttpServletRequest request) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			System.out.println("battery_id=====>" + battery_id);
			BatteryDevice batdiv = new BatteryDevice();
			batdiv.setBattery_id(battery_id);
			batdiv.setDevice_id(device_id);
			batdiv.setStatus("ACTIVE");
			batdiv.setCreated_by(request.getSession().getAttribute("userid")
					.toString());
			batdiv.setUpdated_by(0);
			batdiv.setUpdated_date(null);
			session.beginTransaction();
			session.save(batdiv);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return "success";
	}*/

/*	public void updateBatteryMaster(int batid) {
		String qry = " update  battery  set status='ACTIVE' where battery_id="
				+ batid;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(qry);
		Transaction txn = session.beginTransaction();
		try {
			query.executeUpdate();
			txn.commit();
		} catch (Exception ex) {
			txn.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}*/
	/*	public int getSimDeviceID(int device_id) {
	// TODO Auto-generated method stub
	String qry = " select sim_vtu_id from sim_vtu where device_id="
			+ device_id + " and status='ACTIVE' limit 1 ";
	Query query = HibernateUtil.getSession("").createSQLQuery(qry);
	int simdeviceid = 0;
	if (query.list().size() > 0) {
		simdeviceid = Integer.parseInt(query.uniqueResult().toString());
	}
	return simdeviceid;
}

public int getBatDeviceID(int device_id) {
	// TODO Auto-generated method stub
	String qry = " select device_battery_id from battery_device where device_id="
			+ device_id + " limit 1";
	Query query = HibernateUtil.getSession("").createSQLQuery(qry);
	int simdeviceid = 0;
	if (query.list().size() > 0) {
		simdeviceid = Integer.parseInt(query.uniqueResult().toString());
	}
	return simdeviceid;
}
*/
	public void updateSimMaster(int simid) {
		String qry = " update  simcard  set status='ACTIVE' where simcard_id="
				+ simid;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(qry);
		Transaction txn = session.beginTransaction();
		try {
			query.executeUpdate();
			txn.commit();
		} catch (Exception ex) {
			txn.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	public int checkAssignedSim(int deviceid) {
		int sim_vtu_id = 0;
		String qry = " select sim_vtu_id from sim_vtu where status='ACTIVE' and device_id="
				+ deviceid;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		boolean flag = false;
		if (query.list().size() > 0) {
			sim_vtu_id = Integer.parseInt(query.uniqueResult().toString());
		}
		return sim_vtu_id;
	}

	public void updateSim_VTU(int sim_device_id) {
		String qry = " update  sim_vtu  set status='INACTIVE' where sim_vtu_id="
				+ sim_device_id;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(qry);
		Transaction txn = session.beginTransaction();
		try {
			query.executeUpdate();
			txn.commit();
		} catch (Exception ex) {
			txn.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	public boolean checkSOrgDevice(int deviceid, int orgid) {
		String qry = " select device_id from device_org where status='ACTIVE' and device_id="
				+ deviceid;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		boolean flag = false;
		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean checkSOrgDevicePerticular(int deviceid, int orgid) {
		String qry = " select device_id from device_org where status='ACTIVE'  and device_id="
				+ deviceid + " and org_id=" + orgid;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		boolean flag = false;
		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
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

	public boolean checkSimDevicePerticular(int deviceid, int simid) {
		String qry = " select device_id from sim_vtu where status='ACTIVE' and  device_id="
				+ deviceid;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		boolean flag = false;
		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean checkNewSimCard(int simid) {
		String qry = " select device_id from sim_vtu  where  sim_id=" + simid
				+ " and status='ACTIVE' limit 1";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		boolean flag = false;
		try {
			int device_id = Integer.parseInt(query.uniqueResult().toString());
			if (query.list().size() > 0 && device_id != -1) {
				flag = true;
			}
		} catch (Exception ex) {

		}
		return flag;
	}


	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
	@SuppressWarnings("finally")
	public boolean checkDeviceForUpdate(Device device,String reqType) {
			
		boolean isNew = false;
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from device where device_serial_number='"+ device.getDevice_serial_number().trim()+"' and deleted_status='0'";
			
			if(reqType.equals("update")){
				query = "select count(*)as count from device where device_serial_number='"+ device.getDevice_serial_number().trim()+"' and device_id not in ('"+device.getDevice_id()+"') and deleted_status='0' ";
			}
			Query queryObject = session.createSQLQuery(query).addScalar("count", Hibernate.INTEGER);
			List<Integer> list = queryObject.list();
			int cnt = list.get(0);
			if (cnt > 0) {
				isNew = false;
			} else {
				isNew = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isNew = false;
		} finally {
			if(session != null){
				session.close();
			}
			return isNew;
		}
	}
	@SuppressWarnings("finally")
	public boolean updateSimCardToDevice(int deviceId,Simcard simCard,boolean onlyRelease)
	{
		boolean isSuccess = false;
		Session session = null;
		Transaction transaction = null;
		int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
		String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		try{
			session = HibernateUtil.getSession("");
			transaction = session.beginTransaction();
			String queryToUpadate = "",queryToInsert="";
			
			//***************Release prevoius sim from device ******************//
			queryToUpadate = "update sim_vtu set  status='INACTIVE',updated_by='"+userId+"',updated_date='"+currentDate+"' where device_id='"+deviceId+"' and status='ACTIVE'";
			session.createSQLQuery(queryToUpadate).executeUpdate();
			
			//***************Linking new sim to device if user selected other than ""NA"" ***********//
			if(!onlyRelease){
				if(simCard.getSimcard_id()!=-10){
					queryToInsert = " INSERT INTO sim_vtu (sim_id, device_id, status, created_by, created_date) " +
								" VALUES ('"+simCard.getSimcard_id()+"', '"+deviceId+"', 'ACTIVE', '"+userId+"', '"+currentDate+"')";
					Query qryObject = session.createSQLQuery(queryToInsert);
					qryObject.executeUpdate();
				}
			}
			transaction.commit();
			isSuccess = true;
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			isSuccess = false;
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updateBatteryToDevice(int deviceId,Battery battery,boolean onlyRelease)
	{
		boolean isSuccess = false;
		Session session = null;
		Transaction transaction = null;
		int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
		String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		try{
			session = HibernateUtil.getSession("");
			transaction = session.beginTransaction();
			String queryToUpadate = "",queryToInsert="";
			
			//***************Release prevoius battery from device ******************//
			queryToUpadate = "update battery_device set  status='INACTIVE',updated_by='"+userId+"',updated_date='"+currentDate+"' where device_id='"+deviceId+"' and status='ACTIVE'";
			session.createSQLQuery(queryToUpadate).executeUpdate();
			
			//***************Linking new battery to device if user selected other than ""NA"" ***********//
			if(!onlyRelease){
				if(battery.getBattery_id()!=-10){
					queryToInsert = " INSERT INTO battery_device (battery_id, device_id, status, created_by, created_date) " +
									" VALUES ('"+battery.getBattery_id()+"', '"+deviceId+"', 'ACTIVE', '"+userId+"', '"+currentDate+"')";
					Query qryObject = session.createSQLQuery(queryToInsert);
				qryObject.executeUpdate();
				}
			}
			transaction.commit();
			isSuccess = true;
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			isSuccess = false;
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updateOrgToDevice(int deviceId,OrganisationChart org,boolean onlyRelease)
	{
		boolean isSuccess = false;
		Session session = null;
		Transaction transaction = null;
		int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
		String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		try{
		
			session = HibernateUtil.getSession("");
			transaction = session.beginTransaction();
			String queryToUpadate = "",queryToInsert="";
			int fromdepot=0;
			String value="Transferred";
			if(onlyRelease){
//			 System.out.println("inside===="+onlyRelease);
				value="INACTIVE";
				queryToUpadate = "update device_org set  status='"+value+"',effective_end_date='"+currentDate+"',updated_by='"+userId+"',updated_date='"+currentDate+"' where device_id='"+deviceId+"' and (status='ACTIVE' or status='Transferred')";

			}else{
			//***************Release prevoius org from device ******************//
//			queryToUpadate = "update device_org set  status='INACTIVE',updated_by='"+userId+"',updated_date='"+currentDate+"' where device_id='"+deviceId+"' and status='ACTIVE'";
	//nm		//queryToUpadate = "update device_org set  status='"+value+"',effective_end_date='"+currentDate+"',updated_by='"+userId+"',updated_date='"+currentDate+"' where device_id='"+deviceId+"' and status='ACTIVE'";
				Query query=session.createSQLQuery("select org_id from device_org where status='active' and device_id='"+deviceId+"'");
			Integer	res = (Integer)query.uniqueResult();
				if(res==null) {
					queryToUpadate="INSERT INTO device_org (org_id, device_id, status, created_by, created_date) " +
						" VALUES ('"+org.getOrg_chart_id()+"', '"+deviceId+"', 'ACTIVE', '"+userId+"', '"+currentDate+"')";
				}else {
					fromdepot=res;
						if(org.getOrg_chart_id()==-10) {
							queryToUpadate = "update device_org set  status='INACTIVE',effective_end_date='"+currentDate+"',updated_by='"+userId+"',updated_date='"+currentDate+"' where device_id='"+deviceId+"' and (status='ACTIVE' or status='Transferred')";

						}else {
				queryToUpadate = "update device_org set org_id='"+org.getOrg_chart_id()+"', effective_end_date='"+currentDate+"',updated_by='"+userId+"',updated_date='"+currentDate+"' where device_id='"+deviceId+"' and status='ACTIVE'";
					}}
			}
			session.createSQLQuery(queryToUpadate).executeUpdate();
			
			//***************Linking new org to device if user selected other than ""NA"" ***********//
			if(!onlyRelease){
				if(org.getOrg_chart_id()!=-10){
					if(fromdepot  !=0) {
//				queryToInsert = " INSERT INTO device_org (org_id, device_id, status, created_by, created_date) " +
//							" VALUES ('"+org.getOrg_chart_id()+"', '"+deviceId+"', 'ACTIVE', '"+userId+"', '"+currentDate+"')";
					queryToInsert = "insert into device_transfer (device_id,from_depot_id,to_depot_id,updated_by,updated_date) "
							+ " values ("+deviceId+","+fromdepot+","+org.getOrg_chart_id()+","+userId+", "+"now())";
					Query qryObject = session.createSQLQuery(queryToInsert);
					qryObject.executeUpdate();
					}
				}
			}
			
			transaction.commit();
			isSuccess = true;
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			isSuccess = false;
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean isDeviceFreeFromVehicle(int deviceId){
		Session session = null;
		Common common = new Common();
		isSuccess = false;
		try{
			session = HibernateUtil.getSession("");
			String query = "select count(*) as count from vehicle_device where device_id='"+deviceId+"' and status='ACTIVE'";
			int count = common.getDBResultInt(session, query, "count");
			if(count>0){
				isSuccess = false;
				String queryForVehicleNo = "select license_number from vehicle v " +
						" inner join vehicle_device vd on vd.vehicle_id = v.vehicle_id where vd.device_id = '"+deviceId+"' and vd.status='ACTIVE'";
				String vehicleNo = common.getDBResultStr(session,queryForVehicleNo, "license_number");
				ServletActionContext.getRequest().getSession().setAttribute("vehicleNo", vehicleNo);
			}else{
				isSuccess = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean isDeviceFreeFromOrg(int deviceId){
		Session session = null;
		Common common = new Common();
		isSuccess = false;
		try{
			session = HibernateUtil.getSession("");
			String query = "SELECT COUNT(`device_org_id`) count FROM `device_org` WHERE `status` = 'ACTIVE' AND `device_id` = '"+deviceId+"'";
			int count = common.getDBResultInt(session, query, "count");
			if(count>0){
				isSuccess = false;
				String queryForVehicleNo = "SELECT `org_id`  FROM `device_org` " +
						" WHERE `status` = 'ACTIVE' AND `device_id` = '"+deviceId+"'"; 
				String orgName = common.getDBResultStr(session,queryForVehicleNo, "org_id");
				ServletActionContext.getRequest().getSession().setAttribute("orgName", orgName);
			}else{
				isSuccess = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}
	public boolean assinorg() {
	boolean result=false;
	
		return result;
	}
}
