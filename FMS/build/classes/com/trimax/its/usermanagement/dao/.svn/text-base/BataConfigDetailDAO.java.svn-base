package com.trimax.its.usermanagement.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.type.IntegerType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;
import com.trimax.its.common.Common;
import com.trimax.its.transport.model.ScheduleService;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.usermanagement.model.BataConfigDetails;
import com.trimax.its.usermanagement.model.DesignationType;
import com.trimax.its.usermanagement.model.UserDetails;
import com.trimax.its.util.HibernateUtil;

public class BataConfigDetailDAO {
	
	
	public int getTotalRecords(HttpServletRequest request,String col,String dir){
		
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			String sql="select bata_config_id,bata_config_details.role_id,IFNULL(designation_type.designation_type_name,'NA')designation_type_name,IFNULL(service_type.service_type_name,'NA')service_type_name,schedule_type.schedule_type_id,IFNULL(schedule_type.schedule_type_name,'NA')schedule_type_name,"+
					" min_km,max_km,day_bata,day_allowance,spcl_allowance ,Date_Format(effect_start_date,'%d-%m-%Y') effect_start_date, Date_Format(effect_end_date,'%d-%m-%Y') effect_end_date " + 
					" from  bata_config_details " +
					" INNER JOIN designation_type ON bata_config_details.role_id=designation_type.designation_type_id " + 
					" INNER JOIN schedule_type ON bata_config_details.shift_type_id=schedule_type.schedule_type_id " + 
					" INNER JOIN service_type ON bata_config_details.schedule_service_type=service_type.service_type_id " + 
					" where bata_config_details.deleted_status=0 ";
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and ( bata_config_id like '"+search_term+"%'";
				//sql += "OR bata_config_details.role_id like '"+search_term+"%'";
				sql += "OR designation_type.designation_type_name like '"+search_term+"%'";
				sql += "OR schedule_type.schedule_type_name like '"+search_term+"%'";
				sql += "OR service_type.service_type_name like '"+search_term+"%'";
				sql += "OR min_km like '"+search_term+"%'";
				sql += "OR max_km like '"+search_term+"%'";
				sql += "OR day_bata like '"+search_term+"%'";
				sql += "OR day_allowance like '"+search_term+"%'";
				sql += "OR effect_start_date like '"+search_term+"%'";
				sql += "OR effect_end_date like '"+search_term+"%'";
				sql += "OR spcl_allowance like '"+search_term+"%')";
			}
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			 cnt =	aliasToValueMapList.size();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (session != null) {
					session.close();
				}
			}
			return cnt;
			
		}

	
	public JSONObject getBataList(int total, HttpServletRequest request,String col,String dir){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="select bata_config_id,bata_config_details.role_id,IFNULL(designation_type.designation_type_name,'NA')designation_type_name,schedule_type.schedule_type_id,IFNULL(schedule_type.schedule_type_name,'NA')schedule_type_name,IFNULL(service_type.service_type_name,'NA')service_type_name,"+
						" min_km as min_km,max_km as max_km,day_bata as day_bata,day_allowance as day_allowance,spcl_allowance as spcl_allowance,IFNULL(nighthalt_allowance,' ')as nighthalt_allowance,IFNULL(nightsevice_allowance,'') as nightsevice_allowance,Date_Format(effect_start_date,'%d-%m-%Y') effect_start_date, IFNULL(Date_Format(effect_end_date,'%d-%m-%Y'),'NA') effect_end_date " +
						" from  bata_config_details " +
						" INNER JOIN designation_type ON bata_config_details.role_id=designation_type.designation_type_id " + 
						" INNER JOIN schedule_type ON bata_config_details.shift_type_id=schedule_type.schedule_type_id " + 
						" INNER JOIN service_type ON bata_config_details.schedule_service_type=service_type.service_type_id " + 
						" where bata_config_details.deleted_status=0 ";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and ( bata_config_id like '"+search_term+"%'";
				//sql += "OR bata_config_details.role_id like '"+search_term+"%'";
				sql += "OR designation_type.designation_type_name like '"+search_term+"%'";
				sql += "OR schedule_type.schedule_type_name like '"+search_term+"%'";
				sql += "OR service_type.service_type_name like '"+search_term+"%'";
				sql += "OR min_km like '"+search_term+"%'";
				sql += "OR max_km like '"+search_term+"%'";
				sql += "OR day_bata like '"+search_term+"%'";
				sql += "OR day_allowance like '"+search_term+"%'";
				sql += "OR effect_start_date like '"+search_term+"%'";
				sql += "OR effect_end_date like '"+search_term+"%'";
				sql += "OR spcl_allowance like '"+search_term+"%')";
				sql += "OR nighthalt_allowance like '"+search_term+"%')";
				sql += "OR nightsevice_allowance like '"+search_term+"%')";
			}
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
			
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql).addScalar("bata_config_id").addScalar("designation_type_name")
					.addScalar("service_type_name").addScalar("schedule_type_name").addScalar("min_km")
					.addScalar("max_km").addScalar("day_bata").addScalar("day_allowance").addScalar("spcl_allowance")
					.addScalar("nighthalt_allowance").addScalar("nightsevice_allowance").addScalar("effect_start_date").addScalar("effect_end_date");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			JSONArray array = new JSONArray();
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("bata_config_id")
						+ "' value='"
						+ rs.get("bata_config_id") + "'/>");
				ja.add(rs.get("bata_config_id").toString());
				ja.add(rs.get("designation_type_name").toString());
				ja.add(rs.get("schedule_type_name").toString());
				ja.add(rs.get("service_type_name").toString());
				ja.add(rs.get("min_km").toString());
				ja.add(rs.get("max_km").toString());
				ja.add(rs.get("day_bata").toString());
				ja.add(rs.get("day_allowance").toString());
				ja.add(rs.get("spcl_allowance").toString());
				
				ja.add(rs.get("nighthalt_allowance").toString());
				ja.add(rs.get("nightsevice_allowance").toString());
				
				ja.add(rs.get("effect_start_date").toString());
				ja.add(rs.get("effect_end_date").toString());
				
				array.add(ja);
			}
			
			int cnt=0;
			totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			String search_term2= request.getParameter("sSearch");
			
				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalRecords(request,col,dir);//getTotalRecordsForCount(search_term3);
		
			result.put("iTotalRecords",cnt);
			
			result.put("iTotalDisplayRecords", cnt);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;

}
	
	public Map<Integer, String> getDesignationList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from DesignationType where status='Active' and deleted_status=0 order by designation_type_name");
		
		try{
		List<DesignationType> list = query.list();
		
		for (DesignationType designation : list) {
			//resultMap.put(vendor.getId(),vendor.getVendor_name());
			resultMap.put(designation.getDesignation_type_id(), designation.getDesignation_type_name());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
		

	}
	public Map<Integer, String> getScheduletypeList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = null;
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from ScheduleType where status='ACTIVE' and deleted_status=0 order by schedule_type_name");
		
		
		List<ScheduleType> list = query.list();
		
		for (ScheduleType shedule : list) {
			//resultMap.put(vendor.getId(),vendor.getVendor_name());
			resultMap.put(shedule.getSchedule_type_id(), shedule.getScheduleName());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			
				session.close();
			
		}
		return resultMap;
		

	}
	
	
	public int addBataConfig(BataConfigDetails bataconfig,int userid){
		Transaction tx=null;
		int cnt=0;
		int i=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Common common = new Common();
		tx=session.beginTransaction();
		try{
			//if(cnt==0){
				
			int nighthalt_allowance=0;
			int nightsevice_allowance=0;
				Long minkm=Long.parseLong(bataconfig.getMin_km());
				Long maxkm=Long.parseLong(bataconfig.getMax_km());
				Long daybata=Long.parseLong(bataconfig.getDay_bata());
				Long dayallowance=Long.parseLong(bataconfig.getDay_allowance());
				Long spallowance=Long.parseLong(bataconfig.getSpcl_allowance());
				if(bataconfig.getNighthalt_allowance()!=null && !bataconfig.getNighthalt_allowance().equals("") && !bataconfig.getNighthalt_allowance().isEmpty())
				
				{
					
					nighthalt_allowance=Integer.parseInt(bataconfig.getNighthalt_allowance());
				}
				//Long nighthalt_allowance=Long.parseLong(bataconfig.getNighthalt_allowance());
				//Long nightsevice_allowance=Long.parseLong(bataconfig.getNightsevice_allowance());
				System.out.println("getNighthalt_allowance.."+bataconfig.getNighthalt_allowance()+"nightservice.."+bataconfig.getNightsevice_allowance());
				
				double incetive=bataconfig.getIncetive_percentage();
				
				
				String q="INSERT INTO bata_config_details (role_id, shift_type_id,schedule_service_type, min_km, max_km, day_bata,day_allowance, " +
						" spcl_allowance,nighthalt_allowance,nightsevice_allowance,created_by,created_date,updated_by,effect_start_date," +
						" incetive_percentage ,updated_date, deleted_status) VALUES "
						+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				System.out.println("query......."+q);
				Query query1=session.createSQLQuery(q);
				query1.setParameter(0, bataconfig.getDesignation_type_id().getDesignation_type_id());
				query1.setParameter(1, bataconfig.getSchedule_type_id().getSchedule_type_id());
				query1.setParameter(2, bataconfig.getServicetype().getService_type_id());
				query1.setParameter(3, minkm);
				
				query1.setParameter(4, maxkm);
				query1.setParameter(5, daybata);
				query1.setParameter(6, dayallowance);
				query1.setParameter(7, spallowance);
				query1.setParameter(8, nighthalt_allowance);
				
				query1.setParameter(9, bataconfig.getNightsevice_allowance());
				query1.setParameter(10, bataconfig.getCreated_by());
				query1.setParameter(11, new Date());
				query1.setParameter(12, bataconfig.getCreated_by());
				
				query1.setParameter(13, common.getDateFromPicker(bataconfig.getEffect_start_date()));
				query1.setParameter(14, incetive);
				query1.setParameter(15, new Date());
				query1.setParameter(16, 0);
				int a=query1.executeUpdate();
				session.getTransaction().commit();
				if(a>0){
					Query query2 = session.createSQLQuery("select MAX(bata_config_id) bataid from  bata_config_details").addScalar("bataid",Hibernate.INTEGER);
					List<Integer> list = query2.list();
					i = list.get(0);
					System.out.println("i---"+i);
				}
				
			//}
			/*else{
				i=-1;
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return i;
	}
	
	
	
	
	public int addBataConfigDetails(BataConfigDetails bataconfig,int userid){
		Transaction tx=null;
		int cnt=0;
		int i=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Common common = new Common();
		tx=session.beginTransaction();
		try{
			
				int nighthalt_allowance=0;
				
				Long minkm=Long.parseLong(bataconfig.getMin_km());
				Long maxkm=Long.parseLong(bataconfig.getMax_km());
				Long daybata=Long.parseLong(bataconfig.getDay_bata());
				Long dayallowance=Long.parseLong(bataconfig.getDay_allowance());
				Long spallowance=Long.parseLong(bataconfig.getSpcl_allowance());
				
				if(bataconfig.getNighthalt_allowance()!=null && !bataconfig.getNighthalt_allowance().equals("") && !bataconfig.getNighthalt_allowance().isEmpty())
				{
					nighthalt_allowance=Integer.parseInt(bataconfig.getNighthalt_allowance());
				}
				
				double incetive=bataconfig.getIncetive_percentage();
				
				String q="INSERT INTO bata_config_details (role_id, shift_type_id, min_km, max_km, day_bata, day_allowance, spcl_allowance," +
						" nighthalt_allowance,nightsevice_allowance,created_by, created_date, updated_by, effect_start_date, effect_end_date," +
						" incetive_percentage,updated_date, deleted_status) VALUES "
						+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				Query query1=session.createSQLQuery(q);
				query1.setParameter(0, bataconfig.getDesignation_type_id().getDesignation_type_id());
				query1.setParameter(1, bataconfig.getSchedule_type_id().getSchedule_type_id());
				query1.setParameter(2, minkm);
				
				query1.setParameter(3, maxkm);
				query1.setParameter(4, daybata);
				query1.setParameter(5, dayallowance);
				query1.setParameter(6, spallowance);
				query1.setParameter(7, nighthalt_allowance);
				
				query1.setParameter(8, bataconfig.getNightsevice_allowance());
				query1.setParameter(9, bataconfig.getCreated_by());
				query1.setParameter(10, new Date());
				query1.setParameter(11, bataconfig.getCreated_by());
				
				query1.setParameter(12, common.getDateFromPicker(bataconfig.getEffect_start_date()));
				query1.setParameter(13, common.getDateFromPicker(bataconfig.getEffect_start_date()));
				query1.setParameter(14, incetive);
				query1.setParameter(15, new Date());
				query1.setParameter(16, 0);
				int a=query1.executeUpdate();
				session.getTransaction().commit();
				if(a>0){
					Query query2 = session.createSQLQuery("select MAX(bata_config_id) bataid from  bata_config_details").addScalar("bataid",Hibernate.INTEGER);
					List<Integer> list = query2.list();
					i = list.get(0);
				}
				
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return i;
	}
	
	public int updateBata(int id,BataConfigDetails bataconfig,int userid){
		boolean flag=false;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		int cnt=0;
		try{
			if(cnt==0)
			{
			BataConfigDetails bataconfig1=(BataConfigDetails)session.load(BataConfigDetails.class,new Integer(id));
			HttpServletRequest request=ServletActionContext.getRequest();
			bataconfig1.setDesignation_type_id(bataconfig.getDesignation_type_id());
			bataconfig1.setSchedule_type_id(bataconfig.getSchedule_type_id());
			bataconfig1.setMin_km(bataconfig.getMin_km());
			bataconfig1.setMax_km(bataconfig.getMax_km());
			bataconfig1.setDay_allowance(bataconfig.getDay_allowance());
			bataconfig1.setSpcl_allowance(bataconfig.getSpcl_allowance());
			bataconfig1.setDay_bata(bataconfig.getDay_bata());
			bataconfig1.setUpdated_by(userid);
			bataconfig1.setUpdated_date(new java.util.Date());
			session.update(bataconfig1);
			session.getTransaction().commit();
			i=1;
			}else{
				i=-1;
			}
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return i;
	}
	
	public BataConfigDetails getEditBataDetails(int id){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		BataConfigDetails bataconfig1=(BataConfigDetails)session.load(BataConfigDetails.class,new Integer(id));
		//System.out.println("Test--------"+bataconfig1.getDesignation_type_id());
		return bataconfig1;
	}
	public boolean deletedBata(int id,int userid){
		boolean flag=false;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try{
			BataConfigDetails bataconfig1=(BataConfigDetails)session.load(BataConfigDetails.class,new Integer(id));
			HttpServletRequest request=ServletActionContext.getRequest();
			bataconfig1.setDeleted_status(1);
			bataconfig1.setUpdated_by(userid);
			bataconfig1.setUpdated_date(new java.util.Date());
			session.update(bataconfig1);
			session.getTransaction().commit();
			flag=true;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}
	
	
	public int getCountDetails(int desginationid ,int shifttypeid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int cnt=0;
		try{
			Query query = session.createSQLQuery("select count(*) as count from bata_config_details " +
					" where role_id= ? and shift_type_id= ? and deleted_status=0").addScalar("count",Hibernate.INTEGER);
			query.setParameter(0, desginationid);
			query.setParameter(1, shifttypeid);
			List<Integer> list = query.list();
			cnt=list.get(0);
			System.out.println("cnt---"+cnt);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}
	
	public int getCountDetailsforedit(int desginationid ,int shifttypeid,int id){
		//select count(*) as count from bata_config_details where role_id= 50 and shift_type_id= 6 and bata_config_id not in(9) and deleted_status=0
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int cnt=0;
		try{
			Query query = session.createSQLQuery("select count(*) as count from bata_config_details " +
					" where role_id= ? and shift_type_id= ? and " +
							" bata_config_id not in( ? ) and deleted_status=0").addScalar("count",Hibernate.INTEGER);
			query.setParameter(0, desginationid);
			query.setParameter(1, shifttypeid);
			query.setParameter(2, id);
			List<Integer> list = query.list();
			cnt=list.get(0);
			System.out.println("cnt---"+cnt);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}
	
	public int getMinKmforedit (int desginationid,int shiftype,int id){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int minkmfornew=0;
		try{
			Query query = session.createSQLQuery("select max(max_km)+1 minkm from bata_config_details where role_id= " + desginationid + " and shift_type_id= " + shiftype + " and bata_config_id not in( "+ id +" ) and deleted_status=0").addScalar("minkm",Hibernate.INTEGER);
			List<Integer> list = query.list();
			minkmfornew=list.get(0);
			System.out.println("minkmfornew---"+minkmfornew);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return minkmfornew;
	}
	
	
	/*public ArrayList getMinKmList(int designationtypeid,int shiftypeid,int bataconfigid)
	
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		ArrayList minkm=new ArrayList();
		try{
			//Query query = session.createSQLQuery("select count(*) as count from bata_config_details where role_id= " + desginationid + " and shift_type_id= " + shifttypeid + " and bata_config_id not in( "+ id +" ) and deleted_status=0").addScalar("count",Hibernate.INTEGER);
			Query query = session.createSQLQuery("select min_km as minkm from bata_config_details where role_id= " +designationtypeid+" and shift_type_id= "+ shiftypeid +" and bata_config_id not in( "+ bataconfigid +" ) and deleted_status=0").addScalar("minkm",Hibernate.INTEGER);
			List<Integer> list = query.list();
			for(int i=0;i<list.size();i++){
				minkm.add(list.get(i));
			}
			System.out.println("minkm-----"+minkm);
		}catch(Exception e){
			e.printStackTrace();
		}
		return minkm;
	}*/
	
/*public ArrayList getMaxKmList(int designationtypeid,int shiftypeid,int bataconfigid)
	
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		ArrayList maxkm=new ArrayList();
		try{
			//Query query = session.createSQLQuery("select count(*) as count from bata_config_details where role_id= " + desginationid + " and shift_type_id= " + shifttypeid + " and bata_config_id not in( "+ id +" ) and deleted_status=0").addScalar("count",Hibernate.INTEGER);
			Query query = session.createSQLQuery("select max_km as maxkm from bata_config_details where role_id= " +designationtypeid+" and shift_type_id= "+ shiftypeid +" and bata_config_id not in( "+ bataconfigid +" ) and deleted_status=0").addScalar("maxkm",Hibernate.INTEGER);
			List<Integer> list = query.list();
			for(int i=0;i<list.size();i++){
				maxkm.add(list.get(i));
			}
			System.out.println("maxkm-----"+maxkm);
		}catch(Exception e){
			e.printStackTrace();
		}
		return maxkm;
	}
	*/
	
	public boolean getResultDetailsForEditMin(int designationtypeid,int shiftypeid,int bataconfigid,int minkm )
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		try{
			Query query = session.createSQLQuery(" select count(*) as count from bata_config_details where  " +
					"min_km = "+ minkm +" and role_id= ? and shift_type_id= ?  " +
							" and bata_config_id not in ( ? ) and deleted_status=0").addScalar("count",Hibernate.INTEGER);
			query.setParameter(0, designationtypeid);
			query.setParameter(1, shiftypeid);
			query.setParameter(2, bataconfigid);
			List<Integer> list = query.list();
			int count=list.get(0);
			if(count>0){
			   flag=false;
			}else{
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
	
	public boolean getResultDetailsForEditMax(int designationtypeid,int shiftypeid,int bataconfigid,int maxkm )
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		try{
			Query query = session.createSQLQuery(" select count(*) as count from bata_config_details " +
					"where  max_km = ? and role_id= ? and shift_type_id= ?  " +
							"and bata_config_id not in ( ? ) and deleted_status=0").addScalar("count",Hibernate.INTEGER);
			query.setParameter(0, maxkm);
			query.setParameter(1, designationtypeid);
			query.setParameter(2, shiftypeid);
			query.setParameter(3, bataconfigid);
			List<Integer> list = query.list();
			int count=list.get(0);
			if(count>0){
			   flag=false;
			}else{
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
	
	public boolean getResult(int designationtypeid,int shiftypeid,int bataconfigid,int kmuser)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		try{
			
			//select * from bata_config_details where 215 between min_km AND max_km and role_id=91 and shift_type_id=1 and deleted_status=0;
			Query query = session.createSQLQuery(" select count(*) as count from bata_config_details " +
					"where ? between min_km AND max_km and role_id= ? and shift_type_id= ? " +
							"and bata_config_id not in ( ? ) and deleted_status=0 ").addScalar("count",Hibernate.INTEGER);
			query.setParameter(0, kmuser);
			query.setParameter(1, designationtypeid);
			query.setParameter(2, shiftypeid);
			query.setParameter(3, bataconfigid);
			List<Integer> list = query.list();
			int count=list.get(0);
			if(count>0){
			   flag=false;
			}else{
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
	
	
	public boolean getdatabaseResultMaxkm(int designationtypeid,int shiftypeid,int bataconfigid,int minkmuser,int maxuser)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		try{
			Query query = session.createSQLQuery("select count(*) count from bata_config_details " +
					" where max_km BETWEEN ? and ? and role_id= ? and " +
							" shift_type_id= ? and bata_config_id not in (?) and deleted_status=0").addScalar("count",Hibernate.INTEGER);;
			query.setParameter(0, minkmuser);
			query.setParameter(1, maxuser);
			query.setParameter(2, designationtypeid);	
			query.setParameter(3, shiftypeid);	
			query.setParameter(4, bataconfigid);	
			List<Integer> list = query.list();
			int count=list.get(0);
			if(count>0){
			   flag=false;
			}else{
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
	
	public boolean getdatabaseResultMinkm(int designationtypeid,int shiftypeid,int bataconfigid,int minkmuser,int maxuser)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		try{
			//select *  from bata_config_details where min_km >401 and role_id= 91 and shift_type_id= 1 and bata_config_id not in (9) and deleted_status=0
			Query query = session.createSQLQuery("select count(*) count from bata_config_details " +
					"where min_km BETWEEN ? and ? and role_id= ? and " +
							" shift_type_id= ? and bata_config_id not in (?) and deleted_status=0").addScalar("count",Hibernate.INTEGER);;
							query.setParameter(0, minkmuser);
							query.setParameter(1, maxuser);
							query.setParameter(2, designationtypeid);
							query.setParameter(3, shiftypeid);
							query.setParameter(4, bataconfigid);
			List<Integer> list = query.list();
			int count=list.get(0);
			if(count>0){
			   flag=false;
			}else{
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
	
	/*****************/
	
	public int getCountDetailsfordate(int desginationid ,int shifttypeid,String startdate,String enddate){
		//select count(*) as count from bata_config_details where role_id= 50 and shift_type_id= 6 and bata_config_id not in(9) and deleted_status=0
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int cnt=0;
		try{
			Query query = session.createSQLQuery("select count(*) as count " +
					"from bata_config_details where role_id= ? " +
							"and shift_type_id= ? and deleted_status=0 and " +
									" effect_start_date = ? and effect_end_date = ? ").addScalar("count",Hibernate.INTEGER);
			query.setParameter(0, desginationid);
			query.setParameter(1, shifttypeid);
			query.setParameter(2, startdate);
			query.setParameter(3, enddate);
			List<Integer> list = query.list();
			cnt=list.get(0);
			System.out.println("cnt---"+cnt);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}
	/*************/
	
	public int getMinKmForDate(int desginationid ,int shifttypeid,String startdate,String enddate){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int minkmfornew=0;
		try{
			Query query = session.createSQLQuery("select max(max_km)+1 minkm from bata_config_details " +
					" where role_id= ? and shift_type_id= ? and deleted_status=0 " +
							" and effect_start_date = ? and effect_end_date = ? ").addScalar("minkm",Hibernate.INTEGER);
			query.setParameter(0, desginationid);
			query.setParameter(1, shifttypeid);
			query.setParameter(2, startdate);
			query.setParameter(3, enddate);
			List<Integer> list = query.list();
			minkmfornew=list.get(0);
			System.out.println("minkmfornew---"+minkmfornew);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return minkmfornew;
	}
	
	public boolean getStartdateCheck(int desginationid ,int shifttypeid,String startdate){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		try{
			//select *  from bata_config_details where min_km >401 and role_id= 91 and shift_type_id= 1 and bata_config_id not in (9) and deleted_status=0
			Query query = session.createSQLQuery("select count(*) count " +
					" from bata_config_details where ? " +
							" between effect_start_date and effect_end_date and role_id = ? " +
									" and shift_type_id = ? and deleted_status=0").addScalar("count",Hibernate.INTEGER);
			query.setParameter(0, startdate);
			query.setParameter(1, desginationid);
			query.setParameter(2, shifttypeid);
			List<Integer> list = query.list();
			int count=list.get(0);
			System.out.println("count for start date---------"+count);
			if(count>0){
			   flag=false;
			}else{
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
	
	public boolean getEnddateCheck(int desginationid ,int shifttypeid,String enddate){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		try{
			//select *  from bata_config_details where min_km >401 and role_id= 91 and shift_type_id= 1 and bata_config_id not in (9) and deleted_status=0
			Query query = session.createSQLQuery("select count(*) count from bata_config_details where '"+ enddate +"' between effect_start_date and effect_end_date and role_id= "+ desginationid +" and shift_type_id= " + shifttypeid +" and deleted_status=0").addScalar("count",Hibernate.INTEGER);;
			List<Integer> list = query.list();
			int count=list.get(0);
			System.out.println("count for end date---------"+count);
			if(count>0){
			   flag=false;
			}else{
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
	/***************/
	public int getDetails(int desginationid ,int shifttypeid)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		int count=0;
		try{
			Query query = session.createSQLQuery("select count(*) from bata_config_details where role_id = "+ desginationid +" and shift_type_id = "+ shifttypeid +" and deleted_status=0;").addScalar("count",Hibernate.INTEGER);;
			List<Integer> list = query.list();
			 count=list.get(0);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return count;
	}
	/*************/
	
	public boolean getstartdateCheckingWithdatabase(int desginationid ,int shifttypeid,String startdate,String enddate)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		int count=0;
		try{
			Query query = session.createSQLQuery("select count(*) count from bata_config_details where effect_start_date between '"+ startdate +"' and '"+ enddate +"' and  role_id = "+ desginationid +" and shift_type_id = "+ shifttypeid +" and deleted_status=0;").addScalar("count",Hibernate.INTEGER);;
			List<Integer> list = query.list();
			 count=list.get(0);
			 if(count>0){
				   flag=false;
				}else{
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
	
	public boolean getenddateCheckingWithdatabase(int desginationid ,int shifttypeid,String startdate,String enddate)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		int count=0;
		try{
			Query query = session.createSQLQuery("select count(*) count from bata_config_details where effect_end_date between '"+ startdate +"' and '"+ enddate +"' and  role_id = "+ desginationid +" and shift_type_id = "+ shifttypeid +" and deleted_status=0;").addScalar("count",Hibernate.INTEGER);;
			List<Integer> list = query.list();
			 count=list.get(0);
			 if(count>0){
				   flag=false;
				}else{
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

	/*Get check database record to check*/
	public boolean getTableSize(){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		int count=0;
	try{
		Query query = session.createSQLQuery("SELECT count(*) count FROM bata_config_details where deleted_status=0").addScalar("count",Hibernate.INTEGER);
		List<Integer> list = query.list();
		 count=list.get(0);
		 if(count==0){
			   flag=false;
			}else{
				flag=true;
				}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		if (session != null) {
			session.close();
		}
	}
	return flag;
	}
	

	/*Check record*/
	
	public boolean getCheckRecord(int desginationid ,int shifttypeid,String startdate)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		int count=0;
		try{
			//select count(*) from bata_config_details where role_id = "+ desginationid +" and shift_type_id = "+ shifttypeid +" and deleted_status=0;"
			Query query = session.createSQLQuery(" select count(*) count  from bata_config_details where role_id = "+ desginationid +" and shift_type_id = "+ shifttypeid +" and deleted_status=0 and effect_start_date = '"+ startdate+"'").addScalar("count",Hibernate.INTEGER);
			List<Integer> list = query.list();
			 count=list.get(0);
			 if(count==0){
				   flag=false;
				}else{
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
	
	
	public String getEffectiveDate(int desginationid ,int shifttypeid,int userid,String startdate)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		String effectivedate="";
		int count=0;
		try{
			Query query = session.createSQLQuery("select DATE_SUB(effect_start_date,INTERVAL 1 DAY) effect_end_date  from bata_config_details where  role_id = " + desginationid  + " and shift_type_id= " +  shifttypeid   +" and effect_start_date= '"+ startdate +"' and deleted_status=0  limit 1").addScalar("effect_end_date",Hibernate.STRING);
			List<String> list = query.list();
			 effectivedate=list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return effectivedate;
		
	}
	
	public String getEffectiveDateValue(int desginationid ,int shifttypeid,int userid,String startdate)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		int count=0;
		String effectivedate="";
		
		try{
			Query query = session.createSQLQuery("select effect_end_date effect_end_date  from bata_config_details where role_id= " + desginationid  + " and shift_type_id= " +  shifttypeid   +" and effect_start_date= '"+ startdate +"' and deleted_status=0 limit 1").addScalar("effect_end_date",Hibernate.STRING);
			List<String> list = query.list();
			 effectivedate=list.get(0);
		
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
	}finally{
		if (session != null) {
			session.close();
		}
	}
		return effectivedate;
		
	}
	
	
	public boolean getUpdatedDate(int id,String date,int desginationid,int shifttypeid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		int count=0;
		try{
			String q="update bata_config_details set effect_end_date= ' "+ date + "' where  effect_end_date IS NULL and bata_config_id not in ("+ id +") and role_id= " + desginationid + " and shift_type_id= " + shifttypeid + " and deleted_status=0";
			Query query1 = session.createSQLQuery(q);
			int n=query1.executeUpdate();
			session.getTransaction().commit();
			flag=true;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return flag;
		
	}
	
	
	public int getMinKmForDate(int desginationid ,int shifttypeid,String startdate){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int minkmfornew=0;
		try{
			Query query = session.createSQLQuery("select max(max_km)+1 minkm from bata_config_details where role_id= " + desginationid + " and shift_type_id= " + shifttypeid + " and deleted_status=0 and effect_start_date = '" + startdate + "'").addScalar("minkm",Hibernate.INTEGER);
			List<Integer> list = query.list();
			minkmfornew=list.get(0);
			System.out.println("minkmfornew---"+minkmfornew);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return minkmfornew;
	}
	
	
	public String getEffectivemaxStartdate(int desginationid ,int shifttypeid)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		String effectivedate="";
		try{
			Query query = session.createSQLQuery("select IFNULL(DATE_FORMAT (max(effect_start_date),'%d-%m-%Y'),'NA') effect_start_date  from bata_config_details where role_id = " + desginationid +" and shift_type_id= "+ shifttypeid +" and deleted_status=0 ");
			List<String> list = query.list();
			 effectivedate=list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return effectivedate;
	}
	public Map<Integer, String> getServiceType() {
		Session session = null;
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		Query query = session.createQuery("from ScheduleService where status = 'ACTIVE' and deleted_status = '0'");
		List<ScheduleService> list = query.list();
		resultMap.put(0, "Select");
		for (ScheduleService type : list) {
			resultMap.put(type.getService_type_id(), type.getServiceTypeName());
		}
		
		}
		
		finally{
			session.close();
		}
		return resultMap;

	}
	
	public Map<Integer, String> getDutyType() {
		Session session = null;
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from ShiftType where status ='ACTIVE' and deleted_status='0'");
		List<ShiftType> list = query.list();
		resultMap.put(0, "Select");
		for (ShiftType type : list) {
			resultMap.put(type.getId(), type.getShiftTypeName());
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		finally{
			session.close();
		}
		return resultMap;

	}
	
	
}