<%@page import="org.hibernate.transform.AliasToEntityMapResultTransformer"%>
<%@ page import="com.trimax.its.util.HibernateUtil" %>
<%@ page import="org.hibernate.Transaction"%>
<%@ page import=" org.hibernate.Session" %>
<%@page import="org.hibernate.Query"%>
<%@page import="java.util.Map"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.ArrayList" %>


<%
if (request.getParameter("depotid") != null) {
	
	int depotid=Integer.parseInt(request.getParameter("depotid"));
	
	//String sqlqry = "select APP_ID,APP_NM from app_master where RECORD_STATUS ='ACTIVE' ";
	String sqlqry="Select org_chart_id,org_name from org_chart where org_chart_id= (select parent_id from org_chart where org_chart_id="+depotid+")";
	System.out.println("sqlqry---"+sqlqry);
	Session session1 = null;
	String result="";
	try {
		session1 = HibernateUtil.getSession("parentorgdetails.jsp");
		session1.beginTransaction();
		Query query = session1.createSQLQuery(sqlqry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			 result =  rs.get("org_chart_id")+"|"+rs.get("org_name");
				System.out.println("result---"+result);	
		}
	} catch (Exception ex) {
		System.out.println(ex);
	} finally {
		if (session1 != null) {
			HibernateUtil.closeSession();
		}
	}
	out.println(result);
	
}

%>