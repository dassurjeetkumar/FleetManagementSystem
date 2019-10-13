package com.trimax.its.inventory.dao;


import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

public class DeleteStockDAO {

	Common common = new Common();
	
	@SuppressWarnings("finally")
	public String deleteStock(){
		System.out.println("we are in deleteStock()....Dao..........");
		Session session = null;
		String normalKeyArray = " ";
		String passKeyArray = " ";
		String luggageKeyArray = " ";
		String tsheetKeyArray=" ";
		try{
			String data = ServletActionContext.getRequest().getParameter("tick");
			int userId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("userid");
			if(data!=null && data.length()>0){
				String tickets[] = data.split(",");
				session = HibernateUtil.getSession("");
				session.beginTransaction();
				for(int i=0;i<tickets.length;i++){
					String tokens[] = tickets[i].split("-");
					
					String queryToUpdateInventoryRecordsStatus = "update ticket_inventory_details set  current_status='INACTIVE',updated_by='"+userId+"',updated_date=now()	" +
						" where  `ticket_type_manual_id` = '"+tokens[1]+"'  and denomination_key ='"+tokens[0]+"'  and current_status='New' " +
						" and status = 'ACTIVE' and ((opening_number between '"+tokens[2]+"' and '"+tokens[4]+"') or (closing_number between '"+tokens[2]+"' and '"+tokens[4]+"'))";
					Query queryObject = session.createSQLQuery(queryToUpdateInventoryRecordsStatus);
					queryObject.executeUpdate();
					if(tokens[1].equals("1")){
						if(normalKeyArray.length()>1){
							normalKeyArray += ","+tokens[0] ;
						}else{
							normalKeyArray += tokens[0] ;
						}
					}else if(tokens[1].equals("2") || tokens[1].equals("3")){
						if(passKeyArray.length()>1){
							passKeyArray += ","+tokens[0] ;
						}else{
							passKeyArray += tokens[0] ;
						}
					}else if(tokens[1].equals("4")){
						if(luggageKeyArray.length()>1){
							luggageKeyArray += ","+tokens[0] ;
						}else{
							luggageKeyArray += tokens[0] ;
						}
					}else if(tokens[1].equals("5")){
						if(tsheetKeyArray.length()>1){
							tsheetKeyArray += ","+tokens[0] ;
						}else{
							tsheetKeyArray += tokens[0] ;
						}
					}
				}
				session.getTransaction().commit();
			}
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
			normalKeyArray = " ";
			passKeyArray = " ";
			luggageKeyArray =" ";
			tsheetKeyArray=" ";
		}finally{
			if(session != null){
				session.close();
			}
			return normalKeyArray+"-"+passKeyArray+"-"+luggageKeyArray+"-"+tsheetKeyArray;
		}
	}
}
