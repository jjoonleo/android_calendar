package com.jjoonleo.mycalendar.ui.week;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoonleo.mycalendar.R;
import com.jjoonleo.mycalendar.databinding.WeekCalendarBinding;
import com.jjoonleo.mycalendar.ui.week.calendar.WeekCalendarFragment;

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

public class WeekFragment extends Fragment {
    private WeekCalendarBinding binding;
    private WeekViewModel model;
    GregorianCalendar gregorianCalendar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_week, container, false);
        View view = binding.getRoot();
        model = ViewModelProviders.of(this).get(WeekViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
        gregorianCalendar = new GregorianCalendar();
        model.initCalendarList(new GregorianCalendar());

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
                model.setTitle(gregorianCalendar);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i = -500; i < 500; i++) {
            WeekCalendarFragment weekCalendarFragment = new WeekCalendarFragment();
            Bundle bundle = new Bundle();
            GregorianCalendar newGregorianCalendar = new GregorianCalendar(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DATE)+(i*7));
            bundle.putInt("month", newGregorianCalendar.get(Calendar.MONTH));
            bundle.putInt("year", newGregorianCalendar.get(Calendar.YEAR));
            bundle.putInt("day", newGregorianCalendar.get(Calendar.DATE));
            weekCalendarFragment.setArguments(bundle);
            fragmentAdapter.addItem(weekCalendarFragment);

        }
        viewPager.setId(2);
        viewPager.setCurrentItem(500);

        fragmentAdapter.notifyDataSetChanged();

        return view;
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

    public GregorianCalendar getDate(int position) {
        GregorianCalendar gregorianCalendar  = new GregorianCalendar();
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DAY_OF_MONTH)-gregorianCalendar.get(Calendar.DAY_OF_WEEK)+1+((position-500)*7));
        return gregorianCalendar;
    }

}
