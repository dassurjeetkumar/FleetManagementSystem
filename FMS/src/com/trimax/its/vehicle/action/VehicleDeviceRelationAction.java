package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.ETMAvailabilityDao;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;
import com.trimax.its.vehicle.dao.VehicleDeviceRelationDAO;
import com.trimax.its.vts.dao.ScheduleTripUpdate;
import com.trimax.its.vts.dao.UpdateTripStartEndEvent;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WayBillDetails;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class VehicleDeviceRelationAction extends ActionSupport {

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public String execute()
	{
//		this.setModelTypeList(daoObject.getModelTypeList());
		return "success";
	}

   private String orgdepotname;
	public String getOrgdepotname() {
	return orgdepotname;
}

public void setOrgdepotname(String orgdepotname) {
	this.orgdepotname = orgdepotname;
}
Session session = null;

	
	public String getVehicleDeviceMapping() {

		
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		VehicleDeviceRelationDAO dao=new VehicleDeviceRelationDAO();
		JSONObject result = new JSONObject();
	
		try {
			
			String orgtypeid = (String) req.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute(
					"orgchartid");
			System.out.println("orgtypeid..........." + orgtypeid
					+ "orgchartid................." + orgchartid);
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "org.org_chart_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "org.org_chart_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "org.org_chart_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
//			System.out.println("id...." + id);
			
			

			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getVehicleDeviceRelation(1, req, "", "",id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	//webservice call End
	
/*	String returnString="";
	public String syncData(){
		
			System.out.println("INSIDE sync");
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			 String mappedSchedule="";
			try{
				VehicleDeviceRelationDAO dao=new VehicleDeviceRelationDAO();
				 Session session1 = null;
					Transaction transaction  = null;
				  String qry = "Select license_number,device_serial_number,sc.phone_number phnNumber,IFNULL(cause_status,'')cause_status,v.org_chart_id orgId,oc.org_name orgName from vehicle v inner join " +
				  		" vehicle_device vd on v.vehicle_id=vd.vehicle_id " +
				  		" inner join device d on vd.device_id = d.device_id  inner join sim_vtu sv on d.device_id=sv.device_id inner join " +
				  		" simcard sc on sv.sim_id=sc.simcard_id inner join  org_chart oc on v.org_chart_id=oc.org_chart_id  " +
				  		" where  v.status='ACTIVE' and v.deleted_status=0 and vd.status='ACTIVE' and d.status='ACTIVE' and d.deleted_status=0 and sv.status='Active'";
				  session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					 
					 Query query = session1.createSQLQuery(qry).addScalar("license_number")
								.addScalar("device_serial_number").addScalar("phnNumber").addScalar("cause_status").addScalar("orgId")
								.addScalar("orgName");
						
					 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = query.list();
						
						List<WayBillDetails> mapped= new ArrayList<WayBillDetails>();
						 for (int i = 0; i < aliasToValueMapList.size(); i++) {
							 Map<String, Object> list = aliasToValueMapList.get(i);
							
							 String license_number=list.get("license_number").toString();
							 String deviceSerialNo=list.get("device_serial_number").toString();
							 String phnNo=list.get("device_serial_number").toString();
							 String causeStatus=list.get("cause_status").toString();
							 int orgId=Integer.parseInt(list.get("orgId").toString());
							 String orgName=list.get("orgName").toString();
							 String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
							
//							 System.out.println("license_number=="+license_number+"deviceSerialNo==="+deviceSerialNo+"phnNo=="+phnNo+"causeStatus=="+causeStatus
//									 +"orgId=="+orgId+"orgName=="+orgName+"currentDate=="+currentDate);
							 WayBillDetails details=new WayBillDetails();
							 details.setDepotId(orgId);
							 details.setOrgName(orgName);
							 details.setVEHICLENO(license_number);
							 details.setDEVICEID(deviceSerialNo);
							 details.setSTATUS(causeStatus);
							 details.setSimNumber(phnNo);
							 details.setDUTYDT(currentDate);
							 
							 mapped.add(details);
						 }
					    	 WsUtil_Service service = new WsUtil_Service();
								WsUtil port = service.getWsUtilPort();
								model.jaxb.xml.trimax.com.VtsResponse6 alertresult = null;
								alertresult = port.getVehicleDeviceRelationDataFromITS(mapped,"1");
								System.out.println("size    "+alertresult.getWaybillDetails().size());
								 if(alertresult.getWaybillDetails().get(0).getSTATUS().equals("true")){
										addActionMessage("Data Sync successfully");
										returnString = "success";
										}else{
											addActionError("Data Not Sync..... please try after sometime");
											returnString = "fail";
										}
					    
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					return returnString;
				}*/
	}





	

