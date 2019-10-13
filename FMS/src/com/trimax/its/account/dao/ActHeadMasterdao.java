package com.trimax.its.account.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.its.fare.model.RevenueType;
import com.trimax.its.account.model.ActHeadMasterModel;
import com.trimax.its.common.Common;
import com.trimax.its.componenttype.model.ComponentType;

import com.trimax.its.model.User;





import com.trimax.its.util.HibernateUtil;

public class ActHeadMasterdao {
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Session session=null;

		try {

			int totalAfterFilter = total;

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria;
			//criteria.add(Restrictions.like("status", "%INACTIVEe%"));
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(ActHeadMasterModel.class);
					criteria.createAlias("revenuetype","rvntype");
			}else{
				 criteria = session.createCriteria(ActHeadMasterModel.class);
					criteria.createAlias("revenuetype","rvntype");
					criteria.add(Restrictions.ne("deletedstatus", 1));
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

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("acthead_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("rvntype.revenueTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", ""+ search_term + "%"));
			
				criteria.add(conditionGroup);

			}		
			
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<ActHeadMasterModel> list = criteria.list();

			JSONArray array = new JSONArray();

			
			
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getActhead_id()
						+ "' value='"+ list.get(i).getActhead_id() + "'/>");
				
				ja.add(list.get(i).getActhead_id());//
				ja.add(list.get(i).getActhead_name() );
				ja.add(list.get(i).getRevenuetype().getRevenueTypeName());
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getNotes());
				if(list.get(i).getCR_DB()==null){
					ja.add("");
				}else{
				ja.add(list.get(i).getCR_DB());
				}
				if(list.get(i).getRemarks()==null){
					ja.add("");
				}else{
				ja.add(list.get(i).getRemarks());
				}
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedstatus();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getActhead_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getActhead_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				System.out.println(ja.toString());
			}

			
			/*if (!request.getParameter("sSearch").equals("")) {
				totalAfterFilter = list.size();
				result.put("aaData", array);
				result.put("iTotalRecords", list.size());

				result.put("iTotalDisplayRecords", list.size());
			} else {
				totalAfterFilter = getTotalRecords(request,col,dir);
				result.put("aaData", array);
				result.put("iTotalRecords", total);

				result.put("iTotalDisplayRecords", totalAfterFilter);
			}*/
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
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria;
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				criteria = session.createCriteria(ActHeadMasterModel.class);
				criteria.createAlias("revenuetype","rvntype");
			}else{
				criteria = session.createCriteria(ActHeadMasterModel.class);
				 criteria.createAlias("revenuetype","rvntype");
				 criteria.add(Restrictions.ne("deletedstatus", 1));
			}
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("acthead_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("rvntype.revenueTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", ""+ search_term + "%"));
			
				criteria.add(conditionGroup);

			}		

			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
		
			List<ActHeadMasterModel> list = criteria.list();
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
	
	
	
	//Getting the Revenue type
	@SuppressWarnings("unchecked")
	public Map<Integer,String> getRevenueType(){
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		
		Session session = null;
		
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from RevenueType where deletedStatus=0 and status='Active' order by revenueTypeName desc");
			List<RevenueType> list = query.list();
	
			for (RevenueType rType : list) {
				
				resultMap.put(rType.getId(),rType.getRevenueTypeName());
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
	
	public int addAccountHead(ActHeadMasterModel actheadmaster)
	{
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Common common=new Common();
		
		try{

		int masterId=-1;
		
		//check for duplicate
		masterId=duplicateInsertByQuery(actheadmaster);
			
		if(masterId!=-1){
			return (-2);
		}

		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		ActHeadMasterModel actheadmasterNew=actheadmaster;
		
		actheadmasterNew.setActhead_name(actheadmaster.getActhead_name().trim());
		actheadmasterNew.setStatus(actheadmaster.getStatus());
		actheadmasterNew.setNotes(actheadmaster.getNotes());
		actheadmasterNew.setCreatedby(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		actheadmasterNew.setCreateddate(new Date());
		actheadmasterNew.setUpdatedby(0);
		actheadmasterNew.setUpdateddate(null);
		actheadmasterNew.setDeletedstatus(0);
		
		
		i=(Integer)session.save(actheadmasterNew);
 
		tx.commit();
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
	
	//Update The data
	public int updateAccounthead(ActHeadMasterModel actheadmaster){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Common common=new Common();
		
		try{
			//check for duplicate
			int masterId=-1;			
		
		    masterId=duplicateUpdateByQuery(actheadmaster);
				
			if(masterId!=-1){
				return (-2);
			}

			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();
			
			ActHeadMasterModel actheadmasterNew=(ActHeadMasterModel) session.get(ActHeadMasterModel.class,actheadmaster.getActhead_id());
	
			actheadmasterNew.setActhead_name(actheadmaster.getActhead_name());
			actheadmasterNew.setRevenuetype(actheadmaster.getRevenuetype());
			actheadmasterNew.setStatus(actheadmaster.getStatus());
			actheadmasterNew.setNotes(actheadmaster.getNotes());
			actheadmasterNew.setCR_DB(actheadmaster.getCR_DB());
			actheadmasterNew.setRemarks(actheadmaster.getRemarks());
			actheadmasterNew.setCreatedby(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			//actheadmasterNew.setSyncupdateddate(actheadmaster.getSyncupdateddate());
			actheadmasterNew.setUpdatedby(0);
			actheadmasterNew.setUpdateddate(new Date());
			actheadmasterNew.setDeletedstatus(0);
			
			session.update(actheadmasterNew);
			i=1;
			
			tx.commit();
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
	
	public ActHeadMasterModel getAccountheadById(int id){
		
		Session session=null;
		Transaction tx=null;
		ActHeadMasterModel actheadmaster=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from ActHeadMasterModel where acthead_id="+id+" and deletedstatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()>0){
			actheadmaster=(ActHeadMasterModel)list.get(0);
		}
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return actheadmaster;
	}
	
	//Delete The data
	public String deleteAccounthead(int id,int userid) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		ActHeadMasterModel accountheadNew=(ActHeadMasterModel)session.get(ActHeadMasterModel.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		
		//User user = (User) request.getSession().getAttribute("user");
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		System.out.println("Error " +usrsessionid);
		System.out.println("UserId>>>>>>>>>>>>>"+ userid);
		accountheadNew.setUpdatedby(userid);
		accountheadNew.setUpdateddate(new Date());
		accountheadNew.setDeletedstatus(1);
		
		
		session.getTransaction().commit();
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
	
	/**
	 * For getting duplicate entry
	 * @param concession
	 * @return
	 */
	public int duplicateInsertByQuery(ActHeadMasterModel actheadmaster){
		int masterId=-1;
		Common common=new Common();
		try{
		    
			
			String qry="from ActHeadMasterModel where acthead_name='"+actheadmaster.getActhead_name().trim()+"'"					
				+" and deletedstatus=0 )";
				
				
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<ActHeadMasterModel> list=query.list();
				 
				 if(list.size()>0){
				 masterId=list.get(0).getActhead_id();
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
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return masterId;
	}
	
	public int duplicateUpdateByQuery(ActHeadMasterModel actheadmaster){
		int masterId=-1;
		Common common=new Common();
		try{		
		
		String qry="from ActHeadMasterModel where acthead_name='"+actheadmaster.getActhead_name().trim()+"'"
				+" and acthead_id!="+actheadmaster.getActhead_id()+
				")";
				
				
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<ActHeadMasterModel> list=query.list();
				 
				 if(list.size()>0){
				  masterId=list.get(0).getActhead_id();
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
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
				
		return masterId;
	}

}
