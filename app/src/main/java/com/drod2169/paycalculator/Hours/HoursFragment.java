package com.drod2169.paycalculator.Hours;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drod2169.paycalculator.MainActivity;
import com.drod2169.paycalculator.R;

public class HoursFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    NewTimes newTimes;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HoursFragment newInstance(int sectionNumber) {
        HoursFragment fragment = new HoursFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HoursFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hours, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
