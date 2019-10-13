package com.trimax.its.usermanagement.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.BreakType;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.usermanagement.model.DesignationType;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.usermanagement.model.EmployeeTransfer;
import com.trimax.its.usermanagement.model.EmployeeType;
import com.trimax.its.usermanagement.model.OrgChart;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.OrganisationChart;

public class EmployeeDAO {
	
	
	
		public int getTotalRecordsForEmployee(String col,String dir){
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
		/*		sql = " select EMPLOYEE_ID,IFNULL(e.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME,IFNULL(e.EMPLOYEE_NAME_KANNADA,'NA')EMPLOYEE_NAME_KANNADA, " +
					" IFNULL(EMPLOYEE_DESIGNATION,'NA')EMPLOYEE_DESIGNATION,IFNULL(TOKEN,'NA')TOKEN,IFNULL(PF,'NA')PF,IFNULL(GENDER,'NA')GENDER, " +
					" IFNULL(WORKING_DEPOT,'NA')WORKING_DEPOT,IFNULL(WORKING_DEPT,'NA') WORKING_DEPT, " +
					" IFNULL(DL_EXPIRY_DT,'NA')DL_EXPIRY_DT,IFNULL(DRIVING_LC_NO,'NA')DRIVING_LC_NO, " +
					" IFNULL(DOB,'NA')DOB,IFNULL(CELL_PHONE,'NA')CELL_PHONE, IFNULL(e.EMAIL,'NA')EMAIL,IFNULL(RETIREMENT_DT,'NA')RETIREMENT_DT, " +
					" IFNULL(FATHER_NAME,'NA')FATHER_NAME,IFNULL(e.ADDRESS,'NA')ADDRESS ,IFNULL(dt.designation_type_name,'NA')designation_type_name , "+
					" IFNULL(e.STATUS,'NA')STATUS,IFNULL(DOA,'NA')DOA,IFNULL(CONDUCTOR_LC_EXPDATE,'NA') CONDUCTOR_LC_EXPDATE, " +
					" IFNULL(ot.org_type,'NA')org_type,IFNULL(org_chart.org_name,'NA')org_name " +
					" from employee e " +
					" LEFT JOIN designation_type dt ON e.WORKING_DESIGNATION = dt.designation_type_id " +
					" LEFT JOIN org_type ot ON e.org_type_id =ot.org_type_id " +
					" LEFT JOIN org_chart oc ON e.org_chart_id=oc.org_chart_id where e.status='Active'";*/
				
				sql = " select EMPLOYEE_ID " +
						" from employee e " +
						" LEFT JOIN designation_type dt ON e.WORKING_DESIGNATION = dt.designation_type_id " +
						" LEFT JOIN org_type ot ON e.org_type_id =ot.org_type_id " +
						" LEFT JOIN org_chart oc ON e.org_chart_id=oc.org_chart_id where e.status='Active'";
				
				if(orgtTypeId!=0 && orgtTypeId==1){
					ordchartcompare="";
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					ordchartcompare="and e.org_type_id='"+orgtTypeId+"'";
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					ordchartcompare="and e.org_chart_id='"+orgchartid+"'";
				}
					sql += ordchartcompare;		
				
				String search_term1 = request.getParameter("sSearch");
				////System.out.println("search_term-------"+search_term1);
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
//				if(orgtTypeId!=0 && orgtTypeId==1){
////					sql += " where  ( employee.EMPLOYEE_ID like '"+search_term+"%'";
//						sql += " where  ( EMPLOYEE_NAME like '"+search_term+"%'";
//					}else{
//						sql += " and  ( employee.EMPLOYEE_ID like '"+search_term+"%'";	
						//sql += " where  ( EMPLOYEE_NAME like '%"+search_term+"%'";
						sql += " and  ( EMPLOYEE_NAME like '%"+search_term+"%'";
//					}
//					sql += " OR EMPLOYEE_NAME_KANNADA like '"+search_term+"%'";
//					sql += " OR EMPLOYEE_DESIGNATION like '"+search_term+"%'";
//					sql += " OR PF like '"+search_term+"%'";
//					sql += " OR GENDER like '"+search_term+"%'";
//					sql += " OR WORKING_DEPOT like '"+search_term+"%'";
//					sql += " OR WORKING_DEPT like '"+search_term+"%'";
//					sql += " OR DL_EXPIRY_DT like '"+search_term+"%'";
//					sql += " OR DRIVING_LC_NO like '"+search_term+"%'";
//					sql += " OR DOB like '"+search_term+"%'";
//					sql += " OR CELL_PHONE like '"+search_term+"%'";
//					sql += " OR employee.EMAIL like '"+search_term+"%'";
//					sql += " OR RETIREMENT_DT like '"+search_term+"%'";
//					sql += " OR FATHER_NAME like '"+search_term+"%'";
//					sql += " OR employee.ADDRESS like '"+search_term+"%'";
//					sql += " OR designation_type.designation_type_name like '"+search_term+"%'";
//					sql += " OR employee.STATUS like '"+search_term+"%'";
//					sql += " OR DOA like '"+search_term+"%'";
//					sql += " OR CONDUCTOR_LC_EXPDATE like '"+search_term+"%'";
//					sql += " OR org_type.org_type like '"+search_term+"%'";
					sql += " OR oc.org_name like '%"+search_term+"%'";
					sql += " OR TOKEN like '%"+search_term+"%')";
					
				}
				
		/*		if(!col.equals("")){
					 if(dir.equals("asc")){
						if(col.equals("TOKEN")){
							sql += " order by CAST("+col+" AS UNSIGNED) asc";
						}else
					  sql += " order by "+col+" asc";
					}else{
						if(col.equals("TOKEN")){
							sql += " order by CAST("+col+" AS UNSIGNED) desc";
						}
						else
						sql += " order by "+col+" desc";
					}
				}*/
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
	public JSONObject getEmployeeList(int total, HttpServletRequest request,String col,String dir){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String employeenamekannda="";
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			
			String sql="";
			sql = " select IFNULL(employee.effective_end_date,'') effective_end_date,employee.EMPLOYEE_ID,IFNULL(employee.EMPLOYEE_NAME,'NA')EMPLOYEE_NAME,IFNULL(employee.EMPLOYEE_NAME_KANNADA,'NA')EMPLOYEE_NAME_KANNADA, " +
				" IFNULL(EMPLOYEE_DESIGNATION,'NA')EMPLOYEE_DESIGNATION,IFNULL(TOKEN,'NA')TOKEN,IFNULL(PF,'NA')PF,IFNULL(GENDER,'NA')GENDER, " +
				" IFNULL(WORKING_DEPOT,'NA')WORKING_DEPOT,IFNULL(WORKING_DEPT,'NA') WORKING_DEPT, " +
				" IFNULL(DL_EXPIRY_DT,'NA')DL_EXPIRY_DT,IFNULL(DRIVING_LC_NO,'NA')DRIVING_LC_NO, " +
				" IFNULL(DOB,'NA')DOB,IFNULL(CELL_PHONE,'NA')CELL_PHONE, IFNULL(employee.EMAIL,'NA')EMAIL,IFNULL(RETIREMENT_DT,'NA')RETIREMENT_DT, " +
				" IFNULL(FATHER_NAME,'NA')FATHER_NAME,IFNULL(employee.ADDRESS,'NA')ADDRESS ,IFNULL(designation_type.designation_type_name,'NA')designation_type_name , "+
				" IFNULL(employee.STATUS,'NA')STATUS,IFNULL(DOA,'NA')DOA,IFNULL(CONDUCTOR_LC_EXPDATE,'NA') CONDUCTOR_LC_EXPDATE, " +
				" IFNULL(org_type.org_type,'NA')org_type,IFNULL(org_chart1.org_name,'NA')org_name, " +
				" IFNULL(employee_type.employee_type_name,'NA')employee_type_name,IFNULL(org_chart2.org_name,'NA')org_name1 " +
				" from employee " +
				" LEFT JOIN designation_type ON employee.WORKING_DESIGNATION = designation_type.designation_type_id " +
				" LEFT JOIN org_type ON employee.org_type_id =org_type.org_type_id " +
				" LEFT JOIN org_chart as org_chart1 ON employee.org_chart_id=org_chart1.org_chart_id "+
				" LEFT JOIN org_chart as org_chart2 ON employee.DIVISION_ID=org_chart2.org_chart_id "+
				" LEFT JOIN employee_type ON employee.EMPLOYEE_TYPE_NAME=employee_type.employee_id where employee.status='Active' ";
			if(orgtTypeId!=0 && orgtTypeId==1){
				ordchartcompare = "  ";
			}else if(orgtTypeId!=0 && orgtTypeId==2){
				ordchartcompare = " and employee.org_type_id='"+orgtTypeId+"'  ";
			}
			else if(orgtTypeId!=0 && orgtTypeId==3){
				ordchartcompare = " and employee.org_chart_id='"+orgchartid+"'  ";
			}
		//	sql += ordchartcompare +" AND employee.status='Active'";	
			sql += ordchartcompare;	
			String search_term1 = request.getParameter("sSearch");
			////System.out.println("search_term-------"+search_term1);
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
//				if(orgtTypeId!=0 && orgtTypeId==1){
////				sql += " where  ( employee.EMPLOYEE_ID like '"+search_term+"%'";
//					sql += " where  ( EMPLOYEE_NAME like '"+search_term+"%'";
//				}else{
//					sql += " and  ( employee.EMPLOYEE_ID like '"+search_term+"%'";	
				//	sql += " where  ( EMPLOYEE_NAME like '"+search_term+"%'";
				sql += " and  ( EMPLOYEE_NAME like '%"+search_term+"%'";
//				}
				
				//EMPLOYEE_NAME_KANNADA
//				sql += " OR EMPLOYEE_NAME_KANNADA like '"+search_term+"%'";
//				sql += " OR EMPLOYEE_DESIGNATION like '"+search_term+"%'";
//				sql += " OR PF like '"+search_term+"%'";
//				sql += " OR GENDER like '"+search_term+"%'";
//				sql += " OR WORKING_DEPOT like '"+search_term+"%'";
//				sql += " OR WORKING_DEPT like '"+search_term+"%'";
//				sql += " OR DL_EXPIRY_DT like '"+search_term+"%'";
//				sql += " OR DRIVING_LC_NO like '"+search_term+"%'";
//				sql += " OR DOB like '"+search_term+"%'";
//				sql += " OR CELL_PHONE like '"+search_term+"%'";
//				sql += " OR employee.EMAIL like '"+search_term+"%'";
//				sql += " OR RETIREMENT_DT like '"+search_term+"%'";
//				sql += " OR FATHER_NAME like '"+search_term+"%'";
//				sql += " OR employee.ADDRESS like '"+search_term+"%'";
//				sql += " OR designation_type.designation_type_name like '"+search_term+"%'";
//				sql += " OR employee.STATUS like '"+search_term+"%'";
//				sql += " OR DOA like '"+search_term+"%'";
//				sql += " OR CONDUCTOR_LC_EXPDATE like '"+search_term+"%'";
//				sql += " OR org_type.org_type like '"+search_term+"%'";
				sql += " OR org_chart1.org_name like '%"+search_term+"%'";
				sql += " OR TOKEN like '%"+search_term+"%')";
				
			}
			
			if(!col.equals("")){
				 if(dir.equals("asc")){
					if(col.equals("TOKEN")){
						sql += " order by CAST("+col+" AS UNSIGNED) asc";
					}else
				  sql += " order by "+col+" asc";
				}else{
					if(col.equals("TOKEN")){
						sql += " order by CAST("+col+" AS UNSIGNED) desc";
					}
					else
					sql += " order by "+col+" desc";
				}
			}
		
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			
		////System.out.println("sql----------"+sql);
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		   
		
		JSONArray array = new JSONArray();
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
					+ rs.get("EMPLOYEE_ID")
					+ "' value='"
					+ rs.get("EMPLOYEE_ID") + "'/>");
			ja.add(rs.get("EMPLOYEE_ID").toString());
			ja.add(rs.get("EMPLOYEE_NAME").toString());
			if(rs.get("EMPLOYEE_NAME_KANNADA").toString().trim().length()>0){
				//System.out.println("Test");
			 employeenamekannda=getkanndaname(rs.get("EMPLOYEE_NAME_KANNADA").toString());
			 //System.out.println("employeenamekannda-------------"+employeenamekannda);
			}else{
				//System.out.println("Test22222");
				employeenamekannda="";
			}
			ja.add(employeenamekannda);
			ja.add(rs.get("EMPLOYEE_DESIGNATION").toString());
			ja.add(rs.get("PF").toString());
			ja.add(rs.get("TOKEN").toString());			
			ja.add(rs.get("GENDER").toString());
			ja.add(rs.get("WORKING_DEPOT").toString());
			ja.add(rs.get("WORKING_DEPT").toString());
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-mm-yyyy");
			if(rs.get("DL_EXPIRY_DT").toString().equalsIgnoreCase("NA")){
				ja.add("");
			}else{
			String dateInString =rs.get("DL_EXPIRY_DT").toString()  ;
			Date date = formatter.parse(dateInString);
			ja.add(dateformatJava.format(date));
			}
			ja.add(rs.get("DRIVING_LC_NO").toString());
			if(rs.get("DOB").toString().equalsIgnoreCase("NA")){
				ja.add("");
			}else{
				String dateInString1 =rs.get("DOB").toString()  ;
				Date date1 = formatter.parse(dateInString1);
			ja.add(dateformatJava.format(date1));
			}
			ja.add(rs.get("CELL_PHONE").toString());
			ja.add(rs.get("EMAIL").toString());
			if(rs.get("RETIREMENT_DT").toString().equalsIgnoreCase("NA")){
				ja.add("");
			}else{
				String dateInString2 =rs.get("RETIREMENT_DT").toString()  ;
				Date date2 = formatter.parse(dateInString2);
			ja.add(dateformatJava.format(date2));
			}
			ja.add(rs.get("FATHER_NAME").toString());
			ja.add(rs.get("ADDRESS").toString());
			ja.add(rs.get("STATUS").toString());
			if(rs.get("DOA").toString().equalsIgnoreCase("NA")){
				ja.add("");
			}else{
				String dateInString3 =rs.get("DOA").toString()  ;
				Date date3 = formatter.parse(dateInString3);
			ja.add(dateformatJava.format(date3));
			}
			if(rs.get("CONDUCTOR_LC_EXPDATE").toString().equalsIgnoreCase("NA")){
				ja.add("");
			}else{

				String dateInString4=rs.get("CONDUCTOR_LC_EXPDATE").toString()  ;
				Date date4 = formatter.parse(dateInString4);
			ja.add(dateformatJava.format(date4));
			}
			ja.add(rs.get("employee_type_name").toString());
			if(rs.get("org_name1").toString().equalsIgnoreCase("NA")){
				ja.add(rs.get("org_name").toString());
			}
			ja.add(rs.get("org_name1").toString());
//			ja.add(rs.get("effective_end_date").toString());
			array.add(ja);
			
		}
		int cnt=0;
			//totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			
				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalRecordsForEmployee(col,dir);//getTotalRecordsForCount(search_term3);
		
			result.put("iTotalRecords",cnt);
			
			result.put("iTotalDisplayRecords", cnt);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				//session.close();
			}
		}
		return result;
		
	}
	
	
	public String getkanndaname(String org_name_kannada) {
		/*//System.out.println("org_name_kannada--------"+org_name_kannada);
		
		String out = "";
		
	    
	    org_name_kannada = org_name_kannada.replace(" ", "");
		String[] s = org_name_kannada.split(";");
		for(int i=0; i<s.length; i++)
	    {
			
			String s1= s[i].replace("#", "");
			s1= s1.replace("&", "");
			s1= s1.trim();
	        char c = (char)Integer.parseInt(s1);
	      //System.out.println(s.length+""+c+"----"+s1);
	           out+=""+c;
	        
	    }
		//System.out.println("out---"+out.toString());
		//this.org_name_kannada = out.toString();
		return out.toString();*/
		
		
		StringBuffer out = new StringBuffer();
		String name;
	    for(int i=0; i<org_name_kannada.length(); i++)
	    {
	        char c = org_name_kannada.charAt(i);
	        if(c > 127 || c=='"' || c=='<' || c=='>')
	        {
	           out.append("&#"+(int)c+";");
	        }
	        else
	        {
	            out.append(c);
	        }
	    }
	   
	    name=out.toString();
	    //System.out.println("name-----"+name);
	return name;	
	}

	
	public int saveEmployee(Employee employee)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id=0;
		try {

			session.beginTransaction();
			id=(Integer) session.save(employee);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally {
			  if(session!=null){ session.close(); }
				 
		}
		return id;
	}
	
	public Employee getEditedempId(int parseInt) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Employee employee = (Employee) session.get(Employee.class, new Integer(
				parseInt));
		
		return employee;
	}
	public Map<Integer, String> getDesignation() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `designation_type_id`, `designation_type_name`FROM `designation_type`WHERE `status` = 'active' AND `deleted_status` = '0'").addScalar("designation_type_id", Hibernate.INTEGER).addScalar("designation_type_name", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(DesignationType.class));
			List<DesignationType> list = query.list();
			resultMap.put(0, "Select");
			System.out.println("Size of Schedule List : "+list.size());
			for (DesignationType type : list) {
				resultMap.put(type.getDesignation_type_id(), type.getDesignation_type_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	public Map<Integer, String> getOrgtype() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `org_type_id`, `org_type` as orgType FROM `org_type`WHERE `status` = 'active' ").addScalar("org_type_id", Hibernate.INTEGER).addScalar("orgType", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(OrgType.class));
			List<OrgType> list = query.list();
			resultMap.put(0, "Select");
			System.out.println("Size of Schedule List : "+list.size());
			for (OrgType type : list) {
				resultMap.put(type.getOrg_type_id(), type.getOrgType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	public Map<Integer, String> getOrgchart() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `org_chart_id`, `org_name` FROM `org_chart` ").addScalar("org_chart_id", Hibernate.INTEGER).addScalar("org_name", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(OrgChart.class));
			List<OrgChart> list = query.list();
			resultMap.put(0, "Select");
			System.out.println("Size of Schedule List : "+list.size());
			for (OrgChart type : list) {
				resultMap.put(type.getOrg_chart_id(),type.getOrg_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	public Map<Integer, String> getEmpType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `employee_id`, `employee_type_name`FROM `employee_type`WHERE `status` = 'active' AND `deleted_status` = '0'").addScalar("employee_id", Hibernate.INTEGER).addScalar("employee_type_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			resultMap.put(0, "Select");
			System.out.println("Size of emptype List : "+aliasToValueMapList.size());
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("employee_id").toString()),rs.get("employee_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	
	public boolean updateEmployee(Employee empl, int employee_id) {
		// TODO Auto-generated method stub
		boolean returnstring=true;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			Employee employee = (Employee) session.get(Employee.class, employee_id);

			employee.setEmployee_name(empl.getEmployee_name());
			employee.setEMPLOYEE_NAME_KANNADA(empl.getEMPLOYEE_NAME_KANNADA());
			employee.setEMPLOYEE_DESIGNATION(empl.getEMPLOYEE_DESIGNATION());
			employee.setPF(empl.getPF());
			employee.setTOKEN(empl.getTOKEN());
			employee.setGENDER(empl.getGENDER());
			employee.setWORKING_DEPOT(empl.getWORKING_DEPOT());
			employee.setWORKING_DEPT(empl.getWORKING_DEPT());
			employee.setDL_EXPIRY_DT(empl.getDL_EXPIRY_DT());
			employee.setDRIVING_LC_NO(empl.getDRIVING_LC_NO());
			employee.setDOB(empl.getDOB());
			employee.setCELL_PHONE(empl.getCELL_PHONE());
			employee.setEMAIL(empl.getEMAIL());
			employee.setRETIREMENT_DT(empl.getRETIREMENT_DT());
			employee.setFATHER_NAME(empl.getFATHER_NAME());
			employee.setADDRESS(empl.getADDRESS());
			employee.setWORKING_DESIGNATION(empl.getWORKING_DESIGNATION());
			employee.setSTATUS(empl.getSTATUS());
			employee.setDOA(empl.getDOA());
			employee.setCONDUCTOR_LC_EXPDATE(empl.getCONDUCTOR_LC_EXPDATE());
			//System.out.println("orgtype in form--"+empl.getOrgtype().getOrg_type_id());
			//System.out.println("orgchart in form--"+empl.getOrgchart().getOrg_chart_id());
			//System.out.println("orgtype in db--"+employee.getOrgtype().getOrg_type_id());
			//System.out.println("orgchart in db--"+employee.getOrgchart().getOrg_chart_id());
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String userid=request.getSession().getAttribute("userid").toString();
			if(empl.getOrgtype().getOrg_type_id()==employee.getOrgtype().getOrg_type_id() 
					&& empl.getOrgchart().getOrg_chart_id()==employee.getOrgchart().getOrg_chart_id()){
				employee.setUpdated_by(userid);
				employee.setUpdated_date(new java.util.Date());
				employee.setEffective_end_date(null);
			}
			else{
				employee.setUpdated_by(userid);
				employee.setUpdated_date(new java.util.Date());
				employee.setEffective_end_date(new java.util.Date());
			}
			employee.setOrgtype(empl.getOrgtype());
			employee.setOrgchart(empl.getOrgchart());
			employee.setEMPLOYEE_TYPE_NAME(empl.getEMPLOYEE_TYPE_NAME());
			employee.setDIVISION_ID(empl.getDIVISION_ID());
			
			session.update(employee);
			session.getTransaction().commit();
		} catch (Exception ex) {
			returnstring=false;
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return returnstring;
	}
	
	
	public boolean updateEmployeeAction(Employee empl, int employee_id) {
		// TODO Auto-generated method stub
		boolean returnstring=true;
		Common common =new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userid=request.getSession().getAttribute("userid").toString();
		System.out.println("employee_id===="+employee_id);
		try {
			//
			Employee employee = (Employee) session.get(Employee.class, employee_id);
			if(empl.getWORKING_DESIGNATION().getDesignation_type_id()!=23){
		/*	if(empl.getOrgchart().getOrg_chart_id()==employee.getOrgchart().getOrg_chart_id()){
			employee.setEmployee_name(empl.getEmployee_name());
			employee.setEMPLOYEE_NAME_KANNADA(empl.getEMPLOYEE_NAME_KANNADA());
			employee.setEMPLOYEE_DESIGNATION(empl.getEMPLOYEE_DESIGNATION());
			employee.setPF(empl.getPF());
			employee.setTOKEN(empl.getTOKEN());
			employee.setGENDER(empl.getGENDER());
			employee.setWORKING_DEPOT(empl.getWORKING_DEPOT());
			employee.setWORKING_DEPT(empl.getWORKING_DEPT());
			employee.setDL_EXPIRY_DT(empl.getDL_EXPIRY_DT());
			employee.setDRIVING_LC_NO(empl.getDRIVING_LC_NO());
			employee.setDOB(empl.getDOB());
			employee.setCELL_PHONE(empl.getCELL_PHONE());
			employee.setEMAIL(empl.getEMAIL());
			employee.setRETIREMENT_DT(empl.getRETIREMENT_DT());
			employee.setFATHER_NAME(empl.getFATHER_NAME());
			employee.setADDRESS(empl.getADDRESS());
			employee.setWORKING_DESIGNATION(empl.getWORKING_DESIGNATION());
			employee.setSTATUS(empl.getSTATUS());
			employee.setDOA(empl.getDOA());
			employee.setCONDUCTOR_LC_EXPDATE(empl.getCONDUCTOR_LC_EXPDATE());
			employee.setConductor_RENEWAL_DT(empl.getConductor_RENEWAL_DT());
			employee.setDriver_RENEWAL_DT(empl.getDriver_RENEWAL_DT());
			employee.setADHAR_CARD_NO(empl.getADHAR_CARD_NO());
			//System.out.println("orgtype in form--"+empl.getOrgtype().getOrg_type_id());
			//System.out.println("orgchart in form--"+empl.getOrgchart().getOrg_chart_id());
			//System.out.println("orgtype in db--"+employee.getOrgtype().getOrg_type_id());
			//System.out.println("orgchart in db--"+employee.getOrgchart().getOrg_chart_id());
			
			
			
				employee.setUpdated_by(userid);
				employee.setUpdated_date(new java.util.Date());
				//employee.setEffective_end_date(new java.util.Date());
				
				employee.setOrgtype(empl.getOrgtype());
				employee.setOrgchart(empl.getOrgchart());
				employee.setEMPLOYEE_TYPE_NAME(empl.getEMPLOYEE_TYPE_NAME());
				employee.setDIVISION_ID(empl.getDIVISION_ID());
				
				session.update(employee);
			}
			else {
				employee.setUpdated_by(userid);
				employee.setUpdated_date(new java.util.Date());
				//employee.setEffective_end_date(new java.util.Date());
				//employee.setSTATUS("Transferred");
				//session.update(employee);
				//System.out.println("hiii");
				session.update(employee);//its for createing new row
//				empl.setCreated_by(userid);
//				empl.setCreated_date(new java.util.Date());
//				empl.setEffective_end_date(null);
//				session.save(empl);
				EmployeeTransfer et=new EmployeeTransfer();
				et.setEmployeeId(employee.getEmployee_id());
				et.setFromDepotId(employee.getOrgchart().getOrg_chart_id());
				et.setToDepotId(empl.getOrgchart().getOrg_chart_id());
				et.setUpdateBy(Integer.parseInt(userid));
				et.setUpdatedDate(new java.util.Date());
				session.save(et);
			}*/
				
				employee.setEmployee_name(empl.getEmployee_name());
				employee.setEMPLOYEE_NAME_KANNADA(empl.getEMPLOYEE_NAME_KANNADA());
				employee.setEMPLOYEE_DESIGNATION(empl.getEMPLOYEE_DESIGNATION());
				employee.setPF(empl.getPF());
				employee.setTOKEN(empl.getTOKEN());
				employee.setGENDER(empl.getGENDER());
				employee.setWORKING_DEPOT(empl.getWORKING_DEPOT());
				employee.setWORKING_DEPT(empl.getWORKING_DEPT());
				employee.setDL_EXPIRY_DT(empl.getDL_EXPIRY_DT());
				employee.setDRIVING_LC_NO(empl.getDRIVING_LC_NO());
				employee.setDOB(empl.getDOB());
				employee.setCELL_PHONE(empl.getCELL_PHONE());
				employee.setEMAIL(empl.getEMAIL());
				employee.setRETIREMENT_DT(empl.getRETIREMENT_DT());
				employee.setFATHER_NAME(empl.getFATHER_NAME());
				employee.setADDRESS(empl.getADDRESS());
				employee.setWORKING_DESIGNATION(empl.getWORKING_DESIGNATION());
				employee.setSTATUS(empl.getSTATUS());
				employee.setDOA(empl.getDOA());
				employee.setCONDUCTOR_LC_EXPDATE(empl.getCONDUCTOR_LC_EXPDATE());
				employee.setConductor_RENEWAL_DT(empl.getConductor_RENEWAL_DT());
				employee.setDriver_RENEWAL_DT(empl.getDriver_RENEWAL_DT());
				employee.setADHAR_CARD_NO(empl.getADHAR_CARD_NO());
				//System.out.println("orgtype in form--"+empl.getOrgtype().getOrg_type_id());
				//System.out.println("orgchart in form--"+empl.getOrgchart().getOrg_chart_id());
				//System.out.println("orgtype in db--"+employee.getOrgtype().getOrg_type_id());
				//System.out.println("orgchart in db--"+employee.getOrgchart().getOrg_chart_id());
				
				
				
					employee.setUpdated_by(userid);
					employee.setUpdated_date(new java.util.Date());
					//employee.setEffective_end_date(new java.util.Date());
					
					
					
					session.update(employee);
					System.out.println("chart comparision--------"+empl.getOrgchart().getOrg_chart_id()+"========="+employee.getOrgchart().getOrg_chart_id());
					if(empl.getOrgchart().getOrg_chart_id()!=employee.getOrgchart().getOrg_chart_id()){
						EmployeeTransfer et=new EmployeeTransfer();
						et.setEmployeeId(employee.getEmployee_id());
						et.setFromDepotId(employee.getOrgchart().getOrg_chart_id());
						et.setToDepotId(empl.getOrgchart().getOrg_chart_id());
						et.setUpdateBy(Integer.parseInt(userid));
						et.setUpdatedDate(new java.util.Date());
						session.save(et);
					}
					employee.setOrgtype(empl.getOrgtype());
					employee.setOrgchart(empl.getOrgchart());
					employee.setEMPLOYEE_TYPE_NAME(empl.getEMPLOYEE_TYPE_NAME());
					employee.setDIVISION_ID(empl.getDIVISION_ID());
					session.update(employee);
					
			
			}else{/*
				
				
				if(empl.getOrgchart().getOrg_chart_id()==employee.getOrgchart().getOrg_chart_id()){
					employee.setEmployee_name(empl.getEmployee_name());
					employee.setEMPLOYEE_NAME_KANNADA(empl.getEMPLOYEE_NAME_KANNADA());
					employee.setEMPLOYEE_DESIGNATION(empl.getEMPLOYEE_DESIGNATION());
					employee.setPF(empl.getPF());
					employee.setTOKEN("");
					employee.setGENDER(empl.getGENDER());
					employee.setWORKING_DEPOT(empl.getWORKING_DEPOT());
					employee.setWORKING_DEPT(empl.getWORKING_DEPT());
					employee.setDL_EXPIRY_DT(null);
					employee.setDRIVING_LC_NO("");
					employee.setDOB(empl.getDOB());
					employee.setCELL_PHONE(empl.getCELL_PHONE());
					employee.setEMAIL(empl.getEMAIL());
					employee.setRETIREMENT_DT(null);
					employee.setFATHER_NAME("");
					employee.setADDRESS(empl.getADDRESS());
					employee.setWORKING_DESIGNATION(empl.getWORKING_DESIGNATION());
					employee.setSTATUS(empl.getSTATUS());
					employee.setDOA(empl.getDOA());
					employee.setCONDUCTOR_LC_EXPDATE(empl.getCONDUCTOR_LC_EXPDATE());
					employee.setConductor_RENEWAL_DT(empl.getConductor_RENEWAL_DT());
					employee.setDriver_RENEWAL_DT(empl.getDriver_RENEWAL_DT());
					employee.setADHAR_CARD_NO(empl.getADHAR_CARD_NO());
					//System.out.println("orgtype in form--"+empl.getOrgtype().getOrg_type_id());
					//System.out.println("orgchart in form--"+empl.getOrgchart().getOrg_chart_id());
					//System.out.println("orgtype in db--"+employee.getOrgtype().getOrg_type_id());
					//System.out.println("orgchart in db--"+employee.getOrgchart().getOrg_chart_id());
					
					
					
						employee.setUpdated_by(userid);
						employee.setUpdated_date(new java.util.Date());
						//employee.setEffective_end_date(new java.util.Date());
						
						employee.setOrgtype(empl.getOrgtype());
						employee.setOrgchart(empl.getOrgchart());
						employee.setEMPLOYEE_TYPE_NAME(empl.getEMPLOYEE_TYPE_NAME());
						employee.setDIVISION_ID(empl.getDIVISION_ID());
						
						session.update(employee);
					}
					else {
						employee.setUpdated_by(userid);
						employee.setUpdated_date(new java.util.Date());
//						employee.setEffective_end_date(new java.util.Date());
//						employee.setSTATUS("Transferred");                               
						//session.update(employee);
						//System.out.println("hiii");
						session.update(employee);
//						empl.setCreated_by(userid);
//						empl.setCreated_date(new java.util.Date());
//						empl.setEffective_end_date(null);
//						session.save(empl);
						EmployeeTransfer et=new EmployeeTransfer();
						et.setEmployeeId(employee.getEmployee_id());
						et.setFromDepotId(employee.getOrgchart().getOrg_chart_id());
						et.setToDepotId(empl.getOrgchart().getOrg_chart_id());
						et.setUpdateBy(Integer.parseInt(userid));
						et.setUpdatedDate(new java.util.Date());
						session.save(et);
					}
				
				
			*/
				employee.setEmployee_name(empl.getEmployee_name());
				employee.setEMPLOYEE_NAME_KANNADA(empl.getEMPLOYEE_NAME_KANNADA());
				employee.setEMPLOYEE_DESIGNATION(empl.getEMPLOYEE_DESIGNATION());
				employee.setPF(empl.getPF());
				employee.setTOKEN("");
				employee.setGENDER(empl.getGENDER());
				employee.setWORKING_DEPOT(empl.getWORKING_DEPOT());
				employee.setWORKING_DEPT(empl.getWORKING_DEPT());
				employee.setDL_EXPIRY_DT(null);
				employee.setDRIVING_LC_NO("");
				employee.setDOB(empl.getDOB());
				employee.setCELL_PHONE(empl.getCELL_PHONE());
				employee.setEMAIL(empl.getEMAIL());
				employee.setRETIREMENT_DT(null);
				employee.setFATHER_NAME("");
				employee.setADDRESS(empl.getADDRESS());
				employee.setWORKING_DESIGNATION(empl.getWORKING_DESIGNATION());
				employee.setSTATUS(empl.getSTATUS());
				employee.setDOA(empl.getDOA());
				employee.setCONDUCTOR_LC_EXPDATE(empl.getCONDUCTOR_LC_EXPDATE());
				employee.setConductor_RENEWAL_DT(empl.getConductor_RENEWAL_DT());
				employee.setDriver_RENEWAL_DT(empl.getDriver_RENEWAL_DT());
				employee.setADHAR_CARD_NO(empl.getADHAR_CARD_NO());
				//System.out.println("orgtype in form--"+empl.getOrgtype().getOrg_type_id());
				//System.out.println("orgchart in form--"+empl.getOrgchart().getOrg_chart_id());
				//System.out.println("orgtype in db--"+employee.getOrgtype().getOrg_type_id());
				//System.out.println("orgchart in db--"+employee.getOrgchart().getOrg_chart_id());
				
				
				
					employee.setUpdated_by(userid);
					employee.setUpdated_date(new java.util.Date());
					//employee.setEffective_end_date(new java.util.Date());
					
					
					
					
					if(empl.getOrgchart().getOrg_chart_id()!=employee.getOrgchart().getOrg_chart_id()){
					EmployeeTransfer et=new EmployeeTransfer();
					et.setEmployeeId(employee.getEmployee_id());
					et.setFromDepotId(employee.getOrgchart().getOrg_chart_id());
					et.setToDepotId(empl.getOrgchart().getOrg_chart_id());
					et.setUpdateBy(Integer.parseInt(userid));
					et.setUpdatedDate(new java.util.Date());
					session.save(et);
					}
					employee.setOrgtype(empl.getOrgtype());
					employee.setOrgchart(empl.getOrgchart());
					employee.setEMPLOYEE_TYPE_NAME(empl.getEMPLOYEE_TYPE_NAME());
					employee.setDIVISION_ID(empl.getDIVISION_ID());
					session.update(employee);
			}
			session.getTransaction().commit();
		} catch (Exception ex) {
			returnstring=false;
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return returnstring;
	}
	

	public String getDesignationName(DesignationType designationType) {
		//Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		List<String> list=null;
		String desgName=null;
		int desid=designationType.getDesignation_type_id();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `designation_type_name` FROM `designation_type` where designation_type_id="+desid);
							
			list = query.list();
					 for (String desName : list)
					 {
					     desgName=desName;
					 }

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		System.out.println("desgName...................>>"+desgName);
		return desgName;
	}
	public String getDeptoName(OrganisationChart organisationChart) {
		//Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		List<String> list=null;
		String deptoName=null;
		int orgid=organisationChart.getOrg_chart_id();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `org_name` FROM `org_chart` where org_chart_id="+orgid);
							
			list = query.list();
					 for (String depName : list)
					 {
						 deptoName=depName;
					 }

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		
		return deptoName;
	}
	
	public int getDivisionid(OrganisationChart organisationChart) {
		//Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		List<Integer> list=null;
		int divison=0;
		int orgid=organisationChart.getOrg_chart_id();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery("SELECT `parent_id` FROM `org_chart` where org_chart_id="+orgid);
							
			list = query.list();
					 for (Integer depName : list)
					 {
						 divison=depName;
					 }

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		
		return divison;
	}
	
	@SuppressWarnings("finally")
	public List<String> getUnitId(int orgtypeid) {

		List list = new ArrayList();
		System.out.println("inside get id");
		String qry = "select org_chart_id from org_chart where org_name!='(NULL)' and org_type_id="
				+ orgtypeid + " and deleted_status='0' order by org_chart_id";
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			System.out.println(aliasToValueMapList);
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_chart_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return list;
		}

	}

	@SuppressWarnings("finally")
	public List<String> getUnitName(int orgtypeid) {

		List list = new ArrayList();
		System.out.println("inside get id");
		String qry = "select org_name from org_chart where org_name!='(NULL)' and org_type_id="
				+ orgtypeid + " and deleted_status='0' order by org_chart_id";
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return list;
		}
	}
	
/*	
@SuppressWarnings("finally")
public int isTokenAvailable(Employee employee){
	Common common = new Common();
		int isSuccess = true;
		Session session = null;
		int count=0;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Date myDate = new Date();
			String date=new SimpleDateFormat("yyyy-MM-dd").format(myDate);
			String queryForCount = "select count(*) as count from employee where TOKEN='"+employee.getTOKEN()+"' and EMPLOYEE_DESIGNATION='"+employee.getEMPLOYEE_DESIGNATION()+"' and STATUS='ACTIVE' ";
			
			Query qury2 = session.createSQLQuery(queryForCount)
		  			.addScalar("count", Hibernate.INTEGER);
			List results = qury2.list();
			if(results.size()==0){
				count =0;
			}
			else{
				count=results.size();
			}
//			int count = common.getDBResultInt(session, queryForCount, "count");
			System.out.println("count................"+count);
			if(count > 0){
				isSuccess = 0;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = 1;
		}finally{
			if(session!=null){
				session.close();
			}
			return isSuccess;
		}
	}*/
@SuppressWarnings("finally")
public boolean isTokenAvailable(Employee employee){
	Common common = new Common();
		boolean isSuccess = true;
		Session session = null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Date myDate = new Date();
			String date=new SimpleDateFormat("yyyy-MM-dd").format(myDate);
			String queryForCount = "select count(*) as count from employee where TOKEN='"+employee.getTOKEN()+"' and EMPLOYEE_DESIGNATION='"+employee.getEMPLOYEE_DESIGNATION()+"' and STATUS='ACTIVE' ";
			int count = common.getDBResultInt(session, queryForCount, "count");
			System.out.println("count................"+count);
			if(count > 0){
				isSuccess = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = true;
		}finally{
			if(session!=null){
				session.close();
			}
			return isSuccess;
		}
	}


@SuppressWarnings("finally")
public boolean isTokenAvailable1(Employee employee){
	Common common = new Common();
		boolean isSuccess = true;
		Session session = null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Date myDate = new Date();
			String date=new SimpleDateFormat("yyyy-MM-dd").format(myDate);
			String queryForCount = "select count(*) as count from employee where TOKEN='"+employee.getTOKEN()+"' and EMPLOYEE_DESIGNATION='"+employee.getEMPLOYEE_DESIGNATION()+"'  ";
			int count = common.getDBResultInt(session, queryForCount, "count");
			System.out.println("count................"+count);
			if(count > 0){
				isSuccess = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = true;
		}finally{
			if(session!=null){
				session.close();
			}
			return isSuccess;
		}
	}

@SuppressWarnings("finally")
public String getMenuUserMasterID(int empid) {

	List list = new ArrayList();
	String menu_user_id="";
	System.out.println("inside get menu_user_id");
	String qry = "select user_id from  menu_user_master where emp_id="
			+ empid + " and deleted_status='0' ";
	Session session = null;
	try {
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				menu_user_id=rs.get("user_id").toString();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (session != null) {
			session.close();
		}
		return menu_user_id;
	}
}

@SuppressWarnings("finally")
public int UpdateMenuUserMasterID(String menu_user_id,int empid,int org_chart_id) {

	List list = new ArrayList();
	int count=0;
	System.out.println("inside Update menu_user_id");
	
	
	
	
	
	Session session = null;
	try {
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String sql= "update menu_user_master set  emp_id="+empid+",org_chart_id="+org_chart_id+ "" +
				" where user_id = "+menu_user_id+" and status='ACTIVE' and deleted_status=0"; 
        Query qury = session.createSQLQuery(sql);
	
	count=qury.executeUpdate();
	System.out.println("count======"+count);
	if(count>0){
	
		session.getTransaction().commit();
	}
	
	
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (session != null) {
			session.close();
		}
		return count;
	}
}



}