package com.trimax.its.fare.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.fare.dao.bankRemmitanceDao;
import com.trimax.its.fare.model.bankRemmitanceModal;
import com.trimax.its.usermanagement.dao.EmployeeDAO;


public class BankRemittanceDetailsAction extends ActionSupport{

	bankRemmitanceDao brdao=new bankRemmitanceDao();
	bankRemmitanceModal brmodal;
	private String DIR;
    private int createBankRD;
    
	public int getCreateBankRD() {
		return createBankRD;
	}
	public void setCreateBankRD(int createBankRD) {
		this.createBankRD = createBankRD;
	}
	private int START;
	
	public bankRemmitanceModal getBrmodal() {
		return brmodal;
	}
	public void setBrmodal(bankRemmitanceModal brmodal) {
		this.brmodal = brmodal;
	}
	private Map<Integer, String> depotlist;
	
	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}
	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
	}
	public String execute() throws Exception {
		System.out.println("it is in execute () bank remmitance details........................................");
		return "success";
	}
	public String CreateBankRemittanceDetails(){
		depotlist=brdao.getDepotNo();
		return "success";
	}
	public String saveBankRemittanceDetails(){
		System.out.println("it is in saveBankRemittanceDetails()..............................");
		String returnString="";
		System.out.println("...........getBrmodal()....."+brmodal.getAccnumber());
		System.out.println("...........getBrmodal()....."+brmodal.getAddr());
		System.out.println("...........getBrmodal()....."+brmodal.getBankname());
		System.out.println("...........getBrmodal()....."+brmodal.getDivision());
		System.out.println("...........getBrmodal()....."+brmodal.getDepotno());
		if(brdao.saveBankRemittance(brmodal)){
			addActionMessage("Bank Remittance Details id "+ServletActionContext.getRequest().getSession().getAttribute("id")+" Generated successfully");
			returnString = "success";
		}
		else{
			addActionError("Error in Bank Remittance Details creation");
			returnString = "fail";
		}
		depotlist=brdao.getDepotNo();
		return returnString;
		
	}
	public String EditBankRemittanceDetails(){
		System.out.println("it is in Edit BankRemittanceDetails().............");
		depotlist=brdao.getDepotNo();
		HttpServletRequest request = ServletActionContext.getRequest();
		brmodal=brdao.getEdited(Integer.parseInt(request
				.getParameter("value")));
		return "success";
	}
	public String saveEditDetails(){
		System.out.println("it is in save Edit BankRemittanceDetails()..............................");
		String returnString="";
		System.out.println("...........getBrmodal()....."+brmodal.getAccnumber());
		System.out.println("...........getBrmodal()....."+brmodal.getAddr());
		System.out.println("...........getBrmodal()....."+brmodal.getBankname());
		System.out.println("...........getBrmodal()....."+brmodal.getDivision());
		System.out.println("...........getBrmodal()....."+brmodal.getDepotno());
		if(brdao.saveEditBankRemittance(brmodal,brmodal.getId())){
			addActionMessage("Bank Remittance Details id "+brmodal.getId()+" Edited successfully");
			returnString = "success";
		}
		else{
			addActionError("Error in Bank Remittance Details Edit");
			returnString = "success";
		}
		depotlist=brdao.getDepotNo();
		return returnString;
		
	}
	public String DeleteBankRemittanceDetails(){
		System.out.println("it is in delete BankRemittanceDetails()..............................");
		String returnResult = "success";
		int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("value"));
			if(brdao.DeleteDetails(id))
			{
				addActionMessage("Bank Remittance Details id "+id+" deleted successfully");
				
			}else{
				addActionError("Problem in delete Bank Remittance Details");
				
			}
		
		return "success";
	}
	
	
public String getBankRemittanceDetailsTable() throws Exception {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			String[] dbcol={"","ID","Depot","BankName","AccountNumber","Division","Address"};
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
			int total = -1;
			String SEARCH_TERM = request.getParameter("sSearch");
			total =brdao.getTotalRecordsForBRDetails();
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=brdao.getList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
}
@Override
public void validate() {
	if(getCreateBankRD()!=0){
	if (brmodal.getDepotno()==0) {
		addFieldError("depot","Please enter Depot Name");
	}
	if (brmodal.getBankname() == null || brmodal.getBankname().trim().equals("")) {
		addFieldError("bankname","Please enter Bank Name");
	}
	if (brmodal.getAccnumber() == null || brmodal.getAccnumber().trim().equals("")) {
		addFieldError("accnum","Please enter Account Number");
	}
	if (brmodal.getDivision() == null || brmodal.getDivision().trim().equals("")) {
		addFieldError("divisn","Please enter Division Name");
	}
	if (brmodal.getAddr() == null || brmodal.getAddr().trim().equals("")) {
		addFieldError("addre","Please enter Address");
	}
	super.validate();
	if(hasFieldErrors()){
		depotlist=brdao.getDepotNo();
	}
	}
}

}
