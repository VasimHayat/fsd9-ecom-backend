package com.fsd9.ecom.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String uiDateFormat="MM/dd/yyyy";

    public static LocalDate stringToLocalDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(uiDateFormat);
        return LocalDate.parse(dateString, formatter);
    }
}
