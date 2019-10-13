package com.trimax.its.report.dao;

import java.text.SimpleDateFormat;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.its.common.Common;
import com.trimax.its.report.model.DutyRota;
import com.trimax.its.util.HibernateUtil;



public class EmployeeDutyTypeDAO {
	private Session session;
	
	
	public int getTotalRecords(String orgchartid,String designationId,HttpServletRequest request,String col,String dir){
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		JSONObject result=new JSONObject();
		String str="";
		Query query = null;
		try{
           if(orgchartid.equalsIgnoreCase("1")){
        	   
        	   str="SELECT `EMPLOYEE_ID`, `EMPLOYEE_NAME`, `TOKEN` FROM `employee` " +
   					"WHERE `WORKING_DESIGNATION` = '"+designationId+"' AND `STATUS` = 'ACTIVE' " +
   					//"AND `org_chart_id` = '10' " +
   					"and  " +
   					"EMPLOYEE_ID not in (select emd_id from dutyrota where effective_end_date is null or effective_end_date>now())";
           }else{
			str="SELECT `EMPLOYEE_ID`, `EMPLOYEE_NAME`, `TOKEN` FROM `employee` " +
					"WHERE `WORKING_DESIGNATION` = '"+designationId+"' AND `STATUS` = 'ACTIVE' " +
					"AND `org_chart_id` = '"+orgchartid+"' " +
					"and  " +
					"EMPLOYEE_ID not in (select emd_id from dutyrota where effective_end_date is null or effective_end_date>now())";
           }
			String search_term = request.getParameter("sSearch").trim();
			if (search_term != null && !search_term.equals("")) {
				str += " and ( TOKEN like '%"+search_term+"%'";
				//str += " OR EAD.attendance like '%"+search_term+"%'";
				str += " OR EMPLOYEE_NAME like '%"+search_term+"%')";
				
			}
			
			str += " GROUP BY TOKEN ";
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				   str += " order by "+col+" asc" ;
				}else{
					str += " order by "+col+" desc";
				}
			}
		
			//str += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		
			 query=session.createSQLQuery(str)
					.addScalar("TOKEN")
					.addScalar("EMPLOYEE_NAME");
					
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasMapValueList=query.list();
			System.out.println("alsize----------->"+aliasMapValueList.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return query.list().size();
		}
	
	
	public JSONObject getEmployeeList(String orgchartid,int total,String designationId,HttpServletRequest request,String col,String dir){
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		JSONObject result=new JSONObject();
		int totalAfterFilter = total;
		String str="";
		try{
			if(orgchartid.equalsIgnoreCase("1")){
				str="SELECT `EMPLOYEE_ID`, `EMPLOYEE_NAME`,PF, `TOKEN` FROM `employee` " +
						"WHERE `WORKING_DESIGNATION` = '"+designationId+"' AND `STATUS` = 'ACTIVE' " +
						//"AND `org_chart_id` = '10' " +
						"and  " +
						"EMPLOYEE_ID not in (select emd_id from dutyrota where effective_end_date is null or effective_end_date>=DATE_FORMAT(NOW(), '%Y-%m-%d'))";
			}else{
			str="SELECT `EMPLOYEE_ID`, `EMPLOYEE_NAME`, PF,`TOKEN` FROM `employee` " +
					"WHERE `WORKING_DESIGNATION` = '"+designationId+"' AND `STATUS` = 'ACTIVE' " +
					"AND `org_chart_id` = '"+orgchartid+"' " +
					"and  " +
					"EMPLOYEE_ID not in (select emd_id from dutyrota where effective_end_date is null or effective_end_date>=DATE_FORMAT(NOW(), '%Y-%m-%d'))";
		}
					String search_term = request.getParameter("sSearch").trim();
					if (search_term != null && !search_term.equals("")) {
						str += " and ( TOKEN like '%"+search_term+"%'";
						//str += " OR EAD.attendance like '%"+search_term+"%'";
						str += " OR EMPLOYEE_NAME like '%"+search_term+"%')";
						
					}
					
					str += " GROUP BY TOKEN ";
								
			if(!col.equals("")){
				if(dir.equals("asc")){
				   str += " order by "+col+" asc" ;
				}else{
					str += " order by "+col+" desc";
				}
			}
			//else {str += " order by token asc" ;}
		
			str += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		
			Query query=session.createSQLQuery(str)
					.addScalar("TOKEN")
					.addScalar("EMPLOYEE_NAME")
					.addScalar("PF")
					.addScalar("EMPLOYEE_ID");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasMapValueList=query.list();
			System.out.println("alsize----------->"+aliasMapValueList.size());
			    
			String dutytypes=getdutytypes().toString();
			String dutytypesection=getdutysection().toString();
			String dutybatch=getbatch().toString();
			JSONArray array=new JSONArray();
			for(int i=0;i<aliasMapValueList.size();i++){
				Map<String, Object> rs=aliasMapValueList.get(i);
				JSONArray ja=new JSONArray();

		/*		ja.add("<input type='hidden'  id='empid1' name='empid1' readonly='readonly' value='"+ rs.get("EMPLOYEE_ID")+ "'/>"+rs.get("TOKEN")); 
				ja.add("<input type='hidden'  id='pf' name='pf' readonly='readonly' value='"+ rs.get("PF")+ "'/>"+rs.get("PF")); 

				ja.add(rs.get("EMPLOYEE_NAME"));
			    ja.add(" <select id='"+rs.get("EMPLOYEE_ID")+"dtype' name='dtype' onchange=desdrop(this.id,this.value);> <option value='0'>--select--</option><option value='GS1'>GS1</option><option value='GS2'>GS2</option><option value='NS'>NS</option> <option value='SS'>SS</option><option value='GS(08:00)'>GS(08:00)</option><option value='GS(10:00)'>GS(10:00)</option><option value='FT'>FT</option></select> ");
			    ja.add(" <select id='"+rs.get("EMPLOYEE_ID")+"wo1'  name='wo1'> <option value='0'>--select--</option><option value='sunday'>Sunday</option><option value='monday'>Monday</option><option value='tuesday'>Tuesday</option><option value='wednesday'>Wednesday</option>" +
			    		"<option value='thursday'>Thursday</option><option value='friday'>Friday</option><option value='saturday'>Saturday</option> ");
			    ja.add(" <select id='"+rs.get("EMPLOYEE_ID")+"dtype1' name='dtype1' disabled> <option value='0'>--select--</option><option value='GS2'>GS2</option><option value='NS'>NS</option></select> ");

			    ja.add(" <select id='"+rs.get("EMPLOYEE_ID")+"wo2' name='wo2'><option value='0'>--select--</option> <option value='sunday'>Sunday</option><option value='monday'>Monday</option><option value='tuesday'>Tuesday</option><option value='wednesday'>Wednesday</option>" +
			    		"<option value='thursday'>Thursday</option><option value='friday'>Friday</option><option value='saturday'>Saturday</option> ");
				*/
				
				
				ja.add("<input type='hidden'  id='empid1' name='empid1' readonly='readonly' value='"+ rs.get("EMPLOYEE_ID")+ "'/>"+rs.get("TOKEN")); 
				ja.add("<input type='hidden'  id='pf' name='pf' readonly='readonly' value='"+ rs.get("PF")+ "'/>"+rs.get("PF")); 

				ja.add(rs.get("EMPLOYEE_NAME"));
			    ja.add(" <select id='"+rs.get("EMPLOYEE_ID")+"section' name='dutysection' > <option value='0'>--select--</option>"+dutytypesection+"</select> ");
			    ja.add(" <select id='"+rs.get("EMPLOYEE_ID")+"batch' name='dutybatch' > <option value='0'>--select--</option>"+dutybatch+"</select> ");

			    ja.add(" <select id='"+rs.get("EMPLOYEE_ID")+"dtype' name='dtype' onchange=desdrop(this.id,this.value);> <option value='0'>--select--</option>"+dutytypes+"</select> ");
			    ja.add(" <select id='"+rs.get("EMPLOYEE_ID")+"wo1'  name='wo1'> <option value='0'>--select--</option><option value='sunday'>Sunday</option><option value='monday'>Monday</option><option value='tuesday'>Tuesday</option><option value='wednesday'>Wednesday</option>" +
			    		"<option value='thursday'>Thursday</option><option value='friday'>Friday</option><option value='saturday'>Saturday</option> ");
			    ja.add(" <input type='hidden' id='"+rs.get("EMPLOYEE_ID")+"dtype11' name='duty11'/><select id='"+rs.get("EMPLOYEE_ID")+"dtype1' name='dtype1' disabled> <option value='0'>--select--</option>"+dutytypes+"</select> ");

			    ja.add(" <select id='"+rs.get("EMPLOYEE_ID")+"wo2' name='wo2'><option value='0'>--select--</option> <option value='sunday'>Sunday</option><option value='monday'>Monday</option><option value='tuesday'>Tuesday</option><option value='wednesday'>Wednesday</option>" +
			    		"<option value='thursday'>Thursday</option><option value='friday'>Friday</option><option value='saturday'>Saturday</option> ");
				
			    
			    array.add(ja);
				
				
			}
		
				
			 result.put("aaData", array);
			 
			    String search_term1= request.getParameter("sSearch").trim();
				int cnt=getTotalRecords(orgchartid,designationId, request, col, dir);
				result.put("iTotalRecords",aliasMapValueList.size());

				result.put("iTotalDisplayRecords", cnt);
			 
			if (aliasMapValueList != null) {
				aliasMapValueList.clear();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
		return result;
		
	}
	
	public int saveList(String empid2, String section,String batch,String dtype1,String wo1,String dtype2,String wo2,String startdate,String enddate) {
		// TODO Auto-generated method stub
		boolean isSiuccess = false;
		Session session  = null;
		
		
		int i = 0;
		
		try {
		
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			 HttpServletRequest request = ServletActionContext.getRequest();
			//getAttendanceCount(month);
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			String date2=common.getDateFromPicker(enddate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	        Date currentDate=new java.util.Date();
	      
	            		
			DutyRota drota=new DutyRota();
			
			if(dtype1.equals("1") || dtype1.equals("4")) {
				drota.setWo2("");	
			}else {
			drota.setWo2(wo2);
			}
			drota.setEmd_id(empid2);
			drota.setSection(section);
			drota.setBatch(batch);
			drota.setDuty_type(dtype1);
			drota.setDuty_type1(dtype2);
			drota.setWo1(wo1);
			//drota.setWo2(wo2);
			drota.setEffective_start_date(date1);
			drota.setEffective_end_date(date2);
			drota.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			drota.setDeletedStatus(0);
			drota.setCreated_date(new java.util.Date());
			i = (Integer) session.save(drota);
			
			
			/*if(i!=0){
				isSiuccess=true;
			}*/
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();			
			return i;
		} finally {
			session.close();
		}
		return i;

	}

	
	public int getTotalRecordsForDuty(String orgchartid,String string, String sdir){
		int cnt = 0;
		Common common = new Common();
		String sql="";
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			if(orgchartid.equalsIgnoreCase("1")){
				 sql="Select count(*) count from dutyrota inner join employee " +
						"on employee.EMPLOYEE_ID=dutyrota.emd_id where employee.STATUS='Active' and dutyrota.deleted_status=0 ";
//						"AND `org_chart_id` ='"+orgchartid+"'";
			}else{
			 sql="Select count(*) count from dutyrota inner join employee " +
					"on employee.EMPLOYEE_ID=dutyrota.emd_id where employee.STATUS='Active' and dutyrota.deleted_status=0 "+ 
					"AND `org_chart_id` ='"+orgchartid+"'";
			}
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
		}
	
	
	public JSONObject getEmployeeDutyTypeList(String orgchartid,int total, HttpServletRequest request,String col,String dir){
	
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="";
		try{
			if(orgchartid.equalsIgnoreCase("1")){
			 sql="Select dutyrota_id,emd_id,TOKEN,PF,EMPLOYEE_NAME,ifnull(section_name,'')section_name,ifnull(batch_name,'')batch_name, "
			 		+ "dt.duty_type,ifnull(dt1.duty_type,'')duty_type1,wo_1,ifnull(wo_2,'')wo_2,d.effective_start_date effective_start_date,d.effective_end_date effective_end_date from dutyrota d "
			 		+ "inner join employee e " +
					"on e.EMPLOYEE_ID=d.emd_id "
					+ " inner join dutyrota_type dt on dt.dutyrotatype_id=d.duty_type "
					+ "left join dutyrota_type dt1 on dt1.dutyrotatype_id=d.duty_type1 "
					+ "left join dutyrota_section s on s.id=d.section "
					+ "left join dutyrota_batch db on db.id=d.batch "
					+ "where e.STATUS='Active' and d.deleted_status=0 ";
//					"AND `org_chart_id` ='"+orgchartid+"'";
			}else{
				 sql="Select dutyrota_id,emd_id,TOKEN,PF,EMPLOYEE_NAME,ifnull(section_name,'')section_name,ifnull(batch_name,'')batch_name, "
				 		+ "dt.duty_type,ifnull(dt1.duty_type,'')duty_type1,wo_1,ifnull(wo_2,'')wo_2,d.effective_start_date effective_start_date,d.effective_end_date effective_end_date from dutyrota d "
					 		+ "inner join employee e " +
							"on e.EMPLOYEE_ID=d.emd_id "
							+ " inner join dutyrota_type dt on dt.dutyrotatype_id=d.duty_type "
							+ "left join dutyrota_type dt1 on dt1.dutyrotatype_id=d.duty_type1 "
							+ "left join dutyrota_section s on s.id=d.section "
							+ "left join dutyrota_batch db on db.id=d.batch "
							+ "where e.STATUS='Active' and d.deleted_status=0 "+
						"AND `org_chart_id` ='"+orgchartid+"'";
			}
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (TOKEN like '%" + search_term + "%'";
				// sql += " OR gen_logsheet_id like '%"+search_term+"%'";
				// sql +=
				// " OR form_four.schedule_number_name like '"+search_term+"%'";
				 sql += " OR EMPLOYEE_NAME '%"+search_term+"%')";
				
			}
			//String col = dbcol[index];
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			} else {
				//sql += " order by generated_date desc";
			}
			if(!request.getParameter("iDisplayLength").equals("-1")) {
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			}
			Query query = session.createSQLQuery(sql)
					.addScalar("dutyrota_id", Hibernate.STRING)
					.addScalar("TOKEN", Hibernate.STRING)
					.addScalar("PF", Hibernate.STRING)
					.addScalar("EMPLOYEE_NAME", Hibernate.STRING)
					.addScalar("section_name", Hibernate.STRING)
					.addScalar("batch_name", Hibernate.STRING)
					.addScalar("emd_id", Hibernate.STRING)
					.addScalar("duty_type", Hibernate.STRING)
					.addScalar("duty_type1", Hibernate.STRING)
					.addScalar("wo_1", Hibernate.STRING)
					.addScalar("wo_2", Hibernate.STRING)
					.addScalar("effective_start_date", Hibernate.STRING)
					.addScalar("effective_end_date", Hibernate.STRING);
					
					

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				int j = i + 1;
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("emd_id").toString()
						+ "' value='"
						+ rs.get("emd_id").toString()+ "'/>");
				ja.add(rs.get("dutyrota_id").toString());
				ja.add(rs.get("TOKEN").toString());
				ja.add(rs.get("PF").toString());
				ja.add(rs.get("EMPLOYEE_NAME").toString());
				ja.add(rs.get("section_name").toString());
				ja.add(rs.get("batch_name").toString());
				ja.add(rs.get("duty_type").toString());
				ja.add(rs.get("wo_1").toString());
				ja.add(rs.get("duty_type1").toString());
				ja.add(rs.get("wo_2").toString());
				ja.add(rs.get("effective_start_date").toString());
				ja.add(rs.get("effective_end_date").toString());
				

				array.add(ja);

			}
			int cnt = 0;
			result.put("aaData", array);
			// if (search_term1 != null && !search_term1.equals("")) {
			// totalAfterFilter = getTotalRecordsForSeacrch(total, request,col,
			// dir);
			// result.put("aaData", array);
			// result.put("iTotalRecords", totalAfterFilter);
			// result.put("iTotalDisplayRecords",totalAfterFilter);
			// } else {
			totalAfterFilter = getTotalRecordsForDuty(orgchartid, col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
		
		
	}
	public 	List<Map<String, Object>> getforedit(String id) {

	
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="";
		List<Map<String, Object>> aliasToValueMapList=null;
		try{
		
			 sql="select dutyrota_id,emd_id,PF,EMPLOYEE_NAME,section,batch,token,duty_type,duty_type1,wo_1,wo_2,dr.effective_start_date,dr.effective_end_date from dutyrota dr\n" + 
			 		"inner join employee e on dr.emd_id=e.EMPLOYEE_ID \n" + 
			 		"where e.status='active' and deleted_status=0 and emd_id='"+id+"' ";

			Query query = session.createSQLQuery(sql);
				/*	.addScalar("TOKEN", Hibernate.STRING)
					.addScalar("emd_id", Hibernate.STRING)
					.addScalar("duty_type", Hibernate.STRING)
					.addScalar("wo_1", Hibernate.STRING)
					.addScalar("wo_2", Hibernate.STRING)
					.addScalar("effective_start_date", Hibernate.STRING)
					.addScalar("effective_end_date", Hibernate.STRING);*/
				
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 aliasToValueMapList = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return aliasToValueMapList;
	
		
		
	
	}
public boolean saveeditdetails(String empid2, String dtype1,String wo1,String wo2,String startdate,String enddate) {

	// TODO Auto-generated method stub
	boolean isSiuccess = false;
	Session session  = null;
	
	int i = 0;
	
	try {
	System.out.println("2nd----------------"+empid2);
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		 HttpServletRequest request = ServletActionContext.getRequest();
		//getAttendanceCount(month);
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date currentDate=new java.util.Date();
      
        DutyRota drota=(DutyRota) session.get(DutyRota.class,Integer.parseInt(empid2));
	//	DutyRota drota=new DutyRota();
		
		//drota.setEmd_id(empid2);
		//drota.setDuty_type(dtype1);
		//drota.setWo1(wo1);
		
		//drota.setEffective_start_date(date1);
		drota.setDeletedStatus(1);
		drota.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		//drota.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		//drota.setDeletedStatus(0);
		drota.setUpdatedDate(new java.util.Date());
		//drota.setCreated_date(new java.util.Date());
		i = (Integer) session.save(drota);
		
		
		if(i!=0){
			isSiuccess=true;
		}
		session.getTransaction().commit();
	} catch (Exception e) {
		e.printStackTrace();			
		isSiuccess = false;
	} finally {
		session.close();
	}
	return isSiuccess;


	
}
public StringBuffer getdutytypes() {
	StringBuffer sb=new StringBuffer();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	try {
String sql="select dutyrotatype_id,duty_type duty_type from dutyrota_type where deleted_status=0";
Query query = session.createSQLQuery(sql);
query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
List<Map<String, Object>> l= query.list();
for(Map<String, Object> ll:l) {
	sb=sb.append("<option value='"+ll.get("dutyrotatype_id")+"'>"+ll.get("duty_type")+"</option>");
}
System.out.println(sb);
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		session.close();
	}
	return sb;
	
}
public StringBuffer getdutysection() {

	StringBuffer sb=new StringBuffer();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	try {
String sql="select id,section_name  from dutyrota_section where deleted_status=0";
Query query = session.createSQLQuery(sql);
query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
List<Map<String, Object>> l= query.list();
for(Map<String, Object> ll:l) {
	sb=sb.append("<option value='"+ll.get("id")+"'>"+ll.get("section_name")+"</option>");
}
System.out.println(sb);
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		session.close();
	}
	return sb;
	

}
public StringBuffer getbatch() {
	StringBuffer sb=new StringBuffer();
	System.out.println("batch inside");
	Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Query query = session
			.createSQLQuery("select id,batch_name from dutyrota_batch   where deleted_status=0");
	try {
		System.out.println("div 2");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> l= query.list();
		System.out.println("---------------------"+l.size());
		for(Map<String, Object> map:l) {
			sb=sb.append("<option value='"+map.get("id")+"'>"+map.get("batch_name")+"</option>");
		}
	} catch (Exception ex) {
	} finally {
		session.close();
	}
	return sb;
}
}
