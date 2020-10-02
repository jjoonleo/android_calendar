package com.jjoonleo.mycalendar.ui.week.calendar.ui.viewmodel;

import com.jjoonleo.mycalendar.ui.month.calendar.data.TSLiveData;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.lifecycle.ViewModel;

public class WeekHeaderViewModel extends ViewModel {
    public TSLiveData<Calendar> mCalendar = new TSLiveData<>();
    public WeekHeaderViewModel(){
    }
    public void setHeader(Calendar calendar){
        mCalendar.setValue(calendar);
    }
}
