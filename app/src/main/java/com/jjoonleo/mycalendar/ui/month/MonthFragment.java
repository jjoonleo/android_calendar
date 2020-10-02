package com.jjoonleo.mycalendar.ui.month;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoonleo.mycalendar.MainActivity;
import com.jjoonleo.mycalendar.Parcel;
import com.jjoonleo.mycalendar.R;
import com.jjoonleo.mycalendar.databinding.MonthCalendarBinding;
import com.jjoonleo.mycalendar.ui.month.calendar.CalendarFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class MonthFragment extends Fragment {
    private MonthCalendarBinding binding;
    private MonthViewModel model;
    GregorianCalendar gregorianCalendar;

    private MonthViewModel monthViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_month, container, false);
        View view = binding.getRoot();
        model = ViewModelProviders.of(this).get(MonthViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
        gregorianCalendar = new GregorianCalendar();

        final ViewPager viewPager = binding.viewPager;
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setClipToPadding(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position)
            {
                gregorianCalendar = getDate(viewPager.getCurrentItem());
                model.setTitle(gregorianCalendar.getTimeInMillis());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int i = -500; i < 500; i++) {
            CalendarFragment calendarFragment = new CalendarFragment();
            Bundle bundle = new Bundle();
            GregorianCalendar newGregorianCalendar = new GregorianCalendar(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + i, 1);
            bundle.putInt("Month", newGregorianCalendar.get(Calendar.MONTH));
            bundle.putInt("Year", newGregorianCalendar.get(Calendar.YEAR));
            calendarFragment.setArguments(bundle);
            fragmentAdapter.addItem(calendarFragment);
        }
        viewPager.setId(3);
        viewPager.setCurrentItem(500);

        fragmentAdapter.notifyDataSetChanged();

        return view;
    }

    public GregorianCalendar getDate(int position){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH)+(position-500), 1);
        return gregorianCalendar;
    }

    class FragmentAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments = new ArrayList<>();

        FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        void addItem(Fragment fragment) {
            fragments.add(fragment);
        }
    }
}
