package com.trimax.its.ad.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.ad.dao.AdMannagementDao;
import com.trimax.its.ad.model.Advertisement;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.pis.dao.PisDisplayUnitDao;
import com.trimax.its.pis.dao.PisScrollMessageDao;

public class AdMannagementAction extends ActionSupport implements
		ServletRequestAware {
	private Advertisement advetisement;
	private String msg;
	private String requestType;
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private String destPath;
	private Map<Integer, String> customerlist;
	
	private Map<Integer, String> advertisementlist;
	public Map<Integer, String> getAdvertisementlist() {
		return advertisementlist;
	}

	public void setAdvertisementlist(Map<Integer, String> advertisementlist) {
		this.advertisementlist = advertisementlist;
	}

	public Map<Integer, String> getCustomerlist() {
		return customerlist;
	}

	public void setCustomerlist(Map<Integer, String> customerlist) {
		this.customerlist = customerlist;
	}

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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Advertisement getAdvetisement() {
		return advetisement;
	}

	public void setAdvetisement(Advertisement advetisement) {
		this.advetisement = advetisement;
	}

	public String viewAdMannagement() {
		return "success";
	}

	public String createAdMannagement() {
		return "success";
	}

	public String saveAdMannagement() {
  
		String advetisementName = advetisement.getAdvertisement_name();
		int advertisementtypeid = advetisement.getAdvetisetype()
				.getAdvertisement_type_id();
		int customerid = advetisement.getCust().getId();
		int duration = advetisement.getDuration();
		int frequency = advetisement.getFrequency();
		String statrdate = advetisement.getStart_date();
		
		if (("".equals(advetisementName)) || (advetisementName == null)
				|| (advetisementName.length() == 0)) {

			addFieldError("advertisement_name",
					"Advertisement Name is Manadatory");
			return "input";

		}
		if (("".equals(statrdate)) || (statrdate == null)
				|| (statrdate.length() == 0)) {

			addFieldError("startdate",
					"Advertisement Statrt Date is Manadatory");
			return "input";

		}
		if (duration == 0) {

			addFieldError("duration", "Advertisement Duration is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (frequency == 0) {
			
			addFieldError("frequency", "Advertisement Frequency is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		
		if (advetisement.getAmount()==0) {			
			addFieldError("amount", "Advertisement Amount is Manadatory");
			return "input";
		}
		
		if (advetisement.getAirtime()==null || advetisement.getAirtime().trim().isEmpty()) {
			
			addFieldError("airtime", "Advertisement Airtime is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		
		
		
		CommonValidation cm = new CommonValidation();
		if (!cm.isSpecialChar(advetisementName)) {
			addFieldError("advertisement_name",
					"Advertisement Name is Should be a alphanumeric");
			return "input";
		}
		if (customerid == 0) {

			addFieldError("cust_id",
					"Advertisement Customer Name is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		String date1 = advetisement.getStart_date();
		String date2 = advetisement.getEnd_date();

		try {
			if (advetisement.getEnd_date().trim().length() > 0) {

				// validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date effstdate = sdf.parse(advetisement.getStart_date());
				Date effenddate = sdf.parse(advetisement.getEnd_date());

				if (effstdate.compareTo(effenddate) > 0) {

					addActionError("End Date should be greater than Start Date");

					return INPUT;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
		if (advertisementtypeid == 0) {

			addFieldError("advertisement_type_id",
					"Advertisement Type Name is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (myFile == null) {
			addFieldError("myfile", "Please Upload  File");
			return "input";
		}
		long size = myFile.length();
		long sizeinmb = size / 1048576;
		if (sizeinmb > 20) {
			addFieldError("myfile", "File Size should be less than 20MB");
			return "input";
		}
		System.out.println("File Size is..." + size);
		int duplicateinsertcount = 0;
		
		AdMannagementDao adMgmtDao = new AdMannagementDao();
		
		if ((!"".equals(advetisementName)) || (advetisementName != null)
				|| (advetisementName.length() != 0)) {
			duplicateinsertcount = adMgmtDao
					.checkForDuplicateScrollMessageInsert(advetisement);

		}
		
		if (duplicateinsertcount == 0) {
			System.out.println("start");
			/* Copy file to a safe location */
			destPath = "/home/PIS/";
			String imagepath = "";
			try {
				// System.out.println("Src File name: " + myFile);

				imagepath = destPath + "/" + myFileFileName;
				File destFile = new File(destPath, myFileFileName);
				FileUtils.copyFile(myFile, destFile);

			} catch (IOException e) {
				e.printStackTrace();
				msg = "Uploading Failed Please Try Again";
				return "input";

			}
			advetisement.setFile_path(imagepath);

			int insertid = adMgmtDao.insertAdvertisement(advetisement);
			msg = "Advertisement Id " + insertid + " Inserted Succesfully";
		} else {
			msg = "Advertisement  already Exists";
			return "input";
		}
		}catch(Exception e){
			e.printStackTrace();
		}

		return "success";
	}

	public String editAdMannagement() {

		HttpServletRequest request = ServletActionContext.getRequest();
		AdMannagementDao adMgmtDao = new AdMannagementDao();
		System.out.println("************="+request.getParameter("id"));
		advetisement = adMgmtDao.getAdvertisementDetails(Integer
				.parseInt(request.getParameter("id")));

		return "success";
	}

	public String deleteAdvetisement() {
		HttpServletRequest request = ServletActionContext.getRequest();
		AdMannagementDao adMgmtDao = new AdMannagementDao();

		int displaymappid = Integer.parseInt(request.getParameter("addid"));

		adMgmtDao.deleteAdManagement(Integer.parseInt(request
				.getParameter("addid")));
		msg = "Advertisement Id " + displaymappid + " Deleted successfully";

		return "success";
	}

	public String updateAdvertisement() {
		try{
		String advetisementName = advetisement.getAdvertisement_name();
		int advertisementtypeid = advetisement.getAdvetisetype()
				.getAdvertisement_type_id();
		int customerid = advetisement.getCust().getId();
		int duration = advetisement.getDuration();
		int frequency = advetisement.getFrequency();
		String statrdate = advetisement.getStart_date();

		if (("".equals(advetisementName)) || (advetisementName == null)
				|| (advetisementName.length() == 0)) {

			addFieldError("advertisement_name",
					"Advertisement Name is Manadatory");
			return "input";

		}
		CommonValidation cm = new CommonValidation();
		if (!cm.isSpecialChar(advetisementName)) {
			addFieldError("advertisement_name",
					"Advertisement Name is Should be a alphanumeric");
			return "input";
		}
		if (customerid == 0) {

			addFieldError("cust_id",
					"Advertisement Customer Name is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (("".equals(statrdate)) || (statrdate == null)
				|| (statrdate.length() == 0)) {

			addFieldError("startdate",
					"Advertisement Statrt Date is Manadatory");
			return "input";

		}
		if (duration == 0) {

			addFieldError("duration", "Advertisement Duration is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (frequency == 0) {

			addFieldError("frequency", "Advertisement Frequency is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (advetisement.getAmount()==0) {			
			addFieldError("amount", "Advertisement Amount is Manadatory");
			return "input";
		}
		if (advetisement.getAirtime()==null || advetisement.getAirtime().trim().isEmpty()) {
			
			addFieldError("airtime", "Advertisement Airtime is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		
		String date1 = advetisement.getStart_date();
		String date2 = advetisement.getEnd_date();

		try {
			if (advetisement.getEnd_date().trim().length() > 0) {

				// validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date effstdate = sdf.parse(advetisement.getStart_date());
				Date effenddate = sdf.parse(advetisement.getEnd_date());

				if (effstdate.compareTo(effenddate) > 0) {

					addActionError("End Date should be greater than Start Date");

					return "input";

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (advertisementtypeid == 0) {

			addFieldError("advertisement_type_id",
					"Advertisement Type Name is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}

		int id = advetisement.getAdvertisement_id();
		AdMannagementDao adMgmtDao = new AdMannagementDao();
		int duplicateeditcount = 0;
		if (advetisementName != null) {
			duplicateeditcount = adMgmtDao.checkForDuplicateAdvertisement(
					advetisementName,customerid,advertisementtypeid, id);

		}
		if (duplicateeditcount == 0) {

			adMgmtDao.saveEditedAdvertisementDetails(requestType, id,
					advetisement);

			msg = "Advertisement  Id " + id + " Updated Succesfully";
		} else {
			msg = "Advertisement already exists";
			return "input";
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "success";
	}

	public String getAdvertisementList() throws IOException {
		System.out
				.println("\n \t ************iiiiiiStart of getAdvertisementList*****************");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AdMannagementDao adMgmtDao = new AdMannagementDao();

		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		try {

			// int cnt =
			// vehiclealertconfigdao.getTotalVehicleAlertConfigDataRecords();
			// int cnt = orgdao.getTotalRecordsOfWayBill();
			// System.out.println("Count------>" + cnt);
			String[] cols = { "", "advertisement_id", "advertisement_name",
					"start_date", "end_date", "status" };
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			// sStart : 0 : sAmount : 10 : sCol : 1 : sdir : asc

			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			System.out
					.println("\n \t In getAdvertisementList......Prepare...... : sStart : "
							+ sStart
							+ " : sAmount : "
							+ sAmount
							+ " : sCol : "
							+ sCol + " : sdir : " + sdir);

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

			int total = -1;
			// total =
			// vehiclealertconfigdao.getTotalVehicleAlertConfigDataRecords();
			result = adMgmtDao.getAdvertisementData(total, request,
					cols[Integer.parseInt(sCol)], sdir);

			out.print(result);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

		}

		return null;

	}
	public String getAdvertisementOfCustomer() throws IOException {
		System.out
				.println("\n \t ************iiiiiiStart of getAdvertisementListgffffffffffff12333*****************");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AdMannagementDao adMgmtDao = new AdMannagementDao();
		int customerId = Integer.parseInt(request.getParameter("custId")
				.toString());
		int advertiseId = Integer.parseInt(request.getParameter("adId")
				.toString());
		System.out.println("Customer Id is ..." + advertiseId);
		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		try {

			// int cnt =
			// vehiclealertconfigdao.getTotalVehicleAlertConfigDataRecords();
			// int cnt = orgdao.getTotalRecordsOfWayBill();
			// System.out.println("Count------>" + cnt);
			String[] cols = { "", "advertisement_id", "advertisement_name",
					"start_date", "end_date", "status" };
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			// sStart : 0 : sAmount : 10 : sCol : 1 : sdir : asc

			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			System.out
					.println("\n \t In getAdvertisementList......Prepare...... : sStart : "
							+ sStart
							+ " : sAmount : "
							+ sAmount
							+ " : sCol : "
							+ sCol + " : sdir : " + sdir);

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

			int total = -1;
			// total =
			// vehiclealertconfigdao.getTotalVehicleAlertConfigDataRecords();
			result = adMgmtDao.getAdvertisementDataForCustomer(total, request,
					cols[Integer.parseInt(sCol)], sdir,customerId,advertiseId);

			out.print(result);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

		}

		return null;

	}
	/**
	 * Find all Bus Stations
	 * 
	 * @return
	 */
	public String getAllCustomerNames() {
		AdMannagementDao adMgmtDao = new AdMannagementDao();

		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = adMgmtDao.getCustomerId();
		List<String> l2 = adMgmtDao.getCustomerName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='cust_id" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public String getAllAdvertisement() {
		AdMannagementDao adMgmtDao = new AdMannagementDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int customerId = Integer.parseInt(request.getParameter("custId"));
		List<String> l1 = adMgmtDao.getAdvertiseId(customerId);
		List<String> l2 = adMgmtDao.getAdvertiseName(customerId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=-1>ALL</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='advertise_id" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String getAllAdvertisementTypes() {
		AdMannagementDao adMgmtDao = new AdMannagementDao();

		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = adMgmtDao.getAdvertisementTypeId();
		List<String> l2 = adMgmtDao.getAdvertisementTypeName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='bus_station_id" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getAllAddsForCustomer() {
		AdMannagementDao adMgmtDao = new AdMannagementDao();
		try {
			customerlist = adMgmtDao.getCustomerList();
			advertisementlist=adMgmtDao.getAdvertisementList();
			System.out.println(customerlist
					+ "customerlist%%%%%%%%%%%%%%%%%%%%%%");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}
}
