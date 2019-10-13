package com.trimax.its.vehicle.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.trimax.its.vehicle.model.BodyType;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.MakeType;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.TransferVehicleHistory;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleTrasnsfer;
import com.trimax.its.vehicle.model.VehicleType;

public class TransferVehicleDAO {

	Common common = new Common();
	public List getDepotNames(String... idBusStopName) {
		List<OrganisationChart> organisationList = null;
		Session session = null;
	
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(OrganisationChart.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			//criteria.add(Restrictions.eq("orgType",3));
			//criteria.add(Restrictions.or(Restrictions.eq("orgType", 3), Restrictions.eq("orgType", 10)));
			if (idBusStopName.length > 0) {
				if (!idBusStopName[0].equals("")) {
					criteria.add(Restrictions.like("org_name", "%"+ idBusStopName[0] + "%"));
					//criteria.add(Restrictions.or(lhs, rhs));
					
				}
			}
			criteria.addOrder(Order.asc("org_name"));
			List<Object> det = criteria.list();
			organisationList = new ArrayList<OrganisationChart>();

			OrganisationChart orgType1 = new OrganisationChart();
			orgType1.setOrg_chart_id(0);
			orgType1.setOrg_name("Select");
			organisationList.add(orgType1);

			for (int i = 0; i < det.size(); i++) {

				OrganisationChart organisation = (OrganisationChart) det.get(i);
				//System.out.println("-------"+organisation.getOrgType().getOrg_type_id());
				if(organisation.getOrgType().getOrg_type_id()==3 || organisation.getOrgType().getOrg_type_id()==10 || organisation.getOrgType().getOrg_type_id()==9){
				organisationList.add(organisation);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return organisationList;
		}
	}
	
	@SuppressWarnings("finally")
	public Vehicle getParticularVehicleDetails(String vehicleId) {

		Vehicle vehicle = null;
		Session session = null ;
		try {
			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.flush();
			Query query = session.createQuery("from Vehicle  v left join v.makeType   left join v.bodyType left join v.vehicleType left join v.brandType left join v.modelType left join v.depotOrUnit  WHERE v.vehicleId = '"
							+ vehicleId + "'  ");
			List<Object[]> det = query.list();
			
			// vehicleList = new ArrayList<Vehicle>();

			for (int i = 0; i < det.size(); i++) {
				Object[] result = det.get(i);
				vehicle = (Vehicle) result[0];
				MakeType makeType = (MakeType) result[1];
				BodyType bodyType = (BodyType) result[2];
				VehicleType vehicleType = (VehicleType) result[3];
				BrandType brandType = (BrandType) result[4];
				ModelType modelType = (ModelType) result[5];
				OrganisationChart org = (OrganisationChart) result[6];
				vehicle.setMakeType(makeType);
				vehicle.setBodyType(bodyType);
				vehicle.setVehicleType(vehicleType);
				vehicle.setBrandType(brandType);
				vehicle.setModelType(modelType);
				vehicle.setDepotOrUnit(org);

			}

			String queryForIsVehicleTransfered = "SELECT transfer_vehicle_type	 FROM `transfer_vehicle`  WHERE `vehicle_id` = '"
						+ vehicleId + "' order by transfer_vehicle_id desc";

			Query qry = session.createSQLQuery(queryForIsVehicleTransfered).addScalar("transfer_vehicle_type", Hibernate.STRING);
			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			String result = "";
			List<Map<Object, Object>> aliasToValueMapList = qry.list();
			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<Object, Object> rs = aliasToValueMapList.get(0);
				result = (String) (rs.get("transfer_vehicle_type") != null&& !rs.get("transfer_vehicle_type").equals("") ? rs.get("transfer_vehicle_type") : "");
			}
			if (!result.equals("")) {
				result = result.toLowerCase().contains("perm") ? "perm": "temp";
			}
			ServletActionContext.getRequest().setAttribute("transferTpe",result);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
			return vehicle;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean saveTransferVehicle(TransferVehicleHistory transferVehicleObject, Vehicle vehicleObject) {
		Session session = null;
		Transaction transaction = null;
		boolean isSusuccess = true;
	
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());

			Vehicle vehicle = (Vehicle) session.load(Vehicle.class,	vehicleObject.getVehicleId());
			
			try {
				VehicleTrasnsfer vt=new VehicleTrasnsfer();
				vt.setVehicleId(vehicleObject.getVehicleId());
				vt.setFromDepotId(vehicle.getDepotOrUnit().getOrg_chart_id());
				vt.setToDepotId(transferVehicleObject.getToOrganisationId().getOrg_chart_id());
				vt.setUpdateBy(Integer.parseInt(ServletActionContext
						.getRequest().getSession().getAttribute("userid")
						.toString()));
				vt.setUpdatedDate((new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss")).format(new Date()));
				session.save(vt);
			}catch (Exception e) {
			e.printStackTrace();
			}
			
			vehicle.setDepotOrUnit(transferVehicleObject.getToOrganisationId().getOrg_chart_id() != 0 ? transferVehicleObject.getToOrganisationId() : null);
			vehicle.setUpdated_by(userId);
			vehicle.setUpdated_date((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			session.update(vehicle);

			// Integer id = (Integer) session.save(transferVehicleObject);

			TransferVehicleHistory historyObject = new TransferVehicleHistory();
			historyObject.setTransferVehicleType(transferVehicleObject.getTransferVehicleType());
			historyObject.setFromOrganisationId(transferVehicleObject.getFromOrganisationId().getOrg_chart_id() != 0 ? transferVehicleObject.getFromOrganisationId() : null);
			historyObject.setToOrganisationId(transferVehicleObject.getToOrganisationId().getOrg_chart_id() != 0 ? transferVehicleObject.getToOrganisationId() : null);
			historyObject.setFromDate(common.getDateFromDatePicker(transferVehicleObject.getFromDateString()));
			historyObject.setToDate(transferVehicleObject.getToDateString() != null ? common.getDateFromDatePicker(transferVehicleObject.getToDateString()) : null);
			historyObject.setDescription(!transferVehicleObject.getDescription().trim().equals("") ? transferVehicleObject.getDescription() : null);
			historyObject.setCreatedBy(userId);
			historyObject.setVehicle(vehicleObject);
			Integer id = (Integer) session.save(historyObject);

			session.update(vehicle);
			System.out.println("vehicle transfered success.... having id---->>>"+ id);
	
			session.getTransaction().commit();

		} catch (Exception e) {
			transaction.rollback();
			isSusuccess = false;
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return isSusuccess;
		}

	}
}
