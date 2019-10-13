/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trimax.its.org.json;

import java.util.ArrayList;

/**
 *
 * @author root
 */
public class Test {
    public static void main(String args[]) throws JSONException{
  JSONObject object=new JSONObject();
 object.put("name","Amit Kumar");
 object.put("Max.Marks",new Integer(100));
 object.put("Min.Marks",new Double(40));
 object.put("Scored",new Double(66.67));
 object.put("nickname","Amit");
 System.out.println(" object  "+object);
 
 
 int ar[] = {1, 2, 3};
//String strDate[2][2]=new String[];
ArrayList arrDate = new ArrayList();
ArrayList arrsubDate = new ArrayList();
String a="Aa";
String b="Bb";
String c="Cc";
String d="Dd";
arrsubDate.add(a);
arrsubDate.add(b);
arrDate.add(arrsubDate);
arrsubDate = new ArrayList();
arrsubDate.add(c);
arrsubDate.add(d);
arrDate.add(arrsubDate);    
 
        JSONArray ja = new JSONArray(arrDate);
  System.out.println(" ja.toString()  "+ja.toString());  
  ArrayList data = new ArrayList();
   for(int i=0;i<5;i++) {
                               String BUS_STOP_CD="jpr";
                               String BUS_STOP_NM="jaipur"; 
                  
                                ArrayList data1 = new ArrayList();
                             data1.add(BUS_STOP_CD);
                             data1.add(BUS_STOP_NM);
                            // return_val.add(data);
                               // String data1 ="["+BUS_STOP_CD+","+BUS_STOP_NM+"]";
                              // data = data+data1;
                             data.add(data1);
                             }
   ja = new JSONArray(data);
  System.out.println(" ja.toString()data  "+ja.toString());  
    }   
}
