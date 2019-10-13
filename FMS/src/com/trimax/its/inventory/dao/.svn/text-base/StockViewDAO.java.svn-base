package com.trimax.its.inventory.dao;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.inventory.model.StockViewModel;
import com.trimax.its.orgchart.action.OrgTypeAction;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class StockViewDAO {
	
	static Common common = new Common();
	
	
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
	public List<StockViewModel> getPassengerTickets(String stockDate,int orgId,String divisionparam ){
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
			if(orgId==0){
				//organizationId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
				organizationId = (String)request.getSession().getAttribute("orgchartid");
			}else{
				organizationId = String.valueOf(orgId);
			}
			
			String condition = "";
			if(stockDate!=""){
				condition = "and tid.created_date like '%"+stockDate+"%' ";
			}
			organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			List <String> a=null;
			if(divisionparam!=null){
					String type[]=divisionparam.split("@");
					String orgtypeselect=type[0].trim();
				if(orgtypeselect.trim().contains("all")){
					if(orgtypeselect.contains("6")){
							//System.out.println("orgooo"+organizationId);
				 
								a=getDepotId1(6,session);
						}else if(orgtypeselect.contains("7")){
								a=getDepotId1(7,session);
						}else if(orgtypeselect.contains("4")){
								a=getDepotId1(4,session);
						}else if(orgtypeselect.contains("2")){
								a=getDepotId1(2,session);
						}else if(orgtypeselect.contains("5")){
								a=getDepotId1(5,session);
						}
						if(a!=null){
									String Depid="";
									for(int i=0;i<a.size()-1;i++){
										Depid+=a.get(i)+",";
									}
									organizationId=Depid.substring(0,Depid.length()-1);
				
								}
					
				}else{
			    	   organizationId=orgtypeselect;
			       }	
				typeSelect=type[1];
			}
			if(organizationtype==1){
				
				if(typeSelect.equals("C")){
				currentStatus="New";
				queryForPassengerTickets = "select dy.denomination_type_manual, a.denomination_key,a.priority, " +
					" a.opening_number, a.closing_number, a.number_of_tickets, a.number_of_books, a.value, " +
					" a.ticket_type_manual_id,a.unit_name ,a.current_status  from " +
					" (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, " +
					" max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,priority, " +
					" sum(number_of_books) as number_of_books,sum(value) as value, unit_type,unit_name,ticket_type_manual_id ,tid.current_status " +
					" FROM ticket_inventory_details tid  WHERE tid.status = 'ACTIVE' and tid.current_status='"+currentStatus+"'  and " +
					" tid.unit_name in ("+organizationId.trim()+") " +condition +
					" group by tid.denomination_key, tid.denomination_type_manual_id ) a " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id  " +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=1 " +
					" order by CAST(dy.denomination_type_manual AS UNSIGNED)  ";
				}
				else if(typeSelect.equals("D"))
				{
					organizationtype=2;
					currentStatus="ISSUED";
					queryForPassengerTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=1" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
				}else if(typeSelect.equals("De")){
					currentStatus="ISSUED";
					organizationtype=3;
					queryForPassengerTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=1" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
					
				}else{
					  currentStatus="New";
				       queryForPassengerTickets = "select dy.denomination_type_manual, a.denomination_key,a.priority, " +
							" a.opening_number, a.closing_number, a.number_of_tickets, a.number_of_books, a.value, " +
							" a.ticket_type_manual_id,a.unit_name ,a.current_status  from " +
							" (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, " +
							" max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,priority, " +
							" sum(number_of_books) as number_of_books,sum(value) as value, unit_type,unit_name,ticket_type_manual_id ,tid.current_status " +
							" FROM ticket_inventory_details tid  WHERE tid.status = 'ACTIVE' and tid.current_status='"+currentStatus+"'  and " +
							" tid.unit_name in ("+organizationId.trim()+") " +condition +
							" group by tid.denomination_key, tid.denomination_type_manual_id ) a " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id  " +
							" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=1 " +
							" order by CAST(dy.denomination_type_manual AS UNSIGNED)  ";
				}
					
			}else if(organizationtype==2){
				if(typeSelect.equals("N"))
				{
			    organizationtype=2;
				currentStatus="ISSUED";
				queryForPassengerTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
						" max(ti.closing_number) as closing_number," +
						" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
						" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
						" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
						" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
						" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
						" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
						" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=1" +
						" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
						" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
						" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
				}else if(typeSelect.equals("Y")){
					
					 organizationtype=3;
					currentStatus="ISSUED";
					queryForPassengerTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=1" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
				}else{
					organizationtype=2;
					currentStatus="ISSUED";
					queryForPassengerTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=1" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
					}
				}else if(organizationtype==3){
					currentStatus="ISSUED";
					queryForPassengerTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=1" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
				}
				
			//}
			
			Query queryObject = session.createSQLQuery(queryForPassengerTickets)
					.addScalar("denomination_type_manual", Hibernate.STRING).addScalar("denomination_key", Hibernate.STRING)
					.addScalar("opening_number", Hibernate.STRING).addScalar("closing_number", Hibernate.STRING)
					.addScalar("number_of_tickets", Hibernate.STRING).addScalar("number_of_books", Hibernate.STRING)
					
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
	public List<StockViewModel> getPassTypeTickets(String stockDate,int orgId,String divisionparam){
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
			if(orgId==0){
				//organizationId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
				organizationId = (String)request.getSession().getAttribute("orgchartid");
			}else{
				organizationId = String.valueOf(orgId);
			}
			if(divisionparam!=null){
					String type[]=divisionparam.split("@");
					String orgtypeselect=type[0].trim();
					if(orgtypeselect.trim().contains("all")){
						if(orgtypeselect.contains("6")){
							System.out.println("orgooo"+organizationId);
				 
								a=getDepotId1(6,session);
						}else if(orgtypeselect.contains("7")){
							a=getDepotId1(7,session);
						}else if(orgtypeselect.contains("4")){
							a=getDepotId1(4,session);
					
						}else if(orgtypeselect.contains("2")){
							a=getDepotId1(2,session);
						}else if(orgtypeselect.contains("5")){
							a=getDepotId1(5,session);
						}
				 if(a!=null){
					 		String Depid="";
					 		for(int i=0;i<a.size()-1;i++){
							Depid+=a.get(i)+",";
					 		}
					 		organizationId=Depid.substring(0,Depid.length()-1);
			       }
				
			}else{
					organizationId=orgtypeselect;
				      
				}	
					typeSelect=type[1];
			}
			//int orgId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			String condition = "";
			if(stockDate!=""){
				condition = "and created_date like '%"+stockDate+"%' ";
			}
			organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			if(organizationtype==1){
				if(typeSelect.equals("C")){
		       currentStatus="NeW";
		        queryForPassTypeTickets = "select dy.denomination_type_manual, a.denomination_key,a.priority, " +
					" a.opening_number, a.closing_number, a.number_of_tickets, a.number_of_books, a.value, " +
					" a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name,a.current_status as current_status1 from " +
					" (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, " +
					" max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,priority, " +
					" sum(number_of_books) as number_of_books,sum(value) as value, unit_type,unit_name,ticket_type_manual_id,tid.current_status " +
					" FROM ticket_inventory_details tid  WHERE tid.status = 'ACTIVE'  and tid.current_status='"+currentStatus+"' and  " +
					" tid.unit_name in ("+organizationId.trim()+") " + condition +
					" group by tid.denomination_key, tid.denomination_type_manual_id) a " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id  " +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id in ('2','3') " +
					" order by CAST(dy.denomination_type_manual AS UNSIGNED) ";
				}else if(typeSelect.equals("D")){
					organizationtype=2;
					  currentStatus="ISSUED";
					queryForPassTypeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status as current_status1 from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id in ('2','3')" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id  in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
				}else if(typeSelect.equals("De")){
					organizationtype=3;
					 currentStatus="ISSUED";
					queryForPassTypeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status as current_status1 from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id in ('2','3')" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
				}else{
					  currentStatus="New";
				        queryForPassTypeTickets = "select dy.denomination_type_manual, a.denomination_key,a.priority, " +
							" a.opening_number, a.closing_number, a.number_of_tickets, a.number_of_books, a.value, " +
							" a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name,a.current_status as current_status1 from " +
							" (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, " +
							" max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,priority, " +
							" sum(number_of_books) as number_of_books,sum(value) as value, unit_type,unit_name,ticket_type_manual_id,tid.current_status " +
							" FROM ticket_inventory_details tid  WHERE tid.status = 'ACTIVE'  and tid.current_status='"+currentStatus+"' and  " +
							" tid.unit_name in ("+organizationId.trim()+") " + condition +
							" group by tid.denomination_key, tid.denomination_type_manual_id) a " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id  " +
							" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id in ('2','3') " +
							" order by CAST(dy.denomination_type_manual AS UNSIGNED) ";
				}
			}else if(organizationtype==2){
				if(typeSelect.equals("N")){
					currentStatus="ISSUED";
					organizationtype=2;
					queryForPassTypeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status as current_status1 from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id in ('2','3')" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
				}else if(typeSelect.equals("Y"))
				{
					currentStatus="ISSUED";
					organizationtype=3;
					queryForPassTypeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status as current_status1 from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id in ('2','3')" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
					
				}else{
					currentStatus="ISSUED";
					organizationtype=2;
					queryForPassTypeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status as current_status1 from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id in ('2','3')" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
				}
			}
			   else if(organizationtype==3){
				   currentStatus="ISSUED";
					organizationtype=3;
					queryForPassTypeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status as current_status1 from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id in ('2','3')" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
					
				}
				
				
			
			Query queryObject = session.createSQLQuery(queryForPassTypeTickets)
					.addScalar("denomination_type_manual", Hibernate.STRING).addScalar("denomination_key", Hibernate.STRING)
					.addScalar("ticket_type_manual_name", Hibernate.STRING)
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
	public List<StockViewModel> getLuggaugeTickets(String stockDate,int orgId,String divisionparam){
		List<StockViewModel> object = null;
		List<Map<String,String>> aliasToValueMapList = null;
		Session session = null;
		int totalBooks = 0 ;
		String organizationId="0";
		String queryForLugaugeTickets=" ";
		int organizationtype=0;
		String currentStatus="";
		String typeSelect="";
		List<String> a=null;
		try{
			session = HibernateUtil.getSession("");
			HttpServletRequest request = ServletActionContext.getRequest();
			organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			if(orgId==0){
				organizationId = (String)request.getSession().getAttribute("orgchartid");
			}else{
				organizationId = String.valueOf(orgId);
			}
			if(divisionparam!=null){
				String type[]=divisionparam.split("@");
				String orgtypeselect=type[0].trim();
				if(orgtypeselect.trim().contains("all")){
					if(orgtypeselect.contains("6")){
						System.out.println("orgooo"+organizationId);
						a=getDepotId1(6,session);
					}else if(orgtypeselect.contains("7")){
						a=getDepotId1(7,session);
					}else if(orgtypeselect.contains("4")){
						a=getDepotId1(4,session);
					}else if(orgtypeselect.contains("2")){
						a=getDepotId1(2,session);
					}else if(orgtypeselect.contains("5")){
						a=getDepotId1(5,session);
					}
					if(a!=null){
							String Depid="";
							for(int i=0;i<a.size()-1;i++){
							Depid+=a.get(i)+",";
							}
							organizationId=Depid.substring(0,Depid.length()-1);
					}
			}else{
			       
		    	   organizationId=orgtypeselect;
		      
	      	}	
					typeSelect=type[1];
			}
			//int orgId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			String condition = "";
			if(stockDate!=""){
				condition = "and created_date like '%"+stockDate+"%' ";
			}
			if(organizationtype==1)
			{
				if(typeSelect.equals("C")){
				currentStatus="New";
					queryForLugaugeTickets = "select dy.denomination_type_manual, a.denomination_key,a.priority, " +
					" a.opening_number, a.closing_number, a.number_of_tickets, a.number_of_books, a.value, " +
					" a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ,a.current_status  from " +
					" (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, " +
					" max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,priority, " +
					" sum(number_of_books) as number_of_books,sum(value) as value, unit_type,unit_name,ticket_type_manual_id  , tid.current_status " +
					" FROM ticket_inventory_details tid  WHERE tid.status = 'ACTIVE' and tid.current_status='"+currentStatus+"'  and " +
					" tid.unit_name in ("+organizationId.trim()+") " + condition +
					" group by tid.denomination_key, tid.denomination_type_manual_id ) a " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id  " +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=4 " +
					" order by CAST(dy.denomination_type_manual AS UNSIGNED) " ;
				}else if(typeSelect.equals("D")){
					
					organizationtype=2;
					currentStatus="ISSUED";
					queryForLugaugeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=4" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ("+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
					
			}else if(typeSelect.equals("De"))
			{
				organizationtype=3;
				currentStatus="ISSUED";
				queryForLugaugeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
						" max(ti.closing_number) as closing_number," +
						" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
						" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
						" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
						" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
						" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
						" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
						" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=4" +
						" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ("+organizationId.trim()+") " +
						" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
						" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
			}else{
				currentStatus="New";
				 queryForLugaugeTickets = "select dy.denomination_type_manual, a.denomination_key,a.priority, " +
						" a.opening_number, a.closing_number, a.number_of_tickets, a.number_of_books, a.value, " +
						" a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ,a.current_status  from " +
						" (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, " +
						" max(closing_number) as closing_number, sum(number_of_tickets) as number_of_tickets,priority, " +
						" sum(number_of_books) as number_of_books,sum(value) as value, unit_type,unit_name,ticket_type_manual_id  , tid.current_status " +
						" FROM ticket_inventory_details tid  WHERE tid.status = 'ACTIVE' and tid.current_status='"+currentStatus+"'  and " +
						" tid.unit_name in("+organizationId.trim()+") " + condition +
						" group by tid.denomination_key, tid.denomination_type_manual_id ) a " +
						" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id  " +
						" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=4 " +
						" order by CAST(dy.denomination_type_manual AS UNSIGNED) " ;
			}
				
		   }else if(organizationtype==2){
			   if(typeSelect.equals("N")){
				   organizationtype=2;
				   currentStatus="ISSUED";
					queryForLugaugeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=4" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
			   }else if(typeSelect.equals("Y")){
				   organizationtype=3;
				   currentStatus="ISSUED";
					queryForLugaugeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=4" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
					
			   }else{
				       currentStatus="ISSUED";
				       organizationtype=2;
					   queryForLugaugeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
							" max(ti.closing_number) as closing_number," +
							" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
							" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
							" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
							" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
							" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
							" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
							" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=4" +
							" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in ( "+organizationId.trim()+") " +
							" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
							" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
			   }
				
			}else if(organizationtype==3){
				organizationtype=3;
				currentStatus="ISSUED";
				queryForLugaugeTickets="select dy.denomination_type_manual, ti.denomination_key,ti.priority, min(ti.opening_number) as opening_number,  " +
						" max(ti.closing_number) as closing_number," +
						" sum(ti.number_of_tickets) as number_of_tickets, sum(ti.number_of_books) as number_of_books , " +
						" sum(ti.value) as value, ti.ticket_type_manual_id,ti.unit_name,ttm.ticket_type_manual_name ," +
						" tim.current_status from ticket_invoice_master tim inner join ticket_invoice_details tid " +
						" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
						" inner join ticket_inventory_details ti on ti.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
						" inner join denomination_type_manual dy on dy.denomination_type_manual_id= ti.denomination_type_manual_id" +
						" inner join ticket_type_manual ttm on ti.ticket_type_manual_id=ttm.ticket_type_manual_id and ti.ticket_type_manual_id=4" +
						" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id in( "+organizationId.trim()+") " +
						" AND tim.status = 'ACTIVE' AND tim.created_date LIKE  '%"+stockDate+"%' and tim.current_status='"+currentStatus+"'  "  +
						" group by ti.denomination_key, ti.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED); ";
			}
			Query queryObject = session.createSQLQuery(queryForLugaugeTickets)
					.addScalar("denomination_type_manual", Hibernate.STRING).addScalar("denomination_key", Hibernate.STRING)
					.addScalar("opening_number", Hibernate.STRING).addScalar("closing_number", Hibernate.STRING)
					.addScalar("number_of_tickets", Hibernate.STRING).addScalar("number_of_books", Hibernate.STRING).addScalar("current_status", Hibernate.STRING);
			
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
	public List getDepotId1(int parentID,Session session) {
		List list = new ArrayList();
		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID + " and org_type_id=3 order by org_name";
		Query query = session.createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		//HibernateUtil.closeSession();
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
