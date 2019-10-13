package com.trimax.its.pis.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class PisUploadFileAction extends ActionSupport implements
ServletRequestAware{
	private String msg;
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private HttpServletRequest servletRequest;
	
	   public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	private File myFile;
	   private String myFileContentType;
	   private String myFileFileName;
  	   private String destPath;


public String showPisUploadFile()
  {
		  return "success";
  }
  
  public String savePisUploadFile()
  {
		 /* Copy file to a safe location */
	    destPath = getFilePath();

	    try{
	   	    	 
	   	 File destFile  = new File(destPath, myFileFileName);
	  	 FileUtils.copyFile(myFile, destFile);

	    }catch(IOException e){
	       e.printStackTrace();
	       msg="Uploading Failed Please Try Again";
	       return "input";
	       
	    }

	    msg=myFileFileName+" Uploaded Successfully to "+destPath;
		System.out.println("end");

	  return "success";
  }
 private String getFilePath(){
	 return null;
 }
  @Override
  public void setServletRequest(HttpServletRequest servletRequest) {
  this.servletRequest = servletRequest;
  }
}
