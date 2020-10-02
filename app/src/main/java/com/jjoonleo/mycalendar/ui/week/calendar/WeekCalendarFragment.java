package com.jjoonleo.mycalendar.ui.week.calendar;

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
import android.widget.Button;

import com.jjoonleo.mycalendar.MainActivity;
import com.jjoonleo.mycalendar.R;
import com.jjoonleo.mycalendar.databinding.WeekCalendarFrBinding;
import com.jjoonleo.mycalendar.ui.week.calendar.ui.adapter.WeekCalendarAdapter;
import com.jjoonleo.mycalendar.ui.week.calendar.ui.viewmodel.WeekCalendarListViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class WeekCalendarFragment extends Fragment {
    WeekCalendarFrBinding binding;
    WeekCalendarListViewModel model;
    GregorianCalendar gregorianCalendar;
    int width, height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_week_calendar, container, false);
        View view = binding.getRoot();
        model = ViewModelProviders.of(this).get(WeekCalendarListViewModel.class);
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
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("yearFMTD", model.bcalendar.get(Calendar.YEAR));
                bundle.putInt("monthFMTD", model.bcalendar.get(Calendar.MONTH));
                bundle.putInt("dayFMTD", model.bcalendar.get(Calendar.DAY_OF_MONTH));
                ((MainActivity) getContext()).navController.navigate(R.id.action_nav_week_to_nav_day,bundle);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("yearFMTD", model.bcalendar.get(Calendar.YEAR));
                bundle.putInt("monthFMTD", model.bcalendar.get(Calendar.MONTH));
                bundle.putInt("dayFMTD", model.bcalendar.get(Calendar.DAY_OF_MONTH)+1);
                ((MainActivity) getContext()).navController.navigate(R.id.action_nav_week_to_nav_day,bundle);
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("yearFMTD", model.bcalendar.get(Calendar.YEAR));
                bundle.putInt("monthFMTD", model.bcalendar.get(Calendar.MONTH));
                bundle.putInt("dayFMTD", model.bcalendar.get(Calendar.DAY_OF_MONTH)+2);
                ((MainActivity) getContext()).navController.navigate(R.id.action_nav_week_to_nav_day,bundle);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("yearFMTD", model.bcalendar.get(Calendar.YEAR));
                bundle.putInt("monthFMTD", model.bcalendar.get(Calendar.MONTH));
                bundle.putInt("dayFMTD", model.bcalendar.get(Calendar.DAY_OF_MONTH)+3);
                ((MainActivity) getContext()).navController.navigate(R.id.action_nav_week_to_nav_day,bundle);
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("yearFMTD", model.bcalendar.get(Calendar.YEAR));
                bundle.putInt("monthFMTD", model.bcalendar.get(Calendar.MONTH));
                bundle.putInt("dayFMTD", model.bcalendar.get(Calendar.DAY_OF_MONTH)+4);
                ((MainActivity) getContext()).navController.navigate(R.id.action_nav_week_to_nav_day,bundle);
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("yearFMTD", model.bcalendar.get(Calendar.YEAR));
                bundle.putInt("monthFMTD", model.bcalendar.get(Calendar.MONTH));
                bundle.putInt("dayFMTD", model.bcalendar.get(Calendar.DAY_OF_MONTH)+5);
                ((MainActivity) getContext()).navController.navigate(R.id.action_nav_week_to_nav_day,bundle);
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("yearFMTD", model.bcalendar.get(Calendar.YEAR));
                bundle.putInt("monthFMTD", model.bcalendar.get(Calendar.MONTH));
                bundle.putInt("dayFMTD", model.bcalendar.get(Calendar.DAY_OF_MONTH)+6);
                ((MainActivity) getContext()).navController.navigate(R.id.action_nav_week_to_nav_day,bundle);
            }
        });

        return view;
    }

    private void observe() {
        model.mWeekCalendarList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.pagerCalendar;
                WeekCalendarAdapter adapter = (WeekCalendarAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setCalendarList(objects);
                } else {
                    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.VERTICAL);
                    adapter = new WeekCalendarAdapter(objects, model.typeList, getContext(), height, width);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }
            }
        });
    }
    public void screenSize(){
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();

        width = dm.widthPixels/8;

        height = dm.heightPixels/22;
    }
}