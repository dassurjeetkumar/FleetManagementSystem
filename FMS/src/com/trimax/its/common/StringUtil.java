package com.trimax.its.common;

public class StringUtil {

	public static String convert(String s) {
        boolean flag = true;
        if (s != null) {
            try {

                byte b[] = s.getBytes("8859_1");
                if (b != null && b.length > 0) {
                    int i = 0;
                    while (i < b.length) {
                        //System.out.println("b value:" + b[i]);
                        if (b[i] < 0) {
                            flag = false;
                            break;
                        }
                        i++;
                    }
                }

                if (!flag) {
                    //System.out.println("to be converted");
                    s = new String(b, 0, b.length, "SJIS");
                }
            } catch (Exception e) {
            }
        }
        return s;
    }
}
