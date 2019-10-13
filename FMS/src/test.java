import org.hibernate.Session;

import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;


public class test {
 public static void main(String [] args){
	 
	 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Schedule schedule = new Schedule();
		schedule = (Schedule) session.get(Schedule.class, 764);
		System.out.println("Brand Type -----> "+schedule.getBrand().getBrand_type_name());
 }
}
