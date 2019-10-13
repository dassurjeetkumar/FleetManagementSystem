package com.trimax.its.lookup.sync;

import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import com.trimax.its.logger.TrimaxLogger;

public class AutoGenerateComplaint implements ServletContextListener {
	Logger logger = TrimaxLogger.getTrimaxLogger();
	private static ResourceBundle resorceBundle = ResourceBundle.getBundle("com.trimax.its.lookup.sync.sync");
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		JobDetail job2 = JobBuilder.newJob(ComplaintSchedular.class).build();
		try{
			String cronTimeSchedularTime = resorceBundle.getString("cron_time_expression_for_complaint_creation");
			if(!cronTimeSchedularTime.equals("")){
				Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cronTimeSchedularTime)).build();  
				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(job2,trigger);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			logger.error("Processing Error in Auto Comaplaint generation "+String.valueOf(new Date()), e);
		}
		
		
	}    

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
	

}
