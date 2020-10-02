package com.jjoonleo.mycalendar.ui.week.calendar.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jjoonleo.mycalendar.R;

import com.jjoonleo.mycalendar.databinding.WeekEmptyBinding;
import com.jjoonleo.mycalendar.databinding.WeekEventBinding;
import com.jjoonleo.mycalendar.databinding.WeekHeaderBinding;

import com.jjoonleo.mycalendar.ui.month.calendar.ui.viewmodel.EmptyViewModel;
import com.jjoonleo.mycalendar.ui.utils.Keys;
import com.jjoonleo.mycalendar.ui.week.calendar.ui.viewmodel.WeekEmptyViewModel;
import com.jjoonleo.mycalendar.ui.week.calendar.ui.viewmodel.WeekEventViewModel;
import com.jjoonleo.mycalendar.ui.week.calendar.ui.viewmodel.WeekHeaderViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class WeekCalendarAdapter extends RecyclerView.Adapter{
        private final int HEADER_TYPE = 1;
        private final int EMPTY_TYPE = 0;
        private final int EMPTY_HEADER_TYPE = 2;
        Context context;
        int height, width;

        private List<Object> mCalendarList;
        private ArrayList<Integer> mType = new ArrayList<>();

        public WeekCalendarAdapter(List<Object> calendarList, ArrayList<Integer> type, Context context, int height, int width) {
            mCalendarList = calendarList;
            mType = type;
            this.context = context;
            this.height = height;
            this.width = width;
        }

        public void setCalendarList(List<Object> calendarList) {
            mCalendarList = calendarList;
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) { //뷰타입 나누기
            int item = mType.get(position);
            if(item == 1){
                return HEADER_TYPE;
            }
            else if (item == 0) {
                return EMPTY_TYPE; // 비어있는 일자 타입
            } else if(item == -1) {
                return -1;
            }
            else if(item == EMPTY_HEADER_TYPE){
                return EMPTY_HEADER_TYPE;
            }
            else{
                return item; // 일자 타입
            }
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == HEADER_TYPE) { // 날짜 타입
                WeekHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.week_header, parent, false);
                return new HeaderViewHolder(binding);
            } else if (viewType == EMPTY_TYPE) { //비어있는 일자 타입
                WeekEmptyBinding binding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.week_empty, parent, false);
                return new EmptyViewHolder(binding);
            }else if(viewType > 1){
                WeekEventBinding binding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.week_event, parent, false);// 일자 타입
                return new DayViewHolder(binding);}
            else{
                WeekEmptyBinding binding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.week_empty, parent, false);
                return new EmptyHolder(binding);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            int viewType = getItemViewType(position);

            if (viewType == HEADER_TYPE) { //날짜 타입 꾸미기
                viewHolder.itemView.getLayoutParams().height = height;
                viewHolder.itemView.getLayoutParams().width = width;
                viewHolder.itemView.requestLayout(); // 변경 사항 적용

                HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
                Object item = mCalendarList.get(position);
                WeekHeaderViewModel model = new WeekHeaderViewModel();
                if (item != Keys.EMPTY) {
                    model.setHeader((Calendar) item);
                }
                holder.setViewModel(model);
            } else if (viewType == EMPTY_TYPE) { //비어있는 날짜 타입 꾸미기
                viewHolder.itemView.getLayoutParams().height = height;
                viewHolder.itemView.getLayoutParams().width = width;
                viewHolder.itemView.requestLayout(); // 변경 사항 적용

                EmptyViewHolder holder = (EmptyViewHolder) viewHolder;
                WeekEmptyViewModel model = new WeekEmptyViewModel();
                Object item = mCalendarList.get(position);
                //model.setCalendar((Calendar) item);
                holder.setViewModel(model);
            } else if (viewType >1) { // 일자 타입 꾸미기
                viewHolder.itemView.getLayoutParams().height = height*(viewType-1);
                viewHolder.itemView.getLayoutParams().width = width;
                viewHolder.itemView.requestLayout(); // 변경 사항 적용

                DayViewHolder holder = (DayViewHolder) viewHolder;
                Object item = mCalendarList.get(position);
                WeekEventViewModel model = new WeekEventViewModel();
                if (item instanceof Calendar) {
                    //model.setCalendar((Calendar) item);
                }
                holder.setViewModel(model);
            }
            else{
                viewHolder.itemView.getLayoutParams().height = 0;
                viewHolder.itemView.getLayoutParams().width = 0;
                viewHolder.itemView.requestLayout(); // 변경 사항 적용
                EmptyHolder holder = (EmptyHolder) viewHolder;
                Object item = mCalendarList.get(position);
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
            private WeekHeaderBinding binding;

            private HeaderViewHolder(@NonNull WeekHeaderBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            private void setViewModel(WeekHeaderViewModel model) {
                binding.setModel(model);
                binding.executePendingBindings();
            }
        }


        private class EmptyViewHolder extends RecyclerView.ViewHolder { // 비어있는 요일 타입 ViewHolder
            private WeekEmptyBinding binding;

            private EmptyViewHolder(@NonNull WeekEmptyBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            private void setViewModel(WeekEmptyViewModel model) {
                binding.setModel(model);
                binding.executePendingBindings();
            }

        }

        private class DayViewHolder extends RecyclerView.ViewHolder {// 요일 타입 ViewHolder
            private WeekEventBinding binding;

            private  DayViewHolder(@NonNull WeekEventBinding binding) {
                super(binding.getRoot());
                this.binding = binding;

            }

            private void setViewModel(WeekEventViewModel model) {
                binding.setModel(model);
                binding.executePendingBindings();
            }
        }
    private class EmptyHolder extends RecyclerView.ViewHolder {// 요일 타입 ViewHolder
        private WeekEmptyBinding binding;

        private  EmptyHolder(@NonNull WeekEmptyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
