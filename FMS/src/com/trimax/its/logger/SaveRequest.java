package com.trimax.its.logger;



import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import com.trimax.its.common.StringUtil;

public class SaveRequest {
	

	public static synchronized void save(HttpServletRequest request)
	{
		Calendar cal=java.util.Calendar.getInstance();
		cal.setTime(new Date());
	    String todayDate =(cal.get(Calendar.YEAR)+"_"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.DATE)+"_"+cal.get(Calendar.HOUR_OF_DAY)+"_"+cal.get(Calendar.MINUTE)+"_"+cal.get(Calendar.SECOND));	    
 	  	ResourceBundle rb=ResourceBundle.getBundle("com.trimax.its.properties.general");
 	  	
		File file=null;
		try{
		  file = new File(rb.getString("XMLErrorDataFilePath")+"/"+(cal.get(Calendar.MONTH)+1));
		 if(file.exists()==false)
			 file.mkdirs();
			 //System.out.println("DIRECTORY CRETED:::"+file.mkdirs());
		 file = new File(rb.getString("XMLErrorDataFilePath")+"/"+(cal.get(Calendar.MONTH)+1)+"/trimax_data"+todayDate+".xml");
		 if(file.exists()==false)
			 file.createNewFile();
			 //System.out.println("DIRECTORY CRETED:::"+file.createNewFile());	
		 
		 write(createDocument(request),file);
		 
//		 	delete old files old then 15 days or 1 month
			if(cal.get(Calendar.DATE)>15)
	    	{
		   		File toDeleteFolder = null;
		   		if(cal.get(Calendar.MONTH)+1==1)
		   		{
		   			toDeleteFolder = new File(rb.getString("XMLErrorDataFilePath")+"/12");		   
		   			System.out.println("TO DELETE FOLDER"+rb.getString("XMLErrorDataFilePath")+"/12");
		   		}else{
		   			toDeleteFolder = new File(rb.getString("XMLErrorDataFilePath")+"/"+(cal.get(Calendar.MONTH)));
		   			System.out.println(rb.getString("XMLErrorDataFilePath")+"/"+(cal.get(Calendar.MONTH)));
		   		}
		   		
		   		if(toDeleteFolder.exists())
		   		{
		   			
		   			File[] arr_file=toDeleteFolder.listFiles();
		   			for(int i=0;i<arr_file.length;i++)
		   			{
		   				arr_file[i].delete();
		   			}
		   			toDeleteFolder.delete();
		   			System.out.println("FORLDER DELETED SUCCESSFULLY");
		   		}
		   		
	    	}
		 
		}catch(Exception e){e.printStackTrace();}
		//System.out.println("path:::::::"+path);
		
		
	}
	 public static Document createDocument(HttpServletRequest request) {
		 //System.out.println("OK IN createDocument");
	        Document document = DocumentHelper.createDocument();
	        //System.out.println("ENCODING::::"+document.getXMLEncoding());
	       // document.setXMLEncoding("Shift_JIS"); 
	        //System.out.println("ENCODING::::"+document.getXMLEncoding());
	        Element root = document.addElement( "root" );	       
	        
	        Element elRequest = root.addElement( "request" );
	        Element elRequestParam = elRequest.addElement("Parameter");
			Enumeration paramEnum = request.getParameterNames();
			while(paramEnum.hasMoreElements())
			{
				String attName=paramEnum.nextElement().toString();
				if(request.getParameter(attName)!=null)
					elRequestParam.addElement(attName).addText(StringUtil.convert(request.getParameter(attName).toString()));
				else
					elRequestParam.addElement(attName).addText("null");				
				//System.out.println("Request Parameter Name:: "+attName);
				//System.out.println("Request Parameter Value:: "+request.getParameter(attName));			
			}
			
			
	        Element elRequestAtt = elRequest.addElement("Attribute");
			Enumeration attEnum = request.getAttributeNames();
			while(attEnum.hasMoreElements())
			{
				String attName=attEnum.nextElement().toString();
				if(request.getAttribute(attName)!=null)
					elRequestAtt.addElement(attName).addText(StringUtil.convert(request.getAttribute(attName).toString()));
				else
					elRequestAtt.addElement(attName).addText("null");				
				//System.out.println("Request Attribute Name:: "+attName);
				//System.out.println("Request Attribute Value:: "+request.getAttribute(attName));			
			}			
	        
	        Element elSession = root.addElement( "session" );
	        Element elSessionAtt = elSession.addElement("Attribute");
	        HttpSession session = request.getSession();
			Enumeration attEnumSession = session.getAttributeNames();
			while(attEnumSession.hasMoreElements())
			{
			
				String attName=attEnumSession.nextElement().toString();
				if(session.getAttribute(attName)!=null)
					elSessionAtt.addElement(attName).addText(StringUtil.convert(session.getAttribute(attName).toString()));
				else
					elSessionAtt.addElement(attName).addText("null");
				//System.out.println("Session Attribute Name:: "+attName);
				//System.out.println("Session Attribute Value:: "+session.getAttribute(attName));			
			}
			
			
			
	       
	   

	        return document;
	    }
	 public static void write(Document document,File file)  {
		 try{
			// System.out.println("OK IN WRITE");	       			 
	        XMLWriter writer = new XMLWriter( new FileWriter( file ));
	        writer.write( document );
	        writer.flush();
	        writer.close();
	        
	   
		  }catch(Exception e){e.printStackTrace();}

	    }
}
