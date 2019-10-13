package com.trimax.its.transport.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;

public class TestAction {

	public static void main(String[] args) {
		TestAction action = new TestAction();
	//	double dist = action.distanceBetweenTwoLocationsInKm(12.0, 77.0, 13.0,78.0);
		String dist = action.getStopList(13.01859398,77.53189635);
//		System.out.println("dist" + dist);
	}
	List<BusStops> list;

	public List<BusStops> getList() {
		return list;
	}

	public void setList(List<BusStops> list) {
		this.list = list;
	}
	
	public String getStopList(Double latitudeOne,Double longitudeOne){
		double para=0.02;
		double factor=1.2;
		double dist=-0.0;
		double lastdist=-0.0;
		int id=0; String name="";
		TestAction action = new TestAction();
		list = action.getBusStopList( latitudeOne.toString(), longitudeOne.toString(), para);
		 for(int j=0; j<10;j++){
			 if(list.size()>0){
				 break;
			 }
			 if(list.size()==0){
				 //factor=factor
				 para=para*factor ;
			 }
			 if(list.size()>175){
				 para=para/factor ;
			 }
			 list = action.getBusStopList( latitudeOne.toString(), longitudeOne.toString(), para);
		 }
		 //System.out.println("list.size()"+list.size());
		 for(int i=0;i<list.size();i++){
		double calcdist = action.distanceBetweenTwoLocationsInKm(latitudeOne, longitudeOne, list.get(i).getLatitude(),list.get(i).getLongitude());
		//System.out.println("calcdist"+calcdist);
		if(i>0){
			int compres=Double.compare(calcdist, dist);
		if(compres<0){
			
			dist=calcdist;
			id=list.get(i).getId();
		}else{
			dist=dist;
		}
		}else{
			dist=calcdist;
			lastdist=calcdist;
			id=list.get(i).getId();
			name = list.get(i).getBusStopName();
		}
		//System.out.println(dist+"========"+id);
		 }
		 return name;
	}

	public Double distanceBetweenTwoLocationsInKm(Double latitudeOne,Double longitudeOne, Double latitudeTwo, Double longitudeTwo) {
		if (latitudeOne == null || latitudeTwo == null || longitudeOne == null || longitudeTwo == null) {
			return null;
		}
		
		

		Double earthRadius = 6371.0;
		Double diffBetweenLatitudeRadians = Math.toRadians(latitudeTwo- latitudeOne);
		Double diffBetweenLongitudeRadians = Math.toRadians(longitudeTwo- longitudeOne);
		Double latitudeOneInRadians = Math.toRadians(latitudeOne);
		Double latitudeTwoInRadians = Math.toRadians(latitudeTwo);
		Double a = Math.sin(diffBetweenLatitudeRadians / 2)
				* Math.sin(diffBetweenLatitudeRadians / 2)
				+ Math.cos(latitudeOneInRadians)
				* Math.cos(latitudeTwoInRadians)
				* Math.sin(diffBetweenLongitudeRadians / 2)
				* Math.sin(diffBetweenLongitudeRadians / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return (earthRadius * c);
	}

	public List<BusStops> getBusStopList(String lat, String lag,double para) {
		List<BusStops> list = null;
		double lat1 = 0.0;
		double lag1=0.0;
		if(lat.length()>4){
			lat1 = Double.parseDouble(lat.substring(0, 5));
		}else{
			lat1 = Double.parseDouble(lat);
		}
		
		if(lag.length()>4){
			lag1 = Double.parseDouble(lag.substring(0, 5));
		}else{
			lag1 = Double.parseDouble(lag);
		}
		double latmin = lat1 - para;
		double latmax = lat1 + para;
		double lngmin = lag1 - para - 0.01;
		double lngmax = lag1 + para + 0.01;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		try {
			query = session
					.createQuery("from BusStops where status in ('New','Approved', 'Inactive')  and updated_by!='0' and ((latitude_current	between '"
							+ latmin
							+ "'and '"
							+ latmax
							+ "') and (longitude_current between '"
							+ lngmin
							+ "'and '"
							+ lngmax
							+ "')) ");

			list = (List<BusStops>) query.list();
		} catch (Exception e) {
			System.out.println(e);
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

}
