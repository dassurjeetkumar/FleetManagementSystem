package com.trimax.its.transport.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.fare.model.RateMasterDetail;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.model.User;
import com.trimax.its.transport.model.PeakHour;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.Complaint;
import com.trimax.its.vehicle.model.ServiceType;

public class PeakHourDao extends ActionSupport {

	public int getTotalRecords(int total, HttpServletRequest request,
			String cols, String dir, String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*
		 * Query query = session.createSQLQuery(
		 * "select count(*) as count from Complaint where deleted_status=0 "
		 * ).addScalar( "count", Hibernate.INTEGER); List<Integer> list =
		 * query.list(); int cnt = list.get(0); //System.out.println(cnt);
		 */
		// System.out.println("getTotalRecords++++++++++");

		Criteria criteria;
		if (viewdeletedrecord.equalsIgnoreCase("Y")) {
			criteria = session.createCriteria(PeakHour.class);
		} else {
			criteria = session.createCriteria(PeakHour.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
		}
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("div.service_type_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("peak_Name", search_term,
					MatchMode.START));
			criteria.add(conditionGroup);

		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}
		criteria.createCriteria("servicetype", "div");
		List<PeakHour> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			int totalAfterFilter = total;
			// JSONArray array = new JSONArray();
			String sql = "from PeakHour";
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");

			Criteria criteria;
			if (viewdeletedrecord.equalsIgnoreCase("Y")) {
				criteria = session.createCriteria(PeakHour.class);
			} else {
				criteria = session.createCriteria(PeakHour.class);
				criteria.add(Restrictions.eq("deleted_status", 0));
			}
		
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("div.service_type_name",
						"%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("sche.scheduleName",
						"%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("peak_Name", search_term,
						MatchMode.START));
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

			criteria.createCriteria("servicetype", "div");
			criteria.createCriteria("scheduletype", "sche",Criteria.LEFT_JOIN);
			List<PeakHour> list = criteria.list();
			JSONArray array = new JSONArray();
			// System.out.println("List size----->" + list.size() + "\t"
			// + request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getPeak_Slack_hour_Id()
						+ "' value='"
						+ list.get(i).getPeak_Slack_hour_Id() + "'/>");
				ja.add(list.get(i).getPeak_Slack_hour_Id());
				ja.add(list.get(i).getPeak_Name() != null ? list.get(i).getPeak_Name().replaceAll(" ", "&nbsp;") : "");
				// ja.add(list.get(i).getStatus()!=null ?
				// list.get(i).getStatus().replaceAll(" ","&nbsp;"):"");
    			// ja.add(common.getDateFromDateTime_(list.get(i).getStart_time().toString()));
				//ja.add(common.getDateFromPickertopeak(list.get(i).getStart_time().toString()));
				ja.add(list.get(i).getStart_time() != null ? common.formatDate(list.get(i).getStart_time().toString(),"yyyy-MM-dd HH:mm:ss","HH:mm") : "");
				ja.add(list.get(i).getEnd_time() != null ? common.formatDate(list.get(i).getEnd_time().toString(),"yyyy-MM-dd HH:mm:ss","HH:mm") : "");
				
				//.add(common.getDateFromPickertopeak(list.get(i).getEnd_time().toString()));
				//ja.add(list.get(i).getStart_time()!=null?list.get(i).getStart_time().toString():"");
				//ja.add(list.get(i).getEnd_time()!=null?list.get(i).getEnd_time().toString():"");

				ja.add(list.get(i).getPercentage());
				ja.add(list.get(i).getLumpsum_amount());
				ja.add(list.get(i).getNote());
				ja.add(list.get(i).getEffective_start_date() != null ? common.formatDate(list.get(i).getEffective_start_date().toString(),"yyyy-MM-dd","dd/MM/yyyy") : "");
				ja.add(list.get(i).getEffective_end_date() != null ? common.formatDate(list.get(i).getEffective_end_date().toString(),"yyyy-MM-dd","dd/MM/yyyy") : "");
				
				//ja.add(common.getDateToDateInpeakHour(list.get(i).getEffective_start_date()));
				//ja.add(common.getDateToDateInpeakHour(list.get(i).getEffective_end_date()) != null ?common.getDateToDateInpeakHour(list.get(i).getEffective_end_date()).replaceAll(" ", "&nbsp;") : "");
				ja.add(list.get(i).getServicetype().getService_type_name());
				ja.add(list.get(i).getScheduletype()!=null?list.get(i).getScheduletype().getScheduleName():"");
				ja.add(list.get(i).getRateMaster().getVersionNoServiceType());
				
				if (viewdeletedrecord.equalsIgnoreCase("Y")) {
					int deletedstatus = list.get(i).getDeleted_status();

					if (deletedstatus == 1) {
						ja.add("<input type='hidden' value='N' id='isRocordEligible"
								+ list.get(i).getPeak_Slack_hour_Id()
								+ "'/>Deleted Record");
						// ja.add(" ");
					} else {
						// ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"
								+ list.get(i).getPeak_Slack_hour_Id()
								+ "'/>Record in Database");
					}

					// ja.add(rs.get("vendor_name").toString());
				} else {

				}

				array.add(ja);
				// System.out.println("Array----->" + array);
			}

			totalAfterFilter = getTotalRecords(total, request, col, dir,
					viewdeletedrecord);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			// System.out.println("REsult ------>"
			// + request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int insertDeviceType(PeakHour peakhour) {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Common common=new Common();
		//session.beginTransaction();
		int i = 0;
		// System.out.println("000000000000000000rajesh");
		try {
			peakhour.setEffective_start_date(common.formatDate(peakhour.getEffective_start_date(),"dd/MM/yyyy","yyyy-MM-dd"));
			//peakhour.setEffective_end_date(common.formatDate(peakhour.getEffective_end_date(),"dd/MM/yyyy","yyyy-MM-dd"));
			peakhour.setEffective_end_date(peakhour.getEffective_end_date()!= null ? common.formatDate(peakhour.getEffective_end_date().toString(),"dd/MM/yyyy","yyyy-MM-dd"):"");
			
			peakhour.setStart_time(common.formatDate(peakhour.getStart_time(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"));
			peakhour.setEnd_time(common.formatDate(peakhour.getEnd_time(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"));

			peakhour.setDeleted_status(1);
			i = (Integer) session.save(peakhour);
			//session.getTransaction().commit();
		} catch (Exception e) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		} finally {
			session.close();
		}
		return i;
		}

	public PeakHour getEditedDeviceType(int id) {
		// BusStops busstops = new BusStops();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		PeakHour peakhour = (PeakHour) session.get(PeakHour.class, new Integer(id));
		
		peakhour.setEffective_start_date(peakhour.getEffective_start_date() != null ? common.formatDate(peakhour.getEffective_start_date().toString(),"yyyy-MM-dd","dd/MM/yyyy"):"");
		peakhour.setEffective_end_date(peakhour.getEffective_end_date() != null ? common.formatDate(peakhour.getEffective_end_date().toString(),"yyyy-MM-dd","dd/MM/yyyy"):"");
		//peakhour.setEffective_end_date(peakhour.getEffective_end_date());
		peakhour.setStart_time(common.formatDate(peakhour.getStart_time().toString(),"yyyy-MM-dd HH:mm:ss","dd/MM/yyyy HH:mm"));
		peakhour.setEnd_time(common.formatDate(peakhour.getEnd_time().toString(),"yyyy-MM-dd HH:mm:ss","dd/MM/yyyy HH:mm"));
		// + "\t" + fareChart.getRoute_id());
		return peakhour;

	}
	
	public String deleteDeviceType(PeakHour peakhour, int peak_id) {

		Session session = null;
		String status = "error";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			DependencyChecker dc = new DependencyChecker();
			status = dc.checkDependentEntities(session, "PeakHour", peak_id);
			System.out.println("%%%%%%%" + status);
			if (status.trim().equalsIgnoreCase("")) {

				session.beginTransaction();
				// System.out.println("=====Route id" + role.getRole_id());

				//
				PeakHour peakhours = (PeakHour) session.get(PeakHour.class,
						peak_id);

				peakhours.setDeleted_status(1);
				peakhours.setUpdated_by(peakhour.getUpdated_by());
				peakhours.setUpdated_date(new java.util.Date());

				session.getTransaction().commit();
			}
		} catch (Exception e) {

			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			session.getTransaction().rollback();

		} finally {
			session.close();
		}

		return status;
	}

	// Delete Complaint start

	public String deleteComplaint(Complaint complaint, int Complaint_id) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		// System.out.println("=====Route id" + role.getRole_id());
		try {
			//
			Complaint complaints = (Complaint) session.get(Complaint.class,
					Complaint_id);

			complaints.setStatus("INACTIVE");
			complaints.setDeleted_status(1);
			complaints.setUpdated_by(complaint.getUpdated_by());
			complaints.setUpdated_date(new java.util.Date());

			session.update(complaints);
			session.getTransaction().commit();
		} catch (Exception e) {

			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			session.getTransaction().rollback();

		} finally {
			session.close();
		}

		return null;
	}

	// end

	public String updateDeviceType(Complaint complaint, int complaintid) {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		// System.out.println("Inside edited data$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		try {

			Complaint complaints = (Complaint) session.get(Complaint.class,
					complaintid);
			complaints.setCompliant_type(complaint.getCompliant_type());
			// complaints.setIncident_date(common.getDateFromPicker(complaint.getIncident_date()));
			// complaints.setComplaint_date(common.getDateFromPicker(complaint.getComplaint_date()));
			complaints.setIdentification_data(complaint
					.getIdentification_data());
			complaints.setComplaint_description(complaint
					.getComplaint_description());
			complaints.setTaken_by(complaint.getTaken_by());
			complaints.setStatus(complaint.getStatus());
			complaints.setComplaint_media(complaint.getComplaint_media());

			session.update(complaints);
			session.getTransaction().commit();
		} catch (Exception e) {

			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			session.getTransaction().rollback();

		} finally {
			session.close();
		}

		return null;
	}

	// update complaint
	public String updateComplaint(PeakHour peakhour, int peakid) {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		// System.out.println("Inside edited data$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+peakhour.getEffective_start_date()+""+peakhour.getEffective_end_date());
		try {
			// System.out.println(complaint.getIncident_date()+"tttttttttttttttt"+complaintid);
			PeakHour peakhours = (PeakHour) session.get(PeakHour.class, peakid);
			peakhours.setPeak_Name(peakhour.getPeak_Name());		
			peakhours.setPercentage(peakhour.getPercentage());
			peakhours.setLumpsum_amount(peakhour.getLumpsum_amount());
			peakhours.setServicetype(peakhour.getServicetype());
			peakhours.setScheduletype(peakhour.getScheduletype());
			peakhours.setRateMaster(peakhour.getRateMaster());
			peakhours.setIncrease_decrease(peakhour.getIncrease_decrease());
			peakhours.setNote(peakhour.getNote());
			peakhours.setEffective_start_date(common.formatDate(peakhour.getEffective_start_date(),"dd/MM/yyyy","yyyy-MM-dd"));
			peakhours.setEffective_end_date(common.formatDate(peakhour.getEffective_end_date(),"dd/MM/yyyy","yyyy-MM-dd"));
			peakhours.setStart_time(common.formatDate(peakhour.getStart_time(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"));
			peakhours.setEnd_time(common.formatDate(peakhour.getEnd_time(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"));	
			peakhours.setUpdated_by(peakhour.getUpdated_by());
			peakhours.setUpdated_date(new java.util.Date());
			
			session.update(peakhours);
			session.getTransaction().commit();
		} catch (Exception e) {

			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			session.getTransaction().rollback();
			session.getTransaction().rollback();

		} finally {
			session.close();
		}

		return null;
	}

	

	public boolean isGreaterThanCurrentDate(String date) {
		boolean isSuccess = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// System.out.println("$$$$$$$$$$$$$$$$$$$$$$"+date);
			Date date1 = sdf.parse(date);
			if (date1.compareTo(new Date()) > 0) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;

	}

	public String getTime(Date pickerDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd-MM-yyyy - HH:mm");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
		Date date = null;
		String formattedDate = "";
		try {
			date = simpleDateFormat1.parse(pickerDate.toString());
			formattedDate = simpleDateFormat.format(date);
		} catch (Exception ex) {

		} finally {
			return formattedDate;
		}
	}
	
	public boolean checkPeakHourForDuplicate(PeakHour pk) {
		boolean flag = false;
		Common common=new Common();

		String sd=common.formatDate(pk.getEffective_start_date(), "dd/mm/yyyy", "yyyy-mm-dd");
		String se=pk.getEffective_end_date();
		String st=common.formatDate(pk.getStart_time(), "dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss");
		String et=common.formatDate(pk.getEnd_time(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss");		
		int Service_type_id=pk.getServicetype().getService_type_id();

		String qry = "select * from PeakHour where deleted_status=0  " +
				" and (('"+st+"'>start_time and '"+et+"'<end_time) or ('"+et+"'>start_time and '"+st+"'<end_time)) " +
				"or ('"+et+"' between start_time and end_time) and "+
				" ('"+sd+"' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date)) 	" ;
				
				if (pk.getEffective_end_date() != null && (!pk.getEffective_end_date().trim().isEmpty())) {
			qry += " or ('"+se+"' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date)) ";
				}
		// String qry =
		// " select peak_Name from PeakHour where peak_Name='"+peakname+"' and deleted_status='0'";
		//System.out.println(qry);
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		List<PeakHour> list = query.list();
		 System.out.println("QUery Size===>"+query.list().size());
		if (list.size() > 0) {
			return flag;
		}
		return flag = true;
	}
		
	public boolean checkPeakHourForUpdate(PeakHour pk,int id) {
		boolean flag = false;
		Common common=new Common();

		String sd=common.formatDate(pk.getEffective_start_date(), "dd/mm/yyyy", "yyyy-mm-dd");
		String se=pk.getEffective_end_date();
		String st=common.formatDate(pk.getStart_time(), "dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss");
		String et=common.formatDate(pk.getEnd_time(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss");		
		int Service_type_id=pk.getServicetype().getService_type_id();

		String qry = "select * from PeakHour where deleted_status=0 and Peak_Slack_hour_Id<>"+id+  
				" and (('"+st+"'>start_time and '"+et+"'<end_time) or ('"+et+"'>start_time and '"+st+"'<end_time)) and  " +
				" ('"+sd+"' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date)) 	" ;
				
				if (pk.getEffective_end_date() != null && (!pk.getEffective_end_date().trim().isEmpty())) {
			qry += " or ('"+se+"' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date)) ";
				}
		// String qry =
		// " select peak_Name from PeakHour where peak_Name='"+peakname+"' and deleted_status='0'";
		System.out.println(qry);
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		List<PeakHour> list = query.list();
		 System.out.println("QUery Size===>"+query.list().size());
		if (list.size() > 0) {
			return flag;
		}
		return flag = true;
	}

	// duplicate query

	

	public RateMaster getRateMaster(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		RateMaster rateMaster = (RateMaster) session.get(RateMaster.class,
				new Integer(id));
		return rateMaster;

	}

	public int addRateMaster(RateMaster rm,PeakHour peakhour) {
		Common common = new Common();
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//session.beginTransaction();
		int i = 0;
		RateMaster rateMaster = new RateMaster();
		int usrsessionid = (Integer) request.getSession().getAttribute("userid");

		try {
			rateMaster.setParent_rate_master(rm.getRateMasterId());
			rateMaster.setVersionNumber(rm.getVersionNumber());
			rateMaster.setVersionNoServiceType(rm.getVersionNoServiceType());
			rateMaster.setServiceTypeId(rm.getServiceTypeId());
			rateMaster.setEffectiveStartDate(peakhour.getEffective_start_date());
			rateMaster.setEffectiveEndDate(peakhour.getEffective_end_date());
			rateMaster.setStatus("Inactive");
			rateMaster.setDeletedStatus(1);
			rateMaster.setCreatedBy(usrsessionid);
			rateMaster.setCreatedDate(common
					.getDateInDateSecs(new java.util.Date()));
			rateMaster.setUpdatedBy(0);
			rateMaster.setUpdatedDate(null);
			i = (Integer) session.save(rateMaster);
			//session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return i;
	}

	public Integer getRateMasterId(int rateMasterId) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		// RateMasterDetail rateMasterDetail=(RateMasterDetail)
		// session.get("From RateMaster where RateMaster.parent_rate_master_id = : parent_rate_master_id");
		Query q = session
				.createQuery("select rateMasterId From RateMaster r where r.parent_rate_master = :parent_rate_master");
		q.setParameter("parent_rate_master", rateMasterId);
		return (Integer) q.list().get(q.list().size() - 1);

	}

	public List<RateMasterDetail> getRateMasterDetials(int rmdId) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		RateMasterDetail rateMasterDetail = (RateMasterDetail) session.get(
				RateMasterDetail.class, new Integer(rmdId));
		String qry = "from RateMasterDetail where deletedStatus=0 and rateMasterId="
				+ rmdId;
		// select rateMasterDetailId,stageNo,adult,children,seniorCitizen
		Query query = HibernateUtil.getSession("").createQuery(qry);
		List<RateMasterDetail> list = query.list();
		//System.out.println("rmd list size==>>" + list.size());
		return list;

	}

	// rate master detail
	public int addRateMasterDetail(List<RateMasterDetail> rateMd, int rateId,
			float percent, int lumpsum, int incDecr) {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		// RateMaster rateMaster=rateM;
		int success = 0;
		Session session = HibernateUtil.getSession("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());

		// decide to add or copy
		for (int i = 0; i < 70; i++) {
			// System.out.println("iiii="+i);
			RateMasterDetail rateMasterDetail = new RateMasterDetail();
			rateMasterDetail.setRateMasterId(rateId);
			rateMasterDetail.setServiceTypeId(rateMd.get(i).getServiceTypeId());
			rateMasterDetail.setStageNo(i + 1);
			int perAdult = Math.round((rateMd.get(i).getAdult() * percent / 100));
			int perAdultvalue = Math.round(rateMd.get(i).getAdult()- perAdult);
			int lumpsumvalue = Math.round(rateMd.get(i).getAdult()- lumpsum);
			int perChild = (int) (rateMd.get(i).getChildren() * percent / 100);
			int perSeniorCitizen = (int) (rateMd.get(i).getSeniorCitizen() * percent / 100);
			//if(perAdultvalue>=0&&lumpsumvalue>=0){				
			if (incDecr == 1) {
				if (percent > 0 && lumpsum == 0) {
					rateMasterDetail.setAdult(Math.round(rateMd.get(i).getAdult()+ perAdult));
				} else if (percent == 0 && lumpsum > 0) {
					rateMasterDetail.setAdult(Math.round(rateMd.get(i).getAdult()+ lumpsum));
				}
			} else if (incDecr == 2) {
				if (percent > 0 && lumpsum == 0) {
					rateMasterDetail.setAdult(Math.round(rateMd.get(i).getAdult()- perAdult));
				} else if (percent == 0 && lumpsum > 0) {
					rateMasterDetail.setAdult(Math.round(rateMd.get(i).getAdult()- lumpsum));
				}
			} else {
				rateMasterDetail.setAdult(rateMd.get(i).getAdult());
			}

			if (incDecr == 1) {
				if (percent > 0 && lumpsum == 0) {
					rateMasterDetail.setChildren((int)Math.round((rateMd.get(i).getAdult() + perAdult)*(0.50)));
				} else if (percent == 0 && lumpsum > 0) {
					rateMasterDetail.setChildren((int)Math.round((rateMd.get(i).getAdult() + lumpsum) *(0.50)));
				}
			} else if (incDecr == 2) {
				if (percent > 0 && lumpsum == 0) {
					rateMasterDetail
							.setChildren((int)Math.round((rateMd.get(i).getAdult() - perAdult)*(0.50)));
				} else if (percent == 0 && lumpsum > 0) {
					rateMasterDetail
							.setChildren((int)Math.round((rateMd.get(i).getAdult() - lumpsum) *(0.50)));
				}
			} else {
				rateMasterDetail.setChildren(rateMd.get(i).getChildren());
			}

			if (incDecr == 1) {
				if (percent > 0 && lumpsum == 0) {
					rateMasterDetail.setSeniorCitizen((int)Math.round(((rateMd.get(i).getAdult() + perAdult) * 0.75)));
				} else if (percent == 0 && lumpsum > 0) {
					rateMasterDetail.setSeniorCitizen((int)Math.round(((rateMd.get(i).getAdult() + lumpsum) * 0.75)));
				}

			} else if (incDecr == 2) {
				if (percent > 0 && lumpsum == 0) {
					rateMasterDetail.setSeniorCitizen((int)Math.round(((rateMd.get(i).getAdult() - perAdult) *  0.75)));
				} else if (percent == 0 && lumpsum > 0) {
					rateMasterDetail.setSeniorCitizen((int)Math.round(((rateMd.get(i).getAdult() - lumpsum) *  0.75)));
				}

			} else {
				rateMasterDetail.setSeniorCitizen(rateMd.get(i)
						.getSeniorCitizen());
			}

			rateMasterDetail.setCreatedDate(date);
			rateMasterDetail.setDeletedStatus(0);
			rateMasterDetail.setUpdatedBy(user.getUserId());
			rateMasterDetail.setCreatedBy(user.getUserId());			
			session.save(rateMasterDetail);
			/*}else{
				addActionError("Please Enter valid Fare for stage ");	
			}*/
			success = 1;
			// System.out.println("save success iiii="+i);
		}

		return success;
	}

	public String updateRateMasterDetail(List<RateMasterDetail> rateMd,
			int rateId, float percent, int lumpsum, int incDecr) {

	//	System.out.println("gggggg##$$$$$$$$$$$$$" + rateId);
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		// session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		//System.out.println("iiii=-------------" + rateMd.size());
		try {
			session.beginTransaction();
			Query q = session.createQuery("From RateMasterDetail where rateMasterId = "+ rateId);
			List<RateMasterDetail> list = q.list();
			for (RateMasterDetail rateMasterDetail : list) {				
				// rateMasterDetail.setRateMasterId(rateId);
				// rateMasterDetail.setServiceTypeId(rateMasterDetail.getServiceTypeId());
				// rateMasterDetail.setStageNo(i+1);
				int perAdult = Math.round((rateMasterDetail.getAdult() * percent / 100));
				int perChild = (int) (rateMasterDetail.getChildren() * percent / 100);
				int perSeniorCitizen = (int) (rateMasterDetail.getSeniorCitizen()
						* percent / 100);

				if (incDecr == 1) {
					if (percent > 0 && lumpsum == 0) {
						rateMasterDetail.setAdult(Math.round(rateMasterDetail.getAdult()+ perAdult));
					} else if (percent == 0 && lumpsum > 0) {
						rateMasterDetail.setAdult(Math.round(rateMasterDetail.getAdult()+ lumpsum));
					}
				} else if (incDecr == 2) {
					if (percent > 0 && lumpsum == 0) {
						rateMasterDetail.setAdult(Math.round(rateMasterDetail.getAdult()- perAdult));
					} else if (percent == 0 && lumpsum > 0) {
						rateMasterDetail.setAdult(Math.round(rateMasterDetail.getAdult()- lumpsum));
					}
				} else {
					rateMasterDetail.setAdult(rateMasterDetail.getAdult());
				}

				if (incDecr == 1) {
					if (percent > 0 && lumpsum == 0) {
						rateMasterDetail.setChildren((int)Math.round((rateMasterDetail.getAdult()) *(0.50)));
					} else if (percent == 0 && lumpsum > 0) {
						rateMasterDetail.setChildren((int)Math.round((rateMasterDetail.getAdult()) *(0.50)));
					}
				} else if (incDecr == 2) {
					if (percent > 0 && lumpsum == 0) {
						rateMasterDetail.setChildren((int)Math.round((rateMasterDetail.getAdult()) *(0.50)));
					} else if (percent == 0 && lumpsum > 0) {
						rateMasterDetail.setChildren((int)Math.round((rateMasterDetail.getAdult())*(0.50)));
					}
				} else {
					rateMasterDetail.setChildren(rateMasterDetail.getChildren());
				}

				if (incDecr == 1) {
					if (percent > 0 && lumpsum == 0) {
						rateMasterDetail.setSeniorCitizen((int)Math.round(((rateMasterDetail.getAdult()) * 0.75)));
					} else if (percent == 0 && lumpsum > 0) {
						rateMasterDetail.setSeniorCitizen((int)Math.round(((rateMasterDetail.getAdult()) * 0.75)));
					}

				} else if (incDecr == 2) {
					if (percent > 0 && lumpsum == 0) {
						rateMasterDetail.setSeniorCitizen((int)Math.round(((rateMasterDetail.getAdult()) *0.75)));
					} else if (percent == 0 && lumpsum > 0) {
						rateMasterDetail.setSeniorCitizen((int)Math.round(((rateMasterDetail.getAdult()) *0.75)));
					}

				} else {
					rateMasterDetail.setSeniorCitizen(rateMasterDetail.getSeniorCitizen());
				}

				rateMasterDetail.setUpdatedBy(user.getUserId());
				rateMasterDetail.setUpdatedDate(date);
				// rateMasterDetail.setCreatedBy(user.getUserId());
//				System.out.println(rateMasterDetail.getAdult()+"..........##### : "
//						+ rateMasterDetail.getChildren());
				session.saveOrUpdate(rateMasterDetail);
			}
			session.getTransaction().commit();
		} catch (Exception ex) {
			// session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {

			session.close();
		}

		return null;
	}
	
//	
	public List<String> getRateMasterIdByServiceId(int id) {
		List list = new ArrayList();;
		RateMaster rateMaster = null;
		Common common = new Common();
		// System.out.println("inside get id");
		String qry = "select rate_master_id from rate_master "
				 + "where deleted_status =0 and parent_rate_master=0 and version_number_service_stype!='NULL' and status='Active' and service_type_id="+ id
				+ " and (curdate() between effective_start_date and ifnull(effective_end_date,(DATE_ADD(curdate(),INTERVAL 1 DAY)))"
				+ " or curdate() >= effective_start_date and curdate() <= effective_end_date) or effective_start_date >=curdate() order by version_number";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				try {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					rateMaster = new RateMaster();
					//rateMaster.setRateMasterId(Integer.parseInt(rs.get("rate_master_id").toString()));
					/*rateMaster.setVersionNoServiceType(rs.get("version_number_service_stype").toString());
					rateMaster.setEffectiveStartDate(common.getDateFromDateTime_(rs.get("effective_start_date").toString()));
					rateMaster.setEffectiveEndDate(rs.get("effective_end_date") != null ? common.getDateFromDateTime_(rs.get("effective_end_date").toString()): "");*/
					list.add((rs.get("rate_master_id").toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	public List<String>  getRateMasterVersionByServiceId(int id) {
		List list =new ArrayList();;
		RateMaster rateMaster = null;
		Common common = new Common();
		// System.out.println("inside get id");
		String qry = "select concat(version_number_service_stype," 
				+" '(',DATE_FORMAT(effective_start_date,'%d-%m-%Y'),'-'," +
				" IFNULL(DATE_FORMAT(effective_end_date,'%d-%m-%Y'),'NA'),')') as version " +
				" from rate_master where deleted_status =0 and parent_rate_master=0 and version_number_service_stype!='NULL' and status='Active' and service_type_id="+id
				+" and (curdate() between effective_start_date and " +
				" ifnull(effective_end_date,(DATE_ADD(curdate(),INTERVAL 1 DAY))) " +
				" or curdate() >= effective_start_date and curdate() <= effective_end_date) or effective_start_date >=curdate() order by version_number";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				try {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					rateMaster = new RateMaster();
					//rateMaster.setRateMasterId(Integer.parseInt(rs.get("rate_master_id").toString()));
				   // rateMaster.setVersionNoServiceType(rs.get("version").toString());
//					rateMaster.setEffectiveStartDate(common.getDateFromDateTime_(rs.get("effective_start_date").toString()));
//					rateMaster.setEffectiveEndDate(rs.get("effective_end_date") != null ? common.getDateFromDateTime_(rs.get("effective_end_date").toString()): "");
					list.add(rs.get("version").toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List<String> getRateMasterIdForEdit(int id) {
		List list = new ArrayList();;
		RateMaster rateMaster = null;
		Common common = new Common();
		// System.out.println("inside get id");
		String qry = "select rate_master_id from rate_master "
				 + "where deleted_status =0 and version_number_service_stype!='NULL' and status='Active' and service_type_id="+ id
				+ " and (curdate() between effective_start_date and ifnull(effective_end_date,(DATE_ADD(curdate(),INTERVAL 1 DAY)))"
				+ " or curdate() >= effective_start_date and curdate() <= effective_end_date) or effective_start_date >=curdate() order by version_number";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				try {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					rateMaster = new RateMaster();
					//rateMaster.setRateMasterId(Integer.parseInt(rs.get("rate_master_id").toString()));
					/*rateMaster.setVersionNoServiceType(rs.get("version_number_service_stype").toString());
					rateMaster.setEffectiveStartDate(common.getDateFromDateTime_(rs.get("effective_start_date").toString()));
					rateMaster.setEffectiveEndDate(rs.get("effective_end_date") != null ? common.getDateFromDateTime_(rs.get("effective_end_date").toString()): "");*/
					list.add((rs.get("rate_master_id").toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	public List<String>  getRateMasterVersionForEdit(int id) {
		List list =new ArrayList();;
		RateMaster rateMaster = null;
		Common common = new Common();
		// System.out.println("inside get id");
		String qry = "select concat(version_number_service_stype," 
				+" '(',DATE_FORMAT(effective_start_date,'%d-%m-%Y'),'-'," +
				" IFNULL(DATE_FORMAT(effective_end_date,'%d-%m-%Y'),'NA'),')') as version " +
				" from rate_master where deleted_status =0 and version_number_service_stype!='NULL' and status='Active' and service_type_id="+id
				+" and (curdate() between effective_start_date and " +
				" ifnull(effective_end_date,(DATE_ADD(curdate(),INTERVAL 1 DAY))) " +
				" or curdate() >= effective_start_date and curdate() <= effective_end_date) or effective_start_date >=curdate() order by version_number";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				try {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					rateMaster = new RateMaster();
					//rateMaster.setRateMasterId(Integer.parseInt(rs.get("rate_master_id").toString()));
				   // rateMaster.setVersionNoServiceType(rs.get("version").toString());
//					rateMaster.setEffectiveStartDate(common.getDateFromDateTime_(rs.get("effective_start_date").toString()));
//					rateMaster.setEffectiveEndDate(rs.get("effective_end_date") != null ? common.getDateFromDateTime_(rs.get("effective_end_date").toString()): "");
					list.add(rs.get("version").toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List<String> getServicetypeid() {
		Session session = null;
		List list = null;
		String status="ACTIVE";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();
		
			String qry = "select service_type_id from service_type where status='"+status+"' and deleted_status=0 and service_type_name!='NULL' order by service_type_name";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("service_type_id").toString());
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

	public List<String> getServicetypeName() {
		Session session = null;
		List list = null;
		String status="ACTIVE";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			String qry = "select service_type_name from service_type where status='"+status+"' and deleted_status=0 and service_type_name!='NULL' order by service_type_name";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("service_type_name").toString());
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
	
	public RateMaster getRateMasterDataById(int rateId){

		Session session=null;
		RateMaster rateMaster=null;
		try{
		  session=HibernateUtil.getSession("");
		
		  rateMaster = (RateMaster) session.load(RateMaster.class, new Integer(rateId));	
		
		  String[] effStartDate=rateMaster.getEffectiveStartDate().split("-");
		  rateMaster.setEffectiveStartDate(effStartDate[2]+"/"+effStartDate[1]+"/"+effStartDate[0]);
		  String[] effEndDate=rateMaster.getEffectiveEndDate().split("-");
		  rateMaster.setEffectiveEndDate(effEndDate[2]+"/"+effEndDate[1]+"/"+effEndDate[0]);
		}
		catch(Exception e)
		{}
		finally{
			session.close();
		}

		return rateMaster;
	}
	
	public int updateRateMasterDetailByPeakHour(RateMasterDetail rm,int id){
		Session session = null;
		int updated=0;
		
		try{
		session = HibernateUtil.getSession("");
		session.beginTransaction();
		String queryString = "update RateMasterDetail set adult="+rm.getAdult()+",children="+rm.getChildren()
				+",seniorCitizen="+rm.getSeniorCitizen()+" where rateMasterId="+id
				+" and stageNo="+rm.getStageNo();
		Query query = session.createQuery(queryString);
		updated= query.executeUpdate();
		
		session.getTransaction().commit();
		}
		catch(Exception e){
			session.getTransaction().rollback();
		}
		finally{
			if(session!=null && session.isOpen())
			session.close();
		}
		return updated;
	}
	
	public String updateRateMaster(RateMaster rateMaster,int voucher_id) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();

		try {
			//
			RateMaster ratemaster = (RateMaster) session.get(RateMaster.class, voucher_id);
			ratemaster.setVersionNumber(rateMaster.getVersionNumber());
			ratemaster.setVersionNoServiceType(rateMaster.getVersionNoServiceType());
			ratemaster.setDeletedStatus(0);
			ratemaster.setStatus("Active");			
			
			session.update(ratemaster);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
	
	public boolean checkRateMasterVersion(String vouchernumber) {
		boolean flag = false;
		String qry = " select version_number from rate_master where version_number='"
				+ vouchernumber + "' and deleted_status= " + 0 ;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	public Integer getPeakHourId() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query q = session.createQuery("select Peak_Slack_hour_Id From PeakHour r where r.deleted_status = :deleted_status");
		q.setParameter("deleted_status", 1);
		return (Integer) q.list().get(q.list().size() - 1);

	}
	public RateMaster getNewRateMaster(int rateMasterId) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		// RateMasterDetail rateMasterDetail=(RateMasterDetail)
		// session.get("From RateMaster where RateMaster.parent_rate_master_id = : parent_rate_master_id");
		Query q = session
				.createQuery("From RateMaster r where r.parent_rate_master = :parent_rate_master");
		q.setParameter("parent_rate_master", rateMasterId);
		return (RateMaster) q.list().get(q.list().size() - 1);

	}
	public String updatePeakHourMaster(int peakid,RateMaster rateId) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();

		try {
			//
			PeakHour ph = (PeakHour) session.get(PeakHour.class, peakid);
			ph.setRateMaster(rateId);
			ph.setUpdated_date(new java.util.Date());
			ph.setDeleted_status(0);
			
			session.update(ph);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
	public void deletePeakHr(){

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();			
		session.getTransaction().rollback();		
		session.close();
	}
	
	public Map<Integer, String> getServiceType() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from ServiceType service where deleted_status=0 and status='Active' order by service.service_type_name");
		try {
			List<ServiceType> list = query.list();
			for (ServiceType service : list) {
				resultMap.put(service.getService_type_id(),
						service.getService_type_name());
			}
		} catch (Exception ex) {

		} finally {
			session.close();
		}
		return resultMap;

	}

	public boolean checkPeakName(String peakname) {
		boolean flag = false;
		String qry = " select peak_Name from PeakHour where peak_Name='"
				+ peakname + "' and deleted_status='0'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		// System.out.println("QUery Size===>"+query.list().size());
		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

	public int checkPeakNameduplicate(String peakname) {
		boolean flag = false;
		int i = 0;
		String qry = " select peak_Name from PeakHour where peak_Name='"
				+ peakname + "' ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		// System.out.println("QUery Size===>"+query.list().size());
		i = query.list().size();

		return i;
	}

	public boolean isVehicleTypeNew(PeakHour peakhour, String reqType) {
		boolean isNew = false;
		Common common = new Common();
		Session session = null;

		String strDate = peakhour.getEffective_start_date();

		String strTime = peakhour.getStart_time();
		String endTime = peakhour.getEnd_time();
		String qry = "";

		String endDate = "";
		if (peakhour.getEffective_end_date() != null && (!peakhour.getEffective_end_date().trim().isEmpty())) {
			endDate = peakhour.getEffective_end_date();
		}

		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			// String query =
			// "select count(*)as count from vehicle_type where vehicle_type_name='"+
			// vehicleTypeObject.getPeak_Name().trim()+"'";
			if (reqType.equals("create")) {

			} else if (reqType.equals("update")) {
				// query =
				// "select count(*)as count from PeakHour where peak_Name='"+
				// vehicleTypeObject.getPeak_Name().trim()+"' and Peak_Slack_hour_Id	 not in ('"+vehicleTypeObject.getPeak_Slack_hour_Id()+"') ";
				// int service_id=

				qry = "select * from PeakHour  where service_type_id="
						+ peakhour.getServicetype().getService_type_id()
						+ " and  deleted_status=0  and "
						+ "((('"
						+ strTime
						+ "' between start_time and end_time)  or '"
						+ strTime
						+ "'=end_time or '"
						+ strTime
						+ "'=start_time) "
						+ "or ('"
						+ endTime
						+ "' between start_time and ifnull(end_time,start_time) or "
						+ "(('"
						+ strTime
						+ "' <= start_time and '"
						+ endTime
						+ "' between start_time and ifnull(end_time,start_time) ) "
						+ "or ('"
						+ strTime
						+ "' <= start_time and '"
						+ endTime
						+ "' >= ifnull(end_time,start_time))))) and "

						+ "((('"
						+ strDate
						+ "' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date)) "
						+ " or '"
						+ strDate
						+ "'=ifnull(Effective_end_date,Effective_start_date) or '"
						+ strDate + "'=Effective_start_date) ";

				if (peakhour.getEffective_end_date() != null
						&& (!peakhour.getEffective_end_date().trim().isEmpty())) {
					qry += " or ('"
							+ endDate
							+ "' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date) "
							+ " or (('"
							+ strDate
							+ "' <= Effective_start_date and '"
							+ endDate
							+ "' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date) )"
							+ " or ('"
							+ strDate
							+ "' <= Effective_start_date and '"
							+ endDate
							+ "' >= ifnull(Effective_end_date,Effective_start_date))))";
				}
				qry += " ) and Peak_Slack_hour_Id	 not in ('"
						+ peakhour.getPeak_Slack_hour_Id() + "')";

				System.out.println("Query show:" + qry + "");

				// query =
				// "select count(*)as count from vehicle where license_number not in ('"+
				// vehicleObject.getVehicleRegistrationNumber()+"') or " +
				// "( vehicle_id ='"+vehicleObject.getVehicleId()+"' and license_number = '"+
				// vehicleObject.getVehicleRegistrationNumber()+"')";
			}
			Query query = session.createSQLQuery(qry);
			List<PeakHour> list = query.list();
			// if(list.size()>0)
			int cnt = list.size();

			if (cnt > 0) {
				isNew = false;
			} else {
				isNew = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isNew = false;
		} finally {
			return isNew;
		}
	}
	
	public int duplicateByQuery(PeakHour peakhour) {
		int masterId = -1;
		Common common = new Common();
		try {

			String strDate = peakhour.getEffective_start_date();
			System.out.println("strDate"+strDate);

			String strTime = peakhour.getStart_time();
			System.out.println("strTime"+strTime);
			String endTime = peakhour.getEnd_time();
			System.out.println("endTime"+endTime);

			String endDate = "";
			if (peakhour.getEffective_end_date() != null&& (!peakhour.getEffective_end_date().trim().isEmpty())) {
				endDate = common.getDateToDate5(peakhour.getEffective_end_date());
				System.out.println("endDate"+endDate);
			}

			String qry = "from PeakHour  where servicetype.service_type_id="
					+ peakhour.getServicetype().getService_type_id()
					+ " and  deleted_status=0  and "
					+ "((('"
					+ strTime
					+ "' between start_time and end_time)  or '"
					+ strTime
					+ "'=end_time or '"
					+ strTime
					+ "'=start_time) "
					+ "or ('"
					+ endTime
					+ "' between start_time and ifnull(end_time,start_time) or "
					+ "(('"
					+ strTime
					+ "' <= start_time and '"
					+ endTime
					+ "' between start_time and ifnull(end_time,start_time) ) "
					+ "or ('"
					+ strTime
					+ "' <= start_time and '"
					+ endTime
					+ "' >= ifnull(end_time,start_time))))) and "

					+ "((('"
					+ strDate
					+ "' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date)) "
					+ " or '" + strDate
					+ "'=ifnull(Effective_end_date,Effective_start_date) or '"
					+ strDate + "'=Effective_start_date) ";

			if (peakhour.getEffective_end_date() != null
					&& (!peakhour.getEffective_end_date().trim().isEmpty())) {
				qry += " or ('"
						+ endDate
						+ "' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date) "
						+ " or (('"
						+ strDate
						+ "' <= Effective_start_date and '"
						+ endDate
						+ "' between Effective_start_date and ifnull(Effective_end_date,Effective_start_date) )"
						+ " or ('"
						+ strDate
						+ "' <= Effective_start_date and '"
						+ endDate
						+ "' >= ifnull(Effective_end_date,Effective_start_date))))";
			}
			qry += ")";
			//System.out.println("dfw" + qry);
			Session session = null;
			try {
				Date starTime= new SimpleDateFormat("yyyy/mm/dd HH:mm:ss").parse(strTime + ":00");
				
				//System.out.println("time=" + t.getTime());

				long endtimeparlog =starTime.getTime();
				System.out.println("S=" + endtimeparlog);

				session = HibernateUtil.getSession("");
				Query query = session.createQuery(qry);
				List<PeakHour> list = query.list();
				System.out.println("list.size()"+list.size());
				if (list.size() > 0) {

					// Hour compare code
					for (PeakHour peakhours : list) {
						System.out.println("for loop"+peakhours.getStart_time());
						Date starTime1= new SimpleDateFormat("yyyy/mm/dd HH:mm:ss").parse(peakhours.getStart_time() + ":00");
						Date endTime1= new SimpleDateFormat("yyyy/mm/dd HH:mm:ss").parse(peakhours.getStart_time() + ":00");

						long dbstarttime =starTime1.getTime();
								//Long.parseLong((common.formatDate(peakhours.getStart_time(), "dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"))); 
								//returnFormatHrDate(peakhours	.getStart_time());
						long dbendtime =endTime1.getTime();
								//Long.parseLong((common.formatDate(peakhours.getEnd_time(), "dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"))); 
								//returnFormatHrDate(peakhours.getEnd_time());

						System.out.println("E=" + dbstarttime + "::"+ dbendtime);

						masterId = peakhours.getPeak_Slack_hour_Id();
						if (endtimeparlog > dbstarttime	&& endtimeparlog >= dbendtime) {

							masterId = -1;
							// break;

						} else {
							masterId = 1;
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return masterId;
	}

	public long returnFormatHrDate(String s) {
		/*	
		 * String ss[]=s.split(":");
		 * 
		 * int HH=Integer.parseInt(ss[0]); int mm=Integer.parseInt(ss[1]);
		 

		
		 * Calendar cal = Calendar.getInstance(); cal.set(Calendar.HOUR_OF_DAY,
		 * HH); // Your hour cal.set(Calendar.MINUTE, mm); // Your Mintue
		 * cal.set(Calendar.SECOND, 00); // Your second
		 * 
		 * Time sqlTime3 = new Time(cal.getTime().getTime()); return sqlTime3;
		 */
		Date windowStarttime = null;
		try {
			windowStarttime = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss").parse(s + ":00");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return windowStarttime.getTime();
	}
}
