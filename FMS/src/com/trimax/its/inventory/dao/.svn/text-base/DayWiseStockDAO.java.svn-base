package com.trimax.its.inventory.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.inventory.model.StockViewModel;
import com.trimax.its.util.HibernateUtil;

public class DayWiseStockDAO {
	
	
	public List getReferenceNumber(String date) {
		List list = new ArrayList();
		String fromDate=date+" 00:00:00";
		String tillDate=date+ " 23:59:59 ";
		
		String qry = "select id from ticket_inventory_ref where created_date   between  '"+fromDate+"' and '"+tillDate+"' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	@SuppressWarnings("finally")
	public List<StockViewModel> getPassengerTickets(String stockDate,int referenceId,String divisionparam ){
		List<StockViewModel> object = null;
		List<Map<String,String>> aliasToValueMapList = null;
		String queryForPassengerTickets="";
		Session session = null;
		int totalValue = 0 ;
		String organizationId ="0";
		int organizationtype=0;
		String currentStatus="";
		String typeSelect="";
		try{
			session = HibernateUtil.getSession("");
			HttpServletRequest request = ServletActionContext.getRequest();
			/*if(orgId==0){
				//organizationId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
				organizationId = (String)request.getSession().getAttribute("orgchartid");
			}else{
				organizationId = String.valueOf(orgId);
			}*/
			
			String condition = "";
			if(stockDate!=""){
				condition = "and tid.created_date like '%"+stockDate+"%' ";
			}
			organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			
	
				
				
					  currentStatus="New";
				       queryForPassengerTickets = "select dy.denomination_type_manual, a.denomination_key,a.priority, " +
							" a.opening_number, a.closing_number, a.number_of_tickets, a.number_of_books, a.value, " +
							" a.ticket_type_manual_id,a.unit_name ,a.current_status, a.ticket_inventory_mst_id   from " +
							" (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, " +
							" max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,priority, " +
							" sum(number_of_books) as number_of_books,sum(value) as value, unit_type,unit_name,ticket_type_manual_id ,tid.current_status, tid.ticket_inventory_mst_id " +
							" FROM ticket_inventory_details tid  WHERE tid.status = 'ACTIVE'    " +
							"  " +condition +
							" group by tid.ticket_inventory_mst_id,tid.denomination_key, tid.denomination_type_manual_id ) a " +
							" inner join ticket_inventory_master tm  on a.ticket_inventory_mst_id=tm.ticket_inventory_mst_id and tm.inventory_ref_id="+referenceId+" and tm.current_status='New'"+  
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id  " +
							" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=1 " +
							" order by CAST(dy.denomination_type_manual AS UNSIGNED)  ";
						
			
				
			//}
			
			Query queryObject = session.createSQLQuery(queryForPassengerTickets)
					.addScalar("denomination_type_manual", Hibernate.STRING).addScalar("denomination_key", Hibernate.STRING)
					.addScalar("opening_number", Hibernate.STRING).addScalar("closing_number", Hibernate.STRING)
					.addScalar("number_of_tickets", Hibernate.STRING).addScalar("number_of_books", Hibernate.STRING).addScalar("priority",Hibernate.STRING)
					.addScalar("value", Hibernate.STRING).addScalar("current_status",Hibernate.STRING);
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			
			aliasToValueMapList = queryObject.list();
			if(aliasToValueMapList.size()>0){
				object =  new ArrayList<StockViewModel>();
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String,String> resultSet = aliasToValueMapList.get(i);
					StockViewModel modelObject = new StockViewModel();
					
					String keyNumber = resultSet.get("denomination_key");
					String denominationValue =  resultSet.get("denomination_type_manual");
					String openingNumber = leftPadding(String.valueOf(resultSet.get("opening_number")),8,"0");
					String closingNumber = leftPadding(String.valueOf(resultSet.get("closing_number")),8,"0");
					String noOfTickets =  String.valueOf(resultSet.get("number_of_tickets"));
					String noOfBooks =  String.valueOf(resultSet.get("number_of_books"));
					String value = String.valueOf(resultSet.get("value"));
					String currentstatus=String.valueOf(resultSet.get("current_status"));
					String priority=String.valueOf(resultSet.get("priority"));
					modelObject.setPriority(priority);
					modelObject.setDenominationValue(denominationValue);
					modelObject.setKeyNumber(keyNumber);
					modelObject.setOpeningNumber(openingNumber);
					modelObject.setClosingNumber(closingNumber);
					modelObject.setNoOfTickets(noOfTickets);
					modelObject.setNoOfBooks(noOfBooks);
					modelObject.setValue(leftPadding(value,8," "));
					modelObject.setCurrentStatus(currentstatus);
					
					totalValue += Integer.parseInt(value);
					
					object.add(modelObject);
				}
				/*listOfPassengerTicketObject.setValue(String.valueOf(totalValue));
				listOfPassengerTicketObject.setListOfPassengerTypeTickets(object);*/
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return object;
		}
		
	}
	
	@SuppressWarnings("finally")
	public List<StockViewModel> getPassTypeTickets(String stockDate,int referenceId,String divisionparam){
		List<StockViewModel> object = null;
		List<Map<String,String>> aliasToValueMapList = null;
		Session session = null;
		int totalValue = 0 ;
		String organizationId ="0";
		int organizationtype=0;
		String queryForPassTypeTickets=" ";
		String currentStatus="";
		String typeSelect="";
		List <String> a=null;
		try{
			session = HibernateUtil.getSession("");
			HttpServletRequest request = ServletActionContext.getRequest();
			/*if(orgId==0){
				//organizationId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
				organizationId = (String)request.getSession().getAttribute("orgchartid");
			}else{
				organizationId = String.valueOf(orgId);
			}*/
			
			//int orgId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			String condition = "";
			if(stockDate!=""){
				condition = "and created_date like '%"+stockDate+"%' ";
			}
			organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			
			
		       
					  currentStatus="New";
				        queryForPassTypeTickets = "select dy.denomination_type_manual, a.denomination_key,a.priority, " +
							" a.opening_number, a.closing_number, a.number_of_tickets, a.number_of_books, a.value, " +
							" a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name,a.current_status as current_status1,a.ticket_inventory_mst_id from " +
							" (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, " +
							" max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,priority, tid.ticket_inventory_mst_id ," +
							" sum(number_of_books) as number_of_books,sum(value) as value, unit_type,unit_name,ticket_type_manual_id,tid.current_status " +
							" FROM ticket_inventory_details tid  WHERE tid.status = 'ACTIVE'     " +
							"  " + condition +
							" group by tid.ticket_inventory_mst_id,tid.denomination_key, tid.denomination_type_manual_id) a " +
							" inner join ticket_inventory_master tm  on a.ticket_inventory_mst_id=tm.ticket_inventory_mst_id and tm.inventory_ref_id="+referenceId+" and tm.current_status='New'"+
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id  " +
							" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id in ('2','3') " +
							" order by CAST(dy.denomination_type_manual AS UNSIGNED) ";
				
			
			
			Query queryObject = session.createSQLQuery(queryForPassTypeTickets)
					.addScalar("denomination_type_manual", Hibernate.STRING).addScalar("denomination_key", Hibernate.STRING)
					.addScalar("ticket_type_manual_name", Hibernate.STRING).addScalar("priority",Hibernate.STRING)
					.addScalar("opening_number", Hibernate.STRING).addScalar("closing_number", Hibernate.STRING)
					.addScalar("number_of_tickets", Hibernate.STRING).addScalar("number_of_books", Hibernate.STRING)
					.addScalar("value", Hibernate.STRING).addScalar("current_status1",Hibernate.STRING);
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			if(aliasToValueMapList.size()>0){
				object =  new ArrayList<StockViewModel>();
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String,String> resultSet = aliasToValueMapList.get(i);
					StockViewModel modelObject = new StockViewModel();
					
					String keyNumber = resultSet.get("denomination_key");
					String denominationValue =  resultSet.get("denomination_type_manual");
					String passType =  resultSet.get("ticket_type_manual_name");
					String openingNumber = leftPadding(String.valueOf(resultSet.get("opening_number")),8,"0");
					String closingNumber =  leftPadding(String.valueOf(resultSet.get("closing_number")),8,"0");
					String noOfTickets =  String.valueOf(resultSet.get("number_of_tickets"));
					String noOfBooks =  String.valueOf(resultSet.get("number_of_books"));
					String value =  String.valueOf(resultSet.get("value"));
					String currentstatus=String.valueOf(resultSet.get("current_status1"));
					String priority=String.valueOf(resultSet.get("priority"));
					modelObject.setPriority(priority);
					modelObject.setDenominationValue(denominationValue);
					modelObject.setKeyNumber(keyNumber);
					modelObject.setPassType(passType);
					modelObject.setOpeningNumber(openingNumber);
					modelObject.setClosingNumber(closingNumber);
					modelObject.setNoOfTickets(noOfTickets);
					modelObject.setNoOfBooks(noOfBooks);
					modelObject.setValue(value);
					modelObject.setCurrentStatus(currentstatus);
					
					totalValue += Integer.parseInt(value);
				
					object.add(modelObject);
				}
				/*listOfPassTypeTicketObject.setValue(String.valueOf(totalValue));
				listOfPassTypeTicketObject.setListOfPassTypeTickets(object);*/
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return object;
		}
		
	}

	@SuppressWarnings("finally")
	public List<StockViewModel> getLuggaugeTickets(String stockDate,int referenceId,String divisionparam){
		List<StockViewModel> object = null;
		List<Map<String,String>> aliasToValueMapList = null;
		Session session = null;
		int totalBooks = 0 ;
		String organizationId="0";
		String queryForLugaugeTickets=" ";
		int organizationtype=0;
		String currentStatus="";
		String typeSelect="";
		String condition="";
		List<String> a=null;
		//and tid.current_status='"+currentStatus+"'
		try{
			session = HibernateUtil.getSession("");
			HttpServletRequest request = ServletActionContext.getRequest();
			organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			/*if(orgId==0){
				organizationId = (String)request.getSession().getAttribute("orgchartid");
			}else{
				organizationId = String.valueOf(orgId);
			}*/
			if(stockDate!=""){
				condition = "and created_date like '%"+stockDate+"%' ";
			}
				
				currentStatus="New";
				 queryForLugaugeTickets = "select dy.denomination_type_manual, a.denomination_key,a.priority, " +
						" a.opening_number, a.closing_number, a.number_of_tickets, a.number_of_books, a.value, " +
						" a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ,a.current_status ,a.ticket_inventory_mst_id from " +
						" (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, " +
						" max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,priority, " +
						" sum(number_of_books) as number_of_books,sum(value) as value, unit_type,unit_name,ticket_type_manual_id  , tid.current_status ,tid.ticket_inventory_mst_id" +
						" FROM ticket_inventory_details tid  WHERE tid.status = 'ACTIVE'   " +
						"  " + condition + " group by tid.ticket_inventory_mst_id,tid.denomination_key, tid.denomination_type_manual_id ) a " +
						" inner join ticket_inventory_master tm  on a.ticket_inventory_mst_id=tm.ticket_inventory_mst_id and tm.inventory_ref_id="+referenceId+" and tm.current_status='New'"+                                                                     
						" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id  " +
						" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=4 " +
						" order by CAST(dy.denomination_type_manual AS UNSIGNED) " ;
		
			Query queryObject = session.createSQLQuery(queryForLugaugeTickets)
					.addScalar("denomination_type_manual", Hibernate.STRING).addScalar("denomination_key", Hibernate.STRING)
					.addScalar("opening_number", Hibernate.STRING).addScalar("closing_number", Hibernate.STRING)
					.addScalar("number_of_tickets", Hibernate.STRING).addScalar("number_of_books", Hibernate.STRING).addScalar("current_status", Hibernate.STRING).addScalar("priority",Hibernate.STRING);
			
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			if(aliasToValueMapList.size()>0){
				object =  new ArrayList<StockViewModel>();
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String,String> resultSet = aliasToValueMapList.get(i);
					StockViewModel modelObject = new StockViewModel();
					
//					String keyNumber = resultSet.get("denomination_type_manual");
					String keyNumber =  resultSet.get("denomination_key");
					String passType =  resultSet.get("ticket_type_manual_name");
					String openingNumber = leftPadding(String.valueOf(resultSet.get("opening_number")),8,"0");
					String closingNumber =  leftPadding(String.valueOf(resultSet.get("closing_number")),8,"0");
					String noOfBooks =  String.valueOf(resultSet.get("number_of_books"));
					String currentstatus=String.valueOf(resultSet.get("current_status"));
					String priority=String.valueOf(resultSet.get("priority"));
					modelObject.setPriority(priority);
					modelObject.setKeyNumber(keyNumber);
					modelObject.setPassType(passType);
					modelObject.setOpeningNumber(openingNumber);
					modelObject.setClosingNumber(closingNumber);
					modelObject.setNoOfBooks(noOfBooks);
					
					totalBooks += Integer.parseInt(noOfBooks);
					modelObject.setValue(currentstatus);
					modelObject.setCurrentStatus(currentstatus);
					
					object.add(modelObject);
				}
				/*listOfLuggaugeTickets.setNoOfBooks(String.valueOf(totalBooks));
				listOfLuggaugeTickets.setListOfLuggaugeTickets(object);*/
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return object;
		}
		
	}
	
	public int getTotalValue(List<StockViewModel> listOfObjects){
		int totalValue = 0;
		if(listOfObjects != null && listOfObjects.size()>0){
			for(int i=0;i<listOfObjects.size();i++){
				totalValue += Integer.parseInt(listOfObjects.get(i).getValue().trim());
			}
		}
		return totalValue;
	}
	public int getTotalNoOfBooks(List<StockViewModel> listOfObjects){
		int totalValue = 0;
		if(listOfObjects != null && listOfObjects.size()>0){
			for(int i=0;i<listOfObjects.size();i++){
				totalValue += Integer.parseInt(listOfObjects.get(i).getNoOfBooks().trim());
			}
		}
		return totalValue;
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
	
}