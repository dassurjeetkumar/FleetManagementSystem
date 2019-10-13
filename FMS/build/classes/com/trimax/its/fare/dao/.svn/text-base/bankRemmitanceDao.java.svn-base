package com.trimax.its.fare.dao;

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

import com.trimax.its.fare.model.bankRemmitanceModal;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.util.HibernateUtil;



public class bankRemmitanceDao {
	
	public Map<Integer, String> getDepotNo() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = null;
		try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				Query query = session
						.createSQLQuery("SELECT `org_chart_id`, `org_name` FROM `org_chart` where deleted_status=0").addScalar("org_chart_id", Hibernate.INTEGER).addScalar("org_name", Hibernate.STRING);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				resultMap.put(0, "Select");
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String, Object> rs = aliasToValueMapList.get(i);
					resultMap.put(Integer.parseInt(rs.get("org_chart_id").toString()),rs.get("org_name").toString());
				}	
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				  if(session!=null){ session.close(); }
				 
			}
			return resultMap;
		
	}

	public boolean saveBankRemittance(bankRemmitanceModal brmodal) {
		boolean isSuccess = false;
		Session session = null;
		try{
			String userId= (ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			brmodal.setDeletedstatus(0);
			brmodal.setInsertedby(userId);
			brmodal.setInserteddate(new Date());
			
			int id = (Integer) session.save(brmodal);
			System.out.println("id........................."+id);
			ServletActionContext.getRequest().getSession().setAttribute("id", id);

			session.getTransaction().commit();
			isSuccess = true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			isSuccess = false;
		}finally{
			if(session!=null){
				session.close();
			}
			return isSuccess;
		}
	}

	public JSONObject getList(int total, HttpServletRequest request,String col,String dir){
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		try{
			
			
			String sql="";
			sql = " SELECT id, org_name,Bank_name,Account_number,Division,brd.Address as addr FROM Bank_Remmitance_Details brd " +
					"inner join org_chart o on o.org_chart_id=brd.Depot where brd.deleted_status=0";
			
		
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and  ( id like '"+search_term+"%'";
				sql += " OR org_name like '"+search_term+"%'";
				sql += " OR Bank_name like '"+search_term+"%'";
				sql += " OR Account_number like '"+search_term+"%'";
				sql += " OR Division like '"+search_term+"%'";
				sql += " OR addr like '%"+search_term+"%')";
				
			}
			
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
		
			
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			
		Query query = session.createSQLQuery(sql).addScalar("id").addScalar("org_name").addScalar("Bank_name").addScalar("Account_number")
				.addScalar("Division").addScalar("addr");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		   
		
		JSONArray array = new JSONArray();
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
					+ rs.get("id")
					+ "' value='"
					+ rs.get("id") + "'/>");
			ja.add(rs.get("id").toString());
			ja.add(rs.get("org_name").toString());
			ja.add(rs.get("Bank_name").toString());
			ja.add(rs.get("Account_number").toString());
			ja.add(rs.get("Division").toString());			
			ja.add(rs.get("addr").toString());
			
			array.add(ja);
			
		}
		int cnt=0;
			//totalAfterFilter = aliasToValueMapList.size();
			result.put("aaData", array);
			
				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalRecordsForBRDetails();//getTotalRecordsForCount(search_term3);
		
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

	public int getTotalRecordsForBRDetails() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
				
			String sql="";
			sql = " SELECT id, org_name,Bank_name,Account_number,Division,brd.Address as addr FROM Bank_Remmitance_Details brd " +
					"inner join org_chart o on o.org_chart_id=brd.Depot where brd.deleted_status=0";
			
		
			String search_term1 = request.getParameter("sSearch");
			////System.out.println("search_term-------"+search_term1);
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				//System.out.println("search_term---------"+search_term);
				sql += " and  ( id like '"+search_term+"%'";
				sql += " OR org_name like '"+search_term+"%'";
				sql += " OR Bank_name like '"+search_term+"%'";
				sql += " OR Account_number like '"+search_term+"%'";
				sql += " OR Division like '"+search_term+"%'";
				sql += " OR brd.Address like '%"+search_term+"%')";
				
			}
			Query query = session.createSQLQuery(sql).addScalar("id").addScalar("org_name").addScalar("Bank_name").addScalar("Account_number")
					.addScalar("Division").addScalar("addr");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			cnt=aliasToValueMapList.size();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		return cnt;
	}

	public bankRemmitanceModal getEdited(int parseInt) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		bankRemmitanceModal brm = (bankRemmitanceModal) session.get(bankRemmitanceModal.class, new Integer(
				parseInt));
		
		return brm;
	}

	public boolean saveEditBankRemittance(bankRemmitanceModal brmodal, int i) {
		boolean isSuccess = false;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try{
			bankRemmitanceModal brm = (bankRemmitanceModal) session.get(bankRemmitanceModal.class, i);
			String userId= (ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			
		brm.setDepotno(brmodal.getDepotno());
		brm.setBankname(brmodal.getBankname());
		brm.setAccnumber(brmodal.getAccnumber());
		brm.setDivision(brmodal.getDivision());
		brm.setAddr(brmodal.getAddr());
		brm.setUpdatedby(userId);
		brm.setUpdateddate(new Date());
		session.update(brm);
		session.getTransaction().commit();
		isSuccess=true;
	} catch (Exception ex) {
		session.getTransaction().rollback();
		ex.printStackTrace();
		isSuccess = false;
	} finally {
		session.close();
	}
		return isSuccess;
	}

	public boolean DeleteDetails(int id) {
		Session session = null;
		boolean isSuccess = false;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String userId= (ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			String Innersql = " UPDATE Bank_Remmitance_Details set deleted_status='1',updated_by='"+userId+"',updated_date=now() WHERE id='"+id+"'";
			Query qry2 = session.createSQLQuery(Innersql);
			qry2.executeUpdate();
			
			
			session.getTransaction().commit();
			isSuccess = true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}

}
