package com.jjoonleo.mycalendar.ui.day.calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jjoonleo.mycalendar.R;
import com.jjoonleo.mycalendar.databinding.DayEmptyBinding;
import com.jjoonleo.mycalendar.databinding.DayEventBinding;
import com.jjoonleo.mycalendar.databinding.DayHeaderBinding;
import com.jjoonleo.mycalendar.ui.day.calendar.ui.viewmodel.DayEmptyViewModel;
import com.jjoonleo.mycalendar.ui.day.calendar.ui.viewmodel.DayEventViewModel;
import com.jjoonleo.mycalendar.ui.day.calendar.ui.viewmodel.DayHeaderViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DayCalendarAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 1;
    private final int EMPTY_TYPE = 0;
    private final int EMPTY = -3;
    private final int EMPTY_HEADER_TYPE = -2;
    Context context;
    int height, width;

    private List<Object> mCalendarList;
    private ArrayList<Integer> mType = new ArrayList<>();

    public DayCalendarAdapter(List<Object> calendarList, ArrayList<Integer> type, Context context, int height, int width) {
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
        }else if(item == EMPTY_HEADER_TYPE){
            return EMPTY_HEADER_TYPE;
        }
        else if(item == EMPTY){
            return EMPTY;
        }else{
            return item; // 일자 타입
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) { // 날짜 타입
            DayHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.day_header, parent, false);
            return new DayCalendarAdapter.HeaderViewHolder(binding);
        } else if (viewType == EMPTY_TYPE) { //비어있는 일자 타입
            DayEmptyBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.day_empty, parent, false);
            return new DayCalendarAdapter.EmptyViewHolder(binding);
        }else if(viewType > 1){
            DayEventBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.day_event, parent, false);// 일자 타입
            return new DayCalendarAdapter.DayViewHolder(binding);}
        else if(viewType == EMPTY_HEADER_TYPE){
            DayEmptyBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.day_empty, parent, false);
            return new DayCalendarAdapter.EmptyViewHolder(binding);
        }
        else{
            DayEmptyBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.day_empty, parent, false);
            return new DayCalendarAdapter.EmptyHolder(binding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == HEADER_TYPE) { //날짜 타입 꾸미기
            viewHolder.itemView.getLayoutParams().height = height;
            viewHolder.itemView.getLayoutParams().width = width;
            viewHolder.itemView.requestLayout(); // 변경 사항 적용

            DayCalendarAdapter.HeaderViewHolder holder = (DayCalendarAdapter.HeaderViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            DayHeaderViewModel model = new DayHeaderViewModel();
            if (item instanceof Calendar) {
                model.setHeader((Calendar) item);
            }
            holder.setViewModel(model);
        } else if (viewType == EMPTY_TYPE) { //비어있는 날짜 타입 꾸미기
            viewHolder.itemView.getLayoutParams().height = height;
            viewHolder.itemView.getLayoutParams().width = width*2;
            viewHolder.itemView.requestLayout(); // 변경 사항 적용

            DayCalendarAdapter.EmptyViewHolder holder = (DayCalendarAdapter.EmptyViewHolder) viewHolder;
            DayEmptyViewModel model = new DayEmptyViewModel();
            Object item = mCalendarList.get(position);
            //model.setCalendar((Calendar) item);
            holder.setViewModel(model);
        } else if (viewType >1) { // 일자 타입 꾸미기
            viewHolder.itemView.getLayoutParams().height = height*(viewType-1);
            viewHolder.itemView.getLayoutParams().width = width*2;
            viewHolder.itemView.requestLayout(); // 변경 사항 적용

            DayCalendarAdapter.DayViewHolder holder = (DayCalendarAdapter.DayViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            DayEventViewModel model = new DayEventViewModel();
            if (item instanceof Calendar) {
                //model.setCalendar((Calendar) item);
            }
            holder.setViewModel(model);
        }else if(viewType == EMPTY_HEADER_TYPE){
            viewHolder.itemView.getLayoutParams().height = height;
            viewHolder.itemView.getLayoutParams().width = width;
            viewHolder.itemView.requestLayout(); // 변경 사항 적용

            DayCalendarAdapter.EmptyViewHolder holder = (DayCalendarAdapter.EmptyViewHolder) viewHolder;
            DayEmptyViewModel model = new DayEmptyViewModel();
            Object item = mCalendarList.get(position);
            //model.setCalendar((Calendar) item);
            holder.setViewModel(model);
        }
        else{
            if(viewType == EMPTY){
                viewHolder.itemView.getLayoutParams().height = height;//길이 때문에 넣은 곳
            }
            else{
                viewHolder.itemView.getLayoutParams().height = 0;//버튼 때메 넣은 곳
            }

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
        private DayHeaderBinding binding;

        private HeaderViewHolder(@NonNull DayHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setViewModel(DayHeaderViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }


    private class EmptyViewHolder extends RecyclerView.ViewHolder { // 비어있는 요일 타입 ViewHolder
        private DayEmptyBinding binding;

        private EmptyViewHolder(@NonNull DayEmptyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setViewModel(DayEmptyViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }

    }

    private class DayViewHolder extends RecyclerView.ViewHolder {// 요일 타입 ViewHolder
        private DayEventBinding binding;

        private  DayViewHolder(@NonNull DayEventBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        private void setViewModel(DayEventViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }
    private class EmptyHolder extends RecyclerView.ViewHolder { // 비어있는 요일 타입 ViewHolder
        private DayEmptyBinding binding;

        private EmptyHolder(@NonNull DayEmptyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
