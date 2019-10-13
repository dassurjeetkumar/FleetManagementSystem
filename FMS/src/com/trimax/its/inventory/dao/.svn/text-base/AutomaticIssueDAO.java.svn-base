package com.trimax.its.inventory.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ActionError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.inventory.model.AutomaticIssueModel;
import com.trimax.its.inventory.model.StockViewModel;
import com.trimax.its.util.HibernateUtil;

public class AutomaticIssueDAO {
	Common common = new Common();

	@SuppressWarnings("finally")
	public List<List<String>> getDenomListOfNormalType(){
		List<List<String>> listOfDenomTypes = null;
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		try{
			session = HibernateUtil.getSession("");
			/*String queryForDenomListOfPassenegerType = "SELECT `denomination_type_manual_id`, `denomination_type_manual` " +
					"FROM `denomination_type_manual` WHERE `ticket_type_manual_id` = '1'  AND deleted_status='0' AND status='ACTIVE' ORDER BY CAST(`denomination_type_manual` AS UNSIGNED)   ";
			*/
			String queryForDenomListOfPassenegerType = "select distinct a.denomination_type_manual_id, dy.denomination_type_manual from  " +
					" (SELECT tid.denomination_type_manual_id,  tid.ticket_type_manual_id FROM ticket_inventory_details tid " +
					" inner join ticket_inventory_master tim on tim.ticket_inventory_mst_id = tid.ticket_inventory_mst_id " +
					" WHERE tid.status = 'ACTIVE' and tid.current_status='NEW' group by tid.denomination_key, tid.denomination_type_manual_id ) a " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id " +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id in (1) " +
					" where ttm.deleted_status='0' AND ttm.status='ACTIVE'  AND dy.status='ACTIVE' AND dy.deleted_status='0' " +
					" order by CAST(dy.denomination_type_manual AS UNSIGNED) asc";
			Query queryObject = session.createSQLQuery(queryForDenomListOfPassenegerType);
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			if(aliasToValueMapList.size()>0){
				listOfDenomTypes = new ArrayList<List<String>>();
				List<String> list1 = new ArrayList<String>();
				list1.add("Select");
				list1.add("0");
				listOfDenomTypes.add(list1);
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String,String> resultSet = aliasToValueMapList.get(i);
					List<String> list = new ArrayList<String>();
					
					list.add(String.valueOf(resultSet.get("denomination_type_manual")));
					list.add(String.valueOf(resultSet.get("denomination_type_manual_id")));
					listOfDenomTypes.add(list);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
			return listOfDenomTypes;
		}
	}
	
	@SuppressWarnings("finally")
	public List<List<String>> getDenomListOfPassType(){
		List<List<String>> listOfDenomTypes = null;
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		try{
			session = HibernateUtil.getSession("");
			/*String queryForDenomListOfPassenegerType = "SELECT `denomination_type_manual_id`, `denomination_type_manual` " +
					"FROM `denomination_type_manual` WHERE `ticket_type_manual_id` in ('2','3') AND deleted_status='0' AND status='ACTIVE'   ORDER BY CAST(`denomination_type_manual` AS UNSIGNED)   ";
			*/
			
			String queryForDenomListOfPassenegerType = "select distinct a.denomination_type_manual_id,dy.denomination_type_manual"
					+" from (SELECT tid.denomination_type_manual_id,tid.ticket_type_manual_id FROM ticket_inventory_details tid " 
					+" inner join ticket_inventory_master tim on tim.ticket_inventory_mst_id = tid.ticket_inventory_mst_id "
					+" WHERE tid.status = 'ACTIVE' and tid.current_status='New'"
					+" group by tid.denomination_key, tid.denomination_type_manual_id ) a"  
					+" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id "// and dy.denomination_type_manual='"+denom+"'" 
					+" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id in ('2','3')"
					+" where ttm.deleted_status	='0' AND ttm.status='ACTIVE'  AND dy.status='ACTIVE' AND dy.deleted_status='0' " 
					+" order by a.ticket_type_manual_id,CAST(dy.denomination_type_manual AS UNSIGNED) asc";
			Query queryObject = session.createSQLQuery(queryForDenomListOfPassenegerType);
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			if(aliasToValueMapList.size()>0){
				listOfDenomTypes = new ArrayList<List<String>>();
				List<String> list1 = new ArrayList<String>();
				list1.add("Select");
				list1.add("0");
				listOfDenomTypes.add(list1);
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String,String> resultSet = aliasToValueMapList.get(i);
					List<String> list = new ArrayList<String>();
					
					list.add(String.valueOf(resultSet.get("denomination_type_manual")));
					list.add(String.valueOf(resultSet.get("denomination_type_manual_id")));
					listOfDenomTypes.add(list);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
			return listOfDenomTypes;
		}
	}
	
	@SuppressWarnings("finally")
	public List<AutomaticIssueModel> getPassengerTypeTickets(String orgId,int denomType){
		List<AutomaticIssueModel> listOfPassengerTypeTickets = null;
		List<Map<String,String>> aliasToValueMapList = null;
		Session session = null;
		int reqNoOfBooks = 0;
		try{
			session = HibernateUtil.getSession("");
			reqNoOfBooks = Integer.parseInt(ServletActionContext.getRequest().getParameter("count"));
			
			String queryForPassengerTickets = " select min(opening_number)opening_number,max(closing_number)closing_number,denomination_key," +
					" sum(number_of_books) as number_of_books,denomination_type_manual,ticket_type_manual_name," +
					" sum(number_of_tickets) number_of_tickets,sum(value)value,a.created_date,a.priority  from " +
					" (select opening_number,closing_number,denomination_key,number_of_books,dy.denomination_type_manual," +
					" ttm.ticket_type_manual_name,number_of_tickets,value,tid.created_date,tid.priority " +
					" from  ticket_inventory_details tid " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= tid.denomination_type_manual_id " +
					" inner join ticket_type_manual ttm on tid.ticket_type_manual_id=ttm.ticket_type_manual_id " +
					" where tid.denomination_type_manual_id='"+denomType+"' and tid.ticket_type_manual_id='1' " +
					" and unit_name='"+orgId+"' and tid.status = 'ACTIVE' and tid.current_status='New' " +
					" order by tid.created_date,tid.priority   limit "+reqNoOfBooks+")a  group by denomination_key order by a.created_date,a.priority;";

			Query queryObject = session.createSQLQuery(queryForPassengerTickets);
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			if(aliasToValueMapList.size()>0){
				listOfPassengerTypeTickets = new ArrayList<AutomaticIssueModel>();
				for(int i=0;i<aliasToValueMapList.size();i++){
					AutomaticIssueModel modelObject = new AutomaticIssueModel();
					
					Map<String,String> resultSet = aliasToValueMapList.get(i);
					
					String keyNumber = resultSet.get("denomination_key");
					String denominationValue =  resultSet.get("denomination_type_manual");
					String passType =  resultSet.get("ticket_type_manual_name");
					String openingNumber = leftPadding(String.valueOf(resultSet.get("opening_number")),8,"0");
					String closingNumber =  leftPadding(String.valueOf(resultSet.get("closing_number")),8,"0");
					String noOfTickets =  String.valueOf(resultSet.get("number_of_tickets"));
					String noOfBooks =  String.valueOf(resultSet.get("number_of_books"));
					String value =  String.valueOf(resultSet.get("value"));
					
					modelObject.setDenominationValue(denominationValue);
					modelObject.setKeyNumber(keyNumber);
					modelObject.setPassType(passType);
					modelObject.setOpeningNumber(openingNumber);
					modelObject.setClosingNumber(closingNumber);
					modelObject.setNoOfTickets(noOfTickets);
					modelObject.setNoOfBooks(noOfBooks);
					modelObject.setValue(value);
					
					listOfPassengerTypeTickets.add(modelObject);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		return listOfPassengerTypeTickets;
		}
	}
	
	@SuppressWarnings("finally")
	public List<AutomaticIssueModel> getLuggageTypeTickets(String orgId){
		List<AutomaticIssueModel> listOfLuggageTypeTickets = null;
		List<Map<String,String>> aliasToValueMapList = null;
		Session session = null;
		int reqNoOfBooks = 0;
		try{
			reqNoOfBooks = Integer.parseInt(ServletActionContext.getRequest().getParameter("count"));
			session = HibernateUtil.getSession("");
			String queryForLugaugeTickets = "select min(opening_number)opening_number,max(closing_number)closing_number,denomination_key," +
					" sum(number_of_books) as number_of_books,denomination_type_manual,ticket_type_manual_name," +
					" sum(number_of_tickets) number_of_tickets,sum(value)value, a.created_date,a.priority from " +
					" (select opening_number,closing_number,denomination_key,number_of_books,dy.denomination_type_manual," +
					" ttm.ticket_type_manual_name,number_of_tickets,value,tid.created_date,tid.priority " +
					" from  ticket_inventory_details tid " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= tid.denomination_type_manual_id " +
					" inner join ticket_type_manual ttm on tid.ticket_type_manual_id=ttm.ticket_type_manual_id " +
					" where  tid.ticket_type_manual_id='4' " +
					" and unit_name='"+orgId+"' and tid.status = 'ACTIVE' and tid.current_status='New' " +
					" order by tid.created_date,tid.priority limit "+reqNoOfBooks+") a  group by denomination_key order by a.created_date,a.priority ";

			Query queryObject = session.createSQLQuery(queryForLugaugeTickets);
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			if(aliasToValueMapList.size()>0){
				listOfLuggageTypeTickets = new ArrayList<AutomaticIssueModel>();
				for(int i=0;i<aliasToValueMapList.size();i++){
					AutomaticIssueModel modelObject = new AutomaticIssueModel();
					
					Map<String,String> resultSet = aliasToValueMapList.get(i);
					
					String keyNumber = resultSet.get("denomination_key");
					String denominationValue =  resultSet.get("denomination_type_manual");
					String passType =  resultSet.get("ticket_type_manual_name");
					String openingNumber = leftPadding(String.valueOf(resultSet.get("opening_number")),8,"0");
					String closingNumber =  leftPadding(String.valueOf(resultSet.get("closing_number")),8,"0");
					String noOfTickets =  String.valueOf(resultSet.get("number_of_tickets"));
					String noOfBooks =  String.valueOf(resultSet.get("number_of_books"));
					String value =  String.valueOf(resultSet.get("value"));
					
					modelObject.setDenominationValue(denominationValue);
					modelObject.setKeyNumber(keyNumber);
					modelObject.setPassType(passType);
					modelObject.setOpeningNumber(openingNumber);
					modelObject.setClosingNumber(closingNumber);
					modelObject.setNoOfTickets(noOfTickets);
					modelObject.setNoOfBooks(noOfBooks);
					modelObject.setValue(value);
					
					listOfLuggageTypeTickets.add(modelObject);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		return listOfLuggageTypeTickets;
		}
	}
	@SuppressWarnings("finally")
	public String getPassDays(String denomId){
		Session session= null;
		String returnString = "";
		try{
			session = HibernateUtil.getSession("");
			String queryToGetPassDays = "select pass_day  from  ticket_inventory_details tid" +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= tid.denomination_type_manual_id  " +
					" inner join ticket_type_manual ttm on tid.ticket_type_manual_id=ttm.ticket_type_manual_id " +
					" where tid.denomination_type_manual_id='"+denomId+"' and tid.ticket_type_manual_id in(2) " +
					" and tid.status = 'ACTIVE' and tid.current_status='New' GROUP BY pass_day";
			Query queryObject = session.createSQLQuery(queryToGetPassDays).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List list = queryObject.list();
			for(int i=0;i<list.size();i++){
				Map<String,String> rs = (Map<String, String>) list.get(i);
				returnString += "<option value='"+rs.get("pass_day")+"'>"+rs.get("pass_day")+"</option> ";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return returnString;
		}
	}
	@SuppressWarnings("finally")
	public List<AutomaticIssueModel> getPassTypeTickets(String orgId,int denomType,String passDay){
		List<AutomaticIssueModel> listOfPassTypeTickets = null;
		List<Map<String,String>> aliasToValueMapList = null;
		Session session = null;
		int reqNoOfBooks = 0;
		try{
			session = HibernateUtil.getSession("");
			String passDayCondition = "";
			if(passDay!=""){
				passDayCondition = "AND tid.pass_day='"+passDay+"'";
			}
			reqNoOfBooks = Integer.parseInt(ServletActionContext.getRequest().getParameter("count"));
			String queryForPassTypeTickets = "select min(opening_number)opening_number,max(closing_number)closing_number,denomination_key," +
					" sum(number_of_books) as number_of_books,denomination_type_manual,ticket_type_manual_name," +
					" sum(number_of_passes) number_of_tickets,sum(value)value,a.ticket_type_manual_id,a.pass_day,a.created_date,a.priority from " +
					" (select opening_number,closing_number,denomination_key,number_of_books,dy.denomination_type_manual," +
					" ttm.ticket_type_manual_name,ttm.ticket_type_manual_id,number_of_passes,value,pass_day,tid.created_date,tid.priority " +
					" from  ticket_inventory_details tid " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= tid.denomination_type_manual_id " +
					" inner join ticket_type_manual ttm on tid.ticket_type_manual_id=ttm.ticket_type_manual_id " +
					" where tid.denomination_type_manual_id='"+denomType+"' "+passDayCondition+" and tid.ticket_type_manual_id in(2,3) " +
					" and unit_name='"+orgId+"' and tid.status = 'ACTIVE' and tid.current_status='New' " +
					" order by tid.created_date,tid.priority limit "+reqNoOfBooks+") a  group by denomination_key order by a.created_date,a.priority "; 
			
			Query queryObject = session.createSQLQuery(queryForPassTypeTickets);
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			if(aliasToValueMapList.size()>0){
				listOfPassTypeTickets = new ArrayList<AutomaticIssueModel>();
				for(int i=0;i<aliasToValueMapList.size();i++){
					AutomaticIssueModel modelObject = new AutomaticIssueModel();
					
					Map<String,String> resultSet = aliasToValueMapList.get(i);
					
					String keyNumber = resultSet.get("denomination_key");
					String denominationValue =  resultSet.get("denomination_type_manual");
					String passType =  resultSet.get("ticket_type_manual_name");
					String passday = resultSet.get("pass_day");
					String openingNumber = leftPadding(String.valueOf(resultSet.get("opening_number")),8,"0");
					String ticketType = String.valueOf(resultSet.get("ticket_type_manual_id"));
					String closingNumber =  leftPadding(String.valueOf(resultSet.get("closing_number")),8,"0");
					String noOfPasses =  String.valueOf(resultSet.get("number_of_tickets"));
					String noOfBooks =  String.valueOf(resultSet.get("number_of_books"));
					String value =  String.valueOf(resultSet.get("value"));
					
					modelObject.setDenominationValue(denominationValue);
					modelObject.setKeyNumber(keyNumber);
					modelObject.setPassDay(passday);
					modelObject.setPassType(passType);
					modelObject.setTicketType(ticketType);
					modelObject.setOpeningNumber(openingNumber);
					modelObject.setClosingNumber(closingNumber);
					modelObject.setNoOfPasses(noOfPasses);
					modelObject.setNoOfBooks(noOfBooks);
					modelObject.setValue(value);
					
					listOfPassTypeTickets.add(modelObject);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		return listOfPassTypeTickets;
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
	
	public int getTotalValue(List<AutomaticIssueModel> listOfObjects){
		int totalValue = 0;
		if(listOfObjects != null && listOfObjects.size()>0){
			for(int i=0;i<listOfObjects.size();i++){
				totalValue += Integer.parseInt(listOfObjects.get(i).getValue());
			}
		}
		return totalValue;
	}
	public int getTotalNoOfBooks(List<AutomaticIssueModel> listOfObjects){
		int totalValue = 0;
		if(listOfObjects != null && listOfObjects.size()>0){
			for(int i=0;i<listOfObjects.size();i++){
				totalValue += Integer.parseInt(listOfObjects.get(i).getNoOfBooks());
			}
		}
		return totalValue;
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
	
	@SuppressWarnings("finally")
	public int getCurrentOrganizationOfUser(){
		
		int orgId = 0;
		Session session = null;
		try{
			int userId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("userid");
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String queryTogetOrg = "SELECT `org_chart_id` orgId FROM `menu_user_master` WHERE `user_id` = '"+userId+"' ";	
			orgId = common.getDBResultInt(session, queryTogetOrg, "orgId");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return orgId;
		}
		
	}
	@SuppressWarnings("finally")
	public List<List<String>> getOrgList(){
		List<List<String>> listOfOrg = null;
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		try{
			session = HibernateUtil.getSession("");
			String queryForDenomListOfPassenegerType = "select org_chart_id,org_name from org_chart oc " +
					" inner join org_type ot on ot.org_type_id = oc.org_type_id " +
					" where ot.org_type in('CENTRAL OFFICE','DIVISION','DEPOT','STORE') and oc.org_chart_id !=-1 and deleted_status='0';";
			Query queryObject = session.createSQLQuery(queryForDenomListOfPassenegerType);
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			if(aliasToValueMapList.size()>0){
				listOfOrg = new ArrayList<List<String>>();
				List<String> list1 = new ArrayList<String>();
				list1.add("Select");
				list1.add("0");
				listOfOrg.add(list1);
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String,String> resultSet = aliasToValueMapList.get(i);
					List<String> list = new ArrayList<String>();
					
					list.add(String.valueOf(resultSet.get("org_name")));
					list.add(String.valueOf(resultSet.get("org_chart_id")));
					listOfOrg.add(list);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
			return listOfOrg;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean saveAutomaticIssueTickets(){
		boolean isSuccess = false;
		Session session = null;
		String message = "";
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			session = HibernateUtil.getSession("");
			session.beginTransaction();
			
				try{
					String userId = String.valueOf(request.getSession().getAttribute("userid"));
					int voucherId = insertIntoInvoiceMaster(session,request,userId);
					updateNormalTickets(session,request,userId,voucherId);
					updateLuggageTickets(session, request,userId,voucherId);
					updatePassTypeTickets(session, request,userId,voucherId);
					session.beginTransaction().commit();
					message = "Stock Issued Successfully";
					isSuccess = true;
				}catch(Exception e){
					session.getTransaction().rollback();
					isSuccess = false;
					message = "Problem in Stock Issue";
				}
			
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
			session.getTransaction().rollback();
		}finally{
			if(session != null){
				ServletActionContext.getRequest().getSession().setAttribute("message", message);
				session.close();
			}
			return isSuccess;
		}
	}
	@SuppressWarnings("finally")
	public void updateNormalTickets(Session session,HttpServletRequest request,String userId,int voucherId){
		String srNo[] = request.getParameterValues("normalSrNo");
		String denomValue[] = request.getParameterValues("normalDenomValue"); 
		String keyNo[] = request.getParameterValues("normalKeyNo");
		String openingNo[] =  request.getParameterValues("normalOpeningNo");
		String closingNo[] = request.getParameterValues("normalClosingNo");
		String noOfBooks[] = request.getParameterValues("normalNoOfBooks");
		String value[] = request.getParameterValues("normalValue");
		List<Map<String,String>> alisList = null;
		if(srNo!= null){
			for(int i=0;i<srNo.length;i++){
				
				String query1 = "SELECT ticket_inventory_det_id,tid.ticket_inventory_mst_id,tid.opening_number,tid.closing_number," +
						" tid.denomination_key,tid.pass_day,tid.number_of_tickets,tid.number_of_passes,tid.number_of_books," +
						" tid.denomination_type_manual_id FROM ticket_inventory_details tid " +
						" INNER JOIN denomination_type_manual dtm ON dtm.denomination_type_manual_id = tid.denomination_type_manual_id " +
						" INNER JOIN ticket_inventory_master tim ON tim.ticket_inventory_mst_id = tid.ticket_inventory_mst_id " +
						" WHERE dtm.denomination_type_manual = '"+denomValue[i]+"' AND tid.ticket_type_manual_id='1'" +
						" AND tid.denomination_key='"+keyNo[i]+"' AND tid.opening_number>='"+openingNo[i]+"' " +
						" ORDER BY tid.priority,tid.opening_number limit "+noOfBooks[i]+"  ";
				Query queryObject1 = session.createSQLQuery(query1).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				alisList = queryObject1.list(); 
				for(int x=0;x<alisList.size();x++){
					Map<String,String> rs = alisList.get(x);
					int detailsId = Integer.parseInt(String.valueOf(rs.get("ticket_inventory_det_id")));
					int masterId = Integer.parseInt(String.valueOf(rs.get("ticket_inventory_mst_id")));
					String updateQuery = "UPDATE ticket_inventory_details set  current_status='In Transit',updated_by='"+userId+"',updated_date=now() WHERE ticket_inventory_det_id='"+detailsId+"' ";
					session.createSQLQuery(updateQuery).executeUpdate();
					
					String queryToInsertInvoiceDetails = "INSERT INTO `ticket_invoice_details` (`ticket_invoice_mast_id`," +
							" `ticket_inventory_mst_id`, `ticket_inventory_det_id`, `created_by`, `created_date`) " +
							" VALUES ('"+voucherId+"', '"+masterId+"', '"+detailsId+"', '"+userId+"', now());";
					Query queryObjectForInsertInvoiceDetails = session.createSQLQuery(queryToInsertInvoiceDetails);
					queryObjectForInsertInvoiceDetails.executeUpdate();
				}
				
				/*String queryToUpdateInventoryRecordsStatus = "update ticket_inventory_details set  current_status='In Transit',updated_by='"+userId+"',updated_date=now()	" +
					" where ((opening_number between '"+openingNo[i]+"' and '"+closingNo[i]+"') or (closing_number between '"+openingNo[i]+"' and '"+closingNo[i]+"')) " +
					" and denomination_key ='"+keyNo[i]+"'  and current_status='New' and status = 'ACTIVE'";
				Query queryObject = session.createSQLQuery(queryToUpdateInventoryRecordsStatus);*/
			
				/*String queryForMasterId = "SELECT ticket_inventory_mst_id FROM `ticket_inventory_details`" +
						" WHERE `denomination_key` LIKE '"+keyNo[i]+"' AND `ticket_type_manual_id` = '1' and " +
						" ((opening_number between "+openingNo[i]+" and "+closingNo[i]+") or (closing_number between "+openingNo[i]+" and "+closingNo[i]+")) " +
						" group by ticket_inventory_mst_id";

				int masterId = common.getDBResultInt(session, queryForMasterId, "ticket_inventory_mst_id");
			
				String queryForInventorydetailsId = "SELECT ticket_inventory_det_id FROM `ticket_inventory_details`" +
						" WHERE `denomination_key` LIKE '"+keyNo[i]+"' AND `ticket_type_manual_id` = '1' and " +
						" ((opening_number between "+openingNo[i]+" and "+closingNo[i]+") or (closing_number between "+openingNo[i]+" and "+closingNo[i]+")) ";
				Query query = session.createSQLQuery(queryForInventorydetailsId);
				
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);*/
			
				/*List<Map<String,Integer>> listOfDetailsId = query.list();
				if(listOfDetailsId.size()>0){
					for(int j=0;j<listOfDetailsId.size();j++){
						String queryToInsertInvoiceDetails = "INSERT INTO `ticket_invoice_details` (`ticket_invoice_mast_id`," +
								" `ticket_inventory_mst_id`, `ticket_inventory_det_id`, `created_by`, `created_date`) " +
								" VALUES ('"+voucherId+"', '"+masterId+"', '"+(listOfDetailsId.get(j)).get("ticket_inventory_det_id")+"', '"+userId+"', now());";
						Query queryObjectForInsertInvoiceDetails = session.createSQLQuery(queryToInsertInvoiceDetails);
						queryObjectForInsertInvoiceDetails.executeUpdate();
					}
				}
				queryObject.executeUpdate();*/
			}
		}
	}
	
	@SuppressWarnings("finally")
	public void updateLuggageTickets(Session session,HttpServletRequest request,String userId,int voucherId) throws Exception{
		String srNo[] = request.getParameterValues("luggageSrNo");
		String keyNo[] = request.getParameterValues("luggageKeyNo");
		String openingNo[] =  request.getParameterValues("luggageOpeningNo");
		String closingNo[] = request.getParameterValues("luggageClosingNo");
		String noOfBooks[] = request.getParameterValues("luggageNoOfBooks");
		String denomValue[] = request.getParameterValues("luggageDenominationValue");
		List<Map<String,String>> alisList = new ArrayList<Map<String,String>>();
		if(srNo != null){
			for(int i=0;i<srNo.length;i++){
				
				String query1 = "SELECT ticket_inventory_det_id,tid.ticket_inventory_mst_id,tid.opening_number,tid.closing_number," +
						" tid.denomination_key,tid.pass_day,tid.number_of_tickets,tid.number_of_passes,tid.number_of_books," +
						" tid.denomination_type_manual_id FROM ticket_inventory_details tid " +
						" INNER JOIN denomination_type_manual dtm ON dtm.denomination_type_manual_id = tid.denomination_type_manual_id " +
						" INNER JOIN ticket_inventory_master tim ON tim.ticket_inventory_mst_id = tid.ticket_inventory_mst_id " +
						" WHERE dtm.denomination_type_manual = ? AND tid.ticket_type_manual_id='4' AND tid.denomination_key=? " +
						" AND tid.opening_number>=? ORDER BY tid.priority,tid.opening_number limit ?  ";
				Query queryObject1 = session.createSQLQuery(query1).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				queryObject1.setParameter(0, denomValue[i]);
				queryObject1.setParameter(1, keyNo[i]);
				queryObject1.setParameter(2, openingNo[i]);
				queryObject1.setParameter(3, noOfBooks[i]);
				alisList = queryObject1.list(); 
				for(int x=0;x<alisList.size();x++){
					Map<String,String> rs = alisList.get(x);
					int detailsId = Integer.parseInt(String.valueOf(rs.get("ticket_inventory_det_id")));
					int masterId = Integer.parseInt(String.valueOf(rs.get("ticket_inventory_mst_id")));
					String updateQuery = "UPDATE ticket_inventory_details set  current_status='In Transit'," +
							" updated_by='"+userId+"',updated_date=now() WHERE ticket_inventory_det_id='"+detailsId+"' ";
					session.createSQLQuery(updateQuery).executeUpdate();
					
					String queryToInsertInvoiceDetails = "INSERT INTO `ticket_invoice_details` (`ticket_invoice_mast_id`," +
							" `ticket_inventory_mst_id`, `ticket_inventory_det_id`, `created_by`, `created_date`) " +
							" VALUES ('"+voucherId+"', '"+masterId+"', '"+detailsId+"', '"+userId+"', now());";
					Query queryObjectForInsertInvoiceDetails = session.createSQLQuery(queryToInsertInvoiceDetails);
					queryObjectForInsertInvoiceDetails.executeUpdate();
				}
				
				
				/*String queryToUpdateInventoryRecordsStatus = "update ticket_inventory_details set  current_status='In Transit',updated_by='"+userId+"',updated_date=now()	" +
						" where ((opening_number = '"+openingNo[i]+"' and '"+closingNo[i]+"') or (closing_number between('"+openingNo[i]+"') " +
						" and ('"+closingNo[i]+"'))) and denomination_key ='"+keyNo[i]+"'  and current_status='New' and status = 'ACTIVE'";
				Query queryObject = session.createSQLQuery(queryToUpdateInventoryRecordsStatus);
			
				String queryForMasterId = "SELECT ticket_inventory_mst_id FROM `ticket_inventory_details`" +
						" WHERE `denomination_key` LIKE '"+keyNo[i]+"' AND `ticket_type_manual_id` = '4' and " +
						" ((opening_number between "+openingNo[i]+" and "+closingNo[i]+") or (closing_number between "+openingNo[i]+" and "+closingNo[i]+")) " +
						" group by ticket_inventory_mst_id";

				int masterId = common.getDBResultInt(session, queryForMasterId, "ticket_inventory_mst_id");
			
				String queryForInventorydetailsId = "SELECT ticket_inventory_det_id FROM `ticket_inventory_details`" +
						" WHERE `denomination_key` LIKE '"+keyNo[i]+"' AND `ticket_type_manual_id` = '4' and " +
						" ((opening_number between "+openingNo[i]+" and "+closingNo[i]+") or (closing_number between "+openingNo[i]+" and "+closingNo[i]+")) ";
				Query query = session.createSQLQuery(queryForInventorydetailsId);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			
				List<Map<String,Integer>> listOfDetailsId = query.list();
				if(listOfDetailsId.size()>0){
					for(int j=0;j<listOfDetailsId.size();j++){
						String queryToInsertInvoiceDetails = "INSERT INTO `ticket_invoice_details` (`ticket_invoice_mast_id`," +
							" `ticket_inventory_mst_id`, `ticket_inventory_det_id`, `created_by`, `created_date`) " +
							" VALUES ('"+voucherId+"', '"+masterId+"', '"+(listOfDetailsId.get(j)).get("ticket_inventory_det_id")+"', '"+userId+"', now());";
						Query queryObjectForInsertInvoiceDetails = session.createSQLQuery(queryToInsertInvoiceDetails);
						queryObjectForInsertInvoiceDetails.executeUpdate();
					}
				}
				queryObject.executeUpdate();
				isSuccess = true;*/
			}
		}
	}
	@SuppressWarnings("finally")
	public void updatePassTypeTickets(Session session,HttpServletRequest request,String userId,int voucherId) throws Exception{
		String srNo[] = request.getParameterValues("passSrNo");
		String denomValue[] = request.getParameterValues("passDenomValue"); 
		String keyNo[] = request.getParameterValues("passKeyNo");
		String type[] = request.getParameterValues("passType");
		String passDay[] = request.getParameterValues("passDay");
		String openingNo[] =  request.getParameterValues("passOpeningNo");
		String closingNo[] = request.getParameterValues("passClosingNo");
		String noOfBooks[] = request.getParameterValues("passNoOfBooks");
		String value[] = request.getParameterValues("passValue");
		List<Map<String,String>> alisList = new ArrayList<Map<String,String>>();
		if(srNo != null){
			for(int i=0;i<srNo.length;i++){
				
				String query1 = "SELECT ticket_inventory_det_id,tid.ticket_inventory_mst_id,tid.opening_number,tid.closing_number," +
						" tid.denomination_key,tid.pass_day,tid.number_of_tickets,tid.number_of_passes,tid.number_of_books," +
						" tid.denomination_type_manual_id FROM ticket_inventory_details tid " +
						" INNER JOIN denomination_type_manual dtm ON dtm.denomination_type_manual_id = tid.denomination_type_manual_id " +
						" INNER JOIN ticket_inventory_master tim ON tim.ticket_inventory_mst_id = tid.ticket_inventory_mst_id " +
						" WHERE dtm.denomination_type_manual = '"+denomValue[i]+"' AND tid.pass_day = '"+passDay[i]+"' AND tid.ticket_type_manual_id in (2,3) AND tid.denomination_key='"+keyNo[i]+"' " +
						" AND tid.opening_number>='"+openingNo[i]+"' ORDER BY tid.priority,tid.opening_number limit "+noOfBooks[i]+"  ";
				Query queryObject1 = session.createSQLQuery(query1).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				alisList = queryObject1.list(); 
				for(int x=0;x<alisList.size();x++){
					Map<String,String> rs = alisList.get(x);
					int detailsId = Integer.parseInt(String.valueOf(rs.get("ticket_inventory_det_id")));
					int masterId = Integer.parseInt(String.valueOf(rs.get("ticket_inventory_mst_id")));
					String updateQuery = "UPDATE ticket_inventory_details set  current_status='In Transit',updated_by='"+userId+"',updated_date=now() WHERE ticket_inventory_det_id='"+detailsId+"' ";
					session.createSQLQuery(updateQuery).executeUpdate();
					
					String queryToInsertInvoiceDetails = "INSERT INTO `ticket_invoice_details` (`ticket_invoice_mast_id`," +
							" `ticket_inventory_mst_id`, `ticket_inventory_det_id`, `created_by`, `created_date`) " +
							" VALUES ('"+voucherId+"', '"+masterId+"', '"+detailsId+"', '"+userId+"', now());";
					Query queryObjectForInsertInvoiceDetails = session.createSQLQuery(queryToInsertInvoiceDetails);
					queryObjectForInsertInvoiceDetails.executeUpdate();
				}
				
				/*String queryToUpdateInventoryRecordsStatus = "update ticket_inventory_details set  current_status='In Transit',updated_by='"+userId+"',updated_date=now()	" +
						" where ((opening_number = '"+openingNo[i]+"' and '"+closingNo[i]+"') or (closing_number between('"+openingNo[i]+"') " +
						" and ('"+closingNo[i]+"'))) and denomination_key ='"+keyNo[i]+"'  and current_status='New' and status = 'ACTIVE'";
				Query queryObject = session.createSQLQuery(queryToUpdateInventoryRecordsStatus);
				
				String queryForMasterId = "SELECT ticket_inventory_mst_id FROM `ticket_inventory_details`" +
						" WHERE `denomination_key` LIKE '"+keyNo[i]+"' AND `ticket_type_manual_id`  in('2','3') and " +
						" ((opening_number between "+openingNo[i]+" and "+closingNo[i]+") or (closing_number between "+openingNo[i]+" and "+closingNo[i]+")) " +
						" group by ticket_inventory_mst_id";

				int masterId = common.getDBResultInt(session, queryForMasterId, "ticket_inventory_mst_id");
			
				String queryForInventorydetailsId = "SELECT ticket_inventory_det_id FROM `ticket_inventory_details`" +
						" WHERE `denomination_key` LIKE '"+keyNo[i]+"' AND `ticket_type_manual_id` in('2','3') and " +
						" ((opening_number between "+openingNo[i]+" and "+closingNo[i]+") or (closing_number between "+openingNo[i]+" and "+closingNo[i]+")) ";
				Query query = session.createSQLQuery(queryForInventorydetailsId);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			
				List<Map<String,Integer>> listOfDetailsId = query.list();
				if(listOfDetailsId.size()>0){
					for(int j=0;j<listOfDetailsId.size();j++){
						String queryToInsertInvoiceDetails = "INSERT INTO `ticket_invoice_details` (`ticket_invoice_mast_id`," +
							" `ticket_inventory_mst_id`, `ticket_inventory_det_id`, `created_by`, `created_date`) " +
							" VALUES ('"+voucherId+"', '"+masterId+"', '"+(listOfDetailsId.get(j)).get("ticket_inventory_det_id")+"', '"+userId+"', now());";
						Query queryObjectForInsertInvoiceDetails = session.createSQLQuery(queryToInsertInvoiceDetails);
						queryObjectForInsertInvoiceDetails.executeUpdate();
					}
				}
			queryObject.executeUpdate();
			isSuccess = true;*/
			}
		}
		
	}
	
	@SuppressWarnings("finally")
	public int insertIntoInvoiceMaster(Session session,HttpServletRequest request,String userId) throws Exception{
		int voucherId = 0;
		try{
			String issueTo = request.getParameter("issueTo");
			String issueFrom = request.getParameter("issueFrom");
			String indentNumber = request.getParameter("indentNumber");
			
			String queryForMaxInviceNumber = "select ifnull(count(ticket_invoice_mast_id),0) maxId  from ticket_invoice_master where voucher_type='I'";
			
			String issueToOrgTypeIdQuery = "SELECT `org_type_id` FROM `org_chart` WHERE `org_chart_id` = '"+issueTo+"' ";
			String issueToOrgTypeId= common.getDBResultStr(session, issueToOrgTypeIdQuery, "org_type_id");
			
			String issueFromOrgTypeIdQuery = "SELECT `org_type_id` FROM `org_chart` WHERE `org_chart_id` = '"+issueFrom+"' ";
			String issueFromOrgTypeId= common.getDBResultStr(session, issueFromOrgTypeIdQuery, "org_type_id");
			
			int totalNormalValue = request.getParameter("totalNormalValue")!=null?Integer.parseInt(request.getParameter("totalNormalValue")):0;
			int totalPassValue = request.getParameter("totalPassValue")!=null?Integer.parseInt(request.getParameter("totalPassValue")):0;
			
			
			int maxId = common.getDBResultInt(session, queryForMaxInviceNumber, "maxId");
			String voucherNumber = generateVoucherNumbar(maxId+1,10);
			request.getSession().setAttribute("voucherNumber", voucherNumber);
			String insertQueryForTcktInvoiceMasterTable = "INSERT INTO `ticket_invoice_master` (`voucher_number`,`indent_number`, `issue_to_unit`, `issue_to_unit_id`, `issue_from_unit`," +
					"`issue_from_unit_id`,`created_by`, `current_status`, `status`, `created_date`,`updated_by`,`stock_value` ) " +
					" VALUES ('"+voucherNumber+"','"+indentNumber+"','"+issueToOrgTypeId+"', '"+issueTo+"', '"+issueFromOrgTypeId+"', '"+issueFrom+"', '"+userId+"'," +
					" 'In Transit', 'ACTIVE', now(),'0', '"+(totalNormalValue+totalPassValue)+"');";
			Query qryObject = session.createSQLQuery(insertQueryForTcktInvoiceMasterTable);
			qryObject.executeUpdate();
		
			voucherId = common.getDBResultInt(session, "select last_insert_id() as id from ticket_invoice_master", "id");
			
			String queryForInsertLog = "INSERT INTO `ticket_inventory_logs` (`ticket_invoice_mast_id`, `invoice_status`,  " +
					" `transaction_on_type`, `action_unit`, `action_by`, `created_date`, `created_by`) " +
					" VALUES ('"+voucherId+"', 'In Transit', 'Issued', '"+issueFrom+"', '"+userId+"', now(), '"+userId+"');";
			session.createSQLQuery(queryForInsertLog).executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return voucherId;
		}
	}
	public String generateVoucherNumbar(int voucherSeqNo,int size) throws Exception{
		
			String voucherNumber = new String(String.valueOf(voucherSeqNo));
	        StringBuilder sb = new StringBuilder(String.valueOf(voucherSeqNo));
	        StringBuilder sb2 = new StringBuilder();
	        int m1 = voucherNumber.length();
	        if (m1 >= size) {
//	            str1.substring(0, a1 - 1);
	            return voucherNumber;
	        }
	        size = size - m1;
	        for (int i = 0; i < size; i++) {
	            sb2.append("0");
	        }
	        sb2.append(sb);
	        String sb1 = sb2.toString();
	        return sb1;
	    }
		
	public int getCentralStockCountOfDenom(String denomId,String ticketType,String passDay){
		int count = 0;
		Session session = null;
		try{
			String passDayCondition = "";
			String ticketTypeCondition = "";
			if(!passDay.equals("")){
				passDayCondition = " AND tid.pass_day ='"+passDay+"'";
			}
			if(ticketType.equals("4")){
				ticketTypeCondition = " denomination_type_manual=' '  AND ttm.ticket_type_manual_id ='4'";
			}else{
				ticketTypeCondition = " dy.denomination_type_manual_id='"+denomId+"'";
			}
			session  = HibernateUtil.getSession("");
			String sql = "SELECT  sum(number_of_books) AS number_of_books,sum(number_of_tickets) number_of_tickets,sum(number_of_passes) number_of_passes " +
					" FROM (SELECT number_of_books,number_of_tickets,number_of_passes  FROM ticket_inventory_details tid " +
					" INNER JOIN denomination_type_manual dy ON dy.denomination_type_manual_id= tid.denomination_type_manual_id " +
					" INNER JOIN ticket_type_manual ttm ON tid.ticket_type_manual_id=ttm.ticket_type_manual_id" +
					" WHERE  "+ticketTypeCondition+" "+passDayCondition+"  AND tid.status = 'ACTIVE'" +
					" AND tid.current_status='New')a";
			count = common.getDBResultInt(session, sql, "number_of_books");
			
			String sql1 = "SELECT ttm.ticket_type_manual_id FROM denomination_type_manual dtm  " +
					"INNER JOIN ticket_type_manual ttm ON dtm.ticket_type_manual_id = ttm.ticket_type_manual_id  WHERE dtm.denomination_type_manual_id='"+denomId+"'";
			int passStype = common.getDBResultInt(session, sql1, "ticket_type_manual_id") ;
			if(passStype==3){
				count = common.getDBResultInt(session, sql, "number_of_passes");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return count; 
	}
		
}
