package com.trimax.its.lookup.sync;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.trimax.its.logger.TrimaxLogger;

public class ComplaintSchedular implements Job{

	Logger logger = TrimaxLogger.getTrimaxLogger();
	
	@Override   
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
			
		try{
			logger.debug("Complaint Creation Started");
			ComplaintDao daoObject = new ComplaintDao();
			logger.debug("Complaint Creation Finished \n"+daoObject.createComplaintAuto(new Date()));
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Processing Error in Complaint Creation ", e);
		}
	}   

	}