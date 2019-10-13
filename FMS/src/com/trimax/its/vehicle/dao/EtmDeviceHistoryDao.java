package com.trimax.its.vehicle.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.device.model.Device;
import com.trimax.its.etm.model.EtmDeviceHistory;
import com.trimax.its.etm.model.EtmDeviceIssue;
import com.trimax.its.etm.model.docketinfo;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;



public class EtmDeviceHistoryDao {

	
	public int getTotalRecords(int total, HttpServletRequest request,
			String col, String dir,String viewdeletedrecord) {
		
		System.out.println("Enter into totalRecord()");
		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			int usrroleid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("roleid").toString());
			
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql = "SELECT edh.id as id FROM `etm_device_history` edh left join etm_issue ei on ei.id=edh.etm_issue " +
						"inner join device d on d.device_id=edh.etm_number left join org_chart oc on oc.org_chart_id=edh.division_id " +
						"left join org_chart oc1 on oc1.org_chart_id=edh.depot_id";
			 if(orgtTypeId!=0 && orgtTypeId==1){
					ordchartcompare = " where etm_received_date is null   ";
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					ordchartcompare = " where etm_received_date is null and division_id='"+orgtTypeId+"'  ";
				}else if(orgtTypeId!=0 && orgtTypeId==3 && usrroleid==36){
				//	ordchartcompare = " where etm_received_date is null and etm_resolved_by_fme is null and etm_received_by_fme is null and (etm_pickup_by_verifone is null or etm_resolved_by_verifone is not null) and depot_id='"+orgchartid+"'  ";
					ordchartcompare = " where etm_received_date is null  and depot_id='"+orgchartid+"' ";

				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					//ordchartcompare = " where etm_received_date is null and  (etm_pickup_by_fme is null or etm_received_by_fme is not null) and depot_id='"+orgchartid+"'  ";
					ordchartcompare = " where etm_received_date is null  and depot_id='"+orgchartid+"'  ";

				}
			//	sql += ordchartcompare +" AND employee.status='Active'";	
				sql += ordchartcompare;	

				
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			
				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					if(orgtTypeId!=0 && orgtTypeId==1){
						sql += " and (device_serial_number like '%" + search_term + "%'";	
					}else{
					sql += " and (device_serial_number like '%" + search_term + "%'";
					}
					
					sql += " OR issue_name like '%"+search_term+"%' ";
					sql += " OR oc1.org_name like '%"+search_term+"%')";
				}
				
				
				sql += "group by device_serial_number ";
				
				
				if (!col.equals("")) {
					if (dir.equals("asc")) {
						sql += " order by " + col + " asc";
					} else {
						sql += " order by " + col + " desc";
					}
				}
				
				Query query = session.createSQLQuery(sql)
						.addScalar("id", Hibernate.STRING);

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				cnt=aliasToValueMapList.size();
		} catch (Exception e) {

		}
		
	
		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getData1(int total, HttpServletRequest request,
			String col, String dir,String viewdeletedrecord) {

		System.out.println("Hii in Vehicle Doa getData1()");
		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			int usrroleid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("roleid").toString());
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = "SELECT edh.id as id, `device_serial_number`,  ifnull(oc1.org_name,'')depot, ifnull((select org_name from org_chart where org_chart_id =oc1.parent_id limit 1 ),'') as division , " +
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
					" `etm_created_date`, " +
					"ifnull(etm_pickup_by_fme,'') etm_pickup_by_fme, ifnull(etm_resolved_by_fme,'') etm_resolved_by_fme," +
					"ifnull(etm_pickup_by_verifone,'')etm_pickup_by_verifone,ifnull(etm_resolved_by_verifone,'')etm_resolved_by_verifone," +
					" ifnull(etm_received_by_fme,'') etm_received_by_fme,ifnull(resolved_reason,'')resolved_reason,ifnull(remarks,'')remarks FROM `etm_device_history` edh left join etm_issue ei on ei.id=edh.etm_issue " +
					"inner join device d on d.device_id=edh.etm_number left join org_chart oc on oc.org_chart_id=edh.division_id " +
					"left join org_chart oc1 on oc1.org_chart_id=edh.depot_id";
			System.out.println("id are----"+orgtTypeId+"---"+usrroleid);
			if(orgtTypeId!=0 && orgtTypeId==1){
			//	ordchartcompare = " where etm_received_date is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is null ";
				ordchartcompare = " where etm_received_date is null ";

			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " where etm_received_date is null and division_id='"+orgtTypeId+"'  ";
			}
		else if(orgtTypeId!=0 && orgtTypeId==3 && usrroleid==36){
			//	ordchartcompare = " where etm_received_date is null and etm_resolved_by_fme is null and etm_received_by_fme is null and (etm_pickup_by_verifone is null or etm_resolved_by_verifone is not null) and depot_id='"+orgchartid+"'  ";
				ordchartcompare = " where etm_received_date is null  and depot_id='"+orgchartid+"' ";

			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				//ordchartcompare = " where etm_received_date is null and  (etm_pickup_by_fme is null or etm_received_by_fme is not null) and depot_id='"+orgchartid+"'  ";
				ordchartcompare = " where etm_received_date is null  and depot_id='"+orgchartid+"'  ";

			}
		//	sql += ordchartcompare +" AND employee.status='Active'";	
			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				if(orgtTypeId!=0 && orgtTypeId==1){
					sql += " and ( device_serial_number like '%" + search_term + "%'";	
				}else{
				sql += " and (device_serial_number like '%" + search_term + "%'";
				}
				
				sql += " OR issue_name like '%"+search_term+"%' ";
				sql += " OR oc1.org_name like '%"+search_term+"%')";
				
			}
			
			
			
			sql += " group by device_serial_number ";
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			if(!request.getParameter("iDisplayLength").equals("-1")){
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			}
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.STRING)
					.addScalar("device_serial_number", Hibernate.STRING)
					.addScalar("division", Hibernate.STRING)
					.addScalar("depot", Hibernate.STRING)
					.addScalar("issue_name", Hibernate.STRING)
					.addScalar("etm_created_date", Hibernate.STRING)
					.addScalar("etm_pickup_by_fme", Hibernate.STRING)
					.addScalar("etm_resolved_by_fme", Hibernate.STRING)
					.addScalar("etm_pickup_by_verifone", Hibernate.STRING)
					.addScalar("etm_resolved_by_verifone", Hibernate.STRING)
			.addScalar("etm_received_by_fme", Hibernate.STRING)
			.addScalar("resolved_reason", Hibernate.STRING)
			.addScalar("remarks", Hibernate.STRING);

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
				//ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("id")+"' value='"+rs.get("id")+"'/>");
				ja.add(rs.get("id").toString());ja.add(rs.get("division").toString());ja.add(rs.get("depot").toString());
				ja.add(rs.get("device_serial_number").toString());
				ja.add(rs.get("issue_name").toString());
				ja.add(rs.get("etm_created_date").toString());
				if(rs.get("etm_pickup_by_fme").toString()==null || rs.get("etm_pickup_by_fme").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_pickup_by_fme").toString());
				}
				if(rs.get("etm_resolved_by_fme").toString()==null || rs.get("etm_resolved_by_fme").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_resolved_by_fme").toString());
				}
				if(rs.get("etm_pickup_by_verifone").toString()==null || rs.get("etm_pickup_by_verifone").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_pickup_by_verifone").toString());
				}
				if(rs.get("etm_resolved_by_verifone").toString()==null || rs.get("etm_resolved_by_verifone").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_resolved_by_verifone").toString());
				}
				if(rs.get("etm_received_by_fme").toString()==null || rs.get("etm_received_by_fme").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_received_by_fme").toString());
				}
				ja.add(rs.get("resolved_reason").toString());
				ja.add(rs.get("remarks").toString());
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
          System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	
	
	public Map<Integer, String> getEtmdata() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String ordchartcompare="";
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			if(orgtTypeId!=0 && orgtTypeId==1){
				ordchartcompare = "  ";
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " ";
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				ordchartcompare = " and org_id='"+orgchartid+"'  ";
			}
			Query query = session
					.createSQLQuery("select d.device_id,device_serial_number from device d inner join device_org do on d.device_id=do.device_id " +
							"where device_type_id=2 and d.status='ACTIVE' and d.deleted_status=0 "+ordchartcompare+"  " +
							" and d.device_id not in (select etm_number from etm_device_history where etm_received_date IS NULL) group by d.device_serial_number")
					.addScalar("device_id", Hibernate.INTEGER).addScalar("device_serial_number", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(Device.class));
			List<Device> list = query.list();
			resultMap.put(0, "Select");
			System.out.println("Size of Schedule List : "+list.size());
			for (Device type : list) {
				resultMap.put(type.getDevice_id(), type.getDevice_serial_number());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	
	public Map<Integer, String> getEtmissue() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `id`, `issue_name` FROM `etm_issue` ")
					.addScalar("id", Hibernate.INTEGER).addScalar("issue_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			resultMap.put(0, "Select");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("id").toString()),rs.get("issue_name").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}

	public void savedata(ArrayList<Integer> etmidnumber, ArrayList<Integer> etmissueid,
			String etmpickupdate) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id=0;
		Common common=new Common();
		try {
			String pickupdate=common.getDateFromPicker(etmpickupdate);
			session.beginTransaction();
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			int userid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			int devisionid=getDivisionId(orgchartid);
			//System.out.println(etmidnumber.contains("0")+"------0 is have before removing");
			//etmidnumber.remove(etmidnumber.indexOf("0"));
			//System.out.println(etmidnumber.contains("0")+"------0 is have before removing");
			//System.out.println("list is------------"+etmidnumber);
		
			if(etmidnumber.size()==1 && etmissueid.size()==1){
				EtmDeviceHistory edh=new EtmDeviceHistory();
				EtmDeviceIssue edi=new EtmDeviceIssue();
				edh.setDivision_id(devisionid);
				edh.setDepot_id(orgchartid);
				edh.setEtm_number(etmidnumber.get(0));
				System.out.println("etm number id is-----"+etmidnumber.get(0));
				edh.setEtm_issue(0);
				
				edh.setEtm_created_date(pickupdate);
				edh.setCreated_by(userid);
				int genid=(Integer) session.save(edh);
				edi.setEmtDeviceHistoryId(genid);
				edi.setEtmIssueId(etmissueid.get(0));
				edi.setIssueCreatedDate(pickupdate);
				session.save(edi);
				
				}
			
			
			
			if(etmidnumber.size()>1 && etmissueid.size()==1){
			for(int i=0;i<etmidnumber.size();i++){
				if(etmidnumber.get(i)!=0){
			EtmDeviceHistory edh=new EtmDeviceHistory();
			EtmDeviceIssue edi=new EtmDeviceIssue();
			edh.setDivision_id(devisionid);
			edh.setDepot_id(orgchartid);
			edh.setEtm_number(etmidnumber.get(i));
			System.out.println("etm number id is-----"+etmidnumber.get(i));
			edh.setEtm_issue(0);
			
			edh.setEtm_created_date(pickupdate);
			edh.setCreated_by(userid);
			int genid=(Integer) session.save(edh);
			edi.setEmtDeviceHistoryId(genid);
			edi.setEtmIssueId(etmissueid.get(0));
			edi.setIssueCreatedDate(pickupdate);
			session.save(edi);
			}
			}}
			System.out.println("size is----"+etmissueid.size());
			if(etmissueid.size()>1 && etmidnumber.size()==1){
				EtmDeviceHistory edh=new EtmDeviceHistory();
				
				edh.setDivision_id(devisionid);
				edh.setDepot_id(orgchartid);
				edh.setEtm_number(etmidnumber.get(0));
				edh.setEtm_issue(0);
				
				edh.setEtm_created_date(pickupdate);
				edh.setCreated_by(userid);
				int genid=(Integer) session.save(edh);
				
				for(int i=0;i<etmissueid.size();i++){
					System.out.println("inside of for----"+etmissueid.get(i));
					if(etmissueid.get(i)!=0){
						EtmDeviceIssue edi=new EtmDeviceIssue();
						System.out.println("inside of if----"+etmissueid.get(i));
					edi.setEmtDeviceHistoryId(genid);
					edi.setEtmIssueId(etmissueid.get(i));
					edi.setIssueCreatedDate(pickupdate);
					session.save(edi);
				}
				}
			}
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally {
			  if(session!=null){ session.close(); }
				 
		}
		
		
	}

	private int getDivisionId(int orgchartid) {
		Common common = new Common();
		int division=0;
		Session session=null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String queryForCount = "SELECT `parent_id` FROM `org_chart` WHERE `org_chart_id` = '"+orgchartid+"'";
			division = common.getDBResultInt(session, queryForCount, "parent_id");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
		return division;
	}

	public int updateReceive(int etmserviceid) {
		Session session = null;
		int count=0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql= "update etm_device_history set  etm_received_date=now() where id = "+etmserviceid+" ";
	        Query qury = session.createSQLQuery(sql);
		
		count=qury.executeUpdate();
		System.out.println("count======"+count);
		if(count>0){
		
			session.getTransaction().commit();
		}
		
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		
		
	}
		return count;
	}

	public int getstatus(int etmserviceid) {
		Common common = new Common();
		boolean isSuccess = true;
		Session session = null;
		int count =0;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Date myDate = new Date();
			String date=new SimpleDateFormat("yyyy-MM-dd").format(myDate);
			String queryForCount = "select count(*) as count from etm_device_history where id='"+etmserviceid+"' and " +
					"(etm_received_by_fme is not null or etm_resolved_by_fme is not null)  ";
			 count = common.getDBResultInt(session, queryForCount, "count");
			System.out.println("count................"+count);
			if(count > 0){
				isSuccess = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = true;
		}finally{
			if(session!=null){
				session.close();
			}
			
		}return count;
}

	public int getstatusforResolve(String etmserviceid) {
		Common common = new Common();
		boolean isSuccess = true;
		Session session = null;
		int count =0;
		etmserviceid=etmserviceid.replace("on,","");
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Date myDate = new Date();
			String date=new SimpleDateFormat("yyyy-MM-dd").format(myDate);
			String queryForCount = "select count(*) as count from etm_device_history where id in("+etmserviceid+") and etm_pickup_by_verifone is not null  ";
			 count = common.getDBResultInt(session, queryForCount, "count");
			System.out.println("count................"+count);
			if(count > 0){
				isSuccess = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = true;
		}finally{
			if(session!=null){
				session.close();
			}
			
		}return count;
	}

	public int updateResolved(String etmserviceid,String reason) {
		Session session = null;
		int count=0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String ids[]=etmserviceid.split(",");
			String reasons[]=reason.split(",");
			//System.out.println(reasons.length);
			//System.out.println(ids.length+"--------------"+reasons.length);
			//System.out.println(ids+"===================="+reasons);
			for(int i=0;i<ids.length;i++){
			String sql= "update etm_device_history set  etm_resolved_by_verifone=now(),resolved_reason='"+reasons[i].trim()+"' where id = "+ids[i]+" and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is null ";
	        Query qury = session.createSQLQuery(sql);
		
		count=qury.executeUpdate();
			}
		System.out.println("count======"+count);
		if(count>0){
		
			session.getTransaction().commit();
		}
		
		
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			count=0;
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		
		
	}
		return count;
	}

	public int updatePickup(String etmserviceid,int Docket) {
		Session session = null;
		int count=0;
		etmserviceid=etmserviceid.replace("on,","");
		try {
			int userid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql= "update etm_device_history set  etm_pickup_by_verifone=now(),docket_no="+Docket+",verifone_id="+userid+" where id in ( "+etmserviceid+") and  etm_pickup_by_verifone is null";
	        Query qury = session.createSQLQuery(sql);
		
		count=qury.executeUpdate();
		System.out.println("count======"+count);
		if(count>0){
		
			session.getTransaction().commit();
		}
		
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		
		
	}
		return count;
	}

	public String getname(int orgchartid) {
		Common common = new Common();
		boolean isSuccess = true;
		Session session = null;
		String count ="";
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Date myDate = new Date();
			String date=new SimpleDateFormat("yyyy-MM-dd").format(myDate);
			String queryForCount = "select org_name as count from org_chart where org_chart_id='"+orgchartid+"' ";
			 count = common.getDBResultStr(session, queryForCount, "count");
			System.out.println("count................"+count);
			
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = true;
		}finally{
			if(session!=null){
				session.close();
			}
			
		}return count;
	}

	public String getnameparent(int orgchartid) {
		Common common = new Common();
		boolean isSuccess = true;
		Session session = null;
		String count ="";
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Date myDate = new Date();
			String date=new SimpleDateFormat("yyyy-MM-dd").format(myDate);
			String queryForCount = "select org_name as count from org_chart where parent_id='"+orgchartid+"' ";
			 count = common.getDBResultStr(session, queryForCount, "count");
			System.out.println("count................"+count);
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = true;
		}finally{
			if(session!=null){
				session.close();
			}
			
		}return count;
	}

	public int getTotalServiceRecords(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord) {
		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
			 		"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," + 
			 		"etm_created_date FROM etm_device_history edh " +
						"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
						"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
			 if(orgtTypeId!=0 && orgtTypeId==1){
					ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null  ";
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null and division_id='"+orgtTypeId+"'   ";
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null and depot_id='"+orgchartid+"'   ";
				}
			//	sql += ordchartcompare +" AND employee.status='Active'";	
				sql += ordchartcompare;	

				
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			
				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					if(orgtTypeId!=0 && orgtTypeId==1){
						sql += " where device_serial_number like '%" + search_term + "%'";	
					}else{
					sql += " and device_serial_number like '%" + search_term + "%'";
					}
					
					sql += " OR issue_name like '%"+search_term+"%')";
				}
				
				
				sql += " group by device_serial_number ";
				
				
				if (!col.equals("")) {
					if (dir.equals("asc")) {
						sql += " order by " + col + " asc";
					} else {
						sql += " order by " + col + " desc";
					}
				}
				
				Query query = session.createSQLQuery(sql)
						.addScalar("id", Hibernate.STRING)
						.addScalar("device_serial_number", Hibernate.STRING)
						.addScalar("phone_number", Hibernate.STRING)
						.addScalar("issue_name", Hibernate.STRING)
						.addScalar("etm_created_date", Hibernate.STRING);

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				cnt=aliasToValueMapList.size();
		} catch (Exception e) {

		}
		
	
		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
	}

	public JSONObject getDataService(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
					"etm_created_date FROM etm_device_history edh " +
					"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
					"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
		 if(orgtTypeId!=0 && orgtTypeId==1){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null  ";
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null and division_id='"+orgtTypeId+"'    ";
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null and depot_id='"+orgchartid+"'  ";
			}
		//	sql += ordchartcompare +" AND employee.status='Active'";	
			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				if(orgtTypeId!=0 && orgtTypeId==1){
					sql += " and ( device_serial_number like '%" + search_term + "%'";	
				}else{
				sql += " and (device_serial_number like '%" + search_term + "%'";
				}
				
				sql += " OR issue_name like '%"+search_term+"%')";
			}
			
			
			sql += " group by device_serial_number ";
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			if(!request.getParameter("iDisplayLength").equals("-1")){
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			}
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.STRING)
					.addScalar("device_serial_number", Hibernate.STRING)
					.addScalar("phone_number", Hibernate.STRING)
					.addScalar("issue_name", Hibernate.STRING)
					.addScalar("etm_created_date", Hibernate.STRING);

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
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("id")+"' value='"+rs.get("id")+"'/>");
				ja.add(rs.get("id").toString());
				ja.add(rs.get("device_serial_number").toString());
				ja.add(rs.get("phone_number").toString());
				
				if(rs.get("etm_created_date").toString()==null || rs.get("etm_created_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_created_date").toString());
				}
				ja.add(rs.get("issue_name").toString());
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
         System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
           logger.error(this.getClass() + "$Exception in LoginAction action",ex);
           SaveRequest.save(request);
           ErrorLog log = new ErrorLog();
           log.setMsg(ex.getMessage());
           ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}

	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataOfReceivedETM(int total, HttpServletRequest request,
			String col, String dir,String viewdeletedrecord) {

		System.out.println("Hii in Vehicle Doa getData1()");
		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = "SELECT edh.id as id, `device_serial_number`,  oc.org_name as division, oc1.org_name as depot, `issue_name`, `etm_created_date`, " +
					"ifnull(etm_pickup_date,'') etm_pickup_date, ifnull(etm_resolved_date,'') etm_resolved_date,ifnull(etm_received_date,'')etm_received_date," +
					" ifnull(resolved_reason,'') resolved_reason FROM `etm_device_history` edh left join etm_issue ei on ei.id=edh.etm_issue " +
					"inner join device d on d.device_id=edh.etm_number left join org_chart oc on oc.org_chart_id=edh.division_id " +
					"left join org_chart oc1 on oc1.org_chart_id=edh.depot_id";
			if(orgtTypeId!=0 && orgtTypeId==1){
				ordchartcompare = " where etm_received_date is null  ";
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " where etm_received_date is null and division_id='"+orgtTypeId+"'  ";
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				ordchartcompare = " where etm_received_date is null and depot_id='"+orgchartid+"'  ";
			}
		//	sql += ordchartcompare +" AND employee.status='Active'";	
			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				if(orgtTypeId!=0 && orgtTypeId==1){
					sql += " where device_serial_number like '%" + search_term + "%'";	
				}else{
				sql += " and device_serial_number like '%" + search_term + "%'";
				}
				
				sql += " OR issue_name like '%"+search_term+"%')";
			}
			
			
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.STRING)
					.addScalar("device_serial_number", Hibernate.STRING)
					.addScalar("division", Hibernate.STRING)
					.addScalar("depot", Hibernate.STRING)
					.addScalar("issue_name", Hibernate.STRING)
					.addScalar("etm_created_date", Hibernate.STRING)
					.addScalar("etm_pickup_date", Hibernate.STRING)
					.addScalar("etm_resolved_date", Hibernate.STRING)
					.addScalar("etm_received_date", Hibernate.STRING)
			.addScalar("resolved_reason", Hibernate.STRING);

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
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("id")+"' value='"+rs.get("id")+"'/>");
				ja.add(rs.get("id").toString());ja.add(rs.get("division").toString());ja.add(rs.get("depot").toString());
				ja.add(rs.get("device_serial_number").toString());
				ja.add(rs.get("issue_name").toString());
				ja.add(rs.get("etm_created_date").toString());
				if(rs.get("etm_pickup_date").toString()==null || rs.get("etm_pickup_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_pickup_date").toString());
				}
				if(rs.get("etm_resolved_date").toString()==null || rs.get("etm_resolved_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_resolved_date").toString());
				}
				if(rs.get("etm_received_date").toString()==null || rs.get("etm_received_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_received_date").toString());
				}
				if(rs.get("resolved_reason").toString()==null || rs.get("resolved_reason").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("resolved_reason").toString());
				}
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
          System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}

	public int insertDocketNo(String issuer, String receiver) {
		int Docket=0;
		Common common = new Common();
		boolean isSuccess = true;
		Session session = null;
		String count ="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			docketinfo info=new docketinfo();
			info.setIssuer_name(issuer);
			info.setReceiver_name(receiver);
			info.setDepot_id(orgchartid);
			session.beginTransaction();
			Docket=(Integer) session.save(info);
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = true;
		}finally{
			if(session!=null){
				session.close();
			}
			
		}
		return Docket;
	}

	public JSONObject getDataServiceDuplicate(int total,
			HttpServletRequest request, 
			String col, String dir, String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Session session =null;
		int totalAfterFilter = total;
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			String searchSQL = "";
			String sql = "SELECT `docket_no`, `issuer_name`, `receiver_name`, `created_date` FROM `docket_info` " +
					"WHERE `depot_id` = '"+orgchartid+"'";	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				
				sql += " and (docket_no like '%" + search_term + "%'";
				
				
				sql += " OR issuer_name like '%"+search_term+"%')";
			}
			
			
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("docket_no", Hibernate.STRING)
					.addScalar("issuer_name", Hibernate.STRING)
					.addScalar("receiver_name", Hibernate.STRING)
					.addScalar("created_date", Hibernate.STRING);

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
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("docket_no")+"' value='"+rs.get("docket_no")+"'/>");
				ja.add(rs.get("docket_no").toString());
				if(rs.get("issuer_name").toString()==null || rs.get("issuer_name").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("issuer_name").toString());
				}
				if(rs.get("receiver_name").toString()==null || rs.get("receiver_name").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("receiver_name").toString());
				}
				/*if(rs.get("etm_received_date").toString()==null || rs.get("etm_received_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_received_date").toString());
				}
				if(rs.get("resolved_reason").toString()==null || rs.get("resolved_reason").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("resolved_reason").toString());
				}*/
				ja.add(rs.get("created_date").toString());
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
         System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
           logger.error(this.getClass() + "$Exception in LoginAction action",ex);
           SaveRequest.save(request);
           ErrorLog log = new ErrorLog();
           log.setMsg(ex.getMessage());
           ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}

	public int getTotalServiceduplicate(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){
			
	int cnt=0;
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	
	String ordchartcompare="";
	try{
		int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
		int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
	
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 String sql = "SELECT `docket_no`, `issuer_name`, `receiver_name`, `created_date` FROM `docket_info` " +
					"WHERE `depot_id` = '"+orgchartid+"'";	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
sql += " and (docket_no like '%" + search_term + "%'";
				
				
				sql += " OR issuer_name like '%"+search_term+"%')";
			}
			
			
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			
			Query query = session.createSQLQuery(sql)
					.addScalar("docket_no", Hibernate.STRING)
					.addScalar("issuer_name", Hibernate.STRING)
					.addScalar("receiver_name", Hibernate.STRING)
					.addScalar("created_date", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			cnt=aliasToValueMapList.size();
	} catch (Exception e) {

	}
	

	System.out.println("List size in total"+cnt);
	
	if(session!=null && session.isOpen()){
		session.close();
	}
	
	return cnt;
		
	}

	public int updateReceiveETMData(String data) {
		Session session = null;
		int count=0;
		try {
			int userid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql= "update etm_device_history set  etm_pickup_by_fme=now(),fme_id="+userid+" where id in ("+data+") and etm_pickup_by_fme is null";
	        Query qury = session.createSQLQuery(sql);
		count=qury.executeUpdate();
		System.out.println("count======"+count);
		if(count>0){
			session.getTransaction().commit();
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

	public int updateResolveETMData(String data) {
		Session session = null;
		int count=0;
		try {
			int userid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql= "update etm_device_history set  etm_resolved_by_fme=now(),fme_id="+userid+" where id in ("+data+") " +
					"and etm_pickup_by_verifone is null and etm_pickup_by_fme is not null";
	        Query qury = session.createSQLQuery(sql);
		count=qury.executeUpdate();
		System.out.println("count======"+count);
		if(count>0){
			session.getTransaction().commit();
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

	public int updateReceiveETMFromVerifone(String data,String status) {
		Session session = null;
		int count=0;
		try {
			int userid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql= "update etm_device_history set  etm_received_by_fme=now(),remarks='"+status+"',fme_id="+userid+" where id in ("+data+") and 	etm_resolved_by_verifone is not null ";
	        Query qury = session.createSQLQuery(sql);
		count=qury.executeUpdate();
		System.out.println("count======"+count);
		if(count>0){
			session.getTransaction().commit();
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}
	public int gettotetmpickdepot(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					 "(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
			 		"etm_created_date FROM etm_device_history edh " +
						"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
						"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
			 if(orgtTypeId!=0 && orgtTypeId==1){
					ordchartcompare = " where etm_pickup_by_fme is null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null   ";
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					ordchartcompare = " where etm_pickup_by_fme is null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null and division_id='"+orgtTypeId+"'   ";
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					ordchartcompare = " where etm_pickup_by_fme is null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null and depot_id='"+orgchartid+"'   ";
				}
			//	sql += ordchartcompare +" AND employee.status='Active'";	
				sql += ordchartcompare;	

				
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			
				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					if(orgtTypeId!=0 && orgtTypeId==1){
						sql += " and (device_serial_number like '%" + search_term + "%'";	
					}else{
					sql += " and (device_serial_number like '%" + search_term + "%'";
					}
					
					sql += " OR issue_name like '%"+search_term+"%')";
				}
				
				sql += " group by device_serial_number  ";	
				
				
				
				
				if (!col.equals("")) {
					if (dir.equals("asc")) {
						sql += " order by " + col + " asc";
					} else {
						sql += " order by " + col + " desc";
					}
				}
				
				Query query = session.createSQLQuery(sql)
						.addScalar("id", Hibernate.STRING)
						.addScalar("device_serial_number", Hibernate.STRING)
						.addScalar("phone_number", Hibernate.STRING)
						.addScalar("issue_name", Hibernate.STRING)
						.addScalar("etm_created_date", Hibernate.STRING);

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				cnt=aliasToValueMapList.size();
		} catch (Exception e) {

		}
		
	
		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
	
	}
	public JSONObject getpickupdepot(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
					"etm_created_date FROM etm_device_history edh " +
					"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
					"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
		 if(orgtTypeId!=0 && orgtTypeId==1){
				ordchartcompare = " where etm_pickup_by_fme is  null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null ";
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " where etm_pickup_by_fme is null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null and division_id='"+orgtTypeId+"'    ";
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				ordchartcompare = " where etm_pickup_by_fme is null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null and depot_id='"+orgchartid+"'   ";
			}
		//	sql += ordchartcompare +" AND employee.status='Active'";	
			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				if(orgtTypeId!=0 && orgtTypeId==1){
					sql += " and (device_serial_number like '%" + search_term + "%'";	
				}else{
				sql += " and (device_serial_number like '%" + search_term + "%'";
				}
				
				sql += " OR issue_name like '%"+search_term+"%')";
			}
			
			sql += " group by device_serial_number  ";	
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			if(!request.getParameter("iDisplayLength").equals("-1")){
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			}
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.STRING)
					.addScalar("device_serial_number", Hibernate.STRING)
					.addScalar("phone_number", Hibernate.STRING)
					.addScalar("issue_name", Hibernate.STRING)
					.addScalar("etm_created_date", Hibernate.STRING);

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
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("id")+"' value='"+rs.get("id")+"'/>");
				ja.add(rs.get("id").toString());
				ja.add(rs.get("device_serial_number").toString());
				ja.add(rs.get("phone_number").toString());
				
				if(rs.get("etm_created_date").toString()==null || rs.get("etm_created_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_created_date").toString());
				}
				ja.add(rs.get("issue_name").toString());
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
         System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
           logger.error(this.getClass() + "$Exception in LoginAction action",ex);
           SaveRequest.save(request);
           ErrorLog log = new ErrorLog();
           log.setMsg(ex.getMessage());
           ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	
	}
	
	public int gettotresolvetrimax(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					 "(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
			 		"etm_created_date FROM etm_device_history edh " +
						"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
						"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
			 if(orgtTypeId!=0 && orgtTypeId==1){
					ordchartcompare = " where etm_pickup_by_fme is null and etm_resolved_by_fme is null and etm_pickup_by_verifone is null   ";
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					ordchartcompare = " where etm_pickup_by_fme is null and etm_resolved_by_fme is  null and etm_pickup_by_verifone is null and division_id='"+orgtTypeId+"'  ";
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					ordchartcompare = " where etm_pickup_by_fme is null and etm_resolved_by_fme is  null and etm_pickup_by_verifone is null and depot_id='"+orgchartid+"'   ";
				}
			//	sql += ordchartcompare +" AND employee.status='Active'";	
				sql += ordchartcompare;	

				
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			
				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					if(orgtTypeId!=0 && orgtTypeId==1){
						sql += " and (device_serial_number like '%" + search_term + "%'";	
					}else{
					sql += " and (device_serial_number like '%" + search_term + "%'";
					}
					
					sql += " OR issue_name like '%"+search_term+"%')";
				}
				
				sql += " group by device_serial_number  ";
				
				
				
				
				
				if (!col.equals("")) {
					if (dir.equals("asc")) {
						sql += " order by " + col + " asc";
					} else {
						sql += " order by " + col + " desc";
					}
				}
				
				Query query = session.createSQLQuery(sql)
						.addScalar("id", Hibernate.STRING)
						.addScalar("device_serial_number", Hibernate.STRING)
						.addScalar("phone_number", Hibernate.STRING)
						.addScalar("issue_name", Hibernate.STRING)
						.addScalar("etm_created_date", Hibernate.STRING);

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				cnt=aliasToValueMapList.size();
		} catch (Exception e) {

		}
		
	
		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
	
	}
	public JSONObject getreslovetrimax(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
					"etm_created_date FROM etm_device_history edh " +
					"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
					"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
		 if(orgtTypeId!=0 && orgtTypeId==1){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is  null and etm_pickup_by_verifone is null  ";
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " where etm_pickup_by_fme is  notnull and etm_resolved_by_fme is   null and etm_pickup_by_verifone is null and division_id='"+orgtTypeId+"'   ";
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is  null and etm_pickup_by_verifone is null and depot_id='"+orgchartid+"'  ";
			}
		//	sql += ordchartcompare +" AND employee.status='Active'";	
			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				if(orgtTypeId!=0 && orgtTypeId==1){
					sql += " and (device_serial_number like '%" + search_term + "%'";	
				}else{
				sql += " and (device_serial_number like '%" + search_term + "%'";
				}
				
				sql += " OR issue_name like '%"+search_term+"%')";
			}
			
			sql += " group by device_serial_number  ";	
			
			
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			if(!request.getParameter("iDisplayLength").equals("-1")){
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			}
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.STRING)
					.addScalar("device_serial_number", Hibernate.STRING)
					.addScalar("phone_number", Hibernate.STRING)
					.addScalar("issue_name", Hibernate.STRING)
					.addScalar("etm_created_date", Hibernate.STRING);

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
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("id")+"' value='"+rs.get("id")+"'/>");
				ja.add(rs.get("id").toString());
				ja.add(rs.get("device_serial_number").toString());
				ja.add(rs.get("phone_number").toString());
				
				if(rs.get("etm_created_date").toString()==null || rs.get("etm_created_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_created_date").toString());
				}
				ja.add(rs.get("issue_name").toString());
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
         System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
           logger.error(this.getClass() + "$Exception in LoginAction action",ex);
           SaveRequest.save(request);
           ErrorLog log = new ErrorLog();
           log.setMsg(ex.getMessage());
           ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	
	}
	
	
	public int gettotreceiveverifone(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					 "(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
			 		" etm_created_date FROM etm_device_history edh " +
						"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
						"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
			 if(orgtTypeId!=0 && orgtTypeId==1){
					ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is not null and etm_received_by_fme is null   ";
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is not null and etm_received_by_fme is null  and division_id='"+orgtTypeId+"'  ";
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is not null and etm_received_by_fme is null  and depot_id='"+orgchartid+"'  ";
				}
			//	sql += ordchartcompare +" AND employee.status='Active'";	
				sql += ordchartcompare;	

				
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			
				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					if(orgtTypeId!=0 && orgtTypeId==1){
						sql += " and (device_serial_number like '%" + search_term + "%'";	
					}else{
					sql += " and (device_serial_number like '%" + search_term + "%'";
					}
					
					sql += " OR issue_name like '%"+search_term+"%')";
				}
				
				sql += " group by device_serial_number  ";	
				
				
				
				
				if (!col.equals("")) {
					if (dir.equals("asc")) {
						sql += " order by " + col + " asc";
					} else {
						sql += " order by " + col + " desc";
					}
				}
				
				Query query = session.createSQLQuery(sql)
						.addScalar("id", Hibernate.STRING)
						.addScalar("device_serial_number", Hibernate.STRING)
						.addScalar("phone_number", Hibernate.STRING)
						.addScalar("issue_name", Hibernate.STRING)
						.addScalar("etm_created_date", Hibernate.STRING);

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				cnt=aliasToValueMapList.size();
		} catch (Exception e) {

		}
		
	
		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
	
	}
	public JSONObject getreceiveverifone(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
					"etm_created_date FROM etm_device_history edh " +
					"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
					"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
		 if(orgtTypeId!=0 && orgtTypeId==1){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is not null and etm_received_by_fme is null   ";
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is not null and etm_received_by_fme is null  and division_id='"+orgtTypeId+"'   ";
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is not null and etm_received_by_fme is null  and depot_id='"+orgchartid+"'  ";
			}
		//	sql += ordchartcompare +" AND employee.status='Active'";	
			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				if(orgtTypeId!=0 && orgtTypeId==1){
					sql += " and (device_serial_number like '%" + search_term + "%'";	
				}else{
				sql += " and (device_serial_number like '%" + search_term + "%'";
				}
				
				sql += " OR issue_name like '%"+search_term+"%')";
			}
			
			sql += " group by device_serial_number  ";	
			
			
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			if(!request.getParameter("iDisplayLength").equals("-1")){
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			}
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.STRING)
					.addScalar("device_serial_number", Hibernate.STRING)
					.addScalar("phone_number", Hibernate.STRING)
					.addScalar("issue_name", Hibernate.STRING)
					.addScalar("etm_created_date", Hibernate.STRING);

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
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("id")+"' value='"+rs.get("id")+"'/>");
				ja.add(rs.get("id").toString());
				ja.add(rs.get("device_serial_number").toString());
				ja.add(rs.get("phone_number").toString());
				
				if(rs.get("etm_created_date").toString()==null || rs.get("etm_created_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_created_date").toString());
				}
				ja.add(rs.get("issue_name").toString());
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
         System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
           logger.error(this.getClass() + "$Exception in LoginAction action",ex);
           SaveRequest.save(request);
           ErrorLog log = new ErrorLog();
           log.setMsg(ex.getMessage());
           ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	
	}
	
	public int gettotreceivetrimax(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					 "(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
			 		"etm_created_date FROM etm_device_history edh " +
						"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
						"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
			 if(orgtTypeId!=0 && orgtTypeId==1){
					ordchartcompare = " where  (etm_received_by_fme is not null || etm_resolved_by_fme is not null) and etm_received_date is null    ";
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					ordchartcompare = " where (etm_received_by_fme is not null || etm_resolved_by_fme is not null) and etm_received_date is null  and division_id='"+orgtTypeId+"'   ";
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					ordchartcompare = " where (etm_received_by_fme is not null || etm_resolved_by_fme is not null) and etm_received_date is null  and depot_id='"+orgchartid+"'  ";
				}
			//	sql += ordchartcompare +" AND employee.status='Active'";	
				sql += ordchartcompare;	

				
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			
				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					if(orgtTypeId!=0 && orgtTypeId==1){
						sql += " and (device_serial_number like '%" + search_term + "%'";	
					}else{
					sql += " and (device_serial_number like '%" + search_term + "%'";
					}
					
					sql += " OR issue_name like '%"+search_term+"%')";
				}
				
				sql += " group by device_serial_number  ";	
				
				
				
				
				if (!col.equals("")) {
					if (dir.equals("asc")) {
						sql += " order by " + col + " asc";
					} else {
						sql += " order by " + col + " desc";
					}
				}
				
				Query query = session.createSQLQuery(sql)
						.addScalar("id", Hibernate.STRING)
						.addScalar("device_serial_number", Hibernate.STRING)
						.addScalar("phone_number", Hibernate.STRING)
						.addScalar("issue_name", Hibernate.STRING)
						.addScalar("etm_created_date", Hibernate.STRING);

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				cnt=aliasToValueMapList.size();
		} catch (Exception e) {

		}
		
	
		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
	
	}
	public JSONObject getreceivetrimax(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
					" etm_created_date FROM etm_device_history edh " +
					"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
					"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
		 if(orgtTypeId!=0 && orgtTypeId==1){
				ordchartcompare = " where  (etm_received_by_fme is not null || etm_resolved_by_fme is not null) and etm_received_date is null    ";
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " where (etm_received_by_fme is not null || etm_resolved_by_fme is not null) and etm_received_date is null  and division_id='"+orgtTypeId+"'   ";
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				ordchartcompare = " where (etm_received_by_fme is not null || etm_resolved_by_fme is not null) and etm_received_date is null  and depot_id='"+orgchartid+"'  ";
			}
		//	sql += ordchartcompare +" AND employee.status='Active'";	
			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				if(orgtTypeId!=0 && orgtTypeId==1){
					sql += " and (device_serial_number like '%" + search_term + "%'";	
				}else{
				sql += " and (device_serial_number like '%" + search_term + "%'";
				}
				
				sql += " OR issue_name like '%"+search_term+"%')";
			}
			
			sql += " group by device_serial_number  ";	
			
			
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			if(!request.getParameter("iDisplayLength").equals("-1")){
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			}
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.STRING)
					.addScalar("device_serial_number", Hibernate.STRING)
					.addScalar("phone_number", Hibernate.STRING)
					.addScalar("issue_name", Hibernate.STRING)
					.addScalar("etm_created_date", Hibernate.STRING);

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
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("id")+"' value='"+rs.get("id")+"'/>");
				ja.add(rs.get("id").toString());
				ja.add(rs.get("device_serial_number").toString());
				ja.add(rs.get("phone_number").toString());
				
				if(rs.get("etm_created_date").toString()==null || rs.get("etm_created_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_created_date").toString());
				}
				ja.add(rs.get("issue_name").toString());
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
         System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
           logger.error(this.getClass() + "$Exception in LoginAction action",ex);
           SaveRequest.save(request);
           ErrorLog log = new ErrorLog();
           log.setMsg(ex.getMessage());
           ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	
	}

	public int gettotetmresolve(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'') phone_number," +
					 "(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
			 		"etm_created_date FROM etm_device_history edh " +
						"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
						"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
			 if(orgtTypeId!=0 && orgtTypeId==1){
					ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is null and etm_received_by_fme is null and	etm_received_date is null ";
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is  null and etm_received_by_fme is null and	etm_received_date is null  and division_id='"+orgtTypeId+"'  ";
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is  null and etm_received_by_fme is null and	etm_received_date is null   and depot_id='"+orgchartid+"'  ";
				}
			//	sql += ordchartcompare +" AND employee.status='Active'";	
				sql += ordchartcompare;	

				
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			
				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					if(orgtTypeId!=0 && orgtTypeId==1){
						sql += " and (device_serial_number like '%" + search_term + "%'";	
					}else{
					sql += " and (device_serial_number like '%" + search_term + "%'";
					}
					
					sql += " OR issue_name like '%"+search_term+"%')";
				}
				
				sql += " group by device_serial_number  ";	
				
				
				
				
				if (!col.equals("")) {
					if (dir.equals("asc")) {
						sql += " order by " + col + " asc";
					} else {
						sql += " order by " + col + " desc";
					}
				}
				
				Query query = session.createSQLQuery(sql)
						.addScalar("id", Hibernate.STRING)
						.addScalar("device_serial_number", Hibernate.STRING)
						.addScalar("phone_number", Hibernate.STRING)
						.addScalar("issue_name", Hibernate.STRING)
						.addScalar("etm_created_date", Hibernate.STRING);

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				cnt=aliasToValueMapList.size();
		} catch (Exception e) {

		}
		
	
		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
	
	}
	public JSONObject getetmresolve(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord){

		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql="SELECT edh.id as id,oc.org_name,device_serial_number, ifnull(sd.phone_number,'') phone_number," +
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
					" etm_created_date FROM etm_device_history edh " +
					"inner join device d on d.device_id=edh.etm_number "+
					"inner join org_chart oc on oc.org_chart_id=edh.depot_id "+ 
					"left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
					"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue ";
		 if(orgtTypeId!=0 && orgtTypeId==1){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is null and etm_received_by_fme is null and	etm_received_date is null ";
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is  null  and etm_received_by_fme is null and	etm_received_date is null and division_id='"+orgtTypeId+"'  ";
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				ordchartcompare = " where etm_pickup_by_fme is not null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and etm_resolved_by_verifone is  null and etm_received_by_fme is null and	etm_received_date is null   and depot_id='"+orgchartid+"'  ";
			}
		//	sql += ordchartcompare +" AND employee.status='Active'";	
			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				if(orgtTypeId!=0 && orgtTypeId==1){
					sql += " and (device_serial_number like '%" + search_term + "%'";	
				}else{
				sql += " and (device_serial_number like '%" + search_term + "%'";
				}
				
				sql += " OR issue_name like '%"+search_term+"%')";
			}
			
			sql += " group by device_serial_number  ";	
			
			
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			if(!request.getParameter("iDisplayLength").equals("-1")){
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			}
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("id", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("device_serial_number", Hibernate.STRING)
					.addScalar("phone_number", Hibernate.STRING)
					.addScalar("issue_name", Hibernate.STRING)
					.addScalar("etm_created_date", Hibernate.STRING);

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
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("id")+"' value='"+rs.get("id")+"'/>");
				ja.add(rs.get("id").toString());
				ja.add(rs.get("org_name").toString());
				ja.add(rs.get("device_serial_number").toString());
				ja.add(rs.get("phone_number").toString());
				
				if(rs.get("etm_created_date").toString()==null || rs.get("etm_created_date").toString().equalsIgnoreCase("")){
					ja.add("");
				}else{
					ja.add(rs.get("etm_created_date").toString());
				}
				ja.add(rs.get("issue_name").toString());
				ja.add("<input type='text' id='"+rs.get("id")+"id' />");
				//System.out.println("text feild id is--------"+"<input type='text' id='"+rs.get("id")+"id' value='"+rs.get("id")+"id'  name="+"textname"+"/>");
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
         System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
           logger.error(this.getClass() + "$Exception in LoginAction action",ex);
           SaveRequest.save(request);
           ErrorLog log = new ErrorLog();
           log.setMsg(ex.getMessage());
           ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	
	}
	public int updateisuueagain(String id,String status){

		Session session = null;
		int count=0;
		int count1=0;
		try {
			int userid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql= "update etm_device_history set  etm_pickup_by_verifone=NULL,etm_resolved_by_verifone=NULL,etm_received_by_fme=NULL,remarks='"+status+"',fme_id="+userid+" where id in ("+id+")  ";
	        Query qury = session.createSQLQuery(sql);
		count=qury.executeUpdate();
		String sql1="select (case when etm_issue=0 THEN (select group_concat(etm_issue_id) from  etm_device_issue  "+
              "where etm_device_history_id=edh.id) ELSE etm_issue end) issue_name "+
              "from etm_device_history edh  where id="+id;
              Query query1 = session.createSQLQuery(sql1).addScalar("issue_name", Hibernate.STRING);
			List aliasToValueMapList1 = query1.list();
			String isse=(String) aliasToValueMapList1.get(0);
			String a[]=isse.split(",");
		for(int i=0;i<a.length;i++){
			String sql2= "Insert into fme_reject (etm_device_history_id,etm_issue_id,insert_date) values("+id+","+a[i]+",now())";
	        Query qury2 = session.createSQLQuery(sql2);
		count1=qury2.executeUpdate();
		}
		System.out.println("count======"+count);
		if(count>0 && count1>0){
			session.getTransaction().commit();
		}
		
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if(count==0 || count1==0){
			count=0;
		}
		return count;
	
	}
	
}