package com.jjoonleo.mycalendar.ui.day.calendar.ui.viewmodel;

import com.jjoonleo.mycalendar.ui.month.calendar.data.TSLiveData;

import java.util.Calendar;

import androidx.lifecycle.ViewModel;

public class DayEmptyViewModel extends ViewModel {
    public TSLiveData<Calendar> mCalendar = new TSLiveData<>();
    public DayEmptyViewModel(){
    }
    public void setHeader(Calendar calendar){
        mCalendar.setValue(calendar);
    }
}
