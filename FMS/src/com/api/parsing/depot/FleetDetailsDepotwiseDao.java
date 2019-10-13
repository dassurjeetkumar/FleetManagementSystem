package com.api.parsing.depot;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.BodyType;
import com.trimax.its.vehicle.model.MakeType;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.Vehicle;




public class FleetDetailsDepotwiseDao {
	

	public int saveParseData(Vehicle vehicle) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		//FleetDetailsDepotwise fleetDetailsDepot=new FleetDetailsDepotwise();
		//ParsingXml xml=new ParsingXml();

		try {
			//System.out.println("######### vehicle : "+vehicle.toString());
			i = (Integer) session.save(vehicle);
			
			session.getTransaction().commit();
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			if(i>0){
				System.out.println("Vehicle Inserted Successfully : "+i);
			}
			session.close();
		}
		return i;

	}
	
	public int updateParseData(Vehicle vehicle) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 1;
//		session.get(Vehicle.class,vehicle.getVehicleRegistrationNumber());
		
		try {
			
			//System.out.println("######### vehicle : "+vehicle.toString());
			//vehicleObject.setRegistrationDate(vehicle.getRegistrationDate());
			//vehicle.setUpdated_date(new java.util.Date());
			
			session.saveOrUpdate(vehicle);
			session.getTransaction().commit();
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			System.out.println("Vehicle Updated Successfully : "+(i++));
			session.close();
		}
		return i;

	}
	
	public List<MakeType> getMakeTypeList(String makeTypeName) {
		//System.out.println("#####  : "+ makeTypeName);
		List<MakeType> list = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Criteria criteria = session.createCriteria(MakeType.class);			
			criteria.add(Restrictions.eq("make_type_name", makeTypeName));
			criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.add(Restrictions.eq("status", "ACTIVE"));

			list = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println("#####  : "+ list);
		return list;
	}
	
	public List<ModelType> getModelTypeList(String ModelTypeName) {
		//System.out.println("#####  : "+ makeTypeName);
		List<ModelType> list = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Criteria criteria = session.createCriteria(ModelType.class);			
			criteria.add(Restrictions.eq("model_type_name", ModelTypeName));
			criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.add(Restrictions.eq("status", "ACTIVE"));

			list = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println("#####  : "+ list);
		return list;
	}
	public int saveModelTypeData(String ModelType_name) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		//FleetDetailsDepotwise fleetDetailsDepot=new FleetDetailsDepotwise();
		//ParsingXml xml=new ParsingXml();
		ModelType modelType=new ModelType();
		try {
			modelType.setModel_type_name(ModelType_name);
			modelType.setStatus("ACTIVE");
			modelType.setDeletedStatus(0);
			//System.out.println("######### vehicle : "+vehicle.toString());
			i =(Integer) session.save(modelType);
			session.getTransaction().commit();
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			if(i>0){
				System.out.println("Model Type Inserted Successfully : "+i);
			}
			session.close();
			
		}
		return i;

	}
	public boolean checkModelType(String ModelTypeName) {
		boolean flag = false;
		String qry = " select model_type_name from model_type where model_type_name='"
				+ ModelTypeName + "' and deleted_status= " + 0;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	public List<BodyType> getBodyTypeList(String bodyTypeName) {
		//System.out.println("#####  : "+ makeTypeName);
		List<BodyType> list = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Criteria criteria = session.createCriteria(BodyType.class);			
			criteria.add(Restrictions.eq("body_type_name", bodyTypeName));
			
			criteria.add(Restrictions.eq("status", "ACTIVE"));

			list = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			session.close();
		}
		//System.out.println("#####  : "+ list);
		return list;
	}
	
	public int saveBodyTypeData(String body_type_name) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		//FleetDetailsDepotwise fleetDetailsDepot=new FleetDetailsDepotwise();
		//ParsingXml xml=new ParsingXml();
		BodyType bodyType=new BodyType();
		try {
			bodyType.setBody_type_name(body_type_name);
			bodyType.setStatus("ACTIVE");
			
			//System.out.println("######### vehicle : "+vehicle.toString());
			i =(Integer) session.save(bodyType);
			session.getTransaction().commit();
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			if(i>0){
				System.out.println("Body Type Inserted Successfully : "+i);
			}
			session.close();
		}
		return i;

	}
	public boolean checkBodyType(String bodyTypename) {
		boolean flag = false;
		String st="ACTIVE";
		String qry = " select body_type_name from body_type where body_type_name='"
				+ bodyTypename + "' and status='" + st+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

	public int saveMakeTypeData(String make_type_name) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		//FleetDetailsDepotwise fleetDetailsDepot=new FleetDetailsDepotwise();
		//ParsingXml xml=new ParsingXml();
		MakeType makeType=new MakeType();
		try {
			makeType.setMake_type_name(make_type_name);
			makeType.setStatus("ACTIVE");
			makeType.setDeletedStatus(0);
			//System.out.println("######### vehicle : "+vehicle.toString());
			i =(Integer) session.save(makeType);
			session.getTransaction().commit();
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			if(i>0){
				System.out.println("Make Type Inserted Successfully : "+i);
			}
			session.close();
		}
		return i;

	}
	public boolean checkMakeType(String makeTypeName) {
		boolean flag = false;
		String qry = " select make_type_name from make_type where make_type_name='"
				+ makeTypeName + "' and deleted_status= " + 0;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	public Vehicle checkNewVehicle(String vehicleObject) {
		boolean flag = false;
		String qry = " From Vehicle where vehicleRegistrationNumber='"
				+ vehicleObject + "' and deleted_status= " + 0;
		Query query = HibernateUtil.getSession("").createQuery(qry);
		List<Vehicle> vehicles =query.list();
		Vehicle vehicle = null;
		if (vehicles.size()>0) {
		 vehicle = vehicles.get(0);
		}
//		if (query.list().size() > 0) {
//			flag = true;
//		}
		return vehicle;
	}
	public boolean checkVehicleNew(Vehicle vehicleObject, String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query="";
			if (reqType.equals("create")) {
				query = "select count(*)as count from vehicle where license_number='"+ vehicleObject.getVehicleRegistrationNumber().trim() + "' and deleted_status ='0'";
			} else if (reqType.equals("update")) {
				query = "select count(*)as count from vehicle where license_number='"+ vehicleObject.getVehicleRegistrationNumber().trim()	+ "' and vehicle_id not in ('"+ vehicleObject.getVehicleId() + "')  and deleted_status ='0'";
			}
			Query queryObject = session.createSQLQuery(query).addScalar(
					"count", Hibernate.INTEGER);
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
			return isNew;
		}
	}
}
