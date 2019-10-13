package com.trimax.its.vehicle.dao;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleTrasnsfer;

public class UpdateVehicleDetailsDAO {

	public boolean saveEditedVehicleDetails(Vehicle modifiedVehicle)  
	{
		
		Common common = new Common();
		boolean isSuccess = true ;
		Session session = null;


		int i=0;

		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			String currDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
//			Vehicle vehicleObject = (Vehicle) session.get(Vehicle.class, modifiedVehicleObject.getVehicleId());
//			vehicleObject= modifiedVehicleObject;
//			Date dateObject = (Timestamp) new Date();
			Vehicle vehicleObject = (Vehicle) session.get(Vehicle.class,modifiedVehicle.getVehicleId());
			int vehOrg=0;
			//if(modifiedVehicle.getDepotOrUnit().getOrg_chart_id)
			System.out.println("modifiedVehicle.getDepotOrUnit().getOrg_chart_id"+modifiedVehicle.getDepotOrUnit().getOrg_chart_id());
//			if(modifiedVehicle.getDepotOrUnit().getOrg_chart_id()==0){
////				modifiedVehicle.getDepotOrUnit().setOrg_chart_id(0);
//				vehOrg=Integer.parseInt("null");
//			}else{
//				vehOrg=modifiedVehicle.getDepotOrUnit().getOrg_chart_id();
//			}
			if(modifiedVehicle.getDepotOrUnit().getOrg_chart_id()==vehicleObject.getDepotOrUnit().getOrg_chart_id()){
		
			vehicleObject.setVehicleRegistrationNumber(modifiedVehicle.getVehicleRegistrationNumber());
		
			
			vehicleObject.setMakeType(modifiedVehicle.getMakeType().getMake_type_id()!=0?modifiedVehicle.getMakeType():null);
			vehicleObject.setModelType(modifiedVehicle.getModelType().getModel_type_id()!=0?modifiedVehicle.getModelType():null);
			vehicleObject.setBrandType(modifiedVehicle.getBrandType().getBrand_type_id()!=0?modifiedVehicle.getBrandType():null);
			//vehicleObject.setBodyType(modifiedVehicle.getBodyType().getBody_type_id()!=0?modifiedVehicle.getBodyType():null);
			vehicleObject.setVehicleType(modifiedVehicle.getVehicleType().getVehicle_type_id()!=0?modifiedVehicle.getVehicleType():null);
			//vehicleObject.setServiceType(modifiedVehicle.getServiceType().getService_type_id()!=0?modifiedVehicle.getServiceType():null);
			vehicleObject.setVehicleCategory(modifiedVehicle.getVehicleCategory().getVehicleCategoryId()!=0?modifiedVehicle.getVehicleCategory():null);
			vehicleObject.setDepotOrUnit(modifiedVehicle.getDepotOrUnit().getOrg_chart_id()!=0?modifiedVehicle.getDepotOrUnit():null);
		
			vehicleObject.setRegistrationDate(common.getDateFromDatePicker(modifiedVehicle.getRegistrationDateString()));
			//vehicleObject.setFcExpiryDate(common.getDateFromDatePicker(modifiedVehicle.getFcExpiryDateString()));
           if(modifiedVehicle.getFcRenewalDateString()==null || modifiedVehicle.getFcRenewalDateString().equalsIgnoreCase("")){
        	   vehicleObject.setFcRenewalDate(null);
		    }else{
			vehicleObject.setFcRenewalDate(common.getDateFromDatePicker(modifiedVehicle.getFcRenewalDateString()));
		    }
			vehicleObject.setProgressingKMs(modifiedVehicle.getProgressingKMs());
			vehicleObject.setScheduleKMs(modifiedVehicle.getScheduleKMs());
			vehicleObject.setAcAvailability(modifiedVehicle.getAcAvailability());
			vehicleObject.setProceduredDate(common.getDateFromDatePicker(modifiedVehicle.getProcuredDateString()));
			vehicleObject.setOperationalDate(common.getDateFromDatePicker(modifiedVehicle.getOperationalDateString()));
//			vehicleObject.setVehicleUsageCategory(modifiedVehicle.getVehicleUsageCategory());
			vehicleObject.setWheelsCount(modifiedVehicle.getWheelsCount());
			vehicleObject.setChasisNumber(modifiedVehicle.getChasisNumber());
			vehicleObject.setEngineNumber(modifiedVehicle.getEngineNumber());
			vehicleObject.setStatus(modifiedVehicle.getStatus());
			vehicleObject.setUnderWarranty(modifiedVehicle.getUnderWarranty());
	              vehicleObject.setNorm(modifiedVehicle.getNorm());
	              vehicleObject.setFloorrHeigt(modifiedVehicle.getFloorrHeigt());
	              vehicleObject.setHp(modifiedVehicle.getHp());
	              vehicleObject.setSeat_Capacity(modifiedVehicle.getSeat_Capacity());
	              vehicleObject.setUnladenWeight(modifiedVehicle.getUnladenWeight());
	              vehicleObject.setDocking_Kms(modifiedVehicle.getDocking_Kms());
			//vehicleObject.setDockingPlanningDate(common.getDateFromDatePicker(modifiedVehicle.getDockingPlanningDateString()));
			vehicleObject.setUnderWarranty(vehicleObject.getUnderWarranty());
			vehicleObject.setUpdated_date(currDate);
			vehicleObject.setUpdated_by(userId);
			//vehicleObject.setUpdated_by(modifiedVehicle.getUpdated_by());
		
//			session.evict(modifiedVehicle)
		
			if(vehicleObject.getStatus().equalsIgnoreCase("SCRAP")){ 
			
				vehicleObject.setScrappedDate(common.getDateFromDatePicker(modifiedVehicle.getScrappedDateString()));
				/*String queryForScrapUpdate = "update vehicle_scrap set scrap_date = '"+common.getDateFromPicker(modifiedVehicle.getScrappedDateString())+"',updated_by='"+userId+"',updated_date=now() where vehicle_id = '"+vehicleObject.getVehicleId()+"'";
				Query qryForUpdate  = session.createSQLQuery(queryForScrapUpdate);
				qryForUpdate.executeUpdate();
			
				String queryForReleaseDevice = "UPDATE `vehicle_device` set `status` ='INACTIVE',updated_by = '"+userId+"',updated_date = now() WHERE `vehicle_id` = '"+vehicleObject.getVehicleId()+"'";
				Query qryForRelease = session.createSQLQuery(queryForReleaseDevice);
				qryForRelease.executeUpdate();*/
			}
			session.update(vehicleObject);
			}else{/*
				vehicleObject.setUpdated_date(currDate);
				vehicleObject.setUpdated_by(userId);
				vehicleObject.setEffective_end_date(new java.util.Date());
				vehicleObject.setStatus("Transferred");
				vehicleObject.setDeleted_status(0);
				//session.update(employee);
				//System.out.println("hiii");
				session.update(vehicleObject);
				//updatevehdev(vehicleObject.getVehicleId(),modifiedVehicle.getVehicleId());
				modifiedVehicle.setProceduredDate(vehicleObject.getProceduredDate());
			//	modifiedVehicle.setFcExpiryDate(vehicleObject.getFcExpiryDate());
//				 if(modifiedVehicle.getRegistrationDateString()==null || modifiedVehicle.getRegistrationDateString().equalsIgnoreCase("")){
//		        	   vehicleObject.setFcRenewalDate(null);
//				    }
				  modifiedVehicle.setNorm(vehicleObject.getNorm());
	              modifiedVehicle.setFloorrHeigt(vehicleObject.getFloorrHeigt());
	              modifiedVehicle.setSeat_Capacity(vehicleObject.getSeat_Capacity());
	             
	             modifiedVehicle.setHp(vehicleObject.getHp());
	             modifiedVehicle.setUnladenWeight(vehicleObject.getUnladenWeight());
	             modifiedVehicle.setDocking_Kms(vehicleObject.getDocking_Kms());
				
				
				System.out.println("vehicleObject.getFcRenewalDate()=="+vehicleObject.getFcRenewalDate());
				System.out.println("vehicleObject.getFcRenewalDate()=="+vehicleObject.getFcExpiryDate());
				modifiedVehicle.setFcRenewalDate(vehicleObject.getFcRenewalDate());
				modifiedVehicle.setDockingPlanningDate(vehicleObject.getDockingPlanningDate());
				modifiedVehicle.setRegistrationDate(vehicleObject.getRegistrationDate());
				modifiedVehicle.setOperationalDate(vehicleObject.getOperationalDate());
				if(modifiedVehicle.getStatus().equalsIgnoreCase("SCRAP")){ 
					
					modifiedVehicle.setScrappedDate(vehicleObject.getScrappedDate());
//					String queryForScrapUpdate = "update vehicle_scrap set scrap_date = '"+common.getDateFromPicker(modifiedVehicle.getScrappedDateString())+"',updated_by='"+userId+"',updated_date=now() where vehicle_id = '"+vehicleObject.getVehicleId()+"'";
//					Query qryForUpdate  = session.createSQLQuery(queryForScrapUpdate);
//					qryForUpdate.executeUpdate();
//				
//					String queryForReleaseDevice = "UPDATE `vehicle_device` set `status` ='INACTIVE',updated_by = '"+userId+"',updated_date = now() WHERE `vehicle_id` = '"+vehicleObject.getVehicleId()+"'";
//					Query qryForRelease = session.createSQLQuery(queryForReleaseDevice);
//					qryForRelease.executeUpdate();
				}
				
				//System.out.println("modifiedVehicle.getProceduredDate()"+modifiedVehicle.getProceduredDate());
				modifiedVehicle.setCreated_by(userId);
				modifiedVehicle.setCreated_date(currDate);
				modifiedVehicle.setEffective_end_date(null);
				modifiedVehicle.setDeleted_status(0);
				modifiedVehicle.setCause_status("N");
				System.out.println("created successfully");
				i= (Integer) session.save(modifiedVehicle);
				//updatevehdev(vehicleObject.getVehicleId(),i);
			*/  
				 //modify at 21/6/18
				VehicleTrasnsfer vt=new VehicleTrasnsfer();
				vt.setVehicleId(modifiedVehicle.getVehicleId());
				vt.setFromDepotId(vehicleObject.getDepotOrUnit().getOrg_chart_id());
				vt.setToDepotId(modifiedVehicle.getDepotOrUnit().getOrg_chart_id());
				vt.setUpdateBy(userId);
				vt.setUpdatedDate(currDate);
				session.save(vt);
				
				vehicleObject.setDepotOrUnit(modifiedVehicle.getDepotOrUnit());
				vehicleObject.setUpdated_date(currDate);
				vehicleObject.setUpdated_by(userId);
				
				//session.update(employee);
				//System.out.println("hiii");
				session.update(vehicleObject);
				
			
			
			}
			session.getTransaction().commit();
			//session.flush();

           /*  if(i!=0){
			updatevehdev(vehicleObject.getVehicleId(),i);
             }*/

		}
		catch(Exception e){
			e.printStackTrace();
			isSuccess  = false;
			
			session.getTransaction().rollback();
		}
		finally{
			if(session != null)
			{
				session.close();
			}
		}
		return isSuccess;
		
	}
	public void updatevehdev(int old,int newid){
		Session session = null;
		Transaction t=null;
		try{
			int id=Integer.parseInt(ServletActionContext
					.getRequest().getSession().getAttribute("userid")
					.toString());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			t=session.beginTransaction();
			String sql="update vehicle_device set vehicle_id="+newid+",updated_by="+id+",updated_date=now() where vehicle_id="+old+" and status='ACTIVE'";
			SQLQuery qury=session.createSQLQuery(sql);
			qury.executeUpdate();
		t.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
