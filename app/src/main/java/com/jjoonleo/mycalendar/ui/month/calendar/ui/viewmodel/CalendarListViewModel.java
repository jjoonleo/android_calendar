package com.jjoonleo.mycalendar.ui.month.calendar.ui.viewmodel;

import com.jjoonleo.mycalendar.ui.month.calendar.data.TSLiveData;
import com.jjoonleo.mycalendar.ui.utils.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.lifecycle.ViewModel;

public class CalendarListViewModel extends ViewModel {
    private long mCurrentTime;

    public TSLiveData<String> mTitle = new TSLiveData<>();
    public TSLiveData<ArrayList<Object>> mCalendarList = new TSLiveData<>(new ArrayList<>());
    public ArrayList<Integer> typeList = new ArrayList<>();

    public int mCenterPosition;

    public void setTitle(int position) {
        try {
            Object item = mCalendarList.getValue().get(position);
            if (item instanceof Long) {
                setTitle((Long) item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(long time) {
        mCurrentTime = time;
        mTitle.setValue(DateFormat.getDate(time, DateFormat.CALENDAR_HEADER_FORMAT));
    }


    public void initCalendarList(GregorianCalendar gregorianCalendar) {
        GregorianCalendar cal = gregorianCalendar;
        setCalendarList(cal);
    }

    public void setCalendarList(GregorianCalendar cal) {


        setTitle(cal.getTimeInMillis());

        ArrayList<Object> calendarList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);
                GregorianCalendar preCalendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i - 1, 1, 0, 0, 0);
                if (i == 0) {
                    mCenterPosition = calendarList.size();
                }
                //calendarList.add(calendar.getTimeInMillis());

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; //해당 월에 시작하는 요일 -1 을 하면 빈칸을 구할 수 있겠죠 ?
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월에 마지막 요일

                for (int j = dayOfWeek - 1; j >= 0; j--) {
                    //calendarList.add(Keys.EMPTY);
                    typeList.add(0);
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), preCalendar.get(Calendar.MONTH), preCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)-j));
                }
                for (int j = 1; j <= max; j++) {
                    typeList.add(1);
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j));
                }
                for (int j = 0; j<42-(dayOfWeek + max); j++){
                    //calendarList.add(Keys.EMPTY);
                    typeList.add(0);
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, j));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mCalendarList.setValue(calendarList);

    }

}
