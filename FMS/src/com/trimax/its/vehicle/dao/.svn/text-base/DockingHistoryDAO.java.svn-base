package com.trimax.its.vehicle.dao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Join;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.trimax.its.common.Common;
import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.device.model.Device;
import com.trimax.its.toll.model.Tollfee;
import com.trimax.its.transport.model.PeakHour;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.Complaint;
import com.trimax.its.vehicle.model.DockingDetails;
import com.trimax.its.vehicle.model.DockingHistory;
import com.trimax.its.vehicle.model.DockingType;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleCategory;

public class DockingHistoryDAO {
	
	Common common = new Common();
	@SuppressWarnings("unchecked")
	public JSONObject getDockingHistoryData(int total,HttpServletRequest request, int index, String dir,String viewdeletedrecord) {
		
		JSONObject result = new JSONObject();
		Session session = null;
		
		String[] dbcol={"","docking_id","v.vehicleId","mechanic_user_id","startTime","endTime","status","docType.docking_type","docking_batch_name",
				"fip_change","eoc_change","comp.componentTypeId"};
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(DockingHistory.class);
			}else{
				 criteria = session.createCriteria(DockingHistory.class);
			 criteria.add(Restrictions.eq("deletedStatus", 0));
			}
			
			
//			Criteria criteria = session.createCriteria(DockingHistory.class);
//			 criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.createCriteria("vehicle","v",Criteria.LEFT_JOIN);
			criteria.createCriteria("dockingType", "docType",Criteria.LEFT_JOIN);
			criteria.createCriteria("componenetType","comp",Criteria.LEFT_JOIN);
			String col = dbcol[index];
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			String search_term = request.getParameter("sSearch").trim();
			if (search_term != null && !search_term.equals("")) {
				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("docking_batch_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("v.vehicleRegistrationNumber", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
				criteria.add(conditionGroup);
			}
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));

			System.out.println("LENGHTH:::::"+Integer.parseInt(request.getParameter("iDisplayStart"))+""+Integer.parseInt(request.getParameter("iDisplayLength")));
			List<DockingHistory> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				
				ja.add( "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='" + list.get(i).getDocking_id() + "' value='" + list.get(i).getDocking_id() + "'/>");
				ja.add(list.get(i).getDocking_id());
				ja.add(list.get(i).getVehicle().getVehicleRegistrationNumber());
				ja.add(list.get(i).getMechanic_user_id()!=null ? list.get(i).getMechanic_user_id().replaceAll(" ","&nbsp;"):"");
				
				//ja.add(list.get(i).getHoliday_type()!=null ? list.get(i).getHoliday_type().replaceAll(" ","&nbsp;"):"");
				ja.add(list.get(i).getStartTime()!=null ? common.getDateTimeToDatePicker(list.get(i).getStartTime()).replaceAll(" ","&nbsp;"):"");
				ja.add(list.get(i).getEndTime()!=null ? common.getDateTimeToDatePicker(list.get(i).getEndTime()).replaceAll(" ","&nbsp;"):"");
				ja.add(list.get(i).getStatus()!=null ? list.get(i).getStatus().replaceAll(" ","&nbsp;"):"");
				if(list.get(i).getDockingType()==null){
					ja.add("");
					
					}else{
					ja.add(list.get(i).getDockingType().getDocking_type());

					
				}
				
				ja.add(list.get(i).getDocking_batch_name());
				ja.add(list.get(i).getFip_change());
				ja.add(list.get(i).getEoc_change());
				
				
				if(list.get(i).getComponenetType()!=null){
					ja.add(list.get(i).getComponenetType().getComponentName());
				}else{
					ja.add("");
				}
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						//ja.add(" ");
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getDocking_id()+"'/>Deleted Record");	

						
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getDocking_id()+"'/>Record in Database");	

						
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
			}

			if (search_term != null && !search_term.equals("")) {
				totalAfterFilter =getTotalRecordsForSeacrch(total,request,col,dir,viewdeletedrecord);
				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}else{
				totalAfterFilter=getTotalDockingHistoryRecords(viewdeletedrecord);
				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return result;
		}
	}

	public int getTotalDockingHistoryRecords(String viewdeletedrecord) {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		 Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(DockingHistory.class);
			}else{
				 criteria = session.createCriteria(DockingHistory.class);
			     criteria.add(Restrictions.eq("deletedStatus", 0));
			}
		
		
		//Criteria criteria = session.createCriteria(DockingHistory.class);
		//criteria.add(Restrictions.eq("deletedStatus",0));
		List<Integer> list = criteria.list();
		int cnt = list.size();
		return cnt;
	}
	public int getTotalRecordsForSeacrch(int total,HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session;
		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(DockingHistory.class);
				}else{
					 criteria = session.createCriteria(DockingHistory.class);
				     criteria.add(Restrictions.eq("deletedStatus", 0));
				}
			 
				//Criteria criteria = session.createCriteria(DockingHistory.class);
				//criteria.add(Restrictions.eq("deletedStatus", 0));
				
				if (!request.getParameter("sSearch").equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					Junction conditionGroup = Restrictions.disjunction();
					conditionGroup.add(Restrictions.like("docking_batch_name",search_term,MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("v.vehicleRegistrationNumber", "%"
							+ search_term + "%"));
					conditionGroup.add(Restrictions.like("status",search_term,MatchMode.ANYWHERE));
					criteria.add(conditionGroup);
				}
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				criteria.createCriteria("vehicle","v");
				List<DockingHistory> list = criteria.list();
				cnt = list.size();
				System.out.println(cnt +"vdcvdhwck");
			}catch(Exception e){
				e.printStackTrace();
		}
		return cnt;
	}

	//vehicle list 
	public Map<Integer, String> getVehicleList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Vehicle where status='Active' and deleted_status=0");
		
		try{
		List<Vehicle> list = query.list();
		
		for (Vehicle vehicle : list) {
			resultMap.put(vehicle.getVehicleId(), vehicle.getVehicleRegistrationNumber());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
		

	}
	//Compent type list
	public Map<Integer, String> getComponentList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from ComponentType where status='Active' and deleted_status=0");
		
		try{
		List<ComponentType> list = query.list();
		
		for (ComponentType compotype : list) {
			resultMap.put(compotype.getComponent_type_id(),compotype.getComponent_type_name());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
		

	}

	public List<DockingType> getDocTypeList(int vehicleid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("vehicleic"+vehicleid);
		int klmtr=0;
		String query2="";
		Session session = null;
		List<DockingType> list=new ArrayList<DockingType>();
		List<Map<String,String>> lists = null;
		int klm=0;
		String doctype="";
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String query1="select docking_kms from  docking_details where vehicle_id='"+ vehicleid+"' Order by id desc limit 1";
		
		String query11 = "From DockingHistory  where vehicle_id='"+ vehicleid+"' Order by id desc limit 1";
		Query query=session.createQuery(query11);
		List<DockingHistory> lisst=query.list();
		if(lisst.size()>0)
		{
			DockingHistory dochistory=(DockingHistory)lisst.get(0);
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+dochistory.getDockingType().getDocking_type());
			 doctype=dochistory.getDockingType().getDocking_type();
		}
		Query queryobjct=session.createSQLQuery(query1).addScalar("docking_kms", Hibernate.STRING);
		queryobjct.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		lists=queryobjct.list();
		if(lists.size()>0){
			Map<String,String> resultSet = lists.get(0);
			System.out.println(resultSet.get("docking_kms"));
			klmtr=Integer.parseInt(resultSet.get("docking_kms"));
			
			
			
		
		if(klmtr >=19000 && doctype.equals("D1"))
		{
			
			Criteria criteria = session.createCriteria(DockingType.class);
			 criteria.add(
					  Restrictions.not(
					    Restrictions.in("docking_type", new String[] {"D1"})
					  )
					);
			//Query query3 = session.createQuery("from DockingType");
			
		 list=criteria.list();
		}
		if(klmtr <19000 && doctype.equals("D1"))
		{
			
			Criteria criteria = session.createCriteria(DockingType.class);
			 
			//Query query3 = session.createQuery("from DockingType");
			
		 list=criteria.list();
		}
		
		if(klmtr >=19000 && doctype.equals("D2"))
		{
			
			Criteria criteria = session.createCriteria(DockingType.class);
			 criteria.add(
					  Restrictions.not(
					    Restrictions.in("docking_type", new String[] {"D1","D2"})
					  )
					);
			//Query query3 = session.createQuery("from DockingType");
			
		 list=criteria.list();

			
		}
		if(klmtr <19000 && doctype.equals("D2"))
		{
			
			Criteria criteria = session.createCriteria(DockingType.class);
			 criteria.add(
					  Restrictions.not(
					    Restrictions.in("docking_type", new String[] {"D1"})
					  )
					);
			//Query query3 = session.createQuery("from DockingType");
			
		 list=criteria.list();

			
		}
		if(klmtr >=19000 && doctype.equals("D3"))
		{
			
			Criteria criteria = session.createCriteria(DockingType.class);
			 
			criteria.add(
					  Restrictions.not(
					    Restrictions.in("docking_type", new String[] {"D1","D2","D3"})
					  )
					);
			
		 list=criteria.list();

			
		}
		if(klmtr <19000 && doctype.equals("D3"))
		{
			
			Criteria criteria = session.createCriteria(DockingType.class);
			 
			criteria.add(
					  Restrictions.not(
					    Restrictions.in("docking_type", new String[] {"D1","D2"})
					  )
					);
			
		 list=criteria.list();

			
		}
		if(klmtr >=19000 && doctype.equals("D4"))
		{
			
			Criteria criteria = session.createCriteria(DockingType.class);
			 
			
			
		 list=criteria.list();

			
		}
		if(klmtr <19000 && doctype.equals("D4"))
		{
			
			Criteria criteria = session.createCriteria(DockingType.class);
			 
			criteria.add(
					  Restrictions.not(
					    Restrictions.in("docking_type", new String[] {"D1","D2","D3"})
					  )
					);
			
		 list=criteria.list();

			
		}
}else{
	Criteria criteria = session.createCriteria(DockingType.class);
	 list=criteria.list();
}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		//}
		}
		return list;
		

	}
	public int insertDeviceType(DockingHistory dochistory) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		System.out.println("000000000000000000rajesh"+dochistory.getStartDateString()+"###########"+dochistory.getEndDateString());
		try{
		 i = (Integer) session.save(dochistory);
		session.getTransaction().commit();
		}catch(Exception e)
		{
			
		}finally{
		session.close();
		}
		return i;

	}
	public DockingHistory getEditedDeviceType(int id) {
		// BusStops busstops = new BusStops();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		DockingHistory dockinghistory =(DockingHistory)session.get(DockingHistory.class,new Integer(id));
		dockinghistory.setStartDateString(dockinghistory.getStartTime()!=null ? common.getDateTimeFromDBToPicker(dockinghistory.getStartTime().toString()):"");
		//dockinghistory.setStartDateString(common.getDateTimeFromDBToPicker(dockinghistory.getStartTime().toString()));
		//String date_end=(common.getDateTimeFromDBToPicker(dockinghistory.getEndTime().toString())!=null?common.getDateTimeFromDBToPicker(dockinghistory.getEndTime().toString()):"");
		
		//if(dockinghistory.getEndTime())
		
		//try{
		if(dockinghistory.getEndTime()!=null)
		{
			
			//dockinghistory.setStartDateString(common.getDateTimeFromDBToPicker(dockinghistory.getStartTime().toString()));
		dockinghistory.setEndDateString(common.getDateTimeFromDBToPicker(dockinghistory.getEndTime().toString()));
		}
		else{
			dockinghistory.setEndDateString("");
			
			
		}
		//}catch(Exception e)
		//{
			//System.out.println("wdxq"+e);
		//}
			//	+ "\t" + fareChart.getRoute_id());
		return dockinghistory;

	}
	
	public String updateDockHistory(DockingHistory dockhistory, int dockid) {
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
	try{
		DockingHistory dockhistorys = (DockingHistory)session.get(DockingHistory.class,dockid);
		//dockhistorys.setStartTime(common.getDateTimeFromDatePicker(dockhistory.getStartDateString()));
		dockhistorys.setStartTime(dockhistory.getStartTime());
		dockhistorys.setEndTime(dockhistory.getEndTime());
		System.out.println(dockhistory.getStartTime()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+dockhistory.getComponenetType().getComponentTypeId());
		//dockhistorys.setEndTime(common.getDateTimeFromDatePicker(dockhistory.getStartDateString()));
		
		
		if(dockhistory.getComponenetType().getComponentTypeId()==0){
			dockhistorys.setComponenetType(null);
			
			}else{
				dockhistorys.setComponenetType(dockhistory.getComponenetType());

			
		}
//		if(dockhistory.getComponenetType()==null )
//		{
//		dockhistorys.setComponenetType(dockhistory.getComponenetType());
//		}
//		else{
//			
//			dockhistorys.setComponenetType(null);
//		}
		dockhistorys.setDockingType(dockhistory.getDockingType());
		dockhistorys.setDocking_batch_name(dockhistory.getDocking_batch_name());
		dockhistorys.setVehicle(dockhistory.getVehicle());
		dockhistorys.setStatus(dockhistory.getStatus());
		dockhistorys.setFip_change(dockhistory.getFip_change());
		dockhistorys.setEoc_change(dockhistory.getEoc_change());
		dockhistorys.setUpdatedBy(dockhistory.getUpdatedBy());
		dockhistorys.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));

		session.update(dockhistorys);
		session.getTransaction().commit();
	}catch(Exception ex)
	{
		session.getTransaction().rollback();
		ex.printStackTrace();
	}finally{
		session.close();
	}
		
		return null;
	}

public String deleteDockHistory(DockingHistory dockhistory, int doc_id) {
	// TODO Auto-generated method stub
	
	//System.out.println("deleteComplaintType");
	
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
//System.out.println("=====Route id" + role.getRole_id());
try{
	//
	DockingHistory dockhistorys = (DockingHistory) session.get(DockingHistory.class,doc_id);
	
	
	dockhistorys.setDeletedStatus(1);
	dockhistorys.setUpdatedBy(dockhistory.getUpdatedBy());
	dockhistorys.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
	
	session.update(dockhistorys);
	session.getTransaction().commit();
}catch(Exception ex)
{
	session.getTransaction().rollback();
	ex.printStackTrace();
}finally{
	session.close();
}
	
	return null;
}

public String getVehicleRunningKm(int vehicleid) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	int a=0;
	String km="0";
	int v=15;
	
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Query query = session.createQuery("from Vehicle where vehicleId='"+vehicleid+"'");
	
	try{
	List<Vehicle> list = query.list();
	if(list.size()>0){
	 km=(list.get(0).getProgressingKMs()!=null?list.get(0).getProgressingKMs():"0");
	 System.out.println("Vehiclekilometer%%%%%%%%%%%%%%%%%%"+km);
	}
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally{
		if (session != null) {
			session.close();
		}
	}
	return km;
	

}
public String getDockigKm(int vehicleid) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	int a=0;
	String km="";
	int v=15;
	
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	String query11 = "From DockingHistory  where vehicle_id='"+ vehicleid+"' Order by id desc limit 1";
	Query query=session.createQuery(query11);
	try{
		List<DockingHistory> lisst=query.list();
	 km=lisst.get(0).getDocking_kms();
	 System.out.println("Dockingkilomete%%%%%%%%%%%%%%%%%%%%%%r"+km);
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally{
		if (session != null) {
			session.close();
		}
	}
	return km;
	

}
public String getDockTypeKm(int vehicleid) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	int a=0;
	String km="0";
	int v=15;
	
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	String query11 = "From DockingType  where docking_type_id='"+ vehicleid+"' Order by id desc limit 1";
	Query query=session.createQuery(query11);
	try{
		List<DockingType> lisst=query.list();
		if(lisst.size()>0){
	 km=(lisst.get(0).getDockingTypeKms()!=null?lisst.get(0).getDockingTypeKms():"0");
	 System.out.println("DockingTypekilometer%%%%%%%%%%%%%%%%%%%%%%%%%%%"+km);
		}
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally{
		if (session != null) {
			session.close();
		}
	}
	return km;
	

}

public DockingHistory getDock_Type(int vehicleid) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	int a=0;
	String dock_type="";
	int v=15;
	DockingHistory docktype=new DockingHistory();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	String query11 = "From DockingHistory  where vehicle_id='"+ vehicleid+"' Order by id desc limit 1";
	System.out.println("^^^^^^^^^^^^^^"+query11);
	Query query=session.createQuery(query11);
	try{
		List<DockingHistory> lisst=query.list();
		if(lisst.size()>0)
		{
		docktype=lisst.get(0);
	}else
	{
		
		docktype=null;
	}
	 System.out.println("DockingTypekilometer%%%%%%%%%%%%%%%%%%%%%%%%%%%"+docktype);
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally{
		if (session != null) {
			session.close();
		}
	}
	return docktype;
	

}

public Map<Integer, String> getDocTypeListlist() {
	Session session = null;
	List<DockingType> list=new ArrayList<DockingType>();
	Map<Integer, String> resultMap = new HashMap<Integer, String>();
	session = HibernateUtil.getSession("hibernate.cfg.xml");
	Criteria criteria = session.createCriteria(DockingType.class);
	// list=criteria.list();
	 try{
			List<DockingType> lists = criteria.list();
			
			for (DockingType compotype : lists) {
				resultMap.put(compotype.getDocking_type_id(),compotype.getDocking_type());
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		
		}
return 	resultMap;
}


@SuppressWarnings("finally")
public String getDateTimeFromPickerToDB(String pickerDate)
{
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");
	SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM yyyy - HH:mm");
    Date date = new Date();
    String formattedDate="";
    try
    {
    	date = simpleDateFormat1.parse(pickerDate);
    	formattedDate=simpleDateFormat.format(date);
    }catch(Exception ex)
    {
    	System.out.println("errror--"+ex.getMessage());
    	
    }
    finally{
    	return formattedDate;
    }
}


}
