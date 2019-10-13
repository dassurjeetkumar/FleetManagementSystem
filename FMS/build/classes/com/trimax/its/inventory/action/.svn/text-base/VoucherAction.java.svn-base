package com.trimax.its.inventory.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.inventory.dao.VoucherDAO;

public class VoucherAction {
	
	public String startdate;
	public String enddate;
	private String voucherNumber;
	private String voucherId;
	VoucherDAO daoObject = new VoucherDAO();
	
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

	public String VoucherShow() {
		System.out.println("Inside voucher");
		return "success";

	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public void getVoucherDetails() throws Exception {

		try {
			VoucherDAO dao=new VoucherDAO();
			Common common = new Common();
			String date1=common.changeDataFormat(startdate, "dd-mm-yyyy", "yyyy-mm-dd");
			String date2=common.changeDataFormat(enddate, "dd-mm-yyyy", "yyyy-mm-dd");
			

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			System.out.println("startdate"+startdate+"enddate......"+enddate+""+request.getParameter("enddate"));
			System.out.println("startdate"+getStartdate()+"enddate......"+getEnddate()+""+startdate);
			
			
			String[] dbcol = { "", "ticket_invoice_mast_id", "voucher_number",
					"tinvoice.created_date", "org.org_name", "org1.org_name",
					"current_status" };

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

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getListVoucher(total, request,dbcol[Integer.parseInt(sCol)], sdir,date1,date2);
			out.print(result);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String VoucherDetailsShow() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		setVoucherId(request.getParameter("voincheridd"));
		setVoucherNumber(daoObject.setVoucherNumber(voucherId));
		System.out.println("voucherid$$$$$$" + voucherId);
		return "success";

	}

	public void getVoucherManual() throws Exception {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String voucherId = request.getParameter("voucherId");

			String[] dbcol = { "denomination_type_manual", "denomination_key",
					"opening_number", "closing_number", "number_of_tickets",
					"number_of_books" };

			String result = "";
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
			PrintWriter out = response.getWriter();
			result = daoObject.getListVoucherManual(voucherId);
			System.out.println("result111----------" + result);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getVoucherPass() throws Exception {
		String result = "";
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String voucherId = request.getParameter("voucherId");
			String[] dbcol = { "denomination_type_manual", "denomination_key",
					"opening_number", "closing_number", "number_of_tickets",
					"number_of_books" };

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
			PrintWriter out = response.getWriter();
			result = daoObject.getListVoucherPass(voucherId);
			out.print(result);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getVoucherluggage() throws Exception {
		String result = "";
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String voucherId = request.getParameter("voucherId");
			String[] dbcol = { "denomination_type_manual", "denomination_key",
					"opening_number", "closing_number", "number_of_tickets",
					"number_of_books" };
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
			PrintWriter out = response.getWriter();
			result = daoObject.getListVoucherLuggage(voucherId);
			out.print(result);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getVoucherTsheet() throws Exception {
		String result = "";
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String voucherId = request.getParameter("voucherId");
			String[] dbcol = { "denomination_type_manual", "denomination_key",
					"opening_number", "closing_number", "number_of_passes" };
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
			PrintWriter out = response.getWriter();
			result = daoObject.getListVoucherTsheet(voucherId);
			out.print(result);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOrgTypeSpecific() {

		try {
			List<String> l1 = daoObject.getOrgTypeIdSpecific();
			List<String> l2 = daoObject.getOrgTypeNameSpecific();
			String regionTypeAjaxString = "";
			regionTypeAjaxString += "<option value=0>Select</option>";
			for (int i = 0; i < l1.size(); i++) {
				regionTypeAjaxString += "<option id='orgType" + l1.get(i)
						+ "' value=" + l1.get(i).toString() + ">"
						+ l2.get(i).toString() + "</option>";
			}
			System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;

			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getVoucherHeaderDetails(){
		String returnString = "";
		
		String voucherId = ServletActionContext.getRequest().getParameter("voucherId");
		
		HttpServletResponse response = null;
		PrintWriter out = null;
		try{
			response = ServletActionContext.getResponse();
			out = response.getWriter();
			returnString = daoObject.getVoucherHeaderDetails(voucherId);
		}catch(Exception e){
			e.printStackTrace();
		}	
		out.print(returnString);
		
		return null;
	}
}
