package com.drod2169.paycalculator.Hours;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.drod2169.paycalculator.Constants;
import com.drod2169.paycalculator.R;
import com.drod2169.paycalculator.Storage.SharedPrefs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by drod2169 on 7/19/14.
 */
public class NewTimes extends Activity implements OnClickListener {

    public static final int DATE_DIALOG_ID = 0;
    public static final int DATE_DIALOG_ID2 = 2;
    public static final int TIME_DIALOG_ID = 1;
    public static final int TIME_DIALOG_ID2 = 3;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mMonth;
    private int mYear;
    private Button btn_Done;
    private Button btn_EndDay;
    private Button btn_EndTime;
    private Button btn_StartDay;
    private Button btn_StartTime;
    private JSONArray jsonArray;
    private SharedPrefs pref;
    ArrayList<SalaryItem> ls_SavedItems = new ArrayList();

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int dayOfMonth, int monthOfYear, int year) {
            if (NewTimes.this.pref.getDataFromSharedPref(Constants.DATE_FORMAT).equals("dd/mm/yyyy")) {
                NewTimes.this.btn_StartDay.setText(String.valueOf(year) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(dayOfMonth));
            }
            while (NewTimes.this.pref.getDataFromSharedPref(Constants.DATE_FORMAT).equals("dd/mm/yyyy")) {
                NewTimes.this.btn_EndDay.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year));
                NewTimes.this.btn_StartDay.setText(String.valueOf(year + 1) + "/" + String.valueOf(monthOfYear) + "/" + String.valueOf(dayOfMonth));
                return;
            }
            NewTimes.this.btn_EndDay.setText(String.valueOf(year + 1) + "/" + String.valueOf(monthOfYear) + "/" + String.valueOf(dayOfMonth));
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker mDatePicker, int mDay, int mMonth, int mYear) {
            if (NewTimes.this.pref.getDataFromSharedPref(Constants.DATE_FORMAT).equals("dd/mm/yyyy")) {
                NewTimes.this.btn_EndDay.setText(String.valueOf(mYear) + "/" + String.valueOf(mMonth + 1) + "/" + String.valueOf(mDay));
                return;
            }
            NewTimes.this.btn_EndDay.setText(String.valueOf(mMonth + 1) + "/" + String.valueOf(mYear) + "/" + String.valueOf(mDay));
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker mTimePicker, int mHour, int mMinute) {
            NewTimes.this.btn_StartTime.setText(NewTimes.pad(mHour) + ":" + NewTimes.pad(mMinute));
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker mTimePicker, int mHour, int mMinute) {
            NewTimes.this.btn_EndTime.setText(NewTimes.pad(mHour) + ":" + NewTimes.pad(mMinute));
        }
    };

    public NewTimes() {

    }

    private void addNewItem() {
        if ((this.btn_EndDay.getText().equals("Date")) || (this.btn_StartDay.getText().equals("Date")) || (this.btn_EndTime.getText().equals("Time")) || (this.btn_StartTime.getText().equals("Time"))) {
            return;
        }
        SalaryItem salaryItem = new SalaryItem();
        salaryItem.id = String.valueOf(1 + this.ls_SavedItems.size());
        salaryItem.start_time = (this.btn_StartDay.getText() + "\n" + this.btn_StartTime.getText());
        salaryItem.end_time = (this.btn_EndDay.getText() + "\n" + this.btn_EndTime.getText());
        String[] arrayOfString = new DateTimeCalculation(this).getDifference(salaryItem.end_time, salaryItem.start_time);
        salaryItem.total = arrayOfString[1];
        salaryItem.total_milli = arrayOfString[0];
        this.ls_SavedItems.add(salaryItem);
        //  this.pref.addJson(new Gson().toJson(this.ls_SavedItems).toString());
        finish();
    }


    public static String pad(int padInt) {
        if (padInt >= 10) {
            return String.valueOf(padInt);
        }
        return "0" + String.valueOf(padInt);
    }

    private void showList() {
        this.ls_SavedItems = new ArrayList();
        if (!this.pref.getDataFromSharedPref(Constants.SHEET_LIST).isEmpty()) {
            try {
                this.jsonArray = new JSONArray(this.pref.getDataFromSharedPref(Constants.SHEET_LIST));
                for (int i = 0; ; i++) {
                    if (i >= this.jsonArray.length()) {
                        return;
                    }
                    final int finalI = i;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                JSONObject localJSONObject = NewTimes.this.jsonArray.getJSONObject(finalI);
                                SalaryItem SalaryItem = new SalaryItem();
                                SalaryItem.id = localJSONObject.getString("id");
                                SalaryItem.start_time = localJSONObject.getString("start_time");
                                SalaryItem.end_time = localJSONObject.getString("end_time");
                                SalaryItem.total = localJSONObject.getString("total");
                                SalaryItem.total_milli = localJSONObject.getString("total_milli");
                                NewTimes.this.ls_SavedItems.add(SalaryItem);
                                return;
                            } catch (Exception localException) {
                                localException.printStackTrace();
                            }
                        }
                    });
                }
            } catch (Exception localException) {
                localException.printStackTrace();
            }
        }
    }

    public void onClick(View mView) {
        if (mView.getId() == R.id.btn_StartDay) {
            showDialog(0);
            return;
        }
        do {
            if (mView.getId() == R.id.btn_StartTime) {
                showDialog(1);
                return;
            }
            if (mView.getId() == R.id.btn_EndDay) {
                showDialog(2);
                return;
            }
            if (mView.getId() == R.id.btn_EndTime) {
                showDialog(3);
                return;
            }
        } while (mView.getId() != R.id.btn_Ok);
        addNewItem();
    }

    protected void onCreate(Bundle mBundle) {
        super.onCreate(mBundle);
        setContentView(R.layout.fragment_hours);
        this.pref = new SharedPrefs(getApplicationContext());
        this.btn_StartDay = ((Button) findViewById(R.id.btn_StartDay));
        this.btn_StartTime = ((Button) findViewById(R.id.btn_StartTime));
        this.btn_EndDay = ((Button) findViewById(R.id.btn_EndDay));
        this.btn_EndTime = ((Button) findViewById(R.id.btn_EndTime));
        this.btn_Done = ((Button) findViewById(R.id.btn_Ok));
        this.btn_StartDay.setOnClickListener(this);
        this.btn_StartTime.setOnClickListener(this);
        this.btn_EndDay.setOnClickListener(this);
        this.btn_EndTime.setOnClickListener(this);
        this.btn_Done.setOnClickListener(this);
    }

    protected Dialog onCreateDialog(int mDialog) {
        Calendar localCalendar = Calendar.getInstance();
        this.mYear = localCalendar.get(Calendar.YEAR);
        this.mMonth = localCalendar.get(Calendar.MONTH);
        this.mDay = localCalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        this.mHour = localCalendar.get(Calendar.HOUR_OF_DAY);
        this.mMinute = localCalendar.get(Calendar.MINUTE);
        return null;
    }
        /*switch (mDialog) {
            default:
            case 0: return null;
            case 1: new DatePickerDialog(this, this.mDateSetListener, this.mYear, this.mMonth, this.mDay);

            case 2: new TimePickerDialog(this, this.mTimeSetListener, this.mHour, this.mMinute, false);
            break;
                for (; ;) {
                    return null;
                    return new DatePickerDialog(this, this.mDateSetListener, this.mYear, this.mMonth, this.mDay);
                    return new TimePickerDialog(this, this.mTimeSetListener, this.mHour, this.mMinute, false);
                    try {
                        DatePickerDialog localDatePickerDialog = new DatePickerDialog(this, this.mDateSetListener2, this.mYear, this.mMonth, this.mDay);
                        return localDatePickerDialog;
                    }
                    catch (Exception localException) {
                        localException.printStackTrace();
                    }
                }
        }
        return new TimePickerDialog(this, this.mTimeSetListener2, this.mHour, this.mMinute, false);
    }*/

    protected void onResume() {
        super.onResume();
        showList();
    }

    public void onStart() {
        super.onStart();
        //EasyTracker.getInstance(this).activityStart(this);
    }

    public void onStop() {
        super.onStop();
        //EasyTracker.getInstance(this).activityStop(this);
    }

}
