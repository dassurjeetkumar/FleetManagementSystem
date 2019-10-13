package com.trimax.its.fare.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.fare.model.FareChart;
import com.trimax.its.fare.model.Farechart_Master;
import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.fare.model.RateMasterDetail;
import com.trimax.its.fare.model.RoutePoint_Fare;
import com.trimax.its.model.DependentTableCheck;
import com.trimax.its.model.User;
import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.ScheduleService;
import com.trimax.its.util.HibernateUtil;

public class FareChartMasterDao {

    @SuppressWarnings("unchecked")
    public List<Farechart_Master> getFareChartMaster() {
        List<Farechart_Master> list = null;
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        Query query = session.createQuery("from Farechart_Master ");
        query.setMaxResults(100);
        list = (List<Farechart_Master>) query.list();
        //System.out.println(list.size());
        return list;

    }

    public int getTotalRecordsNative(HttpServletRequest request, String col, String dir) {
//		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		Query query = session.createSQLQuery(
//				"select count(*) as count from farechart_master where deleted_status=0").addScalar(
//				"count", Hibernate.INTEGER);
//		List<Integer> list = query.list();
//		int cnt = list.get(0);
        int cnt = 0;
        try {
            Session session = HibernateUtil.getSession("hibernate.cfg.xml");
            Criteria criteria = session.createCriteria(Farechart_Master.class);
            //
            criteria.add(Restrictions.ne("deleted_status", 1));

            criteria.createAlias("route", "route");

            if (!col.equals("")) {
                if (dir.equals("asc")) {
                    criteria.addOrder(Order.asc(col));
                } else {
                    criteria.addOrder(Order.desc(col));
                }
            }

            if (!request.getParameter("sSearch").equals("")) {
                String search_term = request.getParameter("sSearch").trim();

                Junction conditionGroup = Restrictions.disjunction();

                conditionGroup.add(Restrictions.like("route.route_number", "%"
                        + search_term + "%"));
                conditionGroup.add(Restrictions.like("farechart_name", "%"
                        + search_term + "%"));

                criteria.add(conditionGroup);

            }

            List<Farechart_Master> list = criteria.list();
            cnt = list.size();
        } catch (Exception e) {

        }
        return cnt;
    }

    public int getTotalRecords(HttpServletRequest request, String col, String dir, String viewdeletedrecord) {
        Session session = null;
        int cnt = 0;
        String sql = "";
        try {
            session = HibernateUtil.getSession("hibernate.cfg.xml");
            if (viewdeletedrecord.equalsIgnoreCase("Y")) {
                sql = "select farechart_master_id"
                        + " from farechart_master fcm"
                        + " left join rate_master rm on rm.rate_master_id=fcm.rate_master_id"
                        + " left join route r on r.route_id=fcm.route_id"
                        + " left join service_type st on st.service_type_id=fcm.service_type_id";

                sql += " Where fcm.deleted_status=0 and (fcm.effect_end_date is null or fcm.effect_end_date>=curdate()) ";
            } else {
                sql = "select farechart_master_id"
                        + " from farechart_master fcm"
                        + " left join rate_master rm on rm.rate_master_id=fcm.rate_master_id"
                        + " left join route r on r.route_id=fcm.route_id"
                        + " left join service_type st on st.service_type_id=fcm.service_type_id";

                sql += " Where fcm.deleted_status=0 and (fcm.effect_end_date is null or fcm.effect_end_date>=curdate()) ";
            }
            String search_term = request.getParameter("sSearch");

            if (search_term != null && !search_term.equals("")) {
                search_term = search_term.trim();
                sql += " AND( r.route_number like '" + search_term + "%'";
                sql += " OR farechart_name like '" + search_term + "%')";

            }
//System.out.println("==="+sql);
            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> list = query.list();
            cnt = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cnt;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getDataNativeSql(int total, HttpServletRequest request, String col, String dir) {
        JSONObject result = new JSONObject();
        Map<Integer, String> routeNumberMap;
        Map<Integer, String> serviceTypeMap;
        Map<Integer, String> versionNameMap;
        try {
            routeNumberMap = getRouteNumberAll();//getRouteNumber();
            serviceTypeMap = getServiceType();
            versionNameMap = getRateMasterVersionMap();
            int totalAfterFilter = total;

            Session session = HibernateUtil.getSession("hibernate.cfg.xml");
            Criteria criteria = session.createCriteria(Farechart_Master.class);
            //
            criteria.add(Restrictions.ne("deleted_status", 1));

            criteria.createAlias("route", "route");

            if (!col.equals("")) {
                if (dir.equals("asc")) {
                    criteria.addOrder(Order.asc(col));
                } else {
                    criteria.addOrder(Order.desc(col));
                }
            }

            if (!request.getParameter("sSearch").equals("")) {
                String search_term = request.getParameter("sSearch").trim();

                Junction conditionGroup = Restrictions.disjunction();

                conditionGroup.add(Restrictions.like("route.route_number", "%"
                        + search_term + "%"));
                conditionGroup.add(Restrictions.like("farechart_name", "%"
                        + search_term + "%"));

                criteria.add(conditionGroup);

            }
            criteria.setFirstResult(Integer.parseInt(request
                    .getParameter("iDisplayStart")));
            criteria.setMaxResults(Integer.parseInt(request
                    .getParameter("iDisplayLength")));
            List<Farechart_Master> list = criteria.list();
            JSONArray array = new JSONArray();
			//System.out.println("List size----->" + list.size() + "\t"
            //		+ request.getParameter("iDisplayStart"));
            Common common = new Common();
            for (int i = 0; i < list.size(); i++) {
                JSONArray ja = new JSONArray();

                ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
                        + list.get(i).getFarechart_master_id()
                        + "' value='"
                        + list.get(i).getFarechart_master_id() + "'/>");
                ja.add(list.get(i).getFarechart_master_id());//fare chart master id
                ja.add(list.get(i).getFarechart_name() != null ? list.get(i).getFarechart_name().toString().replaceAll(" ", "&nbsp;") : "");
                ja.add(versionNameMap.get((list.get(i).getRate_master_id())));

                ja.add(routeNumberMap.get(list.get(i).getRoute().getRoute_id()));

                ja.add(serviceTypeMap.get(list.get(i).getService_type_id()));

                String passenger = "Adult";
                if (list.get(i).getPassenger_type_id() == 1) {
                    passenger = "Child";
                } else {
                    if (list.get(i).getPassenger_type_id() == 3) {
                        passenger = "Senior Citizen";
                    }
                }
                ja.add(passenger);

                String startDate = list.get(i).getEffect_start_date() != null ? common.getDateFromDateTime(list
                        .get(i).getEffect_start_date().toString()) : "";
                ja.add(startDate.replace(".0", ""));

                ja.add(list.get(i).getEffect_end_date() != null ? common.getDateFromDateTime(list.get(i)
                        .getEffect_end_date().toString()) : "");
				//ja.add(list.get(i).getOpfrom_time() != null ? list.get(i)
                //		.getOpfrom_time() : "");

				//ja.add(list.get(i).getOpto_time() != null ? list.get(i)
                //		.getOpto_time() : "");
                String ntService = "No";
                if (list.get(i).getNignt_service() != null && list.get(i).getNignt_service().equals("Y")) {
                    ntService = "Yes";
                }
                ja.add(ntService);

                String peakHr = "No";
                if (list.get(i).getPeak_time() != null && list.get(i).getPeak_time().equals("Y")) {
                    peakHr = "Yes";
                }
                ja.add(peakHr);

                String flexiFare = "No";
                if (list.get(i).getFlexi_fare() != null && list.get(i).getFlexi_fare().equals("Y")) {
                    flexiFare = "Yes";
                }
                ja.add(flexiFare);

                array.add(ja);
                //System.out.println("Array----->" + array);
            }

            totalAfterFilter = total;//getTotalRecords(request,col,dir);
            result.put("aaData", array);
            result.put("iTotalRecords", total);

            result.put("iTotalDisplayRecords", totalAfterFilter);

//			System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getData(int total, HttpServletRequest request, String col, String dir, String sEARCH_TERM2) {
        JSONObject result = new JSONObject();
        Session session = null;
        String viewdeletedrecord = (String) request.getSession().getAttribute("viewdeletedrecord");
        String sql = "";
//        System.out.println("..................we are in getData()...................");
        try {
            int totalAfterFilter = total;

            session = HibernateUtil.getSession("hibernate.cfg.xml");
//            System.out.println("in session =="+session);
            if (viewdeletedrecord.equalsIgnoreCase("Y")) {
                sql = "select farechart_master_id,farechart_name,version_number_service_stype,fcm.route_id,st.service_type_name"
                        + ",fcm.passenger_type_id,fcm.effect_start_date,fcm.effect_end_date,fcm.nignt_service,fcm.peak_time,"
                        + " fcm.flexi_fare,ifnull(r.route_number,'') route_number,ifnull(r.route_direction,'') as route_direction,r.effective_from,r.effective_till,fcm.deleted_status"
                        + " from farechart_master fcm"
                        + " left join rate_master rm on rm.rate_master_id=fcm.rate_master_id"
                        + " left join route r on r.route_id=fcm.route_id"
                        + " left join service_type st on st.service_type_id=fcm.service_type_id";

                sql += " Where  (fcm.effect_end_date is null or fcm.effect_end_date>=curdate()) ";
            } else {
                sql = "select farechart_master_id,farechart_name,version_number_service_stype,fcm.route_id,st.service_type_name"
                        + ",fcm.passenger_type_id,fcm.effect_start_date,fcm.effect_end_date,fcm.nignt_service,fcm.peak_time,"
                        + " fcm.flexi_fare,ifnull(r.route_number,'') as route_number,ifnull(r.route_direction,'') as route_direction ,r.effective_from,fcm.deleted_status,r.effective_till"
                        + " from farechart_master fcm"
                        + " left join rate_master rm on rm.rate_master_id=fcm.rate_master_id"
                        + " left join route r on r.route_id=fcm.route_id"
                        + " left join service_type st on st.service_type_id=fcm.service_type_id";

                sql += " Where fcm.deleted_status=0 and (fcm.effect_end_date is null or fcm.effect_end_date>=curdate()) ";

            }
//            System.out.println("queryyy "+sql);
            String search_term = request.getParameter("sSearch");

            if (search_term != null && !search_term.equals("")) {
                search_term = search_term.trim();
                sql += " AND( r.route_number like '" + search_term + "%'";
                sql += " OR farechart_name like '" + search_term + "%'";
                sql += " OR farechart_master_id like '" + search_term + "%'";
                sql += " OR version_number_service_stype like '" + search_term + "%')";

            }

            if (!col.equals("")) {
                if (dir.equals("asc")) {
                    // sql += " order by "+col+" asc";
                    if (sEARCH_TERM2.equals("")) {
                        sql += " order by " + col + " asc";
                    } else {
                        sql += " order by r.route_number asc";
                    }
                } else {
                    sql += " order by " + col + " desc";
                }
            }
            sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
//System.out.println("in final query  "+sql);
            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> list = query.list();

            JSONArray array = new JSONArray();

            Common common = new Common();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> rs = list.get(i);

                JSONArray ja = new JSONArray();

                ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
                        + rs.get("farechart_master_id")
                        + "' value='" + rs.get("farechart_master_id") + "'/>");
                ja.add(rs.get("farechart_master_id"));//fare chart master id
                ja.add(rs.get("farechart_name") != null ? rs.get("farechart_name").toString().replaceAll(" ", "&nbsp;") : "");
                ja.add(rs.get("version_number_service_stype"));

                //format route string
                String sDate = rs.get("effective_from") != null ? common.getDateToDate4(rs.get("effective_from").toString()) : "";
                String eDate = rs.get("effective_till") != null ? common.getDateToDate4(rs.get("effective_till").toString()) : "";
//                System.out.println("route number=="+rs.get("route_number").toString());
                if(rs.get("route_number").toString()== null || rs.get("route_number").toString().equals("")){
                	ja.add("");
                }else{
                ja.add(rs.get("route_number").toString() + "-" + rs.get("route_direction").toString() + "-(" + sDate + " - " + eDate + ")");
                }
                ja.add(rs.get("service_type_name"));

                String passenger = "Adult";
                int pid = Integer.parseInt(rs.get("passenger_type_id").toString());
                if (pid == 1) {
                    passenger = "Child";
                } else {
                    if (pid == 3) {
                        passenger = "Senior Citizen";
                    }
                }
                ja.add(passenger);

                String startDate = rs.get("effect_start_date") != null ? common.getDateFromDateTime(rs.get("effect_start_date").toString()) : "";
                ja.add(startDate.replace(".0", ""));

                ja.add(rs.get("effect_end_date") != null ? common.getDateFromDateTime(rs.get("effect_end_date").toString()) : "");
				//ja.add(list.get(i).getOpfrom_time() != null ? list.get(i)
                //		.getOpfrom_time() : "");

				//ja.add(list.get(i).getOpto_time() != null ? list.get(i)
                //		.getOpto_time() : "");
                String ntService = "No";
                if (rs.get("nignt_service") != null && rs.get("nignt_service").toString().startsWith("Y")) {
                    ntService = "Yes";
                }
                ja.add(ntService);

                String peakHr = "No";
                if (rs.get("peak_time") != null && rs.get("peak_time").toString().startsWith("Y")) {
                    peakHr = "Yes";
                }
                ja.add(peakHr);

                String flexiFare = "No";
                if (rs.get("flexi_fare") != null && rs.get("flexi_fare").toString().startsWith("Y")) {
                    flexiFare = "Yes";
                }
                ja.add(flexiFare);
                if (viewdeletedrecord.equalsIgnoreCase("Y")) {
                    String deletedstatus = rs.get("deleted_status").toString();
                    System.out.println("deletedstatus---" + deletedstatus);

                    if (deletedstatus.equalsIgnoreCase("1")) {
                        ja.add("<input type='hidden' value='N' id='isRocordEligible" + rs.get("farechart_master_id") + "'/>Deleted Record");
                    } else {
                        ja.add("<input type='hidden' value='Y' id='isRocordEligible" + rs.get("farechart_master_id") + "'/>Record in Database");
                    }

                }
                array.add(ja);
                totalAfterFilter = total; //getTotalRecords(request,col,dir);
                result.put("aaData", array);
                result.put("iTotalRecords", total);

                result.put("iTotalDisplayRecords", totalAfterFilter);
//                System.out.println("end");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
        }
        return result;
    }

    public int getFareChartDetailCount(Session session, int fareMasterId) {
    	int count=0;
        Common common = new Common();
        System.out.println("we r in getFareChartDetailCount.....................");
try{
        String sql = "select count(*) as count from fare_chart where farechart_master_id=" + fareMasterId + " and deleted_status=0";
        // Query query=session.createQuery(sql);
        count = common.getDBResultInt(session, sql, "count");
//        System.out.println("count................" + count);
//			 List list=query.list();
//			 count=list.size();
}catch(Exception e){
	e.printStackTrace();
}

        return count;
    }

    public int getFareChartDetailCount(int fareMasterId) {
        int count = 0;
        Session session = null;
        try {
            String sql = "select id from FareChart where fareChartMasterId=" + fareMasterId;
            session = HibernateUtil.getSession("hibernate.cfg.xml");
            Query query = session.createQuery(sql);
            List<FareChart> list = query.list();
            count = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return count;
    }

    public Farechart_Master getFareChart(int id) {
        // BusStops busstops = new BusStops();
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        Farechart_Master fareChart = (Farechart_Master) session.load(
                Farechart_Master.class, new Integer(id));
		//System.out.println("--------->>>" + fareChart.getFarechart_master_id()
        //		+ "\t" + fareChart.getRoute().getRoute_id());
        return fareChart;

    }

    public Farechart_Master getFareChartById(int id) {

        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        Farechart_Master fareChart = (Farechart_Master) session.get(
                Farechart_Master.class, new Integer(id));
        session.close();
        return fareChart;

    }

    public List<Farechart_Master> getFMaster(int routeId, int service, int ver) {
		//check for duplicate entry based on version no-service type
		/*String qry="Select fm.farechart_master_id,fm.farechart_name,fm.effect_start_date,fm.effect_end_date from Farechart_Master fm where route.route_id="+routeId
         +" and service_type_id="+service+"and rate_master_id="+ver
         +" and  deleted_status=0 order by fm.farechart_name asc";
				
         Query query=HibernateUtil.getSession("").createQuery(qry);*/

        String qry = "Select distinct fm.farechart_master_id,fm.farechart_name,fm.effect_start_date,fm.effect_end_date from farechart_master fm "
                + " inner join fare_chart fc on fm.farechart_master_id=fc.farechart_master_id"
                + " where fm.route_id=" + routeId + " and fm.service_type_id=" + service + " and fm.rate_master_id=" + ver
                + " and  fm.deleted_status=0 order by fm.farechart_name asc";

        Query query = HibernateUtil.getSession("").createSQLQuery(qry);

        List list = query.list();
        //System.out.println("list sz=>>"+list.size());

        return list;
    }

    public List<Farechart_Master> checkDuplicateMaster(Farechart_Master fareChartMaster) {
		//check for duplicate entry based on version no-service type
		/*String qry="from Farechart_Master where route.route_id="+fareChartMaster.getRoute().getRoute_id()
         +" and service_type_id="+fareChartMaster.getService_type_id()+"and rate_master_id="+fareChartMaster.getRate_master_id()
         +" and  deleted_status=0 and farechart_name='"+fareChartMaster.getFarechart_name().trim()+"' order by effect_start_date";*/

        String qry = "from Farechart_Master where route.route_id=" + fareChartMaster.getRoute().getRoute_id()
                + " and service_type_id=" + fareChartMaster.getService_type_id() + "and rate_master_id=" + fareChartMaster.getRate_master_id()
                + " and  deleted_status=0 order by effect_start_date";

        Query query = HibernateUtil.getSession("").createQuery(qry);
        List<Farechart_Master> list = query.list();
		//System.out.println("list sz=>>"+list.size());

        return list;
    }

    public List<Farechart_Master> checkDuplicateUpdateMaster(Farechart_Master fareChartMaster) {
		//check for duplicate entry based on version no-service type
		/*String qry="from Farechart_Master where route.route_id="+fareChartMaster.getRoute().getRoute_id()
         +" and service_type_id="+fareChartMaster.getService_type_id()+"and rate_master_id="+fareChartMaster.getRate_master_id()
         +" and  deleted_status=0 and farechart_name='"+fareChartMaster.getFarechart_name().trim()+"' and farechart_master_id!="+fareChartMaster.getFarechart_master_id()+" order by effect_start_date";
         */
        String qry = "from Farechart_Master where route.route_id=" + fareChartMaster.getRoute().getRoute_id()
                + " and service_type_id=" + fareChartMaster.getService_type_id() + "and rate_master_id=" + fareChartMaster.getRate_master_id()
                + " and  deleted_status=0 and farechart_master_id!=" + fareChartMaster.getFarechart_master_id() + " order by effect_start_date";

        Query query = HibernateUtil.getSession("").createQuery(qry);
        List<Farechart_Master> list = query.list();
		//System.out.println("list sz=>>"+list.size());

        return list;
    }

    public int duplicateInsertByQuery(Farechart_Master fareChartMaster) {
        int masterId = -1;
        //Common common=new Common();
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = fareChartMaster.getEffect_start_date();

            String endDate = "";
            if (fareChartMaster.getEffect_end_date() != null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())) {
                endDate = fareChartMaster.getEffect_end_date();
            }

            Map<Integer, String> routeNumberMap;
            routeNumberMap = getRouteNumber();

            String[] routeKey = routeNumberMap.get(fareChartMaster.getRoute().getRoute_id()).split("-");
            String routeNumber = "";
            String routeDir = "";

            if (routeKey.length > 0) {
                routeNumber = routeKey[0];
                routeDir = routeKey[1];
            }

            String qry = "from Farechart_Master where route.route_id=" + fareChartMaster.getRoute().getRoute_id()//route.route_number='"+routeNumber.trim()+"' and route.route_direction='"+routeDir.trim()+"' and farechart_name='"+fareChartMaster.getFarechart_name()+"'"
                    + " and service_type_id=" + fareChartMaster.getService_type_id() + "and rate_master_id=" + fareChartMaster.getRate_master_id()
                    + " and  deleted_status=0 and ((('" + strDate + "' between effect_start_date and  effect_end_date) "
                    + " or '" + strDate + "'=effect_end_date or '" + strDate + "'= effect_start_date )";

            if (fareChartMaster.getEffect_end_date() != null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())) {
                qry += " or ('" + endDate + "' between effect_start_date and ifnull(effect_end_date,effect_start_date)"
                        + " or (('" + strDate + "' <= effect_start_date and '" + endDate + "' between effect_start_date and ifnull(effect_end_date,effect_start_date) )"
                        + " or ('" + strDate + "'<=effect_start_date and '" + endDate + "' >= ifnull(effect_end_date,effect_start_date))))";
            }
            qry += ")";

            Session session = null;
            try {
                session = HibernateUtil.getSession("");
                Query query = session.createQuery(qry);
                List<Farechart_Master> list = query.list();
                masterId = list.get(0).getFarechart_master_id();
            } catch (Exception e) {
                //e.printStackTrace();	
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return masterId;
    }

    public int duplicateUpdateByQuery(Farechart_Master fareChartMaster) {
        int masterId = -1;
        Common common = new Common();
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = common.getDateToDate5(fareChartMaster.getEffect_start_date());

            String endDate = "";
            if (fareChartMaster.getEffect_end_date() != null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())) {
                endDate = common.getDateToDate5(fareChartMaster.getEffect_end_date());
            }

            Map<Integer, String> routeNumberMap;
            routeNumberMap = getRouteNumber();

            String[] routeKey = routeNumberMap.get(fareChartMaster.getRoute().getRoute_id()).split("-");
            String routeNumber = "";
            String routeDir = "";

            if (routeKey.length > 0) {
                routeNumber = routeKey[0];
                routeDir = routeKey[1];
            }

            String qry = "from Farechart_Master where route.route_id=" + fareChartMaster.getRoute().getRoute_id()//route.route_number='"+routeNumber.trim()+"' and route.route_direction='"+routeDir.trim()+"' and farechart_name='"+fareChartMaster.getFarechart_name()+"'"
                    + " and service_type_id=" + fareChartMaster.getService_type_id() + "and rate_master_id=" + fareChartMaster.getRate_master_id() + " and farechart_master_id!=" + fareChartMaster.getFarechart_master_id()
                    + " and  deleted_status=0 and ((('" + strDate + "' between effect_start_date and  effect_end_date) "
                    + " or '" + strDate + "'=effect_end_date or '" + strDate + "'= effect_start_date )";

            if (fareChartMaster.getEffect_end_date() != null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())) {
                qry += " or ('" + endDate + "' between effect_start_date and ifnull(effect_end_date,effect_start_date)"
                        + " or (('" + strDate + "' <= effect_start_date and '" + endDate + "' between effect_start_date and ifnull(effect_end_date,effect_start_date) )"
                        + " or ('" + strDate + "'<=effect_start_date and '" + endDate + "' >= ifnull(effect_end_date,effect_start_date))))";
            }
            qry += ")";

            Session session = null;
            try {
                session = HibernateUtil.getSession("");
                Query query = session.createQuery(qry);
                List<Farechart_Master> list = query.list();
                masterId = list.get(0).getFarechart_master_id();
            } catch (Exception e) {
                //e.printStackTrace();	
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return masterId;
    }

    public int validByRouteEffectiveDates(Farechart_Master fareChartMaster) {
        int masterId = -1;
        Common common = new Common();

        //fare chart date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//System.out.println(common.getDateFromDateTimeFmt2(fareChartMaster.getEffect_start_date()));

            Date strDate = sdf.parse(common.getDateFromDateTimeFmt2(fareChartMaster.getEffect_start_date()));

            Date endDate = new Date();
            if (fareChartMaster.getEffect_end_date() != null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())) {
                endDate = sdf.parse(common.getDateFromDateTimeFmt2(fareChartMaster.getEffect_end_date()));
            }

            Map<Integer, String> routeNumberMap;
            routeNumberMap = getRouteNumber();

            String[] routeKey = routeNumberMap.get(fareChartMaster.getRoute().getRoute_id()).split("-");
            String rStDate = "";
            String rEdDate = "";

            if (routeKey.length > 0) {
                rStDate = routeKey[routeKey.length - 2];
                rEdDate = routeKey[routeKey.length - 1];
            }

            //Route date
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = sdf2.parse(rStDate.substring(1));

            //st>=rs
            if (strDate.compareTo(date1) >= 0) {
                //System.out.println("Date1 is after Date2");
                masterId = 1;

            } else {
                masterId = -1;
                return masterId;
            }

            Date date2 = new Date();

            if (rEdDate.indexOf('/') > 0) {

                //System.out.println(rEdDate.replace(')', ' ').trim());	
                date2 = sdf2.parse(rEdDate.replace(')', ' ').trim());

                //st<=re
                if (date2.compareTo(strDate) >= 0) {
                    //System.out.println("Date1 is after Date2");
                    masterId = 1;
                } else {
                    masterId = -1;
                    return masterId;
                }

            }

            if (fareChartMaster.getEffect_end_date() != null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())) {
                //ed>=rs
                if (endDate.compareTo(date1) >= 0) {
                    //System.out.println("Date1 is after Date2");
                    masterId = 1;

                } else {
                    masterId = -1;
                    return masterId;
                }

                //ed<=re
                if (rEdDate.indexOf('/') > 0) {
                    date2 = sdf2.parse(rEdDate.replace(')', ' ').trim());
                    if (date2.compareTo(endDate) >= 0) {
                        //System.out.println("Date1 is after Date2");
                        masterId = 1;

                    } else {
                        masterId = -1;
                        return masterId;
                    }
                }
            }

            /*if(date2.compareTo(date1)>=0){
             System.out.println("Date1 is after Date2");
             masterId=1;
    			
             }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return masterId;
    }

    public int validByRouteEffectiveDatesUp(Farechart_Master fareChartMaster) {
        int masterId = -1;
        Common common = new Common();

        //fare chart date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//System.out.println(common.getDateToDate2(fareChartMaster.getEffect_start_date()));

            Date strDate = sdf.parse(common.getDateToDate2(fareChartMaster.getEffect_start_date()));

            Date endDate = new Date();
            if (fareChartMaster.getEffect_end_date() != null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())) {
                endDate = sdf.parse(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
            }

            Map<Integer, String> routeNumberMap;
            routeNumberMap = getRouteNumber();

            String[] routeKey = routeNumberMap.get(fareChartMaster.getRoute().getRoute_id()).split("-");
            String rStDate = "";
            String rEdDate = "";

            if (routeKey.length > 0) {
//			rStDate=routeKey[2];
//			rEdDate=routeKey[3];
                rStDate = routeKey[routeKey.length - 2];
                rEdDate = routeKey[routeKey.length - 1];
            }

            //Route date
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = sdf2.parse(rStDate.substring(1));

            //st>=rs
            if (strDate.compareTo(date1) >= 0) {
                //System.out.println("Date1 is after Date2");
                masterId = 1;

            } else {
                masterId = -1;
                return masterId;
            }

            Date date2 = new Date();

            if (rEdDate.indexOf('/') > 0) {

                //System.out.println(rEdDate.replace(')', ' ').trim());	
                date2 = sdf2.parse(rEdDate.replace(')', ' ').trim());

                //st<=re
                if (date2.compareTo(strDate) >= 0) {
                    //System.out.println("Date1 is after Date2");
                    masterId = 1;
                } else {
                    masterId = -1;
                    return masterId;
                }

            }

            if (fareChartMaster.getEffect_end_date() != null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())) {
                //ed>=rs
                if (endDate.compareTo(date1) >= 0) {
                    //System.out.println("Date1 is after Date2");
                    masterId = 1;

                } else {
                    masterId = -1;
                    return masterId;
                }

                //ed<=re
                if (rEdDate.indexOf('/') > 0) {
                    date2 = sdf2.parse(rEdDate.replace(')', ' ').trim());
                    if (date2.compareTo(endDate) >= 0) {
                        //System.out.println("Date1 is after Date2");
                        masterId = 1;

                    } else {
                        masterId = -1;
                        return masterId;
                    }
                }
            }

            /*if(date2.compareTo(date1)>=0){
             System.out.println("Date1 is after Date2");
             masterId=1;
    			
             }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return masterId;
    }

    public String insertFareChartMaster(Farechart_Master fareChartMaster) {
        Session session = null;
        String result = new String();
        Common common = new Common();
        int i = 0;
        try {
            session = HibernateUtil.getSession("hibernate.cfg.xml");
            session.beginTransaction();

            //logic
            List routeName = getRouteDataById(fareChartMaster.getRoute().getRoute_id(), session);

            if (routeName.size() > 0) {
                fareChartMaster.setRoute_name(routeName.get(0).toString());
                fareChartMaster.setRoute_fare_map_id(Integer.parseInt(routeName.get(1).toString()));
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());

            List<Farechart_Master> fareMastersList = checkDuplicateMaster(fareChartMaster);

            //route validation with dates
            int mid = validByRouteEffectiveDates(fareChartMaster);

            if (mid == -1) {
                return "duplicate2";
            }

            //duplicate validation
            int masterId = duplicateInsertByQuery(fareChartMaster);

            if (masterId != -1) {
                return "duplicate";
            }

            session = HibernateUtil.getSession("hibernate.cfg.xml");
            session.beginTransaction();

            i = (Integer) session.save(fareChartMaster);

            /**
             * ********fare chart triangle**************************************
             */
            if (fareChartMaster.getFlexi_fare().startsWith("Y")) {// if flexi_fare=Y, create detail else dont
                List<RateMasterDetail> fareDetails = getFareDetail2(fareChartMaster.getRate_master_id(), fareChartMaster.getService_type_id(), session);

                //get bus stop pts using route_id
                List<RoutePoint_Fare> busStopPts = getBusStop(fareChartMaster.getRoute().getRoute_id(), session);

                //get bus stop pts using route_id for distance calculation
                List<RoutePoint_Fare> busStopPtsDist = getBusStopForDistance(fareChartMaster.getRoute().getRoute_id(), session);

                //get bus stop pts using route_id for toll fee calculation
                List<RoutePoint_Fare> busStopPtsToll = getBusStopForToll(fareChartMaster.getRoute().getRoute_id(), session);

                Set busPtsOver = new HashSet();
                FareChart fareChart = null;

                if (busStopPts.size() > 0) {
                    int countRec = 0;

                    //fare chart triangle data entry in table
                    for (int j = 0; j < busStopPts.size(); j++) {
                        int stageCount = 0, distance = 0, tollFee = 0;

                        for (int j2 = 0; j2 < busStopPts.size(); j2++) {
                            if (!findList(busPtsOver, busStopPts.get(j).getBusStopId(), busStopPts.get(j2).getBusStopId())) {
                                if (busStopPts.get(j).getBusStopId() != busStopPts.get(j2).getBusStopId()) {
	             //Stage calculation start-------------------------------------------
                                    //stageCount=calulateStage(busStopPts,j2,stageCount);	
                                    stageCount = calulateNoOfStages(busStopPts, j, j2);
                                    stageCount = calulateFareStages(busStopPts, j, j2, stageCount);//calulateStage(busStopPts,j,j2,stageCount);

                                    //distance
                                    distance = calculateDistance(busStopPtsDist, busStopPts.get(j).getRouteOrder(), busStopPts.get(j2).getRouteOrder());

                                    //toll fee
                                    tollFee = calculateTollFee(busStopPtsToll, busStopPts.get(j).getRouteOrder(), busStopPts.get(j2).getRouteOrder());

                                    //start:end point combination
                                    busPtsOver.add(busStopPts.get(j).getBusStopId() + ":" + busStopPts.get(j2).getBusStopId());

                                    //insert into fare_chart table for each passenger type
                                    int adultFare = 0;

                                    try {
                                        if (stageCount != 0) {
                                            adultFare = fareDetails.get(stageCount - 1).getAdult();// -1 for array index
                                        } else {
                                            adultFare = fareDetails.get(stageCount).getAdult();
                                        }
                                    } catch (Exception e) {
                                    }

                                    //apply ceil fare if it non zero & greater than adult fare
                                    if (fareChartMaster.getCeiling_fare() != 0 && adultFare > fareChartMaster.getCeiling_fare()) {
                                        adultFare = fareChartMaster.getCeiling_fare();
                                        //System.out.println("applied ceil adultFare="+adultFare+" earlier fare="+fareDetails.get(stageCount).getAdult());
                                    }

                                    //Fare calculation & entry into database for each passenger id
                                    for (int c = 1; c < 4; c++) {
                                        fareChart = new FareChart();
                                        fareChart.setFareChartMasterId(fareChartMaster.getFarechart_master_id());
                                        fareChart.setRouteId(fareChartMaster.getRoute().getRoute_id());
                                        fareChart.setServiceTypeId(fareChartMaster.getService_type_id());
                                        fareChart.setPassengerTypeId(c);
                                        fareChart.setScheduleTypeId(0);
                                        fareChart.setStartPoint(busStopPts.get(j).getBusStopId());
                                        fareChart.setEndPoint(busStopPts.get(j2).getBusStopId());
                                        fareChart.setKms(distance);

                                        //02/08/14 added as per requirement in fare chart for ETIM
                                        int startPointOrder = common.getDBResultInt(session, "SELECT `route_order` FROM `route_point` WHERE `route_id` = '"+fareChart.getRouteId()+"' AND `bus_stop_id` = '"+fareChart.getStartPoint()+"' ", "route_order");
                                        int endPointOrder = common.getDBResultInt(session, "SELECT `route_order` FROM `route_point` WHERE `route_id` = '"+fareChart.getRouteId()+"' AND `bus_stop_id` = '"+fareChart.getEndPoint()+"' ", "route_order");
                                        fareChart.setStartPointOrder(startPointOrder);
                                        fareChart.setEndPointOrder(endPointOrder);
                                       
                                        if (c == 1) {
                                            int childFare = (int) Math.round(adultFare * 0.50);
                                            fareChart.setFareAmount(childFare);//(fareDetails.get(stageCount).getChildren());
                                        } else {
                                            if (c == 2) {
                                                fareChart.setFareAmount(adultFare);//(fareDetails.get(stageCount).getAdult());
                                            } else {
                                                if (c == 3) {
                                                    int srFare = (int) Math.round(adultFare * 0.75);
                                                    fareChart.setFareAmount(srFare);
                                                }
                                            }
                                        }

                                        fareChart.setTollFee(tollFee);
                                        fareChart.setEffStartDate(fareChartMaster.getEffect_start_date());
                                        fareChart.setEffEndDate(fareChartMaster.getEffect_end_date());
                                        fareChart.setCreatedBy(fareChartMaster.getCreated_by());
                                        fareChart.setCreatedDate(date);
                                       // fareChart.setUpdatedBy("0");

                                        session.save(fareChart);//save data
                                    }
                                } else {
                                    //check 1st bus stop
                                    if (j2 == 0) {
                                        //stageCount=calulateStage(busStopPts,j,j2,stageCount);	
                                        stageCount = calulateNoOfStages(busStopPts, j, j2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            /**
             * ********fare chart triangle**************************************
             */

            //update eff start date of old master
            if (fareMastersList.size() > 0) {
                int oldMasterId = fareMastersList.get(fareMastersList.size() - 1).getFarechart_master_id();

                // sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date newDate = sdf.parse(fareChartMaster.getEffect_start_date());
                Calendar cal = Calendar.getInstance();
                cal.setTime(newDate);
                cal.add(Calendar.DATE, -1);
                String updatedEndDt = sdf.format(cal.getTime());

		//eff end date -1 of new effective start date
                String queryString = "update Farechart_Master set effect_end_date='" + updatedEndDt + "' where farechart_master_id=" + oldMasterId;

                if (fareMastersList.get(fareMastersList.size() - 1).getEffect_end_date() == null) {
                    Query query = session.createQuery(queryString);
                    query.executeUpdate();
                }

            }
            session.getTransaction().commit();
            if (i > 0) {
                result = "success";
            } else {
                session.getTransaction().rollback();
                result = "fail";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("exxx=" + e);
        } finally {
            if (session != null && session.isOpen()) {
                HibernateUtil.closeSession();
            }
        }

        return result;

    }

    public int calulateStage(List<RoutePoint_Fare> busStopPts, int startPoint, int point, int stageCount) {
		//point is j2 of insert farechartmaster fcn
//		 if(point==0){//if start pt is stage, add -1
//         	if(busStopPts.get(point).getFareStage().equalsIgnoreCase("Y")){
//         		stageCount=stageCount-1;	
//         	}
//          }//else{
//         
//	            if(point==busStopPts.size()-1){//if end pt is not stage, add 1
//	            	if(!busStopPts.get(point).getFareStage().equalsIgnoreCase("Y")){
//	            		stageCount=stageCount+1;	
//	            	}
//	            }else{
//	            	if(busStopPts.get(point).getFareStage().equalsIgnoreCase("Y")){
//	  				   stageCount=stageCount+1;
//					}
//	            }
//	            

        // if(point==0){//if start pt is stage, add -1
        if (busStopPts.get(startPoint).getFareStage().equalsIgnoreCase("Y")) {
            stageCount = stageCount - 1;
        }
        //  }//else{
        String stp = busStopPts.get(startPoint).getFareStage() + " " + busStopPts.get(startPoint).getBusStopId() + " " + busStopPts.get(startPoint).getRouteOrder();
        String etp = busStopPts.get(point).getFareStage() + " " + busStopPts.get(point).getBusStopId() + " " + busStopPts.get(point).getRouteOrder();

	       //System.out.println(busStopPts.get(point).getRouteOrder()+" stp="+stp+" etp="+etp); 
        //System.out.println();
        if (point == busStopPts.size() - 1) {//if end pt is not stage, add 1
            if (!busStopPts.get(point).getFareStage().equalsIgnoreCase("Y")) {//last stop is not stage
                stageCount = stageCount + 1;
            }
        } else {
            if (busStopPts.get(point).getFareStage().equalsIgnoreCase("Y")) {
                stageCount = stageCount + 1;
            }
        }

        // }
        //System.out.println("stageCount="+stageCount+" busStopPts.size()-1="+busStopPts.size()); 
        return stageCount;
    }

    public int calulateNoOfStages(List<RoutePoint_Fare> busStopPts, int startPoint, int point) {
        int stageCount = 0;

        if (busStopPts.size() > 0) {

            for (int j = startPoint; j <= point; j++) {
                String stp = busStopPts.get(startPoint).getFareStage() + " " + busStopPts.get(startPoint).getBusStopId() + " " + busStopPts.get(startPoint).getRouteOrder();
                String etp = busStopPts.get(point).getFareStage() + " " + busStopPts.get(point).getBusStopId() + " " + busStopPts.get(point).getRouteOrder();

                if (busStopPts.get(j).getFareStage().equalsIgnoreCase("Y")) {
                    stageCount = stageCount + 1;
                }
            }
        }
        return stageCount;
    }

    public int calulateFareStages(List<RoutePoint_Fare> busStopPts, int startPoint, int point, int stageCount) {

        if (busStopPts.size() > 0) {

            for (int j = startPoint; j <= point; j++) {
                String stp = busStopPts.get(startPoint).getFareStage() + " " + busStopPts.get(startPoint).getBusStopId() + " " + busStopPts.get(startPoint).getRouteOrder();
                String etp = busStopPts.get(point).getFareStage() + " " + busStopPts.get(point).getBusStopId() + " " + busStopPts.get(point).getRouteOrder();

                if (j == startPoint && busStopPts.get(j).getFareStage().equalsIgnoreCase("Y")) {
                    stageCount = stageCount - 1;
                }

                if (j == point && busStopPts.get(j).getFareStage().equalsIgnoreCase("N")) {
                    stageCount = stageCount + 1;
                }

//	    	    if(busStopPts.get(j).getFareStage().equalsIgnoreCase("Y")){
//	    	  				   stageCount=stageCount+1;
//	    		}
            }
        }
        return stageCount;
    }

    public int calculateDistance(List<RoutePoint_Fare> busStopPts, int startPoint, int point) {
        int distance = 0;

        if (busStopPts.size() > 0) {

            for (int j = (startPoint - 1); j < (point - 1); j++) {
                try {
                    distance = distance + busStopPts.get(j).getRouteOrder();//dist is stored in routeorder setter
                } catch (Exception e) {
                }
            }
        }
        return distance;
    }

    //12/09/14
    public int calculateTollFee(List<RoutePoint_Fare> busStopPts, int startPoint, int point) {
        int tollFee = 0;

        if (busStopPts.size() > 0) {

            for (int j = startPoint; j < (point - 1); j++) {
                try {
                    if (busStopPts.get(j).getTollZone().equalsIgnoreCase("Y")) {
                        tollFee = tollFee + busStopPts.get(j).getTollFee();
                    }
                } catch (Exception e) {
                }
            }
        }

        return tollFee;
    }

    //copy fare chart
    public String insertFareChartMasterCopy(Farechart_Master fareChartMaster, int oldRateId) {
        Session session = null;
        String result = new String();
        int i = 0;
        try {
            session = HibernateUtil.getSession("hibernate.cfg.xml");
            session.beginTransaction();

            Farechart_Master oldFareMaster = getFareChart(oldRateId);
            fareChartMaster.setCeiling_fare(oldFareMaster.getCeiling_fare() + "");
            fareChartMaster.setNignt_service(oldFareMaster.getNignt_service());
            fareChartMaster.setPeak_time(oldFareMaster.getPeak_time());
            fareChartMaster.setFlexi_fare(oldFareMaster.getFlexi_fare());

            //logic
            List routeName = getRouteDataById(fareChartMaster.getRoute().getRoute_id(), session);

            if (routeName.size() > 0) {
                fareChartMaster.setRoute_name(routeName.get(0).toString());
                fareChartMaster.setRoute_fare_map_id(Integer.parseInt(routeName.get(1).toString()));
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            List<Farechart_Master> fareMastersList = checkDuplicateMaster(fareChartMaster);

            //route validation with dates
            int mid = validByRouteEffectiveDates(fareChartMaster);

            if (mid == -1) {
                return "duplicate2";
            }

            //duplicate validation
            int masterId = duplicateInsertByQuery(fareChartMaster);

            if (masterId != -1) {
                return "duplicate";
            }

            session = HibernateUtil.getSession("hibernate.cfg.xml");
            session.beginTransaction();

            i = (Integer) session.save(fareChartMaster);

            session.getTransaction().commit();

            List<FareChart> farecharts = getFareChartDetail(oldRateId);
		//System.out.println("farecharts="+farecharts.size());

            FareChart fc = null;
            session = HibernateUtil.getSession("hibernate.cfg.xml");
            session.beginTransaction();

            for (int it = 0; it < farecharts.size(); it++) {
                try {
                    fc = new FareChart();
                    fc.setEffStartDate(fareChartMaster.getEffect_start_date());
                    fc.setEffEndDate(fareChartMaster.getEffect_end_date());
                    fc.setEndPoint(farecharts.get(it).getEndPoint());
                    fc.setFareAmount(farecharts.get(it).getFareAmount());
                    fc.setKms(farecharts.get(it).getKms());
                    fc.setPassengerTypeId(farecharts.get(it).getPassengerTypeId());
                    fc.setRouteId(farecharts.get(it).getRouteId());
                    fc.setScheduleTypeId(farecharts.get(it).getScheduleTypeId());
                    fc.setServiceTypeId(farecharts.get(it).getServiceTypeId());
                    fc.setStartPoint(farecharts.get(it).getStartPoint());
                    fc.setTollFee(farecharts.get(it).getTollFee());
                    fc.setUpdatedBy("0");
                    fc.setUpdatedDate(null);
                    fc.setFareChartMasterId(fareChartMaster.getFarechart_master_id());
                    fc.setCreatedBy(fareChartMaster.getCreated_by());
                    fc.setCreatedDate(date);
                    session.save(fc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //update eff start date of old master
            if (fareMastersList.size() > 0) {
                int oldMasterId = fareMastersList.get(fareMastersList.size() - 1).getFarechart_master_id();

                // sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date newDate = sdf.parse(fareChartMaster.getEffect_start_date());
                Calendar cal = Calendar.getInstance();
                cal.setTime(newDate);
                cal.add(Calendar.DATE, -1);
                String updatedEndDt = sdf.format(cal.getTime());
				 //System.out.println("updatedEndDt="+updatedEndDt);

                String queryString = "update Farechart_Master set effect_end_date='" + updatedEndDt + "' where farechart_master_id=" + oldMasterId;
                Query query = session.createQuery(queryString);
                query.executeUpdate();

            }

            session.getTransaction().commit();
            if (i > 0) {
                result = "success";
            } else {
                session.getTransaction().rollback();
                result = "fail";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            result = "fail";
            System.out.println("exxx=" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return result;

    }

    boolean findList(Set list, int v, int v2) {
        boolean flg = false;

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String setElement = iterator.next();
            if (setElement.matches(v2 + ":" + v)) {
                flg = true;
                break;
            }
        }

        return flg;
    }

    public int updateFareChart_Old_250714(Farechart_Master fareChartMaster, int routfarechart_master_ideid) {
        Session session = null;
        int status = -1;
        try {

            //route validation with dates
            int mid = validByRouteEffectiveDatesUp(fareChartMaster);

            if (mid == -1) {
                return 4;
            }

            //duplicate validation
            int masterId = duplicateUpdateByQuery(fareChartMaster);

            if (masterId != -1) {
                return 3;
            }

            session = HibernateUtil.getSession("hibernate.cfg.xml");
            HttpServletRequest request = ServletActionContext.getRequest();
            Common common = new Common();
            session.beginTransaction();
            Farechart_Master fareChart = (Farechart_Master) session.get(Farechart_Master.class, routfarechart_master_ideid);

		//System.out.println(fareChart.getRoute().getRoute_id()+" nm="+fareChart.getRoute().getRoute_name()+","+fareChart.getRoute().getRoute_number());
            fareChart.setFarechart_name(fareChartMaster.getFarechart_name());
            fareChart.setUpdated_date(new java.util.Date());
            fareChart.setUpdated_by(request.getSession().getAttribute("userid").toString());
            fareChart.setEffect_start_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_start_date()));

            if (fareChartMaster.getEffect_end_date() != null) {
                fareChart.setEffect_end_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_end_date()));
            }

            fareChart.setService_type_id(fareChartMaster.getService_type_id());
            fareChart.setRate_master_id(fareChartMaster.getRate_master_id());
            fareChart.setPassenger_type_id(2);//fareChartMaster.getPassenger_type_id());
            fareChart.setPeak_time(fareChartMaster.getPeak_time());
            fareChart.setNignt_service(fareChartMaster.getNignt_service());

            session.update(fareChart);
            session.getTransaction().commit();
            status = 1;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return status;

    }

    public int updateFareChart(Farechart_Master fareChartMaster, int routfarechart_master_ideid, int applyCeilValue, int oldRateMasterId) {
        Session session = null;
        int status = -1;
        int ceilVal = 0;
        try {

            //route validation with dates
            int mid = validByRouteEffectiveDatesUp(fareChartMaster);

            if (mid == -1) {
                return 4;
            }

            //duplicate validation
            int masterId = duplicateUpdateByQuery(fareChartMaster);

            if (masterId != -1) {
                return 3;
            }
            session = HibernateUtil.getSession("hibernate.cfg.xml");
            HttpServletRequest request = ServletActionContext.getRequest();
            Common common = new Common();
            session.beginTransaction();
            Farechart_Master fareChart = (Farechart_Master) session.get(Farechart_Master.class, routfarechart_master_ideid);

            ceilVal = fareChart.getCeiling_fare();
		//System.out.println(fareChart.getRoute().getRoute_id()+" nm="+fareChart.getRoute().getRoute_name()+","+fareChart.getRoute().getRoute_number());

            fareChart.setFarechart_name(fareChartMaster.getFarechart_name());
            fareChart.setUpdated_date(new java.util.Date());
            fareChart.setUpdated_by(request.getSession().getAttribute("userid").toString());
            fareChart.setEffect_start_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_start_date()));

            if (fareChartMaster.getEffect_end_date() != null) {
                fareChart.setEffect_end_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_end_date()));
            }

            fareChart.setService_type_id(fareChartMaster.getService_type_id());
            fareChart.setRate_master_id(fareChartMaster.getRate_master_id());
            fareChart.setPassenger_type_id(2);//fareChartMaster.getPassenger_type_id());
            fareChart.setPeak_time(fareChartMaster.getPeak_time());
            fareChart.setNignt_service(fareChartMaster.getNignt_service());
            fareChart.setFlexi_fare(fareChartMaster.getFlexi_fare());
            fareChart.setCeiling_fare(fareChartMaster.getCeiling_fare() + "");

            session.update(fareChart);

            /**
             * ********fare chart triangle**************************************14/11/14
             */
            if (oldRateMasterId != fareChartMaster.getRate_master_id()) {
                String farechartTable = "UPDATE fare_chart set deleted_status=1 where farechart_master_id=" + fareChartMaster.getFarechart_master_id() + " ";
                int q = getDBExcute(session, farechartTable);
            }
            System.out.println("oldRateMasterId...................." + status);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            if (fareChart.getFlexi_fare().startsWith("Y") && (getFareChartDetailCount(session, fareChart.getFarechart_master_id()) <= 0)) {// if flexi_fare=Y, create detail else dont
                System.out.println("getFareChartDetailCount...................." + status);
                List<RateMasterDetail> fareDetails = getFareDetail2(fareChartMaster.getRate_master_id(), fareChartMaster.getService_type_id(), session);
                System.out.println("RateMasterDetail...................." + status);
                //get bus stop pts using route_id
                List<RoutePoint_Fare> busStopPts = getBusStop(fareChartMaster.getRoute().getRoute_id(), session);
                System.out.println("RoutePoint_Fare...................." + status);
                //get bus stop pts using route_id for distance calculation
                List<RoutePoint_Fare> busStopPtsDist = getBusStopForDistance(fareChartMaster.getRoute().getRoute_id(), session);
                System.out.println("busStopPtsDist...................." + status);
                //get bus stop pts using route_id for toll fee calculation
                List<RoutePoint_Fare> busStopPtsToll = getBusStopForToll(fareChartMaster.getRoute().getRoute_id(), session);
                System.out.println("busStopPtsToll...................." + status);
                Set busPtsOver = new HashSet();
                FareChart fareChartDetail = null;

                if (busStopPts.size() > 0) {
                    int countRec = 0;

                    //fare chart triangle data entry in table
                    for (int j = 0; j < busStopPts.size(); j++) {
                        int stageCount = 0, distance = 0, tollFee = 0;
                        System.out.println("stageCount...................." + status);
                        for (int j2 = 0; j2 < busStopPts.size(); j2++) {
                            if (!findList(busPtsOver, busStopPts.get(j).getBusStopId(), busStopPts.get(j2).getBusStopId())) {
                                if (busStopPts.get(j).getBusStopId() != busStopPts.get(j2).getBusStopId()) {
		             //Stage calculation start-------------------------------------------
                                    //stageCount=calulateStage(busStopPts,j2,stageCount);	
                                    stageCount = calulateNoOfStages(busStopPts, j, j2);
                                    stageCount = calulateFareStages(busStopPts, j, j2, stageCount);//calulateStage(busStopPts,j,j2,stageCount);

                                    //distance
                                    distance = calculateDistance(busStopPtsDist, busStopPts.get(j).getRouteOrder(), busStopPts.get(j2).getRouteOrder());

                                    //toll fee
                                    tollFee = calculateTollFee(busStopPtsToll, busStopPts.get(j).getRouteOrder(), busStopPts.get(j2).getRouteOrder());

                                    //start:end point combination
                                    busPtsOver.add(busStopPts.get(j).getBusStopId() + ":" + busStopPts.get(j2).getBusStopId());

                                    //insert into fare_chart table for each passenger type
                                    int adultFare = 0;

                                    try {
                                        if (stageCount != 0) {
                                            adultFare = fareDetails.get(stageCount - 1).getAdult();// -1 for array index
                                        } else {
                                            adultFare = fareDetails.get(stageCount).getAdult();
                                        }
                                    } catch (Exception e) {
                                    }

                                    //apply ceil fare if it non zero & greater than adult fare
                                    if (fareChartMaster.getCeiling_fare() != 0 && adultFare > fareChartMaster.getCeiling_fare()) {
                                        adultFare = fareChartMaster.getCeiling_fare();
                                        //System.out.println("applied ceil adultFare="+adultFare+" earlier fare="+fareDetails.get(stageCount).getAdult());
                                    }

                                    //Fare calculation & entry into database for each passenger id
                                    for (int c = 1; c < 4; c++) {
                                        fareChartDetail = new FareChart();
                                        fareChartDetail.setFareChartMasterId(fareChartMaster.getFarechart_master_id());
                                        fareChartDetail.setRouteId(fareChartMaster.getRoute().getRoute_id());
                                        fareChartDetail.setServiceTypeId(fareChartMaster.getService_type_id());
                                        fareChartDetail.setPassengerTypeId(c);
                                        fareChartDetail.setScheduleTypeId(0);
                                        fareChartDetail.setStartPoint(busStopPts.get(j).getBusStopId());
                                        fareChartDetail.setEndPoint(busStopPts.get(j2).getBusStopId());
                                        fareChartDetail.setKms(distance);

                                        //02/08/14 added as per requirement in fare chart for ETIM
                                        int startPointOrder = common.getDBResultInt(session, "SELECT `route_order` FROM `route_point` WHERE `route_id` = '"+fareChartDetail.getRouteId()+"' AND `bus_stop_id` = '"+fareChartDetail.getStartPoint()+"' ", "route_order");
                                        int endPointOrder = common.getDBResultInt(session, "SELECT `route_order` FROM `route_point` WHERE `route_id` = '"+fareChartDetail.getRouteId()+"' AND `bus_stop_id` = '"+fareChartDetail.getEndPoint()+"' ", "route_order");
                                                
                                        fareChartDetail.setStartPointOrder(startPointOrder);
                                        fareChartDetail.setEndPointOrder(endPointOrder);

                                        if (c == 1) {
                                            int childFare = (int) Math.round(adultFare * 0.50);
                                            fareChartDetail.setFareAmount(childFare);//(fareDetails.get(stageCount).getChildren());
                                        } else {
                                            if (c == 2) {
                                                fareChartDetail.setFareAmount(adultFare);//(fareDetails.get(stageCount).getAdult());
                                            } else {
                                                if (c == 3) {
                                                    int srFare = (int) Math.round(adultFare * 0.75);
                                                    fareChartDetail.setFareAmount(srFare);
                                                }
                                            }
                                        }

                                        fareChartDetail.setTollFee(tollFee);
                                        fareChartDetail.setEffStartDate(fareChart.getEffect_start_date());
                                        fareChartDetail.setEffEndDate(fareChart.getEffect_end_date());
                                        fareChartDetail.setCreatedBy(fareChart.getCreated_by());
                                        fareChartDetail.setCreatedDate(date);
                                       fareChartDetail.setUpdatedBy("0");


                                        session.save(fareChartDetail);//save data
                                    }
                                } else {
                                    //check 1st bus stop
                                    if (j2 == 0) {
                                        //stageCount=calulateStage(busStopPts,j,j2,stageCount);	
                                        stageCount = calulateNoOfStages(busStopPts, j, j2);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
				//check if ceil fare is changed
                //ceilVal is old ceil fare
                System.out.println("stageCount......updateFareChartTriangle.............." + status);
                status =  updateFareChartTriangle(fareChartMaster, routfarechart_master_ideid, applyCeilValue, oldRateMasterId, "edit");//For No fare Chart
                if (applyCeilValue == 1 && fareChart.getCeiling_fare() != 0 && fareChart.getCeiling_fare() != ceilVal) {
                    applyCeilToFareDetail(session, routfarechart_master_ideid, fareChartMaster.getCeiling_fare());
                }
            }
            /**
             * ********fare chart triangle**************************************
             */

           // session.getTransaction().commit();
            status = 1;
        } catch (Exception e) {
        	System.out.println("it is in catch block....in .....updateFareChart()...............");
        	e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        System.out.println("status...................." + status);
        return status;

    }

    public int applyCeilToFareDetail(Session session, int masterId, int ceilVal) {
        int updated = 0;

        try {

            /* String sql="update fare_chart set fare_amount="+ceilVal+" where farechart_master_id="+masterId
             +" and fare_amount > "+ceilVal+" and passenger_type_id=2";
             Query query = session.createSQLQuery(sql);
             updated=query.executeUpdate();*/
            String sql = "From FareChart where fareChartMasterId=" + masterId + " and passengerTypeId=2 and fareAmount>" + ceilVal;
            Query query = session.createQuery(sql);
            List<FareChart> list = query.list();

            for (int c = 0; c < list.size(); c++) {
                FareChart fc = (FareChart) list.get(c);
		 //System.out.println((fc.getId()-1)+" < "+fc.getId()+" < "+(fc.getId()+1)+" : "+fc.getFareAmount()+" : "+fc.getPassengerTypeId());

                int u1 = updateFareDetailRow(session, (int) Math.round(ceilVal * 0.50), (fc.getId() - 1));
                int u2 = updateFareDetailRow(session, ceilVal, fc.getId());
                int u3 = updateFareDetailRow(session, (int) Math.round(ceilVal * 0.75), (fc.getId() + 1));
                //System.out.println("Updated "+u1+","+u2+","+u3);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }

    int updateFareDetailRow(Session session, int fare, int id) {
        int ok = 0;

        String queryString = "update FareChart set fareAmount=" + fare + " and update_date=now() where id=" + id;
        Query query = session.createQuery(queryString);
        ok = query.executeUpdate();

        return ok;
    }

    public String deleteFareChart(int id) {
        String status = "error";
        Session session = null;
        try {

            session = HibernateUtil.getSession("");
            session.beginTransaction();

            Farechart_Master fareMaster = (Farechart_Master) session.load(Farechart_Master.class, new Integer(id));

            HttpServletRequest request = ServletActionContext.getRequest();
            User user = (User) request.getSession().getAttribute("user");

            fareMaster.setUpdated_date(new java.util.Date());
            fareMaster.setUpdated_by(Integer.toString(user.getUserId()));
            fareMaster.setDeleted_status(1);
            session.getTransaction().commit();
            status = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return status;
    }

    public List getRoutenoById(int id) {
        List list = new ArrayList();
        //System.out.println("inside get id");
        String qry = "select route_number from route where status ='Active' and route_id=" + id;
        Query query = HibernateUtil.getSession("").createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < aliasToValueMapList.size(); i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                list.add(rs.get("route_number").toString());
            }
        }
        HibernateUtil.closeSession();
        return list;
    }

    public List getRouteDataById(int id, Session session) {
        List list = new ArrayList();
		//System.out.println("inside get id");
        //String qry = "select route_name from route where status ='Active' and route_id="+id;
        String qry = "select r.route_name,rfmp.route_fare_map_id from route r"
                + " inner join route_fare_map rfmp on r.route_id=rfmp.route_id"
                + " where r.status ='Active' and r.route_id=" + id;
        //Session session=HibernateUtil.getSession("");
        Query query = session.createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < 1; i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                list.add(rs.get("route_name").toString());
                list.add(rs.get("route_fare_map_id").toString());
            }
        }
        //session.close();
        return list;
    }

    public List getRouteName() {
        List list = new ArrayList();
        //System.out.println("inside get id");
        String qry = "select route_name from route where status='Active' and deleted_status=0";
        Query query = HibernateUtil.getSession("").createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < aliasToValueMapList.size(); i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                list.add(rs.get("route_name").toString());
            }
        }
        HibernateUtil.closeSession();
        return list;
    }

    public List getRouteId() {
        List list = new ArrayList();
        //System.out.println("inside get id");
        String qry = "select route_id,route_number,route_name,route_direction from route where status='Active' and deleted_status=0";
        Query query = HibernateUtil.getSession("").createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
//		if (aliasToValueMapList.size() > 0) {
//
//			for (int i = 0; i < aliasToValueMapList.size(); i++) {
//				Map<String, Object> rs = aliasToValueMapList.get(i);
//				list.add(rs.get("route_id").toString());
//			}
//		}
        HibernateUtil.closeSession();
        return aliasToValueMapList;
    }

    public List getPassengerId() {
        List list = new ArrayList();
        //System.out.println("inside get id");
        String qry = "select passenger_type_id from passenger limit 3";
        Query query = HibernateUtil.getSession("").createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < aliasToValueMapList.size(); i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                list.add(rs.get("passenger_type_id").toString());
            }
        }
        HibernateUtil.closeSession();
        return list;
    }

    public List getPassengerTypeById(int id) {
        List list = new ArrayList();
        //System.out.println("inside get id");
        String qry = "select passenger_type from passenger where passenger_type_id=" + id;
        Query query = HibernateUtil.getSession("").createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < aliasToValueMapList.size(); i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                list.add(rs.get("passenger_type").toString());
            }
        }
        HibernateUtil.closeSession();
        return list;
    }

    public List getPassengerName() {
        List list = new ArrayList();
        //System.out.println("inside get id");
        String qry = "select passenger_type from passenger  limit 3";
        Query query = HibernateUtil.getSession("").createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < aliasToValueMapList.size(); i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                list.add(rs.get("passenger_type").toString());
            }
        }
        HibernateUtil.closeSession();
        return list;
    }

    public List getServiceNameById(int id) {
        List list = new ArrayList();
        // System.out.println("inside get id");
        String qry = "select service_type_name from service_type where deleted_status =0 and service_type_id=" + id;
        Query query = HibernateUtil.getSession("").createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < aliasToValueMapList.size(); i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                list.add(rs.get("service_type_name").toString());
            }
        }
        HibernateUtil.closeSession();
        return list;
    }

    public List getRateMasterVersionByServId(int id) {
        List<RateMaster> list = new ArrayList<RateMaster>();
        RateMaster rateMaster = null;
        Common common = new Common();
        //System.out.println("inside get id");
        String qry = "select rate_master_id,version_number_service_stype,effective_start_date,effective_end_date from rate_master where deleted_status =0 and status='Active' and service_type_id=" + id
                + " and (curdate() between effective_start_date and ifnull(effective_end_date,(DATE_ADD(curdate(),INTERVAL 1 DAY)))"
                + " or curdate() >= effective_start_date) and parent_rate_master=0 order by version_number";
        Query query = HibernateUtil.getSession("").createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < aliasToValueMapList.size(); i++) {
                try {
                    Map<String, Object> rs = aliasToValueMapList.get(i);
                    rateMaster = new RateMaster();
                    rateMaster.setRateMasterId(Integer.parseInt(rs.get("rate_master_id").toString()));
                    rateMaster.setVersionNoServiceType(rs.get("version_number_service_stype").toString());

                    rateMaster.setEffectiveStartDate(common.getDateFromDateTime_(rs.get("effective_start_date").toString()));

                    rateMaster.setEffectiveEndDate(rs.get("effective_end_date") != null ? common.getDateFromDateTime_(rs.get("effective_end_date").toString()) : "");
                    list.add(rateMaster);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        HibernateUtil.closeSession();
        return list;
    }

    public String getRateMasterVersionById(int id) {

        String str = new String();
        String qry = "select version_number_service_stype from rate_master where deleted_status =0 and rate_master_id=" + id;
        Query query = HibernateUtil.getSession("").createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < aliasToValueMapList.size(); i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                str = rs.get("version_number_service_stype").toString();
            }
        }
        HibernateUtil.closeSession();
        return str;
    }

    //get system key value for bus stop point type to be shown in fare chart
    public String getPointTypeByKey(String key, Session session) {
        String ptypes = "1";

        try {

            String qry = "select sys_value from default_system_veriable where sys_key='" + key + "'";

            Query query = session.createSQLQuery(qry);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> aliasToValueMapList = query.list();

            if (aliasToValueMapList.size() > 0) {

                Map<String, Object> rs = aliasToValueMapList.get(0);
                ptypes = rs.get("sys_value").toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ptypes;
    }

    public List getBusStop(int id, Session session) {
// bus stops w/o connector		
//		String qry="from RoutePoint_Fare where deletedStatus =0 and routeId="+id+" order by routeOrder";
//		Query query = session.createQuery(qry);
//	    List list=query.list();	
//
//		return list;
        List<RoutePoint_Fare> list = new ArrayList();
        try {
            String ptypes = getPointTypeByKey("POINT_TYPE_ID", session);

            list = new ArrayList();
            //System.out.println("inside get id");
            String qry = "select * from route_point r"
                    + " inner join bus_stop b on r.bus_stop_id=b.bus_stop_id"
                    + " where r.deleted_Status =0 and r.route_Id=" + id
                    + " and b.point_type_id in (" + ptypes + ") and b.toll_zone!='Y' order by route_Order";

            Query query = session.createSQLQuery(qry);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> aliasToValueMapList = query.list();
            RoutePoint_Fare rpf;
            if (aliasToValueMapList.size() > 0) {

                for (int i = 0; i < aliasToValueMapList.size(); i++) {
                    Map<String, Object> rs = aliasToValueMapList.get(i);
                    rpf = new RoutePoint_Fare();
                    rpf.setId(Integer.parseInt(rs.get("route_points_id").toString()));
                    rpf.setRouteId(Integer.parseInt(rs.get("route_id").toString()));
                    rpf.setBusStopId(Integer.parseInt(rs.get("bus_stop_id").toString()));
                    rpf.setRouteOrder(Integer.parseInt(rs.get("route_order").toString()));
                    rpf.setPointStatus(rs.get("point_status").toString());
                    rpf.setFareStage(rs.get("fare_stage").toString());
                    rpf.setDeletedStatus(Integer.parseInt(rs.get("deleted_status").toString()));

                    list.add(rpf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public List getBusStopForDistance(int id, Session session) {

        List<RoutePoint_Fare> list = new ArrayList();
        try {
            //System.out.println("inside get id");
            String qry = "select b.point_type_id as point_type_id,m.start_bus_stop_id,if(schedule_distance=0,distance,schedule_distance*1000) as distance"
                    + " from route_point p"
                    + " inner join route_map m on p.route_order=m.bus_stop_order and p.route_id=m.route_id"
                    + " inner join bus_stop b on p.bus_stop_id=b.bus_stop_id"
                    + " where p.deleted_Status =0 and p.route_id=" + id + " order by route_order";

            Query query = session.createSQLQuery(qry);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> aliasToValueMapList = query.list();
            RoutePoint_Fare rpf;
            if (aliasToValueMapList.size() > 0) {

                for (int i = 0; i < aliasToValueMapList.size(); i++) {
                    Map<String, Object> rs = aliasToValueMapList.get(i);
                    rpf = new RoutePoint_Fare();

                    rpf.setRouteId(Integer.parseInt(rs.get("point_type_id").toString()));
                    rpf.setBusStopId(Integer.parseInt(rs.get("start_bus_stop_id").toString()));

                    Double d = new Double((Double) rs.get("distance"));
                    int dist = d.intValue();
                    rpf.setRouteOrder((int) dist);

                    list.add(rpf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public List getBusStopForToll(int id, Session session) {

        List<RoutePoint_Fare> list = new ArrayList();
        try {
            String ptypes = getPointTypeByKey("POINT_TYPE_ID", session);

            String qry = "select b.point_type_id as point_type_id,m.start_bus_stop_id,b.toll_zone,b.toll_fee"
                    + " from route_point p"
                    + " inner join route_map m on p.route_order=m.bus_stop_order and p.route_id=m.route_id"
                    + " inner join bus_stop b on p.bus_stop_id=b.bus_stop_id"
                    + " where p.deleted_Status =0 and p.route_id=" + id + " order by route_order";
				//+" where p.deleted_Status =0 and b.point_type_id in ("+ptypes+") and p.route_id="+id+" order by route_order"; // incorrect toll applied from wrong stop

            Query query = session.createSQLQuery(qry);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> aliasToValueMapList = query.list();
            RoutePoint_Fare rpf;
            if (aliasToValueMapList.size() > 0) {

                for (int i = 0; i < aliasToValueMapList.size(); i++) {
                    Map<String, Object> rs = aliasToValueMapList.get(i);
                    rpf = new RoutePoint_Fare();

                    rpf.setRouteId(Integer.parseInt(rs.get("point_type_id").toString()));
                    rpf.setBusStopId(Integer.parseInt(rs.get("start_bus_stop_id").toString()));
                    rpf.setTollZone(rs.get("toll_zone").toString());

                    Double tollFee = new Double((Double) rs.get("toll_fee"));
                    rpf.setTollFee(Math.round(tollFee.floatValue()));

                    list.add(rpf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public List getBusStopsList(int rid) {

        Session session = HibernateUtil.getSession("");

        String ptypes = getPointTypeByKey("POINT_TYPE_ID", session);

        List list = new ArrayList();
        //System.out.println("inside get id");
        String qry = "select r.bus_stop_id,b.bus_stop_name,b.bus_stop_code,r.fare_stage from route_point r"
                + " inner join bus_stop b on r.bus_stop_id=b.bus_stop_id"
                + " where r.route_id=" + rid + " and r.deleted_status=0 and b.point_type_id in (" + ptypes + ") "
                + "  and r.fare_stage='Y' order by r.route_order";

        Query query = session.createSQLQuery(qry);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
        BusStops bs;
        if (aliasToValueMapList.size() > 0) {

            for (int i = 0; i < aliasToValueMapList.size(); i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                bs = new BusStops();
                bs.setId(Integer.parseInt(rs.get("bus_stop_id").toString()));
                bs.setBusStopName(rs.get("bus_stop_name").toString());
                bs.setBus_stop_code(rs.get("bus_stop_code") != null ? rs.get("bus_stop_code").toString() : " ");
                bs.setFareStage(rs.get("fare_stage") != null ? rs.get("fare_stage").toString() : " ");
                list.add(bs);
            }
        }
        HibernateUtil.closeSession();
        return list;
    }

    public List getFareDetail(int rateId, int serviceId, Session session) {
        String qry = "from RateMasterDetail where deletedStatus =0 and rateMasterId=" + rateId
                + " and serviceTypeId=" + serviceId + " order by stageNo";
        //Session session=HibernateUtil.getSession("");
        Query query = session.createQuery(qry);
        List list = query.list();
        //session.close();
        return list;
    }

    //15/10/14 to get service type id from rate_master table
    public List getFareDetail2(int rateId, int serviceId, Session session) {
        List list = null;
        try {
            String sql = "select rmd.rate_master_details_id rateMasterDetailId,rmd.rate_master_id rateMasterId,rm.service_type_id serviceTypeId"
                    + ",stage_no stageNo,adult adult,children children,senior_citizen seniorCitizen,rm.deleted_status deletedStatus"
                    + ",rm.created_by createdBy,rm.created_date createdDate,rm.updated_by updatedBy,rm.updated_date updatedDate"
                    + " from rate_master_details rmd"
                    + " inner join rate_master rm on rm.rate_Master_Id=rmd.rate_Master_Id"
                    + " where rm.deleted_Status =0 and rmd.rate_Master_Id=" + rateId
                    + " and rm.service_Type_Id=" + serviceId + " order by stage_No";

            Query query = session.createSQLQuery(sql)
                    .addScalar("rateMasterDetailId", Hibernate.INTEGER)
                    .addScalar("rateMasterId", Hibernate.INTEGER)
                    .addScalar("serviceTypeId", Hibernate.INTEGER)
                    .addScalar("stageNo", Hibernate.INTEGER)
                    .addScalar("adult", Hibernate.INTEGER)
                    .addScalar("children", Hibernate.INTEGER)
                    .addScalar("seniorCitizen", Hibernate.INTEGER)
                    .addScalar("deletedStatus", Hibernate.INTEGER)
                    .addScalar("createdBy", Hibernate.INTEGER)
                    .addScalar("createdDate", Hibernate.STRING)
                    .addScalar("updatedBy", Hibernate.INTEGER)
                    .addScalar("updatedDate", Hibernate.STRING);
            query.setResultTransformer(Transformers.aliasToBean(RateMasterDetail.class));
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List getFareChartDetail(int id) {

        String qry = "from FareChart where fareChartMasterId=" + id + " and deleted_status=0";
        Session session = HibernateUtil.getSession("");
        Query query = session.createQuery(qry);
        List list = query.list();
        FareChart fc;
        List<FareChart> l = new ArrayList<FareChart>();
        for (int i = 0; i < list.size(); i++) {
            try {

//System.out.println("fc Data="+list.get(i).getId()+":"+list.get(i).getPassengerTypeId()+":"+list.get(i).getFareAmount());
//				
//			fc=new FareChart();
//			fc.setId(list.get(i).getId());
//			fc.setFareChartMasterId(list.get(i).getFareChartMasterId());
//			fc.setRouteId(list.get(i).getRouteId());
//			fc.setServiceTypeId(list.get(i).getServiceTypeId());
//			fc.setPassengerTypeId(list.get(i).getPassengerTypeId());
//			fc.setStartPoint(list.get(i).getStartPoint());
//			fc.setEndPoint(list.get(i).getEndPoint());
//			fc.setFareAmount(list.get(i).getFareAmount());
//			fc.setTollFee(list.get(i).getTollFee());
//			fc.setUpdatedDate("");
//			
//			l.add(fc);
//System.out.println("id>>>="+fc.getId());
            } catch (Exception e) {
                System.out.println("e=" + e);
            }
        }

        session.close();
        return list;
    }

    public Map getFareChartDetailMap(int id) {
System.out.println("farechart master id........."+id);
        String qry = "from FareChart where fareChartMasterId=" + id + " ORDER BY id DESC";
        Session session = HibernateUtil.getSession("");
        Query query = session.createQuery(qry);
        List<FareChart> list = query.list();
        FareChart fc;
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                try {
                    if (list.get(i).getEndPoint() == list.get(j).getEndPoint()) {
                        map.put(list.get(i).getStartPoint() + "-" + list.get(i).getEndPoint() + "-" + list.get(i).getPassengerTypeId(), Integer.toString(list.get(i).getFareAmount()) + ":" + list.get(i).getTollFee() + ":" + list.get(i).getId());
//System.out.println(list.get(i).getStartPoint()+"-"+list.get(i).getEndPoint()+"-"+list.get(i).getPassengerTypeId()+"=>"+Integer.toString(list.get(i).getFareAmount()));
                        break;
                    }
//System.out.println("fc Data="+list.get(i).getId()+":"+list.get(i).getPassengerTypeId()+":"+list.get(i).getFareAmount());
//				
//			fc=new FareChart();
//			fc.setId(list.get(i).getId());
//			fc.setFareChartMasterId(list.get(i).getFareChartMasterId());
//			fc.setRouteId(list.get(i).getRouteId());
//			fc.setServiceTypeId(list.get(i).getServiceTypeId());
//			fc.setPassengerTypeId(list.get(i).getPassengerTypeId());
//			fc.setStartPoint(list.get(i).getStartPoint());
//			fc.setEndPoint(list.get(i).getEndPoint());
//			fc.setFareAmount(list.get(i).getFareAmount());
//			fc.setTollFee(list.get(i).getTollFee());
//			fc.setUpdatedDate("");
//			
//			l.add(fc);
//System.out.println("id>>>="+fc.getId());
                } catch (Exception e) {
                    System.out.println("e=" + e);
                }
            }
        }
        HibernateUtil.closeSession();
        return map;
    }

    public int updateFareTriangle(int id, int fare, int toll) {
        int ok = 0;
        Session session = HibernateUtil.getSession("");
        session.beginTransaction();
        Common common = new Common();
        try{
        	String userid=ServletActionContext.getRequest().getSession().getAttribute("userid").toString();
        String sql="SELECT route_id, start_point_order, end_point_order, passenger_type_id FROM fare_chart WHERE fare_chart_id = '"+id+"'";
        Query query1 = session.createSQLQuery(sql).addScalar("route_id").addScalar("start_point_order")
        		.addScalar("end_point_order").addScalar("passenger_type_id");
        query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query1.list();
        if (aliasToValueMapList.size() > 0) {
        	for (int i = 0; i < aliasToValueMapList.size(); i++) {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                int endpoint=Integer.parseInt(rs.get("end_point_order").toString());
                while(endpoint!=0){
                	endpoint=endpoint-1;
                	String sql11="SELECT COUNT(`route_points_id`) as count FROM `route_point` WHERE `route_id` = '"+rs.get("route_id").toString()+"' " +
                			"AND `route_order` = '"+endpoint+"' AND `fare_stage` != 'Y'";
                	int count = common.getDBResultInt(session, sql11, "count");
        			if(count!=0){
        				 String queryString = "update FareChart set fareAmount=" + fare + " ,  updated_by='"+userid+"' , update_date=now() where route_id='"+rs.get("route_id").toString()+"' "+
        			"and start_point_order='"+rs.get("start_point_order").toString()+"' and end_point_order='"+endpoint+"' " +
        					"and passenger_type_id='"+rs.get("passenger_type_id").toString()+"' ";
        			        Query query = session.createQuery(queryString);
        			        ok = query.executeUpdate();
        			}else{
        				endpoint=0;
        			}
                }
                int strtpoint=Integer.parseInt(rs.get("start_point_order").toString());
                while(strtpoint!=0){
                	strtpoint=strtpoint+1;
                	String sql11="SELECT COUNT(`route_points_id`) as count FROM `route_point` WHERE `route_id` = "+rs.get("route_id").toString()+" " +
                			"AND `route_order` = '"+strtpoint+"' AND `fare_stage` != 'Y'";
                	int count = common.getDBResultInt(session, sql11, "count");
        			if(count!=0){
        				 String queryString = "update FareChart set fareAmount=" + fare + " ,  updated_by='"+userid+"' , update_date=now() where route_id="+rs.get("route_id").toString()+" "+
        			"and start_point_order="+strtpoint+" and end_point_order="+rs.get("end_point_order").toString()+" and " +
        					"passenger_type_id="+rs.get("passenger_type_id").toString()+" ";
        			        Query query = session.createQuery(queryString);
        			        ok = query.executeUpdate();
        			        int endpoint1=Integer.parseInt(rs.get("end_point_order").toString());
        			        while(endpoint1!=0 && strtpoint<endpoint1){
        	                	endpoint1=endpoint1-1;
        	                	String sql111="SELECT COUNT(`route_points_id`) as count FROM `route_point` WHERE `route_id` = '"+rs.get("route_id").toString()+"' " +
        	                			"AND `route_order` = '"+endpoint1+"' AND `fare_stage` != 'Y'";
        	                	int count1 = common.getDBResultInt(session, sql111, "count");
        	        			if(count1!=0){
        	        				 String queryString1 = "update FareChart set fareAmount=" + fare + " ,  updated_by='"+userid+"' , update_date=now() where route_id="+rs.get("route_id").toString()+" "+
        	        			"and start_point_order="+strtpoint+" and end_point_order="+endpoint1+" and " +
        	        					"passenger_type_id="+rs.get("passenger_type_id").toString()+" ";
        	        			        Query query2 = session.createQuery(queryString1);
        	        			        ok = query2.executeUpdate();
        	        			}else{
        	        				endpoint1=0;
        	        			}
        	                }			        
        			}else{
        				strtpoint=0;
        			}
                }
                
            }
        }
        
        
        String queryString = "update FareChart set fareAmount=" + fare + "  where id=" + id;
        Query query = session.createQuery(queryString);
        ok = query.executeUpdate();
        session.getTransaction().commit();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        session.close();
        }
        return ok;

    }

    public int updateFareTriangleToll(int id, int fare, int toll) {
        int ok = 0;
        Session session = HibernateUtil.getSession("");
        session.beginTransaction();
        Common common = new Common();
        try{
        	 String userid=ServletActionContext.getRequest().getSession().getAttribute("userid").toString();
            String sql="SELECT route_id, start_point_order, end_point_order, passenger_type_id FROM fare_chart WHERE fare_chart_id = '"+id+"'";
            Query query1 = session.createSQLQuery(sql).addScalar("route_id").addScalar("start_point_order")
            		.addScalar("end_point_order").addScalar("passenger_type_id");
            query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> aliasToValueMapList = query1.list();
            if (aliasToValueMapList.size() > 0) {
            	for (int i = 0; i < aliasToValueMapList.size(); i++) {
                    Map<String, Object> rs = aliasToValueMapList.get(i);
                    int endpoint=Integer.parseInt(rs.get("end_point_order").toString());
                    while(endpoint!=0){
                    	endpoint=endpoint-1;
                    	String sql11="SELECT COUNT(`route_points_id`) as count FROM `route_point` WHERE `route_id` = '"+rs.get("route_id").toString()+"' " +
                    			"AND `route_order` = '"+endpoint+"' AND `fare_stage` != 'Y'";
                    	int count = common.getDBResultInt(session, sql11, "count");
            			if(count!=0){
            				 String queryString = "update FareChart set tollFee=" + toll + ",  updated_by='"+userid+"' , update_date=now() where route_id='"+rs.get("route_id").toString()+"' "+
            			"and start_point_order='"+rs.get("start_point_order").toString()+"' and end_point_order='"+endpoint+"' " +
            					"and passenger_type_id='"+rs.get("passenger_type_id").toString()+"' ";
            			        Query query = session.createQuery(queryString);
            			        ok = query.executeUpdate();
            			}else{
            				endpoint=0;
            			}
                    }
                    int strtpoint=Integer.parseInt(rs.get("start_point_order").toString());
                    while(strtpoint!=0){
                    	strtpoint=strtpoint+1;
                    	String sql11="SELECT COUNT(`route_points_id`) as count FROM `route_point` WHERE `route_id` = "+rs.get("route_id").toString()+" " +
                    			"AND `route_order` = '"+strtpoint+"' AND `fare_stage` != 'Y'";
                    	int count = common.getDBResultInt(session, sql11, "count");
            			if(count!=0){
            				 String queryString = "update FareChart set tollFee=" + toll + ",  updated_by='"+userid+"' , update_date=now() where route_id="+rs.get("route_id").toString()+" "+
            			"and start_point_order="+strtpoint+" and end_point_order="+rs.get("end_point_order").toString()+" and " +
            					"passenger_type_id="+rs.get("passenger_type_id").toString()+" ";
            			        Query query = session.createQuery(queryString);
            			        ok = query.executeUpdate();
            			        int endpoint1=Integer.parseInt(rs.get("end_point_order").toString());
            			        while(endpoint1!=0 && strtpoint<endpoint1){
            	                	endpoint1=endpoint1-1;
            	                	String sql111="SELECT COUNT(`route_points_id`) as count FROM `route_point` WHERE `route_id` = '"+rs.get("route_id").toString()+"' " +
            	                			"AND `route_order` = '"+endpoint1+"' AND `fare_stage` != 'Y'";
            	                	int count1 = common.getDBResultInt(session, sql111, "count");
            	        			if(count1!=0){
            	        				 String queryString1 = "update FareChart set tollFee=" + toll + " ,  updated_by='"+userid+"' , update_date=now() where route_id="+rs.get("route_id").toString()+" "+
            	        			"and start_point_order="+strtpoint+" and end_point_order="+endpoint1+" and " +
            	        					"passenger_type_id="+rs.get("passenger_type_id").toString()+" ";
            	        			        Query query2 = session.createQuery(queryString1);
            	        			        ok = query2.executeUpdate();
            	        			}else{
            	        				endpoint1=0;
            	        			}
            	                }			        
            			}else{
            				strtpoint=0;
            			}
                    }
                    
                }
            }
            
        String queryString = "update FareChart set tollFee=" + toll + " where id=" + id;
        Query query = session.createQuery(queryString);
        ok = query.executeUpdate();
        session.getTransaction().commit();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        session.close();
        }
        return ok;

    }

    public int fareChartIdByTriangleId(int id) {
        int fareMasterId = 0;
        Session session = null;
        try {
            session = HibernateUtil.getSession("");
            session.beginTransaction();
            String queryString = "from FareChart where id=" + id;
            Query query = session.createQuery(queryString);
            List<FareChart> list = query.list();

            if (list.size() > 0) {
                fareMasterId = list.get(0).getFareChartMasterId();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return fareMasterId;

    }

    public Map<Integer, String> getRouteNumber() {

        Common common = new Common();

        Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        /*Query query = session.createQuery("from Route where status='Active' and deleted_status=0" +
         " and (curdate() between effective_from and ifnull((CASE WHEN effective_till='0000-00-00 00:00:00' THEN null else effective_till end),(curdate()+1))"
         +" or ( curdate() >= effective_from and ifnull((CASE WHEN effective_till='0000-00-00 00:00:00' THEN null else effective_till end),(DATE_ADD(curdate(),INTERVAL 1 DAY)))>=curdate()))"+
         " order by route_number");
         List<Route> list = query.list();*/
        
        /*
        Query query = session.createSQLQuery("Select route_id,route_number,route_direction,effective_from,effective_till from route where status='Active' and deleted_status=0"
                + " and (curdate() between effective_from and ifnull((CASE WHEN effective_till='0000-00-00 00:00:00' THEN null else effective_till end),(curdate()+1))"
                + " or ( curdate() >= effective_from and ifnull((CASE WHEN effective_till='0000-00-00 00:00:00' THEN null else effective_till end),(DATE_ADD(curdate(),INTERVAL 1 DAY)))>=curdate()))"
                + " OR (effective_from >= curdate() AND ifnull((CASE WHEN effective_till='0000-00-00 00:00:00' THEN NULL ELSE effective_till END),(DATE_ADD(curdate(),INTERVAL 1 DAY)))>=curdate())"
                + " order by route_number");*/
        

        Query query = session.createSQLQuery("Select route_id,route_number,route_direction,effective_from,effective_till from route where if(effective_till IS NULL or effective_till = '0000-00-00 00:00:00', CURDATE(), effective_till) >= CURDATE() and status='ACTIVE' "
               +"   and deleted_status='0' "
//                +"   and route_number like '%YTT%' " 
                  +"  order by route_number ");

        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();

        //resultMap.put(0, "Select");
        for (int i = 0; i < aliasToValueMapList.size(); i++) {//for (Route route : list) {
			/*String sDate=route.getEffective_from()!=null ? common.getDateToDate4(route.getEffective_from().toString()):"";
             String eDate=route.getEffective_till() != null ? common.getDateToDate4(route.getEffective_till().toString()) : "";*/
            try {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                String sDate = rs.get("effective_from") != null ? common.getDateToDate4(rs.get("effective_from").toString()) : "";
                String eDate = rs.get("effective_till") != null ? common.getDateToDate4(rs.get("effective_till").toString()) : "";

                String dates = sDate + " - " + eDate;
                //resultMap.put(route.getRoute_id(), route.getRoute_number()+"-"+route.getRoute_direction()+"-("+dates+")");
                resultMap.put(Integer.parseInt(rs.get("route_id").toString()), rs.get("route_number").toString() + "-" + rs.get("route_direction").toString() + "-(" + dates + ")");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultMap;

    }

    //15/10/14
    public Map<Integer, String> getRouteNumberAll() {

        Common common = new Common();

        Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        /*Query query = session.createQuery("from Route where status='Active' and deleted_status=0" +
         " order by route_number");
         List<Route> list = query.list();
         //resultMap.put(0, "Select");
         for (Route route : list) {
         String sDate=route.getEffective_from()!=null ? common.getDateToDate4(route.getEffective_from().toString()):"";
         String eDate=route.getEffective_till() != null ? common.getDateToDate4(route.getEffective_till().toString()) : "";
			
         String dates=sDate+" - "+eDate;
         resultMap.put(route.getRoute_id(), route.getRoute_number()+"-"+route.getRoute_direction()+"-("+dates+")");
         }
         return resultMap;*/

        Query query = session.createSQLQuery("Select route_id,route_number,route_direction,effective_from,effective_till from route where status='Active' and deleted_status=0"
                + " order by route_number");

        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();

        for (int i = 0; i < aliasToValueMapList.size(); i++) {
            try {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                String sDate = rs.get("effective_from") != null ? common.getDateToDate4(rs.get("effective_from").toString()) : "";
                String eDate = rs.get("effective_till") != null ? common.getDateToDate4(rs.get("effective_till").toString()) : "";

                String dates = sDate + " - " + eDate;
                //resultMap.put(route.getRoute_id(), route.getRoute_number()+"-"+route.getRoute_direction()+"-("+dates+")");
                resultMap.put(Integer.parseInt(rs.get("route_id").toString()), rs.get("route_number").toString() + "-" + rs.get("route_direction").toString() + "-(" + dates + ")");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultMap;

    }

    //get route details for validation
    public Map<Integer, String> getRouteDetail() {

        Map<Integer, String> resultMap = new HashMap<Integer, String>();
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        Query query = session.createQuery("from Route where status='Active' and deleted_status=0 order by route_id");
        List<Route> list = query.list();
        //resultMap.put(0, "Select");
        for (Route route : list) {
            resultMap.put(route.getRoute_id(), route.getRoute_number() + "-" + route.getRoute_direction());
        }
        return resultMap;

    }

    public Map<Integer, String> getServiceType() {

        Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();//modified on 01/09/14 earlier was HashMap
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        Query query = session.createQuery("from ScheduleService where status='Active' and deletedStatus=0 order by serviceTypeName");
        List<ScheduleService> list = query.list();

        //resultMap.put(0, "Select");
        for (ScheduleService type : list) {
            resultMap.put(type.getService_type_id(), type.getServiceTypeName());
        }
        return resultMap;

    }

    public Map<Integer, String> getRateMasterVersionMap() {

        Map<Integer, String> resultMap = new HashMap<Integer, String>();
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        Query query = session.createQuery("Select rateMasterId,versionNoServiceType from RateMaster where deletedStatus=0 and status='ACTIVE' order by rateMasterId,versionNoServiceType");
        List list = query.list();
        Object[] obj;
        int rateId;
        String masterName;
        for (Iterator i = list.iterator(); i.hasNext();) {
            obj = (Object[]) i.next();
            rateId = (Integer) obj[0];
            masterName = (String) obj[1];
            resultMap.put(rateId, masterName);
        }
        return resultMap;

    }

    public String updateDuplicateFareChart() {
        Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        Query query = session.createSQLQuery("select group_concat(farechart_master_id) service_type_id from  farechart_master where deleted_status	=0 group by route_id,rate_master_id,effect_start_date,effect_end_date having count(*)>1 order by created_date")
                .addScalar("service_type_id", Hibernate.STRING);

        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();

        for (int i = 0; i < aliasToValueMapList.size(); i++) {
            try {
                Map<String, Object> rs = aliasToValueMapList.get(i);
                String fareids = rs.get("service_type_id").toString();
                String[] fareids1 = fareids.split(",");
                String upfareid = "";
                for (int j = 0; j < fareids1.length - 1; j++) {
                    upfareid = upfareid + fareids1[j] + ",";
                }
                upfareid = upfareid.substring(0, upfareid.length() - 1);
                String sql = "update farechart_master set deleted_status=1 where farechart_master_id in(" + upfareid + ")";
                query = session.createSQLQuery(sql);
                query.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*String sql="select count(*),created_date,farechart_name,group_concat(farechart_master_id),group_concat(service_type_id) from  farechart_masterwhere deleted_status	=0 group by route_id,farechart_name,rate_master_id,effect_start_date,effect_end_date having count(*)>1 order by created_date";
         */
        return "success";
    }

    public JSONObject getHourlyChartList(int total, HttpServletRequest request, String col, String dir, int routeId, int serviceTypeId) {
        JSONObject result = new JSONObject();
        Session session = null;
        try {
            int totalAfterFilter = total;

            session = HibernateUtil.getSession("hibernate.cfg.xml");

            String sql = "select hc.hourly_chart_id,hc.farechart_master_id,hc.fare_chart_name,hc.route_number,st.service_type_name,fcm.effect_start_date,fcm.effect_end_date"
                    + ",0_1h,1_2h,2_3h,3_4h,4_5h,5_6h,6_7h,7_8h,8_9h,9_10h,10_11h,11_12h,12_13h,13_14h,14_15h,15_16h,16_17h,17_18h,18_19h,19_20h,20_21h,21_22h,22_23h,23_24h"
                    + " from hourly_chart hc left join farechart_master fcm on fcm.farechart_master_id=hc.farechart_master_id"
                    + " left join service_type st on st.service_type_id=fcm.service_type_id"
                    + " Where hc.deleted_status=0 and fcm.deleted_status=0 and (fcm.effect_end_date is null or fcm.effect_end_date>=curdate())"
                    + " and hc.route_id=" + routeId + " and hc.service_type_id=" + serviceTypeId;

            String search_term = request.getParameter("sSearch");

            if (search_term != null && !search_term.equals("")) {
                search_term = search_term.trim();
                sql += " AND(hc.fare_chart_name like '" + search_term + "%')";

            }

            if (!col.equals("")) {
                if (dir.equals("asc")) {
                    sql += " order by " + col + " asc";
                } else {
                    sql += " order by " + col + " desc";
                }
            }
            sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
System.out.println("####"+sql);
            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> list = query.list();

            JSONArray array = new JSONArray();

            Common common = new Common();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> rs = list.get(i);

                JSONArray ja = new JSONArray();

                ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
                        + rs.get("hourly_chart_id")
                        + "' value='" + rs.get("hourly_chart_id") + "'/>");
                ja.add(rs.get("farechart_master_id"));//fare chart master id
                ja.add(rs.get("fare_chart_name") != null ? rs.get("fare_chart_name").toString().replaceAll(" ", "&nbsp;") : "");

                String startDate = rs.get("effect_start_date") != null ? common.getDateFromDateTime(rs.get("effect_start_date").toString()) : "";
                ja.add(startDate.replace(".0", ""));

                ja.add(rs.get("effect_end_date") != null ? common.getDateFromDateTime(rs.get("effect_end_date").toString()) : "");

                ja.add(rs.get("0_1h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("1_2h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("2_3h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("3_4h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("4_5h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("5_6h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("6_7h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("7_8h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("8_9h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("9_10h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("10_11h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("11_12h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("12_13h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("13_14h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("14_15h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("15_16h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("16_17h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("17_18h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("18_19h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("19_20h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("20_21h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("21_22h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("22_23h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));
                ja.add(rs.get("23_24h").toString().equals("0") ? ja.add("") : ja.add("&#x2714"));

                array.add(ja);
            }

            totalAfterFilter = total; //getTotalRecords(request,col,dir);
            result.put("aaData", array);
            result.put("iTotalRecords", total);

            result.put("iTotalDisplayRecords", totalAfterFilter);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public int getHourlyChartTotalRecords(HttpServletRequest request, String col, String dir, int routeId, int serviceTypeId) {
        Session session = null;
        int count = 0;
        try {
            session = HibernateUtil.getSession("hibernate.cfg.xml");

            String sql = "select hc.hourly_chart_id"
                    + " from hourly_chart hc left join farechart_master fcm on fcm.farechart_master_id=hc.farechart_master_id"
                    + " left join service_type st on st.service_type_id=fcm.service_type_id"
                    + " Where hc.deleted_status=0 and fcm.deleted_status=0 and (fcm.effect_end_date is null or fcm.effect_end_date>=curdate())"
                    + " and hc.route_id=" + routeId + " and hc.service_type_id=" + serviceTypeId;

            String search_term = request.getParameter("sSearch");

            if (search_term != null && !search_term.equals("")) {
                search_term = search_term.trim();
                sql += " AND(hc.farechart_name like '" + search_term + "%')";

            }

            if (!col.equals("")) {
                if (dir.equals("asc")) {
                    sql += " order by " + col + " asc";
                } else {
                    sql += " order by " + col + " desc";
                }
            }
            sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
            System.out.println("@@@@ "+sql);
            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> list = query.list();
            count = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
	//check dependency of faremaster//rajesh

    public String checkDependencyFareMaster(int faremasterid) {
        String s = "unsuccess";
        String sql = "select * from form_four where route_id in (select route_id from farechart_master where farechart_master_id=" + faremasterid + ")";
        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
        try {
            Query query = session.createSQLQuery(sql);
            List<Object> farelist = query.list();
            //System.out.println("list size"+farelist.size());
            if (farelist.size() > 0) {
                s = "success";
            } else {
                s = "unsuccess";

            }

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return s;
    }

    //end
    public int updateFareChartTriangle(Farechart_Master fareChart, int routfarechart_master_ideid, int applyCeilValue, int oldRateMasterId, String type) {
        Session session = null;
        int status = -1;
        int ceilVal = 0;
        try {

            //route validation with dates
            int mid = validByRouteEffectiveDatesUp(fareChart);

            if (mid == -1) {
                return 4;
            }

            //duplicate validation
            int masterId = duplicateUpdateByQuery(fareChart);

            if (masterId != -1) {
                return 3;
            }
            if (type.equalsIgnoreCase("edit")) {
                if (oldRateMasterId != fareChart.getRate_master_id()) {
                    String farechartTable = "UPDATE fare_chart set deleted_status=1 where farechart_master_id=" + fareChart.getFarechart_master_id() + " ";

                    int q = getDBExcute(session, farechartTable);

                }
            }
            session = HibernateUtil.getSession("hibernate.cfg.xml");
            HttpServletRequest request = ServletActionContext.getRequest();
            Common common = new Common();
            session.beginTransaction();
            /**
             * ********fare chart triangle**************************************14/11/14
             */
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());

            if (fareChart.getFlexi_fare().startsWith("N") && (getFareChartDetailCount(session, fareChart.getFarechart_master_id()) <= 0)) {// if flexi_fare=Y, create detail else dont
                fareChart.setEffect_start_date(common.getDateTimeFromPicker(fareChart.getEffect_start_date()));

                if (fareChart.getEffect_end_date() != null) {
                    fareChart.setEffect_end_date(common.getDateTimeFromPicker(fareChart.getEffect_end_date()));
                }
                List<RateMasterDetail> fareDetails = getFareDetail2(fareChart.getRate_master_id(), fareChart.getService_type_id(), session);

                //get bus stop pts using route_id
                List<RoutePoint_Fare> busStopPts = getBusStop(fareChart.getRoute().getRoute_id(), session);

                //get bus stop pts using route_id for distance calculation
                List<RoutePoint_Fare> busStopPtsDist = getBusStopForDistance(fareChart.getRoute().getRoute_id(), session);

                //get bus stop pts using route_id for toll fee calculation
                List<RoutePoint_Fare> busStopPtsToll = getBusStopForToll(fareChart.getRoute().getRoute_id(), session);

                Set busPtsOver = new HashSet();
                FareChart fareChartDetail = null;

                if (busStopPts.size() > 0) {
                    int countRec = 0;

                    //fare chart triangle data entry in table
                    for (int j = 0; j < busStopPts.size(); j++) {
                        int stageCount = 0, distance = 0, tollFee = 0;

                        for (int j2 = 0; j2 < busStopPts.size(); j2++) {
                            if (!findList(busPtsOver, busStopPts.get(j).getBusStopId(), busStopPts.get(j2).getBusStopId())) {
                                if (busStopPts.get(j).getBusStopId() != busStopPts.get(j2).getBusStopId()) {
		             //Stage calculation start-------------------------------------------
                                    //stageCount=calulateStage(busStopPts,j2,stageCount);	
                                    stageCount = calulateNoOfStages(busStopPts, j, j2);
                                    stageCount = calulateFareStages(busStopPts, j, j2, stageCount);//calulateStage(busStopPts,j,j2,stageCount);

                                    //distance
                                    distance = calculateDistance(busStopPtsDist, busStopPts.get(j).getRouteOrder(), busStopPts.get(j2).getRouteOrder());

                                    //toll fee
                                    tollFee = calculateTollFee(busStopPtsToll, busStopPts.get(j).getRouteOrder(), busStopPts.get(j2).getRouteOrder());

                                    //start:end point combination
                                    busPtsOver.add(busStopPts.get(j).getBusStopId() + ":" + busStopPts.get(j2).getBusStopId());

                                    //insert into fare_chart table for each passenger type
                                    int adultFare = 0;

                                    try {
                                        if (stageCount != 0) {
                                            adultFare = fareDetails.get(stageCount - 1).getAdult();// -1 for array index
                                        } else {
                                            adultFare = fareDetails.get(stageCount).getAdult();
                                        }
                                    } catch (Exception e) {
                                    }

                                    //apply ceil fare if it non zero & greater than adult fare
                                    if (fareChart.getCeiling_fare() != 0 && adultFare > fareChart.getCeiling_fare()) {
                                        adultFare = fareChart.getCeiling_fare();
                                        //System.out.println("applied ceil adultFare="+adultFare+" earlier fare="+fareDetails.get(stageCount).getAdult());
                                    }

                                    //Fare calculation & entry into database for each passenger id
                                    for (int c = 1; c < 4; c++) {
                                        fareChartDetail = new FareChart();
                                        fareChartDetail.setFareChartMasterId(fareChart.getFarechart_master_id());
                                        fareChartDetail.setRouteId(fareChart.getRoute().getRoute_id());
                                        fareChartDetail.setServiceTypeId(fareChart.getService_type_id());
                                        fareChartDetail.setPassengerTypeId(c);
                                        fareChartDetail.setScheduleTypeId(0);
                                        fareChartDetail.setStartPoint(busStopPts.get(j).getBusStopId());
                                        fareChartDetail.setEndPoint(busStopPts.get(j2).getBusStopId());
                                        fareChartDetail.setKms(distance);

                                        //02/08/14 added as per requirement in fare chart for ETIM
                                        fareChartDetail.setStartPointOrder(j + 1);
                                        fareChartDetail.setEndPointOrder(j2 + 1);

                                        if (c == 1) {
                                            int childFare = (int) Math.round(adultFare * 0.50);
                                            fareChartDetail.setFareAmount(childFare);//(fareDetails.get(stageCount).getChildren());
                                        } else {
                                            if (c == 2) {
                                                fareChartDetail.setFareAmount(adultFare);//(fareDetails.get(stageCount).getAdult());
                                            } else {
                                                if (c == 3) {
                                                    int srFare = (int) Math.round(adultFare * 0.75);
                                                    fareChartDetail.setFareAmount(srFare);
                                                }
                                            }
                                        }

                                        fareChartDetail.setTollFee(tollFee);
                                        fareChartDetail.setEffStartDate(fareChart.getEffect_start_date());
                                        fareChartDetail.setEffEndDate(fareChart.getEffect_end_date());
                                        fareChartDetail.setCreatedBy(fareChart.getCreated_by());
                                        fareChartDetail.setCreatedDate(date);
                                        fareChartDetail.setUpdatedBy("0");

                                        session.save(fareChartDetail);//save data
                                    }
                                } else {
                                    //check 1st bus stop
                                    if (j2 == 0) {
                                        //stageCount=calulateStage(busStopPts,j,j2,stageCount);	
                                        stageCount = calulateNoOfStages(busStopPts, j, j2);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
				//check if ceil fare is changed
                //ceilVal is old ceil fare
            	System.out.println("it is in else block....in .....updateFareChartTriangle()...............");
                if (applyCeilValue == 1 && fareChart.getCeiling_fare() != 0 && fareChart.getCeiling_fare() != ceilVal) {
                    applyCeilToFareDetail(session, routfarechart_master_ideid, fareChart.getCeiling_fare());
                }
            }
            /**
             * ********fare chart triangle**************************************
             */

            session.getTransaction().commit();
            status = 1;
        } catch (Exception e) {
        	System.out.println("it is in catch block....in .....updateFareChartTriangle()...............");
        	e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                //session.close();
            }
        }

        return status;

    }

    public int getDBExcute(Session objSession, String sql) {
        int result = 0;

        try {

            Query query = objSession.createSQLQuery(sql);
            result = query.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
