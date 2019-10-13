package com.trimax.its.route.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.doa.ticketing.model.TicketInventory;
import com.trimax.doa.ticketing.model.TicketInventoryLog;
import com.trimax.doa.ticketing.model.TicketInventoryMaster;
import com.trimax.doa.ticketing.model.TicketInvoiceDetails;
import com.trimax.doa.ticketing.model.TicketInvoiceMaster;
import com.trimax.its.common.Common;
import com.trimax.its.inventoryticketpasstype.model.InventoryTicketPassType;
import com.trimax.its.route.model.Route;
import com.trimax.its.route.model.RouteMap;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;

public class TicketInventoryDAO {
	
	Common common = new Common();
	
	@SuppressWarnings("finally")
	public String getOrgName(int orgId){

		Session session = HibernateUtil.getSession("");
		String orgName = null;
		try {
			orgName = common.getDBResultStr(session, "select org_name from org_chart where org_chart_id='"+orgId+"'", "org_name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			return orgName;
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTicketData(Session sessiontick,int unit_type,int unit_name ) {
		//Session sessiontick = null;
		String sql1="";
		try {
			
		
			//sessiontick = HibernateUtilTick.getSession("hibernate.cfg.xml");

			sql1 = "select  dy.denomination_type_manual,a.ticket_type_manual_id,a.denomination_key, a.opening_number, a.closing_number,a.number_of_tickets,"
					+ " a.number_of_books, a.value,ot.org_type,oc.org_name,a.priority,a.created_date from (SELECT denomination_type_manual_id, denomination_key,  min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,issue_to_unit unit_type,issue_to_unit_id unit_name,ticket_type_manual_id,priority,tid.created_date  " 
					+ " FROM ticket_inventory_details tid  " 
					+ " inner join  ticket_invoice_details tind on tind.ticket_inventory_det_id =tid.ticket_inventory_det_id" 
					+ " inner join ticket_invoice_master tim on tim.ticket_invoice_mast_id=tind.ticket_invoice_mast_id" 
					+ " WHERE tid.status = 'ACTIVE' and tid.current_status='Issued' and tid.stock_entry='Y'" 
					+ " group by tid.denomination_key, tid.denomination_type_manual_id  ) a "
					+ " inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id"
					+ " inner join  org_type ot on a.unit_type=ot.org_type_id" 
					+ " inner join org_chart oc on a.unit_name=oc.org_chart_id"
					+ " and a.ticket_type_manual_id=1";
					
				//if(unit_type!=0){
					 sql1 +=	 " where ot.org_type_id='"+unit_type+"'";
			//	}
				if(unit_name!=0){
					 sql1 +=	 " and oc.org_chart_id='"+unit_name+"'";
				}
				sql1 +=	 " order by CAST(dy.denomination_type_manual as UNSIGNED),a.number_of_tickets ";
//			System.out.println(sql1);
			sessiontick.flush();
			sessiontick.clear();
			
		} catch(Exception e){
			
		}
			Query query = sessiontick.createSQLQuery(sql1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2=null;
			aliasToValueMapList2 = query.list();
			return aliasToValueMapList2;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> gePasstData(Session sessiontick,int unit_type,int unit_name ) {
		//Session sessiontick = null;
		String sql1="";
		try {
			
		
			//sessiontick = HibernateUtilTick.getSession("hibernate.cfg.xml");

			 sql1 = "select dy.denomination_type_manual,a.pass_day,a.ticket_type_manual_id,a.denomination_key, ttm.ticket_type_manual_name,a.opening_number, "
					+ "a.closing_number,a.number_of_tickets, a.number_of_books, a.value,a.priority, a.unit_type,a.unit_name,ot.org_type,oc.org_name,a.created_date from "
					+ " (SELECT denomination_type_manual_id, denomination_key,  min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,issue_to_unit unit_type,issue_to_unit_id unit_name,ticket_type_manual_id,priority, pass_day,tid.created_date  " 
					+ " FROM ticket_inventory_details tid  " 
					+ " inner join  ticket_invoice_details tind on tind.ticket_inventory_det_id =tid.ticket_inventory_det_id" 
					+ " inner join ticket_invoice_master tim on tim.ticket_invoice_mast_id=tind.ticket_invoice_mast_id" 
					+ " WHERE tid.status = 'ACTIVE' and tid.current_status='Issued' and tid.stock_entry='Y'" 
					+ " group by tid.denomination_key, tid.denomination_type_manual_id  ) a "
					+ " inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id "
					+ " inner join  org_type ot on a.unit_type=ot.org_type_id" 
					+ " inner join org_chart oc on a.unit_name=oc.org_chart_id"
					
					+ " and a.ticket_type_manual_id in(2,3) " ;
			 if(unit_type!=0){
				 sql1 +=	 " where ot.org_type_id='"+unit_type+"'";
			 }
			 if(unit_name!=0){
				 sql1 +=	 " and oc.org_chart_id='"+unit_name+"'";
			 }
			sql1 +=	 " order by CAST(dy.denomination_type_manual as UNSIGNED),a.number_of_tickets ";
//			System.out.println(sql1);
			sessiontick.flush();
			sessiontick.clear();
			
		} catch(Exception e){
			
		}
			Query query = sessiontick.createSQLQuery(sql1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2=null;
			aliasToValueMapList2 = query.list();
			return aliasToValueMapList2;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLuggData(Session sessiontick,int unit_type,int unit_name ) {
		//Session sessiontick = null;
		String sql1="";
		try {
			
		
			//sessiontick = HibernateUtilTick.getSession("hibernate.cfg.xml");

			sql1 = "select dy.denomination_type_manual, a.denomination_key,a.priority, a.opening_number, a.closing_number,"
					+ " a.number_of_tickets, a.number_of_books, a.value,a.ticket_type_manual_id,a.unit_name,a.unit_type,ot.org_type,oc.org_name,a.created_date from (SELECT denomination_type_manual_id, denomination_key,  min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,issue_to_unit unit_type,issue_to_unit_id unit_name,ticket_type_manual_id,priority,tid.created_date  " 
					+ " FROM ticket_inventory_details tid  " 
					+ " inner join  ticket_invoice_details tind on tind.ticket_inventory_det_id =tid.ticket_inventory_det_id" 
					+ " inner join ticket_invoice_master tim on tim.ticket_invoice_mast_id=tind.ticket_invoice_mast_id" 
					+ " WHERE tid.status = 'ACTIVE' and tid.current_status='Issued' and tid.stock_entry='Y' " 
					+ " group by tid.denomination_key, tid.denomination_type_manual_id  ) a "
					+ " inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id"
					+ " inner join  org_type ot on a.unit_type=ot.org_type_id" 
					+ " inner join org_chart oc on a.unit_name=oc.org_chart_id"
					+ " and a.ticket_type_manual_id=4" ;
					
			if(unit_type!=0){
				 sql1 +=	 " where ot.org_type_id='"+unit_type+"'";
			}
			if(unit_name!=0){
				 sql1 +=	 " and oc.org_chart_id='"+unit_name+"'";
			}		
			sql1 += " order by  a.number_of_tickets ";
//			System.out.println(sql1);
			sessiontick.flush();
			sessiontick.clear();
			
		} catch(Exception e){
			
		}
			Query query = sessiontick.createSQLQuery(sql1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2=null;
			aliasToValueMapList2 = query.list();
			return aliasToValueMapList2;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTSheetData(Session sessiontick,int unit_type,int unit_name ) {
		//Session sessiontick = null;
		String sql1="";
		try {
			
		
			//sessiontick = HibernateUtilTick.getSession("hibernate.cfg.xml");

			sql1 = "select dy.denomination_type_manual, a.denomination_key, a.opening_number, a.closing_number,"
					+ "a.number_of_passes,ot.org_type,oc.org_name,a.created_date from(SELECT denomination_type_manual_id, denomination_key,  min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_passes) as number_of_passes,issue_to_unit unit_type,issue_to_unit_id unit_name,ticket_type_manual_id,priority,tid.created_date  " 
					+ " FROM ticket_inventory_details tid  " 
					+ " inner join  ticket_invoice_details tind on tind.ticket_inventory_det_id =tid.ticket_inventory_det_id" 
					+ " inner join ticket_invoice_master tim on tim.ticket_invoice_mast_id=tind.ticket_invoice_mast_id" 
					+ " WHERE tid.status = 'ACTIVE' and tid.current_status='Issued' and tid.stock_entry='Y' " 
					+ " group by tid.denomination_key, tid.denomination_type_manual_id  ) a "
					+ " inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id"
					+ " inner join  org_type ot on a.unit_type=ot.org_type_id" 
					+ " inner join org_chart oc on a.unit_name=oc.org_chart_id"
					+ " and a.ticket_type_manual_id=5" ;
					
			if(unit_type!=0){
				 sql1 +=	 " where ot.org_type_id='"+unit_type+"'";
			}
			if(unit_name!=0){
				 sql1 +=	 " and oc.org_chart_id='"+unit_name+"'";
			}		
			sql1 += " order by  a.number_of_passes ";
//			System.out.println(sql1);
			sessiontick.flush();
			sessiontick.clear();
			
		} catch(Exception e){
			
		}
			Query query = sessiontick.createSQLQuery(sql1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2=null;
			aliasToValueMapList2 = query.list();
			return aliasToValueMapList2;
	}
	
	public String getDenomlist() {
		List<Map<String, Object>> rolelist = null;
		String result = "";
		try {
			// String
			// sql="select role_id,role_name from role where deleted_status=0 ";
			String sql = "select distinct denomination_type_manual_id,convert(denomination_type_manual,unsigned integer) as denomination_type_manual from denomination_type_manual dtm inner join "
					+ "ticket_type_manual ttm on dtm.ticket_type_manual_id=ttm.ticket_type_manual_id and dtm.ticket_type_manual_id=1 and dtm.deleted_status='0' "
					+ "order by CAST(denomination_type_manual AS UNSIGNED) asc";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			rolelist = query.list();
			/*
			 * data += result.getRoute_id()+"@"; data +=
			 * result.getRouteNumber()+",";
			 */
			if (rolelist.size() > 0) {  

				for (int i = 0; i < rolelist.size(); i++) {
					Map<String, Object> rs = rolelist.get(i);
					String roleid = rs.get("denomination_type_manual_id")
							.toString();
					String rolename = rs.get("denomination_type_manual")
							.toString();
					result += roleid + "@";
					result += rolename + ",";
					
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String getPassTypelist() {
		//System.out.println("inside get denom list dao....");
		List<Map<String, Object>> rolelist = null;
		String result = "";
		try {
			// String
			// sql="select role_id,role_name from role where deleted_status=0 ";
			String sql = "select 	ticket_type_manual_id,	ticket_type_manual_name, block_size" +
					" from ticket_type_manual where status='ACTIVE' and deleted_status=0 and ticket_type_manual_id in('2','3')";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			rolelist = query.list();
			/*
			 * data += result.getRoute_id()+"@"; data +=
			 * result.getRouteNumber()+",";
			 */
			if (rolelist.size() > 0) {  

				for (int i = 0; i < rolelist.size(); i++) {
					Map<String, Object> rs = rolelist.get(i);
					String roleid = rs.get("ticket_type_manual_id")
							.toString();
					String rolename = rs.get("ticket_type_manual_name")
							.toString();
					result += roleid + "@";
					result += rolename + ",";
					
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String getPasslist(String passtype) {
		 
		List<Map<String, Object>> rolelist = null;
		String result = "";
		try {
			// String
			// sql="select role_id,role_name from role where deleted_status=0 ";
			String sql = "select distinct denomination_type_manual_id,convert(denomination_type_manual,unsigned integer) as denomination_type_manual  from denomination_type_manual dtm inner join "
					+ "ticket_type_manual ttm on dtm.ticket_type_manual_id=ttm.ticket_type_manual_id and dtm.ticket_type_manual_id="
					+passtype+ " and dtm.deleted_status='0' order by CAST(denomination_type_manual AS UNSIGNED) asc";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			rolelist = query.list();
			/*
			 * data += result.getRoute_id()+"@"; data +=
			 * result.getRouteNumber()+",";
			 */
			if (rolelist.size() > 0) {

				for (int i = 0; i < rolelist.size(); i++) {
					Map<String, Object> rs = rolelist.get(i);
					String roleid = rs.get("denomination_type_manual_id")
							.toString();
					String rolename = rs.get("denomination_type_manual")
							.toString();
					result += roleid + "@";
					result += rolename + ",";
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int getMaxPriority(String denomno, String denomKey, String ticketType) {
		// TODO Auto-generated method stub
		Session sessiondenompriority = null;
		int priority = 0;
		try {
			sessiondenompriority = HibernateUtilTick.getSession("hibernate.cfg.xml");
			if(denomno.equals("nodenom")){
				priority = Integer.parseInt(sessiondenompriority
						.createSQLQuery("select IFNULL(max(priority),0)+1 from  ticket_inventory_details where ticket_type_manual_id='"+ticketType+"' and status='ACTIVE'  and denomination_key= '"+denomKey+"' ")
						.uniqueResult().toString());	
			}else{
			priority = Integer.parseInt(sessiondenompriority
					.createSQLQuery("select IFNULL(max(priority),0)+1 from  ticket_inventory_details where ticket_type_manual_id='"+ticketType+"' and status='ACTIVE'  and denomination_key= '"+denomKey+"' and denomination_type_manual_id="+denomno)
					.uniqueResult().toString());
			}
			//System.out.println("ticket type priority.."+priority);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessiondenompriority != null && sessiondenompriority.isOpen()) {
				sessiondenompriority.close();
				sessiondenompriority = null;
			}
			
		}

		return priority;
	}
	
	
	public String getLuggageDenomVal() {
		Session session = null;
		String luggagedenomid = "";
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			luggagedenomid = session
					.createSQLQuery(
							"select denomination_type_manual_id from denomination_type_manual where ticket_type_manual_id=4 limit 1")
					.uniqueResult().toString();
			System.out.println(luggagedenomid);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			
		}
		return luggagedenomid;

	}
	public String getTsheetDenomVal() {
		Session session = null;
		String tsheetdenomid = "";
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			tsheetdenomid = session
					.createSQLQuery(
							"select denomination_type_manual_id from denomination_type_manual where ticket_type_manual_id=5 limit 1")
					.uniqueResult().toString();
//			System.out.println(tsheetdenomid);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			
		}
		return tsheetdenomid;

	}
	
	public String insertTicketInventoryMaster(TicketInventoryMaster ticketinvmaster,Session session){
		session.save(ticketinvmaster);	
		return "success";
		
	}
	
	
	public String insertTicketInventoryDetails(TicketInventory ticketinvdetail,Session session){
		session.save(ticketinvdetail);	
		return "success";
		
	}
	
	
	public int checkForDuplicateTicketEntry(String denomno, String denomkey,String startno, String endno, Session session, String tickettype) {
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String sql="select IFNULL(count(*),0) from ticket_inventory_master where " +
					   " denoimination_type_manual_id='"+denomno+"' and ticket_type_manual_id='"+tickettype+"' " +
					   " and status='ACTIVE' and denomination_key='"+denomkey+"' and (" +
					   "'"+startno+"' between " +
					   " (select IFNULL(min(opening_number),0) " +
					   " from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' " +
					   " and ticket_type_manual_id='"+tickettype+"' and  denomination_key='"+denomkey+"' " +
					   " and status='ACTIVE')  " +
					   " and " +
					   " (select IFNULL(max(closing_number),0) " +
					   " from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' " +
					   " and ticket_type_manual_id='"+tickettype+"' " +
					   " and denomination_key='"+denomkey+"' and status='ACTIVE') " +
					   " OR " +
					   " '"+endno+"' between " +
					   " (select IFNULL(min(opening_number),0) " +
					   " from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' " +
					   " and ticket_type_manual_id='"+tickettype+"' and  denomination_key='"+denomkey+"' " +
					   " and status='ACTIVE') " +
					   " and  " +
					   " (select IFNULL(max(closing_number),0) " +
					   " from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' " +
					   " and ticket_type_manual_id='"+tickettype+"' " +
					   " and denomination_key='"+denomkey+"' and status='ACTIVE'))"
					;

			count = Integer.parseInt(session.createSQLQuery(sql).uniqueResult().toString());
//			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			
		}
		return count;
		
	}

			
	public String insertTicketInvoiceMaster(TicketInvoiceMaster ticketinvoicemaster,Session session){
		session.save(ticketinvoicemaster);	
		return "success";
		
	}
	
	public String insertTicketInvoiceDetail(TicketInvoiceDetails ticketInvoiceDetails,Session session){
		session.save(ticketInvoiceDetails);	
		return "success";
		
	}
	
	public String insertTicketInventoryLog(TicketInventoryLog ticketInventoryLog,Session session){
		session.save(ticketInventoryLog);	
		return "success";
		
	}
	
	public String updateTicketinvdetail(TicketInventory ticketinvdetail,Session session){
		session.update(ticketinvdetail);
		return "success";
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<InventoryTicketPassType> getdaypass(){
		//System.out.println("in execute");
		List<InventoryTicketPassType> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from InventoryTicketPassType where 	pass_type='2'");

		//query.setMaxResults(500);
		list = (List<InventoryTicketPassType>) query.list();
		} catch(Exception e){
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<InventoryTicketPassType> getmonthpass(){
		//System.out.println("in execute");
		List<InventoryTicketPassType> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from InventoryTicketPassType where 	pass_type='3'");

		//query.setMaxResults(500);
		list = (List<InventoryTicketPassType>) query.list();
		} catch(Exception e){
			//System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list;
		
	}
	public List<String> getUnitId(int orgtypeid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			String qry = "select org_chart_id from org_chart where org_name!='(NULL)' and org_type_id="
					+ orgtypeid + " and deleted_status='0'";

			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
		
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_chart_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}

	public List<String> getUnitName(int orgtypeid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
		
			String qry = "select org_name from org_chart where org_name!='(NULL)' and org_type_id="
					+ orgtypeid + " and deleted_status='0'";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
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
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}

	public List<String> getOrgTypeId() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
		
			String qry = "select org_type_id from org_type where org_type in('CENTRAL OFFICE','DIVISION','ACCOUNT','DEPOT')";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_type_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}

	public List<String> getOrgTypeName() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			String qry = "select org_type from org_type where org_type in('CENTRAL OFFICE','DIVISION','STORE','ACCOUNT','DEPOT')";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_type").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}
	@SuppressWarnings("finally")
	public String getVoucherNo(){
		String voucherNo = "";
		Session session = null;
		try{
			session = HibernateUtilTick.getSession("");
			String sql = "select lpad(ifnull(count(ticket_invoice_mast_id),0)+1,10,0) voucher_number  from ticket_invoice_master where voucher_type='I'";
			voucherNo = common.getDBResultStr(session, sql, "voucher_number");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!= null){
				session.close();
			}
			return voucherNo;
		}
		
	}

}
