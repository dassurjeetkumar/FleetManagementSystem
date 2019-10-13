package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.math.BigDecimal;
import java.math.MathContext;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class Revenuegrowthreport extends ActionSupport{

	private static final double NA = 0;
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	 public String depotNo;
	 public String divisonNo;
	 
	
	

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDivisonNo() {
		return divisonNo;
	}

	public void setDivisonNo(String divisonNo) {
		this.divisonNo = divisonNo;
	}

	public String getDepotNo() {
		return depotNo;
	}

	public void setDepotNo(String depotNo) {
		this.depotNo = depotNo;
	}


	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	int subtotalTickets=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";

	private Map<Integer, String> divisionlist;

	public Map<Integer, String> getDivisionlist() {
	return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
	this.divisionlist = divisionlist;
	}

	@Override
	public String execute() throws Exception {
	this.setDivisionlist(getDivisionName());
	return "success";
	}

	
	
	private Map<Integer, String> getDivisionName() {
	Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Query query = session
			.createQuery("from OrganisationChart orgchart  " +
					"where parent_id ='1' and deleted_status=0 order by orgchart.org_name");
	try {
		List<OrganisationChart> list = query.list();
		for (OrganisationChart org : list) {
			resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
		}
	} catch (Exception ex) {
	} finally {
		HibernateUtil.closeSession();
	}
	return resultMap;
	}
	
	@SuppressWarnings("finally")


	public String AjaxRevenuegrowthreport()
	{
	try {
		  
		
	      Common common = new Common();
          Session session1 = null;
		  Transaction transaction  = null;

	  
		   String dateType[] = startdate.split("-");
	        int m1 = Integer.parseInt(dateType[0]);
	        int y1 = Integer.parseInt(dateType[1]);
	        int num = 0;
	        String str1 = " " + m1 + "-" + y1;
	        SimpleDateFormat sd = new SimpleDateFormat("MM-yyyy");
	  	      System.out.println(startdate);
	        
	        SimpleDateFormat month_date = new SimpleDateFormat("MMMM", Locale.ENGLISH);
	        //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");

	        String actualDate = startdate;

	        Date date = sd.parse(actualDate);

	        String currentMonth1 = month_date.format(date);
	        System.out.println("Month :" + currentMonth1);
	        
	        
	        
	        
	        Calendar cal11 = Calendar.getInstance();
	        String currentMonth = new SimpleDateFormat("MMMM").format(cal11.getTime());
	        cal11.add(Calendar.MONTH ,-1);
//	        System.out.println(new SimpleDateFormat("MMMM").format(cal11.getTime()));
//	        System.out.println(prevMonth);  
	        String prevMonth = new SimpleDateFormat("MMMM").format(cal11.getTime());
	        
	        
	        try {
	            try {
	                Date d = sd.parse(str1);
	                Calendar c = Calendar.getInstance();
	                c.setTime(d);
	                num = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	            } catch (ParseException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            String strdat = "" + y1 + "-" + m1 + "-" + 1;
	            String enddat = "" + y1 + "-" + m1 + "-" + num;
	            String strt1="" + 01 + "-" + m1 + "-" + y1;
	            String enddat1="" + num + "-" + m1 + "-" + y1;
	            String prvdate = "";
	            int prevmnth = m1 - 1;
	            String str3="";
	            if (prevmnth == 0) {
	                prevmnth = 12;
	                int prevyear = y1 - 1;
	                String str2 = " " + prevmnth + "-" + prevyear;
	                try {
	                    Date d = sd.parse(str2);
	                    Calendar c = Calendar.getInstance();
	                    c.setTime(d);
	                    num = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	                } catch (ParseException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	                prvdate = "" + prevyear + "-" + prevmnth + "-" + num;

	            } else {
	               
	                if(prevmnth==12 && prevmnth==11){
	                 str3 = " " + y1 + "-" + prevmnth;
	                }else{
	                    str3=""+y1+"-0"+prevmnth+"";
	                }
	                try {
	                    Date d = sd.parse(str3);
	                    Calendar c = Calendar.getInstance();
	                    c.setTime(d);
	                    num = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	                } catch (ParseException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	                prvdate = "" + y1 + "-" + prevmnth + "-" + num;
	            }
	            
	            Date ss1 = new Date();
	             SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    //        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
		           
	            String runDateTime = sdf.format(ss1);
	            String m2="";
	            if(m1==12 && m1==11){
	             m2=""+y1+"-"+m1+"";
	            }else{
	                m2=""+y1+"-0"+m1+"";
	            }
	       
	            String m3="";
	            if(prevmnth==12 && prevmnth==11){
	                m3=""+y1+"-"+prevmnth+"";
	            }else{
	                m3=""+y1+"-0"+prevmnth+"";
	            }
	            System.out.println("m3=="+m3);
	        
	            SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM");
	            SimpleDateFormat month_date1 = new SimpleDateFormat("MMMM", Locale.ENGLISH);
		        //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");

		        String actualDate1 = m3;

		        Date date1 = sd1.parse(actualDate1);

		        String prevmnth1 = month_date1.format(date1);
		        System.out.println("Month :" + prevmnth1);
	            
//	        Calendar cal11 = Calendar.getInstance();
//	        cal11.add(Calendar.MONTH ,-1);
//	        System.out.println(new SimpleDateFormat("MMMM").format(cal11.getTime()));
	    
	        
	    
        
        String sql= "SELECT sum(normal)normal,        sum(daily)daily,        sum(monthly)monthly, sum(normal)+sum(daily)+sum(monthly) total,        Date_Format(dat,'%d')dat,        sum(pnormal)pnormal," +
                "        sum(pdaily)pdaily,        sum(pmonthly)pmonthly, sum(pnormal)+sum(pdaily)+sum(pmonthly) ptotal FROM   (SELECT sum(normal)normal,           sum(daily)daily,           sum(monthly)monthly," +
                "           dat,           0 AS pnormal,                0 AS pdaily,                     0 AS pmonthly    FROM      (SELECT sum(total_value)normal,              0 AS daily,                   0 AS monthly, " +
                "                       dat       FROM daily_pass_consumption       WHERE ticket_type = 1         AND dat LIKE '%"+m3+"%'       GROUP BY dat       UNION ALL SELECT 0 AS normal,                             sum(total_value)daily, " +
                "                            0 AS monthly,                                  dat       FROM daily_pass_consumption       WHERE ticket_type = 2         AND dat LIKE '%"+m3+"%'       GROUP BY dat       UNION ALL SELECT 0 AS normal,  " +
                "                           0 AS daily,                                  sum(total_value)monthly,                                  dat       FROM daily_pass_consumption       WHERE ticket_type = 3         AND dat LIKE '%"+m3+"%'       GROUP BY dat)a" +
                "    GROUP BY dat    UNION ALL SELECT 0 AS normal,                          0 AS daily,                               0 AS monthly,                                    dat,                                    sum(pnormal)pnormal, " +
                "                                   sum(pdaily)pdaily,                                    sum(pmonthly)pmonthly    FROM      (SELECT sum(total_value)pnormal,              0 AS pdaily,                   0 AS pmonthly,                        dat" +
                "       FROM daily_pass_consumption       WHERE ticket_type = 1         AND dat LIKE '%"+m2+"%'       GROUP BY dat       UNION ALL SELECT 0 AS pnormal,                             sum(total_value)pdaily,                             0 AS pmonthly, " +
                "                                 dat       FROM daily_pass_consumption       WHERE ticket_type = 2         AND dat LIKE '%"+m2+"%'       GROUP BY dat       UNION ALL SELECT 0 AS pnormal,                             0 AS pdaily,                                  " +
                "sum(total_value)pmonthly,                                  dat       FROM daily_pass_consumption       WHERE ticket_type = 3         AND dat LIKE '%"+m2+"%'       GROUP BY dat)a    GROUP BY dat)b GROUP BY Date_Format(dat,'%d')";
                   


        
        session1 = HibernateUtil.getSession("hibernate.cfg.xml");
                   Query query = session1.createSQLQuery(sql)
                		   .addScalar("dat")
                		   .addScalar("normal",Hibernate.LONG)
                           .addScalar("daily",Hibernate.LONG)
                           .addScalar("monthly",Hibernate.LONG)
                           .addScalar("total",Hibernate.LONG)
                           .addScalar("pnormal",Hibernate.LONG)
                           .addScalar("pdaily",Hibernate.LONG)
                           .addScalar("pmonthly",Hibernate.LONG) 
                           .addScalar("ptotal",Hibernate.LONG);
                   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
                   List<Map<String, Object>> aliasToValueMapList = query.list();
       
       
//                   String fileName = "EarlyArrivalSummary.txt";
                  
//                   regionTypeAjaxString +=" <div id='header' style='display: none;'><table border='0'><tr><td colspan='13' align='center'><h4>Revenue growth report</br>Date:- "+startdate+"</h4></td></tr></div>";
//                   regionTypeAjaxString +="<div><tr><td colspan='4' align='left'><b>Run Date:-</b>"+runDateTime+"</td></tr></table></div>";
              		
                   
                   regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Revenue growth report</br>Date:- "+startdate+"</h4></div>";
                   regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
 				  
                                      
                                    
                        String nwkr="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _\n\n"
                          +      "                                     "+""+"                                                                          \n"
                            +   "                                            "+""+"                                                                                  \n"
                            +   "                                   Revenue growth report      " +
                           "  \n                                 date:- "+startdate+"   \n"                                          
                            +   "                                                               Run Date:-"+runDateTime+"               \n";
         
           
                        
                        
                        
                        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
                        regionTypeAjaxString +="<td align='center' width='10%'colspan='5'><b>"+prevmnth1+"</b></td><td align='center' width='40%'colspan='4'><b>"+currentMonth1+"</b></td><td align='center' width='40%'colspan='4'><b>% of growth</b></td></tr>";
                       
                        regionTypeAjaxString +="<td align='center' width='10%'><b>Date</b></td><td align='center' width='20%'><b>Tkt Revenue</b></td>";
                        regionTypeAjaxString +="<td align='center' width='20%'><b>Daily Pass</b></td><td align='center' width='20%'><b>Monthly Pass</b></td>";
                        regionTypeAjaxString +="<td align='center' width='20%'><b>Total</b></td><td align='center' width='20%'><b>Tkt Revenue</b></td>";
                        regionTypeAjaxString +="<td align='center' width='20%'><b>Daily Pass</b></td><td align='center' width='20%'><b>Monthly Pass</b></td>";
                        regionTypeAjaxString +="<td align='center' width='10%'><b>Total</b></td><td align='center' width='20%'><b>Tkt Revenue</b></td>";
                        regionTypeAjaxString +="<td align='center' width='20%'><b>Daily Pass</b></td><td align='center' width='20%'><b>Monthly Pass</b></td>";
                        regionTypeAjaxString +="<td align='center' width='20%'><b>Total</b></td></tr>";
                        
                        
                        double ticketgrowth = 0,dailypassgrowth=0,monthlypassgrowth = 0,totalgrowth = 0.0;
                        double pticketgrowth = 0,pdailypassgrowth=0,pmonthlypassgrowth = 0,ptotalgrowth = 0.0;
                        double totaltickgrowth = 0,totaldailypassgrowth=0.0,toalmonthlypassgrowth = 0.0,totaltotalgrowth = 0.0;

                       
                        for (int i = 0; i < aliasToValueMapList.size(); i++) {
                           
                            regionTypeAjaxString +="<tr>";
                            Map<String, Object> list = aliasToValueMapList.get(i);
                            int j=i+1;
                            
                         
//                            regionTypeAjaxString +="<td>"+list.get("dat").toString()+"</td>";
//                            regionTypeAjaxString +="<td>"+list.get("pnormal").toString()+"</td>";
//                            regionTypeAjaxString +="<td>"+(list.get("pdaily").toString())+"</td>";
//                            regionTypeAjaxString +="<td>"+list.get("pmonthly").toString()+"</td>";
//                            regionTypeAjaxString +="<td>"+new BigDecimal(list.get("ptotal").toString())+"</td>";
//                            regionTypeAjaxString +="<td>"+list.get("normal").toString()+"</td>";
//                            regionTypeAjaxString +="<td>"+list.get("daily").toString()+"</td>";
//                            regionTypeAjaxString +="<td>"+list.get("monthly").toString()+"</td>";
//                            regionTypeAjaxString +="<td>"+list.get("total").toString()+"</td>";
//                           
                           
                            regionTypeAjaxString +="<td>"+list.get("dat").toString()+"</td>";
                            regionTypeAjaxString +="<td>"+list.get("normal").toString()+"</td>";
                            regionTypeAjaxString +="<td>"+list.get("daily").toString()+"</td>";
                            regionTypeAjaxString +="<td>"+list.get("monthly").toString()+"</td>";
                            regionTypeAjaxString +="<td>"+list.get("total").toString()+"</td>";
                            regionTypeAjaxString +="<td>"+list.get("pnormal").toString()+"</td>";
                            regionTypeAjaxString +="<td>"+(list.get("pdaily").toString())+"</td>";
                            regionTypeAjaxString +="<td>"+list.get("pmonthly").toString()+"</td>";
                            regionTypeAjaxString +="<td>"+new BigDecimal(list.get("ptotal").toString())+"</td>";
                            
                            
                            ticketgrowth = Double.parseDouble(list.get("normal").toString());
                            dailypassgrowth = Double.parseDouble(list.get("daily").toString());
                            monthlypassgrowth = Double.parseDouble(list.get("monthly").toString());
                            totalgrowth = Double.parseDouble(list.get("total").toString());
                            
                            pticketgrowth = Double.parseDouble(list.get("pnormal").toString());
                            pdailypassgrowth = Double.parseDouble(list.get("pdaily").toString());
                            pmonthlypassgrowth = Double.parseDouble(list.get("pmonthly").toString());
                            ptotalgrowth = Double.parseDouble(list.get("ptotal").toString());
                       
                          
                            
//                            totaltickgrowth =Math.rint(((ticketgrowth-pticketgrowth)/pticketgrowth)*100)/100;
//                            totaldailypassgrowth =Math.rint(((dailypassgrowth-pdailypassgrowth)/pdailypassgrowth)*100)/100;
//                            totaldailypassgrowth =Math.rint(((monthlypassgrowth-pmonthlypassgrowth)/pmonthlypassgrowth)*100)/100;
//                            totaltotalgrowth =Math.rint(((totalgrowth-ptotalgrowth)/ptotalgrowth)*100)/100;

//                            
//                            if(pmonthlypassgrowth==0)
//                            toalmonthlypassgrowth = 0;
//                             else
//                            	 totaldailypassgrowth =Math.rint(((monthlypassgrowth-pmonthlypassgrowth)/pmonthlypassgrowth)*100)/100;
//                       
//                          
//                            if(pticketgrowth==0)
//                            	totaltickgrowth = 0;
//                                 else
//                                	 totaltickgrowth =Math.rint(((ticketgrowth-pticketgrowth)/pticketgrowth)*100)/100;
//                               
                            
                            
                            double monthlypassGrowth =(pmonthlypassgrowth-monthlypassgrowth)/pmonthlypassgrowth;
                           if(Double.isNaN(monthlypassGrowth))
                            	toalmonthlypassgrowth = 0;
                            else
                           	toalmonthlypassgrowth =Math.rint((monthlypassGrowth)*100)/100;
                            
                          
                           
                           double totaltickGrowth =(pticketgrowth-ticketgrowth)/pticketgrowth;
                            if(pticketgrowth==0) 
                            	totaltickgrowth = 0;
                            else
                            	totaltickgrowth =Math.rint((totaltickGrowth)*100)/100;
                            
                            
                            double totaldailypassGrowth =(pdailypassgrowth-dailypassgrowth)/pdailypassgrowth;
                            if(pdailypassgrowth==0) 
                            	totaldailypassgrowth = 0;
                            else
                            	totaldailypassgrowth =Math.rint((totaldailypassGrowth)*100)/100;
                            
                            
                            
                            double   totaltotalGrowth =(ptotalgrowth-totalgrowth)/ptotalgrowth;
                            if(ptotalgrowth==0) 
                            	totaltotalgrowth = 0;
                            else
                            	totaltotalgrowth =Math.rint((totaltotalGrowth)*100)/100;
                            
                            
                  //          totaltickgrowth =Math.rint(((pticketgrowth-ticketgrowth)/pticketgrowth)*100)/100;
                  //          totaldailypassgrowth =Math.rint(((pdailypassgrowth-dailypassgrowth)/pdailypassgrowth)*100)/100;
                  //          toalmonthlypassgrowth =Math.rint((pmonthlypassgrowth-monthlypassgrowth)/pmonthlypassgrowth*100)/100;
                  //          totaltotalgrowth =Math.rint(((ptotalgrowth-totalgrowth)/ptotalgrowth)*100)/100;
  
                            
                            regionTypeAjaxString +="<td>"+totaltickgrowth+"</td>";
                             regionTypeAjaxString +="<td>"+totaldailypassgrowth+"</td>";
                            regionTypeAjaxString +="<td>"+toalmonthlypassgrowth+"</td>";
                            regionTypeAjaxString +="<td>"+totaltotalgrowth+"</td>";
                        } 
                        
                     regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";
                 str+= ""+add("",5)+"|" + add("Total", 90) + "|" + "\n";
//                     
                //     ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
                //     System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));

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
    }catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }

                    return null;
    }

    private String add(String string, int i) {
        // TODO Auto-generated method stub
        return null;
    }

}
