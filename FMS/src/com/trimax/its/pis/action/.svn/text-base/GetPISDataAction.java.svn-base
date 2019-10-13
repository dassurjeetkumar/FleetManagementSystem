package com.trimax.its.pis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.Action;
import com.trimax.its.pis.dao.PISDao;
import com.trimax.its.pis.service.GetPIS;

public class GetPISDataAction implements Action {

	List<com.trimax.its.pis.service.GetPIS> list = null;

	public List<com.trimax.its.pis.service.GetPIS> getList() {
		return list;
	}

	public void setList(List<com.trimax.its.pis.service.GetPIS> list) {
		this.list = list;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "null" })
	// Method For Getting PIS Data
	public String getPISData() throws IOException, ParseException {
		HttpServletResponse resp = ServletActionContext.getResponse(); // Object
																		// Create
																		// for
																		// Response
		HttpServletRequest req = ServletActionContext.getRequest(); // Object
																	// Create
																	// for
																	// Request
		String location = req.getParameter("loc");
		@SuppressWarnings("rawtypes")
		String platform = req.getParameter("lpf");
		JSONObject result = new JSONObject(); // Object For JSON
		JSONArray array = new JSONArray(); // Object For JSONArray
		PISDao dao = new PISDao(); // Object OF DAO create here
		list = dao.getData(location, platform); // DAO Method Calling here
		for (int i = 0; i < list.size(); i++) {
			JSONArray ja = new JSONArray();
			ja.add(0, list.get(i).getPlateformNumber());
			ja.add(1, list.get(i).getROUTEID());
			ja.add(2, list.get(i).getVehicleNumber());
			ja.add(3, list.get(i).getSCHEDULENO());
			ja.add(4, list.get(i).getETA());
			ja.add(5, list.get(i).getETD());
			array.add(ja);
		}
		result.put("pisData",array );
		PrintWriter out = resp.getWriter();
		out.println(result);
		return null;
	} // End Method OF GetPISDATA
	public String getDownload() {
		return null;
	}
	public String getLoc() {
		JSONObject json = new JSONObject();
		try {
			// Send JSON responce to requested Ajax
			HttpServletResponse resp = ServletActionContext.getResponse();
			PISDao pisDao = new PISDao();
			json = pisDao.getPisLocations();
			PrintWriter out = resp.getWriter();
			out.print(json);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String getPf() {
		JSONObject json = new JSONObject();
		try {
			// Send JSON responce to requested Ajax
			HttpServletResponse resp = ServletActionContext.getResponse();
			// Get parameters from requested URL
			HttpServletRequest req = ServletActionContext.getRequest();
			int endstopId=Integer.parseInt(req.getParameter("loc"));
			PISDao pisDao = new PISDao();
			json = pisDao.getPisPlatformLocations(endstopId);
			PrintWriter out = resp.getWriter();
			out.print(json);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
