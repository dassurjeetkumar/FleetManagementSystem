package com.trimax.its.fare.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.fare.dao.SpecialPassTicketDao;

import com.trimax.its.fare.model.SpecialPassTicketModel;



public class SpecialPassTicketAction extends ActionSupport{
	SpecialPassTicketModel specialPassTicketModel;
	private Map<Integer,String> servicetype;
	private ArrayList<String> servicetypelist;
	
	public ArrayList<String> getServicetypelist() {
		return servicetypelist;
	}

	public void setServicetypelist(ArrayList<String> servicetypelist) {
		this.servicetypelist = servicetypelist;
	}

	public Map<Integer, String> getServicetype() {
		return servicetype;
	}

	public void setServicetype(Map<Integer, String> servicetype) {
		this.servicetype = servicetype;
	}

	public String execute() throws Exception {
		return "success";
	}
		
	public SpecialPassTicketModel getSpecialPassTicketModel() {
		return specialPassTicketModel;
	}

	public void setSpecialPassTicketModel(
			SpecialPassTicketModel specialPassTicketModel) {
		this.specialPassTicketModel = specialPassTicketModel;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
   public String viewspecialPassTicket(){
	   try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			SpecialPassTicketDao specialPassTicketDao=new SpecialPassTicketDao();
			String[] cols = { "", "id", "servicetype","ticket_type","amount","notes" };
			System.out.println("viewspecialPassTicket method...................................");
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
			int total = 0;
			total = specialPassTicketDao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = specialPassTicketDao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
			//System.out.println(ex);
		} finally {

		}

	   return null;
   }
   public String deletespecialPassTicket(){
	   String masterid = ServletActionContext.getRequest().getParameter("value");
	   System.out.println("masterid..............."+masterid);
	   SpecialPassTicketDao specialPassTicketDao=new SpecialPassTicketDao();
	   try{
		 if(specialPassTicketDao.deletespecialpassMaster(Integer.parseInt(masterid))){
			 addActionMessage("Special Pass Id "+masterid+ " Deleted SuccessFully");
		 }else{
			 addActionError("Problem in Special Pass Id Delete");
		 }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
   }
   SpecialPassTicketDao specialPassTicketDao=new SpecialPassTicketDao();
   public String createspecialPassTicket(){
	   
	   servicetype=specialPassTicketDao.getservicedate();
	   return "success";
   }
   public String createspecialPassTicketAction(){
	   System.out.println("createspecialPassTicketAction()........................");
	   System.out.println("getServicetypelist()........................"+getServicetypelist());
	   String returnString="success";
	   servicetype=specialPassTicketDao.getservicedate();
	  try{
		int	id=specialPassTicketDao.savespecialpassTicket(specialPassTicketModel,getServicetypelist());
			//addActionMessage("Special Pass Id "+id+" Created Successfully");
		addActionMessage("Special Pass Created Successfully");
			}catch(Exception ex){
				
				returnString= "input"; 
			}
	   return returnString;
   }
public String editspecialPassTicket(){
	System.out.println("EditspecialPassTicket/..........................");
	 String masterid = ServletActionContext.getRequest().getParameter("value");
	 System.out.println("masterid............................"+masterid);
	 servicetype=specialPassTicketDao.getservicedate();
	   specialPassTicketModel = specialPassTicketDao.getEditedspecialpass(Integer.parseInt(masterid));
	   return "success";
   }
   public String editspecialPassTicketAction(){
	   String returnString="success";
	   servicetype=specialPassTicketDao.getservicedate();
	  try{
		  specialPassTicketDao.updatespecialpassMaster(specialPassTicketModel);
			addActionMessage("Special Pass Id "+specialPassTicketModel.getId() +" updated Successfully");
				
			}catch(Exception ex){
				
				returnString= "input"; 
			}
	   return returnString;
   
}

}

