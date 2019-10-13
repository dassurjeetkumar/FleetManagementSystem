package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.org.json.JSONException;
import com.trimax.its.org.json.JSONObject;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.Route;
import com.trimax.its.route.model.RoutePoint;
import com.trimax.its.transport.dao.BusStopsDAO;

import com.trimax.its.transport.model.BusStopGroup;
import com.trimax.its.transport.model.BusStopGroupStop;

import com.trimax.its.transport.dao.BusStopsGroupDAO;
import com.trimax.its.transport.model.BusStopGroup;
import com.trimax.its.transport.model.BusStopGroupStop;

import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.BusstopJson;
import com.trimax.its.transport.model.GroupType;
import com.trimax.its.transport.model.PointType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class BusStopsAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private BusStops bustops;
	 private BusStopGroup busStopGroup;
	 private BusStopGroupStop busStopGroupStop;
	 
	private OrganisationChart orgchart; 
	
    private List<BusStops> busstoplist;
    private List<String> busstoplists;
    private Map<String,BusStops> namelist;
	
	

	
	

	

	public Map<String, BusStops> getNamelist() {
		return namelist;
	}

	public void setNamelist(Map<String, BusStops> namelist) {
		this.namelist = namelist;
	}

	public List<String> getBusstoplists() {
		return busstoplists;
	}

	public void setBusstoplists(List<String> busstoplists) {
		this.busstoplists = busstoplists;
	}

	public List<BusStops> getBusstoplist() {
		return busstoplist;
	}

	public void setBusstoplist(List<BusStops> busstoplist) {
		this.busstoplist = busstoplist;
	}
	
	
	
	
	 public OrganisationChart getOrgchart() {
		return orgchart;
	}

 
	public void setOrgchart(OrganisationChart orgchart) {
		this.orgchart = orgchart;
	}


	private String requestType;
	 public String getRequestType() {
		return requestType;
	}


	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}


	List<BusStops> list;
	List<PointType> listPoint;
	List<OrganisationChart> listOrgcharts;
	List<RoutePoint> routePointlist;
	List<Route> routelist;
	List<BusStopGroupStop> grouplist;
	
	public String busStopNames;
	
	
	public List<BusStopGroupStop> getGrouplist() {
		return grouplist;
	}


	public void setGrouplist(List<BusStopGroupStop> grouplist) {
		this.grouplist = grouplist;
	}


	public List<Route> getRoutelist() {
		return routelist;
	}


	public void setRoutelist(List<Route> routelist) {
		this.routelist = routelist;
	}


	public List<RoutePoint> getRoutePointlist() {
		return routePointlist;
	}


	public void setRoutePointlist(List<RoutePoint> routePointlist) {
		this.routePointlist = routePointlist;
	}


	public List<OrganisationChart> getListOrgcharts() {
		return listOrgcharts;
	}


	public void setListOrgcharts(List<OrganisationChart> listOrgcharts) {
		this.listOrgcharts = listOrgcharts;
	}


	String busid;
	public List<PointType> getListPoint() {
		return listPoint;
	}


	public void setListPoint(List<PointType> listPoint) {
		this.listPoint = listPoint;
	}


	public String getBusid() {
		return busid;
	}


	public void setBusid(String busid) {
		this.busid = busid;
	}


	public List<BusStops> getList() {
		return list;
	}


	public void setList(List<BusStops> list) {
		this.list = list;
	}


	public BusStops getBustops() {
		return bustops;
	}


	public void setBustops(BusStops bustops) {
		this.bustops = bustops;
	}
	
	public String busid1;
	


public String getBusid1() {
		return busid1;
	}

	public void setBusid1(String busid1) {
		this.busid1 = busid1;
	}


int sessbustype=0;
	@SkipValidation
	public String execute(){
		BusStopsDAO busstopsdao = new BusStopsDAO();
		 list = busstopsdao.getBusStopList();
		 listPoint= busstopsdao.getPointList();
		 listOrgcharts=busstopsdao.getOrgChart();
		 HttpServletRequest request=ServletActionContext.getRequest();
		 //System.out.println("sessbustype value is++"+request.getParameter("stop_type"));
		 if(sessbustype==0){
		 request.getSession().setAttribute("stop_type", request.getParameter("stop_type"));
		 }
		 request.getSession().setAttribute("centerlat", "12.97928309");
		 request.getSession().setAttribute("centerlang", "77.57205963");
		 //System.out.println("new value assign===========>"+(Double)request.getSession().getAttribute("centerlate")+"====="+(Double)request.getSession().getAttribute("centerlange"));
		 if((Double)request.getSession().getAttribute("centerlate")!= null){
			// System.out.println("new value assign===========>");
			 request.getSession().setAttribute("centerlat", (Double)request.getSession().getAttribute("centerlate"));	 
		 }
		 if((Double)request.getSession().getAttribute("centerlange")!= null){
			// System.out.println("new value assign===========>");
			 request.getSession().setAttribute("centerlang",(Double) request.getSession().getAttribute("centerlange"));	 
		 }
		 sessbustype++;
		// System.out.println("new value assign===========>"+request.getSession().getAttribute("centerlat")+"====="+request.getSession().getAttribute("centerlang"));
		 
		return "success";
	}
	
	@SkipValidation
	public String edit(){
		BusStopsDAO busstopsdao = new BusStopsDAO();
		 HttpServletRequest request = ServletActionContext.getRequest();
		 bustops = busstopsdao.getBusStops(Integer.parseInt(request.getParameter("busid")));
		 bustops.setBus_stop_name_nudi(bustops.getBus_stop_name_nudi());
		 bustops.setBusStopNameKannada1(bustops.getBusStopNameKannada());
		 bustops.setBusStopCodeKannada1(bustops.getBus_stop_code_kannada());
		 bustops.setKalias11(bustops.getKalias1());
		 bustops.setK_alias_21(bustops.getK_alias_2());
		 bustops.setK_alias_31(bustops.getK_alias_3());
		 bustops.setK_alias_41(bustops.getK_alias_4());
		 bustops.setK_alias_51(bustops.getK_alias_5());
		 bustops.setK_alias_61(bustops.getK_alias_6());
		 listOrgcharts=busstopsdao.getOrgChart();
		// BusStopsDAO busstopsdao = new BusStopsDAO();
			listPoint= busstopsdao.getPointList();
		//System.out.println("Bus stops Size------>"+requestType);
		 return "success";
	}
	
	public String addEdited() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		//System.out.println("Bus stops Size------>"+bustops.getId());
		BusStopsDAO busstopsdao = new BusStopsDAO();
		listPoint= busstopsdao.getPointList();
		//String orgid = request.getParameter("org_chart_id");
		//System.out.println("Bus stops Size------>"+orgid);
		//HttpServletRequest request=ServletActionContext.getRequest();
		
		return  busstopsdao.saveEdited(requestType,bustops.getId(), bustops);
		
	}
	
	@SkipValidation
	public String addEditedMap() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		//System.out.println("Bus stops Size------>"+bustops.getId());
		String errmsg="";
		BusStopsDAO busstopsdao = new BusStopsDAO();
		CommonValidation commvalidate = new CommonValidation();
		HttpServletResponse response=ServletActionContext.getResponse();

		if(commvalidate.isEmpty(bustops.getBusStopName())){
			//addActionError("Bus Stop Name is required");
			//System.out.println("Bus Stop Name is required");
			errmsg=errmsg+"Bus Stop Name is required<br/>";
			//return null;
		}
		if(commvalidate.isEmpty(bustops.getBus_stop_code())){
			//addActionError("Bus Stop Code is required");
			errmsg=errmsg+"Bus Stop Code is required<br/>";
		}
		if(!busstopsdao.checkBusstopCode(bustops.getBus_stop_code().trim(),bustops.getId())){
			//addActionError("Bus Stop Code already exists");
			errmsg=errmsg+"Duplicate bus stop Code<br/>";	
		}
		if(!busstopsdao.checkCoordinateEdit(String.valueOf(bustops.getLatitude()),String.valueOf(bustops.getLongitude()))){
			//addActionError("Bus Stop Code already exists");
			errmsg=errmsg+"Bus Stop Already exist on given location<br/>";	
		}
		
		try {
			PrintWriter out;
			out = response.getWriter();
		if(errmsg!=""){
			
			
			out.print(errmsg);
		}else{
			orgchart = new OrganisationChart();
			int valofid=bustops.getPointtype().getPoint_type_id();
			/*if(valofid==6){
				orgchart.setOrg_chart_id(Integer.parseInt(request.getParameter("bustops.bus_stations.org_chart_id")));
			} else{
				orgchart.setOrg_chart_id(-1);
			}
			*/
			//bustops.setBus_stations(orgchart);
			String res=busstopsdao.saveEdited(requestType,bustops.getId(), bustops);
			if(res=="successmap"){
				  request=ServletActionContext.getRequest();
				
			//out.print("Saved Successfully");
			//if((bustops.getToll_fee()!=Integer.parseInt(request.getParameter("toll_fee_old"))) || (bustops.getTollZone()!=request.getParameter("tollZone_old"))){
				out.print("Saved Successfully");
			/*}else{
				out.print("Saved Successfully");
			}*/
			}else{
			out.print("Error");	
			}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  return null;
		
	}

	@SkipValidation
String formatUserMsg(String recordsId){
		
		String[] part2MsgArr=recordsId.split(",");
		
		if(part2MsgArr.length>11){
			recordsId="";
		 for(int y=0;y<10;y++){
			recordsId+=part2MsgArr[y]+",";
		 }
		 recordsId=recordsId+"...";
		}
		
		String msg=recordsId+"\n\r";
		
		return msg;
	}
	
	@SkipValidation
	public String deleteBusStop(){
		BusStopsDAO busstopsdao = new BusStopsDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		//BusStopsDAO busstopsdao = new BusStopsDAO();
		listPoint= busstopsdao.getPointList();
		//System.out.println("Bus stops Size------>"+request.getParameter("requestType")+"--------"+requestType);
		 String errmsg="";
		 PrintWriter out;
		 DependencyChecker dc=new DependencyChecker();
		 String msg="";
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		 session.getTransaction().setTimeout(200);
			session.getTransaction().begin();
			try {
				out = response.getWriter();
				
				routePointlist=busstopsdao.checkStopStatus(Integer.parseInt(request.getParameter("busid")),session);
				 grouplist=busstopsdao.checkStopingroup(Integer.parseInt(request.getParameter("busid")),session);
				
				/*if(!busstopsdao.checkStopStatus(Integer.parseInt(request.getParameter("busid")))){
					//addActionError("Bus Stop Code already exists");
					//errmsg=errmsg+"Bus Stop Already exist on given location<br/>";	
					for(int i-0;i<)
					addActionError("Busstop exist in active Route");
					return "success";
				}*/
				
				
				/*	String status=dc.getStatus(Integer.parseInt(request.getParameter("busid")), "bus_stop");
					System.out.println("status-/////////////--"+status);*/
				
				/*	if(status.equals("")){*/
				
				 if((routePointlist.size()>0) || (grouplist.size()>0 ))
				 {
						if(routePointlist.size()>0){
							String data="";
							for(int i=0;i<routePointlist.size();i++){
								data=data+routePointlist.get(i).getRoute().getRoute_id()+",";
							}
							data=data.substring(0, data.length()-1);
							//System.out.println(data);
							routelist=busstopsdao.getRouteNumber(data);
							String dataRoute="";
							if(routelist.size()>0){
							for(int i=0;i<routelist.size();i++){
								dataRoute=dataRoute+routelist.get(i).getRoute_number()+",";
							}
							dataRoute=dataRoute.substring(0, dataRoute.length()-1);
							System.out.println(dataRoute);
							//String msg=dc.formatUserMsg("Busstop exist in active",0,"Route No",dataRoute);
							String routenumber=formatUserMsg(dataRoute);
							
							msg="Busstop exist in active Route No."+routenumber+"\n\r";
							//out.print("");
							
							//return null;
							}
						}
						
						if(grouplist.size()>0)
						{
							String data1="";
							for(int i=0;i<grouplist.size();i++){
								data1=data1+grouplist.get(i).getGroup_id().getGroup_id()+",";
							}
							data1=data1.substring(0, data1.length()-1);
							List<BusStopGroup> busstopgrouplist=busstopsdao.getgroupname(data1);
							String groupid="";
							if(busstopgrouplist.size()>0)
							{
								for(int i=0;i<busstopgrouplist.size();i++){
									//String 
									groupid=groupid+busstopgrouplist.get(i).getGroup_id()+",";
								}
								groupid=groupid.substring(0,groupid.length()-1);
								//System.out.println(groupid);
								String groupiddetails=formatUserMsg(groupid);
								msg+="Bus stop exist in active Group No."+groupiddetails;
								
							}
						}
						
						out.println(msg);
				 }else{
		/*if(!busstopsdao.checkStopStatus(Integer.parseInt(request.getParameter("busid")))){
			//addActionError("Bus Stop Code already exists");
			//errmsg=errmsg+"Bus Stop Already exist on given location<br/>";	
			out.print("Busstop exist in active Route");
			return null;
		}*/
		busstopsdao.deleteBusStop(request.getParameter("requestType"),Integer.parseInt(request.getParameter("busid")));
		out.print("Busstop deleted Successfully");
				 }
		
				/*	}else{
						out.print(status);
					}*/
		
		
		return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//out.print("Error in Busstop delete");
				e.printStackTrace();
				return null;
			}
			finally{
				//session.getTransaction().commit();
			}
		
	}
	
	
	@SkipValidation
	public String deleteBusStopText(){
		BusStopsDAO busstopsdao = new BusStopsDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		//BusStopsDAO busstopsdao = new BusStopsDAO();
		listPoint= busstopsdao.getPointList();
		
		//System.out.println("Bus stops Size------>"+request.getParameter("requestType")+"--------"+requestType);
		 String errmsg="";
		 String msg1="";
		 String msg2="";
		 PrintWriter out;
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			try {
				out = response.getWriter();
				
				session.getTransaction().setTimeout(200);
				session.getTransaction().begin();
				routePointlist=busstopsdao.checkStopStatus(Integer.parseInt(request.getParameter("busid")),session);
				grouplist=busstopsdao.checkStopingroup(Integer.parseInt(request.getParameter("busid")),session);
		/*if(!busstopsdao.checkStopStatus(Integer.parseInt(request.getParameter("busid")))){
			//addActionError("Bus Stop Code already exists");
			//errmsg=errmsg+"Bus Stop Already exist on given location<br/>";	
			for(int i-0;i<)
			addActionError("Busstop exist in active Route");
			return "success";
		}*/
				/* DependencyChecker dc=new DependencyChecker();
					String status=dc.getStatus(Integer.parseInt(request.getParameter("busid")), "bus_stop");
					System.out.println("status--/////-"+status);
				
					if(status.equals("")){*/
				//System.out.println("routePointlist---//////////////---"+routePointlist);
				 if((routePointlist.size()>0) || (grouplist.size()>0 ))
				 {	
				if(routePointlist.size()>0){
					String data="";
					for(int i=0;i<routePointlist.size();i++){
						data=data+routePointlist.get(i).getRoute().getRoute_id()+",";
					}
					//System.out.println("data----1----"+data);
					data=data.substring(0, data.length()-1);
					//System.out.println("data----2----"+data);
					routelist=busstopsdao.getRouteNumber(data);
					if(routelist.size()>0){
					String dataRoute="";
					for(int i=0;i<routelist.size();i++){
						dataRoute=dataRoute+routelist.get(i).getRoute_number()+",";
					}
					//System.out.println("dataRoute----1-----"+dataRoute);
					dataRoute=dataRoute.substring(0, dataRoute.length()-1);
					//System.out.println("dataRoute----2-----"+dataRoute);
					String msg=formatUserMsg(dataRoute);
					//addActionError("Busstop exist in active Route No."+msg);
					//msg1="Busstop exist in active Route No."+msg+"\\n\\r";
					
					msg2="Busstop exist in active Route No."+msg;
					//return "success";
					}
				}
				
				 grouplist=busstopsdao.checkStopingroup(Integer.parseInt(request.getParameter("busid")),session);
				
				if(grouplist.size()>0)
				{
					String data1="";
					for(int i=0;i<grouplist.size();i++){
						data1=data1+grouplist.get(i).getGroup_id().getGroup_id()+",";
					}
					data1=data1.substring(0, data1.length()-1);
					List<BusStopGroup> busstopgrouplist=busstopsdao.getgroupname(data1);
					String groupid="";
					if(busstopgrouplist.size()>0)
					{
						for(int i=0;i<busstopgrouplist.size();i++){
							//String 
							groupid=groupid+busstopgrouplist.get(i).getGroup_id()+",";
						}
						groupid=groupid.substring(0,groupid.length()-1);
						//System.out.println(groupid);
						String groupiddetails=formatUserMsg(groupid);
						msg1="Bus stop exist in active Group No."+groupiddetails;
						
					}
				}
				
				//out.println(msg);
				addActionError(msg1);
				addActionError(msg2);
				 }else{
		busstopsdao.deleteBusStop(request.getParameter("requestType"),Integer.parseInt(request.getParameter("busid")));
		//out.print("Busstop deleted Successfully");
		//addActionError("Bus Stop Name is required");
		addActionError("Busstop deleted Successfully");
					/*}else{
						addActionError(status);	
					}*/
				 }
		return "success";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//out.print("Error in Busstop delete");
				e.printStackTrace();
				return "success";
			}
			finally{
				//session.getTransaction().close();
			}
		
	}
	
	 String al ="";
	@SkipValidation
	
	public String getBusStop() throws UnsupportedEncodingException{
		//System.out.println("in get bus stop0000");
		BusStopsDAO busstopsdao = new BusStopsDAO();
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			
			int usractid=(Integer)request.getSession().getAttribute("userid");

		//System.out.println("request.getParameter is============="+request.getParameter("stop_type"));
		//String uniqueval=request.getParameter("idvald");
		//String[] array = uniqueval.split(",");
		double para=0.02;
		double factor=1.2;
		request.getSession().setAttribute("stop_type", request.getParameter("stop_type"));
		 list = busstopsdao.getBusStopList( request.getParameter("lat"), request.getParameter("lng"),request.getParameter("idvald"), para);
		// System.out.println("list.size() before"+list.size());
		 for(int j=0; j<10;j++){
			 list = busstopsdao.getBusStopList( request.getParameter("lat"), request.getParameter("lng"),request.getParameter("idvald"), para);
			// System.out.println("list.size() after div"+list.size());
			 if((list.size()>110) && (list.size()<200)){
				 break;
			 }
			 if(list.size()<175){
				 //factor=factor
				 para=para*factor ;
			 }
			 if(list.size()>175){
				 para=para/factor ;
			 }
		 }
		 /*if(list.size()<175){
			 para=para*2;
			 list = busstopsdao.getBusStopList( request.getParameter("lat"), request.getParameter("lng"),request.getParameter("idvald"), para);
			 System.out.println("list.size() after mul"+list.size());
		 }
		 if(list.size()>175){
			 para=para/2;
			 list = busstopsdao.getBusStopList( request.getParameter("lat"), request.getParameter("lng"),request.getParameter("idvald"), para);
			 System.out.println("list.size() after div"+list.size());
		 }*/
		 
		 
		 String busDet="";
		 int dragflag=1;
		    String userid=busstopsdao.getDragAuthUsers();
		    String [] userids=userid.split(",");
		    String roleid=(String)request.getSession().getAttribute("roleid");
		    //System.out.println("roleid=========>"+roleid+"======>"+userid);
		    for(int k=0;k<userids.length;k++){
		    	if(roleid.equals(userids[k])){
		    		dragflag=0;
		    		//System.out.println("hiii");
		    	}
		    	
		    }
		   // System.out.println("dragflag==========>"+dragflag);
		 for(int i=0; i<list.size(); i++){
			 BusStops obj = list.get(i);
			// al.add(obj.getId());
			 //System.out.println(obj.getBusStopName());
			 
			 busDet=busDet+obj.getBusStopName()
					 +"|"+obj.getLatitude()+"|"
					 +obj.getLongitude()+"|"
					 +obj.getId()+"|"
					 +obj.getDeviceCode()
//					  +"|"+obj.getBus_stop_name_nudi()
					 +"|"+obj.getBusStopNameKannada()
					 +"|"+obj.getDescription()
					 +"|"+obj.getStatus()+"|" 
					 +obj.getLocality()+"|"
					 +obj.getBmtcStatus()
					 +"|"+obj.getStop_direction()
					 +"|"+obj.getLandmark()+"|"
					 +obj.getAreaPopulation()+"|"
					 +obj.getTollZone()+"|"
					 +obj.getPointtype().getPoint_type_id()
					 +"|"+obj.getFareStage()+"|"
					 +obj.getBus_stations().getOrg_chart_id()+"|"+obj.getSub_stage()+"|"+obj.getPointtype().getOrg_type_id()+"|"+dragflag+"@@@";
			// System.out.println("size of array is"+al.size());
		 }
		 HttpServletResponse response=ServletActionContext.getResponse();
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list.size()+busDet);
			  out.print(busDet);
			  out.print(al);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	
	@SkipValidation
	public String getBusStopLocate() throws UnsupportedEncodingException{
		//System.out.println("in get bus stoplocate");
		BusStopsDAO busstopsdao = new BusStopsDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
	    int idBusStopName = Integer.parseInt(request.getParameter("id"));
	    request.getSession().setAttribute("stop_type", request.getParameter("stop_type"));
	    int dragflag=1;
	    String userid=busstopsdao.getDragAuthUsers();
	    String [] userids=userid.split(",");
	    String roleid=(String)request.getSession().getAttribute("roleid");
	    //System.out.println("roleid=========>"+roleid+"======>"+userid);
	    for(int k=0;k<userids.length;k++){
	    	if(roleid.equals(userids[k])){
	    		dragflag=0;
	    		//System.out.println("hiii");
	    	}
	    	
	    }
	   // System.out.println("dragflag==========>"+dragflag);
		 /*int dragflagres=busstopsdao.getStopPointData(Integer.parseInt(request.getParameter("id")));*/
		 /*if(dragflagres>0){
			 dragflag=1;
		 }else{
			 dragflag=0;
		 }*/
	    //System.out.println("idBusStopName========"+idBusStopName);
		 list = busstopsdao.getBusStopList(idBusStopName);
		 String busDet="";
		 for(int i=0; i<list.size(); i++){
			 BusStops obj = list.get(i);
			 //System.out.println(obj.getBusStopName());
			 busDet=busDet+obj.getBusStopName()+"|"+obj.getLatitude()+"|"+obj.getLongitude()+"|"+obj.getId()+"|"+obj.getDeviceCode()+"|"+obj.getBusStopNameKannada()+"|"+obj.getDescription()+" |"+obj.getStatus()+"|"+obj.getLocality()+"|"+obj.getBmtcStatus()+"|"+obj.getStop_direction()+"|"+obj.getLandmark()+"|"+obj.getAreaPopulation()+"|"+obj.getTollZone()+"|"+obj.getPointtype().getPoint_type_id()+"|"+obj.getFareStage()+"|"+obj.getBus_stations().getOrg_chart_id()+"|"+obj.getSub_stage()+"|"+obj.getPointtype().getOrg_type_id()+"|"+dragflag+"@@@";
			 //System.out.println(obj.getBmtcStatus());
			// if((Double)request.getSession().getAttribute("centerlate")!= null){
				 request.getSession().setAttribute("centerlat", (Double)obj.getLatitude());	 
			// }
			// if((Double)request.getSession().getAttribute("centerlange")!= null){
				 request.getSession().setAttribute("centerlang",(Double) obj.getLongitude());	 
			// }
		 }
		 
		 
		 HttpServletResponse response=ServletActionContext.getResponse();
		 
		
		
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list.size()+busDet);
			  out.print(busDet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	
	
	
	@SkipValidation
	public String getBusStopDrop(){
		//System.out.println("in get bus stop111111");
		BusStopsDAO busstopsdao = new BusStopsDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
	    String idBusStopName = request.getParameter("id");
	    request.getSession().setAttribute("stop_type", request.getParameter("stop_type"));
//		 list = busstopsdao.getBusStopDropList();
//		 String busDet="";
		 List<BusStops> list = busstopsdao.getBusStopDropList(idBusStopName);
			//System.out.println("Size --->"+list.size());
			//int i=1;
			List<BusstopJson> list1 = new ArrayList<BusstopJson>();
			for(BusStops bustop : list){
				BusstopJson bus = new BusstopJson();
				//bus.setId(bustop.getId());
				//bus.setValue("a"+i);
				bus.setId(bustop.getId());
				bus.setValue(bustop.getBusStopName()+" "+bustop.getAlias1()+" "+bustop.getAlias2()+" "+bustop.getAlias3()+" "+bustop.getAlias4()+" "+bustop.getAlias5()+" "+bustop.getAlias6());
				bus.setBusStopName(bustop.getBusStopName());
				bus.setLongitude(bustop.getLongitude());
				bus.setLatitude(bustop.getLatitude());
				list1.add(bus);
				//i+=1;
				//System.out.println("Bus stops Size------>"+bus.getBusStopName());
			}

		 HttpServletResponse response=ServletActionContext.getResponse();
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list1.size());
			  out.print(new JSONArray(list1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	@SkipValidation
	public String getRouteStopDrop(){
		//System.out.println("in get bus stop2222");
		BusStopsDAO busstopsdao = new BusStopsDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
	    String idBusStopName = request.getParameter("id");
	    request.getSession().setAttribute("stop_type", request.getParameter("stop_type"));
//		 list = busstopsdao.getBusStopDropList();
//		 String busDet="";
		 List<BusStops> list = busstopsdao.getRouteStopDropList(idBusStopName);
			//System.out.println("Size --->"+list.size());
			//int i=1;
			List<BusstopJson> list1 = new ArrayList<BusstopJson>();
			for(BusStops bustop : list){
				BusstopJson bus = new BusstopJson();
				//bus.setId(bustop.getId());
				//bus.setValue("a"+i);
				bus.setId(bustop.getId());
				bus.setValue(bustop.getBusStopName()+" "+bustop.getAlias1()+" "+bustop.getAlias2()+" "+bustop.getAlias3()+" "+bustop.getAlias4()+" "+bustop.getAlias5()+" "+bustop.getAlias6());
				if(bustop.getStop_direction() == null || bustop.getStop_direction().isEmpty()){
					bustop.setStop_direction("");
				}
				bus.setBusStopName(bustop.getBusStopName()+"(Towards: "+bustop.getStop_direction()+")");
				bus.setLongitude(bustop.getLongitude());
				bus.setLatitude(bustop.getLatitude());
				list1.add(bus);
				//i+=1;
			}

		 HttpServletResponse response=ServletActionContext.getResponse();
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list1.size());
			  out.print(new JSONArray(list1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	@SkipValidation
	public String getNudiName(){
		List<String> busstoplist = null;
		String name="";
		Session session = null;
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		try{
			String id=request.getParameter("id");
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select bus_stop_name_nudi as busStopNamenudi from bus_stop where status='Approved' and bus_stop_id='"+id+"'";
         Query query = session.createSQLQuery(sql)
//				.addScalar("id", Hibernate.INTEGER)
				.addScalar("busStopNamenudi", Hibernate.STRING);
         query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		busstoplist = new ArrayList<String>();
		List<Object> det = query.list();
		

		for (int i = 0; i < det.size(); i++) {
               
			name = det.get(i).toString();
			busstoplist.add(name);
		}
		PrintWriter out;
		
			out = response.getWriter();
			
			
//			  out.print(busstoplist.toString());
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			
		}
		
		return name;
	}
	
	public List<BusStops> getBusidlistdata(int id) {
		List<BusStops> busstoplist = null;
		Map<String,BusStops> data=null;
		HttpServletRequest request=ServletActionContext.getRequest();

		Session session = null;
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select bus_stop_name_nudi as busStopNamenudi from bus_stop where status='Approved' and bus_stop_id='"+id+"'";
         Query query = session.createSQLQuery(sql)
				
				.addScalar("busStopNamenudi", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(BusStops.class));
		busstoplist = new ArrayList<BusStops>();
		data=new HashMap<String, BusStops>();
		List<Object> det = query.list();
//		BusStops initialDevice = new BusStops();
//		initialDevice.setBusStopNamenudi("Select");
//		initialDevice.setId(0);
//		busstoplist.add(initialDevice);

		for (int i = 0; i < det.size(); i++) {

			BusStops device = (BusStops) det.get(i);
			request.getSession().setAttribute("name", device.getBusStopNamenudi());
			busstoplist.add(device);
			data.put("name", device);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			return busstoplist;
		}
	}
	
	
	
	
	@SkipValidation
	public String getBusStopDetail() throws UnsupportedEncodingException, JSONException{
		//System.out.println("in get bus stop111");
		BusStopsDAO busstopsdao = new BusStopsDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
	    int idBusStop = Integer.parseInt(request.getParameter("id"));
	   // int routecnt=0;
	   // System.out.println("id is++++++++++++++++"+idBusStop);
//	    this.setBusstoplist(getBusidlistdata(idBusStop));
		try {
	    this.setBusstoplist(getBusidlistdata(idBusStop));
	    BusStops bs = busstopsdao.getBusStops(idBusStop);
		 String busDet="";
		 String busData="";
		 HttpServletResponse response=ServletActionContext.getResponse();
		// routecnt=busstopsdao.getTollRouteData(Integer.parseInt(request.getParameter("id")));
		 busDet=bs.getBusStopName()+"|"+bs.getBusStopNameKannada()+"|"+bs.getLandmark()+"|"+
		bs.getLatitude()+"|"+bs.getLongitude()+"|"+bs.getSheltered()+"|"+bs.getSpeed()+"|"+bs.getAccuracy()+
		"|"+bs.getAltitude()+" |"+bs.getBearing()+"|"+bs.getDeviceCode()+"|"+bs.getLocality()+"|"+bs.getAlias1()+
		"|"+bs.getAlias2()+"|"+bs.getAlias3()+"|"+bs.getAlias4()+"|"+bs.getTollZone()+"|"+bs.getCode1()+"|"
		+bs.getCode2()+"|"+bs.getCityLimit()+"|"+bs.getWardNumber()+"|"+bs.getAreaPopulation()+"|"+bs.getStopSize()+
		"|"+bs.getFareStage()+"|"+bs.getDescription()+"|"+bs.getBmtcStatus()+"|"+bs.getKalias1()+"|"+bs.getK_alias_2()+
		"|"+bs.getId()+"|"+bs.getStatus()+"|"+bs.getStop_direction()+"|"+bs.getAlias5()+"|"+bs.getAlias6()+"|"+
		bs.getBus_stop_code()+"|"+bs.getBus_stop_code_kannada()+"|"+bs.getPointtype().getPoint_type_id()+"|"+
		bs.getBus_stations().getOrg_chart_id()+"|"+bs.getSub_stage()+"|"+bs.getPointtype().getOrg_type_id()+"|"+bs.getToll_fee()
		+"|"+bs.getK_alias_3()+"|"+bs.getK_alias_4()+"|"+bs.getK_alias_5()+"|"+bs.getK_alias_6()+"|"+bs.getBus_stop_name_nudi();
		
//		 JSONObject json=new JSONObject(busDet);
//		System.out.println("bs.getBus_stop_name_nudi()==="+bs.getBus_stop_name_nudi());
//		System.out.println("busDet=="+busDet);
//		//String[] strings = ((BusStops)bs);
//		String nudi=bs.getBus_stop_name_nudi().toString();
//		bs.setBus_stop_name_nudi(nudi);
//		request.setAttribute("nudi_name", nudi);
//		request.getSession().removeAttribute("nudi_name");
//		request.getSession().removeAttribute(nudi);
//		request.getSession().setAttribute("nudi_name", nudi);
		
		PrintWriter out;
	
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list.size()+busDet);
			  out.print(busDet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		return null;
	}
	
	@SkipValidation
	public String showMap(){
		BusStopsDAO busstopsdao = new BusStopsDAO();
		listPoint= busstopsdao.getPointList();
		listOrgcharts=busstopsdao.getOrgChart();
		return "success";
	}
	
	@SkipValidation
	public String addStop(){
		BusStopsDAO busstopsdao = new BusStopsDAO();
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			bustops.setCreatedBy(usrsessionid);
			bustops.setUpdated_by(usrsessionid);
			//bustops.setStatus("Manual");
			String errmsg="";
			CommonValidation commvalidate = new CommonValidation();
			HttpServletResponse response=ServletActionContext.getResponse();

			if(commvalidate.isEmpty(bustops.getBusStopName())){
				//addActionError("Bus Stop Name is required");
				//System.out.println("Bus Stop Name is required");
				errmsg=errmsg+"Bus Stop Name is required<br/>";
				//return null;
			}
			if(commvalidate.isEmpty(bustops.getBus_stop_code())){
				//addActionError("Bus Stop Code is required");
				errmsg=errmsg+"Bus Stop Code is required<br/>";
			}
			if(!busstopsdao.checkBusstopCode(bustops.getBus_stop_code().trim(),bustops.getId())){
				//addActionError("Bus Stop Code already exists");
				errmsg=errmsg+"Duplicate bus stop Code<br/>";	
			}
			if(!busstopsdao.checkCoordinate(String.valueOf(bustops.getLatitude()),String.valueOf(bustops.getLongitude()))){
				//addActionError("Bus Stop Code already exists");
				errmsg=errmsg+"Bus Stop Already exist on given location<br/>";	
			}
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			try {
				PrintWriter out;
				out = response.getWriter();
			if(errmsg!=""){
				
				
				out.print(errmsg);
			}else{
				orgchart = new OrganisationChart();
				//int valofid=bustops.getPointtype().getPoint_type_id();
				/*if(valofid==6){
					//System.out.println("request.getParameter"+request.getParameter("bustops.bus_stations.org_chart_id"));
					orgchart.setOrg_chart_id(Integer.parseInt(request.getParameter("bustops.bus_stations.org_chart_id")));
				} else{
					orgchart.setOrg_chart_id(-1);
				}*/
				//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa"+request.getParameter("bustops.bus_stations.org_chart_id"));
				orgchart.setOrg_chart_id(Integer.parseInt(request.getParameter("bustops.bus_stations.org_chart_id")));
				bustops.setBus_stations(orgchart);
				session.beginTransaction();
				String res=busstopsdao.addStop(bustops,session);
				session.getTransaction().commit();
				if(res=="success"){
					BusStopsGroupDAO busStopsGroupDAO = new BusStopsGroupDAO();
					busStopGroup = new BusStopGroup();
					BusStops busstops=new BusStops();
					busstops.setId(bustops.getId());
					busStopGroup.setGroup_stop(busstops);
					GroupType groupType=new GroupType();
					groupType.setGroup_type_id(2);
					busStopGroup.setGroup_type_id(groupType);
					busStopGroup.setBus_stop_count(1);
					busStopGroup.setStatus("Active");
					busStopGroup.setDeleted_status(0);
					busStopGroup.setInserted_date(new Date());
					busStopGroup.setInserted_by(usrsessionid);
					busStopGroup.setGroup_name("");
					
					session.beginTransaction();
					busStopsGroupDAO.addBus_stop_group(busStopGroup, session);
					
					busStopGroupStop=new BusStopGroupStop();
					busStopGroupStop.setGroup_id(busStopGroup);
					busstops=new BusStops();
					busstops.setId(bustops.getId());
					busStopGroupStop.setBus_stop_group_stop(busstops);
					busStopGroupStop.setStatus("Y");
					busStopGroupStop.setCreated_by(usrsessionid);
					busStopGroupStop.setCreated_date(new Date());
					busStopsGroupDAO.addBus_stop_group_stop(busStopGroupStop, session);
					session.getTransaction().commit();
					
					  request=ServletActionContext.getRequest();
					
				out.print("Saved Successfully");
				}else{
				out.print("Error");	
				}
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			  finally{
				  session.close();
			  }
			  return null;
		//return  busstopsdao.addStop(bustops);
	}
	
	
	@SkipValidation
	public String getRouteBusStop()  {
		//System.out.println("in get bus stop3333");
		BusStopsDAO busstopsdao = new BusStopsDAO();
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			
			int usractid=(Integer)request.getSession().getAttribute("userid");
			double para=0.02;
			double factor=1.2;

		//System.out.println("userid is============="+usractid);
		// list = busstopsdao.getBusStopRouteList( request.getParameter("lat"), request.getParameter("lng"),para);
		 
		 for(int j=0; j<10;j++){
			 list = busstopsdao.getBusStopRouteList( "12.97928309", "77.57205963",para);
			// System.out.println("list.size() after div"+list.size());
			 if((list.size()>110) && (list.size()<200)){
				 break;
			 }
			 if(list.size()<175){
				 //factor=factor
				 para=para*factor ;
			 }
			 if(list.size()>175){
				 para=para/factor ;
			 }
		 }
		 
		 String busDet="";
		 for(int i=0; i<list.size(); i++){
			 BusStops obj = list.get(i);
			 //System.out.println(obj.getBusStopName());
			 busDet=busDet+obj.getBusStopName()+"|"+obj.getLatitude()+"|"+obj.getLongitude()+"|"+obj.getId()+"|"+obj.getDeviceCode()+"|"+obj.getBusStopNameKannada()+"|"+obj.getDescription()+"|"+obj.getStatus()+"|"+obj.getLocality()+"|"+obj.getBmtcStatus()+"|"+obj.getStop_direction()+"|"+obj.getLandmark()+"|"+obj.getAreaPopulation()+"|"+obj.getTollZone()+"|"+obj.getPointtype().getPoint_type_id()+"|"+obj.getFareStage()+"|"+obj.getBus_stations().getOrg_chart_id()+"|"+obj.getSub_stage()+"|"+obj.getPointtype().getOrg_type_id()+"@@@";
			 //System.out.println(obj.getBmtcStatus());
		 }
		 HttpServletResponse response=ServletActionContext.getResponse();
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list.size()+busDet);
			  out.print(busDet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	
	public void validate(){
		CommonValidation commvalidate = new CommonValidation();
		BusStopsDAO busstopsdao = new BusStopsDAO();
		if(commvalidate.isEmpty(bustops.getBusStopName())){
			addActionError("Bus Stop Name is required");
		}
		if(commvalidate.isEmpty(bustops.getBus_stop_code())){
			addActionError("Bus Stop Code is required");
		}
		if(!busstopsdao.checkBusstopCode(bustops.getBus_stop_code().trim(),bustops.getId())){
			addActionError("Bus Stop Code already exists");
			
		}
	}
	
@SkipValidation
	
	public String getBusStopRoute() throws UnsupportedEncodingException{
	//	System.out.println("in get bus stop44444");
		BusStopsDAO busstopsdao = new BusStopsDAO();
		 HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			
			int usractid=(Integer)request.getSession().getAttribute("userid");

		//System.out.println("request.getParameter is============="+request.getParameter("idvald"));
		double para=0.02;
		double factor=1.2;
		//String uniqueval=request.getParameter("idvald");
		//String[] array = uniqueval.split(",");
		// list = busstopsdao.getBusStopRouteList( request.getParameter("lat"), request.getParameter("lng"),request.getParameter("idvald"),para);
		 for(int j=0; j<10;j++){
			 list = busstopsdao.getBusStopRouteList( request.getParameter("lat"), request.getParameter("lng"),request.getParameter("idvald"),para);
			// System.out.println("list.size() after div"+list.size());
			 if((list.size()>110) && (list.size()<200)){
				 break;
			 }
			 if(list.size()<175){
				 //factor=factor
				 para=para*factor ;
			 }
			 if(list.size()>175){
				 para=para/factor ;
			 }
		 }
		 
		 
		 String busDet="";
		 for(int i=0; i<list.size(); i++){
			 BusStops obj = list.get(i);
			// al.add(obj.getId());
			 //System.out.println(obj.getBusStopName());
			 busDet=busDet+obj.getBusStopName()+"|"+obj.getLatitude()+"|"+obj.getLongitude()+"|"+obj.getId()+"|"+obj.getDeviceCode()+"|"+obj.getBusStopNameKannada()+"|"+obj.getDescription()+"|"+obj.getStatus()+"|"+obj.getLocality()+"|"+obj.getBmtcStatus()+"|"+obj.getStop_direction()+"|"+obj.getLandmark()+"|"+obj.getAreaPopulation()+"|"+obj.getTollZone()+"|"+obj.getPointtype().getPoint_type_id()+"|"+obj.getFareStage()+"|"+obj.getBus_stations().getOrg_chart_id()+"|"+obj.getSub_stage()+"|"+obj.getPointtype().getOrg_type_id()+"@@@";
			// System.out.println("size of array is"+al.size());
		 }
		 HttpServletResponse response=ServletActionContext.getResponse();
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list.size()+busDet);
			  out.print(busDet);
			  out.print(al);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		return null;
	
	}


@SkipValidation

public String getBusStopReloadRoute() throws UnsupportedEncodingException{
//	System.out.println("in get bus stop44444");
	BusStopsDAO busstopsdao = new BusStopsDAO();
	 HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		
		int usractid=(Integer)request.getSession().getAttribute("userid");

	//System.out.println("request.getParameter is============="+request.getParameter("idvald"));
	double para=0.02;
	double factor=1.2;
	//String uniqueval=request.getParameter("idvald");
	//String[] array = uniqueval.split(",");
	// list = busstopsdao.getBusStopRouteList( request.getParameter("lat"), request.getParameter("lng"),request.getParameter("idvald"),para);
	// for(int j=0; j<10;j++){
		 list = busstopsdao.getBusStopReloadRouteList( request.getParameter("idvald"));
		// System.out.println("list.size() after div"+list.size());
		 /*if((list.size()>110) && (list.size()<200)){
			 break;
		 }
		 if(list.size()<175){
			 //factor=factor
			 para=para*factor ;
		 }
		 if(list.size()>175){
			 para=para/factor ;
		 }*/
	// }
	 
	 
	 String busDet="";
	 for(int i=0; i<list.size(); i++){
		 BusStops obj = list.get(i);
		// al.add(obj.getId());
		 //System.out.println(obj.getBusStopName());
		 busDet=busDet+obj.getBusStopName()
				 +"|"+obj.getLatitude()
				 +"|"+obj.getLongitude()
				 +"|"+obj.getId()
				 +"|"+obj.getDeviceCode()
				 +"|"+obj.getBusStopNameKannada()
				 +"|"+obj.getDescription()
				 +"|"+obj.getStatus()
				 +"|"+obj.getLocality()
				 +"|"+obj.getBmtcStatus()
				 +"|"+obj.getStop_direction()
				 +"|"+obj.getLandmark()
				 +"|"+obj.getAreaPopulation()
				 +"|"+obj.getTollZone()
				 +"|"+obj.getPointtype().getPoint_type_id()
				 +"|"+obj.getFareStage()
				 +"|"+obj.getBus_stations().getOrg_chart_id()
				 +"|"+obj.getSub_stage()
				 +"|"+obj.getPointtype().getOrg_type_id()+"@@@";
		// System.out.println("size of array is"+al.size());
	 }
	 HttpServletResponse response=ServletActionContext.getResponse();
	//System.out.println("Bus stops Size------>"+list.size()+busDet);
	PrintWriter out;
	try {
		out = response.getWriter();
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		  out.print(busDet);
		  out.print(al);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 

	return null;

}

@SkipValidation
	public String setSession(){
			HttpServletRequest request=ServletActionContext.getRequest();
			//System.out.println("session uid is"+request.getParameter("id"));
		// request.getSession().setAttribute("stop_type", request.getParameter("id"));
		return null;
	}


	@SkipValidation
	public String getOrgList(){
		BusStopsDAO busstopsdao = new BusStopsDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		listPoint= busstopsdao.getPointList(id);
		listOrgcharts=busstopsdao.getOrgChart(listPoint.get(0).getOrg_type_id());
		HttpServletResponse response=ServletActionContext.getResponse();
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		PrintWriter out;
		String datastr="";
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list.size()+busDet);
			if(listOrgcharts.size()!=0){
				datastr = datastr+ "<select class='form-control input-sm' id='org_chart_id' name='bustops.bus_stations.org_chart_id'>";
				datastr = datastr+ "<option value='-1'>Select</option>";
						
							
				for(int i=0;i<listOrgcharts.size();i++){
					datastr=datastr+"<option value='"+listOrgcharts.get(i).getOrg_chart_id()+"'>"+listOrgcharts.get(i).getOrg_name()+"</option>";
				}
				datastr = datastr+ "</select>";
				
			}else{
				datastr = datastr+ "<select class='form-control input-sm' id='org_chart_id' name='bustops.bus_stations.org_chart_id'>";
				datastr = datastr+ "<option value='-1'>Select</option>";
				datastr = datastr+ "</select>";
			}
			  out.print(datastr);
			 // out.print(al);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@SkipValidation
	public String getRouteDropdownList1() {
		// get Depot List..
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			RouteDAO routedao = new RouteDAO();
			String routename=(req.getParameter("routename"));
			List<Route> listroute = routedao.getRouteListDropDown1(routename);
			/*
			
			List<Route> list1 = new ArrayList<Route>();
			for(Route route : listroute){
				Route route1 = new Route();
				route1.setRoute_number(route.getRoute_number() +" "+ route.getRoute_direction());
				route1.setValue(route.getRoute_number());
				//setValue
				route1.setRoute_name(route.getRoute_id() +"-"+route.getStart_stop().getId()+"-"+route.getEnd_stop().getId());
				list1.add(route1);
				
			}*/
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			out = response.getWriter();
			out.print(new JSONArray(listroute));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

//	@SkipValidation
//	public String getTripDropdownList1() {
//		// get Depot List..
//		try {
//			HttpServletRequest req = ServletActionContext.getRequest();
//			RouteDAO routedao = new RouteDAO();
//			String routename=(req.getParameter("routename"));
		//	List<BusStops> listroute = routedao.tripPlannerDownList1(routename);
			/*
			
			List<Route> list1 = new ArrayList<Route>();
			for(Route route : listroute){
				Route route1 = new Route();
				route1.setRoute_number(route.getRoute_number() +" "+ route.getRoute_direction());
				route1.setValue(route.getRoute_number());
				//setValue
				route1.setRoute_name(route.getRoute_id() +"-"+route.getStart_stop().getId()+"-"+route.getEnd_stop().getId());
				list1.add(route1);
				
			}*/
			//System.out.println("listroute.size()"+listroute.size());
//			String jsonArray = "[";
//			String jsonArray1 = "";
			//for(int i=0; i<listroute.size(); i++){
				//System.out.println("listroute.get(i).getBus_stop_code()"+listroute.get(i).getBus_stop_code());
//				if(i<10){
//				if(i == 9){
//					jsonArray += "{\"busStopCode\" : \"" + listroute.get(i).getBus_stop_code() + "\", \"stopName\" : \"" + listroute.get(i).getBusStopName() + "\"}";
//				}
//				else{
				//	jsonArray1 += "{\"busStopCode\" : \"" + listroute.get(i).getBus_stop_code() + "\", \"stopName\" : \"" + listroute.get(i).getBusStopName() + "\"},";
//				}
//				}
			//}
			//jsonArray += jsonArray1.substring(0,jsonArray1.length()-1)+"]";
			//System.out.println("regionTypeAjaxString" + new JSONArray(listroute));
//			HttpServletResponse response = ServletActionContext.getResponse();
//			PrintWriter out;
//			out = response.getWriter();
//			//System.out.println("jsonArray : " + jsonArray.substring(0,jsonArray.length()-1));
//			//out.print(new JSONArray(listroute));
//			out.print("[{'route_number':'RICHMOND','route_name':'1234','value':'313-K'}]");
////		} catch (IOException e) {
////			e.printStackTrace();
////		} 
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//
//	}
}
