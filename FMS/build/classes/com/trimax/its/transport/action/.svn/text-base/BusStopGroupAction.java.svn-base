package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.RoutePoint;
import com.trimax.its.transport.model.BusStopGroupStop;
import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.transport.dao.BusStopsGroupDAO;
import com.trimax.its.transport.model.BusStopGroup;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.GroupType;
import com.trimax.its.transport.model.PointType;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.util.HibernateUtil;

public class BusStopGroupAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	List<GroupType> listGroupType;
	List<BusStopGroup> listBusStopGroup;
	List<BusStopGroup> listGroupName;
//	public List<BusStopGroup> getListGroupName() {
//		return listGroupName;
//	}
//
//	public void setListGroupName(List<BusStopGroup> listGroupName) {
//		this.listGroupName = listGroupName;
//	}

	List<BusStopGroupStop> listBusStopGroupStop;
	List<ScheduleDetails> listScheduleDetails;
	private BusStopGroup busStopGroup;
	private GroupType groupType;
	private BusStopGroupStop busStopGroupStop;
	private BusStops busstops;
	public List<GroupType> getListGroupType() {
		return listGroupType;
	}

	public void setListGroupType(List<GroupType> listGroupType) {
		this.listGroupType = listGroupType;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	public String execute(){
		return "success";
	}
	
	public String getCreateMap(){
		BusStopsGroupDAO busstopgroupdao = new BusStopsGroupDAO();
		listGroupType=busstopgroupdao.getGroupTypeList();
		
		return "success";
	}
	
	public String getEditMap(){
		BusStopsGroupDAO busstopgroupdao = new BusStopsGroupDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
	    int id=Integer.parseInt(request.getParameter("busstopgroupid"));
	    request.getSession().setAttribute("busstopgroupid",id);
		listGroupType=busstopgroupdao.getGroupTypeList();
		return "success";
	}
	
	public String getBusStopGroupEditdata(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		PrintWriter out;
		int groupuptypeid=0;
	    int id=(Integer) request.getSession().getAttribute("busstopgroupid");
	    BusStopsGroupDAO busstopgroupdao = new BusStopsGroupDAO();
	    listBusStopGroup=busstopgroupdao.getBusStopGroupInfo(id);
	    listBusStopGroupStop=busstopgroupdao.getBusStopGroupStopInfo(id);
	    JSONArray array = new JSONArray();
	    JSONArray array1 = new JSONArray();
	    JSONObject result = new JSONObject();
	    try{
	    	for(int i=0;i<listBusStopGroup.size();i++){
				JSONArray jo = new JSONArray();
					jo.add( listBusStopGroup.get(i).getGroup_id());
					jo.add( listBusStopGroup.get(i).getGroup_stop().getId());
					jo.add( listBusStopGroup.get(i).getGroup_stop().getBusStopName());
					jo.add( listBusStopGroup.get(i).getGroup_type_id().getGroup_type_id());
					groupuptypeid=listBusStopGroup.get(i).getGroup_type_id().getGroup_type_id();
					jo.add( listBusStopGroup.get(i).getGroup_type_id().getGroup_type_name());
					jo.add( listBusStopGroup.get(i).getGroup_name());
					jo.add( listBusStopGroup.get(i).getGroup_stop().getLatitude());
					jo.add( listBusStopGroup.get(i).getGroup_stop().getLongitude());
					array.add(jo);
	    		}
			result.put("busstopgroupdata",array);
			result.put("busstopgroupsize",listBusStopGroup.size());
			
			for(int i=0;i<listBusStopGroupStop.size();i++){
				//JSONObject map = new JSONObject();	
				JSONArray map = new JSONArray();
				listScheduleDetails=busstopgroupdao.getScheduleDetails(listBusStopGroup.get(0).getGroup_stop().getId());
				map.add( listBusStopGroupStop.get(i).getBus_stop_group_stop().getId());
				map.add( listBusStopGroupStop.get(i).getBus_stop_group_stop().getBusStopName()+"( Towards:"+listBusStopGroupStop.get(i).getBus_stop_group_stop().getStop_direction()+")");
				try{
				for(int j=0;j<listScheduleDetails.size();j++){
					if(listBusStopGroupStop.get(i).getBus_stop_group_stop().getId()==listScheduleDetails.get(j).getStartPoint()){
						if(groupuptypeid!=1){
						map.add( "N");
						}
						break;
					}
				}
				}catch(Exception ex){
					
				}
				//map.add( listBusStopGroupStop.get(i).getBus_stop_group_stop().getStop_direction());
				array1.add(map);

		}
			result.put("busstopgroupStopsize",listBusStopGroupStop.size());
			result.put("busstopgroupStopdata",array1);
			
			HttpServletResponse response=ServletActionContext.getResponse();
			out = response.getWriter();
			out.print(result);
			 
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
		return null;
	}
	
	public String getViewData() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BusStopsGroupDAO busstopgroupdao = new BusStopsGroupDAO();
		int cnt = busstopgroupdao.getTotalRecords(request.getParameter("sSearch"));
		String[] cols = {"Group Id", "Group Id", "Bus Stop Name", "Group Type", "Group Name", "No. Of Bus Stops"};
		
		String[] dbcol={"group_id","group_id","group_stop.busStopName","group_type_id.group_type_name","group_name","bus_stop_count"};
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		
		if (sStart != null) {
			start = Integer.parseInt(sStart);
			if (start < 0) {
			start = 0;
			}
			}
			if (sAmount != null) {
			amount = Integer.parseInt(sAmount);
			if (amount < 10 || amount > 50) {
			amount = 10;
			}
			}
			if (sCol != null) {
			col = Integer.parseInt(sCol);
			if (col < 0 || col > 5)
			col = 0;
			}
			if (sdir != null) {
			if (!sdir.equals("asc"))
			dir = "desc";
			}
			
			String colName = cols[col];
			int total = -1;
			total = busstopgroupdao.getTotalRecords(request.getParameter("sSearch"));
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = busstopgroupdao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			out.print(result);
		return null;
	}
	
	//Trip Mapping and Grouping...
	public String saveBusStopGroup() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			session.beginTransaction();
			HttpServletResponse response=ServletActionContext.getResponse();
			 
			PrintWriter out;
			out = response.getWriter();
			HttpServletRequest request=ServletActionContext.getRequest();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			
			int group_type_id=Integer.parseInt((String)request.getParameter("group_type_id"));
			String group_name=(String)request.getParameter("group_name");
			String bus_stop_group=(String)request.getParameter("bus_stop_group");
			String []bus_stop_groups=bus_stop_group.split(",");
			int start_stop_id=Integer.parseInt((String)request.getParameter("start_stop_id"));
			BusStopsGroupDAO busStopsGroupDAO = new BusStopsGroupDAO();
			
			
			
				if(group_type_id==2){
					listBusStopGroup=busStopsGroupDAO.check_trip(start_stop_id,session);
					if(listBusStopGroup.size()>0){
						out.print("Trip Mapping is Already Created for This Bus Stop");
						return null;
					}
				}
				
				if(group_type_id==1){
				listGroupName=busStopsGroupDAO.check_Group_Name(group_name,session);
				//System.out.println("listGroupName.size()---"+listGroupName.size());
				if(listGroupName.size()>0){
					out.print("Group Name Already Exit");
					return null;
				}
				}
			busStopGroup = new BusStopGroup();
			
			
			busstops=new BusStops();
			busstops.setId(start_stop_id);
			busStopGroup.setGroup_stop(busstops);
			
			
			groupType=new GroupType();
			groupType.setGroup_type_id(group_type_id);
			busStopGroup.setGroup_type_id(groupType);
			busStopGroup.setGroup_id(group_type_id);
			busStopGroup.setGroup_name(group_name);
			busStopGroup.setBus_stop_count(bus_stop_groups.length);
			busStopGroup.setStatus("Active");
			busStopGroup.setDeleted_status(0);
			busStopGroup.setInserted_date(new Date());
			busStopGroup.setInserted_by(usrsessionid);
			
			busStopsGroupDAO.addBus_stop_group(busStopGroup, session);
			int groupid=busStopGroup.getGroup_id();
			for(int i=0;i<bus_stop_groups.length;i++){
				busStopGroupStop=new BusStopGroupStop();
				busStopGroupStop.setGroup_id(busStopGroup);
				busstops=new BusStops();
				busstops.setId(Integer.parseInt(bus_stop_groups[i]));
				busStopGroupStop.setBus_stop_group_stop(busstops);
				busStopGroupStop.setStatus("Y");
				busStopGroupStop.setCreated_by(usrsessionid);
				busStopGroupStop.setCreated_date(new Date());
				busStopsGroupDAO.addBus_stop_group_stop(busStopGroupStop, session);
			}
			//busStopGroupStop= new BusStopGroupStop();
			//busStopGroupStop.setGroup_stop_id(group_stop_id);
			session.getTransaction().commit();
			out.print("Saved Successfully");
		}
		catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return null;
	}
	
	
	public String editBusStopGroup() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			session.beginTransaction();
			HttpServletResponse response=ServletActionContext.getResponse();
			 
			PrintWriter out;
			out = response.getWriter();
			HttpServletRequest request=ServletActionContext.getRequest();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			
			int group_type_id=Integer.parseInt((String)request.getParameter("group_type_id"));
			int groupid=Integer.parseInt((String)request.getParameter("groupid"));
			String group_name=(String)request.getParameter("group_name");
			String bus_stop_group=(String)request.getParameter("bus_stop_group");
			String []bus_stop_groups=bus_stop_group.split(",");
			int start_stop_id=Integer.parseInt((String)request.getParameter("start_stop_id"));
			busStopGroup = new BusStopGroup();
			
			
			
			
			//busstops=new BusStops();
			//busstops.setId(start_stop_id);
			//busStopGroup.setGroup_stop(busstops);
			
			
			//groupType=new GroupType();
			//groupType.setGroup_type_id(group_type_id);
			//busStopGroup.setGroup_type_id(groupType);
			//busStopGroup.setGroup_id(group_type_id);
			//busStopGroup.setGroup_name(group_name);
			//busStopGroup.setBus_stop_count(bus_stop_groups.length);
			//busStopGroup.setStatus("Active");
			//busStopGroup.setDeleted_status(0);
			//busStopGroup.setUpdated_date(new Date());
			//busStopGroup.setUpdated_by(usrsessionid);
			BusStopsGroupDAO busStopsGroupDAO = new BusStopsGroupDAO();
			busStopsGroupDAO.updateBus_stop_group(groupid,group_name,usrsessionid,bus_stop_groups.length, session);
			//System.out.println("in execute hiiii"+busStopGroup.getGroup_id());
			
			//BusStopsGroupDAO busStopsGroupDAO = new BusStopsGroupDAO();
			busStopsGroupDAO.updateBus_stop_group_stop(groupid, usrsessionid,session);
			for(int i=0;i<bus_stop_groups.length;i++){
				busStopGroupStop=new BusStopGroupStop();
				busStopGroup = new BusStopGroup();
				busStopGroup.setGroup_id(groupid);
				//busStopGroup.setGroup_name(group_name);
				busStopGroupStop.setGroup_id(busStopGroup);
				busstops=new BusStops();
				busstops.setId(Integer.parseInt(bus_stop_groups[i]));
				busStopGroupStop.setBus_stop_group_stop(busstops);
				busStopGroupStop.setStatus("Y");
				busStopGroupStop.setCreated_by(usrsessionid);
				busStopGroupStop.setCreated_date(new Date());
				busStopsGroupDAO.addBus_stop_group_stop(busStopGroupStop, session);
			}
			//busStopGroupStop= new BusStopGroupStop();
			//busStopGroupStop.setGroup_stop_id(group_stop_id);
			session.getTransaction().commit();
			out.print("Saved Successfully");
		}
		catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return null;
	}
	/*List<ScheduleDetails> scheduleDetails;

	public List<ScheduleDetails> getScheduleDetails() {
		return scheduleDetails;
	}

	public void setScheduleDetails(List<ScheduleDetails> scheduleDetails) {
		this.scheduleDetails = scheduleDetails;
	}*/

	public String getDeleteMap(){
		HttpServletRequest request=ServletActionContext.getRequest();
		PrintWriter out;
		int groupuptypeid=0;
		String msg="";
	    int id=Integer.parseInt(request.getParameter("busstopgroupid"));
	    BusStopsGroupDAO busstopgroupdao = new BusStopsGroupDAO();
	    listBusStopGroup=busstopgroupdao.getBusStopGroupInfo(id);
	    listBusStopGroupStop=busstopgroupdao.getBusStopGroupStopInfo(id);
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	    if(listBusStopGroupStop.size()>0){
	    	if(listBusStopGroupStop.size()>0){
	    for(int i=0;i<listBusStopGroupStop.size();i++){
	    	//System.out.println("listBusStopGroupStop.get(i).getGroup_stop_id()"+listBusStopGroupStop.get(i).getGroup_stop_id());
	    	listScheduleDetails=busstopgroupdao.getScheduleDetails(listBusStopGroupStop.get(i).getBus_stop_group_stop().getId());
	    	
	    	for(int j=0;j<listScheduleDetails.size();j++){
	    		if(j==0){
	    			msg=msg+"Bus Stop "+listBusStopGroupStop.get(i).getBus_stop_group_stop().getBusStopName()+" Is Used In Schedule";
	    		}
	    		msg = msg+" "+listScheduleDetails.get(j).getScheduleNumber().getScheduleNumber()+",  ";
	    		//System.out.println("scheduleDetails.get(j).getScheduleNumber().getScheduleNumber()"+listScheduleDetails.get(j).getScheduleNumber().getScheduleNumber());
	    	}
	    	
	    }
	    	}
	    }
	    if(msg==""){
	    	busstopgroupdao.UpdateGropStatus(id,usrsessionid);
	    	
	    	msg=listBusStopGroup.get(0).getGroup_type_id().getGroup_type_name()+" Deleted Successfully";
	    }
	    
	    addActionError(msg);
		return "success";
	}
	

}
