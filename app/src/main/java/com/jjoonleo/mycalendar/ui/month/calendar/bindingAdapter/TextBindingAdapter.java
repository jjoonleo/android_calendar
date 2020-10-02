package com.jjoonleo.mycalendar.ui.month.calendar.bindingAdapter;


import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoonleo.mycalendar.ui.utils.DateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.databinding.BindingAdapter;

public class TextBindingAdapter {

    @BindingAdapter({"setCalendarHeaderText"})
    public static void setCalendarHeaderText(TextView view, Long date) {
        try {
            if (date != null) {
                view.setText(DateFormat.getDate(date, DateFormat.CALENDAR_HEADER_FORMAT));
        }
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setDayText"})
    public static void setDayText(TextView view, Calendar calendar) {
        try {
            if (calendar != null) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                GregorianCalendar tGregorianCalendar = new GregorianCalendar();
                view.setText(DateFormat.getDate(gregorianCalendar.getTimeInMillis(), DateFormat.DAY_FORMAT));
                if(gregorianCalendar.get(Calendar.DAY_OF_MONTH) == tGregorianCalendar.get(Calendar.DAY_OF_MONTH) && gregorianCalendar.get(Calendar.MONTH) == tGregorianCalendar.get(Calendar.MONTH)
                && gregorianCalendar.get(Calendar.YEAR) == tGregorianCalendar.get(Calendar.YEAR)) {
                    view.setTextColor(Color.RED);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @BindingAdapter({"setBground"})
    public static void setBground(LinearLayout linearLayout, Calendar calendar) {
        try{
        if (calendar != null) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            GregorianCalendar tGregorianCalendar = new GregorianCalendar();
            if(gregorianCalendar.get(Calendar.DAY_OF_MONTH) == tGregorianCalendar.get(Calendar.DAY_OF_MONTH) && gregorianCalendar.get(Calendar.MONTH) == tGregorianCalendar.get(Calendar.MONTH)
                    && gregorianCalendar.get(Calendar.YEAR) == tGregorianCalendar.get(Calendar.YEAR)) {
                linearLayout.setBackgroundColor(Color.YELLOW);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}
