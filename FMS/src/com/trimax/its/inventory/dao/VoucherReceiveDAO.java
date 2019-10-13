package com.trimax.its.inventory.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;


import com.trimax.doa.ticketing.model.TicketInventoryMaster;
import com.trimax.doa.ticketing.model.TicketInvoiceMaster;
import com.trimax.external.voucher.transfer.InventoryVoucherTransfer;
import com.trimax.external.voucherTransfer.InvoiceMasterModel;
import com.trimax.external.voucherTransfer.TicketInventoryDetailModel;
import com.trimax.external.voucherTransfer.TicketInventoryMasterModel;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtilTick;

public class VoucherReceiveDAO {
	
	Common common = new Common();
	@SuppressWarnings("finally")
	public Map<String,String> divisionList(){
		Session session = null;
		Map<String,String> divisionListOfMap = null;
		List<Map<String,String>> aliasToValueMapList = null;
		try{
			session = HibernateUtilTick.getSession("");
			String sql = "SELECT org_chart_id, org_name FROM org_chart  oc inner join org_type ot on ot.org_type_id = oc.org_type_id " +
					" where ot.org_type_id='2'  and oc.deleted_status='0' and ot.status='ACTIVE'";
			aliasToValueMapList = session.createSQLQuery(sql).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).list();
			if(aliasToValueMapList.size()>0){
				divisionListOfMap = new HashMap<String,String>(); 
				for(int i=0;i<aliasToValueMapList.size();i++){
					divisionListOfMap.put(String.valueOf(aliasToValueMapList.get(i).get("org_chart_id")),aliasToValueMapList.get(i).get("org_name"));
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
			return divisionListOfMap;
		}
	}
	@SuppressWarnings("finally")
	public String getUnitNames(int orgtypeid) {		
		Session session = null;
		List list = null;
		String returnString = "";
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
	
			String qry = "select org_chart_id,org_name from org_chart where org_name!='(NULL)' and parent_id="
					+ orgtypeid + " and deleted_status='0' ";

			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);   
			List<Map<String, Object>> aliasToValueMapList = query.list();
		
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					if(i!=0){
						returnString += ":";
					}
					Map<String, Object> rs = aliasToValueMapList.get(i);
					returnString += rs.get("org_chart_id")+","+rs.get("org_name");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return returnString;
		}
		
	}

	/*public List<Map<String,String>> getVoucherDetailsofDepot(String parms[]) throws Exception{
		try{
			InventoryVoucherTransfer voucher = new InventoryVoucherTransfer();
			return voucher.getVouchers(parms);
		 }catch(Exception e){
			return null;
		 }
		
	}*/
	@SuppressWarnings("finally")
	public boolean getSyncVoucherDetails(String params[],String voucherId){
		boolean isSucccess  = false;
		Session session = null;
		try{
			InventoryVoucherTransfer voucher = new InventoryVoucherTransfer();
			InvoiceMasterModel  invoiceMaster = voucher.getSyncVoucher(params, voucherId);
			session = HibernateUtilTick.getSession("");
			session.getTransaction().begin();
			String userId = String.valueOf(ServletActionContext.getRequest().getSession().getAttribute("userid"));
			int invoiceId = 0 ;
			if(invoiceMaster!=null){
				
				//String queryForMaxInviceNumber = "select ifnull(count(ticket_invoice_mast_id),0) maxId  from ticket_invoice_master where voucher_type='T'";
				//int count = common.getDBResultInt(session, queryForMaxInviceNumber, "maxId");
				String status = "";
				if(invoiceMaster.getIssueToOrgId().equals("1")){
					status = "New";
				}else{
					status = "In Transit";
				}
				
				if(!invoiceMaster.getIssueToOrgId().equals("1")){
					//String voucherNumber = generateVoucherNumbar(count+1,10);
					String queryForMaster = "INSERT INTO `ticket_invoice_master` (`voucher_number`, `issue_to_unit`, `issue_to_unit_id`, `issue_from_unit`, " +
							" `issue_from_unit_id`, `indent_number`, `created_by`, `current_status`, `status`, `created_date`, `stock_value`,`updated_by`,`voucher_type`) VALUES " +
							" ('"+invoiceMaster.getVoucherNumber()+"', '"+invoiceMaster.getIssueToOrgTypeId()+"', '"+invoiceMaster.getIssueToOrgId()+"'," +
							" '"+invoiceMaster.getIssueFromOrgTypeId()+"', '"+invoiceMaster.getIssueFromOrgId()+"', '"+invoiceMaster.getIndentNumber()+"', " +
							" '"+userId+"', 'In Transit', 'ACTIVE',now(),0,'0','T');";
					session.createSQLQuery(queryForMaster).executeUpdate();
					
					String queryForInvoiceId = "select last_insert_id() as id from ticket_invoice_master";   
					invoiceId = common.getDBResultInt(session, queryForInvoiceId, "id");
				}
				
				
				for(int i=0;i<invoiceMaster.getListOfMasterModel().size();i++){
					
					TicketInventoryMasterModel masterModel = invoiceMaster.getListOfMasterModel().get(i);
					String queryForInventoryMaster = "INSERT INTO `ticket_inventory_master` (`denoimination_type_manual_id`,  `ticket_type_manual_id`, " +
							" `denomination_key`, `opening_number`, `closing_number`, `pass_day`, `number_of_tickets`, `number_of_passes`, `number_of_books`, `value`," +
							" `unit_type`, `unit_name`, `current_status`, `status`, `created_by`, `created_date`,`updated_by`,`priority`)" +
							" VALUES ('"+masterModel.getDenominationTypeManualId()+"', '"+masterModel.getTicketTypeManualId()+"', '"+masterModel.getDenominationKey()+"', " +
							" '"+masterModel.getOpeningNumber()+"', '"+masterModel.getClosingNumber()+"', '"+masterModel.getPassDay()+"', '"+masterModel.getNoOfTickets()+"'," +
							" '"+masterModel.getNoOfPasses()+"', '"+masterModel.getNoOfBooks()+"', '"+masterModel.getValue()+"','"+invoiceMaster.getIssueToOrgId()+"', '"+invoiceMaster.getIssueToOrgTypeId()+"', " +
							" '"+status+"', 'ACTIVE', '"+userId+"', now(),'0','0');";
					session.createSQLQuery(queryForInventoryMaster).executeUpdate();
					
					String queryForHeaderId = "select last_insert_id() as id from ticket_invoice_master";   
					int headerId = common.getDBResultInt(session, queryForHeaderId, "id");
					
					for(int j=0;j<masterModel.getListOfTicketDetails().size();j++){
						
						TicketInventoryDetailModel detailModel = masterModel.getListOfTicketDetails().get(j);
						String partialBook = detailModel.getPartialBook()==null?"N":detailModel.getPartialBook();
						
						String queryForInventoryDetail = "INSERT INTO `ticket_inventory_details` (`ticket_inventory_mst_id`, `denomination_type_manual_id`, " +
								" `ticket_type_manual_id`, `denomination_key`, `pass_day`, `opening_number`, `closing_number`, `number_of_tickets`, " +
								" `number_of_passes`, `number_of_books`, `value`, `unit_type`, `unit_name`, `partial_book`, `current_status`, `status`, " +
								" `created_by`, `created_date`,`updated_by`,`priority`) " + 
								" VALUES ('"+headerId+"', '"+detailModel.getDenominationTypeManualId()+"', '"+detailModel.getTicketTypeManualId()+"'," +
								" '"+detailModel.getDenominationKey()+"', '"+detailModel.getPassDay()+"', '"+detailModel.getOpeningNumber()+"', '"+detailModel.getClosingNumber()+"'," +
								" '"+detailModel.getNoOfTickets()+"', '"+detailModel.getNoOfPasses()+"', '"+detailModel.getNoOfBooks()+"', '"+detailModel.getValue()+"'," +
								" '"+invoiceMaster.getIssueToOrgId()+"', '"+invoiceMaster.getIssueToOrgTypeId()+"'," +
								" '"+partialBook+"', '"+status+"', 'ACTIVE', '"+userId+"', now(),'0','0')";
						session.createSQLQuery(queryForInventoryDetail).executeUpdate();
						
						if(!invoiceMaster.getIssueToOrgId().equals("1")){
							String queryForDetailId = "select last_insert_id() as id from ticket_inventory_details";   
							int detailId = common.getDBResultInt(session, queryForDetailId, "id");
							
							String invoiceDetailsQuery = "INSERT INTO `ticket_invoice_details` (`ticket_invoice_mast_id`," +
									"  `ticket_inventory_mst_id`, `ticket_inventory_det_id`, `created_by`, " +
									" `created_date`) VALUES ('"+invoiceId+"','"+headerId+"', '"+detailId+"', '"+userId+"', now());";
							session.createSQLQuery(invoiceDetailsQuery).executeUpdate();
						}
					}
				}
			}
			session.getTransaction().commit();
			isSucccess = true;
		 }catch(Exception e){
			 e.printStackTrace();
			 isSucccess = false;
		 }finally{
			 if(session != null){
				 session.close();
			 }
 			 return isSucccess;
		 }
		
	}
	@SuppressWarnings("finally")
	public boolean setStatusVoucherAsAccepted(String params[],String voucherId){
		boolean isSuccess = false;
		try{
			InventoryVoucherTransfer voucher = new InventoryVoucherTransfer();
			isSuccess = voucher.setStatusAsAccepted(params, voucherId);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return isSuccess;
		}
	}
	@SuppressWarnings("finally")
	public boolean rejectVoucher(String params[],String voucherId){
		boolean isSuccess = false;
		try{
			InventoryVoucherTransfer voucher = new InventoryVoucherTransfer();
			isSuccess = voucher.getRejectVoucher(params, voucherId);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return isSuccess;
		}
	}
	@SuppressWarnings("finally")
	public String[] getDepotCredentials(String depotId){
		
		Session session = null;
		String[] depotParams = null;
		List<Map<String,String>> aliasToValueMapList = null;
		try{
			session = HibernateUtilTick.getSession("");
			String sql = "SELECT `ip_address`, `user_name`, `password`,`dababase_name` FROM `depot_db_details` WHERE `depot_id` = '"+depotId+"'";
			Query queryObjcet = session.createSQLQuery(sql).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObjcet.list();
			if(aliasToValueMapList.size()>0){
				depotParams = new String[5];
				Map<String,String> rs = aliasToValueMapList.get(0);
				depotParams[0] = rs.get("ip_address");
				depotParams[1] = rs.get("user_name");
				depotParams[2] = rs.get("password");
				depotParams[3] = rs.get("dababase_name");
				depotParams[4] = depotId;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			return depotParams;
		}
	}
	@SuppressWarnings("finally")
	public List<String[]> getAllDepotCredentials(){
		
		Session session = null;
		String[] depotParams = null;
		List<String[]> listOfDepotParams = null ;
		List<Map<String,String>> aliasToValueMapList = null;
		try{
			session = HibernateUtilTick.getSession("");
			String sql = "SELECT `depot_id`,`ip_address`, `user_name`, `password`,`dababase_name` FROM `depot_db_details` ";
			Query queryObjcet = session.createSQLQuery(sql).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObjcet.list();
			listOfDepotParams = new ArrayList<String[]>();
			if(aliasToValueMapList.size()>0){
				for(int i=0;i<aliasToValueMapList.size();i++){
					depotParams = new String[5];
					Map<String,String> rs = aliasToValueMapList.get(i);
					depotParams[0] = rs.get("ip_address");
					depotParams[1] = rs.get("user_name");
					depotParams[2] = rs.get("password");
					depotParams[3] = rs.get("dababase_name");
					depotParams[4] = String.valueOf(rs.get("depot_id"));
					
					listOfDepotParams.add(depotParams);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			return listOfDepotParams;
		}
	}

	/*public static void main(String args[]){
		InventoryVoucherTransfer vouche3r = new InventoryVoucherTransfer();
		String[] params = new String[]{"10.20.41.233","tester","tester","bmtc_doa","42"};
		try{
			System.out.println(vouche3r.getVouchers(params));
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	public String generateVoucherNumbar(int voucherSeqNo,int size) throws Exception{
		
		String voucherNumber = new String(String.valueOf(voucherSeqNo));
        StringBuilder sb = new StringBuilder(String.valueOf(voucherSeqNo));
        StringBuilder sb2 = new StringBuilder();
        int m1 = voucherNumber.length();
        if (m1 >= size) {
//            str1.substring(0, a1 - 1);
            return voucherNumber;
        }
        size = size - m1;
        for (int i = 0; i < size; i++) {
            sb2.append("0");
        }
        sb2.append(sb);
        String sb1 = sb2.toString();
        return sb1;
    }
	
	@SuppressWarnings("finally")
	public InvoiceMasterModel getVoucherDetails(String params[],String voucherId){
		
		InvoiceMasterModel voucherDetails = null;
		try {
			InventoryVoucherTransfer voucher = new InventoryVoucherTransfer();
			voucherDetails = voucher.getSyncVoucher(params, voucherId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return voucherDetails;
		}
	}
}
