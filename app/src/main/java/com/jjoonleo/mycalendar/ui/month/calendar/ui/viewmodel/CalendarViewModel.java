package com.jjoonleo.mycalendar.ui.month.calendar.ui.viewmodel;


import android.content.Context;

import com.jjoonleo.mycalendar.ui.month.calendar.data.TSLiveData;
import com.jjoonleo.mycalendar.ui.month.calendar.ui2.adapter.ButtonAdapter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewModel extends ViewModel {
    public TSLiveData<Calendar> mCalendar = new TSLiveData<>();
    Calendar calendar;
    //public GregorianCalendar tCalendar = new GregorianCalendar();

    public void setCalendar(Calendar calendar) {
        this.mCalendar.setValue(calendar);
        this.calendar = calendar;
        //this.tCalendar = tCalendar;
    }
    public void makeButton(RecyclerView recyclerView, Context context){
        ButtonAdapter adapter = new ButtonAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
