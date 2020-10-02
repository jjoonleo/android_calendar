package com.jjoonleo.mycalendar.ui.month.calendar.ui.viewmodel;


import com.jjoonleo.mycalendar.ui.month.calendar.data.TSLiveData;

import androidx.lifecycle.ViewModel;

public class CalendarHeaderViewModel extends ViewModel {
    public TSLiveData<Long> mHeaderDate = new TSLiveData<>();

    public void setHeaderDate(long headerDate) {
        this.mHeaderDate.setValue(headerDate);
    }
}
