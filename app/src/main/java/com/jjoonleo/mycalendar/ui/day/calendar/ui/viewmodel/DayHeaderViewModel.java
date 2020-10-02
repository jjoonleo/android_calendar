package com.jjoonleo.mycalendar.ui.day.calendar.ui.viewmodel;

import com.jjoonleo.mycalendar.ui.month.calendar.data.TSLiveData;

import java.util.Calendar;

import androidx.lifecycle.ViewModel;

public class DayHeaderViewModel extends ViewModel {
    public TSLiveData<Calendar> mCalendar = new TSLiveData<>();
    public DayHeaderViewModel(){
    }
    public void setHeader(Calendar calendar){
        mCalendar.setValue(calendar);
    }
}
