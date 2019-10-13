package com.trimax.its.fare.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.trimax.its.common.Common;
import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.fare.model.RateMasterDetail;
import com.trimax.its.model.User;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;

public class RateMasterDAO {

	public int add(RateMaster rateM, int isCopy) {
		Session session = null;
		Transaction tx = null;
		int success = 0;

		int newId = 0;

		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			User user = (User) request.getSession().getAttribute("user");
			RateMaster rateMaster = rateM;

			// check for duplicate entry based on version no-service type
			String qry = "from RateMaster where versionNumber='"
					+ rateM.getVersionNumber() + "' and serviceTypeId="
					+ rateM.getServiceTypeId()
					+ " and  deletedStatus=0 order by effectiveStartDate";

			Query query = HibernateUtil.getSession("").createQuery(qry);
			List<RateMaster> list = query.list();
			// System.out.println("list sz=>>"+list.size());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());

			String[] effStartDate = new String[3];
			String[] effEndDate = new String[3];

			// Effective Date
			try {
				effStartDate = rateM.getEffectiveStartDate().split("/");
				rateMaster.setEffectiveStartDate(effStartDate[2] + "/"
						+ effStartDate[0] + "/" + effStartDate[1]);
				// System.out.println("rateM.getEffectiveEndDate()="+rateM.getEffectiveEndDate());
				if (rateM.getEffectiveEndDate().length() > 0) {
					effEndDate = rateM.getEffectiveEndDate().split("/");
					rateMaster.setEffectiveEndDate(effEndDate[2] + "/"
							+ effEndDate[0] + "/" + effEndDate[1]);
				} else {
					rateMaster.setEffectiveEndDate(null);
				}
			} catch (Exception e) {
				try {
					effStartDate = rateM.getEffectiveStartDate().split("-");
					rateMaster.setEffectiveStartDate(effStartDate[0] + "/"
							+ effStartDate[1] + "/" + effStartDate[2]);
					effEndDate = rateM.getEffectiveEndDate().split("-");
					rateMaster.setEffectiveEndDate(effEndDate[0] + "/"
							+ effEndDate[1] + "/" + effEndDate[2]);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			// check for duplicate
			if (list.size() > 0) {

				sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
				Date date1 = sdf2.parse(rateMaster.getEffectiveStartDate());
				Date date2 = sdf.parse(list.get(list.size() - 1)
						.getEffectiveStartDate());

				if (date1.compareTo(date2) == 0) {
					// System.out.println("Date1 is equal to Date2");
					success = 2;
					return success;
				} else {
					if (date2.compareTo(date1) > 0) {
						// System.out.println("Date1 is after Date2");
						success = 3;
						return success;
					}
				}
				if(date1.compareTo(new Date())<0){
					success = 4;
					return success;
				}

				if (list.get(list.size() - 1).getEffectiveEndDate() != null) {
					// end date
					Date date3 = new Date();
					Date date4 = new Date();
					date3 = sdf2.parse(rateMaster.getEffectiveStartDate());
					date4 = sdf.parse(list.get(list.size() - 1)
							.getEffectiveEndDate());

					if (date4.compareTo(date1) > 0
							|| date1.compareTo(date4) == 0) {
						// System.out.println("Date1 is after Date2");
						success = 2;
						return success;
					}
				}

			}

			if (list.size() <= 0) {

				// /////check for duplicate entry based on effective date
				// /////////////////////////////
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sdate = sdf.parse(effStartDate[2] + "-" + effStartDate[0]
						+ "-" + effStartDate[1]);
				Date edate = null;
				if (rateM.getEffectiveEndDate() != null) {
					edate = sdf.parse(effEndDate[2] + "-" + effEndDate[0] + "-"
							+ effEndDate[1]);
				}

				// System.out.println("sdate="+sdate+"  edate="+edate);

				// get all start & end date from rate master
				qry = "from RateMaster where deletedStatus=0 and status='ACTIVE'";

				query = HibernateUtil.getSession("").createQuery(qry);
				List<RateMaster> listDates = query.list();
				// System.out.println("listDates sz=>>"+listDates.size());

				// date comparison
				for (int cnt = 0; cnt < listDates.size(); cnt++) {

					try {
						// System.out.println("dates="+listDates.get(cnt).getEffectiveStartDate()+"::"+listDates.get(cnt).getEffectiveEndDate());

						Date dbSdate = sdf.parse(listDates.get(cnt)
								.getEffectiveStartDate());
						Date dbEdate = sdf.parse(listDates.get(cnt)
								.getEffectiveEndDate());

						if (sdate.compareTo(dbSdate) > 0
								&& sdate.compareTo(dbEdate) < 0) {
							// System.out.println(dbSdate+"<>"+dbEdate+"="+sdate
							// +" is exist");

							// success=2;
							// return success;
						}

						if (edate.compareTo(dbSdate) > 0
								&& edate.compareTo(dbEdate) < 0) {
							// System.out.println(dbSdate+"<end>"+dbEdate+"="+edate
							// +" is exist");
							// success=2;
							// return success;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// ////////////////////////////////////////////////////////////////////////////////////
				rateMaster.setCreatedBy(user.getUserId());
				String serviceTypeName = getServiceNameById(rateM
						.getServiceTypeId());
				rateMaster.setServiceTypeId(rateM.getServiceTypeId());
				rateMaster.setVersionNoServiceType(rateM.getVersionNumber()
						+ "-" + serviceTypeName);
				rateMaster.setVersionNumber(rateM.getVersionNumber());
				rateMaster.setCreatedDate(date);
				rateMaster.setDeletedStatus(0);
				rateMaster.setUpdatedBy(0);
				rateMaster.setUpdatedDate(null);

				session = HibernateUtil.getSession("");
				tx = session.beginTransaction();
				success = (Integer) session.save(rateMaster);
				if (success > 0) {
					success = 1;// insert success
				}
				// auto insert rate master detail
				if (isCopy == 0) {
					addRateMasterDetail(rateM, 0, 0);
				}

			} else {
				// success=2; //duplicate entry
				// -1 end dt of str dt of new mster

				// /rateMaster.setEffectiveStartDate(list.get(0).getEffectiveStartDate());
				// /rateMaster.setEffectiveEndDate(list.get(0).getEffectiveEndDate());
				rateMaster.setCreatedBy(user.getUserId());
				String serviceTypeName = getServiceNameById(rateM
						.getServiceTypeId());
				rateMaster.setServiceTypeId(rateM.getServiceTypeId());
				rateMaster.setVersionNoServiceType(rateM.getVersionNumber()
						+ "-" + serviceTypeName);
				rateMaster.setVersionNumber(rateM.getVersionNumber());
				rateMaster.setCreatedDate(date);
				rateMaster.setDeletedStatus(0);
				rateMaster.setUpdatedBy(0);
				rateMaster.setUpdatedDate(null);

				session = HibernateUtil.getSession("");
				tx = session.beginTransaction();
				success = (Integer) session.save(rateMaster);

				if (success > 0) {
					success = 1;// insert success
				}

				// update end dt of matched record
				qry = "from RateMaster where rateMasterId="
						+ list.get(list.size() - 1).getRateMasterId()
						+ " and deletedStatus=0 order by effectiveStartDate";

				query = HibernateUtil.getSession("").createQuery(qry);
				List<RateMaster> listD = query.list();
				// System.out.println("listD sz="+listD.size()+" list.get(0).getRateMasterId()="+list.get(list.size()-1).getRateMasterId());
				RateMaster rateMasterOld = listD.get(0);// (RateMaster)session.load(RateMaster.class,new
														// Integer(list.get(0).getRateMasterId()));
				sdf = new SimpleDateFormat("yyyy/MM/dd");
				// Date oldDate = sdf.parse();
				Date newDate = sdf.parse(rateMaster.getEffectiveStartDate());
				Calendar cal = Calendar.getInstance();
				cal.setTime(newDate);
				cal.add(Calendar.DATE, -1);
				String updatedEndDt = sdf.format(cal.getTime());
				// System.out.println("updatedEndDt="+updatedEndDt);

				int rateId = (Integer) (list.get(list.size() - 1)
						.getRateMasterId());

				String queryString = "update RateMaster set effectiveEndDate='"
						+ updatedEndDt + "' where rateMasterId=" + rateId;

				if (list.get(list.size() - 1).getEffectiveEndDate() == null) {

					query = session.createQuery(queryString);
					query.executeUpdate();
				}

				// rateMasterOld.setEffectiveEndDate(updatedEndDt);
				// session.getTransaction().commit();
				// auto insert rate master detail
				/*if (isCopy == 0) {
					addRateMasterDetail(rateM, 0, 0);
				}*/
				addRateMasterDetail(rateM, 1, list.get(list.size() - 1).getRateMasterId());
			}

			newId = rateMaster.getRateMasterId();
			/*System.out.println("NewId -----> " + newId + " -------> "
					+ list.get(list.size() - 1).getRateMasterId() + " ----> "
					+ rateMaster.getEffectiveStartDate().replace("/", "-"));*/
			Common common = new Common();
			
			
			if(list.size()>0 ){
				
				String endDate = "null";
				if(rateMaster.getEffectiveEndDate()!=null){
				String splitResult[] = rateMaster.getEffectiveEndDate().split("/");
				endDate = splitResult[2]+"-"+splitResult[1]+"-"+splitResult[0];
				}
				String checkFareMasterSql= "select farechart_master_id from farechart_master where rate_master_id = "+list.get(list.size() - 1).getRateMasterId()+" order by farechart_master_id";
				query = session.createSQLQuery(checkFareMasterSql).addScalar("farechart_master_id", Hibernate.INTEGER);;
				List<Integer> resultList = query.list();
				String newEndDate = null;
				if(rateMaster.getEffectiveEndDate()!=null){
					newEndDate = "'"+rateMaster.getEffectiveEndDate().replace("/", "-")+" 00:00:00'";
				}
				
				if(resultList.size()>0){
			String copyFareMasterSql = "INSERT INTO `farechart_master` (`route_id`, `route_name`, `service_type_id`, `passenger_type_id`, `rate_master_id`, `route_fare_map_id`, `farechart_name`, `effect_start_date`, `effect_end_date`, `opfrom_time`, `opto_time`, `peak_time`, `schedule_service`, `percentage`, `deleted_status`, `created_date`, `updated_date`, `created_by`, `updated_by`, `ceiling_fare`, `nignt_service`, `flexi_fare`, `route_number`, `route_id_`, `sync_updated_date`, `parent_farechart_master`) "
					+ "select `route_id`, `route_name`, `service_type_id`, `passenger_type_id`, "
					+ newId
					+ ", "
					+ "`route_fare_map_id`, `farechart_name`, '"
					+ rateMaster.getEffectiveStartDate().replace("/", "-")
					+ " 00:00:00', "+newEndDate+", `opfrom_time`, "
					+ "`opto_time`, `peak_time`, `schedule_service`, `percentage`, `deleted_status`, now(), "
					+ "null, "
					+ user.getUserId()
					+ ", 0, `ceiling_fare`, `nignt_service`, `flexi_fare`, `route_number`, "
					+ "`route_id_`, null, `parent_farechart_master` from farechart_master where rate_master_id = "
					+ list.get(list.size() - 1).getRateMasterId()+" order by farechart_master_id";
			query = session.createSQLQuery(copyFareMasterSql);
			query.executeUpdate();
			
			
			String farechartListSql = "select farechart_master_id from farechart_master where rate_master_id ="+newId+" order by farechart_master_id";
			query = session.createSQLQuery(farechartListSql).addScalar("farechart_master_id", Hibernate.INTEGER);
			List<Integer> fareChartList = query.list();
			
			
			/*String lastInsertdId = "select LAST_INSERT_ID() as id";
			query = session.createSQLQuery(lastInsertdId).addScalar("id", Hibernate.INTEGER);
			List<Integer> insertedId = query.list();
			int fareMasterID = insertedId.get(0).intValue();*/
			
			Date newDate = sdf.parse(rateMaster.getEffectiveStartDate());
			Calendar cal = Calendar.getInstance();
			cal.setTime(newDate);
			cal.add(Calendar.DATE, -1);
			String updatedEndDt = sdf.format(cal.getTime());
			String updateFareMasterSql = "update farechart_master set effect_end_date='"
					+ updatedEndDt
					+ "' where rate_master_id="
					+ list.get(list.size() - 1).getRateMasterId();
			
			query = session.createSQLQuery(updateFareMasterSql);
			query.executeUpdate();
			
			
			if(fareChartList.size()>0){
				for(int i=0;i<fareChartList.size();i++){
					System.out.println("fareChartList.get(i) ----> "+fareChartList.get(i));
					String updateFareChartSql = "update fare_chart set effective_end_date='"
							+ updatedEndDt.split(" ")[0]
							+ "' where farechart_master_id="
							+ resultList.get(i);
					query = session.createSQLQuery(updateFareChartSql);
					query.executeUpdate();
					String newFareChartEndDate = null;
					if(rateMaster.getEffectiveEndDate()!=null){
						newFareChartEndDate = "'"+rateMaster.getEffectiveEndDate().replace("/", "-")+"'";
					}
					
				String copyFareChartSql = "INSERT INTO `fare_chart` (`farechart_master_id`,`route_id`, `service_type_id`, `passenger_type_id`, `schedule_type_id`, `start_point_id`, `end_point_id`, `start_point_order`, `end_point_order`, `number_of_kms`, `fare_amount`, `toll_fee`, `effective_start_date`, `effective_end_date`, `created_date`, `update_date`, `created_by`, `updated_by`, `sync_updated_date`)" +
						"select "+fareChartList.get(i)+",fc.route_id, fc.service_type_id, fc.passenger_type_id, fc.schedule_type_id, `start_point_id`, `end_point_id`, " +
						"`start_point_order`, `end_point_order`, `number_of_kms`, `fare_amount`, `toll_fee`, " +
						"'"+ rateMaster.getEffectiveStartDate().replace("/", "-")+"', "+newFareChartEndDate+", now(), null, "+user.getUserId()+", 0, null from fare_chart fc inner join farechart_master fm  on fm.farechart_master_id = fc.farechart_master_id inner join rate_master rm on fm.rate_master_id = rm.rate_master_id where rm.rate_master_id = "+list.get(list.size() - 1).getRateMasterId()+" and fm.farechart_master_id = "+resultList.get(i);
				query = session.createSQLQuery(copyFareChartSql);
				query.executeUpdate();
				}
			}
			
			
			}
				
			}
			
		} catch (Exception eAdd) {
			eAdd.printStackTrace();
			tx.rollback();
			System.out.println("eADD=" + eAdd);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		// //Audit
		// Audit audit=new Audit();
		// audit.rateMasterAudit(newId);

		return success;
	}

	public int updateRateMaster(RateMaster rm,String effenddate,int rateId) {
		Common common = new Common();
		Session session = null;
		int success = 0;
		Transaction tx = null;
		try {

			session = HibernateUtil.getSession("");
			session.beginTransaction();
			// System.out.println("rm.getRateMasterId()="+rm.getRateMasterId());
			RateMaster rateMaster = (RateMaster) session.load(RateMaster.class,
					new Integer(rm.getRateMasterId()));

			HttpServletRequest request = ServletActionContext.getRequest();
			User user = (User) request.getSession().getAttribute("user");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());
			String[] effStartDate = rm.getEffectiveStartDate().split("/");
			String[] effEndDate = new String[3];

			// check for duplicate entry based on version no-service type
			String qry = "from RateMaster where versionNumber='"
					+ rm.getVersionNumber() + "' and serviceTypeId="
					+ rm.getServiceTypeId()
					+ " and deletedStatus=0 order by effectiveStartDate";

			Query query = HibernateUtil.getSession("").createQuery(qry);
			tx = session.beginTransaction();
			List<RateMaster> list = query.list();
			// System.out.println("list sz=>>"+list.size());
            int rateMasterCnt = checkRateMaster(rm);
            if(rateMasterCnt >0){
            	success = 2;
				return success;
            }
           // System.out.println("Count ----> "+rateMasterCnt);
			// check for duplicate
			/*if (list.size() > 0) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");

				Date date1 = sdf.parse(rateMaster.getEffectiveStartDate());
				Date date2 = sdf.parse(list.get(list.size() - 1)
						.getEffectiveStartDate());

				if (date1.compareTo(date2) == 0
						&& list.get(list.size() - 1).getRateMasterId() != rateMaster
								.getRateMasterId()) {
					// System.out.println("Date1 is equal to Date2");
					success = 2;
					return success;
				} else {
					System.out.println("list.get(list.size() - 1).getRateMasterId() --->" + list.get(list.size()-1).getRateMasterId()+" "+rateMaster.getRateMasterId());
					if (date2.compareTo(date1) > 0
							&& (!effenddate.equals(common.getDateFromDbToPicker(rateMaster.getEffectiveEndDate())) || rateId!=rateMaster.getRateMasterId())) {
						// System.out.println("Date1 is after Date2");
						success = 3;
						return success;
					}
				}

				// //end date
				// if(list.get(list.size()-1).getEffectiveEndDate()!=null){
				// //end date
				// Date date3=new Date();
				// Date date4=new Date();
				// date3=sdf.parse(rateMaster.getEffectiveStartDate());
				// date4=sdf.parse(list.get(list.size()-1).getEffectiveEndDate());
				//
				// if(date4.compareTo(date1)>0 || date1.compareTo(date4)==0){
				// System.out.println("Date1 is after Date2");
				// success=2;
				// return success;
				// }
				// }

			}
*/
			if (true) {// list.size()<=0){

				if (rm.getEffectiveEndDate() != null
						&& rm.getEffectiveEndDate().length() > 0) {
					effEndDate = rm.getEffectiveEndDate().split("/");
					rateMaster.setEffectiveEndDate(effEndDate[2] + "/"
							+ effEndDate[0] + "/" + effEndDate[1]);
				} else {
					rateMaster.setEffectiveEndDate(null);
				}

				// /////check for duplicate entry based on effective date
				// /////////////////////////////
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sdate = sdf.parse(effStartDate[2] + "-" + effStartDate[0]
						+ "-" + effStartDate[1]);
				Date edate = null;
				if (rm.getEffectiveEndDate().trim().length() > 0) {
					edate = sdf.parse(effEndDate[2] + "-" + effEndDate[0] + "-"
							+ effEndDate[1]);
				}

				// System.out.println("sdate="+sdate+"  edate="+edate);

				// get all start & end date from rate master
				qry = "from RateMaster where deletedStatus=0 and status='ACTIVE'";

				query = HibernateUtil.getSession("").createQuery(qry);
				List<RateMaster> listDates = query.list();
				// System.out.println("listDates sz=>>"+listDates.size());

				// date comparison
				for (int cnt = 0; cnt < listDates.size(); cnt++) {

					try {
						// System.out.println("dates="+listDates.get(cnt).getEffectiveStartDate()+"::"+listDates.get(cnt).getEffectiveEndDate());

						Date dbSdate = sdf.parse(listDates.get(cnt)
								.getEffectiveStartDate());
						Date dbEdate = sdf.parse(listDates.get(cnt)
								.getEffectiveEndDate());

						if (sdate.compareTo(dbSdate) > 0
								&& sdate.compareTo(dbEdate) < 0) {
							// System.out.println(dbSdate+"<>"+dbEdate+"="+sdate
							// +" is exist");
							if (listDates.get(cnt).getRateMasterId() != rm
									.getRateMasterId()) {
								// success=2;
								// return success;
							}
						}

						if (edate.compareTo(dbSdate) > 0
								&& edate.compareTo(dbEdate) < 0) {
							// System.out.println(dbSdate+"<>"+dbEdate+"="+edate
							// +" is exist");
							if (listDates.get(cnt).getRateMasterId() != rm
									.getRateMasterId()) {
								// success=2;
								// return success;
							}
						}
					} catch (Exception e) {
					}
				}

				// ////////////////////////////////////////////////////////////////////////////////////

				rateMaster.setVersionNumber(rm.getVersionNumber());
				rateMaster.setServiceTypeId(rm.getServiceTypeId());
				String serviceTypeName = getServiceNameById(rateMaster
						.getServiceTypeId());
				// System.out.println("serviceTypeName="+rateMaster.getServiceTypeId()+"-"+serviceTypeName);
				// session= HibernateUtil.getSession("");
				// session.beginTransaction();
				rateMaster.setVersionNoServiceType(rm.getVersionNumber() + "-"
						+ serviceTypeName);
				rateMaster.setEffectiveStartDate(effStartDate[2] + "/"
						+ effStartDate[0] + "/" + effStartDate[1]);
				// rateMaster.setEffectiveEndDate(effEndDate[2]+"/"+effEndDate[0]+"/"+effEndDate[1]);
				rateMaster.setUpdatedDate(date);
				rateMaster.setUpdatedBy(user.getUserId());
				rateMaster.setStatus(rm.getStatus());
				success = 1;// update success
			} else {
				success = 2; // duplicate entry
			}
			session.getTransaction().commit();
		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
		} finally {

			session.close();
		}

		// //Audit
		// Audit audit=new Audit();
		// audit.rateMasterAudit(rm.getRateMasterId());

		return success;
	}

	public List<RateMaster> getAllData() {
		String qry = "from RateMaster where deletedStatus=0";
		Query query = HibernateUtil.getSession("").createQuery(qry);
		List<RateMaster> list = query.list();
		// System.out.println("list size==>>"+list.size());

		HibernateUtil.closeSession();
		return list;
	}

	public List getServiceId() {
		List list = new ArrayList();
		// System.out.println("inside get id");
		String qry = "select service_type_id from service_type where deleted_status =0 and status='Active' order by service_type_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("service_type_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	public List getServiceName() {
		List list = new ArrayList();
		// System.out.println("inside get id");
		String qry = "select service_type_name from service_type where deleted_status =0 and status='Active' order by service_type_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("service_type_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	public String getServiceNameById(int id) {

		// System.out.println("inside get id");
		String qry = "select service_type_name from service_type where deleted_status =0 and service_type_id="
				+ id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		String name = "";
		if (aliasToValueMapList.size() > 0) {

			Map<String, Object> rs = aliasToValueMapList.get(0);
			name = (rs.get("service_type_name").toString());
		}
		// HibernateUtil.closeSession();
		return name;
	}

	public RateMaster getDataById(int rateId) {

		Session session = null;
		RateMaster rateMaster = null;
		try {
			session = HibernateUtil.getSession("");

			rateMaster = (RateMaster) session.load(RateMaster.class,
					new Integer(rateId));

			String[] effStartDate = rateMaster.getEffectiveStartDate().split(
					"-");
			rateMaster.setEffectiveStartDate(effStartDate[2] + "/"
					+ effStartDate[1] + "/" + effStartDate[0]);
			String[] effEndDate = rateMaster.getEffectiveEndDate().split("-");
			rateMaster.setEffectiveEndDate(effEndDate[2] + "/" + effEndDate[1]
					+ "/" + effEndDate[0]);
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return rateMaster;
	}

	public String deleteRateMaster(int rateId) {
		Session session = null;
		String status = "error";
		try {

			session = HibernateUtil.getSession("");

			// DependencyChecker dc=new DependencyChecker();
			// status=dc.checkDependentEntities(session, "rate_master", rateId);

			// if(status.trim().equalsIgnoreCase("")){
			session.beginTransaction();
			// System.out.println("rm.getRateMasterId()="+rateId);
			RateMaster rateMaster = (RateMaster) session.load(RateMaster.class,
					new Integer(rateId));

			HttpServletRequest request = ServletActionContext.getRequest();
			User user = (User) request.getSession().getAttribute("user");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());

			rateMaster.setUpdatedDate(date);
			rateMaster.setUpdatedBy(user.getUserId());
			rateMaster.setDeletedStatus(1);
			// rateMaster.setStatus("INACTIVE");

			updateRateMasterDetailById(session, rateId);

			status = "success:" + rateMaster.getVersionNumber();
			session.getTransaction().commit();
			// }
		} catch (Exception ex) {
			status = "error:";
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return status;
	}

	// rate master detail
	public int addRateMasterDetail(RateMaster rateM, int copy, int rateId) {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		RateMaster rateMaster = rateM;
		int success = 0;
		Session session = HibernateUtil.getSession("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());

		// decide to add or copy
		if (copy == 0 && rateId == 0) {
			for (int i = 1; i <= 70; i++) {
				// System.out.println("iiii="+i);
				RateMasterDetail rateMasterDetail = new RateMasterDetail();
				rateMasterDetail.setRateMasterId(rateM.getRateMasterId());
				rateMasterDetail.setServiceTypeId(rateM.getServiceTypeId());
				rateMasterDetail.setStageNo(i);
				rateMasterDetail.setAdult(0);
				rateMasterDetail.setChildren(0);
				rateMasterDetail.setSeniorCitizen(0);
				rateMasterDetail.setLuggage(0);
				rateMasterDetail.setCreatedDate(date);
				rateMasterDetail.setDeletedStatus(0);
				rateMasterDetail.setUpdatedBy(0);
				rateMasterDetail.setCreatedBy(user.getUserId());
				session.save(rateMasterDetail);
				success = 1;
				// System.out.println("save success iiii="+i);
			}
		} else {
			System.out.println("rateid -----> "+rateId);
			String qry = "from RateMasterDetail where deletedStatus=0 and rateMasterId="
					+ rateId;
			// select rateMasterDetailId,stageNo,adult,children,seniorCitizen
			Query query = HibernateUtil.getSession("").createQuery(qry);
			List<RateMasterDetail> list = query.list();
			// System.out.println("rmd list size==>>"+list.size());
			for (int i = 1; i <= 70; i++) {
				// System.out.println("iiii="+i);
				RateMasterDetail rateMasterDetail = new RateMasterDetail();
				rateMasterDetail.setRateMasterId(rateM.getRateMasterId());
				rateMasterDetail.setServiceTypeId(rateM.getServiceTypeId());
				rateMasterDetail.setStageNo(i);
				rateMasterDetail.setAdult(list.get(i - 1).getAdult());
				rateMasterDetail.setChildren(list.get(i - 1).getChildren());
				rateMasterDetail.setSeniorCitizen(list.get(i - 1)
						.getSeniorCitizen());
				rateMasterDetail.setLuggage(list.get(i - 1).getLuggage());
				// System.out.println("fares:"+rateMasterDetail.getAdult()+","+rateMasterDetail.getChildren()+","+rateMasterDetail.getSeniorCitizen());
				rateMasterDetail.setCreatedDate(date);
				rateMasterDetail.setDeletedStatus(0);
				rateMasterDetail.setUpdatedBy(0);
				rateMasterDetail.setCreatedBy(user.getUserId());
				session.save(rateMasterDetail);
				success = 1;
				// System.out.println("save success iiii="+i);
			}
		}
		// HibernateUtil.closeSession();
		return success;
	}

	public int updateRateMasterDetail(RateMasterDetail rm, int id) {
		Session session = null;
		int updated = 0;

		try {
			session = HibernateUtil.getSession("");
			session.beginTransaction();
			String queryString = "update RateMasterDetail set adult="
					+ rm.getAdult() + ",children=" + rm.getChildren()
					+ ",seniorCitizen=" + rm.getSeniorCitizen()+ ",luggage=" + rm.getLuggage()+ ",happy_hour1=" + rm.getHappyhour1()+ ",happy_hour2=" + rm.getHappyhour2()
					+ " where rateMasterId=" + id + " and stageNo="
					+ rm.getStageNo();
			Query query = session.createQuery(queryString);
			updated = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return updated;
	}

	public int copyRateMaster(int rateId, RateMaster rateMaster) {
		Session session = null;
		Transaction tx = null;
		int updated = 0;
		try {
			session = HibernateUtil.getSession("");
			tx = session.beginTransaction();

			// RateMaster
			// rateMaster=;//(RateMaster)session.load(RateMaster.class,new
			// Integer(rateId));

			HttpServletRequest request = ServletActionContext.getRequest();
			User user = (User) request.getSession().getAttribute("user");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());

			// copy rate master
			RateMaster rateMasterNew = rateMaster;
			// rateMasterNew.setServiceTypeId(rateMaster.getServiceTypeId());
			// rateMasterNew.setEffectiveStartDate(rateMaster.getEffectiveStartDate());
			// rateMasterNew.setEffectiveEndDate(rateMaster.getEffectiveEndDate());
			// rateMasterNew.setStatus(rateMaster.getStatus());

			// int oldId=rateMaster.getRateMasterId();
			// System.out.println("oldId="+oldId);
			if ((updated = add(rateMasterNew, 1)) == 1) {
				int newId = rateMasterNew.getRateMasterId();
				// System.out.println("newId="+newId);
				// if(oldId!=newId){
				session = HibernateUtil.getSession("");
				session.beginTransaction();
				rateMaster = (RateMaster) session.load(RateMaster.class,
						new Integer(newId));
				// copy rate master detail
				addRateMasterDetail(rateMaster, 1, rateId);

				updated = 1;
				// }
			} else {
				updated = 2;
			}
			session.getTransaction().commit();
		} catch (Exception ex) {
			updated = 0;
			tx.rollback();
			ex.printStackTrace();
		} finally {

			if (session != null && session.isOpen())
				session.close();
		}

		return updated;
	}

	public List<RateMasterDetail> getAllDetailData(int rateId) {
		String qry = "from RateMasterDetail where deletedStatus=0 and rateMasterId="
				+ rateId;
		// select rateMasterDetailId,stageNo,adult,children,seniorCitizen
		Query query = HibernateUtil.getSession("").createQuery(qry);
		List<RateMasterDetail> list = query.list();
		// System.out.println("rmd list size==>>"+list.size());

		HibernateUtil.closeSession();
		return list;
	}

	// pagination,sorting
	public int getTotalRecords(HttpServletRequest request, String col,
			String dir, String viewdeletedrecord) {
		// Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		// Query query =
		// session.createSQLQuery("select count(*) as count from rate_master where deleted_status=0").addScalar("count",
		// Hibernate.INTEGER);
		// List<Integer> list = query.list();
		// int cnt = list.get(0);
		// return cnt;

		Session session = null;
		int cnt = 0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			// Criteria criteria = session.createCriteria(RateMaster.class);
			// criteria.add(Restrictions.ne("deletedStatus", 1));
			Criteria criteria;
			if (viewdeletedrecord.equalsIgnoreCase("Y")) {
				criteria = session.createCriteria(RateMaster.class);
			} else {
				criteria = session.createCriteria(RateMaster.class);
				criteria.add(Restrictions.ne("deletedStatus", 1));
			}

			// System.out.println("sSearch------->"+request.getParameter("sSearch"));

			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}

			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("versionNoServiceType",
						"%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,
						MatchMode.START));
				criteria.add(conditionGroup);
			}

			// System.out.println("My Criteria");
			List<RateMaster> list = criteria.list();
			cnt = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,
			String col, String dir, String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Session session = null;
		try {
			int totalAfterFilter = total;

			session = HibernateUtil.getSession("hibernate.cfg.xml");

			Criteria criteria;
			if (viewdeletedrecord.equalsIgnoreCase("Y")) {
				criteria = session.createCriteria(RateMaster.class);
			} else {
				criteria = session.createCriteria(RateMaster.class);
				criteria.add(Restrictions.ne("deletedStatus", 1));
			}
			// Criteria criteria = session.createCriteria(RateMaster.class);
			// criteria.add(Restrictions.ne("deletedStatus", 1));
			// System.out.println("sSearch------->"+request.getParameter("sSearch"));

			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}

			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("versionNoServiceType",
						"%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,
						MatchMode.START));
				criteria.add(conditionGroup);
			}
			// System.out.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"+Integer.parseInt(request.getParameter("iDisplayStart"))+":::"+Integer.parseInt(request.getParameter("iDisplayLength")));
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			// System.out.println("My Criteria");
			List<RateMaster> list = criteria.list();
			JSONArray array = new JSONArray();

			// System.out.println("list szz="+list.size());
			Common common = new Common();
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				// System.out.println("json="+i+" "+list.get(i).getRateMasterId()+","+list.get(i).getVersionNumber()+","+list.get(i).getEffectiveStartDate()+","+list.get(i).getEffectiveEndDate()+","+list.get(i).getStatus());
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getRateMasterId()
						+ "' value='"
						+ list.get(i).getRateMasterId() + "'/>");
				ja.add(list.get(i).getRateMasterId());
				ja.add(list.get(i).getVersionNoServiceType());

				ja.add(list.get(i).getEffectiveStartDate() != null ? common
						.getDateFromDateTime_(list.get(i)
								.getEffectiveStartDate().toString()) : "");
				ja.add(list.get(i).getEffectiveEndDate() != null ? common
						.getDateFromDateTime_(list.get(i).getEffectiveEndDate()
								.toString()) : "");

				// ja.add(list.get(i).getEffectiveStartDate());

				// ja.add(list.get(i).getEffectiveEndDate());
				ja.add(list.get(i).getStatus());
				if (viewdeletedrecord.equalsIgnoreCase("Y")) {
					int deletedstatus = list.get(i).getDeletedStatus();

					if (deletedstatus == 1) {
						ja.add("<input type='hidden' value='N' id='isRocordEligible"
								+ list.get(i).getRateMasterId()
								+ "'/>Deleted Record");
						// ja.add(" ");
					} else {
						// ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"
								+ list.get(i).getRateMasterId()
								+ "'/>Record in Database");
					}

					// ja.add(rs.get("vendor_name").toString());
				} else {

				}
				array.add(ja);
				// System.out.println("Array----->"+array);
			}

			totalAfterFilter = total;// getTotalRecords(request,col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

	public int updateRateMasterDetailById(Session session, int id) {
		int updated = 0;

		String queryString = "update RateMasterDetail set deletedStatus=1 where rateMasterId="
				+ id;
		Query query = session.createQuery(queryString);
		updated = query.executeUpdate();

		return updated;
	}
	
	public int checkRateMaster(RateMaster rm){
		Common common = new Common();
		String [] splitResult = rm.getEffectiveEndDate().split("/");
		String resultDate = splitResult[2]+"-"+splitResult[0]+"-"+splitResult[1];
		int cnt=0;
		String sql = "SELECT count(*)  as count FROM `rate_master` " +
				"WHERE `version_number` = '"+rm.getVersionNumber()+"' AND `service_type_id` = '"+rm.getServiceTypeId()+"' AND `deleted_status` = '0' " +
				"and '"+resultDate+"' between effective_start_date " +
				"and if(effective_end_date IS NULL or effective_end_date = '0000-00-00 00:00:00', effective_start_date, effective_end_date) " +
				"and rate_master_id != "+rm.getRateMasterId();
		/*String sql = "SELECT count(*) as count FROM `rate_master` WHERE `version_number` = '"+rm.getVersionNumber()+"' AND `service_type_id` = '"+rm.getServiceTypeId()+"' AND `deleted_status` = '0' " +
				" and '"+common.getDateTimeFromPicker2(rm.getEffectiveEndDate())+"' between effective_start_date and if(effective_end_date IS NULL or effective_end_date = '0000-00-00 00:00:00', effective_start_date, effective_end_date) ";*/
		/*String sql = "select count(*) as count from form_four where form_four_name = "+rm.getVersionNumber()+" and schedule_number_id = "+rm.get+ 
				" and '"+dateString+"' between effective_start_date and if(effective_end_date IS NULL or effective_end_date = '0000-00-00 00:00:00', effective_start_date, effective_end_date) and deleted_status =0 and current_status IN ('ACTIVE','Partial')";*/
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			Query query = session.createSQLQuery(sql).addScalar("count", Hibernate.INTEGER);
			cnt = (Integer) query.uniqueResult();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		finally {
			if(session!=null){  }
		}
			return cnt;
	}
	
	
	
}
