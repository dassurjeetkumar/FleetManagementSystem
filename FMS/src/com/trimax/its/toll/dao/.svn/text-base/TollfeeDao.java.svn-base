package com.trimax.its.toll.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



import com.trimax.its.common.Common;
import com.trimax.its.componenttype.model.ComponentType;

import com.trimax.its.model.User;
import com.trimax.its.toll.model.Tollfee;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.ServiceType;

public class TollfeeDao {
	
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Session session=null;
		Common common=new Common();
		Criteria criteria ;	
		
		try {

			int totalAfterFilter = total;

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(Tollfee.class);
				criteria.createAlias("servicetype","servicetype");
				criteria.createAlias("busstop","busstop");
			}else{
				criteria = session.createCriteria(Tollfee.class);
				criteria.createAlias("servicetype","servicetype");
				criteria.createAlias("busstop","busstop");
				criteria.add(Restrictions.eq("deletedStatus", 0));
			}
		
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}

			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Disjunction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("busstop.busStopName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("servicetype.service_type_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("versionNo","%"
						+ search_term + "%"));
				/*conditionGroup.add(Restrictions.like("amount", "%"
						+ search_term + "%"));*/
				criteria.add(conditionGroup);

			}	
			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<Tollfee> list = criteria.list();

			JSONArray array = new JSONArray();

			
			
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()
						+ "' value='"+ list.get(i).getId() + "'/>");
				ja.add(list.get(i).getId());				
				ja.add(list.get(i).getServicetype().getService_type_name());//
				ja.add(list.get(i).getAmount() );
				ja.add(list.get(i).getEffect_start_date()!=null ? 
						common.getDateFromDateTime_(list.get(i).getEffect_start_date()):"");
				ja.add(list.get(i).getEffect_end_date()!=null ? 
						common.getDateFromDateTime_(list.get(i).getEffect_end_date()):"");
				
				ja.add(list.get(i).getVersionNo());
				ja.add(list.get(i).getBusstop().getBusStopName()+" "+(list.get(i).getBusstop().getStop_direction()!=null ?"-"+ list.get(i).getBusstop().getStop_direction():"" ));
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getId()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getId()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}

				
				
				array.add(ja);
			}

			totalAfterFilter = getTotalRecords(request,col,dir,viewdeletedrecord);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			 if(session!=null && session.isOpen()){
				 session.close();
			 }
			}
		return result;
	}
	public int getTotalRecords(HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		Session session=null;
		int cnt=0;
		Criteria criteria ;	
		
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(Tollfee.class);
				criteria.createAlias("servicetype","servicetype");
				criteria.createAlias("busstop","busstop");
			}else{
				criteria = session.createCriteria(Tollfee.class);
				criteria.createAlias("servicetype","servicetype");
				criteria.createAlias("busstop","busstop");
				criteria.add(Restrictions.eq("deletedStatus", 0));
			}
		

			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
		
			List<Tollfee> list = criteria.list();
			cnt = list.size();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
			 if(session!=null && session.isOpen()){
				 session.close();
			 }
			}
			return cnt;
	}
	
	/**
	 * Get the Service type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer,String> getServiceType(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		
		Session session = null;
		
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from ServiceType where deletedStatus=0 and status='Active' order by service_type_name desc");
			List<ServiceType> list = query.list();
	
			for (ServiceType sType : list) {
				
				resultMap.put(sType.getService_type_id(),sType.getService_type_name());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				 session.close();
			 }
		}
		return resultMap;
	}
	
	/**
	 * Get the Bus Stop name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer,String> getBusStopName(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		
		Session session = null;
		
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from BusStops where  status='NEW' and updated_by !='' order by bus_stop_name desc");
			List<BusStops> list = query.list();
	
			for (BusStops bType : list) {
				
				resultMap.put(bType.getId(),bType.getBusStopName());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				 session.close();
			 }
		}
		return resultMap;
	}
	
public Tollfee getTollfeeById(int id){
		
		Session session=null;
		Transaction tx=null;
		Tollfee tollfee=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from Tollfee where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		//System.out.println("tid..........................."+list.size());
		if(list.size()>0){
			tollfee=(Tollfee)list.get(0);
			Tollfee tollfeeNew=tollfee;
			Date dateStart = sdf.parse(tollfee.getEffect_start_date());
			String dateStrStart = sdf1.format(dateStart);	
			tollfee.setEffect_start_date(dateStrStart);
			
			Date dateEnd = sdf.parse(tollfee.getEffect_end_date());
			String dateStrEnd = sdf1.format(dateEnd);	
			tollfee.setEffect_end_date(dateStrEnd);
			
			tollfee.setId(tollfee.getId());
			
		}
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return tollfee;
	}

		public int addTollfee(Tollfee tollfee)
		{
			int i=0;
			Session session=null;
			Transaction tx=null;
			HttpServletRequest request = ServletActionContext.getRequest();
			Common common=new Common();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			Tollfee tollfeeNew =tollfee;
			String orgStart=tollfee.getEffect_start_date();
			String orgEnd=tollfee.getEffect_end_date();
			
			
			
			try{
		
			int masterId=-1;
			
			//check for Dates
			Date dateStart = sdf1.parse(tollfee.getEffect_start_date());
			String dateStrStart = sdf.format(dateStart);			
			
			Date dateEnd = sdf1.parse(tollfee.getEffect_end_date());
			String dateStrEnd = sdf.format(dateEnd);
			
			/**
			 * Check dates from the database.
			 */
			String qry="from Tollfee where versionNo='"+tollfee.getVersionNo()
					+"' and service_type_id="+tollfee.getServicetype().getService_type_id()+" and  deletedStatus=0 order by effect_start_date";		
			
			Query query=HibernateUtil.getSession("").createQuery(qry);
			List<Tollfee> list=query.list();
			
			
			if(list.size()>0){
				
				if(dateStart.after(dateEnd))
				{
					return 5;
				}
				
				for(int cnt=0;cnt<list.size();cnt++){
					//Check The start date is inbetween to dbstart and dbend date
					Date dbStartDt = sdf.parse(list.get(cnt).getEffect_start_date());
					Date dbEndDt = sdf.parse(list.get(cnt).getEffect_end_date());
					if((dateStart.after(dbStartDt)) && (dateStart.before(dbEndDt))
							|| (dateStart.equals(dbStartDt)) && (dateStart.equals(dbEndDt)))
					{
						return 1;
					}
					else if((dateStart.after(dbStartDt)) && (dateEnd.before(dbEndDt)))
					{
						return 2;
					}
					else if((dateStart.equals(dbStartDt)) || (dateEnd.equals(dbEndDt) ||
							(dateStart.equals(dbEndDt)) || (dateStart.equals(dbStartDt)) ))
					{
						return 3;
					}
					else if(dateStart.before(dbStartDt))
					{
						return 4;
					}					
					
				}
				
				
				try
				{
					session=HibernateUtil.getSession("");
					tx=session.beginTransaction();
					
					tollfeeNew.setAmount(tollfee.getAmount());
					tollfeeNew.setEffect_start_date(dateStrStart);
					tollfeeNew.setEffect_end_date(dateStrEnd);
					tollfeeNew.setVersionNo(tollfee.getVersionNo());			
					tollfeeNew.setCreatedby(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
					tollfeeNew.setCreateddate(new Date());
					tollfeeNew.setUpdatedby(0);
					tollfeeNew.setUpdateddate(null);
					tollfeeNew.setDeletedStatus(0);
					
					i=(Integer)session.save(tollfeeNew);				
					tx.commit();
					
				}
				catch(Exception e){
					e.printStackTrace();
					tx.rollback();
					i=-1;
					try
					{
					tollfeeNew.setEffect_start_date(orgStart);
					tollfeeNew.setEffect_end_date(orgEnd);
					}
					catch(Exception e2){}
				}
				finally{
					 if(session!=null && session.isOpen()){
						 session.close();
					 }
			}
			}
			else {	
				
				if(dateStart.after(dateEnd))
				{
					return 5;
				}
				
			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();
			
			tollfeeNew.setAmount(tollfee.getAmount());
			tollfeeNew.setEffect_start_date(dateStrStart);
			tollfeeNew.setEffect_end_date(dateStrEnd);
			tollfeeNew.setVersionNo(tollfee.getVersionNo());			
			tollfeeNew.setCreatedby(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			tollfeeNew.setCreateddate(new Date());
			tollfeeNew.setUpdatedby(0);
			tollfeeNew.setUpdateddate(null);
			tollfeeNew.setDeletedStatus(0);
			
			i=(Integer)session.save(tollfeeNew);			
			tx.commit();
			
			}
			}
			
			catch(Exception e){
				e.printStackTrace();
				tx.rollback();
				i=-1;
				try
				{
				tollfeeNew.setEffect_start_date(orgStart);
				tollfeeNew.setEffect_end_date(orgEnd);
				}
				catch(Exception e2){}
			}
			finally{
				 if(session!=null && session.isOpen()){
					 session.close();
				 }
			}
			
			return i;
			
		}
		
		//Update The data
		public int updateTollfee(Tollfee tollfee){
			int i=0;
			Session session=null;
			Transaction tx=null;
			HttpServletRequest request = ServletActionContext.getRequest();
			Common common=new Common();
			
			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();	
			
			Tollfee tollfeeNew=(Tollfee) session.get(Tollfee.class,tollfee.getId());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			
			String orgStart=tollfee.getEffect_start_date();
			String orgEnd=tollfee.getEffect_end_date();
			try{
				Date dateStart = sdf1.parse(tollfee.getEffect_start_date());
				String dateStrStart = sdf.format(dateStart);			
				
				Date dateEnd = sdf1.parse(tollfee.getEffect_end_date());
				String dateStrEnd = sdf.format(dateEnd);
				
				/**
				 * Check dates from the database.
				 */
				String qry="from Tollfee where versionNo='"+tollfee.getVersionNo()
						+"' and service_type_id="+tollfee.getServicetype().getService_type_id()+" and  deletedStatus=0" +
						" and id!= "+tollfee.getId()+" order by effect_start_date";		
				
				Query query=HibernateUtil.getSession("").createQuery(qry);
				List<Tollfee> list=query.list();
				
				
				if(list.size()>0){
					if(dateStart.after(dateEnd))
					{
						return -5;
					}
					for(int cnt=0;cnt<list.size();cnt++){
						//Check The start date is inbetween to dbstart and dbend date
						Date dbStartDt = sdf.parse(list.get(cnt).getEffect_start_date());
						Date dbEndDt = sdf.parse(list.get(cnt).getEffect_end_date());
						if((dateStart.after(dbStartDt)) && (dateStart.before(dbEndDt))
								|| (dateStart.equals(dbStartDt)) && (dateStart.equals(dbEndDt)))
						{
							return -1;
						}
						else if((dateStart.after(dbStartDt)) && (dateEnd.before(dbEndDt)))
						{
							return -2;
						}
						else if((dateStart.equals(dbStartDt)) || (dateEnd.equals(dbEndDt) ||
								(dateStart.equals(dbEndDt)) || (dateStart.equals(dbStartDt)) ))
						{
							return -3;
						}
						else if(dateStart.before(dbStartDt))
						{
							return -4;
						}					
						
					}
					
					
					try
					{

				
				
				tollfeeNew.setAmount(tollfee.getAmount());
				tollfeeNew.setServicetype(tollfee.getServicetype());
				tollfeeNew.setEffect_start_date(dateStrStart);
				tollfeeNew.setEffect_end_date(dateStrEnd);
				tollfeeNew.setVersionNo(tollfee.getVersionNo());	
				//tollfeeNew.setBusstop(tollfee.getBusstop());
				tollfeeNew.setCreatedby(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
				tollfeeNew.setCreateddate(new Date());
				tollfeeNew.setUpdatedby(1);
				tollfeeNew.setUpdateddate(null);
				tollfeeNew.setDeletedStatus(0);
				
				session.update(tollfeeNew);
				i=1;				
				tx.commit();
				
				}
				
				catch(Exception e){
					e.printStackTrace();
					tx.rollback();
					i=-1;
					try
					{
					tollfeeNew.setEffect_start_date(orgStart);
					tollfeeNew.setEffect_end_date(orgEnd);
					}
					catch(Exception e2){}
				}
					finally{
						 if(session!=null && session.isOpen()){
							 session.close();
						 }
						}
					}
				else
				{
					if(dateStart.after(dateEnd))
					{
						return -5;
					}
					
					try
					{
				/*session=HibernateUtil.getSession("");
				tx=session.beginTransaction();*/
				
				//Tollfee tollfeeNew=(Tollfee) session.get(Tollfee.class,tollfee.getId());
				
				
				tollfeeNew.setAmount(tollfee.getAmount());
				tollfeeNew.setServicetype(tollfee.getServicetype());
				tollfeeNew.setEffect_start_date(dateStrStart);
				tollfeeNew.setEffect_end_date(dateStrEnd);
				tollfeeNew.setVersionNo(tollfee.getVersionNo());	
				//tollfeeNew.setBusstop(tollfee.getBusstop());
				tollfeeNew.setCreatedby(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
				tollfeeNew.setCreateddate(new Date());
				tollfeeNew.setUpdatedby(1);
				tollfeeNew.setUpdateddate(new Date());
				tollfeeNew.setDeletedStatus(0);
				
				session.update(tollfeeNew);		
				i=1;
				tx.commit();
				
				}
					catch(Exception e){
						e.printStackTrace();
						tx.rollback();
						i=-1;
						try
						{
						tollfeeNew.setEffect_start_date(orgStart);
						tollfeeNew.setEffect_end_date(orgEnd);
						}
						catch(Exception e2){}
					}
					finally{
						 if(session!=null && session.isOpen()){
							 session.close();
						 }
			}
				}
			}
			catch(Exception e){
				e.printStackTrace();
				tx.rollback();
				i=-1;
			}
			finally{
				 if(session!=null && session.isOpen()){
					 session.close();
				 }
			}
			return i;
		}
		
		//Delete The data
		public String deleteTollfee(int id) {

			Session session=null;
			String s="error";
			try{
				
			session= HibernateUtil.getSession("");
			session.beginTransaction();

			Tollfee tollfeeNew=(Tollfee)session.get(Tollfee.class,new Integer(id));

			HttpServletRequest request=ServletActionContext.getRequest();
			User user = (User) request.getSession().getAttribute("user");
			
			tollfeeNew.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			tollfeeNew.setUpdateddate(new Date());
			tollfeeNew.setDeletedStatus(1);

			session.getTransaction().commit();
			session.update(tollfeeNew);		
			s="success";
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				 if(session!=null && session.isOpen()){
					 session.close();
				 }
			}

			return s;
		}
		
		@SuppressWarnings("unchecked")
		public List<BusStops> getBusStopDropList(String name){
			List<BusStops> list=null;
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query;
			 HttpServletRequest request=ServletActionContext.getRequest();
				HttpSession sess=request.getSession();
				
				try{
				
					query = session.createQuery("from BusStops where status!= 'deleted'  and (bus_stop_name like '"+name+"%' or alias1 like '"+name+"%' or alias2 like '"+name+"%' or alias3 like '"+name+"%' or alias4 like '"+name+"%' or alias5 like '"+name+"%' or alias6 like '"+name+"%')");
					
					list = (List<BusStops>) query.list();
				} catch(Exception e){
					e.printStackTrace();
				}
				finally{
					  if(session!=null){
			                session.close();
			            }
				}
			//session.close();
			return list;
			
		}


}
