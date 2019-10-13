package com.trimax.its.orgchart.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.device.model.Device;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class OrgTypeDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir
) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql = " from OrgType  ";
		sql += " limit " + request.getParameter("iDisplayStart") + ", "
				+ request.getParameter("iDisplayLength");
		
		Criteria criteria = session.createCriteria(OrgType.class);
		//criteria.add(Restrictions.isNotNull("orgType"));
		//criteria.add(Restrictions.eq("deleted_status",0));
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();
			Junction conditionGroup = Restrictions.disjunction();
			if(isInteger(search_term))
			{
			int srch=Integer.parseInt(search_term);
			conditionGroup.add(Restrictions.eq("org_type_id",
					srch ));
			}else{
			conditionGroup.add(Restrictions.like("orgType", "%"+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status",search_term,MatchMode.START ));
			}
			criteria.add(conditionGroup);
			
		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}
		@SuppressWarnings("unchecked")
		List<OrgType> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir
) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from OrgType  ";
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(OrgType.class);
			criteria.add(Restrictions.eq("status","ACTIVE"));
			//criteria.add(Restrictions.isNotNull("orgType"));
			//criteria.add(Restrictions.eq("deleted_status",0));
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				Junction conditionGroup = Restrictions.disjunction();
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("org_type_id",
						srch ));
				}else{
				conditionGroup.add(Restrictions.like("orgType", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status",search_term,MatchMode.START ));
				}
			criteria.add(conditionGroup);
				
			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<OrgType> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getOrg_type_id()
						+ "' value='"
						+ list.get(i).getOrg_type_id()+ "'/>");
				ja.add(list.get(i).getOrg_type_id());
				ja.add(list.get(i).getOrgType());
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				
				array.add(ja);
				System.out.println("Array----->" + array);
			}
			
			
			totalAfterFilter = getTotalRecords(total,request,cols,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
			
			System.out.println("REsult ------>"
					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(Exception e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}

	public int saveOrgChart(OrgType organizationType) {
		// TODO Auto-generated method stub
		int i=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			i=(Integer) session.save(organizationType);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return i;
	}

	public boolean checkOrgtype(Object org_name) {
		boolean flag=false;
		String qry = " select org_type from org_type where org_type='"+org_name+"' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}

	public boolean checkOrgtypeForUpdate(String orgType, int org_type_id) {
		boolean flag=false;
		System.out.println("checkOrgtypeForUpdate"+org_type_id);
		String qry = " select org_name from org_chart where org_name='"+orgType+"' and org_type_id="+org_type_id+" " ;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public OrgType getEditedOrg(int parseInt) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		OrgType orgtype = (OrgType) session.get(OrgType.class, new Integer(
				parseInt));
		// System.out.println("--------->>>" +
		// fareChart.getFarechart_master_id()
		// + "\t" + fareChart.getRoute_id());
		return orgtype;
	}
	public String  updateOrgType(OrgType org, int orgId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int id=0;
		try {
			//
			System.out.println("i m in updateOrgType()");
			System.out.println("organizationType.orgType"+org.getOrgType());
			System.out.println("organizationType.orgid"+orgId);
			OrgType orgObj = (OrgType) session.get(OrgType.class, orgId);
			orgObj.setOrgType(org.getOrgType());
			orgObj.setNotes(org.getNotes());
			orgObj.setStatus(org.getStatus());
			session.update(orgObj);
			System.out.println("i m in updatelast");
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	public boolean deleteOrg(int orgId) {
		Session session = null;
		boolean isSuccess = false;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			
			String Innersql = " UPDATE org_type set status='INACTIVE' WHERE org_type_id='"+orgId+"'";
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