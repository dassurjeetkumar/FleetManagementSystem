package com.trimax.its.transport.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.Group_Master;
import com.trimax.its.utility.model.Page_Master;

public class ShiftTypeDao {
	
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			Criteria criteria;
			
			String searchSQL = "";
			String sql = " from ShiftType ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(ShiftType.class);
			}else{
				 criteria = session.createCriteria(ShiftType.class);
				// criteria.add(Restrictions.in("status",Arrays.asList("ACTIVE","INACTIVE")));
				 criteria.add(Restrictions.eq("deletedStatus",0 ));
			}
			
			 if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("shiftTypeName", "%"
					+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"
						+ search_term + "%"));
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("shift_Type_code",srch ));
				}
			  criteria.add(conditionGroup);
				
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			List<ShiftType> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart")+"lp--"+list);
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable'  name='check' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()
						+ "' value='"
						+ list.get(i).getId() + "'/>");
				ja.add(list.get(i).getId());
				
				ja.add(list.get(i).getShiftTypeName());
				ja.add(list.get(i).getShift_Type_code());
				
				ja.add(list.get(i).getStatus());
				ja.add("Deleted Record");	
				/*if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
					System.out.println("deletedstatus--------"+deletedstatus);				
					if(deletedstatus==1)
					{
						
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}*/

				array.add(ja);
				//System.out.println("Array----->" + array);
			}

			/*totalAfterFilter = getTotalRecords();
			result.put("aaData", array);
			result.put("iTotalRecords", total);*/
			if (!request.getParameter("sSearch").equals("")) {
				totalAfterFilter = list.size();
				result.put("aaData", array);
				result.put("iTotalRecords", list.size());

				result.put("iTotalDisplayRecords", list.size());
			} else {
				totalAfterFilter = getTotalRecords(viewdeletedrecord);
				result.put("aaData", array);
				result.put("iTotalRecords", total);

				result.put("iTotalDisplayRecords", totalAfterFilter);
			}

			/*result.put("iTotalDisplayRecords", totalAfterFilter);
*/
			System.out.println("REsult ------>"
					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	public int getTotalRecords(String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		int cnt=0;
	      try{
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
			 query= session.createSQLQuery("select count(*) as count from  shift_type ").addScalar("count", Hibernate.INTEGER);
			}else{
			  query= session.createSQLQuery("select count(*) as count from  shift_type where deleted_status=0  ").addScalar("count", Hibernate.INTEGER);	
			}
	
		List<Integer> list = query.list();
		 cnt = list.get(0);
	      }catch(Exception e){
		e.printStackTrace();
		
	    }finally{
	    	System.out.println(cnt);
	    }
		
		return cnt;
	}
	public int insertShiftTypeDetails(ShiftType shiftType) {
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		/*shiftType.setCreatedDate(new Date());
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		shiftType.setCreatedBy(usrsessionid);
		shiftType.setDeletedStatus(0);*/
		int i = (Integer) session.save(shiftType);
		session.getTransaction().commit();
		session.close();
		return i;
		// TODO Auto-generated method stub
		
	}
	public ShiftType gettShiftTypeDetails(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		ShiftType shiftmaster = (ShiftType) session.get(
				ShiftType.class, new Integer(id));
		System.out.println("--------->>>" + shiftmaster.getShiftTypeName());
		return shiftmaster;
	}
	
	public void saveEDitedShiftTypeDetails(String requestType, int shiftid,
			ShiftType shiftType) {
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		  try
		   {
			  
		   session.beginTransaction(); 
		   //Page_Master page_Master= (BusStops) session.get(Page_Master.class, id);
		    System.out.println("hi in dao"+shiftType.getStatus());
		    HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			ShiftType shift_master=(ShiftType)session.get(ShiftType.class, shiftid);
		
			shift_master.setShiftTypeName(shiftType.getShiftTypeName());
			shift_master.setStatus(shiftType.getStatus());
			shift_master.setShift_Type_code(shiftType.getShift_Type_code());
			System.out.println("hi in dao"+shiftType.getShift_Type_code());
			//shift_master.getScheduletype().setSchedule_type_id(1);
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			/*shift_master.setUpdatedBy(usrsessionid);
			shift_master.setUpdatedDate(new Date());*/
			session.update(shift_master);
			session.getTransaction().commit();
			//session.close();
		}
		 catch(Exception e)
		  {
			session.getTransaction().rollback();

		 }
		  finally
		   {
		    if(session!=null)
		    {
		    	session.close();
		    }
		   }
			
		// TODO Auto-generated method stub
		

		
	}
	public String deleteShiftType(int shiftid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		ShiftType shifttype = (ShiftType) session.get(ShiftType.class,shiftid);
		
		//shifttype.setStatus("DELETED");
		/*shifttype.setDeletedStatus(1);
		HttpServletRequest request = ServletActionContext.getRequest();
		shifttype.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		shifttype.setUpdatedDate(new java.util.Date());*/
		
		session.update(shifttype);
		session.getTransaction().commit();
	}catch(Exception ex)
	{
		session.getTransaction().rollback();
		ex.printStackTrace();
	}finally{
		
            if(session!=null){
                session.close();
           
        } 
	}
		
		return null;
	}
	public int checkForDuplicateShiftType(String shiftTypename,String shiftTypeCode) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select count(*) from shift_type where shift_type_name='"+shiftTypename+"' and deleted_status=0 and shift_Type_code='"+shiftTypeCode+"'").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
	}
	public int checkForDuplicateShiftTypeName(String shiftTypename,int shifttypeid) {
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select count(*) from shift_type where shift_type_name='"+shiftTypename+"' and deleted_status=0 and shift_type_id not in ("+shifttypeid+")").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
	
	}
	public int checkForDuplicateShiftTypeCode(int shiftTypeCode,int shifttypeid) {
          Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select count(*) from shift_type where shift_Type_code="+shiftTypeCode+" and shift_type_id="+shifttypeid+"").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
	
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public int checkForShiftDelete(int shifttypeid)
	{
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			int count = Integer.parseInt(session.createSQLQuery("select count(*) from schedule_details where shift_type_id="+shifttypeid+"").uniqueResult().toString());
			System.out.println(count);
			// TODO Auto-generated method stub
			return count;
	}
	public int checkForDuplicateShiftTypeStatus(String status, int shiftid) {
		
		  Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			int count = Integer.parseInt(session.createSQLQuery("select count(*) from shift_type where status='"+status+"' and shift_type_id="+shiftid+"").uniqueResult().toString());
			System.out.println(count);
			// TODO Auto-generated method stub
			return count;
				
	}
	public int getMaxShiftID() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select max(shift_type_id) from shift_type").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
		}
	}

