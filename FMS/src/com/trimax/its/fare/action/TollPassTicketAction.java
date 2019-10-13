package com.trimax.its.fare.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.fare.dao.TollPassTicketDao;
import com.trimax.its.fare.model.TollPassTicketModel;


public class TollPassTicketAction extends ActionSupport{
	TollPassTicketModel tollPassTicketModel;
	private Map<Integer,String> routetype;


	public Map<Integer, String> getRoutetype() {
		return routetype;
	}




	public void setRoutetype(Map<Integer, String> routetype) {
		this.routetype = routetype;
	}




	public String execute() throws Exception {
		return "success";
	}
	
	


	public TollPassTicketModel getTollPassTicketModel() {
		return tollPassTicketModel;
	}

	public void setTollPassTicketModel(TollPassTicketModel tollPassTicketModel) {
		this.tollPassTicketModel = tollPassTicketModel;
	}




	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
   public String viewTollPassMaster(){
	   try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			TollPassTicketDao tollPassTicketDao=new TollPassTicketDao();
			String[] cols = { "", "id", "routeid","routeno","toll_name","amount","notes" };
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
			total = tollPassTicketDao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = tollPassTicketDao.getData(total, request,
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
   public String deleteTollPassTicket(){
	   String masterid = ServletActionContext.getRequest().getParameter("value");
	   System.out.println("masterid..............."+masterid);
	   TollPassTicketDao tollPassTicketDao=new TollPassTicketDao();
	   try{
		 if(tollPassTicketDao.deletetollpassMaster(Integer.parseInt(masterid))){
			 addActionMessage("Toll Pass Id "+masterid+ " Deleted SuccessFully");
		 }else{
			 addActionError("Problem in Toll Pass Id Delete");
		 }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
   }
   TollPassTicketDao tollPassTicketDao=new TollPassTicketDao();
   public String createTollPassTicket(){
	   
	   routetype=tollPassTicketDao.getroutedate();
	   return "success";
   }
   public String createTollPassTicketAction(){
	   String returnString="success";
	   routetype=tollPassTicketDao.getroutedate();
	  try{
		  if(!tollPassTicketDao.checkduplicate(tollPassTicketModel)){
			  addActionError("Toll Name already Existed For this Root");
			  returnString="success";
		
		  }else{
			  int	id=tollPassTicketDao.saveTollPassTicket(tollPassTicketModel);
				addActionMessage("Toll Pass Id "+id+" Created Successfully");
		  }
			}catch(Exception ex){
				
				returnString= "input"; 
			}
	   return returnString;
   }
public String editTollPassTicket(){
	System.out.println("EditTollPassTicket........................");
	 String masterid = ServletActionContext.getRequest().getParameter("value");
	   routetype=tollPassTicketDao.getroutedate();
	   tollPassTicketModel = tollPassTicketDao.getEditedTollPass(Integer.parseInt(masterid));
	   return "success";
   }
   public String editTollPassTicketAction(){
	   String returnString="success";
	   routetype=tollPassTicketDao.getroutedate();
	  try{
		  if(!tollPassTicketDao.checkduplicate1(tollPassTicketModel)){
			  addActionError("Toll Name already Existed For this Root");
			  returnString="success";
		
		  }else{
		  tollPassTicketDao.updateTollpassMaster(tollPassTicketModel);
			addActionMessage("Toll Pass Id "+tollPassTicketModel.getId() +" updated Successfully");
		  }
			}catch(Exception ex){
				
				returnString= "input"; 
			}
	   return returnString;
   
}

}


