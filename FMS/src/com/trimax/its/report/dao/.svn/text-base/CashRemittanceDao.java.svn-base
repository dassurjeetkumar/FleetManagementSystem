package com.trimax.its.report.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;

public class CashRemittanceDao {

	public JSONObject getDataForCashRemittanceReport(int i,
			HttpServletRequest req, String string, String formattedgivendate1,
			String formattedgivendate2) {
		//Query For Cash Remittance Report..
		Session session =null;
		
		JSONObject result = new JSONObject();
		try{
			session = HibernateUtil.getSession("");
			String sql="select oc.org_name,sum(egt.px_count) pcnt,sum(egt.px_total_amount) ptcnt from bmtcGprs.etim_gprs_ticket egt inner join org_chart oc on egt.depot_id=oc.org_chart_id where STR_TO_DATE(ticket_date, '%d-%m-%Y') between '"+formattedgivendate1+"' and '"+formattedgivendate2+"' and  ticket_date<>'' and ticket_date<>'0' group by egt.depot_id order by oc.org_name;";
			Query query = session.createSQLQuery(sql)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("pcnt", Hibernate.STRING)
					.addScalar("ptcnt", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int x = 0; x < aliasToValueMapList.size(); x++) {
				Map<String, Object> rs = aliasToValueMapList.get(x);
				JSONArray ja = new JSONArray();
				ja.add(x+1);
				ja.add(rs.get("org_name"));
				ja.add(rs.get("pcnt"));
				ja.add(rs.get("ptcnt"));
				array.add(ja);
			}
			result.put("aaData", array);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		//End
		// TODO Auto-generated method stub
		return result;
	}

}
