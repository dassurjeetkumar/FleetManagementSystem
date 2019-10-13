package com.trimax.its.OnlineWaybillLC.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.util.HibernateUtil;



public class OnlineWaybillLCDAO {

	public static void main(String args[]) {
		OnlineWaybillLCDAO object = new OnlineWaybillLCDAO();
		// object.getAllWaybills("42");
	}

	public ArrayList<OnlineWaybills> getAllDataDepot(String divisionId,
			String ticketDate) {
		String ticketDate1="21-11-2014";
		String depotId1="7";
		

		ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			/*
			 * String strQry=
			 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
			 * +
			 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
			 * + " and a.status like 'Active%' order by TOKEN asc";
			 */
			Date startDate = new Date(); 

//			String strQry = "SELECT egt.waybill_no, egt.schedule_no, st.schedule_type_name , sft.shift_type_name,egt.etim_no, egt.route_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount,"
//					+ "  count(distinct egt.ticket_no) as no_of_ticket,egt.vehicle_no "
//					+ " FROM etim_gprs_ticket egt " 
//					+ " inner join device d on d.device_serial_number = egt.etim_no " +
//					" inner join schedule s on s.schedule_number = egt.schedule_no" +
//					" inner join schedule_type st on st.schedule_type_id = s.schedule_type " +
//					" inner join schedule_details sd on sd.schedule_number = s.schedule_id" +
//					" inner join shift_type sft on sd.shift_type_id = sft.shift_type_id and d.status = 'ACTIVE' and d.deleted_status = 0 " 
//					+ " where egt.ticket_date = '"+ ticketDate + "' and egt.depot_id = '"+ depotId+ "' "
//					+ " group by egt.waybill_no, egt.schedule_no, egt.etim_no order by  egt.waybill_no ;";
			
			
			/*String strQry="SELECT   oc.org_name,    count(distinct egt.etim_no) as etim_no,   count(distinct egt.ticket_no) as no_of_ticket FROM    etim_gprs_ticket egt "
			+" inner join ticket_type tt on tt.ticket_type_id=egt.tkt_type_short_code "
			+" inner join    device d on d.device_serial_number =  egt.etim_no " 
			+" inner join    org_chart oc       on oc.org_chart_id = egt.depot_id "
			+" and    d.status =  'ACTIVE'  and d.deleted_status = 0 "
			+" where   egt.ticket_date = '"+ ticketDate + "' and tt.ticket_type_name ='Penalty Ticket' and oc.parent_id ='"+ depotId+ "'"
			+" group by    egt.depot_id  order by    egt.depot_id;";*/
			
			String strQry="SELECT    oc.org_name,   count(distinct egt.etim_no) as etim_no,   count(distinct egt.ticket_no) as no_of_ticket "+
			 "FROM    etim_gprs_ticket egt "+
			" inner join    ticket_type tt    on tt.ticket_type_id=egt.tkt_type_short_code "+
			" inner join    device d  on d.device_serial_number =  egt.etim_no "+
			" inner join    org_chart oc  on oc.org_chart_id = egt.depot_id "+
			" inner join    schedule s       on s.schedule_number = egt.schedule_no "+
			" inner join    schedule_type st       on st.schedule_type_id = s.schedule_type "+
			" inner  join    schedule_details sd       on sd.schedule_number = s.schedule_id "+
			" inner join    shift_type sft       on sd.shift_type_id = sft.shift_type_id "+
			" and d.status =  'ACTIVE'       and d.deleted_status = 0 "+
			" where     egt.ticket_date ='"+ ticketDate + "' " +
			" and tt.ticket_type_name ='Penalty Ticket'    and oc.parent_id='"+divisionId+"' "+
			" group by    egt.depot_id order by     egt.depot_id";
			
			
System.out.println("strQry..........."+strQry);

			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();

				OnlineWaybills onlineWaybill = new OnlineWaybills();

				onlineWaybill.setWaybillNo(obj[0].toString().trim());
				onlineWaybill.setScheduleNo(obj[1].toString().trim());
				onlineWaybill.setScheduleType(obj[2].toString().trim());
				

				arrayList.add(onlineWaybill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return arrayList;
	}
	
	public ArrayList<OnlineWaybills> getPerticularDepot(String divisionId,
			String ticketDate,String depotid) {
		String ticketDate1="21-11-2014";
		String depotId1="7";
		

		ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			/*
			 * String strQry=
			 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
			 * +
			 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
			 * + " and a.status like 'Active%' order by TOKEN asc";
			 */
			Date startDate = new Date(); 


			
			String strQry="SELECT    oc.org_name,   count(distinct egt.etim_no) as etim_no,   count(distinct egt.ticket_no) as no_of_ticket "+
			 "FROM    etim_gprs_ticket egt "+
			" inner join    ticket_type tt    on tt.ticket_type_id=egt.tkt_type_short_code "+
			" inner join    device d  on d.device_serial_number =  egt.etim_no "+
			" inner join    org_chart oc  on oc.org_chart_id = egt.depot_id "+
			" inner join    schedule s       on s.schedule_number = egt.schedule_no "+
			" inner join    schedule_type st       on st.schedule_type_id = s.schedule_type "+
			" inner  join    schedule_details sd       on sd.schedule_number = s.schedule_id "+
			" inner join    shift_type sft       on sd.shift_type_id = sft.shift_type_id "+
			" and d.status =  'ACTIVE'       and d.deleted_status = 0 "+
			" where     egt.ticket_date ='"+ ticketDate + "'and  egt.depot_id = '"+depotid+"' " +
			" and tt.ticket_type_name ='Penalty Ticket'    and oc.parent_id='"+divisionId+"' "+
			" group by    egt.depot_id order by     egt.depot_id";
			
			
System.out.println("strQry..........."+strQry);

			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();

				OnlineWaybills onlineWaybill = new OnlineWaybills();

				onlineWaybill.setWaybillNo(obj[0].toString().trim());
				onlineWaybill.setScheduleNo(obj[1].toString().trim());
				onlineWaybill.setScheduleType(obj[2].toString().trim());
				

				arrayList.add(onlineWaybill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return arrayList;
	}

	public Map<Integer, String> getDepots() {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Date startDate = new Date();
			Query query = session
					.createSQLQuery("Select org_chart_id, org_name from org_chart Where org_type_id='3' and deleted_status = '0' ");
			List list = query.list();
			Date endDate = new Date();
			int msElapsedTime = (int) (endDate.getTime() - startDate.getTime());
			// System.out.println("Depots time >>>>>>"+ msElapsedTime);

			resultMap.put(0, "Select");
			for (Iterator i = list.iterator(); i.hasNext();) {
				Object[] obj = (Object[]) i.next();
				resultMap.put(Integer.parseInt(obj[0].toString().trim()),
						obj[1].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return resultMap;
	}

	public ArrayList<OnlineWaybills> getAllWaybillswaybillwise(String depotId,
			String ticketDate, String waybillno) {

		ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			/*
			 * String strQry=
			 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
			 * +
			 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
			 * + " and a.status like 'Active%' order by TOKEN asc";
			 */
			Date startDate = new Date();

			String strQry = "SELECT egt.waybill_no,egt.schedule_no,st.schedule_type_name , sft.shift_type_name,egt.etim_no,egt.route_no,egt.shift_no, CAST(trim(egt.trip_no) AS UNSIGNED) trip_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount, " +
					"count(distinct egt.ticket_no) as no_of_ticket,oc.org_name " 
					+ " FROM etim_gprs_ticket egt " 
					+ " inner join device d on d.device_serial_number = egt.etim_no " 
					+" inner join    ticket_type tt    on tt.ticket_type_id=egt.tkt_type_short_code "
					+ "inner join org_chart oc on egt.depot_id = oc.org_chart_id" 
					+" inner join schedule s on s.schedule_number = egt.schedule_no" +
					" inner join schedule_type st on st.schedule_type_id = s.schedule_type " +
					" inner join schedule_details sd on sd.schedule_number = s.schedule_id" +
					" inner join shift_type sft on sd.shift_type_id = sft.shift_type_id and d.status = 'ACTIVE' and d.deleted_status = 0 "
					+ " where egt.ticket_date = '" + ticketDate + "' and tt.ticket_type_name ='Penalty Ticket' and  egt.depot_id = '"+ depotId + "' and egt.waybill_no = '"+ waybillno + "' "
					+ " group by egt.waybill_no,egt.schedule_no,egt.etim_no,egt.shift_no,egt.trip_no order by egt.shift_no,trip_no ";

			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();

				OnlineWaybills onlineWaybill = new OnlineWaybills();

				onlineWaybill.setWaybillNo(obj[0].toString().trim());
				onlineWaybill.setScheduleNo(obj[1].toString().trim());
				onlineWaybill.setScheduleType(obj[2].toString().trim());
				onlineWaybill.setShiftType(obj[3].toString().trim());
				onlineWaybill.setEtimNo(obj[4].toString().trim());
				onlineWaybill.setRouteNo(obj[5].toString().trim());
				onlineWaybill.setShift_No(obj[6].toString().trim());
				onlineWaybill.setTripno(obj[7].toString().trim());
				onlineWaybill.setTotal_ticket_amount(obj[8].toString().trim());
				onlineWaybill.setNoOfTickets(obj[9].toString().trim());
				onlineWaybill.setDepotName(obj[10].toString().trim());

				arrayList.add(onlineWaybill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return arrayList;
	}

	public ArrayList<OnlineWaybills> getAllWaybillstripwise(String depotId,
			String ticketDate, String waybillno, String tripno, String shift_no) {

		ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			/*
			 * String strQry=
			 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
			 * +
			 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
			 * + " and a.status like 'Active%' order by TOKEN asc";
			 */
			Date startDate = new Date();

			String strQry = "select CAST(trim(t.ticket_no) AS UNSIGNED) as ticket_no,CAST(trim(t.transaction_no) AS UNSIGNED) as transaction_no,ty.ticket_type_name, "
					+ "ifnull(tsy.ticket_sub_type_name,'') ticket_sub_type_name,t.schedule_no,st.schedule_type_name , sft.shift_type_name,t.route_no, "
					+ "ifnull(bs.bus_stop_name,' ') as from_stop_name,ifnull(bs2.bus_stop_name,' ' ) as till_stop_name, "
					+ "t.ticket_time, t.px_count, sum(t.px_total_amount) px_total_amount , t.lugg_units, sum(t.lugg_total_amount) lugg_total_amount,t.total_ticket_amount,oc.org_name "
					+ "from  etim_gprs_ticket t  "
					+ "left join  bus_stop bs on bs.bus_stop_id=t.from_bus_stop_id "
					+ "left join  bus_stop bs2 on bs2.bus_stop_id=t.till_bus_stop_id "
					+ "left join ticket_type ty on ty.ticket_type_id=t.tkt_type_short_code "
					+ "left join ticket_sub_type tsy on tsy.ticket_sub_type_code=t.tkt_sub_type_short_code " 
					+ " inner join device d on d.device_serial_number = t.etim_no " +
					" inner join org_chart oc on t.depot_id = oc.org_chart_id" 
					+" inner join schedule s on s.schedule_number = t.schedule_no" +
					" inner join schedule_type st on st.schedule_type_id = s.schedule_type " +
					" inner join schedule_details sd on sd.schedule_number = s.schedule_id" +
					" inner join shift_type sft on sd.shift_type_id = sft.shift_type_id "+
					"and d.status = 'ACTIVE' and d.deleted_status = 0  "
					+ "where t.waybill_no='"
					+ waybillno
					+ "' and t.trip_no='"
					+ tripno
					+ "' and t.shift_no='"
					+ shift_no
					+ "' and t.ticket_date = '" + ticketDate + "' group by ticket_no order by ticket_no , transaction_no ";

			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();

				OnlineWaybills onlineWaybill = new OnlineWaybills();

				onlineWaybill.setTicket_no(obj[0].toString().trim());
				onlineWaybill.setTransaction_no(obj[1].toString().trim());
				onlineWaybill.setTicket_type_name(obj[2].toString().trim());
				onlineWaybill.setTicket_sub_type_name(obj[3].toString().trim());
				onlineWaybill.setSchedule_no(obj[4].toString().trim());
				onlineWaybill.setScheduleType(obj[5].toString().trim());
				onlineWaybill.setShiftType(obj[6].toString().trim());
				onlineWaybill.setRouteNo(obj[7].toString().trim());
				onlineWaybill.setFrom_stop_name(obj[8].toString().trim());
				onlineWaybill.setTill_stop_name(obj[9].toString().trim());
				onlineWaybill.setTicket_time(obj[10].toString().trim());
				onlineWaybill.setPx_count(obj[11].toString().trim());
				onlineWaybill.setPx_total_amount(obj[12].toString().trim());
				onlineWaybill.setLugg_units(obj[13].toString().trim());
				onlineWaybill.setLugg_total_amount(obj[14].toString().trim());
				onlineWaybill.setTotal_ticket_amount(obj[15].toString().trim());
				onlineWaybill.setDepotName(obj[16].toString().trim());

				onlineWaybill.setWaybillNo(obj[0].toString().trim());
				arrayList.add(onlineWaybill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return arrayList;
	}
	@SuppressWarnings("finally")
	public List<OnlineWaybills> getWaybillDayWise(String waybillNo,String ticketDate,String depotId,String tripno,String shift_no)
	{
		List<OnlineWaybills> waybillWiseDayWiseDetails = new ArrayList<OnlineWaybills>();
		Session session = null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
//			String query = "SELECT egt.waybill_no,egt.schedule_no,st.schedule_type_name , sft.shift_type_name,egt.etim_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount, " +
//					" count(distinct egt.ticket_no) as no_of_ticket ,egt.ticket_date FROM etim_gprs_ticket egt " +
//					" inner join device d on d.device_serial_number = egt.etim_no " +
//					" inner join schedule s on s.schedule_number = egt.schedule_no " +
//					" inner join schedule_type st on st.schedule_type_id = s.schedule_type " +
//					" inner join schedule_details sd on sd.schedule_number = s.schedule_id" +
//					" inner join shift_type sft on sd.shift_type_id = sft.shift_type_id "+ 
//					"and d.status = 'ACTIVE' and d.deleted_status = 0 " +
//					" where   egt.waybill_no = '"+waybillNo+"' and egt.depot_id='"+depotId+"' group by egt.ticket_date order by egt.ticket_date ";
			
			/*String query = "SELECT egt.waybill_no,egt.schedule_no,st.schedule_type_name , sft.shift_type_name,egt.etim_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount, " 
			+" count(distinct egt.ticket_no) as no_of_ticket ,egt.ticket_date,egt.ticket_time,oc.org_name FROM etim_gprs_ticket egt  "
			+" inner join device d on d.device_serial_number = egt.etim_no "
			+" inner join    ticket_type tt    on tt.ticket_type_id=egt.tkt_type_short_code "
			+" inner join    org_chart oc  on oc.org_chart_id = egt.depot_id "
			+" inner join schedule s on s.schedule_number = egt.schedule_no  "
			+" inner join schedule_type st on st.schedule_type_id = s.schedule_type  "
			+"  inner join schedule_details sd on sd.schedule_number = s.schedule_id "
			+" inner join shift_type sft on sd.shift_type_id = sft.shift_type_id  "
			+" and d.status = 'ACTIVE' and d.deleted_status ='0' "
			+" where  tt.ticket_type_name ='Penalty Ticket'  and egt.waybill_no = '"+waybillNo+"' and  egt.depot_id='"+depotId+"'  order by egt.ticket_date ";*/
			
			String query="select CAST(trim(t.ticket_no) AS UNSIGNED) as ticket_no,CAST(trim(t.transaction_no) AS UNSIGNED) as transaction_no" +
					" ,if(ty.ticket_type_name is null,pass_id_no,ty.ticket_type_name)ticket_type_name, "
			+" ifnull(tsy.ticket_sub_type_name,'') ticket_sub_type_name,t.schedule_no,st.schedule_type_name , sft.shift_type_name,t.route_no,  "
			+" ifnull(bs.bus_stop_name,' ') as from_stop_name,ifnull(bs2.bus_stop_name,' ' ) as till_stop_name,"
		+" t.ticket_time, t.px_count, sum(t.px_total_amount) px_total_amount , t.lugg_units, sum(t.lugg_total_amount) lugg_total_amount,sum(t.px_total_amount+t.lugg_total_amount) total_ticket_amount,oc.org_name "
			+" from  etim_gprs_ticket t " 
			+" left join  bus_stop bs on bs.bus_stop_id=t.from_bus_stop_id  "
			+" left join  bus_stop bs2 on bs2.bus_stop_id=t.till_bus_stop_id  "
			+" left join ticket_type ty on ty.ticket_type_id=t.tkt_type_short_code  "
			+" left join ticket_sub_type tsy on tsy.ticket_sub_type_code=t.tkt_sub_type_short_code  "
			+" inner join device d on d.device_serial_number = t.etim_no  "
			+" inner join org_chart oc on t.depot_id = oc.org_chart_id " 
			+"  inner join schedule s on s.schedule_number = t.schedule_no "
			+" inner join schedule_type st on st.schedule_type_id = s.schedule_type  "
			+"  inner join schedule_details sd on sd.schedule_number = s.schedule_id "
			+"  inner join shift_type sft on sd.shift_type_id = sft.shift_type_id  "
			+" and d.status = 'ACTIVE' and d.deleted_status = 0  " 
			+" where t.waybill_no='"+waybillNo+"'  and t.trip_no=  '"+ tripno+ "' and t.tkt_type_short_code= '6' and t.shift_no='"+ shift_no+ "' and t.ticket_date = '" + ticketDate + "' group by ticket_no order by ticket_no , transaction_no ";
			
			
			System.out.println("query@@@@@@@"+query);
			
			Query queryObject = session.createSQLQuery(query);
			List list = queryObject.list();
			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();

				OnlineWaybills onlineWaybill = new OnlineWaybills();

				onlineWaybill.setTicket_no(obj[0].toString().trim());
				onlineWaybill.setTransaction_no(obj[1].toString().trim());
				onlineWaybill.setTicket_type_name(obj[2].toString().trim());
				onlineWaybill.setTicket_sub_type_name(obj[3].toString().trim());
				onlineWaybill.setSchedule_no(obj[4].toString().trim());
				onlineWaybill.setScheduleType(obj[5].toString().trim());
				onlineWaybill.setShiftType(obj[6].toString().trim());
				onlineWaybill.setRouteNo(obj[7].toString().trim());
				onlineWaybill.setFrom_stop_name(obj[8].toString().trim());
				onlineWaybill.setTill_stop_name(obj[9].toString().trim());
				onlineWaybill.setTicket_time(obj[10].toString().trim());
				onlineWaybill.setPx_count(obj[11].toString().trim());
				onlineWaybill.setPx_total_amount(obj[12].toString().trim());
				onlineWaybill.setLugg_units(obj[13].toString().trim());
				onlineWaybill.setLugg_total_amount(obj[14].toString().trim());
				onlineWaybill.setTotal_ticket_amount(obj[15].toString().trim());
				onlineWaybill.setDepotName(obj[16].toString().trim());

				onlineWaybill.setWaybillNo(obj[0].toString().trim());
				waybillWiseDayWiseDetails.add(onlineWaybill);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return waybillWiseDayWiseDetails;
		}
	}
	
	
	//show division list
	public Map<Integer, String> getDivision() {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Date startDate = new Date();
			Query query = session
					.createSQLQuery("Select org_chart_id, org_name from org_chart Where org_type_id='2' and deleted_status = '0' ");
			List list = query.list();
			Date endDate = new Date();
			int msElapsedTime = (int) (endDate.getTime() - startDate.getTime());
			// System.out.println("Depots time >>>>>>"+ msElapsedTime);

			resultMap.put(0, "Select");
			for (Iterator i = list.iterator(); i.hasNext();) {
				Object[] obj = (Object[]) i.next();
				resultMap.put(Integer.parseInt(obj[0].toString().trim()),
						obj[1].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return resultMap;
	}
	
	
	
	
	//show division list
		public Map<Integer, String> getDivisionLincence() {

			Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			try {
				Date startDate = new Date();
				Query query = session
						.createSQLQuery("Select org_chart_id, org_name from org_chart Where org_type_id='2' and deleted_status = '0' ");
				List list = query.list();
				Date endDate = new Date();
				int msElapsedTime = (int) (endDate.getTime() - startDate.getTime());
				// System.out.println("Depots time >>>>>>"+ msElapsedTime);

				resultMap.put(0, "Select");
				resultMap.put(1, "------ALL----");
				for (Iterator i = list.iterator(); i.hasNext();) {
					Object[] obj = (Object[]) i.next();
					resultMap.put(Integer.parseInt(obj[0].toString().trim()),
							obj[1].toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return resultMap;
		}
	public List getDepotName(int parentID) {
		List list = new ArrayList();
		String qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID + " and org_type_id=3  order by org_name";
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
	
	public List getDepotId(int parentID) {
		List list = new ArrayList();
		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID + " and org_type_id=3 order by org_name";
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
	}
	
	public ArrayList<OnlineWaybills> getAllDepotWaybills(String depotId,
			String ticketDate) {

		ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			/*
			 * String strQry=
			 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
			 * +
			 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
			 * + " and a.status like 'Active%' order by TOKEN asc";
			 */
			Date startDate = new Date();

			String strQry = "SELECT egt.waybill_no, egt.schedule_no, st.schedule_type_name , sft.shift_type_name,egt.etim_no, egt.route_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount,"
					+ "  count(distinct egt.ticket_no) as no_of_ticket,egt.vehicle_no "
					+ " FROM etim_gprs_ticket egt " 
					+ " inner join device d on d.device_serial_number = egt.etim_no " +
					" inner join schedule s on s.schedule_number = egt.schedule_no" +
					" inner join schedule_type st on st.schedule_type_id = s.schedule_type " +
					" inner join schedule_details sd on sd.schedule_number = s.schedule_id" +
					" inner join shift_type sft on sd.shift_type_id = sft.shift_type_id and d.status = 'ACTIVE' and d.deleted_status = 0 " 
					+ " where egt.ticket_date = '"+ ticketDate + "' and egt.depot_id = '"+ depotId+ "' "
					+ " group by egt.waybill_no, egt.schedule_no, egt.etim_no order by  egt.waybill_no ;";

			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();

				OnlineWaybills onlineWaybill = new OnlineWaybills();

				onlineWaybill.setWaybillNo(obj[0].toString().trim());
				onlineWaybill.setScheduleNo(obj[1].toString().trim());
				onlineWaybill.setScheduleType(obj[2].toString().trim());
				onlineWaybill.setShiftType(obj[3].toString().trim());
				onlineWaybill.setEtimNo(obj[4].toString().trim());
				onlineWaybill.setRouteNo(obj[5].toString().trim());
				onlineWaybill.setTotal_ticket_amount(obj[6].toString().trim());
				onlineWaybill.setNoOfTickets(obj[7].toString().trim());
				onlineWaybill.setVehicleNo(obj[8].toString());

				arrayList.add(onlineWaybill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return arrayList;
	}
	
	//depowise data 
	public ArrayList<OnlineWaybills> getAllWaybillsOfDepot(String divisionId,
			String ticketDate) {

		ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			/*
			 * String strQry=
			 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
			 * +
			 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
			 * + " and a.status like 'Active%' order by TOKEN asc";
			 */
			Date startDate = new Date();

			String strQry="SELECT    oc.org_name,   count(distinct egt.etim_no) as etim_no,   count(distinct egt.ticket_no) as no_of_ticket "+
					 "FROM    etim_gprs_ticket egt "+
					" inner join    ticket_type tt    on tt.ticket_type_id=egt.tkt_type_short_code "+
					" inner join    device d  on d.device_serial_number =  egt.etim_no "+
					" inner join    org_chart oc  on oc.org_chart_id = egt.depot_id "+
					" inner join    schedule s       on s.schedule_number = egt.schedule_no "+
					" inner join    schedule_type st       on st.schedule_type_id = s.schedule_type "+
					" inner  join    schedule_details sd       on sd.schedule_number = s.schedule_id "+
					" inner join    shift_type sft       on sd.shift_type_id = sft.shift_type_id "+
					" and d.status =  'ACTIVE'       and d.deleted_status = 0 "+
					" where     egt.ticket_date ='"+ ticketDate + "' " +
					" and tt.ticket_type_name ='Penalty Ticket'    and oc.parent_id='"+divisionId+"' "+
					" group by    egt.depot_id order by     egt.depot_id";
			
			
			

			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();

				OnlineWaybills onlineWaybill = new OnlineWaybills();

				onlineWaybill.setWaybillNo(obj[0].toString().trim());
				onlineWaybill.setScheduleNo(obj[1].toString().trim());
				onlineWaybill.setScheduleType(obj[2].toString().trim());
				

				arrayList.add(onlineWaybill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return arrayList;
	}

	//end
	//depowise data 
		public ArrayList<OnlineWaybills> getLCWaybillsOfDepot(String depotId,
				String ticketDate,String divisionid) {

			ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();

			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			try {
				/*
				 * String strQry=
				 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
				 * +
				 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
				 * + " and a.status like 'Active%' order by TOKEN asc";
				 */
				Date startDate = new Date();

				/*String strQry = " SELECT egt.waybill_no, egt.schedule_no, st.schedule_type_name , sft.shift_type_name,egt.etim_no, egt.route_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount,"
						+ "  count(distinct egt.ticket_no) as no_of_ticket,egt.vehicle_no "
						+ " FROM etim_gprs_ticket egt " 
						+ " inner join device d on d.device_serial_number = egt.etim_no " +
						" inner join schedule s on s.schedule_number = egt.schedule_no" +
						" inner join schedule_type st on st.schedule_type_id = s.schedule_type " +
						" inner join schedule_details sd on sd.schedule_number = s.schedule_id" +
						" inner join shift_type sft on sd.shift_type_id = sft.shift_type_id and d.status = 'ACTIVE' and d.deleted_status = 0 " 
						+ " where egt.ticket_date = '"+ ticketDate + "' and egt.depot_id = '"+ depotId+ "' "
						+ " group by egt.waybill_no, egt.schedule_no, egt.etim_no order by  egt.waybill_no ;";*/
				
				
				String strQry = "SELECT egt.waybill_no, egt.schedule_no, st.schedule_type_name , sft.shift_type_name,egt.etim_no, egt.route_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount,"
					+" count(distinct egt.ticket_no) as no_of_ticket,egt.vehicle_no,oc.org_name ,egt.trip_no,egt.shift_no"
					+" FROM etim_gprs_ticket egt  "
					+" inner join device d on d.device_serial_number = egt.etim_no "
				 	+" inner join    ticket_type tt    on tt.ticket_type_id=egt.tkt_type_short_code "
				 	+" inner join    org_chart oc  on oc.org_chart_id = egt.depot_id "
					+"  inner join schedule s on s.schedule_number = egt.schedule_no "
					+"  inner join schedule_type st on st.schedule_type_id = s.schedule_type " 
					 +" inner join schedule_details sd on sd.schedule_number = s.schedule_id "
					+"  inner join shift_type sft on sd.shift_type_id = sft.shift_type_id and d.status = 'ACTIVE' and d.deleted_status = 0 " 
					+"  where egt.ticket_date = '"+ticketDate+"'and tt.ticket_type_name ='Penalty Ticket' and oc.parent_id='"+divisionid+"' and egt.depot_id = '"+depotId+"' " 
					+" group by egt.waybill_no, egt.schedule_no, egt.etim_no order by  egt.waybill_no ";

				Query query = session.createSQLQuery(strQry);
				List list = query.list();

				for (Iterator i = list.iterator(); i.hasNext();) {

					Object[] obj = (Object[]) i.next();

					OnlineWaybills onlineWaybill = new OnlineWaybills();

					onlineWaybill.setWaybillNo(obj[0].toString().trim());
					onlineWaybill.setScheduleNo(obj[1].toString().trim());
					onlineWaybill.setScheduleType(obj[2].toString().trim());
					onlineWaybill.setShiftType(obj[3].toString().trim());
					onlineWaybill.setEtimNo(obj[4].toString().trim());
					onlineWaybill.setRouteNo(obj[5].toString().trim());
					onlineWaybill.setTotal_ticket_amount(obj[6].toString().trim());
					onlineWaybill.setNoOfTickets(obj[7].toString().trim());
					onlineWaybill.setVehicleNo(obj[8].toString());
					onlineWaybill.setDepotName(obj[9].toString());
					onlineWaybill.setTripno(obj[10].toString());
					onlineWaybill.setShift_No(obj[11].toString());
					arrayList.add(onlineWaybill);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return arrayList;
		}

	
}
