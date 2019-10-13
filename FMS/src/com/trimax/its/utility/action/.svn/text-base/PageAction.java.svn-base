package com.trimax.its.utility.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.classic.Validatable;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.fare.dao.FareChartMasterDao;
import com.trimax.its.fare.dao.RateMasterDAO;
import com.trimax.its.usermanagement.dao.UserDetailDAO;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.PageMasterDao;
import com.trimax.its.utility.dao.PageRoleDao;
import com.trimax.its.utility.dao.RoleDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.utility.model.MenuPageRole;
import com.trimax.its.utility.model.Page_Master;

public class PageAction extends ActionSupport implements Preparable{
    private Page_Master pageMaster;
    //private Map<Integer, String> parentpagelist;
    
   /* public Map<Integer, String> getParentpagelist() {
		return parentpagelist;
	}
	public void setParentpagelist(Map<Integer, String> parentpagelist) {
		this.parentpagelist = parentpagelist;
	}*/


    public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String msg;
    public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	private String requestType;
	public Page_Master getPageMaster() {
		return pageMaster;
	}
	public void setPageMaster(Page_Master pageMaster) {
		this.pageMaster = pageMaster;
	}
	public PageAction() {
		// TODO Auto-generated constructor stub
	}
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	@SkipValidation
	public String execute() throws IOException {
		try {
			
			HttpServletRequest request = ServletActionContext.getRequest();	
			HttpServletResponse response = ServletActionContext.getResponse();
			PageMasterDao pagedao = new PageMasterDao();
			
			//int cnt = roledao.getTotalRecords();
			////System.out.println("Count------>" + cnt);
			String[] cols = {"","page_id", "page_name","path", "status", "parent_id","level","sequence"};
			String[] dbcol = {"","page_id", "page_name","path", "status", "parent_id","level","sequence"};
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
			
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			//total = pagedao.getTotalRecordsForPage(SEARCH_TERM);
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = pagedao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
			
		} catch (Exception ex) {
			//System.out.println("=====?" + ex);
		} finally {
			
		}
		return null;
	}
	
	@SkipValidation
    public String addPage()
    {
    	PageMasterDao pagedao = new PageMasterDao();
    	//parentpagelist=pagedao.getParentPagedetails();
    	//System.out.println("hi");
		return "success";
    	
    }
    
	
    @SkipValidation
    public String deletePage(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	int pageid=Integer.parseInt(request.getParameter("pageiddetails"));
    	AccessRightsDao  accessrightdao=new AccessRightsDao();
    	AccessRights accessrights=new AccessRights();
    	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
    	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "pageView.action");
    	String access=accessrights.getACCESS();
    	String create=accessrights.getCREATE();
    	String edit=accessrights.getEDIT();
    	String delete=accessrights.getDELETE();
    	if(delete.equalsIgnoreCase("Y")){
    	PageMasterDao pagedao = new PageMasterDao();
    	//int res=pagedao.saveEDitedPageDetails(pageid,pageMaster);
    	int userid=(Integer)request.getSession().getAttribute("userid");
    	int res=pagedao.getDeletePageRecord(pageid, pageMaster, userid);
    	if(res>0)
    	{
    		addActionMessage("Page Id " + pageid + " Deleted successfully");
    	}else{
    		addActionError("Not Deleted");
    	}
    	
    	return "success";
    	}else{
    		return "success";
    	}
    }
    


    
    
    public String editPageDetails()
    {
    	//System.out.println("----------------editPageDetails----------");
    	int i=0;
    	boolean flagdetails=true;
    	String flag2="";
    	String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonValidation common=new CommonValidation();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "pageView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		/*String pageiddetails=request.getParameter("pageiddetails");
		//System.out.println("pageid----"+pageiddetails);
		String pagename=request.getParameter("pagenamedetails");
		//System.out.println("pagename----"+pagename);
		String path=request.getParameter("pathdetails");
		//System.out.println("path----"+path);
		String status=request.getParameter("statusdetails");
		//System.out.println("status----"+status);
		String parentid=request.getParameter("parentiddetails");
		//System.out.println("parentid----"+parentid);
		String level=request.getParameter("leveldetails");
		//System.out.println("level----"+level);
		String squence=request.getParameter("sequencedetails");
		//System.out.println("squence----"+squence);*/
		
		/*Page_Master pageMaster=new Page_Master();
		pageMaster.setPage_id(Integer.parseInt(pageiddetails));
		pageMaster.setPage_name(pagename);
		pageMaster.setPath(path);
		pageMaster.setStatus(status);*/
		//pageMaster.setParent_id(Integer.parseInt(parentid));
		//pageMaster.setLevel(Integer.parseInt(level));
		//pageMaster.setSequence(Integer.parseInt(squence));
		
		/*boolean flag1=false;		
CommonValidation commonv=new CommonValidation();
    	
    	if((pageMaster.getPage_name().trim().equals(""))||(pageMaster.getPage_name()==null)||(pageMaster.getPage_name().trim().length()==0)){
			 addFieldError("pageName","Page Name is required");
			  flagdetails=false;
				flag="error";
		}
    	
			if(!isSpecialCharDetails(pageMaster.getPage_name())){
			addFieldError("pageName","Special Character not allowed");
			 flagdetails=false;
				flag="error";
			}
			
    	
    	
    	
    	if(pageMaster.getPage_id()==0 && pageMaster.getLevel()<0){
    		flag1=true;
    		addFieldError("level","Level is required");
    		 flagdetails=false;
				flag="error";
    		
    	}
    	if(flag1)
    	{
    	if(pageMaster.getLevel()>=3){
    		addFieldError("level","Level should not be more than or equal 3");
    		 flagdetails=false;
				flag="error";
    	}
    	}
    	if(pageMaster.getSequence()<=0){
    		addFieldError("sequence","Sequence is required");
    	}
		
		
		//String pagename=pageMaster.getPage_name();
    	////System.out.println("hi---i mam in create page"+pagename);
    	//String path=pageMaster.getPath();

    	*/
		
		//validation
		if(edit.equalsIgnoreCase("Y")){
		PageMasterDao pagedao = new PageMasterDao();
		int pageid=pageMaster.getPage_id();
		//pageMaster = pagedao.getPageDetails(Integer.parseInt(request.getParameter("pageid")));
		//pageMaster.setParentname(pagedao.getParentName(pageMaster.getParent_id()));
		
		if(flagdetails){
    	//System.out.println("hi i am in editPageDEtails"+pageid);
    	int res=pagedao.saveEDitedPageDetails(requestType,pageid,pageMaster);
    	if(res>0){
			//System.out.println("test1");
			addActionMessage("Page Id " +  pageid + " Updated successfully");
			flag2="success";	 
		}else{
			  if(res ==-1){
				  //System.out.println("test2");
			    	addActionError("Page Name Already Exist");
			    	flag2="input";
			    }else{
			    	//System.out.println("test3");
			    	addActionError("Database error");
			    	flag2="input";
			    }
		}
		}
	return flag2;
		}else{
			return "input";
		}
    	
    }
    
    
   
    public String savePageDetails()
    {
    	HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse(); 
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "pageView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		PageMasterDao pagedao = new PageMasterDao();
		PageRoleDao pro=new PageRoleDao();
    	//parentpagelist=pagedao.getParentPagedetails();
        
    	String flagdetails="";
    	String roleid=pagedao.getMasterPageRoleRecords();
		
/*if(pageMaster.getParent_id()==0){
			//System.out.println("hi if of old of parentid"+pageMaster.getParent_id());
			
	              addFieldError("parentid","Page Name is required");
	              return "input";
	          	
	              
		}*/
	
    	
    	
    	/*int i=pagedao.insertFareChartMaster(pageMaster);
    	int j=pagedao.insertPageRole(pageMaster,request.getSession().getAttribute("userid").toString());
		
    	if((i>0)&&(j>0))
    	{
    		msg="Page Created Successfully";
    		
    	}
    	else
    	{
    		msg="Creation failed";
    	}*/
//    	if(isValidData)
//    	{
    	int userid=(Integer)request.getSession().getAttribute("userid");
    	if(pageMaster.getPath().length()==0){
    		pageMaster.setPath("#");
    	}
    	int res=pagedao.insertPageMaster(pageMaster,userid,pageMaster.getParent_id());

    	
    	if(res>0){
			//System.out.println("test1");
    		if(pageMaster.getPage_type().equalsIgnoreCase("C"))
    		{
    		if(roleid.length()>0){
    			String str[]=roleid.split("\\,");
    			
    			for(int i=0;i<str.length;i++){
    				System.out.println("id------////////------"+str[i]);
    				if((str[i].contains("."))||(str[i].contains("-"))){
    					
    				}else{
    				MenuPageRole menupage =new MenuPageRole();
    				
    				menupage.setRole_id(Integer.parseInt(str[i]));
    				menupage.setPage_id(res);
    				menupage.setReadaccess(1);
    				menupage.setCreateaccess(1);
    				menupage.setDeleteaccess(1);
    				menupage.setEditaccess(1);
    				menupage.setViewdelete(1);
    				String  msg =pro.addPageId(menupage, userid);
    				}
    			}
    			
    		}
    		}
			addActionMessage("Page Id " +  res + " Added Successfully");
			flagdetails="success";	 
		}else{
			  if(res ==-1){
				  //System.out.println("test2");
			    	addActionError("Page Name Already Exist");
			    	flagdetails="input";
			    }else{
			    	//System.out.println("test3");
			    	addActionError("Database error");
			    	flagdetails="input";
			    }
//		}
    	}
    	return flagdetails;
		}else{
			return "input";
		}
    	
    }
    
    @SkipValidation
    public String ceatePage()
    {
    	
    	PageMasterDao pagedao = new PageMasterDao();
		//parentpagelist=pagedao.getParentPagedetails();
		return "success";
    	
    }
    
    @SkipValidation
    public String getSequence() throws IOException
    {
    	//System.out.println("hi i am in getSEQUENCE");
    	HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
    	PageMasterDao pagedao = new PageMasterDao();
    	//parentpagelist=pagedao.getParentPagedetails();
    	int parentid=Integer.parseInt(request.getParameter("parentid"));
    	//System.out.println("hi"+parentid);
    	PrintWriter out=null;
    	

    	int getmaxseqid=pagedao.getMaxSeqId(parentid);
    	out = response.getWriter();
    	out.print(getmaxseqid);
    
    	
    
    	//out.print(1);
		return null;
    	
    }
    
    @SkipValidation
    public String getSequenceEdit() throws IOException
    {
    	//System.out.println("hi i am in getSEQUENCE");
    	HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
    	PageMasterDao pagedao = new PageMasterDao();
    	//parentpagelist=pagedao.getParentPagedetails();
    	int parentid=Integer.parseInt(request.getParameter("parentid"));
    	//System.out.println("hi"+parentid);
    	PrintWriter out=null;
    	try{

    	int getmaxseqid=pagedao.getMaxSeqId(parentid);
    	
    	//System.out.println("editParentId="+request.getSession().getAttribute("editParentId"));
    	
    	int oldParentId=(Integer)request.getSession().getAttribute("editParentId");
    	
    	if(parentid==oldParentId){
    		getmaxseqid=(Integer)request.getSession().getAttribute("editSeq");
    	}	
    	
    	out = response.getWriter();
    	out.print(getmaxseqid);
    
    	}
    	catch(Exception e){e.printStackTrace();}
    
    	//out.print(1);
		return null;
    	
    }
    
    @SkipValidation
    public String getLevel() throws IOException{
    	int level=0;
    			 
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
    				
    	PageMasterDao pagedao = new PageMasterDao();
    		    	//parentpagelist=pagedao.getParentPagedetails();
    	PrintWriter out=null;
    	
    	int parentid=Integer.parseInt(request.getParameter("parentid"));
         System.out.println("parentid---1111---"+parentid); 
        int paraentiddetails=pagedao.parentiddetails(parentid);
        System.out.println("paraentiddetails--2222----"+paraentiddetails);
      /*  if(paraentiddetails!=0){//main menu          
    	  level=pagedao.getLevel(parentid);
        }else{
          level=0;	
        }*/
        
        if(parentid==0){
        	level=0;
        }else{
        	
        	level=pagedao.getLevel(parentid);
        }
        
    	out = response.getWriter();
    	out.print(level);
    		    	
    	return null;
    		    	
    }
   
    @SkipValidation
    public String getLevelEdit() throws IOException{
    	int level=0;
    			 
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
    				
    	PageMasterDao pagedao = new PageMasterDao();
    		    	//parentpagelist=pagedao.getParentPagedetails();
    	PrintWriter out=null;
    	
    	int parentid=Integer.parseInt(request.getParameter("parentid"));
    	 int paraentiddetails=pagedao.parentiddetails(parentid);
         //System.out.println("paraentiddetails---"+paraentiddetails);        
        if(paraentiddetails!=0){//main menu          
    	  level=pagedao.getLevel(parentid);
        }else{
          level=0;	
        }
        
        int oldParentId=(Integer)request.getSession().getAttribute("editParentId");
    	
    	if(parentid==oldParentId){
    		
        	level=(Integer)request.getSession().getAttribute("editLevel");
        }
        
        
    	out = response.getWriter();
    	out.print(level);
    		    	
    	return null;
    		    	
    }
   
    
    @SkipValidation
    public String editAddPage()
    {
    	//System.out.println("hi i am in edit");
    	PageMasterDao pagedao = new PageMasterDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		//HttpSession mySession = request.getSession();
		//parentpagelist=pagedao.getParentPagedetails();
		pageMaster = pagedao.getPageDetails(Integer.parseInt(request.getParameter("pageid")));
		pageMaster.setParentname(pagedao.getParentName(pageMaster.getParent_id()));
		//mySession.setAttribute("currpagemaster", currpageMaster);
		
		//for edit page 
    	request.getSession().setAttribute("editParentId",pageMaster.getParent_id());
    	request.getSession().setAttribute("editLevel",pageMaster.getLevel());
    	request.getSession().setAttribute("editSeq",pageMaster.getSequence());
    	
    	int pageid=Integer.parseInt(request.getParameter("pageid"));
		String userlist=pagedao.getuserlistForPage(pageid);
		//System.out.println("userlist---"+userlist);
		String rolelist=pagedao.getrolelistForPage(pageid);
		//System.out.println("rolelist---"+rolelist);
		/*if(userlist.length()>0){
		msg="Page is Mapped to User-"+userlist;
		}else{
			msg="";
		}
		if(msg.length()>0)
		{
		if(rolelist.length()>0){
			msg+="\n and  Role-"+rolelist+".\n";
		}else{
			msg+="";
		}
		}else{
			
			if(rolelist.length()>0){
				msg+="\n Page is Mapped to role-"+rolelist+".\n";
			}else{
				msg+="";
			}
		}
		
		if(msg.length()==0){
			msg="0";
		}
    	*/
		int parentid=pagedao.parentiddetails(pageid);
		System.out.println("parentid------"+parentid);
		//String childpagelist=pagedao.getchildlist(pageid,parentid);
		
		if(parentid == 0){//main that it is parent and calculate its child.if child is present or not 
			String childpagelist=pagedao.getchildlist(pageid,parentid);
			if(childpagelist.equalsIgnoreCase("0")){//child is not present
				
				if(userlist.length()>0){
					msg=" Page is Mapped to User-"+userlist;
					}else{
						msg="";
					}
					if(msg.length()>0)
					{
					if(rolelist.length()>0){
						msg+="\n and  Role-"+rolelist;
					}else{
						msg+="";
					}
					}else{
						
						if(rolelist.length()>0){
							msg+="\n Page is Mapped to role-"+rolelist;
						}else{
							msg+="";
						}
					}
					
				
				
			}else{
				
				msg="Page is Mapped to childpage- "+childpagelist;
				if(userlist.length()>0){
					msg+=" and User- "+userlist;
					}else{
						msg+="";
					}
				if(msg.length()>0)
				{
				if(rolelist.length()>0){
					msg+=" and  Role-"+rolelist;
				}else{
					msg+="";
				}
			}else{
				if(rolelist.length()>0){
					msg+=" Page is Mapped to role-"+rolelist;
				}else{
					msg+="";
				}
			}
			
			}
			
		}else{//else condition only for child one level of parent page 
			
			/*int parentiddetails=pagedao.parentiddetails(parentid);
			if(parentiddetails==0){*/
				String childpagelist=pagedao.getchildlist(pageid,parentid);
				if(childpagelist.equalsIgnoreCase("0")){//child is not present
					
					if(userlist.length()>0){
						msg=" Page is Mapped to User-"+userlist;
						}else{
							msg="";
						}
						if(msg.length()>0)
						{
						if(rolelist.length()>0){
							msg+="\n and  Role-"+rolelist;
						}else{
							msg+="";
						}
						}else{
							
							if(rolelist.length()>0){
								msg+="\n Page is Mapped to role-"+rolelist;
							}else{
								msg+="";
							}
						}
						
					
					
				}else{
					
					msg="Page is Mapped to childpage- "+childpagelist;
					if(userlist.length()>0){
						msg+=" and User- "+userlist;
						}else{
							msg+="";
						}
					if(msg.length()>0)
					{
					if(rolelist.length()>0){
						msg+=" and  Role-"+rolelist;
					}else{
						msg+="";
					}
				}else{
					if(rolelist.length()>0){
						msg+=" Page is Mapped to role-"+rolelist;
					}else{
						msg+="";
					}
				}
				
				}
				
			//}
			
		
		
		
		}
		
		
		pageMaster.setMsg(msg);
		
    	//PageMasterDao pagedao = new PageMasterDao();
    	//return pagedao.saveEDitedPageDetails(requestType,pageMaster.getPage_id(),pageMaster);
		return "success";
    	
    }
    
    @SkipValidation
    public String getParentId()
    {
    	PageMasterDao rmDao=new PageMasterDao(); 
		
		//serviceTypeIds=rmDao.getServiceId();
		List<String> l1=rmDao.getParentId();
		List<String> l2=rmDao.getParentName();
		String regionTypeAjaxString = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		//int pageid=Integer.parseInt(request.getParameter("parentid"));
		////System.out.println("pageid--------"+pageid);
		////System.out.println("test");
		//System.out.println("id-------"+pageMaster.getPage_id());
        regionTypeAjaxString += "<option value=0>------select------</option>";
        for (int i = 0; i < l1.size(); i++) {
            regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">" + l2.get(i).toString() + "</option>";
        }
        //regionTypeAjaxString += "</select>";
        //System.out.println("regionTypeAjaxString="+regionTypeAjaxString);
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
    @SkipValidation
    public void iddetails(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
    	PageMasterDao rmDao=new PageMasterDao(); 
    	PrintWriter out;
    	try{
    	int id=Integer.parseInt(request.getParameter("prevType"));
    	int iddetails=rmDao.parentiddetails(id);
    	String regionTypeAjaxString=Integer.toString(iddetails);
    	out = response.getWriter();
		out.print(regionTypeAjaxString);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    @SkipValidation
	public String pagelistdetails() {
		////System.out.println("test");
		HttpServletRequest request = ServletActionContext.getRequest();
	
		String  pagetype = request.getParameter("pagetype");
		System.out.println("pagetype------"+pagetype);
		PageMasterDao pagemaster=new PageMasterDao();
    	
		
		List<String> l1 = pagemaster.getPageid(pagetype);
		List<String> l2 = pagemaster.getPageName(pagetype);
		String regionTypeAjaxString = "";
		 regionTypeAjaxString += "<select>";
		regionTypeAjaxString += "<option value=0>Main</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='"+l1.get(i)+"' value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		 regionTypeAjaxString += "</select>";
		//System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(orgtypeid);
		return null;

	}

    
   
    
    @SkipValidation
    public String getPageDetailsForInactive(){
    	try{
    		
    		//System.out.println("getPageDetailsForInactive");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			PageMasterDao pagedao=new PageMasterDao();
			String msg="";
			int pageid=Integer.parseInt(request.getParameter("pageid"));
			String userlist=pagedao.getuserlistForPage(pageid);
			//System.out.println("userlist---"+userlist);
			String rolelist=pagedao.getrolelistForPage(pageid);
			//System.out.println("rolelist---"+rolelist);
			int parentid=pagedao.parentiddetails(pageid);
			System.out.println("parentid------"+parentid);
			//String childpagelist=pagedao.getchildlist(pageid,parentid);
			//System.out.println("childpagelist--------"+childpagelist);
			
			if(parentid == 0){//main that it is parent and calculate its child.if child is present or not 
				String childpagelist=pagedao.getchildlist(pageid,parentid);
				if(childpagelist.equalsIgnoreCase("0")){//child is not present
					
					if(userlist.length()>0){
						msg=" Page is Mapped to User-"+userlist;
						}else{
							msg="";
						}
						if(msg.length()>0)
						{
						if(rolelist.length()>0){
							msg+="\n and  Role-"+rolelist;
						}else{
							msg+="";
						}
						}else{
							
							if(rolelist.length()>0){
								msg+="\n Page is Mapped to role-"+rolelist;
							}else{
								msg+="";
							}
						}
						
					
					
				}else{
					
					msg="Page is Mapped to childpage- "+childpagelist;
					if(userlist.length()>0){
						msg+=" and User- "+userlist;
						}else{
							msg+="";
						}
					if(msg.length()>0)
					{
					if(rolelist.length()>0){
						msg+=" and  Role-"+rolelist;
					}else{
						msg+="";
					}
				}else{
					if(rolelist.length()>0){
						msg+=" Page is Mapped to role-"+rolelist;
					}else{
						msg+="";
					}
				}
				
				}
				
			}else{//else condition only for child one level of parent page 
				
				/*int parentiddetails=pagedao.parentiddetails(parentid);
				if(parentiddetails==0){*/
					String childpagelist=pagedao.getchildlist(pageid,parentid);
					if(childpagelist.equalsIgnoreCase("0")){//child is not present
						
						if(userlist.length()>0){
							msg=" Page is Mapped to User-"+userlist;
							}else{
								msg="";
							}
							if(msg.length()>0)
							{
							if(rolelist.length()>0){
								msg+="\n and  Role-"+rolelist;
							}else{
								msg+="";
							}
							}else{
								
								if(rolelist.length()>0){
									msg+="\n Page is Mapped to role-"+rolelist;
								}else{
									msg+="";
								}
							}
							
						
						
					}else{
						
						msg="Page is Mapped to childpage- "+childpagelist;
						if(userlist.length()>0){
							msg+=" and User- "+userlist;
							}else{
								msg+="";
							}
						if(msg.length()>0)
						{
						if(rolelist.length()>0){
							msg+=" and  Role-"+rolelist;
						}else{
							msg+="";
						}
					}else{
						if(rolelist.length()>0){
							msg+=" Page is Mapped to role-"+rolelist;
						}else{
							msg+="";
						}
					}
					
					}
					
				//}
				
				
				
			}
			
			if(msg.length()==0){
				msg="0";
			}
			
            out.println(msg);			
		}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public boolean isSpecialCharDetails(String fieldvalue){
    	
    	String regex = "^[a-zA-Z0-9- /()_]*$";
    	 if(fieldvalue.matches(regex)){
    		 return true;
    		 }else{
    			 return false;
    		 }
    }
    
    public void validate(){
    	boolean flag=false;
    	CommonValidation commonv=new CommonValidation();
    	System.out.println("pagetype---///////////////----"+pageMaster.getPage_type());
    	
    	if((pageMaster.getPage_name().trim().equals(""))||(pageMaster.getPage_name()==null)||(pageMaster.getPage_name().trim().length()==0)){
			 addFieldError("pageName","Page Name is required");

		}
    	
			if(!isSpecialCharDetails(pageMaster.getPage_name())){
			addFieldError("pageName","Special Character not allowed");
			}
			
    	
    	/*if((pageMaster.getPath().trim().equals(""))||(pageMaster.getPath()==null)||(pageMaster.getPath().trim().length()==0)){
			 addFieldError("path","Path is required");

		}else{
			if(!commonv.isSpecialChar(pageMaster.getPath())){
			addFieldError("path","Special Character not allowed");
			}
		}*/
    	
    	/*if(pageMaster.getPage_id()>0 && pageMaster.getLevel()<=0){
    		addFieldError("level","Level is required");
    	}*/
    	
			
			
			
    	if(pageMaster.getPage_id()==0 && pageMaster.getLevel()<0){	
    		flag=true;
    		addFieldError("level","Level is required");
    		
    	}
    	
    	System.out.println("pageid---"+pageMaster.getPage_id());
    	
    	/*if(pageMaster.getPage_id()==0 && pageMaster.getLevel()>0 )
    	{
    		addFieldError("level","Level should be zero when its Parent is Main Menu");
    	}*/
    	
    	if(pageMaster.getParent_id()==0 && pageMaster.getLevel()>0)
    	{
    		addFieldError("level","Level should be zero when its Parent is Main Menu");
    	}
    	
    /*	if(pageMaster.getPage_id()>0 && pageMaster.getLevel()==0)
    	{
    		addFieldError("level","Level should be greater than zero");
    	}*/
    	
    	/*if(flag)
    	{*/
    	
    	if(pageMaster.getLevel()>=4){
    		addFieldError("level","Level should not be more than or equal 4");
    	}
    	//}
    	/*if(pageMaster.getSequence()<=0){
    		addFieldError("sequence","Sequence is required");
    	}*/
    }
    public void prepare() throws Exception {
    	PageMasterDao pagedao = new PageMasterDao();
    	//parentpagelist=pagedao.getParentPagedetails();
	}
    
}
