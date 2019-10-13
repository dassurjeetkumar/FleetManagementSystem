package com.trimax.test.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.device.dao.DeviceTypeDao;
import com.trimax.its.usermanagement.dao.UserDetailDAO;
import com.trimax.test.model.UserTest;

public class UserTestAction extends ActionSupport implements Preparable {
	    UserTest UserTest=new UserTest();
	List<UserTest> UserTestlist=new ArrayList<UserTest>();
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	

	    public List<UserTest> getUserTestlist() {
	        return UserTestlist;
	    }

	    public void setUserTestlist(List<UserTest> UserTestlist) {
	        this.UserTestlist = UserTestlist;
	    }


	public UserTest getUserTest() {
	        return UserTest;
	    }

	    public void setUserTest(UserTest UserTest) {
	        this.UserTest = UserTest;
	    }

	   @Override
	public String execute() throws Exception {
	// TODO Auto-generated method stub
	return null;
	} 
	    
	    public UserTestAction() {
	    }
	    public String insertData()
	    {
	    	System.out.println("******************************************1insert data");
			return "success";
		}

		@Override
		public void prepare() throws Exception {
			// TODO Auto-generated method stub
			System.out.println("******************************************1insert dataprepare");
			
			try {
	    		HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				DeviceTypeDao devicedao = new DeviceTypeDao();
				DeviceTypeDao deviceddo = new DeviceTypeDao();
				String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
				String[] dbcol = { "","device_type_id", "device_type_name","notes","status"};
				JSONObject result = new JSONObject();
				int amount = 10;
				int start = 0;
				int col = 0;
				String dir = "asc";
				String sStart = request.getParameter("iDisplayStart");
				String sAmount = request.getParameter("iDisplayLength");
				String sCol = request.getParameter("iSortCol_0");
				String sdir = request.getParameter("sSortDir_0");

				if (sStart != null) {
					start = Integer.parseInt(sStart);
					if (start < 0) {
						start = 0;
					}
				}
				if (sAmount != null) {
					amount = Integer.parseInt(sAmount);
					if (amount < 10 || amount > 50) {
						amount = 10;
					}
				}
				if (sCol != null) {
					col = Integer.parseInt(sCol);
					if (col < 0 || col > 5)
						col = 0;
				}
				if (sdir != null) {
					if (!sdir.equals("asc"))
						dir = "desc";
				}

				String colName = dbcol[col];
				int total = -1;
				//total = .getTotalRecords(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
				total=deviceddo.getTotalRecords(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
				AMOUNT = amount;
				SEARCH_TERM = request.getParameter("sSearch");
				COL_NAME = colName;
				DIR = dir;
				START = start;

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();
				
				
				result=deviceddo.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
				System.out.println("REsult of datatable------>" + result);
				out.print(result);
			
			}
		
		catch(Exception e){
			e.printStackTrace();
		}
		}
	    
	    
//	    public String execute() throws Exception {
//	        throw new UnsupportedOperationException("Not supported yet.");
//	    }

	   
	}


