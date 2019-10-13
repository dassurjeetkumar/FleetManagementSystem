package com.trimax.its.etm.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;


public class ReportOfDevice extends ActionSupport{
	public String startdate;
	String regionTypeAjaxString = "";
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	public String getReportData() {
		int indepot=0;
		int infme=0,inverifone=0,total=0;
		try {
			Date  ss1=new Date();
			Common common = new Common();
		  	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			String date1=common.getDateFromPicker(startdate);
			Session session1 = null;
			Transaction transaction  = null;
			
			String sql="select org_name,sum(indepot) indepot,sum(infme) infme,sum(inverifone) inverifone," +
					"(sum(indepot)+sum(infme)+sum(inverifone)) total  from(" +
					"select org_name,count(distinct(etm_number)) indepot,0 as infme,0 as inverifone from etm_device_history edh " +
					"inner join org_chart oc on oc.org_chart_id=edh.depot_id where etm_created_date='"+date1+"' and etm_pickup_by_fme is null " +
					"group by org_name union all select org_name,0 as indepot,count(distinct(etm_number)) infme,0 as inverifone " +
					"from etm_device_history edh inner join org_chart oc on oc.org_chart_id=edh.depot_id where etm_pickup_by_fme='"+date1+"' and " +
					"etm_pickup_by_verifone is null and etm_received_date is null group by org_name union all select org_name,0 as indepot,0 as infme," +
					"count(distinct(etm_number)) inverifone from etm_device_history edh inner join org_chart oc on oc.org_chart_id=edh.depot_id " +
					"where etm_pickup_by_verifone='"+date1+"' and etm_received_by_fme is null group by org_name)a group by org_name";
	        
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 Query query = session1.createSQLQuery(sql).addScalar("org_name")
			 		.addScalar("indepot").addScalar("infme").addScalar("inverifone").addScalar("total");
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Bangalore Metropolitan Transport Corporation </br>ETM Device History Report</br> Date:- "+startdate+" </h4></div>";
			 regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				regionTypeAjaxString += "<div ><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
				regionTypeAjaxString +="<td align='center' width='10%'><b>Sl No</b></td><td align='center' width='20%'><b>Depot Name</b></td>";
				regionTypeAjaxString +="<td align='center' width='20%'><b>In depot</b></td><td align='center' width='20%'><b>FME</b></td>" +
						"<td align='center' width='20%'><b>Verifone</b></td><td align='center' width='20%'><b>Total</b></td></tr>";
							
							
						
	                		for (int i = 0; i < aliasToValueMapList.size(); i++) {
	                			
							regionTypeAjaxString +="<tr>";
							Map<String, Object> list = aliasToValueMapList.get(i);
							int j=i+1;
							indepot+=Integer.parseInt(list.get("indepot").toString());
                			infme+=Integer.parseInt(list.get("infme").toString());
                			inverifone+=Integer.parseInt(list.get("inverifone").toString());
                			total+=Integer.parseInt(list.get("total").toString());
							regionTypeAjaxString +="<td>"+j+"</td>";
							regionTypeAjaxString +="<td>"+list.get("org_name").toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.get("indepot").toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.get("infme").toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.get("inverifone").toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.get("total").toString()+"</td>";
							regionTypeAjaxString +="</tr>";
						}
	                		regionTypeAjaxString+="<tr><td colspan=2>Total</td><td>"+indepot+"</td><td>"+infme+"</td><td>"+inverifone+"</td><td>"+total+"</td></tr>";
							regionTypeAjaxString += "</table></div> </div>  <div align='center'>";
	              HttpServletResponse response = ServletActionContext.getResponse();
							PrintWriter out;
						
						
							out = response.getWriter();
							out.print(regionTypeAjaxString);
						} catch (IOException e) {
							
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						return null;
	}

}
