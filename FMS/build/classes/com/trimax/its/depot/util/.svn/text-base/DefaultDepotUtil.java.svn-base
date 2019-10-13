package com.trimax.its.depot.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.trimax.its.vts.dao.VtsDataDAO;

public class DefaultDepotUtil {
	
	public Map<Integer, String> getDefaultDepotUtil() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String orgtypeid = (String) request.getSession().getAttribute("orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute("orgchartid");
		
		String id = "!=0";
		if ("1".equals(orgtypeid)) {
			id = "org_type_id=2 and org_chart_id!=0";
		}else if ("2".equals(orgtypeid)) {
			id = "org_chart_id in ('"+ orgchartid + "')";
		} else if ("3".equals(orgtypeid)) {
			id = "org_chart_id in (select parent_id from org_chart where org_chart_id='"
					+ orgchartid + "')";
		} 
		
		return getDivision(orgtypeid,id);
	}
	
	public Map<Integer, String> getDivision(String orgtypeid,String id){
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		return vvt.getDivision(orgtypeid,id);
	}

}
