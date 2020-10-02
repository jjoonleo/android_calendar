package com.jjoonleo.mycalendar;

import android.app.Application;

import java.util.GregorianCalendar;

public class settingCalendar extends Application {
    GregorianCalendar gregorianCalendar;
    @Override
    public void onCreate() {
        super.onCreate();
        gregorianCalendar = new GregorianCalendar();
    }
    public void setGregorianCalendar(GregorianCalendar gregorianCalendar){
        this.gregorianCalendar = gregorianCalendar;
    }

    public GregorianCalendar getGregorianCalendar() {
        return gregorianCalendar;
    }
}
