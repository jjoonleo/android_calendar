package com.jjoonleo.mycalendar.ui.day.calendar.ui.viewmodel;

import com.jjoonleo.mycalendar.ui.month.calendar.data.TSLiveData;
import com.jjoonleo.mycalendar.ui.utils.Keys;
import com.jjoonleo.mycalendar.ui.utils.Types;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.lifecycle.ViewModel;

public class DayCalendarListViewModel extends ViewModel {
    public TSLiveData<ArrayList<Object>> mDayCalendarList = new TSLiveData<>(new ArrayList<Object>());
    public ArrayList<Integer> typeList = new ArrayList<>();
    public TSLiveData<String> strings = new TSLiveData<>();//날짜 출력
    public TSLiveData<String> DotW = new TSLiveData<>();//요일 출력

    public void initCalendarList(GregorianCalendar gregorianCalendar) {
        GregorianCalendar cal = gregorianCalendar;
        setCalendarList(cal);
    }

    public void setCalendarList(GregorianCalendar cal) {

        ArrayList<Object> timeTable = new ArrayList<Object>();
        boolean[] check = new boolean[51];
        for (int i = 0; i < 51; i++) {
            check[i] = false;
        }
        for (int i = 0; i < 1; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);


                for (int j = 1; j <= 1; j++) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 7, 0, 0);
                    strings.setValue(String.format("%d", calendar.get(Calendar.DAY_OF_MONTH)));
                    //calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_WEEK)+j));
                    setDotW(calendar.get(Calendar.DAY_OF_WEEK));
                }
                for(int j = 0; j<51; j++){
                    if(j%3==0){
                        timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), j*20, 0));
                        typeList.add(Types.HEADERTYPE);
                    }else{
                        timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), cal.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), j * 20, 0));
                        typeList.add(Types.EMPTYHEADERTYPE);
                    }
                    if (check[j]!=true){
                        if(j == 5){
                            typeList.add(4);
                            check[j+1] = true;
                            check[j+2] = true;
                            timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), cal.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), j * 20, 0));
                        }
                        else{
                            typeList.add(Types.EMPTYTYPE);
                            timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), cal.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), j * 20, 0));
                        }
                    }else{
                        typeList.add(-1);
                        timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), cal.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), j * 20, 0));
                    }
                    typeList.add(Types.EMPTY3);
                    timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), cal.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), j * 20, 0));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mDayCalendarList.setValue(timeTable);

    }
    public void setDotW(int i){
        switch (i){
            case 1:
                DotW.setValue("일");
            case 2:
                DotW.setValue("월");
            case 3:
                DotW.setValue("화");
            case 4:
                DotW.setValue("수");
            case 5:
                DotW.setValue("목");
            case 6:
                DotW.setValue("금");
            case 7:
                DotW.setValue("토");
        }
    }
}
