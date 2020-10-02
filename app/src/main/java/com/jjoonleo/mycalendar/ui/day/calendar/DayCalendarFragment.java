package com.jjoonleo.mycalendar.ui.day.calendar;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoonleo.mycalendar.R;
import com.jjoonleo.mycalendar.databinding.DayCalendarFBinding;
import com.jjoonleo.mycalendar.ui.day.calendar.ui.adapter.DayCalendarAdapter;
import com.jjoonleo.mycalendar.ui.day.calendar.ui.viewmodel.DayCalendarListViewModel;


import java.util.ArrayList;
import java.util.GregorianCalendar;


public class DayCalendarFragment extends Fragment {
    DayCalendarFBinding binding;
    DayCalendarListViewModel model;
    GregorianCalendar gregorianCalendar;
    int width, height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day_calendar, container, false);
        View view = binding.getRoot();
        model = ViewModelProviders.of(this).get(DayCalendarListViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setModel(model);
        gregorianCalendar = new GregorianCalendar();
        screenSize();
        if (getArguments() != null) {
            Bundle args = getArguments();
            gregorianCalendar.set(args.getInt("year"),args.getInt("month"),args.getInt("day"));
            model.initCalendarList(gregorianCalendar);
        }
        observe();

        if (model != null) {
            model.initCalendarList(gregorianCalendar);
        }
        return view;
    }
    private void observe() {
        model.mDayCalendarList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.pagerCalendar;
                DayCalendarAdapter adapter = (DayCalendarAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setCalendarList(objects);
                } else {
                    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                    adapter = new DayCalendarAdapter(objects, model.typeList, getContext(), height, width);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }
            }
        });
    }
    public void screenSize(){
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();

        width = dm.widthPixels/3;

        height = dm.heightPixels/22;
    }
}
