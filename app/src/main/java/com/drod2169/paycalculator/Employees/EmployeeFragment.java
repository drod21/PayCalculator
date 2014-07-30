package com.drod2169.paycalculator.Employees;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.drod2169.paycalculator.MainActivity;
import com.drod2169.paycalculator.R;

/**
 * Created by drod2169 on 7/14/14.
 */
public class EmployeeFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    View rootView;
    EditText editText;

    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EmployeeFragment newInstance(int sectionNumber) {
        EmployeeFragment fragment = new EmployeeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public EmployeeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_employee, container, false);
        editText = (EditText) rootView.findViewById(R.id.employee);
        editText.requestFocus();
        if (editText != null) {
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        handled = true;
                    }
                    return handled;
                }
            });
        }
        return rootView;
    }

    void showDialog() {

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        EmpDialogFragment newFragment = EmpDialogFragment.newInstance();
        newFragment.showDialog();
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
