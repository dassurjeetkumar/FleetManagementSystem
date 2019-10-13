package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.transport.dao.RoadTypeDao;
import com.trimax.its.transport.dao.ShiftTypeDao;
import com.trimax.its.transport.model.RoadType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.PageMasterDao;
import com.trimax.its.utility.model.AccessRights;

public class RoadTypeAction extends ActionSupport{
	private String token;

	  public String getToken() {
	    return token;
	  }

	  public void setToken(String token) {
	    this.token = token;
	  }

	private RoadType roadType;
    public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	private String requestType;

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String msg;
	public RoadType getRoadType() {
		return roadType;
	}
	public void setRoadType(RoadType roadType) {
		this.roadType = roadType;
	}
	public String checkForShiftId()
	{
		return "success";
	}
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

    public String execute()
    {
    	try {
    		HttpServletRequest request = ServletActionContext.getRequest();
    		HttpServletResponse response = ServletActionContext.getResponse();
    		RoadTypeDao roadtypedao=new RoadTypeDao();
    		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
    		//int cnt = roadtypedao.getTotalRecords();
    		//System.out.println("Count------>" + cnt);
    		String[] cols = { "","road_type_id","road_type_name","note","status"};
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

    		String colName = cols[col];
    		int total = -1;
    		total = roadtypedao.getTotalRecords(total, request, cols[Integer.parseInt(sCol)], sdir, viewdeletedrecord);
    		AMOUNT = amount;
    		SEARCH_TERM = request.getParameter("sSearch");
    		COL_NAME = colName;
    		DIR = dir;
    		START = start;

    		response.setContentType("application/json");
    		response.setHeader("Cache-Control", "no-store");
    		PrintWriter out = response.getWriter();

    		result = roadtypedao.getData(total, request,cols[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
    		out.print(result);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		
    	}
    	return null;

    }
    public String createRoadType()
    {
    	return "success";
    }
    
    public String updateRoadType()
    {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	RoadTypeDao roaddao=new RoadTypeDao();
		roadType = roaddao.getRoadTypeDetails(Integer.parseInt(request
				.getParameter("road_type_id")));


    	return "success";
    }
    public String updateRoadTypeDetails()
    {
    	int i=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"roadType.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		String roadname=roadType.getRoad_type_name();
    	String note=roadType.getNote();
    	String status=roadType.getStatus();
    	int roadtypeid=roadType.getRoad_type_id();
    	RoadTypeDao roadtypedao=new RoadTypeDao();
		boolean isValidData=false;
		//validation
		
		if(("".equals(roadname))||(roadname==null)||(roadname.length()==0)){
			 
	              addFieldError("roadtypename","Road Type Name is Required");
	             // msg="Page Details not Updated Try Again";
	              return "input";
	          	
	              
		}
		CommonValidation cm=new CommonValidation();
		if(!cm.isSpecialChar(roadname))
		{
			addFieldError("roadtypename", "Special Character For Road Type is Not Allowed");
			return "input";
		}
		/*if(("".equals(note))||(note==null)||(note.length()==0)){
			System.out.println("hi if of old");
			
	              addFieldError("Note","Note  is required");
	              msg="Page Details not Updated Try Again";
	              return "input";
	          	
	              
		}*/
		int roadtypenamecount=0;
		int roadtypenotecount=0;
		if (roadname != null) {
			roadtypenamecount = roadtypedao.checkForDuplicateRoadTypeName(
					roadname, roadtypeid);
		}
		
		if (roadtypenamecount == 0) {
			i=roadtypedao.saveEDitedPageDetails(requestType,roadtypeid,roadType);

			msg = "Road Type Id "+roadtypeid+" Updated Sccessfully";
		} else {
			msg = "Could not update Duplicate Road Type";
			return "input";
		}
    	
	return "success";
		}else{
			return "input";
		}
    	
    }
    public String deleteRoadType()
    {
    	 
    	HttpServletRequest request = ServletActionContext.getRequest();
    	AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"roadType.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
    	RoadTypeDao roaddao=new RoadTypeDao();
		int roadid = Integer.parseInt(request.getParameter("roadid"));
		int i=0;
		i=roaddao.deleteRoadtype(Integer.parseInt(request.getParameter("roadid")));
		//DependencyChecker dc=new DependencyChecker();
		//String status=dc.getStatus(i, "road_type");
		//System.out.println("status---"+status);
		//if(status.equals(""))
		//{
        if(i==1)
        {
        	msg="Road Type Id "+roadid+" Deleted Sccessfully";
        	
        }
        else
        {
        	msg="Failed to Delete Road Type";
        }
		/*}else{
			msg=status;
		}*/
    	return "success";
    	}else{
    		msg="Access Denied - User Does Not Have Access Privileges";
    		return "success";
    	}
    }
    public String saveRoadTypeDetails()
    {
    	HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"roadType.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		String roadTypeName=roadType.getRoad_type_name();
    	String note=roadType.getNote();
    	RoadTypeDao roaddao=new RoadTypeDao();
		boolean isValidData=false;
		//validation
		
		if(("".equals(roadTypeName))||(roadTypeName==null)||(roadTypeName.length()==0)){
			System.out.println("hi if of old");
			   addFieldError("roadtypename","Road Type Name is Required");
			   //  msg="Road Details not Saved Try Again";
	           
	              return "input";
	          	
	              
		}
		CommonValidation cm=new CommonValidation();
		if(!cm.isSpecialChar(roadTypeName))
		{
			addFieldError("roadtypename", "Special Character For Road Type is Not Allowed");
			return "input";
		}
		/*if(("".equals(note))||(note==null)||(note.length()==0)){
			System.out.println("hi if of old");
			 addFieldError("Note","note is required");
			// msg="Road Details not Saveed Try Again";
	             
	              return "input";
	          	
	              
		}
*/
		ShiftTypeDao shiftypedao = new ShiftTypeDao();
		int roadtypecount = 0;
		if ((roadTypeName != null) && (note != null)) {
			roadtypecount = roaddao.checkForDuplicateRoadType(
					roadTypeName, note);
			
		}
		int insertid=roaddao.getMaxRoadTypeID()+1;
		if(!roaddao.getDuplicate(roadTypeName))
		{
			int i=roaddao.saveRoadDetails(roadType);
			msg = "Road Type Id "+insertid+" Saved Sccessfully";
		} else {
			msg = "Could not insert Duplicate Road Type ";
			return "input";
		}

		
    	/*if((i>0)&&(j>0))
    	{
    		msg="Page Created Successfully";
    		
    	}
    	else
    	{
    		msg="Creation failed";
    	}*/
    	return "success";

    
    } else{
	     return "input";    	
	    }
    
   /* public String getRoadID()
    {
    	RoadTypeDao roaddao=new RoadTypeDao();
PageMasterDao rmDao=new PageMasterDao(); 
		
		//serviceTypeIds=rmDao.getServiceId();
		List<String> l1=roaddao.getRoadId();
		List<String> l2=roaddao.getRoadName();
		String regionTypeAjaxString = "";
        regionTypeAjaxString += "<option value=0>------select------</option>";
        for (int i = 0; i < l1.size(); i++) {
            regionTypeAjaxString +=
                    "<option value=" + l1.get(i).toString() + ">" + l2.get(i).toString() + "</option>";
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
    
*/
   }
}