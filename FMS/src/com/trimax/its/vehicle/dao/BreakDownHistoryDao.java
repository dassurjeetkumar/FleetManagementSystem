package com.trimax.its.vehicle.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.trimax.doa.ticketing.model.DenominationType;
import com.trimax.doa.ticketing.model.TicketBagMaster;
import com.trimax.its.common.Common;
import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.BreakDownHistory;
import com.trimax.its.vehicle.model.BreakDownTypeDetails;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.Vehicle;

public class BreakDownHistoryDao {
	public static void main(String args[]) {
		try {
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

			// Get the date today using Calendar object.
			Date today = new Date();
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			String reportDate = df.format(today);

			// Print what date is today!
			//System.out.println("\n \t Report Date: " + reportDate);
			// return reportDate;

		} catch (Exception e) {
			// TODO: handle exception
			// return "";
		}
	}

	public int getTotalViewBreakDownHistoryRecords(String viewdeletedrecord) {
		Session session = null;
		Criteria criteria ;
		int cnt = 0;
		try {
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(BreakDownHistory.class);
			}else{
				 criteria = session.createCriteria(BreakDownHistory.class);
				 criteria.add(Restrictions.eq("deleted_status", 0));
				 criteria.add(Restrictions.eq("status", "ACTIVE"));
			}
			
			List<TicketBagMaster> list = criteria.list();
			cnt = list.size();
			//System.out.println(cnt);
			return cnt;

		} catch (Exception e) {
			//System.out
			//		.println("\n \t Inside WaybillDAO Class with Exception in getTotalRecordsOfWayBill Method as : "
			//				+ e.getMessage());
			return cnt;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	public JSONObject viewBreakDownHistoryData(int total,HttpServletRequest request, String cols, String dir,String viewdeletedrecord) {
	//	System.out.println("\n \t IN JSONObject Method.........");

		JSONObject result = new JSONObject();
		Session session;

		Criteria criteria;
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			// criteria.addOrder(Order.asc("waybil_Id"));
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(BreakDownHistory.class);
				//criteria.add(Restrictions.eq("status", "ACTIVE"));
				criteria.createAlias("breakDownTypeDetails", "breakdowntype");
				criteria.createAlias("vehicle", "veh");
			}else{
				criteria = session.createCriteria(BreakDownHistory.class);
				criteria.add(Restrictions.eq("deleted_status", 0));
				criteria.add(Restrictions.eq("status", "ACTIVE"));
				criteria.createAlias("breakDownTypeDetails", "breakdowntype");
				criteria.createAlias("vehicle", "veh");
			}
			int srch = 0;
			//System.out.println("\n \t JSONObject getData 1111..............");
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				if (isInteger(search_term)) {
					srch = Integer.parseInt(search_term);
					conditionGroup.add(Restrictions.eq("breakdownId", srch));
				}
				/*
				 * if(isInteger(search_term)) {
				 * srch=Integer.parseInt(search_term);
				 * conditionGroup.add(Restrictions.eq("vehicleId",srch )); }
				 */

				conditionGroup.add(Restrictions.like("date", "%" + search_term
						+ "%"));

				conditionGroup.add(Restrictions
						.like("breakdowntype.breakdown_name", "%" + search_term
								+ "%"));
				conditionGroup.add(Restrictions.like(
						"veh.vehicleRegistrationNumber", "%" + search_term
								+ "%"));

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
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));

			List<BreakDownHistory> list = criteria.list();
			//System.out.println("\n \t JSONObject getData 22222..............");
			// TODO Auto-generated method stub

			JSONArray array = new JSONArray();
			//System.out.println("List size----->" + list.size() + "\t"
			//		+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getBreakdownId()
						+ "' value='"
						+ list.get(i).getBreakdownId() + "'/>");

				// ja.add((i+1));

				/*
				 * String date = list.get(i).getDate() != null ? list
				 * .get(i).getDate().toString() : ""; ja.add(date.replace(".0",
				 * ""));
				 */

				/*
				 * String orgName = list.get(i).getOrgname() != null ? list
				 * .get(i).getOrgname().toString() : "";
				 */Common cm = new Common();
				ja.add(list.get(i).getBreakdownId());
				ja.add(cm.getDateToDate4(list.get(i).getDate()));
				ja.add(list.get(i).getVehicle().getVehicleRegistrationNumber());

				ja.add(list.get(i).getBreakDownTypeDetails()
						.getBreakdown_name());

				// ja.add(list.get(i).getConductor_Id().getUser_id());
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getBreakdownId()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getBreakdownId()+"'/>Record in Database1");	
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
				totalAfterFilter = getTotalViewBreakDownHistoryRecords(viewdeletedrecord);
				result.put("aaData", array);
				result.put("iTotalRecords", total);

				result.put("iTotalDisplayRecords", totalAfterFilter);
			}

			/*
			 * if (!request.getParameter("sSearch").equals("")) {
			 * totalAfterFilter = list.size(); result.put("aaData", array);
			 * result.put("iTotalRecords", list.size());
			 * 
			 * result.put("iTotalDisplayRecords", list.size()); }else{
			 * 
			 * 
			 * //totalAfterFilter = getTotalRecordsOfWayBill();
			 * result.put("aaData", array); result.put("iTotalRecords", total);
			 * 
			 * result.put("iTotalDisplayRecords", totalAfterFilter); //}
			 *////System.out.println("REsult ------>"
			//		+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	public String convertDateToSring(Date date) {

		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			// Get the date today using Calendar object.
			Date today = date;
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			String reportDate = df.format(today);

			// Print what date is today!
			//System.out.println("\n \t Report Date: " + reportDate);
			return reportDate;

		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public static String getDateFromDateTimeFmt2(String pickerDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String formattedDate = "";
		try {
			date = simpleDateFormat.parse(pickerDate);
			formattedDate = simpleDateFormat1.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			return formattedDate;
		}
	}

	public int checkForDuplicateBreakDownHistoryInsert(
			BreakDownHistory breakdownhistory) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String reportDate = null;
		int count = 0;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// Get the date today using Calendar object.
			// Date today = breakdownhistory.getDate();
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			// reportDate = df.format(today);

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			count = Integer.parseInt(session
					.createSQLQuery(
							"select count(*) from breakdown_history "
									+ "where vehicle_id='"
									+ breakdownhistory.getVehicle()
											.getVehicleId()
									+ "' "
									+ "and breakdown_type_id='"
									+ breakdownhistory
											.getBreakDownTypeDetails()
											.getBreakdown_type_Id()
									+ "' and date='"
									+ getDateFromDateTimeFmt2(breakdownhistory
											.getDate()) + "'").uniqueResult()
					.toString());

		//	System.out.println(count);

		//	System.out.println("\n \t Report Date: " + reportDate);

		} catch (Exception e) {
			// TODO: handle exception

		} finally {
			if (session != null) {
				session.close();
			}
		}
		// TODO Auto-generated method stub
		return count;
	}

	public int insertBreakDownHistoryDetails(BreakDownHistory breakdownhistory) {
		Session session = null;
		int i = 0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			session.beginTransaction();
			breakdownhistory.setCreated_date(new Date());
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			breakdownhistory.setDate(getDateFromDateTimeFmt2(breakdownhistory
					.getDate()));
			breakdownhistory.setCreated_by(usrsessionid);
			breakdownhistory.setStatus("ACTIVE");
			i = (Integer) session.save(breakdownhistory);
			//System.out.println("Inserted Breakdown History id is...." + i);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return i;

	}

	public BreakDownHistory getBreakDownHistoryDetails(int breakdownid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		BreakDownHistory breakdownhist = (BreakDownHistory) session.get(
				BreakDownHistory.class, new Integer(breakdownid));
		// System.out.println("--------->>>" + breakdownhist.getVehicleId());
		return breakdownhist;
	}

	public void saveEditedBreakDownHistoryDetails(String requestType,int breakdownid, BreakDownHistory breakdownhistoryold) {
		Session session = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			// Page_Master page_Master= (BusStops)
			// session.get(Page_Master.class, id);
			// System.out.println("hi EDit of  dao"+breakdownhistoryold.getDate()+"formatted date is..."+new
			// Common().getDateTimeToDatePicker(breakdownhistoryold.getDate()));
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			BreakDownHistory breakdownhistorynew = (BreakDownHistory) session.get(BreakDownHistory.class, breakdownid);

			breakdownhistorynew.setDate(common.getDateFromPicker(breakdownhistoryold.getDate()));
			// breakdownhistorynew.setVehicleId(breakdownhistoryold.getVehicleId());

			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleId(breakdownhistoryold.getVehicle().getVehicleId());
			breakdownhistorynew.setVehicle(vehicle);
			BreakDownTypeDetails breakdowntype = new BreakDownTypeDetails();
			breakdowntype.setBreakdown_type_Id(String.valueOf(breakdownhistoryold.getBreakDownTypeDetails().getBreakdown_type_Id()));
			breakdownhistorynew.setBreakDownTypeDetails(breakdowntype);

			// shift_master.getScheduletype().setSchedule_type_id(1);
			int usrsessionid = (Integer) request.getSession().getAttribute("userid");
			breakdownhistorynew.setUpdated_by(usrsessionid);
			breakdownhistorynew.setUpdated_date(new Date());
			session.update(breakdownhistorynew);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void deleteBreakDownHistoryType(int breakdownid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		// System.out.println("=====Route id" + role.getRole_id());
		try {
			//
			BreakDownHistory breakdownhistory = (BreakDownHistory) session.get(BreakDownHistory.class, breakdownid);

			breakdownhistory.setStatus("INACTIVE");
			breakdownhistory.setDeleted_status(1);
			HttpServletRequest request = ServletActionContext.getRequest();
			breakdownhistory.setUpdated_by(Integer.parseInt(request
					.getSession().getAttribute("userid").toString()));
			breakdownhistory.setUpdated_date(new java.util.Date());

			session.update(breakdownhistory);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {

			if (session != null) {
				session.close();

			}
		} // TODO Auto-generated method stub

	}

	public List<Vehicle> getVehicleDropList(String name) {
	//	System.out.println("***Inside getVehicleDropList***");
		List<Vehicle> list = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();

		try {

			query = session
					.createQuery("from Vehicle where status in ('ACTIVE')  and (vehicleRegistrationNumber like '"
							+ name + "%')");

			list = (List<Vehicle>) query.list();
		} catch (Exception e) {

		} finally {
			if (session != null) {
				session.close();
			}
		}
		// session.close();
		return list;
	}

	public List<BreakDownTypeDetails> getBreakDownTypeDropList(String name) {
		//System.out.println("***Inside getVehicleDropList***");
		List<BreakDownTypeDetails> list = null;
		Session sessionbreakdown = HibernateUtil
				.getSession("hibernate.cfg.xml");
		Query query;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();

		try {

			query = sessionbreakdown
					.createQuery("from BreakDownTypeDetails where deleted_status=0  and (breakdown_name like '"
							+ name + "%')");

			list = (List<BreakDownTypeDetails>) query.list();
		} catch (Exception e) {

		} finally {
			if (sessionbreakdown != null) {
				sessionbreakdown.close();
			}
		}
		// session.close();
		return list;
	}

}