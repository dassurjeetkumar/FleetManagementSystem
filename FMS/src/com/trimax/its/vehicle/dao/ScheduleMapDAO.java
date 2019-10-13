package com.trimax.its.vehicle.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.log4j.Logger;
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


import com.sun.beans.decoder.ValueObject;
import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.device.model.Device;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vehicle.model.DockingHistory;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.ScheduleMap;
import com.trimax.ws.vts.vtsutility.WayBillDetails;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class ScheduleMapDAO {

	public int getTotalScheduleMappingRecords(HttpServletRequest request,String col,String dir, String orgchartid) {
		int cnt=0;
//		Session session = HibernateUtilVtu.getSession("");
		Session session1 = HibernateUtil.getSession("");
	 
		try{
			String sql;
			
			// we changed the query for pisdb.schedule_mapping_vehicle to its schedule_mapping_vehicle
			sql="SELECT schedule_id as schedule_id,schedule_name,IFNULL(s.schedule_number,'') as schedule_no,st.schedule_type_name as schedule_type_name,"+
	   		        " vehicle_no,sm.depot_name as depot_name,occ.org_name as org_name " +
	   		        " from schedule_mapping_vehicle1 sm left join schedule s on s.schedule_id=sm.schedule_no "+
	   		        "left join schedule_type st on st.schedule_type_id=sm.schedule_type_id "+
	   		        //"left join vehicle v on v.vehicle_id=sm.vehicle_id "+
	                "left join org_chart oc on oc.org_chart_id=sm.depot_id "+
	                "left join org_chart occ on occ.org_chart_id=oc.parent_id where sm.status='ACTIVE' and schedule_id is not null and sm."+orgchartid+" ";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
//				String search_term = request.getParameter("sSearch").trim();
				search_term1=search_term1.trim();
//				sql += " and (schedule_no like '%"+search_term+"%'";
				sql += " and (schedule_name like '%"+search_term1+"%'";
				sql += " OR schedule_type_name like '%"+search_term1+"%'";
				sql += " OR vehicle_no like '%"+search_term1+"%'";
				sql += " OR depot_name like '%"+search_term1+"%') ";
			}
			sql +="group by sm.schedule_no";
			Query query = session1.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			 cnt =	aliasToValueMapList.size();
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			if (session != null) {
//				session.close();
//			}
		}
		return cnt;
	
}
	public JSONObject getScheduleMappingRecords1(int total, HttpServletRequest request,String col,String dir, String orgchartid){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
//		Session session = HibernateUtilVtu.getSession("");
		Session session1 = HibernateUtil.getSession("");
		try{
			String sql;
//			
//			
//			sql="SELECT schedule_id as schedule_id,schedule_name,IFNULL(s.schedule_number,'') as schedule_no,st.schedule_type_name as schedule_type_name,"+
//   		        " vehicle_no,sm.depot_name as depot_name,occ.org_name as org_name from schedule_mapping_vehicle1 sm " +
//   		        " left join schedule s on s.schedule_id=sm.schedule_no "+
//   		        "left join schedule_type st on st.schedule_type_id=sm.schedule_type_id "+
//   		       // "left join vehicle v on v.vehicle_id=sm.vehicle_id "+
//                "left join org_chart oc on oc.org_chart_id=sm.depot_id "+
//                "left join org_chart occ on occ.org_chart_id=oc.parent_id where sm.status='ACTIVE' and sm."+orgchartid+" ";
//			
//			String search_term = request.getParameter("sSearch");
//			if (search_term != null && !search_term.equals("")) {
////				String search_term = request.getParameter("sSearch").trim();
//				search_term=search_term.trim();
////				sql += " and (schedule_no like '%"+search_term+"%'";
//				sql += " and (schedule_name like '%"+search_term+"%'";
//				sql += " OR schedule_type_name like '%"+search_term+"%'";
//				sql += " OR vehicle_no like '%"+search_term+"%'";
//				sql += " OR depot_name like '%"+search_term+"%') ";
//			}
//			sql+=" group by sm.schedule_no";
//			
//			if(!col.equals("")){
//				if(dir.equals("asc")){
//				  sql += " order by "+col+" asc";
//				}else{
//					sql += " order by "+col+" desc";
//				}
//			}
//			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
//			
//		  // System.out.println("query........."+sql);
//		  	 Query query = session1.createSQLQuery(sql)
//		  			.addScalar("schedule_id", Hibernate.INTEGER)
//						 .addScalar("schedule_no", Hibernate.STRING)
//						 .addScalar("schedule_name", Hibernate.STRING)
//						 .addScalar("schedule_type_name", Hibernate.STRING)
//						 .addScalar("vehicle_no", Hibernate.STRING)
//		  	 			 .addScalar("depot_name",Hibernate.STRING)
//		  	 			 .addScalar("org_name",Hibernate.STRING);
//			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String, Object>> aliasToValueMapList = query.list();	
//				
//		    	JSONArray array = new JSONArray();
//				
//				for (int i = 0; i < aliasToValueMapList.size(); i++) {
//					Map<String, Object> rs = aliasToValueMapList.get(i);
//					int j=i+1;
//					JSONArray ja = new JSONArray();
//					ja.add("<input type='checkbox' class='group-checkable' id='"+ rs.get("schedule_id")+ "' value='"+ rs.get("schedule_id") + "'/>");
//					/*data-set='#sample_2 .checkboxes' */
////					ja.add(rs.get("schedule_id"));
//					ja.add(rs.get("schedule_no").toString());
//					ja.add(rs.get("schedule_name").toString());
//					ja.add(rs.get("schedule_type_name").toString());
//					ja.add(rs.get("vehicle_no").toString());
//					ja.add(rs.get("depot_name").toString());
//					ja.add(rs.get("org_name").toString());
////					System.out.println("data is"+ja);
//					array.add(ja);
//				}
				
			

			sql="SELECT schedule_no as schedule_id,schedule_name,IFNULL(s.schedule_number,'') as schedule_no,st.schedule_type_name as schedule_type_name,"+
   		        " vehicle_no,sm.depot_name as depot_name,occ.org_name as org_name from schedule_mapping_vehicle1 sm " +
   		        " left join schedule s on s.schedule_id=sm.schedule_no "+
   		        "left join schedule_type st on st.schedule_type_id=sm.schedule_type_id "+
   		       // "left join vehicle v on v.vehicle_id=sm.vehicle_id "+
                "left join org_chart oc on oc.org_chart_id=sm.depot_id "+
                "left join org_chart occ on occ.org_chart_id=oc.parent_id where sm.status='ACTIVE' and sm."+orgchartid+" ";
			
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
//				String search_term = request.getParameter("sSearch").trim();
				search_term=search_term.trim();
//				sql += " and (schedule_no like '%"+search_term+"%'";
				sql += " and (schedule_name like '%"+search_term+"%'";
				//sql += " OR schedule_type_name like '%"+search_term+"%'";
				sql += " OR vehicle_no like '%"+search_term+"%'";
				sql += " OR depot_name like '%"+search_term+"%') ";
			}
			sql+=" group by sm.schedule_name";
			
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
		  			.addScalar("schedule_id", Hibernate.INTEGER)
						 .addScalar("schedule_no", Hibernate.STRING)
						 .addScalar("schedule_name", Hibernate.STRING)
						 .addScalar("schedule_type_name", Hibernate.STRING)
						 .addScalar("vehicle_no", Hibernate.STRING)
		  	 			 .addScalar("depot_name",Hibernate.STRING)
		  	 			 .addScalar("org_name",Hibernate.STRING);
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					int j=i+1;
					JSONArray ja = new JSONArray();
					ja.add("<input type='checkbox' class='group-checkable' id='"+ rs.get("schedule_id")+ "' value='"+ rs.get("schedule_id") + "'/>");
					/*data-set='#sample_2 .checkboxes' */
//					ja.add(rs.get("schedule_id"));
					//ja.add(rs.get("schedule_no").toString());
					ja.add(rs.get("schedule_name").toString());
					//ja.add(rs.get("schedule_type_name").toString());
					ja.add(rs.get("vehicle_no").toString());
					ja.add(rs.get("depot_name").toString());
					ja.add(rs.get("org_name").toString());
//					System.out.println("data is"+ja);
					array.add(ja);
				}
			
			
			
			
				int cnt=0;
				totalAfterFilter = aliasToValueMapList.size();
				result.put("aaData", array);
				String search_term2= request.getParameter("sSearch");
				

				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalScheduleMappingRecords(request,col,dir,orgchartid);//getTotalRecordsForCount(search_term3);
		
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
	public int getTotalRecordsForSeacrch(int total, HttpServletRequest request,String cols ,String dir) {
		
		Session session=null;
		int cnt=0;
		try{
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			 session = HibernateUtilVtu.getSession("");
			
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(ScheduleMap.class);
				}else{
					 criteria = session.createCriteria(ScheduleMap.class);
				    
				}

				if (!request.getParameter("sSearch").equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					Junction conditionGroup = Restrictions.disjunction();
					
					try{    
						conditionGroup.add(Restrictions.eq("schedule_no", Integer.parseInt(search_term)));
					}catch(Exception e){		
						e.printStackTrace();
					}
					conditionGroup.add(Restrictions.like("schedule_name",search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("vehicle_no", search_term, MatchMode.START));
					criteria.add(conditionGroup);
				}
				if (!cols.equals("")) {
					if (dir.equals("asc")) {       
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));        
					}
				}
				List<ModelType> list = criteria.list();
				cnt = list.size();
			}catch(Exception e){
				e.printStackTrace();
		}
		finally{
			if(session!=null){
				session.close();
			}
		}
		return cnt;
	}
	
	
	
	public ScheduleMap getScheduleMapDetails(int scheduleMappingId)
	{
		ScheduleMap schMapData = null;
		Session session = null;
		//List<ScheduleMap> result=null;
		try{
			session =  HibernateUtilVtu.getSession("");
			 String sql ="SELECT schedule_id as schedule_id,IFNULL(s.schedule_number,'') as schedule_no,sm.depot_name as depot_name," +
				   		" vehicle_no from pisdb.schedule_mapping_vehicle sm left join pisdb.schedule s on s.schedule_id=sm.schedule_no " +
				   		"left join pisdb.schedule_type st on st.schedule_type_id=sm.schedule_type_id " +
				   		//"left join vehicle v on v.vehicle_id=sm.vehicle_id " +
				   		" where sm.status='ACTIVE' AND schedule_id="+scheduleMappingId+" group by sm.schedule_no";
			 Query query = session.createSQLQuery(sql)
			  			.addScalar("schedule_id", Hibernate.STRING)
							 .addScalar("schedule_no", Hibernate.STRING)
//							 .addScalar("schedule_type_name", Hibernate.STRING)
							 .addScalar("vehicle_no", Hibernate.STRING)
							  .addScalar("depot_name", Hibernate.STRING);
				    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				    List<Map<String, Object>> aliasToValueMapList1 = query.list();
				    schMapData=new ScheduleMap();
					for(int j=0;j<aliasToValueMapList1.size();j++){
						Map<String, Object> rs = aliasToValueMapList1.get(j);
						schMapData.setSchedule_no(rs.get("schedule_no").toString());
						schMapData.setVehicle_no(rs.get("vehicle_no").toString());
						schMapData.setDepot_name(rs.get("depot_name").toString());
						schMapData.setSchedule_type_id(Integer.parseInt(rs.get("schedule_id").toString()));
					}
			 
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally{
		if(session != null)
		{
			session.close();
		}
		return schMapData;
	}
}
	
	String schname = "";
	String ismapped = "";
	public String checkVehicleScheduleMapping(String vehicleNo)
	{
		Session session = null;
		
		if(!vehicleNo.equals("NA")){
		try
		{
			session =  HibernateUtil.getSession("");
			String qur="select IFNULL(schedule_name,'') as schedule_name from schedule_mapping_vehicle1 where vehicle_no='"+vehicleNo+"' ";

			Query qury2 = session.createSQLQuery(qur)
		  			.addScalar("schedule_name", Hibernate.STRING);
			
		
			qury2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qury2.list();
			
			String schname="";
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> list = aliasToValueMapList.get(i);
				 JSONArray ja = new JSONArray();
				   schname = list.get("schedule_name").toString();
			}
			if(aliasToValueMapList.size()==0){
//				System.out.println("size is-----"+alertresult.getWaybillDetails().size());
				ismapped="";
		}
				else 
				{
					ismapped=schname;
				}
				}
		catch(Exception e){
			e.printStackTrace();
			ismapped = schname;
		}
		finally{
			if(session != null){
				session.close();
			}
		}	
		}
		return ismapped;
	}
	
	String schname1 = "";
	String ismapped1 = "";
	public String checkVehicleDailyScheduleMapping(String vehicleNo,String op_date)
	{
		Session session = null;
		
		if(!vehicleNo.equals("NA")){
		try
		{
			session =  HibernateUtil.getSession("hibernate.cfg.xml");
			String qur="select IFNULL(schedule_name,'') as schedule_name from daily_schedule_mapping_vehicle where vehicle_no='"+vehicleNo+"' and operated_date='"+op_date+"' ";

			Query qury2 = session.createSQLQuery(qur)
		  			.addScalar("schedule_name", Hibernate.STRING);
			
		
			qury2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qury2.list();
			
			String schname="";
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> list = aliasToValueMapList.get(i);
				 JSONArray ja = new JSONArray();
				   schname = list.get("schedule_name").toString();
			}
			if(aliasToValueMapList.size()==0){
//				System.out.println("size is-----"+alertresult.getWaybillDetails().size());
				ismapped1="";
		}else {
					ismapped1=schname;
				}
				}
		catch(Exception e){
			e.printStackTrace();
			ismapped1 = schname1;
		}
		finally{
			if(session != null){
				session.close();
			}
		}	
		}
		return ismapped1;
	}
	
	
	
	
	boolean isSuccess = false;
	public boolean saveEditedDetails(int id,String vehicleNo)
	{
		Session session = null;
		Session sessionITS=null;
//		Transaction transaction = null;
		int resultId;
		//session =  HibernateUtilVtu.getSession("");
		sessionITS =  HibernateUtil.getSession("");
		try{
		if(!vehicleNo.equals("NA")){
		try
		{
			Query query1 = sessionITS.createSQLQuery("select vehicle_id as vehicle_id from vehicle where license_number =? limit 1");
			query1.setString(0, vehicleNo);
			resultId = (Integer) query1.uniqueResult(); //same vehicle id for all records.
			Transaction tx=sessionITS.beginTransaction();
			int usrid = Integer.parseInt(ServletActionContext
					.getRequest().getSession().getAttribute("userid")
					.toString());
			
//			String hql = "update schedule_mapping_vehicle1 set vehicle_no ='"+vehicleNo +"',vehicle_id='"+resultId+"',updated_date=now() , updated_by= "+usrid+" where schedule_no="+id+"";
			
			String hql = "update schedule_mapping_vehicle1 smv "+
					 " inner join vehicle v on v.license_number ='"+vehicleNo +"' "+
				     " inner join vehicle_device vd on vd.vehicle_id=v.vehicle_id "+
					 " inner join device d on d.device_id=vd.device_id set smv.vehicle_no ='"+vehicleNo +"',smv.vehicle_id=v.vehicle_id,smv.device_id=d.device_serial_number,smv.updated_date=now(),smv.updated_by= "+usrid+" where  smv.schedule_no="+id+" and vd.status='ACTIVE' and d.status='ACTIVE' and d.deleted_status=0 and v.status='ACTIVE' and v.deleted_status=0 ";
		 
			
			
			Query query= sessionITS.createSQLQuery(hql);
			 query.executeUpdate();
			 tx.commit();
			isSuccess = true;			
		}
		catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			
		}			
		}
		else{
			System.out.println("Enter into esle=="+id);
			int usrid = Integer.parseInt(ServletActionContext
					.getRequest().getSession().getAttribute("userid")
					.toString());
			Transaction tx=sessionITS.beginTransaction();
//			if vehicle for schedule is Selecting NA by user
			String hql = "update schedule_mapping_vehicle1 set vehicle_no ='NA',vehicle_id=0,device_id='0',updated_date=now() , updated_by= "+usrid+" where schedule_no="+id+"";
			 System.out.println("SQl==="+hql);
			Query query= sessionITS.createSQLQuery(hql);
			 query.executeUpdate();
			 tx.commit();
			isSuccess=true;
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(sessionITS != null){
				sessionITS.close();
			}
		}
		
		return isSuccess;	
	}
	
	public boolean deleteScheduleMapping(int scheduleMapId)
	{
		System.out.println("in delete schedule dao");
		System.out.println("id coming is"+scheduleMapId);
		String status="error";
		ScheduleMap schMapData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("");
			//for pisd.schedule_mapping_vehicle query
			//Query query = session.createSQLQuery("delete from pisdb.schedule_mapping_vehicle where schedule_no=?");
			Query query = session.createSQLQuery("delete from schedule_mapping_vehicle1 where schedule_no=?");
		    query.setInteger(0, scheduleMapId);
		    int deleted = query.executeUpdate();
			isSuccess = true;
			
		}catch(Exception e){
			status="error";
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			
			return isSuccess;
		}
	}
	
	
	public boolean deleteScheduleMappingWebservicecall(int scheduleMapId)
	{
		Session sessionITS=null;
//		Transaction transaction = null;
		int resultId;
		//session =  HibernateUtilVtu.getSession();
		sessionITS =  HibernateUtil.getSession("");
		System.out.println("scheduleMapId=="+scheduleMapId);
		try{
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
		
		try
		{
			port.deleteSchedulemapping(scheduleMapId,rbKey);
		
			isSuccess = true;			
		}
		catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			
		}		
		
		}catch(Exception ex){
			
		}finally{
			if(sessionITS != null){
				sessionITS.close();
			}
		}
		
		return isSuccess;	
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForshowScheduleMapping(int j,
			HttpServletRequest req, String string, String string2,String orgchartid) {

		JSONObject result = new JSONObject();
		Common common = new Common();
		//String selectedstartdate = common.getDateFromDateTime_(selectedstartDate);
		//String selectedenddate = common.getDateFromDateTime_(selectedendDate);
		//System.out.println("final date is--"+selectedstartdate);
		try {
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
//			System.out.println("rbKey==="+rbKey);
			VtsResponse6 alertresult = port.getScheduleMapping(orgchartid,rbKey);
			//System.out.println("alertresult"+alertresult);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				 JSONArray ja = new JSONArray();
				
//				     System.out.println("alertresult.getWaybillDetails().get(i).getSCHEDULENO()=="+alertresult.getWaybillDetails().get(i).getSCHEDULETYPEID()+"---"+alertresult.getWaybillDetails().get(i).getDutyTypeId()+"#"+alertresult.getWaybillDetails().get(i).getDepotId());
					//System.out.println(alertresult.getWaybillDetails().get(i).getCONDNAME());
				 ja.add("<input type='checkbox' class='group-checkable' id='"+ alertresult.getWaybillDetails().get(i).getSCHEDULETYPEID()+ "' value='"+ alertresult.getWaybillDetails().get(i).getSCHEDULETYPEID() +"#"+ alertresult.getWaybillDetails().get(i).getDutyTypeId()+"#"+alertresult.getWaybillDetails().get(i).getDepotId()+"#"+alertresult.getWaybillDetails().get(i).getSCHEDULENO()+"'/>");
					
					ja.add(alertresult.getWaybillDetails().get(i).getCONDNAME());
					ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
					ja.add(alertresult.getWaybillDetails().get(i).getScheduleTypeName());
					ja.add(alertresult.getWaybillDetails().get(i).getShiftTypeName());
//					System.out.println("shift-----"+alertresult.getWaybillDetails().get(i).getShiftTypeName());
					ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
					ja.add(alertresult.getWaybillDetails().get(i).getDepotCd());
//					ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					
					array.add(ja);
				
				 
				 }
				 result.put("aaData", array);
				 } catch (Exception ex) {
				 ex.printStackTrace();
				 }
				 return result;
				 }
	@SuppressWarnings("unchecked")
	public int getDataForDailyScheduleMapping(int j,
			HttpServletRequest req, String string, String string2,String orgchartid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		JSONObject result = new JSONObject();
		Common common = new Common();
		int count=0;
		//String selectedstartdate = common.getDateFromDateTime_(selectedstartDate);
		//String selectedenddate = common.getDateFromDateTime_(selectedendDate);
		//System.out.println("final date is--"+selectedstartdate);
		try {
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
//			System.out.println("rbKey==="+rbKey);
			VtsResponse6 alertresult = port.getScheduleMapping(orgchartid,rbKey);
			System.out.println("alertresult"+alertresult);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				 JSONArray ja = new JSONArray();
				
//				     System.out.println("alertresult.getWaybillDetails().get(i).getSCHEDULENO()=="+alertresult.getWaybillDetails().get(i).getSCHEDULETYPEID()+"---"+alertresult.getWaybillDetails().get(i).getDutyTypeId()+"#"+alertresult.getWaybillDetails().get(i).getDepotId());
					//System.out.println(alertresult.getWaybillDetails().get(i).getCONDNAME());
//				 ja.add("<input type='checkbox' class='group-checkable' id='"+ alertresult.getwaybilldetails().get(i).getscheduletypeid()+ "' value='"+ alertresult.getwaybilldetails().get(i).getscheduletypeid() +"#"+ alertresult.getwaybilldetails().get(i).getdutytypeid()+"#"+alertresult.getwaybilldetails().get(i).getdepotid()+"#"+alertresult.getwaybilldetails().get(i).getscheduleno()+"'/>");
					String sch_name=alertresult.getWaybillDetails().get(i).getSCHEDULENAME();
					String sch_no=alertresult.getWaybillDetails().get(i).getCONDNAME();
					int sch_type_id=alertresult.getWaybillDetails().get(i).getSCHEDULETYPEID();
					String schdule_no=alertresult.getWaybillDetails().get(i).getSCHEDULENO();
//					String vehicle_id=alertresult.getWaybillDetails().get(i).getVehicleId();
					String vehicle_no=alertresult.getWaybillDetails().get(i).getVEHICLENO();
					String depot_name=alertresult.getWaybillDetails().get(i).getDepotCd();
					String division_name=alertresult.getWaybillDetails().get(i).getOrgName();
				 
				 String sql="insert into  daily_schedule_mapping_vehicle(depot_name,schedule_type_id,schedule_no,schedule_name,vehicle_no,updated_date," +
                        "division,schedule_no_name,operated_date) values('"+depot_name+"',"+sch_type_id+","+schdule_no+",'"+sch_name+
                                "','"+vehicle_no+"','2017-07-01','"+division_name+"','"+sch_no+"','2017-07-01')";
				 
				 
				session.beginTransaction();
				 Query query =session.createSQLQuery(sql);
				 count =query.executeUpdate();
				 session.getTransaction().commit();
//					ja.add(alertresult.getWaybillDetails().get(i).getCONDNAME());
//					ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
//					ja.add(alertresult.getWaybillDetails().get(i).getScheduleTypeName());
//					ja.add(alertresult.getWaybillDetails().get(i).getShiftTypeName());
//					System.out.println("shift-----"+alertresult.getWaybillDetails().get(i).getShiftTypeName());
//					ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
//					ja.add(alertresult.getWaybillDetails().get(i).getDepotCd());
//					ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
					
//					array.add(ja);
				
				 
				 }
//				 result.put("aaData", array);
			System.out.println("Count===="+count);
				 } catch (Exception ex) {
				 ex.printStackTrace();
				 }
				 return count;
				 }
	
	
	
	
	
	public static String rbKey = String.valueOf(getSysParameterForVts());

	public List<com.trimax.ws.vts.vtsutility.VtsDataModel> getDeviceIMEI(
			String FromDate, String TillDate) {
		List<com.trimax.ws.vts.vtsutility.VtsDataModel> imeiList = null;
		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse result = port
					.getIMEIList(rbKey);
			for (int i = 0; i < result.getVtsDatamodel().size(); i++) {
				imeiList = result.getVtsDatamodel();
			}
		} catch (Exception ex) {

		} finally {
			// session.close();
		}
		return imeiList;
	}

	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return param;
	}
	
	
	
	public boolean saveEditedDetailsWebserviceCall(int id,String vehicleNo,int shiftId)
	{
		Session sessionITS=null;
//		Transaction transaction = null;
		int resultId;
		//session =  HibernateUtilVtu.getSession();
		sessionITS =  HibernateUtil.getSession("");
		try{
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
		if(!vehicleNo.equals("NA")){
		try
		{
			
			
				
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				System.out.println("rbKey==="+rbKey);
				
			
			
			Query query1 = sessionITS.createSQLQuery("select vehicle_id as vehicle_id from vehicle where license_number =? limit 1");
			query1.setString(0, vehicleNo);
			resultId = (Integer) query1.uniqueResult(); //same vehicle id for all records.
			System.out.println("desired id is"+resultId);
		
			int usrid = Integer.parseInt(ServletActionContext
					.getRequest().getSession().getAttribute("userid")
					.toString());
			
			port.updateScheduleMappingVehicleNo(vehicleNo,resultId,usrid,id,shiftId,rbKey);
			//String hql = "update pisdb.schedule_mapping_vehicle set vehicle_no ='"+vehicleNo +"',vehicle_id='"+resultId+"',updated_date=now() , updated_by= "+usrid+" where schedule_no="+id+"";
			// Query query= session.createSQLQuery(hql);
			// query.executeUpdate();
//			 System.out.println("updated");

			
			isSuccess = true;			
		}
		catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			
		}		
		}
	
		else{
			int usrid = Integer.parseInt(ServletActionContext
					.getRequest().getSession().getAttribute("userid")
					.toString());
//			if vehicle for schedule is Selecting NA by user
			//String hql = "update pisdb.schedule_mapping_vehicle set vehicle_no ='',vehicle_id=0,updated_date=now() , updated_by= "+usrid+" where schedule_no="+id+"";
			 //Query query= session.createSQLQuery(hql);
			// query.executeUpdate();
			port.updateScheduleMappingWithoutVehicleNo(usrid,id,shiftId,rbKey);
			 System.out.println("updated");
			isSuccess=true;
		}
		}catch(Exception ex){
			
		}finally{
			if(sessionITS != null){
				sessionITS.close();
			}
		}
		
		return isSuccess;	
	}
	
	
	//public ScheduleMap getScheduleMapDetailsEdit(int scheduleMappingId, int shiftId, int depotId,int schdId)
	public ScheduleMap getScheduleMapDetailsEdit(int scheduleMappingId)
	{
		ScheduleMap schMapData = null;
		//List<ScheduleMap> result=null;
		Session session=null;
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		// TODO initialize WS operation arguments here
		// Query To Get Schedule Header Information..
		System.out.println("schdId==="+scheduleMappingId);
		try{
		//VtsResponse6 alertresult = port.getScheduleMappingDetailsEdit(scheduleMappingId,shiftId,rbKey);
		
			
			
			session =  HibernateUtil.getSession("");
			 String sql ="SELECT schedule_id as schedule_id,shift_type_name,duty_type_id,sm.depot_id depot_id,IFNULL(s.schedule_number,'') as schedule_no,sm.depot_name as depot_name," +
				   		" vehicle_no from schedule_mapping_vehicle1 sm left join schedule s on s.schedule_id=sm.schedule_no " +
				   		"left join schedule_type st on st.schedule_type_id=sm.schedule_type_id " +
				   		"left join shift_type sf on sf.shift_type_id=sm.duty_type_id " +
				   		" where sm.status='ACTIVE' AND schedule_id="+scheduleMappingId+" group by sm.schedule_no";
			 Query query = session.createSQLQuery(sql)
			  			.addScalar("schedule_id", Hibernate.STRING)
							 .addScalar("schedule_no", Hibernate.STRING)
//							 .addScalar("schedule_type_name", Hibernate.STRING)
							 .addScalar("vehicle_no", Hibernate.STRING)
							  .addScalar("depot_name", Hibernate.STRING)
							  .addScalar("shift_type_name", Hibernate.STRING)
							   .addScalar("duty_type_id", Hibernate.STRING)
							    .addScalar("depot_id", Hibernate.STRING);
				    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				    List<Map<String, Object>> aliasToValueMapList1 = query.list();
				    schMapData=new ScheduleMap();
					for(int j=0;j<aliasToValueMapList1.size();j++){
						Map<String, Object> rs = aliasToValueMapList1.get(j);
						schMapData.setSchedule_no(rs.get("schedule_no").toString());
						schMapData.setVehicle_no(rs.get("vehicle_no").toString());
						schMapData.setDepot_name(rs.get("depot_name").toString());
						schMapData.setSchedule_type_id(Integer.parseInt(rs.get("schedule_id").toString()));
						schMapData.setShift_type_id(Integer.parseInt(rs.get("duty_type_id").toString()));
						schMapData.setShiftName(rs.get("shift_type_name").toString());
						schMapData.setDepot_id(Integer.parseInt(rs.get("depot_id").toString()));
					}
					
		//Webservice call
		     /*     JSONArray array = new JSONArray();
					for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
						 JSONArray ja = new JSONArray();
						
						     //System.out.println("alertresult.getWaybillDetails().get(i).getSCHEDULENO()=="+alertresult.getWaybillDetails().get(i).getSCHEDULETYPEID());
						 System.out.println(alertresult.getWaybillDetails().get(i).getId());
//							ja.add("<input type='checkbox' class='group-checkable' id='"+ alertresult.getWaybillDetails().get(i).getSCHEDULETYPEID()+ "' value='"+ alertresult.getWaybillDetails().get(i).getSCHEDULETYPEID() + "'/>");
						     schMapData=new ScheduleMap();
						     schMapData.setSchedule_no(alertresult.getWaybillDetails().get(i).getSCHEDULENO());
						     schMapData.setShiftName(alertresult.getWaybillDetails().get(i).getShiftTypeName());
//							ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
						     schMapData.setVehicle_no(alertresult.getWaybillDetails().get(i).getVEHICLENO());
						     schMapData.setDepot_name(alertresult.getWaybillDetails().get(i).getDepotCd());
						     schMapData.setSchedule_type_id(alertresult.getWaybillDetails().get(i).getSCHEDULETYPEID());
						     schMapData.setForm_four_id(alertresult.getWaybillDetails().get(i).getId());
						     schMapData.setDepot_id(depotId);
//						     System.out.println("shift id is "+alertresult.getWaybillDetails().get(i).getDutyTypeId());
						     
						     schMapData.setShift_type_id(alertresult.getWaybillDetails().get(i).getDutyTypeId());
//							ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
							
							
						
						 
						 }
						 */
//						 result.put("aaData", array);
						
			 
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally{
		
		return schMapData;
	}
}
	
	
	public String checkVehicleScheduleMappingWebServiceCall(String vehicleNo, int Schedule_id,int schduleTypeId, String schedule_name)
	{
		
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		// TODO initialize WS operation arguments here
		// Query To Get Schedule Header Information..
		
//		System.out.println("sch id===="+schduleTypeId);  //  new schedule schedule_type id
		int schedule_type_id=0;
		if(!vehicleNo.equals("NA")){
		/*try
		{
			System.out.println("check 2");
			VtsResponse6 alertresult = port.getScheduleMappingDetailsEditWebservicecall(vehicleNo,Schedule_id,shiftId,rbKey);
//			String qur="select IFNULL(schedule_name,'') as schedule_name from pisdb.schedule_mapping_vehicle where vehicle_no='"+vehicleNo+"' ";
//
//			Query qury2 = session.createSQLQuery(qur)
//		  			.addScalar("schedule_name", Hibernate.STRING);
//			List results = qury2.list();
			String schname="";
//			System.out.println("sch name is-------"+schname);
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				 JSONArray ja = new JSONArray();
				 schname =  alertresult.getWaybillDetails().get(i).getSCHEDULENAME();
			}
			if(alertresult.getWaybillDetails().size()==0){
//			System.out.println("size is-----"+alertresult.getWaybillDetails().size());
			ismapped="";
	}
			else 
			{
				ismapped=schname;
			}
			}
		catch(Exception e){
			e.printStackTrace();
			ismapped = schname;
		}
		finally{
//			if(session != null){
//				session.close();
//			}
		}	
		
		*/
		
		try
		{
			Common common=new Common();
			Session session1 = HibernateUtil.getSession("");
			  String schType = "select schedule_type_id from schedule_mapping_vehicle1 WHERE vehicle_no='"+vehicleNo+"' limit 1";
			 // int schedule_type_id = Integer.parseInt(common.getDBResultStr(session1, schType, "schedule_type_id"));

			  if(common.getDBResultStr(session1, schType, "schedule_type_id").equals("")){
				  schedule_type_id = 0;

			 }else{
				  schedule_type_id = Integer.parseInt(common.getDBResultStr(session1, schType, "schedule_type_id"));
			 }
			  
			  //			  System.out.println("sche type id==="+schedule_type_id);    // already existed schedule schedule_type_id
			 
			  if(schedule_type_id !=4 && schedule_type_id !=1){     // if no night service and general shift then assign any schedule
//				  System.out.println("no night ser and gen shift");
			String qur="select IFNULL(schedule_name,'') as schedule_name from schedule_mapping_vehicle1 where " +
					" vehicle_no='"+vehicleNo+"' and schedule_no!='"+Schedule_id+"' ";

			Query qury2 = session1.createSQLQuery(qur)
		  			.addScalar("schedule_name", Hibernate.STRING);
			qury2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qury2.list();
			
			String schname="";
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> list = aliasToValueMapList.get(i);
				 JSONArray ja = new JSONArray();
				   schname = list.get("schedule_name").toString();
			}

			if(aliasToValueMapList.size()==0){      // no Schedule then assign any schedule
			ismapped=""; 
	}
			else 
			{
				ismapped=schname;       
			}
			}else{     // if in night service or general shift schedule you are assigning to other schedule 
				String scheduleno = "select count(distinct schedule_no) schedule_no from schedule_mapping_vehicle1 WHERE vehicle_no='"+vehicleNo+"' ";
				  int schedulNo = Integer.parseInt(common.getDBResultStr(session1, scheduleno, "schedule_no"));
//				  System.out.println("sche allotted is ==="+schedulNo);
				  if(schedulNo !=2 ){     // if vehicle is already mapped with 2 schedule (1-ns,2-gs)
				if(schedule_type_id == 4){        // in night shift only gen shift sch can mapped 
					if(schduleTypeId ==1){
//					System.out.println("assigned to gen==");
					String scheduleType = "select IFNULL(schedule_name,'') as schedule_name from schedule_mapping_vehicle1 WHERE vehicle_no='"+vehicleNo+"' and schedule_type_id=1 limit 1";
					  String schedulname = common.getDBResultStr(session1, scheduleType, "schedule_name");
					  if(schedulname.equalsIgnoreCase("") || schedulname == null){
						  ismapped="";
					  }else{
						  ismapped = schname;
					  }
				}else{
					ismapped = schname;
				}
				}else if(schedule_type_id == 1){   // in gen shift only allow to mapped night service
//					System.out.println("in gen if");
					if(schduleTypeId == 4){
//					System.out.println("assigned to night==");
					String scheduleType = "select IFNULL(schedule_name,'') as schedule_name from schedule_mapping_vehicle1 WHERE vehicle_no='"+vehicleNo+"' and schedule_type_id=4 limit 1";
					  String schedulname = common.getDBResultStr(session1, scheduleType, "schedule_name");
//					  System.out.println("sche name==="+schedulname);
					  if(schedulname.equalsIgnoreCase("") || schedulname == null){
						  ismapped="";
					  }else{
						  ismapped = schname;
					  }
					
				}else{
//					already vehicle mapped with 2 schedule (1-ns,2-gs) then not able to assign to other schedule
					ismapped=schedule_name;
				}
					
				}
				  else{
//					  if not ns and gs 
					  ismapped = schedule_name;
				  }
				  }else{
//					  more than 2 sch cant assigned 
					  ismapped = schedule_name;
				  }
				
//				ismapped="";
			}
		}
		catch(Exception e){
			e.printStackTrace();
			ismapped = schname;
		}
		finally{
//			if(session != null){
//				session.close();
//			}
		}	
		
		}
		return ismapped;
	}
	
	public String checkVehicleScheduleMappingEditWebServiceCall(String vehicleNo, int Schedule_id, int shiftId)
	{
		
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		// TODO initialize WS operation arguments here
		// Query To Get Schedule Header Information..
//		System.out.println("vehicleNo==="+vehicleNo+"...schedule"+Schedule_id+".....shiftId"+shiftId);
		
		
		
		if(!vehicleNo.equals("NA")){
		try
		{
			VtsResponse6 alertresult = port.getScheduleMappingDetailsEditWebservicecall1(vehicleNo,Schedule_id,shiftId,rbKey);
//			String qur="select IFNULL(schedule_name,'') as schedule_name from pisdb.schedule_mapping_vehicle where vehicle_no='"+vehicleNo+"' ";
//
//			Query qury2 = session.createSQLQuery(qur)
//		  			.addScalar("schedule_name", Hibernate.STRING);
//			List results = qury2.list();
			String schname="";
//			System.out.println("sch name is-------"+schname);
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				 JSONArray ja = new JSONArray();
				 schname =  alertresult.getWaybillDetails().get(i).getSCHEDULENAME();
			}
			if(alertresult.getWaybillDetails().size()==0){
//			System.out.println("size is-----"+alertresult.getWaybillDetails().size());
			ismapped="";
	}
			else 
			{
				ismapped=schname;
			}
			}
		catch(Exception e){
			e.printStackTrace();
			ismapped = schname;
		}
		finally{
//			if(session != null){
//				session.close();
//			}
		}	
		}
		return ismapped;
	}
	
	
	public String checkScheduleNoScheduleMappingWebServiceCall(String vehicleNo, String Schedule_name ,int scheduleid)
	{
		
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		// TODO initialize WS operation arguments here
		// Query To Get Schedule Header Information..
//		System.out.println("rbKey==="+rbKey);
		
		
		
		if(!vehicleNo.equals("NA")){
		try
		{
//			System.out.println("in check 1");
			Session session1 = HibernateUtil.getSession("");
		
			//VtsResponse6 alertresult = port.getScheduleNoScheduleMappingWebServiceCall(vehicleNo,Schedule_name,scheduleid,rbKey);
			String qur="select IFNULL(schedule_name,'') as schedule_name from schedule_mapping_vehicle1 where schedule_no='"+scheduleid+"' ";
//
			Query qury2 = session1.createSQLQuery(qur)
		  			.addScalar("schedule_name", Hibernate.STRING);
//			System.out.println("check already== "+qury2);
			//List results = qury2.list();
			qury2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qury2.list();
			
			String schname="";
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> list = aliasToValueMapList.get(i);
				 JSONArray ja = new JSONArray();
				   schname = list.get("schedule_name").toString();
			}
//			System.out.println("sch name is-------"+schname);
//			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
//				 JSONArray ja = new JSONArray();
//				 schname =  alertresult.getWaybillDetails().get(i).getSCHEDULENAME();
//			}
			if(aliasToValueMapList.size()==0){
//			System.out.println("size is-----"+alertresult.getWaybillDetails().size());
			ismapped="";
	}
			else 
			{
				ismapped=schname;
			}
			}
		catch(Exception e){
			e.printStackTrace();
			ismapped = schname;
		}
		finally{
//			if(session != null){
//				session.close();
//			}
		}	
		}
		return ismapped;
	}
	
	
	
	public List getVehicleID2(int parentid) {
//		System.out.println("get Vehicle===");
		List list = new ArrayList();
		if(parentid!=0){
		String qry = "select vehicle_id,license_number from vehicle WHERE `org_chart_id` = "+parentid+" AND `deleted_status` = '0' AND `cause_status` = 'N' ";
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
//System.out.println("size of veh"+aliasToValueMapList.size());
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					list.add(rs.get("vehicle_id").toString());
					// list.add( rs.get("license_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			
		}
		}
		else{
			list.add("NA");
			System.out.println("vehicle id =0 bcz Selecting NA");
		}
		return list;
	}
	
	
	public List getVehicleName1(int parentid) {
		List list = new ArrayList();
		String qry = "select vehicle_id,license_number from vehicle WHERE `org_chart_id` = "+parentid+" AND `deleted_status` = '0' AND `cause_status` = 'N' ";
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					// list.add(rs.get("vehicle_id").toString());
					list.add(rs.get("license_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}
	
	
	@SuppressWarnings("rawtypes")
	public List getVehicleId(int parentID) {
		List list = new ArrayList();
		String qry = "select vehicle_id,license_number from vehicle WHERE `org_chart_id` = "+parentID+" AND `deleted_status` = '0' AND `cause_status` = 'N' ";

		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					// list.add(rs.get("vehicle_id").toString());
					list.add(rs.get("vehicle_id").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}
			
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getVehicleName(int parentID) {
		List list = new ArrayList();
		String qry = "select vehicle_id,license_number from vehicle WHERE `org_chart_id` = "+parentID+" AND `deleted_status` = '0' AND `cause_status` = 'N' ";

		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					// list.add(rs.get("vehicle_id").toString());
					list.add(rs.get("license_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List getShiftId(int parentID) {
		List list = new ArrayList();
		String qry = "select shift_type_id,shift_type_name from schedule s inner join schedule_type st on st.schedule_type_id=s.schedule_type " +
				"inner join shift_type sf on sf.schedule_type_id=st.schedule_type_id where s.schedule_id="+parentID;
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					// list.add(rs.get("vehicle_id").toString());
					list.add(rs.get("shift_type_id").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}
			
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getShiftName(int parentID) {
		List list = new ArrayList();
//		String qry = "select shift_type_id,shift_type_name from schedule s inner join schedule_type st on st.schedule_type_id=s.schedule_type " +
//				"inner join shift_type sf on sf.schedule_type_id=st.schedule_type_id where s.schedule_id="+parentID;
		String qry = "SELECT schedule_id,schedule_number  FROM  schedule s " +
				"where (s.status='ACTIVE' or s.status='NEW') and " +
				"schedule_id not in(select distinct(schedule_no) from schedule_mapping_vehicle1) " +
				"and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and s.depot_id="+parentID;
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					// list.add(rs.get("vehicle_id").toString());
					list.add(rs.get("shift_type_name").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List getScheduleId(int parentID) {
		List list = new ArrayList();
		String qry = "SELECT schedule_id,schedule_number  FROM  schedule s " +
				"where (s.status='ACTIVE' or s.status='NEW') and  " +
				"schedule_id not in(select distinct(schedule_no) from schedule_mapping_vehicle1) " +
				"and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and s.depot_id="+parentID;
//		SELECT * FROM `vehicle` WHERE `org_chart_id` = '31' AND `deleted_status` = '0' AND `cause_status` != 'S'
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					// list.add(rs.get("vehicle_id").toString());
					list.add(rs.get("schedule_id").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}
			
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getScheduleName(int parentID) {
		List list = new ArrayList();
		String qry = "SELECT schedule_id,schedule_number  FROM  schedule s " +
				"where (s.status='ACTIVE' or s.status='NEW') and  " +
				"schedule_id not in(select distinct(schedule_no) from schedule_mapping_vehicle1) " +
				"and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and s.depot_id="+parentID;
//		SELECT * FROM `vehicle` WHERE `org_chart_id` = '31' AND `deleted_status` = '0' AND `cause_status` != 'S'
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					// list.add(rs.get("vehicle_id").toString());
					list.add(rs.get("schedule_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}
	}
//	public boolean saveDetailsWebserviceCall(int depotid,String org_name,int schedule_type_id,int service_type_id,int form_four_id,
//			int schedulelist1,String schedule_number_name,String start_time,String end_time,int shiftlist1,int route_id,String route_number,String route_direction,int trip_number,String vehicle_id,String license_number,String is_dead_trip,String device_id)
//	{
	public boolean saveDetailsWebserviceCall(int depotid,String org_name,int schedule_type_id,int service_type_id,int schedulelist1,String schedule_number,String schedule_type_name,String vehicle_id,String license_number,String device_id,String division,int user_id)
	{
		 HttpServletRequest request=ServletActionContext.getRequest();
		Session session = null;
		Session sessionITS=null;
//		Transaction transaction = null;
		int resultId;
		//session =  HibernateUtilVtu.getSession("");
		sessionITS =  HibernateUtil.getSession("");
		
		try
		{

			//Transaction tx=sessionITS.beginTransaction();

			
//			System.out.println(depotid+"--"+org_name+"--"+schedule_type_id+"--"+service_type_id+"--"+form_four_id+"--"+schedulelist1+"--"+
//                  schedule_number_name+"--"+start_time+"--"+shiftlist1+"--"+route_id+"--"+route_number+"--"+route_direction+"--"+trip_number+"--"+vehicle_id+"--"+license_number);
//			String hql = "INSERT INTO schedule_mapping_vehicle1 (depot_id, depot_name, schedule_type_id,service_type_id," +
//					"form_four_id,schedule_no,schedule_name,start_time,end_time,duty_type_id,route_id,route_number,route_direction,trip_number,vehicle_id," +
//					"vehicle_no,device_id,platform_ids,updated_date,is_dead_trip)  VALUES ("+depotid+", '"+org_name+"', "+schedule_type_id+","+service_type_id+","+form_four_id+","+schedulelist1+",'"+schedule_number_name+"'," +
//							"'"+start_time+"','"+end_time+"',"+shiftlist1+","+route_id+",'"+route_number+"','"+route_direction+"',"+trip_number+","+vehicle_id+",'"+license_number+"','"+device_id+"',"+"' '"+",now(),"+is_dead_trip+")";
			
			System.out.println(depotid+"--"+org_name+"--"+schedule_type_id+"--"+service_type_id+"--"+schedulelist1+"--"+
	                  schedule_number+"--"+vehicle_id+"--"+license_number);
				String hql = "INSERT INTO schedule_mapping_vehicle1 (depot_id,depot_name,schedule_type_id,service_type_id,schedule_no,schedule_name,vehicle_id,vehicle_no,device_id,inserted_by,updated_date)  VALUES ("+depotid+", '"+org_name+"', "+schedule_type_id+","+service_type_id+","+schedulelist1+",'"+schedule_number+"'," +
								" "+vehicle_id+",'"+license_number+"','"+device_id+"',"+user_id+",now())";
			
			Query query= sessionITS.createSQLQuery(hql);
			 query.executeUpdate();
//			10--Depot-07--3--3--12325--6164--V-335E/26-All Days--20:50:00--5--13512--KBS-D-7--DOWN--5--1366--KA01F3924
			
			
			// tx.commit();
			isSuccess = true;			
		}
		catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			if(sessionITS != null){
				sessionITS.close();
			}
			
		}			
		
		return isSuccess;	
	}
	
	public int getTotalRecordsData(int total, HttpServletRequest request) {
		
		System.out.println("Enter into totalRecord()");
		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*
		 * Query query = session.createSQLQuery(
		 * "select count(*) as count from device_type where deleted_status=0 "
		 * ).addScalar( "count", Hibernate.INTEGER); List<Integer> list =
		 * query.list(); int cnt = list.get(0); System.out.println(cnt);
		 */
		//System.out.println("Hii in Revenue Doa>>>");
		
		
//		Criteria criteria ;
//		if(viewdeletedrecord.equalsIgnoreCase("Y"))
//		{
//			 criteria = session.createCriteria(LogsheetCancelType.class);
//		}else{
//			 criteria = session.createCriteria(LogsheetCancelType.class);
//		     criteria.add(Restrictions.eq("status", 'Y'));
//		     System.out.println("Enter into else part");
//		}
		
		try {
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql = "Select count(*) count from( select count(*) count from daily_schedule_mapping_vehicle group by schedule_no )a";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		
		

		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
		
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllData(String id,HttpServletRequest request,String depotNo,String date) {

//		System.out.println("Hii in Schedule Doa getData1()");
		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();

		Session session =null;
		
		try {
//			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
//			System.out.println("id====="+id);
			String searchSQL = "";
//			String sql = " select schedule_no,schedule_name,ifnull(shift_type_name,'') shift_type," +
//					"schedule_no_name,vehicle_no,depot_name,ifnull(division,'') division,ifnull(schedule_type_name,'') schedule_type_name," +
//					"ifnull(depot_id,'') depot_id,operated_date from daily_schedule_mapping_vehicle where depot_id='"+depotNo+"' and operated_date='"+date+"' ";

//			String sql = " select schedule_no,schedule_name,ifnull(dsm.shift_type_name,'') shift_type, "+
//					    " schedule_no_name,vehicle_no,depot_name,ifnull(division,'') division, "+
//					    " ifnull(schedule_type_name,'') schedule_type_name,ifnull(depot_id,'') depot_id,operated_date "+
//					    " from daily_schedule_mapping_vehicle dsm "+
//						" inner join new_shift_type nst on nst.shift_type_name=dsm.shift_type_name "+
//						" where dsm.depot_id='"+depotNo+"' and dsm.operated_date='"+date+"' ";

			String sql = " select * from( "+
					" select schedule_no,schedule_name,ifnull(dsm.shift_type_name,'') shift_type,  schedule_no_name,vehicle_no,depot_name, "+
					" ifnull(division,'') division,  ifnull(schedule_type_name,'') schedule_type_name,ifnull(depot_id,'') depot_id,operated_date  "+
					" from daily_schedule_mapping_vehicle dsm  "+
					" inner join new_shift_type nst on nst.shift_type_name=dsm.shift_type_name  "+
					" where dsm.depot_id='"+depotNo+"' and dsm.operated_date='"+date+"'  ";
			//						" group by schedule_no,nst.shift_type_name ";

			
			// sql += " order by " + COL_NAME + " " + DIR;

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			
			
//			Criteria criteria ;
//			if(viewdeletedrecord.equalsIgnoreCase("Y"))
//			{
//				 criteria = session.createCriteria(LogsheetCancelType.class);
//			}else{
//				 criteria = session.createCriteria(LogsheetCancelType.class);
//			     criteria.add(Restrictions.eq("status", 'Y'));
//			     System.out.println("Enter into else"+criteria);
//			}
//			Criteria criteria = session.createCriteria(RevenueSector.class);
//			criteria.add(Restrictions.eq("deleted_status", 0));
//			if (!request.getParameter("sSearch").equals("")) {
//				String search_term = request.getParameter("sSearch").trim();
//
//				Junction conditionGroup = Restrictions.disjunction();
//
//				conditionGroup.add(Restrictions.like("sector_name","%"
//						+ search_term + "%"));
//				conditionGroup.add(Restrictions.like("status", search_term,
//						MatchMode.START));
//				criteria.add(conditionGroup);getDailyScheduleMapping
//				// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
//				// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
//			}
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += "and (schedule_no_name like '%" + search_term + "%'";
				 sql += " OR schedule_name like '%"+search_term+"%'";
				 sql += " OR vehicle_no like '%"+search_term+"%'";
				 sql += " OR depot_name like '%"+search_term+"%' )";
//				// sql +=
//				// " OR form_four.schedule_number_name like '"+search_term+"%'";
//				// sql += " OR driver1.TOKEN like '"+search_term+"%'";
//				// sql += " OR driver1.EMPLOYEE_NAME like '"+search_term+"%'";
//				// sql += " OR driver2.TOKEN like '"+search_term+"%'";
//				// sql += " OR driver2.EMPLOYEE_NAME like '"+search_term+"%'";
//				// sql += " OR conductor1.TOKEN like '"+search_term+"%'";
//				// sql +=
//				// " OR conductor1.EMPLOYEE_NAME like '"+search_term+"%'";
//				// sql += " OR conductor2.TOKEN like '"+search_term+"%'";
//				// sql +=
//				// " OR conductor2.EMPLOYEE_NAME like '"+search_term+"%'";
//				// sql +=
//				// " OR schedule_type.schedule_type_name like '"+search_term+"%'";
//				// sql +=
//				// " OR service_type.service_type_name like '"+search_term+"%'";
//				// sql += " OR gen_logsheet.status like '"+search_term+"%'";
//				// sql +=
//				// " OR vehicle.license_number like '%"+search_term+"%')";
			}
			
		//	sql+=" group by schedule_no,nst.shift_type_name";
			
			sql+="order by schedule_no,nst.shift_type_name,dsm.updated_date desc "+
				 " ) a group by schedule_no,shift_type ";
			
			
//			if (!col.equals("")) {
//				if (dir.equals("asc")) {
//					sql += " order by " + col + " asc";
//				} else {
//					sql += " order by " + col + " desc";
//				}
//			}
			
//			sql += " limit " + request.getParameter("iDisplayStart") + ", "
//					+ request.getParameter("iDisplayLength");
			
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("schedule_no", Hibernate.STRING)
					.addScalar("schedule_name", Hibernate.STRING)
					.addScalar("shift_type", Hibernate.STRING)
					.addScalar("schedule_type_name", Hibernate.STRING)
					.addScalar("vehicle_no", Hibernate.STRING)
					.addScalar("depot_name", Hibernate.STRING)
					.addScalar("depot_id", Hibernate.STRING)
					.addScalar("division", Hibernate.STRING)
					.addScalar("schedule_no_name", Hibernate.STRING)
					.addScalar("operated_date", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			
//			List<LogsheetCancelType> list = criteria.list();
			JSONArray array = new JSONArray();
		//	System.out.println("List size----->" + list.size() + "\t"
			//		+ request.getParameter("iDisplayStart"));
			System.out.println("Size list==="+aliasToValueMapList.size());
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("schedule_no").toString()+","+rs.get("schedule_name").toString()+","+rs.get("schedule_type_name").toString()+","+rs.get("shift_type").toString()+","+rs.get("vehicle_no").toString()+","+rs.get("depot_name").toString()+","+rs.get("division").toString()+","+rs.get("depot_id").toString()+","+rs.get("operated_date").toString()
						+ "' value='"
						+rs.get("schedule_no").toString()+","+rs.get("schedule_name").toString()+","+rs.get("schedule_type_name").toString()+","+rs.get("shift_type").toString()+","+rs.get("vehicle_no").toString()+","+rs.get("depot_name").toString()+","+rs.get("division").toString()+","+rs.get("depot_id").toString()+","+rs.get("operated_date").toString()+"'/>");
//				ja.add(rs.get("etm_number").toString());
				String sch_no=rs.get("schedule_no").toString();
				String sch_name=rs.get("schedule_name").toString();
				String shift_type=rs.get("shift_type").toString();
				String vehicle_no=rs.get("vehicle_no").toString();
				String depot_name=rs.get("depot_name").toString();
				String division=rs.get("division").toString();
				String schedule_type_name=rs.get("schedule_type_name").toString();
				
//				String division_name=etmdao.getDevisionName(division_id);
//				String depot_name=etmdao.getDepotName(division_id,depot_id);
//				ja.add(rs.get("division_id").toString());
//				ja.add(rs.get("depot_id").toString());
				//ja.add(sch_no);
				ja.add(rs.get("schedule_no_name").toString());
				ja.add(sch_name);
				ja.add(schedule_type_name);
				ja.add(shift_type);
				ja.add(vehicle_no);
				ja.add(depot_name);
//				ja.add(division);
//				String etmdate=(String)rs.get("etm_replace_date");
				//System.out.println("sch_no==="+sch_no);
				if(division==null || division.equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(division);
				}
//				ja.add(rs.get("etm_replace_date").toString());
//				String remark=(String)rs.get("remarks");
//				if(remark==null || remark.equalsIgnoreCase("")){
//					ja.add(" ");
//				}else{
//					ja.add(rs.get("remarks").toString());
//				}
//				ja.add(rs.get("remarks").toString());
//				ja.add(list.get(i).getGeo_fence()!=null? "Available":"Not Available");
//				if(list.get(i).getGeo_fence()==null)
//				{
//				ja.add("Geo Fence Not Available");
//				}else{
//					ja.add("Available");
//					ja.add(list.get(i).getStatus());
//					ja.add(list.get(i).getNotes());
				//}
//					if(viewdeletedrecord.equalsIgnoreCase("Y"))
//					{
//						int deletedstatus=list.get(i).getDeleted_status();
//											
//						if(deletedstatus==1)
//						{
//							ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getSector_id()+"'/>Deleted Record");	
//							//ja.add(" ");
//						}else{
//							//ja.add(" ");
//							ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getSector_id()+"'/>Record in Database");	
//						}
//						
//						//ja.add(rs.get("vendor_name").toString());	
//					}else{
//						
//					}
					array.add(ja);
					//System.out.println("Array----->" + array);
				}
					

				// ja.add(list.get(i).getNotes());
				// ja.add(list.get(i).getStatus());

				//array.add(ja);
				//System.out.println("Array----->" + array);
			//}

//			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
//			result.put("iTotalRecords", totalAfterFilter);
//
//			result.put("iTotalDisplayRecords", totalAfterFilter);
          //System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
//			Logger logger = TrimaxLogger.getTrimaxLogger();
//            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
//            SaveRequest.save(request);
//            ErrorLog log = new ErrorLog();
//            log.setMsg(ex.getMessage());
//            ErrorLogDAO.saveException(log);
			ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	
		
	public int getDailyScheduleData(String date,String depot) {
//		System.out.println(" TotalRecord From DailyScheduleMapping");
		Common common = new Common();
		 int count=0;
//		System.out.println("Date===="+date  +"depot==="+depot);
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*
		 * Query query = session.createSQLQuery(
		 * "select count(*) as count from device_type where deleted_status=0 "
		 * ).addScalar( "count", Hibernate.INTEGER); List<Integer> list =
		 * query.list(); int cnt = list.get(0); System.out.println(cnt);
		 */
		//System.out.println("Hii in Revenue Doa>>>");
		
		
//		Criteria criteria ;
//		if(viewdeletedrecord.equalsIgnoreCase("Y"))
//		{
//			 criteria = session.createCriteria(LogsheetCancelType.class);
//		}else{
//			 criteria = session.createCriteria(LogsheetCancelType.class);
//		     criteria.add(Restrictions.eq("status", 'Y'));
//		     System.out.println("Enter into else part");
//		}
		
		try {
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql = "select count(total) count from (select count(*) total from daily_schedule_mapping_vehicle where operated_date='"+date+"' and depot_id="+depot+" group by schedule_no) a" ;
//			String sql = " select count(*) count from daily_schedule_mapping_vehicle where operated_date='"+date+"' and depot_id="+depot+" ";
				System.out.println("daily total"+sql);	
			Query query = HibernateUtil.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					count=Integer.parseInt(rs.get("count").toString());
				}
			}
			HibernateUtil.closeSession();
			
		} catch (Exception e) {

		}
	
		
		return count;
		
	}

	
public int getScheduleMappingCountRecords(String depot) {
		
		System.out.println(" TotalRecord From DailyScheduleMapping");
		Common common = new Common();
		 int count=0;
		Session session = null;
		
		try {
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql = "select count(total) count from (select count(*) total from schedule_mapping_vehicle1 where depot_id='"+depot+"' group by schedule_no) b" ;
//			String sql = " select count(*) count from schedule_mapping_vehicle1 where depot_id='"+depot+"' group by schedule_no ";
			 System.out.println("schedule total"+sql);	
			Query query = HibernateUtil.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					count=Integer.parseInt(rs.get("count").toString());
				}
			}
			HibernateUtil.closeSession();
			
		} catch (Exception e) {

		}
	
		
		return count;
		
	}

public int getAllDataIntoSchedule(String depot,String date ) {
		
		System.out.println("Enter into DailySchedule()");
		Common common = new Common();
		String operateddate="";
		Session session = HibernateUtil.getSession("hibernate.its_Slave_cfg.xml");
		int count1=0;
		String day="";
		String strHolyday="";
		Transaction transaction = null;
		
		int usrid = Integer.parseInt(ServletActionContext
				.getRequest().getSession().getAttribute("userid")
				.toString());
		Date myDate=new Date();
        String DATE_FORMAT = "dd/MM/yyyy";
        String DATE_FORMAT1 = "yyyy-MM-dd";
        try {
		if (date != null) {

			SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
            String strDate1 = sdf1.format(myDate);

            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            Date dt1 = format1.parse(strDate1);
            DateFormat format2 = new SimpleDateFormat("EEEE");
            day = format2.format(dt1).toLowerCase();
            
            System.out.println("day====="+day);

            SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT1);
            strHolyday = sdf2.format(myDate);

        } else {
//            JOptionPane.showMessageDialog(null, "Please Select The Date", "Error Message", JOptionPane.ERROR_MESSAGE);
//            removeAllData();
//            return;
        }
		 String strH = getHolyday(strHolyday);
	        System.out.println("strh==="+strH);
	        if (!strH.equals("NA")) {
	            day = "holiday";
	           // System.out.println("datyyyyyy holy days==== > " + day);
	        } else {
	            strH = day;
	        }
	        System.out.println("DAY>>>>>>>>>>>>>" + day + ">>" + strH);
		
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session.beginTransaction();
//			String sql = " select smv.depot_id depot_id,smv.depot_name depot_name,smv.schedule_type_id schedule_type_id,smv.service_type_id service_type_id,smv.form_four_id form_four_id,smv.schedule_no schedule_no,smv.schedule_name schedule_name,smv.start_time start_time,IFNULL(end_time,'') end_time ,smv.duty_type_id duty_type_id,smv.route_id route_id,smv.route_number route_number,smv.route_direction route_direction,smv.trip_number trip_number," +
//					  "  smv.vehicle_id vehicle_id,smv.vehicle_no vehicle_no,smv.device_id device_id,smv.platform_ids platform_ids,smv.inserted_by inserted_by,smv.inserted_date inserted_date,smv.updated_by updated_by,smv.updated_date updated_date,smv.status status,oc1.org_name division,st.shift_type_name shift_type_name,s.schedule_number schedule_no_name,sp.schedule_type_name schedule_type,is_dead_trip from schedule_mapping_vehicle1 smv " +
//					" inner join schedule s on s.schedule_id=smv.schedule_no  "+
//					" inner join schedule_type sp on sp.schedule_type_id=smv.schedule_type_id  "+
//					" inner join org_chart oc on oc.org_chart_id=smv.depot_id   "+
//					" inner join org_chart oc1 on oc1.org_chart_id=oc.parent_id  "+
//					" inner join shift_type st on st.shift_type_id=smv.duty_type_id  and st.deleted_status=0  "+
//		  			" where smv.depot_id="+depot+" and smv.schedule_name not in (select schedule_name FROM `daily_schedule_mapping_vehicle` WHERE `depot_id` ="+depot+" AND `operated_date` ='"+date+"' AND (DATE(updated_date) = '"+date+"') )";
				
			 String sql="SELECT smv.depot_id,smv.depot_name,ff.form_four_id,shedule.schedule_type_id,shedule.schedule_type_name schedule_type,service.service_type_id, shift.shift_type_id duty_type_id, "+
					 	" ff.schedule_number_name schedule_name, sc.schedule_id schedule_no,sc.schedule_number schedule_no_name, r.route_id,r.route_number,r.route_direction,sd.trip_number, sd.start_time, "+
					 	" sd.end_time end_time,vehicle_id,vehicle_no,device_id,platform_ids,sd.is_dread_trip is_dead_trip,smv.platform_ids platform_ids,smv.inserted_by inserted_by,smv.inserted_date inserted_date,smv.updated_by updated_by,smv.updated_date updated_date," +
					 	" 0 as status,og1.org_name division,og.org_name org_name,shift.shift_type_name FROM schedule sc  "+
					 	" INNER JOIN form_four ff ON ff.schedule_number_id = sc.schedule_id  INNER JOIN weeklyChart wc ON wc.form_four_id = ff.form_four_id  "+
					 	" inner join schedule_details sd on sd.form_four_id = ff.form_four_id  inner join shift_type shift on shift.shift_type_id = sd.shift_type_id  "+
					 	" inner join service_type service on service.service_type_id = schedule_service_type  "+
					 	" inner join schedule_type shedule on shedule.schedule_type_id = sc.schedule_type  inner join route r on r.route_id=sd.route_number_id "+
					 	" inner join schedule_mapping_vehicle1 smv on smv.schedule_name=sc.schedule_number "+
					 	" inner join org_chart og on og.org_chart_id=sc.depot_id  "+
					 	" inner join org_chart og1 on og1.org_chart_id=og.parent_id "+
					 	" WHERE smv.depot_id="+depot+" and smv.schedule_no not in (select schedule_no FROM `daily_schedule_mapping_vehicle` WHERE `depot_id` ="+depot+" "+
					 	" AND  smv.schedule_no  in (select schedule_no FROM `daily_schedule_mapping_vehicle` WHERE `depot_id` ="+depot+" AND `operated_date` ='"+date+"' "+
					 	" AND (DATE(updated_date) = '"+date+"') ) and `operated_date` ='"+date+"'  ) and sc.deleted_status='0' AND sc.status = 'NEW' and sc.current_status='CASE WORKER' "+
					 	" AND if(" + day + "='1'," + day + "='1'," + strH + "='1') AND ff.deleted_status='0' AND ff.current_status='ACTIVE' and sd.deleted_status=0 AND wc.deleted_status='0' "+
					 	" AND wc.status='ACTIVE' group by schedule_id,sd.shift_type_id,sd.trip_number;";
			 
			
			 
			Query query = HibernateUtil.getSession("").createSQLQuery(sql).addScalar("depot_id", Hibernate.STRING).addScalar("depot_name", Hibernate.STRING).addScalar("schedule_type", Hibernate.STRING)
					.addScalar("schedule_type_id", Hibernate.STRING).addScalar("service_type_id", Hibernate.STRING).addScalar("form_four_id", Hibernate.STRING).addScalar("schedule_no", Hibernate.STRING)
					.addScalar("schedule_name", Hibernate.STRING).addScalar("start_time", Hibernate.STRING).addScalar("end_time", Hibernate.STRING).addScalar("duty_type_id", Hibernate.STRING).addScalar("route_id", Hibernate.STRING)
					.addScalar("route_number", Hibernate.STRING).addScalar("route_direction", Hibernate.STRING).addScalar("trip_number", Hibernate.STRING).addScalar("vehicle_id", Hibernate.STRING)
					.addScalar("vehicle_no", Hibernate.STRING).addScalar("device_id", Hibernate.STRING).addScalar("platform_ids", Hibernate.STRING).addScalar("inserted_by", Hibernate.STRING).addScalar("inserted_date", Hibernate.STRING)
					.addScalar("updated_by", Hibernate.STRING).addScalar("updated_date", Hibernate.STRING).addScalar("status", Hibernate.STRING).addScalar("division", Hibernate.STRING).addScalar("shift_type_name", Hibernate.STRING)
					.addScalar("schedule_no_name", Hibernate.STRING)
					.addScalar("is_dead_trip", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			System.out.println("aliasToValueMapList.size()==="+aliasToValueMapList.size());
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
//					System.out.println("schedule_name==="+rs.get("schedule_name").toString());
//					System.out.println("i==========="+i);
					String depot_id=rs.get("depot_id").toString();
					String depot_name =rs.get("depot_name").toString();
					String schedule_type_id =rs.get("schedule_type_id").toString();
					String service_type_id =rs.get("service_type_id").toString();
					String form_four_id =rs.get("form_four_id").toString();
					String schedule_no =rs.get("schedule_no").toString();
					String schedule_name =rs.get("schedule_name").toString();
					String start_time =rs.get("start_time").toString();
					String end_time =rs.get("end_time").toString();
					String duty_type_id =rs.get("duty_type_id").toString();
					String vehicle_no =rs.get("vehicle_no").toString();
					String device_id =rs.get("device_id").toString();
					String platform_ids =rs.get("platform_ids").toString();
					String inserted_by =rs.get("inserted_by").toString();
					String inserted_date =rs.get("inserted_date").toString();
					String updated_by =rs.get("updated_by").toString();
					String updated_date =rs.get("updated_date").toString();
					String status =rs.get("status").toString();
					String division =rs.get("division").toString();
					String shift_type_name =rs.get("shift_type_name").toString();
					String route_id =rs.get("route_id").toString();
					String route_number =rs.get("route_number").toString();
					String route_direction =rs.get("route_direction").toString();
					String trip_number =rs.get("trip_number").toString();
					String vehicle_id =rs.get("vehicle_id").toString();
					String schedule_no_name =rs.get("schedule_no_name").toString();
					String schedule_type =rs.get("schedule_type").toString();
					String is_dead_trip =rs.get("is_dead_trip").toString();
					
					
					
				 String sql1="insert into  daily_schedule_mapping_vehicle(depot_id,depot_name,schedule_type_id,service_type_id,form_four_id,schedule_no,schedule_name,start_time,end_time,duty_type_id,route_id,route_number,route_direction,trip_number," +
				 		"vehicle_id,vehicle_no,device_id,inserted_by,status,division,schedule_no_name,schedule_type_name,shift_type_name," +
                        "operated_date,is_dead_trip) values("+depot_id+",'"+depot_name+"',"+schedule_type_id+","+service_type_id+
                                ","+form_four_id+","+schedule_no+",'"+schedule_name+"','"+start_time+"','"+end_time+"',"+duty_type_id+","+route_id+",'"+route_number+"','"+route_direction+"'," +
                                		""+trip_number+","+vehicle_id+",'"+vehicle_no+"','"+device_id+"',"+usrid+",'"+status+"','"+division+"','"+schedule_no_name+"','"+schedule_type+"','"+shift_type_name+"','"+date+"','"+is_dead_trip+"')";
				 
//				 System.out.println("Sql===="+sql1);
				 
				session.beginTransaction();
				 Query query1 =session.createSQLQuery(sql1);
				 count1 =query1.executeUpdate();
				 session.getTransaction().commit();
//				
					
				}
			}
			HibernateUtil.closeSession();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	
		
		return count1;
		
	}

public String getHolyday(String date) {
    String result = "NA";

//    Connection conn = null;
//    Statement stmt = null;
//    ResultSet rs = null;
    Session session = null;

    if (date != null) {
//        try {
//
////            conn = DBConnect.getConnection();
////            stmt = conn.createStatement();
//            String query = " SELECT holiday_day FROM `holiday_master` where status='ACTIVE' and holiday_date='" + date + "' ;";
//            System.out.println("\n \t query for holioday --> " + query);
//            boolean flag1 = stmt1.execute(query);
//            if (flag1) {
//                rs = stmt.getResultSet();
//                if (rs != null && rs.next()) {
//                    result = rs.getString("holiday_day");
//                }
//            }
//        } 
        
        
        session =  HibernateUtil.getSession("hibernate.cfg.xml");
    	String shift_type="";
    	String hql1="";
//    	try{
//    	if(!vehicleNo.equals("NA")){
    	try
    	{
//    		System.out.println(deviceNo+"====="+vehicleId+"==="+vehicleNo+"===="+id);
//    		Query query1 = session.createSQLQuery("select vehicle_id as vehicle_id from vehicle where license_number =? limit 1");
//    		query1.setString(0, vehicleNo);
//    		resultId = (Integer) query1.uniqueResult(); //same vehicle id for all records.
    		Transaction tx=session.beginTransaction();
    		
    		String deviceSql="SELECT holiday_day FROM `holiday_master` where status='ACTIVE' and holiday_date='" + date + "' ;";
    		Query queryys = session.createSQLQuery(deviceSql).addScalar("holiday_day", Hibernate.STRING);
    		queryys.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    		List<Map<String, Object>> aliasToValueMapList = queryys.list();
    		System.out.println("aliasToValueMapList.size()==="+aliasToValueMapList.size());

    			for (int i = 0; i < aliasToValueMapList.size(); i++) {
    				Map<String, Object> rs = aliasToValueMapList.get(i);
    				result =rs.get("holiday_day").toString();
    				 
    			System.out.println("holiday_day=="+result);
    			}
    		 
    					
    	}catch (Exception e) {
           // CommonFunction.errorLog(e);
        }
    }
    return result;
}



public int deleteDailyScheduleMapping(String depotId, String opDate) {
	
	Common common = new Common();
	String operateddate="";
	Session session = null;
	int count1=0;
	Transaction transaction = null;
	try {
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session.beginTransaction();
		 Query query = session.createSQLQuery("delete from daily_schedule_mapping_vehicle where depot_id= :depotId and operated_date= :opDate AND (DATE(updated_date) != '"+opDate+"' or updated_date is null)");
		 query.setParameter("depotId", depotId);
		  query.setParameter("opDate", opDate);
		  count1 = query.executeUpdate();
System.out.println("count ====="+count1);
//		 session.getTransaction().commit();
	}catch (Exception e) {
		transaction.rollback();
		e.printStackTrace();
		
		// TODO: handle exception
	}
	return count1;
}

public boolean saveEditedDailySchduleDetails(int id,String vehicleNo,String date,String scheduleNo,String shift_type,String tripno,String reason)
{
	Session session = null;
	boolean isSuccess=false;
//	Transaction transaction = null;
	int resultId;
	String device = "";
	int vehicle_id=0;
	String deviceNo="";
	int c1=0;
	int rowsInserted=0;
	System.out.println("Enter into SaveScheduleDetails");
	session =  HibernateUtil.getSession("hibernate.cfg.xml");
//	try{
//	if(!vehicleNo.equals("NA")){
	try
	{
		String deviceSql="select device_serial_number,v.vehicle_id from vehicle_device vd inner join device d on vd.device_id=d.device_id inner join vehicle v on vd.vehicle_id=v.vehicle_id where license_number='"+vehicleNo+"' and vd.status='active' and d.deleted_status=0 limit 1";
		Query queryys = session.createSQLQuery(deviceSql).addScalar("device_serial_number", Hibernate.STRING).addScalar("vehicle_id", Hibernate.STRING);
		queryys.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = queryys.list();
		System.out.println("aliasToValueMapList.size()==="+aliasToValueMapList.size());
//		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				deviceNo=rs.get("device_serial_number").toString();
				 vehicle_id =Integer.parseInt(rs.get("vehicle_id").toString());
			System.out.println("devicNo=="+deviceNo+"vehicleid "+vehicle_id+"sch no"+scheduleNo);
			}
		Transaction tx=session.beginTransaction();
		int usrid = Integer.parseInt(ServletActionContext
				.getRequest().getSession().getAttribute("userid")
				.toString());
//		System.out.println("User=="+usrid);
		String hql="";
		String hql2="";
		System.out.println();
		
//		if(shift_type.equalsIgnoreCase("Shift 2")){
//			
//			if(vehicleNo.equals("NA")){
//				 hql = "update daily_schedule_mapping_vehicle  "+ 
//							 " set vehicle_no ='"+vehicleNo +"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='"+shift_type+"'  ";
//				}else{
//					 hql = "update daily_schedule_mapping_vehicle "+ 
//							 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicle_id+",device_id='"+deviceNo+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='"+shift_type+"' "+tripno+"   ";
//				 
//				}
//			
//		}else {
//		
//		if(vehicleNo.equals("NA")){
//		 hql = "update daily_schedule_mapping_vehicle  "+ 
//					 " set vehicle_no ='"+vehicleNo +"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+"  ";
//		}else{
//			 hql = "update daily_schedule_mapping_vehicle "+ 
//					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicle_id+",device_id='"+deviceNo+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" "+tripno+"  ";
//		 
//		}
//		}
		
		if(shift_type.equalsIgnoreCase("Shift 2") || shift_type.equalsIgnoreCase("Day 2")){
			hql= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicle_id+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='"+shift_type+"' "+tripno+"  ";
		}else if(shift_type.equalsIgnoreCase("Shift 1")){
		
			hql= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicle_id+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='"+shift_type+"' "+tripno+"  ";
			hql2= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicle_id+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='Shift 2' ";
			
		}else if(shift_type.equalsIgnoreCase("Day 1")){
			
			hql= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicle_id+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='"+shift_type+"' "+tripno+"  ";
			hql2= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicle_id+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='Day 2' ";	
		}else {
			hql= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicle_id+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" "+tripno+"  ";
		}
		
		
		
		
		
		
		
		
		
		System.out.println("Sql===="+hql);
		Query query= session.createSQLQuery(hql);
		rowsInserted = query.executeUpdate();
		
		System.out.println("rowsInserted=="+rowsInserted);
		 if(!hql2.equals("") && rowsInserted>0){			
				 Query query2= session.createSQLQuery(hql2);
				c1= query2.executeUpdate();
			}
		 System.out.println("rowsInserted====="+rowsInserted+"C1====="+c1);
		if(rowsInserted>0){
		 tx.commit();
		isSuccess = true;	
		}else{
			isSuccess = false;
		}
	}
	catch(Exception e){
		e.printStackTrace();
		isSuccess = false;
	}
	
	finally{
		if(session != null){
			session.close();
		}
		
	}
	
	return isSuccess;	
}

public boolean getUpdateEditVehicle(String sch_no,int id,String vehicleNo,String date,String shift_type,String tripno,String reason)
{
	Session session = null;
	boolean isSuccess=false;
//	Transaction transaction = null;
	int resultId;
	int c2=0;
	int vehicle_id2=0;
	String deviceNo ="";
	String hql="";
//	System.out.println("Enter into UpdateDaily Schedule");
	session =  HibernateUtil.getSession("hibernate.cfg.xml");
//	try{
//	if(!vehicleNo.equals("NA")){
	try
	{
		String deviceSql="select device_serial_number,v.vehicle_id from vehicle_device vd inner join device d on vd.device_id=d.device_id inner join vehicle v on vd.vehicle_id=v.vehicle_id where license_number='"+vehicleNo+"' and vd.status='active' and d.deleted_status=0 limit 1";
		Query queryys = session.createSQLQuery(deviceSql).addScalar("device_serial_number", Hibernate.STRING).addScalar("vehicle_id", Hibernate.STRING);
		queryys.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = queryys.list();
		System.out.println("aliasToValueMapList.size()==="+aliasToValueMapList.size());

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				deviceNo =rs.get("device_serial_number").toString();
				 vehicle_id2 =Integer.parseInt(rs.get("vehicle_id").toString());
			System.out.println("devicNo=="+deviceNo+"vehicleid "+vehicle_id2+"sch no"+sch_no+"shift_type="+shift_type);
			}
		
		Transaction tx=session.beginTransaction();
		int usrid = Integer.parseInt(ServletActionContext
				.getRequest().getSession().getAttribute("userid")
				.toString());
		System.out.println("User=="+usrid);
//		if(shift_type.equalsIgnoreCase("Day 1")){
//			String shifttype=getScheduleShift(sch_no,date);
//			System.out.println("Shift====="+shifttype);
//			if(shifttype.equalsIgnoreCase("Day Out")){
//				hql = "update daily_schedule_mapping_vehicle set vehicle_no ='NA',vehicle_id=0,device_id=0,updated_date=now() , updated_by= "+usrid+" where operated_date='"+date+"' and schedule_name='"+sch_no+"' and shift_type_name='Shift 2'";
//			}else{
//				hql = "update daily_schedule_mapping_vehicle set vehicle_no ='NA',vehicle_id=0,device_id=0,updated_date=now() , updated_by= "+usrid+" where operated_date='"+date+"' and schedule_name='"+sch_no+"'";
//			}
//			
//		}else if(shift_type.equalsIgnoreCase("Shift 2")){
//			hql = "update daily_schedule_mapping_vehicle set vehicle_no ='NA',vehicle_id=0,device_id=0,updated_date=now() , updated_by= "+usrid+" where operated_date='"+date+"' and schedule_name='"+sch_no+"' and shift_type_name='"+shift_type+"'";
//		}else{
//		 hql = "update daily_schedule_mapping_vehicle set vehicle_no ='NA',vehicle_id=0,device_id=0,updated_date=now() , updated_by= "+usrid+" where operated_date='"+date+"' and schedule_name='"+sch_no+"'";
//		}
//		 Query query= session.createSQLQuery(hql);
//		int c1= query.executeUpdate();
//		 System.out.println("C1====="+c1);
//		 tx.commit();
//		 if(c1>0){
			 c2=getUpdateVehicleNo(id,vehicleNo,date,deviceNo,vehicle_id2,shift_type,tripno,reason);
//		 }
		 if(c2>0){
			 isSuccess = true;	
		 }else{
		 
//		 Transaction tx1=session.beginTransaction();
//			String hql1 = "update daily_schedule_mapping_vehicle set vehicle_no ='"+vehicleNo+"',updated_date=now() , updated_by= "+usrid+" where schedule_no="+id+"";
//			 Query query1= session.createSQLQuery(hql1);
//			int c2= query.executeUpdate();
//			System.out.println("C2====="+c2);
//			 tx1.commit();
		 
		isSuccess = false;		
		 }
	}
	catch(Exception e){
		e.printStackTrace();
		isSuccess = false;
	}
	
	finally{
		if(session != null){
			session.close();
		}
		
	}
	
	return isSuccess;	
}

public int getUpdateVehicleNo(int id,String vehicleNo,String date,String deviceNo,int vehicleId,String shift_type,String tripno,String reason)
{
	Session session = null;
	boolean isSuccess=false;
//	Transaction transaction = null;
	int resultId;
	System.out.println("Enter into Update Vehicle Schedule");
	session =  HibernateUtil.getSession("hibernate.cfg.xml");
	int c2=0;
	int c1=0;
	String hql1="";
	String hql2="";
//	try{
//	if(!vehicleNo.equals("NA")){
	try
	{
		System.out.println(deviceNo+"====="+vehicleId+"==="+vehicleNo+"===="+id);
//		Query query1 = session.createSQLQuery("select vehicle_id as vehicle_id from vehicle where license_number =? limit 1");
//		query1.setString(0, vehicleNo);
//		resultId = (Integer) query1.uniqueResult(); //same vehicle id for all records.
		Transaction tx=session.beginTransaction();
		int usrid = Integer.parseInt(ServletActionContext
				.getRequest().getSession().getAttribute("userid")
				.toString());
		System.out.println("User=="+usrid);
		if(shift_type.equalsIgnoreCase("Shift 2") || shift_type.equalsIgnoreCase("Day 2")){
			hql1= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicleId+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='"+shift_type+"' "+tripno+"  ";
		}else if(shift_type.equalsIgnoreCase("Shift 1")){
		
			hql1= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicleId+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='"+shift_type+"' "+tripno+"  ";
			hql2= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicleId+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='Shift 2' ";
			
		}else if(shift_type.equalsIgnoreCase("Day 1")){
			
			hql1= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicleId+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='"+shift_type+"' "+tripno+"  ";
			hql2= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicleId+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" and shift_type_name='Day 2' ";	
		}else {
			hql1= "update daily_schedule_mapping_vehicle "+ 
					 " set vehicle_no ='"+vehicleNo +"',vehicle_id="+vehicleId+",device_id='"+deviceNo+"',platform_ids='"+reason+"',updated_date=now(),updated_by= "+usrid+" where operated_date='"+date+"' and schedule_no="+id+" "+tripno+"  ";
		}
		
		System.out.println("hql2==="+hql2);
			 Query query1= session.createSQLQuery(hql1);
			 c2= query1.executeUpdate();
			 if(!hql2.equals("") && c2>0){
					 Query query2= session.createSQLQuery(hql2);
					c1= query2.executeUpdate();
				}
			System.out.println("C2====="+c2+"C1====="+c1);
//			 tx.commit();
//		 
//		isSuccess = true;	
		
		if(c2>0){
			 tx.commit();
			isSuccess = true;	
			}else{
				isSuccess = false;
			}
	}
	catch(Exception e){
		e.printStackTrace();
		isSuccess = false;
	}
	
	finally{
//		if(session != null){
//			session.close();
//		}
		
	}
	
	return c2;	
}

public String getScheduleShift(String sch_no,String date)
{
	Session session = null;
	boolean isSuccess=false;
//	Transaction transaction = null;
	int resultId;
	System.out.println("Enter into find Shift Schedule");
	session =  HibernateUtil.getSession("hibernate.cfg.xml");
	String shift_type="";
	String hql1="";
//	try{
//	if(!vehicleNo.equals("NA")){
	try
	{
//		System.out.println(deviceNo+"====="+vehicleId+"==="+vehicleNo+"===="+id);
//		Query query1 = session.createSQLQuery("select vehicle_id as vehicle_id from vehicle where license_number =? limit 1");
//		query1.setString(0, vehicleNo);
//		resultId = (Integer) query1.uniqueResult(); //same vehicle id for all records.
		Transaction tx=session.beginTransaction();
		
		String deviceSql="SELECT schedule_type_name FROM `daily_schedule_mapping_vehicle` "+
						 " WHERE `operated_date` = '"+date+"' AND `schedule_name` = '"+sch_no+"' LIMIT 1";
		Query queryys = session.createSQLQuery(deviceSql).addScalar("schedule_type_name", Hibernate.STRING);
		queryys.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = queryys.list();
		System.out.println("aliasToValueMapList.size()==="+aliasToValueMapList.size());

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				shift_type =rs.get("schedule_type_name").toString();
				 
			System.out.println("shift_type=="+shift_type);
			}
		 
					
	}
	catch(Exception e){
		e.printStackTrace();
		isSuccess = false;
	}
	
	finally{
//		if(session != null){
//			session.close();
//		}
		
	}
	
	return shift_type;	
}


	
	
}
