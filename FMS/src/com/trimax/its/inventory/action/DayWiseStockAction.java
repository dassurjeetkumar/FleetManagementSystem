package com.trimax.its.inventory.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.trimax.its.common.Common;
import com.trimax.its.inventory.dao.DayWiseStockDAO;
import com.trimax.its.inventory.dao.StockViewDAO;
import com.trimax.its.inventory.model.StockViewModel;

public class DayWiseStockAction {
	
	static StockViewDAO daoObject = new StockViewDAO();
	static DayWiseStockDAO doa = new DayWiseStockDAO();
	public List<StockViewModel> passengerTicketObject ;
	public List<StockViewModel> passTypeTicketObject ;
	public List<StockViewModel> lugguageTicketObject ;
	public String orgName;
	StockViewModel model = new StockViewModel();
	Common common = new Common();
	public int totalNormalvalue;
	public int totalPassValue;
	public int totalNoOfLuggageBooks;
	public int referenceNumber;
	public String stockDate;
	private Map<Integer, String> divisionlist;
	public String runDate;
	public String dateHeader;
	public String getRunDate() {
		return runDate;
	}
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	public List<StockViewModel> getPassengerTicketObject() {
		return passengerTicketObject;
	}
	public void setPassengerTicketObject(List<StockViewModel> passengerTicketObject) {
		this.passengerTicketObject = passengerTicketObject;
	}
	public List<StockViewModel> getPassTypeTicketObject() {
		return passTypeTicketObject;
	}
	public void setPassTypeTicketObject(List<StockViewModel> passTypeTicketObject) {
		this.passTypeTicketObject = passTypeTicketObject;
	}
	public List<StockViewModel> getLugguageTicketObject() {
		return lugguageTicketObject;
	}
	public void setLugguageTicketObject(List<StockViewModel> lugguageTicketObject) {
		this.lugguageTicketObject = lugguageTicketObject;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public StockViewModel getModel() {
		return model;
	}
	public void setModel(StockViewModel model) {
		this.model = model;
	}
	
	public int getTotalNormalvalue() {
		return totalNormalvalue;
	}
	public void setTotalNormalvalue(int totalNormalvalue) {
		this.totalNormalvalue = totalNormalvalue;
	}
	public int getTotalPassValue() {
		return totalPassValue;
	}
	public void setTotalPassValue(int totalPassValue) {
		this.totalPassValue = totalPassValue;
	}
	public int getTotalNoOfLuggageBooks() {
		return totalNoOfLuggageBooks;
	}
	public void setTotalNoOfLuggageBooks(int totalNoOfLuggageBooks) {
		this.totalNoOfLuggageBooks = totalNoOfLuggageBooks;
	}
	
	
	public int getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getStockDate() {
		return stockDate;
	}
	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}
	public String viewStock(){
		setOrgName(daoObject.getOrgName());
		return "success";
	}
	@SuppressWarnings("finally")
	public String getAllList(){	
		
		HttpServletRequest req = ServletActionContext.getRequest();
		int orgsession = Integer.parseInt((String)req.getSession().getAttribute("orgchartid"));
		int orgtypesession = Integer.parseInt((String)req.getSession().getAttribute("orgtypeid"));
		setOrgName(daoObject.getOrgName());
		referenceNumberList();
		divisionlist =daoObject.getDivisionName();
		dateHeader=common.changeDataFormat(stockDate, "dd-mm-yyyy", "dd/mm/yyyy");
		String	selectedDate = common.changeDataFormat(stockDate, "dd-mm-yyyy", "yyyy-mm-dd");
	
	/*if(model!=null){
		model=null;
	}else{
		model=model;
	}*/
		
		try { 		/*passengerTicketObject.clear();
					passTypeTicketObject.clear();
					lugguageTicketObject.clear();	*/
			
				           // int organizationId = doa.getCurrentOrganizationOfUser();
			if(ServletActionContext.getRequest().getSession().getAttribute("RefId")!=null){
				 referenceNumber = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("RefId").toString());
				 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				 Date date = new Date();
				 setStockDate(sdf.format(date));
				 setReferenceNumber(referenceNumber);
				 ServletActionContext.getRequest().getSession().removeAttribute("RefId");
				 dateHeader=getStockDate();
				}				
			model.setPassengerTicketObject(doa.getPassengerTickets(selectedDate,referenceNumber,null));
			setTotalNormalvalue(doa.getTotalValue(model.getPassengerTicketObject()));
			model.setPassTypeTicketObject(doa.getPassTypeTickets(selectedDate,referenceNumber,null));
			setTotalPassValue(doa.getTotalValue(model.getPassTypeTicketObject()));
			model.setLugguageTicketObject(doa.getLuggaugeTickets(selectedDate,referenceNumber,null));
			setTotalNoOfLuggageBooks(doa.getTotalNoOfBooks(model.getLugguageTicketObject()));
			java.util.Date  ss1=new Date();
			SimpleDateFormat formatter5=new SimpleDateFormat(" dd-MM-yyyy hh:mm aaa ");
			runDate= formatter5.format(ss1);
							
		//out.print(new JSONArray(model.getPassengerTicketObject())+"@"+totalNormalValue+"#"+new JSONArray(model.getPassTypeTicketObject())+"@"+totalPassValue+"#"+new JSONArray(model.getLugguageTicketObject())+"@"+totalLuggageValue);
		//}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			return "success";
		}
		
	}
	@SuppressWarnings("unchecked")
	public String referenceNumberList() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		String stockDate = ServletActionContext.getRequest().getParameter("stockDate");
		
	
		stockDate = common.changeDataFormat(stockDate, "dd-mm-yyyy", "yyyy-mm-dd");
		System.out.println("Date"+stockDate);
		List<String> l1 = doa.getReferenceNumber(stockDate);
	
		req.getSession().setAttribute("Depotid",l1);
		List <String> depid=(List<String>)req.getSession().getAttribute("Depotid");
		System.out.println("Depoid"+depid.size());
		String regionTypeAjaxString = "";
		//" <option value='all'> All </option>"
		regionTypeAjaxString += "<option value='0'> Select </option>"  ;
								
			for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l1.get(i).toString() + "</option>";
			}
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
