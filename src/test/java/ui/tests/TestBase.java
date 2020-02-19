package ui.tests;

import com.codeborne.selenide.Configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TestBase {
    static {
        Configuration.baseUrl = "http://dillali.lancertool.com";

    }

    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int year  = localDate.getYear();
    int month = localDate.getMonthValue();
    int day   = localDate.getDayOfMonth();





}
