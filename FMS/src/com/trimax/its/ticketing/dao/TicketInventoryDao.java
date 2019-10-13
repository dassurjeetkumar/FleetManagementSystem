package com.trimax.its.ticketing.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.doa.ticketing.model.DenominationType;
import com.trimax.doa.ticketing.model.TicketInventory;
import com.trimax.doa.ticketing.model.TicketInventoryIssue;
import com.trimax.doa.ticketing.model.TicketInventoryMaster;
import com.trimax.doa.ticketing.model.TicketInvoiceDetails;
import com.trimax.doa.ticketing.model.TicketInvoiceMaster;
import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;

import com.trimax.its.utility.model.Page_Master;
import com.trimax.its.vehicle.model.Complaint;
import com.trimax.its.vehicle.model.OrganisationChart;

import freemarker.template.SimpleDate;

public class TicketInventoryDao {

	static int subtotmanualvalue;
	static int subtotmanualticket;
	static int subtotmanualbook;
	
	static int subtotpassvalue;
	static int subtotpassticket;
	static int subtotpassbook;
	
	static int subtotluggagevalue;
	static int subtotluggageticket;
	static int subtotluggagebook;
	Common cm=new Common();
	Date date = new Date();
	String todaysDate = (new SimpleDateFormat("YYYY-MM-DD").format(date));
	public Session getSession(){
		//SessionFactory factory=null;
		Session ses=null;
		try{
	         //factory = new Configuration().configure().buildSessionFactory();
	       ses=HibernateUtil.getSession("hibernate.cfg.xml");
		}catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
		}
		return ses;
	}
	@SuppressWarnings("finally")
	public String setCurrentOrgName(){
		String orgName = "";
		Session session = null;
		Common common= new Common();
		try{
			String orgId = (String) ServletActionContext.getRequest().getSession().getAttribute("orgchartid");
			session = HibernateUtilTick.getSession("");
			String query = "select org_name from org_chart where org_chart_id='"+orgId+"'";
			orgName = common.getDBResultStr(session, query, "org_name");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return orgName;
		}
	}
	@SuppressWarnings("finally")
	public int getStockeEntryRefId(){
		int id = 0;
		Session session = null;
		try{
			session = HibernateUtilTick.getSession("");
			session.beginTransaction();
			String sqlQuery = "INSERT INTO `ticket_inventory_temp_ref`()VALUES ();";
			Query queryObject = session.createSQLQuery(sqlQuery);
			queryObject.executeUpdate();
			id = cm.getDBResultInt(session, "select last_insert_id() id from  `ticket_inventory_temp_ref`", "id"); 
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session != null){
				session.close();
			}
			return id;
		}
	}
	@SuppressWarnings({ "unchecked", "finally" })
	public JSONObject getData(int total, HttpServletRequest request,String col, String dir) {
		JSONObject resulttick = new JSONObject();
		Session sessiontick = null;
		String currentStatus="";
		int organizationId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
		int organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
		String sql1="";
		try {
			int totalAfterFilter = total;
			sessiontick = HibernateUtilTick.getSession("hibernate.cfg.xml");
			
			if(organizationtype==1){
				
				sql1 = "select  dy.denomination_type_manual,a.ticket_type_manual_id,a.denomination_key, a.opening_number, a.closing_number,a.number_of_tickets,"
					+ " a.number_of_books, a.value,ot.org_type,oc.org_name,a.priority,a.created_date from (SELECT denomination_type_manual_id, denomination_key, "
					+ " min(opening_number) as opening_number, max(closing_number) as closing_number,"
					+ " sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,"
					+ " unit_type,unit_name,ticket_type_manual_id,priority,tid.created_date  FROM ticket_inventory_details tid "
					+ " WHERE tid.status = 'ACTIVE' and tid.current_status='New' and tid.unit_name='"+organizationId +"'"
					+ " group by tid.denomination_key, tid.denomination_type_manual_id,tid.ticket_inventory_mst_id  order by tid.created_date) a "
					+ " inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id"
					+"  inner join  org_type ot on a.unit_type=ot.org_type_id" 
					+"  inner join org_chart oc on a.unit_name=oc.org_chart_id"
					+ " and a.ticket_type_manual_id=1  ";
				          
			}else if(organizationtype==2){
				
			    currentStatus="Issued";
				sql1="select dy.denomination_type_manual,a.pass_day,a.ticket_type_manual_id, a.denomination_key,a.priority, min(a.opening_number) as opening_number,  " +
					" max(a.closing_number) as closing_number," +
					" sum(a.number_of_tickets) as number_of_tickets, sum(a.number_of_books) as number_of_books , " +
					" sum(a.value) as value, a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ," +
					" tim.current_status as current_status, a.priority,tim.created_date from ticket_invoice_master tim inner join ticket_invoice_details tid " +
					" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
					" inner join ticket_inventory_details a on a.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id" +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id in=1" +
					" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id =  "+organizationId+" " +
					" AND tim.status = 'ACTIVE' and tim.current_status='"+currentStatus+"'  "  +
					" group by ti.denomination_key, a.denomination_type_manual_id   ";   
				 
				 
			}else if(organizationtype==3){
				 
			     currentStatus="Issued";
				 sql1="select dy.denomination_type_manual,a.pass_day,a.ticket_type_manual_id, a.denomination_key,a.priority, min(a.opening_number) as opening_number,  " +
					" max(a.closing_number) as closing_number," +
					" sum(a.number_of_tickets) as number_of_tickets, sum(a.number_of_books) as number_of_books , " +
					" sum(a.value) as value, a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ," +
					" tim.current_status as current_status, a.priority,tim.created_date from ticket_invoice_master tim inner join ticket_invoice_details tid " +
					" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
					" inner join ticket_inventory_details a on a.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id" +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=1" +
					" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id =  "+organizationId+" " +
					" AND tim.status = 'ACTIVE' and tim.current_status='"+currentStatus+"'  "  +
					" group by a.denomination_key, a.denomination_type_manual_id  ";
			 }
			 
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
				sql1 += " where dy.denomination_type_manual='"+ search_term + "'";
				sql1 += " OR a.denomination_key like '%" + search_term + "%'";
				sql1 += " OR a.opening_number like '%" + search_term + "%'";
				sql1 += " OR a.closing_number like '%" + search_term + "%'";
				sql1 += " OR a.number_of_tickets='" + search_term + "'";
				sql1 += " OR a.value='" + search_term + "'";
				sql1 += " OR a.number_of_books='" + search_term + "'";
			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql1 += " order by " + col + " asc";
				} else {
					sql1 += " order by " + col + " desc";
				}
			}else{
				sql1 += " order by ";
				if(organizationtype==1){
					sql1 += " CAST(dy.denomination_type_manual AS UNSIGNED),priority";
				}else if(organizationtype==2){
					sql1+="  CAST(dy.denomination_type_manual AS UNSIGNED),priority";
				}else if(organizationtype==3){
					sql1+="  CAST(dy.denomination_type_manual AS UNSIGNED),priority";
				}
			}
			
			
			Query query = sessiontick.createSQLQuery(sql1)
					.addScalar("denomination_type_manual", Hibernate.STRING).addScalar("ticket_type_manual_id",Hibernate.STRING)
					.addScalar("denomination_key", Hibernate.STRING).addScalar("value",Hibernate.STRING)
					.addScalar("opening_number", Hibernate.STRING).addScalar("closing_number", Hibernate.STRING)
					.addScalar("number_of_tickets", Hibernate.STRING).addScalar("number_of_books", Hibernate.STRING)
					.addScalar("created_date", Hibernate.STRING).addScalar("priority", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2=null;
			aliasToValueMapList2 = query.list();
			JSONArray arraytickstock = new JSONArray();
			String ticketTable = "<div id='normalTiocketTable'><table>";
			ticketTable += "<tr><th>Denom</th><th>Created date</th><th>Denom Key</th><th>Opening Number</th><th>Closing Number</th>" +
					"<th>No.of Tickets</th><th>No.of Books</th><th>Value</th><th>Priority</th></tr>";
			for (int i = 0; i < aliasToValueMapList2.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList2.get(i);
				
				JSONArray jatickstock= new JSONArray();
				jatickstock.add("<input type='checkbox' class='group-checkable checkbox1' name='chkbox' data-set='#sample_2 .checkboxes' " +
						" id='"+rs.get("denomination_key")+
						" ' value='"+rs.get("denomination_key")+"-"+rs.get("ticket_type_manual_id")+"-"
						+Integer.parseInt(rs.get("opening_number").toString())+"-"+
						rs.get("denomination_type_manual")+"-"+Integer.parseInt(rs.get("closing_number").toString())+"'/>");
				
				ticketTable += "<tr>";
				ticketTable += "<td>"+rs.get("denomination_type_manual")+"</td>";
				ticketTable += "<td>"+cm.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy")+"</td>";
				ticketTable += "<td>"+rs.get("denomination_key")+"</td>";
				ticketTable += "<td>"+cm.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString()))+"</td>";
				ticketTable += "<td>"+cm.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString()))+"</td>";
				ticketTable += "<td>"+rs.get("number_of_tickets")+"</td>";
				ticketTable += "<td>"+rs.get("number_of_books")+"</td>";
				ticketTable += "<td>"+rs.get("value")+"</td>";
				ticketTable += "<td>"+rs.get("priority")+"</td>";
				ticketTable += "</tr>";
				
				jatickstock.add(rs.get("denomination_type_manual"));
				jatickstock.add(cm.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy"));
				jatickstock.add(rs.get("denomination_key"));
				jatickstock.add(cm.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
				jatickstock.add(cm.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
				jatickstock.add(rs.get("number_of_tickets"));
				jatickstock.add(rs.get("number_of_books"));/*jatickstock.add(rs.get("org_name"));*/
				jatickstock.add(rs.get("value"));
				jatickstock.add(rs.get("priority"));
				arraytickstock.add(jatickstock);
			}
			if(aliasToValueMapList2.size()==0){
				ticketTable = "";
			}
			ticketTable += "</table></div>";
			ServletActionContext.getRequest().getSession().setAttribute("normalTicketTableString", ticketTable);
			resulttick.put("aaData", arraytickstock);
			resulttick.put("iTotalRecords", total);
			resulttick.put("iTotalDisplayRecords", totalAfterFilter);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessiontick != null && sessiontick.isOpen()) {
				sessiontick.flush();
				sessiontick.close();
				sessiontick = null;
			}
			return resulttick;
		}
	}

	public int insertTicketInventoryMasterLuggage(TicketInventoryMaster tickinvmaster) {
	
		Session session = null;
		int i = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			tickinvmaster.setCreated_date(new Date());

			HttpServletRequest request = ServletActionContext.getRequest();
			int usrsessionid = (Integer) request.getSession().getAttribute(	"userid");
			tickinvmaster.setCreated_by(usrsessionid);
			tickinvmaster.setCreated_date(new Date());
			tickinvmaster.setCurrentstatus("New");
			tickinvmaster.setStatus("ACTIVE");
			i = (Integer) session.save(tickinvmaster);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}

		return i;

	}
	public int insertTicketInventoryMasterPass(TicketInventoryMaster tickinvmaster) {
		
		Session session = null;
		int i = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			tickinvmaster.setCreated_date(new Date());

			HttpServletRequest request = ServletActionContext.getRequest();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			tickinvmaster.setCreated_by(usrsessionid);
			tickinvmaster.setCreated_date(new Date());
			tickinvmaster.setCurrentstatus("New");
			tickinvmaster.setStatus("ACTIVE");
			i = (Integer) session.save(tickinvmaster);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}

		return i;

	}
	public int insertTicketInventoryMaster(TicketInventoryMaster tickinvmaster) {
		
		Session session = null;
		int i = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			tickinvmaster.setCreated_date(new Date());

			HttpServletRequest request = ServletActionContext.getRequest();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			tickinvmaster.setCreated_by(usrsessionid);
			tickinvmaster.setCreated_date(new Date());
			tickinvmaster.setCurrentstatus("New");
			tickinvmaster.setStatus("ACTIVE");
			i = (Integer) session.save(tickinvmaster);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}

		return i;

	}
	public int getMaxDEnomid() {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer
					.parseInt(session
							.createSQLQuery(
									"select IFNULL(max(ticket_inventory_mst_id),0) from  ticket_inventory_master")
							.uniqueResult().toString());
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		// TODO Auto-generated method stub
		return count;

	}

	public int insertToDenomType(Integer denoimination_type) {
		Session session = null;
		int i = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			DenominationType denomtype = new DenominationType();
			//denomtype.setDenomtype(denoimination_type);
			denomtype.getTicketTypeManual().setTicketTypeId(1);
			denomtype.setStatus("ACTIVE");
			denomtype.setDeletedstatus(0);
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			denomtype.setCreatedby(usrsessionid);
			denomtype.setCreateddate(new Date());
			i = (Integer) session.save(denomtype);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return i;
	}

	public List<String> getOrgTypeId() {
		Session session = null;
		List list = null;
		String status="ACTIVE";
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
		
			String qry = "select org_type_id from org_type where status='"+status+"' and org_type_id  not in (5,6,7)";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_type_id").toString());
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

	public List<String> getOrgTypeName() {
		Session session = null;
		List list = null;
		String status="ACTIVE";
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			String qry = "select org_type from org_type where status='"+status+"' and org_type_id not in (5,6,7)";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_type").toString());
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

	public List<String> getUnitId(int orgtypeid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			String qry = "select org_chart_id from org_chart where deleted_status=0 and org_name!='NULL' and org_type_id="
					+ orgtypeid + " order by org_name";

			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
		
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_chart_id").toString());
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

	public List<String> getUnitName(int orgtypeid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
		
			String qry = "select org_name from org_chart where deleted_status=0 and org_name!='NULL' and org_type_id="
					+ orgtypeid + " order by org_name ";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			DenominationTypeDao denomtypedao = new DenominationTypeDao();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
			
		}
		return list;
	}

	public int getTotalIssuedRecords(String arr) {
		Session sessiontottick = null;
		int cnt = 0;
		try {
			sessiontottick = HibernateUtilTick.getSession("hibernate.cfg.xml");

			arr = arr.substring(0, arr.length() - 1);
			String strkey = "";
			String strdenomtype = "";
			String s[] = arr.split(",");
			for (int i = 0; i < s.length; i++) {
				String key = s[i].substring(0, s[i].indexOf("-"));

				String denomtype = s[i].substring(s[i].indexOf("-") + 1,
						s[i].length())
						+ ",";
				if (Integer.parseInt(s[i].substring(s[i].indexOf("-") + 1,
						s[i].length())) == 1) {
					strkey += "'" + key + "',";
					strdenomtype += denomtype;
				}
			}
			if (strkey.equalsIgnoreCase("")
					&& strdenomtype.equalsIgnoreCase("")) {
				strkey = "'" + "'";
				strdenomtype = "'" + "'";
			} else {
				strkey = strkey.substring(0, strkey.length() - 1);
				strdenomtype = strdenomtype.substring(0,
						strdenomtype.length() - 1);
				
			}
			

			String sql = "select a.ticket_inventory_mst_id,dy.denomination_type_manual, a.denomination_key, a.ticket_type_manual_id,"
					+ " a.opening_number, a.closing_number,  a.number_of_tickets, a.number_of_books,"
					+ " a.value,a.unit_type,a.unit_name,a.priority from ("
					+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
					+ " opening_number, closing_number,  number_of_tickets,number_of_books, value,unit_type,unit_name,ticket_type_manual_id,priority "
					+ " FROM ticket_inventory_details tid  WHERE tid.denomination_key in ("
					+ strkey
					+ ") and tid.partial_book = 'Y' "
					+ " and tid.status = 'ACTIVE' and tid.current_status='New'  union all "
					+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id,"
					+ " denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as "
					+ " number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,ticket_type_manual_id ,priority"
					+ " FROM ticket_inventory_details tid "
					+ " WHERE tid.denomination_key in ("
					+ strkey
					+ ") and tid.partial_book = 'N' and tid.status = 'ACTIVE' and tid.current_status='New' "
					+ " group by tid.denomination_key, tid.denomination_type_manual_id ) a  inner join denomination_type_manual dy on "
					+ " dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id "
					+ " and a.ticket_type_manual_id in(" + strdenomtype + ") "+
			        " order by a.denomination_key, a.number_of_tickets";
			
			
						Query query = sessiontottick.createSQLQuery(sql);
			
			List<Integer> list = query.list();
			cnt = list.size();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (sessiontottick != null && sessiontottick.isOpen()) {
				sessiontottick.flush();
				sessiontottick.close();

			}

		}
		return cnt;
	}

	

	public void insertToInventoryLogs(int id, int unitname) {
		// TODO Auto-generated method stub
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		try {

			String qry = "insert into ticket_inventory_logs(current_status,transaction_on_type,issued_to,denomination_d,accepted_by,rejected_by) values('In Transit','Issued',"
					+ unitname + ",'" + id + "','','')";
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}

	}

	public void insertIntoINventoryLogs(TicketInventory tickinv) {
		// TODO Auto-generated method stub
		Session session = null;

		int i = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			String qry = "insert into ticket_inventory_logs(ticket_invoice_mast_id,invoice_status,transaction_on_type,action_to_unit,action_by) values(-1,'New','',"
					+ tickinv.getUnittype() + "," + usrsessionid + ")";
			// String
			// qry="insert into ticket_inventory_logs(current_status,denomination_d,transaction_on_type,issued_to,accepted_by,rejected_by) values('New',"+tickinv+",'','','','')";
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}

	}

	public List<String> getDenomId() {
		Session session = null;
		int count = 0;
		List list = null;
		try {
			list = new ArrayList();
		
			String qry = "SELECT distinct denomination_type_id,denomination_type FROM denomination_type where denomination_type_id!=-1 order by denomination_type asc";
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("denomination_type_id").toString());
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

	public List<String> getDenomName() {
		Session session = null;
		int count = 0;
		List list = null;
		try {
			list = new ArrayList();
			
			String qry = "SELECT distinct denomination_type_id,denomination_type FROM denomination_type where denomination_type_id!=-1 order by denomination_type asc";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("denomination_type").toString());
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

	public Map<Integer, String> getDEnoms() {
		Session session = null;
		int count = 0;
		List list = null;
		Map<Integer, String> resultMap = null;
		try {
			resultMap = new HashMap<Integer, String>();
			list = new ArrayList();
			
			String qry = "SELECT distinct denomination_type_id,denomination_type FROM denomination_type where denomination_type_id!=-1 order by denomination_type asc";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					resultMap.put(Integer.parseInt(rs.get(
							"denomination_type_id").toString()),
							rs.get("denomination_type").toString());
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
		return resultMap;
	}

	public int insertTicketInventoryDetails(TicketInventory tickinv) {
		Session session = null;
		int count = 0;
		List list = null;
		int i = 0;
		Map<Integer, String> resultMap = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			tickinv.setCreated_date(new Date());

			HttpServletRequest request = ServletActionContext.getRequest();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			tickinv.setCreated_by(usrsessionid);
			tickinv.setCreated_date(new Date());
			tickinv.setCurrentstatus("New");
			tickinv.setStatus("ACTIVE");

			i = (Integer) session.save(tickinv);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}

		return i;
	}

	public int insertTicketInventoryDetails(int blockno,String denomkey,String denomno,String priority,int stno,int deomval,int inserttickinvmstid) {
		Session session = null;
		int count = 0;
		List list = null;
		int s = 0;
		Map<Integer, String> resultMap = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
			int orgTypeId = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			int orgId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			int usrsessionid = (Integer) request.getSession().getAttribute("userid");
			TicketInventory tickinv=null;
			TicketInventoryIssue tickinv1=null;
			
			for (int i = 0; i < blockno; i++) {
				
				tickinv= new TicketInventory();
				tickinv.setTicket_type_manual_id(1);
				tickinv.setKey_number(denomkey);
				tickinv.setDenoimination_type(denomno);
				int sstno = (stno + 100 * i);
				int cclno = sstno + 99;
				String sstnumber = String.valueOf(sstno);
				tickinv.setStartno(sstno);
				tickinv.setEndno(cclno);
				tickinv.setNumber_of_tickets(100);
				tickinv.setValue(100 * deomval);
				tickinv.setNoofbooks(1);
				tickinv.setTicketinventorymasterid(inserttickinvmstid);
				tickinv.setPartialbook("N");
				tickinv.setPriority(Integer.parseInt(priority));
				tickinv.setUnittype(orgTypeId);
				tickinv.setUnitname(orgId);
				tickinv.setCreated_by(usrsessionid);
				tickinv.setCreated_date(new Date());
				tickinv.setCurrentstatus("New");
				tickinv.setStatus("ACTIVE");
				tickinv.setCreated_date(new Date());
				s = (Integer) session.save(tickinv);
				tickinv1= new TicketInventoryIssue();
				tickinv1.setTicket_inventory_id(s);
				tickinv1.setTicket_type_manual_id(1);
				tickinv1.setKey_number(denomkey);
				tickinv1.setDenoimination_type(denomno);
				int sstno1 = (stno + 100 * i);
				int cclno1 = sstno1 + 99;
				String sstnumber1 = String.valueOf(sstno1);
				tickinv1.setStartno(sstno1);
				tickinv1.setEndno(cclno1);
				tickinv1.setNumber_of_tickets(100);
				tickinv1.setValue(100 * deomval);
				tickinv1.setNoofbooks(1);
				tickinv1.setTicketinventorymasterid(inserttickinvmstid);
				tickinv1.setPartialbook("N");
				tickinv1.setPriority(Integer.parseInt(priority));
				tickinv1.setUnittype(orgTypeId);
				tickinv1.setUnitname(orgId);
				tickinv1.setCreated_by(usrsessionid);
				tickinv1.setCreated_date(new Date());
				tickinv1.setCurrentstatus("New");
				tickinv1.setStatus("ACTIVE");
				tickinv1.setCreated_date(new Date());
				 session.save(tickinv1);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return s;
	}
	
	@SuppressWarnings("finally")
	public int insertTicketInventoryDetailsLug(int blockno,String luggageticketkey,String denomno,String priority,int stno,int deomval,int inserttickinvmstid,String luggagedeomid) {
		
		Session session= null;
		int savedId = 0;
		try {
			session= HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			int orgTypeId = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			int orgId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			int usrsessionid = (Integer) request.getSession().getAttribute("userid");
			TicketInventory tickinv=null;
			TicketInventoryIssue tickinv1=null;
			for (int i = 0; i < blockno; i++) {
				
				int sstno = (stno + 50 * i);
				int cclno = sstno + 49;
				tickinv= new TicketInventory();
				tickinv.setTicket_type_manual_id(4);
				tickinv.setKey_number(luggageticketkey);
				tickinv.setDenoimination_type(denomno);
				tickinv.setUnittype(11);
				tickinv.setUnitname(144);
				tickinv.setStartno(sstno);
				tickinv.setEndno(cclno);
				tickinv.setNoofbooks(1);
				tickinv.setNoofpasses(50);
				tickinv.setDenoimination_type(luggagedeomid);
				tickinv.setValue(0);
				tickinv.setTicketinventorymasterid(inserttickinvmstid);
				tickinv.setKey_number(luggageticketkey);
				tickinv.setTicket_type_manual_id(4);
				tickinv.setUnittype(orgTypeId);
				tickinv.setUnitname(orgId);
				tickinv.setPriority(Integer.parseInt(priority));
				tickinv.setCreated_by(usrsessionid);
				tickinv.setCreated_date(new Date());
				tickinv.setCurrentstatus("New");
				tickinv.setStatus("ACTIVE");
				tickinv.setCreated_date(new Date());
				savedId  = (Integer) session.save(tickinv);
				
				tickinv1= new TicketInventoryIssue();
				tickinv1.setTicket_inventory_id(savedId);
				tickinv1.setTicket_type_manual_id(4);
				tickinv1.setKey_number(luggageticketkey);
				tickinv1.setDenoimination_type(denomno);
				tickinv1.setUnittype(11);
				tickinv1.setUnitname(144);
				tickinv1.setStartno(sstno);
				tickinv1.setEndno(cclno);
				tickinv1.setNoofbooks(1);
				tickinv1.setNoofpasses(50);
				tickinv1.setDenoimination_type(luggagedeomid);
				tickinv1.setValue(0);
				tickinv1.setTicketinventorymasterid(inserttickinvmstid);
				tickinv1.setKey_number(luggageticketkey);
				tickinv1.setTicket_type_manual_id(4);
				tickinv1.setUnittype(orgTypeId);
				tickinv1.setUnitname(orgId);
				tickinv1.setPriority(Integer.parseInt(priority));
				tickinv1.setCreated_by(usrsessionid);
				tickinv1.setCreated_date(new Date());
				tickinv1.setCurrentstatus("New");
				tickinv1.setStatus("ACTIVE");
				tickinv1.setCreated_date(new Date());
				session.save(tickinv1);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
			return savedId;
		}
	}
	
	@SuppressWarnings("finally")
	public int insertTicketInventoryDetailsTsheet(String tsheetkey,String denomno,int stno,int clno,int inserttickinvmstid,String nooftsheets) {
		
		Session session= null;
		int savedId = 0;
		try {
			session= HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			int orgTypeId = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			int orgId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			int usrsessionid = (Integer) request.getSession().getAttribute("userid");
			TicketInventory tickinv=null;
			TicketInventoryIssue tickinv1=null;
				
				
				tickinv= new TicketInventory();
				tickinv.setTicket_type_manual_id(5);
				tickinv.setKey_number(tsheetkey);
				tickinv.setDenoimination_type(denomno);
				tickinv.setStartno(stno);
				tickinv.setEndno(clno);
				tickinv.setPartialbook("N");
				tickinv.setNoofpasses(Integer.parseInt(nooftsheets));
				tickinv.setDenoimination_type(denomno);
				tickinv.setValue(0);
				tickinv.setTicketinventorymasterid(inserttickinvmstid);
				
				
				tickinv.setUnittype(orgTypeId);
				tickinv.setUnitname(orgId);
				
				tickinv.setCreated_by(usrsessionid);
				tickinv.setCreated_date(new Date());
				tickinv.setCurrentstatus("New");
				tickinv.setStatus("ACTIVE");
				tickinv.setCreated_date(new Date());
				savedId  = (Integer) session.save(tickinv);
				
				
				tickinv1= new TicketInventoryIssue();
				tickinv1.setTicket_inventory_id(savedId);
				tickinv1.setTicket_type_manual_id(5);
				tickinv1.setKey_number(tsheetkey);
				tickinv1.setDenoimination_type(denomno);
				tickinv1.setStartno(stno);
				tickinv1.setEndno(clno);
				tickinv1.setPartialbook("N");
				tickinv1.setNoofpasses(Integer.parseInt(nooftsheets));
				tickinv1.setDenoimination_type(denomno);
				tickinv1.setValue(0);
				tickinv1.setTicketinventorymasterid(inserttickinvmstid);
				
				
				tickinv1.setUnittype(orgTypeId);
				tickinv1.setUnitname(orgId);
				
				tickinv1.setCreated_by(usrsessionid);
				tickinv1.setCreated_date(new Date());
				tickinv1.setCurrentstatus("New");
				tickinv1.setStatus("ACTIVE");
				tickinv1.setCreated_date(new Date());
				session.save(tickinv1);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
			return savedId;
		}
	}
	
	@SuppressWarnings("finally")
	public int insertTicketInventoryDetailsMonthlyPass(String passday,String tickettype,String blockno,String denomkey,String denomno,String priority,int stno,int deomval,int inserttickinvmstid) {
		Session session = null;
		int s = 0;
		
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();

			HttpServletRequest request = ServletActionContext.getRequest();
			int orgTypeId = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			int orgId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			int usrsessionid = (Integer) request.getSession().getAttribute("userid");
			TicketInventory tickinv=null;
			TicketInventoryIssue tickinv1=null;
			for (int i = 0; i < Integer.parseInt(blockno); i++) {
				
				tickinv= new TicketInventory();
				tickinv.setTicket_type_manual_id(2);
				tickinv.setKey_number(denomkey);
				tickinv.setDenoimination_type(denomno);
				
				
				
				int value = deomval;
				tickinv.setStartno(stno);
				
				tickinv.setEndno(stno);
				tickinv.setTicketinventorymasterid(inserttickinvmstid);
				tickinv.setNoofbooks(0);
				tickinv.setNoofpasses(1);
				tickinv.setValue(value);
				tickinv.setDenoimination_type(denomno);
				tickinv.setPass_day(passday);
				tickinv.setKey_number(denomkey);
				tickinv.setTicket_type_manual_id(Integer.parseInt(tickettype));
				tickinv.setUnittype(orgTypeId);
				tickinv.setPriority(Integer.parseInt(priority));
				tickinv.setUnitname(orgId);
				tickinv.setCreated_by(usrsessionid);
				tickinv.setCreated_date(new Date());
				tickinv.setCurrentstatus("New");
				tickinv.setStatus("ACTIVE");
				tickinv.setCreated_date(new Date());
				s = (Integer) session.save(tickinv);
				
				
				tickinv1= new TicketInventoryIssue();
				tickinv1.setTicket_inventory_id(s);
				tickinv1.setTicket_type_manual_id(2);
				tickinv1.setKey_number(denomkey);
				tickinv1.setDenoimination_type(denomno);
				tickinv1.setStartno(stno);
				tickinv1.setEndno(stno);
				tickinv1.setTicketinventorymasterid(inserttickinvmstid);
				tickinv1.setNoofbooks(0);
				tickinv1.setNoofpasses(1);
				tickinv1.setValue(value);
				tickinv1.setDenoimination_type(denomno);
				tickinv1.setPass_day(passday);
				tickinv1.setKey_number(denomkey);
				tickinv1.setTicket_type_manual_id(Integer.parseInt(tickettype));
				tickinv1.setUnittype(orgTypeId);
				tickinv1.setPriority(Integer.parseInt(priority));
				tickinv1.setUnitname(orgId);
				tickinv1.setCreated_by(usrsessionid);
				tickinv1.setCreated_date(new Date());
				tickinv1.setCurrentstatus("New");
				tickinv1.setStatus("ACTIVE");
				tickinv1.setCreated_date(new Date());
			session.save(tickinv1);
				stno++;
		}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				//session.flush();
				session.close();
			}
			return s;
		}
	}
	
	public int insertTicketInventoryDetailsPass(String passday,String tickettype,int blockno,String denomkey,String denomno,String priority,int stno,int deomval,int inserttickinvmstid) {
		Session session = null;
		int savedId = 0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			int orgTypeId = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			int orgId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			int usrsessionid = (Integer) request.getSession().getAttribute("userid");
			TicketInventory tickinv=null;
			TicketInventoryIssue tickinv1=null;
			for (int i = 0; i < blockno; i++) {
				tickinv= new TicketInventory();
				tickinv.setTicket_type_manual_id(2);
				tickinv.setKey_number(denomkey);
				tickinv.setDenoimination_type(denomno);
				int sstno = (stno + 50 * i);
				int cclno = sstno + 49;
				int value = 50* deomval;
				tickinv.setStartno(sstno);
				tickinv.setEndno(cclno);
				tickinv.setTicketinventorymasterid(inserttickinvmstid);
				tickinv.setNoofbooks(1);
				tickinv.setNoofpasses(50);
				tickinv.setValue(value);
				tickinv.setDenoimination_type(denomno);
				tickinv.setPass_day(passday);
				tickinv.setKey_number(denomkey);
				tickinv.setTicket_type_manual_id(Integer.parseInt(tickettype));
				tickinv.setUnittype(orgTypeId);
				tickinv.setPriority(Integer.parseInt(priority));
				tickinv.setUnitname(orgId);
				tickinv.setCreated_by(usrsessionid);
				tickinv.setCreated_date(new Date());
				tickinv.setCurrentstatus("New");
				tickinv.setStatus("ACTIVE");
				tickinv.setCreated_date(new Date());
				savedId = (Integer) session.save(tickinv);
				
				tickinv1= new TicketInventoryIssue();
				tickinv1.setTicket_inventory_id(savedId);
				tickinv1.setTicket_type_manual_id(2);
				tickinv1.setKey_number(denomkey);
				tickinv1.setDenoimination_type(denomno);
				tickinv1.setStartno(sstno);
				tickinv1.setEndno(cclno);
				tickinv1.setTicketinventorymasterid(inserttickinvmstid);
				tickinv1.setNoofbooks(1);
				tickinv1.setNoofpasses(50);
				tickinv1.setValue(value);
				tickinv1.setDenoimination_type(denomno);
				tickinv1.setPass_day(passday);
				tickinv1.setKey_number(denomkey);
				tickinv1.setTicket_type_manual_id(Integer.parseInt(tickettype));
				tickinv1.setUnittype(orgTypeId);
				tickinv1.setPriority(Integer.parseInt(priority));
				tickinv1.setUnitname(orgId);
				tickinv1.setCreated_by(usrsessionid);
				tickinv1.setCreated_date(new Date());
				tickinv1.setCurrentstatus("New");
				tickinv1.setStatus("ACTIVE");
				tickinv1.setCreated_date(new Date());
				session.save(tickinv1);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return savedId;
	}
	
	public int getMaxTickinvId() {
		Session session = null;
		int count = 0;
		List list = null;
		int i = 0;
		Map<Integer, String> resultMap = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer
					.parseInt(session
							.createSQLQuery(
									"select IFNULL(max(ticket_inventory_det_id),0) from  ticket_inventory_details")
							.uniqueResult().toString());
		
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}

		// TODO Auto-generated method stub
		return count;
	}

	public void insertTicketInventoryLogs(Session session,int ticketinvoiceid,
			TicketInventory tickinv, int issuedfromunit, boolean flag) {
		if (flag == false) {
			
			if(session==null || (!session.isOpen())){
			 session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			}
			//session.beginTransaction();
			int i = 0;
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession sess = request.getSession();
				int usrsessionid = (Integer) request.getSession().getAttribute(
						"userid");
				String qry = "insert into ticket_inventory_logs(ticket_invoice_mast_id,invoice_status,transaction_on_type,action_unit,action_by,created_date,created_by) values("
						+ ticketinvoiceid
						+ ",'In Transit','Issued',"
						+ issuedfromunit
						+ ","
						+ usrsessionid
						+ ",now(),"
						+ usrsessionid + ")";
				/*
				 * String qry =
				 * "update  ticket_inventory_logs set invoice_status='In Transit',transaction_on_type='Issued',action_unit="
				 * + issuedfromunit + ",action_by=" + usrsessionid +
				 * " where ticket_invoice_mast_id=" + ticketinvoiceid + "";
				 */
				Query query = session.createSQLQuery(qry);
				i = query.executeUpdate();

				//session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();

			} finally {
//				if (session != null && session.isOpen()) {
//					session.flush();
//					session.close();

//				}

			}
		}
		// TODO Auto-generated method stub

	}

	public int getMaxInventInvMastId(Session session) {
		//Session session = null;
		int count = 0;
		List list = null;
		int i = 0;
		Map<Integer, String> resultMap = null;
		try {
			if(session==null || (!session.isOpen())){
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			}

			count = Integer
					.parseInt(session
							.createSQLQuery(
									"select IFNULL(max(ticket_invoice_mast_id),0) from  ticket_invoice_master")
							.uniqueResult().toString());
			
		} catch (Exception e) {
			//session.getTransaction().rollback();

//		} finally {
//			if (session != null && session.isOpen()) {
//				session.flush();
//				session.close();
//
//			}

		}
		// TODO Auto-generated method stub
		return count;
	}

	public void insertIntoInventoryInvoiceDetails(int invinvmstid) {
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		try {
			String qry = "insert into inventory_invoice_details(inventory_invoice_mast_id) values("
					+ invinvmstid + ")";
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}

	}

	@SuppressWarnings("finally")
	public boolean finalSaveOfStockEntry(int id){
		boolean isSuccess = false;
		Session session = null;
		try{
			Common common1 = new Common();
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String indentNumber = ServletActionContext.getRequest().getParameter("indentNumber");
			String indentDate = ServletActionContext.getRequest().getParameter("indentDate");
			
			String vendorName = ServletActionContext.getRequest().getParameter("vendorName")==null?"":ServletActionContext.getRequest().getParameter("vendorName");
			String vendorContact = ServletActionContext.getRequest().getParameter("vendorContact")==null?"":ServletActionContext.getRequest().getParameter("vendorContact");
			String vendorAddress = ServletActionContext.getRequest().getParameter("vendorAddress")==null?"":ServletActionContext.getRequest().getParameter("vendorAddress");
			
			indentDate = common1.changeDataFormat(indentDate, "dd-mm-yyyy", "yyyy-mm-dd");
			String voucherNumber = ServletActionContext.getRequest().getParameter("receiptVoucherNo");
			if(cm.getDBResultInt(session, "SELECT COUNT(*) count FROM `ticket_inventory_master` WHERE `inventory_temp_ref_id` = '"+id+"'", "count")>0){
				String insertQuery = "INSERT INTO `ticket_inventory_ref` (`indent_number`, `indent_date`, `voucher_number`, `created_date`,`vendor_name`,`vendor_contact`,`vendor_address`) " +
						"VALUES ('"+indentNumber+"', '"+indentDate+"', '"+voucherNumber+"', now(),'"+vendorName+"','"+vendorContact+"','"+vendorAddress+"');";
				Query queryObject = session.createSQLQuery(insertQuery);
				queryObject.executeUpdate();
				int finalRefId = cm.getDBResultInt(session, "select last_insert_id() id from  `ticket_inventory_ref` ", "id");
				String sqlQueryToUpdate = "UPDATE `ticket_inventory_master` SET `inventory_ref_id` = '"+finalRefId+"' WHERE `inventory_temp_ref_id` = '"+id+"'";
				queryObject = session.createSQLQuery(sqlQueryToUpdate);
				queryObject.executeUpdate();
				session.getTransaction().commit();
				ServletActionContext.getRequest().getSession().setAttribute("RefId", finalRefId);
				isSuccess = true;
			}else{
				isSuccess = false;
			}
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			isSuccess = false;
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
		
	}
	public int getStockValue(String s) {
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		int stockval = 0;
		try {
			int tickinvdetid = Integer.parseInt(s);
			String sql = "select de.denomination_type_manual*td.number_of_tickets as denom FROM  ticket_inventory_details td inner join "
					+ " denomination_type_manual de on td.denomination_type_manual_id=de.denomination_type_manual_id "
					+ " where td.status='ACTIVE' and td.partial_book='Y' and ticket_inventory_mst_id="
					+ tickinvdetid + " group by denomination_key";

			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			double stockvalue = Double.parseDouble(session.createSQLQuery(sql)
					.uniqueResult().toString());
			stockval = (int) stockvalue;
			
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		// TODO Auto-generated method stub
		return stockval;

	}

	public void insertIntoTicketInvoiceMaster(Session session,TicketInvoiceMaster tickinvmst) {
		//Session session = null;
		try {
			if(session==null || (!session.isOpen())){
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			}
			//session.beginTransaction();

			tickinvmst.setCreated_date(new Date());

			HttpServletRequest request = ServletActionContext.getRequest();
			
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			tickinvmst.setCreated_by(usrsessionid);
			tickinvmst.setUpdatedby(0);

			int i = (Integer) session.save(tickinvmst);
			//session.getTransaction().commit();
		} catch (Exception e) {
			//session.getTransaction().rollback();

		} finally {
//			if (session != null && session.isOpen()) {
//				session.flush();
//				session.close();
//
//			}

		}

	}

	public int getTicketInvDetId(String s) {
		Session session = null;
		int i = 0;
		int tickinvmstid = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			int tickinvdetid = Integer.parseInt(s);
			String sql = "select ticket_inventory_det_id FROM  ticket_inventory_details td inner join denomination_type_manual de "
					+ " on td.denomination_type_manual_id	=de.denomination_type_manual_id where td.status='ACTIVE' "
					+ " and td.partial_book='Y' and ticket_inventory_mst_id="
					+ tickinvdetid + " group by denomination_key";
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			tickinvmstid = Integer.parseInt(session.createSQLQuery(sql)
					.uniqueResult().toString());
			
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		// TODO Auto-generated method stub
		return tickinvmstid;
	}

	public int getTicketInventoryInvoiceMasterId() {
		Session session = null;
		int i = 0;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer
					.parseInt(session
							.createSQLQuery(
									"select IFNULL(max(ticket_invoice_mast_id),0) from  ticket_invoice_master")
							.uniqueResult().toString());
		
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		// TODO Auto-generated method stub
		return count;
	}

	public int insertTicketInvoiceDetails(Session session,int tickinvinsertinvoicemasterid,
			int string, int ticketinventorymstid) {
//		Session session = null;
		int i = 0;
		try {
			if(session==null || (!session.isOpen())){
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			}

			//session.beginTransaction();
			TicketInvoiceDetails tinvdet = new TicketInvoiceDetails();
			tinvdet.setTicketinventorydetailstid(string);
			tinvdet.setTicketinventorymastid(ticketinventorymstid);

			tinvdet.setTicketinvoicemasterid(tickinvinsertinvoicemasterid);
			tinvdet.setCreated_date(new Date());

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			tinvdet.setCreated_by(usrsessionid);

			i = (Integer) session.save(tinvdet);
			//session.getTransaction().commit();
		} catch (Exception e) {
			//session.getTransaction().rollback();

		} finally {
//			if (session != null && session.isOpen()) {
//				session.flush();
//				session.close();
//
//			}
			
		}
		return i;
	}

	public String insertTicketInventoryLogs(Session session,String denomkey, int reqnumber,TicketInventory tickinv,
			boolean flag, int issuefromunit,int issuefromunitid, int tickettype) {
	
		List list = new ArrayList();
		String qry = "";
		String voucherlpad = "";
		try {
			qry = "SELECT * FROM  ticket_inventory_details td inner join ticket_inventory_master tm on td.ticket_inventory_mst_id=tm.ticket_inventory_mst_id  where td.current_status='New' and td.denomination_key='"
					+ denomkey	+ "' and td.ticket_type_manual_id="	+ tickettype+ " order by tm.priority ASC LIMIT " + reqnumber + "";
			if(session==null || (!session.isOpen())){
				session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			}
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			if (aliasToValueMapList.size() > 0) {
				if (flag == false) {
					TicketInvoiceMaster tickinvmst = new TicketInvoiceMaster();
					tickinvmst.setIssuetounit(tickinv.getUnittype());
					tickinvmst.setIssuetounitid(tickinv.getUnitname());
					int stockvalue = tickinv.getDenoimination_type()* reqnumber;
					tickinvmst.setStockvalue(stockvalue);
					tickinvmst.setCurrentstatus("In Transit");
					tickinvmst.setStatus("ACTIVE");
					tickinvmst.setIssuefromunit(issuefromunit);
					tickinvmst.setIssuefromunitid(issuefromunitid);
					insertIntoTicketInvoiceMaster(session,tickinvmst);
				}
				int tickinvinsertinvoicemasterid = getMaxInventInvMastId(session);
				voucherlpad = StringUtils.leftPad("" + tickinvinsertinvoicemasterid, 10, "0");
				int voucherid = updateVoucherInvoiceMaster(session,voucherlpad,tickinvinsertinvoicemasterid, flag);
				insertTicketInventoryLogs(session,tickinvinsertinvoicemasterid,tickinv, issuefromunit, flag);
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					int tickinvmstid = insertTicketInvoiceDetails(session,tickinvinsertinvoicemasterid,
											Integer.parseInt(rs.get("ticket_inventory_det_id").toString()),
											Integer.parseInt(rs.get("ticket_inventory_mst_id").toString()));

					updateTicketInventoryDetails(session,Integer.parseInt(rs.get("ticket_inventory_det_id").toString()));
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return voucherlpad;
	}

	private int updateVoucherInvoiceMaster(Session session,String voucherno,
			int tickinvinsertinvoicemasterid, boolean flag) {
		//Session session = null;

		
		int i = 0;
		if (flag == false) {
			try {
				if(session==null || (!session.isOpen())){
				session = HibernateUtilTick.getSession("hibernate.cfg.xml");
				}

				//session.beginTransaction();

				String qry = "update ticket_invoice_master set voucher_number='"
						+ voucherno
						+ "' where ticket_invoice_mast_id="
						+ tickinvinsertinvoicemasterid + "";
				Query query = session.createSQLQuery(qry);
				i = query.executeUpdate();

				//session.getTransaction().commit();
			} catch (Exception e) {
				//session.getTransaction().rollback();

			} finally {
//				if (session != null && session.isOpen()) {
//					session.flush();
//					session.close();
//
//				}
			}
		}
		return i;
	}

	public int getDenomValue(String denomkey) {
		Session session = null;
		int count = 0;
		int i = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer
					.parseInt(session
							.createSQLQuery(
									"select distinct dy.denomination_type FROM ticket_inventory_details tid "
											+ "inner join denomination_type "
											+ "dy on dy.denomination_type_id= tid.denoimination_type_id where denomination_key='"
											+ denomkey + "'").uniqueResult()
							.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		
			
		}
		return count;
		// TODO Auto-generated method stub

	}

	public void updateTicketInvoiceDetails(Session session,int tickinvinsertinvoicemasterid,int stockvalue) {
		
		if(session==null || (!session.isOpen())){
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		}
		int prevstockval = Integer.parseInt(session.createSQLQuery(
								"select stock_value from ticket_invoice_master where ticket_invoice_mast_id="
								+ tickinvinsertinvoicemasterid + "").uniqueResult().toString());
		try {
			session.beginTransaction();
			int upstockval = prevstockval + stockvalue;
			String qry = "update ticket_invoice_master set stock_value="+ upstockval + " where ticket_invoice_mast_id="+ tickinvinsertinvoicemasterid + "";
			Query query = session.createSQLQuery(qry);
			int i = query.executeUpdate();
		} catch (Exception e) {

		}
	}

	public void updateTicketInventoryDetails(Session session,int ticketinventorydetid) {
		
		
		//Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		//session.beginTransaction();
		int i = 0;
		try {
			String qry = "update ticket_inventory_details set current_status='In Transit' where ticket_inventory_det_id="
					+ ticketinventorydetid + "";
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			//session.getTransaction().commit();
		} catch (Exception e) {
			//session.getTransaction().rollback();

		} finally {
//			if (session != null && session.isOpen()) {
//				session.flush();
//				session.close();
//
//			}
		}

	}

	public int getPriorityCount(int denomtypeid) {
		Session session = null;
		int i = 0;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			// session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer
					.parseInt(session
							.createSQLQuery(
									"select count(*) from ticket_inventory_master where status='ACTIVE' and denoimination_type_manual_id="
											+ denomtypeid + "").uniqueResult()
							.toString());
			
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}
		}

		return count;

	}




	

	public void delteTicketInvMstDetails(String denomkey) {
		Session session = null;

		
		int i = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String qry = "update ticket_inventory_master set status='INACTIVE' where ticket_inventory_mst_id='"
					+ denomkey + "'";
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		

	}

	@SuppressWarnings("unchecked")
	public JSONObject getDeleteData(int total, HttpServletRequest request,
			String arr, String col, String dir) {
		JSONObject result = new JSONObject();
		Session session = null;
		String str = arr;
		arr = arr.substring(0, arr.length() - 1);
		String strkey = "";
		String strdenomtype = "";
		String s[] = arr.split(",");
		for (int i = 0; i < s.length; i++) {
			String key = s[i].substring(0, s[i].indexOf("-"));

			String denomtype = s[i].substring(s[i].indexOf("-") + 1,
					s[i].length())
					+ ",";
			if (Integer.parseInt(s[i].substring(s[i].indexOf("-") + 1,
					s[i].length())) == 1) {
				strkey += "'" + key + "',";
				strdenomtype += denomtype;
			}
		}
		if (strkey.equalsIgnoreCase("") && strdenomtype.equalsIgnoreCase("")) {
			strkey = "'" + "'";
			strdenomtype = "'" + "'";
		} else {
			strkey = strkey.substring(0, strkey.length() - 1);
			strdenomtype = strdenomtype.substring(0, strdenomtype.length() - 1);
		}
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from TicketInventory";

			// sql += " order by " + COL_NAME + " " + DIR;

			// sql += " limit " + request.getParameter("iDisplayStart") + ", "
			// + request.getParameter("iDisplayLength");
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String sql1 = "select ticket_inventory_mst_id,dy.denomination_type_manual,"
					+ " denomination_key,opening_number,closing_number,number_of_books,value,priority "
					+ " from ticket_inventory_master tm inner join denomination_type_manual dy "
					+ " on tm.denoimination_type_manual_id=dy.denomination_type_manual_id inner join ticket_type_manual ttm on ttm.ticket_type_manual_id=tm.ticket_type_manual_id	 "
					+ " where tm.current_status='New' and ttm.ticket_type_manual_id in("
					+ strdenomtype
					+ ")"
					+ " and tm.status='ACTIVE' and denomination_key in("
					+ strkey + ")";
			/*
			 * String sql1=
			 * "select  dy.denomination_type, a.denomination_key, a.opening_number, a.closing_number,a.priority,"
			 * +
			 * " a.number_of_tickets, a.number_of_books, a.value,a.unit_type,a.unit_name from (SELECT denoimination_type_id, denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, "
			 * +
			 * " sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,priority "
			 * + " FROM ticket_inventory_details tid " +
			 * " WHERE tid.denomination_key in("
			 * +strkey+") and tid.status = 'ACTIVE' and tid.current_status='New' "
			 * +
			 * " group by tid.denomination_key, tid.denoimination_type_id ) a "
			 * +
			 * " inner join denomination_type dy on dy.denomination_type_id= a.denoimination_type_id "
			 * + "order by a.denomination_key, a.number_of_tickets";
			 */
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {


				sql1 += " and ticket_inventory_mst_id='" + search_term + "'";
				sql1 += " OR dy.denomination_type='" + search_term + "'";
				sql1 += " OR denomination_key like '%" + search_term + "%'";
				sql1 += " OR  opening_number='" + search_term + "'";
				sql1 += " OR  closing_number='" + search_term + "'";
				sql1 += " OR  number_of_books='" + search_term + "'";
				sql1 += " OR  value='" + search_term + "'";
				sql1 += " OR  priority='" + search_term + "'";

			}
			
			try{
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					// criteria.addOrder(Order.asc(col));
					sql += " order by " + col + " asc";
				} else {
					// criteria.addOrder(Order.desc(col));
					sql += " order by " + col + " desc";
				}
			}
		}
		catch(Exception e){
		
		}
			
			Query query = session.createSQLQuery(sql1);

			/*
			 * query.setFirstResult(Integer.parseInt(request
			 * .getParameter("iDisplayStart")));
			 */
			/*
			 * query.setMaxResults(Integer.parseInt(request
			 * .getParameter("iDisplayLength")));
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			/*
			 * criteria.setFirstResult(Integer.parseInt(request
			 * .getParameter("iDisplayStart")));
			 * criteria.setMaxResults(Integer.parseInt(request
			 * .getParameter("iDisplayLength")));
			 */
			// List<TicketInventory> list =(List<TicketInventory>) query.list();
			JSONArray array = new JSONArray();
			// System.out.println("List size----->" + list.size() +
			// list.get(0).getValue()+"\t"
			// + request.getParameter("iDisplayStart"));
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();

				// ja.add(rs.get("ticket_inventory_mst_id"));
				ja.add(rs.get("ticket_inventory_mst_id"));
				ja.add(rs.get("denomination_type_manual"));
				/*
				 * ja.add(rs.get("pass_type")); ja.add(rs.get("service_type"));
				 */

				ja.add(rs.get("denomination_key"));
				ja.add(rs.get("opening_number"));
				ja.add(rs.get("closing_number"));
				// ja.add(rs.get("number_of_tickets"));
				ja.add(rs.get("number_of_books"));
				ja.add(rs.get("value"));
				// ja.add(rs.get("unit_name"));
				// ja.add(rs.get("unit_type"));
				ja.add(rs.get("priority"));

				ja.add("<a href='#' class='deletetkt' id='ticket_delete_"
						+ rs.get("ticket_inventory_mst_id") + "' value='"
						+ rs.get("ticket_inventory_mst_id") + "'> Delete</a>");
				ja.add("");
				// ja.add("<a class='deletetkt' id='floor_delete_"+
				// rs.get("denomination_key")+ "' value='"+
				// rs.get("denomination_key") + "'> Delete</a>");

				/*
				 * String creattedDate = list.get(i).getCreated_date() != null ?
				 * list .get(i).getCreated_date().toString() : "";
				 * ja.add(creattedDate.replace(".0", ""));
				 */

				array.add(ja);
			}

			totalAfterFilter = getTotalDeleteRecords(str);
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			/*
			 * System.out.println("REsult ------>" +
			 * request.getParameter("iDisplayStart"));
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			return result;
		}

	}

	public int getTotalDeleteRecords(String arr) {
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		arr = arr.substring(0, arr.length() - 1);
		String strkey = "";
		String strdenomtype = "";
		String s[] = arr.split(",");
		for (int i = 0; i < s.length; i++) {
			String key = s[i].substring(0, s[i].indexOf("-"));

			String denomtype = s[i].substring(s[i].indexOf("-") + 1,
					s[i].length())
					+ ",";
			if (Integer.parseInt(s[i].substring(s[i].indexOf("-") + 1,
					s[i].length())) == 1) {
				strkey += "'" + key + "',";
				strdenomtype += denomtype;
			}
		}
		if (strkey.equalsIgnoreCase("") && strdenomtype.equalsIgnoreCase("")) {
			strkey = "'" + "'";
			strdenomtype = "'" + "'";
		} else {
			strkey = strkey.substring(0, strkey.length() - 1);
			strdenomtype = strdenomtype.substring(0, strdenomtype.length() - 1);
		}
		// System.out.println(strkey + "denomination type" + strdenomtype);
		String sql1 = "select count(*) as count,ticket_inventory_mst_id,dy.denomination_type_manual,"
				+ " denomination_key,opening_number,closing_number,number_of_books,value,priority "
				+ " from ticket_inventory_master tm inner join denomination_type_manual dy "
				+ " on tm.denoimination_type_manual_id=dy.denomination_type_manual_id inner join ticket_type_manual ttm on ttm.ticket_type_manual_id=tm.ticket_type_manual_id	 "
				+ " where tm.current_status='New' and ttm.ticket_type_manual_id in("
				+ strdenomtype
				+ ")"
				+ " and tm.status='ACTIVE' and denomination_key in("
				+ strkey
				+ ")";
		/*
		 * String sql=
		 * "SELECT count(*) as count from(select  dy.denomination_type, a.denomination_key, a.opening_number, a.closing_number,"
		 * +
		 * " a.number_of_tickets, a.number_of_books, a.value,a.unit_type,a.unit_name from (SELECT denoimination_type_id, denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, "
		 * +
		 * " sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name "
		 * + " FROM ticket_inventory_details tid " +
		 * " WHERE tid.denomination_key in ("
		 * +strkey+") and tid.status = 'ACTIVE' and tid.current_status='New' " +
		 * " group by tid.denomination_key, tid.denoimination_type_id ) a " +
		 * " inner join denomination_type dy on dy.denomination_type_id= a.denoimination_type_id "
		 * + "order by a.denomination_key, a.number_of_tickets) b ";
		 */
		Query query = session.createSQLQuery(sql1).addScalar("count",
				Hibernate.INTEGER);
		;

		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);
		return cnt;
	}

	public int getTotalIssueRecords() {
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");

		String sql = "select count(*) as count from(select a.tickinvmstid,ot.org_type,oc.org_name,a.created_date,a.stockval from(select tm.ticket_invoice_mast_id as tickinvmstid,"
				+ " tm.issue_from_unit_id,tm.created_date,tm.issue_from_unit,tm.issue_to_unit_id,td.ticket_inventory_det_id,sum(tid.value) stockval from ticket_invoice_details td "
				+ " inner join ticket_invoice_master tm "
				+ " on td.ticket_invoice_mast_id=tm.ticket_invoice_mast_id inner join ticket_inventory_details tid "
				+ " on tid.ticket_inventory_det_id=td.ticket_inventory_det_id where tm.current_status='In Transit' group by tm.ticket_invoice_mast_id) a "
				+ " inner join  org_type ot on a.issue_from_unit=ot.org_type_id "
				+ "inner join org_chart oc on a.issue_from_unit_id=oc.org_chart_id) b";

		Query query = session.createSQLQuery(sql).addScalar("count",
				Hibernate.INTEGER);
		;

		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getIssueData(int total, HttpServletRequest request,
			String col, String dir) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from TicketInventory";

			// sql += " order by " + COL_NAME + " " + DIR;

			// sql += " limit " + request.getParameter("iDisplayStart") + ", "
			// + request.getParameter("iDisplayLength");
			Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			String sql1 = "select a.ticket_inventory_det_id,a.voucher_number,a.tickinvmstid,ot.org_type,oc.org_name,a.created_date,a.stockval from(select tm.ticket_invoice_mast_id as tickinvmstid,"
					+ " tm.issue_from_unit_id,tm.created_date,tm.issue_from_unit,tm.issue_to_unit_id,td.ticket_inventory_det_id,tm.voucher_number,sum(tid.value) stockval from ticket_invoice_details td "
					+ " inner join ticket_invoice_master tm "
					+ " on td.ticket_invoice_mast_id=tm.ticket_invoice_mast_id inner join ticket_inventory_details tid "
					+ " on tid.ticket_inventory_det_id=td.ticket_inventory_det_id where tm.current_status='In Transit' and tm.issue_to_unit_id="+Integer.parseInt(request.getSession().getAttribute("orgchartid").toString())+" group by tm.ticket_invoice_mast_id) a "
					+ " inner join  org_type ot on a.issue_from_unit=ot.org_type_id "
					+ " inner join org_chart oc on a.issue_from_unit_id=oc.org_chart_id";

			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
				//search_term=request.getParameter("sSearch").trim();

				sql1 += " where a.tickinvmstid='" + search_term + "'";
				sql1 += " OR ot.org_type like '%" + search_term + "%'";
				sql1 += " OR oc.org_name like '%" + search_term + "%'";
				sql1 += " OR  a.created_date like '%" + search_term + "%'";
				sql1 += " OR  a.stockval='" + search_term + "'";

			}
			
			try{
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					// criteria.addOrder(Order.asc(col));
					sql1 += " order by " + col + " asc";
				} else {
					// criteria.addOrder(Order.desc(col));
					sql1 += " order by " + col + " desc";
				}
			}
			}
			catch(Exception e){
			
			}
			
			Query query = session.createSQLQuery(sql1);
			/*query.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			query.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
*/			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			// System.out.println("List size----->" + list.size() +
			// list.get(0).getValue()+"\t"
			// + request.getParameter("iDisplayStart"));
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' name='chkbox' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("tickinvmstid")
						+ "' value='"
						+ rs.get("tickinvmstid") + "'/>");

				ja.add(rs.get("tickinvmstid"));
                ja.add(rs.get("voucher_number"));
				ja.add(rs.get("org_type"));
				ja.add(rs.get("org_name"));
				String creattedDate = rs.get("created_date") != null ? rs.get(
						"created_date").toString() : "";
						creattedDate=creattedDate.replace(".0", "");
						Common cm=new Common();
						String dateofissuance=cm.getDateFromDateTime(creattedDate);
				ja.add(dateofissuance);

				ja.add(rs.get("stockval"));
				/* ja.add(""); */

				/*
				 * String creattedDate = list.get(i).getCreated_date() != null ?
				 * list .get(i).getCreated_date().toString() : "";
				 * ja.add(creattedDate.replace(".0", ""));
				 */

				array.add(ja);
			}

			totalAfterFilter = getTotalIssueRecords();
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	public void updateTickInvDetails(int tickinvmstid) {

		List list = new ArrayList();
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		String qry = "select ticket_inventory_det_id from ticket_invoice_details where ticket_invoice_mast_id="
				+ tickinvmstid + "";
		Query query = session.createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("ticket_inventory_det_id").toString());
			}
		}
		int i = 0;
		Iterator iterator = list.iterator();

		session.beginTransaction();
		try {

			while (iterator.hasNext()) {

				String qry1 = "update ticket_inventory_details set current_status='Issued' where ticket_inventory_det_id="
						+ iterator.next() + "";
				Query query1 = session.createSQLQuery(qry1);
				i = query1.executeUpdate();

			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				// System.out.println(iterator.next());
			}
		}

	}

	public void updateTicketInvoiceMaster(int tickinvoicemstid) {

		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			String qry = "update ticket_invoice_master set current_status='Issued',updated_date=now(),updated_by="
					+ usrsessionid
					+ " where ticket_invoice_mast_id="
					+ tickinvoicemstid + "";
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				// System.out.println(iterator.next());
			}
		}

	}

	public void updateTicketInventoryLogs(int tickinvmstid) {
		Session session = null;
		int i = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			int action_unit = Integer
					.parseInt(session
							.createSQLQuery(
									" SELECT issue_to_unit FROM ticket_invoice_master WHERE ticket_invoice_mast_id = "
											+ tickinvmstid).uniqueResult()
							.toString());
			session.beginTransaction();

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			String qry = "insert into ticket_inventory_logs(ticket_invoice_mast_id,invoice_status,transaction_on_type,action_unit,action_by,created_date,created_by) values("
					+ tickinvmstid
					+ ",'Accepted','Accepted',"
					+ action_unit
					+ "," + usrsessionid + ",now()," + usrsessionid + ")";
			/*
			 * String qry =
			 * "update  ticket_inventory_logs set invoice_status='Accepted',transaction_on_type='Accepted',action_unit="
			 * +action_unit+" where ticket_invoice_mast_id=" + tickinvmstid +
			 * "";
			 */
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				// System.out.println(iterator.next());
			}
		}

		// TODO Auto-generated method stub

	}

	public void updateTickInvDetailsReject(int tickinvmstid) {
		List list = new ArrayList();
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		String qry = "select ticket_inventory_det_id from ticket_invoice_details where ticket_invoice_mast_id="
				+ tickinvmstid + "";
		Query query = session.createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("ticket_inventory_det_id").toString());
			}
		}
		int i = 0;
		Iterator iterator = list.iterator();

		session.beginTransaction();
		try {

			while (iterator.hasNext()) {

				String qry1 = "update ticket_inventory_details set current_status='New' where ticket_inventory_det_id="
						+ iterator.next() + "";

				Query query1 = session.createSQLQuery(qry1);
				i = query1.executeUpdate();

			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				// System.out.println(iterator.next());
			}
		}

	}

	public void updateTicketInvoiceMasterReject(int tickinvoicemstid) {
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			String qry = "update ticket_invoice_master set current_status='Rejected',updated_date=now(),updated_by="
					+ usrsessionid
					+ " where ticket_invoice_mast_id="
					+ tickinvoicemstid + "";
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		}
		session.close();

	}

	public void updateTicketInventoryLogsReject(int tickinvmstid) {
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		int action_unit = Integer
				.parseInt(session
						.createSQLQuery(
								" SELECT issue_to_unit FROM ticket_invoice_master WHERE ticket_invoice_mast_id = "
										+ tickinvmstid).uniqueResult()
						.toString());
		session.beginTransaction();
		int i = 0;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			String qry = "insert into ticket_inventory_logs(ticket_invoice_mast_id,invoice_status,transaction_on_type,action_unit,action_by,created_date,created_by) values("
					+ tickinvmstid
					+ ",'Rejected','Rejected',"
					+ action_unit
					+ "," + usrsessionid + ",now()," + usrsessionid + ")";
			/*
			 * String qry =
			 * "update  ticket_inventory_logs set invoice_status='Rejected',transaction_on_type='Rejected',action_unit="
			 * +action_unit+" where ticket_invoice_mast_id=" + tickinvmstid +
			 * "";
			 */
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();

		}
		session.close();

	}

	
	

	
	
	
	
	
	
	
	

	public int getIssueFromUnit(int issuefromunitid) {
		Session session = HibernateUtilTick.getSession("hibernate.cfg.xml");

		int workingorgchartid = Integer.parseInt(session
				.createSQLQuery(
						"select org_type_id from  org_chart where org_chart_id="
								+ issuefromunitid).uniqueResult().toString());
		// TODO Auto-generated method stub
		return workingorgchartid;
	}

	public int getDenomVal(int denoimination_type) {
		Session session = null;
		int denomval=0;
		try{
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			Object obj=session.createSQLQuery("select IFNULL(denomination_type_manual,0) from denomination_type_manual dtm inner join ticket_type_manual ttm "
							+ "on dtm.ticket_type_manual_id=ttm.ticket_type_manual_id and dtm.ticket_type_manual_id=1 "
							+ "where denomination_type_manual_id='"
							+ denoimination_type
							+ "' order by denomination_type_manual asc").uniqueResult();
			if(obj!=null){
				denomval=Integer.parseInt(obj.toString());
			}
		}catch(Exception e){
		    session.flush();
		    session.close();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return denomval;
	}

	public void delteTicketInvDetails(String denomkey) {
		Session session =null;
		
		int i = 0;
		try {
			session= HibernateUtilTick.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String qry = "update ticket_inventory_details set status='INACTIVE' where ticket_inventory_mst_id='"
					+ denomkey + "'";
			Query query = session.createSQLQuery(qry);
			i = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}
		finally
		{
		session.close();
		}
	}

	public String getDenomlist() {

		List<Map<String, Object>> rolelist = null;
		String result = "";
		try {
			String sql = "select distinct denomination_type_manual_id,denomination_type_manual from denomination_type_manual dtm inner join "
					+ " ticket_type_manual ttm on dtm.ticket_type_manual_id=ttm.ticket_type_manual_id and dtm.ticket_type_manual_id=1 and dtm.status='ACTIVE'"
					+ " AND dtm.deleted_status='0' order by CAST(denomination_type_manual as UNSIGNED)";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			rolelist = query.list();
			if (rolelist.size() > 0) {  
				for (int i = 0; i < rolelist.size(); i++) {
					Map<String, Object> rs = rolelist.get(i);
					String roleid = rs.get("denomination_type_manual_id").toString();
					String rolename = rs.get("denomination_type_manual").toString();
					result += roleid + "@";
					result += rolename + ",";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return result;
		}
	}

	public String getPasslist(String passtype) {
		 
		List<Map<String, Object>> rolelist = null;
		String result = "";
		try {
			String sql = "select distinct denomination_type_manual_id,denomination_type_manual from denomination_type_manual dtm inner join "
					+ " ticket_type_manual ttm on dtm.ticket_type_manual_id=ttm.ticket_type_manual_id and dtm.ticket_type_manual_id="
					+ passtype+ " and dtm.status='ACTIVE'  and dtm.deleted_status='0' order by CAST(denomination_type_manual as UNSIGNED)";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			rolelist = query.list();
			if (rolelist.size() > 0) {

				for (int i = 0; i < rolelist.size(); i++) {
					Map<String, Object> rs = rolelist.get(i);
					String roleid = rs.get("denomination_type_manual_id").toString();
					String rolename = rs.get("denomination_type_manual").toString();
					result += roleid + "@";
					result += rolename + ",";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return result;
		}
	}

	public String getLuggagelist() {
	
		List<Map<String, Object>> rolelist = null;
		String result = "";
		try {
			String sql = "select distinct denomination_type_manual_id,denomination_type_manual from denomination_type_manual dtm inner join "
					+ "ticket_type_manual ttm on dtm.ticket_type_manual_id=ttm.ticket_type_manual_id and dtm.ticket_type_manual_id=3 "
					+ " and dtm.status='ACTIVE' order by denomination_type_manual asc";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			rolelist = query.list();
			if (rolelist.size() > 0) {

				for (int i = 0; i < rolelist.size(); i++) {
					Map<String, Object> rs = rolelist.get(i);
					String roleid = rs.get("denomination_type_manual_id").toString();
					String rolename = rs.get("denomination_type_manual").toString();
					result += roleid + "@";
					result += rolename + ",";
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			return result;
		}
	}
	public JSONObject getPassData(int total, HttpServletRequest request,String col, String dir) {
		JSONObject result = new JSONObject();
		Session session2 = null;
		Common cm=new Common();
		try {
			int totalAfterFilter = total;
			String searchSQL = "";
			session2 = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String currentStatus="";
			int organizationId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			int organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			String sql1="";
			
			if(organizationtype==1){
				
				sql1 = "select dy.denomination_type_manual,a.pass_day,a.ticket_type_manual_id,a.denomination_key, ttm.ticket_type_manual_name,a.opening_number, "
					+ "a.closing_number,a.number_of_tickets, a.number_of_books, a.value,a.priority,a.created_date from "
					+ " (SELECT denomination_type_manual_id, denomination_key, min(opening_number) as opening_number, max(closing_number) "
					+ " as closing_number, sum(number_of_passes) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as "
					+ " value,priority,unit_name,ticket_type_manual_id,pass_day,tid.created_date FROM ticket_inventory_details tid  "
					+ " WHERE tid.status = 'ACTIVE' and tid.current_status='New' and tid.unit_name='"+organizationId +"' " 
					+ " group by tid.denomination_key, tid.denomination_type_manual_id,tid.pass_day,tid.ticket_inventory_mst_id  order by tid.created_date) a "
					+ " inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id "
	 				+ " and a.ticket_type_manual_id in(2,3,6,7)    ";
				
			}else if(organizationtype==2){
				
				currentStatus="Issued";
				sql1="select dy.denomination_type_manual,a.pass_day, a.ticket_type_manual_id,a.denomination_key,a.priority, min(a.opening_number) as opening_number,  " +
					" max(a.closing_number) as closing_number," +
					" sum(a.number_of_passes) as number_of_tickets, sum(a.number_of_books) as number_of_books , " +
					" sum(a.value) as value, a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ," +
					" tim.current_status as current_status, a.priority,tim.created_date from ticket_invoice_master tim inner join ticket_invoice_details tid " +
					" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
					" inner join ticket_inventory_details a on a.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id" +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id in ('2','3','6','7')" +
					" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id =  "+organizationId+" " +
					" AND tim.status = 'ACTIVE' and tim.current_status='"+currentStatus+"'  "  +
					" group by a.denomination_key, a.denomination_type_manual_id  order by CAST(dy.denomination_type_manual AS UNSIGNED), tim.created_date ";
				
			}else if(organizationtype==3){
				
				currentStatus="Issued";
				sql1="select dy.denomination_type_manual,a.pass_day,a.ticket_type_manual_id, a.denomination_key,a.priority, min(a.opening_number) as opening_number,  " +
					" max(a.closing_number) as closing_number," +
					" sum(a.number_of_passes) as number_of_tickets, sum(a.number_of_books) as number_of_books , " +
					" sum(a.value) as value, a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ," +
					" tim.current_status as current_status, a.priority,tim.created_date from ticket_invoice_master tim inner join ticket_invoice_details tid " +
					" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
					" inner join ticket_inventory_details a on a.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id" +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id in ('2','3','6','7')" +
					" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id =  "+organizationId+" " +
					" AND tim.status = 'ACTIVE' and tim.current_status='"+currentStatus+"'  "  +
					" group by a.denomination_key, a.denomination_type_manual_id   ";
				
			}
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
				sql1 += " where dy.denomination_type_manual='" + search_term + "'";
				sql1 += " OR ttm.ticket_type_manual_name like '%" + search_term+ "%'";
				sql1 += " OR a.denomination_key like '%" + search_term + "%'";
				sql1 += " OR a.pass_day like '%" + search_term + "%'";
				sql1 += " OR a.opening_number like '%" + search_term + "%'";
				sql1 += " OR  a.closing_number like '%" + search_term + "%'";
				sql1 += " OR  a.number_of_tickets='" + search_term + "'";
				sql1 += " OR  a.value='" + search_term + "'";
				sql1 += " OR  a.number_of_books='" + search_term + "'";
			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql1 += " order by " + col + " asc";
				} else {
					sql1 += " order by " + col + " desc";
				}
			}else{
				sql1 += " order by ";
				if(organizationtype==1){
					sql1 += " ticket_type_manual_id,CAST(dy.denomination_type_manual AS UNSIGNED),priority ,a.pass_day ";
				}else if(organizationtype==2){
					sql1 += " ticket_type_manual_id,CAST(dy.denomination_type_manual AS UNSIGNED),priority,a.pass_day ";
				}else if(organizationtype==3){
					sql1 += " ticket_type_manual_id,CAST(dy.denomination_type_manual AS UNSIGNED),priority,a.pass_day ";
				}
			}
			
			Query query = session2.createSQLQuery(sql1)
					.addScalar("denomination_type_manual", Hibernate.STRING).addScalar("denomination_key", Hibernate.STRING)
					.addScalar("opening_number", Hibernate.STRING).addScalar("closing_number", Hibernate.STRING)
					.addScalar("number_of_tickets", Hibernate.STRING).addScalar("number_of_books", Hibernate.STRING)
					.addScalar("created_date", Hibernate.STRING).addScalar("priority", Hibernate.STRING)
					.addScalar("value", Hibernate.STRING).addScalar("pass_day",Hibernate.STRING )
					.addScalar("ticket_type_manual_id",Hibernate.STRING).addScalar("ticket_type_manual_name",Hibernate.STRING);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList=null;
			aliasToValueMapList = query.list();
			JSONArray array2 = new JSONArray();
			String TicketTableString = "<div id='passTicketTable'><table>";
			TicketTableString += "<tr><th>Pass Type</th><th>Denom</th><th>Created date</th><th>Denom Key</th><th>Day/Month</th>" +
					"<th>Opening Number</th><th>Closing Number</th><th>No.of Tickets</th><th>No.of Books</th><th>Value</th><th>Priority</th></tr>";
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja2 = new JSONArray();
				ja2.add("<input type='checkbox' class='group-checkable checkboxpass' name='chkbox' data-set='#sample_2 .checkboxes' " +
						" id='"+ rs.get("denomination_key")+ "' "+ 
						" value='"+ rs.get("denomination_key")+ "-"+ rs.get("ticket_type_manual_id")+
						"-"+ Integer.parseInt(rs.get("opening_number").toString())+"-"+rs.get("denomination_type_manual")+"-"+Integer.parseInt(rs.get("closing_number").toString())+"'/>");
				
				TicketTableString += "<tr>";
				TicketTableString += "<td>"+rs.get("ticket_type_manual_name")+"</td>";
				TicketTableString += "<td>"+rs.get("denomination_type_manual")+"</td>";
				TicketTableString += "<td>"+cm.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy")+"</td>";
				TicketTableString += "<td>"+rs.get("denomination_key")+"</td>";
				TicketTableString += "<td>"+rs.get("pass_day")+"</td>";
				TicketTableString += "<td>"+cm.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString()))+"</td>";
				TicketTableString += "<td>"+cm.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString()))+"</td>";
				
				TicketTableString += "<td>"+rs.get("number_of_tickets")+"</td>";
				
				
				ja2.add(rs.get("ticket_type_manual_name"));
				ja2.add(rs.get("denomination_type_manual"));
                ja2.add(cm.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy"));
				ja2.add(rs.get("denomination_key"));
				ja2.add(rs.get("pass_day"));
				ja2.add(cm.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
				ja2.add(cm.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
				ja2.add(rs.get("number_of_tickets"));
				if(rs.get("ticket_type_manual_id").equals("2")){
					ja2.add(rs.get("number_of_books"));
					TicketTableString += "<td>"+rs.get("number_of_books")+"</td>";
				}else {
					ja2.add("");
					TicketTableString += "<td></td>";
				}
				ja2.add(rs.get("value"));
				ja2.add(rs.get("priority"));
				
				TicketTableString += "<td>"+rs.get("value")+"</td>";
				TicketTableString += "<td>"+rs.get("priority")+"</td>";
				
				array2.add(ja2);
				TicketTableString += "</tr>";
			}
			if(aliasToValueMapList.size()==0){
				TicketTableString ="";
			}
			TicketTableString += "</table></div>";
			ServletActionContext.getRequest().getSession().setAttribute("passTicketTableString", TicketTableString);
			result.put("aaData", array2);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session2 != null && session2.isOpen()) {
				session2.close();
				session2 = null;
			}
		}
		return result;
	}

	public JSONObject getLuggageData(int total, HttpServletRequest request,String col, String dir) {
		JSONObject resultlugg = new JSONObject();
		Session session3 = null; 
		try {
			int totalAfterFilter = total;
			session3 = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String currentStatus="";
			int organizationId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			int organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			String sql1="";
			
			if(organizationtype==1){
				
				sql1 = "select dy.denomination_type_manual, a.denomination_key,a.priority, a.opening_number, a.closing_number,"
					+ " a.number_of_tickets, a.number_of_books, a.value,a.ticket_type_manual_id,a.unit_name,a.created_date from (SELECT denomination_type_manual_id,"
					+ " denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number,"
					+ " sum(number_of_passes) as number_of_tickets,priority,sum(number_of_books) as number_of_books,sum(value) as value,"
					+ " unit_type,unit_name,ticket_type_manual_id,tid.created_date FROM ticket_inventory_details tid "
					+ " WHERE tid.status = 'ACTIVE' and tid.current_status='New'  and tid.unit_name='"+organizationId +"'  "
					+ " group by tid.denomination_key, tid.denomination_type_manual_id,tid.ticket_inventory_mst_id  order by tid.created_date) a "
					+ " inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id"
					+ " and a.ticket_type_manual_id=4  ";
				
			}else if(organizationtype==2){
				
				currentStatus="Issued";
				sql1="select dy.denomination_type_manual,a.pass_day,a.ticket_type_manual_id, a.denomination_key,a.priority, min(a.opening_number) as opening_number,  " +
					" max(a.closing_number) as closing_number," +
					" sum(a.number_of_passes) as number_of_tickets, sum(a.number_of_books) as number_of_books , " +
					" sum(a.value) as value, a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ," +
					" tim.current_status as current_status, a.priority,tim.created_date from ticket_invoice_master tim inner join ticket_invoice_details tid " +
					" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
					" inner join ticket_inventory_details a on a.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id" +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=4" +
					" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id =  "+organizationId+" " +
					" AND tim.status = 'ACTIVE' and tim.current_status='"+currentStatus+"'  "  +
					" group by a.denomination_key, a.denomination_type_manual_id   ";
				
			}else if(organizationtype==3){
				
				currentStatus="Issued";
				sql1="select dy.denomination_type_manual,a.pass_day, a.ticket_type_manual_id,a.denomination_key,a.priority, min(a.opening_number) as opening_number,  " +
					" max(a.closing_number) as closing_number," +
					" sum(a.number_of_passes) as number_of_tickets, sum(a.number_of_books) as number_of_books , " +
					" sum(a.value) as value, a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ," +
					" tim.current_status as current_status, a.priority,tim.created_date from ticket_invoice_master tim inner join ticket_invoice_details tid " +
					" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
					" inner join ticket_inventory_details a on a.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id" +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=4" +
					" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id =  "+organizationId+" " +
					" AND tim.status = 'ACTIVE' and tim.current_status='"+currentStatus+"'  "  +
					" group by a.denomination_key, a.denomination_type_manual_id   ";
				
			}
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
				
				sql1 += " where dy.denomination_type_manual='"+ search_term + "'";
				sql1 += " OR a.denomination_key like '%" + search_term + "%'";
				sql1 += " OR a.opening_number like '%" + search_term + "%'";
				sql1 += " OR a.closing_number like '%" + search_term + "%'";
				sql1 += " OR a.number_of_tickets='" + search_term + "'";
				sql1 += " OR a.value='" + search_term + "'";
				sql1 += " OR a.number_of_books='" + search_term + "'";
			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql1 += " order by " + col + " asc,";
				} else {
					sql1 += " order by " + col + " desc,";
				}
			}else{
				sql1 += " order by ";
			}
			if(organizationtype==1){
				sql1 += "  a.created_date ";
				}else if(organizationtype==2){
					sql1 += " a.denomination_key,priority ";
				}else if(organizationtype==3){
					sql1 += " a.denomination_key,priority ";
				}
			
			Query query = session3.createSQLQuery(sql1)
					     .addScalar("denomination_type_manual", Hibernate.STRING).addScalar("value", Hibernate.STRING)
						 .addScalar("denomination_key", Hibernate.STRING).addScalar("ticket_type_manual_id",Hibernate.STRING)
					     .addScalar("opening_number", Hibernate.STRING).addScalar("closing_number", Hibernate.STRING)
					     .addScalar("number_of_tickets", Hibernate.STRING).addScalar("number_of_books", Hibernate.STRING)
					     .addScalar("created_date", Hibernate.STRING).addScalar("priority", Hibernate.STRING);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList=null;
		    aliasToValueMapList = query.list();
			JSONArray array1 = new JSONArray();

			String TicketTableString = "<div id='luggageTicketTable'><table>";
			TicketTableString += "<tr><th>Created date</th><th>Lugguage Key</th><th>Opening Number</th><th>Closing Number</th>" +
					"<th>No.of Tickets</th><th>No.of Books</th><th>Priority</th></tr>";
			
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja1 = new JSONArray();
				ja1.add("<input type='checkbox' class='group-checkable checkboxlugg' name='chkbox' data-set='#sample_2 .checkboxes'"
						+ " id='"+ rs.get("denomination_key")+"' " 
						+ " value='"+ rs.get("denomination_key")+ "-"+ rs.get("ticket_type_manual_id")+"-"
						+ Integer.parseInt(rs.get("opening_number").toString())+"-0-"+Integer.parseInt(rs.get("closing_number").toString())+"'/>");
				Common cm=new Common();
				ja1.add(cm.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy"));
				ja1.add(rs.get("denomination_key"));
				ja1.add(cm.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
				ja1.add(cm.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
				ja1.add(rs.get("number_of_tickets"));
				ja1.add(rs.get("number_of_books"));
				ja1.add(rs.get("priority"));
				
				TicketTableString += "<tr>";
				TicketTableString += "<td>"+cm.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy")+"</td>";
				TicketTableString += "<td>"+rs.get("denomination_key")+"</td>";
				TicketTableString += "<td>"+cm.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString()))+"</td>";
				TicketTableString += "<td>"+cm.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString()))+"</td>";
				TicketTableString += "<td>"+rs.get("number_of_tickets")+"</td>";
				TicketTableString += "<td>"+rs.get("number_of_books")+"</td>";
				TicketTableString += "<td>"+rs.get("priority")+"</td>";
				TicketTableString += "</tr>";
				
				array1.add(ja1);
			}
			if(aliasToValueMapList.size()==0){
				TicketTableString = "";
			}
			TicketTableString += "</table></div>";
			ServletActionContext.getRequest().getSession().setAttribute("luggageTicketTable", TicketTableString);
			resultlugg.put("aaData", array1);
			resultlugg.put("iTotalRecords", total);
			resultlugg.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null && session3.isOpen()) {
				session3.close();
				session3 = null;
			}
			return resultlugg;
		}
		
	}
	
	public JSONObject getTsheetData(int total, HttpServletRequest request,String col, String dir) {
		JSONObject resultlugg = new JSONObject();
		Session session3 = null; 
		try {
			int totalAfterFilter = total;
			session3 = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String currentStatus="";
			int organizationId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
			int organizationtype = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
			String sql1="";
			
			if(organizationtype==1){
				
				sql1 = "select dy.denomination_type_manual, a.denomination_key, a.opening_number, a.closing_number,"
					+ " a.number_of_passes, a.ticket_type_manual_id,a.unit_name,a.created_date from (SELECT denomination_type_manual_id,"
					+ " denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number,"
					+ " sum(number_of_passes) as number_of_passes,"
					+ " unit_type,unit_name,ticket_type_manual_id,tid.created_date FROM ticket_inventory_details tid "
					+ " WHERE tid.status = 'ACTIVE' and tid.current_status='New'  and tid.unit_name='"+organizationId +"'  "
					+ " group by tid.denomination_key, tid.denomination_type_manual_id,tid.ticket_inventory_mst_id  order by tid.created_date) a "
					+ " inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id"
					+ " and a.ticket_type_manual_id=5  ";
				
			}else if(organizationtype==2){
				
				currentStatus="Issued";
				sql1="select dy.denomination_type_manual,a.ticket_type_manual_id, a.denomination_key, min(a.opening_number) as opening_number,  " +
					" max(a.closing_number) as closing_number," +
					" sum(a.number_of_passes) as number_of_passes,  " +
					"  a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ," +
					" tim.current_status as current_status, tim.created_date from ticket_invoice_master tim inner join ticket_invoice_details tid " +
					" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
					" inner join ticket_inventory_details a on a.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id" +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=5" +
					" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id =  "+organizationId+" " +
					" AND tim.status = 'ACTIVE' and tim.current_status='"+currentStatus+"'  "  +
					" group by a.denomination_key, a.denomination_type_manual_id   ";
				
			}else if(organizationtype==3){
				
				currentStatus="Issued";
				sql1="select dy.denomination_type_manual, a.ticket_type_manual_id,a.denomination_key, min(a.opening_number) as opening_number,  " +
					" max(a.closing_number) as closing_number," +
					" sum(a.number_of_passes) as number_of_passes,  " +
					"  a.ticket_type_manual_id,a.unit_name,ttm.ticket_type_manual_name ," +
					" tim.current_status as current_status, tim.created_date from ticket_invoice_master tim inner join ticket_invoice_details tid " +
					" on tid.ticket_invoice_mast_id=tim.ticket_invoice_mast_id " +
					" inner join ticket_inventory_details a on a.ticket_inventory_det_id = tid.ticket_inventory_det_id  " +
					" inner join denomination_type_manual dy on dy.denomination_type_manual_id= a.denomination_type_manual_id" +
					" inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id and a.ticket_type_manual_id=5" +
					" where tim.issue_to_unit = '"+organizationtype+"' AND tim.issue_to_unit_id =  "+organizationId+" " +
					" AND tim.status = 'ACTIVE' and tim.current_status='"+currentStatus+"'  "  +
					" group by a.denomination_key, a.denomination_type_manual_id   ";
				
			}
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
				
				sql1 += " where dy.denomination_type_manual='"+ search_term + "'";
				sql1 += " OR a.denomination_key like '%" + search_term + "%'";
				sql1 += " OR a.opening_number like '%" + search_term + "%'";
				sql1 += " OR a.closing_number like '%" + search_term + "%'";
				sql1 += " OR a.number_of_passes='" + search_term + "'";
				
			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql1 += " order by " + col + " asc,";
				} else {
					sql1 += " order by " + col + " desc,";
				}
			}else{
				sql1 += " order by ";
			}
			if(organizationtype==1){
				sql1 += "  a.created_date ";
				}else if(organizationtype==2){
					sql1 += " a.denomination_key, tim.created_date ";
				}else if(organizationtype==3){
					sql1 += " a.denomination_key, tim.created_date ";
				}
			
			Query query = session3.createSQLQuery(sql1)
					     .addScalar("denomination_type_manual", Hibernate.STRING)
						 .addScalar("denomination_key", Hibernate.STRING).addScalar("ticket_type_manual_id",Hibernate.STRING)
					     .addScalar("opening_number", Hibernate.STRING).addScalar("closing_number", Hibernate.STRING)
					     .addScalar("number_of_passes", Hibernate.STRING)
					     .addScalar("created_date", Hibernate.STRING);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList=null;
		    aliasToValueMapList = query.list();
			JSONArray array1 = new JSONArray();

			String TicketTableString = "<div id='tripTicketTable'><table>";
			TicketTableString += "<tr><th>Created date</th><th>Waybill Key</th><th>Opening Number</th><th>Closing Number</th>" +
					"<th>No.of Sheets</th></tr>";
			
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja1 = new JSONArray();
				ja1.add("<input type='checkbox' class='group-checkable checkboxtsheet' name='chkbox' data-set='#sample_2 .checkboxes'"
						+ " id='"+ rs.get("denomination_key")+"' " 
						+ " value='"+ rs.get("denomination_key")+ "-"+ rs.get("ticket_type_manual_id")+"-"
						+ Integer.parseInt(rs.get("opening_number").toString())+"-0-"+Integer.parseInt(rs.get("closing_number").toString())+"'/>");
				Common cm=new Common();
				ja1.add(cm.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy"));
				ja1.add(rs.get("denomination_key"));
				ja1.add(cm.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString())));
				ja1.add(cm.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString())));
				ja1.add(rs.get("number_of_passes"));
				
				TicketTableString += "<tr>";
				TicketTableString += "<td>"+cm.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy")+"</td>";
				TicketTableString += "<td>"+rs.get("denomination_key")+"</td>";
				TicketTableString += "<td>"+cm.convertToPaddingString(Integer.parseInt(rs.get("opening_number").toString()))+"</td>";
				TicketTableString += "<td>"+cm.convertToPaddingString(Integer.parseInt(rs.get("closing_number").toString()))+"</td>";
				TicketTableString += "<td>"+rs.get("number_of_passes")+"</td>";
				TicketTableString += "</tr>";
				
				
				array1.add(ja1);
			}
			if(aliasToValueMapList.size()==0){
				TicketTableString = "";
			}
			TicketTableString += "</table></div>";
			ServletActionContext.getRequest().getSession().setAttribute("tripTicketTable", TicketTableString);

			resultlugg.put("aaData", array1);
			resultlugg.put("iTotalRecords", total);
			resultlugg.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null && session3.isOpen()) {
				session3.close();
				session3 = null;
			}
			return resultlugg;
		}
		
	}

	public String getPrintData(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String printString = "<center><h3>Banglore Metropoliton Transport Corporation</h3>";
		printString += "<h4> Central Stock </h4>";/*of : "+setCurrentOrgName()+"*/
		printString += "<h4> Date : "+(new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").format(new Date()))+"</h4>";
		printString += "<br/><br/>"+session.getAttribute("normalTicketTableString");
		printString += "<br/><br/>"+session.getAttribute("passTicketTableString");
		printString += "<br/><br/>"+session.getAttribute("luggageTicketTable");
		printString += "<br/><br/>"+session.getAttribute("tripTicketTable")+"<center>";
		return printString;
	}
	public int getTotalIssuedPassRecords(String arr) {
		Session sessionissuepass = null;
		int cnt = 0;

		try {
			sessionissuepass = HibernateUtilTick.getSession("hibernate.cfg.xml");
			arr = arr.substring(0, arr.length() - 1);
			String strkey = "";
			String strdenomtype = "";
			String s[] = arr.split(",");
			for (int i = 0; i < s.length; i++) {
				String key = s[i].substring(0, s[i].indexOf("-"));

				String denomtype = s[i].substring(s[i].indexOf("-") + 1,
						s[i].length())
						+ ",";
				int denompass = Integer.parseInt(s[i].substring(
						s[i].indexOf("-") + 1, s[i].length()));
				if ((denompass == 2) || (denompass == 3)) {
					strkey += "'" + key + "',";
					strdenomtype += denomtype;
				}
			}
			if (strkey.equalsIgnoreCase("")
					&& strdenomtype.equalsIgnoreCase("")) {
				strkey = "'" + "'";
				strdenomtype = "'" + "'";
			} else {
				strkey = strkey.substring(0, strkey.length() - 1);
				strdenomtype = strdenomtype.substring(0,
						strdenomtype.length() - 1);
			}
			/*String sql = "select count(*) as count from(select a.ticket_inventory_mst_id,dy.denomination_type_manual, a.denomination_key, a.ticket_type_manual_id,"
					+ " a.opening_number, a.closing_number,  a.number_of_tickets, a.number_of_books,"
					+ " a.value,a.unit_type,a.unit_name from ("
					+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
					+ " opening_number, closing_number,  number_of_tickets,number_of_books, value,unit_type,unit_name,ticket_type_manual_id "
					+ " FROM ticket_inventory_details tid  WHERE tid.denomination_key in ("
					+ strkey
					+ ") and tid.partial_book = 'Y' "
					+ " and tid.status = 'ACTIVE' and tid.current_status='New'  union all "
					+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id,"
					+ " denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as "
					+ " number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,ticket_type_manual_id "
					+ " FROM ticket_inventory_details tid "
					+ " WHERE tid.denomination_key in ("
					+ strkey
					+ ") and tid.partial_book = 'N' and tid.status = 'ACTIVE' and tid.current_status='New' "
					+ " group by tid.denomination_key, tid.denomination_type_manual_id ) a  inner join denomination_type_manual dy on "
					+ " dy.denomination_type_manual_id= a.denomination_type_manual_id "
					+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id "
					+ " and a.ticket_type_manual_id in("
					+ strdenomtype
					+ ") "
					+ " order by a.denomination_key, a.number_of_tickets) b";*/
			

			String sql = "select a.ticket_inventory_mst_id,dy.denomination_type_manual,ttm.ticket_type_manual_name, a.denomination_key, "
					+ " a.ticket_type_manual_id,a.opening_number, a.closing_number, a.pass_day, a.number_of_tickets, a.number_of_books, "
					+ " a.value,a.priority,a.unit_name,a.priority from ( "
					+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id,pass_day, denomination_key, min(opening_number) "
					+ " as opening_number, max(closing_number) as closing_number, sum(number_of_passes) as  number_of_tickets,sum(number_of_books) "
					+ " as number_of_books,sum(value) as value,unit_type,unit_name,ticket_type_manual_id,priority  FROM ticket_inventory_details tid  "
					+ " WHERE tid.denomination_key in ("
					+ strkey
					+ ") and tid.status = 'ACTIVE' and tid.current_status='New'  "
					+ " group by tid.denomination_key, tid.denomination_type_manual_id ) a  inner join denomination_type_manual dy on "
					+ " dy.denomination_type_manual_id= a.denomination_type_manual_id  inner join ticket_type_manual "
					+ " ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id  and a.ticket_type_manual_id in("
					+ strdenomtype + ")  ";

		
			/*Query query = sessionissuepass.createSQLQuery(sql).addScalar(
					"count", Hibernate.INTEGER);*/
			
			Query query = sessionissuepass.createSQLQuery(sql);
			
			List<Integer> list = query.list();
			cnt = list.size();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (sessionissuepass != null && sessionissuepass.isOpen()) {
				sessionissuepass.close();
				sessionissuepass = null;
			}
		}
		return cnt;

	}

	

	public int getTotalIssuedLuggageRecords(String arr) {
		Session sessionluggissue = null;
		int cnt = 0;
		try {
			sessionluggissue = HibernateUtilTick.getSession("hibernate.cfg.xml");

			arr = arr.substring(0, arr.length() - 1);
			String strkey = "";
			String strdenomtype = "";
			String s[] = arr.split(",");
			for (int i = 0; i < s.length; i++) {
				String key = s[i].substring(0, s[i].indexOf("-"));

				String denomtype = s[i].substring(s[i].indexOf("-") + 1,
						s[i].length())
						+ ",";
				int denompass = Integer.parseInt(s[i].substring(
						s[i].indexOf("-") + 1, s[i].length()));
				if (denompass == 4) {
					strkey += "'" + key + "',";
					strdenomtype += denomtype;
				}
			}
			if (strkey.equalsIgnoreCase("")
					&& strdenomtype.equalsIgnoreCase("")) {
				strkey = "'" + "'";
				strdenomtype = "'" + "'";
			} else {
				strkey = strkey.substring(0, strkey.length() - 1);
				strdenomtype = strdenomtype.substring(0,
						strdenomtype.length() - 1);
			}
			String sql = "select a.ticket_inventory_mst_id,a.denomination_key, a.ticket_type_manual_id,"
					+ " a.opening_number, a.closing_number,  a.number_of_tickets, a.number_of_books,"
					+ " a.value,a.unit_type,a.unit_name,a.priority from ("
					+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
					+ " min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as "
					+ " number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,priority,"
					+ " ticket_type_manual_id  FROM ticket_inventory_details tid  WHERE tid.denomination_key in ("
					+ strkey
					+ ") and "
					+ " tid.status = 'ACTIVE' and tid.current_status='New'  group by tid.denomination_key, tid.denomination_type_manual_id ) a "
					+ " inner join denomination_type_manual dy on  dy.denomination_type_manual_id= a.denomination_type_manual_id "
	 				+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id  and a.ticket_type_manual_id in("
					+ strdenomtype + ") "+
			       " order by a.denomination_key, a.number_of_tickets";

		
			/*Query query = sessionluggissue.createSQLQuery(sql).addScalar(
					"count", Hibernate.INTEGER);*/
			
			Query query = sessionluggissue.createSQLQuery(sql);
			
			List<Integer> list = query.list();
			cnt = list.size();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessionluggissue != null && sessionluggissue.isOpen()) {
				sessionluggissue.close();
			}

		}

		return cnt;

	}

	public int getPassVal(int denoimination_type) {
		Session session = null;
		int cnt = 0;
		int workingorgchartid = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			workingorgchartid = Integer
					.parseInt(session
							.createSQLQuery(
									"select denomination_type_manual from denomination_type_manual dtm inner join ticket_type_manual ttm "
											+ "on dtm.ticket_type_manual_id=ttm.ticket_type_manual_id and dtm.ticket_type_manual_id in(2,3,6,7) "
											+ "where denomination_type_manual_id='"
											+ denoimination_type
											+ "' order by denomination_type_manual asc")
							.uniqueResult().toString());
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				//session.flush();
				session.close();
			}

		}

		// TODO Auto-generated method stub
		return workingorgchartid;
	}

	
	


	public int getTotalLuggageDeleteRecords(String arr) {
		Session session = null;
		int cnt = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			arr = arr.substring(0, arr.length() - 1);
			String strkey = "";
			String strdenomtype = "";
			String s[] = arr.split(",");
			for (int i = 0; i < s.length; i++) {
				String key = s[i].substring(0, s[i].indexOf("-"));
				String denomtype = s[i].substring(s[i].indexOf("-") + 1,
						s[i].length())
						+ ",";
				if (Integer.parseInt(s[i].substring(s[i].indexOf("-") + 1,
						s[i].length())) == 4) {
					strdenomtype += denomtype;
					strkey += "'" + key + "',";
				}
			}
			if (strkey.equalsIgnoreCase("")
					&& strdenomtype.equalsIgnoreCase("")) {
				strkey = "'" + "'";
				strdenomtype = "'" + "'";
			} else {
				strkey = strkey.substring(0, strkey.length() - 1);
				strdenomtype = strdenomtype.substring(0,
						strdenomtype.length() - 1);
			}
			String sql1 = "select count(*) as count,ticket_inventory_mst_id,dy.denomination_type_manual,"
					+ " denomination_key,opening_number,closing_number,number_of_books,value,priority "
					+ " from ticket_inventory_master tm inner join denomination_type_manual dy "
					+ " on tm.denoimination_type_manual_id=dy.denomination_type_manual_id inner join ticket_type_manual ttm on ttm.ticket_type_manual_id=tm.ticket_type_manual_id	 "
					+ " where tm.current_status='New' and ttm.ticket_type_manual_id in("
					+ strdenomtype
					+ ")"
					+ " and tm.status='ACTIVE' and denomination_key in("
					+ strkey + ")";
			/*
			 * String sql=
			 * "SELECT count(*) as count from(select  dy.denomination_type, a.denomination_key, a.opening_number, a.closing_number,"
			 * +
			 * " a.number_of_tickets, a.number_of_books, a.value,a.unit_type,a.unit_name from (SELECT denoimination_type_id, denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, "
			 * +
			 * " sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name "
			 * + " FROM ticket_inventory_details tid " +
			 * " WHERE tid.denomination_key in ("
			 * +strkey+") and tid.status = 'ACTIVE' and tid.current_status='New' "
			 * +
			 * " group by tid.denomination_key, tid.denoimination_type_id ) a "
			 * +
			 * " inner join denomination_type dy on dy.denomination_type_id= a.denoimination_type_id "
			 * + "order by a.denomination_key, a.number_of_tickets) b ";
			 */
			Query query = session.createSQLQuery(sql1).addScalar("count",
					Hibernate.INTEGER);
			;

			List<Integer> list = query.list();
			cnt = list.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}

		}
		return cnt;

	}

	public JSONObject getLuggageDeleteData(int total,
			HttpServletRequest request, String arr, String col, String dir) {
		String str = arr;
		Session session = null;
		JSONObject result = new JSONObject();
		arr = arr.substring(0, arr.length() - 1);
		String strkey = "";
		String strdenomtype = "";
		String s[] = arr.split(",");
		for (int i = 0; i < s.length; i++) {
			String key = s[i].substring(0, s[i].indexOf("-"));

			String denomtype = s[i].substring(s[i].indexOf("-") + 1,
					s[i].length())
					+ ",";
			if (Integer.parseInt(s[i].substring(s[i].indexOf("-") + 1,
					s[i].length())) == 4) {
				strdenomtype += denomtype;
				strkey += "'" + key + "',";
			}

		}
		if (strkey.equalsIgnoreCase("") && strdenomtype.equalsIgnoreCase("")) {
			strkey = "'" + "'";
			strdenomtype = "'" + "'";
		} else {
			strkey = strkey.substring(0, strkey.length() - 1);
			strdenomtype = strdenomtype.substring(0, strdenomtype.length() - 1);
		}
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from TicketInventory";

			// sql += " order by " + COL_NAME + " " + DIR;

			// sql += " limit " + request.getParameter("iDisplayStart") + ", "
			// + request.getParameter("iDisplayLength");
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String sql1 = "select ticket_inventory_mst_id,dy.denomination_type_manual,"
					+ " denomination_key,opening_number,closing_number,number_of_books,value,priority "
					+ " from ticket_inventory_master tm inner join denomination_type_manual dy "
					+ " on tm.denoimination_type_manual_id=dy.denomination_type_manual_id inner join ticket_type_manual ttm on ttm.ticket_type_manual_id=tm.ticket_type_manual_id	 "
					+ " where tm.current_status='New' and ttm.ticket_type_manual_id in("
					+ strdenomtype
					+ ")"
					+ " and tm.status='ACTIVE' and denomination_key in("
					+ strkey + ")";
			/*
			 * String sql1=
			 * "select  dy.denomination_type, a.denomination_key, a.opening_number, a.closing_number,a.priority,"
			 * +
			 * " a.number_of_tickets, a.number_of_books, a.value,a.unit_type,a.unit_name from (SELECT denoimination_type_id, denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, "
			 * +
			 * " sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,priority "
			 * + " FROM ticket_inventory_details tid " +
			 * " WHERE tid.denomination_key in("
			 * +strkey+") and tid.status = 'ACTIVE' and tid.current_status='New' "
			 * +
			 * " group by tid.denomination_key, tid.denoimination_type_id ) a "
			 * +
			 * " inner join denomination_type dy on dy.denomination_type_id= a.denoimination_type_id "
			 * + "order by a.denomination_key, a.number_of_tickets";
			 */
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {


				sql1 += " and ticket_inventory_mst_id='" + search_term + "'";
				sql1 += " OR dy.denomination_type='" + search_term + "'";
				sql1 += " OR denomination_key like '%" + search_term + "%'";
				sql1 += " OR  opening_number='" + search_term + "'";
				sql1 += " OR  closing_number='" + search_term + "'";
				sql1 += " OR  number_of_books='" + search_term + "'";
				sql1 += " OR  value='" + search_term + "'";
				sql1 += " OR  priority='" + search_term + "'";

			}
			
			try{
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					// criteria.addOrder(Order.asc(col));
					sql += " order by " + col + " asc";
				} else {
					// criteria.addOrder(Order.desc(col));
					sql += " order by " + col + " desc";
				}
			}
			}
			catch(Exception e){
			
			}
			
			
			Query query = session.createSQLQuery(sql1);

			/*
			 * query.setFirstResult(Integer.parseInt(request
			 * .getParameter("iDisplayStart")));
			 */
			/*
			 * query.setMaxResults(Integer.parseInt(request
			 * .getParameter("iDisplayLength")));
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			// System.out.println("List size----->" + list.size() +
			// list.get(0).getValue()+"\t"
			// + request.getParameter("iDisplayStart"));
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();

				// ja.add(rs.get("ticket_inventory_mst_id"));
				ja.add(rs.get("ticket_inventory_mst_id"));

				ja.add(rs.get("denomination_type_manual"));
				/*
				 * ja.add(rs.get("pass_type")); ja.add(rs.get("service_type"));
				 */

				ja.add(rs.get("denomination_key"));
				ja.add(rs.get("opening_number"));
				ja.add(rs.get("closing_number"));
				// ja.add(rs.get("number_of_tickets"));
				ja.add(rs.get("number_of_books"));
				ja.add(rs.get("value"));
				// ja.add(rs.get("unit_name"));
				// ja.add(rs.get("unit_type"));
				ja.add(rs.get("priority"));
				ja.add("<a href='#' class='deleteluggage' id='luggage_delete_"
						+ rs.get("ticket_inventory_mst_id") + "' value='"
						+ rs.get("ticket_inventory_mst_id") + "'> Delete</a>");
				ja.add("");
				// ja.add("<a class='deletetkt' id='floor_delete_"+
				// rs.get("denomination_key")+ "' value='"+
				// rs.get("denomination_key") + "'> Delete</a>");

				/*
				 * String creattedDate = list.get(i).getCreated_date() != null ?
				 * list .get(i).getCreated_date().toString() : "";
				 * ja.add(creattedDate.replace(".0", ""));
				 */

				array.add(ja);
			}

			totalAfterFilter = getTotalLuggageDeleteRecords(str);
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			/*
			 * System.out.println("REsult ------>" +
			 * request.getParameter("iDisplayStart"));
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return result;
		}

	}

	public int getTotalDeletePassRecords(String arr) {
		Session session = null;
		int cnt = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			arr = arr.substring(0, arr.length() - 1);
			String strkey = "";
			String strdenomtype = "";
			String s[] = arr.split(",");
			for (int i = 0; i < s.length; i++) {
				String key = s[i].substring(0, s[i].indexOf("-"));

				String denomtype = s[i].substring(s[i].indexOf("-") + 1,
						s[i].length())
						+ ",";

				int denompass = Integer.parseInt(s[i].substring(
						s[i].indexOf("-") + 1, s[i].length()));
				if ((denompass == 2) || (denompass == 3)) {
					strkey += "'" + key + "',";
					strdenomtype += denomtype;
				}

			}
			if (strkey.equalsIgnoreCase("")
					&& strdenomtype.equalsIgnoreCase("")) {
				strkey = "'" + "'";
				strdenomtype = "'" + "'";
			} else {
				strkey = strkey.substring(0, strkey.length() - 1);
				strdenomtype = strdenomtype.substring(0,
						strdenomtype.length() - 1);
				System.out.println(strkey + "denomination type" + strdenomtype);
			}
			String sql1 = "select count(*) as count,ticket_inventory_mst_id,dy.denomination_type_manual,"
					+ " denomination_key,opening_number,closing_number,number_of_books,value,priority "
					+ " from ticket_inventory_master tm inner join denomination_type_manual dy "
					+ " on tm.denoimination_type_manual_id=dy.denomination_type_manual_id inner join ticket_type_manual ttm on ttm.ticket_type_manual_id=tm.ticket_type_manual_id	 "
					+ " where tm.current_status='New' and ttm.ticket_type_manual_id in("
					+ strdenomtype
					+ ")"
					+ " and tm.status='ACTIVE' and denomination_key in("
					+ strkey + ")";
			/*
			 * String sql=
			 * "SELECT count(*) as count from(select  dy.denomination_type, a.denomination_key, a.opening_number, a.closing_number,"
			 * +
			 * " a.number_of_tickets, a.number_of_books, a.value,a.unit_type,a.unit_name from (SELECT denoimination_type_id, denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, "
			 * +
			 * " sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name "
			 * + " FROM ticket_inventory_details tid " +
			 * " WHERE tid.denomination_key in ("
			 * +strkey+") and tid.status = 'ACTIVE' and tid.current_status='New' "
			 * +
			 * " group by tid.denomination_key, tid.denoimination_type_id ) a "
			 * +
			 * " inner join denomination_type dy on dy.denomination_type_id= a.denoimination_type_id "
			 * + "order by a.denomination_key, a.number_of_tickets) b ";
			 */
			Query query = session.createSQLQuery(sql1).addScalar("count",
					Hibernate.INTEGER);
			;

			List<Integer> list = query.list();
			cnt = list.get(0);
			System.out.println(cnt);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}

		}
		return cnt;
	}

	public JSONObject getDeletePassData(int total, HttpServletRequest request,
			String arr, String col, String dir) {
		// TODO Auto-generated method stub
		String str = arr;
		Session session = null;
		JSONObject result = new JSONObject();
		arr = arr.substring(0, arr.length() - 1);
		String strkey = "";
		String strdenomtype = "";
		String s[] = arr.split(",");
		for (int i = 0; i < s.length; i++) {
			String key = s[i].substring(0, s[i].indexOf("-"));

			String denomtype = s[i].substring(s[i].indexOf("-") + 1,
					s[i].length())
					+ ",";

			int denompass = Integer.parseInt(s[i].substring(
					s[i].indexOf("-") + 1, s[i].length()));
			if ((denompass == 2) || (denompass == 3)) {
				strkey += "'" + key + "',";
				strdenomtype += denomtype;
			}

		}
		if (strkey.equalsIgnoreCase("") && strdenomtype.equalsIgnoreCase("")) {
			strkey = "'" + "'";
			strdenomtype = "'" + "'";
		} else {
			strkey = strkey.substring(0, strkey.length() - 1);
			strdenomtype = strdenomtype.substring(0, strdenomtype.length() - 1);
		}
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from TicketInventory";

			// sql += " order by " + COL_NAME + " " + DIR;

			// sql += " limit " + request.getParameter("iDisplayStart") + ", "
			// + request.getParameter("iDisplayLength");
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String sql1 = "select ticket_inventory_mst_id,pass_day,ttm.ticket_type_manual_name,number_of_passes,dy.denomination_type_manual,"
					+ " denomination_key,opening_number,closing_number,number_of_books,value,priority "
					+ " from ticket_inventory_master tm inner join denomination_type_manual dy "
					+ " on tm.denoimination_type_manual_id=dy.denomination_type_manual_id inner join ticket_type_manual ttm on ttm.ticket_type_manual_id=tm.ticket_type_manual_id	 "
					+ " where tm.current_status='New' and ttm.ticket_type_manual_id in("
					+ strdenomtype
					+ ")"
					+ " and tm.status='ACTIVE' and denomination_key in("
					+ strkey + ")";
			/*
			 * String sql1=
			 * "select  dy.denomination_type, a.denomination_key, a.opening_number, a.closing_number,a.priority,"
			 * +
			 * " a.number_of_tickets, a.number_of_books, a.value,a.unit_type,a.unit_name from (SELECT denoimination_type_id, denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, "
			 * +
			 * " sum(number_of_tickets) as number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,priority "
			 * + " FROM ticket_inventory_details tid " +
			 * " WHERE tid.denomination_key in("
			 * +strkey+") and tid.status = 'ACTIVE' and tid.current_status='New' "
			 * +
			 * " group by tid.denomination_key, tid.denoimination_type_id ) a "
			 * +
			 * " inner join denomination_type dy on dy.denomination_type_id= a.denoimination_type_id "
			 * + "order by a.denomination_key, a.number_of_tickets";
			 */
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {

				System.out.println("search_term---------" + search_term);

				sql1 += " and ticket_inventory_mst_id='" + search_term + "'";
				sql1 += " OR dy.denomination_type='" + search_term + "'";
				sql1 += " OR denomination_key like '%" + search_term + "%'";
				sql1 += " OR  opening_number='" + search_term + "'";
				sql1 += " OR  closing_number='" + search_term + "'";
				sql1 += " OR  number_of_books='" + search_term + "'";
				sql1 += " OR  value='" + search_term + "'";
				sql1 += " OR  priority='" + search_term + "'";

			}
			
			try{
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					// criteria.addOrder(Order.asc(col));
					sql += " order by " + col + " asc";
				} else {
					// criteria.addOrder(Order.desc(col));
					sql += " order by " + col + " desc";
				}
			}
			}
			catch(Exception e){
			
			}
			
			Query query = session.createSQLQuery(sql1);

			/*
			 * query.setFirstResult(Integer.parseInt(request
			 * .getParameter("iDisplayStart")));
			 */
			/*
			 * query.setMaxResults(Integer.parseInt(request
			 * .getParameter("iDisplayLength")));
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			// List<TicketInventory> list =(List<TicketInventory>) query.list();
			JSONArray array = new JSONArray();
			// System.out.println("List size----->" + list.size() +
			// list.get(0).getValue()+"\t"
			// + request.getParameter("iDisplayStart"));
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();

				// ja.add(rs.get("ticket_inventory_mst_id"));
				ja.add(rs.get("ticket_inventory_mst_id"));
				ja.add(rs.get("ticket_type_manual_name"));

				ja.add(rs.get("denomination_type_manual"));
				/*
				 * ja.add(rs.get("pass_type")); ja.add(rs.get("service_type"));
				 */

				ja.add(rs.get("denomination_key"));
				ja.add(rs.get("pass_day"));
				ja.add(rs.get("opening_number"));
				ja.add(rs.get("closing_number"));
				// ja.add(rs.get("number_of_tickets"));
				ja.add(rs.get("number_of_books"));
				ja.add(rs.get("number_of_passes"));
				ja.add(rs.get("value"));
				// ja.add(rs.get("unit_name"));
				// ja.add(rs.get("unit_type"));
				ja.add(rs.get("priority"));
				ja.add("<a href='#' class='deletepass' id='pass_delete_"
						+ rs.get("ticket_inventory_mst_id") + "' value='"
						+ rs.get("ticket_inventory_mst_id") + "'> Delete</a>");
				ja.add("");
				// ja.add("<a class='deletetkt' id='floor_delete_"+
				// rs.get("denomination_key")+ "' value='"+
				// rs.get("denomination_key") + "'> Delete</a>");

				/*
				 * String creattedDate = list.get(i).getCreated_date() != null ?
				 * list .get(i).getCreated_date().toString() : "";
				 * ja.add(creattedDate.replace(".0", ""));
				 */

				array.add(ja);
			}

			totalAfterFilter = getTotalDeletePassRecords(str);
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			/*
			 * System.out.println("REsult ------>" +
			 * request.getParameter("iDisplayStart"));
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return result;
		}

	}

	public int checkFordeleteTicketInvMst(String tickinvmstd) {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer.parseInt(session
					.createSQLQuery(
							"select count(*) from  ticket_inventory_details where ticket_inventory_mst_id="
									+ tickinvmstd
									+ "' and current_status='Issued'")
					.uniqueResult().toString());
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return count;
		}

	}
	public int getTotalNumberOfBooks() {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer.parseInt(session
					.createSQLQuery(
							"select ifnull(sum(number_of_books),0) from  ticket_inventory_details where ticket_type_manual_id=1 and current_status='New' and status='ACTIVE'")
					.uniqueResult().toString());
			System.out.println(count);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return count;
		}

	} 
	public int getTotalTicketValue() {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer.parseInt(session
					.createSQLQuery(
							"select ifnull(sum(value),0) from  ticket_inventory_details where ticket_type_manual_id=1 and current_status='New' and status='ACTIVE'")
					.uniqueResult().toString());
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return count;
		}

	}
	public int getTotalPassValue() {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer.parseInt(session
					.createSQLQuery(
							"select ifnull(sum(value),0) from  ticket_inventory_details where ticket_type_manual_id in(2,3) and current_status='New' and status='ACTIVE'")
					.uniqueResult().toString());
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return count;
		}
	}
	public int getTotalNumberOfPasses() {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer.parseInt(session
					.createSQLQuery(
							"select ifnull(sum(number_of_books),0) from  ticket_inventory_details where ticket_type_manual_id in(2,3,6,7) and current_status='New' and status='ACTIVE'")
					.uniqueResult().toString());
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return count;
		}

	}
	public int getTotalNumberOfLuggagess() {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer.parseInt(session
					.createSQLQuery(
							"select ifnull(sum(number_of_books),0) from  ticket_inventory_details where ticket_type_manual_id=4 and current_status='New' and status='ACTIVE'")
					.uniqueResult().toString());
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return count;
		}	}
	public int getTotalLugggageValue() {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			count = Integer.parseInt(session
					.createSQLQuery(
							"select ifnull(sum(value),0) from  ticket_inventory_details where ticket_type_manual_id=4 and current_status='New'")
					.uniqueResult().toString());
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return count;
		}
	}
	public int checkForDuplicateTicketEntry(String denomno, String denomkey,
			String startno, String endno) {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String sql="select IFNULL(count(*),0) from ticket_inventory_master where " +
					   " denoimination_type_manual_id='"+denomno+"' and ticket_type_manual_id='1' " +
					   " and status='ACTIVE' and denomination_key='"+denomkey+"' and " +
					   "'"+startno+"' between " +
					   " (select IFNULL(min(opening_number),0) " +
					   " from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' " +
					   " and ticket_type_manual_id='1' and  denomination_key='"+denomkey+"' " +
					   " and status='ACTIVE')  " +
					   " and " +
					   " (select IFNULL(max(closing_number),0) " +
					   " from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' " +
					   " and ticket_type_manual_id='1' " +
					   " and denomination_key='"+denomkey+"' and status='ACTIVE') " +
					   " OR " +
					   " '"+endno+"' between " +
					   " (select IFNULL(min(opening_number),0) " +
					   " from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' " +
					   " and ticket_type_manual_id='1' and  denomination_key='"+denomkey+"' " +
					   " and status='ACTIVE') " +
					   " and  " +
					   " (select IFNULL(max(closing_number),0) " +
					   " from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' " +
					   " and ticket_type_manual_id='1' " +
					   " and denomination_key='"+denomkey+"' and status='ACTIVE')"
					;

			/*String sql="select IFNULL(count(*),0) from ticket_inventory_master where " +
					" denoimination_type_manual_id='"+denomno+"' and ticket_type_manual_id='1' " +
					" and denomination_key='"+denomkey+"' and '"+startno+"'>=(select IFNULL(min(opening_number),0) " +
					" from " +
					" ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' and ticket_type_manual_id='1' and " +
					" denomination_key='"+denomkey+"' and status='ACTIVE') " +
					" and '"+endno+"'<=(select IFNULL(max(closing_number),0) from " +
					" ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' and ticket_type_manual_id='1' " +
					" and denomination_key='"+denomkey+"' and status='ACTIVE')";*/
			count = Integer.parseInt(session
					.createSQLQuery(
							sql)
					.uniqueResult().toString());
			/*count = Integer.parseInt(session
					.createSQLQuery(
							"select count(*) from ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' and ticket_type_manual_id='1' " +
							"and denomination_key='"+denomkey+"' and " +
							"opening_number>='"+startno+"' and closing_number<='"+endno+"' and status='ACTIVE'")
					.uniqueResult().toString());*/
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			
		}
		return count;
		
	}
	public int checkForDuplicatePassEntry(String denomno,String startno, String endno,String denomKey,String passDay) {
		Session session = null;
		int count = 0;
		String conditionorDailyPass = "";
		try {
			if(!passDay.equals("")){
				conditionorDailyPass = " and pass_day = '"+passDay+"' ";
			}
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String sql=" select IFNULL(count(*),0) from ticket_inventory_master where  denoimination_type_manual_id='"+denomno+"'  and status='ACTIVE' and ticket_type_manual_id in('2','3','6','7') " +
					   " and denomination_key='"+denomKey+"'  "+conditionorDailyPass+" and '"+startno+"' between " +
					   " (select IFNULL(min(opening_number),0)  from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' " +
					   " and ticket_type_manual_id in('2','3') and denomination_key='"+denomKey+"'  "+conditionorDailyPass+"  and status='ACTIVE')  and" +
					   " (select IFNULL(max(closing_number),0) from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' and ticket_type_manual_id in('2','3','6','7') " +
					   "  and denomination_key='"+denomKey+"' "+conditionorDailyPass+"  and status='ACTIVE') OR '"+endno+"' between " +
					   " (select IFNULL(min(opening_number),0) from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"'  and ticket_type_manual_id in('2','3','6','7') " +
					   " and denomination_key='"+denomKey+"'  "+conditionorDailyPass+"  and status='ACTIVE') and  " +
					   " (select IFNULL(max(closing_number),0) from  ticket_inventory_master where denoimination_type_manual_id='"+denomno+"'  and ticket_type_manual_id in('2','3','6','7') " +
					   " and denomination_key='"+denomKey+"'  "+conditionorDailyPass+" and status='ACTIVE')";
			count = Integer.parseInt(session.createSQLQuery(sql).uniqueResult().toString());
			/*count = Integer.parseInt(session
					.createSQLQuery(
							"select count(*) from ticket_inventory_master where denoimination_type_manual_id='"+denomno+"' and ticket_type_manual_id in('2','3') " +
							"and denomination_key='"+denomkey+"' and " +
							"opening_number>='"+startno+"' and closing_number<='"+endno+"' and status='ACTIVE'")
					.uniqueResult().toString());*/
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			return count;
		}
	}
	public int checkForDuplicateLuggageEntry(String denomkey, String startno,
			String endno) {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String sql="select IFNULL(count(*),0) from ticket_inventory_master where " +
					   " ticket_type_manual_id='4' " +
					   " and status='ACTIVE' and denomination_key='"+denomkey+"' and " +
					   "('"+startno+"' between " +
					   " (select IFNULL(min(opening_number),999999999) " +
					   " from  ticket_inventory_master where " +
					   " ticket_type_manual_id='4' and  denomination_key='"+denomkey+"' " +
					   " and status='ACTIVE')  " +
					   " and " +
					   " (select IFNULL(max(closing_number),999999999) " +
					   " from  ticket_inventory_master where " +
					   " ticket_type_manual_id='4' " +
					   " and denomination_key='"+denomkey+"' and status='ACTIVE') " +
					   " OR " +
		  			   " '"+endno+"' between " +
					   " (select IFNULL(min(opening_number),999999999) " +
					   " from  ticket_inventory_master where " +
					   " ticket_type_manual_id='4' and  denomination_key='"+denomkey+"' " +
					   " and status='ACTIVE') " +
					   " and  " +
					   " (select IFNULL(max(closing_number),999999999) " +
					   " from  ticket_inventory_master where " +
					   " ticket_type_manual_id='4' " +
					   " and denomination_key='"+denomkey+"' and status='ACTIVE'))"
					;

			/*String sql="select IFNULL(count(*),0) from ticket_inventory_master where ticket_type_manual_id='4' " +
					" and denomination_key='"+denomkey+"' and '"+startno+"'>=(select IFNULL(min(opening_number),0) " +
					" from " +
					" ticket_inventory_master where ticket_type_manual_id='4' and " +
					" denomination_key='"+denomkey+"' and status='ACTIVE') " +
					" and '"+endno+"'<=(select IFNULL(max(closing_number),0) from " +
					" ticket_inventory_master where ticket_type_manual_id='4' " +
					" and denomination_key='"+denomkey+"' and status='ACTIVE')";*/
			count = Integer.parseInt(session
					.createSQLQuery(
							sql)
					.uniqueResult().toString());
			
			/*count = Integer.parseInt(session
					.createSQLQuery(
							"select count(*) from ticket_inventory_master where ticket_type_manual_id='4' " +
							"and denomination_key='"+denomkey+"' and " +
							"opening_number>='"+startno+"' and closing_number<='"+endno+"' and status='ACTIVE'")
					.uniqueResult().toString());
*/			
//			System.out.println(sql);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			
		}
		return count;
	}
	public int checkForDuplicateTSheetEntry(String denomkey, String startno,
			String endno) {
		Session session = null;
		int count = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			String sql="select IFNULL(count(*),0) from ticket_inventory_master where " +
					   " ticket_type_manual_id='5' " +
					   " and status='ACTIVE' and denomination_key='"+denomkey+"' and " +
					   "('"+startno+"' between " +
					   " (select IFNULL(min(opening_number),999999999) " +
					   " from  ticket_inventory_master where " +
					   " ticket_type_manual_id='5' and  denomination_key='"+denomkey+"' " +
					   " and status='ACTIVE')  " +
					   " and " +
					   " (select IFNULL(max(closing_number),999999999) " +
					   " from  ticket_inventory_master where " +
					   " ticket_type_manual_id='5' " +
					   " and denomination_key='"+denomkey+"' and status='ACTIVE') " +
					   " OR " +
		  			   " '"+endno+"' between " +
					   " (select IFNULL(min(opening_number),999999999) " +
					   " from  ticket_inventory_master where " +
					   " ticket_type_manual_id='5' and  denomination_key='"+denomkey+"' " +
					   " and status='ACTIVE') " +
					   " and  " +
					   " (select IFNULL(max(closing_number),999999999) " +
					   " from  ticket_inventory_master where " +
					   " ticket_type_manual_id='5' " +
					   " and denomination_key='"+denomkey+"' and status='ACTIVE'))"
					;

			/*String sql="select IFNULL(count(*),0) from ticket_inventory_master where ticket_type_manual_id='4' " +
					" and denomination_key='"+denomkey+"' and '"+startno+"'>=(select IFNULL(min(opening_number),0) " +
					" from " +
					" ticket_inventory_master where ticket_type_manual_id='4' and " +
					" denomination_key='"+denomkey+"' and status='ACTIVE') " +
					" and '"+endno+"'<=(select IFNULL(max(closing_number),0) from " +
					" ticket_inventory_master where ticket_type_manual_id='4' " +
					" and denomination_key='"+denomkey+"' and status='ACTIVE')";*/
			count = Integer.parseInt(session
					.createSQLQuery(
							sql)
					.uniqueResult().toString());
			
			/*count = Integer.parseInt(session
					.createSQLQuery(
							"select count(*) from ticket_inventory_master where ticket_type_manual_id='4' " +
							"and denomination_key='"+denomkey+"' and " +
							"opening_number>='"+startno+"' and closing_number<='"+endno+"' and status='ACTIVE'")
					.uniqueResult().toString());
*/			
//			System.out.println(sql);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			
		}
		return count;
	}
	public String getLuggageDenomVal() {
		Session session = null;
		String luggagedenomid = "";
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			luggagedenomid = session
					.createSQLQuery(
							"select denomination_type_manual_id from denomination_type_manual where ticket_type_manual_id=4 limit 1")
					.uniqueResult().toString();
//			System.out.println(luggagedenomid);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			
		}
		return luggagedenomid;

	}
	public String getTsheetDenomVal() {
		Session session = null;
		String tsheetdenomid = "";
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			tsheetdenomid = session
					.createSQLQuery(
							"select denomination_type_manual_id from denomination_type_manual where ticket_type_manual_id=5 limit 1")
					.uniqueResult().toString();
//			System.out.println(tsheetdenomid);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			
		}
		return tsheetdenomid;

	}
	public int getTotalnoOfBooks(String issutick) {
		Session sessiontotticket = null;
		int noofbooksofticket = 0;
		String arr = issutick;
		try {
			if(issutick.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], strOpeningNumber = new String[s.length],strDenomValue = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(!tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						count++;
					}
				}
				sessiontotticket = HibernateUtilTick.getSession("hibernate.cfg.xml");
				for(int j=0;j<count;j++){
					if(strdenomtype[j].equals("1")){
						String sql = "select  IFNULL(sum(a.number_of_books), 0)  from ("
							+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
							+ " opening_number, closing_number,  number_of_tickets,number_of_books, value,unit_type,unit_name,ticket_type_manual_id,priority "
							+ " FROM ticket_inventory_details tid  WHERE tid.denomination_key='"+ strkey[j]+"' and tid.partial_book = 'Y' and ticket_type_manual_id='1' "
							+ " and tid.status = 'ACTIVE' and tid.current_status='New'  union all "
							+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id,"
							+ " denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as "
							+ " number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,ticket_type_manual_id ,priority"
							+ " FROM ticket_inventory_details tid "
							+ " WHERE tid.denomination_key  ='"+ strkey[j]+ "' and tid.partial_book = 'N' and tid.status = 'ACTIVE' and tid.current_status='New' "
							+ " group by tid.denomination_key, tid.denomination_type_manual_id ) a  inner join denomination_type_manual dy on "
							+ " dy.denomination_type_manual_id= a.denomination_type_manual_id "
							+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id "
							+ " and a.ticket_type_manual_id='1'";
		
						noofbooksofticket += Integer.parseInt(sessiontotticket.createSQLQuery(sql).uniqueResult().toString());
						System.out.println(noofbooksofticket);
					}
				}
			}
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessiontotticket != null && sessiontotticket.isOpen()) {
				sessiontotticket.close();
				sessiontotticket = null;
			}
			return noofbooksofticket;
		}

	}

	public int getTotalIssuedTicketValue(String issutick) {
		Session sessionissuedtotalticketval= null;
		String arr = issutick;
		int totticketval = 0;
		try {
			if(issutick.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], strOpeningNumber = new String[s.length],strDenomValue = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(!tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						count++;
					}
				}
				sessionissuedtotalticketval = HibernateUtilTick.getSession("hibernate.cfg.xml");
				for(int j=0;j<count;j++){
					if(strdenomtype[j].equals("1")){
						String sql = "select  IFNULL(sum(a.value), 0)  from ("
							+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
							+ " opening_number, closing_number,  number_of_tickets,number_of_books, value,unit_type,unit_name,ticket_type_manual_id,priority "
							+ " FROM ticket_inventory_details tid  WHERE tid.denomination_key ='"+ strkey[j]+ "' and tid.partial_book = 'Y' "
							+ " and tid.status = 'ACTIVE' and tid.current_status='New' and ticket_type_manual_id ='1'   union all "
							+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id,"
							+ " denomination_key, min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as "
							+ " number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,ticket_type_manual_id ,priority"
							+ " FROM ticket_inventory_details tid "
							+ " WHERE tid.denomination_key ='"+ strkey[j]+ "'and tid.partial_book = 'N' and tid.status = 'ACTIVE' and tid.current_status='New' "
							+ " group by tid.denomination_key,tid.denomination_type_manual_id ) a  inner join denomination_type_manual dy on "
							+ " dy.denomination_type_manual_id= a.denomination_type_manual_id "
							+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id "
							+ " and a.ticket_type_manual_id ='1' ";
		
						totticketval += Integer.parseInt(sessionissuedtotalticketval.createSQLQuery(sql).uniqueResult().toString());
						System.out.println(totticketval);
					}
				}
			}
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessionissuedtotalticketval != null && sessionissuedtotalticketval.isOpen()) {
				sessionissuedtotalticketval.close();
				sessionissuedtotalticketval = null;
			}
			return totticketval;
		}

	}

	public int getTotalBooksPass(String issutick) {

		Session sessiontotpas= null;
		int noofbooksofpass = 0;
		String arr = issutick;
		try {
			sessiontotpas = HibernateUtilTick.getSession("hibernate.cfg.xml");
			if(issutick.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], strOpeningNumber = new String[s.length],strDenomValue = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(!tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						count++;
					}
				}
				for(int j=0;j<count;j++){
					if(strdenomtype[j].equals("2")|| strdenomtype[j].equals("3")){
						String sql = "select IFNULL(sum(a.number_of_books),0) from ( "
						+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id,pass_day, denomination_key, min(opening_number) "
						+ " as opening_number, max(closing_number) as closing_number, sum(number_of_passes) as  number_of_tickets,sum(number_of_books) "
						+ " as number_of_books,sum(value) as value,unit_type,unit_name,ticket_type_manual_id,priority  FROM ticket_inventory_details tid  "
						+ " WHERE tid.denomination_key ='"+strkey[j]+"' and tid.status = 'ACTIVE' and tid.current_status='New'  "
						+ " group by tid.denomination_key, tid.denomination_type_manual_id ) a  inner join denomination_type_manual dy on "
						+ " dy.denomination_type_manual_id= a.denomination_type_manual_id  inner join ticket_type_manual "
						+ " ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id  and a.ticket_type_manual_id in ('2','3','6','7')"
						+ "order by a.denomination_key, a.number_of_tickets";
						noofbooksofpass += Integer.parseInt(sessiontotpas.createSQLQuery(sql).uniqueResult().toString());
						System.out.println(noofbooksofpass);
					}
				}
			}
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessiontotpas != null && sessiontotpas.isOpen()) {
				sessiontotpas.close();
				sessiontotpas = null;
			}
		}
		return noofbooksofpass;

	}

	public int getTotalIssuedPassvalue(String issutick) {
		Session sessiontotpassval = null;
		int totpassvalue = 0;
		String arr = issutick;
		try {
			sessiontotpassval = HibernateUtilTick.getSession("hibernate.cfg.xml");
			if(issutick.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], strOpeningNumber = new String[s.length],strDenomValue = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(!tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						count++;
					}
				}
				for(int j=0;j<count;j++){
					if(strdenomtype[j].equals("2") || strdenomtype[j].equals("3")){
						String sql = "select  IFNULL(sum(a.value),0) from ( "
								+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id,pass_day, denomination_key, min(opening_number) "
								+ " as opening_number, max(closing_number) as closing_number, sum(number_of_passes) as  number_of_tickets,sum(number_of_books) "
								+ " as number_of_books,sum(value) as value,unit_type,unit_name,ticket_type_manual_id,priority  FROM ticket_inventory_details tid  "
								+ " WHERE tid.denomination_key ='"+ strkey[j]+ "' and tid.status = 'ACTIVE' and tid.current_status='New'  "
								+ " group by tid.denomination_key, tid.denomination_type_manual_id ) a  inner join denomination_type_manual dy on "
								+ " dy.denomination_type_manual_id= a.denomination_type_manual_id  inner join ticket_type_manual "
								+ " ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id  and a.ticket_type_manual_id in ('2','3','6','7') "
								+ "order by a.denomination_key, a.number_of_tickets";
						totpassvalue += Integer.parseInt(sessiontotpassval.createSQLQuery(sql).uniqueResult().toString());
						System.out.println(totpassvalue);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessiontotpassval != null && sessiontotpassval.isOpen()) {
				sessiontotpassval.close();
				sessiontotpassval = null;
			}
		}
		return totpassvalue;

	}

	public int getTotalBooksLuggage(String issutick) {
		// TODO Auto-generated method stub
		Session sessiontotlugg = null;
		int totnoofluggissued = 0;
		String arr = issutick;
		try {
			sessiontotlugg = HibernateUtilTick.getSession("hibernate.cfg.xml");
			if(issutick.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], strOpeningNumber = new String[s.length],strDenomValue = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						count++;
					}
				}
				for(int j=0;j<count;j++){
					if(strdenomtype[j].equals("4")){
						String sql = "select  IFNULL(sum(a.number_of_books),0) from ("
							+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
							+ " min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as "
							+ " number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,priority,"
							+ " ticket_type_manual_id  FROM ticket_inventory_details tid  WHERE tid.denomination_key ='"+ strkey[j]+ "' and "
							+ " tid.status = 'ACTIVE' and tid.current_status='New'  group by tid.denomination_key, tid.denomination_type_manual_id ) a "
							+ " inner join denomination_type_manual dy on  dy.denomination_type_manual_id= a.denomination_type_manual_id "
							+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id  and a.ticket_type_manual_id='4' order by a.denomination_key, a.number_of_tickets";
						
						totnoofluggissued += Integer.parseInt(sessiontotlugg.createSQLQuery(sql).uniqueResult().toString());
					}
				}
			}
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessiontotlugg != null && sessiontotlugg.isOpen()) {
				sessiontotlugg.close();
				sessiontotlugg = null;	
				
		}
		}
		return totnoofluggissued;
	}

	public int getTotalIssuedLuggagevalue(String issutick) {
		Session sessiontotlugg = null;
		int totissuedluggagevalue = 0;
		String arr = issutick;
		try {
			sessiontotlugg = HibernateUtilTick.getSession("hibernate.cfg.xml");
			if(issutick.length()>0){
				arr = arr.substring(0, arr.length() - 1);
				String s[] = arr.split(",");
				String[] strkey  = new String[s.length], strdenomtype = new String[s.length], strOpeningNumber = new String[s.length],strDenomValue = new String[s.length];
				int count = 0;
				for (int i = 0; i < s.length; i++) {
					String[] tokens = s[i].split("-");
					if(tokens[3].equals("0")){
						strkey[count] = tokens[0] ;
						strdenomtype[count]= tokens[1];
						strOpeningNumber[count]= tokens[2];
						strDenomValue[count]= tokens[3];
						count++;
					}
				}
				for(int j=0;j<count;j++){
					if(strdenomtype[j].equals("4")){
						String sql = "select  IFNULL(sum(a.number_of_books),0) from ("
								+ " SELECT ticket_inventory_mst_id,denomination_type_manual_id, denomination_key,"
								+ " min(opening_number) as opening_number, max(closing_number) as closing_number, sum(number_of_tickets) as "
								+ " number_of_tickets,sum(number_of_books) as number_of_books,sum(value) as value,unit_type,unit_name,priority,"
								+ " ticket_type_manual_id  FROM ticket_inventory_details tid  WHERE tid.denomination_key = '"+ strkey[j]+"' and "
								+ " tid.status = 'ACTIVE' and tid.current_status='New'  group by tid.denomination_key, tid.denomination_type_manual_id ) a "
								+ " inner join denomination_type_manual dy on  dy.denomination_type_manual_id= a.denomination_type_manual_id "
								+ " inner join ticket_type_manual ttm on a.ticket_type_manual_id=ttm.ticket_type_manual_id " 
								+ " and a.ticket_type_manual_id = '4'  order by a.denomination_key, a.number_of_tickets";
						totissuedluggagevalue += Integer.parseInt(sessiontotlugg.createSQLQuery(sql).uniqueResult().toString());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessiontotlugg != null && sessiontotlugg.isOpen()) {
				sessiontotlugg.close();
				sessiontotlugg = null;
			}
		}
		return totissuedluggagevalue; 
	}
	public int getMaxPriority(String ticketdenomno) {
		// TODO Auto-generated method stub
		Session sessiondenompriority = null;
		int priority = 0;
		try {
			sessiondenompriority = HibernateUtilTick.getSession("hibernate.cfg.xml");

			priority = Integer.parseInt(sessiondenompriority
					.createSQLQuery("select IFNULL(max(priority),0)+1 from  ticket_inventory_details " +
							" where ticket_type_manual_id=1 and status='ACTIVE' and current_status='New' and created_date like '%"+todaysDate+"%'" +
							" and denomination_type_manual_id="+ticketdenomno)
					.uniqueResult().toString());
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessiondenompriority != null && sessiondenompriority.isOpen()) {
				sessiondenompriority.close();
				sessiondenompriority = null;
			}
			
		}

		return priority;
	}
	public int getMaxPassPriority(String... params) {
		Session session = null;
		int priority = 0;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");

			if(params[1].equals("")){
				priority = Integer.parseInt(session.createSQLQuery("select IFNULL(max(priority),0)+1 from  ticket_inventory_details " +
						" where ticket_type_manual_id in(3) and status='ACTIVE' and current_status='New' and created_date like '%"+todaysDate+"%' " +
						" and denomination_type_manual_id="+params[0]).uniqueResult().toString());
			}else{
				priority = Integer.parseInt(session.createSQLQuery("select IFNULL(max(priority),0)+1 from  ticket_inventory_details " +
						" where ticket_type_manual_id in(2) and pass_day='"+params[1]+"' and status='ACTIVE' and current_status='New' and created_date like '%"+todaysDate+"%'  " +
						" and denomination_type_manual_id="+params[0]).uniqueResult().toString());
			}
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace(); 
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
			
		}

		return priority;

	}
	
	public int getMaxLuggagePriority(String luggagedeomid, String luggagedenomkey) {
		Session sessionpasspriority= null;
		int priority = 0; 
		try {
			sessionpasspriority = HibernateUtilTick.getSession("hibernate.cfg.xml");
			
			priority = Integer.parseInt(sessionpasspriority
					.createSQLQuery(" select IFNULL(max(priority),0)+1 from  ticket_inventory_details where ticket_type_manual_id=4 and status='ACTIVE' " +
							" and current_status='New' and created_date like '%"+todaysDate+"%' and denomination_type_manual_id="+luggagedeomid)
					.uniqueResult().toString());
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessionpasspriority != null && sessionpasspriority.isOpen()) {
				sessionpasspriority.close();
				sessionpasspriority = null;
			}  
			
		}

		return priority;
	}
	public int deleteCheckedStock(String key, int tickettype) {
		// TODO Auto-generated method stub
		Session sessionstockdelete= null;
		int i= 0; 
		try {
			sessionstockdelete = HibernateUtilTick.getSession("hibernate.cfg.xml");
			sessionstockdelete.beginTransaction();
			String qry = "update ticket_inventory_details set status='INACTIVE'  where denomination_key='" + key
					+ "' and current_status='New' and ticket_type_manual_id="+tickettype;
			Query query = sessionstockdelete.createSQLQuery(qry);
			i = query.executeUpdate();

			sessionstockdelete.getTransaction().commit();	
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessionstockdelete != null && sessionstockdelete.isOpen()) {
				sessionstockdelete.close();
				sessionstockdelete = null;
			}
			
		}

		return i;
	}
	public int deleteFromMaster(String key, int tickettype) {
		// TODO Auto-generated method stub
		Session sessionstockdeletefrommast= null;
		int i= 0; 
		try {
			sessionstockdeletefrommast = HibernateUtilTick.getSession("hibernate.cfg.xml");
			sessionstockdeletefrommast.beginTransaction();
			String qry = "update ticket_inventory_master set status='INACTIVE'  where denomination_key='" + key
					+ "' and current_status='New' and ticket_type_manual_id="+tickettype;
			Query query = sessionstockdeletefrommast.createSQLQuery(qry);
			i = query.executeUpdate();

			sessionstockdeletefrommast.getTransaction().commit();		
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			sessionstockdeletefrommast.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			if (sessionstockdeletefrommast != null && sessionstockdeletefrommast.isOpen()) {
				sessionstockdeletefrommast.close();
				
				sessionstockdeletefrommast = null;
			}
			
		}

		return i;	
	}
	public String getPassDayOrMOnthList(String passtype) {
		List<Map<String, Object>> rolelist = null;
		List<Map<String, Object>> rolelist2 = null;
		List<String> finalList = new ArrayList<String>();
		String result = "";
		try {
			// String
			// sql="select role_id,role_name from role where deleted_status=0 ";
			String sql ="select inventorypass_id,day_month from  InventoryTicket_PassType " +
					" where status='ACTIVE' and deleted_status=0 and pass_type='"+passtype+"' order by inventorypass_id asc";
			

			Query query = HibernateUtilTick.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			rolelist = query.list();
			if(passtype.equals("3")){
				finalList = null;
				finalList = convertToListFromListOfMap(rolelist);
				finalList = getListForMonthsWithYear(finalList);
				
				if (finalList.size() > 0) {

					for (int i = 0; i < finalList.size(); i++) {
						
						String roleid = finalList.get(i)
								.toString().split("-")[0];
						String rolename = finalList.get(i);
						result += rolename + "@";
						result += rolename + ",";
						
					}
				}
				
			}else{
				if (rolelist.size() > 0) {

					for (int i = 0; i < rolelist.size(); i++) {
						
						Map<String, Object> rs = rolelist.get(i);
						String roleid = rs.get("day_month")
								.toString();
						
						String rolename = rs.get("day_month")
								.toString().split("-")[0];
						result += rolename + "@";
						result += roleid + ",";
						
					}
				}
				
		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	public List getListForMonthsWithYear(List<String> monthsList1){
		List<String> returnList = new ArrayList<String>(); 
//		List<String> monthList = new ArrayList<String>();
		/*if (monthsList1.size() > 0) {

			for (int i = 0; i < monthsList1.size(); i++) {
				Map<String, Object> rs = monthsList1.get(i);
				String month = rs.get("day_month").toString().toLowerCase();
				monthList.add(month); 
			}
		}*/
		
		int count = 0;
		Calendar cal = Calendar.getInstance();
		String currentMonth = new SimpleDateFormat("MMMM").format(cal.getTime());
		String currentyear = new SimpleDateFormat("YYYY").format(cal.getTime());
		int currentPosition = monthsList1.indexOf(currentMonth);
		
		for(int i=currentPosition;i<monthsList1.size() ;i++){
			if(count<=10){
				returnList.add(monthsList1.get(i)+"-"+currentyear);
				count++;
			}
		}
		cal.add(Calendar.YEAR,1);
		String nextYear = new SimpleDateFormat("YYYY").format(cal.getTime());
		for(int i=0;i<currentPosition;i++){
			if(count<=10){
				returnList.add(monthsList1.get(i)+"-"+nextYear);
				count++;
			}
		}
		return returnList;
	}
	
	public List<String> convertToListFromListOfMap(List<Map<String,Object>> listOfmap){
		List<String> returnList = new ArrayList<String>();
		
		if(listOfmap.size()>0){
			for(int i=0;i<listOfmap.size();i++){
				Map<String,Object> resultset = listOfmap.get(i);
				returnList.add(resultset.get("day_month").toString());
			}
		}
		return returnList;
	}
	
	
	//vouncher show start
	
	
	
	

	
//manual show 





public int getTotalRecordsPass(HttpServletRequest request,String col,String dir,String viewdeletedrecord,String vouncherid){
	
	String regionTypeAjaxString="";
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	
	
	regionTypeAjaxString+="<table border='1' cellspacing='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;border-collapse: collapse;'>";
	regionTypeAjaxString +="<thead><th>Denomination</th><th>Denomination Key</th><th>Start Number</th><th>End Number</th><th>No. of tickets</th><th>No. Of Books</th><th>value</th></thead>";
	int cnt=0;
	String sql="";
	String sql1="";
	try{

		
		sql=   " select td.ticket_inventory_det_id as tinvetory_id,denomination_type_manual as denomination_type_manual,tm.ticket_type_manual_name as ticket_type_manual_name," +
				" td.denomination_key as denomination_key, "+
				" td.pass_day as pass_day,td.opening_number as opening_number,td.closing_number as closing_number, td.number_of_tickets as number_of_tickets,td.number_of_passes as number_of_passes,"+	 
				" td.number_of_books as number_of_books,td.value as value,td.current_status as current_status," +
				" td.status as status from ticket_inventory_details td inner join ticket_invoice_details ti "+
				" on td.ticket_inventory_det_id=ti.ticket_inventory_det_id "+
				" inner join denomination_type_manual de on td.denomination_type_manual_id=de.denomination_type_manual_id "+
				" inner join ticket_type_manual tm on tm.ticket_type_manual_id=td.ticket_type_manual_id "+
				" where ti.ticket_invoice_mast_id="+vouncherid+" and td.ticket_type_manual_id in(2,3,6,7) ";


	String search_term1 = request.getParameter("sSearch");
	////System.out.println("search_term-------"+search_term1);
//	if (search_term1 != null && !search_term1.equals("")) {
//		String search_term = request.getParameter("sSearch").trim();
//		////System.out.println("search_term---------"+search_term);
//		sql += " and (ticket_invoice_mast_id like '"+search_term+"%'";
//		sql += " OR voucher_number like '"+search_term+"%'";
//		sql += " OR org.org_name like '"+search_term+"%'";
//		sql += " OR org1.org_name like '"+search_term+"%'";
//		sql += " OR tinvoice.created_date like '"+search_term+"%'";
//		sql += " OR current_status like '"+search_term+"%')";
//		//sql += " OR employee.EMPLOYEE_NAME_KANNADA like  '"+search_term+"%'";
//		//sql += " OR employee.WORKING_DEPOT like '"+search_term+"%'";
//		//sql += " OR menu_user_master.status like '"+search_term+"%'";
//		//sql += " OR menu_user_master.created_date like '"+search_term+"%'";
//		//sql += " OR user.user_name like '"+search_term+"%'";
//		//sql += " OR employee.WORKING_DEPT like '"+search_term+"%')";
//		
//	}

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
////System.out.println("sql----------"+sql);
Query query = session.createSQLQuery(sql)
		.addScalar("tinvetory_id")
		.addScalar("denomination_type_manual")
		.addScalar("ticket_type_manual_name")
		.addScalar("denomination_key")
		.addScalar("pass_day")
		.addScalar("opening_number")
		.addScalar("closing_number")
		.addScalar("number_of_passes")
		.addScalar("status");
		//.addScalar("number_of_tickets")
		//.addScalar("number_of_passes");
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

public int getTotalRecordsLuggage(HttpServletRequest request,String col,String dir,String viewdeletedrecord,String vouncherid){
	
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	int cnt=0;
	String sql="";
	String sql1="";
	try{

		
		sql=   " select td.ticket_inventory_det_id as tinvetory_id,denomination_type_manual as denomination_type_manual,tm.ticket_type_manual_name as ticket_type_manual_name," +
				" td.denomination_key as denomination_key, "+
				" td.pass_day as pass_day,td.opening_number as opening_number,td.closing_number as closing_number, td.number_of_tickets as number_of_tickets,td.number_of_passes as number_of_passes,"+	 
				" td.number_of_books as number_of_books,td.value as value,td.current_status as current_status," +
				" td.status as status from ticket_inventory_details td " +
				"inner join ticket_invoice_details ti  on td.ticket_inventory_det_id=ti.ticket_inventory_det_id "+
				" inner join denomination_type_manual de on td.denomination_type_manual_id=de.denomination_type_manual_id "+
				" inner join ticket_type_manual tm on tm.ticket_type_manual_id=td.ticket_type_manual_id "+
				" where ti.ticket_invoice_mast_id="+vouncherid+" and td.ticket_type_manual_id in(4) ";

//	System.out.println("sql-----////////////////////---"+sql);

	String search_term1 = request.getParameter("sSearch");
	////System.out.println("search_term-------"+search_term1);
//	if (search_term1 != null && !search_term1.equals("")) {
//		String search_term = request.getParameter("sSearch").trim();
//		////System.out.println("search_term---------"+search_term);
//		sql += " and (ticket_invoice_mast_id like '"+search_term+"%'";
//		sql += " OR voucher_number like '"+search_term+"%'";
//		sql += " OR org.org_name like '"+search_term+"%'";
//		sql += " OR org1.org_name like '"+search_term+"%'";
//		sql += " OR tinvoice.created_date like '"+search_term+"%'";
//		sql += " OR current_status like '"+search_term+"%')";
//		//sql += " OR employee.EMPLOYEE_NAME_KANNADA like  '"+search_term+"%'";
//		//sql += " OR employee.WORKING_DEPOT like '"+search_term+"%'";
//		//sql += " OR menu_user_master.status like '"+search_term+"%'";
//		//sql += " OR menu_user_master.created_date like '"+search_term+"%'";
//		//sql += " OR user.user_name like '"+search_term+"%'";
//		//sql += " OR employee.WORKING_DEPT like '"+search_term+"%')";
//		
//	}

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
////System.out.println("sql----------"+sql);
Query query = session.createSQLQuery(sql)
		.addScalar("tinvetory_id")
		.addScalar("denomination_type_manual")
		.addScalar("ticket_type_manual_name")
		.addScalar("denomination_key")
		.addScalar("pass_day")
		.addScalar("opening_number")
		.addScalar("closing_number")
		.addScalar("number_of_tickets")
		.addScalar("number_of_passes")
		.addScalar("status");
		//.addScalar("number_of_tickets")
		//.addScalar("number_of_passes");
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

//luggage typeend
	//vouncher details show

public String getListVoucheShow(String vouncherid){
	//int totalAfterFilter = total;
	String regionTypeAjaxString="";
	JSONObject result = new JSONObject();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	try{
		List<TicketInvoiceMaster> ticketinv=new ArrayList<TicketInvoiceMaster>();
		
		String sql="";
		String sql1="";
		String employeenamekannda="";
		
		
		regionTypeAjaxString += "<div><div id='printid'><table border='1' cellspacing='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;border-collapse: collapse;'>";

		//regionTypeAjaxString +="<thead><th>Sr. No.</th><th>Ticket No.</th><th>Transaction No.</th><th>Ticket Type</th><th>Ticket Sub Type</th><th>Schedule No.</th>";

					
			sql=    " select ticket_invoice_mast_id as vouncherid,voucher_number as vounchernum,DATE_FORMAT(tinvoice.created_date,'%d/%m/%Y') as vouncherdate," +
					" current_status as status,org.org_name as ToOrg,org1.org_name as FromOrg " +
					" from ticket_invoice_master tinvoice "+
					" inner join org_chart org on org.org_chart_id=tinvoice.issue_to_unit_id "+
					" inner join org_chart org1 on org1.org_chart_id=tinvoice.issue_from_unit_id "+
					" where tinvoice.ticket_invoice_mast_id="+vouncherid+"";

		//System.out.println("sql11111-----////////////////////---"+sql);
	
		
	Query query = session.createSQLQuery(sql)
			.addScalar("vouncherid")
			.addScalar("vounchernum")
			.addScalar("vouncherdate")
			.addScalar("status")
			.addScalar("FromOrg")
			.addScalar("ToOrg");
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	
	JSONArray array = new JSONArray();
	for(int i=0;i<aliasToValueMapList.size();i++){
		
		Map<String, Object> rs = aliasToValueMapList.get(i);
		JSONArray ja = new JSONArray();
		/*ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
				+ rs.get("vouncherid")
				+ "' value='"
				+ rs.get("vouncherid") + "'/>");*/
		regionTypeAjaxString+="<tr>";
		ja.add(rs.get("vouncherid").toString());
		//regionTypeAjaxString +="<td style='text-align:center'> Vouncherid </td><td>"+rs.get("vouncherid").toString() +"</td>";
		//ja.add(rs.get("userloginname").toString());
		//ja.add(rs.get("vounchernum").toString()!=null ? rs.get("vounchernum").toString().replaceAll(" ","&nbsp;") : "");
		
		ja.add(rs.get("vouncherdate").toString());
		regionTypeAjaxString+="<tr>";
		regionTypeAjaxString +="<td colspan='2' style='text-align:center'><b> Vouncher Date: </b>"+rs.get("vouncherdate").toString() +"</td>";
		//System.out.println("test-------"+rs.get("EMPLOYEE_NAME_KANNADA").toString().trim().length());
		
		ja.add(rs.get("FromOrg").toString());
		regionTypeAjaxString+="</tr>";
		regionTypeAjaxString+="<tr>";
		regionTypeAjaxString +="<td style='text-align:center'><b> From Org: </b>"+ rs.get("FromOrg").toString() +"</td>";
		
		
		
		ja.add(rs.get("ToOrg").toString());
		
		regionTypeAjaxString +="<td style='text-align:center'> <b>To Org: </b>"+ rs.get("ToOrg").toString() +"</td>";
		regionTypeAjaxString+="</tr>";
		regionTypeAjaxString+="<tr>";
		ja.add(rs.get("status").toString());
		regionTypeAjaxString +="<td colspan='2' style='text-align:center'><b> Status:</b> "+ rs.get("status").toString() +"</td>";
		regionTypeAjaxString+="</tr>";
		
		regionTypeAjaxString+="</table></div></div>";
		
		
		//array.add(ja);
		
	}
	
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if (session != null) {
			//session.close();
		}
	}
	return regionTypeAjaxString;
	
}

public int checkForDuplicateInitialEntry(String orgtype, String orgname) {
	Session session = null;
	int count = 0;
	try {
		session = HibernateUtilTick.getSession("hibernate.cfg.xml");
		String sql="select count(*) from ticket_inventory_details where stock_entry='Y' and unit_type="+orgtype+" and unit_name="+orgname;
				

		
		count = Integer.parseInt(session.createSQLQuery(sql).uniqueResult().toString());
		
		
//			System.out.println(sql);
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		if (session != null && session.isOpen()) {
			session.close();
			session = null;
		}
		
	}
	return count;
}
public int insertTicketInventoryDetailsMonthlyDailyPassId(String tickettype,
		String noofpasses, String denomkey, String denomno, String priority,
		int stno, int deomval, int inserttickinvmstid) {
	Session session = null;
	int s = 0;
	
	try {
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();

		HttpServletRequest request = ServletActionContext.getRequest();
		int orgTypeId = Integer.parseInt((String)request.getSession().getAttribute("orgtypeid"));
		int orgId = Integer.parseInt((String)request.getSession().getAttribute("orgchartid"));
		int usrsessionid = (Integer) request.getSession().getAttribute("userid");
		TicketInventory tickinv=null;
		
		for (int i = 0; i < Integer.parseInt(noofpasses); i++) {
			
			tickinv= new TicketInventory();
			tickinv.setTicket_type_manual_id(2);
			tickinv.setKey_number(denomkey);
			tickinv.setDenoimination_type(denomno);
			
			
			
			int value = deomval;
			tickinv.setStartno(stno);
			
			tickinv.setEndno(stno);
			tickinv.setTicketinventorymasterid(inserttickinvmstid);
			tickinv.setNoofbooks(0);
			tickinv.setNoofpasses(1);
			tickinv.setValue(value);
			tickinv.setDenoimination_type(denomno);
			tickinv.setPass_day("0");
			tickinv.setKey_number(denomkey);
			tickinv.setTicket_type_manual_id(Integer.parseInt(tickettype));
			tickinv.setUnittype(orgTypeId);
			tickinv.setPriority(Integer.parseInt(priority));
			tickinv.setUnitname(orgId);
			tickinv.setCreated_by(usrsessionid);
			tickinv.setCreated_date(new Date());
			tickinv.setCurrentstatus("New");
			tickinv.setStatus("ACTIVE");
			tickinv.setCreated_date(new Date());
			s = (Integer) session.save(tickinv);
			stno++;
	}
		session.getTransaction().commit();
	} catch (Exception e) {
		session.getTransaction().rollback();
		e.printStackTrace();
	} finally {
		if (session != null && session.isOpen()) {
			//session.flush();
			session.close();
		}
		return s;
	}
}



//end
	//vouncher show end
 
}