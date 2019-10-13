package com.trimax.its.Conversion;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.opensymphony.xwork2.ActionSupport;

import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;

public class NudiToKannadaConverter extends ActionSupport {
	
	
	private List<BusStops> busstoplist;

	
	

	public List<BusStops> getBusstoplist() {
		return busstoplist;
	}

	public void setBusstoplist(List<BusStops> busstoplist) {
		this.busstoplist = busstoplist;
	}

	public String execute() {
		System.out.println("in execute");
		//this.ServiceTaxCollections();
		this.setBusstoplist(getBusidlistdata());
		return "success";
	}
	
	public List<BusStops> getBusidlistdata() {
		List<BusStops> busstoplist = null;
		Session session = null;
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select bus_stop_id as id,bus_stop_name_nudi as busStopNamenudi from bus_stop where status='Approved' and bus_stop_name_nudi IS NOT NULL;";
         Query query = session.createSQLQuery(sql)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("busStopNamenudi", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(BusStops.class));
		busstoplist = new ArrayList<BusStops>();
		List<Object> det = query.list();
		BusStops initialDevice = new BusStops();
		initialDevice.setBusStopNamenudi("Select");
		initialDevice.setId(0);
		busstoplist.add(initialDevice);

		for (int i = 0; i < det.size(); i++) {

			BusStops device = (BusStops) det.get(i);
			busstoplist.add(device);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			return busstoplist;
		}
	}

}
