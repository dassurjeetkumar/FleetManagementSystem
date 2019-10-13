package com.trimax.its.pis.dao;

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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.trimax.its.model.User;
import com.trimax.its.pis.model.PISCustomer;
import com.trimax.its.util.HibernateUtil;

public class PISCustomerDao {
	public int addPisCustomer(PISCustomer pisCustomer){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		try{

		int masterId=-1;
		
		//check for duplicate
		masterId=duplicateInsertByQuery(pisCustomer);
			
		if(masterId!=-1){
			return (-2);
		}

		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		PISCustomer pisCustomerNew=pisCustomer;
		
		pisCustomerNew.setCustName(pisCustomer.getCustName().trim());
		pisCustomerNew.setCustAddress(pisCustomer.getCustAddress().trim());
		pisCustomerNew.setCustCity(pisCustomer.getCustCity().trim());
		pisCustomerNew.setCustState(pisCustomer.getCustState().trim());
		pisCustomerNew.setCustCountry(pisCustomer.getCustCountry().trim());
		pisCustomerNew.setCustEmail(pisCustomer.getCustEmail().trim());
		pisCustomerNew.setCustWebsite(pisCustomer.getCustWebsite().trim());
		pisCustomerNew.setCustPhone(pisCustomer.getCustPhone().trim());
		pisCustomerNew.setCustCell(pisCustomer.getCustCell().trim());
		pisCustomerNew.setCustContactPersonName(pisCustomer.getCustContactPersonName().trim());
		pisCustomerNew.setCustContactPersonEmail(pisCustomer.getCustContactPersonEmail().trim());
		pisCustomerNew.setCustContactPersonPhone(pisCustomer.getCustContactPersonPhone().trim());
		pisCustomerNew.setStatus(pisCustomer.getStatus());
		pisCustomerNew.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		pisCustomerNew.setCreatedDate(new Date());
		pisCustomerNew.setUpdatedBy(0);
		pisCustomerNew.setUpdatedDate(null);
		pisCustomerNew.setDeletedStatus(0);	
		
		i=(Integer)session.save(pisCustomerNew);
 
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
	
	public int updatePisCustomer(PISCustomer pisCustomer){
		int i=0;
		Session session=null;
		Transaction tx=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		try{
			//check for duplicate
			int masterId=-1;			
		
		    masterId=duplicateUpdateByQuery(pisCustomer);
				
			if(masterId!=-1){
				return (-2);
			}

			session=HibernateUtil.getSession("");
			tx=session.beginTransaction();
			
			PISCustomer pisCustomerNew=(PISCustomer) session.get(PISCustomer.class,pisCustomer.getId());
			
			pisCustomerNew.setCustName(pisCustomer.getCustName().trim());
			pisCustomerNew.setCustAddress(pisCustomer.getCustAddress().trim());
			pisCustomerNew.setCustCity(pisCustomer.getCustCity().trim());
			pisCustomerNew.setCustState(pisCustomer.getCustState().trim());
			pisCustomerNew.setCustCountry(pisCustomer.getCustCountry().trim());
			pisCustomerNew.setCustEmail(pisCustomer.getCustEmail().trim());
			pisCustomerNew.setCustWebsite(pisCustomer.getCustWebsite().trim());
			pisCustomerNew.setCustPhone(pisCustomer.getCustPhone().trim());
			pisCustomerNew.setCustCell(pisCustomer.getCustCell().trim());
			pisCustomerNew.setCustContactPersonName(pisCustomer.getCustContactPersonName().trim());
			pisCustomerNew.setCustContactPersonEmail(pisCustomer.getCustContactPersonEmail().trim());
			pisCustomerNew.setCustContactPersonPhone(pisCustomer.getCustContactPersonPhone().trim());
			pisCustomerNew.setStatus(pisCustomer.getStatus());
			pisCustomerNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			pisCustomerNew.setUpdatedDate(new Date());
			pisCustomerNew.setDeletedStatus(0);	
			
			session.update(pisCustomerNew);
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
	
	
	
	public String deletePisCustomer(int id) {

		Session session=null;
		String s="error";
		try{
			
		session= HibernateUtil.getSession("");
		session.beginTransaction();

		PISCustomer pisCustomerNew=(PISCustomer)session.get(PISCustomer.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		pisCustomerNew.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		pisCustomerNew.setUpdatedDate(new Date());
		pisCustomerNew.setDeletedStatus(1);

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
	
	public int duplicateUpdateByQuery(PISCustomer pisCustomer){
		int masterId=-1;

		try{				
		String qry="from PISCustomer where custName='"+pisCustomer.getCustName().trim()+"'"
				+" and custEmail='"+pisCustomer.getCustEmail()+"'"
				+" and id!="+pisCustomer.getId()
				+" and deletedStatus=0";
		
				if(pisCustomer.getCustPhone()!=null && !pisCustomer.getCustPhone().isEmpty()){
					qry+=" and custPhone='"+pisCustomer.getCustEmail()+"'";
				}
				
				if(pisCustomer.getCustCell()!=null && !pisCustomer.getCustCell().isEmpty()){
					qry+=" and custCell='"+pisCustomer.getCustCell()+"'";
				}
				
				if(pisCustomer.getCustContactPersonEmail()!=null && !pisCustomer.getCustContactPersonEmail().isEmpty()){
					qry+=" and custContactPersonEmail='"+pisCustomer.getCustContactPersonEmail()+"'";
				}
				
				if(pisCustomer.getCustContactPersonPhone()!=null && !pisCustomer.getCustContactPersonPhone().isEmpty()){
					qry+=" and custContactPersonPhone='"+pisCustomer.getCustContactPersonPhone()+"'";
				}
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<PISCustomer> list=query.list();
				 
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
	
	public int duplicateInsertByQuery(PISCustomer pisCustomer){
		int masterId=-1;

		try{
			String qry="from PISCustomer where custName='"+pisCustomer.getCustName().trim()+"'"
					+" and custEmail='"+pisCustomer.getCustEmail()+"'"
					+" and deletedStatus=0";
			
			if(pisCustomer.getCustPhone()!=null && !pisCustomer.getCustPhone().isEmpty()){
				qry+=" and custPhone='"+pisCustomer.getCustEmail()+"'";
			}
			
			if(pisCustomer.getCustCell()!=null && !pisCustomer.getCustCell().isEmpty()){
				qry+=" and custCell='"+pisCustomer.getCustCell()+"'";
			}
			
			if(pisCustomer.getCustContactPersonEmail()!=null && !pisCustomer.getCustContactPersonEmail().isEmpty()){
				qry+=" and custContactPersonEmail='"+pisCustomer.getCustContactPersonEmail()+"'";
			}
			
			if(pisCustomer.getCustContactPersonPhone()!=null && !pisCustomer.getCustContactPersonPhone().isEmpty()){
				qry+=" and custContactPersonPhone='"+pisCustomer.getCustContactPersonPhone()+"'";
			}
				
				Session session=null;
				try{
				 session=HibernateUtil.getSession("");
				 Query query=session.createQuery(qry);
				 List<PISCustomer> list=query.list();
				 
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
	
	public PISCustomer getPisCustomerById(int id){
		
		Session session=null;
		PISCustomer pisCustomer=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PISCustomer where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		if(list.size()>0){
			pisCustomer=(PISCustomer)list.get(0);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return pisCustomer;
	}
	
	public int getTotalRecords(HttpServletRequest request, String col, String dir,String viewdeletedrecord) {
		Session session=null;
		int cnt=0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria ;
			
			if(viewdeletedrecord.equalsIgnoreCase("Y")){
				 criteria = session.createCriteria(PISCustomer.class);
			}else{
				 criteria = session.createCriteria(PISCustomer.class);
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

				conditionGroup.add(Restrictions.like("custName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custAddress", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custCity", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custState", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custCountry", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custEmail", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custWebsite", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custPhone", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custCell", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custContactPersonName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custContactPersonEmail", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custContactPersonPhone", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));

				criteria.add(conditionGroup);
			}
		
			List<PISCustomer> list = criteria.list();
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
				 criteria = session.createCriteria(PISCustomer.class);
			}else{
				 criteria = session.createCriteria(PISCustomer.class);
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

				conditionGroup.add(Restrictions.like("custName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custAddress", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custCity", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custState", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custCountry", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custEmail", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custWebsite", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custPhone", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custCell", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custContactPersonName", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custContactPersonEmail", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("custContactPersonPhone", "%"+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term ,MatchMode.START));

				criteria.add(conditionGroup);
			}
			
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<PISCustomer> list = criteria.list();

			JSONArray array = new JSONArray();

			
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+list.get(i).getId()+ "' value='"+ list.get(i).getId() + "'/>");
				
				ja.add(list.get(i).getId());
				ja.add(list.get(i).getCustName() != null ? list.get(i).getCustName().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustAddress() != null ? list.get(i).getCustAddress().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustCity() != null ? list.get(i).getCustCity().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustState() != null ? list.get(i).getCustState().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustCountry() != null ? list.get(i).getCustCountry().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustEmail() != null ? list.get(i).getCustEmail().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustWebsite() != null ? list.get(i).getCustWebsite().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustPhone() != null ? list.get(i).getCustPhone().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustCell() != null ? list.get(i).getCustCell().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustContactPersonName() != null ? list.get(i).getCustContactPersonName().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustContactPersonEmail() != null ? list.get(i).getCustContactPersonEmail().toString().replaceAll(" ","&nbsp;") : "");
				ja.add(list.get(i).getCustContactPersonPhone()!= null ? list.get(i).getCustContactPersonPhone().toString().replaceAll(" ","&nbsp;") : "");
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
}
