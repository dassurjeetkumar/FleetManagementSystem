package com.trimx.its.OnlineWaybills.action;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ShowOnlineWaybillsAction extends ActionSupport {

	/**
	 * 
	 */
	private String dateforonlineticket;
	public String getDateforonlineticket() {
		return dateforonlineticket;
	}

	public void setDateforonlineticket(String dateforonlineticket) {
		this.dateforonlineticket = dateforonlineticket;
	}

	public String getDepotNameForTicket() {
		return depotNameForTicket;
	}

	public void setDepotNameForTicket(String depotNameForTicket) {
		this.depotNameForTicket = depotNameForTicket;
	}
	
	private String depotNameForTicket;

	private static final long serialVersionUID = 1L;
	private ShowOnlineWaybillDAO waybillShowDao = new ShowOnlineWaybillDAO();
	
	private String waybillNo;
	private String depotId;
	private String depotName;
	private String scheduleNumber;
	private String ticketDate;
	private double totalAmount;
	private int totalTickets;
	private double totalPassengerAmt;
	private int totalPassengerCnt;
	private int totallugCnt;
	private double totalLugAmount;
	private double totaltotalAmount;
	private int totalPassengers;
	public String orgname;
	private String vehiclelist;
	private String startdate;
	private String enddate;
	private Map<Integer, String> divisionlist;
	private String depotlist1;
	private String divisionlist1;
	private double totalCashAmt;
	private double totalCardAmt;
	private double totalServiceTaxAmt;
	
	
	
	public double getTotalServiceTaxAmt() {
		return totalServiceTaxAmt;
	}

	public void setTotalServiceTaxAmt(double totalServiceTaxAmt) {
		this.totalServiceTaxAmt = totalServiceTaxAmt;
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	public String getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	
	
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}

	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
	}

	private Map<Integer, String> depotlist;

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getDepotId() {
		return depotId;
	}

	public void setDepotId(String depotId) {
		this.depotId = depotId;
	}

	ArrayList<OnlineWaybills> showWaybill = new ArrayList<OnlineWaybills>();
	
	public ArrayList<OnlineWaybills> getShowWaybill() {
		return showWaybill;
	}

	public void setShowWaybill(ArrayList<OnlineWaybills> showWaybill) {
		this.showWaybill = showWaybill;
	}

	private Map<Integer, String> depotMap;

	public String execute() {
		return SUCCESS;
	}

	public Map<Integer, String> getDepotMap() {
		return depotMap;
	}

	public void setDepotMap(Map<Integer, String> depotMap) {
		this.depotMap = depotMap;
	}

	public String showSelection() {
		
		depotMap = waybillShowDao.getDepots();
		return SUCCESS;
	}

	public String getTicketDate() {
		return ticketDate;
	}

	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}
	
	

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	public int getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	
	
	public double getTotalPassengerAmt() {
		return totalPassengerAmt;
	}

	public void setTotalPassengerAmt(double totalPassengerAmt) {
		this.totalPassengerAmt = totalPassengerAmt;
	}

	public int getTotalPassengerCnt() {
		return totalPassengerCnt;
	}

	public void setTotalPassengerCnt(int totalPassengerCnt) {
		this.totalPassengerCnt = totalPassengerCnt;
	}

	public int getTotallugCnt() {
		return totallugCnt;
	}

	public void setTotallugCnt(int totallugCnt) {
		this.totallugCnt = totallugCnt;
	}

	public double getTotalLugAmount() {
		return totalLugAmount;
	}

	public void setTotalLugAmount(double totalLugAmount) {
		this.totalLugAmount = totalLugAmount;
	}

	public double getTotaltotalAmount() {
		return totaltotalAmount;
	}

	public void setTotaltotalAmount(double totaltotalAmount) {
		this.totaltotalAmount = totaltotalAmount;
	}
	
	

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
	
	

	/**
	 * @return the depotName
	 */
	public String getDepotName() {
		return depotName;
	}

	/**
	 * @param depotName the depotName to set
	 */
	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}

	/**
	 * @return the scheduleNumber
	 */
	public String getScheduleNumber() {
		return scheduleNumber;
	}
	
	

	/**
	 * @param scheduleNumber the scheduleNumber to set
	 */
	public void setScheduleNumber(String scheduleNumber) {
		this.scheduleNumber = scheduleNumber;
	}

	
	public int getTotalPassengers() {
		return totalPassengers;
	}

	public void setTotalPassengers(int totalPassengers) {
		this.totalPassengers = totalPassengers;
	}

	public String showDataOfDepot() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//System.out.println("\n \t Show Online Waybillss Action.......");
		//System.out.println("\n \t ShowDataOfDepot() method.......");
		HttpServletRequest request = ServletActionContext.getRequest();
		String depotid="";
		String ticketdate="";
		//System.out.println("request.getParameter"+request.getParameter("ticket_date"));
		//System.out.println("request.getParameter"+request.getParameter("depotid"));
		if(request.getParameter("depotid")!=null){
			//System.out.println("ticketdate----> ");
		 depotid = request.getParameter("depotid");
		 ticketdate = request.getParameter("ticket_date");
		 //System.out.println("ticketdate----> "+ticketdate);
		 setDepotId(depotid);
			setTicketDate(ticketdate);
		}
		
		
		Common common = new Common();
		String strQry1 = "select org_name as org_name from org_chart where org_chart_id="+depotId+" and deleted_status=0";
		
		try {
			orgname = common.getDBResultStr(session, strQry1, "org_name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("divname is"+orgname);
		
		depotMap = waybillShowDao.getDepots();
		
		double totAmt = 0.0;
		double totCash=0.0;
		double totCard=0.0;
		double totservicetax=0.0;
		int totTickets = 0;
		int totPassengers = 0;
		DecimalFormat df = new DecimalFormat("#.##");
			try{	
		showWaybill = waybillShowDao.getAllWaybills(depotId,ticketDate);
			
		
		for(int i=0;i<showWaybill.size();i++){
			totCash = Double.valueOf(df.format(totCash+Double.parseDouble(showWaybill.get(i).getCash_amt())));
			totCard =Double.valueOf(df.format(totCard+Double.parseDouble(showWaybill.get(i).getCard_amt())));
			totservicetax =Double.valueOf(df.format(totservicetax+Double.parseDouble(showWaybill.get(i).getService_tax_amt())));
			totAmt = Double.valueOf(df.format(totAmt+Double.parseDouble(showWaybill.get(i).getTotal_ticket_amount())));
			totTickets = totTickets+Integer.parseInt(showWaybill.get(i).getNoOfTickets());
			totPassengers += Integer.parseInt(showWaybill.get(i).getPx_count());
			
		}
		setTotalCashAmt(totCash);
		setTotalCardAmt(totCard);
		setTotalServiceTaxAmt(totservicetax);
		setTotalAmount(totAmt);
		setTotalTickets(totTickets);
		setDepotId(depotId);
		setTicketDate(ticketDate);
		setTotalPassengers(totPassengers);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return SUCCESS;
	}
	
	private String devicelicensenumber;
	private String vehicleno;
	

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getDevicelicensenumber() {
		return devicelicensenumber;
	}

	public void setDevicelicensenumber(String devicelicensenumber) {
		this.devicelicensenumber = devicelicensenumber;
	}

	public String showDataOfDepotWaybillwise() {
		
		//System.out.println("\n \t Show Online Waybillss Action.......");
		HttpServletRequest request = ServletActionContext.getRequest();
		String waybillno = request.getParameter("waybillno");
		String depotid = request.getParameter("depotid");
		String ticketdate = request.getParameter("ticketdate");
		devicelicensenumber = request.getParameter("deviceser");
		vehicleno = request.getParameter("vehicleNo");
		orgname=request.getParameter("orgname");
		DecimalFormat df = new DecimalFormat("#.##");
		
		setDepotId(depotid);
		setTicketDate(ticketdate);
		depotMap = waybillShowDao.getDepots();
		showWaybill = waybillShowDao.getAllWaybillswaybillwise(depotid,ticketdate,waybillno);
		if(showWaybill.size()>0){
			setDepotName(showWaybill.get(0).getDepotName());
			setScheduleNumber(showWaybill.get(0).getScheduleNo());
		}
		double totAmt = 0.0;
		double totcash=0.0, totcard=0.0;
		int totTkt = 0;
		for(int i=0;i<showWaybill.size();i++){
		   totcash = Double.valueOf(df.format(totcash+Double.parseDouble(showWaybill.get(i).getCash_amt())));
		   totcard =Double.valueOf(df.format(totcard+Double.parseDouble(showWaybill.get(i).getCard_amt())));
			totAmt = Double.valueOf(df.format(totAmt+Double.parseDouble(showWaybill.get(i).getTotal_ticket_amount())));
			totTkt = totTkt+Integer.parseInt(showWaybill.get(i).getNoOfTickets());
		}
		setTotalCashAmt(totcash);
		setTotalCardAmt(totcard);
		setTotalAmount(totAmt);
		setTotalTickets(totTkt);
		return SUCCESS;
	}
	
	public String showDataOfDepotTripwise() {
		try{
			
		HttpServletRequest request = ServletActionContext.getRequest();
		String waybillno = request.getParameter("waybillno");
		String depotid = request.getParameter("depotid");
		String ticketdate = request.getParameter("ticketdate");
		String tripno = request.getParameter("tripNo");
		String shift_no = request.getParameter("shift_no");
		devicelicensenumber = request.getParameter("deviceser");
		vehicleno = request.getParameter("vehicleNo");
		orgname = request.getParameter("orgname");
		
		
		setWaybillNo(waybillno);
		setDepotId(depotid);
		setTicketDate(ticketdate);
		depotMap = waybillShowDao.getDepots();
		showWaybill = waybillShowDao.getAllWaybillstripwise(depotid,ticketdate,waybillno,tripno,shift_no);
		if(showWaybill.size()>0){
			setDepotName(showWaybill.get(0).getDepotName());
			setScheduleNumber(showWaybill.get(0).getSchedule_no());
		}
		
		int totPassCnt = 0;
		double totalPassAmt=0.0;
		int totLuggCnt = 0;
		double totalLugAmt = 0;
		double tottotAmt = 0;
		double totalCash=0;
		double totalCardAmt=0;
		DecimalFormat df = new DecimalFormat("#.##");
		for(int i=0;i<showWaybill.size();i++){
			totPassCnt = totPassCnt + Integer.parseInt(showWaybill.get(i).getPx_count());
			totalPassAmt = Double.valueOf(df.format(totalPassAmt + Double.parseDouble(showWaybill.get(i).getPx_total_amount())));
			totLuggCnt = totLuggCnt + Integer.parseInt(showWaybill.get(i).getLugg_units());
			totalCash = Double.valueOf(df.format(totalCash+ Double.parseDouble(showWaybill.get(i).getCash_amt())));
			totalCardAmt = Double.valueOf(df.format(totalCardAmt+Double.parseDouble(showWaybill.get(i).getCard_amt())));
			totalLugAmt = Double.valueOf(df.format(totalLugAmt + Double.parseDouble(showWaybill.get(i).getLugg_total_amount())));
			tottotAmt = Double.valueOf(df.format(tottotAmt + Double.parseDouble(showWaybill.get(i).getTotal_ticket_amount())));
		}
		
		
		setTotalPassengerCnt(totPassCnt);
		setTotalPassengerAmt(totalPassAmt);
		setTotallugCnt(totLuggCnt);
		setTotalCashAmt(totalCash);
		setTotalCardAmt(totalCardAmt);
		setTotalLugAmount(totalLugAmt);
		setTotaltotalAmount(tottotAmt);
	}		catch(Exception e){
		e.printStackTrace();
	}
		return SUCCESS;
	}
	
	public String ShowGPRSWaybillWiseDayWise(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String waybillno = request.getParameter("waybillno");
		String depotid = request.getParameter("depotid");
		String ticketdate = request.getParameter("ticketdate");
		
		setWaybillNo(waybillno);
		setDepotId(depotid);
		setTicketDate(ticketdate);
		
		setShowWaybill((ArrayList<OnlineWaybills>) waybillShowDao.getWaybillDayWise(waybillno, ticketdate, depotid));
		double totalAmt = 0.0;
		int totalTkt = 0;
		for(int i=0;i<showWaybill.size();i++){
			totalAmt = totalAmt+Double.parseDouble(showWaybill.get(i).getTotal_ticket_amount());
			totalTkt = totalTkt+Integer.parseInt(showWaybill.get(i).getNoOfTickets());
		}
		setTotalAmount(totalAmt);
		setTotalTickets(totalTkt);
		return SUCCESS;
	}
	
	public String reportDate;
	public List<Map<String,String>> depotWiseCountList;
	public int totalCount;
	
	
	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public List<Map<String, String>> getDepotWiseCountList() {
		return depotWiseCountList;
	}

	public void setDepotWiseCountList(List<Map<String, String>> depotWiseCountList) {
		this.depotWiseCountList = depotWiseCountList;
	}

	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getActiveETMCountDepotWise(){
		ShowOnlineWaybillDAO daoObject = new ShowOnlineWaybillDAO();
		if(this.getReportDate()==null || this.getReportDate().equals("")){
			this.setReportDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		}
		this.setDepotWiseCountList(daoObject.getActiveETMCountDepotWise(this.getReportDate()));
		this.setTotalCount(daoObject.getTotal(this.depotWiseCountList));
		return "success"; 
	}
	
	private String vehicleNo;
	
	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	

	private String serialno;
	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String showLiveBusDetails(){
		try{
		Date date = new Date();

		Date date1 = new Date();
		HttpServletRequest request = ServletActionContext.getRequest();
	    vehicleNo = request.getParameter("vehicleNo");
		ticketDate= request.getParameter("ticketdate");
		
		serialno=request.getParameter("deviceser");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
		Date date2 = (Date)formatter.parse(ticketDate);
		 orgname = request.getParameter("orgname");
		 depotId = request.getParameter("depotId");
		SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
		SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - 00:00");
		
		setTicketDate(sm2.format(date2));
		setStartdate(sm3.format(date));
		setEnddate(sm2.format(date));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
	}
	private Map<Integer, String> vehiclelistname;


	public Map<Integer, String> getVehiclelistname() {
		return vehiclelistname;
	}

	public void setVehiclelistname(Map<Integer, String> vehiclelistname) {
		this.vehiclelistname = vehiclelistname;
	}
	public static Date addDays(Date d, int days) {
		d.setTime(d.getTime()  - 10800 * 1000);
		return d;
	}

	public double getTotalCashAmt() {
		return totalCashAmt;
	}

	public void setTotalCashAmt(double totalCashAmt) {
		this.totalCashAmt = totalCashAmt;
	}

	public double getTotalCardAmt() {
		return totalCardAmt;
	}

	public void setTotalCardAmt(double totalCardAmt) {
		this.totalCardAmt = totalCardAmt;
	}

	
	
    
	
	
}

