package com.trimax.its.pass.dao;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.model.User;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.util.HibernateUtil;

public class PassTypeDao {
	
	public int addPassType(PassType passType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from PassType where pass_type_name='"+passType.getPass_type_name().trim()+"' and deleted_status=0";
				
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		
			
		 i=(Integer)session.save(passType);
		}else{
			i=-1;
		}
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return i;
	}
	
	public int updatePassType(PassType passType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from PassType where pass_type_name='"+passType.getPass_type_name().trim()+"' and deleted_status=0";			
		
		List<PassType> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getPass_type_id()==passType.getPass_type_id()){
			
			PassType passTypeNew = (PassType) session.get(PassType.class,passType.getPass_type_id());

			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				passTypeNew.setUpdated_by(Integer.parseInt(userid));	
			}
			
			passTypeNew.setUpdated_date(new Date());
			passTypeNew.setStatus(passType.getStatus());
			passTypeNew.setNote(passType.getNote().trim());
			passTypeNew.setPass_type_name(passType.getPass_type_name().trim());
			
            session.update(passTypeNew);
            i=1;
		}else{
			i=-1;
		}
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			i=0;
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return i;
	}
	
	public PassType getPassTypeById(int id){
	
		Session session=null;
		Transaction tx=null;
		PassType passType=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassType where pass_type_id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		passType=(PassType)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return passType;
	}
	
	public String deletePassType(int id) {

		Session session=null;
		String s="error";
		String status="error";

		try{
			
		session= HibernateUtil.getSession("");
		 DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"passtype",id);
			System.out.println("&&&&&&&&&&"+status);
		if(status.trim().equalsIgnoreCase("")){
		session.beginTransaction();
		PassType passType=(PassType)session.load(PassType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		passType.setUpdated_date(new java.util.Date());
		passType.setUpdated_by(user.getUserId());
		passType.setDeleted_status(1);
		s="success";
		session.getTransaction().commit();
		}
		}
		catch(Exception ex){
			status="error:";
			ex.printStackTrace();
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
		}

		return status;
	}
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		int cnt=0;
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
		//	Criteria criteria = session.createCriteria(PassType.class);
			//
		//	criteria.add(Restrictions.ne("deleted_status", 1));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(PassType.class);
			}else{
				 criteria = session.createCriteria(PassType.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
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

				conditionGroup.add(Restrictions.like("pass_type_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status",search_term,MatchMode.START ));
			
				criteria.add(conditionGroup);

			}

			List<PassType> list = criteria.list();
		cnt = list.size();
		}
		catch(Exception e){
			
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Session session = null;
		try {

			int totalAfterFilter = total;

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(PassType.class);
			//
			//criteria.add(Restrictions.ne("deleted_status", 1));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(PassType.class);
			}else{
				 criteria = session.createCriteria(PassType.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}

			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}else{
				criteria.addOrder(Order.asc("pass_type_id"));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("pass_type_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status",search_term,MatchMode.START ));
			
				criteria.add(conditionGroup);

			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			List<PassType> list = criteria.list();
			JSONArray array = new JSONArray();

			Common common=new Common();
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getPass_type_id()
						+ "' value='"
						+ list.get(i).getPass_type_id() + "'/>");
				ja.add(list.get(i).getPass_type_id());//fare chart master id
				ja.add(list.get(i).getPass_type_name() != null ? list.get(i).getPass_type_name().toString().replaceAll(" ","&nbsp;") : "");
					
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getNote() != null ? list.get(i).getNote().toString() : "");
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getPass_type_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getPass_type_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				//System.out.println("Array----->" + array);
			}

			totalAfterFilter = total;//getTotalRecords(request,col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

//			System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
		}
		return result;
	}
}
