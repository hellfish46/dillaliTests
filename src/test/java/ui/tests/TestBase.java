package ui.tests;

import com.codeborne.selenide.Configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TestBase {
    static {
        Configuration.baseUrl = "secret";

    }

    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int year  = localDate.getYear();
    int month = localDate.getMonthValue();
    int day   = localDate.getDayOfMonth();

    protected String getDay(){
        if(day < 10){
            return String.format("0%d", day);
        } else {
            return String.valueOf(day);
        }
    }

    protected String getMonth(){
        if(month < 10){
            return String.format("0%d", month);
        } else {
            return String.valueOf(month);
        }
    }

    protected String getYear(){
        return String.valueOf(year);
    }





}
