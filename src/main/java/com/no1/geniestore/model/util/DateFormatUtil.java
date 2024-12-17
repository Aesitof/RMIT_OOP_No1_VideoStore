package com.no1.geniestore.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    
    public static Date stringToDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }
}
