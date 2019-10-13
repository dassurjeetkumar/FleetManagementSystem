package com.trimax.its.toll.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.toll.dao.TollfeeDao;
import com.trimax.its.toll.model.TollBusStop;
import com.trimax.its.toll.model.Tollfee;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.model.OrganisationChart;

public class TollfeeAction extends ActionSupport implements Preparable {

	private Tollfee tollfee ;
	private int tid;
	private Map<Integer,String> busTypeMap;
	private Map<Integer,String> serviceTypeMap;
	List<Tollfee> list;
	  private ArrayList<String> list1;
	  
	@SkipValidation
	public String view() {
		return "view";
	}
	
	@SkipValidation
	public String add() {
		return "add";
	}
	
	@SkipValidation
	public String showTollfee() {
		return "success";
	}
	
	private OrganisationChart orgchart; 
	
	@SkipValidation
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String para=request.getParameter("tid");
		tid=Integer.parseInt(para);
		if(para!=null && para.isEmpty()){
			tid=Integer.parseInt(para);
		}
		
		System.out.println(tid);
		
		Common common=new Common();
		
		TollfeeDao dao=new TollfeeDao();
		tollfee=dao.getTollfeeById(tid);
		return "edit";
	}
	
	@SkipValidation
	public String copy() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String para=request.getParameter("tid1");
		tid=Integer.parseInt(para);
		if(para!=null && para.isEmpty()){
			tid=Integer.parseInt(para);
		}
		
		System.out.println(tid);
		
		Common common=new Common();
		
		TollfeeDao dao=new TollfeeDao();
		tollfee=dao.getTollfeeById(tid);
		return "copy";
	}
	
	public String execute()
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			
			TollfeeDao dao=new TollfeeDao();

			String[] dbcol={"","id","servicetype.service_type_name","amount","effect_start_date","effect_end_date","versionNo","busstop.busStopName"};

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


			int total = -1;
			total = dao.getTotalRecords(request,dbcol[col],dir,viewdeletedrecord);

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
	
	@Override
	public void prepare() throws Exception {
		TollfeeDao dao= new TollfeeDao();
		setServiceTypeMap(dao.getServiceType());
		//setBusTypeMap(dao.getBusStopName());
		
	}

	public Tollfee getTollfee() {
		return tollfee;
	}

	public void setTollfee(Tollfee tollfee) {
		this.tollfee = tollfee;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public Map<Integer, String> getBusTypeMap() {
		return busTypeMap;
	}

	public void setBusTypeMap(Map<Integer, String> busTypeMap) {
		this.busTypeMap = busTypeMap;
	}

	public Map<Integer, String> getServiceTypeMap() {
		return serviceTypeMap;
	}

	public void setServiceTypeMap(Map<Integer, String> serviceTypeMap) {
		this.serviceTypeMap = serviceTypeMap;
	}
	
	public OrganisationChart getOrgchart() {
		return orgchart;
	}

	public void setOrgchart(OrganisationChart orgchart) {
		this.orgchart = orgchart;
	}

		//Add Data into database
		public String Add(){
			HttpServletRequest request = ServletActionContext.getRequest();
			TollfeeDao dao=new TollfeeDao();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "Tollfeeaction!view");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			// tollfee.getBusstop().getBusStopName().replace(",","");
//			 if(tollfee.getBusstop().getBusStopName().charAt(0)==',')
//			 {
//				 BusStops bus=new BusStops(); 
//				 bus.setId(0);
//				 tollfee.setBusstop(bus);
//				 
//			 }
			//String string3 = Integer.toString(tollfee.getAmount());
			if(create.equalsIgnoreCase("Y")){
			if(tollfee.getServicetype().getService_type_id()==0 )
			{
				
				addFieldError("serId","Please select service type ");	
				
				return INPUT;
			}
			if(tollfee.getAmount()==null || tollfee.getAmount()=="")
			{
				addFieldError("amount","Please insert amount");			
				return INPUT;
			}
			
			if(tollfee.getAmount().trim().equals("") )
			{
				
				addFieldError("amount","Please insert amount");			
				return INPUT;
//				addActionError("DeviceType Name is Required");
				//complaint.setIdentification_data(complaint.getIdentification_data());
				
			}
			
			 if (!tollfee.getAmount().trim().matches("^[0-9 ]*$")) {
					addFieldError("amount","Amount should be number");
					return INPUT;
					}
			
			 if (tollfee.getAmount().trim().matches("[a-zA-Z!@#$%^&?`~*,._+=]*$")) {
			addFieldError("amount","Amount should be number");
			return INPUT;
			}
			if(tollfee.getEffect_start_date()==null || tollfee.getEffect_start_date().equals("")
					|| tollfee.getEffect_start_date().length()<=0)
			{
			addFieldError("effectiveStartDate","Please enter Effective Start Date.");	

				return INPUT;
			}
			
			if(tollfee.getEffect_end_date()==null || tollfee.getEffect_end_date().equals("")
					|| tollfee.getEffect_end_date().length()<=0)
			{
				addFieldError("effectiveEndDate","Please enter Effective End Date.");	
				
				return INPUT;
			}
			if (!tollfee.getVersionNo().trim().matches("^[0-9 ]*$")) {
				addFieldError("versionNo","Version should be number");
				return INPUT;
				}
			if(tollfee.getVersionNo().equalsIgnoreCase("") || tollfee.getVersionNo()==null )
			{
				addFieldError("versionNo","Please insert Version number");			
				return INPUT;
			}			
			
			if(tollfee.getBusstop().getId()==0 || tollfee.getBusstop().getBusStopName().equalsIgnoreCase(""))
            {
               //tollfee.getBusstop().getBusStopName().replace(",", "");
                addFieldError("busStop","Please Select Bus Name");           
                return INPUT;
            }
			int i=dao.addTollfee(tollfee);
			
			if(i==1)
			{				
				addActionError("Tollfee master already exist.");
				return INPUT;
			}
			else if(i==2)
			{
				addActionError("Tollfee master already exist.");
				return INPUT;
			}
			else if(i==3)
			{
				addActionError("Tollfee master already exist.");
				return INPUT;
			}
			else if(i==4)
			{
				addActionError("Tollfee master already exist.");				
				return INPUT;
			}
			else if(i==5)
			{
				addFieldError("effectiveStartDate","Effective End date should be greater than Effective Start Date");
				return INPUT;
			}
			
			else if(i>5){
				addActionMessage("Tollfee id "+i+" created successfully.");
			}
			else if(i==-1){
                addActionError("Database error retry again.");
                return INPUT;
            }
			
			//execute();
			return  SUCCESS;}
			else{
				return INPUT;
			}
		}
		
		//Update data
		public String update(){
			
			//System.out.println("Update.................??????????????????????<");
			HttpServletRequest request = ServletActionContext.getRequest();		
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "Tollfeeaction!view");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(delete.equalsIgnoreCase("Y")){
			if(tollfee.getServicetype().getService_type_id()==0 )
			{
				addFieldError("serId","Please select service type ");		
				
				return INPUT;
			}
			if(tollfee.getAmount()==null || tollfee.getAmount()=="")
			{
				addFieldError("amount","Please insert amount");			
				return INPUT;
			}
			if(tollfee.getAmount().matches("[A-Za-z_!@#$%^&?`~*,._+=]"))
			{
			 addFieldError("amount","Amount should be number");	
				return INPUT;
			} 
			
			
			if(tollfee.getAmount().trim().equals("") )
			{
				
				addFieldError("amount","Please insert amount");			
				return INPUT;
//				addActionError("DeviceType Name is Required");
				//complaint.setIdentification_data(complaint.getIdentification_data());
				
			}
			 if (!tollfee.getAmount().trim().matches("^[0-9 ]*$")) {
					addFieldError("amount","Amount should be number");
					return INPUT;
					}
			if(tollfee.getEffect_start_date()==null || tollfee.getEffect_start_date().equals("")
					|| tollfee.getEffect_start_date().length()<=0)
			{
				addFieldError("effectiveStartDate","Please enter Effective Start Date.");		
				return INPUT;
			}
			if(tollfee.getEffect_end_date()==null || tollfee.getEffect_end_date().equals("")
					|| tollfee.getEffect_end_date().length()<=0)
			{
				addFieldError("effectiveEndDate","Please enter Effective End Date.");		
				return INPUT;
			}
			if (!tollfee.getVersionNo().trim().matches("^[0-9 ]*$")) {
				addFieldError("versionNo","Version should be number");
				return INPUT;
				}
			if(tollfee.getVersionNo().equalsIgnoreCase("") || tollfee.getVersionNo()==null )
			{
				addFieldError("versionNo","Please insert Version number");			
				return INPUT;
			}
			
			
			
			TollfeeDao dao=new TollfeeDao();			

			int i=dao.updateTollfee(tollfee);
			
			if(i==-1)
			{
				addActionError("Tollfee master already exist.");
				return INPUT;
			}
			else if(i==-2)
			{
				addActionError("Tollfee master already exist.");
				return INPUT;
			}
			else if(i==-3)
			{
				addActionError("Tollfee master already exist.");
				return INPUT;
			}
			else if(i==-4)
			{
				addActionError("Tollfee master already exist.");				
				return INPUT;
			}
			else if(i==-5)
			{
				addFieldError("effectiveStartDate","Effective End date should be greater than Effective Start Date");
				return INPUT;
			}
			
			else if(i>0){
				addActionMessage("Toll fee id "+tollfee.getId()+" updated successfully.");
			}else{
					addActionError("Database error retry again.");
					return INPUT;
				}
			
			//execute();
			return SUCCESS;
			}else{
				return INPUT;
			}
		}
		
		@SkipValidation
		public String delete() {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "Tollfeeaction!view");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			String para=request.getParameter("tid3");
			tid=Integer.parseInt(para);
			if(para!=null && para.isEmpty()){
				tid=Integer.parseInt(para);
			}
			if(delete.equalsIgnoreCase("Y")){
				
			
			TollfeeDao dao=new TollfeeDao();
			String s=dao.deleteTollfee(tid);
			

			if(s.equals("success")){
				addActionMessage("Toll Fee id "+tid+" deleted successfully.");
			}else{
				addActionMessage("Database error retry again.");
			}
			
			return "success";}
			else{
				addActionMessage("Access Denied - User Does Not Have Access Privileges");
				return "success";
			}
		}
				
				//Add Copy Data into database
				public String copyTollFee(){
					HttpServletRequest request = ServletActionContext.getRequest();
					TollfeeDao dao=new TollfeeDao();
					if(tollfee.getServicetype().getService_type_id()==0 )
					{
						addFieldError("serviceTypeMap","Please select service type ");			
						return INPUT;
					}
					if(tollfee.getAmount()==null || tollfee.getAmount()=="")
					{
						addFieldError("amount","Please insert amount");			
						return INPUT;
					}
					
					if (!tollfee.getAmount().trim().matches("^[0-9 ]*$")) {
						addFieldError("amount","amount should be number");
						return INPUT;
						}
					if(tollfee.getAmount().matches("[A-Za-z_!@#$%^&?`~*,._+=]"))
					
					{
					 addFieldError("amount","Amount should be number");			
						return INPUT;
					} 
					if(tollfee.getEffect_start_date()==null || tollfee.getEffect_start_date().equals("")
							|| tollfee.getEffect_start_date().length()<=0)
					{
						addFieldError("effectiveStartDate","Please enter Effective Start Date.");		
						return INPUT;
					}
					if(tollfee.getEffect_end_date()==null || tollfee.getEffect_end_date().equals("")
							|| tollfee.getEffect_end_date().length()<=0)
					{
						addFieldError("effectiveEndDate","Please enter Effective End Date.");		
						return INPUT;
					}
					
					if (!tollfee.getVersionNo().trim().matches("^[0-9 ]*$")) {
						addFieldError("versionNo","Version should be number");
						return INPUT;
						}
					if(tollfee.getVersionNo().equalsIgnoreCase("") || tollfee.getVersionNo()==null )
					{
						addFieldError("versionNo","Please insert Version number");			
						return INPUT;
					}
					
					if((String.valueOf(tollfee.getVersionNo()).toString().matches("[A-Za-z_!@#$%^&?`~*,._+=]")))
					{
					 addFieldError("versionNo","Version number should be number");			
						return INPUT;
					} 
					
					int i=dao.addTollfee(tollfee);
					
					if(i==1)
					{
						//addFieldError("effectiveStartDate","Toll Fee Master already exist.");	
						addActionError("Tollfee master already exist.");
						return INPUT;
					}
					else if(i==2)
					{
						addFieldError("effectiveEndDate","Toll Fee Master already exist.");		
						addActionError("Tollfee master already exist.");
						return INPUT;
					}
					else if(i==3)
					{
						/*addFieldError("effectiveEndDate","Effective End date is already exist");
						addFieldError("effectiveStartDate","Effective Start date is already exist");*/
						addActionError("Tollfee master already exist.");
						return INPUT;
					}
					else if(i==4)
					{
						addActionError("Tollfee master already exist.");
						//addFieldError("effectiveStartDate","Effective Start date should be greater than Effective End Date");
						return INPUT;
					}
					else if(i==5)
					{
						addFieldError("effectiveStartDate","Effective Start date should be greater than Effective End Date");
						return INPUT;
					}
					
					else if(i>6){
						addActionMessage("Tollfee id "+i+" copied successfully.");
					}
					else if(i==-1){
                        addActionError("Database error retry again.");
                        return INPUT;
                    }
					
					//execute();
					return  SUCCESS;
				}
		@SkipValidation
		public String getBusStopDrop(){
			TollfeeDao tollfeedao = new TollfeeDao();
			HttpServletRequest request=ServletActionContext.getRequest();
		    String idBusStopName = request.getParameter("id");
		    
//			 list = busstopsdao.getBusStopDropList();
//			 String busDet="";
			 List<BusStops> list = tollfeedao.getBusStopDropList(idBusStopName);
			
				//int i=1;
				List<TollBusStop> list1 = new ArrayList<TollBusStop>();
				for(BusStops bustop : list){
					TollBusStop bus = new TollBusStop();
					//bus.setId(bustop.getId());
					//bus.setValue("a"+i);
					bus.setId(bustop.getId());
					bus.setValue(bustop.getBusStopName());
					bus.setBusStopName(bustop.getBusStopName());
					bus.setStop_direction(bustop.getStop_direction()!=null ? " - "+bustop.getStop_direction().trim() :"");
					/*bus.setLongitude(bustop.getLongitude());
					bus.setLatitude(bustop.getLatitude());*/
					list1.add(bus);
					//i+=1;
				}

			 HttpServletResponse response=ServletActionContext.getResponse();
			//System.out.println("Bus stops Size------>"+list.size()+busDet);
			PrintWriter out;
			try {
				out = response.getWriter();
				  out.print(new JSONArray(list1));
			} catch (IOException e) {
				// TODO Auto-generated catch block	
				e.printStackTrace();
			}
	     

			return null;
		
		}

		public List<Tollfee> getList() {
			return list;
		}

		public void setList(List<Tollfee> list) {
			this.list = list;
		}

		public ArrayList<String> getList1() {
			return list1;
		}

		public void setList1(ArrayList<String> list1) {
			this.list1 = list1;
		}

}
