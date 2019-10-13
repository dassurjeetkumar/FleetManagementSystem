package com.trimax.its.usermanagement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.ticketing.dao.DenominationTypeDao;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.usermanagement.model.DesignationType;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.usermanagement.model.MenuUserRolePage;
import com.trimax.its.usermanagement.model.UserType;

import com.trimax.its.usermanagement.model.UserDetails;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.utility.model.Group_Master;
import com.trimax.its.utility.model.MenuPageRole;
import com.trimax.its.utility.model.Role;
import com.trimax.its.vehicle.model.BreakDownTypeDetails;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.common.EncryptionDecryption;
import com.trimax.its.device.model.Device;
import com.trimax.its.model.User;

public class UserDetailDAO {
	public Map<Integer, String> getDesignationList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from DesignationType where status='Active' and deleted_status=0 order by designation_type_name");

		try {
			List<DesignationType> list = query.list();

			for (DesignationType designation : list) {
				// resultMap.put(vendor.getId(),vendor.getVendor_name());
				resultMap.put(designation.getDesignation_type_id(),
						designation.getDesignation_type_name());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;

	}

	public Map<Integer, String> getUserTypeList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from UserType order by user_type_name");

		try {
			List<UserType> list = query.list();

			for (UserType usertype : list) {
				// resultMap.put(vendor.getId(),vendor.getVendor_name());
				// resultMap.put(designation.getDesignation_type_id(),
				// designation.getDesignation_type_name());
				resultMap.put(usertype.getUser_type_id(),
						usertype.getUser_type_name());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;

	}

	public Map<Integer, String> getOrgnizationList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrgType where status = 'ACTIVE' order by orgType");

		try {

			List<OrgType> list = query.list();
			for (OrgType orgnization : list) {
				resultMap.put(orgnization.getOrg_type_id(),
						orgnization.getOrgType());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;

	}

	public Map<Integer, String> getRole() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		// Query query =
		// session.createQuery("from Role where status='Active' and deleted_status= 0 order by role_name");
		Criteria criteria = session.createCriteria(Role.class);
		criteria.add(Restrictions.ilike("status", "Active"));
		criteria.add(Restrictions.eq("deleted_status", 0));
		criteria.addOrder(Order.asc("role_name"));

		try {
			List<Role> list = criteria.list();

			for (Role role : list) {
				resultMap.put(role.getRole_id(), role.getRole_name());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;

	}

	public Map<Integer, String> getGroup() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from Group_Master where status='ACTIVE' and deleted_status=0 order by group_name ");

		try {
			List<Group_Master> list = query.list();

			for (Group_Master groupmaster : list) {
				// resultMap.put(role.getRole_id(), role.getRole_name());
				resultMap.put(groupmaster.getGroup_id(),
						groupmaster.getGroup_name());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;

	}

	public Map<Integer, String> getEmployee() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createSQLQuery("select EMPLOYEE_ID,EMPLOYEE_NAME,PF from employee where EMPLOYEE_ID not in(select emp_id from menu_user_master where deleted_status=0) and status='ACTIVE' order by EMPLOYEE_NAME");

		try {

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);

				int key = Integer.parseInt(rs.get("EMPLOYEE_ID").toString());
				String employeename = rs.get("EMPLOYEE_NAME").toString();
				String pf = rs.get("PF").toString();
				String value = employeename + "(" + pf + ")";
				// resultMap.put(key, rs.get("EMPLOYEE_NAME").toString());
				resultMap.put(key, value);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;

	}

	public Map<Integer, String> getEmployeeListForEdit() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createSQLQuery("select EMPLOYEE_ID,EMPLOYEE_NAME,PF from employee where status='ACTIVE' ");

		try {

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);

				/*
				 * int key=Integer.parseInt(rs.get("EMPLOYEE_ID").toString());
				 * String value= rs.get("EMPLOYEE_NAME").toString();
				 * //resultMap.put(key, rs.get("EMPLOYEE_NAME").toString());
				 * resultMap.put(key, value);
				 */

				int key = Integer.parseInt(rs.get("EMPLOYEE_ID").toString());
				String employeename = rs.get("EMPLOYEE_NAME").toString();
				String pf = rs.get("PF").toString();
				String value = employeename + "(" + pf + ")";
				// resultMap.put(key, rs.get("EMPLOYEE_NAME").toString());
				resultMap.put(key, value);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;

	}

	public int addUser(UserDetails user, int userid, int workingdesignationtype) {
		int i = 0;
		EncryptionDecryption encry = new EncryptionDecryption();
		Transaction tx = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String status = "";
		try {
			user.setDesignation_type_id(workingdesignationtype);
			// System.out.println("test----------------"+user.getDesignation_type_id());
			tx = session.beginTransaction();
			// Here we get employee status add to the user
			// if((user.getUser_type().equalsIgnoreCase("Employee"))){
			String usertype = getUserTypename(user.getUsertypedetails()
					.getUser_type_id());

			// System.out.println("usertype---------"+usertype);
			if (usertype.equalsIgnoreCase("Employee")) {
				Query query1 = session.createSQLQuery(
						"select STATUS as STATUS from employee where EMPLOYEE_ID = "
								+ user.getEmpid()).addScalar("STATUS",
						Hibernate.STRING);
				List list = query1.list();
				status = list.get(0).toString();
				// System.out.println("Employee Status---------"+status);

			} else {
				status = "ACTIVE";
			}

			// Here we check uniqueness
			/*
			 * String query =
			 * " select user_id from menu_user_master where userloginname ='"
			 * +user.getUserloginname()+"' and  deleted_status=0 "; List
			 * list1=session.createSQLQuery(query).list();
			 * 
			 * //System.out.println("list1-----------"+list1.size());
			 */

			/*
			 * Query query2 = session.createSQLQuery(
			 * "select user_id from menu_user_master where userloginname ='"
			 * +user.getUserloginname()+"' and  deleted_status='0';"); List
			 * list1 = query2.list(); int idInteger =
			 * Integer.parseInt(list1.get(0).toString());
			 * 
			 * //System.out.println("\n \t Mahesh.......... idInteger : "+idInteger
			 * ); String id=list1.get(0).toString();
			 * //System.out.println("id---------"+id);
			 * //System.out.println("list1 size--------"+list1);
			 */

			Query query = session
					.createSQLQuery(
							"select count(*) as count from menu_user_master where deleted_status = 0 and  userloginname ='"
									+ user.getUserloginname() + "'").addScalar(
							"count", Hibernate.INTEGER);
			List<Integer> list = query.list();
			int cnt = list.get(0);
			// System.out.println("cnt--------"+cnt);

			// List list=null;
			if (cnt == 0) {

				// if((user.getUser_type().equalsIgnoreCase("Director"))||(user.getUser_type().equalsIgnoreCase("Guest")))
				// if(user.getUsertypedetails().getUser_type_name().equalsIgnoreCase("Director")
				// ||(user.getUsertypedetails().getUser_type_name().equalsIgnoreCase("Guest")))
				if (usertype.equalsIgnoreCase("Director")
						|| (usertype.equalsIgnoreCase("Guest"))) {
					// System.out.println("testing----------------");
					user.setEmpid(0);
				}

				String encryptedpassward = encry.encrypt(user.getPassword());
				// System.out.println("encryptedpassward------"+encryptedpassward);
				String user_org = getUserOrg(user.getUsertypedetails()
						.getUser_type_id());
				// System.out.println("user_org-------"+user_org);

				user.setPassword(encryptedpassward);
				user.setCreated_by(userid);
				user.setCreated_date(new java.util.Date());
				user.setStatus(status.toUpperCase());
				user.setAccess_status(user_org);

				i = (Integer) session.save(user);
			} else {
				i = -1;
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return i;

	}

	/*
	 * public Device getEditedDevice(int parseInt) { // TODO Auto-generated
	 * method stub Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml"); Device devicetype =
	 * (Device) session.get(Device.class, new Integer( parseInt)); //
	 * //System.out.println("--------->>>" + //
	 * fareChart.getFarechart_master_id() // + "\t" + fareChart.getRoute_id());
	 * return devicetype; }
	 */
	public UserDetails getEditUserDetails(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		UserDetails user1 = (UserDetails) session.get(UserDetails.class,
				new Integer(id));

		return user1;
	}

	public String getDesignationTypename(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String designatationame = "";
		if (id != 0) {
			Query query = session
					.createSQLQuery(
							"select designation_type_name as designation_type_name from designation_type where designation_type_id ="
									+ id).addScalar("designation_type_name",
							Hibernate.STRING);
			List<String> list = query.list();
			designatationame = list.get(0);
			// System.out.println("designatationame-------"+designatationame);
		} else {
			designatationame = " ";
		}
		return designatationame;
	}

	/*
	 * public String getDesignationTypename(int id){ Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml"); String designatationame
	 * =""; if(id!=0){ Query query = session.createSQLQuery(
	 * "select designation_type_name as designation_type_name from designation_type where designation_type_id ="
	 * +id).addScalar("designation_type_name", Hibernate.STRING); List<String>
	 * list = query.list(); designatationame = list.get(0);
	 * //System.out.println("designatationame-------"+designatationame); }else{
	 * designatationame=" "; } return designatationame; }
	 */

	public String getUserTypename(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String usertypename = "";
		if (id != 0) {
			Query query = session
					.createSQLQuery(
							"select user_type_name as user_type_name from menu_user_type where user_type_id ="
									+ id).addScalar("user_type_name",
							Hibernate.STRING);
			List<String> list = query.list();
			usertypename = list.get(0);
			// System.out.println("usertypename-------"+usertypename);
		} else {
			usertypename = " ";
		}
		return usertypename;
	}

	public String getUserOrg(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String userorg = "";
		if (id != 0) {
			Query query = session.createSQLQuery(
					"select user_org as user_org from menu_user_type where user_type_id ="
							+ id).addScalar("user_org", Hibernate.STRING);
			List<String> list = query.list();
			userorg = list.get(0);
			// System.out.println("userorg-------"+userorg);
		} else {
			userorg = " ";
		}
		return userorg;
	}

	public String getRolename(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String rolename = "";
		if (id != 0) {
			Query query = session.createSQLQuery(
					"select role_name as role_name from  menu_role_master where role_id ="
							+ id).addScalar("role_name", Hibernate.STRING);
			List<String> list = query.list();
			rolename = list.get(0);
			// System.out.println("rolename-------"+rolename);
		} else {
			rolename = " ";
		}
		return rolename;
	}

	public String getActiveRolename(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String rolename = "";
		if (id != 0) {
			Query query = session.createSQLQuery(
					"Select role_name as role_name from  menu_role_master where role_id= "
							+ id
							+ " and deleted_status=0 and status= 'ACTIVE' ")
					.addScalar("role_name", Hibernate.STRING);
			List<String> list = query.list();
			if (list.size() > 0) {
				rolename = list.get(0);
				// System.out.println("rolename-------"+rolename);
			} else {
				rolename = " ";
			}
		} else {
			rolename = " ";
		}
		return rolename;
	}

	public int updateUser(int id, UserDetails user) {
		boolean flag = false;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		try {

			// BreakDownTypeDetails
			// breakdowntype=(BreakDownTypeDetails)session.load(BreakDownTypeDetails.class,new
			// Integer(id));
			// String
			// query=" from BreakDownTypeDetails where breakdown_category= '"+breakdowntype.getBreakdown_category()+"' and breakdown_system_type= '"+breakdowntype.getBreakdown_system_type()
			// +"' and breakdown_system_sub_type= '"+
			// breakdowntype.getBreakdown_system_sub_type()+"' and deleted_status=0 and breakdown_type_Id NOT IN("+breakdowntype.getBreakdown_type_Id()+")";
			// String query =
			// "select user_id from user_master where userloginname ='"+user.getUserloginname()+"'and user_id NOT IN("+
			// id +")";
			// List list=session.createQuery(query).list();
			// ///System.out.println("size---update----"+list.size());
			/*
			 * if(list.size()==0) {
			 */
			UserDetails user1 = (UserDetails) session.load(UserDetails.class,
					new Integer(id));
			HttpServletRequest request = ServletActionContext.getRequest();
			User user2 = (User) request.getSession().getAttribute("user");
			EncryptionDecryption encry = new EncryptionDecryption();
			// System.out.println("test----"+user.getPassword());
			// user1.setDesignationtypeid(user.getDesignationtypeid());
			user1.setUsertypedetails(user.getUsertypedetails());
			user1.setRole(user.getRole());
			user1.setUsergroupid(user.getUsergroupid());
			user1.setPassword(encry.encrypt(user.getPassword()));
			user1.setOrgnizationType(user.getOrgnizationType());
			user1.setOrgchartdetails(user.getOrgchartdetails());
			user1.setEmailid(user.getEmailid());
			user1.setContactno(user.getContactno());
			user1.setNote(user.getNote());
			user1.setStatus(user.getStatus());
			user1.setUpdated_by(user2.getUserId());
			user1.setUpdated_date(new java.util.Date());
			user1.setAgent_id(user.getAgent_id());
			session.update(user1);
			session.getTransaction().commit();
			i = 1;
			// }/*else{
			// i=-1;
			// }*/
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return i;
	}

	/*
	 * public String getStatus(int id){ Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml");
	 * session.beginTransaction(); String status="error"; try{ DependencyChecker
	 * dc=new DependencyChecker();
	 * status=dc.checkDependentEntities(session,"menu_user_master",id);
	 * }catch(Exception e){ status="error:"; e.printStackTrace(); } finally{ if
	 * (session != null) { session.close(); } } return status; }
	 */

	public boolean deletedUser(int id) {
		// Session session=null;
		// String s="error";
		boolean flag = false;

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();

		try {

			UserDetails user1 = (UserDetails) session.load(UserDetails.class,
					new Integer(id));
			HttpServletRequest request = ServletActionContext.getRequest();
			User user2 = (User) request.getSession().getAttribute("user");
			user1.setDeleted_status(1);
			// user1.setStatus("INACTIVE");
			user1.setUpdated_by(user2.getUserId());
			user1.setUpdated_date(new java.util.Date());
			session.update(user1);
			session.getTransaction().commit();
			flag = true;

		} catch (Exception ex) {

			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return flag;
	}

	public int getTotalRecords(HttpServletRequest request, String col,String dir, String viewdeletedrecord) {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt = 0;
		String sql = "";
		try {

			if (viewdeletedrecord.equalsIgnoreCase("Y")) {
				sql = " select  menu_user_master.user_id from menu_user_master"
						+ " LEFT JOIN designation_type ON designation_type.designation_type_id=menu_user_master.designation_type_id "
						+ " LEFT JOIN employee ON employee.EMPLOYEE_ID=menu_user_master.emp_id "
						+ " INNER JOIN menu_user_master user ON user.user_id = menu_user_master.created_by "
						+ " INNER JOIN menu_user_type ON menu_user_type.user_type_id=menu_user_master.user_type_id ";

			} else {
				sql = " select menu_user_master.user_id user_id from menu_user_master"
						+ " LEFT JOIN designation_type ON designation_type.designation_type_id=menu_user_master.designation_type_id "
						+ " LEFT JOIN employee ON employee.EMPLOYEE_ID=menu_user_master.emp_id "
						+ " INNER JOIN menu_user_master user ON user.user_id = menu_user_master.created_by "
						+ " INNER JOIN menu_user_type ON menu_user_type.user_type_id=menu_user_master.user_type_id "
						+ " where menu_user_master.deleted_status = 0 ";
			}

			String search_term1 = request.getParameter("sSearch");

			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and ( menu_user_master.user_id like '" + search_term + "%'";
				sql += " OR menu_user_master.userloginname like '" + search_term + "%'";
				sql += " OR menu_user_master.emp_id like '" + search_term + "%'";
				sql += " OR employee.EMPLOYEE_NAME like '" + search_term + "%'";
				sql += " OR menu_user_type.user_type_name like '" + search_term + "%'";
				sql += " OR employee.pf like '" + search_term + "%'";
				sql += " OR employee.EMPLOYEE_NAME_KANNADA like  '"+ search_term + "%'";
				sql += " OR employee.WORKING_DEPOT like '" + search_term + "%'";
				sql += " OR menu_user_master.status like '" + search_term + "%'";
				sql += " OR menu_user_master.created_date like '" + search_term + "%'";
				sql += " OR user.userloginname like '" + search_term + "%'";
				sql += " OR employee.WORKING_DEPT like '" + search_term + "%')";

			}

			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			cnt = aliasToValueMapList.size();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}

	public int getTotalRecordsForCount(String search_term) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt = 0;
		try {

			// Query query=
			// session.createSQLQuery("select count(*) as count from user_master  where deleted_status = 0   and (user_id like '%"+search_term+"%' OR userloginname like '%"+search_term+"%' OR emp_id like '%"+search_term+"%' OR EMPLOYEE_NAME like '%"+search_term+"%' OR user_type like '%"+search_term+"%'  OR pf like '%"+search_term+"%' OR WORKING_DEPOT like '%"+search_term+"%' OR status like '%"+search_term+"%' OR WORKING_DEPT like '%"+search_term+"%')  order by user_id").addScalar("count",
			// Hibernate.INTEGER);
			Query query = session
					.createSQLQuery(
							" select count(*) as count from menu_user_master INNER JOIN designation_type ON designation_type.designation_type_id=menu_user_master.designation_type_id "
									+ " LEFT JOIN employee ON employee.EMPLOYEE_ID=menu_user_master.emp_id where menu_user_master.deleted_status = 0  and (user_id like '%"
									+ search_term
									+ "%' OR userloginname like '%"
									+ search_term
									+ "%' OR emp_id like '%"
									+ search_term
									+ "%' OR EMPLOYEE_NAME like '%"
									+ search_term
									+ "%' OR user_type like '%"
									+ search_term
									+ "%'  OR pf like '%"
									+ search_term
									+ "%' OR WORKING_DEPOT like '%"
									+ search_term
									+ "%' OR menu_user_master.status like '%"
									+ search_term
									+ "%' OR WORKING_DEPT like '%"
									+ search_term + "%')  order by user_id")
					.addScalar("count", Hibernate.INTEGER);
			List<Integer> list = query.list();
			cnt = list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getUserList(int total, HttpServletRequest request,String col, String dir) {
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String viewdeletedrecord = (String) request.getSession().getAttribute("viewdeletedrecord");
			String sql = "";
			String employeenamekannda = "";

			if (viewdeletedrecord.equalsIgnoreCase("Y")) {
				sql = " select menu_user_master.user_id user_id,IFNULL(menu_user_master.userloginname,' ')userloginname," 
						+ " menu_user_master.emp_id,IFNULL(employee.EMPLOYEE_NAME,' ')EMPLOYEE_NAME," 
						+ " IFNULL(employee.EMPLOYEE_NAME_KANNADA,' ')EMPLOYEE_NAME_KANNADA,"
						+ " menu_user_type.user_type_name,IFNULL(designation_type.designation_type_name,' ')designation_type_name," 
						+ " IFNULL(employee.TOKEN,' ')Token,IFNULL(employee.PF,' ')pf,IFNULL(employee.WORKING_DEPOT,' ')WORKING_DEPOT," 
						+ " IFNULL(employee.WORKING_DEPT,' ')WORKING_DEPT,DATE_FORMAT(menu_user_master.created_date, '%d/%m/%Y')created_date ," 
						+ " user.userloginname  user_name ,IFNULL(menu_user_master.status,' ')status,menu_user_master.deleted_status " 
						+ " from menu_user_master "
						+ " LEFT JOIN designation_type ON designation_type.designation_type_id=menu_user_master.designation_type_id "
						+ " LEFT JOIN employee ON employee.EMPLOYEE_ID=menu_user_master.emp_id "
						+ " INNER JOIN menu_user_master user ON user.user_id = menu_user_master.created_by "
						+ " INNER JOIN menu_user_type ON menu_user_type.user_type_id=menu_user_master.user_type_id ";

			} else {
				sql = " select menu_user_master.user_id user_id,IFNULL(menu_user_master.userloginname,' ')userloginname,menu_user_master.emp_id,"
						+ " IFNULL(employee.EMPLOYEE_NAME,' ')EMPLOYEE_NAME,IFNULL(employee.EMPLOYEE_NAME_KANNADA,' ')EMPLOYEE_NAME_KANNADA,"
						+ " menu_user_type.user_type_name,IFNULL(designation_type.designation_type_name,' ')designation_type_name," 
						+ " IFNULL(employee.TOKEN,' ')Token, IFNULL(employee.PF,' ')pf,IFNULL(employee.WORKING_DEPOT,' ')WORKING_DEPOT,"
						+ " IFNULL(employee.WORKING_DEPT,' ')WORKING_DEPT,DATE_FORMAT(menu_user_master.created_date, '%d/%m/%Y')created_date ,"
						+ " user.userloginname  user_name,IFNULL(menu_user_master.status,' ')status,menu_user_master.deleted_status " 
						+ " from menu_user_master "
						+ " LEFT JOIN designation_type ON designation_type.designation_type_id=menu_user_master.designation_type_id "
						+ " LEFT JOIN employee ON employee.EMPLOYEE_ID=menu_user_master.emp_id "
						+ " INNER JOIN menu_user_master user ON user.user_id = menu_user_master.created_by "
						+ " INNER JOIN menu_user_type ON menu_user_type.user_type_id=menu_user_master.user_type_id "
						+ " where menu_user_master.deleted_status = 0 ";
			}

			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				
				String search_term = request.getParameter("sSearch").trim();
				sql += " and ( menu_user_master.user_id like '" + search_term + "%'";
				sql += " OR menu_user_master.userloginname like '%"	+ search_term + "%'";
				sql += " OR menu_user_master.emp_id like '" + search_term + "%'";
				sql += " OR employee.EMPLOYEE_NAME like '%" + search_term + "%'";
				sql += " OR menu_user_type.user_type_name like '" + search_term + "%'";
				sql += " OR employee.pf like '" + search_term + "%'";
				sql += " OR employee.EMPLOYEE_NAME_KANNADA like '%"+ search_term + "%'";
				sql += " OR employee.WORKING_DEPOT like '" + search_term + "%'";
				sql += " OR menu_user_master.status like '%" + search_term + "%'";
				sql += " OR menu_user_master.created_date like '" + search_term + "%'";
				sql += " OR user.userloginname like '" + search_term + "%'";
				sql += " OR employee.WORKING_DEPT like '" + search_term + "%')";

			}

			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}

			sql += " limit " + request.getParameter("iDisplayStart") + ", "+ request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql)
						.addScalar("user_id", Hibernate.STRING).addScalar("userloginname", Hibernate.STRING).addScalar("emp_id", Hibernate.STRING)
						.addScalar("EMPLOYEE_NAME", Hibernate.STRING).addScalar("EMPLOYEE_NAME_KANNADA", Hibernate.STRING).addScalar("user_type_name", Hibernate.STRING)
						.addScalar("designation_type_name", Hibernate.STRING).addScalar("Token", Hibernate.STRING).addScalar("pf", Hibernate.STRING)
						.addScalar("WORKING_DEPOT", Hibernate.STRING).addScalar("WORKING_DEPT", Hibernate.STRING)
						.addScalar("created_date", Hibernate.STRING).addScalar("user_name", Hibernate.STRING)
						.addScalar("status", Hibernate.STRING).addScalar("deleted_status", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+ rs.get("user_id")+ "' value='"+ rs.get("user_id")+ "'/>");
				ja.add(rs.get("user_id").toString());
				// ja.add(rs.get("userloginname").toString());
				ja.add(rs.get("userloginname").toString() != null ? rs.get("userloginname").toString().replaceAll(" ", "&nbsp;") : "");
				int empid = Integer.parseInt(rs.get("emp_id").toString());
				if (empid == 0) {
					ja.add("NA");
				} else {
					ja.add(rs.get("emp_id").toString());
				}
				ja.add(rs.get("EMPLOYEE_NAME").toString());
				if (rs.get("EMPLOYEE_NAME_KANNADA").toString().trim().length() > 0) {
					employeenamekannda = getkanndaname(rs.get("EMPLOYEE_NAME_KANNADA").toString());
				} else {
					employeenamekannda = "";
				}
				ja.add(employeenamekannda);
				ja.add(rs.get("user_type_name").toString());
				ja.add(rs.get("designation_type_name").toString());
				ja.add(rs.get("Token").toString());
				ja.add(rs.get("pf").toString());
				ja.add(rs.get("WORKING_DEPOT").toString());
				ja.add(rs.get("WORKING_DEPT").toString());
				ja.add(rs.get("status").toString());
				ja.add(rs.get("created_date").toString());
				ja.add(rs.get("user_name").toString());

				if (viewdeletedrecord.equalsIgnoreCase("Y")) {
					String deletedstatus = rs.get("deleted_status").toString();

					if (deletedstatus.equalsIgnoreCase("1")) {
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+rs.get("user_id")+"'/>Deleted Record");	
					} else {
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+rs.get("user_id")+"'/>Record in Database");	
					}

				} 
				array.add(ja);
			}
			int cnt = 0;
			result.put("aaData", array);
			cnt = getTotalRecords(request, col, dir, viewdeletedrecord);// getTotalRecordsForCount(search_term3);
			result.put("iTotalRecords", cnt);
			result.put("iTotalDisplayRecords", cnt);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
			}
		}
		return result;

	}

	/* code use for user role page */

	public String getDeletRecord(int userrolepageid, int userid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String msg = "";
		/*
		 * String sql=" update menu_page_role set deleted_status = 1 " +
		 * " where page_role_id = "+pageroleid;
		 */
		// System.out.println("userrolepageid-------"+userrolepageid);
		try {
			MenuUserRolePage menuuserrolepage = (MenuUserRolePage) session.get(
					MenuUserRolePage.class, userrolepageid);
			int pageid = menuuserrolepage.getPage_id();
			menuuserrolepage.setDeleted_status(1);
			menuuserrolepage.setUpdated_by(userid);
			menuuserrolepage.setUpdated_date(new java.util.Date());
			session.update(menuuserrolepage);
			session.getTransaction().commit();
			msg = "Page Id " + pageid + " Deleted Sucessfully";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	// public boolean addPageId(int pageid,int roleid,int readaccess,int
	// writeaccess,int userid){
	public String addPageId(MenuUserRolePage menuuserrolepage, int userid) {
		// Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List<Map<String, Object>> list = null;
		int userrolepageid = 0;
		boolean flag = false;
		session.beginTransaction();
		String msg = "";
		int pageid = menuuserrolepage.getPage_id();
		try {

			String sql1 = "select user_rolepage_id from menu_user_role_page where user_id="
					+ menuuserrolepage.getUser_id()
					+ " and page_id="
					+ menuuserrolepage.getPage_id()
					+ " and status='ACTIVE' and deleted_status=0";
			// System.out.println("sql1-------"+sql1);
			Query query = session.createSQLQuery(sql1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			list = query.list();

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> rs = list.get(i);
					userrolepageid = Integer.parseInt(rs
							.get("user_rolepage_id").toString());
					// System.out.println("userrolepageid---1---"+userrolepageid);
					/*
					 * String rolename=rs.get("role_name").toString(); result+=
					 * roleid +"@"; result+= rolename+",";
					 * //System.out.println("result---1---"+result);
					 */
				}

			}
			// System.out.println("user_rolepage_id---------"+userrolepageid);
			if (userrolepageid == 0) {
				// System.out.println("user_rolepage_id********************"+userrolepageid);
				menuuserrolepage.setCreated_by(userid);
				menuuserrolepage.setStatus("ACTIVE");
				menuuserrolepage.setCreated_date(new java.util.Date());
				int a = (Integer) session.save(menuuserrolepage);
				session.getTransaction().commit();

				if (a > 0) {
					// flag=true;
					msg = "Page Id " + pageid + " Created Successfully";
				} else {
					// flag=false;
					msg = "Page Id " + pageid + " Not Created";
				}

			} else {
				// System.out.println("test11111");

				MenuUserRolePage menuuserrolepage1 = (MenuUserRolePage) session
						.get(MenuUserRolePage.class, userrolepageid);

				menuuserrolepage1.setStatus("INACTIVE");
				menuuserrolepage1.setUpdated_by(userid);
				menuuserrolepage1.setUpdated_date(new java.util.Date());
				session.update(menuuserrolepage1);

				try {
					session.beginTransaction();
					menuuserrolepage.setStatus("ACTIVE");
					menuuserrolepage.setCreated_by(userid);
					menuuserrolepage.setCreated_date(new java.util.Date());
					int a = (Integer) session.save(menuuserrolepage);
					session.getTransaction().commit();

					if (a > 0) {
						// flag=true;
						msg = "Page Id " + pageid + " Created Successfully";
					} else {
						// flag=false;
						msg = "Page Id " + pageid + "Not Created";
					}

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// System.out.println("Test666666666666666666666");
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public JSONObject getPageRoleListForUserPage(int total,
			HttpServletRequest request, int roleid, int userid) {
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String sqldetails = "";

			sqldetails = "select menu_user_role_page.user_rolepage_id,menu_user_role_page.user_id, "
					+ " menu_user_role_page.page_id, menu_page_master.page_name,menu_user_role_page.readaccess,menu_user_role_page.createaccess from menu_user_role_page "
					+ " INNER JOIN menu_page_master ON menu_page_master.page_id= menu_user_role_page.page_id "
					+ " where menu_user_role_page.user_id="
					+ userid
					+ " and  menu_user_role_page.deleted_status=0 and "
					+ " menu_page_master.status='ACTIVE' and menu_user_role_page.status='ACTIVE' and menu_page_master.deleted_status=0 and menu_page_master.page_type = 'C' ";
			// System.out.println("sql--------"+sqldetails);

			Query query = session.createSQLQuery(sqldetails);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add(rs.get("page_id").toString());
				ja.add(rs.get("page_name").toString());
				String readaccess = rs.get("readaccess").toString();
				String createaccess = rs.get("createaccess").toString();
				if (readaccess.equalsIgnoreCase("0")) {
					ja.add("<input type='checkbox' class='group-checkable'  disabled='disabled'  data-set='#sample_2 .checkboxes' id='"
							+ rs.get("readaccess")
							+ "' value='"
							+ rs.get("readaccess") + "'/>");
				} else {
					ja.add("<input type='checkbox' class='group-checkable' checked='checked' disabled='disabled' data-set='#sample_2 .checkboxes' id='"
							+ rs.get("readaccess")
							+ "' value='"
							+ rs.get("readaccess") + "'/>");
				}
				if (createaccess.equalsIgnoreCase("0")) {
					ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' disabled='disabled' id='"
							+ rs.get("createaccess")
							+ "' value='"
							+ rs.get("createaccess") + "'/>");
				} else {
					ja.add("<input type='checkbox' class='group-checkable'  checked='checked' disabled='disabled' data-set='#sample_2 .checkboxes' id='"
							+ rs.get("createaccess")
							+ "' value='"
							+ rs.get("createaccess") + "'/>");
				}
				ja.add(" ");
				ja.add("<a class='delete' id='userrolepageid_delete_"
						+ rs.get("user_rolepage_id").toString() + "' value='"
						+ rs.get("user_rolepage_id") + "' href=''> Delete</a>");
				array.add(ja);

			}
			int cnt = 0;
			totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			// String search_term2= request.getParameter("sSearch");
			// if (search_term2 != null && !search_term2.equals("")) {
			// String search_term3= request.getParameter("sSearch").trim();
			// cnt=getTotalRecordsForCount(search_term3);
			// }
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;

	}

	/*
	 * public String getDesignationfromemployee(int empid){ List<Map<String,
	 * Object>> designationlist=null; String result=""; Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml");
	 * session.beginTransaction(); try{ //String sql=
	 * "select employee.WORKING_DESIGNATION,designation_type.designation_type_name from employee INNER JOIN designation_type ON designation_type.designation_type_id=employee.WORKING_DESIGNATION where employee.EMPLOYEE_ID = "
	 * +empid; String sql=
	 * " select IFNULL(employee.WORKING_DESIGNATION,' ')WORKING_DESIGNATION,IFNULL(designation_type.designation_type_name,'')designation_type_name, "
	 * +
	 * " org_type.org_type_id,IFNULL(org_type.org_type,'')org_type,org_chart.org_chart_id ,IFNULL(org_chart.org_name,' ')org_name  from employee  "
	 * +
	 * " INNER JOIN designation_type ON designation_type.designation_type_id=employee.WORKING_DESIGNATION "
	 * +
	 * " and designation_type.status='ACTIVE' and designation_type.deleted_status=0 "
	 * +
	 * " INNER JOIN org_type ON employee.org_type_id = org_type.org_type_id and org_type.status='ACTIVE' "
	 * +
	 * " INNER JOIN org_chart ON employee.org_chart_id = org_chart.org_chart_id  "
	 * + " where employee.EMPLOYEE_ID = "+ empid; String sql=
	 * " select IFNULL(designation_type.designation_type_id,'0')WORKING_DESIGNATION,IFNULL(designation_type.designation_type_name,'NA')designation_type_name, "
	 * + " IFNULL(org_type.org_type_id,'0')org_type_id," +
	 * " IFNULL(org_type.org_type,'NA')org_type, "+
	 * " IFNULL(org_chart.org_chart_id,'0') org_chart_id ,"+
	 * " IFNULL(org_chart.org_name,' ')org_name " + " from employee "+
	 * " LEFT JOIN designation_type ON designation_type.designation_type_id=employee.WORKING_DESIGNATION "
	 * +
	 * " and designation_type.status='ACTIVE' and designation_type.deleted_status=0 "
	 * +
	 * " LEFT JOIN org_type ON employee.org_type_id = org_type.org_type_id and org_type.status='ACTIVE' "
	 * +
	 * " LEFT JOIN org_chart ON employee.org_chart_id = org_chart.org_chart_id "
	 * + " where employee.EMPLOYEE_ID =  "+ empid; Query query =
	 * session.createSQLQuery(sql);
	 * query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	 * designationlist = query.list();
	 * 
	 * if (designationlist.size() > 0) {
	 * 
	 * for(int i=0;i<designationlist.size();i++){ Map<String, Object> rs =
	 * designationlist.get(i); String
	 * designationid=rs.get("WORKING_DESIGNATION").toString(); String
	 * designationname=rs.get("designation_type_name").toString(); String
	 * orgtypeid=rs.get("org_type_id").toString(); String
	 * orgtype=rs.get("org_type").toString(); String
	 * orgchartid=rs.get("org_chart_id").toString(); String
	 * orgname=rs.get("org_name").toString();
	 * 
	 * result= designationid +"@" +designationname +"," +orgtypeid +"@"
	 * +orgtype+ "," +orgchartid+"@"+orgname; //result+= ;
	 * 
	 * //System.out.println("result---1---"+result);
	 * 
	 * 
	 * 
	 * } }else{ result="0@ "; } //System.out.println("result--2----"+result);
	 * }catch(Exception e){e.printStackTrace();} finally{
	 * ////System.out.println("getRolelist"); if(session!=null){
	 * session.close(); } } return result; }
	 */
	/* For create */

	public String getDesignationDetailsForEmp(int empid) {

		String result = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String designation = "";
		try {
			String sql = " select IFNULL(designation_type.designation_type_id,'0')WORKING_DESIGNATION,IFNULL(designation_type.designation_type_name,'NA')designation_type_name "
					+ " from designation_type "
					+ " INNER JOIN employee ON designation_type.designation_type_id=employee.WORKING_DESIGNATION "
					+ " where employee.EMPLOYEE_ID = " + empid;
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// designation=(String)rs.get("WORKING_DESIGNATION").toString()
					// +
					String designationid = rs.get("WORKING_DESIGNATION")
							.toString();
					String designationname = rs.get("designation_type_name")
							.toString();
					designation = designationid + "@" + designationname;
				}
			} else {
				String designationid = "0";
				String designationname = "NA";
				designation = designationid + "@" + designationname;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return designation;
	}

	public String getOrgtypeDetailsForEmp(int empid) {

		String result = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String orgtypedetails = "";
		try {
			String sql = " select IFNULL(org_type.org_type_id,'0')org_type_id, IFNULL(org_type.org_type,'NA')org_type "
					+ " from org_type "
					+ " INNER JOIN employee ON employee.org_type_id = org_type.org_type_id and org_type.status='ACTIVE' "
					+ " where employee.EMPLOYEE_ID = " + empid;
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// designation=(String)rs.get("WORKING_DESIGNATION").toString()
					// +
					String orgtypeid = rs.get("org_type_id").toString();
					String orgtype = rs.get("org_type").toString();
					orgtypedetails = orgtypeid + "@" + orgtype;
				}
			} else {
				String orgtypeid = "0";
				String orgtype = "NA";
				orgtypedetails = orgtypeid + "@" + orgtype;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return orgtypedetails;
	}

	public String getOrgCharttypeDetailsForEmp(int empid) {

		String result = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String orgchartdetails = "";
		try {
			String sql = "  select IFNULL(org_chart.org_chart_id,'0') org_chart_id,IFNULL(org_chart.org_name,'NA')org_name "
					+ " from org_chart "
					+ " INNER JOIN employee ON employee.org_chart_id = org_chart.org_chart_id "
					+ " where employee.EMPLOYEE_ID = " + empid;
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// designation=(String)rs.get("WORKING_DESIGNATION").toString()
					// +
					String orgchartid = rs.get("org_chart_id").toString();
					String orgname = rs.get("org_name").toString();
					orgchartdetails = orgchartid + "@" + orgname;
				}
			} else {
				String orgchartid = "0";
				String orgname = "NA";
				orgchartdetails = orgchartid + "@" + orgname;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return orgchartdetails;
	}

	/** for edit **/
	public String getDesignationDetails(int userid) {

		String result = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String designation = "";
		try {
			String sql = " select IFNULL(designation_type.designation_type_id,'0')WORKING_DESIGNATION,IFNULL(designation_type.designation_type_name,'NA')designation_type_name "
					+ " from menu_user_master "
					+ " INNER JOIN designation_type ON designation_type.designation_type_id=menu_user_master.designation_type_id "
					+ " and designation_type.status='ACTIVE' and designation_type.deleted_status=0 "
					+ " where menu_user_master.user_id = " + userid;
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// designation=(String)rs.get("WORKING_DESIGNATION").toString()
					// +
					String designationid = rs.get("WORKING_DESIGNATION")
							.toString();
					String designationname = rs.get("designation_type_name")
							.toString();
					designation = designationid + "@" + designationname;
				}
			} else {
				String designationid = "0";
				String designationname = "NA";
				designation = designationid + "@" + designationname;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return designation;
	}

	public String getOrgtypeDetails(int userid) {

		String result = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String orgtypedetails = "";
		try {
			String sql = " select IFNULL(org_type.org_type_id,'0')org_type_id, IFNULL(org_type.org_type,'NA')org_type from org_type "
					+ " INNER JOIN  menu_user_master ON org_type.org_type_id =menu_user_master.org_type_id and org_type.status='ACTIVE' "
					+ " where menu_user_master.user_id = " + userid;
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// designation=(String)rs.get("WORKING_DESIGNATION").toString()
					// +
					String orgtypeid = rs.get("org_type_id").toString();
					String orgtype = rs.get("org_type").toString();
					orgtypedetails = orgtypeid + "@" + orgtype;
				}
			} else {
				String orgtypeid = "0";
				String orgtype = "NA";
				orgtypedetails = orgtypeid + "@" + orgtype;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return orgtypedetails;
	}

	public String getOrgCharttypeDetails(int userid) {

		String result = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String orgchartdetails = "";
		try {
			String sql = " select  IFNULL(org_chart.org_chart_id,'0') org_chart_id ,"
					+ " IFNULL(org_chart.org_name,' ')org_name "
					+ " from org_chart "
					+ " INNER JOIN  menu_user_master ON org_chart.org_chart_id =menu_user_master.org_chart_id "
					+ " where menu_user_master.user_id =" + userid;
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// designation=(String)rs.get("WORKING_DESIGNATION").toString()
					// +
					String orgchartid = rs.get("org_chart_id").toString();
					String orgname = rs.get("org_name").toString();
					orgchartdetails = orgchartid + "@" + orgname;
				}
			} else {
				String orgchartid = "0";
				String orgname = "NA";
				orgchartdetails = orgchartid + "@" + orgname;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return orgchartdetails;
	}

	public List<String> getOrgTypeId() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			// String qry =
			// "select org_type_id from org_type where org_type in('CENTRAL OFFICE','DIVISION','STORE','ACCOUNT','DEPOT')";
			String qry = "select org_type_id from org_type where status='ACTIVE' ";

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
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getOrgTypeName() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			// String qry =
			// "select org_type from org_type where org_type in('CENTRAL OFFICE','DIVISION','STORE','ACCOUNT','DEPOT')";
			String qry = "select org_type from org_type where status='ACTIVE' ";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
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
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getUnitId(int orgtypeid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();
			String qry = ""; /*
							 * String qry =
							 * "select org_chart_id from org_chart where org_name!='(NULL)' and org_type_id="
							 * + orgtypeid + "";
							 */
			if (orgtypeid == 5) {
				qry = "select floor_id from floor  where deleted_status=0 and status='Active' order by floor_name";
			} else if (orgtypeid == 6) {
				qry = "select bay_id from bay  where deleted_status=0 and status='Active' order by bay_name";
			} else if (orgtypeid == 7) {
				qry = "select platform_id from platform   where deleted_status=0  and status='Active' order by platform_name";
			} else {
				qry = "select org_chart_id from org_chart where org_name!='(NULL)'  and org_type_id= "
						+ orgtypeid + " and deleted_status=0 order by org_name";
			}

			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					if (orgtypeid == 5) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						list.add(rs.get("floor_id").toString());
					}
					 else if (orgtypeid == 6) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						list.add(rs.get("bay_id").toString());
					} else if (orgtypeid == 7) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						list.add(rs.get("platform_id").toString());					
						} else {
							Map<String, Object> rs = aliasToValueMapList.get(i);
							list.add(rs.get("org_chart_id").toString());						
						}
			}
				
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getUnitName(int orgtypeid) {
		Session session = null;
		List list = null;
		try {
			session =  HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			/*
			 * String qry =
			 * "select org_name from org_chart where org_name!='(NULL)' and org_type_id="
			 * + orgtypeid + "";
			 * 
			 */
			String qry="";
			if (orgtypeid == 5) {
				qry = "select floor_name from floor where deleted_status=0 and status='ACTIVE' order by floor_name ";
		
			} else if (orgtypeid == 6) {
				qry = "select bay_name from bay  where deleted_status=0 and status='ACTIVE' order by bay_name ";
			} else if (orgtypeid == 7) {
				qry = "select platform_name from platform where deleted_status=0  and status='ACTIVE' order by platform_name ";
			} else {
				qry = "select org_name from org_chart where org_name!='(NULL)'  and org_type_id= "
						+ orgtypeid + " and deleted_status=0 order by org_name";
			}


			
			//String qry = "select org_name from org_chart where org_name!='(NULL)'  and org_type_id= "
				//	+ orgtypeid + " and deleted_status=0 order by org_name";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					if (orgtypeid == 5) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						list.add(rs.get("floor_name").toString());
					}
					 else if (orgtypeid == 6) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						list.add(rs.get("bay_name").toString());
					} else if (orgtypeid == 7) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						list.add(rs.get("platform_name").toString());					
						} else {
							Map<String, Object> rs = aliasToValueMapList.get(i);
							list.add(rs.get("org_name").toString());						
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			DenominationTypeDao denomtypedao = new DenominationTypeDao();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public String getkanndaname(String org_name_kannada) {
		/*
		 * //System.out.println("org_name_kannada--------"+org_name_kannada);
		 * 
		 * String out = "";
		 * 
		 * 
		 * org_name_kannada = org_name_kannada.replace(" ", ""); String[] s =
		 * org_name_kannada.split(";"); for(int i=0; i<s.length; i++) {
		 * 
		 * String s1= s[i].replace("#", ""); s1= s1.replace("&", ""); s1=
		 * s1.trim(); char c = (char)Integer.parseInt(s1);
		 * //System.out.println(s.length+""+c+"----"+s1); out+=""+c;
		 * 
		 * } //System.out.println("out---"+out.toString());
		 * //this.org_name_kannada = out.toString(); return out.toString();
		 */

		StringBuffer out = new StringBuffer();
		String name;
		for (int i = 0; i < org_name_kannada.length(); i++) {
			char c = org_name_kannada.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}

		name = out.toString();
		// System.out.println("name-----"+name);
		return name;
	}

}
