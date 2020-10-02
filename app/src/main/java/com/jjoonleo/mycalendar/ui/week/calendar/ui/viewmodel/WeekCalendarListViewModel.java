package com.jjoonleo.mycalendar.ui.week.calendar.ui.viewmodel;

import com.jjoonleo.mycalendar.ui.month.calendar.data.TSLiveData;
import com.jjoonleo.mycalendar.ui.utils.Keys;
import com.jjoonleo.mycalendar.ui.utils.Types;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.lifecycle.ViewModel;

public class WeekCalendarListViewModel extends ViewModel {
    public TSLiveData<ArrayList<Object>> mWeekCalendarList = new TSLiveData<>(new ArrayList<Object>());
    public ArrayList<Integer> typeList = new ArrayList<>();
    public TSLiveData<ArrayList<String>> strings = new TSLiveData<>(new ArrayList<String>());//날짜 출력
    public TSLiveData<ArrayList<String>> DotW = new TSLiveData<>(new ArrayList<String>());//요일 출력
    public GregorianCalendar calendar = new GregorianCalendar();
    public GregorianCalendar bcalendar = new GregorianCalendar();

    public void initCalendarList(GregorianCalendar gregorianCalendar) {
        GregorianCalendar cal = gregorianCalendar;
        bcalendar.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_WEEK)+1, 0, 0, 0);
        setCalendarList(cal);
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
        ArrayList<Object> timeTable = new ArrayList<Object>();
        boolean[][] check = new boolean[51][7];
        for (int i = 0; i < 51; i++) {
            for(int j = 0; j<7;j++)
            check[i][j] = false;
        }
        for (int i = 0; i < 1; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);


                for (int j = 1; j <= 7; j++) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_WEEK)+j, 0, 0, 0);
                    //calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_WEEK)+j));
                    date.add(String.format("%d", calendar.get(Calendar.DAY_OF_MONTH)));
                }
                calendar.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_WEEK)+1, 7, 0, 0);
                for(int j = 0; j<51; j++){
                    if(j%3==0){
                        timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), j*20, 0));
                        typeList.add(Types.HEADERTYPE);
                    }else{
                        timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), j * 20, 0));
                        typeList.add(Types.EMPTYTYPE);
                    }
                    for(int k = 0; k < 7; k++){
                        if (check[j][k]!=true){
                            if(j == 5 && (k == 6 || k == 5||k==4||k==3||k == 2)){
                                typeList.add(4);
                                check[j+1][k] = true;
                                check[j+2][k] = true;
                                timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), cal.get(Calendar.DATE) + k, calendar.get(Calendar.HOUR_OF_DAY), j * 20, 0));
                            }
                            else {
                                typeList.add(Types.EMPTYTYPE);
                                timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), cal.get(Calendar.DATE) + k, calendar.get(Calendar.HOUR_OF_DAY), j * 20, 0));
                        }
                        }else{
                            typeList.add(-1);
                            timeTable.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), cal.get(Calendar.DATE) + k, calendar.get(Calendar.HOUR_OF_DAY), j * 20, 0));
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        strings.setValue(date);
        mWeekCalendarList.setValue(timeTable);

    }

}
