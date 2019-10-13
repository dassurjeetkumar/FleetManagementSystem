package com.trimax.its.vts.dao;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import java.io.IOException;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ScheduleTripUpdate {
	 public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		updateScheduleData(args[0],args[1],args[2]);

	}
	
	public static String updateScheduleData(String selectedDate, String vehicleno,
			String scheduleno) {
		String res="";
		// TODO Auto-generated method stub
		String user = "root";
        String password = "its!@#admin147";
        String host = "10.30.1.147";
        int port = 22;

        String command1 = "php  /opt/trimax/starttimeupdate.php '"+vehicleno+"' '"+selectedDate+"' '"+scheduleno+"'";
        //String command1 = "php  /opt/trimax/schedulers/pisdataupdate_indv1.php KA01F8818 2016-09-11 V-335";
        try {

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    //(channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
		return res;
		
	}

}
