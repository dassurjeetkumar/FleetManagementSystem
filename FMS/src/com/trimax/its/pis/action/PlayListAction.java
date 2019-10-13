package com.trimax.its.pis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.ad.model.Advertisement;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.pis.dao.PlayListDao;
import com.trimax.its.pis.model.PlayList;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class PlayListAction  extends ActionSupport{
	private PlayList playList;
	private int cid;
	private Map<Integer,String> advertisementMap;
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@SkipValidation
	public String view() {
		return "view";
	}
	
	@SkipValidation
	public String add() {
		PlayListDao dao=new PlayListDao();
		advertisementMap=dao.getAdvertisementList();
		return "add";
	}
	
	@SkipValidation
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		PlayListDao dao=new PlayListDao();
		advertisementMap=dao.getAdvertisementList();
		String para=request.getParameter("cid");
		
		if(para!=null && para.isEmpty()){
		cid=Integer.parseInt(para);
		}

		playList=dao.getPlayListById(cid);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "playListView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		String para=request.getParameter("cid");
		
		if(para!=null && para.isEmpty()){
		cid=Integer.parseInt(para);
		}
		
		PlayListDao dao=new PlayListDao();
		String s=dao.deletePlayList(cid);
		

		if(s.equals("success")){
			addActionMessage("Play List id "+cid+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
		
		return "success";}
		else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "playListView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		PlayListDao dao=new PlayListDao();
		if(create.equalsIgnoreCase("Y")){
		advertisementMap=dao.getAdvertisementList();
		String advlist=playList.getAdvertisementList();
		if(playList.getAdvertisementList()==null || playList.getAdvertisementList().trim().length()<=0){
			addFieldError("advertisementList","Please Select Advertisement Name");
			return INPUT;
		}
		
		int i=dao.addPlayList(playList);
		
		if(i>0){
			addActionMessage("Play List id "+i+" created successfully.");
		}else{
			if(i==-2){
				addActionError("Play List already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		//execute();
		return SUCCESS;}
		else{
			return INPUT;
		}
	}
	
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "playListView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();	
		PlayListDao dao=new PlayListDao();
		if(edit.equalsIgnoreCase("Y")){
		advertisementMap=dao.getAdvertisementList();
		int i=dao.updatePlayList(playList);
		
		if(i>0){
			addActionMessage("Play List id "+playList.getId()+" updated successfully.");
		}else{
			if(i==-2){
				addActionError("Play List already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		 return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	public void validate(){
		PlayListDao dao=new PlayListDao();
		advertisementMap=dao.getAdvertisementList();
		if(playList.getPlayListName()==null || playList.getPlayListName().trim().isEmpty()){
			addFieldError("playListName","Please Enter Play List Name");
		}else{
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(playList.getPlayListName())){
				addFieldError("playListName","Special characters not allowed"); 
			 }
		}
		
		/*try{
		if(playList.getAdvertisementList()==null || playList.getAdvertisementList().trim().length()<=0){
			addFieldError("advertisementList","Please Select Advertisement Name");
		}
		}catch(Exception e){}*/
		

		if(playList.getPlayStartTime()==null || playList.getPlayStartTime().trim().length()<=0){
			addFieldError("playStartTime","Please Select Start Date Time");
		}
	
	}
	
	@SkipValidation
	public String execute()  {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			PlayListDao dao=new PlayListDao();

			String[] dbcol={"","id","playListName","playAdvertisementId","playStartTime","playEndTime","status"};

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
				if (col < 0 || col > 7)
					col = 0;
			}
			if (sdir != null) {
				if (sdir.equals("asc"))
					dir = "asc";
				else
					dir="desc";
			}
			
			int total = dao.getTotalRecords(request,dbcol[col],dir,viewdeletedrecord);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[Integer.parseInt(sCol)],dir,viewdeletedrecord);

			out.print(result);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return null;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

	public Map<Integer,String> getAdvertisementMap() {
		return advertisementMap;
	}

	public void setAdvertisementMap(Map<Integer,String> advertisementMap) {
		this.advertisementMap = advertisementMap;
	}
	
	@SkipValidation
	public String getAdverList(){
		PlayListDao dao=new PlayListDao();
		List<Advertisement> l=dao.getAdvertisementList2();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>Select</option>";
		for (int i = 0; i < l.size(); i++) {
			regionTypeAjaxString += "<option value="+l.get(i).getAdvertisement_id()+">" + l.get(i).getAdvertisement_name()+ "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
