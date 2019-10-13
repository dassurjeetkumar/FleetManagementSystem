package com.trimax.its.fare.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.fare.dao.RateMasterDAO;
import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.fare.model.RateMasterDetail;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class RateMasterAction extends ActionSupport{
    private RateMaster rateMaster;
    private List<String> serviceTypeIds;
    private List<RateMaster> rateMasterList;
    private List<RateMasterDetail> rateMasterDetailList;
    private int formType;
    private String version;
    private String fareType;
    private String effStartDate;
    private String effEndDate;
    private int rateId;
    
    public RateMaster getRateMaster() {
		return rateMaster;
	}

	public void setRateMaster(RateMaster rateMaster) {
		this.rateMaster = rateMaster;	
	}
	
	public void setServiceTypeIds(List<String> serviceTypeIds) {
		this.serviceTypeIds = serviceTypeIds;
	}

	public List<RateMaster> getRateMasterList() {
		return rateMasterList;
	}

	public void setRateMasterList(List<RateMaster> rateMasterList) {
		this.rateMasterList = rateMasterList;
	}

	public int getFormType() {
		return formType;
	}

	public void setFormType(int formType) {
		this.formType = formType;
	}

	public List<RateMasterDetail> getRateMasterDetailList() {
		return rateMasterDetailList;
	}

	public void setRateMasterDetailList(List<RateMasterDetail> rateMasterDetailList) {
		this.rateMasterDetailList = rateMasterDetailList;
	}
	
	
	
	/**
	 * @return the effStartDate
	 */
	public String getEffStartDate() {
		return effStartDate;
	}

	/**
	 * @param effStartDate the effStartDate to set
	 */
	public void setEffStartDate(String effStartDate) {
		this.effStartDate = effStartDate;
	}

	
	
	
	/**
	 * @return the effEndDate
	 */
	public String getEffEndDate() {
		return effEndDate;
	}

	/**
	 * @param effEndDate the effEndDate to set
	 */
	public void setEffEndDate(String effEndDate) {
		this.effEndDate = effEndDate;
	}
	
	
	
	/**
	 * @return the rateId
	 */
	public int getRateId() {
		return rateId;
	}

	/**
	 * @param rateId the rateId to set
	 */
	public void setRateId(int rateId) {
		this.rateId = rateId;
	}

	public String execute(){
	
		RateMasterDAO rmDao=new RateMasterDAO();
		setRateMasterList(rmDao.getAllData());
		setFormType(1);
		return "data";
	}
	
	public String rateDetail(){
		RateMasterDAO rmDao=new RateMasterDAO();
		//setRateMasterList(rmDao.getAllData());
		HttpServletRequest request = ServletActionContext.getRequest();
		int rateId=Integer.parseInt(request.getParameter("id").toString());
		request.getSession().setAttribute("rateId",rateId);
		System.out.println("rate id11------>"+rateId);
		rateMaster=rmDao.getDataById(rateId);
		rateMaster.setVersionNoServiceType(rateMaster.getVersionNoServiceType().split("-")[1]);
		setFormType(2);		
		return INPUT;
	}
	
	public String add(){
		RateMasterDAO rmDao=new RateMasterDAO();
		//setServiceTypeIds(rmDao.getServiceId());
	 return INPUT;
	}
	
	public String edit(){
		RateMasterDAO rmDao=new RateMasterDAO();
		//setServiceTypeIds(rmDao.getServiceId());
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int rateId=Integer.parseInt(request.getParameter("id").toString());
		System.out.println("rate id11------>"+rateId);
		rateMaster=rmDao.getDataById(rateId);
		rateMaster.setVersionNoServiceType(rateMaster.getVersionNoServiceType().split("-")[1]);
		setEffStartDate(rateMaster.getEffectiveStartDate());
		setEffEndDate(rateMaster.getEffectiveEndDate());
		setRateId(rateMaster.getRateMasterId());
		return "edit";
		}
	
	public String delete(){
		
		 return INPUT;
		}
	
	public String cancel(){
		
		 return "data";
		}
	
	public String addRate(){
		System.out.println("in addrate");
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();		
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "RateMasterAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		RateMasterDAO rmDao=new RateMasterDAO();
		//setServiceTypeIds(rmDao.getServiceId());
		Common common=new Common();
		boolean isValidData=false;
		if(create.equalsIgnoreCase("Y")){
		//original date
		String sDt=rateMaster.getEffectiveStartDate();
		String eDt=rateMaster.getEffectiveEndDate();
        
		
		//validation
		String versionNo=rateMaster.getVersionNumber();
		if(versionNo==null || versionNo.trim().equals("")){
			addFieldError("versionNumber","Please enter Version Number.");
			isValidData=true;
		}
		
		if(rateMaster.getEffectiveStartDate().equals("") || rateMaster.getEffectiveStartDate().length()<=0){
			addFieldError("effectiveStartDate","Please enter Effective Start Date.");
			isValidData=true;
		}else{
			
			rateMaster.setEffectiveStartDate(common.getDateToDate(rateMaster.getEffectiveStartDate()));
		}
		
		if(!rateMaster.getEffectiveEndDate().equals("") || rateMaster.getEffectiveEndDate().length()>0){
			rateMaster.setEffectiveEndDate(common.getDateToDate(rateMaster.getEffectiveEndDate()));
		}
		
		if(rateMaster.getServiceTypeId()==0){
			
			addFieldError("serviceTypeId","Please select Fare Category Type.");
			isValidData=true;
		}else{
			RateMasterDAO dao=new RateMasterDAO();
			String serviceTypeName=dao.getServiceNameById(rateMaster.getServiceTypeId());
			rateMaster.setVersionNoServiceType(serviceTypeName);
		}
		
		if(rateMaster.getStatus().equals("") || rateMaster.getStatus().length()<=0){
			addFieldError("status","Please enter Status.");
			isValidData=true;
		}
		
		try{
			if(rateMaster.getEffectiveEndDate().length()>0){
		//validate str& end date
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date1=sdf.parse(rateMaster.getEffectiveStartDate());
		Date date2=sdf.parse(rateMaster.getEffectiveEndDate());
		
		if(date1.compareTo(date2)>0){
    		System.out.println("Date1 is after Date2");
    		addActionError("Effective End Date should be greater than Effective Start Date");
    		rateMaster.setEffectiveStartDate(sDt);
    		rateMaster.setEffectiveEndDate(eDt);
    		//rateMaster.setEffectiveStartDate(common.getDateToDate2(rateMaster.getEffectiveStartDate()));
    		//rateMaster.setEffectiveEndDate(common.getDateToDate2(rateMaster.getEffectiveEndDate()));
    		 return INPUT;
    	}else if(date1.compareTo(date2)==0){
    		System.out.println("Date1 is equal to Date2");
    		addActionError("Effective End Date should be greater than Effective Start Date");
    		rateMaster.setEffectiveStartDate(sDt);
    		rateMaster.setEffectiveEndDate(eDt);
    		//rateMaster.setEffectiveStartDate(common.getDateToDate2(rateMaster.getEffectiveStartDate()));
    		//rateMaster.setEffectiveEndDate(common.getDateToDate2(rateMaster.getEffectiveEndDate()));
    		 return INPUT;
    	}
			}
	}catch(Exception e){e.printStackTrace();}
		
		if(isValidData){
			rateMaster.setEffectiveStartDate(sDt);
    		rateMaster.setEffectiveEndDate(eDt);
		   return INPUT;	
		}
		
		
		
	//	HttpServletRequest request = ServletActionContext.getRequest();		
		int rateId=(request.getParameter("id").toString())!=null?Integer.parseInt(request.getParameter("id").toString()):0;
		System.out.println("rate id11------>"+rateId);
		int added=0;
		int copyOrCreate=0;
		if(rateId==0){
		
		
		added=rmDao.add(rateMaster,0);
		copyOrCreate=1;
	
		}else{
			System.out.println("copying....");
			added=copyRate(rateMaster);
			copyOrCreate=2;
			
			if(added==2){
				rateMaster.setEffectiveStartDate(common.getDateToDate3(rateMaster.getEffectiveStartDate()));
	    		rateMaster.setEffectiveEndDate(common.getDateToDate3(rateMaster.getEffectiveEndDate()));
	        	addActionError("Rate Master Version already exist.");
	        	rateMaster.setEffectiveStartDate(sDt);
	    		rateMaster.setEffectiveEndDate(eDt);
	        	return INPUT;
	        }
			else if(added==4){
				rateMaster.setEffectiveStartDate(common.getDateToDate3(rateMaster.getEffectiveStartDate()));
	    		rateMaster.setEffectiveEndDate(common.getDateToDate3(rateMaster.getEffectiveEndDate()));
	        	rateMaster.setEffectiveStartDate(sDt);
	    		rateMaster.setEffectiveEndDate(eDt);
				addActionError("Rate Master already exist.Please create with effective start date greater than or equal to current date.");
			}
			else{
	        	if(added==1 && copyOrCreate==2){
	        		addActionMessage("Rate Master "+rateMaster.getRateMasterId()+" copied successfully.");
	        	}else{
	        		if(added==3){
	        			rateMaster.setEffectiveStartDate(common.getDateToDate3(rateMaster.getEffectiveStartDate()));
	        			if(rateMaster.getEffectiveEndDate()!=null){
	            		rateMaster.setEffectiveEndDate(common.getDateToDate3(rateMaster.getEffectiveEndDate()));
	        			}
	        			addActionError("Rate Master with greater date already exist.");
	        			rateMaster.setEffectiveStartDate(sDt);
	            		rateMaster.setEffectiveEndDate(eDt);
	        			return INPUT;
	        		}else{
	        		addActionError("Database error retry again.");
	        		rateMaster.setEffectiveStartDate(common.getDateToDate3(rateMaster.getEffectiveStartDate()));
	        		
	        		if(rateMaster.getEffectiveEndDate()!=null){
	        		rateMaster.setEffectiveEndDate(common.getDateToDate3(rateMaster.getEffectiveEndDate()));
	        		}
	        		rateMaster.setEffectiveStartDate(sDt);
	        		rateMaster.setEffectiveEndDate(eDt);
	        		return INPUT;
	        		}
	        	}
	        }
			
			//show data view list
	        execute();
			return "data";
			///System.out.println("copied.");
		}
		
		//show error msg based on added
        if(added==2){
        	addActionError("Rate Master already exist.");
        	rateMaster.setEffectiveStartDate(common.getDateToDate3(rateMaster.getEffectiveStartDate()));
        	
        	if(rateMaster.getEffectiveEndDate()!=null){
    		rateMaster.setEffectiveEndDate(common.getDateToDate3(rateMaster.getEffectiveEndDate()));
        	}
        	rateMaster.setEffectiveStartDate(sDt);
    		rateMaster.setEffectiveEndDate(eDt);
        	return INPUT;
        }
        else if(added==4){
			rateMaster.setEffectiveStartDate(common.getDateToDate3(rateMaster.getEffectiveStartDate()));
    		rateMaster.setEffectiveEndDate(common.getDateToDate3(rateMaster.getEffectiveEndDate()));
        	rateMaster.setEffectiveStartDate(sDt);
    		rateMaster.setEffectiveEndDate(eDt);
			addActionError("Rate Master already exist.Please create with effective start date greater than or equal to current date.");
			return INPUT;
		}
        else{
        	if(added==1 && copyOrCreate==1){
        		addActionMessage("Rate Master "+rateMaster.getRateMasterId()+" created successfully.");
        	}else{
        		if(added==3){
        			addActionError("Rate Master with greater date already exist.");
        			rateMaster.setEffectiveStartDate(common.getDateToDate3(rateMaster.getEffectiveStartDate()));
        			if(rateMaster.getEffectiveEndDate()!=null){
            		rateMaster.setEffectiveEndDate(common.getDateToDate3(rateMaster.getEffectiveEndDate()));
        			}
        			rateMaster.setEffectiveStartDate(sDt);
            		rateMaster.setEffectiveEndDate(eDt);
        			return INPUT;
        		}else{
        		addActionError("Database error retry again.");
        		rateMaster.setEffectiveStartDate(common.getDateToDate3(rateMaster.getEffectiveStartDate()));
        		
        		if(rateMaster.getEffectiveEndDate()!=null){
        		rateMaster.setEffectiveEndDate(common.getDateToDate3(rateMaster.getEffectiveEndDate()));
        		}
        		rateMaster.setEffectiveStartDate(sDt);
        		rateMaster.setEffectiveEndDate(eDt);
        		return INPUT;
        		}
        	}
        }
		
        //show data view list
        execute();
		return "data";
		}else{
			return INPUT;
		}
	}

	public String editRate(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();		
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "RateMasterAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		//original date
		String sDt=rateMaster.getEffectiveStartDate();
		String eDt=rateMaster.getEffectiveEndDate();
		
		RateMasterDAO rmDao=new RateMasterDAO();
		Common common=new Common();
		boolean isValidData=false;
		//validation
		
		 DependencyChecker dc=new DependencyChecker();
			String status=dc.getStatus(rateMaster.getRateMasterId(), "rate_master");
			System.out.println("status---"+status);
		if(status.equals("")  ||(!status.equals("") &&  rateMaster.getStatus().equals("Active"))){
		
		String versionNo=rateMaster.getVersionNumber();
		if(versionNo==null || versionNo.trim().equals("")){
			addFieldError("versionNumber","Please enter Version Number.");
			isValidData=true;
		}
		
		if(rateMaster.getEffectiveStartDate().equals("") || rateMaster.getEffectiveStartDate().length()<=0){
			addFieldError("effectiveStartDate","Please enter Effective Start Date.");
			isValidData=true;
		}else{
			
			rateMaster.setEffectiveStartDate(common.getDateToDate(rateMaster.getEffectiveStartDate()));
		}
		
		if(!rateMaster.getEffectiveEndDate().equals("") || rateMaster.getEffectiveEndDate().length()>0){
			rateMaster.setEffectiveEndDate(common.getDateToDate(rateMaster.getEffectiveEndDate()));
		}

		if(rateMaster.getServiceTypeId()==0){
			
			addFieldError("serviceTypeId","Please select Fare Category Type.");
			
			isValidData=true;
		}else{
			RateMasterDAO dao=new RateMasterDAO();
			String serviceTypeName=dao.getServiceNameById(rateMaster.getServiceTypeId());
			rateMaster.setVersionNoServiceType(serviceTypeName);
		}
		
		if(rateMaster.getStatus().equals("") || rateMaster.getStatus().length()<=0){
			addFieldError("status","Please enter Status.");
			isValidData=true;
		}
		
		//version no. for detail page
		setVersion(versionNo);
		
		try{
			if(rateMaster.getEffectiveEndDate().length()>0){
		//validate str& end date
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date1=sdf.parse(rateMaster.getEffectiveStartDate());
		Date date2=sdf.parse(rateMaster.getEffectiveEndDate());
		
		if(date1.compareTo(date2)>0){
    		System.out.println("Date1 is after Date2");
    		rateMaster.setEffectiveStartDate(sDt);//common.getDateToDate2(rateMaster.getEffectiveStartDate()));
    		rateMaster.setEffectiveEndDate(eDt);//(common.getDateToDate2(rateMaster.getEffectiveEndDate()));
    		
    		addActionError("Effective End Date should be greater than Effective Start Date");
    		 return "edit";
    	}else if(date1.compareTo(date2)==0){
    		System.out.println("Date1 is equal to Date2");
    		rateMaster.setEffectiveStartDate(sDt);//(common.getDateToDate2(rateMaster.getEffectiveStartDate()));
    		rateMaster.setEffectiveEndDate(eDt);//(common.getDateToDate2(rateMaster.getEffectiveEndDate()));
    		addActionError("Effective End Date should be greater than Effective Start Date");
    		 return "edit";
    	}
			}
	}catch(Exception e){e.printStackTrace();}
		
		
		
		if(isValidData){
			rateMaster.setEffectiveStartDate(sDt);
    		rateMaster.setEffectiveEndDate(eDt);
		   return "edit";	
		}
		}else{
			if(rateMaster.getStatus().equals("Inactive"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "inactivestatus";
			}
			
		}
		int updated=rmDao.updateRateMaster(rateMaster,getEffEndDate(),getRateId());
		if(updated==2){
			addActionError("Rate Master already exist.");
//			rateMaster.setEffectiveStartDate(common.getDateToDate2(rateMaster.getEffectiveStartDate()));
//			if(rateMaster.getEffectiveEndDate()!=null){
//    		rateMaster.setEffectiveEndDate(common.getDateToDate2(rateMaster.getEffectiveEndDate()));}
			rateMaster.setEffectiveStartDate(sDt);
			rateMaster.setEffectiveEndDate(eDt);
        	return "edit";
        }else{
        	if(updated==1){
        		//addActionMessage("Rate Master Version "+rateMaster.getVersionNumber()+" updated successfully.");
        		addActionMessage("Rate Master "+rateMaster.getRateMasterId()+" updated successfully.");
        	}else{
        		if(updated==3){
        			addActionError("Rate Master already exist.");
//        			rateMaster.setEffectiveStartDate(common.getDateToDate2(rateMaster.getEffectiveStartDate()));
//        			if(rateMaster.getEffectiveEndDate()!=null){
//            		rateMaster.setEffectiveEndDate(common.getDateToDate2(rateMaster.getEffectiveEndDate()));
//        			}
        			rateMaster.setEffectiveStartDate(sDt);
        			rateMaster.setEffectiveEndDate(eDt);
        			return "edit";
        		}else{
        		addActionError("Database error retry again.");
//        		rateMaster.setEffectiveStartDate(common.getDateToDate2(rateMaster.getEffectiveStartDate()));
//        		
//        		if(rateMaster.getEffectiveEndDate()!=null){
//        		rateMaster.setEffectiveEndDate(common.getDateToDate2(rateMaster.getEffectiveEndDate()));
//        		}
        		rateMaster.setEffectiveStartDate(sDt);
    			rateMaster.setEffectiveEndDate(eDt);
        		return "edit";
        		}
        		
        	}
        }
		
		
		if(updated==1){
		  execute();
		  return "data";
		}else{
			rateMaster.setEffectiveStartDate(sDt);
			rateMaster.setEffectiveEndDate(eDt);
		  return "edit";
		}}else{
			return "edit";
		}
		}
	
	public String deleteRate(){
		System.out.println("in delete rate");
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();		
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "RateMasterAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		RateMasterDAO rmDao=new RateMasterDAO();
		//HttpServletRequest request = ServletActionContext.getRequest();
		if(delete.equalsIgnoreCase("Y")){
		int rateId=Integer.parseInt(request.getParameter("id").toString());
		DependencyChecker dc=new DependencyChecker();
		String statusdetails=dc.getStatus(rateId, "rate_master");
		//System.out.println("status---"+statusdetails);
		if(statusdetails.equals(""))
		{
		String s=rmDao.deleteRateMaster(rateId);
	
		
		if(s.split(":")[0].equals("success")){
			//addActionMessage("Rate Master Version "+s.split(":")[1]+" deleted successfully.");
			addActionMessage("Rate Master "+rateId+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
		}else{
			addActionError(statusdetails);
			return "deletedstat";
		}
		execute();
		return "data";}
		else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "data";
		}
	}

	public int copyRate(RateMaster rateMaster){
		RateMasterDAO rmDao=new RateMasterDAO();
		HttpServletRequest request = ServletActionContext.getRequest();		
		int rateId=Integer.parseInt(request.getParameter("id").toString());
		System.out.println("rate id11------>"+rateId);
		int added=rmDao.copyRateMaster(rateId,rateMaster);
		System.out.println("");
		System.out.println("copied="+added);
		
		//execute();
		return added;
	}
	
	public List<String> getServiceTypeIds() {
		return serviceTypeIds;
	}

	public String getServiceTypes(){
		RateMasterDAO rmDao=new RateMasterDAO(); 
		
		//serviceTypeIds=rmDao.getServiceId();
		List<String> l1=rmDao.getServiceId();
		List<String> l2=rmDao.getServiceName();
		String regionTypeAjaxString = "";
        regionTypeAjaxString += "<option value=''>Select</option>";
        for (int i = 0; i < l1.size(); i++) {
            regionTypeAjaxString +=
                    "<option value=" + l1.get(i).toString() + ">" +l2.get(i).toString() + "</option>";
        }
        //regionTypeAjaxString += "</select>";
        System.out.println("regionTypeAjaxString="+regionTypeAjaxString);
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
	
	public String rateDetailEdit(){
		RateMasterDAO rmDao=new RateMasterDAO();
		//setServiceTypeIds(rmDao.getServiceId());
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int rateId=Integer.parseInt(request.getParameter("id").toString());
		System.out.println("rmd rate id------>"+rateId);
		setRateMasterDetailList(rmDao.getAllDetailData(rateId));
		
		//version no for detail page
		RateMaster rm=rmDao.getDataById(rateId);
		setVersion(rm.getVersionNumber());
		setFareType(rm.getVersionNoServiceType().split("-")[1]);
		 return "rateDetailEdit";
		}
	
	public String retailDetailUpdate(){
		RateMasterDAO rmDao=new RateMasterDAO();
		//setServiceTypeIds(rmDao.getServiceId());
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int rateId=Integer.parseInt(request.getParameter("id").toString());
		System.out.println("rmd rate id------=>"+rateId);
		RateMasterDetail rmd;
	
		int adultFare=0,childFare=0,srFare=0;
		double lFare=0;
		int happy_hour1=0;
		int happy_hour2=0;
		int flag=0; // to indicate all stages are successfully saved
		
		for(int c=1;c<=70;c++){
			try{
				flag=0;
			rmd=new RateMasterDetail();
//System.out.println(request.getParameter("stageNo"+c)+","+request.getParameter("adult"+c)+","+request.getParameter("children"+c)+","+request.getParameter("seniorCitizen"+c));
			
			
			adultFare=Integer.parseInt(request.getParameter("adult"+c));
			childFare=Integer.parseInt(request.getParameter("children"+c));
			srFare=Integer.parseInt(request.getParameter("seniorCitizen"+c));
			lFare=Double.parseDouble(request.getParameter("luggage"+c));
			happy_hour1=Integer.parseInt(request.getParameter("happyhour1"+c));
			happy_hour2=Integer.parseInt(request.getParameter("happyhour2"+c));
			
			
			rmd.setStageNo(Integer.parseInt(request.getParameter("stageNo"+c)));
			rmd.setAdult(adultFare);
			rmd.setChildren(childFare);
			rmd.setSeniorCitizen(srFare);
			rmd.setLuggage(lFare);
			rmd.setHappyhour1(happy_hour1);
			rmd.setHappyhour2(happy_hour2);
//			System.out.println(rmd.getStageNo()+","+rmd.getAdult()+","+rmd.getChildren()+","+rmd.getSeniorCitizen());
			flag=rmDao.updateRateMasterDetail(rmd,rateId);
			
			if(flag<=0){
				addActionError("Fare for stage "+c+" is not saved please retry");	
				break;
			}

			
			}
			catch(Exception e){}
		}
	    
		String versionNo=rmDao.getDataById(rateId).getVersionNumber();
		
		if(flag>0){
		 addActionMessage("Rate Master Version "+versionNo+" Detail updated successfully.");
		}else{
			
		}
		
		//rateDetailEdit();//after update
				 
//		 return "rateDetailEdit";
		return "data";
		}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}
}
