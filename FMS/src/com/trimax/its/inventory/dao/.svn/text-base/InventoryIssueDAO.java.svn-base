package com.trimax.its.inventory.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.doa.ticketing.model.TicketInventory;
import com.trimax.its.common.Common;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.vehicle.model.OrganisationChart;



public class InventoryIssueDAO {

	Common common = new Common();
	HttpServletRequest request = ServletActionContext.getRequest(); 
	Logger logger = TrimaxLogger.getTrimaxLogger();
	
	@SuppressWarnings({ "unchecked", "finally" })
	public JSONObject getIssuedData(HttpServletRequest request,String arr, String col, String dir) {
		JSONObject result = new JSONObject();
		Session sessiontottick = null;
		JSONArray arrayissutick = new JSONArray();
		int totalRecords = 0;
		try{
			if(arr.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length],
						strOpeningNumber = new String[s.length],strDenomValue = new String[s.length],strClosingNumber = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(!tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						strClosingNumber[count] = tokens[4];
						count++;
					}
				}
				sessiontottick = HibernateUtilTick.getSession("");
				for(int j=0;j<count ;j++){
					if( strdenomtype[j].equals("1")){
					/*String sql = "select a.ticket_inventory_mst_id,dy.denomination_type_manual, a.denomination_key, a.ticket_type_manual_id,"
						+ " a.opening_number, a.closing_number,  a.number_of_tickets, a.number_of_books,"
						+ " a.value,a.unit_type,a.unit_name,a.priority from ("
						+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
						+ " opening_number, closing_number,  number_of_tickets,number_of_books, value,unit_type,unit_name,ticket_type_manual_id,priority "
						+ " FROM ticket_inventory_details tid  WHERE tid.denomination_key ='"+ strkey[j]+ "' and ticket_type_manual_id ='1' and tid.partial_book = 'Y' "
						+ " and tid.status = 'ACTIVE' and tid.current_status='New'  union all "
						+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id,"
						+ " denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as "
						+ " number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,ticket_type_manual_id ,priority"
						+ " FROM ticket_inventory_details tid "
						+ " WHERE tid.denomination_key ='"+strkey[j]+"' and tid.partial_book = 'N' and tid.status = 'ACTIVE' and tid.current_status='New' "
						+ " group by tid.denomination_key, tid.denomination_type_manual_id ) a  inner join denomination_type_manual dy on "
						+ " dy.denomination_type_manual_id= a.denomination_type_manual_id "
						+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id "
						+ " and a.ticket_type_manual_id ='1'  order by a.denomination_key, a.number_of_tickets ";*/
						String sql = "select a.ticket_inventory_mst_id,dy.denomination_type_manual, a.denomination_key, a.ticket_type_manual_id,"
								+ " a.opening_number, a.closing_number,  a.number_of_tickets, a.number_of_books,"
								+ " a.value,a.unit_type,a.unit_name,a.priority from (SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key, " +
								" min(opening_number) as opening_number, max(closing_number) as closing_number, " +
								" sum(number_of_tickets) as  number_of_tickets,sum(number_of_books) as number_of_books, " +
								" sum(value) as value,unit_type,unit_name,ticket_type_manual_id ,priority  " +
								" FROM ticket_inventory_details tid  " +
								" WHERE tid.denomination_key ='"+strkey[j]+"'  and tid.status = 'ACTIVE' and tid.current_status='New' " +
								" and opening_number>='"+strOpeningNumber[j]+"' and closing_number<='"+strClosingNumber[j]+"'" +
								" group by tid.denomination_key,tid.ticket_inventory_mst_id,tid.denomination_type_manual_id ) a " +
								" inner join denomination_type_manual dy on  dy.denomination_type_manual_id= a.denomination_type_manual_id " +
								" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id  " +
								" and a.ticket_type_manual_id ='1' where  a.ticket_type_manual_id ='"+ strdenomtype[j] + "' and dy.denomination_type_manual='"+strDenomValue[j]+"' " +
								" order by a.denomination_key, a.number_of_tickets ";
					
						Query query = sessiontottick.createSQLQuery(sql);
						query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = query.list();
							if(aliasToValueMapList.size()>0) {
								Map<String, Object> rs = aliasToValueMapList.get(0);
								JSONArray jaissuetick = new JSONArray();
								jaissuetick.add(rs.get("denomination_type_manual"));
								jaissuetick.add(rs.get("denomination_key"));
								jaissuetick.add(common.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
								jaissuetick.add(common.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
								jaissuetick.add(rs.get("number_of_books"));
								jaissuetick.add("<input type='text' class='form-control' name='requiredBooks' id='requiredBooks"+j+"' onblur='validateREquiredAmount("+j+")'/>"
									+ "<input type='hidden' name='denomKey' id='denomKey"+j+"' value='"+rs.get("denomination_key")+"'/>"
									+ "<input type='hidden' name='ticketType' id='ticketType"+ j+"' value='"+rs.get("ticket_type_manual_id")+"'/>"
									+ "<input type='hidden' name='booksCount' id='booksCount"+ j+ "' value='"+ rs.get("number_of_books")+ "'/>" 
									+ "<input type='hidden' name='passCount' id='passCount"+ j+ "' value='0'/>" 
									+ "<input type='hidden' name='openingNumber' id='openingNumber"+ j+ "' value='"+rs.get("opening_number")+"'/>"
									+ "<input type='hidden' name='closingNumber' id='closingNumber"+ j+ "' value='"+rs.get("closing_number")+"'/>" 
									+ "<input type='hidden' name='denomValue' id='denomValue"+ j+ "' value='"+rs.get("denomination_type_manual")+"'/>");
								jaissuetick.add(rs.get("value"));
								jaissuetick.add(rs.get("priority"));
								arrayissutick.add(jaissuetick);
							}
						}
				
					}
				}
				result.put("aaData", arrayissutick);
				result.put("iTotalRecords", totalRecords);
				result.put("iTotalDisplayRecords", totalRecords);
				System.out.println("REsult ------>"+ request.getParameter("iDisplayStart"));
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessiontottick != null && sessiontottick.isOpen()) {
				sessiontottick.flush();
				sessiontottick.close();

			}
			return result;
		}

	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public JSONObject getIssuedPassData(HttpServletRequest request,	String arr, String col, String dir) {
		
		JSONObject result = new JSONObject();
		Session sessionissuepass = null;
		JSONArray arrayissuepass = new JSONArray();
		int totalRecords = 0;
		try {
			if(arr.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], 
						strOpeningNumber = new String[s.length],strDenomValue = new String[s.length],
						strClosingNumber = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(!tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						strClosingNumber[count]= tokens[4];
						count++;
					}
				}
				sessionissuepass = HibernateUtilTick.getSession("");
				for(int j=0;j<count ;j++){
					if(strdenomtype[j].equals("2") || strdenomtype[j].equals("3")){
						String sql = "select a.ticket_inventory_mst_id,dy.denomination_type_manual,ttm.ticket_type_manual_name, a.denomination_key, "
							+ " a.ticket_type_manual_id,a.opening_number, a.closing_number, a.pass_day, a.number_of_tickets, a.number_of_books, "
							+ " a.value,a.priority,a.unit_name,a.priority from ( "
							+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id,pass_day, denomination_key, min(opening_number) "
							+ " as opening_number, max(closing_number) as closing_number, sum(number_of_passes) as  number_of_tickets,sum(number_of_books) "
							+ " as number_of_books,sum(value) as value,unit_type,unit_name,ticket_type_manual_id,priority " 
							+ " FROM ticket_inventory_details tid  "
							+ " WHERE tid.denomination_key ='"+ strkey[j]+ "'  and tid.ticket_type_manual_id in ('2','3') " 
							+ " and opening_number>='"+strOpeningNumber[j]+"' and closing_number<='"+strClosingNumber[j]+"'"
							+ " and tid.status = 'ACTIVE' and tid.current_status='New'  "
							+ " group by tid.denomination_key,tid.ticket_inventory_mst_id, tid.denomination_type_manual_id ) a  " 
							+ " inner join denomination_type_manual dy on  dy.denomination_type_manual_id= a.denomination_type_manual_id  " 
							+ " inner join ticket_type_manual  ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id " 
							+ " where  a.ticket_type_manual_id ='"+ strdenomtype[j] + "' and dy.denomination_type_manual='"+strDenomValue[j]+"' " 
							+ " order by a.denomination_key, a.number_of_tickets ";
						Query query = sessionissuepass.createSQLQuery(sql);
						query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = query.list();
	
						if(aliasToValueMapList.size()>0) {
							Map<String, Object> rs = aliasToValueMapList.get(0);
							JSONArray jaissuepass = new JSONArray();
							@SuppressWarnings("unused")
							int totalamt = Integer.parseInt(rs.get("number_of_tickets").toString());
							jaissuepass.add(rs.get("ticket_type_manual_name"));
							jaissuepass.add(rs.get("denomination_type_manual"));
							jaissuepass.add(rs.get("denomination_key"));
							jaissuepass.add(rs.get("pass_day"));
							jaissuepass.add(common.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
							jaissuepass.add(common.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
							jaissuepass.add(rs.get("number_of_tickets"));
							if(strdenomtype[j].equals("2")){
								jaissuepass.add(rs.get("number_of_books"));
							}
							else{
								jaissuepass.add("");
							}
							jaissuepass.add("<input type='text' class='form-control' name='requiredBooks' id='requiredBooks"+j+"' onblur='validateREquiredPassAmount("+j+ ")'/>"
									+ "<input type='hidden' name='denomKey' id='denomKey"+j+"' value='"+rs.get("denomination_key")+"'/>"
									+ "<input type='hidden' name='ticketType' id='ticketType"+j+"' value='"+ rs.get("ticket_type_manual_id")+ "'/>"
									+ "<input type='hidden' name='booksCount' id='booksCount"+ j+ "' value='"+ rs.get("number_of_books")+ "'/>"
									+ "<input type='hidden' name='passCount' id='passCount"+ j+ "' value='"+ rs.get("number_of_tickets")+ "'/>" 
									+ "<input type='hidden' name='openingNumber' id='openingNumber"+ j+ "' value='"+rs.get("opening_number")+"'/>"
									+ "<input type='hidden' name='closingNumber' id='closingNumber"+ j+ "' value='"+rs.get("closing_number")+"'/>"
									+ "<input type='hidden' name='denomValue' id='denomValue"+ j+ "' value='"+rs.get("denomination_type_manual")+"'/>");
	
							jaissuepass.add(rs.get("value"));
							jaissuepass.add(rs.get("priority"));
							arrayissuepass.add(jaissuepass);
						}
					}
				}
				result.put("aaData", arrayissuepass);
				result.put("iTotalRecords", totalRecords);
				result.put("iTotalDisplayRecords", totalRecords);
				System.out.println("REsult ------>"+ request.getParameter("iDisplayStart"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessionissuepass != null && sessionissuepass.isOpen()) {
				sessionissuepass.close();
				sessionissuepass = null;
			}
			return result;
		}
	}
	
@SuppressWarnings({ "unchecked", "finally" })
public JSONObject getIssuedLuggageData(HttpServletRequest request, String arr, String col, String dir) {
		
		JSONObject result = new JSONObject();
		Session sessionluggissue = null;
		JSONArray arrayissuelugg = new JSONArray();
		try {
			if(arr.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length],
						strOpeningNumber = new String[s.length],strDenomValue = new String[s.length],
						strClosingNumber = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						strClosingNumber[count] = tokens[4];
						count++;
					}
				}
				sessionluggissue = HibernateUtilTick.getSession("");
				for(int j=0;j<count ;j++){
					if(strdenomtype[j].equals("4")){
						String sql = "select a.ticket_inventory_mst_id,a.denomination_key, a.ticket_type_manual_id,"
							+ " a.opening_number, a.closing_number,  a.number_of_tickets, a.number_of_books,"
							+ " a.value,a.unit_type,a.unit_name,a.priority from ("
							+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
							+ " min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_passes) as "
							+ " number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,priority,"
							+ " ticket_type_manual_id  FROM ticket_inventory_details tid  " 
							+ " WHERE tid.denomination_key ='"+ strkey[j]+ "' and  tid.status = 'ACTIVE' "
							+ " and opening_number>='"+strOpeningNumber[j]+"' and closing_number<='"+strClosingNumber[j]+"'"
							+ " and tid.current_status='New'  group by tid.denomination_key, tid.denomination_type_manual_id ) a "
							+ " inner join denomination_type_manual dy on  dy.denomination_type_manual_id= a.denomination_type_manual_id "
							+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id  " 
							+ " and a.ticket_type_manual_id ='4'order by a.denomination_key, a.number_of_tickets ";
						Query query = sessionluggissue.createSQLQuery(sql);
						query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = query.list();
						if(aliasToValueMapList.size()>0) {
							JSONArray jaissuelugg = new JSONArray();
							Map<String, Object> rs = aliasToValueMapList.get(0);
							int totalamt = Integer.parseInt(rs.get("number_of_tickets").toString());
							jaissuelugg.add(rs.get("denomination_key"));
							jaissuelugg.add(common.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
							jaissuelugg.add(common.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
							jaissuelugg.add(rs.get("number_of_tickets"));
							jaissuelugg.add(rs.get("number_of_books"));
							jaissuelugg.add("<input type='text' class='form-control' name='requiredBooks' id='requiredBooks"+j+"' onblur='validateREquiredPassAmount("+j+ ")'/>"
									+ "<input type='hidden' name='denomKey' id='denomKey"+j+"' value='"+rs.get("denomination_key")+"'/>"
									+ "<input type='hidden' name='ticketType' id='ticketType"+j+"' value='"+ rs.get("ticket_type_manual_id")+ "'/>"
									+ "<input type='hidden' name='booksCount' id='booksCount"+ j+ "' value='"+ rs.get("number_of_books")+ "'/>"
									+ "<input type='hidden' name='passCount' id='passCount"+ j+ "' value='0'/>" 
									+ "<input type='hidden' name='openingNumber' id='openingNumber"+ j+ "' value='"+rs.get("opening_number")+"'/>"
									+ "<input type='hidden' name='closingNumber' id='closingNumber"+ j+ "' value='"+rs.get("closing_number")+"'/>" 
									+ " <input type='hidden' name='denomValue' id='denomValue"+ j+ "' value='0'/>");
							jaissuelugg.add(rs.get("value"));
							jaissuelugg.add(rs.get("priority"));
							arrayissuelugg.add(jaissuelugg);
						}
					}
				}
			}
			result.put("aaData", arrayissuelugg);
			result.put("iTotalRecords", 0);
			result.put("iTotalDisplayRecords", 0);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally { 
			if (sessionluggissue != null && sessionluggissue.isOpen()) {
				sessionluggissue.close();
			}
			return result;
		}

	}
@SuppressWarnings({ "unchecked", "finally" })
public JSONObject getIssuedTsheetData(HttpServletRequest request, String arr, String col, String dir) {
		
		JSONObject result = new JSONObject();
		Session sessiontsheetissue = null;
		JSONArray arrayissuetsheet = new JSONArray();
		try {
			if(arr.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length],
						strOpeningNumber = new String[s.length],strDenomValue = new String[s.length],
						strClosingNumber = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						strClosingNumber[count] = tokens[4];
						count++;
					}
				}
				sessiontsheetissue = HibernateUtilTick.getSession("");
				for(int j=0;j<count ;j++){
					if(strdenomtype[j].equals("5")){
						String sql = "select a.ticket_inventory_mst_id,a.denomination_key, a.ticket_type_manual_id,"
							+ " a.opening_number, a.closing_number,  a.number_of_passes,"
							+ " a.unit_type,a.unit_name from ("
							+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
							+ " min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_passes) as "
							+ " number_of_passes,unit_type,unit_name,"
							+ " ticket_type_manual_id  FROM ticket_inventory_details tid  " 
							+ " WHERE tid.denomination_key ='"+ strkey[j]+ "' and  tid.status = 'ACTIVE' "
							+ " and opening_number>='"+strOpeningNumber[j]+"' and closing_number<='"+strClosingNumber[j]+"'"
							+ " and tid.current_status='New'  group by tid.denomination_key, tid.denomination_type_manual_id ) a "
							+ " inner join denomination_type_manual dy on  dy.denomination_type_manual_id= a.denomination_type_manual_id "
							+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id  " 
							+ " and a.ticket_type_manual_id ='5'order by a.denomination_key, a.number_of_passes ";
						Query query = sessiontsheetissue.createSQLQuery(sql);
						query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = query.list();
						if(aliasToValueMapList.size()>0) {
							JSONArray jaissuetsheet = new JSONArray();
							Map<String, Object> rs = aliasToValueMapList.get(0);
							
							jaissuetsheet.add(rs.get("denomination_key"));
							jaissuetsheet.add(common.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
							jaissuetsheet.add(common.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
							jaissuetsheet.add(rs.get("number_of_passes"));
							jaissuetsheet.add("<input type='text' class='form-control' name='requiredBooks' id='requiredBooks"+j+"' onblur='validateREquiredAmountTSheet("+j+ ")'/>"
									+ "<input type='hidden' name='denomKey' id='denomKey"+j+"' value='"+rs.get("denomination_key")+"'/>"
									+ "<input type='hidden' name='ticketType' id='ticketType"+j+"' value='"+ rs.get("ticket_type_manual_id")+ "'/>"
									+ "<input type='hidden' name='booksCount' id='booksCount"+ j+ "' value='"+ rs.get("number_of_passes")+ "'/>"
									+ "<input type='hidden' name='passCount' id='passCount"+ j+ "' value='0'/>" 
									+ "<input type='hidden' name='openingNumber' id='openingNumber"+ j+ "' value='"+rs.get("opening_number")+"'/>"
									+ "<input type='hidden' name='closingNumber' id='closingNumber"+ j+ "' value='"+rs.get("closing_number")+"'/>" 
									+ " <input type='hidden' name='denomValue' id='denomValue"+ j+ "' value='0'/>");
							
							arrayissuetsheet.add(jaissuetsheet);
						}
					}
				}
			}
			result.put("aaData", arrayissuetsheet);
			result.put("iTotalRecords", 0);
			result.put("iTotalDisplayRecords", 0);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally { 
			if (sessiontsheetissue != null && sessiontsheetissue.isOpen()) {
				sessiontsheetissue.close();
			}
			return result;
		}

	}
	@SuppressWarnings("finally")
	public boolean validateIssue(){

		boolean isSuccess = true;
		try{
			
			String ticketType[] = request.getParameterValues("ticketType");
			String booksCount[] = request.getParameterValues("booksCount");
			String passCount[] = request.getParameterValues("passCount");
			String requiredBooks[] = request.getParameterValues("requiredBooks");
			
			int count = ticketType.length;
			for(int i=0;i<count;i++){
				if(ticketType[i].equals("1")){
					if( requiredBooks[i]==null || Integer.parseInt(requiredBooks[i])>Integer.parseInt(booksCount[i]))
						isSuccess = false;
				}if(ticketType[i].equals("2")){
					if( requiredBooks[i]==null || Integer.parseInt(requiredBooks[i])>Integer.parseInt(booksCount[i]))
						isSuccess = false;
				}if(ticketType[i].equals("3")){
					if( requiredBooks[i]==null || Integer.parseInt(requiredBooks[i])>Integer.parseInt(passCount[i]))
						isSuccess = false;
				}if(ticketType[i].equals("4")){
					if(requiredBooks[i]==null || Integer.parseInt(requiredBooks[i])>Integer.parseInt(booksCount[i]))
						isSuccess = false;
				}
				if(ticketType[i].equals("5")){
					if(requiredBooks[i]==null || Integer.parseInt(requiredBooks[i])>Integer.parseInt(booksCount[i]))
						isSuccess = false;
				}
			}
			isSuccess = true;
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
			request.getSession().setAttribute("error", "Error In Issue");
		}finally{
			return isSuccess;
		}
	}
	@SuppressWarnings("finally")
	public boolean saveIssueStock(String unitType,String unitName){
       System.out.println("saveIssueStock()........................");
		Session session = null;
		boolean isSuccess = false;
		try{
			session = HibernateUtil.getSession("");
			session.beginTransaction();
			updateInvoiceDetails(session,geenrateInvoice(session,unitName));
			session.getTransaction().commit();
			isSuccess = true;
		}catch(Exception e){
			ServletActionContext.getRequest().getSession().setAttribute("error",e.getMessage());
			e.printStackTrace();
			logger.error(this.getClass(),e);
			session.getTransaction().rollback();
			isSuccess = false;
		}finally{   
			if(session!=null){
				session.close();
			}
			return isSuccess;
		}
	}
	
	@SuppressWarnings("finally")
	public int geenrateInvoice(Session session,String issueTo) throws Exception{
		System.out.println("geenrateInvoice()........................");
		int voucherId = 0;
		int totalStockValue = getTotalStockValue();
		String queryForVoucherNo = "select lpad(ifnull(count(ticket_invoice_mast_id),0)+1,10,0) voucher_number  from ticket_invoice_master where voucher_type='I'";
		logger.debug(queryForVoucherNo);
		String voucherNumber = common.getDBResultStr(session, queryForVoucherNo, "voucher_number");
			
		String issueToOrgTypeIdQuery = "SELECT `org_type_id` FROM `org_chart` WHERE `org_chart_id` = '"+issueTo+"' ";
		logger.debug(issueToOrgTypeIdQuery);
		String issueToOrgTypeId= common.getDBResultStr(session, issueToOrgTypeIdQuery, "org_type_id");
		
		
		String issueFrom = (String) request.getSession().getAttribute("orgtypeid");
		String issueFromOrgTypeId = (String) request.getSession().getAttribute("orgtypeid");
		int userId =  (Integer) request.getSession().getAttribute("userid"); 
		
		request.getSession().setAttribute("voucherNumber", voucherNumber);
		String insertQueryForTcktInvoiceMasterTable = "INSERT INTO `ticket_invoice_master` (`voucher_number`, `issue_to_unit`, `issue_to_unit_id`, `issue_from_unit`," +
				"`issue_from_unit_id`,`created_by`, `current_status`, `status`, `created_date`,`updated_by`,`stock_value` ) " +
				" VALUES ('"+voucherNumber+"','"+issueToOrgTypeId+"', '"+issueTo+"', '"+issueFromOrgTypeId+"', '"+issueFrom+"', '"+userId+"'," +
				" 'In Transit', 'ACTIVE', now(),'0', '"+totalStockValue+"');";
		Query qryObject = session.createSQLQuery(insertQueryForTcktInvoiceMasterTable);
		logger.debug(insertQueryForTcktInvoiceMasterTable);
		qryObject.executeUpdate();
	
		voucherId = common.getDBResultInt(session, "select last_insert_id() as id from ticket_invoice_master", "id");
		
		String queryForInsertLog = "INSERT INTO `ticket_inventory_logs` (`ticket_invoice_mast_id`, `invoice_status`,  " +
				" `transaction_on_type`, `action_unit`, `action_by`, `created_date`, `created_by`) " +
				" VALUES ('"+voucherId+"', 'In Transit', 'Issued', '"+issueFrom+"', '"+userId+"', now(), '"+userId+"');";
		logger.debug(queryForInsertLog);
		session.createSQLQuery(queryForInsertLog).executeUpdate();
		
		return voucherId;
	}
	
	public void updateInvoiceDetails(Session session,int voucherId) throws Exception{
		
		String ticketType[] = request.getParameterValues("ticketType");
		int count = ticketType.length;
		for(int i=0;i<count;i++){
			updateTicketDetails(session,i,voucherId);
		}
	}
	public boolean updateTicketDetails(Session session,int i,int voucherId) throws Exception{
		boolean isSuccess = false;
		System.out.println("updateTicketDetails()........................");
		String denomKey[] = request.getParameterValues("denomKey");
		String ticketType[] = request.getParameterValues("ticketType");
		String requiredBooks[] = request.getParameterValues("requiredBooks");
		String openingNumber[] = request.getParameterValues("openingNumber");
		String closingNumber[] = request.getParameterValues("closingNumber");
		String denomValue[] = request.getParameterValues("denomValue");
		
		Integer userId =  (Integer) request.getSession().getAttribute("userid"); 
		
		String denominationTypeCondition = " ";
		String joinConditionForNonLuggage = "";
		if(!ticketType[i].equals("5")){
			
		if(!ticketType[i].equals("4")){
			denominationTypeCondition = " and dtm.denomination_type_manual ='"+denomValue[i]+"' ";
			joinConditionForNonLuggage = " inner join denomination_type_manual dtm on dtm.denomination_type_manual_id = tid.denomination_type_manual_id ";
		}
		
		
		/*String queryForMasterId = "SELECT ticket_inventory_mst_id FROM `ticket_inventory_details` tid" +
				" WHERE `denomination_key` ='"+denomKey[i]+"' AND tid.ticket_type_manual_id= '"+ticketType[i]+"' and " +
				" opening_number ='"+openingNumber[i]+"'  " +
				" group by ticket_inventory_mst_id";
		logger.debug(queryForMasterId);
		int masterId = common.getDBResultInt(session, queryForMasterId, "ticket_inventory_mst_id");*/
	
		String queryForInventorydetailsId = "SELECT  ticket_inventory_mst_id,ticket_inventory_det_id,opening_number,closing_number,unit_type,unit_name  FROM `ticket_inventory_details` tid " +joinConditionForNonLuggage+  
				" WHERE `denomination_key` ='"+denomKey[i]+"' AND tid.ticket_type_manual_id = '"+ticketType[i]+"' and tid.status='ACTIVE'  AND tid.current_status='New' and " +
				" opening_number >='"+openingNumber[i]+"' and closing_number<='"+closingNumber[i]+"' "+denominationTypeCondition+" limit "+requiredBooks[i]+" ";
		Query query = session.createSQLQuery(queryForInventorydetailsId);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		logger.debug(queryForInventorydetailsId);
		List<Map<String,Integer>> listOfDetailsId = query.list();
		System.out.println("updateTicketDetails()........................"+listOfDetailsId.size());
		if(listOfDetailsId.size()>0){
			System.out.println("updateTicketDetails()........................"+listOfDetailsId.size());
			for(int j=0;j<listOfDetailsId.size();j++){
				String queryToInsertInvoiceDetails = "INSERT INTO `ticket_invoice_details` (`ticket_invoice_mast_id`," +
						" `ticket_inventory_mst_id`, `ticket_inventory_det_id`, `created_by`, `created_date`) " +
						" VALUES ('"+voucherId+"', '"+(listOfDetailsId.get(j)).get("ticket_inventory_mst_id")+"', '"+(listOfDetailsId.get(j)).get("ticket_inventory_det_id")+"', '"+userId+"', now());";
				Query queryObjectForInsertInvoiceDetails = session.createSQLQuery(queryToInsertInvoiceDetails);
				logger.debug(queryToInsertInvoiceDetails);
				queryObjectForInsertInvoiceDetails.executeUpdate();
				
				String updateQueryForInvDetais= "update  ticket_inventory_details  set  current_status='In Transit',updated_by='"+userId+"'," +
						" updated_date=now() where ticket_inventory_det_id = '"+(listOfDetailsId.get(j)).get("ticket_inventory_det_id")+"'"; 
				Query queryObjectForUpdateInventoryDetails = session.createSQLQuery(updateQueryForInvDetais);
				logger.debug(updateQueryForInvDetais);
				queryObjectForUpdateInventoryDetails.executeUpdate();
			}
			}
		}
			else{
				
				if(!ticketType[i].equals("5")){
					denominationTypeCondition = " and dtm.denomination_type_manual ='"+denomValue[i]+"' ";
					joinConditionForNonLuggage = " inner join denomination_type_manual dtm on dtm.denomination_type_manual_id = tid.denomination_type_manual_id ";
				}
				String queryForInventorydetailsId1 = "SELECT  ticket_inventory_mst_id,ticket_inventory_det_id,opening_number,closing_number,unit_type,unit_name  FROM `ticket_inventory_details` tid " +joinConditionForNonLuggage+  
						" WHERE `denomination_key` ='"+denomKey[i]+"' AND tid.ticket_type_manual_id = '"+ticketType[i]+"' and tid.status='ACTIVE'  AND tid.current_status='New' and " +
						" opening_number >='"+openingNumber[i]+"' and closing_number<='"+closingNumber[i]+"' "+denominationTypeCondition+" limit "+requiredBooks[i]+" ";
				Query query1 = session.createSQLQuery(queryForInventorydetailsId1);
				query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				logger.debug(queryForInventorydetailsId1);
				List<Map<String,Integer>> listOfDetailsId1 = query1.list();
				System.out.println("updateTicketDetails()........................"+listOfDetailsId1.size());
				if(listOfDetailsId1.size()>0){
					System.out.println("updateTicketDetails()........................"+listOfDetailsId1.size());
					for(int j=0;j<listOfDetailsId1.size();j++){
						String queryToInsertInvoiceDetails = "INSERT INTO `ticket_invoice_details` (`ticket_invoice_mast_id`," +
								" `ticket_inventory_mst_id`, `ticket_inventory_det_id`, `created_by`, `created_date`) " +
								" VALUES ('"+voucherId+"', '"+(listOfDetailsId1.get(j)).get("ticket_inventory_mst_id")+"', '"+(listOfDetailsId1.get(j)).get("ticket_inventory_det_id")+"', '"+userId+"', now());";
						Query queryObjectForInsertInvoiceDetails = session.createSQLQuery(queryToInsertInvoiceDetails);
						logger.debug(queryToInsertInvoiceDetails);
						queryObjectForInsertInvoiceDetails.executeUpdate();
					System.out.println("updateTicketDetails()........................"+Integer.parseInt(requiredBooks[i]));
					int issuedtsheets=Integer.parseInt(requiredBooks[i]);
					int clnumber=(listOfDetailsId1.get(j)).get("opening_number")+issuedtsheets-1;
					String updateQueryForInvDetais= "update  ticket_inventory_details  set  current_status='In Transit',updated_by='"+userId+"'," +
							"closing_number='"+clnumber+"',number_of_passes='"+issuedtsheets+"', updated_date=now() where ticket_inventory_det_id = '"+(listOfDetailsId1.get(j)).get("ticket_inventory_det_id")+"'"; 
					Query queryObjectForUpdateInventoryDetails = session.createSQLQuery(updateQueryForInvDetais);
					logger.debug(updateQueryForInvDetais);
					queryObjectForUpdateInventoryDetails.executeUpdate();
					
					TicketInventory tickinv=null;
					String denomno=denomKey[i];
					int opnumber=(listOfDetailsId1.get(j)).get("opening_number")+issuedtsheets;
					int clonumber=(listOfDetailsId1.get(j)).get("closing_number");
					int noofpasses=clonumber-opnumber+1;
					tickinv= new TicketInventory();
					tickinv.setTicket_type_manual_id(5);
					tickinv.setKey_number(denomno);
					
					tickinv.setStartno(opnumber);
					tickinv.setEndno(clonumber);
					tickinv.setPartialbook("N");
					tickinv.setNoofpasses(noofpasses);
					tickinv.setDenoimination_type("36");
					tickinv.setValue(0);
					tickinv.setTicketinventorymasterid((listOfDetailsId1.get(j)).get("ticket_inventory_mst_id"));
					tickinv.setUnittype((listOfDetailsId1.get(j)).get("unit_type"));
					tickinv.setUnitname((listOfDetailsId1.get(j)).get("unit_name"));
					tickinv.setCreated_by(userId);
					tickinv.setCreated_date(new Date());
					tickinv.setCurrentstatus("New");
					tickinv.setStatus("ACTIVE");
					
					int savedId  = (Integer) session.save(tickinv);
					
					
					}
			}
		}
		
		/*String queryToUpdateInventoryRecordsStatus = "select ticket_inventory_det_id from ticket_inventory_details  tid " +
				 joinConditionForNonLuggage +
				" where opening_number >= '"+openingNumber[i]+"' and closing_number<='"+closingNumber[i]+"'  " 
				+ denominationTypeCondition +
				" and denomination_key ='SAA'  " +
				" and dtm.denomination_type_manual ='1025'  and current_status='New'   " +
				" and dtm.status = 'ACTIVE'  and tid.status='ACTIVE' order by ticket_inventory_det_id  limit '"+requiredBooks[i]+"' " ;
		query = session.createSQLQuery(queryToUpdateInventoryRecordsStatus);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Integer>> listOfDetailsId = query.list();
				
		Query queryObject = session.createSQLQuery(queryToUpdateInventoryRecordsStatus);
		queryObject.executeUpdate();*/
		
		isSuccess = true;
			
		return isSuccess;
	}
	
	public int getTotalStockValue(){
		
		int totalStockValue = 0;
		String ticketType[] = request.getParameterValues("ticketType");
		String openingNumber[] = request.getParameterValues("openingNumber");
		String closingNumber[] = request.getParameterValues("closingNumber");
		
		int count = ticketType.length;
		for(int i=0;i<count;i++){
			
			if(ticketType[i].equals("1")){
				totalStockValue +=(Integer.parseInt(closingNumber[i])-Integer.parseInt(openingNumber[i])+1);
			}if(ticketType[i].equals("2")){
				totalStockValue +=(Integer.parseInt(closingNumber[i])-Integer.parseInt(openingNumber[i])+1);
			}if(ticketType[i].equals("3")){
				totalStockValue +=(Integer.parseInt(closingNumber[i])-Integer.parseInt(openingNumber[i])+1);
			}if(ticketType[i].equals("4")){
					//return isSuccess;
			}
		}
		return totalStockValue;
	}
	
	  //Division name
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
		
		
		public String getOrgName(){
			String orgName = "";
			Session session = null;
			try{
				String orgId = (String) ServletActionContext.getRequest().getSession().getAttribute("orgchartid");
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				String queryForOrgName = "select org_name from org_chart where org_chart_id = '"+orgId+"'";
				orgName = common.getDBResultStr(session, queryForOrgName, "org_name");
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return orgName;
		}
		
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
		
}
