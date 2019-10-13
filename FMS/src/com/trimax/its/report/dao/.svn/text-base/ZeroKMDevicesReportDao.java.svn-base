package com.trimax.its.report.dao;


import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.trimax.its.util.HibernateUtil;
import com.trimax.vts.NewWsVts_Service;

import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;


public class ZeroKMDevicesReportDao {
	public static String rbKey = String.valueOf(getSysParameterForVts());

	@SuppressWarnings("unchecked")
	public JSONObject getDataForZeroKMDevicesReport(HttpServletRequest request, String depotId,String startdate){
		JSONObject result = new JSONObject();
		try{
			
			System.out.println("ashu :in dao"); //testing 
			
			int temp_depot=Integer.parseInt(depotId);
			String depot=Integer.toString(temp_depot);
			System.out.println("start date :"+startdate+"depotid"+depot);
			String rbKey = String.valueOf(getSysParameterForVts());
		    NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
		    System.out.println("1");
			com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
			System.out.println("2");
			VtsResponseNew myresult=null;;
			myresult=port.webGetZeroKMDevices(depot,startdate,rbKey);
			System.out.println("after web services ");
//			model.jaxb.xml.trimax.com.VtsResponse5 myresult = null;
//			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
//			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			//myresult = port.webGet20PerCan(depot, date, rbKey);
			
			JSONArray array = new JSONArray();
			for (int i = 0; i < myresult.getVtsDatamodelnew().size(); i++) {
				JSONArray ja = new JSONArray();
				// Calling vehicle result
				//System.out.println("Depot"+myresult.getVtsDatamodel().get(i).getDepotName());
				ja.add(i+1);
				ja.add(myresult.getVtsDatamodelnew().get(i).getDeviceId());
				ja.add(myresult.getVtsDatamodelnew().get(i).getVehicleno());
				ja.add(myresult.getVtsDatamodelnew().get(i).getPacketcount());
				
////				ja.add(myresult.getVtsDatamodel().get(i).getScheduleNumberName());
//				String schedule_name=myresult.getVtsDatamodel().get(i)
//						.getScheduleNumberName();
//				//int formFour_id= getScheduleFormFourId(schedule_name);
//
////				System.out.println("form form id"+formFour_id);
//
//				ja.add(myresult.getVtsDatamodelNew().get(i).getDEPOTNAME());
//				ja.add(myresult.getVtsDatamodel().get(i).getSCHDISTANCE());
//				ja.add(myresult.getVtsDatamodel().get(i).getPisDistance());
//				ja.add(myresult.getVtsDatamodel().get(i).getCanPer());
			    array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	
//	PUBLIC INT GETSCHEDULEFORMFOURID(STRING SCHEDULENAME){
//		INT ID=0;
//		SESSION SES=NULL;
//		TRY{
//			 SES=HIBERNATEUTIL.GETSESSION("HIBERNATE.CFG.XML");
//			 QUERY QUERY2 = SES.CREATESQLQUERY("SELECT FORM_FOUR_ID  FROM `FORM_FOUR` WHERE " +
//			 		"`SCHEDULE_NUMBER_NAME` LIKE '%"+SCHEDULENAME+"%' AND `DELETED_STATUS` = '0' AND `CURRENT_STATUS` = 'ACTIVE'  LIMIT 1")
//						.ADDSCALAR("FORM_FOUR_ID",HIBERNATE.STRING);
//			 IF (QUERY2.LIST().SIZE() > 0) {
//					ID = INTEGER.PARSEINT(QUERY2.UNIQUERESULT().TOSTRING());
//				}
////			 ID= (INTEGER)QUERY2.UNIQUERESULT();	
//	}CATCH(EXCEPTION EX)
//	{
//		EX.PRINTSTACKTRACE();
//	}FINALLY{
//		
//	}
//		return id;
//}
	
//		public int getTotalZeroKMDevicesRecords(HttpServletRequest request, String depotId,String date)	{
//		int total=0;	
//		try{
//			int depot=Integer.parseInt(depotId);
//			model.jaxb.xml.trimax.com.VtsResponse5 myresult = null;
//			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
//			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
//			// TODO initialize WS operation arguments here
//			myresult = port.webGet20PerCan(depot, date, rbKey);
//			total=myresult.getVtsDatamodel().size();
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//		}
//		return total;
//		}				

		
	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return param;
	}
}