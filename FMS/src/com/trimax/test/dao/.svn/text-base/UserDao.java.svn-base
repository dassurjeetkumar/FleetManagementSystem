package com.trimax.test.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.device.model.Device_Type;
import com.trimax.its.util.HibernateUtil;
import com.trimax.test.model.UserTest;
 

public class UserDao {
    public UserDao()
    {
    
    }
    public List<UserTest> insert(UserTest s)
    {
   List branch=new ArrayList();
//        SessionFactory sf = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();           
//      Session ss = sf.openSession();
//      org.hibernate.Transaction t = ss.beginTransaction();
//       Query query = ss.createQuery("from Student");
//       branch=query.list();
//        System.out.println("size="+branch.size());

//    Student student=new Student();
//    student.setId(1); 
//    student.setName("ram");
//     System.out.println("begin transaction #################");
    //transaction.begin();
    
        //System.out.println("name"+s.getName());
        //s.setName("ram");
   //ss.get(Student.class);
    
//   
    /*Configuration cf=new Configuration();
    cf.configure("hibernate-userTest.cfg.xml");
    SessionFactory sf=cf.buildSessionFactory();
    Session session=sf.openSession();
    Transaction t=session.beginTransaction();
    //session.save(s);
    Query q=session.createQuery("from Student"); 
    branch=q.list();
    t.commit();
       session.close();*/
    
    return branch;
    
    }
    
    
public int getTotalRecords(HttpServletRequest request,String col,String dir){
	Configuration cf=new Configuration();
    cf.configure("hibernate-userTest.cfg.xml");
    SessionFactory sf=cf.buildSessionFactory();
    Session session=sf.openSession();
    Transaction t=session.beginTransaction();
		//Session session = HibernateUtil.getSession("hibernate-userTest.cfg.xml");
		int cnt=0;
		try{
//		Query	 query = session.createSQLQuery("select count(*) as count from menu_user_master where deleted_status = 0 ").addScalar("count", Hibernate.INTEGER);
//		List<Integer> list = query.list();
//		 cnt = list.get(0);
			
			String sql="";
			//sql="select user_id,IFNULL(userloginname,'NA') userloginname , IFNULL(breakdown_system_sub_type,'NA') breakdown_system_sub_type ,IFNULL(notes,'NA') notes from breakdown_type_details  where deleted_status = 0";
			//sql="select user_id,IFNULL(userloginname,'NA')userloginname,emp_id from user_master where deleted_status = 0";
			sql="select * from student";
			System.out.println("sql--------"+sql);
		
			String search_term1 = request.getParameter("sSearch");
			//System.out.println("search_term-------"+search_term1);
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				System.out.println("search_term---------"+search_term);
				sql += " and ( menu_user_master.user_id like '%"+search_term+"%'";
				sql += " OR menu_user_master.userloginname like '%"+search_term+"%'";
				sql += " OR menu_user_master.emp_id like '%"+search_term+"%'";
				sql += " OR employee.EMPLOYEE_NAME like '%"+search_term+"%'";
				sql += " OR menu_user_master.user_type like '%"+search_term+"%'";
				sql += " OR employee.pf like '%"+search_term+"%'";
				sql += " OR employee.WORKING_DEPOT like '%"+search_term+"%'";
				sql += " OR menu_user_master.status like '%"+search_term+"%'";
				sql += " OR employee.WORKING_DEPT like '%"+search_term+"%')";
				
			}
			
			/*if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}*/
		
			/*int cntdetails=getTotalRecords();
			if(cntdetails>10)*/
			//{
			//sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			//}
		//System.out.println("sql----------"+sql);
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

public JSONObject getUserList(int total, HttpServletRequest request,String col,String dir){
	int totalAfterFilter = total;
	JSONObject result = new JSONObject();
	  Configuration cf=new Configuration();
	    cf.configure("hibernate-userTest.cfg.xml");
	    SessionFactory sf=cf.buildSessionFactory();
	    Session session=sf.openSession();
	    Transaction t=session.beginTransaction();
	    //session.save(s);
	  
	      
	    
	try{
		String sql="";
		
		sql=" select * from student";
		System.out.println("sql--------"+sql);
	
		String search_term1 = request.getParameter("sSearch");
		//System.out.println("search_term-------"+search_term1);
		if (search_term1 != null && !search_term1.equals("")) {
			String search_term = request.getParameter("sSearch").trim();
			System.out.println("search_term---------"+search_term);
			sql += " and ( menu_user_master.user_id like '%"+search_term+"%'";
			sql += " OR menu_user_master.userloginname like '%"+search_term+"%'";
			sql += " OR menu_user_master.emp_id like '%"+search_term+"%'";
			sql += " OR employee.EMPLOYEE_NAME like '%"+search_term+"%'";
			sql += " OR menu_user_master.user_type like '%"+search_term+"%'";
			sql += " OR employee.pf like '%"+search_term+"%'";
			sql += " OR employee.WORKING_DEPOT like '%"+search_term+"%'";
			sql += " OR menu_user_master.status like '%"+search_term+"%'";
			sql += " OR employee.WORKING_DEPT like '%"+search_term+"%')";
			
		}
		
		if(!col.equals("")){
			if(dir.equals("asc")){
			  sql += " order by "+col+" asc";
			}else{
				sql += " order by "+col+" desc";
			}
		}
	
		/*int cntdetails=getTotalRecords();
		if(cntdetails>10)*/
		//{
		sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
		//}
	//System.out.println("sql----------"+sql);
	Query query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	
	JSONArray array = new JSONArray();
	for(int i=0;i<aliasToValueMapList.size();i++){
		Map<String, Object> rs = aliasToValueMapList.get(i);
		JSONArray ja = new JSONArray();
		ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
				+ rs.get("user_id")
				+ "' value='"
				+ rs.get("user_id") + "'/>");
		ja.add(rs.get("user_id").toString());
		ja.add(rs.get("userloginname").toString());
		int empid=Integer.parseInt(rs.get("emp_id").toString());
		if(empid==0){
			ja.add("NA");	
		}else{
			ja.add(rs.get("emp_id").toString());
		}
		ja.add(Integer.parseInt(rs.get("id").toString()));
		ja.add(rs.get("name").toString());
		ja.add(rs.get("password").toString());
		ja.add(Integer.parseInt(rs.get("rollno").toString()));
		
		
		array.add(ja);
		
	}
	int cnt=0;
		totalAfterFilter = aliasToValueMapList.size();
		result.put("aaData", array);
		String search_term2= request.getParameter("sSearch");
		//if (search_term2 != null && !search_term2.equals("")) {
			String search_term3= request.getParameter("sSearch").trim();
		 cnt=getTotalRecords(request,col,dir);//getTotalRecordsForCount(search_term3);
		//}
		result.put("iTotalRecords",cnt);
		// result.put("iTotalRecords",totalAfterFilter);
		result.put("iTotalDisplayRecords", cnt);
	
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		 session.close();
		if (session != null) {
			//session.close();
		}
	}
	return result;
	
}

public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir) {
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	/*Query query = session.createSQLQuery(
			"select count(*) as count from device_type where deleted_status=0 ").addScalar(
			"count", Hibernate.INTEGER);
	List<Integer> list = query.list();
	int cnt = list.get(0);
	System.out.println(cnt);*/
	
	Criteria criteria = session.createCriteria(Device_Type.class);
	 criteria.add(Restrictions.eq("deleted_status", 0));
	if (!request.getParameter("sSearch").equals("")) {
		String search_term = request.getParameter("sSearch").trim();

		Junction conditionGroup = Restrictions.disjunction();

		conditionGroup.add(Restrictions.like("device_type_name", "%"
				+ search_term + "%"));
		conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
						criteria.add(conditionGroup);
		// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
		// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
	}
	if (!cols.equals("")) {
		if (dir.equals("asc")) {
			criteria.addOrder(Order.asc(cols));
		} else {
			criteria.addOrder(Order.desc(cols));
		}
	}
	
	List<Device_Type> list = criteria.list();
	return list.size();
}
@SuppressWarnings("unchecked")
public JSONObject getData(int total, HttpServletRequest request,String col,String dir) {
	JSONObject result = new JSONObject();
	try {
		int totalAfterFilter = total;

		// JSONArray array = new JSONArray();
		String searchSQL = "";
		String sql = " from Device_Type  ";

		// sql += " order by " + COL_NAME + " " + DIR;

		sql += " limit " + request.getParameter("iDisplayStart") + ", "
				+ request.getParameter("iDisplayLength");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(Device_Type.class);
		 criteria.add(Restrictions.eq("deleted_status", 0));
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("device_type_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status",  search_term,MatchMode.START));
							criteria.add(conditionGroup);
			// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
			// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
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
		List<Device_Type> list = criteria.list();
		JSONArray array = new JSONArray();
		System.out.println("List size----->" + list.size() + "\t"
				+ request.getParameter("iDisplayStart"));
		for (int i = 0; i < list.size(); i++) {
			JSONArray ja = new JSONArray();

			ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
					+ list.get(i).getDevice_type_id()
					+ "' value='"
					+ list.get(i).getDevice_type_id() + "'/>");
			ja.add(list.get(i).getDevice_type_id());
			ja.add(list.get(i).getDevice_type_name());
//			ja.add(list.get(i).getNote());

			ja.add(list.get(i).getNotes());
			ja.add(list.get(i).getStatus());
			
			array.add(ja);
			System.out.println("Array----->" + array);
		}
		
		totalAfterFilter = getTotalRecords(total,request,col,dir);
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
    
}
