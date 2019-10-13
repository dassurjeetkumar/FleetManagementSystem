package com.trimax.its.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;


import com.trimax.its.model.DependentTableCheck;
import com.trimax.its.util.HibernateUtil;

public class DependencyChecker {

	List<DependentTableCheck> getDependentTableDetails(Session session,String tableName){
		List<DependentTableCheck> list=null;
		try{
		String sql="select dependent_table_id dependentTableId,master_table_name masterTableName," +
				"master_table_col masterTableCol,dependent_table_name dependentTableName," +
				"dependent_table_col dependentTableCol,dependent_table_primary_col dependentTablePrimaryCol," +
				"user_msg userMsg,user_msg2 userMsg2,status,allow_delete allowDelete,criteria" +
				" from dependent_table_check where master_table_name='"+tableName+"' and status='ACTIVE'";
		
		Query query = session.createSQLQuery(sql)
				.addScalar("dependentTableId", Hibernate.INTEGER)
				.addScalar("masterTableName", Hibernate.STRING)
				.addScalar("masterTableCol", Hibernate.STRING)
				.addScalar("dependentTableName", Hibernate.STRING)
				.addScalar("dependentTableCol", Hibernate.STRING)
				.addScalar("dependentTablePrimaryCol", Hibernate.STRING)
				.addScalar("criteria", Hibernate.STRING)
				.addScalar("userMsg", Hibernate.STRING)
				.addScalar("userMsg2", Hibernate.STRING)
				.addScalar("status", Hibernate.STRING)
				.addScalar("allowDelete", Hibernate.STRING)
				;
		query.setResultTransformer(Transformers.aliasToBean(DependentTableCheck.class));
		list = query.list();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return list;
	}

	/*Created by Ashwini*/
List<DependentTableCheck> getDependentTableDetailsForOrgChart(Session session,String tableName,String usermsg){
		
		String sql="select dependent_table_id dependentTableId,master_table_name masterTableName," +
				"master_table_col masterTableCol,dependent_table_name dependentTableName," +
				"dependent_table_col dependentTableCol,dependent_table_primary_col dependentTablePrimaryCol," +
				"user_msg userMsg,user_msg2 userMsg2,status,allow_delete allowDelete,criteria" +
				" from dependent_table_check where master_table_name='"+tableName+"' and status='ACTIVE' and user_msg ='"+ usermsg+"'";
		
		Query query = session.createSQLQuery(sql)
				.addScalar("dependentTableId", Hibernate.INTEGER)
				.addScalar("masterTableName", Hibernate.STRING)
				.addScalar("masterTableCol", Hibernate.STRING)
				.addScalar("dependentTableName", Hibernate.STRING)
				.addScalar("dependentTableCol", Hibernate.STRING)
				.addScalar("dependentTablePrimaryCol", Hibernate.STRING)
				.addScalar("criteria", Hibernate.STRING)
				.addScalar("userMsg", Hibernate.STRING)
				.addScalar("userMsg2", Hibernate.STRING)
				.addScalar("status", Hibernate.STRING)
				.addScalar("allowDelete", Hibernate.STRING)
				;
		query.setResultTransformer(Transformers.aliasToBean(DependentTableCheck.class));
		List<DependentTableCheck> list = query.list();
			 
		return list;
	}
	
/*Created by Ashwini*/
public String checkDependentEntitiesForOrgChart(Session session,String masterTableName,int recordId,String usermsg){
	String recordsId="";
	StringBuffer userMsg=new StringBuffer();
	
	try{

		List<DependentTableCheck> list=getDependentTableDetailsForOrgChart(session,masterTableName,usermsg);
		System.out.println("list-------"+list);
		if(list.size()>0)
		{
		//check for each records in table dependent_table_check
		for(int i=0;i<list.size();i++){
		 //check for allow_delete
		 if(list.get(i).getAllowDelete().equalsIgnoreCase("Y")){
			try{
			  recordsId=checkDependentTableForMasterUsage(session,list.get(i).getDependentTableName(),
					  list.get(i).getDependentTableCol(),list.get(i).getDependentTablePrimaryCol(),
					  recordId,list.get(i).getCriteria());
			 
			  if(!recordsId.equalsIgnoreCase("0")){
				  //user message
				  userMsg.append(formatUserMsg(list.get(i).getUserMsg(),recordId,list.get(i).getUserMsg2(),recordsId));
				     
			  }
			}catch(Exception e){
				userMsg.append("error");
				e.printStackTrace();	
			}
			  
		 }else{
				userMsg.setLength(0);
				userMsg.append(list.get(i).getUserMsg());	
				
				return userMsg.toString();
		 }
		}
		}else{
			userMsg.append("");
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return userMsg.toString();
}




	
	String checkDependentTableForMasterUsage(Session session,String dependentTblName,String dependentTblCol,String dependentTblRecordIdCol,int recordId,String criteria){
		String recordIdList="";
		
		//try{
			String sql="select ifnull(group_concat("+dependentTblRecordIdCol+"),0) "+dependentTblRecordIdCol+" from "+dependentTblName
					+" where "+dependentTblCol+"="+recordId+" "+criteria;
			
			System.out.println("sql--------"+sql);
			Query query = session.createSQLQuery(sql).addScalar(dependentTblRecordIdCol,Hibernate.STRING);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> list = query.list();
			
			//return comma separated records id 
			if(list.size()>0){
				  Map<String, Object> rs = list.get(0);
				  recordIdList=rs.get(dependentTblRecordIdCol).toString();
			}
		/*}catch(Exception e){
			e.printStackTrace();
		}*/
		
		return recordIdList;
	}
	
	
	//method with implicit session object
	/*public String checkDependentEntities(String masterTableName,int recordId){
		String recordsId="";
		StringBuffer userMsg=new StringBuffer();
		Session session=null;
		
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			List<DependentTableCheck> list=getDependentTableDetails(session,masterTableName);
			
			//check for each records in table dependent_table_check
			for(int i=0;i<list.size();i++){
			 //check for allow_delete
			 if(list.get(i).getAllowDelete().equalsIgnoreCase("Y")){
				try{
				  recordsId=checkDependentTableForMasterUsage(session,list.get(i).getDependentTableName(),
						  list.get(i).getDependentTableCol(),list.get(i).getDependentTablePrimaryCol(),
						  recordId,list.get(i).getCriteria());
				 System.out.println("recordsId---"+recordsId);
				  if(!recordsId.equalsIgnoreCase("0")){
					  //user message
					  userMsg.append(formatUserMsg(list.get(i).getUserMsg(),recordId,list.get(i).getUserMsg2(),recordsId));
					     
				  }
				}catch(Exception e){
					userMsg.append("error");
					e.printStackTrace();	
				}
				  
			 }else{
					userMsg.setLength(0);
					userMsg.append(list.get(i).getUserMsg());	
					
					return userMsg.toString();
			 }
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		
		return userMsg.toString();
	}*/
	
	
	//method with explicit session object
	public String checkDependentEntities(Session session,String masterTableName,int recordId){
		String recordsId="";
		StringBuffer userMsg=new StringBuffer();
		
		try{

			List<DependentTableCheck> list=getDependentTableDetails(session,masterTableName);
			System.out.println("list-------"+list);
			if(list.size()>0)
			{
			//check for each records in table dependent_table_check
			for(int i=0;i<list.size();i++){
			 //check for allow_delete
			 if(list.get(i).getAllowDelete().equalsIgnoreCase("Y")){
				try{
				  recordsId=checkDependentTableForMasterUsage(session,list.get(i).getDependentTableName(),
						  list.get(i).getDependentTableCol(),list.get(i).getDependentTablePrimaryCol(),
						  recordId,list.get(i).getCriteria());
				 
				  if(!recordsId.equalsIgnoreCase("0")){
					  //user message
					  userMsg.append(formatUserMsg(list.get(i).getUserMsg(),recordId,list.get(i).getUserMsg2(),recordsId));
					     
				  }
				}catch(Exception e){
					userMsg.append("error");
					e.printStackTrace();	
				}
				  
			 }else{
					userMsg.setLength(0);
					userMsg.append(list.get(i).getUserMsg());	
					
					return userMsg.toString();
			 }
			}
			}else{
				userMsg.append("");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return userMsg.toString();
	}
	
	String formatUserMsg(String msgPart1,int recordId,String msgPart2,String recordsId){
		String recordIds=getUniqueRecordId(recordsId);
		String[] part2MsgArr=recordIds.split(",");
		
		if(part2MsgArr.length>11){
			recordsId="";
		 for(int y=0;y<10;y++){
			recordsId+=part2MsgArr[y]+",";
		 }
		 recordsId=recordsId+"...";
		}
		
		String msg=msgPart1+" "+recordId+" "+msgPart2+" "+recordsId+"\n\r"+"@";
		
		return msg;
	}
	
	
	
	
	/*created by Ashwini*/
	public String getStatus(int id,String tablename){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String status="error";
		try{
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,tablename,id);
		}catch(Exception e){
			status="error:";
			e.printStackTrace();
		}	finally{
			if (session != null) {
				session.close();
			}
		}
		return status; 
	}
	
	public String getStatus1(int id,String tablename){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String status="error";
		try{
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,tablename,id);
		}catch(Exception e){
			status="error:";
			e.printStackTrace();
		}	finally{
			if (session != null) {
				session.close();
			}
		}
		return status; 
	}
	
	/*created by Ashwini*/
	public String getStatusForOrgChart(int id,String tablename,String usermsg){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String status="error";
		try{
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntitiesForOrgChart(session,tablename,id,usermsg);
		}catch(Exception e){
			status="error:";
			e.printStackTrace();
		}	finally{
			if (session != null) {
				session.close();
			}
		}
		return status; 
		
	}
	
	public String getUniqueRecordId(String recordId){
		String uniqueRecordId="";
		String recordIds[]=recordId.split(",");
		Set<Integer> uniqueSetId=new HashSet<Integer>();
		for(int i=0;i<recordIds.length;i++){
			uniqueSetId.add(Integer.parseInt(recordIds[i]));
		}
		java.util.Iterator iter =uniqueSetId.iterator();
		while (iter.hasNext()) {
			uniqueRecordId += (Integer) ( iter).next() + " ,";
			// System.out.println(iter.next());
		}
		return uniqueRecordId;
		
	}
	
}
