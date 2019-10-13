package com.trimax.its.vehicle.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.trimax.its.util.HibernateUtil;

public class Mainii {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Inside Main");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Vehicle  v join v.makeType  ");
		query.setMaxResults(5);
		try {

			List<Object[]> det = query.list();
			List<Vehicle> vehicleList = new ArrayList<Vehicle>();

			for (int i = 0; i < det.size(); i++) {
				Object[] result = det.get(i);
				Vehicle vehicle = (Vehicle) result[0];
				MakeType makeType = (MakeType) result[1];
				vehicle.setMakeType(makeType);

				vehicleList.add(vehicle);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
