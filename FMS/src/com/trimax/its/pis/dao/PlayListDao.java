package com.trimax.its.pis.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.ad.model.Advertisement;
import com.trimax.its.common.Common;
import com.trimax.its.fare.model.LuggageType;
import com.trimax.its.model.User;
import com.trimax.its.pis.model.PISCustomer;
import com.trimax.its.pis.model.PlayList;
import com.trimax.its.transport.model.ScheduleService;
import com.trimax.its.util.HibernateUtil;

public class PlayListDao {
	public int addPlayList(PlayList playList){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		try{

		int masterId=-1;
		
		//check for duplicate
		masterId=duplicateInsertByQuery(playList);
			
		if(masterId!=-1){
			return (-2);
		}

		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();		
		
		String[] advertList=playList.getAdvertisementList().split(",");
		
		Common common=new Common();
		
		for(int k=0;k<advertList.length;k++){
			PlayList playListNew=new PlayList();
			
			playListNew.setPlayListName(playList.getPlayListName().trim());		
			playListNew.setPlayStartTime(common.formatDate(playList.getPlayStartTime(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"));
			System.out.println("playList.getPlayEndTime()=="+playList.getPlayEndTime());
			if(playList.getPlayEndTime().trim()!="" || playList.getPlayEndTime()!=null){
			playListNew.setPlayEndTime(playList.getPlayEndTime()!=null ? common.formatDate(playList.getPlayEndTime(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"):null);
			}else{
				playListNew.setPlayEndTime(null);
			}
			playListNew.setStatus(playList.getStatus());
			playListNew.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			playListNew.setCreatedDate(new Date());
			playListNew.setUpdatedBy(0);
			playListNew.setUpdatedDate(null);
			playListNew.setDeletedStatus(0);
			int advId=Integer.parseInt(advertList[k].trim());
			//playListNew.getAdvertisement().setAdvertisement_id(advId);
			Advertisement adv=new Advertisement();
			adv.setAdvertisement_id(advId);
			playListNew.setAdvertisement(adv);
			i=(Integer)session.save(playListNew);
		}

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
	
	
	public int updatePlayList(PlayList playList){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Common common=new Common();
		try{
			//check for duplicate
			int masterId=-1;			
		
		    masterId=duplicateUpdateByQuery(playList);
				
			if(masterId!=-1){
				return (-2);
			}

			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();
			
			PlayList playListNew=(PlayList) session.get(PlayList.class,playList.getId());
			
			playListNew.setPlayListName(playList.getPlayListName().trim());		
			playListNew.setPlayStartTime(common.formatDate(playList.getPlayStartTime(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"));
			playListNew.setPlayEndTime(playList.getPlayEndTime()!=null ? common.formatDate(playList.getPlayEndTime(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"):null);
			
			playListNew.setStatus(playList.getStatus());
			playListNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			playListNew.setUpdatedDate(new Date());
			playListNew.setDeletedStatus(0);	

			playListNew.setAdvertisement(playList.getAdvertisement());
			
			
			session.update(playListNew);
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
	
	
	
	public String deletePlayList(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		PlayList playListNew=(PlayList)session.get(PlayList.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		playListNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		playListNew.setUpdatedDate(new Date());
		playListNew.setDeletedStatus(1);

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
	
	public int duplicateUpdateByQuery(PlayList playList){
		int masterId=-1;

		try{				
		String qry="from PlayList where playListName='"+playList.getPlayListName().trim()+"'"
				+" and id!="+playList.getId()
				+" and deletedStatus=0";
		
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<PlayList> list=query.list();
				 
				 if(list.size()>0){
				  masterId=list.get(0).getId();
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
	
	public int duplicateInsertByQuery(PlayList playList){
		int masterId=-1;

		try{
			String qry="from PlayList where playListName='"+playList.getPlayListName().trim()+"'"
					+" and deletedStatus=0";
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<PlayList> list=query.list();
				 
				 if(list.size()>0){
				 masterId=list.get(0).getId();
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
	
	public PlayList getPlayListById(int id){
		
		Session session=null;
		PlayList playList=null;
		Common common=new Common();
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PlayList where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()>0){
			playList=(PlayList)list.get(0);
			playList.setPlayStartTime(common.formatDate(playList.getPlayStartTime(), "yyyy-MM-dd HH:mm:ss","dd/MM/yyyy HH:mm"));
			playList.setPlayEndTime(playList.getPlayEndTime()!=null?common.formatDate(playList.getPlayEndTime(), "yyyy-MM-dd HH:mm:ss","dd/MM/yyyy HH:mm"):"");
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return playList;
	}
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		Session session=null;
		int cnt=0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(LuggageType.class);

			//criteria.add(Restrictions.ne("deletedStatus", 1));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(PlayList.class);
			}else{
				 criteria = session.createCriteria(PlayList.class);
			     criteria.add(Restrictions.ne("deletedStatus", 1));
			}


			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("id"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("playListName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("advertisement.advertisement_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("playStartTime", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("playEndTime", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));

				criteria.add(conditionGroup);
			}
		
			List<LuggageType> list = criteria.list();
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


	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Session session=null;

		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria ;
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(PlayList.class);
			}else{
				 criteria = session.createCriteria(PlayList.class);
			     criteria.add(Restrictions.ne("deletedStatus", 1));
			}


			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("id"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("playListName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("advertisement.advertisement_name", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("playStartTime", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("playEndTime", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));

				criteria.add(conditionGroup);
			}
			
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<PlayList> list = criteria.list();

			JSONArray array = new JSONArray();
			Common common=new Common();
			
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+list.get(i).getId()+ "' value='"+ list.get(i).getId() + "'/>");
				
				ja.add(list.get(i).getId());
				ja.add(list.get(i).getPlayListName() != null ? list.get(i).getPlayListName().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getAdvertisement().getAdvertisement_name() != null ? list.get(i).getAdvertisement().getAdvertisement_name().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getPlayStartTime() != null ? common.formatDate(list.get(i).getPlayStartTime().toString(),"yyyy-MM-dd HH:mm:ss","dd/MM/yyyy HH:mm:ss") : "");
				ja.add(list.get(i).getPlayEndTime() != null ? common.formatDate(list.get(i).getPlayEndTime().toString(),"yyyy-MM-dd HH:mm:ss","dd/MM/yyyy HH:mm:ss") : "");
				ja.add(list.get(i).getStatus());
				
				if(viewdeletedrecord.equalsIgnoreCase("Y")){
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1){
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getId()+"'/>Deleted Record");	
					}else{
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getId()+"'/>Record in Database");	
					}					
				}
				array.add(ja);
			}

			totalAfterFilter = total;
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
	
	public Map<Integer,String> getAdvertisementList() {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Advertisement where status='Active' and deleted_status=0 order by advertisement_name");
		List<Advertisement> list = query.list();

		//resultMap.put(0, "Select");
		for (Advertisement type : list) {
			resultMap.put(type.getAdvertisement_id(), type.getAdvertisement_name());
		}
		return resultMap;

	}
	
	public List getAdvertisementList2() {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Advertisement where status='Active' and deleted_status=0 order by advertisement_name");
		List<Advertisement> list = query.list();

		return list;

	}
}
