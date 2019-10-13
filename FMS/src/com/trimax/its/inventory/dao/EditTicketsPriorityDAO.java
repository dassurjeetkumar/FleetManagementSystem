package com.trimax.its.inventory.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtilTick;

public class EditTicketsPriorityDAO {

	Common common = new Common();
	
	public void updateTicketInventoryMasterPriority(String denomkey, String priority) {
		Session session = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String qry = "update ticket_inventory_master set priority='"+ priority + "' where ticket_inventory_mst_id='" + denomkey	+ "'";
			Query query = session.createSQLQuery(qry);
			int i = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

	}
	
	public void updatePriorityTicketInventoryDetails(String denomkey,
			String priority) {
		Session session = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String qry = "update ticket_inventory_details set priority='"+ priority + "' where ticket_inventory_mst_id='" + denomkey+ "'";
			Query query = session.createSQLQuery(qry);
			int i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		
	}
	@SuppressWarnings({ "unchecked", "finally" })
	public JSONObject getEditLuggageData( HttpServletRequest request,	String arr, String col, String dir) {
		
		String str = arr;
		Session session = null;
		JSONObject result = new JSONObject();
		int totalAfterFilter = 0;
		JSONArray array = new JSONArray();
		try{
			if(arr.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], strOpeningNumber = new String[s.length],strDenomValue = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						count++;
					}
				}
				String sql = " from TicketInventory";
				session = HibernateUtilTick.getSession("hibernate.cfg.xml");
				for(int j=0;j<count;j++){
					String sql1 = "select  a.ticket_inventory_mst_id,dy.denomination_type_manual,a.ticket_type_manual_id,a.denomination_key, a.opening_number," +
							" a.closing_number,a.number_of_passes, a.number_of_books,a.value,ot.org_type,oc.org_name,a.priority," +
							" a.created_date from (SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key, " +
							" min(opening_number) as opening_number, max(closing_number) as closing_number, " +
							" sum(number_of_passes) as number_of_passes,sum(number_of_books) as number_of_books,sum(value) as value," +
							" unit_type,unit_name,ticket_type_manual_id,priority,tid.created_date  FROM ticket_inventory_details tid  " +
							" WHERE tid.status = 'ACTIVE' and tid.current_status='New'  group by tid.denomination_key, " +
							" tid.denomination_type_manual_id,tid.created_date  order by tid.created_date) a  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id " +
							" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id " +
							" inner join  org_type ot on a.unit_type=ot.org_type_id " +
							" inner join org_chart oc on a.unit_name=oc.org_chart_id and a.ticket_type_manual_id ='4'  "+
							" and denomination_key= '"+strkey[j]+"' and opening_number='"+strOpeningNumber[j]+"'"+
							" ";
					Query query = session.createSQLQuery(sql1);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					if(count>0){
						List<Map<String, Object>> aliasToValueMapList = query.list();
						if(aliasToValueMapList.size()>0) {
							Map<String, Object> rs = aliasToValueMapList.get(0);
							JSONArray ja = new JSONArray();
							ja.add(rs.get(""));
							ja.add(rs.get("denomination_key"));
							ja.add(common.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
							ja.add(common.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
							ja.add(rs.get("number_of_passes"));
							ja.add(rs.get("number_of_books"));
							ja.add(rs.get("value"));
							ja.add(rs.get("priority"));
							ja.add("<a href='#' class='editluggage' id='luggage_edit_"+ rs.get("ticket_inventory_mst_id") + "' value='"+ rs.get("ticket_inventory_mst_id") + "'> Edit</a>");
							ja.add("");
							array.add(ja);
						}
					}
				}
//				totalAfterFilter = getTotalEditLuggageRecords(str);
				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return result;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public JSONObject getEditPassData(HttpServletRequest request,String arr, String col, String dir) {
	
		Session session = null;
		String str = arr;
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		int totalAfterFilter = 0;
		try{
			if(arr.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], strOpeningNumber = new String[s.length],strDenomValue = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(!tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						count++;
					}
				}
				String searchSQL = "";
				String sql = " from TicketInventory";
				session = HibernateUtilTick.getSession("hibernate.cfg.xml");
				for(int j=0;j<count;j++){
					String sql1 = "select  a.ticket_inventory_mst_id,dy.denomination_type_manual,ttm.ticket_type_manual_name,a.pass_day,a.ticket_type_manual_id,a.denomination_key, a.opening_number," +
							" a.closing_number,a.number_of_passes, a.number_of_books, a.value,ot.org_type,oc.org_name,a.priority," +
							" a.created_date from (SELECT ticket_inventory_mst_id,denomination_type_manual_id,pass_day, denomination_key, " +
							" min(opening_number) as opening_number, max(closing_number) as closing_number, " +
							" sum(number_of_passes) as number_of_passes,sum(number_of_books) as number_of_books,sum(value) as value," +
							" unit_type,unit_name,ticket_type_manual_id,priority,tid.created_date  FROM ticket_inventory_details tid  " +
							" WHERE tid.status = 'ACTIVE' and tid.current_status='New'  group by tid.denomination_key, " +
							" tid.denomination_type_manual_id,tid.created_date  order by tid.created_date) a  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id " +
							" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id " +
							" inner join  org_type ot on a.unit_type=ot.org_type_id " +
							" inner join org_chart oc on a.unit_name=oc.org_chart_id and a.ticket_type_manual_id in ('2','3')  "+
							" and denomination_key= '"+strkey[j]+"' and opening_number='"+strOpeningNumber[j]+"'"+
							" and denomination_type_manual='"+strDenomValue[j]+"' ";
				/*	String sql1 = "select ticket_inventory_mst_id,pass_day,ttm.ticket_type_manual_name,number_of_passes,dy.denomination_type_manual,"
							+ " denomination_key,opening_number,closing_number,number_of_books,value,priority "
							+ " from ticket_inventory_master tm inner join denomination_type_manual dy "
							+ " on tm.denoimination_type_manual_id=dy.denomination_type_manual_id " 
							+ " inner join ticket_type_manual ttm on ttm.ticket_type_manual_id=tm.ticket_type_manual_id	 "
							+ " where tm.current_status='New' and ttm.ticket_type_manual_id in(2,3)"
							+ " and tm.status='ACTIVE' and denomination_key ='"+ strkey[j] + "' and opening_number ='"+strOpeningNumber[j]+"'"
							+ " and denomination_type_manual='"+strDenomValue[j]+"'";*/
					String search_term = request.getParameter("sSearch");
					Query query = session.createSQLQuery(sql1);
					if(count>0){
						query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = query.list();
						if(aliasToValueMapList.size()>0) {
							Map<String, Object> rs = aliasToValueMapList.get(0);
							JSONArray ja = new JSONArray();
							ja.add("");
							ja.add(rs.get("ticket_type_manual_name"));
							ja.add(rs.get("denomination_type_manual"));
							ja.add(rs.get("denomination_key"));
							ja.add(rs.get("pass_day"));
							ja.add(common.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
							ja.add(common.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
							ja.add(rs.get("number_of_passes"));
							ja.add(rs.get("number_of_books"));
							ja.add(rs.get("value"));
							ja.add(rs.get("priority"));
							ja.add("<a href='#' class='editpass' id='pass_edit_"+ rs.get("ticket_inventory_mst_id") + "' value='"+ rs.get("ticket_inventory_mst_id") + "'> Edit</a>");
							ja.add("");
							array.add(ja);
						}
					}
				}
			}
			result.put("aaData", array);
			result.put("iTotalRecords", 0);
			result.put("iTotalDisplayRecords", totalAfterFilter);
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			return result;
		}

}


	@SuppressWarnings({ "unchecked", "finally" })
	public JSONObject getEditData( HttpServletRequest request,String arr, String col, String dir) {
	
		Common cm=new Common();
		Session session = null;
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		
		int totalAfterFilter = 0;
		String str = arr;
		try {
			if(arr.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], strOpeningNumber = new String[s.length],strDenomValue = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(!tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						count++;
					}
				}
				System.out.println(strkey + "denomination type" + strdenomtype+"opening number"+strOpeningNumber);
				String searchSQL = "";
				String sql = " from TicketInventory";
				session = HibernateUtilTick.getSession("hibernate.cfg.xml");
				for(int k=0;k<count;k++){
					String sql1 = "select a.ticket_inventory_mst_id,dy.denomination_type_manual,a.ticket_type_manual_id,a.denomination_key, a.opening_number," +
							" a.closing_number,a.number_of_tickets, a.number_of_books, a.value,ot.org_type,oc.org_name,a.priority," +
							" a.created_date from (SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key, " +
							" min(opening_number) as opening_number, max(closing_number) as closing_number, " +
							" sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value," +
							" unit_type,unit_name,ticket_type_manual_id,priority,tid.created_date  FROM ticket_inventory_details tid  " +
							" WHERE tid.status = 'ACTIVE' and tid.current_status='New'  group by tid.denomination_key, " +
							" tid.denomination_type_manual_id,tid.created_date  order by tid.created_date) a  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id " +
							" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id " +
							" inner join  org_type ot on a.unit_type=ot.org_type_id " +
							" inner join org_chart oc on a.unit_name=oc.org_chart_id and a.ticket_type_manual_id=1  "+
							" and denomination_key= '"+strkey[k]+"' and opening_number='"+strOpeningNumber[k]+"'"+
							" and denomination_type_manual='"+strDenomValue[k]+"'";
					
				/*	String sql1 = "select ticket_inventory_mst_id,dy.denomination_type_manual,"
						+ " denomination_key,opening_number,closing_number,number_of_books,value,priority "
						+ " from ticket_inventory_master tm inner join denomination_type_manual dy "
						+ " on tm.denoimination_type_manual_id=dy.denomination_type_manual_id inner join ticket_type_manual ttm on ttm.ticket_type_manual_id=tm.ticket_type_manual_id	 "
						+ " where tm.current_status='New' and ttm.ticket_type_manual_id = 1"
						+ " and tm.status='ACTIVE' and denomination_key= '"+strkey[k]+"' and opening_number='"+strOpeningNumber[k]+"'"
						+ " and denomination_type_manual='"+strDenomValue[k]+"'";*/
					Query query = session.createSQLQuery(sql1);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					if(aliasToValueMapList.size()>0) {
						Map<String, Object> rs = aliasToValueMapList.get(0);
						JSONArray ja = new JSONArray();
						ja.add("");
						ja.add(rs.get("denomination_type_manual"));
						ja.add(rs.get("denomination_key"));
						ja.add(common.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
						ja.add(common.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
						ja.add(rs.get("number_of_books"));
						ja.add(rs.get("value"));
						ja.add(rs.get("priority"));
						ja.add("<a href='#' class='edittkt' id='floor_edit'_"+ rs.get("ticket_inventory_mst_id") + "' value='"+ rs.get("ticket_inventory_mst_id") + "'> Edit</a>");
						ja.add("");
						array.add(ja);
					}
				}
			}
	//		totalAfterFilter = getTotalEditRecords(str);
			result.put("aaData", array);
			result.put("iTotalRecords", 0);
			result.put("iTotalDisplayRecords", 0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			return result;
		}
	
	}
	

}
