package com.example.finalProjectEpam;

import com.example.finalProjectEpam.entity.enums.RoleStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        test test = new test();
        Date date = new Date();
        System.out.println(date);
        test.getFormatDate(date);

    }

    public java.util.Date getFormatDate(java.util.Date date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        DateFormat format1 = new SimpleDateFormat(pattern);
        String mysqlDateString = format1.format(date);

        System.out.println(mysqlDateString);

        java.util.Date mysqlDate =  format1.parse(mysqlDateString);
        System.out.println(mysqlDate);
        java.sql.Date date1 = new java.sql.Date(mysqlDate.getTime());
        System.out.println(date1);
        return date1;
    }
}
