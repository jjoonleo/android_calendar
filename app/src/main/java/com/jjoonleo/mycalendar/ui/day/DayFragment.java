package com.jjoonleo.mycalendar.ui.day;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjoonleo.mycalendar.MainActivity;
import com.jjoonleo.mycalendar.R;
import com.jjoonleo.mycalendar.databinding.DayCalendarBinding;
import com.jjoonleo.mycalendar.ui.day.calendar.DayCalendarFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DayFragment extends Fragment {
    private DayCalendarBinding binding;
    private DayViewModel model;
    GregorianCalendar gregorianCalendar;
    MainActivity mainActivity = new MainActivity();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day, container, false);
        View view = binding.getRoot();
        model = ViewModelProviders.of(this).get(DayViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
        if (getArguments() != null) {
            gregorianCalendar = new GregorianCalendar(getArguments().getInt("yearFMTD"), getArguments().getInt("monthFMTD"), getArguments().getInt("dayFMTD"), 0, 0, 0);
        }else{
            gregorianCalendar =new GregorianCalendar();
        }
        model.initCalendarList(gregorianCalendar);

        final ViewPager viewPager = binding.viewPager;
        DayFragment.FragmentAdapter fragmentAdapter = new DayFragment.FragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setClipToPadding(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position)
            {
                model.setTitle(getDate(viewPager.getCurrentItem()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i = -500; i < 500; i++) {
            DayCalendarFragment dayCalendarFragment = new DayCalendarFragment();
            Bundle bundle = new Bundle();
            GregorianCalendar newGregorianCalendar = new GregorianCalendar(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DATE)+i);
            bundle.putInt("month", newGregorianCalendar.get(Calendar.MONTH));
            bundle.putInt("year", newGregorianCalendar.get(Calendar.YEAR));
            bundle.putInt("day", newGregorianCalendar.get(Calendar.DATE));
            dayCalendarFragment.setArguments(bundle);
            fragmentAdapter.addItem(dayCalendarFragment);

        }
        viewPager.setId(1);
        viewPager.setCurrentItem(500);

        fragmentAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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
        GregorianCalendar newGregorianCalendar = new GregorianCalendar(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DAY_OF_MONTH)+(position-500));
        return newGregorianCalendar;
    }

}
