package com.trimax.its.inventory.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.mapping.Array;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.inventory.dao.StockViewDAO;
import com.trimax.its.inventory.model.StockViewModel;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.org.json.JSONObject;
import com.trimax.its.orgchart.action.DepotAction;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class StockViewAction extends ActionSupport{

	static StockViewDAO daoObject = new StockViewDAO();
	public List<StockViewModel> passengerTicketObject ;
	public List<StockViewModel> passTypeTicketObject ;
	public List<StockViewModel> lugguageTicketObject ;
	public String orgName;
	StockViewModel model = new StockViewModel();
	Common common = new Common();
	public int totalNormalvalue;
	public int totalPassValue;
	public int totalNoOfLuggageBooks;
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
	
	public String getAllList()
	{	HttpServletRequest req = ServletActionContext.getRequest();
	 
		int orgsession = Integer.parseInt((String)req.getSession().getAttribute("orgchartid"));
		int orgtypesession = Integer.parseInt((String)req.getSession().getAttribute("orgtypeid"));
		
		setOrgName(daoObject.getOrgName());
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		dateHeader=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist =daoObject.getDivisionName();
		try {
				            int organizationId = daoObject.getCurrentOrganizationOfUser();
				            model.setPassengerTicketObject(daoObject.getPassengerTickets(currentDate,0,null));
							setTotalNormalvalue(daoObject.getTotalValue(model.getPassengerTicketObject()));
							model.setPassTypeTicketObject(daoObject.getPassTypeTickets(currentDate,0,null));
							setTotalPassValue(daoObject.getTotalValue(model.getPassTypeTicketObject()));
							model.setLugguageTicketObject(daoObject.getLuggaugeTickets(currentDate,0,null));
							setTotalNoOfLuggageBooks(daoObject.getTotalNoOfBooks(model.getLugguageTicketObject()));
							java.util.Date  ss1=new Date();
							SimpleDateFormat formatter5=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
							runDate= formatter5.format(ss1);
							
							
		//out.print(new JSONArray(model.getPassengerTicketObject())+"@"+totalNormalValue+"#"+new JSONArray(model.getPassTypeTicketObject())+"@"+totalPassValue+"#"+new JSONArray(model.getLugguageTicketObject())+"@"+totalLuggageValue);
		//}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "success";
	}
	public String getAllListOfParticularDate(){
			HttpServletRequest req = ServletActionContext.getRequest();
			HttpServletResponse response = null;
			PrintWriter out = null;
			int orgchart=0;
			HttpSession session=req.getSession(); 
			int orgtypesession = Integer.parseInt((String)req.getSession().getAttribute("orgtypeid"));
			String orgchart12 = (String)ServletActionContext.getRequest().getParameter("orgchartid");
			System.out.println("orgchart12"+orgchart12);
		 
	   
			try {
					String stockDate = ServletActionContext.getRequest().getParameter("stockDate");
		
					System.out.print("iooc"+orgchart);
					stockDate = common.changeDataFormat(stockDate, "dd-mm-yyyy", "yyyy-mm-dd");
					dateHeader=common.changeDataFormat(stockDate, "dd-mm-yyyy", "dd/mm/yyyy");
					System.out.println("Date  .."+dateHeader);
					List <String> depid=(List<String>)req.getSession().getAttribute("Depotid");
					response = ServletActionContext.getResponse();
					out = response.getWriter();
					if(depid==null){
								model.setPassengerTicketObject(daoObject.getPassengerTickets(stockDate,orgchart,null));
								int totalNormalValue = daoObject.getTotalValue(model.getPassengerTicketObject());
								model.setPassTypeTicketObject(daoObject.getPassTypeTickets(stockDate,orgchart,null));
								int totalPassValue = daoObject.getTotalValue(model.getPassTypeTicketObject());
								model.setLugguageTicketObject(daoObject.getLuggaugeTickets(stockDate,orgchart,null));
								int totalLuggageValue = daoObject.getTotalNoOfBooks(model.getLugguageTicketObject());
								out.print(new JSONArray(model.getPassengerTicketObject())+"@"+totalNormalValue+"#"+new JSONArray(model.getPassTypeTicketObject())+"@"+totalPassValue+"#"+new JSONArray(model.getLugguageTicketObject())+"@"+totalLuggageValue);
					}else{
				
								model.setPassengerTicketObject(daoObject.getPassengerTickets(stockDate,orgchart,orgchart12));
								int totalNormalValue = daoObject.getTotalValue(model.getPassengerTicketObject());
								model.setPassTypeTicketObject(daoObject.getPassTypeTickets(stockDate,orgchart,orgchart12));
								int totalPassValue = daoObject.getTotalValue(model.getPassTypeTicketObject());
								model.setLugguageTicketObject(daoObject.getLuggaugeTickets(stockDate,orgchart,orgchart12));
								int totalLuggageValue = daoObject.getTotalNoOfBooks(model.getLugguageTicketObject());
								out.print(new JSONArray(model.getPassengerTicketObject())+"@"+totalNormalValue+"#"+new JSONArray(model.getPassTypeTicketObject())+"@"+totalPassValue+"#"+new JSONArray(model.getLugguageTicketObject())+"@"+totalLuggageValue);
								System.out.println("HiiSessionRemove"+depid.size());
					}
			
		//	out.print(new JSONArray(model.getPassengerTicketObject())+"@"+totalNormalValue+"#"+new JSONArray(model.getPassTypeTicketObject())+"@"+totalPassValue+"#"+new JSONArray(model.getLugguageTicketObject())+"@"+totalLuggageValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getPerticularDepotName() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = daoObject.getDepotId(parentId);
		List<String> l2 = daoObject.getDepotName(parentId);
		req.getSession().setAttribute("Depotid",l1);
		List <String> depid=(List<String>)req.getSession().getAttribute("Depotid");
		System.out.println("Depoid"+depid.size());
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'> Select </option>" +
								" <option value='all'> All </option>" ;
			for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
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
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String getDateHeader() {
		return dateHeader;
	}
	public void setDateHeader(String dateHeader) {
		this.dateHeader = dateHeader;
	}
	
}
