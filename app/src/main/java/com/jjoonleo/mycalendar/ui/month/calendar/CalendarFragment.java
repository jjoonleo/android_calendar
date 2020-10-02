package com.jjoonleo.mycalendar.ui.month.calendar;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoonleo.mycalendar.R;
import com.jjoonleo.mycalendar.databinding.CalendarFrBinding;
import com.jjoonleo.mycalendar.ui.month.calendar.ui.adapter.CalendarAdapter;
import com.jjoonleo.mycalendar.ui.month.calendar.ui.viewmodel.CalendarListViewModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class CalendarFragment extends Fragment {
    private CalendarFrBinding binding;
    private CalendarListViewModel model;
    GregorianCalendar gregorianCalendar;
    int width, height;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false);
        View view = binding.getRoot();
        model = ViewModelProviders.of(this).get(CalendarListViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
        gregorianCalendar = new GregorianCalendar();
        if (getArguments() != null) {
            Bundle args = getArguments();
            gregorianCalendar.set(args.getInt("Year"),args.getInt("Month"),1);
            model.initCalendarList(gregorianCalendar);
        }
        screenSize();
        observe();

        if (model != null) {
            model.initCalendarList(gregorianCalendar);
        }
        return view;
    }

    private void observe() {
        model.mCalendarList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.pagerCalendar;
                CalendarAdapter adapter = (CalendarAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setCalendarList(objects);
                } else {
                    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
                    adapter = new CalendarAdapter(objects, model.typeList, getContext(), height);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                    if (model.mCenterPosition >= 0) {
                        view.scrollToPosition(model.mCenterPosition);
                    }
                }
            }
        });
    }
    public void screenSize(){
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();

        width = dm.widthPixels/4;

        height = dm.heightPixels / 156 *22;
    }
}
