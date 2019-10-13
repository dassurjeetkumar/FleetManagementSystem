package com.trimax.its.inventory.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;

public class VoucherDAO {

	private int subtotpassticket;
	private int subtotpassvalue;
	private int subtotpassbook;
	private int subtotmanualvalue;

	Common common = new Common();   
	@SuppressWarnings({ "unchecked", "finally" })
	public JSONObject getListVoucher(int total, HttpServletRequest request,String col, String dir,String date1,String date2) {
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		System.out.println("usrsessionid....."+usrsessionid);
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String viewdeletedrecord = (String) request.getSession().getAttribute("viewdeletedrecord");
			String sql = "";
			sql = " select ticket_invoice_mast_id as voucherid,voucher_number as vouchernum,tinvoice.created_date as voucherdate,"
				+ " current_status as status,org.org_name as ToOrg,org1.org_name as FromOrg "
				+ " from ticket_invoice_master tinvoice "
				+ " inner join org_chart org on org.org_chart_id=tinvoice.issue_to_unit_id "
				+ " inner join org_chart org1 on org1.org_chart_id=tinvoice.issue_from_unit_id " 
				+ " where tinvoice.current_status in ('In Transit','Issued') and tinvoice.status='ACTIVE' and " 
				+ " tinvoice.created_date between '" +date1+ " 00:00:00' and '"+date2+" 23:59:59'";
					//+" and tinvoice.created_by='"+usrsessionid+"' ";

			String search_term1 = request.getParameter("sSearch");
			
			if (search_term1 != null && !search_term1.equals("")) {
				
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (ticket_invoice_mast_id like '" + search_term+ "%'";
				sql += " OR voucher_number like '" + search_term + "%'";
				sql += " OR org.org_name like '" + search_term + "%'";
				sql += " OR org1.org_name like '" + search_term + "%'";
				sql += " OR tinvoice.created_date like '" + search_term + "%'";
				sql += " OR current_status like '" + search_term + "%')";
			}

			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}

			sql += " limit " + request.getParameter("iDisplayStart") + ", "+ request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql).addScalar("voucherid")
					.addScalar("vouchernum").addScalar("voucherdate")
					.addScalar("status").addScalar("FromOrg")
					.addScalar("ToOrg");
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				System.out.println("status" + rs.get("status"));
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+ rs.get("voucherid")+ "' value='"+ rs.get("voucherid") + "'/>");
				ja.add(rs.get("voucherid").toString());
				ja.add(rs.get("vouchernum").toString() != null ? rs.get("vouchernum").toString().replaceAll(" ", "&nbsp;") : "");
				ja.add(common.changeDataFormat(rs.get("voucherdate").toString(), "yyyy-mm-dd", "dd-mm-yyyy"));
				ja.add(rs.get("FromOrg").toString());
				ja.add(rs.get("ToOrg").toString());
				ja.add(rs.get("status").toString());
				array.add(ja);

			}
			int cnt = 0;
			cnt = getTotalRecords(request, col, dir, viewdeletedrecord,date1,date2);
			result.put("aaData", array);
			result.put("iTotalRecords", cnt);
			result.put("iTotalDisplayRecords", cnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
			}
			return result;
		}

	}
	public int getTotalRecords(HttpServletRequest request,String col,String dir,String viewdeletedrecord,String date1,String date2){
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		String sql="";
		try{
			sql=" select ticket_invoice_mast_id " +
				" from ticket_invoice_master tinvoice "+
				" inner join org_chart org on org.org_chart_id=tinvoice.issue_to_unit_id " +
				" inner join org_chart org1 on org1.org_chart_id=tinvoice.issue_from_unit_id "+
				" where tinvoice.current_status in ('In Transit','Issued') and tinvoice.status='ACTIVE' and " +
				" tinvoice.created_date between '" +date1+ " 00:00:00' and '"+date2+" 23:59:59'";
				
		
			String search_term1 = request.getParameter("sSearch");   
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (ticket_invoice_mast_id like '"+search_term+"%'";
				sql += " OR voucher_number like '"+search_term+"%'";
				sql += " OR org.org_name like '"+search_term+"%'";
				sql += " OR org1.org_name like '"+search_term+"%'";
				sql += " OR tinvoice.created_date like '"+search_term+"%'";
				sql += " OR current_status like '"+search_term+"%')";
			}
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

	@SuppressWarnings({ "unchecked", "finally" })
	public String getListVoucherPass(String voucherid) {

		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<table border='1' cellspacing='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;border-collapse: collapse;'>";
		regionTypeAjaxString += "<thead><th>Pass type</th>" +
				" <th>Denom</th>" +
				" <th>Denom key</th><th>Start number</th>" +
				" <th>End number</th><th>No.of passes</th>" +
				" <th>No.of books</th><th>value</th></thead>";
		subtotpassvalue = 0;
		subtotpassticket = 0;
		subtotpassbook = 0;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {

			String sql = " SELECT  tid.ticket_inventory_det_id as tinvetory_id,ifnull(de.denomination_type_manual,'') as denomination_type_manual,tim.denomination_key," +
					" min(tid.opening_number) as opening_number,max(tid.closing_number) as closing_number,sum(tid.number_of_tickets) number_of_tickets," +
					" sum(tid.number_of_passes) as number_of_passes,tim.priority,sum(tid.number_of_books) as number_of_books,tim.ticket_type_manual_id, " +
					" sum(tid.value) as value,tm.ticket_type_manual_id FROM ticket_inventory_details tid " +
					" inner join ticket_invoice_details ti on tid.ticket_inventory_det_id=ti.ticket_inventory_det_id " +
					" inner join denomination_type_manual de on tid.denomination_type_manual_id=de.denomination_type_manual_id " +
					" inner join ticket_type_manual tm on tm.ticket_type_manual_id=tid.ticket_type_manual_id " +
					" inner join ticket_inventory_master tim on tim.ticket_inventory_mst_id = tid.ticket_inventory_mst_id " +
					" where ti.ticket_invoice_mast_id='"+voucherid+"' and tid.status = 'ACTIVE' and tid.current_status in ('In Transit','Issued') " +
					" and tid.ticket_type_manual_id in (2,3) " +
					" group by tid.ticket_inventory_mst_id,tid.denomination_key  order by CAST(denomination_type_manual AS UNSIGNED)";
			System.out.println("sql.........."+sql);
			Query query = session.createSQLQuery(sql)
					.addScalar("denomination_type_manual").addScalar("ticket_type_manual_id")
					.addScalar("denomination_key").addScalar("opening_number").addScalar("closing_number")
					.addScalar("number_of_passes").addScalar("number_of_books").addScalar("value");

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				regionTypeAjaxString += "<tr>";
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				
				if(rs.get("ticket_type_manual_id").toString().equals("2")){
					regionTypeAjaxString += "<td style='text-align:center'>Daily</td>";
				}else if(rs.get("ticket_type_manual_id").toString().equals("3")){
					regionTypeAjaxString += "<td style='text-align:center'>Monthly</td>";
				}
				ja.add(rs.get("denomination_type_manual").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("denomination_type_manual").toString()+ "</td>";
				ja.add(rs.get("denomination_key").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("denomination_key").toString() + "</td>";
				ja.add(rs.get("opening_number").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ leftPadding(rs.get("opening_number").toString(),8, "0")+ "</td>";
				ja.add(rs.get("closing_number").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ leftPadding(rs.get("closing_number").toString(),8, "0") + "</td>";
				ja.add(rs.get("number_of_passes").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("number_of_passes").toString() + "</td>";
				subtotpassticket += Integer.parseInt(rs.get("number_of_passes").toString());
				ja.add(rs.get("number_of_books").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("number_of_books").toString() + "</td>";
				subtotpassbook += Integer.parseInt(rs.get("number_of_books").toString());
				ja.add(rs.get("value").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("value").toString() + "</td>";
				subtotpassvalue += Integer.parseInt(rs.get("value").toString());
				array.add(ja);
			}
			regionTypeAjaxString += "<tr><td colspan='5'>Sub total</td>" 
					+" <td style='text-align:center' id='subtotpassticket'>"+ subtotpassticket+ "</td>" 
					+" <td style='text-align:center' id='subtotpassbook'>"+ subtotpassbook	+ "</td>" 
					+" <td style='text-align:center' id='subtotpassvalue'>"+ subtotpassvalue + "</td>" 
					+" </tr>";

			result.put("aaData", array);
			regionTypeAjaxString += "</table></div></div>";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				// session.close();
			}
			return regionTypeAjaxString;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public String getListVoucherManual(String voucherid) {

		subtotmanualvalue = 0;
		int subtotmanualticket = 0;
		int subtotmanualbook = 0;
		String regionTypeAjaxString = "";
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {

			String sql = "";

			regionTypeAjaxString += "<table border='1' cellspacing='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;border-collapse: collapse;'>";
			regionTypeAjaxString += "<thead><th>Denom</th><th>Denom key</th>" +
					" <th>Start number</th>" +
					" <th>End number</th> " +
					" <th>No.of tickets</th>" +
					" <th>No.of books</th>" +
					" <th>value</th></thead>";

			sql = " SELECT  tid.ticket_inventory_det_id as tinvetory_id,ifnull(de.denomination_type_manual,'') as denomination_type_manual,tim.denomination_key," +
					" min(tid.opening_number) as opening_number,max(tid.closing_number) as closing_number,sum(tid.number_of_tickets) number_of_tickets," +
					" sum(tid.number_of_passes) as number_of_passes,tim.priority,sum(tid.number_of_books) as number_of_books, " +
					" sum(tid.value) as value,tm.ticket_type_manual_id FROM ticket_inventory_details tid " +
					" inner join ticket_invoice_details ti on tid.ticket_inventory_det_id=ti.ticket_inventory_det_id " +
					" inner join denomination_type_manual de on tid.denomination_type_manual_id=de.denomination_type_manual_id " +
					" inner join ticket_type_manual tm on tm.ticket_type_manual_id=tid.ticket_type_manual_id " +
					" inner join ticket_inventory_master tim on tim.ticket_inventory_mst_id = tid.ticket_inventory_mst_id " +
					" where ti.ticket_invoice_mast_id='"+voucherid+"' and tid.status = 'ACTIVE' and tid.current_status in ('In Transit','Issued')  " +
					" and tid.ticket_type_manual_id ='1' " +
					" group by tid.ticket_inventory_mst_id,tid.denomination_key  order by CAST(denomination_type_manual AS UNSIGNED)";


			Query query = session.createSQLQuery(sql).addScalar("tinvetory_id")
					.addScalar("denomination_type_manual")
					.addScalar("denomination_key").addScalar("opening_number").addScalar("closing_number")
					.addScalar("number_of_tickets").addScalar("number_of_books").addScalar("value");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				regionTypeAjaxString += "<tr>";
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				System.out.println("status22222" + rs.get("status"));
				ja.add(rs.get("denomination_type_manual").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("denomination_type_manual").toString()+ "</td>";
				ja.add(rs.get("denomination_key").toString());
				regionTypeAjaxString += "<td style='text-align:center'>" + rs.get("denomination_key").toString() + "</td>";
				ja.add(rs.get("opening_number").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+  leftPadding(rs.get("opening_number").toString(),8, "0")+ "</td>";
				ja.add(rs.get("closing_number").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+  leftPadding(rs.get("closing_number").toString(),8, "0") + "</td>";
				ja.add(rs.get("number_of_tickets").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("number_of_tickets").toString() + "</td>";
				subtotmanualticket += Integer.parseInt(rs.get("number_of_tickets").toString());
				ja.add(rs.get("number_of_books").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("number_of_books").toString() + "</td>";
				subtotmanualbook += Integer.parseInt(rs.get("number_of_books").toString());
				ja.add(rs.get("value").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("value").toString() + "</td>";
				subtotmanualvalue += Integer.parseInt(rs.get("value").toString());
				System.out.println(subtotmanualticket + "" + subtotmanualbook+ "" + subtotmanualvalue);
				array.add(ja);
				regionTypeAjaxString += "</tr>";
				System.out.println("regionTypeAjaxString............."+regionTypeAjaxString);
			}
			regionTypeAjaxString += "<tr><td colspan='4'>Sub total</td>" +
					" <td style='text-align:center' id='subtotmanualticket'>"+ subtotmanualticket+ "</td>" +
					" <td style='text-align:center' id='subtotmanualbook'>"+ subtotmanualbook+ "</td>" +
					" <td style='text-align:center' id='subtotmanualvalue'>"+ subtotmanualvalue+ "</td>" +
					" </tr>";
			result.put("aaData", array);
			regionTypeAjaxString += "</table></div></div>";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				// session.close();
			}
			return regionTypeAjaxString;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public String getListVoucherLuggage(String voucherid) {

		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<table border='1' cellspacing='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;border-collapse: collapse;'>";
		regionTypeAjaxString += "<thead><th>Ticket key</th>" +
				"<th>Start number</th><th>End number</th>" +
				"<th>No.of tickets</th>" +
				"<th colspan='2'>No.of books</th>" +
				"</thead>";

		int totalvalue = 0;
		int totalticket = 0;
		int totalbook = 0;

		int subtotluggagevalue = 0;
		int subtotluggageticket = 0;
		int subtotluggagebook = 0;
		int subtotmanualbook = 0;
		int subtotmanualticket = 0;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {

			String sql = " SELECT  tid.ticket_inventory_det_id as tinvetory_id,ifnull(de.denomination_type_manual,'') as denomination_type_manual,tim.denomination_key," +
					" min(tid.opening_number) as opening_number,max(tid.closing_number) as closing_number,sum(tid.number_of_tickets) number_of_tickets," +
					" sum(tid.number_of_passes) as number_of_passes,tim.priority,sum(tid.number_of_books) as number_of_books, " +
					" sum(tid.value) as value,tm.ticket_type_manual_id FROM ticket_inventory_details tid " +
					" inner join ticket_invoice_details ti on tid.ticket_inventory_det_id=ti.ticket_inventory_det_id " +
					" inner join denomination_type_manual de on tid.denomination_type_manual_id=de.denomination_type_manual_id " +
					" inner join ticket_type_manual tm on tm.ticket_type_manual_id=tid.ticket_type_manual_id " +
					" inner join ticket_inventory_master tim on tim.ticket_inventory_mst_id = tid.ticket_inventory_mst_id " +
					" where ti.ticket_invoice_mast_id='"+voucherid+"' and tid.status = 'ACTIVE' and tid.current_status in ('In Transit','Issued')  " +
					" and tid.ticket_type_manual_id ='4' " +
					" group by tid.ticket_inventory_mst_id,tid.denomination_key  order by CAST(tim.denomination_key AS UNSIGNED)";


			Query query = session.createSQLQuery(sql)
					.addScalar("denomination_key").addScalar("opening_number").addScalar("closing_number")
					.addScalar("number_of_passes").addScalar("number_of_books").addScalar("value");
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				regionTypeAjaxString += "<tr>";
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				
				ja.add(rs.get("denomination_key").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("denomination_key").toString() + "</td>";
				ja.add(rs.get("opening_number").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ leftPadding(rs.get("opening_number").toString(),8, "0") + "</td>";
				ja.add(rs.get("closing_number").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ leftPadding(rs.get("closing_number").toString(),8, "0") + "</td>";
				ja.add(rs.get("number_of_passes").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("number_of_passes").toString() + "</td>";
				subtotluggageticket += Integer.parseInt(rs.get("number_of_passes").toString());
				ja.add(rs.get("number_of_books").toString());
				subtotluggagebook += Integer.parseInt(rs.get("number_of_books")	.toString());
				regionTypeAjaxString += "<td colspan='2' style='text-align:center'>"+ rs.get("number_of_books").toString() + "</td>";
				ja.add(rs.get("value").toString());
				
				subtotluggagevalue += Integer.parseInt(rs.get("value").toString());
				array.add(ja);
				regionTypeAjaxString += "</tr>";
			}
			totalticket = subtotmanualticket + subtotpassticket+ subtotluggageticket;
			totalbook = subtotmanualbook + subtotpassbook + subtotluggagebook;
			totalvalue = subtotmanualvalue + subtotpassvalue;

			System.out.println(totalticket + "@" + totalbook + "@" + totalvalue);
			regionTypeAjaxString += "<tr><td colspan='3'>Sub total</td>" +
					"<td style='text-align:center' id='subtotluggageticket'>"+ subtotluggageticket+ "</td>" +
					"<td colspan='2' style='text-align:center' id='subtotluggagebook'>"+ subtotluggagebook + "</td>" +
					"</tr>";
			/*regionTypeAjaxString += "<tr><td colspan='6'><b>Total</b></td></tr>";
			regionTypeAjaxString += "<tr><td colspan='3'><b></b></td>" +
					"<td style='text-align:center'><b>Total No.Of Ticket</b></td>" +
					"<td style='text-align:center'><b>Total No. Of Books</b></td>." +
					"<td style='text-align:center'><b>Total Value</b></td>" +
					"</tr>";

			regionTypeAjaxString += "<tr><td colspan='3'></td>" +
					"<td style='text-align:center'>"+ totalticket+ "</td>" +
					"<td style='text-align:center'>"+ totalbook	+ "</td>" +
					"<td style='text-align:center'>"+ totalvalue + "</td>" +
					"</tr>";*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				// session.close();
			}
			return regionTypeAjaxString;
		}
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public String getListVoucherTsheet(String voucherid) {

		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<table border='1' cellspacing='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;border-collapse: collapse;'>";
		regionTypeAjaxString += "<thead><th>Waybill key</th>" +
				"<th>Start number</th><th>End number</th>" +
				"<th>No.of Trip Sheets</th>" +
				"</thead>";

		
		int totalticket = 0;
		

		
		int subtottsheet = 0;
		
		int subtotmanualticket = 0;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {

			String sql = " SELECT  tid.ticket_inventory_det_id as tinvetory_id,ifnull(de.denomination_type_manual,'') as denomination_type_manual,tim.denomination_key," +
					" min(tid.opening_number) as opening_number,max(tid.closing_number) as closing_number," +
					" sum(tid.number_of_passes) as number_of_passes, " +
					" tm.ticket_type_manual_id FROM ticket_inventory_details tid " +
					" inner join ticket_invoice_details ti on tid.ticket_inventory_det_id=ti.ticket_inventory_det_id " +
					" inner join denomination_type_manual de on tid.denomination_type_manual_id=de.denomination_type_manual_id " +
					" inner join ticket_type_manual tm on tm.ticket_type_manual_id=tid.ticket_type_manual_id " +
					" inner join ticket_inventory_master tim on tim.ticket_inventory_mst_id = tid.ticket_inventory_mst_id " +
					" where ti.ticket_invoice_mast_id='"+voucherid+"' and tid.status = 'ACTIVE' and tid.current_status in ('In Transit','Issued')  " +
					" and tid.ticket_type_manual_id ='5' " +
					" group by tid.ticket_inventory_mst_id,tid.denomination_key  order by CAST(tim.denomination_key AS UNSIGNED)";


			Query query = session.createSQLQuery(sql)
					.addScalar("denomination_key").addScalar("opening_number").addScalar("closing_number")
					.addScalar("number_of_passes");
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				regionTypeAjaxString += "<tr>";
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				
				ja.add(rs.get("denomination_key").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("denomination_key").toString() + "</td>";
				ja.add(rs.get("opening_number").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ leftPadding(rs.get("opening_number").toString(),8, "0") + "</td>";
				ja.add(rs.get("closing_number").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ leftPadding(rs.get("closing_number").toString(),8, "0") + "</td>";
				ja.add(rs.get("number_of_passes").toString());
				regionTypeAjaxString += "<td style='text-align:center'>"+ rs.get("number_of_passes").toString() + "</td>";
				subtottsheet += Integer.parseInt(rs.get("number_of_passes").toString());
				
				
				
				
				regionTypeAjaxString += "</tr>";
			}
			totalticket = subtotmanualticket + subtotpassticket+ subtottsheet;
			

			
			regionTypeAjaxString += "<tr><td colspan='3'>Sub total</td>" +
					"<td style='text-align:center' id='subtottsheet'>"+ subtottsheet+ "</td>" +
					
					"</tr>";
			/*regionTypeAjaxString += "<tr><td colspan='6'><b>Total</b></td></tr>";
			regionTypeAjaxString += "<tr><td colspan='3'><b></b></td>" +
					"<td style='text-align:center'><b>Total No.Of Ticket</b></td>" +
					"<td style='text-align:center'><b>Total No. Of Books</b></td>." +
					"<td style='text-align:center'><b>Total Value</b></td>" +
					"</tr>";

			regionTypeAjaxString += "<tr><td colspan='3'></td>" +
					"<td style='text-align:center'>"+ totalticket+ "</td>" +
					"<td style='text-align:center'>"+ totalbook	+ "</td>" +
					"<td style='text-align:center'>"+ totalvalue + "</td>" +
					"</tr>";*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				// session.close();
			}
			return regionTypeAjaxString;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<String> getOrgTypeIdSpecific() {
		
		Session session = null;
		List list = null;
		String status = "ACTIVE";
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
			String qry = "select org_type_id from org_type where status='"+ status+ "' and org_type!='NULL' and " +
					" org_type_id in (1,2,3,4,11) order by org_type limit 20";
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
				// session.flush();
				session.close();
			}
			return list;
		}
		
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<String> getOrgTypeNameSpecific() {
		Session session = null;
		List list = null;
		String status = "ACTIVE";
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
			String qry = "select org_type from org_type where status='"	+ status+ "' and org_type!='NULL'" +
					" and org_type_id in (1,2,3,4,11) order by org_type limit 20";
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
				// session.flush();
				session.close();
			}
			return list;
		}
	}
	
	public String leftPadding(String str1, int a1,String paddingWith) {
        StringBuilder sb = new StringBuilder(str1);
        StringBuilder sb2 = new StringBuilder();

        int m1 = str1.length();
        if (m1 >= a1) {
            return str1;
        }
        a1 = a1 - m1;
        for (int i = 0; i < a1; i++) {
            sb2.append(paddingWith);
        }
        sb2.append(sb);
        String sb1 = sb2.toString();
        return sb1;
    }
	
	public String getDateFromPicker(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/mm/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	//ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String setVoucherNumber(String voucherid){
		
		String voucherNumber = "";
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			voucherNumber = common.getDBResultStr(session, "SELECT `voucher_number` FROM `ticket_invoice_master` WHERE `ticket_invoice_mast_id` = '"+voucherid+"'", "voucher_number");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return voucherNumber;
		}
		
	}
	public String getVoucherHeaderDetails(String voucherId){
		String returnString = "";
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			String sql = "SELECT org_name FROM ticket_invoice_master " +
				" INNER JOIN org_chart ON issue_to_unit_id = org_chart_id WHERE ticket_invoice_mast_id = '"+voucherId+"' ";
			String depotName = common.getDBResultStr(session, sql, "org_name");
			returnString += "<b>Issued To : "+depotName+" </b>";
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return returnString;
		}
	}
}
