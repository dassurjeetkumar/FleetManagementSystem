package com.trimax.its.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;

public class UserAction {

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();

		try {
			FileInputStream fstream = new FileInputStream("D:\\textfile.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				System.out.println(strLine);
			}
			in.close();
		} catch (Exception e) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}
		return "success";
	}
	
	public String welcome(){
		
		return "welcome";
	}
	
public String login(){
		
		return "success";
	}
}
