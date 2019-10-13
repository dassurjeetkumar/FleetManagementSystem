package com.trimax.its.utility.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.AccessRights;

public class AccessRightsDao {
	
	public static void main(String args[]){
		AccessRightsDao ard=new AccessRightsDao();
		int userid=1;
		String pagename="roadTyp.action";
	   HashMap details=(HashMap)ard.getAccessRights(userid, pagename);
	   String access=details.get("ACCESS").toString();
//	  System.out.println("access-------"+access);
	   
	   String error=details.get("ERROR").toString();
	   System.out.println("error-------"+error);
//	   System.out.println("details------"+details);
		
	}
	
	public AccessRights accessRightsdetails(int userid,String action)
	{
		AccessRights accessrights=new AccessRights();
		  HashMap details=(HashMap)getAccessRights(userid, action);
		  accessrights.setACCESS(details.get("ACCESS").toString());
		  accessrights.setERROR(details.get("ERROR").toString());
		  accessrights.setCREATE(details.get("CREATE").toString());
		  accessrights.setDELETE(details.get("DELETE").toString());
		  accessrights.setEDIT(details.get("EDIT").toString());
		  accessrights.setREAD(details.get("READ").toString());
		  accessrights.setVIEWDELETE(details.get("VIEWDELETE").toString());
		return accessrights;
	}
	
	
	public HashMap getAccessRights(int userid,String path){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HashMap accessRightsDetails=new HashMap();
				try{
		/*String query1=" select IFNULL(sum(readaccess),'NA') readaccess,IFNULL(sum(createaccess),'NA')createaccess ,IFNULL(sum(editaccess),'NA') editaccess,IFNULL(sum(deleteaccess),'NA') deleteaccess ,IFNULL(sum(viewdelete),'NA') viewdelete "+
					  " from (select  murp.readaccess ,murp.createaccess,murp.editaccess,murp.deleteaccess,murp.viewdelete " +
					  " from menu_user_role_page murp "+ 
					  " INNER JOIN menu_page_master mpm ON murp.page_id=mpm.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' "+
					  " where murp.user_id="+userid+" and mpm.path='"+ path +"' and " +
					  " murp.deleted_status=0 and murp.status='ACTIVE' "+
					  " union " +

					" select mpr.readaccess ,mpr.createaccess,mpr.editaccess,mpr.deleteaccess,mpr.viewdelete "+
					" from menu_page_role mpr " +
					" INNER JOIN menu_role_master mrm ON mpr.role_id=mrm.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE' " +
					" INNER JOIN menu_user_master mum ON mum.role_id= mrm.role_id and mum.deleted_status=0 and mum.status='ACTIVE' " + 
					" INNER JOIN menu_page_master mpm ON mpr.page_id=mpm.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' " +
					" where mum.user_id="+userid+" and mpm.path=' " + path +"' and mpr.deleted_status=0 and mpr.status='ACTIVE' "+
					" union " +
					" select mpr.readaccess  ,mpr.createaccess,mpr.editaccess,mpr.deleteaccess,mpr.viewdelete " +
					" from menu_page_role mpr " +
					" INNER JOIN menu_role_master mrm ON mpr.role_id=mrm.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE' " + 
					" INNER JOIN menu_user_master mum ON mum.role_id= mrm.role_id and mum.deleted_status=0 and mum.status='ACTIVE' " + 
					" INNER JOIN menu_page_master mpm ON mpr.page_id=mpm.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' " +
					" INNER JOIN menu_user_group mug ON mum.user_id=mug.user_id  and  mug.status='ACTIVE' " +
					" INNER JOIN menu_group_role mgr ON mug.group_id=mgr.group_id and mgr.deleted_status=0 and mgr.status='ACTIVE' "+
					" where mum.user_id="+userid+" and mpm.path='"+ path +"' and mpr.deleted_status=0 and mpr.status='ACTIVE') a  ";*/
		
		
		
		String query1=" select IFNULL(sum(readaccess),'NA') readaccess,IFNULL(sum(createaccess),'NA')createaccess ,IFNULL(sum(editaccess),'NA') editaccess,IFNULL(sum(deleteaccess),'NA') deleteaccess ,IFNULL(sum(viewdelete),'NA') viewdelete "+
		  " from ( "+
		//Group wise
		" select mpr.readaccess  ,mpr.createaccess,mpr.editaccess,mpr.deleteaccess,mpr.viewdelete from menu_user_group mug " + 
		" inner join menu_group_master mgm on mug.group_id=mgm.group_id and mgm.deleted_status=0 and mgm.status='ACTIVE'  " +
		" inner join menu_group_role mgr on mug.group_id=mgr.group_id and mgr.deleted_status=0 and mgr.status='ACTIVE'  " +
		" inner join menu_role_master mrm on mrm.role_id=mgr.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE'  " +
		" inner join menu_page_role mpr on mgr.role_id=mpr.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE'  " +
		" inner join menu_page_master mpm on mpr.page_id=mpm.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' and mpm.path='"+ path +"'  " +
		" inner join menu_user_master mum on mum.user_id = mug.user_id and mum.deleted_status=0 and mum.status='ACTIVE'  " +
		" where mug.user_id ="+userid+" and mug.status='ACTIVE'  " +
		
		" union " +
		//User Role wise
		" select mpr.readaccess  ,mpr.createaccess,mpr.editaccess,mpr.deleteaccess,mpr.viewdelete from menu_user_master mum " + 
		" inner join menu_role_master mrm on mrm.role_id = mum.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE'  " +
		" inner join menu_page_role mpr on mpr.role_id=mrm.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE'  " +
		" inner join menu_page_master mpm on mpm.page_id=mpr.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C'  and mpm.path='"+ path +"' " +
		" where mum.user_id ="+userid+" and mum.deleted_status=0 and mum.status='ACTIVE'   " +
		
		" union " +
		//User Page wise
		" select mup.readaccess  ,mup.createaccess,mup.editaccess,mup.deleteaccess,mup.viewdelete from menu_user_master mum " + 
		" inner join menu_user_role_page mup on mup.user_id=mum.user_id and mup.deleted_status=0 and mup.status='ACTIVE'   " +
		" inner join menu_page_master mpm on mpm.page_id=mup.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' and mpm.path='"+ path +"' " +
		" where mum.user_id ="+userid+" and mum.deleted_status=0 and mum.status='ACTIVE') a  ";
		
		

		
		Query query =session.createSQLQuery(query1).addScalar("readaccess", Hibernate.STRING).addScalar("createaccess", Hibernate.STRING).addScalar("editaccess", Hibernate.STRING).addScalar("deleteaccess", Hibernate.STRING).addScalar("viewdelete", Hibernate.STRING);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if(aliasToValueMapList.size()>0)
		{
			//accessRightsDetails.add("ACCESS","Y");
			
			
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			String  readaccess=rs.get("readaccess").toString();
//			System.out.println("readaccess-------"+readaccess);
			if(readaccess.equalsIgnoreCase("NA"))
			{
				accessRightsDetails.put("ACCESS","N");
				accessRightsDetails.put("ERROR", "User hava not any rights");
				accessRightsDetails.put("READ", "N");
				accessRightsDetails.put("CREATE", "N");
				accessRightsDetails.put("EDIT", "N");
				accessRightsDetails.put("DELETE", "N");
				accessRightsDetails.put("VIEWDELETE", "N");
				
			}else{
				accessRightsDetails.put("ACCESS","Y");
				int  readaccessdetails=Integer.parseInt(rs.get("readaccess").toString());
			if(readaccessdetails>0){
				accessRightsDetails.put("READ", "Y");
			}else{
				accessRightsDetails.put("READ", "N");
			}
			int createaccess=Integer.parseInt(rs.get("createaccess").toString());
			if(createaccess>0){
				accessRightsDetails.put("CREATE", "Y");
			}else{
				accessRightsDetails.put("CREATE", "N");
			}
			int editaccess=Integer.parseInt(rs.get("editaccess").toString());
			if(editaccess>0){
				accessRightsDetails.put("EDIT", "Y");
			}else{
				accessRightsDetails.put("EDIT", "N");
			}
			int deleteaccess=Integer.parseInt(rs.get("deleteaccess").toString());
			if(deleteaccess>0){
				accessRightsDetails.put("DELETE", "Y");
			}else{
				accessRightsDetails.put("DELETE", "N");
			}
			int viewdelete=Integer.parseInt(rs.get("viewdelete").toString());
			if(viewdelete>0){
				accessRightsDetails.put("VIEWDELETE", "Y");
			}else{
				accessRightsDetails.put("VIEWDELETE", "N");
			}
			accessRightsDetails.put("ERROR", "");
			
			}
			
			
		}
		}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
				finally {
					if (session != null && session.isOpen()) {
						session.close();
					}
				}
				return accessRightsDetails;
	}
	public String UserStatus(int id){
		String status="";
		try{
		String qry = " select status  from menu_user_master where  user_id="
				+ id + " ";
		Query query = HibernateUtil.getSession("hibernate.cfg.xml")
				.createSQLQuery(qry);

		//System.out.println("QUery Size===>" + query.list().size());
		if (query.list().size() > 0) {
			status=query.uniqueResult().toString();
//			System.out.println("Hii staus"+status);
		}}
		catch(Exception e){
			
		}finally{
			HibernateUtil.closeSession();
		}
		
		
		return  status;
	}
 public int getroleid(int id){

		String status="";
		try{
		String qry = " select role_id from menu_user_master "+
                     "where user_id="+id+" and deleted_status=0 and status='Active'";
				//System.out.println("roll id qry---"+qry);
		Query query = HibernateUtil.getSession("hibernate.cfg.xml")
				.createSQLQuery(qry);

		//System.out.println("QUery Size===>" + query.list().size());
		if (query.list().size() > 0) {
			status=query.uniqueResult().toString();
//			System.out.println("Hii staus"+status);
		}
		
		
		}
		catch(Exception e){
			
		}finally{
			HibernateUtil.closeSession();
		}
		
		
		return  Integer.parseInt(status);
	 
 }
}
