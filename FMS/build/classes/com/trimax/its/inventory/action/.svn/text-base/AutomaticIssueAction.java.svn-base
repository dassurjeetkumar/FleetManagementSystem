package com.trimax.its.inventory.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.inventory.dao.AutomaticIssueDAO;
import com.trimax.its.inventory.model.AutomaticIssueModel;
import com.trimax.its.org.json.JSONArray;

public class AutomaticIssueAction extends ActionSupport{

	AutomaticIssueDAO daoObject = new AutomaticIssueDAO();
	
	List<List<String>> normalTypeDenomList;
	List<List<String>> passTypeDenomList;
	List<List<String>> orgList;
	public String orgName;
	public int orgId;
	AutomaticIssueModel modelObject = new AutomaticIssueModel();

	public List<List<String>> getNormalTypeDenomList() {
		return normalTypeDenomList;
	}
	public void setNormalTypeDenomList(List<List<String>> normalTypeDenomList) {
		this.normalTypeDenomList = normalTypeDenomList;
	}
	
	public AutomaticIssueModel getModelObject() {
		return modelObject;
	}
	public void setModelObject(AutomaticIssueModel modelObject) {
		this.modelObject = modelObject;
	}
	public List<List<String>> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<List<String>> orgList) {
		this.orgList = orgList;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public List<List<String>> getPassTypeDenomList() {
		return passTypeDenomList;
	}
	public void setPassTypeDenomList(List<List<String>> passTypeDenomList) {
		this.passTypeDenomList = passTypeDenomList;
	}
	public String execute(){
		
		setOrgName(daoObject.getOrgName());
		setOrgId(daoObject.getCurrentOrganizationOfUser());
		setOrgList(daoObject.getOrgList());
		setNormalTypeDenomList(daoObject.getDenomListOfNormalType());
		setPassTypeDenomList(daoObject.getDenomListOfPassType());
		return "success";
	}
	public String getNormalTicketsByNoOfBooks(){
		
		String denomType = ServletActionContext.getRequest().getParameter("denomType");
		String organizationId = ServletActionContext.getRequest().getParameter("orgId");
		PrintWriter out = null;
		HttpServletResponse response = null;
		int totalValue = 0;
		try {
			modelObject.setPassengerTypeTickets(daoObject.getPassengerTypeTickets(organizationId,Integer.parseInt(denomType)));
			response = ServletActionContext.getResponse();
			totalValue = daoObject.getTotalValue(modelObject.getPassengerTypeTickets());
			out = response.getWriter();
			out.print(new JSONArray(modelObject.getPassengerTypeTickets())+"@"+totalValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String getLuggageTicketsByNoOfBooks(){
		
		PrintWriter out = null;
		HttpServletResponse response = null;
		String organizationId = ServletActionContext.getRequest().getParameter("orgId");
		int totalValue = 0;
		try {
			response = ServletActionContext.getResponse();
			out = response.getWriter();
			
			modelObject.setLuggaugeTickets(daoObject.getLuggageTypeTickets(organizationId));
			totalValue = daoObject.getTotalNoOfBooks(modelObject.getLuggaugeTickets());
			out.print(new JSONArray(modelObject.getLuggaugeTickets())+"@"+totalValue);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public String getPassDay(){
		PrintWriter out = null;
		HttpServletResponse response = null;
		try {
			String denomId = ServletActionContext.getRequest().getParameter("denomId");
			response = ServletActionContext.getResponse();
			AutomaticIssueDAO daoObject = new AutomaticIssueDAO();
			String data = daoObject.getPassDays(denomId);
			out = response.getWriter();
			out.print(data);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return null;
		}
	}
	
	public String getPassTypeTicketsByNoOfBooks(){
		

		String denomType = ServletActionContext.getRequest().getParameter("denomType");
		String passDay = ServletActionContext.getRequest().getParameter("passday");
		String organizationId = ServletActionContext.getRequest().getParameter("orgId");
		PrintWriter out = null;
		HttpServletResponse response = null;
		int totalValue = 0;
		try {
			response = ServletActionContext.getResponse();
			out = response.getWriter();
			modelObject.setPassTypeTickets(daoObject.getPassTypeTickets(organizationId,Integer.parseInt(denomType),passDay));
			totalValue = daoObject.getTotalValue(modelObject.getPassTypeTickets());
			out.print(new JSONArray(modelObject.getPassTypeTickets())+"@"+totalValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String saveAutomaticIssueTickets(){
		
		if(daoObject.saveAutomaticIssueTickets()){
			String message = (String) ServletActionContext.getRequest().getSession().getAttribute("message");
			String voucherNumber = (String) ServletActionContext.getRequest().getSession().getAttribute("voucherNumber");
			addActionMessage(voucherNumber+" is generated and saved successfully");
		}else{
			String message = (String) ServletActionContext.getRequest().getSession().getAttribute("message");
			addActionError(message);
			execute();
		}
		ServletActionContext.getRequest().getSession().removeAttribute("message");
		return "success";
	}
	public String getCentralStockCountOfDenom(){
		
		PrintWriter out = null;
		HttpServletResponse response = null;
		String denomId = ServletActionContext.getRequest().getParameter("denomId");
		String ticketType = ServletActionContext.getRequest().getParameter("ticketType");
		String passDay = ServletActionContext.getRequest().getParameter("passday");
		int totalValue = 0;
		try {
			response = ServletActionContext.getResponse();
			out = response.getWriter();
			
			int count = daoObject.getCentralStockCountOfDenom(denomId,ticketType,passDay);
			out.print(count);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
