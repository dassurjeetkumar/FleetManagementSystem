package com.trimax.its.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.usermanagement.model.EmployeeType;
import com.trimax.its.util.HibernateUtil;

/**
 * @author root
 *
 */
public class EmployeeTypeDAO {
	/**
	 * This method use for add employeetype details
	 * @param employeetypename
	 * @param employeenote
	 * @param employeestatus
	 * @param createdby
	 * @param employeedeletestatus
	 * @return boolean
	 */
	public String addemployeetype(String employeetypename,String employeenote,String employeestatus,int createdby)
	{
		
		Transaction transaction=null;
		Session session=null;
		session = HibernateUtil.getSession("EmployeeTypeDAO");
		String str="";
		String flag="";
		String msg="";
		try{
			
			String sql="select employee_id  from employee_type where employee_type_name='"+ employeetypename +"' and deleted_status=0";
					
			session.beginTransaction();
			Query query=session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//System.out.println("aliasToValueMapList---"+aliasToValueMapList);

			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<String, Object> rs = aliasToValueMapList.get(0);
				if (rs != null) {
					////System.out.println("Already Added");
					int employee_id=Integer.parseInt(rs.get("employee_id").toString());
					//System.out.println("Record allready Present");
					str= " Employee Type ID" + employee_id + " already Present";
					flag="false";
					msg=flag+"|"+str;
				}
			}else{
				//try{
					transaction=session.beginTransaction();
					String q=" INSERT INTO `employee_type` (`employee_type_name`, `status`, `note`, `created_by`, `created_date`, `updated_by`, `updated_date`, `deleted_status`) VALUES "
							+ "('"+employeetypename+"','"+employeestatus+"','"+employeenote+"',"+createdby+",now(),0,NULL,"+0+")";							
					//System.out.println("q---"+q);
					Query query1=session.createSQLQuery(q);
					int a=query1.executeUpdate();
					session.getTransaction().commit();
					if(a>0){
						Query query2 = session.createSQLQuery("select MAX(employee_id)employee_id  from  employee_type").addScalar("employee_id", Hibernate.INTEGER);
						List<Integer> list = query2.list();
						int employee_id = list.get(0);
						//System.out.println("employee_id----"+employee_id);
						str= "Employee Type ID " + employee_id +" Added Sucessfully";
						flag="true";
						msg=flag+"|"+str;
					}
					//System.out.println("added sucessfully");
					session.flush();
					
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}
	
	/*
	 * */
public int getTotalRecords(){
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		 try{
		Query	 query = session.createSQLQuery("select count(*) as count from employee_type where deleted_status = 0").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		 cnt = list.get(0);
		 }catch(Exception e){
			 e.printStackTrace();
			 
		 }finally{
			 if (session != null) {
					session.close();
				}
		 }
		return cnt;
	}

public int getTotalRecordsForCount(String search_term){
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	int cnt=0;
	try{
	Query	 query= session.createSQLQuery("select count(*) as count  from employee_type where deleted_status=0  and (employee_id like '%"+search_term+"%' OR employee_type_name like '%"+search_term+"%' OR status like '%"+search_term+"%' OR note like '%"+search_term+"%' )  order by employee_id").addScalar("count", Hibernate.INTEGER);
	List<Integer> list = query.list();
	 cnt = list.get(0);
	}catch(Exception e){
		e.printStackTrace();
		
	}
	
	return cnt;
}
	
	public JSONObject getEmployeeTypeList(int total,  HttpServletRequest request,String col,String dir){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		try{
			String sql="";
			sql="select employee_id,IFNULL(employee_type_name,'NA')employee_type_name,IFNULL(status,'NA')status,IFNULL(note,'NA')note from employee_type where deleted_status = 0 ";
			if(!request.getParameter("sSearch").equals("")){
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
				sql += " and ( employee_id like '%"+search_term+"%'";
				sql += " OR employee_type_name like '%"+search_term+"%'";
				sql += " OR status like '%"+search_term+"%'";
				sql += " OR note like '%"+search_term+"%')";
			}
			if(!col.equals("")){
				if(dir.equals("asc")){
				    //criteria.addOrder(Order.asc(col));
					sql += " order by "+col;
				}else{
					//criteria.addOrder(Order.desc(col));	
					sql += " order by "+col+" desc";
				}
			}
			
			//System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");	
		//System.out.println("sql----------"+sql);
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		JSONArray array = new JSONArray();
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
					+ rs.get("employee_id")
					+ "' value='"
					+ rs.get("employee_id") + "'/>");
			ja.add(rs.get("employee_id").toString());
			ja.add(rs.get("employee_type_name").toString());
			ja.add(rs.get("status").toString());
			ja.add(rs.get("note").toString());
			
			array.add(ja);
			//System.out.println("Array----->"+array);
		}
	
			totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			String search_term = request.getParameter("sSearch").trim();
			int cnt=getTotalRecordsForCount(search_term);
			result.put("iTotalRecords",cnt);
			result.put("iTotalDisplayRecords",cnt);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return result;
	}
	
	
	/*public ArrayList <EmployeeType> getEmployeeTypeList(){
		ArrayList <EmployeeType>showemployeetypelist=new ArrayList();
		Transaction transaction = null;
		Session session = null;
		session = HibernateUtil.getSession("EmployeeTypeDAO");
		String q="select employee_id,employee_type_name,status,note from employee_type where status ='NEW' order by employee_id desc";
		try{
			//System.out.println("q---"+q);
			
			session.beginTransaction();
			 Query query = session.createSQLQuery(q);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//System.out.println("Test----"+aliasToValueMapList);
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				EmployeeType employeetype = new EmployeeType();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				if(rs.size()>0){
					employeetype.setEmp_id(rs.get("employee_id").toString());
					employeetype.setEmployee_typename(rs.get("employee_type_name").toString());
					employeetype.setEmployee_status(rs.get("status").toString());
					employeetype.setEmployee_note(rs.get("note").toString());
				}
				showemployeetypelist.add(employeetype);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return showemployeetypelist;
	}
	*/
	
	public String getInactiveRecord(int employeetypeid,int updateby){
		String flag="";
		Transaction transaction = null;
		Session session = null;
		String msg="";
		String str="";
		session = HibernateUtil.getSession("EmployeeTypeDAO");
		transaction = session.beginTransaction();
		String sql="";
		try{
			//sql="update user_pg_rel set RECORD_STATUS = 'INACTIVE',UPDATED_TM = now(), UPDATED_BY= '"+updatedby+"' where USER_PG_REL_ID ="+ userpagerelationid;
			sql="update employee_type set STATUS = 'INACTIVE',deleted_status=1,updated_by="+updateby+",updated_date=now() where employee_id ="+employeetypeid;
			//System.out.println("sql--"+sql);
			Query query1 = session.createSQLQuery(sql);
			
			int n=query1.executeUpdate();
			session.getTransaction().commit();
			//System.out.println("updated sucessfully");
			session.flush();
			if(n>0){
				//System.out.println("Updated sucessfully");
				session.flush();
				str="EmployeeType ID "+ employeetypeid +" Deleted sucessfully";
				flag="true";
				msg=flag+"|"+str;
			}else{
				//System.out.println("Record not Deleted");
				str="EmployeeType ID "+ employeetypeid +" Not Deleted";
				flag="false";
				msg=flag+"|"+str;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}
	
	
	/**
	 * This method use for modify the employeetype
	 * @param empid
	 * @param employeetype
	 * @param employeestatus
	 * @param note
	 * @param updateby
	 * @return String
	 */
	public String modifyEmployeeType(int empid,String employeetype,String employeestatus,String note,int updateby){
		String flag="";
		String msg="";
		String str="";
		Transaction transaction = null;
		Session session = null;
		session = HibernateUtil.getSession("EmployeeTypeDAO");
		String sql="";
		int deletedstatus;
		try{
			String q="select employee_id from employee_type where employee_type_name='"+ employeetype +"'and deleted_status=0 and  employee_id NOT IN (" + empid + ")";
			session.beginTransaction();
			Query query=session.createSQLQuery(q);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//System.out.println("aliasToValueMapList---"+aliasToValueMapList);
			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<String, Object> rs = aliasToValueMapList.get(0);
				if (rs != null) {
					int empiddetails=Integer.parseInt(rs.get("employee_id").toString());
					//System.out.println("Record allready Present");
					str="Employee Type ID" + empiddetails +" already Present";
					flag="false";
					msg=flag+"|"+str;
					
			}
				
				
				
				
			}else{
			/*if(employeestatus.equalsIgnoreCase("NEW"))
			{
				deletedstatus=0;
			}else{
				deletedstatus=1;
			}*/
			transaction = session.beginTransaction();
			sql="update employee_type set employee_type_name='"+employeetype+"',status='"+employeestatus+"',note='"+note+"',updated_date= now(),updated_by="+updateby+"  where employee_id="+empid; 
			//System.out.println("sql--"+sql);
			Query query1 = session.createSQLQuery(sql);
			//System.out.println("test1");
			int n=query1.executeUpdate();
			session.getTransaction().commit();
			//System.out.println("test2");
			//System.out.println("added sucessfully");
			session.flush();
			if(n>0){
				str="Employee type ID "+ empid +" Modify sucessfully";
				flag="true";
				msg=flag+"|"+str;
			//System.out.println("msg---"+msg);
			}else{
				//System.out.println("Record not updated");
				str="Employee type "+ empid +" Not Modify sucessfully";
				flag="false";
				msg=flag+"|"+str;
			}//else
				}//else
				
			
		}catch(Exception e){
			str="Error In Modify the Record";
			flag="false";
			
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}
	
	
	/**
	 * This method use for getting list from id
	 * @param empid
	 * @return
	 */
	public EmployeeType getEmployeeTypeListUsingid(int empid){
		ArrayList <EmployeeType>showemployeetypelist=new ArrayList();
		Transaction transaction = null;
		Session session = null;
		session = HibernateUtil.getSession("EmployeeTypeDAO");
		EmployeeType employeetype = new EmployeeType();
		String q="select employee_id,employee_type_name,status,note from employee_type where employee_id="+empid;
		try{
			//System.out.println("q---"+q);
			
			session.beginTransaction();
			 Query query = session.createSQLQuery(q);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//System.out.println("Test----"+aliasToValueMapList);
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				if(rs.size()>0){
					employeetype.setEmp_id(Integer.parseInt(rs.get("employee_id").toString()));
					employeetype.setEmployee_typename(rs.get("employee_type_name").toString());
					employeetype.setEmployee_status(rs.get("status").toString());
					employeetype.setEmployee_note(rs.get("note").toString());
				}
				showemployeetypelist.add(employeetype);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return employeetype;
	}
	
	
}