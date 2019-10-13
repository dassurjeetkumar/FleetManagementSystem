package com.trimax.its.passengercategory.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.trimax.its.cashremittancevoucher.dao.CashRemittanceVoucherDao;
import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.componenttype.dao.ComponentTypeDao;
import com.trimax.its.passengercategory.dao.PassengerCategoryDao;
import com.trimax.its.passengercategory.model.PassengerCategory;

public class PassengerCategoryAction extends ActionSupport implements
		Preparable {
	private PassengerCategory passengercategory;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	String insertstaus;
	String updatestaus;
	String deletestaus;

	public PassengerCategory getPassengercategory() {
		return passengercategory;
	}

	public void setPassengercategory(PassengerCategory passengercategory) {
		this.passengercategory = passengercategory;
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

	@SkipValidation
	public String execute() {

		return null;
	}

	@SkipValidation
	public String showPassengerCategory() {
		
		return "success";
	}

	@SkipValidation
	public String createPassengerCategory() {
		return "success";
	}

	public String createPassengerCategoryAction() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PassengerCategoryDao dao = new PassengerCategoryDao();
		if (!dao.checkPassengerCategory(passengercategory
				.getPassenger_category_name())) {
			passengercategory.setCreated_by(request.getSession()
					.getAttribute("userid").toString());
			passengercategory.setCreated_date(new java.util.Date());
			String res = "";
			int id = 0;
			try {
				id = dao.createPassengerCategory(passengercategory);
			} catch (Exception ex) {
				setInsertstaus("fail");
				return "input";
			} finally {
				setInsertstaus("success");
				addActionMessage("Passenger Category Id " + id
						+ " Inserted Successfully");
			}
		} else {
			setInsertstaus("duplicate");
			return "input";
		}
		return "success";

	}

	@SkipValidation
	public String editPassengercategory() {
	
		PassengerCategoryDao dao = new PassengerCategoryDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		passengercategory = dao.getEditedPassengerCategory(Integer
				.parseInt(request.getParameter("categoryid")));

		return "success";

	}

	public String addeditedPassengerCategory() {
		PassengerCategoryDao dao = new PassengerCategoryDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		passengercategory.setUpdated_by(Integer.parseInt(request
				.getSession().getAttribute("userid").toString()));

		if (dao.checkPassengerCategoryForUpdate(
				passengercategory.getPassenger_category_name(),
				passengercategory.getPassenger_category_id())) {
//			passengercategory.setUpdated_by(Integer.parseInt(request
//					.getSession().getAttribute("userid").toString()));

			try {
				dao.updatePassengerCategory(passengercategory,
						passengercategory.getPassenger_category_id());
			} catch (Exception ex) {
				setUpdatestaus("fail");
				return "input";
			} finally {
				setUpdatestaus("success");
				addActionMessage("Passenger Category Id "
						+ passengercategory.getPassenger_category_id()
						+ " Updated Successfully");
			}
		} else if (!dao.checkPassengerCategory(passengercategory
				.getPassenger_category_name())) {
			try {
				dao.updatePassengerCategory(passengercategory,
						passengercategory.getPassenger_category_id());
			} catch (Exception ex) {
				setUpdatestaus("fail");
				return "input";
			} finally {
				setUpdatestaus("success");
				addActionMessage("Passenger Category Id "
						+ passengercategory.getPassenger_category_id()
						+ " Updated Successfully");
			}
		} else {
			setUpdatestaus("duplicate");
			return "input";
		}
		return "success";

	}

	@SkipValidation
	public String deletePassengerCategoryAction() {
		PassengerCategoryDao dao = new PassengerCategoryDao();
		PassengerCategory passengerCat = new PassengerCategory();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		passengerCat.setUpdated_by(Integer.parseInt(request.getSession()
				.getAttribute("userid").toString()));
		try {
			dao.deletePassengerCategory(passengerCat,
					Integer.parseInt(request.getParameter("PassengerCategoryid")));
		} catch (Exception ex) {
			setDeletestaus("fail");
			return "input";
		} finally {
			setDeletestaus("success");
			addActionMessage("Passenger Category Id "
					+ request.getParameter("PassengerCategoryid")
					+ " Deleted Successfully");
		}
		return "success";

	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
		if(passengercategory.getPassenger_category_name().trim().equals("") || passengercategory.getPassenger_category_name().equals(" "))
		{
			addFieldError("Passenger_category", "Passenger category is Required");
			
			passengercategory.setPassenger_category_name(passengercategory.getPassenger_category_name());
			
		}
		if(!common.isSpecialChar(passengercategory.getPassenger_category_name()))
		{
			addFieldError("Passenger_category", "Special Character For Passenger category is Not Allowed");
			
			this.passengercategory.setPassenger_category_name(this.passengercategory.getPassenger_category_name());
		}
		
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PassengerCategoryDao dao = new PassengerCategoryDao();
			
			String[] dbcol = { "", "passenger_category_id",
					"passenger_category_name", "notes", "status" };
			// /String[] dbcol = {"","passenger_category_id"};
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
			
			total = dao.getTotalRecords(total, request,	dbcol[Integer.parseInt(sCol)], sdir);
			
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			
			result = dao.getData(total, request, dbcol[Integer.parseInt(sCol)],	sdir);
	
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

		}

	}

}
