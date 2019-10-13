package com.trimax.its.cashremittancevoucher.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.cashremittancevoucher.dao.CashRemittanceVoucherDao;
import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.ticketing.dao.TicketInventoryDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class CashRemittanceVoucherAction extends ActionSupport implements
		Preparable {

	private CashRemittanceVoucher cashRemittanceVoucher;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	private int orgChartid;

	String insertstaus;
	String updatestaus;
	String deletestaus;

	private Map<Integer, String> orgChartlist;
	private Map<Integer, String> orgTypeList;
	private Map<Integer, String> employeeList;

	public CashRemittanceVoucher getCashRemittanceVoucher() {
		return cashRemittanceVoucher;
	}

	public void setCashRemittanceVoucher(
			CashRemittanceVoucher cashRemittanceVoucher) {
		this.cashRemittanceVoucher = cashRemittanceVoucher;
	}

	public String getInsertstaus() {
		return insertstaus;
	}

	public void setInsertstaus(String insertstaus) {
		this.insertstaus = insertstaus;
	}

	public String getUpdatestaus() {
		return updatestaus;
	}

	public void setUpdatestaus(String updatestaus) {
		this.updatestaus = updatestaus;
	}

	public String getDeletestaus() {
		return deletestaus;
	}

	public void setDeletestaus(String deletestaus) {
		this.deletestaus = deletestaus;
	}

	public Map<Integer, String> getOrgChartlist() {
		return orgChartlist;
	}

	public void setOrgChartlist(Map<Integer, String> orgChartlist) {
		this.orgChartlist = orgChartlist;
	}

	public Map<Integer, String> getOrgTypeList() {
		return orgTypeList;
	}

	public void setOrgTypeList(Map<Integer, String> orgTypeList) {
		this.orgTypeList = orgTypeList;
	}

	public int getOrgChartid() {
		return orgChartid;
	}

	public void setOrgChartid(int orgChartid) {
		this.orgChartid = orgChartid;
	}

	public Map<Integer, String> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(Map<Integer, String> employeeList) {
		this.employeeList = employeeList;
	}

	@SkipValidation
	public String execute() {

		return null;
	}

	@SkipValidation
	public String showCashRemittanceVoucher() {
		return "success";
	}

	@SkipValidation
	public String createCashRemittanceVoucher() {
		return "success";
	}

	public String createCashRemittanceVoucherAction() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "cashRemittanceVoucherView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		CashRemittanceVoucherDao dao = new CashRemittanceVoucherDao();
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		CommonValidation common1 = new CommonValidation();
		Common common = new Common();
		System.out.println("inside method");
		if (!dao.checkCashRemittanceVoucher(cashRemittanceVoucher.getVoucher_number())) {
			cashRemittanceVoucher.setCreated_by(request.getSession().getAttribute("userid").toString());
			/*
			 * cashRemittanceVoucher.setRemitted_by(request.getSession()
			 * .getAttribute("userid").toString());
			 */
			System.out.println("inside method");
			cashRemittanceVoucher.setCreated_date(new java.util.Date());
			cashRemittanceVoucher.setVoucher_date(common.getDateFromPicker(cashRemittanceVoucher.getVoucher_date()));
			String res = "";
			int id = 0;
			try {
				id = dao.createCashRemittanceVoucher(cashRemittanceVoucher);
			} catch (Exception ex) {
				setInsertstaus("fail");
				ex.printStackTrace();
				return "input";
			} finally {
				if (id > 0) {
					setInsertstaus("success");
					addActionMessage("Voucher Id " + id
							+ " Created Successfully");
				}
				
			}
		} else {
			setInsertstaus("duplicate");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}

	}

	@SkipValidation
	public String editCashRemittanceVoucher() {
		CashRemittanceVoucherDao dao = new CashRemittanceVoucherDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		cashRemittanceVoucher = dao.getEditedCashRemittanceVoucher(Integer.parseInt(request.getParameter("voucherid")));

		return "success";

	}

	public String addeditCashRemittanceVoucherAction() {
		CashRemittanceVoucherDao dao = new CashRemittanceVoucherDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		Common common = new Common();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "cashRemittanceVoucherView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		if (dao.checkCashRemittanceVoucherForUpdate(cashRemittanceVoucher.getVoucher_number(),cashRemittanceVoucher.getVoucher_id())) {
			cashRemittanceVoucher.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = sdf.parse(cashRemittanceVoucher.getVoucher_date());
				dao.updateCashRemittanceVoucher(cashRemittanceVoucher,cashRemittanceVoucher.getVoucher_id());
			} catch (Exception ex) {
				setUpdatestaus("fail");
				return "input";
			} finally {
				setUpdatestaus("success");
				addActionMessage("Voucher Id " + cashRemittanceVoucher.getVoucher_id() + " Updated Successfully");
			}
		} else if (!dao.checkCashRemittanceVoucher(cashRemittanceVoucher.getVoucher_number())) {
			try {
				dao.updateCashRemittanceVoucher(cashRemittanceVoucher,cashRemittanceVoucher.getVoucher_id());
			} catch (Exception ex) {
				setUpdatestaus("fail");
				return "input";
			} finally {
				setUpdatestaus("success");
				addActionMessage("Voucher Id " + cashRemittanceVoucher.getVoucher_id() + " Updated Successfully");
			}
		} else {
			setUpdatestaus("duplicate");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}

	}

	@SkipValidation
	public String deleteCashRemittanceVoucherAction() {
		CashRemittanceVoucherDao dao = new CashRemittanceVoucherDao();
		CashRemittanceVoucher cashremittance = new CashRemittanceVoucher();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "cashRemittanceVoucherView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		cashremittance.setUpdated_by(Integer.parseInt(request.getSession()
				.getAttribute("userid").toString()));
		try {
			dao.deleteCashRemittanceVoucher(cashremittance,Integer.parseInt(request.getParameter("delvoucherid")));
		} catch (Exception ex) {
			setDeletestaus("fail");
			return "input";
		} finally {
			setDeletestaus("success");
			addActionMessage("Voucher Id "
					+ request.getParameter("delvoucherid")
					+ " Deleted Successfully");
		}
		return "success";
		}else{
			return "success";
		}

	}

	/*public String getOrganizationName() {
		CashRemittanceVoucherDao dao = new CashRemittanceVoucherDao();
		// HttpServletRequest request = ServletActionContext.getRequest();
		// HttpServletResponse response = ServletActionContext.getResponse();
		orgChartlist = dao.getOrgNamne(orgChartid);

		return "success";

	}*/

	@Override
	public void validate() {
		// TODO Auto-generated method stub

		CommonValidation common = new CommonValidation();

		if (common.isEmpty(cashRemittanceVoucher.getVoucher_number().trim())) {
			addFieldError("Vouchernumber", "Voucher Number is Required");

			cashRemittanceVoucher.setVoucher_number(cashRemittanceVoucher
					.getVoucher_number());

		}
		if (!common.isSpecialChar(cashRemittanceVoucher.getVoucher_number())) {
			addFieldError("Vouchernumber",
					"Special Character For Voucher Number is Not Allowed");

			this.cashRemittanceVoucher.setVoucher_number(this.cashRemittanceVoucher.getVoucher_number());
		}
		if (common.isEmpty(cashRemittanceVoucher.getVoucher_date())) {
			addFieldError("voucher_date", "Date is Required");

			cashRemittanceVoucher.setVoucher_date(cashRemittanceVoucher
					.getVoucher_date());

		}
		if (cashRemittanceVoucher.getOrgChartCash().getOrg_chart_id() == 0) {
			addFieldError("OrganizationName", "Please Select Organization Name");
		}

		if (cashRemittanceVoucher.getEmployeeCash().getEmployee_id() == 0) {
			addFieldError("remmittedby", "Please Select Remitted By");

			
		}

		if (common.isEmpty(cashRemittanceVoucher.getBank_name())) {
			addFieldError("BankName", "Bank Name is Required");
			this.cashRemittanceVoucher.setBank_name(this.cashRemittanceVoucher.getBank_name());
		}
		if (!common.isSpecialChar(cashRemittanceVoucher.getBank_name())) {
			addFieldError("BankName","Special Character For Bank Name is Not Allowed");
			this.cashRemittanceVoucher.setBank_name(this.cashRemittanceVoucher.getBank_name());
			
		}

		if (cashRemittanceVoucher.getOrgTypeCash().getOrg_type_id() == 0) {
			addFieldError("orgTypeId", "Please Select Organization Type");

		}
		if (cashRemittanceVoucher.getAmount() == 0) {
			addFieldError("Amount", "Amount is Required");
			this.cashRemittanceVoucher.setAmount(this.cashRemittanceVoucher.getAmount());

		}
		if (cashRemittanceVoucher.getAmount() <= 0) {
			addFieldError("Amount", "Please enter Valid Amount");
			this.cashRemittanceVoucher.setAmount(this.cashRemittanceVoucher.getAmount());
		}
		if (cashRemittanceVoucher.getAmount() <= 0) {
			addFieldError("Amount", "Please enter Valid Amount");
			this.cashRemittanceVoucher.setAmount(this.cashRemittanceVoucher.getAmount());
		}

	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			CashRemittanceVoucherDao dao = new CashRemittanceVoucherDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			/*orgChartlist = dao.getOrgNamne(orgChartid);
			orgTypeList = dao.getOrgType();*/
			employeeList = dao.getRemittedBy();
			String[] dbcol = { "", "voucher_id", "voucher_number",
					"voucher_date", "emp.employee_name", "bank_name",
					"type.orgType", "chart.org_name", "amount", "notes",
					"status" };

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

			String colName = dbcol[col];
			int total = -1;

			total = dao.getTotalRecords(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);

			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);

			out.print(result);
		} catch (Exception ex) {
			//ex.printStackTrace();
		} finally {

		}

	}
	
	 /**
     * Find all Organisation Types
     * @return
     */
	@SkipValidation
	public String getOrgType() {
		CashRemittanceVoucherDao dao = new CashRemittanceVoucherDao();

		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = dao.getOrgTypeId();
		List<String> l2 = dao.getOrgTypeName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='orgType"+l1.get(i)+"' value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
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
/**
 * Getting all Organisation Names
 * @return
 */
	@SkipValidation
	public String getUnitName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int orgtypeid = Integer.parseInt(request.getParameter("orgid"));
		CashRemittanceVoucherDao dao = new CashRemittanceVoucherDao();
		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = dao.getUnitId(orgtypeid);
		List<String> l2 = dao.getUnitName(orgtypeid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='orgName"+l1.get(i)+"' value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(orgtypeid);
		return null;

	}
}
