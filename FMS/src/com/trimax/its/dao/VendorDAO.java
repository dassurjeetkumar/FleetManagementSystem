package com.trimax.its.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.model.Vendor;








public class VendorDAO {
	public String saveDetails(Vendor details) {
		//System.out.println("testing");
		//boolean flag=false;
		Transaction transaction=null;
		Session session=null;
		session=HibernateUtil.getSession("hibernate.cfg.xml");
		HttpServletRequest request=ServletActionContext.getRequest();
		String str;
		String flag;
		
		String msg="";
		try{
			String q1="select vendor_id from vendor where deleted_status = 0 and  vendor_name ='"+details.getVendor_name()+"'";
			session.beginTransaction();
			Query query=session.createSQLQuery(q1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//System.out.println("aliasToValueMapList---"+aliasToValueMapList);
			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<String, Object> rs = aliasToValueMapList.get(0);
				if (rs != null) {
					//System.out.println("Record allready Present");
					int vendor_id=Integer.parseInt(rs.get("vendor_id").toString());
					//str= "Vendor Name already Present";
					//str=" Vendor Id "+ vendor_id  +" already Present";
					str="Vendor Name already Present";
					flag="false";
					msg=flag+"|"+str;
					
			}
			}else{
			
			transaction=session.beginTransaction();
			String notes=details.getNote().trim();
			//System.out.println("notes------"+notes);
			
			String q="INSERT INTO `vendor` (`vendor_name`, `vendor_contact_person`, `note`, `status`, `created_by`, `updated_by`, `created_date`, `updated_date`, `deleted_status`) VALUES" +
					"('"+details.getVendor_name()+"','"+details.getVendor_contact_person()+"','"+notes+"','ACTIVE',"+details.getCreated_by()+",0,now(),NULL,"+details.getDeleted_status()+")";
			Query query1=session.createSQLQuery(q);
			int a=query1.executeUpdate();
			
			session.getTransaction().commit();
			////System.out.println("a--//////--"+query1.);
			//System.out.println("added Successfully");
			session.flush();
			if(a>0){
				Query	 query2 = session.createSQLQuery("select MAX(vendor_id) vendor_id from  vendor").addScalar("vendor_id", Hibernate.INTEGER);
				List<Integer> list = query2.list();
				int vendor_id = list.get(0);
				//System.out.println("vendor_id----"+vendor_id);
			
			str= "Vendor ID "+ vendor_id +" Created Successfully";
			flag="true";
			msg=flag+"|"+str;
			}
			}
		}catch(Exception e){
			//System.out.println("Exception in Query"+e.getMessage());
			transaction.rollback();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	/*public ArrayList getVendorlist(){
		ArrayList <Vendor> vendorlist=new ArrayList();
		Transaction transaction = null;
		Session session=null;
		session=HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = null;
		//String q= "select vendor_id,IFNULL(vendor_name,'NA'),IFNULL(vendor_contact_person,'NA'),IFNULL(note,'NA'),IFNULL(status,'NA') from vendor";
		String q="select vendor_id,IFNULL(vendor_name,'NA')vendor_name ,IFNULL(vendor_contact_person,'NA')vendor_contact_person,IFNULL(note,'NA')note,IFNULL(status,'NA')status from vendor where deleted_status=0";
		try{
				
			session.beginTransaction();
			 query = session.createSQLQuery(q);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				//System.out.println(aliasToValueMapList);
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Vendor ven=new Vendor();
					Map<String, Object> rs = aliasToValueMapList.get(i);
					if (rs != null) {
						int vendorid=Integer.parseInt(rs.get("vendor_id").toString());
						ven.setId(vendorid);
						ven.setVendor_name(rs.get("vendor_name").toString());
						ven.setVendor_contact_person(rs.get("vendor_contact_person").toString());
						ven.setNote(rs.get("note").toString());
						ven.setStatus(rs.get("status").toString());
					
					}
					vendorlist.add(ven);
					
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
				return vendorlist;
	}
	*/
	
	public int getTotalRecords(){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
		Query	 query = session.createSQLQuery("select count(*) as count from vendor where deleted_status=0").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		cnt = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			
		}
		return cnt;
	}
	
	public int getTotalRecordsForCount(String search_term,String viewdeletedrecord){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		HttpServletRequest request=ServletActionContext.getRequest();
		int cnt=0;
		Query query=null;
		try{
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
			 query= session.createSQLQuery("select count(*) as count  from vendor   where  (vendor_name like '"+search_term+"%'  OR status like '%"+search_term+"%')  order by vendor_id").addScalar("count", Hibernate.INTEGER);
			}else{
			  query= session.createSQLQuery("select count(*) as count  from vendor where deleted_status=0  and (vendor_name like '"+search_term+"%'  OR status like '%"+search_term+"%')  order by vendor_id").addScalar("count", Hibernate.INTEGER);	
			}
		List<Integer> list = query.list();
		cnt = list.get(0);
		}catch(Exception e){
			//e.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}finally{
			
		}
		
		return cnt;
	}
	
	public JSONObject getVendorTypeList(int total,  HttpServletRequest request,String col,String dir){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			System.out.println("viewdeletedrecord------//////////-----"+viewdeletedrecord);
			String sql="";
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				sql="select vendor_id,IFNULL(vendor_name,'NA')vendor_name,IFNULL(vendor_contact_person,'NA')vendor_contact_person,IFNULL(note,'NA')note,IFNULL(status,'NA')status,deleted_status from vendor  ";	
			}else{
			sql="select vendor_id,IFNULL(vendor_name,'NA')vendor_name,IFNULL(vendor_contact_person,'NA')vendor_contact_person,IFNULL(note,'NA')note,IFNULL(status,'NA')status,deleted_status from vendor where deleted_status=0 ";
			}
			
			
			if(!request.getParameter("sSearch").equals("")){
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
				//sql += " and (vendor_id like '%"+search_term+"%'";
				sql += " and ( vendor_name like '"+search_term+"%'";
				//sql += " OR vendor_contact_person like '%"+search_term+"%'";
				//sql += " OR note like '%"+search_term+"%'";
				sql += " OR status like '"+search_term+"%')";
				
			}
			
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    //criteria.addOrder(Order.asc(col));
					sql += " order by "+col+ " asc";
				}else{
					//criteria.addOrder(Order.desc(col));	
					sql += " order by "+col+" desc";
				}
			}
			
			
			//int cntdetails=getTotalRecords();
			//if(cntdetails>10){
			//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		//}
		//System.out.println("sql----------"+sql);
		
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		JSONArray array = new JSONArray();
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
					+ rs.get("vendor_id")
					+ "' value='"
					+ rs.get("vendor_id") + "'/>");
			ja.add(rs.get("vendor_id").toString());
			ja.add(rs.get("vendor_name").toString());
			ja.add(rs.get("vendor_contact_person").toString());
			
			
			ja.add(rs.get("note").toString() != null ? rs.get("note").toString() : "");
			ja.add(rs.get("status").toString());
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				String deletedstatus=rs.get("deleted_status").toString();
				System.out.println("deletedstatus---"+deletedstatus);
				
				if(deletedstatus.equalsIgnoreCase("1"))
				{
					//ja.add("Deleted Record");	
					ja.add("<input type='hidden' value='N' id='isRocordEligible"+ rs.get("vendor_id")+"'/>Deleted Record");	

					}else{
					//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ rs.get("vendor_id")+"'/>Record in Database1");	

				}
				
				
			}else{
				
			}
			
			array.add(ja);
			//System.out.println("Array----->"+array);
		}
		

			
			totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			//if(!request.getParameter("sSearch").equals("")){
			String search_term = request.getParameter("sSearch").trim();
			 cnt=getTotalRecordsForCount(search_term,viewdeletedrecord);
			//}
			result.put("iTotalRecords", cnt);
			result.put("iTotalDisplayRecords", cnt);
		}catch(Exception e){
			//e.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}finally{
			if (session != null) {
				session.close();
			}
			
		}
		return result;
	}
	
	
	public String modifyVendor(int vendorid,String Vendorname,String VendorContactPersonName,String note,int updatedby,String status){
		String flag="";
		String msg="";
		String str="";
		Transaction transaction = null;
		Session session = null;
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		HttpServletRequest request=ServletActionContext.getRequest();
		String sql="";
		int deletedstatus;
		try{
			
			String q1="select vendor_id from vendor where deleted_status = 0 and vendor_name = '"+Vendorname+"' and  vendor_id NOT IN ("+ vendorid +")";
			session.beginTransaction();
			Query query=session.createSQLQuery(q1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//System.out.println("aliasToValueMapList---"+aliasToValueMapList);
			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<String, Object> rs = aliasToValueMapList.get(0);
				if (rs != null) {
					//System.out.println("Record allready Present");
					int vendor_id =Integer.parseInt(rs.get("vendor_id").toString());
					//str= "Vendor ID "+vendor_id+ " already Present";
					str="Vendor Name already Exist";
					flag="false";
					msg=flag+"|"+str;
					
			}
			}else{
				/*if(status.equalsIgnoreCase("ACTIVE"))
				{
					deletedstatus=0;
				}else{
					deletedstatus=1;
				}*/
			transaction = session.beginTransaction();
			//sql="update module_master set RECORD_STATUS = 'INACTIVE',UPDATED_TM = now(), UPDATED_BY= '"+username+"' where MODULE_ID = "+module_id;
			//sql="update module_master set MODULE_NM= '"+modulename+"',UPDATED_TM = now(), UPDATED_BY= '"+username+"' where MODULE_ID = "+module_id;
			sql="update vendor set vendor_name ='"+ Vendorname + "', vendor_contact_person = '"+ VendorContactPersonName +"',note='"+note+"',updated_by = "+ updatedby + ", updated_date=now(),status ='"+ status +"' where vendor_id ="+vendorid;
			//System.out.println("sql--"+sql);
			Query query1 = session.createSQLQuery(sql);
			////System.out.println("test1");
			int n=query1.executeUpdate();
			if(n>0)
			{
			session.getTransaction().commit();
			////System.out.println("test2");
			//System.out.println("Updated Successfully");
			session.flush();
			str="Vendor ID  "+ vendorid  +"  Updated Successfully";
			flag="true";
			msg=flag+"|"+str;
			}else{
				//System.out.println("Record not updated");
				str="Record not updated";
				flag="false";
				msg=flag+"|"+str;
			}//else
			}
		}catch(Exception e){
			//e.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return msg;	
	}
	
	public String getInactiveRecord(int vendorid,int updatedby){
		String flag="";
		Transaction transaction = null;
		Session session = null;
		String msg="";
		String str="";
		String status="error";
		HttpServletRequest request=ServletActionContext.getRequest();
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		DependencyChecker dc=new DependencyChecker();
		status=dc.checkDependentEntities(session,"vendor",vendorid);
		if(status.trim().equalsIgnoreCase("")){
		transaction = session.beginTransaction();
		String sql="";
		
			//sql="update user_pg_rel set RECORD_STATUS = 'INACTIVE',UPDATED_TM = now(), UPDATED_BY= '"+updatedby+"' where USER_PG_REL_ID ="+ userpagerelationid;
			sql="update vendor set deleted_status=1,updated_by="+updatedby+",updated_date=now() where vendor_id ="+vendorid;
			//System.out.println("sql--"+sql);
			Query query1 = session.createSQLQuery(sql);
			
			int n=query1.executeUpdate();
			session.getTransaction().commit();
			//System.out.println("updated Successfully");
			session.flush();
			if(n>0){
				//System.out.println("Updated Successfully");
				session.flush();
				str="Vendor  ID "+ vendorid + " Deleted Successfully";
				flag="true";
				msg=flag+"|"+str;
			}else{
				//System.out.println("Record not updated");
				str="Record not Deleted";
				flag="false";
				msg=flag+"|"+str;
			}
		}
		}catch(Exception e){
			//e.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return status;
	}
	
	public Vendor  getlist(int vendorid){
		ArrayList <Vendor> vendorlist=new ArrayList();
		Transaction transaction = null;
		Session session=null;
		session=HibernateUtil.getSession("hibernate.cfg.xml");
		HttpServletRequest request=ServletActionContext.getRequest();
		Query query = null;
		Vendor ven=new Vendor();
		try{
			String q="select vendor_id,IFNULL(vendor_name,'NA')vendor_name ,IFNULL(vendor_contact_person,'NA')vendor_contact_person,IFNULL(note,'NA')note,IFNULL(status,'NA')status from vendor where vendor_id="+vendorid;
			session.beginTransaction();
			 query = session.createSQLQuery(q);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				//System.out.println(aliasToValueMapList);
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
				
					Map<String, Object> rs = aliasToValueMapList.get(i);
					if (rs != null) {
						int vendorid1=Integer.parseInt(rs.get("vendor_id").toString());
						ven.setId(vendorid1);
						ven.setVendor_name(rs.get("vendor_name").toString());
						ven.setVendor_contact_person(rs.get("vendor_contact_person").toString());
						ven.setNote(rs.get("note").toString());
						ven.setStatus(rs.get("status").toString());
					
					}
					//vendorlist.add(ven);
					
				}
		}catch(Exception e){
			//e.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}finally{
			if (session != null) {
				session.close();
			}
	
		}
		return ven;
	}
	
}