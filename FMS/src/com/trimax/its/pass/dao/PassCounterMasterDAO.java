package com.trimax.its.pass.dao;

import java.util.Date;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.model.User;
import com.trimax.its.pass.model.PassCounterMaster;
import com.trimax.its.pass.model.PassInstitutionType;
import com.trimax.its.pass.model.PassIssuerCenter;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.util.HibernateUtil;

public class PassCounterMasterDAO {

public int getTotalRecordsForPasscounterMaster(String string, String sdir) {
		
		int cnt = 0;
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="SELECT count(*) count FROM pass_issue_counter picnt inner join pass_issue_center pic " +
					"on pic.pass_issue_center_id=picnt.pass_issue_center_id" +
					" WHERE picnt.status = 'Y'";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	public JSONObject getPasscounterMasterCenterList(int total, HttpServletRequest request,String col,String dir) {
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="SELECT pass_issue_counter_id,pass_center_name,pass_counter_name," +
					"picnt.pass_counter_number,picnt.terminal_no,picnt.status,picnt.remarks FROM pass_issue_counter picnt inner join pass_issue_center pic " +
					"on pic.pass_issue_center_id=picnt.pass_issue_center_id " +
					"WHERE picnt.status = 'Y'";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (pass_issue_counter_id like '%" + search_term + "%'";
				// sql += " OR gen_logsheet_id like '%"+search_term+"%'";
				// sql +=
				// " OR form_four.schedule_number_name like '"+search_term+"%'";
				 sql += " OR pass_counter_name like '"+search_term+"%')";
				
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
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql)
					.addScalar("pass_issue_counter_id", Hibernate.STRING)
					.addScalar("pass_center_name", Hibernate.STRING)
					.addScalar("pass_counter_name", Hibernate.STRING)
					.addScalar("pass_counter_number", Hibernate.STRING)
					.addScalar("terminal_no", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("remarks", Hibernate.STRING);
					

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				int j = i + 1;
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("pass_issue_counter_id").toString()
						+ "' value='"
						+ rs.get("pass_issue_counter_id").toString()+ "'/>");
				ja.add(rs.get("pass_issue_counter_id").toString());
				ja.add(rs.get("pass_counter_name").toString());
				ja.add(rs.get("pass_center_name").toString());
				ja.add(rs.get("pass_counter_number").toString());
				ja.add(rs.get("terminal_no").toString());
				ja.add(rs.get("status").toString());
				ja.add(rs.get("remarks").toString());

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
			totalAfterFilter = getTotalRecordsForPasscounterMaster( col,dir);
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
	
	public int addPassCounterMasterType(PassCounterMaster passcountermaster){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from PassCounterMaster where pass_counter_name='"+passcountermaster.getPass_counter_name().trim()+"' and status='Y'";
				
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		
			
		 i=(Integer)session.save(passcountermaster);
		}else{
			i=-1;
		}
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
	
	
public PassCounterMaster getPassCounterMasterById(int id){
		
		Session session=null;
		Transaction tx=null;
		PassCounterMaster passType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassCounterMaster where pass_issue_counter_id="+id+" and status='Y'";
		
		List list=session.createQuery(query).list();
		
		passType=(PassCounterMaster)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return passType;
	}
	
	public int updatePassCounterMaster(PassCounterMaster passCountermaster){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from PassCounterMaster where pass_counter_name='"+passCountermaster.getPass_counter_name().trim()+"' and status='Y'";			
		
		List<PassCounterMaster> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getPass_issue_counter_id()==passCountermaster.getPass_issue_counter_id()){
			
			PassCounterMaster passCasteTypeNew = (PassCounterMaster) session.get(PassCounterMaster.class,passCountermaster.getPass_issue_counter_id());

			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				passCasteTypeNew.setUpdated_by(Integer.parseInt(userid));	
			}
			
			passCasteTypeNew.setUpdated_date(new Date());
			passCasteTypeNew.setStatus(passCountermaster.getStatus());
			passCasteTypeNew.setRemarks(passCountermaster.getRemarks().trim());
			passCasteTypeNew.setPass_counter_name(passCountermaster.getPass_counter_name().trim());
			passCasteTypeNew.setPass_issue_center_id(passCountermaster.getPass_issue_center_id());
			passCasteTypeNew.setPass_counter_number(passCountermaster.getPass_counter_number());
			passCasteTypeNew.setTerminal_no(passCountermaster.getTerminal_no());
			
            session.update(passCasteTypeNew);
            i=1;
		}else{
			i=-1;
		}
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
	
	public String deletePassCounterMaster(int id) {

		Session session=null;
		String s="error";
		String status="error";

		try{
			
		session= HibernateUtil.getSession("");
		 DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"passistitutetype",id);
			System.out.println("&&&&&&&&&&"+status);
		if(status.trim().equalsIgnoreCase("")){
		session.beginTransaction();
		PassCounterMaster passInstituteType=(PassCounterMaster)session.load(PassCounterMaster.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		passInstituteType.setUpdated_date(new java.util.Date());
		passInstituteType.setUpdated_by(user.getUserId());
		passInstituteType.setStatus("N");
		s="success";
		session.getTransaction().commit();
		}
		}
		catch(Exception ex){
			status="error:";
			ex.printStackTrace();
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
		}

		return status;
	}
	
	public Map<Integer, String> getCenterIssuerType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = null;
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from PassIssuerCenter where status = 'Y' ");
		List<PassIssuerCenter> list = query.list();
		resultMap.put(0, "Select");
		for (PassIssuerCenter type : list) {
			resultMap.put(type.getPass_issue_center_id(), type.getPass_center_name());
		}
		}
		finally{
			session.close();
		}
		return resultMap;
	}
}
