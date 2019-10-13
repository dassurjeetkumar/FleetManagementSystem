package com.trimax.its.vehicle.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.action.DockingAction;
import com.trimax.its.vehicle.model.ComponentsTypes;
import com.trimax.its.vehicle.model.DockingDetails;
import com.trimax.its.vehicle.model.DockingHistory;
import com.trimax.its.vehicle.model.DockingType;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleModel;

public class DockingVehicleDAO {

	Common common = new Common();
	boolean isSuccess = false;
	@SuppressWarnings("finally")
	public DockingHistory isAlreadyDocked(Vehicle vehicleObject) {

		
		DockingHistory returnResult = null;
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = true;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(DockingHistory.class);
			criteria.addOrder(Order.desc("docking_id"));
			criteria.createAlias("vehicle", "vehicle");
			criteria.setMaxResults(1);
			criteria.add(Restrictions.eq("vehicle.vehicleId",
					vehicleObject.getVehicleId()));
			// returnResult = getDBResultInt(session,
			// "select docking_type from docking_history where vehicle_id="+vehicleObject.getVehicleId()+" order by docking_id desc limit 1",
			// "docking_type");
			List<Object> det = criteria.list();
			if (det.size() > 0) {
				// Object result = det.get(i);
				//
				// Device deviceList1 = (Device) result;
				//
				// vtuDeviceList.add(deviceList1);
				returnResult = (DockingHistory) det.get(0);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return returnResult;
		}
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<ComponentsTypes> getComponentsList() {
		List<ComponentsTypes> componentssList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria = session.createCriteria(ComponentsTypes.class);
			criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.add(Restrictions.like("status", "ACTIVE"));
			
			List<Object> det = criteria.list();
			componentssList = new ArrayList<ComponentsTypes>();

			ComponentsTypes componenets1 = new ComponentsTypes();
			componenets1.setComponentTypeId(0);
			componenets1.setComponentName("Select");
			componentssList.add(componenets1);

			for (int i = 0; i < det.size(); i++) {

				ComponentsTypes componenetsTypes = (ComponentsTypes) det.get(i);
				componentssList.add(componenetsTypes);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return componentssList;
		}
	}
	@SuppressWarnings({ "finally", "unchecked" })
	public List<VehicleModel> getVehicleDockingTypes() {
		List<VehicleModel> dockingTypeNames = null;
		Session session = null;
		Query query = null;
		List<Map<String, Object>> aliasToValueMapList = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			query = session
					.createSQLQuery("SELECT `docking_type`, docking_type_id  FROM `docking_type` where deleted_status='0'")
					.addScalar("docking_type", Hibernate.STRING).addScalar("docking_type_id", Hibernate.STRING);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = query.list();

			dockingTypeNames = new ArrayList<VehicleModel>();
			VehicleModel form1 = new VehicleModel();
			form1.setDockinTypeId(0);
			form1.setDockingType("Select");
			dockingTypeNames.add(form1);

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {

					Map<String, Object> resultSet = aliasToValueMapList.get(i);
					VehicleModel form = new VehicleModel();
					form.setDockinTypeId(Integer.parseInt((String) resultSet.get("docking_type_id")));
					form.setDockingType(resultSet.get("docking_type").toString());
					dockingTypeNames.add(form);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return dockingTypeNames;
		}
	}
	
	@SuppressWarnings("finally")
	public List<DockingType> getDockingTypeRecords() {
		List<DockingType> dockingTypes = null;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			Criteria criteria = session.createCriteria(DockingType.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			criteria.add(Restrictions.like("status", "ACTIVE"));

			List<Object> result = criteria.list();

			if (result.size() > 0) {
				dockingTypes = new ArrayList<DockingType>();
				for (int i = 0; i < result.size(); i++) {
					DockingType doctype = (DockingType) result.get(i);
					dockingTypes.add(doctype);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return dockingTypes;
		}

	}
	
	@SuppressWarnings("finally")
	public boolean saveDockingVehicle(DockingHistory dockingVehicleObject,Vehicle vehicleObject, DockingHistory latestDockingObject) {
	
		Session session = null;
		Transaction transaction = null;
		String currDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
		DockingDetails dockingDetails = null;
		boolean isDockingSkipped = false ;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			Vehicle vehicle = (Vehicle) session.get(Vehicle.class,vehicleObject.getVehicleId());
			
			String finalUpdateKms = "";
			String kmsOfCurrentDockingType;
//			= getCurrentDockingKmsRange(dockingVehicleObject,session);
			Map<String,String> dockingTypeMap = getDockingTypeMap(session);
			kmsOfCurrentDockingType = getDockingTypesLowerLimitMap(dockingVehicleObject,dockingTypeMap,session);
			isDockingSkipped = isUserSkippingDocking(dockingVehicleObject,latestDockingObject,session);
			
			if(latestDockingObject!=null){
				if(isDockingSkipped){
					finalUpdateKms = String.valueOf(Integer.parseInt(kmsOfCurrentDockingType)-Integer.parseInt(latestDockingObject.getDocking_kms()));
				}else{
					finalUpdateKms = kmsOfCurrentDockingType;
				}
			}else{
				finalUpdateKms = kmsOfCurrentDockingType;
			}
			
			dockingVehicleObject.setStartTime(common.getDateTimeFromDatePicker(dockingVehicleObject.getStartDateString()));
			dockingVehicleObject.setDocking_kms(finalUpdateKms);
			dockingVehicleObject.setDockingType(dockingVehicleObject.getDockingType().getDocking_type_id() != 0 ? dockingVehicleObject.getDockingType() : null);
			dockingVehicleObject.setDocking_batch_name(!dockingVehicleObject.getDocking_batch_name().trim().equals("") ? dockingVehicleObject.getDocking_batch_name().trim() : null);
			dockingVehicleObject.setComponenetType(dockingVehicleObject.getComponenetType().getComponentTypeId() != 0 ? dockingVehicleObject.getComponenetType() : null);
			dockingVehicleObject.setEoc_change(dockingVehicleObject.getEoc_change());
			dockingVehicleObject.setFip_change(dockingVehicleObject.getFip_change());
			dockingVehicleObject.setCretaedBy(userId);
			dockingVehicleObject.setCreatedDate(currDate);
			dockingVehicleObject.setUpdatedBy(0);
			dockingVehicleObject.setStatus(dockingVehicleObject.getStatus());
			dockingVehicleObject.setVehicle(vehicle);
			
			dockingDetails = new DockingDetails();
			dockingDetails.setVehicle(vehicle);
			dockingDetails.setDocking_kms(finalUpdateKms);
			dockingDetails.setCreatedBy(userId);
			dockingDetails.setCreatedDate(currDate);
			dockingDetails.setUpdatedBy(0);

			vehicle.setStatus("INACTIVE");
			vehicle.setUpdated_by(userId);
			vehicle.setUpdated_date(currDate);
			
			if(isDockingSkipped){
				DockingHistory dockingHisObjectForSkipped = new DockingHistory();
				dockingHisObjectForSkipped.setStatus("SKIPPED");
				dockingHisObjectForSkipped.setCretaedBy(userId);
				dockingHisObjectForSkipped.setCreatedDate(currDate);
				dockingHisObjectForSkipped.setVehicle(vehicle);
				session.save(dockingHisObjectForSkipped);
			}
			session.save(dockingVehicleObject);
			session.update(vehicle);
			session.save(dockingDetails);
			

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
	
	public boolean isUserSkippingDocking(DockingHistory presentDockingObject,DockingHistory previousDockingObject,Session session){
		
		boolean isUserSkipping = false;
		int currentDockingTypeId = presentDockingObject.getDockingType().getDocking_type_id();
		
		String queryForDockingType = "SELECT `docking_type` FROM `docking_type` WHERE `docking_type_id` = '"+currentDockingTypeId+"' ";
		String currentDockingType="";
		
		try{
			currentDockingType = common.getDBResultStr(session, queryForDockingType, "docking_type");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		if(previousDockingObject == null) {
			if( currentDockingType.equals("D1")){
					isUserSkipping = false;
			}else{
				isUserSkipping = true;
			}
		}else if(currentDockingType.equals("D1")&& previousDockingObject.getDockingType().getDocking_type().equals("D4") || 
				 currentDockingType.equals("D2")&& previousDockingObject.getDockingType().getDocking_type().equals("D1") ||
				 currentDockingType.equals("D3")&& previousDockingObject.getDockingType().getDocking_type().equals("D2") || 
				 currentDockingType.equals("D4")&& previousDockingObject.getDockingType().getDocking_type().equals("D3") ){
			isUserSkipping = false;  
		}else{
			isUserSkipping = true;
			}
//		}
		
		return isUserSkipping;
	}
	
	public String getCurrentDockingKmsRange(DockingHistory currentDockingHistoryObject,Session session){
		
		String dueryForGetKms = " SELECT `docking_kms` as kms FROM `docking_type` WHERE `docking_type_id` " +
						" = '"+currentDockingHistoryObject.getDockingType().getDocking_type_id()+"' ";
		String kms="";
		try{
		 kms= common.getDBResultStr(session, dueryForGetKms, "kms");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return kms;
	}
	
	public Map<String,String> getDockingTypeMap(Session session){

		Map<String,String> mapOfDockingTypes = new HashMap<String,String>();
		Criteria criteria = session.createCriteria(DockingType.class);
		List<DockingType> listOfObjects = criteria.list();
		for(int i=0;i<listOfObjects.size();i++){
			mapOfDockingTypes.put(listOfObjects.get(i).getDocking_type(), listOfObjects.get(i).getDockingTypeKms());
		}
		
		return mapOfDockingTypes;
	}
	public String getDockingTypesLowerLimitMap(DockingHistory currentDockingHistoryObject,Map<String,String> dockingTypeMap,Session session){
		
		String finalKms = "";
		String dueryForGetDockingTypeName = " SELECT `docking_type`  FROM `docking_type` WHERE `docking_type_id`= " +
				"'"+currentDockingHistoryObject.getDockingType().getDocking_type_id()+"' ";
		String dockingTypeName="";
		try{
		dockingTypeName = common.getDBResultStr(session, dueryForGetDockingTypeName, "docking_type");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(dockingTypeName.equals("D1")){
			finalKms = "0";
		}else if(dockingTypeName.equals("D2")){
			finalKms = dockingTypeMap.get("D1");
		}else if(dockingTypeName.equals("D3")){
			finalKms = dockingTypeMap.get("D2");
		}else if(dockingTypeName.equals("D4")){
			finalKms = dockingTypeMap.get("D3");
		}
		return finalKms;
	}
	
	public boolean isValidForDocking(DockingHistory presentDockingObject,Vehicle vehicle,DockingHistory previousDockingObject){
		boolean isValid = false;
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			
			String queryForLastestkms = "SELECT `docking_kms` as kms FROM `docking_details` WHERE `vehicle_id` = '"+vehicle.getVehicleId()+"' ORDER BY `id` DESC LIMIT 1";
			int latestDockingKms = common.getDBResultInt(session, queryForLastestkms, "kms");
			
			String countForVehicleInDockingDetails = "SELECT count(*) as count FROM `docking_details` WHERE `vehicle_id` = '"+vehicle.getVehicleId()+"' ";
			int count = common.getDBResultInt(session, countForVehicleInDockingDetails, "count");
			    
			String dueryForGetDockingTypeName = " SELECT `docking_type`  FROM `docking_type` WHERE `docking_type_id` = '"+presentDockingObject.getDockingType().getDocking_type_id()+"' ";
			String dockingTypeName = common.getDBResultStr(session, dueryForGetDockingTypeName, "docking_type");
			
			int maximumKms = Integer.parseInt (getDockingTypeMap(session).get(dockingTypeName));
			int minimumKms = Integer.parseInt (getDockingTypesLowerLimitMap(presentDockingObject,getDockingTypeMap(session),session));
			
			int vehicleProgrsessiveKms = Integer.parseInt(vehicle.getProgressingKMs());
			
			if(latestDockingKms >= minimumKms && latestDockingKms <= maximumKms && count>0){
				isValid  = true;
			}else if(vehicleProgrsessiveKms >= minimumKms && vehicleProgrsessiveKms <= maximumKms  && count==0){
				isValid  = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return isValid;
	}
	/*@SuppressWarnings("finally")
	public boolean saveDockingVehicle(DockingHistory dockingVehicleObject,Vehicle vehicleObject, String reqType, List dockingTypeMasterdata) {
		
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = true;
		DockingDetails dockingDetails = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Vehicle vehicle = (Vehicle) session.get(Vehicle.class,vehicleObject.getVehicleId());
			int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			String currDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
			String finalUpdateKms = vehicle.getProgressingKMs();
			if (dockingVehicleObject.getDockingType().equals("D1")) {
				finalUpdateKms = "0";
			}

			if (reqType.equals("update")) {
				finalUpdateKms = ""+ prevDockingTypeEndKms(dockingTypeMasterdata,dockingVehicleObject.getDockingType().getDocking_type());
				// finalUpdateKms +=1;

			}
			dockingDetails = new DockingDetails();
			dockingDetails.setVehicle(vehicle);
			dockingDetails.setDocking_kms(finalUpdateKms);

			dockingDetails.setCreatedBy(userId);
			dockingDetails.setCreatedDate(currDate);
			dockingDetails.setUpdatedBy(0);

			vehicle.setStatus("INACTIVE");
			vehicle.setUpdated_by(userId);
			vehicle.setUpdated_date(currDate);

			dockingVehicleObject.setStartTime(common.getDateTimeFromDatePicker(dockingVehicleObject.getStartDateString()));
			dockingVehicleObject.setDocking_kms(finalUpdateKms);
			dockingVehicleObject.setDockingType(dockingVehicleObject.getDockingType().getDocking_type_id() != 0 ? dockingVehicleObject.getDockingType() : null);
			dockingVehicleObject.setDocking_batch_name(!dockingVehicleObject.getDocking_batch_name().trim().equals("") ? dockingVehicleObject.getDocking_batch_name().trim() : null);
			dockingVehicleObject.setComponenetType(dockingVehicleObject.getComponenetType().getComponentTypeId() != 0 ? dockingVehicleObject.getComponenetType() : null);
			dockingVehicleObject.setEoc_change(dockingVehicleObject.getEoc_change());
			dockingVehicleObject.setFip_change(dockingVehicleObject.getFip_change());
			dockingVehicleObject.setCretaedBy(userId);
			dockingVehicleObject.setCreatedDate(currDate);
			dockingVehicleObject.setUpdatedBy(0);
			dockingVehicleObject.setStatus(dockingVehicleObject.getStatus());
			dockingVehicleObject.setVehicle(vehicle);

			transaction = session.beginTransaction();
			Integer id = (Integer) session.save(dockingVehicleObject);
			session.update(vehicle);
			session.save(dockingDetails);

			session.getTransaction().commit();
			System.out.println("vehicle docking success.... having id---->>>"+ id);

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			isSuccess = false;
		} finally {
			if (session != null) {
				session.close();
			}
			return isSuccess;
		}

	}
	*/

	int prevDockingTypeEndKms(List dockingTypeMasterdata, String dockType) {

		int finalkms = 0, d1kms = 0, d2kms = 0, d3kms = 0, d4kms = 0;

		for (int i = 0; i < dockingTypeMasterdata.size(); i++) {
			DockingType dockingIteraion = (DockingType) dockingTypeMasterdata
					.get(i);

			if (dockingIteraion.getDocking_type().equals("D1")) {
				d1kms = Integer.parseInt(dockingIteraion.getDockingTypeKms());

			}
			if (dockingIteraion.getDocking_type().equals("D2")) {
				d2kms = Integer.parseInt(dockingIteraion.getDockingTypeKms());

			}
			if (dockingIteraion.getDocking_type().equals("D3")) {
				d3kms = Integer.parseInt(dockingIteraion.getDockingTypeKms());

			}
			if (dockingIteraion.getDocking_type().equals("D4")) {
				d4kms = Integer.parseInt(dockingIteraion.getDockingTypeKms());

			}

		}
		if (dockType.equals("D1")) {
			finalkms = 0;
		} else if (dockType.equals("D2")) {
			finalkms = d1kms;
		} else if (dockType.equals("D3")) {
			finalkms = d2kms;
		} else if (dockType.equals("D4")) {
			finalkms = d3kms;
		}

		return finalkms;
	}

	@SuppressWarnings("finally")
	public boolean endDocking(int dockingId,String dockingEndDateString){
		
		Session  session = null;
		Transaction transaction = null;
		
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			
			int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			String currDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
			
			DockingHistory dockingHistoryObject = (DockingHistory) session.load(DockingHistory.class,dockingId);
			dockingHistoryObject.setEndTime(common.getDateTimeFromDatePicker(dockingEndDateString));
			dockingHistoryObject.setUpdatedBy(userId);
			dockingHistoryObject.setUpdatedDate(currDate);
			dockingHistoryObject.setStatus("FINISHED");
			
			String updatedVehicleStatus = "update vehicle set status ='ACTIVE' where vehicle_id = '"+dockingHistoryObject.getVehicle().getVehicleId()+"'";
			Query queryObject = session.createSQLQuery(updatedVehicleStatus);
			queryObject.executeUpdate();
			
			transaction.commit();
			isSuccess = true;
			
		}catch(Exception e){
			transaction.rollback();
			isSuccess = false;
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			return isSuccess;
		}
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<DockingHistory> getDockinDetails(int vehicleId) { 
		List<DockingHistory> dockingHistoryDetails = new ArrayList<DockingHistory>();
		DockingAction dockingACtion= new DockingAction();
		Session session = null; try
		{
		  
			session = HibernateUtil.getSession("hibernate.cfg.xml"); Criteria
			criteria = session.createCriteria(DockingHistory.class);
			criteria.createCriteria("vehicle","vehicle", Criteria.LEFT_JOIN);
//		  	criteria.createCriteria("componenetType", Criteria.LEFT_JOIN);
//		  	criteria.createCriteria("subComponenetType", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("vehicle.vehicleId", vehicleId)); 
			criteria.addOrder(Order.desc("docking_id"));
			List<Object>  result = criteria.list();
		  
			int listSize = result.size();
			if (listSize > 0) { 
				for(int i=0; i < listSize; i++){
//					DockingHistory dockingHistory = new DockingHistory();
					DockingHistory  dockingHistory = (DockingHistory) result.get(i);
					dockingHistory.setStartDateString(common.getDateTimeToDatePicker(dockingHistory.getStartTime()));
					dockingHistory.setEndDateString(common.getDateTimeToDatePicker(dockingHistory.getEndTime()));
					if(i==0){
						ServletActionContext.getRequest().getSession().setAttribute("latestDockingObject",dockingHistory);
					}
					dockingHistoryDetails.add(dockingHistory);
				}
			} 
		} catch (Exception e) { 
			  e.printStackTrace();
		}
		finally { 
			  if (session != null) {
				  session.close(); 
			  } 
			  return dockingHistoryDetails; 
		}
	}
}
