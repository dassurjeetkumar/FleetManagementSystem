package com.trimax.its.report.dao;


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
import com.trimax.its.util.HibernateUtilVtu;

	public class VehicleLocationDataDao {
	
		Session ses;
		
		
		
		// here we are getting Location
		public String getVehicleLocation(double lat1, double longitude){
			String loc="";
			try{
				 ses=HibernateUtil.getSession("hibernate.cfg.xml");
			Query query2 = ses.createSQLQuery("SELECT bus_stop_name,(((acos(sin(('"+lat1+"'*pi()/180)) *  sin((`latitude_current`*pi()/180))+ " +
					" cos(('"+lat1+"'*pi()/180)) *cos((`latitude_current`*pi()/180)) * cos((('"+longitude+"'- `longitude_current`)* " +
					" pi()/180))))*180/pi())*60*1.1515)*1000 as dist FROM bus_stop bs  where  bs.point_type_id not in (2,13)  order by dist limit 1")
					.addScalar("bus_stop_name");
			loc= (String)query2.uniqueResult();			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			
		}
			return loc;
		
	
}
	}
