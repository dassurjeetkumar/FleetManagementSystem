package com.trimax.its.pis.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/*import javax.servlet.http.HttpServletRequest;

 import javax.servlet.http.HttpServletResponse;



 import org.apache.struts2.ServletActionContext;*/

import com.opensymphony.xwork2.ActionSupport;

public class DownloadFileAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream inputStream;
	private String fileName;
	private long contentLength;

	public long getContentLength() {
		return contentLength;
	}

	public String getFileName() {
		return fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String execute() throws FileNotFoundException {

		HttpServletResponse resp = ServletActionContext.getResponse(); // Object
																		// Create
																		// for
																		// Response
		HttpServletRequest req = ServletActionContext.getRequest();
		String filename = req.getParameter("filename");
		System.out.println("File name in action ..........." + filename);

		File file = new File("/root/Downloads/" + filename);

		try {
			// create FileInputStream object
			FileInputStream fin = new FileInputStream(file);

			/*
			 * Create byte array large enough to hold the content of the file.
			 * Use File.length to determine size of the file in bytes.
			 */

			byte fileContent[] = new byte[(int) file.length()];

			System.out.println("file size is :::::::::" + (int) file.length());
			/*
			 * To read content of the file in byte array, use int read(byte[]
			 * byteArray) method of java FileInputStream class.
			 */
			fin.read(fileContent);

			// create string from byte array
			String strFileContent = new String(fileContent);

			System.out.println("File content : ");
			System.out.println(strFileContent);

		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the file " + ioe);
		}

		try {
			com.trimax.its.pis.service.WsPis_Service service = new com.trimax.its.pis.service.WsPis_Service();
			com.trimax.its.pis.service.WsPis port = service.getWsPisPort();
			// TODO initialize WS operation arguments here
			java.lang.String getDir = "Interrante-PopBounce.ogg";
			// TODO process result here
			java.lang.String result = port.getDir(getDir);
			PrintWriter out = resp.getWriter();
			out.println(result);
			System.out.println("result in action >>>>>>>>>>>" + result);
		} catch (Exception ex) {
			// TODO handle custom exceptions here
		}
		return null;
	}

}