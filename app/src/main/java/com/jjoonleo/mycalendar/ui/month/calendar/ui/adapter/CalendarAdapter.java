package com.jjoonleo.mycalendar.ui.month.calendar.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jjoonleo.mycalendar.MainActivity;
import com.jjoonleo.mycalendar.R;
import com.jjoonleo.mycalendar.databinding.CalendarHeaderBinding;
import com.jjoonleo.mycalendar.databinding.DayItemBinding;
import com.jjoonleo.mycalendar.databinding.EmptyBinding;
import com.jjoonleo.mycalendar.ui.month.calendar.ui.viewmodel.CalendarHeaderViewModel;
import com.jjoonleo.mycalendar.ui.month.calendar.ui.viewmodel.CalendarViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


public class CalendarAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 0;
    private final int EMPTY_TYPE = 1;
    private final int DAY_TYPE = 2;
    int height, width;
    Context context;

    private List<Object> mCalendarList;
    private ArrayList<Integer> mType = new ArrayList<>();

    public CalendarAdapter(List<Object> calendarList, ArrayList<Integer> type, Context context, int height) {
        mCalendarList = calendarList;
        mType = type;
        this.context = context;
        this.height= height;
    }

    public void setCalendarList(List<Object> calendarList) {
        mCalendarList = calendarList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) { //뷰타입 나누기
        int item = mType.get(position);
        if (item == 0) {
            return EMPTY_TYPE; // 비어있는 일자 타입
        } else{
            return DAY_TYPE; // 일자 타입
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) { // 날짜 타입
            CalendarHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_calendar_header, parent, false);
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) binding.getRoot().getLayoutParams();
            params.setFullSpan(true); //Span을 하나로 통합하기
            binding.getRoot().setLayoutParams(params);
            return new HeaderViewHolder(binding);
        } else if (viewType == EMPTY_TYPE) { //비어있는 일자 타입
            EmptyBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day_empty, parent, false);
            return new EmptyViewHolder(binding);
        }
        DayItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day, parent, false);// 일자 타입
        return new DayViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if(viewType != HEADER_TYPE){
            viewHolder.itemView.getLayoutParams().height = height;

            viewHolder.itemView.requestLayout(); // 변경 사항 적용
        }


        if (viewType == HEADER_TYPE) { //날짜 타입 꾸미기
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            CalendarHeaderViewModel model = new CalendarHeaderViewModel();
            if (item instanceof Long) {
                model.setHeaderDate((Long) item);
            }
            holder.setViewModel(model);
        } else if (viewType == EMPTY_TYPE) { //비어있는 날짜 타입 꾸미기
            EmptyViewHolder holder = (EmptyViewHolder) viewHolder;
            CalendarViewModel model = new CalendarViewModel();
            Object item = mCalendarList.get(position);
            model.setCalendar((Calendar) item);
            holder.setViewModel(model);
        } else if (viewType == DAY_TYPE) { // 일자 타입 꾸미기

            Object item = mCalendarList.get(position);
            DayViewHolder holder = (DayViewHolder) viewHolder;
            CalendarViewModel model = new CalendarViewModel();
            if (item instanceof Calendar) {
                model.setCalendar((Calendar) item);
            }
            RecyclerView recyclerView = holder.setViewModel(model);
            model.makeButton(recyclerView, context);

            Button button = holder.getButton();
            final GregorianCalendar gregorianCalendar = (GregorianCalendar)item;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("yearFMTD", gregorianCalendar.get(Calendar.YEAR));
                    bundle.putInt("monthFMTD", gregorianCalendar.get(Calendar.MONTH));
                    bundle.putInt("dayFMTD", gregorianCalendar.get(Calendar.DAY_OF_MONTH));
                    ((MainActivity)context).navController.navigate(R.id.action_nav_month_to_nav_day,bundle);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mCalendarList != null) {
            return mCalendarList.size();
        }
        return 0;
    }


    private class HeaderViewHolder extends RecyclerView.ViewHolder { //날짜 타입 ViewHolder
        private CalendarHeaderBinding binding;

        private HeaderViewHolder(@NonNull CalendarHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setViewModel(CalendarHeaderViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }


    private class EmptyViewHolder extends RecyclerView.ViewHolder { // 비어있는 요일 타입 ViewHolder
        private EmptyBinding binding;

        private EmptyViewHolder(@NonNull EmptyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setViewModel(CalendarViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }

    }

    private class DayViewHolder extends RecyclerView.ViewHolder {// 요일 타입 ViewHolder
        private DayItemBinding binding;
        RecyclerView recyclerView;
        Button button;

        private  DayViewHolder(@NonNull DayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private RecyclerView setViewModel(CalendarViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
            this.recyclerView = binding.itemRecyclerView;
            return recyclerView;
        }
        public Button getButton(){
            button = binding.button;
            return button;
        }
    }
}
