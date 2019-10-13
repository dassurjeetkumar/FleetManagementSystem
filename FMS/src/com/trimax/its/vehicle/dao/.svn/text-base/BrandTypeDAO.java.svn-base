package com.trimax.its.vehicle.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.jaxb.xml.trimax.com.VtsResponse6;

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

import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;
import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.model.Device;
import com.trimax.its.model.Vendor;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.usermanagement.model.UserType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.ScheduleMap;
import com.trimax.its.vehicle.model.ServiceLimit;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class BrandTypeDAO {
	@SuppressWarnings("finally")
	public List<BrandType> getBrandTypeList()
	{
		List<BrandType> brandTypeList = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(BrandType.class);
			criteria.createCriteria("serviceType");
			criteria.add(Restrictions.eq("deletedStatus",0));
			criteria.addOrder(Order.asc("brand_type_id"));
			List<Object> det = criteria.list();
			brandTypeList = new ArrayList<BrandType>();
			for(int i=0;i<det.size();i++)
			{
				
				Object result = det.get(i);
				BrandType brandType  = (BrandType) result;
				brandTypeList.add(brandType);
				
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
			return brandTypeList;
		}
	}
	
	@SuppressWarnings("finally")
	public BrandType getBrandTypeDetails(int brandTypeId)
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		BrandType brandTypeData = (BrandType) session.get(BrandType.class, new Integer(brandTypeId));
		brandTypeData.setEffective_start_date(common.getDateToPicker(brandTypeData.getEffective_start_date()));
		brandTypeData.setEffective_end_date(common.getDateToPicker(brandTypeData.getEffective_end_date()));
		return brandTypeData;
		/*BrandType brandTypeData = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(BrandType.class);
			criteria.add(Restrictions.eq("brand_type_id",brandTypeId));
			List<Object> resultSetArray = criteria.list();
			if(resultSetArray.size()>0){
				Object result = resultSetArray.get(0);
				brandTypeData  = (BrandType) result;
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
			return brandTypeData;
		}*/
	}
	
	@SuppressWarnings("finally")
	public BrandType saveEditedDetails(BrandType brandTypeObject)
	{
		Common common = new Common();
		
		Session session = null;
		Transaction transaction = null;
		BrandType brandTypeObjectFromSession=null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			brandTypeObjectFromSession = (BrandType) session.get(BrandType.class, brandTypeObject.getBrand_type_id());
			brandTypeObjectFromSession.setBrand_type_name(brandTypeObject.getBrand_type_name());
			brandTypeObjectFromSession.setNote(brandTypeObject.getNote());
			brandTypeObjectFromSession.setStatus(brandTypeObject.getStatus());
			brandTypeObjectFromSession.setEffective_start_date(common.getDateFromPicker(brandTypeObject.getEffective_start_date()));
			brandTypeObjectFromSession.setEffective_end_date(common.getDateFromPicker(brandTypeObject.getEffective_end_date()));
			brandTypeObjectFromSession.setServiceType(brandTypeObject.getServiceType());
			brandTypeObjectFromSession.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			brandTypeObjectFromSession.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			transaction = session.beginTransaction();
			session.update(brandTypeObjectFromSession);
			session.getTransaction().commit();

			
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
			return brandTypeObjectFromSession;
		}
	}
	
	@SuppressWarnings("finally")
	public int saveBrandType(BrandType makeTypeObject)
	{
		Session session = null;
		int id=0;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			 id = (Integer) session.save(makeTypeObject);
			System.out.println("BrandType edited success....");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(session != null){
					session.close();
			}
			return id;	
		}
	}
	
	@SuppressWarnings("finally")
	public String deleteBrandType(int BrandTypeId)
	{
		String status="error";
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session, "brand_type", BrandTypeId);
			if(status.trim().equalsIgnoreCase("")){
			BrandType makeTypeObject =  (BrandType) session.get(BrandType.class,BrandTypeId);
			
			makeTypeObject.setDeletedStatus(1);
			//makeTypeObject.setStatus("INACTIVE");
			makeTypeObject.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			makeTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));

			transaction = session.beginTransaction();
			session.update(makeTypeObject);
			session.getTransaction().commit();
			}
		}catch(Exception e){
			status="error:";
			e.printStackTrace();
		}
		finally{
			if(session != null){
					session.close();
			}
				//return "status";
		}
		return status;
	}
	
	public List<String> getServiceId() {

		List list = new ArrayList();
		System.out.println("inside get id");
		String qry = "select   service_type_id from service_type where status='ACTIVE' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("service_type_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
		// TODO Auto-generated method stub

	}

	public List<String> getServiceName() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List list = new ArrayList();
		System.out.println("inside get id");
		String qry = "select service_type_name from service_type where status='ACTIVE'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("service_type_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;

	}
	
	public Map<Integer, String> getServiceTypeName() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from ServiceType where status='Active' and deleted_status=0 order by service_type_id");
		
		try{
		List<ServiceType> list = query.list();
		for (ServiceType serviceType : list) {
			resultMap.put(serviceType.getService_type_id(),serviceType.getService_type_name());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return resultMap;
		

	}
	public boolean getDuplicateRecord(String brandname)
	{
		boolean flag=false;
		String qry = "select brand_type_name from brand_type  where deleted_status = 0 and  brand_type_name=?";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setParameter(0, brandname);
		if(query.list().size()>0){flag=true;}
		return flag;
	}
	public boolean getDuplicateRecordForUpdate(String brandname,int id)
	{
		boolean flag=false;
		String qry = "select brand_type_name from brand_type where  brand_type_name=? and brand_type_id=?";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setParameter(0, brandname);
		query.setParameter(1, id);
		if(query.list().size()>0){flag=true;}
		return flag;
	}
	
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			//	Criteria criteria = session.createCriteria(BrandType.class);
				//criteria.add(Restrictions.eq("deletedStatus", 0));
				Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(BrandType.class);
				}else{
					 criteria = session.createCriteria(BrandType.class);
				     criteria.add(Restrictions.eq("deletedStatus", 0));
				}
				if (!request.getParameter("sSearch").equals("")) {
					String search_term = request.getParameter("sSearch").trim();

					Junction conditionGroup = Restrictions.disjunction();
					
					conditionGroup.add(Restrictions.like("brand_type_name",
							"%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("div.service_type_name",
							"%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START ));	
				criteria.add(conditionGroup);
				}
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				
						
				criteria.createCriteria("serviceType", "div");
						//criteria.createCriteria("vendorid", "ven");
						List<BrandType> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			
		}
		return cnt;
	}
	

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from BrandType  ";

			// sql += " order by " + COL_NAME + " " + DIR;
			
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(BrandType.class);
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(BrandType.class);
			}else{
				 criteria = session.createCriteria(BrandType.class);
			     criteria.add(Restrictions.eq("deletedStatus", 0));
			}
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				
					conditionGroup.add(Restrictions.like("brand_type_name",
							"%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("div.service_type_name",
							"%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START ));
				
				criteria.add(conditionGroup);
				
			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			
					criteria.setFirstResult(Integer.parseInt(request
							.getParameter("iDisplayStart")));
					criteria.setMaxResults(Integer.parseInt(request
							.getParameter("iDisplayLength")));
				
			criteria.createCriteria("serviceType", "div");
			//criteria.createCriteria("vendorid", "ven");
			List<BrandType> list = criteria.list();
			
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getBrand_type_id()
						+ "' value='"
						+ list.get(i).getBrand_type_id() + "'/>");
				ja.add(list.get(i).getBrand_type_id());
				ja.add(list.get(i).getBrand_type_name());
				ja.add(list.get(i).getServiceType().getService_type_name());
				// ja.add(list.get(i).getNote());
				ja.add(list.get(i).getNote());
				ja.add(common.getDateFromDbToPicker(list.get(i).getEffective_start_date()));
				ja.add(common.getDateFromDbToPicker(list.get(i).getEffective_end_date()));
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getBrand_type_id()+"'/>Deleted Record");	

						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getBrand_type_id()+"'/>Record in Database1");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
			}
			
			totalAfterFilter = total;//getTotalRecords(total,request,cols,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public JSONObject getServiceLimitDetails(int total, HttpServletRequest request,String col,String dir, String orgchartid){
		System.out.println("in records1111111111111111111111");
		System.out.println("id is--------"+orgchartid);
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			
			sql="SELECT brand_service_id,brand_type_id,brand_type_name,brand_service,note FROM `brand_service` where deleted_status=0";
			
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
				search_term=search_term.trim();
				System.out.println("term is=="+search_term);

				sql += " and brand_type_id like '%"+search_term+"%'";
				sql += " OR brand_type_name like '%"+search_term+"%'";
				sql += " OR brand_service like '%"+search_term+"%'";
				sql += " OR note like '%"+search_term+"%' ";
			}
			sql+=" order by brand_type_id";
			
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			
		    System.out.println("query........."+sql);
		  	 Query query = session.createSQLQuery(sql)
		  			 .addScalar("brand_service_id",Hibernate.INTEGER)
		  			.addScalar("brand_type_id", Hibernate.INTEGER)
						 .addScalar("brand_type_name", Hibernate.STRING)
						 .addScalar("brand_service", Hibernate.STRING)
						 .addScalar("note", Hibernate.STRING);
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					int j=i+1;
					JSONArray ja = new JSONArray();
					ja.add("<input type='checkbox' class='group-checkable' id='"+ rs.get("brand_type_id")+ "' value='"+ rs.get("brand_type_id") 
							 +"#"+rs.get("brand_type_name") +"#"+rs.get("brand_service")+"#"+rs.get("brand_service_id").toString()+" '/>");
					ja.add(rs.get("brand_service_id").toString());
					ja.add(rs.get("brand_type_id").toString());
				    ja.add(rs.get("brand_type_name").toString());
					ja.add(rs.get("brand_service").toString());
					ja.add(rs.get("note").toString());
					
					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				int cnt=0;
				totalAfterFilter = aliasToValueMapList.size();
				result.put("aaData", array);
				String search_term2= request.getParameter("sSearch");
				

				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalServiceLimitData(request,col,dir,orgchartid);//getTotalRecordsForCount(search_term3);
		
			result.put("iTotalRecords",cnt);
			
			result.put("iTotalDisplayRecords", cnt);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	
	public int getTotalServiceLimitData(HttpServletRequest request,String col,String dir, String orgchartid) {
		System.out.println("getTotal service limit Records-----");
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			 
			
			sql="SELECT brand_service_id,brand_type_id,brand_type_name,brand_service,note,created_date,created_by  FROM `brand_service` where deleted_status=0";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and brand_type_id like '%"+search_term+"%'";
				sql += " OR brand_type_name like '%"+search_term+"%'";
				sql += " OR brand_service like '%"+search_term+"%'";
				sql += " OR note like '%"+search_term+"%' ";
		
			}
			sql+=" order by brand_type_id";
			System.out.println("%%%%%"+sql );
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			 cnt =	aliasToValueMapList.size();
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	
}
	
	String brandName = "";
	String ismapped = "";
	public String checkServiceLimit(int brandTypeId)
	{
		Session session = null;
		
//		if(!brandTypeId ==0){
		try
		{
			session =  HibernateUtilVtu.getSession("");
			String qur="select IFNULL(brand_type_name,'') as schedule_name from brand_service where brand_type_id="+brandTypeId+" ";

			Query qury2 = session.createSQLQuery(qur)
		  			.addScalar("schedule_name", Hibernate.STRING);
			List results = qury2.list();
			String brandName=results.get(0).toString();
			System.out.println("brand name is-------"+brandName);
			if(results.size()==0){
	}
			else 
			{
				ismapped=brandName;
			}
			}
		catch(Exception e){
			e.printStackTrace();
			ismapped = brandName;
		}
		finally{
			if(session != null){
				session.close();
			}
		}	
//		}
		return ismapped;
	}	
	
	boolean isSuccess = false;
	public boolean saveEditBrandServiceValue(int brandId,String brandName,String service,String remarks){
		
		System.out.println("in insert brand service value");
		Session session =null;
		Transaction transaction = null;
		 int count=0;
		 String sql="";
		 HttpServletRequest request=ServletActionContext.getRequest();
		 try{
			 int userid = (Integer) request.getSession().getAttribute("userid");
			 Common common=new Common();
			String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
		          sql = "insert into brand_service (brand_type_id,brand_type_name,brand_service,note,created_date,created_by) values ("+brandId+",'"+brandName+"','"+service+"','"+remarks+"','"+currentDate+"',"+userid+")";
			 
		 System.out.println("Sql=="+sql);
	 Query query = session.createSQLQuery(sql);
	 count=query.executeUpdate();
	 if(count>0){
		 isSuccess=true;
	 }else{
		 isSuccess=false;
	 }
	 
	 System.out.println("count"+count);
		transaction.commit();
	}catch(Exception e){
		e.printStackTrace();
		transaction.rollback();
	}finally{
		if(session!=null){
			session.close();
		}
	}
return isSuccess;
	}
	
	
//	boolean isSuccess = false;
	public boolean saveEditServiceLimit(int schId,int serviceId,String serviceLimit){
		
		System.out.println("in insert Service limit value");
		Session session =null;
		Transaction transaction = null;
		 int count=0;
		 String sql="";
		 HttpServletRequest request=ServletActionContext.getRequest();
		 try{
			 int userid = (Integer) request.getSession().getAttribute("userid");
			String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
		          sql = "insert into schedule_service_limit (schedule_id,brand_service_id,service_limit,created_date,created_by) values ("+schId+","+serviceId+",'"+serviceLimit+"','"+currentDate+"',"+userid+")";
			 
		 System.out.println("Sql=="+sql);
	 Query query = session.createSQLQuery(sql);
	 count=query.executeUpdate();
	 if(count>0){
		 isSuccess=true;
	 }else{
		 isSuccess=false;
	 }
	 
	 System.out.println("count"+count);
		transaction.commit();
	}catch(Exception e){
		e.printStackTrace();
		transaction.rollback();
	}finally{
		if(session!=null){
			session.close();
		}
	}
return isSuccess;
	}
	
	


	public String getBrandName(int brandId) {

		String result="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createSQLQuery("select brand_type_name from brand_type where status = 'ACTIVE' and deleted_status = '0' and brand_type_id="+brandId+" ");
		
		result=query.uniqueResult().toString();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		finally{
			session.close();
		}
		return result;

	}
	
	public ServiceLimit getBrandDetails(int brandId) {

		String result="";
		ServiceLimit limit=new ServiceLimit();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createSQLQuery("select brand_type_name from brand_type where status = 'ACTIVE' and deleted_status = '0' and brand_type_id="+brandId+" ");
		
		result=query.uniqueResult().toString();
		System.out.println("result"+result+"id "+brandId);
		limit.setBrand_service_id(brandId);
		limit.setBrand_type_name(result);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		finally{
			session.close();
		}
		return limit;

	}
	
	
	

	boolean schResult=false;
	public boolean checkScheduleServiceLimit(int scheduleId)
	{
		Session session = null;
		
		try
		{
			session =  HibernateUtil.getSession("hibernate.cfg.xml");
			Query query;
			 query = session.createSQLQuery("SELECT count(*) as count FROM schedule_service_limit WHERE schedule_id = "+scheduleId+"").addScalar("count", Hibernate.INTEGER);
		
			 List<Integer> list = query.list();
				int cnt = list.get(0);
			 
			if(cnt==0){
				schResult=true;
	}
			else{
				schResult =false;
			}
			}
		catch(Exception e){
			e.printStackTrace();
			schResult = false;
		}
		finally{
			if(session != null){
				session.close();
			}
		
		}
		return schResult;
	}
	
	public String UpdateServiceLimit(int scheduleId,String serviceLimit)
	{
		Session session = null;
		 HttpServletRequest request=ServletActionContext.getRequest();
		try
		{
			 int userid = (Integer) request.getSession().getAttribute("userid");
			String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
			session =  HibernateUtil.getSession("hibernate.cfg.xml");
			String qur="update schedule_service_limit set service_limit='"+serviceLimit+"',updated_date='"+currentDate+"',updated_by="+userid+" where schedule_id="+scheduleId+" ";

			
			Query query1 = session.createSQLQuery(qur);
			int count=query1.executeUpdate();
			
			System.out.println("count is-------"+count);
			if(count==1){
				ismapped="true";
	}
			else{
				ismapped ="false";
			}
			}
		catch(Exception e){
			e.printStackTrace();
			ismapped = "false";
		}
		finally{
			if(session != null){
				session.close();
			}
		
		}
		return ismapped;
	}
	
	
	
	
	public boolean EditBrandServiceValue(int brandId,String brandName,String Newservice,String Newremarks,String oldServiceValue,String brandServiceId){
		
		System.out.println("in edit brand service value");
		Session session =null;
		Transaction transaction = null;
		 int count=0;
		 String sql="";
		 boolean isSuccess1 = false;
		 String result="";
		 HttpServletRequest request=ServletActionContext.getRequest();
		 try{
//			 int serviceId=Integer.parseInt(brandServiceId);
//			 System.out.println("serviceId  "+serviceId);
			 int userid = (Integer) request.getSession().getAttribute("userid");
			 Common common=new Common();
			String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
		 String qur="update brand_service set brand_service='"+Newservice+"',note='"+Newremarks+"',updated_date='"+currentDate+"',updated_by="+userid+" where brand_service_id="+brandServiceId+" ";

			
			Query query1 = session.createSQLQuery(qur);
		 count=query1.executeUpdate();
			
			System.out.println("count is-------"+count);
			if(count==1){
				isSuccess1=true;
	}
			else{
				isSuccess1 =false;
			}
			

	 System.out.println("count "+count);
		transaction.commit();
	}catch(Exception e){
		e.printStackTrace();
		transaction.rollback();
	}finally{
		if(session!=null){
			session.close();
		}
	}
return isSuccess1;
	}
	
	@SuppressWarnings("finally")
	public boolean deleteBrandService(String brandserviceId)
	{
		System.out.println("in delete brand tyep dao");
		System.out.println("id coming is"+brandserviceId);
		String status="error";
		int count=0;
		Session session = null;
		Transaction transaction = null;
		boolean isdeleted=false;
		try
		{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			 String qur="update brand_service set deleted_status=1 where brand_service_id="+brandserviceId+" ";
				
				Query query1 = session.createSQLQuery(qur);
			 count=query1.executeUpdate();
				
				System.out.println("count is-------"+count);
				if(count==1){
					isdeleted=true;
		}
				else{
					isdeleted =false;
				}
			
		}catch(Exception e){
			status="error";
			e.printStackTrace();
			isdeleted = false;
			transaction.rollback();
		}
		finally{
			if (session!=null) {
				session.close();
			}
			
			return isdeleted;
		}
	}
	
	
	
	
	
}
