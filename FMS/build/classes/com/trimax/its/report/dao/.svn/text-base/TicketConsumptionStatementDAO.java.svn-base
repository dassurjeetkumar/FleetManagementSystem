package com.trimax.its.report.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.report.model.TicketConsumptionStatementModel;
import com.trimax.its.util.HibernateUtil;

public class TicketConsumptionStatementDAO {
	TicketConsumptionStatementModel modelObject = new TicketConsumptionStatementModel();

	@SuppressWarnings("finally")
	public List<Map<String,String>> getDivisionList(){
		List<Map<String,String>> divisionList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> aliasToValueMpalIst = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession("");
			String queryForDivisionLsit = "SELECT org_name,org_chart_id FROM org_chart oc INNER JOIN org_type ot ON ot.org_type_id = oc.org_type_id " +
					" WHERE ot.org_type like '%division%'";
			aliasToValueMpalIst = session.createSQLQuery(queryForDivisionLsit).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).list();
			/*for(int i=0; i<aliasToValueMpalIst.size();i++){
				Map<String,String> rs = aliasToValueMpalIst.get(i);
				
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return aliasToValueMpalIst;
		}
	}
	
	public TicketConsumptionStatementModel getTicketConsumptionStatement(){
		TicketConsumptionStatementModel modelObject = new TicketConsumptionStatementModel();
		return modelObject;
	}
}
