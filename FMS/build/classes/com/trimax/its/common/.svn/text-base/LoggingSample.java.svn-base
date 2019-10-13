package com.trimax.its.common;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class LoggingSample {
	private static Logger logger=Logger.getLogger("LoggingSample");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
		      FileInputStream fstream = 
		                         new FileInputStream("D:\\textfile.txt");
		      DataInputStream in = 
		                         new DataInputStream(fstream);
		      BufferedReader br = 
		                  new BufferedReader(new InputStreamReader(in));
		      String strLine;
		      while ((strLine = br.readLine()) != null){
		    System.out.println (strLine);
		      }
		      in.close();
		   }catch (FileNotFoundException fe){
		    logger.error("File Not Found",fe);
		        logger.warn("This is a warning message");
		        logger.trace("This message will not be logged since log level is set as DEBUG");
		   }catch (IOException e){
		    logger.error("IOEXception occured:", e);
		  }
	}

}
