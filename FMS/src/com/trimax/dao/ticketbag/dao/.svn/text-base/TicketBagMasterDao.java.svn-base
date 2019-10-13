package com.trimax.dao.ticketbag.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.doa.ticketing.model.TicketBagMaster;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.transport.model.ShiftType;


public class TicketBagMasterDao {
    public static void main(String args[])
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	System.out.println(dateFormat.format(date)); 
    }
	public int getTotalBagRecords() {
		Session session = null;
		int cnt=0;
		try{
//			session = HibernateUtil.getSession("hibernate.cfg.xml");
//			Query query = session.createSQLQuery("SELECT count(*) as count FROM ticketbag_master WHERE status='ACTIVE';")
//					.addScalar("count", Hibernate.INTEGER);
//			List<Integer> list = query.list();
//			 cnt = list.get(0);
//			System.out.println(cnt);
//			return cnt;
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			System.out.println("orgchartid.................."+orgchartid);
			System.out.println("orgtTypeId.................."+orgtTypeId);
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			//Criteria criteria = session.createCriteria(TicketBagMaster.class);
			//criteria.createAlias("orgchart", "org");
			//criteria.add(Restrictions.in("status",Arrays.asList("ACTIVE","INACTIVE")));
			//criteria.add(Restrictions.eq("status","ACTIVE"));
			//criteria.add(Restrictions.eq("deleted_status",0 ));
			//criteria.addOrder(Order.asc("ticketbag_id"));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(TicketBagMaster.class);
				criteria.createAlias("orgchart", "org");
				if(orgtTypeId!=0 && orgtTypeId==1){
					System.out.println("this is acess of centrol");
					
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					System.out.println("this is acess of Division");
					criteria.add(Restrictions.eq("orgtype.org_type_id",orgtTypeId));
					
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					System.out.println("this is acess of Depot");
					criteria.add(Restrictions.eq("orgchart.org_chart_id",orgchartid ));
					
				}

			}else{
				criteria = session.createCriteria(TicketBagMaster.class);
				criteria.createAlias("orgchart", "org");
				if(orgtTypeId!=0 && orgtTypeId==1){
					System.out.println("this is acess of centrol");
					
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					System.out.println("this is acess of Division");
					criteria.add(Restrictions.eq("orgtype.org_type_id",orgtTypeId));
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					System.out.println("this is acess of Depot");
					criteria.add(Restrictions.eq("orgchart.org_chart_id",orgchartid ));
				}
				criteria.add(Restrictions.eq("deleted_status",0 ));
				 
			}
			criteria.add(Restrictions.in("status",Arrays.asList("ACTIVE","INACTIVE")));
			System.out.println("\n \t JSONObject getData 1111..............");
			int srch=0;
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				if(isInteger(search_term))
				{ 
				srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("ticketbag_id",srch )); 
				}
				conditionGroup.add(Restrictions.like("bagcode", "%"
						+ search_term + "%"));
				
				conditionGroup.add(Restrictions.like("org.org_name", "%"
					+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("notes", "%"
						+ search_term + "%"));
				
				
				criteria.add(conditionGroup);
				
			}
          List<TicketBagMaster> list = criteria.list();
          cnt = list.size();
			System.out.println(cnt);
			return cnt;
			
			
		}catch (Exception e) {
			System.out.println("\n \t Inside WaybillDAO Class with Exception in getTotalRecordsOfWayBill Method as : "+e.getMessage());
			return cnt;
		}finally{
			if(session!=null){
				session.close();
			}
		}
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
	public JSONObject getBagData(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
                System.out.println("\n \t IN JSONObject Method.........");
		
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			System.out.println("orgchartid.................."+orgchartid);
			System.out.println("orgtTypeId.................."+orgtTypeId);
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			//Criteria criteria = session.createCriteria(TicketBagMaster.class);
			//criteria.createAlias("orgchart", "org");
			//criteria.add(Restrictions.in("status",Arrays.asList("ACTIVE","INACTIVE")));
			//criteria.add(Restrictions.eq("status","ACTIVE"));
			//criteria.add(Restrictions.eq("deleted_status",0 ));
			//criteria.addOrder(Order.asc("ticketbag_id"));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(TicketBagMaster.class);
				criteria.createAlias("orgchart", "org");
				if(orgtTypeId!=0 && orgtTypeId==1){
					System.out.println("this is acess of centrol");
					
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					System.out.println("this is acess of Division");
					criteria.add(Restrictions.eq("orgtype.org_type_id",orgtTypeId));
					
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					System.out.println("this is acess of Depot");
					criteria.add(Restrictions.eq("orgchart.org_chart_id",orgchartid ));
					
				}

			}else{
				criteria = session.createCriteria(TicketBagMaster.class);
				criteria.createAlias("orgchart", "org");
				if(orgtTypeId!=0 && orgtTypeId==1){
					System.out.println("this is acess of centrol");
					
				}else if(orgtTypeId!=0 && orgtTypeId==2){
					System.out.println("this is acess of Division");
					criteria.add(Restrictions.eq("orgtype.org_type_id",orgtTypeId));
				}
				else if(orgtTypeId!=0 && orgtTypeId==3){
					System.out.println("this is acess of Depot");
					criteria.add(Restrictions.eq("orgchart.org_chart_id",orgchartid ));
				}
				criteria.add(Restrictions.eq("deleted_status",0 ));
				 
			}
			criteria.add(Restrictions.in("status",Arrays.asList("ACTIVE","INACTIVE")));
			System.out.println("\n \t JSONObject getData 1111..............");
			int srch=0;
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				if(isInteger(search_term))
				{ 
				srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("ticketbag_id",srch )); 
				}
				conditionGroup.add(Restrictions.like("bagcode", "%"
						+ search_term + "%"));
				
				conditionGroup.add(Restrictions.like("org.org_name", "%"
					+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("notes", "%"
						+ search_term + "%"));
				
				
				criteria.add(conditionGroup);
				
			}
            System.out.println("SORTING DIRECTION IS..."+dir+"column is..."+cols);
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
			
			List<TicketBagMaster> list = criteria.list();
			System.out.println("\n \t JSONObject getData 22222..............");

			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				
				JSONArray ja = new JSONArray();
				
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getTicketbag_id()
						+ "' value='"
						+ list.get(i).getTicketbag_id()+ "'/>");
				
				ja.add(list.get(i).getTicketbag_id());
				ja.add(list.get(i).getBagcode());
				Common cm=new Common();
				
				/* String orgName = list.get(i).getOrgname() != null ?
				 list .get(i).getOrgname().toString() : "";
*/				 ja.add(list.get(i).getOrgchart().getOrg_name());
				
                String effstartdateDate =list.get(i).getEffectivestartdate()!= null ? list.get(i).getEffectivestartdate().toString() : "";
                effstartdateDate=effstartdateDate.replace(".0", "");
                String startdate=cm.getDateFromDateTime_(effstartdateDate);
                ja.add(startdate);
                String effenddateDate =list.get(i).getEffectiveenddate()!= null ? list.get(i).getEffectiveenddate().toString() : "";
                effenddateDate=effenddateDate.replace(".0", "");
                if(!effenddateDate.equalsIgnoreCase(""))
                {
                String enddate=cm.getDateFromDateTime_(effenddateDate);
                ja.add(enddate);
                }
                else
                {
                	ja.add("");
                }
				/*ja.add(convertDateToSring(list.get(i).getEffectivestartdate())); 
				ja.add(convertDateToSring(list.get(i).getEffectiveenddate()));*/
//				ja.add(list.get(i).getConductor_Id().getUser_id()); 
				
				ja.add(list.get(i).getStatus()); 
				ja.add(list.get(i).getNotes()); 
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getTicketbag_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getTicketbag_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				
				array.add(ja);
			}
			if (!request.getParameter("sSearch").equals("")) {
				totalAfterFilter = list.size();
				result.put("aaData", array);
				result.put("iTotalRecords", list.size());

				result.put("iTotalDisplayRecords", list.size());
			} else {
				totalAfterFilter = getTotalBagRecords();
				result.put("aaData", array);
				result.put("iTotalRecords", total);

				result.put("iTotalDisplayRecords", totalAfterFilter);
			}

			
		System.out.println("REsult ------>" + request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
		return result;

	}

	public String convertDateToSring(Date date){
		
		try{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			// Get the date today using Calendar object.
			Date today = date;        
			// Using DateFormat format method we can create a string 
			// representation of a date with the defined format.
			String reportDate = df.format(today);

			// Print what date is today!
			System.out.println("\n \t Report Date: " + reportDate);
			return reportDate;
			
		}catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
public int getMaxTicketBagID() {
	Session session =null;
	int count =0;
	  try
	   {
		  session=HibernateUtil.getSession("hibernate.cfg.xml");
	
	
	     count = Integer.parseInt(session.createSQLQuery("select max(ticketbag_id) from  ticketbag_master").uniqueResult().toString());
	     System.out.println(count);
	   }
		 catch(Exception e)
		  {
			//session.getTransaction().rollback();

		 }
		  finally
		   {
		    if(session!=null)
		    {
		    	session.close();
		    }
		   }

	// TODO Auto-generated method stub
	return count;

}
public int insertTicketBagDetails(TicketBagMaster ticketbagmaster) {
	Session session =null;
	int i =0;
	  try
	   {
		  session=HibernateUtil.getSession("hibernate.cfg.xml");
	
	session.beginTransaction();
	ticketbagmaster.setCreated_date(new Date());
	ticketbagmaster.setStatus("ACTIVE");
	ticketbagmaster.setDeleted_status(0);
	ticketbagmaster.setEffectivestartdate(getDateFromDateTime_(ticketbagmaster.getEffectivestartdate()));
	if (!ticketbagmaster.getEffectiveenddate().trim().isEmpty()){
		ticketbagmaster.setEffectiveenddate(getDateFromDateTime_(ticketbagmaster.getEffectiveenddate()));
	}else{
		ticketbagmaster.setEffectiveenddate(null);
	}
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpSession sess=request.getSession();
	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	ticketbagmaster.setCreated_by(usrsessionid);
    i = (Integer) session.save(ticketbagmaster);
	session.getTransaction().commit();
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

	
	return i;
}
@SuppressWarnings("finally")
public String getDateFromDateTime_(String pickerDate)
{
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
   Date date = new Date();
   String formattedDate="";
   try
   {
   	date = simpleDateFormat1.parse(pickerDate);
   	formattedDate=simpleDateFormat.format(date);
   }catch(Exception ex)
   {
   	ex.printStackTrace();
   	
   }
   finally{
   	return formattedDate;
   }
}
public TicketBagMaster getTicketBagDetails(int id) {
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	TicketBagMaster ticketbagmaster = (TicketBagMaster) session.get(
			TicketBagMaster.class, new Integer(id));
	System.out.println("--------->>>" + ticketbagmaster.getOrgchart().getOrg_chart_id());
	return ticketbagmaster;
}
public void saveEditedTicketBagDetails(String requestType, int id,
		TicketBagMaster ticketbagmaster) {
	 Session session =null;
	  try
	   {
		  session=HibernateUtil.getSession("hibernate.cfg.xml");
	   session.beginTransaction(); 
	   //Page_Master page_Master= (BusStops) session.get(Page_Master.class, id);
	    System.out.println("hi in dao"+ticketbagmaster.getStatus());
	    HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		TicketBagMaster ticketbag_master=(TicketBagMaster)session.get(TicketBagMaster.class, id);
	
		ticketbag_master.setBagcode(ticketbagmaster.getBagcode());
		ticketbag_master.setStatus(ticketbagmaster.getStatus());
		OrganisationChart orgch=new OrganisationChart();
		orgch.setOrg_chart_id(ticketbagmaster.getOrgchart().getOrg_chart_id());
		ticketbag_master.setOrgchart(orgch);
		OrgType orgtype=new OrgType();
		 Common cm=new Common();

		orgtype.setOrg_type_id(ticketbagmaster.getOrgtype().getOrg_type_id());
		ticketbag_master.setOrgtype(orgtype);
		//ticketbag_master.setOrgcharttypeid(ticketbagmaster.getOrgcharttypeid());
		//System.out.println("hi in dao"+ticketbagmaster.getOrgchartid());
		ticketbag_master.setEffectivestartdate(getDateFromDateTime_(ticketbagmaster.getEffectivestartdate()));
		ticketbag_master.setStatus(ticketbagmaster.getStatus());
		ticketbag_master.setNotes(ticketbagmaster.getNotes());
		ticketbag_master.setEffectiveenddate(getDateFromDateTime_(ticketbagmaster.getEffectiveenddate()));
		//shift_master.getScheduletype().setSchedule_type_id(1);
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		ticketbag_master.setUpdated_by(usrsessionid);
		ticketbag_master.setUpdated_date(new Date());
		session.update(ticketbag_master);
		session.getTransaction().commit();
		
	}
	 catch(Exception e) 
	  {
		 e.printStackTrace();
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
public void deleteTicketBag(int id) {
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
//System.out.println("=====Route id" + role.getRole_id());
try{
	//
	TicketBagMaster ticketbag_master=(TicketBagMaster)session.get(TicketBagMaster.class, id);
	
	//ticketbag_master.setStatus("DELETED");
	ticketbag_master.setDeleted_status(1);
	HttpServletRequest request = ServletActionContext.getRequest();
	ticketbag_master.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
	ticketbag_master.setUpdated_date(new java.util.Date());
	
	session.update(ticketbag_master);
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
}
public int checkForDuplicateTicketBagCode(String ticketbagcodeCode, int id) {
	    Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		int count = Integer.parseInt(session.createSQLQuery("select count(*) from ticketbag_master where bag_code='"+ticketbagcodeCode+"' and ticketbag_id not in ("+id+") and status='ACTIVE' and deleted_status=0").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count; 
	
}
public int checkForDuplicateTicketBagInsert(TicketBagMaster ticketbagmaster) {
	 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 String effstartdt=getDateFromDateTime_(ticketbagmaster.getEffectivestartdate());
	 
	 //String effenddt=dateFormat.format(ticketbagmaster.getEffectiveenddate());
		int count = Integer.parseInt(session.createSQLQuery("select count(*) from ticketbag_master where org_type_id='"+ticketbagmaster.getOrgtype().getOrg_type_id()+"' " +
				"and org_chart_id='"+ticketbagmaster.getOrgchart().getOrg_chart_id()+"' and bag_code='"+ticketbagmaster.getBagcode()+"' " +
						" and deleted_status=0").uniqueResult().toString());
		System.out.println(count);
		// TODO Auto-generated method stub
		return count;
	
}	
	}