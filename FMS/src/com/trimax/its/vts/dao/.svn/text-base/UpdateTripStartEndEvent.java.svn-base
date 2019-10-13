package com.trimax.its.vts.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateTripStartEndEvent extends Thread implements Runnable {

    private static String duty_dt = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

    	try {
            DBConnect connect = new DBConnect();
            DBConnectVts connectVts = new DBConnectVts();
            List<DeviceDetails> depotList = getAllDepotData(connectVts,args[0]);
            duty_dt = getCurrDate(connectVts);

            //duty_dt="2016-07-29";
            //System.out.println("dutydt" + duty_dt);
            for (DeviceDetails depotListval : depotList) {
                boolean flag = false;
                //Getting Depotwise Connection...
                try {

                    InetAddress inet = InetAddress.getByName(depotListval.getIp());

                    boolean status = inet.isReachable(5000); //Timeout = 5000 milli seconds

                    if (!status) {
                        String URL = "jdbc:mysql://" + "10.30.1.157" + "/" + "bmtcGprs" + "?loginTimeout=60";

                        String User = "abhishek";
                        String Password = "abhishek";
//                
//                    String URL = "jdbc:mysql://10.30.1.158:23306" + "/" + "D11";
//                    String User = "temp";
//                    String Password = "temp";
                        //String depot_name = ;
                        Class.forName("com.mysql.jdbc.Driver");
                        DriverManager.setLoginTimeout(100);
                        connect.connection = DriverManager.getConnection(URL, User, Password);
                        System.out.println("Connected" + URL);
                        connect.statement = connect.connection.createStatement(1004, 1007);
                        flag = true;

                    } else {
                        String URL = "jdbc:mysql://" + depotListval.getIp() + "/" + depotListval.getDbname() + "?loginTimeout=60";

                        String User = depotListval.getUname();
                        String Password = depotListval.getPassword();
//                
//                    String URL = "jdbc:mysql://10.30.1.158:23306" + "/" + "D11";
//                    String User = "temp";
//                    String Password = "temp";
                        //String depot_name = ;
                        Class.forName("com.mysql.jdbc.Driver");
                        DriverManager.setLoginTimeout(100);
                        connect.connection = DriverManager.getConnection(URL, User, Password);
                        System.out.println("Connected" + URL);
                        connect.statement = connect.connection.createStatement(1004, 1007);
                    }
                 //wait(2000);
                    //connect.statement.setQueryTimeout(5);

                    //updateTickets(connectVts,waybillList.get(0).getMax_ticket_id(),depotListval.getDepot_id());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }

                //Get Not Update Waybills from vts waybilltrip_details
                List<DeviceDetails> waybillListWtd = getNotUpdatedDataFromWaybill(connectVts, depotListval.getDepot_id(), args[2],args[1]);
                for (DeviceDetails root1 : waybillListWtd) {
                    List<DeviceDetails> waybillList = getNotUpdatedData(connect, depotListval.getDepot_id(), depotListval.getMax_ticket_id(), root1.getWaybill_no(), flag);
                    for (DeviceDetails root : waybillList) {

                        // List<DeviceDetails> child = getActualValue(connect, root.getWaybill_no(), root.getTrip_number(), root.getDuty_type_id(), depot_name);
                        //for (DeviceDetails childDetails : child) {
                        if (root.getTicket_sub_type_short_code().equals("TS")) {
                            //Update Start
                            updateTripStartDetails(root.getTicket_date() + " " + root.getTicket_time(),
                                    root.getWaybill_no(), root.getTrip_number(), connectVts, root.getTicket_date(), root.getShift_no());
                        } else {
                            //Update End
                            updateTripEndDetails(root.getTicket_date() + " " + root.getTicket_time_end(),
                                    root.getWaybill_no(), root.getTrip_number(), connectVts, root.getTicket_date(), root.getShift_no(),root.getTicket_date() + " " + root.getTicket_time());
                        }

                        try {
                            //String target = "php /opt/trimax/schedulers/pisdataupdate_indvtripwise.php "+" "+ root.getVehicle_no() +" "+ root1.getDuty_dt()+" '"+ root1.getSCHEDULE_NAME() +"' "+root1.getDuty_type_id()+" "+root.getTrip_number();
                            //System.out.println("TARGETTTT"+target);
// String target = new String("mkdir stackOver");
                            //Runtime rt = Runtime.getRuntime();
                            //Process proc = rt.exec(target);
                            //proc.waitFor();
                            
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }

                        //}
//                        List<DeviceDetails> dreadlistdet = getNotUpdatedDataDreadTrip(connectVts, depotListval.getDepot_id(), root.getWaybill_no(), root.getTrip_number());
//                        for (DeviceDetails dreadlist : dreadlistdet) {
//                            if (dreadlist.getIs_dread_trip() == 1 && dreadlist.getTrip_number() == 1) {
//                                //update 1st trip from TripNo:2
//                                String deptTime = getDepartureDataTrip2(connectVts, dreadlist.getDevice_id(), dreadlist.getDuty_dt(), dreadlist.getWaybill_no(),
//                                        dreadlist.getTrip_number());
//                                if (deptTime.length() > 0 && deptTime != null) {
//                                    updateTripStartDeparture(root.getTicket_date() + " " + root.getTicket_time(), dreadlist.getWaybill_no(), dreadlist.getTrip_number(), duty_dt, connectVts);
//                                    break;
//                                }
//
//                            } else if (dreadlist.getIs_dread_trip() == 1 && dreadlist.getTrip_number() != 1) {
//                                //update 1st trip from DepotInout Arrival
//                                String arrTime = getArrivalDatalastTrip(connectVts, dreadlist.getDevice_id(), duty_dt, dreadlist.getWaybill_no(),
//                                        dreadlist.getTrip_number());
//                                if (arrTime.length() > 0 && arrTime != null) {
//                                    updateTripEndArrival(root.getTicket_date() + " " + root.getTicket_time(), dreadlist.getWaybill_no(), dreadlist.getTrip_number(), duty_dt, connectVts);
//                                    break;
//                                }
//                            }
//                            System.out.println("Completed Schedule::" + dreadlist.getSCHEDULE_NAME());
//                        }
                        //Update Max Ticket in Server
                        //updateTickets(connectVts, root.getMax_ticket_id(), depotListval.getDepot_id());

                    }
                }
                //Process Dread Trip..

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    
    
    }

//Method to get Vts Data from DB..
private static String getCurrDate(DBConnectVts db) {
    String currDate = null;
    ResultSet rs = null;
    try {
        String sql = "SELECT DATE(now()) currDate ";
        // System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            currDate = rs.getString("currDate");
//            DeviceDetails details = new DeviceDetails();
//            details.setDepot_id(rs.getInt("depot_id"));
//            details.setDepotname(rs.getString("depotname"));
//            //details.setID(rs.getInt("id"));
//            list.add(details);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return currDate;
}

public static List<DeviceDetails> getAllDepotData(DBConnectVts db,String depot_id) {
    ResultSet rs = null;
    List<DeviceDetails> list = new ArrayList<DeviceDetails>();

    try {
        String sql = " SELECT `depot_id`,depotname,max_ticket_id,ip,uname,password,dbname "
                + " FROM `depot_ip_info` where deleted_status=0 and depot_id="+depot_id+" ";
        // System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            DeviceDetails details = new DeviceDetails();
            details.setDepot_id(rs.getInt("depot_id"));
            details.setDepotname(rs.getString("depotname"));
            details.setMax_ticket_id(BigInteger.valueOf(rs.getLong("max_ticket_id")));
            details.setIp(rs.getString("ip"));
            details.setUname(rs.getString("uname"));
            details.setPassword(rs.getString("password"));
            details.setDbname(rs.getString("dbname"));
            //details.setID(rs.getInt("id"));
            list.add(details);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return list;
}

public static String getDepartureData(DBConnectVts db, String device_id, String duty_dt) {
    ResultSet rs = null;
    String ist_date = "";

    try {
        String sql = "select min(ist_date) is_date from late_departure "
                + " where device_id='" + device_id + "' and ist_date "
                + " between concat('" + duty_dt + "',' ','00:00:00')"
                + " and concat('" + duty_dt + "',' ','23:59:59') and status='Y'";
        // System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            ist_date = rs.getString("is_date");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return ist_date;
}

public static String getDepartureDataTrip2(DBConnectVts db, String device_id, String duty_dt, String waybillno, int trip_number) {
    ResultSet rs = null;
    String ist_date = "";

    try {
        //get Sch.Type 

        List<DeviceDetails> list = getScheduleType(db, waybillno);
        String sql = "";
        //if duty_dt==
        if (list.get(0).getSCHEDULE_TYPE_ID() == 2) {
            sql = "select ETM_START_TIME from waybill_trip_details where"
                    //+ "  DUTY_DT='" + duty_dt + "' and "
                    + " WAYBILL_NO='" + waybillno + "' and trip_number=(" + trip_number + ")+1 and duty_type_id=2 ";
        } else {
            sql = "select ETM_START_TIME from waybill_trip_details where"
                    //+ "  DUTY_DT='" + duty_dt + "' and "
                    + " WAYBILL_NO='" + waybillno + "' and trip_number=(" + trip_number + ")+1 ";
        }
        // System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            ist_date = rs.getString("ETM_START_TIME") != null ? rs.getString("ETM_START_TIME") : "";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return ist_date;
}

public static String getArrivalData(DBConnectVts db, String device_id, String duty_dt) {
    ResultSet rs = null;
    String ist_date = "";

    try {
        String sql = "select max(ist_date) is_date from early_arrival "
                + " where device_id='" + device_id + "' and ist_date "
                + " between concat('" + duty_dt + "',' ','00:00:00')"
                + " and concat('" + duty_dt + "',' ','23:59:59') and status='Y'";
        //System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            ist_date = rs.getString("is_date");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return ist_date;
}

public static String getArrivalDatalastTrip(DBConnectVts db, String device_id, String duty_dt, String waybill_id, int trip_number) {
    ResultSet rs = null;
    String ist_date = "";

    try {
        List<DeviceDetails> list = getScheduleType(db, waybill_id);
        String sql = "";
        if (list.get(0).getSCHEDULE_TYPE_ID() == 2) {
            sql = "select ETM_END_TIME from waybill_trip_details where"
                    //+ "  DUTY_DT='" + duty_dt + "' and "
                    + " WAYBILL_NO='" + waybill_id + "' and trip_number=(" + trip_number + ")-1 and duty_type_id=3 ";
        } else {
            sql = "select ETM_END_TIME from waybill_trip_details where"
                    //+ "  DUTY_DT='" + duty_dt + "' and "
                    + " WAYBILL_NO='" + waybill_id + "' and trip_number=(" + trip_number + ")-1";
        }
        // System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            ist_date = rs.getString("ETM_END_TIME") != null ? rs.getString("ETM_END_TIME") : "";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return ist_date;
}

//public static List<DeviceDetails> getNotUpdatedData(DBConnectVts db, int depot_id) {
//    ResultSet rs = null;
//    List<DeviceDetails> list = new ArrayList<DeviceDetails>();
//
//    try {
//        String sql = "select ID,waybill_id,waybill_no,vehicle_no,device_id,trip_number,duty_type_id,is_dread_trip from waybill_trip_details "
//                + " where depot_id=" + depot_id + " and (ETM_START_TIME and ETM_END_TIME) is null and DUTY_DT='"+duty_dt+"' "
//                + " and is_dread_trip!=1 and status in('AUDITED','CLOSED') and ETIM_Coll_Amt!=0  "
//                + " order by waybill_id,trip_number,duty_type_id;";
//       // System.out.println("Sql" + sql);
//        rs = db.statement.executeQuery(sql);
//        while (rs.next()) {
//            DeviceDetails details = new DeviceDetails();
//            details.setWaybill_id(rs.getInt("waybill_id"));
//            details.setWaybill_no(rs.getString("waybill_no"));
//            details.setVehicle_no(rs.getString("vehicle_no"));
//            details.setTrip_number(rs.getInt("trip_number"));
//            details.setDuty_type_id(rs.getInt("duty_type_id"));
//            details.setIs_dread_trip(rs.getInt("is_dread_trip"));
//            details.setDevice_id(rs.getString("device_id"));
//            //details.setID(rs.getInt("id"));
//            list.add(details);
//        }
//    } catch (Exception ex) {
//        ex.printStackTrace();
//    } finally {
//
//    }
//    return list;
//}
public static List<DeviceDetails> getNotUpdatedData(DBConnect db, int depot_id, BigInteger maxID, String waybill_no, boolean flag) {
    ResultSet rs = null;
    List<DeviceDetails> list = new ArrayList<DeviceDetails>();

    try {
//        String sql = "select ticket_id,waybil_Id,waybill_no,schedule_no,"
//                + " trip_no,vehicle_no,ticket_sub_type_short_code,ticket_date,ticket_time,shift_no from ticket where WAYBILL_NO='" + waybill_no + "' "
//                //+ " where ticket_id >" +maxID+"  "
//                + " AND `ticket_sub_type_short_code` IN ('TS','TL','TC') order by shift_no,trip_no";//WAYBILL_NO='D1116071900311';
        String sql = "";
        if (!flag) {
            sql = "select *,if((select ticket_time from ticket where WAYBILL_NO='" + waybill_no + "' and trip_no=(A.trip_no+1) and shift_no=A.shift_no \n"
                    + "and ticket_no!=0 order by ticket_time limit 1) is null,A.ticket_time,\n"
                    + "(select ticket_time from ticket where WAYBILL_NO='" + waybill_no + "' and trip_no=(A.trip_no+1) and shift_no=A.shift_no \n"
                    + "and ticket_no!=0 order by ticket_time limit 1)) ticket_time_end\n"
                    + "from (select ticket_id,waybil_Id,waybill_no,schedule_no,\n"
                    + "trip_no,vehicle_no,ticket_sub_type_short_code,ticket_date,ticket_time,shift_no from ticket t \n"
                    + "where WAYBILL_NO='" + waybill_no + "' \n"
                    + "AND `ticket_sub_type_short_code` IN ('TS','TL','TC') \n"
                    + "order by shift_no,trip_no)A";
        } else {
            sql = "select *,if((select ticket_time from etim_gprs_ticket where WAYBILL_NO='" + waybill_no + "' and trip_no=(A.trip_no+1) \n"
                    + "and ticket_no!=0 order by ticket_time limit 1) is null,A.ticket_time,\n"
                    + "(select ticket_time from etim_gprs_ticket where WAYBILL_NO='" + waybill_no + "' and trip_no=(A.trip_no+1) \n"
                    + "and ticket_no!=0 order by ticket_time limit 1)) ticket_time_end\n"
                    + "from (select waybill_no,schedule_no,\n"
                    + "trip_no,vehicle_no,tkt_sub_type_short_code ticket_sub_type_short_code,ticket_date,ticket_time,shift_no from etim_gprs_ticket t \n"
                    + "where WAYBILL_NO='" + waybill_no + "' \n"
                    + "AND `tkt_sub_type_short_code` IN ('TS','TL','TC') \n"
                    + "order by shift_no,trip_no)A";
        }
        System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            DeviceDetails details = new DeviceDetails();
            //details.setWaybill_id(rs.getInt("waybil_Id"));
            details.setWaybill_no(rs.getString("waybill_no"));
            details.setVehicle_no(rs.getString("vehicle_no"));
            details.setTrip_number(rs.getInt("trip_no"));
            //details.setDuty_type_id(rs.getInt("duty_type_id"));
            //details.setIs_dread_trip(rs.getInt("is_dread_trip"));
            //details.setMax_ticket_id(BigInteger.valueOf(rs.getLong("ticket_id")));
            //details.setDevice_id(rs.getString("device_id"));
            //details.setID(rs.getInt("id"));
            details.setTicket_sub_type_short_code(rs.getString("ticket_sub_type_short_code"));
            details.setTicket_date(rs.getString("ticket_date"));
            details.setTicket_time(rs.getString("ticket_time"));
            details.setTicket_time_end(rs.getString("ticket_time_end"));
            details.setShift_no(rs.getInt("shift_no"));
            list.add(details);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return list;
}

public static List<DeviceDetails> getNotUpdatedDataFromWaybill(DBConnectVts db, int depot_id, String duty_dt,String Schedule_name) {
    ResultSet rs = null;
    List<DeviceDetails> list = new ArrayList<DeviceDetails>();

    try {
        String sql = "select waybill_no from waybill_trip_details where DUTY_DT >= DATE_ADD(DATE('"+duty_dt+"'), INTERVAL -1 day) "
                + " and (ETM_START_TIME IS NULL OR ETM_END_TIME IS NULL)   and depot_id="+depot_id+" and is_dread_trip!=1 and SCHEDULE_NAME='"+Schedule_name+"' "
              //  + " and ETIM_Coll_Amt!=0 "
                + "group by waybill_no " ;//WAYBILL_NO='D1116071900311';
        System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            DeviceDetails details = new DeviceDetails();
            //details.setWaybill_id(rs.getInt("waybil_Id"));
            details.setWaybill_no(rs.getString("waybill_no"));
            //details.setVehicle_no(rs.getString("vehicle_no"));
            //details.setTrip_number(rs.getInt("trip_no"));
            //details.setDuty_type_id(rs.getInt("duty_type_id"));
            //details.setIs_dread_trip(rs.getInt("is_dread_trip"));
            //details.setMax_ticket_id(BigInteger.valueOf(rs.getLong("ticket_id")));
            //details.setDevice_id(rs.getString("device_id"));
            //details.setID(rs.getInt("id"));
            //details.setTicket_sub_type_short_code(rs.getString("ticket_sub_type_short_code"));
            //details.setTicket_date(rs.getString("ticket_date"));
            //details.setTicket_time(rs.getString("ticket_time"));
            list.add(details);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return list;
}
//public static List<DeviceDetails> getNotUpdatedDataDreadTrip(DBConnectVts db, int depot_id) {
//    ResultSet rs = null;
//    List<DeviceDetails> list = new ArrayList<DeviceDetails>();
//
//    try {
//        String sql = "select ID,waybill_id,waybill_no,vehicle_no,device_id,trip_number,duty_type_id,is_dread_trip,SCHEDULE_NAME from waybill_trip_details "
//                + " where depot_id=" + depot_id + " and (ETM_START_TIME and ETM_END_TIME) is null "
//                + " and DUTY_DT='" + duty_dt + "' and is_dread_trip=1 and status in('AUDITED','CLOSED')  "
//                + " order by waybill_id,trip_number,duty_type_id;";
//        //System.out.println("Sql" + sql);
//        rs = db.statement.executeQuery(sql);
//        while (rs.next()) {
//            DeviceDetails details = new DeviceDetails();
//            details.setWaybill_id(rs.getInt("waybill_id"));
//            details.setWaybill_no(rs.getString("waybill_no"));
//            details.setVehicle_no(rs.getString("vehicle_no"));
//            details.setTrip_number(rs.getInt("trip_number"));
//            details.setDuty_type_id(rs.getInt("duty_type_id"));
//            details.setIs_dread_trip(rs.getInt("is_dread_trip"));
//            details.setDevice_id(rs.getString("device_id"));
//            details.setSCHEDULE_NAME(rs.getString("SCHEDULE_NAME"));
//            //details.setID(rs.getInt("id"));
//            list.add(details);
//        }
//    } catch (Exception ex) {
//        ex.printStackTrace();
//    } finally {
//
//    }
//    return list;
//}
public static List<DeviceDetails> getNotUpdatedDataDreadTrip(DBConnectVts db, int depot_id, String waybill_no, int trip_no) {
    ResultSet rs = null;
    List<DeviceDetails> list = new ArrayList<DeviceDetails>();

    try {
        String sql = "select ID,duty_dt,waybill_id,waybill_no,vehicle_no,device_id,trip_number,"
                + " duty_type_id,is_dread_trip,SCHEDULE_NAME from waybill_trip_details "
                + " where depot_id=" + depot_id + " and WAYBILL_NO='" + waybill_no + "'  and is_dread_trip=1  and ETIM_Coll_Amt!=0 and ETM_START_TIME IS NULL";
        //System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            DeviceDetails details = new DeviceDetails();
            details.setWaybill_id(rs.getInt("waybill_id"));
            details.setWaybill_no(rs.getString("waybill_no"));
            details.setVehicle_no(rs.getString("vehicle_no"));
            details.setTrip_number(rs.getInt("trip_number"));
            details.setDuty_type_id(rs.getInt("duty_type_id"));
            details.setIs_dread_trip(rs.getInt("is_dread_trip"));
            details.setDevice_id(rs.getString("device_id"));
            details.setSCHEDULE_NAME(rs.getString("SCHEDULE_NAME"));
            details.setDuty_dt(rs.getString("duty_dt"));
            //details.setID(rs.getInt("id"));
            list.add(details);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return list;
}

public static List<DeviceDetails> getActualValue(DBConnect db, String waybillno, int trip_number, int duty_type, String depotcd) {
    ResultSet rs = null;
    List<DeviceDetails> list = new ArrayList<DeviceDetails>();

    try {
        String sql = "SELECT `waybil_Id`, `waybill_no`, `trip_no`, `ticket_sub_type_short_code`, `ticket_date`, `ticket_time`, `shift_no`"
                + " FROM `ticket` WHERE `ticket_date` = '" + duty_dt + "' AND WAYBILL_NO = '" + waybillno + "' and trip_no=" + trip_number + ""
                + " AND `ticket_sub_type_short_code` IN ('TS','TL','TC')";
       // System.out.println("SQL!" + sql);

        //System.out.println("db.connection"+db.connection);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            DeviceDetails details = new DeviceDetails();
            details.setWaybill_id(rs.getInt("waybil_Id"));
            details.setWaybill_no(rs.getString("waybill_no"));
            details.setTrip_number(rs.getInt("trip_no"));
            details.setDuty_type_id(rs.getInt("shift_no"));
            details.setTicket_sub_type_short_code(rs.getString("ticket_sub_type_short_code"));
            details.setTicket_date(rs.getString("ticket_date"));
            details.setTicket_time(rs.getString("ticket_time"));
            //details.setID(rs.getInt("id"));
            list.add(details);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return list;
}

public static List<DeviceDetails> getScheduleType(DBConnectVts db, String waybill_no) {
    ResultSet rs = null;
    int schid = 0;
    List<DeviceDetails> list = new ArrayList<DeviceDetails>();
    try {
        String sql = "select SCHEDULE_TYPE_ID,DUTY_DT from waybill_trip_details where WAYBILL_NO='" + waybill_no + "' limit 1";
        // System.out.println("Sql" + sql);
        rs = db.statement.executeQuery(sql);
        while (rs.next()) {
            DeviceDetails details = new DeviceDetails();
            details.setSCHEDULE_TYPE_ID(rs.getInt("SCHEDULE_TYPE_ID"));
            details.setDuty_dt(rs.getString("DUTY_DT"));
            list.add(details);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
    return list;
}

public static void updateTripStartDetails(String ist_date, String waybillno, int trip_number, DBConnectVts db, String duty_dt, int shift_no) {
    try {

        db.connection.setAutoCommit(false);
        List<DeviceDetails> list = getScheduleType(db, waybillno);
        String sql1 = "";
        String duty_type = "0";
        if (shift_no == 1) {
            duty_type = " duty_type_id in(1,2,4,79) ";
        } else {
            duty_type = " duty_type_id in(3,5,80) ";
        }

        //
//        if (list.get(0).getSCHEDULE_TYPE_ID() == 2) {
//            if (list.get(0).getDuty_dt().equals(duty_dt)) {
//                sql1 = " update waybill_trip_details set ETM_START_TIME ='" + ist_date + "' "
//                        + " where  WAYBILL_NO = '" + waybillno + "' and trip_number=" + trip_number + " and duty_type_id=2 and is_dread_trip!=1";
//            } else {
//                sql1 = " update waybill_trip_details set ETM_START_TIME ='" + ist_date + "' "
//                        + " where  WAYBILL_NO = '" + waybillno + "' and trip_number=" + trip_number + " and duty_type_id=3 and is_dread_trip!=1";
//            }
//        } else {
        sql1 = " update waybill_trip_details set ETM_START_TIME ='" + ist_date + "' "
                + " where  WAYBILL_NO = '" + waybillno + "' and trip_number=" + trip_number + "  and " + duty_type + " and is_dread_trip!=1";
        //}

        //  + " and DUTY_DT='" + duty_dt + "'";
        //System.out.println("SQL TS" + sql1);
        db.statement.executeUpdate(sql1);
        db.connection.commit();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
}

public static void updateTripEndDetails(String ist_date, String waybillno, int trip_number, DBConnectVts db, String duty_dt, int shift_no,String actual_etm_end) {
    try {
        db.connection.setAutoCommit(false);
        List<DeviceDetails> list = getScheduleType(db, waybillno);
        String sql1 = "";
        String duty_type = "";
        if (shift_no == 1) {
            duty_type = " duty_type_id in(1,2,4,79) ";
        } else {
            duty_type = " duty_type_id in(3,5,80)";
        }
        //
//        if (list.get(0).getSCHEDULE_TYPE_ID() == 2) {
//            if (list.get(0).getDuty_dt().equals(duty_dt)) {
//                sql1 = " update waybill_trip_details set ETM_END_TIME ='" + ist_date + "' "
//                        + " where WAYBILL_NO = '" + waybillno + "' and trip_number=" + trip_number + " and duty_type_id=2 and is_dread_trip!=1";
//            } else {
//                sql1 = " update waybill_trip_details set ETM_END_TIME ='" + ist_date + "' "
//                        + " where WAYBILL_NO = '" + waybillno + "' and trip_number=" + trip_number + " and duty_type_id=3 and is_dread_trip!=1 ";
//            }
//        } else {
        sql1 = " update waybill_trip_details set ETM_END_TIME ='" + ist_date + "',crew_change_location='"+actual_etm_end+"',crew_change=1 "
                + " where WAYBILL_NO = '" + waybillno + "' and trip_number=" + trip_number + " and " + duty_type + " and is_dread_trip!=1";
        //}
        System.out.println("sql TL" + sql1);
        db.statement.executeUpdate(sql1);

        db.connection.commit();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
}

public static void updateTripStartDeparture(String ist_date, String waybillno, int trip_number, String duty_dt, DBConnectVts db) {
    try {
        db.connection.setAutoCommit(false);
        List<DeviceDetails> list = getScheduleType(db, waybillno);
        String sql1 = "";
        //
        if (list.get(0).getSCHEDULE_TYPE_ID() == 2) {
            // if (list.get(0).getDuty_dt().equals(duty_dt)) {
            sql1 = " update waybill_trip_details set ETM_END_TIME ='" + ist_date + "',"
                    + " ETM_START_TIME=DATE_ADD('" + ist_date + "',INTERVAL -5 MINUTE),crew_change=1 "
                    + " where WAYBILL_NO='" + waybillno + "' and trip_number=" + trip_number + " and duty_type_id=2 and is_dread_trip=1";
        } else {
            sql1 = " update waybill_trip_details set ETM_END_TIME ='" + ist_date + "',"
                    + " ETM_START_TIME=DATE_ADD('" + ist_date + "',INTERVAL -5 MINUTE),crew_change=1 "
                    + " where WAYBILL_NO='" + waybillno + "' and trip_number=" + trip_number;
        }
        System.out.println("sql1" + sql1);
        // }
        db.statement.executeUpdate(sql1);
        db.connection.commit();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
}

public static void updateTripEndArrival(String ist_date, String waybillno, int trip_number, String duty_dt, DBConnectVts db) {
    try {
        db.connection.setAutoCommit(false);

        String sql1 = " update waybill_trip_details set ETM_START_TIME ='" + ist_date + "',"
                + " ETM_END_TIME=DATE_ADD('" + ist_date + "',INTERVAL +5 MINUTE),crew_change=1 "
                + " where WAYBILL_NO='" + waybillno + "' and trip_number=" + trip_number + " and is_dread_trip=1";
        db.statement.executeUpdate(sql1);
        db.connection.commit();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
}

public static void updateTickets(DBConnectVts db, BigInteger id, int depot_id) {
    try {
        db.connection.setAutoCommit(false);

        String sql1 = " update depot_ip_info set max_ticket_id =" + id + " where depot_id=" + depot_id;
        db.statement.executeUpdate(sql1);
        db.connection.commit();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {

    }
}

@Override
public void run() {
    System.out.println("Date" + duty_dt);

}

}
