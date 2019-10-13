package com.trimax.its.common;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

public class Common {
	
	@SuppressWarnings("finally")
	public String getDateTimeFromPicker(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String getDateTimeFromPickerBrand(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	@SuppressWarnings("finally")
	public String getDateTimeFromPickerBrandEdit(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String getDateFromDateTime(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat1.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String getDateFromDateTime_(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat1.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String getDateToDate(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat1.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String getDateToDate2(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat1.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String getDateToDate4(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat1.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String getDateToDate3(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat1.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String getDateToDate5(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	public static String getDateFromDateTimeFmt2(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat1.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String getDateFromPicker(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-mm-yyyy");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	formattedDate=null;
        	ex.printStackTrace();
        	
        }
        finally{
        	
        	return formattedDate;
        }
	}
	@SuppressWarnings("finally")
	public String getDateFromPickerWithTime(String pickerDate)
	{
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat.parse(pickerDate);
        	formattedDate=simpleDateFormat1.format(date);
        }catch(Exception ex)
        {
        	formattedDate=null;
        	ex.printStackTrace();
        	
        }
        finally{
        	
        	return formattedDate;
        }
	}
	@SuppressWarnings("finally")
	public String getDateFromPickerWithTime1(String pickerDate)
	{
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	
        	formattedDate=simpleDateFormat.format(date);
        	
        }catch(Exception ex)
        {
        	formattedDate=null;
        	ex.printStackTrace();
        	
        }
        finally{
        	
        	return formattedDate;
        }
	}
	
	@SuppressWarnings("finally")
	public String formatDate(String pickerDate,String srcFormat,String destFormat){
		SimpleDateFormat srcDateFormat = new SimpleDateFormat(srcFormat);
		SimpleDateFormat destDateFormat = new SimpleDateFormat(destFormat);
        Date date = new Date();
        String formattedDate="";
        
        try{
        	date = srcDateFormat.parse(pickerDate);     	
        	formattedDate=destDateFormat.format(date);        	
        }catch(Exception ex){
        	formattedDate=null;
        	ex.printStackTrace(); 	
        }finally{       	
        	return formattedDate;
        }
	}
	
	public String getDateToPicker(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();
        Date date1 = null;
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	formattedDate=null;
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return formattedDate;
        }
	}

	public Date getDate(String pickerDate){
		Date date = new Date();
		try {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		pickerDate += " 00:00:00";
		
			date =  simpleDateFormat.parse(pickerDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
if(!pickerDate.equals("") || pickerDate!=null){
	return date;
}
else{
	return null;
}
		
	}
	
	public Date getHours(String pickerHour){
		System.out.println("Picker Hur---->"+pickerHour);
		Date date = new Date();
		try {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		/*String splitResult[] = pickerHour.split(":");
		
		if(Integer.parseInt(splitResult[0])<9){
			pickerHour += "0"+pickerHour;
		}*/
		
		date = simpleDateFormat.parse(pickerHour);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public String getInDecimal(double number){
		String result="";
		Locale fmtLocale = Locale.getDefault();
        NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        formatter.setMaximumFractionDigits(1);
        formatter.setMinimumFractionDigits(1);
        
		result = formatter.format(number);
		return result;
	}
	
	public String addTime(String hour1, String hour2){
		
		int totalHours=0;
		int totalMinutes=0;
		String [] splitresult1 = hour1.split(":");
		String [] splitresult2 = hour2.split(":");
		if(splitresult1.length>1 && splitresult2.length>1){
		totalHours = Integer.parseInt(splitresult1[0]) + Integer.parseInt(splitresult2[0]);
		totalMinutes = Integer.parseInt(splitresult1[1])+ Integer.parseInt(splitresult2[1]);
		}
		if (totalMinutes >= 60) {
			  totalHours ++;
			  totalMinutes = totalMinutes % 60;
			}
		return totalHours+":"+totalMinutes;
	}
	@SuppressWarnings("finally")
	public String getDateTimeFromPickerToDB(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM yyyy - HH:mm");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	
	@SuppressWarnings("finally")
	public String getDateTimeISTFromPickerToDB(String pickerDate)
	{
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss");
//		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM yyyy - HH:mm");
//        Date date = new Date();
//        String formattedDate="";
//        try
//        {
//        	date = simpleDateFormat1.parse(pickerDate);
//        	formattedDate=simpleDateFormat.format(date);
//        }
		
		DateFormat readFormat = new SimpleDateFormat( "EEE MMM dd yyyy hh:mm");

	    DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
	    Date date = null;
	    String formattedDate = "";
	    try {
	       date = readFormat.parse( pickerDate );
	    

	   
	    if( date != null ) {
	    	formattedDate = writeFormat.format( date );
	    }
		
	    }catch(Exception ex)
        {
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	
	public String getDateTimeFromDBToPicker(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return formattedDate;
        }
	}
	public String getDateTimeFromDBToList(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	/**
	 * @author : Satya
	 * @return 1 if this Date is greater than argument Date
	 *         0 if this Date is equal   to   argument Date
	 *        -1 if this Date is less    than argument Date
	 * */
	public int compareDates(String dateOne,String dateTwo) 
	{
		try{
			System.out.println("dateone ------> "+dateOne+"  datetwo------>"+dateTwo);
			if(dateTwo==null || dateTwo.equals("")){
				return 0;
			}else if(dateOne==null || dateOne.equals("")){
				return 0;
			}else{
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date date1 = sdf.parse(dateOne);
				Date date2 = sdf.parse(dateTwo);
				System.out.println("dateone ------> "+date1+"  datetwo------>"+date2);
				if(date1.compareTo(date2)>0){
					System.out.println("Return 1");
					return 1;
				}else if(date1.compareTo(date2)<0){
					return -1;
				}
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return -10;
		}
	}
	/**
	 * @author : Satya
	 * @param : date one ,date two,date format
	 * @return 1 if this date one is greater than argument date two
	 *         0 if this date one is equal   to   argument date two
	 *        -1 if this date one is less    than argument date two
	 * */
	public int compareDates(String dateOne,String dateTwo,String format) 
	{
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date1 = sdf.parse(dateOne);
			Date date2 = sdf.parse(dateTwo);
			if(date1.compareTo(date2)>0){
				return 1;
			}else if(date1.compareTo(date2)<0){
				return -1;
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return -10;
		}
	}
	public int compareDatesBrand(String dateOne,String dateTwo) 
	{
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = sdf.parse(dateOne);
    	Date date2 = sdf.parse(dateTwo);
    	if(date1.compareTo(date2)>0){
    		return 1;
    	}else if(date1.compareTo(date2)<0){
    		return -1;
    	}
    		return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -10;
		}
	}
	public int compareDatesBrandEdit(String dateOne,String dateTwo) 
	{
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(dateOne);
    	Date date2 = sdf.parse(dateTwo);
    	if(date1.compareTo(date2)>0){
    		return 1;
    	}else if(date1.compareTo(date2)<0){
    		return -1;
    	}
    		return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -10;
		}
	}
	
	public int compareTwoDatesEdit(String dateOne,String dateTwo) 
	{
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		
		
		String date=getDateTimeFromPicker2(dateOne);
		Date date1 = sdf.parse(date);
    	Date date2 = sdf.parse(dateTwo);
    	if(date1.compareTo(date2)>0){
    		return 1;
    	}else if(date1.compareTo(date2)<0){
    		return -1;
    	}
    		return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -10;
		}
	}
	
	
	
	@SuppressWarnings("finally")
	public Date getDateFromDatePicker(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return date;
        }
	}
	public String getDateToDatePicker(Date pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate.toString());
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	public String getDateInDateSecs(Date pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        String formattedDate="";
        try
        {
        	formattedDate=simpleDateFormat.format(pickerDate);
        }catch(Exception ex)
        {
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return formattedDate;
        }
	}
	
	public String getDateTimeToDatePicker(Date pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate.toString());
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return formattedDate;
        }
	}
	public Date getDateTimeFromDatePicker(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM yyyy - HH:mm");
        Date date = null;
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return date;
        }
	}
	
	 public String getDateInDDMMYY(Date date){
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
			return simpleDateFormat1.format(date);
		}
	 
	 public String getDateInHHMM(Date date){
		 SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
			return simpleDateFormat1.format(date);
	 }
	@SuppressWarnings("finally")
	public String getDateFromDbToPicker(String pickerDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return formattedDate;
        }
	}
	 
	 @SuppressWarnings("finally")
		public String getDateTimeFromPicker2(String pickerDate)
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
	        Date date = new Date();
	        String formattedDate="";
	        try
	        {
	        	date = simpleDateFormat1.parse(pickerDate);
	        	formattedDate=simpleDateFormat.format(date);
	        }catch(Exception ex)
	        {
	        	ex.printStackTrace();
	        	
	        }
	        finally{
	        	return formattedDate;
	        }
		}
	 public int compareDatesTime(String dateOne,String dateTwo) 
		{
			try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy - HH:mm");
			Date date1 = sdf.parse(dateOne);
	    	Date date2 = sdf.parse(dateTwo);
	    	if(date1.compareTo(date2)>0){
	    		return 1;
	    	}else if(date1.compareTo(date2)<0){
	    		return -1;
	    	}
	    		return 0;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return -10;
			}
		}
	public boolean isGreaterThanCurrentDateTime(String date)
	{
		boolean isSuccess = false;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy - HH:mm");
			Date date1 = sdf.parse(date);
			if(date1.compareTo(new Date())>0){
				isSuccess =true;
			}
			else{
				isSuccess = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isSuccess;
				
	}
	public String convertToPaddingString(int number)
	{
		    DecimalFormat df = new DecimalFormat("00000000");
	        
	        String convertednumber=df.format(number);
	        return convertednumber;
	}
	
	/**
	 * @author : Satya
	 * @param  : date String ,current format,change format
	 * @return : date of required format
	 * */
    @SuppressWarnings("finally")
	public String changeDataFormat(String date, String dateFormat, String changeFormat) {
        String strDate = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            Date register_dt = sdf.parse(date);
            sdf = new SimpleDateFormat(changeFormat);
            strDate = sdf.format(register_dt);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }finally{
        	return strDate;
        }
    }
    
    /**
     * @author : Satya
     * @param objSession as session object
     * @param sql as query
     * @param selectName column name
     * @return column value
     */
    public String getDBResultStr(Session objSession, String sql, String selectName) throws Exception {
        String result = "";

            Query query = objSession.createSQLQuery(sql).addScalar(selectName, Hibernate.STRING);

            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, String>> aliasToValueMapList = query.list();

            if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
                Map<String, String> rs = aliasToValueMapList.get(0);
                result = (rs.get(selectName) != null && !rs.get(selectName).equals("") ? rs.get(selectName) : "");
            }


        return result;
    }
    
    /**
     * @author : Satya
     * @param objSession as session object
     * @param sql as query
     * @param selectName column name
     * @return column value
     */
    public int getDBResultInt(Session objSession, String sql, String selectName) {
        int result_count = 0;

        try {
            Query query = objSession.createSQLQuery(sql).addScalar(selectName, Hibernate.STRING);

            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, String>> aliasToValueMapList = query.list();

            if (aliasToValueMapList != null && aliasToValueMapList.size() > 0) {
                Map<String, String> rs = aliasToValueMapList.get(0);
                result_count = Integer.parseInt(rs.get(selectName) != null && !rs.get(selectName).equals("") ? rs.get(selectName) : "0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result_count;
    }
    
    public Date getIncrementDate(Date dateToIncrement, int daysToIncrement){
    	
    		Calendar c = Calendar.getInstance();
    		c.setTime(dateToIncrement);
    		c.add(Calendar.DATE, daysToIncrement);
    		Date newDate = c.getTime();
    		return newDate;

    }
    
    public int compareOnlyDate(Date date1){
    	
    	int result = 0;
    	Calendar myCalendar = Calendar.getInstance();
    	Calendar currentCalendar = Calendar.getInstance();
    	Date currentDate = new Date();
    	
    	myCalendar.setTime(date1);
    	myCalendar.set(Calendar.HOUR_OF_DAY, 0);
    	myCalendar.set(Calendar.MINUTE, 0);
    	myCalendar.set(Calendar.SECOND, 0);
    	myCalendar.set(Calendar.MILLISECOND, 0);
    	
    	currentCalendar.setTime(currentDate);
    	currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
    	currentCalendar.set(Calendar.MINUTE, 0);
    	currentCalendar.set(Calendar.SECOND, 0);
    	currentCalendar.set(Calendar.MILLISECOND, 0);
    	
    	if(myCalendar.after(currentCalendar)){
    		result = 1;
    	}
    	if(myCalendar.before(currentCalendar)){
    		result = -1;
    	}
    	if(myCalendar.equals(currentCalendar)){
    		result = 0;
    	}
    	return result;
    }
    
    @SuppressWarnings("finally")
	public Date getDateFromApi(String apiDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(apiDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        	
        }
        finally{
        	return date;
        }
	}    
   
    public int compareDatesInPeakHour(String dateOne,String dateTwo) 
	{
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date1 = sdf.parse(dateOne);
    	Date date2 = sdf.parse(dateTwo);
    	if(date1.compareTo(date2)>0){
    		return 1;
    	}else if(date1.compareTo(date2)<0){
    		return -1;
    	}
    		return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -10;
		}
	}
    
    @SuppressWarnings("finally")
	public String getDateToDateInpeakHour(String pickerDate)
	{
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();
        Date date1 = null;
        String formattedDate="";
        try
        {
        	date = simpleDateFormat1.parse(pickerDate);
        	formattedDate=simpleDateFormat.format(date);
        }catch(Exception ex)
        {
        	formattedDate=null;
        	System.out.println("errror--"+ex.getMessage());
        	
        }
        finally{
        	return formattedDate;
        }
	}
    @SuppressWarnings("finally")
  	public String getDateFromPickertopeak(String pickerDate)
  	{
      	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm");
  		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
  		 Date date = new Date();
         Date date1 = null;
         String formattedDate="";
         try
         {
         	date = simpleDateFormat1.parse(pickerDate);
         	formattedDate=simpleDateFormat.format(date);
         }catch(Exception ex)
         {
         	formattedDate=null;
         	System.out.println("errror--"+ex.getMessage());
         	
         }
         finally{
         	return formattedDate;
         }
 	}
    //adding differnce between two dates
    public int differenceOfDateString(String dateOne,String dateTwo)
    {
        int daysCount = 0;
        try{
            Calendar cal1 = new GregorianCalendar();
            Calendar cal2 = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = sdf.parse(dateOne);
        Date date2 = sdf.parse(dateTwo);
        cal1.setTime(date1);
        cal2.setTime(date2);
        daysCount = 1+daysBetween(cal1.getTime(),cal2.getTime());
        }catch(Exception e)
        {
            e.printStackTrace();
            return -10;
        }finally{
        	System.out.println("daysCount"+daysCount);
            return daysCount;
        }
        
    }
    public static int daysBetween(Date d1, Date d2){
    	return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    	}
    
    public String getDatefromString(String dateStr) throws ParseException
    {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
    	Date date = (Date)formatter.parse(dateStr);
    	System.out.println(date);        

    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +  cal.get(Calendar.DATE);
    	System.out.println("formatedDate : " + formatedDate);   
    	return formatedDate;
    }

}
