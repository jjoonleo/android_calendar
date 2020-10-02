package com.jjoonleo.mycalendar.ui.day.calendar.bindingAdapter;


import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.databinding.BindingAdapter;

public class TextBindingAdapter {

    @BindingAdapter({"setDayCalendarHeaderText"})
    public static void setCalendarHeaderText(TextView view, Calendar calendar) {
        try {
            if (calendar != null) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), 0);
                view.setText(String.format("%d:%d0", gregorianCalendar.get(Calendar.HOUR_OF_DAY),gregorianCalendar.get(Calendar.MINUTE)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@BindingAdapter({"setDayText"})
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
    }*/

}
