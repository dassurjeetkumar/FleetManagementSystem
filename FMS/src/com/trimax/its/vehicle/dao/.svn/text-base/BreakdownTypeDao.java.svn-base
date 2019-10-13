package com.trimax.its.vehicle.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.AccidentType;
import com.trimax.its.vehicle.model.BreakDownCategory;
import com.trimax.its.vehicle.model.BreakDownType;
import com.trimax.its.vehicle.model.ComponentsTypes;



public class BreakdownTypeDao {
	
	public int getTotalRecords() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(
				"select count(*) as count from breakdown_type where deleted_status=0 ")
				.addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);
		return cnt;
	}

	public List getbreakdowncategory() {
		List<BreakDownCategory> breakdownList = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(BreakDownCategory.class);
		//criteria.add(Restrictions.eq("deleted_status", 0));
		List list = criteria.list();
		System.out.println("list----"+list.get(0));
		
		
		breakdownList = new ArrayList<BreakDownCategory>();

		BreakDownCategory breakdowncategory1 = new BreakDownCategory();
		

		for (int i = 0; i < list.size(); i++) {

			BreakDownCategory breakdowncategory = (BreakDownCategory) list.get(i);
			breakdownList.add(breakdowncategory);
		}
		return breakdownList;
	}
	
	public List getComponentsType() {
		List<ComponentsTypes> componenttypelist = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(ComponentsTypes.class);
		//criteria.add(Restrictions.eq("deleted_status", 0));
		List list = criteria.list();
		System.out.println("list----"+list.get(0));
		
		
		componenttypelist = new ArrayList<ComponentsTypes>();

		//ComponentsTypes comptype = new ComponentsTypes();
		

		for (int i = 0; i < list.size(); i++) {

			ComponentsTypes comptype = (ComponentsTypes) list.get(i);
			componenttypelist.add(comptype);
		}
		return componenttypelist;
	}
	
	
	/*public String getFloorNameByByPid(int id) {

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("Select f.floor_id,f.floor_name from Floor f where f.deleted_status=0 and f.orgChart.org_chart_id="+id+" order by f.floor_name");
		List list = query.list();
		
		Object[] obj;
		int floorId;
		String fName;
		
		String regionTypeAjaxString = "";
		if(list.size()>1){
		regionTypeAjaxString += "<option value='0'>Select</option>";
		}
		
		
		for (Iterator i=list.iterator();i.hasNext();) {
			
			obj=(Object[])i.next();
			floorId=(Integer)obj[0];
			fName=(String)obj[1];
			regionTypeAjaxString += "<option value=" + floorId
					+ ">" + fName + "</option>";
		}
		return regionTypeAjaxString;

	}
	*/
	
	public String getSubComponentType(int id){
		String subComponentAjaxString = "";
		try{
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//Query query = session.createQuery("Select f.floor_id,f.floor_name from Floor f where f.deleted_status=0 and f.orgChart.org_chart_id="+id+" order by f.floor_name");
		Query query=session.createSQLQuery("select sub_components_id,sub_component_name from sub_components where component_type_id="+id);
		System.out.println("query------"+query);
		List list = query.list();
		
		Object[] obj;
		int subcomponentid;
		String subcomponentname;
		
		
		if(list.size()>1){
			subComponentAjaxString += "<option value='0'>Select</option>";
		}
		
		
		for (Iterator i=list.iterator();i.hasNext();) {
			
			obj=(Object[])i.next();
			subcomponentid=(Integer)obj[0];
			subcomponentname=(String)obj[1];
			subComponentAjaxString += "<option value=" + subcomponentid
					+ ">" + subcomponentname + "</option>";
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("subComponentAjaxString---"+subComponentAjaxString);
		return subComponentAjaxString;

	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from BreakDownType ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "+request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(BreakDownType.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			
			if (!request.getParameter("sSearch").trim().equals("")) {
				Junction conditionGroup = Restrictions.disjunction();
				String search_term = request.getParameter("sSearch").trim();
				if(isInteger(search_term)){
					int srch=Integer.parseInt(search_term);
					conditionGroup.add(Restrictions.eq("breakdown_type_id",
							srch ));
				}
				else{
				
					
					conditionGroup.add(Restrictions.like("category.breakdown_category_name",
							"%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("systype.componentName",
							"%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("syssubtype.subComponentType",
							"%" + search_term + "%"));
					
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
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			criteria.createCriteria("breakdown_category", "category");
			criteria.createCriteria("system_type", "systype");
			criteria.createCriteria("system_sub_type", "syssubtype");
			criteria.createCriteria("vehiclelist", "vehicle");
			List<BreakDownType> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getBreakdown_type_id()
						+ "' value='"
						+ list.get(i).getBreakdown_type_id() + "'/>");
				//ja.add(list.get(i).getBreakdown_category().getBreakdown_category_name());
				ja.add(list.get(i).getSystem_type().getComponentName());
				ja.add(list.get(i).getSystem_sub_type().getSubComponentType());
				//ja.add(list.get(i).getVehicle().getVehicleId());
				// ja.add(list.get(i).getNote());
				ja.add(list.get(i).getNotes());
				array.add(ja);
				System.out.println("Array----->" + array);
			}

			totalAfterFilter = getTotalRecords();
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			System.out.println("REsult ------>"+ request.getParameter("iDisplayStart"));
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
	public void saveBreakDownType(BreakDownType breakdowntype)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {

			session.beginTransaction();
			session.save(breakdowntype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		
	}
	public boolean getDuplicate(String accName)
	{
		boolean flag=false;
		String qry = " select accident_type_name from accident_type where accident_type_name='"+accName+"' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public String deleteAccidentType(BreakDownType accidenttype, int accid,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			BreakDownType accobj = (BreakDownType) session.get(BreakDownType.class, accid);

			accobj.setStatus("INACTIVE");
			accobj.setDeleted_status(1);
			accobj.setUpdated_by(Integer.parseInt(request.getSession()
					.getAttribute("userid").toString()));
			accobj.setUpdated_date(new java.util.Date());

			session.update(accobj);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

}
