package com.trimax.its.orgchart.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.device.model.Device;
import com.trimax.its.model.Vendor;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class OrgChartDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*Query query = session.createSQLQuery(
				"select count(*) as count from org_chart where deleted_status=0 and parent_id IS NOT NULL and org_type_id=1 ")
				.addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);*/
		String sql = " from OrganisationChart  ";

		// sql += " order by " + COL_NAME + " " + DIR;

		sql += " limit " + request.getParameter("iDisplayStart") + ", "
				+ request.getParameter("iDisplayLength");
		
//		Criteria criteria = session.createCriteria(OrganisationChart.class);
//		criteria.add(Restrictions.isNotNull("parent_id"));
//		criteria.add(Restrictions.eq("deleted_status",0));
//		criteria.add(Restrictions.eq("orgType.org_type_id",1));
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(OrganisationChart.class);
		}else{
			 criteria = session.createCriteria(OrganisationChart.class);
			
				criteria.add(Restrictions.eq("deleted_status",0));
				
		} criteria.add(Restrictions.isNotNull("parent_id"));
		criteria.add(Restrictions.eq("orgType.org_type_id",1));
		
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();
			
			if(isInteger(search_term))
			{
			int srch=Integer.parseInt(search_term);
			conditionGroup.add(Restrictions.eq("org_chart_id",
					srch ));
			
			
			}else{
			conditionGroup.add(Restrictions.like("org_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("org_code",
					"%"+ search_term + "%" ));
			conditionGroup.add(Restrictions.like("website",
					"%"+ search_term + "%" ));
//			conditionGroup.add(Restrictions.like("parent_org_name", "%"
//					+ search_term + "%"));
			}
//			conditionGroup.add(Restrictions.like("parent_org_name", "%"
//					+ search_term + "%"));
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
		List<OrganisationChart> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from OrganisationChart  ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//			Criteria criteria = session.createCriteria(OrganisationChart.class);
//			criteria.add(Restrictions.isNotNull("parent_id"));
//			criteria.add(Restrictions.eq("deleted_status",0));
//			criteria.add(Restrictions.eq("orgType.org_type_id",1));
			
			
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(OrganisationChart.class);
			}else{
				 criteria = session.createCriteria(OrganisationChart.class);
				// criteria.add(Restrictions.isNotNull("parent_id"));
					criteria.add(Restrictions.eq("deleted_status",0));
					//criteria.add(Restrictions.eq("orgType.org_type_id",1));
			}
			criteria.add(Restrictions.isNotNull("parent_id"));
			criteria.add(Restrictions.eq("orgType.org_type_id",1));
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("org_chart_id",
						srch ));
				
				
				}else{
				conditionGroup.add(Restrictions.like("org_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("org_code",
						"%"+ search_term + "%" ));
				conditionGroup.add(Restrictions.like("website",
						"%"+ search_term + "%" ));
//				conditionGroup.add(Restrictions.like("parent_org_name", "%"
//						+ search_term + "%"));
				}
//				conditionGroup.add(Restrictions.like("parent_org_name", "%"
//						+ search_term + "%"));
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
			
			List<OrganisationChart> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				OrganisationChart org=new OrganisationChart();
				if(list.get(i).getParent_id()==0){
					org.setParent_org_name(list.get(i).getOrg_name());
				}else{
				org.setParent_org_name(getParentName(list.get(i).getParent_id()));
				}
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getOrg_chart_id()
						+ "' value='"
						+ list.get(i).getOrg_chart_id() + "'/>");
				ja.add(list.get(i).getOrg_chart_id());
				ja.add(list.get(i).getOrg_name());
				ja.add(list.get(i).getOrg_name_kannada());
				ja.add(list.get(i).getOrg_code());
				ja.add(list.get(i).getOrg_code_kannada());
				/*if(list.get(i).getParent_id()==0){
					ja.add(list.get(i).getOrg_name());
				}else{
				ja.add(getParentName(list.get(i).getParent_id()));
				}*/
				String address=list.get(i).getAddress()!=null?list.get(i).getAddress().toString():"";
				ja.add(list.get(i).getWebsite());
				ja.add(address);ja.add(list.get(i).getPhone_number());
				ja.add(list.get(i).getCity());ja.add(list.get(i).getState());
				ja.add(list.get(i).getCountry());//ja.add(list.get(i).getLatitude());
				ja.add(list.get(i).getLandmark());//ja.add(list.get(i).getLogitude());*/
				ja.add(list.get(i).getContact_person());
				ja.add(list.get(i).getSector_layer());
				ja.add(list.get(i).getSector_for_line_checking());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getOrg_chart_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getOrg_chart_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				System.out.println("Array----->" + array);
			}
			
			
			totalAfterFilter = total;//getTotalRecords(total,request,cols,dir);
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
	public String getParentName(int parent_id)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select org_name from org_chart where org_chart_id="+parent_id;
		Query query=session.createSQLQuery(sql);
		String parent_name="";
		if(query.list().size()>0){
		parent_name=query.uniqueResult().toString();
		}
		return parent_name;
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
	
	public List<String> getorgId() {

		List list = new ArrayList();
		System.out.println("inside get id");
		String qry = "select   org_chart_id from org_chart where deleted_status=0 ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
		// TODO Auto-generated method stub

	}

	public List<String> getOrgName() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List list = new ArrayList();
		System.out.println("inside get id");
		String qry = "select org_name from org_chart where deleted_status=0";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;

	}
	
	public int  getorgTypeIdfromOrgChart( int id) {

		
		System.out.println("inside get id");
		String qry = "select   org_type_id from org_chart where deleted_status=0  and org_chart_id="+id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		int typeid=0;
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				typeid=Integer.parseInt(rs.get("org_type_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return typeid;
		// TODO Auto-generated method stub

	}
	public List<String> getOrgTypeId(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List list = new ArrayList();
		int type_id=0,depotid=0;
		if(id==1){
			type_id=2;
		}else if(id==2){
			type_id=3;
		}else if(id==3)
		{
			type_id=4;depotid=9;
		}
		System.out.println("inside get id");
		String qry="";
		if(id!=3){
		 qry= "select org_type_id from org_type where status='ACTIVE' and org_type_id="+type_id;
		}
		else{
		qry = "select org_type_id from org_type where status='ACTIVE' and org_type_id in('"+type_id+"','"+depotid+"')";
		}
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_type_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;

	}

	public List<String> getOrgTypeName(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int type_id=0,depotid=0;
		if(id==1){
			type_id=2;
		}else if(id==2){
			type_id=3;
		}else if(id==3)
		{
			type_id=4;depotid=9;
		}
		List list = new ArrayList();
		System.out.println("inside get id");
		String qry="";
		if(id!=3){
		 qry= "select org_type from org_type where status='ACTIVE' and org_type_id="+type_id;
		}
		else{
			qry= "select org_type from org_type where status='ACTIVE' and org_type_id in('"+type_id+"','"+depotid+"')";
		}
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_type").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;

	}
	
	public List<OrganisationChart> getorganization(int type) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<OrganisationChart> list = new ArrayList<OrganisationChart>();
		List<OrganisationChart> list1 = new ArrayList<OrganisationChart>();
		System.out.println("inside get id");
		String qry="";
		if(type==3){
		 qry = "select org_chart_id,org_name from org_chart where  deleted_status=0  and parent_id IS NOT NULL and org_type_id in("+type+","+2+")";
		}else{
			qry="select org_chart_id,org_name from org_chart where  deleted_status=0  and parent_id IS NOT NULL and org_type_id="+type;
		}
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		String respString="";
		OrganisationChart org=null;
		System.out.println("LIST SIZE-======>"+aliasToValueMapList.size());
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				 org=new OrganisationChart();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				//respString="<option value="+rs.get("simcard_id").toString()+">"+rs.get("phone_number").toString()+"</option>";
//				card.setSimcard_id(Integer.parseInt(rs.get("org_chart_id").toString()));
//				card.setPhone_number(new BigInteger(rs.get("org_name").toString()));
//				list.add(card);
				org.setOrg_chart_id(Integer.parseInt(rs.get("org_chart_id")!=null ?rs.get("org_chart_id").toString():""));
				org.setOrg_name(rs.get("org_name")!=null?rs.get("org_name").toString():"");
//				orgchart.setOrg_chart_id(Integer.parseInt(rs.get("org_chart_id")!=null ?rs.get("org_chart_id").toString():""));
//				orgchart.setOrg_name(rs.get("org_name")!=null?rs.get("org_name").toString():"");
				list.add(org);
				
			}
		}
		
//		list.add(respString);
		HibernateUtil.closeSession();
		return list;

	}
	
	/*public Map<Integer, String> getorganization(int type) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from OrganisationChart where deleted_status=0  and parent_id IS NOT NULL and org_type_id= "+type);
		
		try{
		List<OrganisationChart> list = query.list();
		for (OrganisationChart org : list) {
			resultMap.put(org.getOrg_chart_id(),org.getOrg_name());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return resultMap;
		

	}
	*/
	public int saveOrgChart(OrganisationChart orgchart)
	{
		int i=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			i=(Integer) session.save(orgchart);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return i;
	}
	
	public OrganisationChart getEditedDevice(int parseInt) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		OrganisationChart orgchart = (OrganisationChart) session.get(OrganisationChart.class, new Integer(
				parseInt));
		
		// System.out.println("--------->>>" +
		// fareChart.getFarechart_master_id()
		// + "\t" + fareChart.getRoute_id());
		return orgchart;
	}
	public String getText(int orgId)
	{
		String sql="select asText(location_string) as location_string from org_chart where org_chart_id="+orgId;
		Session session =null;
		String lineString="";
		
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query=session.createSQLQuery(sql).addScalar("location_string",Hibernate.STRING);
			if(query.list().size()>0){
				System.out.println("query.uniqueResult().toString()"+query.uniqueResult().toString());
			lineString=query.uniqueResult().toString();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return lineString;
	}
public void upDateLineString(String lineString,int orgId)
{
	String sql="update org_chart set location_string=GeomFromText(' "+ lineString +" ') where org_chart_id="+orgId;
	Session session =null;
	Transaction txn=null;
	try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		txn=session.beginTransaction();
		Query query= session.createSQLQuery(sql);
		query.executeUpdate();
		txn.commit();
	}catch(Exception ex)
	{
		txn.rollback();
	}finally{
		session.close();
	}
}
	public String deleteDevice(OrganisationChart orgchart, int parseInt) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			OrganisationChart deviceobj = (OrganisationChart) session.get(OrganisationChart.class, parseInt);

			//deviceobj.setStatus("INACTIVE");
			deviceobj.setDeleted_status(1);
			//deviceobj.setUpdated_by(orgchart.getUpdated_by());
			deviceobj.setUpdated_date(new java.util.Date());

			session.update(deviceobj);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	public int updateDevice(OrganisationChart orgchart, int org_chart_id) {
		// TODO Auto-generated method stub
		int res=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			OrganisationChart org = (OrganisationChart) session.get(OrganisationChart.class, org_chart_id);
			org.setOrg_code(orgchart.getOrg_code());
			org.setOrg_code_kannada(orgchart.getOrg_code_kannada());
			org.setOrg_name(orgchart.getOrg_name());
			org.setOrg_name_kannada(orgchart.getOrg_name_kannada());
			org.setAddress(orgchart.getAddress());
			org.setCity(orgchart.getCity());
			org.setCountry(orgchart.getCountry());
			org.setState(orgchart.getState());
			org.setContact_person(orgchart.getContact_person());
			org.setLandmark(orgchart.getLandmark());
			org.setLatitude(orgchart.getLatitude());
			org.setLogitude(orgchart.getLogitude());
			org.setPhone_number(orgchart.getPhone_number());
			org.setWebsite(orgchart.getWebsite());
			org.setSector_layer(orgchart.getSector_layer());
			org.setSector_for_line_checking(orgchart.getSector_for_line_checking());
			org.setUpdated_date(new java.util.Date());
		
		    session.update(org);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return res;
		
	}
	
	public boolean checkOrgtype(String orgtypename) throws Exception
	{
		boolean flag=false;
		String qry = " select org_name from org_chart where deleted_status=0 and  org_name='"+orgtypename+"' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public boolean checkOrgtypeForUpdate(String orgtypename,int id)
	{
		boolean flag=false;
		String qry = " select org_name from org_chart where org_name='"+orgtypename+"' and deleted_status=0 and org_chart_id= "+id ;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public boolean checkOrgCodeForUpdate(String orgcode,int id)
	{
		boolean flag=false;
		String qry = " select org_code from org_chart where org_code='"+orgcode+"' and deleted_status=0 and org_chart_id= "+id ;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public boolean checkOrgCode(String orgcode) throws Exception
	{
		boolean flag=false;
		String qry = " select org_code from org_chart where deleted_status=0 and  org_code='"+orgcode+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	
	
	//For Divisions
	
	
	public int getTotalRecordsForDivisions(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*Query query = session.createSQLQuery(
				"select count(*) as count from org_chart where deleted_status=0 and parent_id IS NOT NULL and org_type_id=1 ")
				.addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);*/
		String sql = " from OrganisationChart  ";

		// sql += " order by " + COL_NAME + " " + DIR;

		sql += " limit " + request.getParameter("iDisplayStart") + ", "
				+ request.getParameter("iDisplayLength");
		
//		Criteria criteria = session.createCriteria(OrganisationChart.class);
//		criteria.add(Restrictions.isNotNull("parent_id"));
//		criteria.add(Restrictions.eq("deleted_status",0));
//		criteria.add(Restrictions.eq("orgType.org_type_id",2));
		
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(OrganisationChart.class);
		}else{
			 criteria = session.createCriteria(OrganisationChart.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}

		criteria.add(Restrictions.isNotNull("parent_id"));
		criteria.add(Restrictions.eq("orgType.org_type_id",2));
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();
			
			if(isInteger(search_term))
			{
			int srch=Integer.parseInt(search_term);
			conditionGroup.add(Restrictions.eq("org_chart_id",
					srch ));
			
			
			}else{
			conditionGroup.add(Restrictions.like("org_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("org_code",
					"%"+ search_term + "%" ));
			conditionGroup.add(Restrictions.like("website",
					"%"+ search_term + "%" ));
//			conditionGroup.add(Restrictions.like("parent_org_name", "%"
//					+ search_term + "%"));
			}
//			conditionGroup.add(Restrictions.like("parent_org_name", "%"
//					+ search_term + "%"));
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
		List<OrganisationChart> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForDivisions(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from OrganisationChart  ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
//			//Criteria criteria = session.createCriteria(OrganisationChart.class);
//			criteria.add(Restrictions.isNotNull("parent_id"));
//			criteria.add(Restrictions.eq("deleted_status",0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(OrganisationChart.class);
			}else{
				 criteria = session.createCriteria(OrganisationChart.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}

			criteria.add(Restrictions.isNotNull("parent_id"));
			criteria.add(Restrictions.eq("orgType.org_type_id",2));
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("org_chart_id",
						srch ));
				}else{
				conditionGroup.add(Restrictions.like("org_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("org_code",
						"%"+ search_term + "%" ));
				conditionGroup.add(Restrictions.like("website",
						"%"+ search_term + "%" ));
//				conditionGroup.add(Restrictions.like("parent_org_name", "%"
//						+ search_term + "%"));
				}
//				conditionGroup.add(Restrictions.like("parent_org_name", "%"
//						+ search_term + "%"));
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
			
			List<OrganisationChart> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				OrganisationChart org=new OrganisationChart();
				if(list.get(i).getParent_id()==0){
					org.setParent_org_name(list.get(i).getOrg_name());
				}else{
				org.setParent_org_name(getParentName(list.get(i).getParent_id()));
				}
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getOrg_chart_id()
						+ "' value='"
						+ list.get(i).getOrg_chart_id() + "'/>");
				ja.add(list.get(i).getOrg_chart_id());
				ja.add(list.get(i).getOrg_name());
				ja.add(list.get(i).getOrg_name_kannada());
				ja.add(list.get(i).getOrg_code());
				ja.add(list.get(i).getOrg_code_kannada());
				if(list.get(i).getParent_id()==0){
					ja.add(list.get(i).getOrg_name());
				}else{
				ja.add(getParentName(list.get(i).getParent_id()));
				}
				String address=list.get(i).getAddress()!=null?list.get(i).getAddress().toString():"";
				/*ja.add(list.get(i).getOrg_name_kannada());*/ja.add(list.get(i).getWebsite());
				ja.add(address);ja.add(list.get(i).getPhone_number());
				ja.add(list.get(i).getCity());ja.add(list.get(i).getState());
				ja.add(list.get(i).getCountry());//ja.add(list.get(i).getLatitude());
				ja.add(list.get(i).getLandmark());//ja.add(list.get(i).getLogitude());
				ja.add(list.get(i).getContact_person());
				ja.add(list.get(i).getSector_layer());
				ja.add(list.get(i).getSector_for_line_checking());
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getOrg_chart_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getOrg_chart_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}

				array.add(ja);
				System.out.println("Array----->" + array);
			}
			
			
			totalAfterFilter = total;//getTotalRecordsForDivisions(total,request,cols,dir);
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
	
	public int getTotalRecordsForDepots(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*Query query = session.createSQLQuery(
				"select count(*) as count from org_chart where deleted_status=0 and parent_id IS NOT NULL and org_type_id=1 ")
				.addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);*/
		String sql = " from OrganisationChart  ";

		// sql += " order by " + COL_NAME + " " + DIR;

		sql += " limit " + request.getParameter("iDisplayStart") + ", "
				+ request.getParameter("iDisplayLength");
		
//		Criteria criteria = session.createCriteria(OrganisationChart.class);
//		criteria.add(Restrictions.isNotNull("parent_id"));
//		criteria.add(Restrictions.eq("deleted_status",0));
//		criteria.add(Restrictions.eq("orgType.org_type_id",3));
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(OrganisationChart.class);
		}else{
			 criteria = session.createCriteria(OrganisationChart.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}

		criteria.add(Restrictions.isNotNull("parent_id"));
		criteria.add(Restrictions.eq("orgType.org_type_id",3));
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();
			
			if(isInteger(search_term))
			{
			int srch=Integer.parseInt(search_term);
			conditionGroup.add(Restrictions.eq("org_chart_id",
					srch ));
			
			
			}else{
			conditionGroup.add(Restrictions.like("org_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("org_code",
					"%"+ search_term + "%" ));
			conditionGroup.add(Restrictions.like("website",
					"%"+ search_term + "%" ));
//			conditionGroup.add(Restrictions.like("parent_org_name", "%"
//					+ search_term + "%"));
			}
//			conditionGroup.add(Restrictions.like("parent_org_name", "%"
//					+ search_term + "%"));
			criteria.add(conditionGroup);
			
		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}
		
		List<OrganisationChart> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForDepots(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from OrganisationChart  ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//			Criteria criteria = session.createCriteria(OrganisationChart.class);
//			criteria.add(Restrictions.isNotNull("parent_id"));
//			criteria.add(Restrictions.eq("deleted_status",0));
//			criteria.add(Restrictions.eq("orgType.org_type_id",3));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(OrganisationChart.class);
			}else{
				 criteria = session.createCriteria(OrganisationChart.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}

			criteria.add(Restrictions.isNotNull("parent_id"));
			criteria.add(Restrictions.eq("orgType.org_type_id",3));
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("org_chart_id",
						srch ));
				
				
				}else{
				conditionGroup.add(Restrictions.like("org_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("org_code",
						"%"+ search_term + "%" ));
				conditionGroup.add(Restrictions.like("website",
						"%"+ search_term + "%" ));
//				conditionGroup.add(Restrictions.like("parent_org_name", "%"
//						+ search_term + "%"));
				}
//				conditionGroup.add(Restrictions.like("parent_org_name", "%"
//						+ search_term + "%"));
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
			
			List<OrganisationChart> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				OrganisationChart org=new OrganisationChart();
				if(list.get(i).getParent_id()==0){
					org.setParent_org_name(list.get(i).getOrg_name());
				}else{
				org.setParent_org_name(getParentName(list.get(i).getParent_id()));
				}
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getOrg_chart_id()
						+ "' value='"
						+ list.get(i).getOrg_chart_id() + "'/>");
				ja.add(list.get(i).getOrg_chart_id());
				ja.add(list.get(i).getOrg_name());
				ja.add(list.get(i).getOrg_name_kannada());
				ja.add(list.get(i).getOrg_code());
				ja.add(list.get(i).getOrg_code_kannada());
				if(list.get(i).getParent_id()==0){
					ja.add(list.get(i).getOrg_name());
				}else{
				ja.add(getParentName(list.get(i).getParent_id()));
				}
				String address=list.get(i).getAddress()!=null?list.get(i).getAddress().toString():"";
				/*ja.add(list.get(i).getOrg_name_kannada());*/ja.add(list.get(i).getWebsite());
				ja.add(address);ja.add(list.get(i).getPhone_number());
				ja.add(list.get(i).getCity());ja.add(list.get(i).getState());
				ja.add(list.get(i).getCountry());//ja.add(list.get(i).getLatitude());
				ja.add(list.get(i).getLandmark());//ja.add(list.get(i).getLogitude());
				ja.add(list.get(i).getContact_person());
				ja.add(list.get(i).getSector_layer());
				ja.add(list.get(i).getSector_for_line_checking());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getOrg_chart_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getOrg_chart_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				System.out.println("Array----->" + array);
			}
			
				
			
			totalAfterFilter = total;//getTotalRecordsForDepots(total,request,cols,dir);
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
	
	//For SubStations
	
	
	public int getTotalRecordsForSubstations(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
//		int size=0;
//		try{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*Query query = session.createSQLQuery(
				"select count(*) as count from org_chart where deleted_status=0 and parent_id IS NOT NULL and org_type_id=1 ")
				.addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);*/
		String sql = " from OrganisationChart  ";

		// sql += " order by " + COL_NAME + " " + DIR;

		sql += " limit " + request.getParameter("iDisplayStart") + ", "
				+ request.getParameter("iDisplayLength");
		
//		Criteria criteria = session.createCriteria(OrganisationChart.class);
//		criteria.add(Restrictions.isNotNull("parent_id"));
//		criteria.add(Restrictions.eq("deleted_status",0));
//		criteria.add(Restrictions.eq("orgType.org_type_id",3));
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(OrganisationChart.class);
	}else{
			 criteria = session.createCriteria(OrganisationChart.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}

		criteria.add(Restrictions.isNotNull("parent_id"));
		criteria.add(Restrictions.eq("orgType.org_type_id",4));
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();
			
			if(isInteger(search_term))
			{
			int srch=Integer.parseInt(search_term);
			conditionGroup.add(Restrictions.eq("org_chart_id",
					srch ));
			
			
			}else{
			conditionGroup.add(Restrictions.like("org_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("org_code",
					"%"+ search_term + "%" ));
			conditionGroup.add(Restrictions.like("website",
					"%"+ search_term + "%" ));
//			conditionGroup.add(Restrictions.like("parent_org_name", "%"
//					+ search_term + "%"));
			}
//			conditionGroup.add(Restrictions.like("parent_org_name", "%"
//					+ search_term + "%"));
			criteria.add(conditionGroup);
			
		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}
		List<OrganisationChart> list = criteria.list();
		/*size=list.size();
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		return list.size();
		
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForSubStations(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from OrganisationChart  ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(OrganisationChart.class);
			}else{
				 criteria = session.createCriteria(OrganisationChart.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}

			criteria.add(Restrictions.isNotNull("parent_id"));
			criteria.add(Restrictions.eq("orgType.org_type_id",4));

		   			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("org_chart_id",srch ));
				
				
				}else{
				conditionGroup.add(Restrictions.like("org_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("org_code",
						"%"+ search_term + "%" ));
				conditionGroup.add(Restrictions.like("website",
						"%"+ search_term + "%" ));
//				conditionGroup.add(Restrictions.like("parent_org_name", "%"
//						+ search_term + "%"));
				}
//				conditionGroup.add(Restrictions.like("parent_org_name", "%"
//						+ search_term + "%"));
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
			
			List<OrganisationChart> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				OrganisationChart org=new OrganisationChart();
				if(list.get(i).getParent_id()==0){
					org.setParent_org_name(list.get(i).getOrg_name());
				}else{
				org.setParent_org_name(getParentName(list.get(i).getParent_id()));
				}
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getOrg_chart_id()
						+ "' value='"
						+ list.get(i).getOrg_chart_id() + "'/>");
				ja.add(list.get(i).getOrg_chart_id());
				ja.add(list.get(i).getOrg_name());
				ja.add(list.get(i).getOrg_name_kannada());
				ja.add(list.get(i).getOrg_code());
				ja.add(list.get(i).getOrg_code_kannada());
				if(list.get(i).getParent_id()==0){
					ja.add(list.get(i).getOrg_name());
				}else{
				ja.add(getParentName(list.get(i).getParent_id()));
				}
				String address=list.get(i).getAddress()!=null?list.get(i).getAddress().toString():"";
				/*ja.add(list.get(i).getOrg_name_kannada());*/ja.add(list.get(i).getWebsite());
				ja.add(address);ja.add(list.get(i).getPhone_number());
				ja.add(list.get(i).getCity());ja.add(list.get(i).getState());
				ja.add(list.get(i).getCountry());//ja.add(list.get(i).getLatitude());
				ja.add(list.get(i).getLandmark());//ja.add(list.get(i).getLogitude());
				ja.add(list.get(i).getContact_person());
				ja.add(list.get(i).getSector_layer());
				ja.add(list.get(i).getSector_for_line_checking());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
								System.out.println("deletedstatus--------"+deletedstatus);		
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getOrg_chart_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getOrg_chart_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}


				array.add(ja);
				System.out.println("Array----->" + array);
			}
			
			
			totalAfterFilter = total;//getTotalRecordsForSubstations(total,request,cols,dir);
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
	
	public static void main(String [] args)
	{
		String arr="LINESTRING(12.964292541584731 77.58785247802734,12.962285104608046 77.59008407592773,12.957935602290721 77.58750915527344,12.960444939824349 77.5828742980957,12.966634530926513 77.58682250976562)";
		System.out.println(arr.split("\\("));
	}
	
	
}
