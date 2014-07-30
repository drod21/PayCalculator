package com.drod2169.paycalculator.Employees;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.drod2169.paycalculator.R;

public class EmpFragmentActivity extends FragmentActivity implements EmpDialogFragment.EditNameDialogListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_name);
        showEditDialog();
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        EmpDialogFragment editNameDialog = new EmpDialogFragment();
        editNameDialog.show(fm, "fragment_edit_name");
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
    }
}

