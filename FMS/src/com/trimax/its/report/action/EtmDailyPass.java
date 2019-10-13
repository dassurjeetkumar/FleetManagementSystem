package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class EtmDailyPass {

	private String startdate;
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
		this.divisionlist = divisionlist;
	}
    public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String execute() throws Exception {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
}

	 //  List list = new ArrayList();
	public void getdata() {
		JSONObject result = new JSONObject();
			Map list=new LinkedHashMap();
	   Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
		
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			 Common common = new Common();
			String date=common.getDateFromPicker(startdate);
			String edate=common.getDateFromPicker(enddate);
			String div=req.getParameter("div");
			String org_chart_id=req.getParameter("depot");
			double etmpasscount=0;
			double etmpassamt=0;
			double mpasscount=0;
			double mpassamt=0;
			double tpasscount=0;
			double tpassamt=0;
			System.out.println("depot id are -----------"+div+"======="+org_chart_id);
			try {
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			 String subquery=" depot_id="+org_chart_id;
			if(Integer.parseInt(org_chart_id)==0 && Integer.parseInt(div)!=0 ) {
				 subquery=" division_id="+div;
			}

			String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info "
					+ " where "+subquery+"  order by depotname ";

			Query qry=session1.createSQLQuery(sql1).
					addScalar("depotname")
					.addScalar("central_ip",Hibernate.STRING)
					.addScalar("central_uname")
					.addScalar("central_pwd");
			
			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = qry.list();	
				String depotdb="";
				String depotIp="";
				String user="";
				String password="";
			      JSONArray array = new JSONArray();
				for (int j = 0; j < aliasToValueMapList.size(); j++) {
					Map<String, Object> aliasValue = aliasToValueMapList.get(j);
					 depotdb=aliasValue.get("depotname").toString();
					 depotIp=aliasValue.get("central_ip").toString();
					 user=aliasValue.get("central_uname").toString();
					 password=aliasValue.get("central_pwd").toString();
				try {
			 Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			 //connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

			 
			 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();

/*			 String qry1="select org_name,denomanual,sum(etmpasscount)etmpasscount,sum(etmpassamt)etmpassamt,sum(tickettotal)tickettotal,sum(bvalue)bvalue,sum(etmpasscount+tickettotal)tpass,sum(etmpassamt+bvalue)tamt from (select org_name,wd.waybill_no,date(wd.Ticket_Audited_Date)tdate,ifnull(ticket_manual.ticket_type_manual_name,'') as ticket_type_manual,ifnull(denoType.denomination_type_manual,0) as denomanual," + 
			 		"(ifnull((SELECT sum(`px_count`) FROM `ticket` WHERE `waybil_Id` =wd.waybil_Id AND `ticket_sub_type_short_code` = 'dp'),0))etmpasscount," + 
			 		"(ifnull((SELECT SUM(`px_total_amount`) FROM `ticket` WHERE `waybil_Id` =wd.waybil_Id AND `ticket_sub_type_short_code` = 'dp'),0))etmpassamt," + 
			 		"sum(if(block.value!=0,(block.closing_number-block.opening_number+1),0))as tickettotal,sum(ifnull(block.value,0)) as bvalue from Waybill_Details wd  " + 
			 		"left join waybill_cwa_block_master cwa  on cwa.waybill_cwa_block_master_id=wd.param2  " + 
			 		"left join Waybill_cwa_receipt_details block  on block.waybill_cwa_block_master_id = cwa.waybill_cwa_block_master_id "+
			 	//	+ "# and block.value!=0" + 
			 		" left join denomination_type_manual denoType ON block.denomination_type_manual_id = denoType.denomination_type_manual_id 	" + 
			 		" left join ticket_type_manual ticket_manual on	block.ticket_type_manual_id=ticket_manual.ticket_type_manual_id   " + 
			 		"  join depot  " + 
			 		" where  wd.Ticket_Audited_Date between '"+date+" 00:00:00' and '"+edate+" 23:59:59'   and " + 
			 		" ticket_manual.ticket_type_manual_id=2" + 
			 		//"#and denoType.denomination_type_manual=5" + 
			 		" group by wd.generated_date,waybill_no  " + 
			 		" order by tdate,denomanual)aa group by denomanual";*/
			 
			 
			 String 	qry1="select org_name,tdate,denomanual,sum(etmpasscount)etmpasscount,sum(etmpassamt)etmpassamt, " + 
			 		"sum(tickettotal)tickettotal,sum(bvalue)bvalue,sum(etmpasscount+tickettotal)tpass,sum(etmpassamt+bvalue)tamt from ( " + 
			 		"select org_name,date(wd.Ticket_Audited_Date)tdate, " + 
			 		"ifnull(denoType.denomination_type_manual,0) as denomanual, " + 
			 		"(0)etmpasscount, " + 
			 		"(0)etmpassamt, " + 
			 		"sum(if(block.value!=0,(block.closing_number-block.opening_number+1),0))as tickettotal,sum(ifnull(block.value,0)) as bvalue from Waybill_Details wd   " + 
			 		"inner join waybill_cwa_block_master cwa  on cwa.waybill_cwa_block_master_id=wd.param2   " + 
			 		"inner join Waybill_cwa_receipt_details block  on block.waybill_cwa_block_master_id = cwa.waybill_cwa_block_master_id   " + 
			 		"inner join denomination_type_manual denoType ON block.denomination_type_manual_id = denoType.denomination_type_manual_id 	  " + 
			 		"inner join ticket_type_manual ticket_manual on	block.ticket_type_manual_id=ticket_manual.ticket_type_manual_id     join depot    " + 
			 		"where  wd.Ticket_Audited_Date between '"+date+" 00:00:00' and '"+edate+" 23:59:59'   and  ticket_manual.ticket_type_manual_id=2 and block.value!=0 " + 
			 		"group by tdate,denomanual " + 
			 		"union " + 
			 		"select org_name,date(wd.Ticket_Audited_Date)tdate,round(px_total_amount-service_tax_amt,0) as denomanual,sum(ifnull(px_count,0))etmpasscount, " + 
			 		"sum(ifnull(px_total_amount-service_tax_amt,0))etmpassamt, " + 
			 		"(0)as tickettotal,(0) as bvalue from Waybill_Details wd  " + 
			 		"inner join ticket t on t.waybil_id=wd.waybil_id " + 
			 		"join depot    " + 
			 		"where  wd.Ticket_Audited_Date between '"+date+" 00:00:00' and '"+edate+" 23:59:59'    " + 
			 		"AND `ticket_sub_type_short_code` = 'dp' and px_total_amount>0  " + 
			 		"group by tdate,denomanual  )aa group by tdate,denomanual order by tdate,denomanual";
			 
			System.out.println("qry1"+qry1);
			rs=stmt.executeQuery(qry1);

			 while(rs.next()){
				 JSONArray ja = new JSONArray();
				 int count=j+1;
				// int waycount=Integer.parseInt(rs.getString("waybill").toString());
					ja.add(count);
		    		ja.add(rs.getString("org_name").toString());
		    		ja.add(rs.getString("tdate").toString());
		    		ja.add(rs.getString("denomanual").toString());
		    		ja.add(rs.getString("etmpasscount").toString());
		    	etmpasscount +=Double.parseDouble(rs.getString("etmpasscount").toString());
		    		ja.add(rs.getString("etmpassamt").toString());
			    	etmpassamt +=Double.parseDouble(rs.getString("etmpassamt").toString());
		    		ja.add(rs.getString("tickettotal").toString());
			    	mpasscount +=Double.parseDouble(rs.getString("tickettotal").toString());
		    		ja.add(rs.getString("bvalue").toString());
			    	mpassamt +=Double.parseDouble(rs.getString("bvalue").toString());
		    		ja.add(rs.getString("tpass").toString());
			    	tpasscount +=Double.parseDouble(rs.getString("tpass").toString());
		    		ja.add(rs.getString("tamt").toString());
			    	tpassamt +=Double.parseDouble(rs.getString("tamt").toString());
		    		
		    		
		    		
				array.add(ja);
			}

			 
			 
				}catch (Exception e) {
                    e.printStackTrace();
			}
			 }
				
				 JSONArray ja = new JSONArray();
				 ja.add("Total");
				 ja.add("");
				 ja.add("");
				 ja.add("");
				 ja.add(BigDecimal.valueOf(etmpasscount).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				 ja.add(BigDecimal.valueOf(etmpassamt).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				 ja.add(BigDecimal.valueOf(mpasscount).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				 ja.add(BigDecimal.valueOf(mpassamt).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				 ja.add(BigDecimal.valueOf(tpasscount).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				 ja.add(BigDecimal.valueOf(tpassamt).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					array.add(ja);
					
			     HttpServletResponse response = ServletActionContext.getResponse();

			   	 PrintWriter out;

			   		    	result.put("aaData",array);
			   				out = response.getWriter();
			   				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
				
}

}
