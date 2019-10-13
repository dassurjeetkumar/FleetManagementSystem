package com.trimax.its.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.its.transport.model.BusStops;
import com.trimax.its.usermanagement.model.DesignationType;


import com.trimax.its.util.HibernateUtil;





public class DesignationTypeDAO {
	
	
	/*public List<DesignationType> getDesignationList(){
		
		ArrayList <DesignationType>showDesignationlist=new ArrayList();
		Transaction transaction = null;
		List<DesignationType> list=null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		//String q="select designation_type_id,IFNULL(designation_type_name,'NA') designation_type_name ,IFNULL(status,'NA') status ,IFNULL(note,'NA') note from designation_type  where deleted_status=0";
		String q="select designation_type_id,IFNULL(designation_type_name,'NA') designation_type_name ,IFNULL(status,'NA') status ,IFNULL(note,'NA') note from designation_type  where deleted_status = 0 and status='NEW' order by designation_type_id asc";
		session.beginTransaction();
		Query query = session.createSQLQuery(q);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			DesignationType des=new DesignationType();
			Map<String, Object> rs = aliasToValueMapList.get(i);
			
			if(rs.size()>0){
				des.setDesignation_type_id(Integer.parseInt(rs.get("designation_type_id").toString()));
				des.setDesignation_type_name(rs.get("designation_type_name").toString());
				des.setStatus(rs.get("status").toString());
				des.setNote(rs.get("note").toString());
			}
			showDesignationlist.add(des);
			////System.out.println("showDesignationlist---------"+showDesignationlist);
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return showDesignationlist;
		}*/
	
	
	
	public int getTotalRecords(){
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{ 
		Query	 query = session.createSQLQuery("select count(*) as count from designation_type where deleted_status = 0 ").addScalar("count", Hibernate.INTEGER);
		
		////System.out.println("query------//////////----------"+query);
		
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
		Query	 query= session.createSQLQuery("select count(*) as count  from designation_type where deleted_status=0  and (designation_type_id like '%"+search_term+"%' OR designation_type_name like '%"+search_term+"%' OR status like '%"+search_term+"%' OR note like '%"+search_term+"%' )  order by designation_type_id").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		 cnt = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return cnt;
	}
	
	public JSONObject getDesignationTypeList(int total, HttpServletRequest request,String col,String dir){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="";
			sql="select designation_type_id,IFNULL(designation_type_name,'NA') designation_type_name ,IFNULL(designation_type_code,'NA')designation_type_code ,IFNULL(status,'NA') status ,IFNULL(note,'NA') note from designation_type  where deleted_status = 0";
		
			
			
			
			String search_term = request.getParameter("sSearch").trim();
			if (search_term != null && !search_term.equals("")) {
				
				//System.out.println("search_term---------"+search_term);
				sql += " and ( designation_type_id like '%"+search_term+"%'";
				sql += " OR designation_type_name like '%"+search_term+"%'";
				sql += " OR designation_type_code like '%"+search_term+"%'";
				sql += " OR status like '%"+search_term+"%'";
				sql += " OR note like '%"+search_term+"%')";
				
			}
			
			
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    //criteria.addOrder(Order.asc(col));
					sql += " order by "+col+" asc" ;
				}else{
					//criteria.addOrder(Order.desc(col));	
					sql += " order by "+col+" desc";
				}
			}
		
			////System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayLength")));
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
					+ rs.get("designation_type_id")
					+ "' value='"
					+ rs.get("designation_type_id") + "'/>");
			ja.add(rs.get("designation_type_id").toString());
			ja.add(rs.get("designation_type_name").toString());
			ja.add(rs.get("designation_type_code").toString());
			ja.add(rs.get("status").toString());
			ja.add(rs.get("note").toString());
			
			array.add(ja);
			////System.out.println("Array----->"+array);
		}
		
		
		
		//if (!request.getParameter("sSearch").equals("")) {
			totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			String search_term1= request.getParameter("sSearch").trim();
			int cnt=getTotalRecordsForCount(search_term1);
			result.put("iTotalRecords",cnt);

			result.put("iTotalDisplayRecords", cnt);
		/*}else{
			totalAfterFilter = getTotalRecords();
			result.put("aaData",array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
		}*/
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return result;
	}
	
	
	public DesignationType getlistfromId(int designationid){
		Transaction transaction = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		DesignationType des=new DesignationType();
		try{
		String q="select designation_type_id,IFNULL(designation_type_name,'NA') designation_type_name ,IFNULL(designation_type_code,'NA')designation_type_code,IFNULL(status,'NA') status ,IFNULL(note,'NA') note from designation_type  where designation_type_id ="+designationid;
		session.beginTransaction();
		Query query = session.createSQLQuery(q);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
	
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			
			Map<String, Object> rs = aliasToValueMapList.get(i);
			
			if(rs.size()>0){
				des.setDesignation_type_id(Integer.parseInt(rs.get("designation_type_id").toString()));
				des.setDesignation_type_name(rs.get("designation_type_name").toString());
				des.setDesignation_type_code(rs.get("designation_type_code").toString());
				des.setStatus(rs.get("status").toString());
				des.setNote(rs.get("note").toString());
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return des;
	}
	public String checkuniquness(String designationtypename,String designationcode){
		String flag="true";
		String msg="";
		String str="";
		//Transaction transaction = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String q1="select designation_type_id from designation_type where designation_type_name='"+ designationtypename +"'and deleted_status = 0";
			session.beginTransaction();
			Query query=session.createSQLQuery(q1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//System.out.println("aliasToValueMapList---"+aliasToValueMapList);
			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<String, Object> rs = aliasToValueMapList.get(0);
				if (rs != null) {
					int designation_type_id=Integer.parseInt(rs.get("designation_type_id").toString());
					//System.out.println("Record allready Present 1111111");
					/*str= " Designation Name should " + designation_type_id + " already Present";*/
					str="Designation Name should already Present of Designation Type Id "+ designation_type_id;
					flag="false";
					msg=flag+"|"+str;
					
			}
			}else{
				/*str="Record not duplicate";
				flag="true";
				msg=flag+"|"+str;*/
				
				Query	 query1= session.createSQLQuery("select designation_type_id as designation_type_id  from designation_type where designation_type_code = '"+ designationcode + "' and deleted_status=0").addScalar("designation_type_id", Hibernate.INTEGER);
				List<Integer> list = query1.list();
				if(list.size()>0)
				{
				int cnt = list.get(0);
				//System.out.println("Designation code should be uniqe");
				//str= " Designation Type ID " + cnt + " already Present";
				str="Designation code should already Present of Designation Type Id "+ cnt;
				flag="false";
				msg=flag+"|"+str;
				}else{
					str="Record not duplicate";
					flag="true";
					msg=flag+"|"+str;
				}
				
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
	
	public int addDesignationType(DesignationType designationtype){
		int a=0;
		String msg;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		session.beginTransaction();
		designationtype.getDesignation_type_code().toUpperCase();
		 a= (Integer)session.save(designationtype);
		
			
		
		session.getTransaction().commit();
		session.close();
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if (session != null) {
/*				session.close();*/
			}
		}
		return a;
	}
	
	
	public DesignationType getDesignationType(int id){

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		DesignationType designationtype = (DesignationType) session.get(DesignationType.class, new Integer(id));
		return designationtype;
		
	}
	
	/*@SuppressWarnings("unchecked")
	public String saveEdited(int id, DesignationType designationtype){
		//System.out.println("id------"+id);
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DesignationType designationtypes = (DesignationType) session.get(DesignationType.class, id);
		
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			//designationtypes.setDesignation_type_id(designationtype.getDesignation_type_id());
			//System.out.println("name-------"+designationtype.getDesignation_type_name());
			designationtypes.setDesignation_type_name(designationtype.getDesignation_type_name());
			designationtypes.setNote(designationtype.getNote());
			designationtypes.setStatus(designationtype.getStatus());
			designationtypes.setUpdated_by(usrsessionid);
			designationtypes.setUpdated_date(new Date());
			session.update(designationtypes);
			session.getTransaction().commit();
			session.close();
			return "success";
	}
	
	*/
	
	
	
	/*public String deleteBusStop(String requestType,int id){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		BusStops busstops = (BusStops) session.get(BusStops.class, id);
		busstops.setStatus("DELETED");
		session.update(busstops);
		session.getTransaction().commit();
		if(requestType.equals("map")){
			return "successmap";
		}
		else{
		return "success";
		}
	}*/
	

	
	public String saveEdited(int id,DesignationType designationtype,int userid){
		String flag="";
		String msg="";
		String str="";
		Transaction transaction = null;
		int deletedstatus;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		try{
			String q1="select designation_type_id from designation_type where designation_type_name='"+ designationtype.getDesignation_type_name() +"'and deleted_status=0 and designation_type_id NOT IN("+ id  +")";
			session.beginTransaction();
			Query query=session.createSQLQuery(q1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//System.out.println("aliasToValueMapList---"+aliasToValueMapList);
			if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
				Map<String, Object> rs = aliasToValueMapList.get(0);
				if (rs != null) {
					int designation_type_id=Integer.parseInt(rs.get("designation_type_id").toString());
					//System.out.println("Record allready Present");
					//str= designationtype.getDesignation_type_name()  + " Designation Type Name  already Present";
					//str=" Designation Type ID "+ designation_type_id + " already Present";
					str="Designation Name should already Present of Designation Type Id "+ designation_type_id;
					flag="false";
					msg=flag+"|"+str;
					
			}
			}else{
				/*if(designationtype.getStatus().equalsIgnoreCase("NEW"))
				{
					deletedstatus=1;
				}else{
					deletedstatus=0;
				}*/
				
				Query	 query2= session.createSQLQuery("select designation_type_id as designation_type_id  from designation_type where designation_type_code = '"+ designationtype.getDesignation_type_code() + "' and deleted_status=0 and designation_type_id NOT IN("+ id  +")").addScalar("designation_type_id", Hibernate.INTEGER);
				List<Integer> list = query2.list();
				if(list.size()>0)
				{
					int cnt = list.get(0);
					//System.out.println("Designation code should be uniqe");
					//str= " Designation Type ID " + cnt + " already Present";
					str="Designation code should already Present of Designation Type Id "+ cnt;
					flag="false";
					msg=flag+"|"+str;
				}else{
			transaction = session.beginTransaction();
			String q=" update designation_type set  designation_type_name ='"+ designationtype.getDesignation_type_name() + "', designation_type_code ='"+designationtype.getDesignation_type_code() +"',status = '"+ designationtype.getStatus() +" ',note=' "+designationtype.getNote()+ " ',updated_by ="+ userid  +", updated_date = now() where designation_type_id  = "+ id;
			//System.out.println("q--"+q);
			Query query1 = session.createSQLQuery(q);
			int n=query1.executeUpdate();
			if(n>0)
			{
			session.getTransaction().commit();
			////System.out.println("test2");
			//System.out.println("Updated sucessfully");
			session.flush();
			str=" Designation Type ID " + id + " Modify successfully";
			flag="true";
			msg=flag+"|"+str;
			}else{
				//System.out.println("Record not updated");
				str="Designation Type " + id +" not Modify";
				flag="false";
				msg=flag+"|"+str;
			}//else
			}
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
	
	/*public String deleteDesignationType(int id ){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DesignationType designationtypes = (DesignationType) session.get(DesignationType.class, id);
		designationtypes.setDeleted_status(1);
		session.update(designationtypes);
		session.getTransaction().commit();
		return "success";
	}*/
	public boolean deleteDesignationType(int id,int userid ){
		//String flag="";
		boolean flag=false;
		String msg="";
		String str="";
		Transaction transaction = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		try{
			transaction = session.beginTransaction();
			String q="update designation_type set deleted_status = 1  , updated_by =" + userid + ",updated_date = now() where designation_type_id="+id; 
			//System.out.println("q--"+q);
			Query query1 = session.createSQLQuery(q);
			int n=query1.executeUpdate();
			//System.out.println("n--"+n);
			if(n>0)
			{
			session.getTransaction().commit();
			////System.out.println("test2");
			//System.out.println("Updated successfully");
			session.flush();
			flag=true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}
}




