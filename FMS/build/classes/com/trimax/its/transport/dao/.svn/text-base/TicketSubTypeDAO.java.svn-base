package com.trimax.its.transport.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.model.User;
import com.trimax.its.pass.model.PassDurationType;
import com.trimax.its.pass.model.PassSubType;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.transport.model.TicketSubType;
import com.trimax.its.util.HibernateUtil;

public class TicketSubTypeDAO {
	
public int getTotalRecordsForTicketSubType(String string, String sdir) {
		
		int cnt = 0;
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="SELECT count(*) count " +
					"FROM ticket_sub_type inner join ticket_type on ticket_type.ticket_type_id=ticket_sub_type.ticket_type_id" +
					" WHERE ticket_sub_type.deleted_status = '0' and ticket_sub_type.status='ACTIVE' and ticket_type.status='ACTIVE' and	ticket_type.deleted_status='0'";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	public JSONObject getTicketSubTypeList(int total, HttpServletRequest request,String col,String dir) {
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql="SELECT ticket_type.ticket_type_name,ticket_sub_type_id,ticket_sub_type_code,ticket_sub_type_name,ticket_sub_type_name_kannada,ticket_sub_type.ticket_type_id,ticket_sub_type.status,ticket_sub_type.notes " +
					"FROM ticket_sub_type inner join ticket_type on ticket_type.ticket_type_id=ticket_sub_type.ticket_type_id" +
					" WHERE ticket_sub_type.deleted_status = '0' and ticket_sub_type.status='ACTIVE' and ticket_type.status='ACTIVE' and	ticket_type.deleted_status='0'";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (ticket_sub_type_id like '%" + search_term + "%'";
				// sql += " OR gen_logsheet_id like '%"+search_term+"%'";
				// sql +=
				// " OR form_four.schedule_number_name like '"+search_term+"%'";
				 sql += " OR ticket_sub_type_name like '"+search_term+"%')";
				
			}
			//String col = dbcol[index];
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			} else {
				//sql += " order by generated_date desc";
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql)
					.addScalar("ticket_sub_type_id", Hibernate.STRING)
					.addScalar("ticket_sub_type_code", Hibernate.STRING)
					.addScalar("ticket_sub_type_name", Hibernate.STRING)
					.addScalar("ticket_sub_type_name_kannada", Hibernate.STRING)
					.addScalar("ticket_type_name", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("notes", Hibernate.STRING);
					
					

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				int j = i + 1;
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ rs.get("ticket_sub_type_id").toString()
						+ "' value='"
						+ rs.get("ticket_sub_type_id").toString()+ "'/>");
				ja.add(rs.get("ticket_sub_type_id").toString());
				ja.add(rs.get("ticket_sub_type_code").toString());
				ja.add(rs.get("ticket_sub_type_name").toString());
				ja.add(rs.get("ticket_sub_type_name_kannada").toString());
				ja.add(rs.get("ticket_type_name").toString());
				ja.add(rs.get("notes").toString());
				ja.add(rs.get("status").toString());
				

				array.add(ja);

			}
			int cnt = 0;
			result.put("aaData", array);
			// if (search_term1 != null && !search_term1.equals("")) {
			// totalAfterFilter = getTotalRecordsForSeacrch(total, request,col,
			// dir);
			// result.put("aaData", array);
			// result.put("iTotalRecords", totalAfterFilter);
			// result.put("iTotalDisplayRecords",totalAfterFilter);
			// } else {
			totalAfterFilter = getTotalRecordsForTicketSubType( col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	
	}
public String getPassTypeName(String id){
		
		Session session=null;
		Transaction tx=null;
		PassType passType=null;
		String pass_name="";
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassType where pass_type_id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		passType=(PassType)list.get(0);
		pass_name=passType.getPass_type_name();
		System.out.println("passType==="+passType.getPass_type_name());
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		
		return pass_name;
	}

public String getPassSubTypeName(String id){
	
	Session session=null;
	Transaction tx=null;
	PassSubType passType=null;
	String pass_sub_name="";
	
	try{
	session=HibernateUtil.getSession("");
	
	String query="from PassSubType where pass_subtype_id="+id+" and status='Y'";
	
	List list=session.createQuery(query).list();
	
	passType=(PassSubType)list.get(0);
	pass_sub_name=passType.getSub_type_name();
	System.out.println("passType==="+passType.getSub_type_name());
	
	}
	catch(Exception e){
		e.printStackTrace();

	}
	
	return pass_sub_name;
}


	
	
	public int addTicketSubType(TicketSubType ticketsubtype){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from TicketSubType where ticket_sub_type_name='"+ticketsubtype.getTicket_sub_type_name().trim()+"' and deleted_status=0";
				
		List list=session.createQuery(query).list();
		
		if(list.size()<=0){
		
			
		 i=(Integer)session.save(ticketsubtype);
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
	
	
public TicketSubType getTicketSubTypeById(int id){
		
		Session session=null;
		Transaction tx=null;
		TicketSubType ticketsubtype=null;
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from TicketSubType where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		ticketsubtype=(TicketSubType)list.get(0);
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return ticketsubtype;
	}
	
	public int updateTicketSubType(TicketSubType ticketSubType){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String qry="from TicketSubType where ticket_sub_type_name='"+ticketSubType.getTicket_sub_type_name().trim()+"' and deletedStatus=0";			
		
		List<TicketSubType> list=session.createQuery(qry).list();
	
		
		if(list.size()<=0 || list.get(0).getId()==ticketSubType.getId()){
			
			TicketSubType ticketsubTypeNew = (TicketSubType) session.get(TicketSubType.class,ticketSubType.getId());

			String userid=request.getSession().getAttribute("userid").toString();
			
			if(userid!=null || userid.equals("")){
				ticketsubTypeNew.setUpdatedBy(Integer.parseInt(userid));	
			}
			
			ticketsubTypeNew.setUpdatedDate(new Date());
			ticketsubTypeNew.setStatus(ticketSubType.getStatus());
			ticketsubTypeNew.setNotes(ticketSubType.getNotes().trim());
			ticketsubTypeNew.setTicket_sub_type_name(ticketSubType.getTicket_sub_type_name().trim());
			ticketsubTypeNew.setTicket_sub_type_code(ticketSubType.getTicket_sub_type_code().trim());
			ticketsubTypeNew.setTicket_sub_type_name_kannada(ticketSubType.getTicket_sub_type_name_kannada());
			ticketsubTypeNew.setTicket_type_id(ticketSubType.getTicket_type_id());
			
            session.update(ticketsubTypeNew);
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
	
	public String deleteSubType(int id) {

		Session session=null;
		String s="error";
		String status="error";

		try{
			
		session= HibernateUtil.getSession("");
		 DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"subtype",id);
			System.out.println("&&&&&&&&&&"+status);
		if(status.trim().equalsIgnoreCase("")){
		session.beginTransaction();
		TicketSubType ticketType=(TicketSubType)session.load(TicketSubType.class,new Integer(id));

		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		ticketType.setUpdatedDate(new java.util.Date());
		ticketType.setUpdatedBy(user.getUserId());
		ticketType.setDeletedStatus(1);
		ticketType.setStatus("INACTIVE");
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
	

}
