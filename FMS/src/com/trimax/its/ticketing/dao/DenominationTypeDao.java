package com.trimax.its.ticketing.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.doa.ticketing.model.DenominationType;
import com.trimax.doa.ticketing.model.TicketBagMaster;
import com.trimax.doa.ticketing.model.TicketTypeManual;
import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;
import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;


public class DenominationTypeDao {

	public int getTotalDenominationTypeRecords(int total, HttpServletRequest request,String cols, String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria ;	
		
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			criteria = session.createCriteria(DenominationType.class);
			criteria.createCriteria("ticketTypeManual", "tickettype");
		}else{
			criteria = session.createCriteria(DenominationType.class);
		     criteria.add(Restrictions.eq("deletedstatus", 0));
		     criteria.createCriteria("ticketTypeManual", "tickettype");
		}
		
		/*if (!request.getParameter("sSearch").equals("")) {
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
		}*/
		
		
		List<DenominationType> list = criteria.list();
		return list.size();
		
		/*Session session = null;
		int cnt=0;
		Criteria criteria;
		try{
            session = HibernateUtil.getSession("hibernate.cfg.xml");	
			
						
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(DenominationType.class);
				//criteria.add(Restrictions.eq("status","ACTIVE"));
				//criteria.createAlias("ticketTypeManual", "tickettype");
			}else{
				criteria = session.createCriteria(DenominationType.class);
				criteria.add(Restrictions.eq("status","ACTIVE"));
				criteria.createAlias("ticketTypeManual", "tickettype");
			    criteria.add(Restrictions.eq("deleted_status", 0));
			}
			
			List<Integer> list = criteria.list();
			cnt = list.size();
			System.out.println("skgflskfgskdfskgo"+cnt);
			return cnt;
			
		}catch (Exception e) {
			System.out.println("\n \t Inside TicketBagDao Class with Exception in getTotalRecordsOfTicketBAG Method as : "+e.getMessage());
			return cnt;
		}finally{
			if(session!=null){
				session.close();
			}
		}*/
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

	@SuppressWarnings("unchecked")
	public JSONObject getDenominationTypeData(int total,HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria ;
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(DenominationType.class);
				//criteria.add(Restrictions.eq("status","ACTIVE"));
				//criteria.addOrder(Order.asc("denomination_type_id"));
				criteria.createAlias("ticketTypeManual", "tickettype");
			}else{
				criteria = session.createCriteria(DenominationType.class);
				//criteria.addOrder(Order.asc("denomination_type_id"));
				criteria.add(Restrictions.eq("deletedstatus",0));
				criteria.add(Restrictions.eq("status","ACTIVE"));
				//criteria.add(Restrictions.in("status",Arrays.asList("ACTIVE","INACTIVE")));
				//criteria.addOrder(Order.asc("waybil_Id"));
				criteria.createAlias("ticketTypeManual", "tickettype");
			}
			
			int srch=0;
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				
				if(isInteger(search_term))
				{ 
				srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("denomination_type_id",srch));
				 
			    }
				
				conditionGroup.add(Restrictions.eq("denomtype",search_term )); 
				
				
				if(isInteger(search_term))
				{
			    srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("tickettype.ticketTypeId", srch));
				}
				conditionGroup.add(Restrictions.like("status", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("notes", "%"+ search_term + "%"));				
				
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
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<DenominationType> list = criteria.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				
				JSONArray ja = new JSONArray();
				
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getDenomination_type_id()
						+ "' value='"
						+ list.get(i).getDenomination_type_id()+ "'/>");
				ja.add(list.get(i).getDenomination_type_id());
				//ja.add(list.get(i).getDenomination_series());
				ja.add(list.get(i).getDenomtype());
				
				/* String orgName = list.get(i).getOrgname() != null ?
				 list .get(i).getOrgname().toString() : "";
*/				 ja.add(list.get(i).getTicketTypeManual().getTicketTypeName());
				
								
				ja.add(list.get(i).getStatus()); 
				ja.add(list.get(i).getPercentage());
				ja.add(list.get(i).getAmount());
				ja.add(list.get(i).getServicetax());
				ja.add(list.get(i).getNotes()); 
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedstatus();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getDenomination_type_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getDenomination_type_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				
				array.add(ja);
			}
			/*if (!request.getParameter("sSearch").equals("")) {
				totalAfterFilter = list.size();
				result.put("aaData", array);
				result.put("iTotalRecords", list.size());

				result.put("iTotalDisplayRecords", list.size());
			} else {*/
				totalAfterFilter = getTotalDenominationTypeRecords(total, request, col, dir,viewdeletedrecord);
				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);

				result.put("iTotalDisplayRecords", totalAfterFilter);
			//}

			/*if (!request.getParameter("sSearch").equals("")) {
				totalAfterFilter = list.size();	
				result.put("aaData", array);
				result.put("iTotalRecords", list.size());

				result.put("iTotalDisplayRecords", list.size());
			}else{
				
			
			//totalAfterFilter = getTotalRecordsOfWayBill();
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);
			//}
*/			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	public List<String> getTicketTypeId() {
		Session session = null;
		int count = 0;
		List list = null;
		try {
			list = new ArrayList();
		
			String qry = "SELECT distinct ticket_type_manual_id,ticket_type_manual_name FROM ticket_type_manual";
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("ticket_type_manual_id").toString());
				}
			}
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;

	}

	public List<String> getTicketTypeName() {
		Session session = null;
		int count = 0;
		List list = null;
		try {
			list = new ArrayList();
			
			String qry = "SELECT distinct ticket_type_manual_id,ticket_type_manual_name FROM ticket_type_manual";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("ticket_type_manual_name").toString());
				}
			}
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;

	}

	public int insertDenominationTypeDetails(DenominationType denominationtype) {
		Session session =null;
		int i =0;
		  try
		   {
			  session=HibernateUtil.getSession("hibernate.cfg.xml");
		
		session.beginTransaction();
		denominationtype.setCreateddate(new Date());
		denominationtype.setStatus("ACTIVE");
		denominationtype.setDeletedstatus(0);
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		denominationtype.setCreatedby(usrsessionid);
	    i = (Integer) session.save(denominationtype);
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

	public DenominationType getDenomTypeDetails(int denomtypeid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		DenominationType denomtype = (DenominationType) session.get(
				DenominationType.class, new Integer(denomtypeid));
		return denomtype;
	}

	public void saveEditedDenomTypeDetails(String requestType,
			int denomination_type_id, DenominationType denominationtypeold) {
		   Session session =null;
		  try
		   {
		    session=HibernateUtil.getSession("hibernate.cfg.xml");
		    session.beginTransaction(); 
		   //Page_Master page_Master= (BusStops) session.get(Page_Master.class, id);
		 //   System.out.println("In Edit Denomination type dao..........."+denominationtypeold.getTicketTypeManual().getTicketTypeId()+"DENOMINATION TYPE ID....."+denomination_type_id);
		    HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			DenominationType denominationtypenew=(DenominationType)session.get(DenominationType.class, denomination_type_id);
		
			//denominationtypenew.setDenomtype(denominationtypeold.getDenomtype());
		//	TicketTypeManual tickettype=new TicketTypeManual();
			//tickettype.setTicketTypeId(denominationtypeold.getTicketTypeManual().getTicketTypeId());
			
			//denominationtypenew.setTicketTypeManual(tickettype);
			denominationtypenew.setAmount(denominationtypeold.getAmount());
			denominationtypenew.setPercentage(denominationtypeold.getPercentage());
			denominationtypenew.setNotes(denominationtypeold.getNotes());
			denominationtypenew.setStatus(denominationtypeold.getStatus());
			denominationtypenew.setServicetax(denominationtypeold.getServicetax());
		
			//shift_master.getScheduletype().setSchedule_type_id(1);
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			denominationtypenew.setUpdatedby(usrsessionid);
			denominationtypenew.setUpdateddate(new Date());
			session.update(denominationtypenew);
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

		
	}

	public void deleteDenominationType(int denomid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		DenominationType denominationtype=(DenominationType)session.get(DenominationType.class, denomid);
		
		//denominationtype.setStatus("INACTIVE");
		denominationtype.setDeletedstatus(1);
		HttpServletRequest request = ServletActionContext.getRequest();
		denominationtype.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		denominationtype.setUpdateddate(new java.util.Date());
		
		session.update(denominationtype);
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
	public int checkForDuplicateDenomTypeUpdate(
			DenominationType denominationtype) {
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			int count = Integer.parseInt(session.createSQLQuery("select count(*) from denomination_type_manual where denomination_type_manual='"+denominationtype.getDenomtype()+"' and status='ACTIVE' and deleted_status=0 and ticket_type_manual_id='"+denominationtype.getTicketTypeManual().getTicketTypeId()+"' and denomination_type_manual_id not in ("+denominationtype.getDenomination_type_id()+")").uniqueResult().toString());
			// TODO Auto-generated method stub
			return count;
		
	}
	public int checkForDuplicateDenomTypeInsert(
			DenominationType denominationtype) { 
		// TODO Auto-generated method stub
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			int count = Integer.parseInt(session.createSQLQuery("select count(*) from denomination_type_manual where denomination_type_manual='"+denominationtype.getDenomtype()+"' and status='ACTIVE' and deleted_status=0 and " +
					"ticket_type_manual_id='"+denominationtype.getTicketTypeManual().getTicketTypeId()+"'").uniqueResult().toString());
			// TODO Auto-generated method stub
			return count;

	}

		
	}

