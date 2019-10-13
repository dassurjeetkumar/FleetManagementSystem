package com.trimax.its.report.action;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;

import org.apache.struts2.ServletActionContext;
import org.apache.taglibs.standard.extra.spath.Path;
import org.hibernate.Query;

import antlr.StringUtils;
import antlr.collections.List;

import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.jcraft.jsch.Session;
import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.util.HibernateUtil;

public class countapplication extends ActionSupport{
	    public String startdate;

	String path="";
	char ft = 15;
	String str="";

	String c=" ";

	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	public String getStartdate() {
	    return startdate;
	}

	public void setStartdate(String startdate) {
	    this.startdate = startdate;
	}




    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        return "success";
    }

    static String regionTypeAjaxString = "";
    private String runDateTime;

    public String getRegionTypeAjaxString() {
        return regionTypeAjaxString;
    }

    public void setRegionTypeAjaxString1(String regionTypeAjaxString) {
        this.regionTypeAjaxString = regionTypeAjaxString;
    }


        
    List textFiles(String directory) {
          List textFiles = (List) new ArrayList<String>();
          File dir = new File(directory);
          for (File file : dir.listFiles()) {
            if (file.getName().endsWith(("D11-2016-11-02-slalog"))) {
              textFiles.add(file.getName());
            }
          }
       //   System.out.println(textFiles);
          return textFiles;
        }


    public static void main(String ar[]){
        Ajaxcountapplication();}
     
  
    public static void insertdata(String timestamp,String type,String depotid,String status){    
        Query query=null;
        String sql= "insert into  sla_application(date,id,type,satus) values ('"+timestamp+"','"+depotid+"','"+type+"','"+status+"')";
          org.hibernate.Session session = null;
        int updated=0;
        try {
             session = HibernateUtil.getSession("hibernate.cfg.xml");
             org.hibernate.Transaction tx=session.beginTransaction();
//             System.out.println("sql-----"+sql);
            query = session.createSQLQuery(sql);
            updated=query.executeUpdate();
//            System.out.println("Update value====="+updated);
            if(updated>0){
                tx.commit();
            }else{
            }
         } catch (Exception e) {
          System.out.println(e);
            e.printStackTrace();
                }}
  

    private static  File getLatestFilefromDir(String dirPath){
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
           if (lastModifiedFile.lastModified() < files[i].lastModified()) {
               lastModifiedFile = files[i];
           }
        }
     //   System.out.println(lastModifiedFile.getName());
        return lastModifiedFile;
    }

  
    public static String Ajaxcountapplication(){
        File file1 = getLatestFilefromDir("/root/SLA");
        {
        String line="";
            String filename = file1.getName().toString();
//        System.out.println("name"+filename);
//        File file =new File("filename");
//      System.out.println("file1----"+file1);
           Scanner in = null;
        try {
            in = new Scanner(file1);
            while(((Scanner) in).hasNext())
            {
                line=((Scanner) in).nextLine();
               if(line.contains("Application")){
          //     System.out.println("line");
           String lines [] =line.split(" ");
           String timestamp=lines[0]+" "+lines[1];
           String depotid=lines[2];
           String type=lines[3];
           String status=lines[4];
//           System.out.println(lines[0] + lines[1] + "    " + lines[2] + "    " + lines[3] + "   " + lines[4]);
           insertdata(timestamp, type,depotid,status);
           }else {
               }}}
        catch (Exception e) {
            e.printStackTrace();}
       return line;
        }}}	