package com.trimax.its.vts.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.util.HibernateUtil;

public class ScheduleTripwiseStatausSummaryDOA {
	
	public List getShiftTypeIdName() {
		List list = new ArrayList();
		try {
		String qry = "select shift_type_id,shift_type_name from shift_type " +
				"where status='ACTIVE' and deleted_status=0";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("shift_type_id").toString() + "#"
							+ rs.get("shift_type_name").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}
	


}
