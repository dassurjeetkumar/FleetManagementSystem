/*  Document   : MenuDao.java
    Created on : May 19, 2014, 11:30:01 AM
    Author     : manojv
*/
package com.trimax.its.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.model.Menu;
import com.trimax.its.model.User;
import com.trimax.its.util.HibernateUtil;

public class MenuDao {

	public List<Menu> getMenuList(User user){
		
		
		
		
		/*String sql="select mpm.page_id,page_name,path,parent_id,level,sequence,mgr.group_id  from menu_user_group mug "+
				   " inner join menu_group_role mgr on mug.group_id=mgr.group_id and mgr.deleted_status=0 and mgr.status='ACTIVE' " +
				   " inner join menu_page_role mpr on mgr.role_id=mpr.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE' "+
				   " inner join menu_page_master mpm on mpr.page_id=mpm.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' " +  
				   " where mug.user_id = "+ user.getUserId() + " and mug.status='ACTIVE'  group by mpm.page_id order by mpm.sequence " ;*/
		/*String sql= " select mpm.page_id,page_name,path,parent_id,level,sequence,mgr.group_id  from menu_user_group mug " +
					" inner join menu_group_master mgm on mug.group_id=mgm.group_id and mgm.deleted_status=0 and mgm.status='ACTIVE' " +
					" inner join menu_group_role mgr on mug.group_id=mgr.group_id and mgr.deleted_status=0 and mgr.status='ACTIVE' " +
					" inner join menu_role_master mrm on mrm.role_id=mgr.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE' " +
					" inner join menu_page_role mpr on mgr.role_id=mpr.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE' " +
					" inner join menu_page_master mpm on mpr.page_id=mpm.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' " + 
					" where mug.user_id = " + user.getUserId() + " and mug.status='ACTIVE'  group by mpm.page_id order by mpm.sequence ";*/
		
		
		
		
		
		

		String sql= " select * from (" +
				//group wise role wise level 0				
				" select mpm.page_id,mpm.page_name,mpm.path,mpm.parent_id,mpm.level,mpm.sequence from menu_user_group mug " +
				" inner join menu_group_master mgm on mug.group_id=mgm.group_id and mgm.deleted_status=0 and mgm.status='ACTIVE' " +
				" inner join menu_group_role mgr on mug.group_id=mgr.group_id and mgr.deleted_status=0 and mgr.status='ACTIVE' " +
				" inner join menu_role_master mrm on mrm.role_id=mgr.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE' " +
				" inner join menu_page_role mpr on mgr.role_id=mpr.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE' " +
				" inner join menu_page_master mpm on mpr.page_id=mpm.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' " +
				" inner join menu_user_master mum on mum.user_id = mug.user_id and mum.deleted_status=0 and mum.status='ACTIVE' " +
				" where mug.user_id = " + user.getUserId() + " and mug.status='ACTIVE'  " +
				
				" union " +

				//group wise role wise level 1

				" select mpm6.page_id,mpm6.page_name,mpm6.path,mpm6.parent_id,mpm6.level,mpm6.sequence from menu_page_master mpm6 " +  
				" where mpm6.page_id in (select mpm.parent_id from menu_user_group mug  " +
				" inner join menu_group_master mgm on mug.group_id=mgm.group_id and mgm.deleted_status=0 and mgm.status='ACTIVE' " + 
				" inner join menu_group_role mgr on mug.group_id=mgr.group_id and mgr.deleted_status=0 and mgr.status='ACTIVE'  " +
				" inner join menu_role_master mrm on mrm.role_id=mgr.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE'  " +
				" inner join menu_page_role mpr on mgr.role_id=mpr.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE'  " +
				" inner join menu_page_master mpm on mpr.page_id=mpm.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' " + 
				" inner join menu_user_master mum on mum.user_id = mug.user_id and mum.deleted_status=0 and mum.status='ACTIVE'  " +
				" where mug.user_id = " + user.getUserId() + " and mug.status='ACTIVE') and mpm6.deleted_status=0 and mpm6.status='ACTIVE' and mpm6.page_type = 'C' " +

				" union " +

				//group wise role wise level 2

				" select mpm7.page_id,mpm7.page_name,mpm7.path,mpm7.parent_id,mpm7.level,mpm7.sequence from menu_page_master mpm7   " +
				" where mpm7.page_id in (select mpm6.parent_id from menu_page_master mpm6   " +
				" where mpm6.page_id in (select mpm.parent_id from menu_user_group mug  " +
				" inner join menu_group_master mgm on mug.group_id=mgm.group_id and mgm.deleted_status=0 and mgm.status='ACTIVE' " + 
				" inner join menu_group_role mgr on mug.group_id=mgr.group_id and mgr.deleted_status=0 and mgr.status='ACTIVE'  " +
				" inner join menu_role_master mrm on mrm.role_id=mgr.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE'  " +
				" inner join menu_page_role mpr on mgr.role_id=mpr.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE'  " +
				" inner join menu_page_master mpm on mpr.page_id=mpm.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' " + 
				" inner join menu_user_master mum on mum.user_id = mug.user_id and mum.deleted_status=0 and mum.status='ACTIVE'  " +
				" where mug.user_id = " + user.getUserId() + " and mug.status='ACTIVE') and mpm6.deleted_status=0 and mpm6.status='ACTIVE' and mpm6.page_type = 'C') " +
				" and mpm7.deleted_status=0 and mpm7.status='ACTIVE' and mpm7.page_type = 'C' " +
				
				" union " +
				//user role wise level 0
				" select mpm.page_id,mpm.page_name,mpm.path,mpm.parent_id,mpm.level,mpm.sequence from menu_user_master mum " +
				" inner join menu_role_master mrm on mrm.role_id = mum.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE' " +
				" inner join menu_page_role mpr on mpr.role_id=mrm.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE' " +
				" inner join menu_page_master mpm on mpm.page_id=mpr.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' " +
				" where mum.user_id = " + user.getUserId() + " and mum.deleted_status=0 and mum.status='ACTIVE'  " +

				" union  " +
				//user role wise level 1
				" select mpm4.page_id,mpm4.page_name,mpm4.path,mpm4.parent_id,mpm4.level,mpm4.sequence from menu_page_master mpm4 " + 
				" where mpm4.page_id in (select mpm.parent_id from menu_user_master mum " +
				" inner join menu_role_master mrm on mrm.role_id = mum.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE' " +
				" inner join menu_page_role mpr on mpr.role_id=mrm.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE' " +
				" inner join menu_page_master mpm on mpm.page_id=mpr.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' " +
				" where mum.user_id = " + user.getUserId() + " and mum.deleted_status=0 and mum.status='ACTIVE' ) and mpm4.deleted_status=0 and mpm4.status='ACTIVE' and mpm4.page_type = 'C' " +

				" union " +
				//user role wise level 2
				" select mpm5.page_id,mpm5.page_name,mpm5.path,mpm5.parent_id,mpm5.level,mpm5.sequence from menu_page_master mpm5 " + 
				" where mpm5.page_id in ( " +
				" select mpm4.parent_id from menu_page_master mpm4 " + 
				" where mpm4.page_id in (select mpm.parent_id from menu_user_master mum " +
				" inner join menu_role_master mrm on mrm.role_id = mum.role_id and mrm.deleted_status=0 and mrm.status='ACTIVE' " +
				" inner join menu_page_role mpr on mpr.role_id=mrm.role_id and mpr.deleted_status=0 and mpr.status='ACTIVE' " +
				" inner join menu_page_master mpm on mpm.page_id=mpr.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' " +
				" where mum.user_id = " + user.getUserId() + " and mum.deleted_status=0 and mum.status='ACTIVE' ) and mpm4.deleted_status=0 and mpm4.status='ACTIVE' and mpm4.page_type = 'C' " +
				" ) and mpm5.deleted_status=0 and mpm5.status='ACTIVE' and mpm5.page_type = 'C' " +

				
				" union " +
				//User page wise level 0
				" select mpm.page_id,mpm.page_name,mpm.path,mpm.parent_id,mpm.level,mpm.sequence from menu_user_master mum " +
				" inner join menu_user_role_page mup on mup.user_id=mum.user_id and mup.deleted_status=0 and mup.status='ACTIVE' " + 
				" inner join menu_page_master mpm on mpm.page_id=mup.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' " +
				" where mum.user_id = " + user.getUserId() + " and mum.deleted_status=0 and mum.status='ACTIVE' " +

				" union " +
				//User page wise level 1
				" select mpm1.page_id,mpm1.page_name,mpm1.path,mpm1.parent_id,mpm1.level,mpm1.sequence from menu_page_master mpm1 " + 
				" where mpm1.page_id in (select mpm.parent_id from menu_user_master mum " +
				" inner join menu_user_role_page mup on mup.user_id=mum.user_id and mup.deleted_status=0 and mup.status='ACTIVE' " + 
				" inner join menu_page_master mpm on mpm.page_id=mup.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C' " + 
				" where mum.user_id = " + user.getUserId() + " and mum.deleted_status=0 and mum.status='ACTIVE') and mpm1.deleted_status=0 and mpm1.status='ACTIVE' and mpm1.page_type = 'C'  " +

				" union " +
				//User page wise level 2
				" select mpm2.page_id,mpm2.page_name,mpm2.path,mpm2.parent_id,mpm2.level,mpm2.sequence from menu_page_master mpm2 " + 
				" where mpm2.page_id in (select mpm1.parent_id from menu_page_master mpm1 " +
				" where mpm1.page_id in (select mpm.parent_id from menu_user_master mum " +
				" inner join menu_user_role_page mup on mup.user_id=mum.user_id and mup.deleted_status=0 and mup.status='ACTIVE' " + 
				" inner join menu_page_master mpm on mpm.page_id=mup.page_id and mpm.deleted_status=0 and mpm.status='ACTIVE' and mpm.page_type = 'C'  " +  
				" where mum.user_id = " + user.getUserId() + " and mum.deleted_status=0 and mum.status='ACTIVE') and mpm1.deleted_status=0 and mpm1.status='ACTIVE' and mpm1.page_type = 'C' ) " +
				" and mpm2.deleted_status=0 and mpm2.status='ACTIVE' and mpm2.page_type = 'C'  " +

				" ) a  group by a.page_id order by a.sequence  ";
		
		
		
		
		

		//System.out.println("sql----"+sql);
		Session session = null;
		List<Menu> userList=new ArrayList<Menu>();
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(sql)
					.addScalar("page_id", Hibernate.INTEGER)
					.addScalar("page_name", Hibernate.STRING)
					.addScalar("path", Hibernate.STRING)
					//.addScalar("group_id", Hibernate.INTEGER)
					.addScalar("parent_id", Hibernate.INTEGER)
					.addScalar("level", Hibernate.INTEGER)
					.addScalar("sequence", Hibernate.INTEGER)
					;
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> list = query.list();
			Menu menu =null;
			Object[] obj;
			if(list.size()>0){
			for (int i=0;i<list.size();i++) {		
				menu=new Menu(); 
				Map<String, Object> rs = list.get(i);
				menu.setPageId((Integer)rs.get("page_id"));
				menu.setPageName((String)rs.get("page_name"));
				menu.setPath((String)rs.get("path"));
				//menu.setGroupId((Integer)rs.get("group_id"));
				menu.setParentId((Integer)rs.get("parent_id"));
				menu.setLevel((Integer)rs.get("level"));
				menu.setSequence((Integer)rs.get("sequence"));
				userList.add(menu);
//System.out.println(menu.getPageId()+"|"+menu.getPageName()+"|"+menu.getPath()+"|"+menu.getGroupId());
			}
			}

		} catch (Exception e) {
			System.out.println(e);
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in UserDao", e);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);// save error log to db
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
		return userList;
	}
}

