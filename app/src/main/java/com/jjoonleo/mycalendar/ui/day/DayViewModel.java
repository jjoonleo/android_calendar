package com.jjoonleo.mycalendar.ui.day;

import com.jjoonleo.mycalendar.ui.month.calendar.data.TSLiveData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.lifecycle.ViewModel;

public class DayViewModel extends ViewModel {
    public TSLiveData<String> mTitle = new TSLiveData<String>();
    public ArrayList<Integer> typeList = new ArrayList<>();
    public TSLiveData<ArrayList<String>> strings = new TSLiveData<>(new ArrayList<String>());//날짜 출력
    public TSLiveData<ArrayList<String>> DotW = new TSLiveData<>(new ArrayList<String>());//요일 출력

    public void initCalendarList(GregorianCalendar gregorianCalendar) {
        GregorianCalendar cal = gregorianCalendar;
        setCalendarList(cal);
    }
    public void setTitle(GregorianCalendar gregorianCalendar){
        GregorianCalendar tCalendar = new GregorianCalendar();
        int month = gregorianCalendar.get(Calendar.MONTH);
        if(gregorianCalendar.get(Calendar.YEAR) != tCalendar.get(Calendar.YEAR))
            mTitle.setValue(String.format("%d "+getMonthInString(month), gregorianCalendar.get(Calendar.YEAR)));
        else
            mTitle.setValue(getMonthInString(month));
    }

    public void setCalendarList(GregorianCalendar cal) {
        ArrayList<String> DotW2 = new ArrayList<>();//요일 출력
        DotW2.add("일");
        DotW2.add("월");
        DotW2.add("화");
        DotW2.add("수");
        DotW2.add("목");
        DotW2.add("금");
        DotW2.add("토");
        DotW.setValue(DotW2);

        ArrayList<String> date = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);


                for (int j = 1; j <= 7; j++) {
                    //calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_WEEK)+j));
                    date.add(String.format("%d", calendar.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_WEEK)+j));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        strings.setValue(date);

    }
    public String getMonthInString(int month){
        switch (month){
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
        }
        return "";
    }

}

