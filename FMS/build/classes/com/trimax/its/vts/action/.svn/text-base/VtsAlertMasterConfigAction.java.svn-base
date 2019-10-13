package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.vts.dao.VehicleAlertConfigDAO;
import com.trimax.its.vts.dao.VehicleDepotinoutDAO;
import com.trimax.its.vts.dao.VtsAlertMasterConfigDao;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.VtsAlertMaster;

public class VtsAlertMasterConfigAction extends ActionSupport{
	private VtsAlertMaster vtsalertmaster;
	private String requestType;

	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public VtsAlertMaster getVtsalertmaster() {
		return vtsalertmaster;
	}
	public void setVtsalertmaster(VtsAlertMaster vtsalertmaster) {
		this.vtsalertmaster = vtsalertmaster;
	}
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String showVtsAlertMastConfig()
	{
		return "success";
	}
	
	public String saveAlertConfigMasterDetails()
	{
		try {
			System.out.println("i am inside saveAlertConfig"+ vtsalertmaster.getVts_id()+"VTS PACKET CODE..."+vtsalertmaster.getPacket_code()+"VTS MIS BYTES..."+vtsalertmaster.getMisc_bytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String vts_packet_code=vtsalertmaster.getPacket_code();
		if(("".equals(vts_packet_code))||(vts_packet_code==null)||(vts_packet_code.length()==0)){
			System.out.println("hi if of old");
			 
	              addFieldError("packetcode","Vts Packet Code is Manadatory");
	            
	              return "input";
	          	
	              
		}
		CommonValidation cm=new CommonValidation();
		if(!cm.isSpecialChar(vts_packet_code))
		{
			addFieldError("packet_code", "Special Character For Vts Packet Code is Not Allowed");
			return "input";
		}
		String vts_mis_bytes=vtsalertmaster.getMisc_bytes();
		if(("".equals(vts_mis_bytes))||(vts_mis_bytes==null)||(vts_mis_bytes.length()==0)){
			System.out.println("hi if of old");
			 
	              addFieldError("mis_bytes","Vts Mis Bytes is Manadatory");
	            
	              return "input";
	          	
	              
		}
		
		if(!cm.isSpecialChar(vts_mis_bytes))
		{
			addFieldError("mis_bytes", "Special Character For Vts Mis Bytes is Not Allowed");
			return "input";
		}
		String vts_alert_shortcode=vtsalertmaster.getAlert_short_code();
		if(("".equals(vts_alert_shortcode))||(vts_alert_shortcode==null)||(vts_alert_shortcode.length()==0)){
			
			 
	              addFieldError("alert_short_code","Vts Alert Short Code is Manadatory");
	           
	              return "input";
	          	
	              
		}
		
		if(!cm.isSpecialChar(vts_alert_shortcode))
		{
			addFieldError("alert_short_code", "Special Character For Vts Alert Short Code is Not Allowed");
			return "input";
		}
		String vts_alert_desc=vtsalertmaster.getAlert_desc();
		if(("".equals(vts_alert_desc))||(vts_alert_desc==null)||(vts_alert_desc.length()==0)){
			
			 
	              addFieldError("alert_desc","Vts Alert Desc is Manadatory");
	              
	              return "input";
	          	
	              
		}
		
		if(!cm.isSpecialChar(vts_alert_desc))
		{
			addFieldError("alert_desc", "Special Character For Vts Alert Desc is Not Allowed");
			return "input";
		}
		int insertid = 0;
		VtsAlertMasterConfigDao vehiclealertmasterconfigdao = new VtsAlertMasterConfigDao();
		int duplicateinsertcount = vehiclealertmasterconfigdao.checkDuplicateInsertAlertConfig(vtsalertmaster);
		if (duplicateinsertcount == 0) {
			insertid = vehiclealertmasterconfigdao.saveAlertConfigDetails(vtsalertmaster);
			System.out.println("ticket bag alert decsription is,..."	+ duplicateinsertcount);
			msg = "Vehicle Alert Config  Id " + insertid+ " Inserted Successfully";
			return "success";
	} else {
		System.out.println("ticket bag alert decsription is,..."+ duplicateinsertcount);
		msg = "Vehicle Alert Config   Id Already Exists";
		return "input";
	}
		
	}
	/**
	 * showVehicleAlertConfigMasterList
	 * @return
	 * @throws IOException
	 */
	public String showVehicleAlertConfigMasterList() throws IOException
	{
		 System.out.println("\n \t ************Start of showVehicleDepotinoutList*****************");
		 HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			VtsAlertMasterConfigDao vehiclealertmasterconfigdao = new VtsAlertMasterConfigDao();
			Date FromDate = new Date();
			PrintWriter out = null;
			
			
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				String[] cols = { "", "", "", "", "", "", "", "" };
				JSONObject result = new JSONObject();
				out = response.getWriter();
				result = vehiclealertmasterconfigdao.getDataForVtsMaster(1, request );
				out.print(result);
			} catch (Exception ex) {
				// ex.printStackTrace();
			} finally {

			}
			
	        System.out.println("************showVehicleDepotinoutList**************");
			return null; 

	} 
	
	public String editAlertConfigMasterDetails()
	{
		int id = vtsalertmaster.getVts_id();
		VtsAlertMasterConfigDao vehiclealertmasterconfigdao = new VtsAlertMasterConfigDao();
		
	   int duplicateupdatecount=vehiclealertmasterconfigdao.checkDuplicateUpdateAlertConfig(vtsalertmaster);
	   String vts_packet_code=vtsalertmaster.getPacket_code();
		if(("".equals(vts_packet_code))||(vts_packet_code==null)||(vts_packet_code.length()==0)){
			System.out.println("hi if of old");
			 
	              addFieldError("packet_code","Vts Packet Code is Manadatory");
	            
	              return "input";
	          	
	              
		}
		CommonValidation cm=new CommonValidation();
		if(!cm.isSpecialChar(vts_packet_code))
		{
			addFieldError("packet_code", "Special Character For Vts Packet Code is Not Allowed");
			return "input";
		}
		String vts_mis_bytes=vtsalertmaster.getMisc_bytes();
		if(("".equals(vts_mis_bytes))||(vts_mis_bytes==null)||(vts_mis_bytes.length()==0)){
			
			 
	              addFieldError("mis_bytes","Vts Mis Bytes is Manadatory");
	            
	              return "input";
	          	
	              
		}
		
		if(!cm.isSpecialChar(vts_mis_bytes))
		{
			addFieldError("mis_bytes", "Special Character For Vts Mis Bytes is Not Allowed");
			return "input";
		}
		String vts_alert_shortcode=vtsalertmaster.getAlert_short_code();
		if(("".equals(vts_alert_shortcode))||(vts_alert_shortcode==null)||(vts_alert_shortcode.length()==0)){
			
			 
	              addFieldError("alert_short_code","Vts Alert Short Code is Manadatory");
	           
	              return "input";
	          	
	              
		}
		
		if(!cm.isSpecialChar(vts_alert_shortcode))
		{
			addFieldError("alert_short_code", "Special Character For Vts Alert Short Code is Not Allowed");
			return "input";
		}
		String vts_alert_desc=vtsalertmaster.getAlert_desc();
		if(("".equals(vts_alert_desc))||(vts_alert_desc==null)||(vts_alert_desc.length()==0)){
			
			 
	              addFieldError("alert_desc","Vts Alert Desc is Manadatory");
	              
	              return "input";
	          	
	              
		}
		
		if(!cm.isSpecialChar(vts_alert_desc))
		{
			addFieldError("alert_desc", "Special Character For Vts Alert Desc is Not Allowed");
			return "input";
		}
		if(duplicateupdatecount==0)
		{
		vehiclealertmasterconfigdao.saveEditAlertConfigMasterDetails(requestType, id,
				vtsalertmaster);

		msg = "Vehicle Alert Config  Id " + id + " Updated Succesfully";
		return "success";
		}
		else
        {
		   msg = "Vehicle Alert Config Already Exists";
		 return "input";
		 }

		
	}
	public String editVehicleAlertMasterConfig()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		VtsAlertMasterConfigDao vehiclealertmasterconfigdao = new VtsAlertMasterConfigDao();
		System.out.println("vts id is..."
				+ Integer.parseInt(request.getParameter("alertconfigmastid")));
		vtsalertmaster = vehiclealertmasterconfigdao
				.getVehicleAlertConfigDetails(Integer.parseInt(request
						.getParameter("alertconfigmastid")));

		 return "success";
	}
	public String deleteVehicleAlertMasterConfig()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		VtsAlertMasterConfigDao vehiclealertmasterconfigdao = new VtsAlertMasterConfigDao();
		int alertconfigmastid = Integer.parseInt(request.getParameter("alertconfigmastid"));

		vehiclealertmasterconfigdao.deleteVehicleAlertConfigMaster(Integer.parseInt(request.getParameter("alertconfigmastid")));
		msg = "Vehicle Alert Config  Id " + alertconfigmastid+ " Deleted successfully";
		 return "success";
	}
	
	public String createVehicleAlertMasterConfig()
	{
		
		 return "success";
	}
	
}
