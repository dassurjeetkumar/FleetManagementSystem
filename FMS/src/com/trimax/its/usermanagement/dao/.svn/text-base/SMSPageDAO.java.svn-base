package com.trimax.its.usermanagement.dao;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

public class SMSPageDAO {
	
	
	public int getTotalRecords(String col,String dir){
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
		/*Query	 query= session.createSQLQuery("select count(*) as count  from employee where  ( EMPLOYEE_ID like '"+search_term+"%' OR EMPLOYEE_NAME like '"+search_term+"%'  OR EMPLOYEE_DESIGNATION like '"+search_term+"%' OR PF like '"+search_term+"%' OR GENDER like '"+search_term+"%' OR WORKING_DEPOT like '"+search_term+"%' OR WORKING_DEPT like '"+search_term+"%' OR TOKEN like '"+search_term+"%' OR DL_EXPIRY_DT like '"+search_term+"%' OR DRIVING_LC_NO like '"+search_term+"%' OR DOB like '"+search_term+"%'  OR CELL_PHONE like '"+search_term+"%' OR EMAIL like '"+search_term+"%' OR RETIREMENT_DT like '"+search_term+"%' OR FATHER_NAME like '"+search_term+"%' OR ADDRESS like '"+search_term+"%' OR designation_type.designation_type_name like '"+search_term+"%' OR org_type.org_type like '"+search_term+"%' OR org_chart.org_name like '"+search_term+"%' )  order by EMPLOYEE_ID").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		cnt = list.get(0);*/
			String sql="";
			sql = " select EMPLOYEE_ID,IFNULL(employee.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME, " +
				"IFNULL(CELL_PHONE,'NA')CELL_PHONE, " +
				"IFNULL(org_chart.org_name,'NA')org_name " +
				" from employee " +
				" LEFT JOIN designation_type ON employee.WORKING_DESIGNATION = designation_type.designation_type_id " +
				" LEFT JOIN org_chart ON employee.org_chart_id=org_chart.org_chart_id where employee.status='Active' and designation_type_id in ('1','2','16') ";
//			if(orgtTypeId!=0 && orgtTypeId==1){
//				ordchartcompare="";
//			}else if(orgtTypeId!=0 && orgtTypeId==2){
//				ordchartcompare="and employee.org_type_id='"+orgtTypeId+"'";
//			}
//			else if(orgtTypeId!=0 && orgtTypeId==3){
//				ordchartcompare="and employee.org_chart_id='"+orgchartid+"'";
//			}
//				sql += ordchartcompare;		
			
			String search_term1 = request.getParameter("sSearch");
			////System.out.println("search_term-------"+search_term1);
//			if (search_term1 != null && !search_term1.equals("")) {
//				String search_term = request.getParameter("sSearch").trim();
////			
//				sql += " OR org_chart.org_name like '%"+search_term+"%'";
//				sql += " OR TOKEN like '%"+search_term+"%')";
//				
//			}
			
//			if(!col.equals("")){
//				 if(dir.equals("asc")){
//					if(col.equals("TOKEN")){
//						sql += " order by CAST("+col+" AS UNSIGNED) asc";
//					}else
//				  sql += " order by "+col+" asc";
//				}else{
//					if(col.equals("TOKEN")){
//						sql += " order by CAST("+col+" AS UNSIGNED) desc";
//					}
//					else
//					sql += " order by "+col+" desc";
//				}
//			}
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			cnt=aliasToValueMapList.size();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return cnt;
	}



@SuppressWarnings("unchecked")
public JSONObject getList(int total, HttpServletRequest request,String col,String dir){
	int totalAfterFilter = total;
	JSONObject result = new JSONObject();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	String employeenamekannda="";
	String ordchartcompare="";
	try{
		int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
		int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
		
		String sql="";
		sql = " select EMPLOYEE_ID,IFNULL(employee.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME,designation_type_id, " +
				"IFNULL(CELL_PHONE,'NA')CELL_PHONE, " +
				"IFNULL(org_chart.org_name,'NA')org_name " +
				" from employee " +
				" LEFT JOIN designation_type ON employee.WORKING_DESIGNATION = designation_type.designation_type_id " +
				" LEFT JOIN org_chart ON employee.org_chart_id=org_chart.org_chart_id where employee.status='Active' and designation_type_id in ('1','2','16')";
//		if(orgtTypeId!=0 && orgtTypeId==1){
//			ordchartcompare = "  ";
//		}else if(orgtTypeId!=0 && orgtTypeId==2){
//			ordchartcompare = " and employee.org_type_id='"+orgtTypeId+"'  ";
//		}
//		else if(orgtTypeId!=0 && orgtTypeId==3){
//			ordchartcompare = " and employee.org_chart_id='"+orgchartid+"'  ";
//		}
	//	sql += ordchartcompare +" AND employee.status='Active'";	
//		sql += ordchartcompare;	
//		String search_term1 = request.getParameter("sSearch");
		////System.out.println("search_term-------"+search_term1);
//		if (search_term1 != null && !search_term1.equals("")) {
//			String search_term = request.getParameter("sSearch").trim();
//			
//			sql += " and  ( EMPLOYEE_NAME like '%"+search_term+"%'";
//
//			sql += " OR org_chart1.org_name like '%"+search_term+"%'";
//			sql += " OR TOKEN like '%"+search_term+"%')";
//			
//		}
		
//		if(!col.equals("")){
//			 if(dir.equals("asc")){
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) asc";
//				}else
//			  sql += " order by "+col+" asc";
//			}else{
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) desc";
//				}
//				else
//				sql += " order by "+col+" desc";
//			}
//		}
//	
//		sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		
	////System.out.println("sql----------"+sql);
	Query query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	   
	System.out.println("aliasToValueMapList.size()==="+aliasToValueMapList.size());
	JSONArray array = new JSONArray();
	for(int i=0;i<aliasToValueMapList.size();i++){
		Map<String, Object> rs = aliasToValueMapList.get(i);
		JSONArray ja = new JSONArray();
		ja.add("&nbsp;<input type='checkbox' class='driverId' id='logid' name='logid' value='"+ rs.get("CELL_PHONE")+"'/>");
		ja.add(rs.get("EMPLOYEE_NAME").toString());
		ja.add(rs.get("CELL_PHONE").toString());
		ja.add(rs.get("org_name").toString());
		array.add(ja);
	}
	
	result.put("aaData", array);
	System.out.println("array.size()==="+array.size());
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if (session != null) {
			//session.close();
		}
	}
	return result;
	
}



public int getTotalRecordsforCoductor(String col,String dir){
	HttpServletRequest request = ServletActionContext.getRequest();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	int cnt=0;
	String ordchartcompare="";
	try{
		int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
		int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
	/*Query	 query= session.createSQLQuery("select count(*) as count  from employee where  ( EMPLOYEE_ID like '"+search_term+"%' OR EMPLOYEE_NAME like '"+search_term+"%'  OR EMPLOYEE_DESIGNATION like '"+search_term+"%' OR PF like '"+search_term+"%' OR GENDER like '"+search_term+"%' OR WORKING_DEPOT like '"+search_term+"%' OR WORKING_DEPT like '"+search_term+"%' OR TOKEN like '"+search_term+"%' OR DL_EXPIRY_DT like '"+search_term+"%' OR DRIVING_LC_NO like '"+search_term+"%' OR DOB like '"+search_term+"%'  OR CELL_PHONE like '"+search_term+"%' OR EMAIL like '"+search_term+"%' OR RETIREMENT_DT like '"+search_term+"%' OR FATHER_NAME like '"+search_term+"%' OR ADDRESS like '"+search_term+"%' OR designation_type.designation_type_name like '"+search_term+"%' OR org_type.org_type like '"+search_term+"%' OR org_chart.org_name like '"+search_term+"%' )  order by EMPLOYEE_ID").addScalar("count", Hibernate.INTEGER);
	List<Integer> list = query.list();
	cnt = list.get(0);*/
		String sql="";
		sql = " select EMPLOYEE_ID,IFNULL(employee.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME, " +
			"IFNULL(CELL_PHONE,'NA')CELL_PHONE, " +
			"IFNULL(org_chart.org_name,'NA')org_name " +
			" from employee " +
			" LEFT JOIN designation_type ON employee.WORKING_DESIGNATION = designation_type.designation_type_id " +
			" LEFT JOIN org_chart ON employee.org_chart_id=org_chart.org_chart_id where employee.status='Active' and designation_type_id='2'";
//		if(orgtTypeId!=0 && orgtTypeId==1){
//			ordchartcompare="";
//		}else if(orgtTypeId!=0 && orgtTypeId==2){
//			ordchartcompare="and employee.org_type_id='"+orgtTypeId+"'";
//		}
//		else if(orgtTypeId!=0 && orgtTypeId==3){
//			ordchartcompare="and employee.org_chart_id='"+orgchartid+"'";
//		}
//			sql += ordchartcompare;		
		
		String search_term1 = request.getParameter("sSearch");
		////System.out.println("search_term-------"+search_term1);
//		if (search_term1 != null && !search_term1.equals("")) {
//			String search_term = request.getParameter("sSearch").trim();
////		
//			sql += " OR org_chart.org_name like '%"+search_term+"%'";
//			sql += " OR TOKEN like '%"+search_term+"%')";
//			
//		}
		
//		if(!col.equals("")){
//			 if(dir.equals("asc")){
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) asc";
//				}else
//			  sql += " order by "+col+" asc";
//			}else{
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) desc";
//				}
//				else
//				sql += " order by "+col+" desc";
//			}
//		}
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		cnt=aliasToValueMapList.size();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
	}
	
	return cnt;
}



@SuppressWarnings("unchecked")
public JSONObject getListCoductor(int total, HttpServletRequest request,String col,String dir){
int totalAfterFilter = total;
JSONObject result = new JSONObject();
Session session = HibernateUtil.getSession("hibernate.cfg.xml");
String employeenamekannda="";
String ordchartcompare="";
try{
	int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
	int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
	
	
	String sql="";
	sql = " select EMPLOYEE_ID,IFNULL(employee.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME,designation_type_id, " +
			"IFNULL(CELL_PHONE,'NA')CELL_PHONE, " +
			"IFNULL(org_chart.org_name,'NA')org_name " +
			" from employee " +
			" LEFT JOIN designation_type ON employee.WORKING_DESIGNATION = designation_type.designation_type_id " +
			" LEFT JOIN org_chart ON employee.org_chart_id=org_chart.org_chart_id where employee.status='Active' and designation_type_id='2'";
//	if(orgtTypeId!=0 && orgtTypeId==1){
//		ordchartcompare = "  ";
//	}else if(orgtTypeId!=0 && orgtTypeId==2){
//		ordchartcompare = " and employee.org_type_id='"+orgtTypeId+"'  ";
//	}
//	else if(orgtTypeId!=0 && orgtTypeId==3){
//		ordchartcompare = " and employee.org_chart_id='"+orgchartid+"'  ";
//	}
//	sql += ordchartcompare +" AND employee.status='Active'";	
//	sql += ordchartcompare;	
//	String search_term1 = request.getParameter("sSearch");
	////System.out.println("search_term-------"+search_term1);
//	if (search_term1 != null && !search_term1.equals("")) {
//		String search_term = request.getParameter("sSearch").trim();
//		
//		sql += " and  ( EMPLOYEE_NAME like '%"+search_term+"%'";
//
//		sql += " OR org_chart1.org_name like '%"+search_term+"%'";
//		sql += " OR TOKEN like '%"+search_term+"%')";
//		
//	}
	
//	if(!col.equals("")){
//		 if(dir.equals("asc")){
//			if(col.equals("TOKEN")){
//				sql += " order by CAST("+col+" AS UNSIGNED) asc";
//			}else
//		  sql += " order by "+col+" asc";
//		}else{
//			if(col.equals("TOKEN")){
//				sql += " order by CAST("+col+" AS UNSIGNED) desc";
//			}
//			else
//			sql += " order by "+col+" desc";
//		}
//	}
//
//	sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
	
////System.out.println("sql----------"+sql);
Query query = session.createSQLQuery(sql);
query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
List<Map<String, Object>> aliasToValueMapList = query.list();
   

JSONArray array = new JSONArray();
for(int i=0;i<aliasToValueMapList.size();i++){
	Map<String, Object> rs = aliasToValueMapList.get(i);
	JSONArray ja = new JSONArray();
	ja.add("&nbsp;<input type='checkbox' class='conductorId' id='logid' name='logid' value='"+ rs.get("CELL_PHONE")+ "'/>");
	ja.add(rs.get("EMPLOYEE_NAME").toString());
	ja.add(rs.get("CELL_PHONE").toString());
	ja.add(rs.get("org_name").toString());
	array.add(ja);
}

result.put("aaData", array);
}catch(Exception e){
	e.printStackTrace();
}finally{
	if (session != null) {
		//session.close();
	}
}
return result;

}

public int getTotalRecordsDriverConductor(String col,String dir){
	HttpServletRequest request = ServletActionContext.getRequest();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	int cnt=0;
	String ordchartcompare="";
	try{
		int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
		int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
	/*Query	 query= session.createSQLQuery("select count(*) as count  from employee where  ( EMPLOYEE_ID like '"+search_term+"%' OR EMPLOYEE_NAME like '"+search_term+"%'  OR EMPLOYEE_DESIGNATION like '"+search_term+"%' OR PF like '"+search_term+"%' OR GENDER like '"+search_term+"%' OR WORKING_DEPOT like '"+search_term+"%' OR WORKING_DEPT like '"+search_term+"%' OR TOKEN like '"+search_term+"%' OR DL_EXPIRY_DT like '"+search_term+"%' OR DRIVING_LC_NO like '"+search_term+"%' OR DOB like '"+search_term+"%'  OR CELL_PHONE like '"+search_term+"%' OR EMAIL like '"+search_term+"%' OR RETIREMENT_DT like '"+search_term+"%' OR FATHER_NAME like '"+search_term+"%' OR ADDRESS like '"+search_term+"%' OR designation_type.designation_type_name like '"+search_term+"%' OR org_type.org_type like '"+search_term+"%' OR org_chart.org_name like '"+search_term+"%' )  order by EMPLOYEE_ID").addScalar("count", Hibernate.INTEGER);
	List<Integer> list = query.list();
	cnt = list.get(0);*/
		String sql="";
		sql = " select EMPLOYEE_ID,IFNULL(employee.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME, " +
			"IFNULL(CELL_PHONE,'NA')CELL_PHONE, " +
			"IFNULL(org_chart.org_name,'NA')org_name " +
			" from employee " +
			" LEFT JOIN designation_type ON employee.WORKING_DESIGNATION = designation_type.designation_type_id " +
			" LEFT JOIN org_chart ON employee.org_chart_id=org_chart.org_chart_id where employee.status='Active' and designation_type_id='16'";
//		if(orgtTypeId!=0 && orgtTypeId==1){
//			ordchartcompare="";
//		}else if(orgtTypeId!=0 && orgtTypeId==2){
//			ordchartcompare="and employee.org_type_id='"+orgtTypeId+"'";
//		}
//		else if(orgtTypeId!=0 && orgtTypeId==3){
//			ordchartcompare="and employee.org_chart_id='"+orgchartid+"'";
//		}
//			sql += ordchartcompare;		
		
		String search_term1 = request.getParameter("sSearch");
		////System.out.println("search_term-------"+search_term1);
//		if (search_term1 != null && !search_term1.equals("")) {
//			String search_term = request.getParameter("sSearch").trim();
////		
//			sql += " OR org_chart.org_name like '%"+search_term+"%'";
//			sql += " OR TOKEN like '%"+search_term+"%')";
//			
//		}
		
//		if(!col.equals("")){
//			 if(dir.equals("asc")){
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) asc";
//				}else
//			  sql += " order by "+col+" asc";
//			}else{
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) desc";
//				}
//				else
//				sql += " order by "+col+" desc";
//			}
//		}
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		cnt=aliasToValueMapList.size();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
	}
	
	return cnt;
}



@SuppressWarnings("unchecked")
public JSONObject getListDriverConductor(int total, HttpServletRequest request,String col,String dir){
int totalAfterFilter = total;
JSONObject result = new JSONObject();
Session session = HibernateUtil.getSession("hibernate.cfg.xml");
String employeenamekannda="";
String ordchartcompare="";
try{
	int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
	int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
	
	
	String sql="";
	sql = " select EMPLOYEE_ID,IFNULL(employee.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME,designation_type_id, " +
			"IFNULL(CELL_PHONE,'NA')CELL_PHONE, " +
			"IFNULL(org_chart.org_name,'NA')org_name " +
			" from employee " +
			" LEFT JOIN designation_type ON employee.WORKING_DESIGNATION = designation_type.designation_type_id " +
			" LEFT JOIN org_chart ON employee.org_chart_id=org_chart.org_chart_id where employee.status='Active' and designation_type_id='16'";
//	if(orgtTypeId!=0 && orgtTypeId==1){
//		ordchartcompare = "  ";
//	}else if(orgtTypeId!=0 && orgtTypeId==2){
//		ordchartcompare = " and employee.org_type_id='"+orgtTypeId+"'  ";
//	}
//	else if(orgtTypeId!=0 && orgtTypeId==3){
//		ordchartcompare = " and employee.org_chart_id='"+orgchartid+"'  ";
//	}
//	sql += ordchartcompare +" AND employee.status='Active'";	
//	sql += ordchartcompare;	
//	String search_term1 = request.getParameter("sSearch");
	////System.out.println("search_term-------"+search_term1);
//	if (search_term1 != null && !search_term1.equals("")) {
//		String search_term = request.getParameter("sSearch").trim();
//		
//		sql += " and  ( EMPLOYEE_NAME like '%"+search_term+"%'";
//
//		sql += " OR org_chart1.org_name like '%"+search_term+"%'";
//		sql += " OR TOKEN like '%"+search_term+"%')";
//		
//	}
	
//	if(!col.equals("")){
//		 if(dir.equals("asc")){
//			if(col.equals("TOKEN")){
//				sql += " order by CAST("+col+" AS UNSIGNED) asc";
//			}else
//		  sql += " order by "+col+" asc";
//		}else{
//			if(col.equals("TOKEN")){
//				sql += " order by CAST("+col+" AS UNSIGNED) desc";
//			}
//			else
//			sql += " order by "+col+" desc";
//		}
//	}
//
//	sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
	
////System.out.println("sql----------"+sql);
Query query = session.createSQLQuery(sql);
query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
List<Map<String, Object>> aliasToValueMapList = query.list();
   

JSONArray array = new JSONArray();
for(int i=0;i<aliasToValueMapList.size();i++){
	Map<String, Object> rs = aliasToValueMapList.get(i);
	JSONArray ja = new JSONArray();
	ja.add("&nbsp;<input type='checkbox' class='conductordriverId' id='logid' name='logid' value='"+ rs.get("CELL_PHONE")+ "'/>");
	ja.add(rs.get("EMPLOYEE_NAME").toString());
	ja.add(rs.get("CELL_PHONE").toString());
	ja.add(rs.get("org_name").toString());
	array.add(ja);
}

result.put("aaData", array);
}catch(Exception e){
	e.printStackTrace();
}finally{
	if (session != null) {
		//session.close();
	}
}
return result;

}

public int insertSmsStatus(String mobile,String status ) {
	
	System.out.println("Enter into insertSmsStatus()");
	Common common = new Common();
	String operateddate="";
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	int count1=0;
	Transaction transaction = null;
	
	int usrid = Integer.parseInt(ServletActionContext
			.getRequest().getSession().getAttribute("userid")
			.toString());
	
	
	try {
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session.beginTransaction();
	
				
				
				
			 String sql1="insert into  sms_status(mobile,status) values('"+mobile+"','"+status+"')";
			 
			 
			session.beginTransaction();
			 Query query1 =session.createSQLQuery(sql1);
			 count1 =query1.executeUpdate();
			 session.getTransaction().commit();
//			
				
		
		
//		HibernateUtil.closeSession();
		
	} catch (Exception e) {

	}

	
	return count1;
	
}
@SuppressWarnings("unchecked")
public List<String> getEmpGroupDetails(){

Session session = HibernateUtil.getSession("hibernate.cfg.xml");
String employeenamekannda="";
String ordchartcompare="";
List<String> list=new ArrayList<String>();
try{
	int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
	int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
	
	
	String sql="";
	sql = "select group_id,group_name from sms_group where status='y' ";

Query query = session.createSQLQuery(sql);
query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
List<Map<String, Object>> aliasToValueMapList = query.list();
   

JSONArray array = new JSONArray();
for(int i=0;i<aliasToValueMapList.size();i++){
	Map<String, Object> rs = aliasToValueMapList.get(i);
	JSONArray ja = new JSONArray();
	list.add(rs.get("group_name").toString());
	
	
}


}catch(Exception e){
	e.printStackTrace();
}finally{
	if (session != null) {
		//session.close();
	}
}
return list;

}
@SuppressWarnings("rawtypes")
public List getDepotIdDC(int parentID, String orgchart_id) {
	List list = new ArrayList();
	
	String qry = "";
	if(parentID==1){
		qry = "select org_chart_id from org_chart where deleted_status=0 "
				+ " and org_type_id=3 order by org_name";
	}else{
		qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3 order by org_name";
	}
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

@SuppressWarnings({ "rawtypes", "unchecked" })
public List getDepotNameDC(int parentID, String org_chart_id) {
	List list = new ArrayList();
	String qry = "";
	
	if(parentID==1){
		qry = "select org_name from org_chart where deleted_status=0 "
				+ " and org_type_id=3 order by org_name";
	}else{
		qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3 order by org_name";
	}
	
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

@SuppressWarnings("rawtypes")
public List getDepotId(int parentID) {
	List list = new ArrayList();
	String qry = "";
	if(parentID==1){
		qry = "select org_chart_id from org_chart where deleted_status=0 "
				+ " and org_type_id=3 order by org_name";
	}else{
		qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3 order by org_name";
	}
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

@SuppressWarnings({ "rawtypes", "unchecked" })
public List getDepotName(int parentID) {
	List list = new ArrayList();
String qry = "";
	
	if(parentID==1){
		qry = "select org_name from org_chart where deleted_status=0 "
				+ " and org_type_id=3 order by org_name";
	}else{
		qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3 order by org_name";
	}
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

@SuppressWarnings("rawtypes")
public List getDepotId(int parentID, String orgchart_id) {
	List list = new ArrayList();
	String qry = "";
	if(parentID==1){
		qry = "select org_chart_id from org_chart where deleted_status=0 "
				+ " and org_type_id=3 order by org_name";
	}else{
		qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3 order by org_name";
	}
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
@SuppressWarnings({ "rawtypes", "unchecked" })
public List getDepotName(int parentID, String org_chart_id) {
	List list = new ArrayList();
String qry = "";
	
	if(parentID==1){
		qry = "select org_name from org_chart where deleted_status=0 "
				+ " and org_type_id=3 order by org_name";
	}else{
		qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3 order by org_name";
	}
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

public int getTotalRecordsAll(String col,String dir,String type,String depotid,String division_id){
	HttpServletRequest request = ServletActionContext.getRequest();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	int cnt=0;
	String ordchartcompare="";
	try{
		int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
		int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
	/*Query	 query= session.createSQLQuery("select count(*) as count  from employee where  ( EMPLOYEE_ID like '"+search_term+"%' OR EMPLOYEE_NAME like '"+search_term+"%'  OR EMPLOYEE_DESIGNATION like '"+search_term+"%' OR PF like '"+search_term+"%' OR GENDER like '"+search_term+"%' OR WORKING_DEPOT like '"+search_term+"%' OR WORKING_DEPT like '"+search_term+"%' OR TOKEN like '"+search_term+"%' OR DL_EXPIRY_DT like '"+search_term+"%' OR DRIVING_LC_NO like '"+search_term+"%' OR DOB like '"+search_term+"%'  OR CELL_PHONE like '"+search_term+"%' OR EMAIL like '"+search_term+"%' OR RETIREMENT_DT like '"+search_term+"%' OR FATHER_NAME like '"+search_term+"%' OR ADDRESS like '"+search_term+"%' OR designation_type.designation_type_name like '"+search_term+"%' OR org_type.org_type like '"+search_term+"%' OR org_chart.org_name like '"+search_term+"%' )  order by EMPLOYEE_ID").addScalar("count", Hibernate.INTEGER);
	List<Integer> list = query.list();
	cnt = list.get(0);*/
		String designation="";
		String depot_id="";
		if(type=="0" || type.equalsIgnoreCase("0")){
			designation="";
		}else{
			designation=" and sm.group_id = '"+type+"'";
		}
		if(depotid=="1" || depotid.equalsIgnoreCase("1")){
			depot_id=" division_id="+division_id+"";
		}else{
			depot_id=" depot_id="+depotid+"";
		}
		String sql="";
//		sql = " select EMPLOYEE_ID,IFNULL(employee.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME, " +
//			"IFNULL(CELL_PHONE,'NA')CELL_PHONE, " +
//			"IFNULL(org_chart.org_name,'NA')org_name " +
//			" from employee " +
//			" LEFT JOIN designation_type ON employee.WORKING_DESIGNATION = designation_type.designation_type_id " +
//			" LEFT JOIN org_chart ON employee.org_chart_id=org_chart.org_chart_id where employee.status='Active' "+depot_id+" "+designation+"  order by org_name ";

		sql = " select employee_name,group_name,mobile,depot_name from sms_employee sm "+
			  " inner join sms_group sg on sg.group_id=sm.group_id "+
			  " where "+depot_id+" "+designation+" and sg.status='Y' ";
		
		
		
		//		if(orgtTypeId!=0 && orgtTypeId==1){
//			ordchartcompare="";
//		}else if(orgtTypeId!=0 && orgtTypeId==2){
//			ordchartcompare="and employee.org_type_id='"+orgtTypeId+"'";
//		}
//		else if(orgtTypeId!=0 && orgtTypeId==3){
//			ordchartcompare="and employee.org_chart_id='"+orgchartid+"'";
//		}
//			sql += ordchartcompare;		
		
		String search_term1 = request.getParameter("sSearch");
		////System.out.println("search_term-------"+search_term1);
//		if (search_term1 != null && !search_term1.equals("")) {
//			String search_term = request.getParameter("sSearch").trim();
////		
//			sql += " OR org_chart.org_name like '%"+search_term+"%'";
//			sql += " OR TOKEN like '%"+search_term+"%')";
//			
//		}
		
//		if(!col.equals("")){
//			 if(dir.equals("asc")){
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) asc";
//				}else
//			  sql += " order by "+col+" asc";
//			}else{
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) desc";
//				}
//				else
//				sql += " order by "+col+" desc";
//			}
//		}
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		cnt=aliasToValueMapList.size();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
	}
	
	return cnt;
}



@SuppressWarnings("unchecked")
public JSONObject getAllList(int total, HttpServletRequest request,String col,String dir,String type,String depotid,String division_id){
	int totalAfterFilter = total;
	JSONObject result = new JSONObject();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	String employeenamekannda="";
	String ordchartcompare="";
	try{
		int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
		int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		String designation="";
		String depot_id="";
		if(type=="0" || type.equalsIgnoreCase("0")){
			designation="";
		}else{
			if(division_id=="1" || division_id.equals("1")){
				designation=" sm.group_id = '"+type+"' and ";
			}else{
			designation=" and sm.group_id = '"+type+"' and ";
			}
		}
		
		
		
		
		if(depotid=="1" || depotid.equalsIgnoreCase("1")){
			
			if(division_id=="1" || division_id.equals("1")){
				depot_id="";
			}else{
				depot_id="division_id="+division_id+"";
			}
		}else{
			depot_id="depot_id="+depotid+"";
		}
		
		String sql="";
//		sql = " select EMPLOYEE_ID,IFNULL(employee.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME,EMPLOYEE_DESIGNATION,designation_type_id, " +
//				"IFNULL(CELL_PHONE,'NA')CELL_PHONE, " +
//				"IFNULL(org_chart.org_name,'NA')org_name " +
//				" from employee " +
//				" LEFT JOIN designation_type ON employee.WORKING_DESIGNATION = designation_type.designation_type_id " +
//				" LEFT JOIN org_chart ON employee.org_chart_id=org_chart.org_chart_id where employee.status='Active' "+depot_id+" "+designation+" order by org_name ";

		sql = " select IFNULL(employee_name,'NA')employee_name,group_name,IFNULL(mobile,'NA') mobile,depot_name from sms_employee sm "+
				  " inner join sms_group sg on sg.group_id=sm.group_id "+
				  " where "+depot_id+" "+designation+" sg.status='Y' ";
			
		
		
		//		if(orgtTypeId!=0 && orgtTypeId==1){
//			ordchartcompare = "  ";
//		}else if(orgtTypeId!=0 && orgtTypeId==2){
//			ordchartcompare = " and employee.org_type_id='"+orgtTypeId+"'  ";
//		}
//		else if(orgtTypeId!=0 && orgtTypeId==3){
//			ordchartcompare = " and employee.org_chart_id='"+orgchartid+"'  ";
//		}
	//	sql += ordchartcompare +" AND employee.status='Active'";	
//		sql += ordchartcompare;	
//		String search_term1 = request.getParameter("sSearch");
		////System.out.println("search_term-------"+search_term1);
//		if (search_term1 != null && !search_term1.equals("")) {
//			String search_term = request.getParameter("sSearch").trim();
//			
//			sql += " and  ( EMPLOYEE_NAME like '%"+search_term+"%'";
//
//			sql += " OR org_chart1.org_name like '%"+search_term+"%'";
//			sql += " OR TOKEN like '%"+search_term+"%')";
//			
//		}
		
//		if(!col.equals("")){
//			 if(dir.equals("asc")){
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) asc";
//				}else
//			  sql += " order by "+col+" asc";
//			}else{
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) desc";
//				}
//				else
//				sql += " order by "+col+" desc";
//			}
//		}
//	
//		sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		
	////System.out.println("sql----------"+sql);
	Query query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	   
	System.out.println("aliasToValueMapList.size()==="+aliasToValueMapList.size());
	JSONArray array = new JSONArray();
	for(int i=0;i<aliasToValueMapList.size();i++){
		Map<String, Object> rs = aliasToValueMapList.get(i);
		JSONArray ja = new JSONArray();
		ja.add("&nbsp;<input type='checkbox' class='driverId' id='logid' name='logid' value='"+ rs.get("mobile")+"'/>");
		ja.add(rs.get("employee_name").toString());
		ja.add(rs.get("group_name").toString());
		ja.add(rs.get("mobile").toString());
		ja.add(rs.get("depot_name").toString());
		array.add(ja);
	}
	
	result.put("aaData", array);
	System.out.println("array.size()==="+array.size());
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if (session != null) {
			//session.close();
		}
	}
	return result;
	
}



}
