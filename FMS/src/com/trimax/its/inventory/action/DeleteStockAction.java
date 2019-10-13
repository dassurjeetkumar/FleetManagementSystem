package com.trimax.its.inventory.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.inventory.dao.DeleteStockDAO;
import com.trimax.its.ticketing.dao.TicketInventoryDao;

public class DeleteStockAction extends ActionSupport {

	private String ticktdeletemsg;
	private String passdeletemsg;
	private String luggagedeletemsg;
	private String tsheetdeletemsg;
	
	
	public String getTsheetdeletemsg() {
		return tsheetdeletemsg;
	}

	public void setTsheetdeletemsg(String tsheetdeletemsg) {
		this.tsheetdeletemsg = tsheetdeletemsg;
	}

	public String getTicktdeletemsg() {
		return ticktdeletemsg;
	}

	public void setTicktdeletemsg(String ticktdeletemsg) {
		this.ticktdeletemsg = ticktdeletemsg;
	}

	public String getPassdeletemsg() {
		return passdeletemsg;
	}

	public void setPassdeletemsg(String passdeletemsg) {
		this.passdeletemsg = passdeletemsg;
	}

	public String getLuggagedeletemsg() {
		return luggagedeletemsg;
	}

	public void setLuggagedeletemsg(String luggagedeletemsg) {
		this.luggagedeletemsg = luggagedeletemsg;
	}

	DeleteStockDAO daoObject = new DeleteStockDAO();
	
	public String deleteCheckedStock() {
	System.out.println("we are in deleteCheckedStock()..............");
		String result = daoObject.deleteStock();
		if(result.equals("")){
			addActionError("Error in delting tickets");
		}else{
			System.out.println("\tresult is"+result);
			String resultArray[] = result.split("-");
			if(resultArray[0]!=null && resultArray[0].length()>1)
				setTicktdeletemsg("Tickets having key(s) "+resultArray[0]+" deleted successfully");
			if(resultArray[1]!=null && resultArray[1].length()>1)
				setPassdeletemsg("Passes having key(s) "+resultArray[1]+" deleted successfully");
			if(resultArray[2]!=null && resultArray[2].length()>1)
				setLuggagedeletemsg("Luggage tickets having key(s) "+resultArray[2]+" deleted successfully");
			if(resultArray[3]!=null && resultArray[3].length()>1)
				setTsheetdeletemsg("Trip Sheets having key(s) "+resultArray[3]+" deleted successfully");
			
		}
		return "success";
	}
}