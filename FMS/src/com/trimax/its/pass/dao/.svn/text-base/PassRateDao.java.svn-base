package com.trimax.its.pass.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.fare.model.Farechart_Master;
import com.trimax.its.fare.model.PassengerType;
import com.trimax.its.model.User;
import com.trimax.its.pass.model.PassCasteType;
import com.trimax.its.pass.model.PassCounterMaster;
import com.trimax.its.pass.model.PassDistanceType;
import com.trimax.its.pass.model.PassDurationType;
import com.trimax.its.pass.model.PassIssuerType;
import com.trimax.its.pass.model.PassPurchaseType;
import com.trimax.its.pass.model.PassRate;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.ServiceType;

public class PassRateDao {
//	public List<PassRate> checkDuplicateMaster(PassRate passRate){
//		//check for duplicate entry 
//		String qry="from PassRate where passType.pass_type_id="+passRate.getPassType().getPass_type_id()
//				+" and versionNumber="+passRate.getVersionNumber()
//				+" and  deleted_status=0 order by effective_start_date";
//				
//		Query query=HibernateUtil.getSession("").createQuery(qry);
//		List<PassRate> list=query.list();
//
//		return list;
//	}
	
	public int compareDates(List<PassRate> listDates,Date sdate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int masterId=0;
		//date comparison
		for(int cnt=0;cnt<listDates.size();cnt++){
			    
			    try{
			    	System.out.println("dates="+listDates.get(cnt).getEffective_start_date());		
				    
				    Date dbSdate=sdf.parse(listDates.get(cnt).getEffective_start_date());
				    
				    if(sdate.compareTo(dbSdate)>0 || sdate.compareTo(dbSdate)==0){
				      	
				    	masterId= cnt;
				    }
			    }
			    catch(Exception e){}
		}
		return masterId;
	}
	
//	public int duplicateByQuery(PassRate passRate){
//		int masterId=-1;
//		Common common=new Common();
//		try{
//		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		String strDate=common.getDateTimeFromPicker2(passRate.getEffective_start_date());
//		
//		String endDate="";
//		if(passRate.getEffective_end_date()!=null && (!passRate.getEffective_end_date().trim().isEmpty())){
//			endDate=common.getDateTimeFromPicker2(passRate.getEffective_end_date());
//		}	
//		
//		String qry="from PassRate where passType.pass_type_id="+passRate.getPassType().getPass_type_id()
//				+" and versionNumber="+passRate.getVersionNumber().trim()+" and id!="+passRate.getId()
//				+" and  deleted_status=0 and ((('"+strDate+"' between effective_start_date and  effective_end_date) "
//				+" or '"+strDate+"'=effective_end_date or '"+strDate+"'=effective_start_date )";
//				
//				if(passRate.getEffective_end_date()!=null && (!passRate.getEffective_end_date().trim().isEmpty())){				
//			    qry+=" or ('"+endDate+"' between effective_start_date and ifnull(effective_end_date,effective_start_date)"
//			    	+" or (('"+strDate+"' <= effective_start_date and '"+endDate+"' between effective_start_date and ifnull(effective_end_date,effective_start_date) )"	
//			         +" or ('"+strDate+"'<=effective_start_date and '"+endDate+"' >= ifnull(effective_end_date,effective_start_date))))";
//				}
//				qry+=")";
//				
//				Session session=null;
//				try{
//				 session=HibernateUtil.getSession("");
//				 Query query=session.createQuery(qry);
//				 List<PassRate> list=query.list();
//				 masterId=list.get(0).getId();
//				}
//				catch(Exception e){
//				e.printStackTrace();	
//				}
//				finally{
//					if(session!=null){
//						session.close();
//					}
//				}
//		
//		
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		//date comparison
//		for(int cnt=0;cnt<listDates.size();cnt++){
//			    
//			    try{
//			    	System.out.println("dates="+listDates.get(cnt).getEffective_end_date());		
//			    	Date dbSdate=new Date();
//			    	
//			    	if(listDates.get(cnt).getEffective_end_date()!=null){
//				     dbSdate=sdf.parse(listDates.get(cnt).getEffective_end_date());
//				    
//				    if(sdate.compareTo(dbSdate)<0 || sdate.compareTo(dbSdate)==0){
//				      	
//				    	masterId= cnt;
//				    }
//			    	}else{
//			    		dbSdate=sdf.parse(listDates.get(cnt).getEffective_start_date());
//					    
//					    if(sdate.compareTo(dbSdate)>0 || sdate.compareTo(dbSdate)==0){
//					      	
//					    	masterId= cnt;
//					    }
//			    	}
//			    }
//			    catch(Exception e){}
//		}
		
		
		
		
//		return masterId;
//	}
//	
//	public int duplicateInsertByQuery(PassRate passRate){
//		int masterId=-1;
//		Common common=new Common();
//		try{
//		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		String strDate=common.getDateTimeFromPicker2(passRate.getEffective_start_date());
//		
//		String endDate="";
//		if(passRate.getEffective_end_date()!=null && (!passRate.getEffective_end_date().trim().isEmpty())){
//			endDate=common.getDateTimeFromPicker2(passRate.getEffective_end_date());
//		}	
//		
//		String qry="from PassRate where passType.pass_type_id="+passRate.getPassType().getPass_type_id()
//				+" and versionNumber="+passRate.getVersionNumber().trim()
//				+" and  deleted_status=0 and ((('"+strDate+"' between effective_start_date and  effective_end_date) "
//				+" or '"+strDate+"'=effective_end_date or '"+strDate+"'=effective_start_date )";
//				
//				if(passRate.getEffective_end_date()!=null && (!passRate.getEffective_end_date().trim().isEmpty())){
//			    qry+=" or ('"+endDate+"' between effective_start_date and effective_end_date"
//			    	+" or (('"+strDate+"' <= effective_start_date and '"+endDate+"' between effective_start_date and effective_end_date )"	
//			         +" or ('"+strDate+"'<=effective_start_date and '"+endDate+"' >= effective_end_date)))";
//				}
//				qry+=")";
//				
//				Session session=null;
//				try{
//				 session=HibernateUtil.getSession("");
//				 Query query=session.createQuery(qry);
//				 List<PassRate> list=query.list();
//				 masterId=list.get(0).getId();
//				}
//				catch(Exception e){
//				e.printStackTrace();	
//				}
//				finally{
//					if(session!=null){
//						session.close();
//					}
//				}
//		
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		return masterId;
//	}
	
//	public int addPassRate(PassRate passRate){
//		int i=0;
//		Session session=null;
//		Transaction tx=null;
//		//System.out.println("dt="+passRate.getEffective_start_date());
//		try{
//		
//		List<PassRate> passList=checkDuplicateMaster(passRate);
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
//		
//		int masterId=-1;
//		
//		//check for duplicate
//		if(passList.size()>0){
//
//			masterId=duplicateInsertByQuery(passRate);
//			
//			if(masterId!=-1){
//				return (-2);
//			}
//			Date date1=sdf2.parse(passRate.getEffective_start_date());
//			masterId=compareDates(passList,date1);
////			Date date2=sdf.parse(passList.get(masterId).getEffective_start_date());
////			
////			if(date1.compareTo(date2)==0){
////	    		
////	    		return (-2);//"duplicate";
////	    	}else{
////	    		if(date2.compareTo(date1)>0){
////	    			
////	    			return (-2);//"after";
////	    		}
////	    	}
////			
////			if(passList.get(passList.size()-1).getEffective_end_date()!=null){
////				//end date
////				Date date4=sdf.parse(passList.get(masterId).getEffective_end_date());
////				
////				if(date4.compareTo(date1)>0 || date1.compareTo(date4)==0){
////					
////	    			return (-2);//"duplicate";
////	    		}
////			}
//			
//		}
//		session=HibernateUtil.getSession("");
//		tx=session.beginTransaction();
//		PassRate passRateNew=passRate;
//		
//		Common common=new Common();
//		
//		passRateNew.setEffective_start_date(common.getDateTimeFromPicker2(passRate.getEffective_start_date()));
//		
//		if(passRate.getEffective_end_date()!=null && passRate.getEffective_end_date().length()>0){
//			passRateNew.setEffective_end_date(common.getDateTimeFromPicker2(passRate.getEffective_end_date()));		
//		}else{
//			passRateNew.setEffective_end_date(null);
//		}
//		
//		i=(Integer)session.save(passRateNew);
//
//		//update eff end date of old master
//		if(passList.size()>0){
//					
//		 int oldMasterId=passList.get(masterId).getId();
//	     Date newDate = sdf.parse(passRate.getEffective_start_date());
//	     Calendar cal = Calendar.getInstance();
//	     cal.setTime(newDate);
//	     cal.add(Calendar.DATE, -1);
//		 String updatedEndDt=sdf.format(cal.getTime());
//
//		 String queryString = "update PassRate set effective_end_date='"+updatedEndDt+"' where id="+oldMasterId;
//			
//		 if(passList.get(masterId).getEffective_end_date()==null){
//			Query query = session.createQuery(queryString);
//			query.executeUpdate();
//		 }			 
//		}
//		 
//		tx.commit();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			tx.rollback();
//			i=-1;
//		}
//		finally{
//			 if(session!=null && session.isOpen()){
//				 session.close();
//			 }
//		}
//		
//		return i;
//	}
//	
	public int addPassRateNew(PassRate passrate){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		//String query="from PassCounterMaster where pass_counter_name='"+passcountermaster.getPass_counter_name().trim()+"' and status='Y'";
				
		//List list=session.createQuery(query).list();
		
		//if(list.size()<=0){
		
			
		 i=(Integer)session.save(passrate);
//		}else{
//			i=-1;
//		}
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return i;
	}
	
	public int updatePassRateNew(PassRate passRate){
		int i=0;
		Session session=null;
		Transaction tx=null;
		Common common = new Common();
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		//String qry="from PassDistanceType where pass_distance_name='"+passDistance.getPass_distance_name().trim()+"' and deleted_status=0";			
		
		//List<PassDistanceType> list=session.createQuery(qry).list();
	
		
		//if(list.size()<=0 || list.get(0).getPass_distance_id()==passDistance.getPass_distance_id()){
			
			PassRate passRateNew = (PassRate) session.get(PassRate.class,passRate.getId());

			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				passRateNew.setUpdated_by(Integer.parseInt(userid));	
			}
			
			
			passRateNew.setPassenger_type(passRate.getPassenger_type());
			passRateNew.setPass_issuer_type(passRate.getPass_issuer_type());
			passRateNew.setService_type_id(passRate.getService_type_id());
			passRateNew.setPass_type_id(passRate.getPass_type_id());
			passRateNew.setPass_sub_type_id(passRate.getPass_sub_type_id());
			passRateNew.setPass_purchase_type(passRate.getPass_purchase_type());
			passRateNew.setPass_distance_type(passRate.getPass_distance_type());
			passRateNew.setPass_duration_type(passRate.getPass_duration_type());
			passRateNew.setValidity_months(passRate.getValidity_months());
			passRateNew.setPass_amount(passRate.getPass_amount());
			passRateNew.setService_tax_amount(passRate.getService_tax_amount());
			passRateNew.setProcessing_amount(passRate.getProcessing_amount());
			passRateNew.setTotal_amount(passRate.getTotal_amount());
			passRateNew.setPass_start_date(passRate.getPass_start_date());
			passRateNew.setPass_expiry_date(passRate.getPass_expiry_date());
			passRateNew.setPass_start_time(passRate.getPass_start_time());
			passRateNew.setPass_end_time(passRate.getPass_end_time());
			passRateNew.setPass_to_validate(passRate.getPass_to_validate());
			passRateNew.setUpgrade_availability(passRate.getUpgrade_availability());
			passRateNew.setRoute_to_validate(passRate.getRoute_to_validate());
			passRateNew.setException_day(passRate.getException_day());
			passRateNew.setExtension_expiry_date(passRate.getExtension_expiry_date());
			passRateNew.setEffective_start_date(common.getDateFromPicker(passRate.getEffective_start_date()));
			passRateNew.setEffective_end_date(common.getDateFromPicker(passRate.getEffective_end_date()));
			passRateNew.setExtension_status(passRate.getExtension_status());
			passRateNew.setNotes(passRate.getNotes());
			passRateNew.setVersion_number(passRate.getVersion_number().trim());
			passRateNew.setUpdated_date(new Date());
			passRateNew.setNotes(passRate.getNotes().trim());
			//passRateNew.setPass_distance_name(passRate.getPass_distance_name().trim());
			
            session.update(passRateNew);
            i=1;
//		}else{
//			i=-1;
//		}
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			i=0;
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return i;
	}
	
	
//	public int updatePassRate(PassRate passRate){
//		int i=0;
//		Session session=null;
//		Transaction tx=null;
//		
//		try{
////		HttpServletRequest request = ServletActionContext.getRequest();
////		session=HibernateUtil.getSession("");
////		tx=session.beginTransaction();
//		
//		
//		try{
//			List<PassRate> passList=checkDuplicateMaster(passRate);
//			
//			int masterId=-1;
//			
//			//check for duplicate
//			if(passList.size()>0){
//				masterId=duplicateByQuery(passRate);
//				
//				if(masterId!=-1){
//					return (-2);
//				}
////			
////				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
////			
////				Date date1=sdf2.parse(passRate.getEffective_start_date());
////				masterId=compareDates(passList,date1);
////				Date date2=sdf.parse(passList.get(masterId).getEffective_start_date());
////			
////				
////			if(date1.compareTo(date2)==0 && passList.get(masterId).getId()!=passRate.getId()){
////
////	    		return (-2);
////	    	}else{
////	    		if(date2.compareTo(date1)>0 && passList.get(masterId).getId()!=passRate.getId()){
////	    			return (-2);
////	    		}
//	    	}
//			
//		
////			if(passList.size()>0){
////			if(passList.get(masterId).getEffective_end_date()!=null){
////				//end date
////				Date date3=new Date();
////				Date date4=new Date();
////				Date date5=new Date();
////				date3=sdf2.parse(passRate.getEffective_end_date());
////				masterId=compareEndDates(passList,date3);
////				
////				if(passList.get(masterId).getEffective_end_date()!=null){
////				    date4=sdf.parse(passList.get(masterId).getEffective_end_date());
////				}
////					date5=sdf.parse(passList.get(masterId).getEffective_start_date());	
////				
////				
////				if((date4.compareTo(date1)>0 || date1.compareTo(date4)==0) && passList.get(masterId).getId()!=passRate.getId()){
////					return (-2);
////	    		}
////				
////				if((date4.compareTo(date3)<0 || date4.compareTo(date3)==0) && passList.get(masterId).getId()!=passRate.getId()){
////					return (-2);
////	    		}
//				
//				//
////				if((date5.compareTo(date1)>0 || date1.compareTo(date4)==0) && passList.get(masterId).getId()!=passRate.getId()){
////					return (-2);
////	    		}
////				
////				if((date5.compareTo(date3)<0 || date5.compareTo(date3)==0) && passList.get(masterId).getId()!=passRate.getId()){
////					return (-2);
////	    		}
//			//}
////			}
//			
////			}	
//		}catch(Exception ex){ex.printStackTrace();}
//
//		session=HibernateUtil.getSession("");
//		tx=session.beginTransaction();
//		
//		PassRate passRateNew=(PassRate) session.get(PassRate.class,passRate.getId());
//		
//		Common common=new Common();
//		
//		passRateNew.setEffective_start_date(common.getDateTimeFromPicker2(passRate.getEffective_start_date()));
//		
//		if(passRate.getEffective_end_date()!=null && passRate.getEffective_end_date().length()>0){
//			passRateNew.setEffective_end_date(common.getDateTimeFromPicker2(passRate.getEffective_end_date()));		
//		}else{
//			passRateNew.setEffective_end_date(null);
//		}
//		
//		passRateNew.setVersionNumber(passRate.getVersionNumber().trim());
//		passRateNew.setPassType(passRate.getPassType());
//		passRateNew.setAmount(passRate.getAmount());
//		passRateNew.setStatus(passRate.getStatus());
//		passRateNew.setNotes(passRate.getNotes().trim());
//		
//		session.update(passRateNew);
//		i=1;
//		
//		tx.commit();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			tx.rollback();
//			i=-1;
//		}
//		finally{
//			 if(session!=null && session.isOpen()){
//				 session.close();
//			 }
//		}
//		return i;
//	}
	
	public PassRate getPassRateById(int id){
	
		Session session=null;
		Transaction tx=null;
		PassRate passRate=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassRate where id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		passRate=(PassRate)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return passRate;
	}
	
	public String deletePassRate(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		PassRate passRate=(PassRate)session.load(PassRate.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		passRate.setUpdated_date(new java.util.Date());
		passRate.setUpdated_by(user.getUserId());
		passRate.setDeleted_status(1);
		s="success";
		session.getTransaction().commit();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
		}

		return s;
	}
	
	
	
//	public Map<Integer,String> getPassType(){
//		Map<Integer, String> resultMap = new HashMap<Integer, String>();
//		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		Query query = session.createQuery("from PassType where deleted_status=0 and status='Active' order by pass_type_name desc");
//		List<PassType> list = query.list();
//
//		for (PassType pType : list) {
//			
//			resultMap.put(pType.getPass_type_id(),pType.getPass_type_name());
//		}
//		return resultMap;
//	}
	
	  public Map<Integer, String> getPassType() {
	        Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	       // int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("org_chart_id").toString());

	        try {
	            Query query = session
	                    .createSQLQuery("SELECT pass_type_id,pass_type_name FROM passtype where deleted_status=0 and status='Active'").addScalar("pass_type_id", Hibernate.INTEGER).addScalar("pass_type_name", Hibernate.STRING);
	            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	            List<Map<String, Object>> aliasToValueMapList = query.list();
	            resultMap.put(0, "ALL");
	            System.out.println("Size of usertype type List : " + aliasToValueMapList.size());
	            for (int i = 0; i < aliasToValueMapList.size(); i++) {
	                Map<String, Object> rs = aliasToValueMapList.get(i);
	                resultMap.put(Integer.parseInt(rs.get("pass_type_id").toString()), rs.get("pass_type_name").toString());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null) {
	                session.close();
	            }

	        }
	        return resultMap;
	    }
	public Map<Integer,String> getPassengerType(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from PassengerType where deleted_status=0 and status='Active'");
		List<PassengerType> list = query.list();

		for (PassengerType pType : list) {
			
			resultMap.put(pType.getId(),pType.getPassengerTypeName());
		}
		return resultMap;
	}
	public Map<Integer,String> getPaIssuerType(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from PassIssuerType where deleted_status=0 and status='Active'");
		List<PassIssuerType> list = query.list();

		for (PassIssuerType pType : list) {
			
			resultMap.put(pType.getPass_issuer_id(),pType.getPass_issuer_name());
		}
		return resultMap;
	}
	
	public Map<Integer,String> getPurchaseType(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from PassPurchaseType where deleted_status=0 and status='Active'");
		List<PassPurchaseType> list = query.list();

		for (PassPurchaseType pType : list) {
			
			resultMap.put(pType.getPass_purchase_id(),pType.getPass_purchase_name());
		}
		return resultMap;
	}
	
	public Map<Integer,String> getPassDistanceType(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from PassDistanceType where deleted_status=0 and status='Active'");
		List<PassDistanceType> list = query.list();

		for (PassDistanceType pType : list) {
			
			resultMap.put(pType.getPass_distance_id(),pType.getPass_distance_name());
		}
		return resultMap;
	}
	public Map<Integer,String> getPassDurationType(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from PassDurationType where deleted_status=0 and status='Active'");
		List<PassDurationType> list = query.list();

		for (PassDurationType pType : list) {
			
			resultMap.put(pType.getPass_duration_id(),pType.getPass_duration_name());
		}
		return resultMap;
	}
	public Map<Integer,String> getServiceType(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from ServiceType where deleted_status=0 and status='Active'");
		List<ServiceType> list = query.list();

		for (ServiceType pType : list) {
			
			resultMap.put(pType.getService_type_id(),pType.getService_type_name());
		}
		return resultMap;
	}
	public Map<Integer,String> getPassCategory(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from PassCasteType where  status='Y'");
		List<PassCasteType> list = query.list();

		for (PassCasteType pType : list) {
			
			resultMap.put(pType.getPass_cast_id(),pType.getPass_cast_name());
		}
		return resultMap;
	}
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		Session session=null;
		int cnt = 0;
		Common common = new Common();
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="select count(*) count from pass_rate_master prm " +
					"left join passenger p on p.passenger_type_id=prm.passenger_type " +
					"left join pass_issuer_type pi on pi.pass_issuer_id=prm.pass_issuer_type " +
					"left join passtype pt on pt.pass_type_id=prm.pass_type_id " +
					"left join pass_purchase_type ppt on ppt.pass_purchase_id=prm.pass_purchase_type " +
					"left join pass_distance_type pdt on pdt.pass_distance_id=prm.pass_distance_type " +
					"left join pass_duration_type on pass_duration_type.pass_duration_id=prm.pass_duration_type " +
					"left join pass_caste_category on pass_caste_category.pass_cast_id=prm.pass_cast_id " +
					"inner join service_type st on st.service_type_id = prm.service_type_id WHERE prm.deleted_status = '0'";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		 String sql="select prm.pass_rate_master_id,ifnull(p.passenger_type,'') passenger_type,ifnull(pi.pass_issuer_name,'') pass_issuer_name," +
		 	    	"ifnull(pt.pass_type_name,'') pass_type_name,ifnull(ppt.pass_purchase_name,'') pass_purchase_name," +
					"ifnull(prm.pass_sub_type_id,'') pass_sub_type_id,ifnull(pdt.pass_distance_name,'') pass_distance_name," +
					"ifnull(pass_duration_name,'') pass_duration_name,ifnull(pass_cast_name,'') pass_cast_name,ifnull(validity_months,'') validity_months," +
					"ifnull(pass_amount,'') pass_amount,ifnull(psub.sub_type_name,'') sub_type_name," +
					"ifnull(service_tax_amount,'') service_tax_amount,ifnull(processing_amount,'') processing_amount,ifnull(total_amount,'') total_amount," +
					"ifnull(prm.pass_start_date,'') pass_start_date,ifnull(prm.pass_expiry_date,'') pass_expiry_date,ifnull(pass_start_time,'') pass_start_time," +
					"ifnull(pass_end_time,'') pass_end_time,ifnull(pass_to_validate,'') pass_to_validate," +
					"ifnull(service_type_name,'') service_type_name,ifnull(effective_start_date,'') effective_start_date," +
					"ifnull(effective_end_date,'') effective_end_date,ifnull(version_number,'') version_number " +
					"from pass_rate_master prm " +
					"left join passenger p on p.passenger_type_id=prm.passenger_type " +
					"left join pass_issuer_type pi on pi.pass_issuer_id=prm.pass_issuer_type " +
					"left join passtype pt on pt.pass_type_id=prm.pass_type_id " +
					"left join pass_purchase_type ppt on ppt.pass_purchase_id=prm.pass_purchase_type " +
					"left join pass_sub_type psub on psub.pass_subtype_id=prm.pass_sub_type_id " +
					"left join pass_distance_type pdt on pdt.pass_distance_id=prm.pass_distance_type " +
					"left join pass_duration_type on pass_duration_type.pass_duration_id=prm.pass_duration_type " +
					"left join pass_caste_category on pass_caste_category.pass_cast_id=prm.pass_cast_id " +
					"left join service_type st on st.service_type_id = prm.service_type_id WHERE prm.deleted_status = '0'";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (pass_rate_master_id like '%" + search_term + "%'";
				 sql += " OR p.passenger_type like '%"+search_term+"%')";
				
			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			} else {
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql)
					.addScalar("pass_rate_master_id", Hibernate.STRING)
					.addScalar("passenger_type", Hibernate.STRING)
					.addScalar("pass_issuer_name", Hibernate.STRING)
					.addScalar("pass_type_name", Hibernate.STRING)
					.addScalar("pass_purchase_name", Hibernate.STRING)
					.addScalar("sub_type_name", Hibernate.STRING)
					.addScalar("pass_distance_name", Hibernate.STRING)
					.addScalar("pass_duration_name", Hibernate.STRING)
					.addScalar("pass_cast_name", Hibernate.STRING)
					.addScalar("validity_months", Hibernate.STRING)
					.addScalar("pass_amount", Hibernate.STRING)
					.addScalar("service_tax_amount", Hibernate.STRING)
					.addScalar("processing_amount", Hibernate.STRING)
					.addScalar("total_amount", Hibernate.STRING)
					.addScalar("pass_start_date", Hibernate.STRING)
					.addScalar("pass_expiry_date", Hibernate.STRING)
					.addScalar("pass_start_time", Hibernate.STRING)
					.addScalar("pass_end_time", Hibernate.STRING)
					.addScalar("pass_to_validate", Hibernate.STRING)
					.addScalar("service_type_name", Hibernate.STRING)
					.addScalar("effective_start_date", Hibernate.STRING)
					.addScalar("effective_end_date", Hibernate.STRING)
					.addScalar("version_number", Hibernate.STRING);
					
					

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				int j = i + 1;
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("pass_rate_master_id").toString()
						+ "' value='"
						+ rs.get("pass_rate_master_id").toString()+ "'/>");
				ja.add(rs.get("pass_rate_master_id").toString());
				ja.add(rs.get("passenger_type").toString());
				ja.add(rs.get("pass_issuer_name").toString());
				ja.add(rs.get("pass_type_name").toString());
				ja.add(rs.get("pass_purchase_name").toString());
				ja.add(rs.get("service_type_name").toString());
				ja.add(rs.get("sub_type_name").toString());
				ja.add(rs.get("pass_distance_name").toString());
				ja.add(rs.get("pass_duration_name").toString());
				ja.add(rs.get("pass_cast_name").toString());
				ja.add(rs.get("validity_months").toString());
				ja.add(rs.get("pass_amount").toString());
				ja.add(rs.get("service_tax_amount").toString());
				ja.add(rs.get("processing_amount").toString());
				ja.add(rs.get("total_amount").toString());
				ja.add(rs.get("pass_start_date").toString());
				//ja.add(rs.get("pass_expiry_date").toString());
				ja.add(rs.get("pass_start_time").toString());
				ja.add(rs.get("pass_end_time").toString());
				ja.add(rs.get("pass_to_validate").toString());
				
				ja.add(rs.get("effective_start_date").toString());
				ja.add(rs.get("effective_end_date").toString());
				ja.add(rs.get("version_number").toString());

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
			totalAfterFilter = getTotalRecords(request,col,dir,viewdeletedrecord);
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
}
