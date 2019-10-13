package com.trimax.its.logger;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
//import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.FileAppender;
/**
 * 
 * This class is used for Log 
 *
 */
public class TrimaxLogger extends Logger{
	public static Logger logger=null;	
	private TrimaxLogger()
	{ 
		super("Trimax LOGGER");			
	} 	
	
	public static Logger getTrimaxLogger()
	{			
		Calendar cal=java.util.Calendar.getInstance();
		cal.setTime(new Date());
	    String todayDate =(cal.get(Calendar.YEAR)+"_"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.DATE));	    
 	    ResourceBundle db=ResourceBundle.getBundle("com.trimax.its.properties.general");
 	   File file=null;
 	  file = new File(db.getString("commonLogFilePath")+"/"+(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE)));
		 if(file.exists()==false)
			 file.mkdirs();
			
		 
		 File f = new File(db.getString("commonLogFilePath")+"/"+(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE))+"/Trimax_log"+todayDate+".html");	  
			 
		  if(f.exists()== false || logger==null)
		  {
			try{	
				
			  	Logger log = Logger.getLogger("Trimax LOGGER");
			 MyHTMLLayout layout = new MyHTMLLayout();
		   		//layout.setLocationInfo(true);
			 FileAppender appender = new FileAppender(layout,db.getString("commonLogFilePath")+"/"+(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE))+"/Trimax_log"+todayDate+".html",true);
		 	   log.removeAllAppenders();
		 	  log.addAppender(appender);		   	  
		 	 log.setLevel(Level.DEBUG);
		 	 System.out.println(":::::::::::LOGGER INITIALIZED WITH NEW DATE FILE::::::::::::");
		 	logger=log;
		       
		 	logger.info("LOGGER INITIALIZED WITH NEW DATE FILE::"+new Date()); 
		 	
//		 	delete old files old then 15 days or 1 month
			if(cal.get(Calendar.DATE)>15)
   	    	{
		   		File toDeleteFolder = null;
		   		if(cal.get(Calendar.MONTH)+1==1)
		   		{
		   			toDeleteFolder = new File(db.getString("commonLogFilePath")+"/12");	
		   			System.out.println("TO DELETE FOLDER"+db.getString("commonLogFilePath")+"/12");
		   		}else{
		   			toDeleteFolder = new File(db.getString("commonLogFilePath")+"/"+(cal.get(Calendar.MONTH)));
		   			System.out.println(db.getString("commonLogFilePath")+"/"+(cal.get(Calendar.MONTH)));
		   		}
		   		
	   			if(toDeleteFolder.exists())
		   		{
		   			
		   			File[] arr_file=toDeleteFolder.listFiles();
		   			for(int i=0;i<arr_file.length;i++)
		   			{
		   				arr_file[i].delete();
		   			}
		   			toDeleteFolder.delete();
		   			System.out.println("FOLDER DELETED SUCCESSFULLY");
		   		}
		   		
   	    	}
		 	
		       
		   	 } catch (Exception fe) {   System.out.println("LOGGER NOT INITILIZED::"+fe);     }
		  }	   		
		return logger;
	}
        
        
        
        
        
}
