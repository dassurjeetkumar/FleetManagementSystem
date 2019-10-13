package com.trimax.its.inventory.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.external.voucher.transfer.InventoryVoucherTransfer;
import com.trimax.external.voucherTransfer.InvoiceMasterModel;
import com.trimax.its.inventory.dao.VoucherDAO;
import com.trimax.its.inventory.dao.VoucherReceiveDAO;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.org.json.JSONObject;
import com.trimax.its.route.dao.TicketInventoryDAO;

public class VoucherReceiveAction extends ActionSupport{
	 
	public Map<String,String> divisionList;

	public Map<String, String> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(Map<String, String> divisionList) {
		this.divisionList = divisionList;
	}

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	public String getDivisions(){

		VoucherReceiveDAO daoObject = new VoucherReceiveDAO();
		setDivisionList(daoObject.divisionList());
		return "success";
		
	}
	@SuppressWarnings("finally")
	public String getUnitNames() {
		PrintWriter out = null;
		VoucherReceiveDAO daoObject = new  VoucherReceiveDAO();
		try {
			out = response.getWriter();
			int orgtypeid = Integer.parseInt(request.getParameter("divisionId"));
			out.print(daoObject.getUnitNames(orgtypeid));
		} catch (Exception e) {
			out.print("fail");
		}finally{
			
			return null;
		}
		
	}
	public String getVouchersOfDepot(){
		
		VoucherReceiveDAO daoObject = new  VoucherReceiveDAO();
	//	String orgId = request.getParameter("depotId");
		List<String[]> listOfParams = daoObject.getAllDepotCredentials();
		PrintWriter out = null;
		try{
			out = response.getWriter();
			if(listOfParams!=null){
				InventoryVoucherTransfer voucher = new InventoryVoucherTransfer();
				out.print(new JSONArray(voucher.getVouchers(listOfParams))); 
			}
		}catch(Exception e){
			e.printStackTrace();
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			Map<String,String> map = new HashMap<String,String>();
			map.put("error", e.getMessage());
			list.add(map);
			out.print(new JSONArray(list));
		}finally{
			
		}
		return null;
	}
	public String acceptVoucher(){
		
		VoucherReceiveDAO daoObject = new  VoucherReceiveDAO();
		String orgId = request.getParameter("depotId");
		String voucherId = request.getParameter("voucherId");
		String params[] = daoObject.getDepotCredentials(orgId);
		PrintWriter out = null;
		try{
			out = response.getWriter();
			//daoObject.getVoucherDetailsofDepot(params);
			if(params==null){
//				addActionError("Selected organization credentials not found");
				out.print("error@Selected organization credentials not found");
			}else{
				if(daoObject.getSyncVoucherDetails(params,voucherId)){
					out.print(daoObject.setStatusVoucherAsAccepted(params, voucherId));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return null;
		
	}
	public String rejectVoucher(){
		
		VoucherReceiveDAO daoObject = new  VoucherReceiveDAO();
		String orgId = request.getParameter("depotId");
		String voucherId = request.getParameter("voucherId");
		String params[] = daoObject.getDepotCredentials(orgId);
		PrintWriter out = null;
		try{
			out = response.getWriter();
			//daoObject.getVoucherDetailsofDepot(params);
			if(params==null){
//				addActionError("Selected organization credentials not found");
				out.print("error@Selected organization credentials not found");
			}else{
				out.print(daoObject.rejectVoucher(params, voucherId));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return null;
		
	}
	public String voucherDetails(){
		
		VoucherReceiveDAO daoObject = new  VoucherReceiveDAO();
		String orgId = request.getParameter("depotId");
		String voucherId = request.getParameter("voucherId");
		String params[] = daoObject.getDepotCredentials(orgId);
		PrintWriter out = null;
		try{
			out = response.getWriter();
			//daoObject.getVoucherDetailsofDepot(params);
			if(params==null){
//				addActionError("Selected organization credentials not found");
				out.print("error@Selected organization credentials not found");
			}else{
				out.println(new JSONObject(daoObject.getVoucherDetails(params,voucherId)));
				//){
					//out.print(daoObject.setStatusVoucherAsAccepted(params, voucherId));
			//}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return null;
		
	}

}
