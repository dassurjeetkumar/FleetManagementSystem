package com.trimax.its.OnlineWaybillLC.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.util.HibernateUtil;

public class OnlineWaybillLCAction  extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OnlineWaybillLCDAO waybillShowDao = new OnlineWaybillLCDAO();
	
	private String waybillNo;
	private String depotId;
	private String depotName;
	private String scheduleNumber;
	private String divisionId;
	private String ticketDate;
	private String ticketdate;
	private double totalAmount;
	private int totalTickets;
	private double totalPassengerAmt;
	private int totalPassengerCnt;
	private int totallugCnt;
	private double totalLugAmount;
	private int totaltotalAmount;

	
	
	public String getTicketdate() {
		return ticketdate;
	}

	public void setTicketdate(String ticketdate) {
		this.ticketdate = ticketdate;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
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
	private Map<Integer,String> divisionMap;
	

	public Map<Integer, String> getDivisionMap() {
		return divisionMap;
	}

	public void setDivisionMap(Map<Integer, String> divisionMap) {
		this.divisionMap = divisionMap;
	}

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
		System.out.println("\n \t Show Online Waybillss Action.......");
		depotMap = waybillShowDao.getDepots();
		divisionMap=waybillShowDao.getDivision();
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

	public int getTotaltotalAmount() {
		return totaltotalAmount;
	}

	public void setTotaltotalAmount(int totaltotalAmount) {
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

	public String showDataOfDepot() {
		System.out.println("\n \t Show Online Waybillss Actionaaaaaa.......");
		HttpServletRequest request = ServletActionContext.getRequest();
		String depotid="";
		String ticketdate="";
		String divisionid="";
		System.out.println("request.getParameter"+request.getParameter("ticketdate"));
		System.out.println("request.getParameter"+request.getParameter("depotId"));
		divisionid=request.getParameter("divisionId");
		setDivisionId(divisionid);
		if(request.getParameter("depotId").equals("all")){
				System.out.println("ticketdate----> ");
				depotid = request.getParameter("depotId");
				setDepotId(depotid);
				ticketdate = request.getParameter("ticketdate");
				setTicketDate(ticketdate);
				String divid=getDivisionId();
				System.out.println("ticketdate----> "+divid);
				divisionid=request.getParameter("divisionId");
		 
				System.out.println("ticketdate----> "+divisionid);
				divisionMap=waybillShowDao.getDivision();
				showWaybill = waybillShowDao.getAllWaybillsOfDepot(divisionid,ticketdate);
		}
		else{
			
			
				depotid = request.getParameter("depotId");
				System.out.println("perticular depot...."+depotid);
			 	setDepotId(depotid);
				setTicketDate(ticketdate);
				ticketdate = request.getParameter("ticketdate");
				setTicketDate(ticketdate);
				String divid=getDivisionId();
				System.out.println("ticketdate----> "+divid);
				divisionid=request.getParameter("divisionId");
			 
				System.out.println("ticketdate----> "+divisionid);
				divisionMap=waybillShowDao.getDivision();
				showWaybill = waybillShowDao.getPerticularDepot(divisionid,ticketdate,depotid);
			
		}
		//  depotMap = waybillShowDao.getDepots();
		
		double totAmt = 0.0;
		int totTickets = 0;
				
		
		
//		for(int i=0;i<showWaybill.size();i++){
//			totAmt = totAmt+Double.parseDouble(showWaybill.get(i).getTotal_ticket_amount());
//			totTickets = totTickets+Integer.parseInt(showWaybill.get(i).getNoOfTickets());
//		}
//		setTotalAmount(totAmt);
//		setTotalTickets(totTickets);
//		setDepotId(depotId);
//		setTicketDate(ticketDate);
		return SUCCESS;
	}

	public String showDataOfDepotWaybillwise() {
		
		
		
		System.out.println("\n \t Show Online Waybillss Action.......");
		HttpServletRequest request = ServletActionContext.getRequest();
		String waybillno = request.getParameter("waybillno");
		String depotid = request.getParameter("depotid");
		String ticketdate = request.getParameter("ticketdate");
		setDepotId(depotid);
		setTicketDate(ticketdate);
		depotMap = waybillShowDao.getDepots();
		showWaybill = waybillShowDao.getAllWaybillswaybillwise(depotid,ticketdate,waybillno);
		if(showWaybill.size()>0){
			setDepotName(showWaybill.get(0).getDepotName());
			setScheduleNumber(showWaybill.get(0).getScheduleNo());
		}
		double totAmt = 0.0;
		int totTkt = 0;
		for(int i=0;i<showWaybill.size();i++){
			totAmt = totAmt+Double.parseDouble(showWaybill.get(i).getTotal_ticket_amount());
			totTkt = totTkt+Integer.parseInt(showWaybill.get(i).getNoOfTickets());
		}
		setTotalAmount(totAmt);
		setTotalTickets(totTkt);
		return SUCCESS;
	}
	
	//data of perticular depot and division
	
	public String showDataOfPerticularDepot() {
		System.out.println("\n \t Show Online Waybillss Action.......");
		HttpServletRequest request = ServletActionContext.getRequest();
		String depotid="";
		String ticketdate="";
		String divisionid="";
		System.out.println("request.getParameter"+request.getParameter("ticketdate"));
		System.out.println("request.getParameter"+request.getParameter("depotId"));
		divisionid=request.getParameter("divisionId");
		if(request.getParameter("depotId")!=null){
			System.out.println("ticketdate----> ");
		 depotid = request.getParameter("depotId");
		 ticketdate = request.getParameter("ticketdate");
		 System.out.println("ticketdate----> "+ticketdate);
		 setDepotId(depotid);
			setTicketDate(ticketdate);
		}
		
		depotMap = waybillShowDao.getDepots();
		
		double totAmt = 0.0;
		int totTickets = 0;
				
		showWaybill = waybillShowDao.getLCWaybillsOfDepot(depotId,ticketDate,divisionid);
		if(showWaybill.size()>0){
			setDepotName(showWaybill.get(0).getDepotName());
			setScheduleNumber(showWaybill.get(0).getScheduleNo());
		}
		for(int i=0;i<showWaybill.size();i++){
			totAmt = totAmt+Double.parseDouble(showWaybill.get(i).getTotal_ticket_amount());
			totTickets = totTickets+Integer.parseInt(showWaybill.get(i).getNoOfTickets());
		}
		setTotalAmount(totAmt);
		setTotalTickets(totTickets);
		setDepotId(depotId);
		setTicketDate(ticketDate);
		return SUCCESS;
	}
	//end 
	
	public String showDataOfDepotTripwise() {
		try{
			
		System.out.println("\n \t Show Online Waybillss Action.......");
		HttpServletRequest request = ServletActionContext.getRequest();
		String waybillno = request.getParameter("waybillno");
		String depotid = request.getParameter("depotid");
		String ticketdate = request.getParameter("ticketdate");
		String tripno = request.getParameter("tripNo");
		String shift_no = request.getParameter("shift_no");
		
		setWaybillNo(waybillno);
		setDepotId(depotid);
		setTicketDate(ticketdate);
		depotMap = waybillShowDao.getDepots();
		showWaybill = waybillShowDao.getAllWaybillstripwise(depotid,ticketdate,waybillno,tripno,shift_no);
		if(showWaybill.size()>0){
			setDepotName(showWaybill.get(0).getDepotName());
			setScheduleNumber(showWaybill.get(0).getSchedule_no());
		}
		System.out.println("\n \tticket : "+showWaybill.size());
		int totPassCnt = 0;
		double totalPassAmt=0.0;
		int totLuggCnt = 0;
		double totalLugAmt = 0;
		int tottotAmt = 0;
		
		
		for(int i=0;i<showWaybill.size();i++){
			totPassCnt = totPassCnt + Integer.parseInt(showWaybill.get(i).getPx_count());
			totalPassAmt = totalPassAmt + Double.parseDouble(showWaybill.get(i).getPx_total_amount());
			totLuggCnt = totLuggCnt + Integer.parseInt(showWaybill.get(i).getLugg_units());
			totalLugAmt = totalLugAmt + Double.parseDouble(showWaybill.get(i).getLugg_total_amount());
			tottotAmt = tottotAmt + Integer.parseInt(showWaybill.get(i).getTotal_ticket_amount());
		}
		
		
		setTotalPassengerCnt(totPassCnt);
		setTotalPassengerAmt(totalPassAmt);
		setTotallugCnt(totLuggCnt);
		setTotalLugAmount(totalLugAmt);
		setTotaltotalAmount(tottotAmt);
	}		catch(Exception e){
		e.printStackTrace();
	}
		return SUCCESS;
	}
	
	public String ShowLcWaybillWiseDayWise(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String waybillno = request.getParameter("waybillno");
		String depotid = request.getParameter("depotId");
		String ticketdate = request.getParameter("ticketdate");
		String tripno=request.getParameter("tripno");
		String shift_no=request.getParameter("shiftno");
		
		setWaybillNo(waybillno);
		setDepotId(depotid);
		setTicketDate(ticketdate);
		
		setShowWaybill((ArrayList<OnlineWaybills>) waybillShowDao.getWaybillDayWise(waybillno, ticketdate, depotid,tripno,shift_no));
		System.out.println("\n \tticket : "+showWaybill.size());
		if(showWaybill.size()>0){
			setDepotName(showWaybill.get(0).getDepotName());
			setScheduleNumber(showWaybill.get(0).getSchedule_no());
		}
		System.out.println("\n \tticket : "+showWaybill.size());
		int totPassCnt = 0;
		double totalPassAmt=0.0;
		int totLuggCnt = 0;
		double totalLugAmt = 0;
		double tottotAmt = 0;
		int totalamount=0;
		
		
		for(int i=0;i<showWaybill.size();i++){
			totPassCnt = totPassCnt + Integer.parseInt(showWaybill.get(i).getPx_count());
			totalPassAmt = totalPassAmt + Double.parseDouble(showWaybill.get(i).getPx_total_amount());
			//System.out.println("totalPassAmt"+totalPassAmt);
			totLuggCnt = totLuggCnt + Integer.parseInt(showWaybill.get(i).getLugg_units());
			totalLugAmt = totalLugAmt + Double.parseDouble(showWaybill.get(i).getLugg_total_amount());
			tottotAmt = tottotAmt + Double.parseDouble(showWaybill.get(i).getTotal_ticket_amount());
		}
		Double d = new Double(tottotAmt);
		 totalamount = d.intValue();
		
		setTotalPassengerCnt(totPassCnt);
		setTotalPassengerAmt(totalPassAmt);
		setTotallugCnt(totLuggCnt);
		setTotalLugAmount(totalLugAmt);
		setTotaltotalAmount(totalamount);
		return SUCCESS;
	}
	
	public String getPerticularDepotName() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		
		System.out.println("parentId......"+parentId);
		List<String> l1 = waybillShowDao.getDepotId(parentId);
		List<String> l2 = waybillShowDao.getDepotName(parentId);
		req.getSession().setAttribute("Depotid",l1);
		List <String> depid=(List<String>)req.getSession().getAttribute("Depotid");
		System.out.println("Depoid"+depid.size());
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------Select------</option>" +
								" <option value='all'>------ALL------</option>"               ;
			for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
			}
			System.out.println("regionTypeAjaxString......"+regionTypeAjaxString);
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(regionTypeAjaxString);
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return null;

	}
	
}
