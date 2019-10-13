package com.trimax.its.vehicle.dao;

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
import org.hibernate.SQLQuery;
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
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.model.Device;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.BodyType;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.Fuel;
import com.trimax.its.vehicle.model.MakeType;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vehicle.model.TransferVehicleHistory;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleCategory;
import com.trimax.its.vehicle.model.VehicleDevice;
import com.trimax.its.vehicle.model.VehicleFcRenewal;
import com.trimax.its.vehicle.model.VehicleScrap;
import com.trimax.its.vehicle.model.VehicleTrasnsfer;
import com.trimax.its.vehicle.model.VehicleType;

public class VehicleDAO {
	public static void main(String arsg[]) {
		VehicleDAO daoObject = new VehicleDAO();
		daoObject.getVehicleList();
	}

	Common common = new Common();

	public List getVehicleList() {
		List<Vehicle> vehicleList = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from Vehicle  v left join v.makeType  left join v.bodyType left join v.vehicleType left join v.brandType left join v.modelType left join v.vehicleCategory  order by v.vehicleId");
		query.setMaxResults(50);
		try {

			List<Object[]> det = query.list();
			System.out.println("sis" + det.size());
			vehicleList = new ArrayList<Vehicle>();

			for (int i = 0; i < det.size(); i++) {
				Object[] result = det.get(i);
				Vehicle vehicle = (Vehicle) result[0];
				MakeType makeType = (MakeType) result[1];
				// OrganisationChart orgType = (OrganisationChart) result[2];
				BodyType bodyType = (BodyType) result[2];
				VehicleType vehicleType = (VehicleType) result[3];
				BrandType brandType = (BrandType) result[4];
				ModelType modelType = (ModelType) result[5];
				VehicleCategory vehicleCategory = (VehicleCategory) result[6];
				vehicle.setMakeType(makeType);
				// vehicle.setDepotOrUnit(orgType);
				vehicle.setBodyType(bodyType);
				vehicle.setBrandType(brandType);
				vehicle.setModelType(modelType);
				vehicle.setVehicleCategory(vehicleCategory);
				vehicleList.add(vehicle);

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return vehicleList;
		}
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(Long total, HttpServletRequest request,
			String index, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Session session = null;
		try {
			Long totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria ;
			
			
			int orgtTypeId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			int orgId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			if(viewdeletedrecord.equalsIgnoreCase("Y")){
				 criteria = session.createCriteria(Vehicle.class);
			}else{
				 criteria = session.createCriteria(Vehicle.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}

			
			
			//Criteria criteria = session.createCriteria(Vehicle.class);
			//criteria.add(Restrictions.ne("status", "deleted"));
			String[] dbcol = {"","vehicleId","vehicleRegistrationNumber","org.org_name","org.org_name","fcExpiryDate","progressingKMs","scheduleKMs","vehicle.vehicle_type_name",
					"brand.brand_type_name","acAvailability","make.make_type_name","model.model_type_name","proceduredDate",
					"body.body_type_name","operationalDate","dockingPlanningDate","service.service_type_name","registrationDate","chassisNumber",
					"wheelsCount","category.vehicleCategoryName","underWarranty","status","isScarpped","scrappedDate","created_date","created_by","updated_date","updated_by"};
			String col = dbcol[Integer.parseInt(index)];
			System.out.println("sSearch------->" + col + dir);
			
			
			if (!col.equals("")) {
				if (!dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			} else {
				criteria.addOrder(Order.asc("vehicleId"));
			}
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
				
				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.ilike("vehicleRegistrationNumber", search_term.trim(),MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.ilike("status", search_term.trim(),MatchMode.START));
				criteria.add(conditionGroup);

			}
			
			Junction conditionGroup = Restrictions.disjunction();
			if(orgtTypeId!=0 && orgtTypeId==1){
				
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				conditionGroup.add(Restrictions.eq("org.orgType.org_type_id",orgtTypeId));
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				conditionGroup.add(Restrictions.eq("org.org_chart_id",orgId));
			}
			criteria.add(conditionGroup);
			
			criteria.createCriteria("makeType", "make",Criteria.LEFT_JOIN);
			criteria.createCriteria("bodyType", "body",Criteria.LEFT_JOIN);
			criteria.createCriteria("vehicleType", "vehicle",Criteria.LEFT_JOIN);
			criteria.createCriteria("brandType", "brand",Criteria.LEFT_JOIN);
			criteria.createCriteria("modelType", "model",Criteria.LEFT_JOIN);
			criteria.createCriteria("vehicleCategory","category",Criteria.LEFT_JOIN);
			criteria.createCriteria("serviceType","service",Criteria.LEFT_JOIN);
			criteria.createCriteria("depotOrUnit","org",Criteria.LEFT_JOIN);
			criteria.createCriteria("org.orgType","orgtype",Criteria.LEFT_JOIN);
			criteria.add(Restrictions.ilike("status", "ACTIVE"));
			criteria.add(Restrictions.eq("deleted_status", 0));
//			criteria.add(Restrictions.not(Restrictions.like("status", "SCRAP")));
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request	.getParameter("iDisplayLength")));
			
			List<Vehicle> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getVehicleId()
						+ "' value='"
						+ list.get(i).getVehicleId() + "'/>");
				if(list.get(i).getVehicleId()!=0){
					ja.add(list.get(i).getVehicleId());
				}else{
					ja.add("");
				}
				if(list.get(i).getVehicleRegistrationNumber()!=null){
					ja.add(list.get(i).getVehicleRegistrationNumber());
				}else{
					ja.add("");
				}
								
				if(list.get(i).getDepotOrUnit()!=null){
					//String queryString = "select org_name from org_chart where org_chart_id=(select  parent_id from org_chart  where org_chart_id="+list.get(i).getDepotOrUnit().getOrg_chart_id()+")";
					ja.add(list.get(i).getDepotOrUnit().getOrgType().getOrgType());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getDepotOrUnit()!=null)
				{
					ja.add(list.get(i).getDepotOrUnit().getOrg_name());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getFcExpiryDate()!=null){
					ja.add(common.getDateToPicker(list.get(i).getFcExpiryDate().toString()));
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getProgressingKMs()!=null)
				{
					ja.add(list.get(i).getProgressingKMs());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getScheduleKMs()!=null)
				{
					ja.add(list.get(i).getScheduleKMs());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getVehicleType()!=null){
					ja.add(list.get(i).getVehicleType().getVehicle_type_name());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getBrandType()!=null){
					ja.add(list.get(i).getBrandType().getBrand_type_name());
				}else{
					ja.add(" ");
				}
				if(list.get(i).getAcAvailability()!=null){
					ja.add(list.get(i).getAcAvailability());
				}else{
					ja.add("");
				}
				
				if(list.get(i).getMakeType()!=null){
					ja.add(list.get(i).getMakeType().getMake_type_name());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getModelType()!=null){
					ja.add(list.get(i).getModelType().getModel_type_name());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getProceduredDate()!=null){
					ja.add(common.getDateToPicker(list.get(i).getProceduredDate().toString()));
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getBodyType()!=null)
				{
					ja.add(list.get(i).getBodyType().getBody_type_name());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getOperationalDate()!= null){
					ja.add(common.getDateToPicker(list.get(i).getOperationalDate().toString()));
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getDockingPlanningDate()!= null){
					ja.add(common.getDateToPicker(list.get(i).getDockingPlanningDate().toString()));
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getServiceType()!=null){
					ja.add(list.get(i).getServiceType().getService_type_name());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getRegistrationDate()!=null){
					ja.add(common.getDateToPicker(list.get(i).getRegistrationDate().toString()));
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getChasisNumber()!=null){
					ja.add(list.get(i).getChasisNumber());
				}else{
					ja.add("");
				}
				
				if(list.get(i).getWheelsCount()!=null){
					ja.add(list.get(i).getWheelsCount());
				}else{
					ja.add("");
				}
				
				if(list.get(i).getVehicleCategory()!=null){
					ja.add(list.get(i).getVehicleCategory().getVehicleCategoryName());
				}else{
					ja.add(" ");
				}
				
				if(list.get(i).getUnderWarranty()!=null){
					ja.add(list.get(i).getUnderWarranty());
				}else{
					ja.add("");
				}
				
				if(list.get(i).getStatus()!=null){
					ja.add(list.get(i).getStatus());
				}else{
					ja.add("");
				}
				
				if(list.get(i).getIsScarpped()!=null){
					ja.add(list.get(i).getIsScarpped().toString());
				}
				else{
					ja.add(" ");
				}
				
				if(list.get(i).getScrappedDate()!=null){
					ja.add(common.getDateToPicker(list.get(i).getScrappedDate().toString()));
				}else{
					ja.add("");
				}
				if(list.get(i).getCreated_date()!=null){
					ja.add((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(list.get(i).getCreated_date())));
				}else{
					ja.add("");
				}
				
				if(list.get(i).getCreated_by()==null||list.get(i).getCreated_by()==0){
					ja.add("");
				}else
					ja.add(getUser(session,list.get(i).getCreated_by()));
				
				if(list.get(i).getUpdated_date()!=null){
					ja.add((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(list.get(i).getUpdated_date())));
				}else{
					ja.add("");
				}
				if(list.get(i).getUpdated_by()==null||list.get(i).getUpdated_by()==0){
					ja.add("");
				}else
				{
					ja.add(getUser(session,list.get(i).getUpdated_by()));
				}
				

				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						//ja.add(" ");
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getVehicleId()+"'/>Deleted Record");	

					}else{
						//ja.add(" ");
						//ja.add("Record in Database");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getVehicleId()+"'/>Record in Database1");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				
				
				array.add(ja);
			}
			if (search_term != null && !search_term.equals("")) {
				totalAfterFilter = getTotalRecordsForSeacrch(total,request,col,dir,session);

				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}
			else{
			totalAfterFilter = getTotalRecords();

			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getData1(HttpServletRequest request,String index, String dir, String orgchartid){
	//	int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session1 = HibernateUtil.getSession("");
		try{
			String sql;
			String[] dbcol = {"","vehicle_id","license_number","org_name","org_type","fc_expiry_date","schedule_kms","running_kms","vehicle_type_name",
			"brand_type_name","ac_availability","make_type_name","model_type_name","procured_date",
			"body_type_name","operational_date","docking_planning_date","service_type_name","registration_date","chasis_number","engine_no","norm","floor_height",
			"hp","seat_capacity","docking_kms","unladen_weight",
			"number_of_wheels","vehicle_category_name","under_warranty","status","scrapped","scrapped_date","created_date","createdby","updated_date","userloginname"};
	      String col = dbcol[Integer.parseInt(index)];
			
			sql="select ifnull(v.vehicle_id,'') vehicle_id,ifnull(v.license_number,'') license_number,ifnull(og.org_name,'') org_name" +
					",ifnull(ot.org_type,'') org_type, ifnull(fc_expiry_date,'') fc_expiry_date,ifnull(running_kms,'') running_kms," +
					"ifnull(schedule_kms,'') schedule_kms,ifnull(vt.vehicle_type_name,'') vehicle_type_name,ifnull(bt.brand_type_name,'') brand_type_name," +
					"ifnull(ac_availability,'') ac_availability," +
					"ifnull(mt.make_type_name,'') make_type_name,ifnull(model_type_name,'') model_type_name,ifnull(procured_date,'') procured_date," +
					"ifnull(body_type_name,'') body_type_name,ifnull(operational_date,'') operational_date," +
					"ifnull(docking_planning_date,'') docking_planning_date,ifnull(service_type_name,'') service_type_name,ifnull(registration_date,'') registration_date," +
					"ifnull(chasis_number,'') chasis_number,ifnull(engine_no,'') engine_no,ifnull(norm,'')norm,ifnull(norm,'')norm," +
					"ifnull(floor_height,'')floor_height,ifnull(hp,'')hp,ifnull(seat_capacity,'')seat_capacity," +
					"ifnull(docking_kms,'')docking_kms,ifnull(unladen_weight,'')unladen_weight," +
					"ifnull(number_of_wheels,'') number_of_wheels,ifnull(vehicle_category_name,'') vehicle_category_name,ifnull(under_warranty,'') under_warranty," +
					"ifnull(v.status,'') status,ifnull(scrapped,'') scrapped," +
					"ifnull(scrapped_date,'') scrapped_date,ifnull(v.created_date,'') created_date,ifnull(mum1.userloginname,'') createdby," +
					"ifnull(v.updated_date,'') updated_date,ifnull(mum.userloginname,'') userloginname " +
					"from vehicle v left join org_chart og on v.org_chart_id=og.org_chart_id " +
					"left join org_type ot on ot.org_type_id=og.org_type_id " +
					"left join vehicle_type vt on vt.vehicle_type_id=v.vehicle_type_id " +
					"left join brand_type bt on bt.brand_type_id=v.brand_type_id " +
					"left join make_type mt on mt.make_type_id=v.make_type_id " +
					"left join model_type mti on mti.model_type_id=v.model_type_id " +
					"left join body_type btype on btype.body_type_id=v.body_type_id " +
					"left join service_type st on st.service_type_id=v.service_type_id " +
					"left join vehicle_category vc on vc.vechile_id=v.vehicle_usage_category " +
					"left join menu_user_master mum on mum.user_id=v.updated_by " +
					"left join menu_user_master mum1 on mum1.user_id=v.created_by " +
					"where v.deleted_status=0 and v.status='ACTIVE' and v.cause_status not in('S') "+orgchartid+" ";
			
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
//				String search_term = request.getParameter("sSearch").trim();
				search_term=search_term.trim();
//				sql += " and (schedule_no like '%"+search_term+"%'";
//				sql += " and (vehicle_id like '%"+search_term+"%'";
				sql += " and (license_number like '%"+search_term+"%'";
				sql += " OR chasis_number like '%"+search_term+"%'";
				sql += " OR org_name like '%"+search_term+"%')";
				//sql += " OR org_type like '%"+search_term+"%') ";
			}
			sql+=" group by v.vehicle_id";
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			
		  // System.out.println("query........."+sql);
		  	 Query query = session1.createSQLQuery(sql)
		  			.addScalar("vehicle_id", Hibernate.INTEGER)
						 .addScalar("license_number", Hibernate.STRING)
						 .addScalar("org_name", Hibernate.STRING)
						 .addScalar("org_type", Hibernate.STRING)
						 .addScalar("fc_expiry_date", Hibernate.STRING)
		  	 			 .addScalar("running_kms",Hibernate.STRING)
		  	 			 .addScalar("schedule_kms",Hibernate.STRING)
		  	 			  .addScalar("vehicle_type_name", Hibernate.STRING)
						 .addScalar("brand_type_name", Hibernate.STRING)
		  	 			 .addScalar("ac_availability",Hibernate.STRING)
		  	 			 .addScalar("make_type_name",Hibernate.STRING)
		  	 			  .addScalar("model_type_name", Hibernate.STRING)
						 .addScalar("procured_date", Hibernate.STRING)
		  	 			 .addScalar("body_type_name",Hibernate.STRING)
		  	 			 .addScalar("operational_date",Hibernate.STRING)
		  	 			  .addScalar("docking_planning_date", Hibernate.STRING)
						 .addScalar("service_type_name", Hibernate.STRING)
		  	 			 .addScalar("registration_date",Hibernate.STRING)
		  	 			 .addScalar("chasis_number",Hibernate.STRING)
		  	 			 	 .addScalar("engine_no",Hibernate.STRING)
		  	 			 	 .addScalar("norm",Hibernate.STRING)
		  	 			 	 .addScalar("floor_height",Hibernate.STRING)
		  	 			 	 .addScalar("hp",Hibernate.STRING)
		  	 			 	 .addScalar("seat_capacity",Hibernate.STRING)
		  	 			 	 .addScalar("docking_kms",Hibernate.STRING)
		  	 			 	  .addScalar("unladen_weight",Hibernate.STRING)
		  	 			  .addScalar("number_of_wheels",Hibernate.STRING)
		  	 			   .addScalar("vehicle_category_name",Hibernate.STRING)
		  	 			    .addScalar("under_warranty",Hibernate.STRING)
		  	 			     .addScalar("status",Hibernate.STRING)
		  	 			     .addScalar("scrapped",Hibernate.STRING)
		  	 			     .addScalar("scrapped_date",Hibernate.STRING)
		  	 			     .addScalar("created_date",Hibernate.STRING)
		  	 			      .addScalar("createdby",Hibernate.STRING)
		  	 			       .addScalar("updated_date",Hibernate.STRING)
		  	 			        .addScalar("userloginname",Hibernate.STRING);
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					//int j=i+1;
					JSONArray ja = new JSONArray();
					ja.add("<input type='checkbox' class='group-checkable' id='"+ rs.get("vehicle_id")+ "' value='"+ rs.get("vehicle_id") + "'/>");
					/*data-set='#sample_2 .checkboxes' */
//					ja.add(rs.get("schedule_id"));
					//System.out.println(rs.get("vehicle_id").toString());
					ja.add(rs.get("vehicle_id").toString());
					ja.add(rs.get("license_number").toString());
					ja.add(rs.get("org_type").toString());
					ja.add(rs.get("org_name").toString());
					if(rs.get("fc_expiry_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("fc_expiry_date").toString()));
					}
					ja.add(rs.get("running_kms").toString());
					ja.add(rs.get("schedule_kms").toString());
					ja.add(rs.get("vehicle_type_name").toString());
					ja.add(rs.get("brand_type_name").toString());
					ja.add(rs.get("ac_availability").toString());
					ja.add(rs.get("make_type_name").toString());
					ja.add(rs.get("model_type_name").toString());
					if(rs.get("procured_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("procured_date").toString()));
					}
					ja.add(rs.get("body_type_name").toString());
					if(rs.get("operational_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("operational_date").toString()));
					}
					if(rs.get("docking_planning_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("docking_planning_date").toString()));
					}
					ja.add(rs.get("service_type_name").toString());
					if(rs.get("registration_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("registration_date").toString()));
					}
					ja.add(rs.get("chasis_number").toString());
					ja.add(rs.get("engine_no").toString());
					ja.add(rs.get("norm").toString());
					ja.add(rs.get("floor_height").toString());
					ja.add(rs.get("hp").toString());
					ja.add(rs.get("seat_capacity").toString());
					ja.add(rs.get("docking_kms").toString());
					ja.add(rs.get("unladen_weight").toString());
					ja.add(rs.get("number_of_wheels").toString());
					ja.add(rs.get("vehicle_category_name").toString());
					ja.add(rs.get("under_warranty").toString());
					ja.add(rs.get("status").toString());
					ja.add(rs.get("scrapped").toString());
					if(rs.get("scrapped_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("scrapped_date").toString()));
					}
					if(rs.get("created_date").toString().equalsIgnoreCase("") || rs.get("created_date").toString() == null){
						ja.add("");	
					}else{
						ja.add((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(rs.get("created_date").toString())));
					}
					
					ja.add(rs.get("createdby").toString());
					if(rs.get("updated_date").toString().equalsIgnoreCase("") || rs.get("updated_date").toString() == null ){
						ja.add("");
					}else{
					ja.add((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(rs.get("updated_date").toString())));
					}
					ja.add(rs.get("userloginname").toString());
					
					
//					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				int cnt=0;
				//totalAfterFilter = aliasToValueMapList.size();
				///result.put("aaData", array);
				//String search_term2= request.getParameter("sSearch");
				
				result.put("aaData", array);

				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalVehicleRecords(request,col,dir,orgchartid);//getTotalRecordsForCount(search_term3);
		
			result.put("iTotalRecords",cnt);
			
			result.put("iTotalDisplayRecords", cnt);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session1 != null) {
				session1.close();
			}
		}
		return result;
	}
	
	
	public int getTotalVehicleRecords(HttpServletRequest request,String col,String dir, String orgchartid) {
		int cnt=0;
		//Session session = HibernateUtilVtu.getSession("");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	 
		try{
			String sql="";
			sql = "select ifnull(v.vehicle_id,'') vehicle_id,ifnull(v.license_number,'') license_number,ifnull(og.org_name,'') org_name, " +
//					",ifnull(ot.org_type,'') org_type, ifnull(fc_expiry_date,'') fc_expiry_date,ifnull(running_kms,'') running_kms," +
//					"ifnull(schedule_kms,'') schedule_kms,ifnull(vt.vehicle_type_name,'') vehicle_type_name,ifnull(bt.brand_type_name,'') brand_type_name," +
//					"ifnull(ac_availability,'') ac_availability," +
//					"ifnull(mt.make_type_name,'') make_type_name,ifnull(model_type_name,'') model_type_name,ifnull(procured_date,'') procured_date," +
//					"ifnull(body_type_name,'') body_type_name,ifnull(operational_date,'') operational_date," +
//					"ifnull(docking_planning_date,'') docking_planning_date,ifnull(service_type_name,'') service_type_name,ifnull(registration_date,'') registration_date," +
//					"ifnull(chasis_number,'') chasis_number," +
//					"ifnull(number_of_wheels,'') number_of_wheels,ifnull(vehicle_category_name,'') vehicle_category_name,ifnull(under_warranty,'') under_warranty," +
//					"ifnull(v.status,'') status,ifnull(scrapped,'') scrapped," +
				"ifnull(scrapped_date,'') scrapped_date,ifnull(v.created_date,'') created_date,ifnull(mum1.userloginname,'') createdby," +
					"ifnull(v.updated_date,'') updated_date,ifnull(mum.userloginname,'') userloginname " +
					"from vehicle v left join org_chart og on v.org_chart_id=og.org_chart_id " +
					"left join org_type ot on ot.org_type_id=og.org_type_id " +
					"left join vehicle_type vt on vt.vehicle_type_id=v.vehicle_type_id " +
					"left join brand_type bt on bt.brand_type_id=v.brand_type_id " +
					"left join make_type mt on mt.make_type_id=v.make_type_id " +
					"left join model_type mti on mti.model_type_id=v.model_type_id " +
					"left join body_type btype on btype.body_type_id=v.body_type_id " +
					"left join service_type st on st.service_type_id=v.service_type_id " +
					"left join vehicle_category vc on vc.vechile_id=v.vehicle_usage_category " +
					"left join menu_user_master mum on mum.user_id=v.updated_by " +
					"left join menu_user_master mum1 on mum1.user_id=v.created_by " +
					"where v.deleted_status=0 and v.status='ACTIVE'  and v.cause_status !='S' "+orgchartid+" ";
			
			
			String search_term1 = request.getParameter("sSearch");
			////System.out.println("search_term-------"+search_term1);
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
//				sql += " and (vehicle_id like '%"+search_term+"%'";
				sql += " and (license_number like '%"+search_term+"%'";
				sql += " OR chasis_number like '%"+search_term+"%'";
				sql += " OR org_name like '%"+search_term+"%')";
				
			}
			
			if(!col.equals("")){
				if(dir.equals("asc")){
					if(col.equals("created_date")){
						 sql += " order by v."+col+" asc";
					}else{
				  sql += " order by "+col+" asc";
					}
				}else{
					if(col.equals("created_date")){
						 sql += " order by v."+col+" asc";
					}else{
					sql += " order by "+col+" desc";
					}
					}
			}
			
//			if(!col.equals("")){
//				 if(dir.equals("asc")){
//					if(col.equals("TOKEN")){
//						sql += " order by CAST("+col+" AS UNSIGNED) asc";
//					}else
//				  sql += " order by "+col+" asc";
//				}else{
//					if(col.equals("TOKEN")){
//						sql += " order by CAST("+col+" AS UNSIGNED) desc";
//					}
//					else
//					sql += " order by "+col+" desc";
//				}
//			}
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			cnt=aliasToValueMapList.size();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return cnt;
	
}
	public Long getTotalRecordsForSeacrch(Long total, HttpServletRequest request,String cols, String dir,Session session) {

		Long cnt = 0l;
		try {
			String search_term = request.getParameter("sSearch");
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y")){
				 criteria = session.createCriteria(Vehicle.class);
			}else{
				 criteria = session.createCriteria(Vehicle.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			
			if (search_term != null && !search_term.equals("")) {
				
				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.ilike("vehicleRegistrationNumber", search_term.trim(),MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.ilike("status", search_term.trim(),MatchMode.START));
				criteria.add(conditionGroup);

			}
			
			int orgtTypeId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			int orgId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			Junction conditionGroup = Restrictions.disjunction();
			if(orgtTypeId!=0 && orgtTypeId==1){
				
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				conditionGroup.add(Restrictions.eq("org.orgType.org_type_id",orgtTypeId));
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				conditionGroup.add(Restrictions.eq("org.org_chart_id",orgId));
			}
			criteria.add(conditionGroup);
			
			criteria.createCriteria("makeType", "make",Criteria.LEFT_JOIN);
			criteria.createCriteria("bodyType", "body",Criteria.LEFT_JOIN);
			criteria.createCriteria("vehicleType", "vehicle",Criteria.LEFT_JOIN);
			criteria.createCriteria("brandType", "brand",Criteria.LEFT_JOIN);
			criteria.createCriteria("modelType", "model",Criteria.LEFT_JOIN);
			criteria.createCriteria("vehicleCategory","category",Criteria.LEFT_JOIN);
			criteria.createCriteria("serviceType","service",Criteria.LEFT_JOIN);
			criteria.createCriteria("depotOrUnit","org",Criteria.LEFT_JOIN);
			criteria.createCriteria("org.orgType","orgtype",Criteria.LEFT_JOIN);
			criteria.add(Restrictions.ilike("status", "ACTIVE"));
			criteria.add(Restrictions.eq("deleted_status", 0));
//			criteria.add(Restrictions.not(Restrictions.like("status", "SCRAP")));
			
			/*criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request	.getParameter("iDisplayLength")));
*/
			
			List<Vehicle> list = criteria.list();
			cnt = (long) list.size();
		} catch (Exception e) {
		}
		return cnt;
	}
	public Long getTotalRecords() {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String hqlQuery =  " select count(*)  from Vehicle  v  left join v.makeType  left join v.bodyType left join v.vehicleType " +
					" left join v.brandType left join v.modelType left join v.vehicleCategory left join v.serviceType " +
					" left join v.depotOrUnit org left join org.orgType where v.deleted_status ='0' and v.status='Active' ";
		
		int orgtTypeId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		int orgId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
		
		if(orgtTypeId!=0 && orgtTypeId==1){
			
		}else if(orgtTypeId!=0 && orgtTypeId==2){
			hqlQuery += " AND v.depotOrUnit.orgType.org_type_id='"+orgtTypeId+"'";
		}
		else if(orgtTypeId!=0 && orgtTypeId==3){
			hqlQuery += " AND v.depotOrUnit.org_chart_id='"+orgId+"'";
		}
		
		Query query = session
				.createQuery(hqlQuery);
		List<Object> list = query.list();
		
		Long cnt = (Long) list.get(0);

		return cnt;

	}

	

	public String getUser(Session session, int userId) {
		String userName = "";
		try {
			String query = "SELECT userloginname FROM menu_user_master  WHERE user_id = '"+userId+"'";
			userName = getDBResultStr(session, query, "userloginname");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}

	public String getDBResultStr(Session objSession, String sql,
			String selectName) {
		String result = "";

		try {
			Query query = objSession.createSQLQuery(sql).addScalar(selectName,
					Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, String>> aliasToValueMapList = query.list();

			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<String, String> rs = aliasToValueMapList.get(0);
				result = (rs.get(selectName) != null && !rs.get(selectName).equals("") ? rs.get(selectName): "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getTransferHistoryData(int total,
			HttpServletRequest request, String col, String dir) {
		JSONObject result = new JSONObject();
		Session session = null;
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session
					.createCriteria(TransferVehicleHistory.class);
			criteria.createCriteria("fromOrganisationId", "fromorg");
			criteria.createCriteria("toOrganisationId", "toorg");
			criteria.createCriteria("vehicle", "veh");
			criteria.add(Restrictions.ne("status", "deleted"));
			System.out.println("sSearch------->" + col + dir);
			if (!col.equals("")) {
				if (!dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			String search_term = request.getParameter("sSearch").trim();
			if (search_term != null && !search_term.equals("")) {

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.ilike("transfer_vehicle_type",
						search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions
						.sqlRestriction("vehicleId LIKE '%" + search_term
								+ "%' "));
				conditionGroup.add(Restrictions.ilike("fromorg.org_name",
						search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.ilike("toorg.org_name",
						search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.ilike("from_date", search_term,
						MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.ilike("to_date", search_term,
						MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.ilike("description",
						search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.ilike("status", search_term,
						MatchMode.ANYWHERE));
				criteria.add(conditionGroup);
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			criteria.add(Restrictions.eq(
					"veh.vehicleId",
					Integer.parseInt(ServletActionContext.getRequest()
							.getSession().getAttribute("editVehicleId")
							.toString())));

			List<TransferVehicleHistory> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("");
				ja.add(list.get(i).getTransferVehicleType());
				ja.add(list.get(i).getVehicle().getVehicleId());
				ja.add(list.get(i).getFromOrganisationId().getOrg_name());
				ja.add(list.get(i).getToOrganisationId().getOrg_name());
				ja.add(common.getDateToDatePicker(list.get(i).getFromDate()));
				ja.add(common.getDateToDatePicker(list.get(i).getToDate()));
				ja.add(list.get(i).getDescription());
				ja.add(list.get(i).getStatus());
				array.add(ja);
			}

			totalAfterFilter = getTotalTransferVehicleHistoryRecords();

			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return result;
		}

	}

	@SuppressWarnings("unchecked")
	public JSONObject getVehicleFCRenewalData(int total,
			HttpServletRequest request, String col, String dir) {
		JSONObject result = new JSONObject();

		Session session = null;
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(VehicleFcRenewal.class);
			criteria.createCriteria("vehicle", "v");
			if (!col.equals("")) {
				if (!dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			String search_term = request.getParameter("sSearch").trim();
			if (search_term != null && !search_term.equals("")) {

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions
						.sqlRestriction("vehicle_fc_renewal_id LIKE '%"
								+ search_term + "%' "));
				conditionGroup.add(Restrictions
						.sqlRestriction("vehicleId LIKE '%" + search_term
								+ "%' "));
				conditionGroup.add(Restrictions.ilike("fc_expiry_date",
						search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.ilike("fc_renewal_date",
						search_term, MatchMode.ANYWHERE));

				criteria.add(conditionGroup);
			}

			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			criteria.add(Restrictions.eq(
					"v.vehicleId",
					Integer.parseInt(ServletActionContext.getRequest()
							.getSession().getAttribute("editVehicleId")
							.toString())));

			List<VehicleFcRenewal> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getVehicle().getVehicleId()
						+ "' value='"
						+ list.get(i).getVehicle().getVehicleId() + "'/>");
				ja.add(list.get(i).getVehicle_fc_renewal_id());
				ja.add(list.get(i).getVehicle().getVehicleId());
				ja.add(common
						.getDateToDatePicker(list.get(i).getFcExpiryDate()));
				ja.add(common.getDateToDatePicker(list.get(i)
						.getFcRenewalDate()));
				array.add(ja);
			}

			totalAfterFilter = getTotalFCRenewalRecords();

			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return result;
		}

	}

//	@SuppressWarnings("unchecked")
//	public JSONObject getFuelHistoryData(int total, HttpServletRequest request,
//			String col, String dir) {
//		JSONObject result = new JSONObject();
//
//		Session session = null;
//		try {
//			int totalAfterFilter = total;
//			session = HibernateUtil.getSession("hibernate.cfg.xml");
//			Criteria criteria = session.createCriteria(Fuel.class);
//			criteria.createCriteria("vehicle", "v");
//			criteria.createCriteria("filledByUser", "u");
//			if (!col.equals("")) {
//				if (!dir.equals("asc")) {
//					criteria.addOrder(Order.asc(col));
//				} else {
//					criteria.addOrder(Order.desc(col));
//				}
//			}
//			String search_term = request.getParameter("sSearch");
//			if (search_term != null && !search_term.equals("")) {
//				Junction conditionGroup = Restrictions.disjunction();
//				conditionGroup
//						.add(Restrictions.sqlRestriction("fuel_id LIKE '%"
//								+ search_term + "%' "));
//				conditionGroup.add(Restrictions
//						.sqlRestriction("vehicleId LIKE '%" + search_term
//								+ "%' "));
//				conditionGroup.add(Restrictions.ilike("kms", search_term,
//						MatchMode.ANYWHERE));
//				conditionGroup.add(Restrictions.ilike("litres", "%"
//						+ search_term + "%"));
//				conditionGroup.add(Restrictions.ilike("date", "%" + search_term
//						+ "%"));
//				conditionGroup.add(Restrictions.ilike("fuel_type", "%"
//						+ search_term + "%"));
//				conditionGroup.add(Restrictions
//						.sqlRestriction("user_name LIKE '%" + search_term
//								+ "%' "));
//
//				criteria.add(conditionGroup);
//			}
//
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
//			List<Fuel> list = criteria.list();
//			JSONArray array = new JSONArray();
//			for (int i = 0; i < list.size(); i++) {
//
//				JSONArray ja = new JSONArray();
//				/*
//				 * ja.add(
//				 * "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
//				 * + list.get(i).getVehicle().getVehicleId() + "' value='" +
//				 * list.get(i).getVehicle().getVehicleId() + "'/>");
//				 */
//				ja.add(list.get(i).getFuel_id());
//				ja.add(list.get(i).getVehicle().getVehicleId());
//				ja.add(list.get(i).getKms());
//				ja.add(list.get(i).getLitres());
//				ja.add(common.getDateToPicker(list.get(i).getDate()));
//				ja.add(list.get(i).getFuel_type());
//				ja.add(list.get(i).getFilledByUser().getUser_name());
//				array.add(ja);
//			}
//
//			totalAfterFilter = getTotalFuelRecords();
//
//			result.put("aaData", array);
//			result.put("iTotalRecords", total);
//			result.put("iTotalDisplayRecords", totalAfterFilter);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//			return result;
//		}
//	}

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
				//System.out.println("vehicle related"+vehicle);
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

			Query qry = session.createSQLQuery(queryForIsVehicleTransfered)
					.addScalar("transfer_vehicle_type", Hibernate.STRING);
			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			String result = "";
			List<Map<Object, Object>> aliasToValueMapList = qry.list();

			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<Object, Object> rs = aliasToValueMapList.get(0);
				result = (String) (rs.get("transfer_vehicle_type") != null
						&& !rs.get("transfer_vehicle_type").equals("") ? rs
						.get("transfer_vehicle_type") : "");
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

	public int getTotalTransferVehicleHistoryRecords() {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		Query query = session.createSQLQuery(
				"select count(*) as count from transfer_vehicle where vehicle_id='"
						+ ServletActionContext.getRequest().getSession().getAttribute("editVehicleId") + "'")
				.addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);

		return cnt;
	}

	public int getTotalFCRenewalRecords() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery("select count(*) as count from `vehicle_fc_renewal` where vehicle_id='"
						+ ServletActionContext.getRequest().getSession().getAttribute("editVehicleId") + "' ")
				.addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		return cnt;
	}

	public int getTotalFuelRecords() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery("select count(*) as count from `fuel`").addScalar("count",Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		return cnt;
	}



	
	
	  
//	  }
	 

	/*
	 * public boolean updateDockingDetails(DockingHistory dockingHistoryObject)
	 * { Session session = null; Transaction transaction = null; boolean
	 * isSuccess = true; try {
	 * 
	 * session = HibernateUtil.getSession("hibenate.cfg.xml");
	 * 
	 * DockingHistory dockingHistory = (DockingHistory) session.get(
	 * DockingHistory.class, dockingHistoryObject.getDocking_id());
	 * 
	 * dockingHistory.setStart_time(dockingHistoryObject.getStart_time());
	 * dockingHistory.setEnd_time(dockingHistoryObject.getEnd_time());
	 * dockingHistory .setDockingType(dockingHistoryObject.getDockingType());
	 * dockingHistory.setFip_change(dockingHistoryObject.getFip_change());
	 * dockingHistory.setEoc_change(dockingHistoryObject.getEoc_change());
	 * dockingHistory.setComponenetType(dockingHistoryObject
	 * .getComponenetType());
	 * dockingHistory.setDocking_batch_name(dockingHistoryObject
	 * .getDocking_batch_name());
	 * dockingHistory.setUpdatedBy(Integer.parseInt(ServletActionContext
	 * .getRequest().getSession().getAttribute("userid") .toString()));
	 * dockingHistory.setUpdatedDate((new SimpleDateFormat(
	 * "yyyy-MM-dd HH:mm:ss")).format(new Date())); session.beginTransaction();
	 * session.update(dockingHistory);
	 * 
	 * session.getTransaction().commit();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); isSuccess = false; } finally
	 * { if (session != null) { session.close(); } return isSuccess; }
	 * 
	 * }
	 */

//	public List getFuelFilledUserList() {
//		List fuelFilledUsersList = null;
//		Session session = null;
//		Query query = null;
//		List<Map<String, Object>> aliasToValueMapList = null;
//		try {
//			session = HibernateUtil.getSession("hibernate.cfg.xml");
//			query = session
//					.createSQLQuery("SELECT `user_name`,user_id  FROM `user` order by `user_name`");
//			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			aliasToValueMapList = query.list();
//
//			if (aliasToValueMapList.size() > 0) {
//				fuelFilledUsersList = new ArrayList();
//				for (int i = 0; i < aliasToValueMapList.size(); i++) {
//
//					Map<String, Object> resultSet = aliasToValueMapList.get(i);
//					VehicleModel form = new VehicleModel();
//					form.setFuelFilledUserId(Integer.parseInt(resultSet.get(
//							"user_id").toString()));
//					form.setFuelFilledUser(resultSet.get("user_name")
//							.toString());
//
//					fuelFilledUsersList.add(form);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//			return fuelFilledUsersList;
//		}
//	}

	/*
	 * public TransferVehicleHistory getTransferDetails(String vehicleId) {
	 * TransferVehicleHistory transferObject = new TransferVehicleHistory();
	 * 
	 * Session session = HibernateUtil.getSession("hibernate.cfg.xml"); Query
	 * query = session .createQuery(
	 * "from TransferVehicleHistory  h join  h.fromOrganisationId join h.toOrganisationId where vehicle_id=?"
	 * );
	 * 
	 * query.setParameter(0, vehicleId); try {
	 * 
	 * List<Object[]> det = query.list(); System.out.println("sis" +
	 * det.size());
	 * 
	 * 
	 * if(det.size()>0) { Object[] result = det.get(0);
	 * 
	 * TransferVehicleHistory transferHistory = (TransferVehicleHistory)
	 * result[0]; OrganisationChart fromOrganisation = (OrganisationChart)
	 * result[1]; OrganisationChart toOrganisation = (OrganisationChart)
	 * result[2];
	 * 
	 * transferObject.setFromOrganisationId(fromOrganisation);
	 * transferObject.setToOrganisationId(toOrganisation);
	 * 
	 * 
	 * } } catch (Exception e) { e.printStackTrace();
	 * 
	 * } finally { if (session != null) { session.close(); } return
	 * transferObject; } }
	 */

	public List getTransferHistory() {
		List<TransferVehicleHistory> vehicleTrnasferHistoryList = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from TransferVehicleHistory  h join  h.fromOrganisationId join h.toOrganisationId join h.vehicle ");

		try {

			List<Object[]> det = query.list();
			System.out.println("sis" + det.size());
			vehicleTrnasferHistoryList = new ArrayList<TransferVehicleHistory>();

			for (int i = 0; i < det.size(); i++) {
				Object[] result = det.get(i);

				TransferVehicleHistory transferHistory = (TransferVehicleHistory) result[0];
				OrganisationChart fromOrganisation = (OrganisationChart) result[1];
				OrganisationChart toOrganisation = (OrganisationChart) result[2];
				Vehicle vehicle = (Vehicle) result[3];

				transferHistory.setFromOrganisationId(fromOrganisation);
				transferHistory.setToOrganisationId(toOrganisation);
				transferHistory.setVehicle(vehicle);
				vehicleTrnasferHistoryList.add(transferHistory);

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return vehicleTrnasferHistoryList;
		}
	}

	public List getVehicleFcRenewalHistory() {
		List<VehicleFcRenewal> vehicleFcRenewalHistoryList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from VehicleFcRenewal vfc join vfc.vehicle ");

			List<Object[]> det = query.list();
			System.out.println("sis" + det.size());
			vehicleFcRenewalHistoryList = new ArrayList<VehicleFcRenewal>();

			for (int i = 0; i < det.size(); i++) {
				Object object[] = det.get(i);
				VehicleFcRenewal vehicleFenewal = (VehicleFcRenewal) object[0];
				// VehicleFcRenewal renewalHistory = (VehicleFcRenewal) ;
				Vehicle vehicle = (Vehicle) object[1];
				vehicleFenewal.setVehicle(vehicle);
				vehicleFcRenewalHistoryList.add(vehicleFenewal);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return vehicleFcRenewalHistoryList;
		}

	}

	/*
	 * public List getDockingHistory() { List<DockingHistory>
	 * vehicleDockingHistoryList = null; Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml"); Query query = session
	 * .createQuery(
	 * "from DockingHistory  d join d.dockingType join d.vehicle v where v.vehicleId='"
	 * + ServletActionContext.getRequest().getSession()
	 * .getAttribute("editVehicleId") + "'");
	 * 
	 * try {
	 * 
	 * List<Object[]> det = query.list(); System.out.println("sis" +
	 * det.size()); vehicleDockingHistoryList = new ArrayList<DockingHistory>();
	 * 
	 * for (int i = 0; i < det.size(); i++) { Object[] result = det.get(i);
	 * 
	 * DockingHistory dockingHistory = (DockingHistory) result[0]; DockingType
	 * dockingType = (DockingType) result[1];
	 * dockingHistory.setDockingType(dockingType); dockingHistory
	 * .setStart_time(common .getDateTimeFromDBToList(dockingHistory
	 * .getStart_time())); dockingHistory.setEnd_time(common
	 * .getDateTimeFromDBToList(dockingHistory.getEnd_time()));
	 * vehicleDockingHistoryList.add(dockingHistory);
	 * 
	 * } } catch (Exception e) { e.printStackTrace();
	 * 
	 * } finally { if (session != null) { session.close(); } return
	 * vehicleDockingHistoryList; } }
	 */
	public List getDepotNames(String... idBusStopName) {
		List<OrganisationChart> organisationList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(OrganisationChart.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			if (idBusStopName.length > 0) {
				if (!idBusStopName[0].equals("")) {
					criteria.add(Restrictions.like("org_name", "%"+ idBusStopName[0] + "%"));
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
				organisationList.add(organisation);
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

	public List<VehicleType> getVehicleTypes(String... vehicleTypeStartWith) {
		List<VehicleType> vehicleTypeList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");

			Criteria criteria = session.createCriteria(VehicleType.class);
			criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.add(Restrictions.like("status", "ACTIVE"));

			if (vehicleTypeStartWith.length > 0) {
				if (!vehicleTypeStartWith[0].equals("")) {
					criteria.add(Restrictions.like("vehicle_type_name", "%" + vehicleTypeStartWith[0] + "%"));
				}
			}
			criteria.addOrder(Order.asc("vehicle_type_name"));
			List<Object> det = criteria.list();
			vehicleTypeList = new ArrayList<VehicleType>();

			VehicleType vehicleType1 = new VehicleType();
			vehicleType1.setVehicle_type_id(0);
			vehicleType1.setVehicle_type_name("Select");
			vehicleTypeList.add(vehicleType1);

			for (int i = 0; i < det.size(); i++) {

				VehicleType vehicleType = (VehicleType) det.get(i);
				vehicleTypeList.add(vehicleType);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return vehicleTypeList;
		}
	}

	public List<BrandType> getBrandTypes(String... brnadTypeNameStartWith) {
		List<BrandType> brandTypeList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");

			Criteria criteria = session.createCriteria(BrandType.class);
			criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.add(Restrictions.like("status", "ACTIVE"));
			if (brnadTypeNameStartWith.length > 0) {
				if (!brnadTypeNameStartWith[0].equals("")) {
					criteria.add(Restrictions.like("brand_type_name", "%" + brnadTypeNameStartWith[0] + "%"));
				}
			}
			criteria.addOrder(Order.asc("brand_type_name"));
			List<Object> det = criteria.list();

			brandTypeList = new ArrayList<BrandType>();

			BrandType brandType1 = new BrandType();
			brandType1.setBrand_type_id(0);
			brandType1.setBrand_type_name("Select");
			brandTypeList.add(brandType1);

			for (int i = 0; i < det.size(); i++) {

				BrandType brandType = (BrandType) det.get(i);
				brandTypeList.add(brandType);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return brandTypeList;
		}
	}

	public List<MakeType> getMakeTypes(String... makeTypeNameStartWith) {
		List<MakeType> makeTypeList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(MakeType.class);
			criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.add(Restrictions.like("status", "ACTIVE"));
			if (makeTypeNameStartWith.length > 0) {
				if (!makeTypeNameStartWith[0].equals("")) {
					criteria.add(Restrictions.like("make_type_name", "%"+ makeTypeNameStartWith[0] + "%"));
				}
			}
			criteria.addOrder(Order.asc("make_type_name"));
			List<Object> det = criteria.list();
			makeTypeList = new ArrayList<MakeType>();

			MakeType makeType1 = new MakeType();
			makeType1.setMake_type_id(0);
			makeType1.setMake_type_name("Select");
			makeTypeList.add(makeType1);

			for (int i = 0; i < det.size(); i++) {

				MakeType makeType = (MakeType) det.get(i);
				makeTypeList.add(makeType);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return makeTypeList;
		}
	}

	public List<ModelType> getModelTypes(String... modelTypeNameStartWith) {
		List<ModelType> modelTypeList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(ModelType.class);
			criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.add(Restrictions.like("status", "ACTIVE"));
			if (modelTypeNameStartWith.length > 0) {
				if (!modelTypeNameStartWith[0].equals("")) {
					criteria.add(Restrictions.like("model_type_name", "%"+ modelTypeNameStartWith[0] + "%"));

				}
			}
			criteria.addOrder(Order.asc("model_type_name"));
			List<Object> det = criteria.list();
			modelTypeList = new ArrayList<ModelType>();

			ModelType modelType1 = new ModelType();
			modelType1.setModel_type_id(0);
			modelType1.setModel_type_name("select");
			modelTypeList.add(modelType1);

			for (int i = 0; i < det.size(); i++) {

				ModelType modelType = (ModelType) det.get(i);
				modelTypeList.add(modelType);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return modelTypeList;
		}
	}

	public List<ServiceType> getServiceTypes(String... serviceTypeNameStartWith) {
		List<ServiceType> serviceTypeTypeList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(ServiceType.class);
			criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.add(Restrictions.like("status", "ACTIVE"));
			if (serviceTypeNameStartWith.length > 0) {
				if (!serviceTypeNameStartWith[0].equals("")) {
					criteria.add(Restrictions.like("service_type_name", "%"+ serviceTypeNameStartWith[0] + "%"));
				}
			}
			criteria.addOrder(Order.asc("service_type_name"));
			List<Object> det = criteria.list();
			serviceTypeTypeList = new ArrayList<ServiceType>();

			ServiceType serviceType1 = new ServiceType();
			serviceType1.setService_type_id(0);
			serviceType1.setService_type_name("Select");
			serviceTypeTypeList.add(serviceType1);

			for (int i = 0; i < det.size(); i++) {

				ServiceType serviceType = (ServiceType) det.get(i);
				serviceTypeTypeList.add(serviceType);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return serviceTypeTypeList;
		}
	}

	public List<BodyType> getBodyTypes(String... bodyTypeNameStartWith) {
		List<BodyType> bodyTypeList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(BodyType.class);

			if (bodyTypeNameStartWith.length > 0) {
				if (!bodyTypeNameStartWith[0].equals("")) {
					criteria.add(Restrictions.like("body_type_name", "%"+ bodyTypeNameStartWith[0] + "%"));
				}
			}
			criteria.addOrder(Order.asc("body_type_name"));
			List<Object> det = criteria.list();
			bodyTypeList = new ArrayList<BodyType>();

			BodyType bodyType1 = new BodyType();
			bodyType1.setBody_type_id(0);
			bodyType1.setBody_type_name("Select");
			bodyTypeList.add(bodyType1);

			for (int i = 0; i < det.size(); i++) {

				BodyType bodyType = (BodyType) det.get(i);
				bodyTypeList.add(bodyType);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return bodyTypeList;
		}
	}

	public List<VehicleCategory> getCategoryTypes(
			String... bodyTypeNameStartWith) {
		List<VehicleCategory> vehicleCategoryList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(VehicleCategory.class);
			criteria.add(Restrictions.eq("deletedStatus", 0));

			if (bodyTypeNameStartWith.length > 0) {
				if (!bodyTypeNameStartWith[0].equals("")) {
					criteria.add(Restrictions.like("vehicleCategoryName", "%"+ bodyTypeNameStartWith[0] + "%"));
				}
			}
			criteria.addOrder(Order.asc("vehicleCategoryName"));
			List<Object> det = criteria.list();
			vehicleCategoryList = new ArrayList<VehicleCategory>();

			VehicleCategory vehicleCategory1 = new VehicleCategory();
			vehicleCategory1.setVehicleCategoryId(0);
			vehicleCategory1.setVehicleCategoryName("Select");
			vehicleCategoryList.add(vehicleCategory1);

			for (int i = 0; i < det.size(); i++) {

				VehicleCategory vehicleCategory = (VehicleCategory) det.get(i);
				vehicleCategoryList.add(vehicleCategory);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return vehicleCategoryList;
		}
	}

	public List<OrgType> getOrgTypes() {
		List<OrgType> orgTypeList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(OrgType.class);

			Junction conditionGroup = Restrictions.disjunction();
			conditionGroup.add(Restrictions.in("orgType", new String[] { "CENTRAL OFFICE", "DIVISION","WORKSHOP", "ACCOUNT", "DEPOT" }));
			criteria.add(conditionGroup);

			criteria.addOrder(Order.asc("orgType"));
			List<Object> det = criteria.list();
			orgTypeList = new ArrayList<OrgType>();

			OrgType orgType1 = new OrgType();
			orgType1.setOrg_type_id(0);
			orgType1.setOrgType("Select");
			orgTypeList.add(orgType1);

			for (int i = 0; i < det.size(); i++) {

				OrgType orgType = (OrgType) det.get(i);
				orgTypeList.add(orgType);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return orgTypeList;
		}
	}
	
	public String statusOfVehicle(String vehicleId)
	{
		String vehicleStatus= "";
		Session session = null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String  statusQuery = "select status from vehicle where vehicle_id = '"+vehicleId+"'";
			vehicleStatus = getDBResultStr(session, statusQuery, "status");
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(session != null){
				session.close();
			}
			return vehicleStatus;
		}
		
	}

	public List<String> getUnitId(int orgtypeid) {

		List list = new ArrayList();
		System.out.println("inside get id");
		String qry = "select org_chart_id from org_chart where org_name!='(NULL)' and org_type_id="
				+ orgtypeid + " and deleted_status='0' order by org_chart_id";
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			System.out.println(aliasToValueMapList);
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_chart_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return list;
		}

	}

	public List<String> getUnitName(int orgtypeid) {

		List list = new ArrayList();
		System.out.println("inside get id");
		String qry = "select org_name from org_chart where org_name!='(NULL)' and org_type_id="
				+ orgtypeid + " and deleted_status='0' order by org_chart_id";
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return list;
		}
	}

//	public List getFuleHistoryList() {
//		List<Fuel> fuelHistoryHistoryList = null;
//		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		Query query = session.createQuery("from Fuel f join f.filledByUser ");
//
//		try {
//
//			List<Object[]> det = query.list();
//
//			fuelHistoryHistoryList = new ArrayList<Fuel>();
//
//			for (int i = 0; i < det.size(); i++) {
//				Object[] result = det.get(i);
//
//				Fuel fuelHistory = (Fuel) result[0];
//				User filledUser = (User) result[1];
//				fuelHistory.setFilledByUser(filledUser);
//				fuelHistoryHistoryList.add(fuelHistory);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//			return fuelHistoryHistoryList;
//		}
//	}



	public boolean isVehicleNew(Vehicle vehicleObject, String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from vehicle where license_number='"+ vehicleObject.getVehicleRegistrationNumber().trim() + "' and deleted_status ='0'";
			if (reqType.equals("create")) {

			} else if (reqType.equals("update")) {
				query = "select count(*)as count from vehicle where license_number='"+ vehicleObject.getVehicleRegistrationNumber().trim()	+ "' and vehicle_id not in ('"+ vehicleObject.getVehicleId() + "')  and deleted_status ='0' and status='ACTIVE' ";

				// query =
				// "select count(*)as count from vehicle where license_number not in ('"+
				// vehicleObject.getVehicleRegistrationNumber()+"') or " +
				// "( vehicle_id ='"+vehicleObject.getVehicleId()+"' and license_number = '"+
				// vehicleObject.getVehicleRegistrationNumber()+"')";
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
public boolean isnewchasis(Vehicle vehicleObject, String reqType){
	

	boolean isNew = false;

	Session session = null;
	try {
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		System.out.println("---------"+vehicleObject.getChasisNumber().trim());
		String query = "select count(*)as count from vehicle where chasis_number='"+ vehicleObject.getChasisNumber().trim() + "' and deleted_status ='0'";
		if (reqType.equals("create")) {

		} else if (reqType.equals("update")) {
			query = "select count(*)as count from vehicle where chasis_number='"+ vehicleObject.getChasisNumber().trim()	+ "' and vehicle_id not in ('"+ vehicleObject.getVehicleId() + "')  and deleted_status ='0'  and status='ACTIVE' ";

			// query =
			// "select count(*)as count from vehicle where license_number not in ('"+
			// vehicleObject.getVehicleRegistrationNumber()+"') or " +
			// "( vehicle_id ='"+vehicleObject.getVehicleId()+"' and license_number = '"+
			// vehicleObject.getVehicleRegistrationNumber()+"')";
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
	@SuppressWarnings("finally")
	public boolean saveNewVehicle(Vehicle vehicelObject) {
		boolean isSuccess = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			// session.
			transaction = session.beginTransaction();
			Integer id = (Integer) session.save(vehicelObject);

			session.getTransaction().commit();
			System.out.println("vehicle created. having id---->>>" + id);
			ServletActionContext.getRequest().setAttribute("createdVehicleId",
					id);

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			isSuccess = false;
			;
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return isSuccess;
		}

	}



	public boolean saveFCRenewalDetails(VehicleFcRenewal fcRenewalObject,
			Vehicle vehicleObject) {
		Session session = null;
		Transaction transaction = null;
		boolean returnResult = true;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			Integer id = (Integer) session.save(fcRenewalObject);

			Vehicle vehicle = (Vehicle) session.get(Vehicle.class,
					vehicleObject.getVehicleId());
			vehicle.setFcExpiryDate(common
					.getDateFromDatePicker(fcRenewalObject
							.getFcRenewDateString()));

			vehicle.setUpdated_by(Integer.parseInt(ServletActionContext
					.getRequest().getSession().getAttribute("userid")
					.toString()));
			vehicle.setUpdated_date((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"))
					.format(new Date()));
			session.update(vehicle);

			fcRenewalObject.setFcExpiryDate(common
					.getDateFromDatePicker(fcRenewalObject
							.getFcExpiryDateString()));
			fcRenewalObject.setFcRenewalDate(common
					.getDateFromDatePicker(fcRenewalObject
							.getFcRenewDateString()));
			fcRenewalObject.setVehicle(vehicleObject);
			session.save(fcRenewalObject);

			System.out
					.println("vehicle fc renewal  success.... having id---->>>"
							+ id);
			session.getTransaction().commit();

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			returnResult = false;
			;
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return returnResult;
		}

	}



	


	public boolean saveFuel(Fuel fuelObject) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = true;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			Integer id = (Integer) session.save(fuelObject);
			System.out
					.println("fuel filling success.... having id---->>>" + id);
			session.getTransaction().commit();

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			isSuccess = false;
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return isSuccess;
		}

	}

	/*public boolean deleteVehilce(Integer deleteVehicleId) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			int userID = Integer.parseInt(ServletActionContext
					.getRequest().getSession().getAttribute("userid")
					.toString());
			Vehicle vehicleObject = (Vehicle) session.get(Vehicle.class,
					deleteVehicleId);
			vehicleObject.setDeleted_status(1);
			vehicleObject.setUpdated_by(userID);
			vehicleObject.setUpdated_date((new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss")).format(new Date()));
			session.update(vehicleObject);
			String queryForReleaseDevice = "UPDATE `vehicle_device` set `status` ='INACTIVE',updated_by = '"+userID+"',updated_date = now() WHERE `vehicle_id` = '"+deleteVehicleId+"'";
			Query qryForRelease = session.createSQLQuery(queryForReleaseDevice);
			qryForRelease.executeUpdate();
			session.getTransaction().commit();
			isSuccess = true;

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			isSuccess = false;
		} finally {
			if (session != null) {
				session.close();
			}

			return isSuccess;
		}
	}


	public String deleteVehilce(Integer deleteVehicleId) {

*/
	
	
	public String deleteVehilce(Integer deleteVehicleId) {
		System.out.println("in delete veh mthod");
		Session session = null;
		String status="error";
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"vehicle",deleteVehicleId);
			System.out.println("&&&&&&&&&&"+status);
			if(status.trim().equalsIgnoreCase("")){
				
				
			
			transaction = session.beginTransaction();
			int userID = Integer.parseInt(ServletActionContext
					.getRequest().getSession().getAttribute("userid")
					.toString());
			Vehicle vehicleObject = (Vehicle) session.get(Vehicle.class,
					deleteVehicleId);
			vehicleObject.setDeleted_status(1);
			vehicleObject.setUpdated_by(userID);
			vehicleObject.setUpdated_date((new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss")).format(new Date()));
			session.update(vehicleObject);
			String queryForReleaseDevice = "UPDATE `vehicle_device` set `status` ='INACTIVE',updated_by = '"+userID+"',updated_date = now() WHERE `vehicle_id` = '"+deleteVehicleId+"'";
			Query qryForRelease = session.createSQLQuery(queryForReleaseDevice);
			qryForRelease.executeUpdate();
			session.getTransaction().commit();
			System.out.println("delete mapping");
			
			isSuccess = true;
			
		}
		} catch (Exception e) {
			status="error:";
			session.getTransaction().rollback();
			e.printStackTrace();
			isSuccess = false;
		} finally {
			if (session != null) {
				session.close();

			}

			return status;
		}
	}
	
	public boolean deleteScheduleMappingForVehicle(int deletedVehicleID) {
		Session sesion=null;
		Transaction trx =null;
		boolean deleted_flag=false;
		int count1=0;
		
		try {
			 sesion =HibernateUtil.getSession("hibernate.cfg.xml");
			sesion.beginTransaction();
			String sq="delete from schedule_mapping_vehicle1 where vehicle_id="+deletedVehicleID+" ";
			System.out.println("delete--"+sq);
				Query qry=sesion.createSQLQuery(sq);
				int count=qry.executeUpdate();
				//sesion.getTransaction().commit();
				if(count>0) {
				String sql1="delete from daily_schedule_mapping_vehicle where vehicle_id="+deletedVehicleID+" and operated_date=DATE(now()) ";
				System.out.println("delete now--"+sql1);
					Query qry1=sesion.createSQLQuery(sql1);
				 count1=qry1.executeUpdate();
				}else {
					deleted_flag=false;
				}
				if(count>0 && count1>0) {	
					sesion.getTransaction().commit();
					deleted_flag=true;
				}
					
				
		}catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
			deleted_flag=false;
		}
		finally {
			sesion.close();	
			}
		return deleted_flag;
	}
	
	public boolean saveOrganisationOfVehicle(Vehicle vehicle) {
		boolean isSuccess = false;

		Session session = null;
		Transaction transction = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			Vehicle vehicleObject = (Vehicle) session.get(Vehicle.class,
					vehicle.getVehicleId());
			try {
				VehicleTrasnsfer vt=new VehicleTrasnsfer();
				vt.setVehicleId(vehicle.getVehicleId());
				vt.setFromDepotId(vehicleObject.getDepotOrUnit().getOrg_chart_id());
				vt.setToDepotId(vehicle.getDepotOrUnit().getOrg_chart_id());
				vt.setUpdateBy(Integer.parseInt(ServletActionContext
						.getRequest().getSession().getAttribute("userid")
						.toString()));
				vt.setUpdatedDate((new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss")).format(new Date()));
				session.save(vt);
			}catch (Exception e) {
			e.printStackTrace();
			}
			
			vehicleObject.setDepotOrUnit(vehicle.getDepotOrUnit());
			vehicleObject.setUpdated_by(Integer.parseInt(ServletActionContext
					.getRequest().getSession().getAttribute("userid")
					.toString()));
			vehicleObject.setUpdated_date((new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss")).format(new Date()));
			session.update(vehicleObject);
			session.getTransaction().commit();
			isSuccess = true;
			//updatedevicerel(vehicle.getVehicleId());
			
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			isSuccess = false;
		} finally {
			if (session != null) {
				session.close();
			}
			
			return isSuccess;
		}

	}
public void updatedevicerel(int vehicle){


	Session session = null;
	Transaction transction = null;
	try {

		session = HibernateUtil.getSession("hibernate.cfg.xml");
		transction=session.beginTransaction();
	int id=Integer.parseInt(ServletActionContext
				.getRequest().getSession().getAttribute("userid")
				.toString());
	
		String qry="update vehicle_device set status='Active',updated_by="+id+",updated_date=now() where vehicle_id="+vehicle;
		//System.out.println("update==="+qry);
		SQLQuery qury=session.createSQLQuery(qry);
		qury.executeUpdate();
	transction.commit();
		//System.out.println("==========");
		

	} catch (Exception e) {
		e.printStackTrace();
		
	}
	
	}

	public int getDBResultInt(Session objSession, String sql, String selectName) {
		int result_count = 0;

		try {
			Query query = objSession.createSQLQuery(sql).addScalar(selectName,
					Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, String>> aliasToValueMapList = query.list();

			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<String, String> rs = aliasToValueMapList.get(0);
				result_count = Integer.parseInt(rs.get(selectName) != null
						&& !rs.get(selectName).equals("") ? rs.get(selectName)
						: "0");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result_count;
	}

	public boolean saveScrapVehicle(Vehicle vehicleObject,VehicleScrap vehicleScrap,String date) {
		boolean isSuccess = false;
		Session session = null;
		Transaction transcation = null;
	
		try {
			VehicleDAO vdao=new VehicleDAO();
			Common c=new Common();
			Date date1=common.getDate(date);
			String date2=common.getDateFromPicker(date);
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transcation = session.beginTransaction();
			String currDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
			int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());

			Vehicle vehicle = (Vehicle) session.get(Vehicle.class,vehicleObject.getVehicleId());
			vehicle.setScrappedDate(date1);
			//System.out.println("-------------------------------"+vehicle.getScrappedDateString());
			vehicle.setUpdated_by(userId);
			vehicle.setUpdated_date(currDate);
			vehicle.setIsScarpped("YES");
			vehicle.setCause_status("S");
			VehicleScrap vehicleScrapObject = new VehicleScrap();
			vehicleScrapObject.setScrapDate(date1);
			vehicleScrapObject.setScrapVehicle(vehicleObject);
			vehicleScrapObject.setCreatedBy(userId);
			vehicleScrapObject.setReason(vehicleScrap.getReason());
			vehicleScrapObject.setStatus("YES");
			vehicleScrapObject.setCreated_date(currDate);
			vehicleScrapObject.getScrapVehicle().setCause_status("S");
			session.save(vehicleScrapObject);
			
			session.update(vehicle);

			session.getTransaction().commit();
			isSuccess = true;
			removedevicerel( vehicleObject);
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			isSuccess = false;
		} finally {
			if (session != null) {
				session.close();
			}
			return isSuccess;
		}

	}
public void removedevicerel(Vehicle vehicleObject){
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Transaction transcation=session.beginTransaction();
	try{
	int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());

	String sql="update  vehicle_device set status='INACTIVE',updated_by="+userId+",updated_date=now()  where vehicle_id="+vehicleObject.getVehicleId();
	SQLQuery qury=session.createSQLQuery(sql);
	qury.executeUpdate();
	transcation.commit();
	}catch (Exception e) {
		
	e.printStackTrace();
	}//finally{session.close();}
}
	public List<Device_Type> getDeviceTypeList() {
		List<Device_Type> deviceTypeList = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(Device_Type.class);
		criteria.add(Restrictions.eq("deleted_status", 0));
		criteria.add(Restrictions.like("device_type_name", "VTU"));

		try {
			List<Object> det = criteria.list();
			deviceTypeList = new ArrayList<Device_Type>();
			Device_Type dev = new Device_Type();
//			dev.setDevice_type_id(0);
//			dev.setDevice_type_name("Select");
//			deviceTypeList.add(dev);

			for (int i = 0; i < det.size(); i++) {
				Object result = det.get(i);
				Device_Type deviceList1 = (Device_Type) result;
				deviceTypeList.add(deviceList1);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return deviceTypeList;
		}
	}

	public List<Device> getAllDeviceList(int devType, int vehicleId) {

		List<Map<String, String>> aliaValueToMapList;
		List<Device> deviceList = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		String queryStringForDevice = "select dd.device_id as id,dd.device_serial_number from  vehicle_device vvdd "
				+ "inner join device dd on dd.device_id=vvdd.device_id "
				+ " inner join device_type ddtt on ddtt.device_type_id = vvdd.device_type_id  "
				+ " where vvdd.status='ACTIVE' and vvdd.device_type_id='"
				+ devType
				+ "' and vvdd.vehicle_id='"
				+ vehicleId
				+ "' union "
				+ " select distinct(d.device_id)as id,d.device_serial_number from device d "
				+ " inner join device_type dt on dt.device_type_id = d.device_type_id "
				+ " left outer join  vehicle_device vd on vd.device_id = d.device_id and vd.status='ACTIVE'"
				+ "  WHERE  ((vd.status!='ACTIVE' and vehicle_id!='"
				+ vehicleId
				+ "') or  vd.status is null ) AND d.`deleted_status` = '0' "
				+ " AND  d.`status`='ACTIVE'  and dt.`device_type_id` = '"
				+ devType
				+ "' and dt.deleted_status='0' and dt.status='ACTIVE' order by id";
		/*
		 * "select distinct(d.device_id),d.device_serial_number from device d "
		 * +
		 * "inner join device_type dt on dt.device_type_id = d.device_type_id "
		 * + "left outer join  vehicle_device vd on vd.device_id = d.device_id "
		 * +
		 * "WHERE  (vd.status='INACTIVE' or  vd.status is null ) AND d.`deleted_status` = '0' AND  d.`status`='ACTIVE' "
		 * + " and dt.`device_type_id` = '"+devType+
		 * "' and dt.deleted_status='0' and dt.status='ACTIVE' union  ";
		 */
		Query queryForDevice = session.createSQLQuery(queryStringForDevice)
				.addScalar("id", Hibernate.STRING)
				.addScalar("device_serial_number", Hibernate.STRING);
		queryForDevice
				.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		/*
		 * Criteria criteria = session.createCriteria(Device.class);
		 * criteria.createCriteria("device", "devType",Criteria.LEFT_JOIN);
		 * criteria.createCriteria("deviceMappingVehicle", "vehidevi");
		 * 
		 * Junction conditionGroupmain = Restrictions.conjunction();
		 * 
		 * Junction conditionGroup = Restrictions.disjunction();
		 * conditionGroup.add(Restrictions.like("vehidevi.status", "INACTIVE"));
		 * conditionGroup.add(Restrictions.isNull("vehidevi.status"));
		 * 
		 * conditionGroupmain.add(conditionGroup);
		 * 
		 * conditionGroupmain.add(Restrictions.ilike("status", "ACTIVE"));
		 * conditionGroupmain.add(Restrictions.eq("deleted_status",0));
		 * 
		 * conditionGroupmain.add(Restrictions.eq("devType.deleted_status", 0));
		 * conditionGroupmain.add(Restrictions.ilike("devType.status",
		 * "ACTIVE"));
		 * 
		 * conditionGroupmain.add(Restrictions.eq("device.device_type_id",
		 * devType));
		 * 
		 * criteria.add(conditionGroupmain);
		 */

		try {

			aliaValueToMapList = queryForDevice.list();

			deviceList = new ArrayList<Device>();
			Device dev = new Device();
			dev.setDevice_id(0);
			dev.setDevice_serial_number("Select");
			deviceList.add(dev);

			for (int i = 0; i < aliaValueToMapList.size(); i++) {
				Map<String, String> rs = aliaValueToMapList.get(i);
				Device device = new Device();
				device.setDevice_id(Integer.parseInt(rs.get("id")));
				device.setDevice_serial_number(rs.get("device_serial_number"));
				// deviceList1.setDevice_id(deviceList1.getDevice().getDevice_type_id());
				deviceList.add(device);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return deviceList;
		}
	}

	public int currentAllocatedDevice(int vehicleId, int devTypeid) {

		VehicleDevice deviceList = null;
		int deviceId = -9;
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(VehicleDevice.class);
			criteria.createAlias("vehicleDetails", "vehi");
			// criteria.createAlias("deviceDetails", "devi");
			criteria.createAlias("deviceDetails", "devi");
			criteria.createAlias("devi.device", "devType");
			criteria.add(Restrictions.eq("vehi.vehicleId", vehicleId));
			criteria.add(Restrictions.eq("devType.device_type_id", devTypeid));
			criteria.add(Restrictions.ilike("status", "ACTIVE"));

			List<Object> det = criteria.list();
			if (det.size() > 0) {
				// for (int i = 0; i < det.size(); i++) {
				/*
				 * Object result = det.get(i); Device
				 */
				deviceList = (VehicleDevice) det.get(0);
				deviceId = deviceList.getDeviceDetails().getDevice_id();
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return deviceId;
		}
	}

	public boolean assignDevice(Vehicle vehicleObject, Device deviceObject) {
		boolean isSuccess = false;
		Session session = null;
		Transaction transaction = null;
		int device_id=0;
		try {
			int user_id = Integer.parseInt(ServletActionContext.getRequest()
					.getSession().getAttribute("userid").toString());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			String check1="select * from vehicle_device where vehicle_id="+ vehicleObject.getVehicleId()+ " and " +
					" device_id="+deviceObject.getDevice_id()+" and status='active'";
			SQLQuery query1 = session.createSQLQuery(check1);
			   query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			    List<Map<String, Object>> aliasToValueMapList1 = query1.list();
			    transaction = session.beginTransaction();
			   // System.out.println("condition--------"+aliasToValueMapList1.size());
			if(aliasToValueMapList1.size()==0){
			//==============
				//System.out.println("enter 2nd conditon===========");
			String check="select * from vehicle_device where vehicle_id="+ vehicleObject.getVehicleId()+ " and " +
					" status='active'";
			SQLQuery query = session.createSQLQuery(check);
			   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			    List<Map<String, Object>> aliasToValueMapList = query.list();
			    System.out.println("----"+aliasToValueMapList);
			    if(aliasToValueMapList.size()!=0){
			//System.out.println("==========enter update================");
			
			SQLQuery queryForUpdation = session
					.createSQLQuery("update vehicle_device set status ='INACTIVE',updated_date='"
							+ (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date())+ "',updated_by='"+ user_id
							+ "' where vehicle_id='"+ vehicleObject.getVehicleId()+ "'" 
							+ " and  status='ACTIVE' and device_type_id='"+ deviceObject.getDevice().getDevice_type_id()+ "' ");
			int update = queryForUpdation.executeUpdate();
			    }
			   // System.out.println("===========after update==========");
			if(deviceObject.getDevice_id()!=-10){
				//System.out.println("==========add new============");
				device_id=deviceObject.getDevice_id();
				VehicleDevice vehicleDeviceObject = new VehicleDevice();
				vehicleDeviceObject.setVehicleDetails(vehicleObject);
				vehicleDeviceObject.setDeviceDetails(deviceObject);
				vehicleDeviceObject.setStatus("ACTIVE");
				vehicleDeviceObject.setDeviceTypeDetails(deviceObject.getDevice());
				vehicleDeviceObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
				vehicleDeviceObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
				vehicleDeviceObject.setUpdatedBy(0);
				
				

			/*
			 * Device device = new Device();
			 * device.setDevice_id(vehicleObject.getVtuDevice().getDevice_id());
			 * Vehicle vehicle = (Vehicle) session.get(Vehicle.class,
			 * vehicleObject.getVehicleId());
			 * 
			 * vehicle.setUpdated_by(Integer.parseInt(ServletActionContext.
			 * getRequest().getSession().getAttribute("userid").toString()));
			 * vehicle.setUpdated_date((new
			 * SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			 * vehicle.setVtuDevice(device);
			 */

				session.save(vehicleDeviceObject);
			}
			//System.out.println("==========new row ending=========");
			
			SQLQuery queryForUpdationOfVehicle = session
					.createSQLQuery("update vehicle set updated_date=now(),updated_by='"+ user_id+ "' where vehicle_id='"+ vehicleObject.getVehicleId()+"' ");
			queryForUpdationOfVehicle.executeUpdate();
			
			if(deviceObject.getDevice_id()!=-10){
				SQLQuery queryForUpdateScheduleMapping = session
						.createSQLQuery("update schedule_mapping_vehicle1 smv inner join device d on d.device_id="+deviceObject.getDevice_id()+" set smv.device_id=d.device_serial_number,smv.updated_by='"+ user_id+ "' where smv.vehicle_id='"+ vehicleObject.getVehicleId()+"' ");
				queryForUpdateScheduleMapping.executeUpdate();
			}else{
				SQLQuery queryForUpdateScheduleMapping = session
						.createSQLQuery("update schedule_mapping_vehicle1 smv set smv.device_id=0,smv.updated_by='"+ user_id+ "' where smv.vehicle_id='"+ vehicleObject.getVehicleId()+"' ");
				queryForUpdateScheduleMapping.executeUpdate();
			}
			
//			SQLQuery queryForUpdateScheduleMapping = session
//					.createSQLQuery("update schedule_mapping_vehicle1 smv inner join device d on d.device_id="+deviceObject.getDevice_id()+" set smv.device_id=d.device_serial_number,smv.updated_by='"+ user_id+ "' where smv.vehicle_id='"+ vehicleObject.getVehicleId()+"' ");
//			queryForUpdateScheduleMapping.executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.print("Device allocation success");
			isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			isSuccess = false;
		} finally {
			if (session != null) {
				session.clear();
			}
			return isSuccess;
		}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForScrap(HttpServletRequest request,String index, String dir, String orgchartid){
	//	int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session1 = HibernateUtil.getSession("");
		System.out.println("in scrap#######");
		try{
			String sql;
			String[] dbcol = {"","vehicle_id","license_number","org_name","org_type","fc_expiry_date","schedule_kms","running_kms","vehicle_type_name",
			"brand_type_name","ac_availability","make_type_name","model_type_name","procured_date",
			"body_type_name","operational_date","docking_planning_date","service_type_name","registration_date","chasis_number",
			"number_of_wheels","vehicle_category_name","under_warranty","status","scrapped","scrapped_date","reason","created_date","createdby","updated_date","userloginname"};
	      String col = dbcol[Integer.parseInt(index)];
			
			sql="select ifnull(v.vehicle_id,'') vehicle_id,ifnull(v.license_number,'') license_number,ifnull(og.org_name,'') org_name" +
					",ifnull(ot.org_type,'') org_type, ifnull(fc_expiry_date,'') fc_expiry_date,ifnull(running_kms,'') running_kms," +
					"ifnull(schedule_kms,'') schedule_kms,ifnull(vt.vehicle_type_name,'') vehicle_type_name,ifnull(bt.brand_type_name,'') brand_type_name," +
					"ifnull(ac_availability,'') ac_availability," +
					"ifnull(mt.make_type_name,'') make_type_name,ifnull(model_type_name,'') model_type_name,ifnull(procured_date,'') procured_date," +
					"ifnull(body_type_name,'') body_type_name,ifnull(operational_date,'') operational_date," +
					"ifnull(docking_planning_date,'') docking_planning_date,ifnull(service_type_name,'') service_type_name,ifnull(registration_date,'') registration_date," +
					"ifnull(chasis_number,'') chasis_number," +
					"ifnull(number_of_wheels,'') number_of_wheels,ifnull(vehicle_category_name,'') vehicle_category_name,ifnull(under_warranty,'') under_warranty," +
					"ifnull(v.status,'') status,ifnull(scrapped,'') scrapped," +
					"ifnull(v.scrapped_date,'') scrapped_date,ifnull(vs.reason,'')reason,ifnull(v.created_date,'') created_date,ifnull(mum1.userloginname,'') createdby," +
					"ifnull(v.updated_date,'') updated_date,ifnull(mum.userloginname,'') userloginname " +
					"from vehicle v inner join vehicle_scrap vs on v.vehicle_id=vs.vehicle_id "
					+ " left join org_chart og on v.org_chart_id=og.org_chart_id " +
					"left join org_type ot on ot.org_type_id=og.org_type_id " +
					"left join vehicle_type vt on vt.vehicle_type_id=v.vehicle_type_id " +
					"left join brand_type bt on bt.brand_type_id=v.brand_type_id " +
					"left join make_type mt on mt.make_type_id=v.make_type_id " +
					"left join model_type mti on mti.model_type_id=v.model_type_id " +
					"left join body_type btype on btype.body_type_id=v.body_type_id " +
					"left join service_type st on st.service_type_id=v.service_type_id " +
					"left join vehicle_category vc on vc.vechile_id=v.vehicle_usage_category " +
					"left join menu_user_master mum on mum.user_id=v.updated_by " +
					"left join menu_user_master mum1 on mum1.user_id=v.created_by " +
					"where v.deleted_status=0 and v.status='ACTIVE' and v.cause_status='S' "+orgchartid+" and vs.deleted_status=0 ";
			
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
//				String search_term = request.getParameter("sSearch").trim();
				search_term=search_term.trim();
//				sql += " and (schedule_no like '%"+search_term+"%'";
//				sql += " and (vehicle_id like '%"+search_term+"%'";
				sql += " and (license_number like '%"+search_term+"%'";
				sql += " OR org_name like '%"+search_term+"%')";
				//sql += " OR org_type like '%"+search_term+"%') ";
			}
			sql+=" group by v.vehicle_id";
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			
		  // System.out.println("query........."+sql);
		  	 Query query = session1.createSQLQuery(sql)
		  			.addScalar("vehicle_id", Hibernate.INTEGER)
						 .addScalar("license_number", Hibernate.STRING)
						 .addScalar("org_name", Hibernate.STRING)
						 .addScalar("org_type", Hibernate.STRING)
						 .addScalar("fc_expiry_date", Hibernate.STRING)
		  	 			 .addScalar("running_kms",Hibernate.STRING)
		  	 			 .addScalar("schedule_kms",Hibernate.STRING)
		  	 			  .addScalar("vehicle_type_name", Hibernate.STRING)
						 .addScalar("brand_type_name", Hibernate.STRING)
		  	 			 .addScalar("ac_availability",Hibernate.STRING)
		  	 			 .addScalar("make_type_name",Hibernate.STRING)
		  	 			  .addScalar("model_type_name", Hibernate.STRING)
						 .addScalar("procured_date", Hibernate.STRING)
		  	 			 .addScalar("body_type_name",Hibernate.STRING)
		  	 			 .addScalar("operational_date",Hibernate.STRING)
		  	 			  .addScalar("docking_planning_date", Hibernate.STRING)
						 .addScalar("service_type_name", Hibernate.STRING)
		  	 			 .addScalar("registration_date",Hibernate.STRING)
		  	 			 .addScalar("chasis_number",Hibernate.STRING)
		  	 			  .addScalar("number_of_wheels",Hibernate.STRING)
		  	 			   .addScalar("vehicle_category_name",Hibernate.STRING)
		  	 			    .addScalar("under_warranty",Hibernate.STRING)
		  	 			     .addScalar("status",Hibernate.STRING)
		  	 			     .addScalar("scrapped",Hibernate.STRING)
		  	 			     .addScalar("scrapped_date",Hibernate.STRING)
		  	 			  .addScalar("reason",Hibernate.STRING)
		  	 			     .addScalar("created_date",Hibernate.STRING)
		  	 			      .addScalar("createdby",Hibernate.STRING)
		  	 			       .addScalar("updated_date",Hibernate.STRING)
		  	 			        .addScalar("userloginname",Hibernate.STRING);
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					//int j=i+1;
					JSONArray ja = new JSONArray();
					ja.add("<input type='checkbox' class='group-checkable' id='"+ rs.get("vehicle_id")+ "' value='"+ rs.get("vehicle_id") + "'/>");
					/*data-set='#sample_2 .checkboxes' */
//					ja.add(rs.get("schedule_id"));
					//System.out.println(rs.get("vehicle_id").toString());
					ja.add(rs.get("vehicle_id").toString());
					ja.add(rs.get("license_number").toString());
					ja.add(rs.get("org_type").toString());
					ja.add(rs.get("org_name").toString());
					if(rs.get("fc_expiry_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("fc_expiry_date").toString()));
					}
					ja.add(rs.get("running_kms").toString());
					ja.add(rs.get("schedule_kms").toString());
					ja.add(rs.get("vehicle_type_name").toString());
					ja.add(rs.get("brand_type_name").toString());
					ja.add(rs.get("ac_availability").toString());
					ja.add(rs.get("make_type_name").toString());
					ja.add(rs.get("model_type_name").toString());
					if(rs.get("procured_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("procured_date").toString()));
					}
					ja.add(rs.get("body_type_name").toString());
					if(rs.get("operational_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("operational_date").toString()));
					}
					if(rs.get("docking_planning_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("docking_planning_date").toString()));
					}
					ja.add(rs.get("service_type_name").toString());
					if(rs.get("registration_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("registration_date").toString()));
					}
					ja.add(rs.get("chasis_number").toString());
					ja.add(rs.get("number_of_wheels").toString());
					ja.add(rs.get("vehicle_category_name").toString());
					ja.add(rs.get("under_warranty").toString());
					ja.add(rs.get("status").toString());
					ja.add(rs.get("scrapped").toString());
					if(rs.get("scrapped_date").toString()==""){
						ja.add("");
					}else{
					ja.add(common.getDateToPicker(rs.get("scrapped_date").toString()));
					}
					ja.add(rs.get("reason").toString());

					if(rs.get("created_date").toString().equalsIgnoreCase("") || rs.get("created_date").toString() == null){
						ja.add("");	
					}else{
						ja.add((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(rs.get("created_date").toString())));
					}
					
					ja.add(rs.get("createdby").toString());
					if(rs.get("updated_date").toString().equalsIgnoreCase("") || rs.get("updated_date").toString() == null ){
						ja.add("");
					}else{
					ja.add((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(rs.get("updated_date").toString())));
					}
					ja.add(rs.get("userloginname").toString());
					
					
//					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				int cnt=0;
				//totalAfterFilter = aliasToValueMapList.size();
				///result.put("aaData", array);
				//String search_term2= request.getParameter("sSearch");
				
				result.put("aaData", array);

				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalVehicleRecordsScrap(request,col,dir,orgchartid);//getTotalRecordsForCount(search_term3);
		
			result.put("iTotalRecords",cnt);
			
			result.put("iTotalDisplayRecords", cnt);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session1 != null) {
				session1.close();
			}
		}
		return result;
	}
	
	
	public int getTotalVehicleRecordsScrap(HttpServletRequest request,String col,String dir, String orgchartid) {
		int cnt=0;
		//Session session = HibernateUtilVtu.getSession("");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	 
		try{
			String sql="";
			sql = "select ifnull(v.vehicle_id,'') vehicle_id,ifnull(v.license_number,'') license_number,ifnull(og.org_name,'') org_name " +
//					",ifnull(ot.org_type,'') org_type, ifnull(fc_expiry_date,'') fc_expiry_date,ifnull(running_kms,'') running_kms," +
//					"ifnull(schedule_kms,'') schedule_kms,ifnull(vt.vehicle_type_name,'') vehicle_type_name,ifnull(bt.brand_type_name,'') brand_type_name," +
//					"ifnull(ac_availability,'') ac_availability," +
//					"ifnull(mt.make_type_name,'') make_type_name,ifnull(model_type_name,'') model_type_name,ifnull(procured_date,'') procured_date," +
//					"ifnull(body_type_name,'') body_type_name,ifnull(operational_date,'') operational_date," +
//					"ifnull(docking_planning_date,'') docking_planning_date,ifnull(service_type_name,'') service_type_name,ifnull(registration_date,'') registration_date," +
//					"ifnull(chasis_number,'') chasis_number," +
//					"ifnull(number_of_wheels,'') number_of_wheels,ifnull(vehicle_category_name,'') vehicle_category_name,ifnull(under_warranty,'') under_warranty," +
//					"ifnull(v.status,'') status,ifnull(scrapped,'') scrapped," +
//					"ifnull(scrapped_date,'') scrapped_date,ifnull(v.created_date,'') created_date,ifnull(mum1.userloginname,'') createdby," +
//					"ifnull(v.updated_date,'') updated_date,ifnull(mum.userloginname,'') userloginname " +
					"from vehicle v left join org_chart og on v.org_chart_id=og.org_chart_id " +
					"left join org_type ot on ot.org_type_id=og.org_type_id " +
					"left join vehicle_type vt on vt.vehicle_type_id=v.vehicle_type_id " +
					"left join brand_type bt on bt.brand_type_id=v.brand_type_id " +
					"left join make_type mt on mt.make_type_id=v.make_type_id " +
					"left join model_type mti on mti.model_type_id=v.model_type_id " +
					"left join body_type btype on btype.body_type_id=v.body_type_id " +
					"left join service_type st on st.service_type_id=v.service_type_id " +
					"left join vehicle_category vc on vc.vechile_id=v.vehicle_usage_category " +
					"left join menu_user_master mum on mum.user_id=v.updated_by " +
					"left join menu_user_master mum1 on mum1.user_id=v.created_by " +
					"where v.deleted_status=0 and v.status='ACTIVE' and v.cause_status='S' "+orgchartid+" ";
			
			
			String search_term1 = request.getParameter("sSearch");
			////System.out.println("search_term-------"+search_term1);
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
//				sql += " and (vehicle_id like '%"+search_term+"%'";
				sql += " and (license_number like '%"+search_term+"%'";
				sql += " OR org_name like '%"+search_term+"%')";
				
			}
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
			
//			if(!col.equals("")){
//				 if(dir.equals("asc")){
//					if(col.equals("TOKEN")){
//						sql += " order by CAST("+col+" AS UNSIGNED) asc";
//					}else
//				  sql += " order by "+col+" asc";
//				}else{
//					if(col.equals("TOKEN")){
//						sql += " order by CAST("+col+" AS UNSIGNED) desc";
//					}
//					else
//					sql += " order by "+col+" desc";
//				}
//			}
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			cnt=aliasToValueMapList.size();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return cnt;
	
}
	public boolean isnewscrap(int id) {

		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from vehicle_scrap where vehicle_id='"+id + "' and deleted_status ='0'";
	
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
