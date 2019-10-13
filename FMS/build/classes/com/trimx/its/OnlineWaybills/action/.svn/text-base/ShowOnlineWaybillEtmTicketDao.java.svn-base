package com.trimx.its.OnlineWaybills.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

public class ShowOnlineWaybillEtmTicketDao {
	
	public static void main(String args[]) {
		ShowOnlineWaybillEtmTicketDao object = new ShowOnlineWaybillEtmTicketDao();
		// object.getAllWaybills("42");
	}

	public ArrayList<OnlineWaybills> getAllWaybills(String depotId,
			String ticketDate) {

		ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(0);
		Common common = new Common();
//		System.out.println("ticketDate"+ticketDate);
		String ticketDate1=common.getDateFromPicker(ticketDate);
		

		try {
			/*
			 * String strQry=
			 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
			 * +
			 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
			 * + " and a.status like 'Active%' order by TOKEN asc";
			 */
			Date startDate = new Date();

			String strQry = "SELECT egt.waybill_no, egt.schedule_no, st.schedule_type_name ,egt.etim_no, egt.route_no," +
					"if(egt.payment_mode=0,sum(egt.px_total_amount+egt.lugg_total_amount),0) cash_amount, "+
                  " if(egt.payment_mode=1,sum(egt.px_total_amount+egt.lugg_total_amount),0) card_amount, " +
                  " sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount,"
					+ "  count(distinct egt.ticket_no) as no_of_ticket,egt.vehicle_no" +
					",(select if(emp.EMPLOYEE_NAME is null,'',emp.EMPLOYEE_NAME) from employee emp where  emp.TOKEN=egt.conductor_token_id and WORKING_DESIGNATION in(2,16) and egt.depot_id='"+depotId+"' limit 1)EMPLOYEE_NAME," +
					//", if(emp.EMPLOYEE_NAME is null,'',emp.EMPLOYEE_NAME) EMPLOYEE_NAME" +
					"sum(egt.px_count) px_count, d.device_serial_number," +
					"(select ticket_code from   bmtcGprs.etim_gprs_ticket_08012017 FORCE INDEX(idx_waybill_no_ticket_date)  " +
					" where ticket_date = '"+ticketDate1+"' and waybill_no=egt.waybill_no and  tkt_sub_type_short_code='TS' limit 1 ) tkt_code "
					+ " FROM bmtcGprs.etim_gprs_ticket_08012017 egt "
					+ " left join device d on d.device_serial_number = egt.etim_no "
					+ " left join form_four ff on ff.form_four_id = egt.schedule_id "
					+ " left join schedule s on s.schedule_id = ff.schedule_number_id "
					+ " left join schedule_type st on st.schedule_type_id = s.schedule_type "
					/*
					 * " inner join schedule_details sd on sd.schedule_number = s.schedule_id "
					 * +
					 */
					/*
					 * " inner join shift_type sft on sd.shift_type_id = sft.shift_type_id "
					 * +
					 */
//					+ " and d.status = 'ACTIVE' and d.deleted_status = 0 "
					//+ "inner join employee emp on emp.TOKEN=egt.conductor_token_id and WORKING_DESIGNATION in(1,16) "
					+ " where egt.ticket_date = '"
					+ ticketDate1
					+ "' and egt.depot_id = '"
					+ depotId
					//+ "' and tkt_printed_flag ='Y' AND ticket_no!=0 AND fare_type!='NINC'"
					+ "' AND ticket_no!=0 AND fare_type!='NINC' AND d.status='ACTIVE' "
					+ " group by egt.waybill_no, egt.schedule_no, egt.etim_no order by  egt.waybill_no ";
			
			
//			SELECT egt.waybill_no, egt.schedule_no, st.schedule_type_name ,egt.etim_no, egt.route_no,
//			if(egt.payment_mode=0,sum(egt.px_total_amount+egt.lugg_total_amount),0) cash_amount,
//			if(egt.payment_mode=1,sum(egt.px_total_amount+egt.lugg_total_amount),0) card_amount,
//			sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount, 
//			count(distinct egt.ticket_no) as no_of_ticket,egt.vehicle_no,(select if(emp.EMPLOYEE_NAME is null,'',emp.EMPLOYEE_NAME)
//			from employee emp where  emp.TOKEN=egt.conductor_token_id and WORKING_DESIGNATION in(1,16) limit 1)EMPLOYEE_NAME,
//			sum(egt.px_count) px_count, d.device_serial_number,(select ticket_code from  
//			bmtcGprs.etim_gprs_ticket FORCE INDEX(idx_waybill_no_ticket_date) 
//			 where ticket_date = '2016-11-28' and waybill_no=egt.waybill_no and  tkt_sub_type_short_code='TS' limit 1 ) tkt_code 
//			FROM bmtcGprs.etim_gprs_ticket egt
//			 inner join device d on d.device_serial_number = egt.etim_no
//			 inner join form_four ff on ff.form_four_id = egt.schedule_id 
//			inner join schedule s on s.schedule_id = ff.schedule_number_id
//			inner join schedule_type st on st.schedule_type_id = s.schedule_type  and d.status = 'ACTIVE' and d.deleted_status = 0 
//			where egt.ticket_date = '2016-11-28' and egt.depot_id = '42' AND ticket_no!=0 AND fare_type!='NINC'
//			group by egt.waybill_no, egt.schedule_no, egt.etim_no order by  egt.waybill_no 
//			
			
//System.out.println(strQry);
			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();
				//Call vehicle ID
				
//				String strQry1 = "select vehicle_id as vehicle_id from vehicle where license_number='"+obj[7].toString()+"'";
//				String vehicleid = common.getDBResultStr(session, strQry1, "vehicle_id");
				//System.out.println("vehicleid"+vehicleid);
				//Call device_serial_number
				
//				String strQry2 ="select device_serial_number from device"
//				+" dev inner join vehicle_device vd on vd.device_id=dev.device_id where vd.vehicle_id="+vehicleid+" and vd.status='ACTIVE' and dev.deleted_status='ACTIVE'";
//				String deviceno = common.getDBResultStr(session, strQry2, "device_serial_number");
				//System.out.println("device_serial_number"+deviceno);
				
				//Query For Version No(Ticket code)
//				String strQry3 ="SELECT ticket_code FROM bmtcGprs.etim_gprs_ticket"
//						+" WHERE ticket_date = '"+ticketDate1+"'"+" AND waybill_no = '"+obj[0].toString()+"'"+" AND etim_no = '"+obj[3].toString()+"'"+" and tkt_sub_type_short_code='TS' limit 1";
//						String ticketcode = common.getDBResultStr(session, strQry3, "ticket_code");
//						System.out.println("ticket_code"+ticketcode);
				OnlineWaybills onlineWaybill = new OnlineWaybills();
				
				onlineWaybill.setWaybillNo(obj[0].toString().trim());
				onlineWaybill.setScheduleNo(obj[1].toString().trim());
				onlineWaybill.setScheduleType(obj[2].toString().trim());
				onlineWaybill.setEtimNo(obj[3].toString().trim());
//				onlineWaybill.setTicket_code(ticketcode);
				onlineWaybill.setRouteNo(obj[4].toString().trim());
				onlineWaybill.setCash_amt(obj[5].toString().trim());
				onlineWaybill.setCard_amt(obj[6].toString().trim());
				onlineWaybill.setTotal_ticket_amount(obj[7].toString().trim());
				onlineWaybill.setNoOfTickets(obj[8].toString().trim());
				onlineWaybill.setVehicleNo(obj[9].toString());
				onlineWaybill.setConductorname(obj[10]!=null?obj[10].toString():"");
				onlineWaybill.setPx_count(nf.format(obj[11]));
				onlineWaybill.setTicket_code(obj[13]!=null ? obj[13].toString().trim():"N/A");
//				onlineWaybill.setDevice_serial_number(deviceno);
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
			HttpServletRequest req = ServletActionContext.getRequest();
			String orgtypeid = (String) req.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute(
					"orgchartid");
			Date startDate = new Date();
			List list = null;
			if(orgtypeid.equals("1")  && orgchartid.equals("1")){
			Query query = session
					.createSQLQuery("Select org_chart_id, org_name from org_chart Where org_type_id='3' and deleted_status = '0' ");
			 list = query.list();
			 resultMap.put(0, "Select");
			}else if(orgtypeid.equals("2")){
				Query query = session
						.createSQLQuery("Select org_chart_id, org_name from org_chart Where org_type_id='3' and parent_id="+orgchartid+" and deleted_status = '0' ");
				 list = query.list();
				 
			} else if(orgtypeid.equals("4")){
				Query query = session
						.createSQLQuery("Select org_chart_id, org_name from org_chart Where org_type_id='3'  and deleted_status = '0' ");
				 list = query.list();
			}
			else{
				Query query = session
						.createSQLQuery("Select org_chart_id, org_name from org_chart Where org_type_id='3' and org_chart_id="+orgchartid+" and deleted_status = '0' ");
				 list = query.list();
				 
			}
			Date endDate = new Date();
			int msElapsedTime = (int) (endDate.getTime() - startDate.getTime());
			// System.out.println("Depots time >>>>>>"+ msElapsedTime);

			
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

			/*
			 * String strQry =
			 * "SELECT egt.waybill_no,egt.schedule_no,st.schedule_type_name , A.shift_type_name,egt.etim_no,egt.route_no,egt.shift_no, CAST(trim(egt.trip_no) AS UNSIGNED) trip_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount, "
			 * + "count(distinct egt.ticket_no) as no_of_ticket,oc.org_name " +
			 * " FROM etim_gprs_ticket egt " +
			 * " inner join device d on d.device_serial_number = egt.etim_no " +
			 * "inner join org_chart oc on egt.depot_id = oc.org_chart_id"
			 * +" inner join schedule s on s.schedule_number = egt.schedule_no"
			 * +
			 * " inner join schedule_type st on st.schedule_type_id = s.schedule_type "
			 * +
			 * " inner join schedule_details sd on sd.schedule_number = s.schedule_id "
			 * +
			 * "inner join (select shift_type_name,schedule_number from shift_type sft inner join schedule_details sd on sd.shift_type_id = sft.shift_type_id group by schedule_number) A "
			 * + "on A.schedule_number = s.schedule_id " +
			 * "and d.status = 'ACTIVE' and d.deleted_status = 0 " +
			 * " where egt.ticket_date = '" + ticketDate +
			 * "' and egt.depot_id = '"+ depotId + "' and egt.waybill_no = '"+
			 * waybillno + "' " +
			 * " group by egt.waybill_no,egt.schedule_no,egt.etim_no,egt.shift_no,egt.trip_no order by egt.shift_no,trip_no "
			 * ;
			 */
			Common common = new Common();
			String ticketDate1=common.getDateFromPicker(ticketDate);
			String strQry = "SELECT egt.waybill_no,egt.schedule_no,st.schedule_type_name, "
					+ "egt.etim_no,egt.route_no,egt.shift_no, "
					+ "CAST(trim(egt.trip_no) AS UNSIGNED) trip_no, "
					+" if(egt.payment_mode=0,sum(egt.px_total_amount+egt.lugg_total_amount),0) cash_amount,"
					+" if(egt.payment_mode=1,sum(egt.px_total_amount+egt.lugg_total_amount),0) card_amount,"
					+ "sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount, "
					+ "count(distinct egt.ticket_no) as no_of_ticket,oc.org_name,st.schedule_code  "
					+ "FROM bmtcGprs.etim_gprs_ticket_08012017 egt "
					+ "inner join device d on d.device_serial_number = egt.etim_no "
					+ "inner join org_chart oc on egt.depot_id = oc.org_chart_id "
					+ "inner join form_four ff on egt.schedule_id = ff.form_four_id "
					+ "inner join schedule s on s.schedule_id = ff.schedule_number_id "
					+ "inner join schedule_type st on st.schedule_type_id = s.schedule_type  "
					+ "where egt.ticket_date = '"
					+ ticketDate1
					+ "' and egt.depot_id = '"
					+ depotId
					+ "' and egt.waybill_no = '"
					+ waybillno
					//+ "' and ticket_no!=0 AND fare_type!='NINC' and tkt_printed_flag ='Y'"
					+ "' and ticket_no!=0 AND fare_type!='NINC' AND d.status='ACTIVE' "
					+ "group by egt.waybill_no,egt.schedule_no,egt.etim_no,egt.shift_no,egt.trip_no order by egt.shift_no,trip_no";
			
			
			
			

			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();

				OnlineWaybills onlineWaybill = new OnlineWaybills();

				onlineWaybill.setWaybillNo(obj[0].toString().trim());
				onlineWaybill.setScheduleNo(obj[1].toString().trim());
				onlineWaybill.setScheduleType(obj[2].toString().trim());
				onlineWaybill.setEtimNo(obj[3].toString().trim());
				onlineWaybill.setRouteNo(obj[4].toString().trim());
				onlineWaybill.setShift_No(obj[5].toString().trim());
				onlineWaybill.setTripno(obj[6].toString().trim());
				onlineWaybill.setCash_amt(obj[9].toString().trim());
				onlineWaybill.setCard_amt(obj[8].toString().trim());
				onlineWaybill.setTotal_ticket_amount(obj[7].toString().trim());
				onlineWaybill.setNoOfTickets(obj[10].toString().trim());
				onlineWaybill.setDepotName(obj[11].toString().trim());
				onlineWaybill.setScheduleCode(obj[12].toString().trim());

				if (onlineWaybill.getScheduleCode().equals("NO")
						|| onlineWaybill.getScheduleCode().equals("NS")) {
					if (onlineWaybill.getShift_No().equals("1")) {
						onlineWaybill.setShiftType("DAY 1");
					} else {
						onlineWaybill.setShiftType("DAY 2");
					}
				} else if (onlineWaybill.getScheduleCode().equals("DO")) {
					if (onlineWaybill.getShift_No().equals("1")) {
						onlineWaybill.setShiftType("SHIFT 1");
					} else {
						onlineWaybill.setShiftType("SHIFT 2");
					}
				} else {
					onlineWaybill.setShiftType("GENERAl SHIFT");
				}
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
		Common common = new Common();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Common common1 = new Common();
			String ticketDate1=common1.getDateFromPicker(ticketDate);
			/*
			 * String strQry=
			 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
			 * +
			 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
			 * + " and a.status like 'Active%' order by TOKEN asc";
			 */
			Date startDate = new Date();

			String strQry = "select IF(CAST(trim(t.ticket_no) AS UNSIGNED)=0,'',ticket_no) as ticket_no,CAST(trim(t.transaction_no) AS UNSIGNED) as transaction_no,IFNULL(ty.ticket_type_name,pass_id_no) ticket_type_name, "
					+ " ifnull(tsy.ticket_sub_type_name,'') ticket_sub_type_name,t.schedule_no,st.schedule_type_name , t.shift_no,t.route_no, "
				+"	if(t.payment_mode=0,sum(t.px_total_amount+t.lugg_total_amount),0) cash_amount,"
				+" if(t.payment_mode=1,sum(t.px_total_amount+t.lugg_total_amount),0) card_amount,"
					+ " ifnull(bs.bus_stop_name,' ') as from_stop_name,ifnull(bs2.bus_stop_name,' ' ) as till_stop_name, "
//					+ " t.ticket_time, t.px_count, sum(t.px_total_amount) px_total_amount , t.lugg_units, sum(t.lugg_total_amount) lugg_total_amount,t.total_ticket_amount,oc.org_name,st.schedule_code,d.device_serial_number,t.tkt_printed_flag "
					+ " t.ticket_time, t.px_count, sum(t.px_total_amount) px_total_amount , t.lugg_units, sum(t.lugg_total_amount) lugg_total_amount,sum(t.px_total_amount+t.lugg_total_amount) total_ticket_amount,oc.org_name,st.schedule_code,d.device_serial_number,t.tkt_printed_flag "
					+ " from  bmtcGprs.etim_gprs_ticket_08012017 t  "
					+ " left join  bus_stop bs on bs.bus_stop_id=t.from_bus_stop_id "
					+ " left join  bus_stop bs2 on bs2.bus_stop_id=t.till_bus_stop_id "
					+ " left join ticket_type ty on ty.ticket_type_id=t.tkt_type_short_code "
					+ " left join ticket_sub_type tsy on tsy.ticket_sub_type_code=t.tkt_sub_type_short_code "
					+ " left join device d on d.device_serial_number = t.etim_no "
					+ " left join org_chart oc on t.depot_id = oc.org_chart_id "
					+ " left join form_four ff on t.schedule_id = ff.form_four_id "
					+ " left join schedule s on s.schedule_id = ff.schedule_number_id "
					+ " left join schedule_type st on st.schedule_type_id = s.schedule_type "
//					+ " and d.status = 'ACTIVE' and d.deleted_status = 0  "
					+ " where t.waybill_no='"
					+ waybillno
					+ "' and t.trip_no='"
					+ tripno
					+ "' and t.shift_no='"
					+ shift_no
					+ "' and t.ticket_date = '"
					+ ticketDate1
				//	+ "' AND fare_type!='NINC' and tkt_printed_flag ='Y'  GROUP BY transaction_no order by ticket_date,ticket_time "; //and ticket_no!=0group by ticket_no
					+ "' AND fare_type!='NINC' AND d.status='ACTIVE' GROUP BY transaction_no order by ticket_date,ticket_time ";
			

			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {

				Object[] obj = (Object[]) i.next();

				OnlineWaybills onlineWaybill = new OnlineWaybills();
				
				//finding the vts device id
//				String strQry1 = "select vehicle_id as vehicle_id from vehicle where license_number='"+vehicleno+"'";
//				String vehicleid = common.getDBResultStr(session, strQry1, "vehicle_id");
//				System.out.println("vehicleid"+vehicleid);
//				//Call device_serial_number
//				
//				String strQry2 ="select device_serial_number from device"
//				+" dev inner join vehicle_device vd on vd.device_id=dev.device_id where vd.vehicle_id="+vehicleid+" and vd.status='ACTIVE' and dev.deleted_status='ACTIVE'";
//				String deviceno = common.getDBResultStr(session, strQry2, "device_serial_number");
//				System.out.println("device_serial_number"+deviceno);
				
				//end

				onlineWaybill.setTicket_no(obj[0].toString().trim());
				onlineWaybill.setTransaction_no(obj[1].toString().trim());
				onlineWaybill.setTicket_type_name(obj[2].toString().trim());
				onlineWaybill.setTicket_sub_type_name(obj[3].toString().trim());
				onlineWaybill.setSchedule_no(obj[4].toString().trim());
				onlineWaybill.setScheduleType(obj[5].toString().trim());
				onlineWaybill.setShift_No(obj[6].toString().trim());
				onlineWaybill.setRouteNo(obj[7].toString().trim());
				onlineWaybill.setCash_amt(obj[8].toString().trim());
				onlineWaybill.setCard_amt(obj[9].toString().trim());
				onlineWaybill.setFrom_stop_name(obj[10].toString().trim());
				onlineWaybill.setTill_stop_name(obj[11].toString().trim());
				onlineWaybill.setTicket_time(obj[12].toString().trim());
				onlineWaybill.setPx_count(obj[13].toString().trim());
				onlineWaybill.setPx_total_amount(obj[14].toString().trim());
				onlineWaybill.setLugg_units(obj[15].toString().trim());
				onlineWaybill.setLugg_total_amount(obj[16].toString().trim());
				onlineWaybill.setTotal_ticket_amount(obj[17].toString().trim());
				onlineWaybill.setDepotName(obj[18].toString().trim());
				onlineWaybill.setScheduleCode(obj[19].toString().trim());
				//onlineWaybill.setVehicleNo(vehicleno);
				//onlineWaybill.setDevice_serial_number(deviceno);
				onlineWaybill.setWaybillNo(obj[0].toString().trim());

				if (onlineWaybill.getScheduleCode().equals("NO")
						|| onlineWaybill.getScheduleCode().equals("NS")) {
					if (onlineWaybill.getShift_No().equals("1")) {
						onlineWaybill.setShiftType("DAY 1");
					} else {
						onlineWaybill.setShiftType("DAY 2");
					}
				} else if (onlineWaybill.getScheduleCode().equals("DO")) {
					if (onlineWaybill.getShift_No().equals("1")) {
						onlineWaybill.setShiftType("SHIFT 1");
					} else {
						onlineWaybill.setShiftType("SHIFT 2");
					}
				} else {
					onlineWaybill.setShiftType("GENERAl SHIFT");
				}
				onlineWaybill.setPrinted_flag(obj[21].toString().trim());
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
	public List<OnlineWaybills> getWaybillDayWise(String waybillNo,
			String ticketDate, String depotId) {
		List<OnlineWaybills> waybillWiseDayWiseDetails = new ArrayList<OnlineWaybills>();
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			/*
			 * String query =
			 * "SELECT egt.waybill_no,egt.schedule_no,st.schedule_type_name , egt.shift_no,egt.etim_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount, "
			 * +
			 * " count(distinct egt.ticket_no) as no_of_ticket ,egt.ticket_date,st.schedule_code FROM etim_gprs_ticket egt "
			 * + "INNER JOIN device d ON d.device_serial_number = egt.etim_no "
			 * + "INNER JOIN schedule s ON s.schedule_id = egt.schedule_id " +
			 * "INNER JOIN schedule_type st ON st.schedule_type_id = s.schedule_type "
			 * +
			 * "inner join (select shift_type_name,schedule_number from shift_type sft inner join schedule_details sd on sd.shift_type_id = sft.shift_type_id group by schedule_number) A "
			 * + "on A.schedule_number = s.schedule_id " +
			 * "and d.status = 'ACTIVE' and d.deleted_status = 0 " +
			 * " where   egt.waybill_no = '"
			 * +waybillNo+"' and egt.depot_id='"+depotId+
			 * "' and ticket_no!=0 group by egt.ticket_date order by egt.ticket_date "
			 * ;
			 */
			String query = "SELECT egt.waybill_no,egt.schedule_no,st.schedule_type_name , egt.shift_no,egt.etim_no, "
					+ " sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount,"
					+ " count(distinct egt.ticket_no) as no_of_ticket ,egt.ticket_date,st.schedule_code FROM bmtcGprs.etim_gprs_ticket_08012017 egt"
					+ " left JOIN device d ON d.device_serial_number = egt.etim_no"
					+ " left join form_four ff on egt.schedule_id = ff.form_four_id"
					+ " left join schedule s on s.schedule_id = ff.schedule_number_id"
					+ " left JOIN schedule_type st ON st.schedule_type_id = s.schedule_type"
					+ " left join (select shift_type_name,schedule_number from shift_type sft "
					+ " left join schedule_details sd on sd.shift_type_id = sft.shift_type_id group by schedule_number) A"
					+ " on A.schedule_number = s.schedule_id"
					+ " and d.status = 'ACTIVE' " 
//					+"and d.deleted_status = 0"
					+ " where  egt.waybill_no = '"
					+ waybillNo
					+ "' and egt.depot_id='"
					+ depotId
					//+ "' and ticket_no!=0 AND fare_type!='NINC' and tkt_printed_flag ='Y' group by"
					+ "' and ticket_no!=0 AND fare_type!='NINC' group by"
					+ " egt.ticket_date order by egt.ticket_date ;";

			Query queryObject = session.createSQLQuery(query);
			List list = queryObject.list();
			for (int i = 0; i < list.size(); i++) {

				Object[] object = (Object[]) list.get(i);

				OnlineWaybills waybillModel = new OnlineWaybills();
				waybillModel.setWaybillNo(object[0].toString());
				waybillModel.setScheduleNo(object[1].toString());
				waybillModel.setScheduleType(object[2].toString());
				waybillModel.setShift_No(object[3].toString());
				waybillModel.setEtimNo(object[4].toString());
				waybillModel.setTotal_ticket_amount(object[5].toString());
				waybillModel.setNoOfTickets(object[6].toString());
				waybillModel.setTicket_date(object[7].toString());
				waybillModel.setScheduleCode(object[8].toString());
				if (waybillModel.getScheduleCode().equals("NO")
						|| waybillModel.getScheduleCode().equals("NS")) {
					if (waybillModel.getShift_No().equals("1")) {
						waybillModel.setShiftType("DAY 1");
					} else {
						waybillModel.setShiftType("DAY 2");
					}
				} else if (waybillModel.getScheduleCode().equals("DO")) {
					if (waybillModel.getShift_No().equals("1")) {
						waybillModel.setShiftType("SHIFT 1");
					} else {
						waybillModel.setShiftType("SHIFT 2");
					}
				} else {
					waybillModel.setShiftType("GENERAl SHIFT");
				}

				waybillWiseDayWiseDetails.add(waybillModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return waybillWiseDayWiseDetails;
		}
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<Map<String, String>> getActiveETMCountDepotWise(String date) {
		List<Map<String, String>> finalList = new ArrayList<Map<String, String>>();
		Session session = null;
		Common common = new Common();
		try {

			String WaybillDate = common.changeDataFormat(date, "dd-MM-yyyy", "yyMMdd");
			session = HibernateUtil.getSession("");

			String queryForData = "SELECT COUNT(*) as count,a.org_name FROM (SELECT count(DISTINCT egt.ticket_no) AS no_of_ticket,egt.vehicle_no," +
					" sum(egt.px_count) px_count,egt.depot_id,oc.org_name FROM (SELECT schedule_id,route_no,waybill_no,schedule_no,etim_no," +
					" px_total_amount,lugg_total_amount,ticket_no,vehicle_no,px_count,depot_id  FROM bmtcGprs.etim_gprs_ticket egt" +
					" WHERE egt.waybill_no like '%"+WaybillDate+"%' AND egt.tkt_type_short_code IN (1,3,4)) egt left JOIN device d ON d.device_serial_number = egt.etim_no  " +
					" left JOIN form_four ff ON ff.form_four_id = egt.schedule_id  " +
					" left JOIN schedule s ON s.schedule_id = ff.schedule_number_id " +
					" left JOIN schedule_type st ON st.schedule_type_id = s.schedule_type  " +
					" left JOIN org_chart oc ON oc.org_chart_id = egt.depot_id " +
					" AND d.status = 'ACTIVE' " +
//					"AND d.deleted_status = 0 " +
					" GROUP BY egt.waybill_no, egt.schedule_no,egt.etim_no) a " +
					" GROUP BY depot_id ORDER BY a.org_name;";

//			System.out.print(queryForData);
			finalList = session.createSQLQuery(queryForData)
					.addScalar("count", Hibernate.INTEGER)
					.addScalar("org_name", Hibernate.STRING)
					.setResultTransformer(
							Transformers.aliasToBean(OnlineWaybills.class))
					.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return finalList;
		}

	}

	public int getTotal(List<Map<String, String>> listOfData) {
		int total = 0;
		try {
			if (listOfData != null)
				for (int i = 0; i < listOfData.size(); i++) {
					OnlineWaybills waybill = (OnlineWaybills) listOfData.get(i);
					total += waybill.getCount();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;

	}

}
