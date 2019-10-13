package com.trimax.its.vts.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.model.INFO_MODE;
import com.trimax.its.vts.model.VehicleAlertConfig;
import com.trimax.its.vts.model.VtsAlertMaster;

public class VtsAlertMasterConfigDao {

	public JSONObject showVehicleAlertConfigData(int total,
	   		HttpServletRequest request, String col, String dir) {
		System.out
				.println("\n \t IN JSONObject getTotalVehicleAlertConfigData.........");

		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			Session alertconfigsession = new HibernateUtilVtu().getSession("");
			String sql = "select ID,PACKET_CODE,MISC_BYTES,ALERT_SHORT_CODE,ALERT_DESC,INFO_MODE,DASHBOARD_VIEW from vts_alert_master";

			System.out
					.println("\n \t JSONObject getTotalVehicleAlertConfigData 1111..............");
			int srch = 0;
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {

				System.out.println("search_term---------" + search_term);

				sql += " and ( ID like '%" + search_term + "%'";
				sql += " OR PACKET_CODE like '%" + search_term + "%'";
				sql += " OR MISC_BYTES like '%" + search_term + "%'";
				sql += " OR ALERT_SHORT_CODE like '%" + search_term + "%'";
				sql += " OR ALERT_DESC like '%" + search_term + "%'";
				sql += " OR INFO_MODE like '%" + search_term + "%'";
				sql += " OR DASHBOARD_VIEW like '%" + search_term + "%')";

			}

			// sql+=" ORDER BY ID,PACKET_CODE ASC";

			System.out.println("SORTING DIRECTION IS..." + dir + "column is..."
					+ col);
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			System.out.println("sql is.." + sql);
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("ID", Hibernate.STRING)
					.addScalar("PACKET_CODE", Hibernate.STRING)
					.addScalar("MISC_BYTES", Hibernate.STRING)
					.addScalar("ALERT_SHORT_CODE", Hibernate.STRING)
					.addScalar("ALERT_DESC", Hibernate.STRING)
					.addScalar("INFO_MODE", Hibernate.STRING)
					.addScalar("DASHBOARD_VIEW", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2 = null;
			aliasToValueMapList2 = query.list();

			JSONArray arraytickstock = new JSONArray();
			// System.out.println("List size----->" + list.size() +
			// list.get(0).getValue()+"\t"
			// + request.getParameter("iDisplayStart"));
			for (int i = 0; i < aliasToValueMapList2.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList2.get(i);
				JSONArray jatickstock = new JSONArray();

				jatickstock
						.add("<input type='checkbox' class='group-checkable checkbox1' name='chkbox' data-set='#sample_2 .checkboxes' id='"
								+ rs.get("ID")
								+ "' value='"
								+ rs.get("ID")
								+ "'/>");
				jatickstock.add(rs.get("ID"));
				jatickstock.add(rs.get("PACKET_CODE"));
				jatickstock.add(rs.get("MISC_BYTES"));
				jatickstock.add(rs.get("ALERT_SHORT_CODE"));
				jatickstock.add(rs.get("ALERT_DESC"));
				jatickstock.add(rs.get("INFO_MODE"));
				jatickstock.add(rs.get("DASHBOARD_VIEW"));
				arraytickstock.add(jatickstock);

			}

			result.put("aaData", arraytickstock);
			String search_term2 = request.getParameter("sSearch");

			String search_term3 = request.getParameter("sSearch").trim();
			int cnt = getTotalVehicleMasterConfigRecords(request, col, dir);

			result.put("iTotalRecords", cnt);

			result.put("iTotalDisplayRecords", cnt);

			System.out
					.println("****ticket array data...---->" + arraytickstock);
			System.out.println("Ticket INV REsult ------>"
					+ request.getParameter("iDisplayStart"));

			System.out.println("REsult ------>"
					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	private int getTotalVehicleMasterConfigRecords(HttpServletRequest request,
			String col, String dir) {
		Session alertconfigsession = null;
		int cnt = 0;

		try {
			// HibernateUtilVtu h = new HibernateUtilVtu();
			String sql = "select ID,PACKET_CODE,MISC_BYTES,ALERT_SHORT_CODE,ALERT_DESC,INFO_MODE,DASHBOARD_VIEW from vts_alert_master";

			System.out
					.println("\n \t JSONObject getTotalVehicleAlertConfigData 1111..............");
			int srch = 0;
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {

				System.out.println("search_term---------" + search_term);

				sql += " and ( ID like '%" + search_term + "%'";
				sql += " OR PACKET_CODE like '%" + search_term + "%'";
				sql += " OR MISC_BYTES like '%" + search_term + "%'";
				sql += " OR ALERT_SHORT_CODE like '%" + search_term + "%'";
				sql += " OR ALERT_DESC like '%" + search_term + "%'";
				sql += " OR INFO_MODE like '%" + search_term + "%'";
				sql += " OR DASHBOARD_VIEW like '%" + search_term + "%')";

			}

			// sql+=" ORDER BY ID,PACKET_CODE ASC";

			System.out.println("SORTING DIRECTION IS..." + dir + "column is..."
					+ col);
			System.out
					.println("\n \t JSONObject getTotalVehicleDepotinoutRecords 1111..............");
 
			System.out.println("sql----" + sql);
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("ID", Hibernate.STRING)
					.addScalar("PACKET_CODE", Hibernate.STRING)
					.addScalar("MISC_BYTES", Hibernate.STRING)
					.addScalar("ALERT_SHORT_CODE", Hibernate.STRING)
					.addScalar("ALERT_DESC", Hibernate.STRING)
					.addScalar("INFO_MODE", Hibernate.STRING)
					.addScalar("DASHBOARD_VIEW", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			cnt = aliasToValueMapList.size();
			System.out.println("inside getTotalVehicleDepotinoutRecords..."
					+ cnt);

		} catch (Exception e) {
           e.printStackTrace();
			/*System.out
					.println("\n \t Inside WaybillDAO Class with Exception in getTotalVehicleDepotinoutRecords Method as : "
							+ e.getMessage());
			return cnt;*/
		} finally {
			if (alertconfigsession != null) {
				alertconfigsession.close();
			}
		}
		return cnt;

	}

	public int saveAlertConfigDetails(VtsAlertMaster vtsalertmaster) {
		Session session = null;
		int i = 0;
		try {
			session = new HibernateUtilVtu().getSession("");

			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			vtsalertmaster.setCreated_date(new Date());

			vtsalertmaster.setCreated_by(usrsessionid);
			INFO_MODE color = INFO_MODE.EMAIL;  
			String name= color.name(); 
			
			vtsalertmaster.setInfomode(name);
			vtsalertmaster.setRecord_status("Y");
			i = (Integer) session.save(vtsalertmaster);
			session.getTransaction().commit();
			System.out.println("end of commit");
		} catch (Exception e) {
			e.printStackTrace();

			// session.getTransaction().rollback();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return i; 
 
	}

	public VtsAlertMaster getVehicleAlertConfigDetails(int alertconfigmastid) {
		Session session;
		VtsAlertMaster vehiclealertmastconfig = null;
		try {
			session = new HibernateUtilVtu().getSession("");
			vehiclealertmastconfig = (VtsAlertMaster) session.get(
					VtsAlertMaster.class, new Integer(alertconfigmastid));
			System.out.println("--------->>>"
					+ vehiclealertmastconfig.getMisc_bytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vehiclealertmastconfig;
	}

	public void saveEditAlertConfigMasterDetails(String requestType, int id,
			VtsAlertMaster vtsalertmaster) {
	
			Session session = null;
			try {
				session = new HibernateUtilVtu().getSession("");
				session.beginTransaction();
	 			
				
				VtsAlertMaster vtsalert_Master= (VtsAlertMaster)session.get(VtsAlertMaster.class, id);
				 System.out.println("hi in dao" +vtsalert_Master.getAlert_desc()); 
				 HttpServletRequest request=ServletActionContext.getRequest(); 
				 HttpSession sess=request.getSession(); 
				 
				 
				 vtsalert_Master.setPacket_code(vtsalertmaster.getPacket_code());
				 vtsalert_Master.setMisc_bytes(vtsalertmaster.getMisc_bytes());
				 vtsalert_Master.setAlert_short_code(vtsalertmaster.getAlert_short_code()); 
				 
				 vtsalert_Master.setAlert_desc(vtsalertmaster.getAlert_desc());
				 vtsalert_Master.setDashboard_view(vtsalertmaster.getDashboard_view());
				 
				 int usrsessionid =(Integer)request.getSession().getAttribute("userid");
				 vtsalert_Master.setUpdated_by(usrsessionid);
				 vtsalert_Master.setUpdated_date(new Date());
				 
			 	 session.update(vtsalert_Master);
				 
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
 
	

	public void deleteVehicleAlertConfigMaster(int id) {
		Session session = null;

		// System.out.println("=====Route id" + role.getRole_id());
		try {
			//
			session = new HibernateUtilVtu().getSession("");
			session.beginTransaction();
			VtsAlertMaster vehiclealertmastconfig = (VtsAlertMaster) session.get(VtsAlertMaster.class, id);

			vehiclealertmastconfig.setRecord_status("N");
			HttpServletRequest request = ServletActionContext.getRequest();
			vehiclealertmastconfig.setUpdated_by(Integer.parseInt(request
					.getSession().getAttribute("userid").toString()));
			vehiclealertmastconfig.setUpdated_date(new java.util.Date());

			session.update(vehiclealertmastconfig);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {

			if (session != null) {
				session.close();

			}
		}
	}

	public int checkDuplicateInsertAlertConfig(VtsAlertMaster vtsalertmaster) {
		Session session = null;
		int duplicatecount = 0;
		try {
			session = new HibernateUtilVtu().getSession("");

			session.beginTransaction();

			String sql = "select IFNULL(count(*),0) from vts_alert_master where "
					+ " PACKET_CODE='"
					+ vtsalertmaster.getPacket_code()
					+ "' AND MISC_BYTES='"
					+ vtsalertmaster.getMisc_bytes()
					+ "' AND ALERT_SHORT_CODE='"
					+ vtsalertmaster.getAlert_short_code()
                    + "' AND ALERT_DESC='"
					+ vtsalertmaster.getAlert_desc()
					+ "' AND "
					+ " RECORD_STATUS='Y'";
			System.out.println("SQL IS..." + sql);

			duplicatecount = Integer.parseInt(session.createSQLQuery(sql)
					.uniqueResult().toString());
			System.out.println("ticket type priority.." + duplicatecount);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}

			return duplicatecount;
		}
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForVtsMaster(int no, HttpServletRequest request) {
		// TODO Auto-generated method stub
				Session session = null;
				JSONObject result = new JSONObject();
				try {
					
					boolean flag = false;
					String trip_status="";
					HibernateUtilVtu h = new HibernateUtilVtu();
					session = h.getSession("");
					String sql = "select ID,PACKET_CODE,MISC_BYTES,ALERT_SHORT_CODE,ALERT_DESC,INFO_MODE,DASHBOARD_VIEW from vts_alert_master where RECORD_STATUS='Y'";
					Query query = session.createSQLQuery(sql).addScalar("ID", Hibernate.STRING)
							.addScalar("PACKET_CODE", Hibernate.STRING)
							.addScalar("MISC_BYTES", Hibernate.STRING)
							.addScalar("ALERT_SHORT_CODE", Hibernate.STRING)
							.addScalar("ALERT_DESC", Hibernate.STRING)
							.addScalar("INFO_MODE", Hibernate.STRING)
							.addScalar("DASHBOARD_VIEW", Hibernate.STRING);;
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						
						Map<String, Object> rs = aliasToValueMapList.get(i);
						
						
						JSONArray jatickstock = new JSONArray();

						jatickstock
								.add("<input type='checkbox' class='group-checkable checkbox1' name='chkbox' data-set='#sample_2 .checkboxes' id='"
										+ rs.get("ID")
										+ "' value='"
										+ rs.get("ID")
										+ "'/>");
						jatickstock.add(rs.get("ID"));
						jatickstock.add(rs.get("PACKET_CODE"));
						jatickstock.add(rs.get("MISC_BYTES"));
						jatickstock.add(rs.get("ALERT_SHORT_CODE"));
						jatickstock.add(rs.get("ALERT_DESC"));
						jatickstock.add(rs.get("INFO_MODE"));
						jatickstock.add(rs.get("DASHBOARD_VIEW"));
						array.add(jatickstock);
					}
					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();
					
				} finally {
					session.close();
				}
				return result;
	}

	public int checkDuplicateUpdateAlertConfig(VtsAlertMaster vtsalertmaster) {
		Session session = null;
		int duplicatecount = 0;
		try {
			session = new HibernateUtilVtu().getSession("");

			session.beginTransaction();
           
			String sql = "select IFNULL(count(*),0) from vts_alert_master where ID not in('"+vtsalertmaster.getVts_id()+"') " +
					     " AND  PACKET_CODE='"+vtsalertmaster.getPacket_code()+
					     "' AND MISC_BYTES='"+vtsalertmaster.getMisc_bytes()+"' " +
					      " and RECORD_STATUS='Y'";
			System.out.println("SQL IS..." + sql);

			duplicatecount = Integer.parseInt(session.createSQLQuery(sql)
					.uniqueResult().toString());
			System.out.println("ticket type priority.." + duplicatecount);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}

			return duplicatecount;
		}
	}
	}

	
	
