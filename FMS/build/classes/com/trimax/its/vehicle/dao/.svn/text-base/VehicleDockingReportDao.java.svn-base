package com.trimax.its.vehicle.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.DockingType;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.Vehicle;

public class VehicleDockingReportDao {
	
	@SuppressWarnings("rawtypes")
	public List getDepotId(int parentID) {
		List list = new ArrayList();
		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID + " and org_type_id=3 order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getDepotName(int parentID) {
		List list = new ArrayList();
		String qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID + " and org_type_id=3  order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	public Map<Integer,String> getDockingType(){
		String st="ACTIVE";
		Map<Integer,String> result=new LinkedHashMap<Integer, String>();
		Session session=HibernateUtil.getSession("hibernate.cfg.xml");
		Query query=session.createQuery("from DockingType dokType where deleted_status=0 and status='ACTIVE' order by dokType.docking_type");
		try{
			List<DockingType> list=query.list();
			for(DockingType dok:list){
				result.put(dok.getDocking_type_id(),dok.getDocking_type());
			}
			
		}catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}
	
	public Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=2 and deleted_status=0 order by orgchart.org_name");
		try {
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	
	public int getTotalRecordsForDocking(int total,int running_kms, int depotid, HttpServletRequest request,String cols, String dir) {
		//String viewdeletedrecord = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String searchSQL = "";
		String dockingType="select kms_limit_for_alert from docking_type where docking_type_id="+running_kms;
		Query q=session.createSQLQuery(dockingType);
		String sql = " from vehicle  ";

		sql += " limit " + request.getParameter("iDisplayStart") + ", "
				+ request.getParameter("iDisplayLength");
		
		
		Criteria criteria = session.createCriteria(Vehicle.class);
		criteria.add(Restrictions.eq("deleted_status", 0));
		//criteria.add(Restrictions.eq("status", "ACTIVE"));
		criteria.add(Restrictions.ge("progressingKMs", q.list().get(0)));
		criteria.add(Restrictions.eq("depotOrUnit.org_chart_id", depotid));
		
		
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("vehicleRegistrationNumber", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status", search_term,
					MatchMode.START));
			criteria.add(conditionGroup);

		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}
		criteria.createCriteria("makeType", "make",Criteria.LEFT_JOIN);
		criteria.createCriteria("bodyType", "body",Criteria.LEFT_JOIN);
		criteria.createCriteria("vehicleType", "vehicle",Criteria.LEFT_JOIN);
		criteria.createCriteria("brandType", "brand",Criteria.LEFT_JOIN);
		criteria.createCriteria("modelType", "model",Criteria.LEFT_JOIN);
		criteria.createCriteria("vehicleCategory","category",Criteria.LEFT_JOIN);
		criteria.createCriteria("serviceType","service",Criteria.LEFT_JOIN);
		criteria.createCriteria("depotOrUnit","org",Criteria.LEFT_JOIN);
		criteria.createCriteria("org.orgType","orgtype",Criteria.LEFT_JOIN);

		List<Vehicle> list = criteria.list();
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public JSONObject getDataForDocking(int total,int running_kms, int depotid,
			HttpServletRequest request, String col, String dir) {
		JSONObject result = new JSONObject();
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			int totalAfterFilter = total;
			String dockingType="select kms_limit_for_alert from docking_type where docking_type_id="+running_kms;
			Query q=session.createSQLQuery(dockingType);
			String searchSQL = "";
			String sql = " from vehicle  ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			
			Criteria criteria = session.createCriteria(Vehicle.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			//criteria.add(Restrictions.eq("status", "ACTIVE"));
			criteria.add(Restrictions.ge("progressingKMs", q.list().get(0)));
			criteria.add(Restrictions.eq("depotOrUnit.org_chart_id", depotid));

			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like(
						"vehicleRegistrationNumber", "%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,
						MatchMode.START));
				criteria.add(conditionGroup);

			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			criteria.createCriteria("makeType", "make",Criteria.LEFT_JOIN);
			criteria.createCriteria("bodyType", "body",Criteria.LEFT_JOIN);
			criteria.createCriteria("vehicleType", "vehicle",Criteria.LEFT_JOIN);
			criteria.createCriteria("brandType", "brand",Criteria.LEFT_JOIN);
			criteria.createCriteria("modelType", "model",Criteria.LEFT_JOIN);
			criteria.createCriteria("vehicleCategory","category",Criteria.LEFT_JOIN);
			criteria.createCriteria("serviceType","service",Criteria.LEFT_JOIN);
			criteria.createCriteria("depotOrUnit","org",Criteria.LEFT_JOIN);
			criteria.createCriteria("org.orgType","orgtype",Criteria.LEFT_JOIN);
			
			 criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			 criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			List<Vehicle> list = criteria.list();
			JSONArray array = new JSONArray();
			Common common=new Common();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				
				/*ja.add(list.get(i).getVehicleId());
				ja.add(list.get(i).getVehicleRegistrationNumber() != null ? list.get(i).getVehicleRegistrationNumber() : "");
				ja.add(list.get(i).getDepotOrUnit().getOrg_name());
				ja.add(common.getDateToDatePicker(list.get(i).getFcExpiryDate()));
				ja.add(list.get(i).getProgressingKMs());
				ja.add(list.get(i).getScheduleKMs());
				ja.add(list.get(i).getVehicleType().getVehicle_type_name() != null ? list.get(i).getVehicleType().getVehicle_type_name(): "");
				ja.add(common.getDateToDatePicker(list.get(i).getProceduredDate()));
				ja.add(list.get(i).getStatus());*/
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

				array.add(ja);
			}

			 totalAfterFilter = getTotalRecordsForDocking(total,running_kms,depotid, request, col,dir);
			 result.put("aaData", array);
			 result.put("iTotalRecords", totalAfterFilter);
			 result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
