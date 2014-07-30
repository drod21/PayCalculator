package com.drod2169.paycalculator.Employees;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.drod2169.paycalculator.R;

/**
 * Created by drod2169 on 7/28/14.
 */
public class EmpDialogFragment extends DialogFragment implements OnEditorActionListener {

    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    static EmpDialogFragment newInstance() {
        return new EmpDialogFragment();
    }

    private EditText mEditText;

    public EmpDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        getDialog().setTitle("Employee Name");

        // Show soft keyboard automatically
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEditText.setOnEditorActionListener(this);

        return view;
    }

    void showDialog() {
        // Create the fragment and show it as a dialog.
        EmpDialogFragment newFragment = EmpDialogFragment.newInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener activity = (EditNameDialogListener) getActivity();
            activity.onFinishEditDialog(mEditText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }
}

