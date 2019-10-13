package com.trimax.its.cashremittancevoucher.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;
import com.trimax.its.common.Common;
import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.device.model.Device_Type;

import com.trimax.its.ticketing.dao.DenominationTypeDao;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.vehicle.model.Complaint;
import com.trimax.its.vehicle.model.OrganisationChart;

public class CashRemittanceVoucherDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols, String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria ;	
		
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			criteria = session.createCriteria(CashRemittanceVoucher.class);
		}else{
			criteria = session.createCriteria(CashRemittanceVoucher.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}
		
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();
			conditionGroup.add(Restrictions.like("voucher_number", "%"+ search_term + "%"));
			conditionGroup.add(Restrictions.like("bank_name", "%"+ search_term + "%"));
			conditionGroup.add(Restrictions.like("chart.org_name", "%"+ search_term + "%"));
			conditionGroup.add(Restrictions.like("type.orgType", "%"+ search_term + "%"));
			conditionGroup.add(Restrictions.like("emp.employee_name", "%"+ search_term + "%"));			
			conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
			criteria.add(conditionGroup);

		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}
		criteria.createCriteria("orgChartCash", "chart");
		criteria.createCriteria("orgTypeCash","type" );
		criteria.createCriteria("employeeCash","emp" );
		List<CashRemittanceVoucher> list = criteria.list();
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col, String dir,String viewdeletedrecord) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			String searchSQL = "";
			String sql = " from CashRemittanceVoucher  ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(CashRemittanceVoucher.class);
			}else{
				criteria = session.createCriteria(CashRemittanceVoucher.class);
				criteria.add(Restrictions.eq("deleted_status", 0));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("voucher_number", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("bank_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("chart.org_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("type.orgType", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("emp.employee_name", "%"+ search_term + "%"));
				
				conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
				criteria.add(conditionGroup);

			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			criteria.createCriteria("orgChartCash", "chart");
			criteria.createCriteria("orgTypeCash","type" );
			criteria.createCriteria("employeeCash","emp" );
			
			List<CashRemittanceVoucher> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getVoucher_id()
						+ "' value='"
						+ list.get(i).getVoucher_id() + "'/>");
				ja.add(list.get(i).getVoucher_id());
				ja.add(list.get(i).getVoucher_number());
				ja.add(common.getDateFromDbToPicker(list.get(i).getVoucher_date()));
				ja.add(list.get(i).getEmployeeCash().getEmployee_name());
				ja.add(list.get(i).getBank_name());
				ja.add(list.get(i).getOrgTypeCash().getOrgType());
				ja.add(list.get(i).getOrgChartCash().getOrg_name());
				
				ja.add(list.get(i).getAmount());
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
					
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getVoucher_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getVoucher_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
			}

			totalAfterFilter = getTotalRecords(total, request, col, dir,viewdeletedrecord);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	

	public int createCashRemittanceVoucher(CashRemittanceVoucher cashremittanceVoucher) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;

		try {
			System.out.println("createCashRemittanceVoucher");
			i = (Integer) session.save(cashremittanceVoucher);
			session.getTransaction().commit();
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			session.close();
		}
		return i;

	}
	public String deleteCashRemittanceVoucher(CashRemittanceVoucher cashRemittanceVoucher,
			int voucher_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		
		try {
			
			CashRemittanceVoucher cashremittancevoucher = (CashRemittanceVoucher) session.get(
					CashRemittanceVoucher.class, voucher_id);

			cashremittancevoucher.setDeleted_status(1);
			cashremittancevoucher.setUpdated_by(cashRemittanceVoucher.getUpdated_by());
			cashremittancevoucher.setUpdated_date(new java.util.Date());

			session.update(cashremittancevoucher);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
	public CashRemittanceVoucher getEditedCashRemittanceVoucher(int id) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		CashRemittanceVoucher cashremittanceVoucher = (CashRemittanceVoucher) session.get(CashRemittanceVoucher.class, new Integer(id));
		cashremittanceVoucher.setVoucher_date(common.getDateToPicker(cashremittanceVoucher.getVoucher_date()));
		return cashremittanceVoucher;
		/*DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Complaint complaint = (Complaint) session.get(Complaint.class, new Integer(id));
		complaint.setComplaint_date(common.getDateToPicker(complaint.getComplaint_date()));
		complaint.setIncident_date(common.getDateToPicker(complaint.getIncident_date()));
			//	+ "\t" + fareChart.getRoute_id());
		return complaint;*/

	}

	public String updateCashRemittanceVoucher(CashRemittanceVoucher cashremittanceVoucher,
			int voucher_id) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();

		try {
			//
			CashRemittanceVoucher cashRemittanceVoucher = (CashRemittanceVoucher) session.get(CashRemittanceVoucher.class, voucher_id);
			cashRemittanceVoucher.setVoucher_number(cashremittanceVoucher.getVoucher_number());
			cashRemittanceVoucher.setVoucher_date(common.getDateFromPicker(cashremittanceVoucher.getVoucher_date()));
			cashRemittanceVoucher.setEmployeeCash(cashremittanceVoucher.getEmployeeCash());
			cashRemittanceVoucher.setBank_name(cashremittanceVoucher.getBank_name());
		    cashRemittanceVoucher.setOrgChartCash(cashremittanceVoucher.getOrgChartCash());
		    cashRemittanceVoucher.setOrgTypeCash(cashremittanceVoucher.getOrgTypeCash());
			cashRemittanceVoucher.setAmount(cashremittanceVoucher.getAmount());
			cashRemittanceVoucher.setNotes(cashremittanceVoucher.getNotes());
			cashRemittanceVoucher.setStatus(cashremittanceVoucher.getStatus());
			cashRemittanceVoucher.setUpdated_date(new java.util.Date());
			cashRemittanceVoucher.setUpdated_by(cashremittanceVoucher.getUpdated_by());
			
			session.update(cashRemittanceVoucher);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	public boolean checkCashRemittanceVoucher(String vouchernumber) {
		boolean flag = false;
		String qry = " select voucher_number from cash_remittance_voucher where voucher_number='"
				+ vouchernumber + "' and deleted_status= " + 0;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean checkCashRemittanceVoucherForUpdate(String voucher, int id) {
		boolean flag = false;
		String qry = " select voucher_number from cash_remittance_voucher where voucher_number='"
				+ voucher + "' and voucher_id= " + id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	
	public List<String> getOrgTypeId() {
		Session session = null;
		List list = null;
		String status="ACTIVE";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();
		
			String qry = "select org_type_id from org_type where status='"+status+"' and org_type!='NULL' order by org_type";

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
		String status="ACTIVE";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			String qry = "select org_type from org_type where status='"+status+"' and org_type!='NULL' order by org_type";
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
	
			String qry = "select org_chart_id from org_chart where deleted_status=0 and org_name!='NULL' and org_type_id="
					+ orgtypeid + " order by org_name";

			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
		
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_chart_id").toString());
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
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();
		
			String qry = "select org_name from org_chart where deleted_status=0 and org_name!='NULL' and org_type_id="
					+ orgtypeid + " order by org_name";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
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
			DenominationTypeDao denomtypedao = new DenominationTypeDao();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}

	public Map<Integer, String> getRemittedBy() {
		// TODO Auto-generated method stub
		String status="ACTIVE";
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				
		try{
			SQLQuery query = session.createSQLQuery("select * from employee where status ='"+status+"' ORDER BY EMPLOYEE_NAME");
				query.addEntity(Employee.class);
		List<Employee> list = query.list();
		
		
		for (Employee employeelist : list) {
			resultMap.put(employeelist.getEmployee_id(),employeelist.getEmployee_name());
		}
		}catch(Exception ex)
		{
			
		}finally{
			session.close();
		}
		return resultMap;
		}
	
}
