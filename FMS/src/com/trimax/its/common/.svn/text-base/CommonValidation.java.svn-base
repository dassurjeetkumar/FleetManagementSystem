package com.trimax.its.common;

import com.opensymphony.xwork2.ActionSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidation {
	
	public boolean isvalidPhoneNO(String phoneNo) {
		 boolean flag=false;
	      //String sPhoneNumber = "6088899933";
	      //String sPhoneNumber = "605-88899991";
	      //String sPhoneNumber = "605-888999A";
	 
	      Pattern pattern = Pattern.compile("\\d{10}");
	      Matcher matcher = pattern.matcher(phoneNo);
	 
	      if (matcher.matches()) {
	    	  System.out.println("Phone Number Valid");
	    	  flag=true;
	      }
	      else
	      {
	    	  System.out.println("Phone Number must be in the form XXX-XXXXXXX");
	      }
	      return flag;
	 }
	
	public boolean isEmpty(String fieldValue){
		
		if(fieldValue == null || fieldValue.trim().equals("")){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public boolean isNumberic(String fieldvalue){
		Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
	    Matcher matcher = pattern.matcher(fieldvalue);
	     if (matcher.matches()) {
	    	 return true;
	     }else{
	    	 return false;
	    }
	}
	
	public boolean isSpecialChar(String fieldvalue){
		String regex = "^[a-zA-Z0-9- ]*$";
		 if(fieldvalue.matches(regex)){
			 return true;
			 }else{
				 return false;
			 }
	}
	
	public boolean isValidEmailId(String value){
		 String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		  Boolean b = value.matches(EMAIL_REGEX);
		  return b;
	}
	
	public boolean isValidPhoneNum(String value){
		Pattern pattern = Pattern.compile("\\d{12}");
	      Matcher matcher = pattern.matcher(value);
	 
	      return matcher.matches();
	}
	
	public boolean isValidMobileNum(String value,int digit){
		Pattern pattern = Pattern.compile("\\d{"+digit+"}");
	      Matcher matcher = pattern.matcher(value);
	 
	      return matcher.matches();
	}
	
	public boolean isNumber(String fieldvalue){
		Pattern pattern = Pattern.compile(".*\\D.*");
	    Matcher matcher = pattern.matcher(fieldvalue);
	     if (matcher.matches()) {
	    	 return false;
	     }else{
	    	 return true;
	    }
	}
}
