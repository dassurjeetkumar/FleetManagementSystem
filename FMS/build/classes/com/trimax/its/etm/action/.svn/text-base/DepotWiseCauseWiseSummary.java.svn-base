package com.trimax.its.etm.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DepotWiseCauseWiseSummary {
	private String startdate;
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	private String enddate;
	
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	private Map<Integer, String> divisionlist;
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;}
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		//System.out.println("in execute");
		return "success";
	}
	public String getdata(){
		

		JSONObject result = new JSONObject();
		Session session = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
		String divid=req.getParameter("divid");
		String depot=req.getParameter("depot");
		
	    String depots="";
        if(divid.equals("0")){
        }else if(!divid.equals("0") && depot.equals("0")){
     	   depots=" and oc1.parent_id="+divid+" "; 
        }else if(!divid.equals("0") && !depot.equals("0")){
     	   depots=" and oc1.org_chart_id="+depot+" "; 
        }
		
		
		
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select *,(tot_etm-(depotrep+servrep))tot_avil,ROUND((((depotrep+servrep)/tot_etm)*100),2)faulty from (select org_name,tot_etm,"+
				"sum(case when (etm_created_date is not null and etm_pickup_by_fme is null) then 1 else 0 end ) depotrep,"+
				"sum(case when (etm_pickup_by_fme is not null and etm_received_date is null) then 1 else 0 end )servrep,"+
				"sum(case when etm_issue=1 then 1 else 0 end) battpro,"+
				"sum(case when etm_issue=2 then 1 else 0 end) dispro,"+
				"sum(case when etm_issue=3 then 1 else 0 end) etmdamage,"+
				"sum(case when etm_issue=4 then 1 else 0 end) keypadpro,"+
				"sum(case when etm_issue=5 then 1 else 0 end) printerpro,"+
				"sum(case when etm_issue=6 then 1 else 0 end) autorestartorswithoff,"+
				"sum(case when etm_issue=7 then 1 else 0 end) tamperpro,"+
				"sum(case when etm_issue=8 then 1 else 0 end) usbpro,"+
				"sum(case when etm_issue=9 then 1 else 0 end) tktpro,"+
				"sum(case when etm_issue=10 then 1 else 0 end) logpro,"+
				"sum(case when etm_issue=11 then 1 else 0 end) settpro,"+
				"sum(case when etm_issue=12 then 1 else 0 end) lostetm,"+
				"sum(case when etm_issue=13 then 1 else 0 end) 3code,"+
				"sum(case when etm_issue=14 then 1 else 0 end) versionpro,"+
				"sum(case when etm_issue=15 then 1 else 0 end) dustindisplay,"+
				"sum(case when etm_issue=16 then 1 else 0 end) etmsnonotmatched "+
				"from ( "+
				"select org_name,(select count(device_serial_number) from device d inner join device_org do on do.device_id=d.device_id "+
                "inner join org_chart oc on do.org_id=oc.org_chart_id where d.status='Active' and do.status='Active' and oc.org_chart_id=oc1.org_chart_id "+
                "and d.device_type_id=2) tot_etm,ei.id as etm_issue,etm_created_date,etm_pickup_by_fme,etm_resolved_by_fme,etm_pickup_by_verifone,"+
                "etm_resolved_by_verifone,etm_received_by_fme,etm_received_date from etm_device_history edh "+
                "inner join device d on edh.etm_number=d.device_id  inner join org_chart oc1 on edh.depot_id=oc1.org_chart_id "+
                 "inner join etm_issue ei on edh.etm_issue=ei.id "+
                "where d.status='Active' and etm_created_date between '"+date1+"' and '"+date2+"'"+
               "and edh.etm_received_date is null and oc1.org_chart_id !=1 "+depots+" " +
            " union all "+
             "select org_name,(select count(device_serial_number) from device d inner join device_org do on do.device_id=d.device_id "+
             "inner join org_chart oc on do.org_id=oc.org_chart_id where d.status='Active' and do.status='Active' and oc.org_chart_id=oc1.org_chart_id "+
             "and d.device_type_id=2) tot_etm,etm_issue_id as etm_issue,etm_created_date,etm_pickup_by_fme,etm_resolved_by_fme,etm_pickup_by_verifone,"+
             "etm_resolved_by_verifone,etm_received_by_fme,etm_received_date from etm_device_history edh "+
             "inner join device d on edh.etm_number=d.device_id  inner join org_chart oc1 on edh.depot_id=oc1.org_chart_id "+
             "inner join etm_device_issue edi on edh.id=edi.etm_device_history_id  "+
             "where d.status='Active' and etm_created_date between '"+date1+"' and '"+date2+"' "+
             "and edh.etm_received_date is null and oc1.org_chart_id !=1 "+depots+" "+
             ") a group by org_name )b group by org_name";
	/*	String sql="select *,(tot_etm-(depotrep+servrep))tot_avil,ROUND((((depotrep+servrep)/tot_etm)*100),2)faulty from  "+
				"(select org_name,tot_etm,sum(case when (etm_created_date is not null and etm_pickup_by_fme is null) then 1 else 0 end ) depotrep,"+
				"sum(case when (etm_pickup_by_fme is not null and etm_received_date is null) then 1 else 0 end )servrep,"+
				"sum(case when etm_issue=1 then 1 else 0 end) battpro,sum(case when etm_issue=2 then 1 else 0 end) dispro,"+
				"sum(case when etm_issue=3 then 1 else 0 end) etmdamage,sum(case when etm_issue=4 then 1 else 0 end) keypadpro,"+
				"sum(case when etm_issue=5 then 1 else 0 end) printerpro,sum(case when etm_issue=6 then 1 else 0 end) autorestartorswithoff,"+
				"sum(case when etm_issue=7 then 1 else 0 end) tamperpro,sum(case when etm_issue=8 then 1 else 0 end) usbpro,"+
				"sum(case when etm_issue=9 then 1 else 0 end) tktpro,sum(case when etm_issue=10 then 1 else 0 end) logpro,"+
				"sum(case when etm_issue=11 then 1 else 0 end) settpro,sum(case when etm_issue=12 then 1 else 0 end) lostetm,"+
				"sum(case when etm_issue=13 then 1 else 0 end) 3code,sum(case when etm_issue=14 then 1 else 0 end) versionpro,"+
				"sum(case when etm_issue=15 then 1 else 0 end) dustindisplay,sum(case when etm_issue=16 then 1 else 0 end) etmsnonotmatched from "+
				"( select org_name,(select count(device_serial_number) from device d inner join device_org do on do.device_id=d.device_id "+
				"inner join org_chart oc on do.org_id=oc.org_chart_id where d.status='Active' and do.status='Active' and oc.org_chart_id=oc1.org_chart_id "+
				"and d.device_type_id=2) tot_etm,ei.id as etm_issue,etm_created_date,etm_pickup_by_fme,etm_resolved_by_fme,etm_pickup_by_verifone,"+
				"etm_resolved_by_verifone,etm_received_by_fme,etm_received_date from device d "+
				"inner join device_org do on do.device_id=d.device_id "+
				"inner join org_chart oc1 on do.org_id=oc1.org_chart_id  "+
				"left join etm_device_history edh on edh.etm_number=d.device_id  and etm_created_date between '"+date1+"' and   '"+date2+"'  and edh.etm_received_date is null "+
				"left join etm_issue ei on edh.etm_issue=ei.id where d.status='Active'  and oc1.org_chart_id !=1 "+
				" "+depots+" "+
				"union all "+
				"select org_name,(select count(device_serial_number) from device d inner join device_org do on do.device_id=d.device_id "+
				"inner join org_chart oc on do.org_id=oc.org_chart_id where d.status='Active' and do.status='Active' and oc.org_chart_id=oc1.org_chart_id "+
				"and d.device_type_id=2) tot_etm,etm_issue_id as etm_issue,etm_created_date,etm_pickup_by_fme,etm_resolved_by_fme,etm_pickup_by_verifone,"+
				"etm_resolved_by_verifone,etm_received_by_fme,etm_received_date from device d "+
				"inner join device_org do on do.device_id=d.device_id "+
				"inner join org_chart oc1 on do.org_id=oc1.org_chart_id "+
				"left join etm_device_history edh on edh.etm_number=d.device_id and etm_created_date between '"+date1+"' and '"+date2+"' and edh.etm_received_date is null "+
				"left join etm_device_issue edi on edh.id=edi.etm_device_history_id  where d.status='Active' "+
				"and oc1.org_chart_id !=1 "+
				" "+depots+" ) a group by org_name )b group by org_name";*/
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();
	    int totetm=0;
	    int seretm=0;
	    int reparetm=0;
	    int availetm=0;
	    int battery=0;
	    int display=0;
	    int damage=0;
	    int keypad=0;
	    int printer=0;
	    int swithoff=0;
	    int tamper=0;
	    int usb=0;
	    int ticket=0;
	    int log=0;
	    int settle=0;
	    int lost=0;
	    int code=0;
	    int version=0;
	    int dust=0;
	    int serial=0;
	   
	    
	      JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
			ja.add(list.get("org_name").toString());
		ja.add(list.get("tot_etm").toString());
		totetm=totetm+Integer.parseInt(list.get("tot_etm").toString());
			ja.add(list.get("servrep").toString());
			seretm=seretm+Integer.parseInt(list.get("servrep").toString());
			ja.add(list.get("depotrep").toString());
			reparetm=reparetm+Integer.parseInt(list.get("depotrep").toString());
			ja.add(list.get("tot_avil").toString());
			availetm=availetm+Integer.parseInt(list.get("tot_avil").toString());
			ja.add(list.get("faulty").toString());
			ja.add(list.get("battpro").toString());
			battery=battery+Integer.parseInt(list.get("battpro").toString());
			//ja.add(Integer.parseInt(list.get("sch_0").toString())+Integer.parseInt(list.get("sch_v").toString())+Integer.parseInt(list.get("sch_vv").toString())+Integer.parseInt(list.get("sch_a").toString()));
			ja.add(list.get("dispro").toString());
			display=display+Integer.parseInt(list.get("dispro").toString());
			ja.add(list.get("etmdamage").toString());
			damage=damage+Integer.parseInt(list.get("etmdamage").toString());
			ja.add(list.get("keypadpro").toString());
			keypad=keypad+Integer.parseInt(list.get("keypadpro").toString());
			ja.add(list.get("printerpro").toString());
			printer=printer+Integer.parseInt(list.get("printerpro").toString());
			ja.add(list.get("autorestartorswithoff").toString());
			swithoff=swithoff+Integer.parseInt(list.get("autorestartorswithoff").toString());
			ja.add(list.get("tamperpro").toString());
			tamper=tamper+Integer.parseInt(list.get("tamperpro").toString());
			ja.add(list.get("usbpro").toString());
			usb=usb+Integer.parseInt(list.get("usbpro").toString());
			ja.add(list.get("tktpro").toString());
			ticket=ticket+Integer.parseInt(list.get("logpro").toString());
			ja.add(list.get("logpro").toString());
			log=log+Integer.parseInt(list.get("logpro").toString());
			ja.add(list.get("settpro").toString());
			settle=settle+Integer.parseInt(list.get("settpro").toString());
			ja.add(list.get("lostetm").toString());
			lost=lost+Integer.parseInt(list.get("lostetm").toString());
			ja.add(list.get("3code").toString());
			code=code+Integer.parseInt(list.get("3code").toString());
			ja.add(list.get("versionpro").toString());
			version=version+Integer.parseInt(list.get("versionpro").toString());
			ja.add(list.get("dustindisplay").toString());
			dust=dust+Integer.parseInt(list.get("dustindisplay").toString());
			ja.add(list.get("etmsnonotmatched").toString());
			serial=serial+Integer.parseInt(list.get("etmsnonotmatched").toString());
		/*	ja.add(list.get("vv_de").toString());
			ja.add(list.get("vv_tot").toString());
			ja.add(list.get("a_gs").toString());
			ja.add(list.get("a_do").toString());
			ja.add(list.get("a_no").toString());
			ja.add(list.get("a_ns").toString());
			ja.add(list.get("a_ss").toString());
			ja.add(list.get("a_de").toString());
			ja.add(list.get("a_tot").toString());
			ja.add(list.get("TOTALSER").toString());
			ja.add(list.get("totdead").toString());*/
			//double dd=Double.parseDouble(list.get("totdead").toString())+Double.parseDouble(list.get("TOTALSER").toString());
			// BigDecimal d=new BigDecimal(dd);
				//ja.add(list.get("total").toString());
			array.add(ja);
			
				
				 }
	        JSONArray ja = new JSONArray();
	        ja.add(1111);
	        ja.add("Total");
	        ja.add(totetm);
	        ja.add(seretm);
	        ja.add(reparetm);
	        ja.add(availetm);
	        ja.add("");
	        ja.add(battery);
	        ja.add(display);
	        ja.add(damage);
	        ja.add(keypad);
	        ja.add(printer);
	        ja.add(swithoff);
	        ja.add(tamper);
	        ja.add(usb);
	        ja.add(ticket);
	        ja.add(log);
	        ja.add(settle);
	        ja.add(lost);
	        ja.add(code);
	        ja.add(version);
	        ja.add(dust);
	        ja.add(serial);
	       array.add(ja);
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	
		
	}
}
