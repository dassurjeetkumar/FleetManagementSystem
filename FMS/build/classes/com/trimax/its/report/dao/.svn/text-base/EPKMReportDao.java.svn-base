package com.trimax.its.report.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;


	public class EPKMReportDao {
		
		 @SuppressWarnings("unchecked")
	    	public JSONObject getDataForEPKMScheduleWise(String todate,String fromdate,String qryy,String scheduleNo){
	    		JSONObject result = new JSONObject();
	    		try{
	    			model.jaxb.xml.trimax.com.VtsResponse6 operatedResult = null;
	                com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
	                com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
	                
	    			 operatedResult=port.webGetEPKMData(fromdate, todate,qryy,scheduleNo,rbKey);
	    			 double totalKM =0.0;
	    			JSONArray array = new JSONArray();
	    			for (int i = 0; i < operatedResult.getWaybillDetails().size(); i++) {
	    				JSONArray ja = new JSONArray();
	    				ja.add(i+1);
	    			
	    				ja.add(operatedResult.getWaybillDetails().get(i).getDepotName());
	    				ja.add("<a data-toggle='modal'  role='button' href='#mymodal45'  onclick=javascript:viewScheduleWiseEPKMData('"
	    						+ fromdate
	    						+ "','"+operatedResult.getWaybillDetails().get(i).getSCHEDULENO()+"') >"
	    						+ operatedResult.getWaybillDetails().get(i).getSCHEDULENO()
	    						+ "</a>");
	    			
	    				ja.add(operatedResult.getWaybillDetails().get(i).getShiftTypeName());
	    				ja.add(operatedResult.getWaybillDetails().get(i).getWAYBILLNO());
	    				ja.add(operatedResult.getWaybillDetails().get(i).getVEHICLENO());
	    				if(operatedResult.getWaybillDetails().get(i).getSchDistance() !=0.0){
	    					ja.add(operatedResult.getWaybillDetails().get(i).getSchDistance());
	    				}
	    				else {
	    					totalKM	= getFormFourTotalDistance(operatedResult.getWaybillDetails().get(i).getDutyTypeId(),operatedResult.getWaybillDetails().get(i).getScheduleId());
	    					ja.add(totalKM);
	    				}
	    				
	    				ja.add(operatedResult.getWaybillDetails().get(i).getRunningKM());
	    				ja.add(operatedResult.getWaybillDetails().get(i).getGpsDistance());
	    				ja.add(operatedResult.getWaybillDetails().get(i).getETIMCollAmt());
	    				
	    				if(operatedResult.getWaybillDetails().get(i).getForm4EPKM()== null){
	    					ja.add("0.00");
	    				}else{
	    				ja.add(operatedResult.getWaybillDetails().get(i).getForm4EPKM());
	    				}
	    				if(operatedResult.getWaybillDetails().get(i).getVtuEPKM()== null){
	    					ja.add("0.00");
	    				}else{
	    				 ja.add(operatedResult.getWaybillDetails().get(i).getVtuEPKM());
	    				}
	    				
	    			    array.add(ja);
	    			}
	    			result.put("aaData", array);
	    		} catch (Exception ex) {
	    			ex.printStackTrace();
	    		} finally {
	    		}
	    		return result;
	    	}
		 
		 public static String rbKey = String.valueOf(getSysParameterForVts());
			
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
			
			public double getFormFourTotalDistance(int duty_type_id,int scheduleNo){
				double distance=0.0;
				String  sql ="";
				Session	session = HibernateUtil.getSession("hibernate.cfg.xml");
				
				  if(duty_type_id==2 || duty_type_id==3){
		           sql="SELECT sum(distance)`total_km` FROM `schedule_details` WHERE `form_four_id` =" + scheduleNo + "" ;
		            }else{
		            sql = "SELECT sum(distance)`total_km` FROM `schedule_details` WHERE `form_four_id` =" + scheduleNo + " and shift_type_id=" + duty_type_id;
		            }
					Query query = session.createSQLQuery(sql);
					distance =Double.parseDouble(query.uniqueResult().toString());
				return distance;
			}
	        
	
	
	}
