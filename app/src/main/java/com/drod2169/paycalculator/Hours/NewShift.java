package com.drod2169.paycalculator.Hours;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.drod2169.paycalculator.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by drod2169 on 7/17/14.
 */
public class NewShift extends FragmentActivity
        implements View.OnClickListener {
    private static final String KEY_passPunchIn = "passIn";
    private static final String KEY_passPunchOut = "passOut";
    public static SharedPreferences globalPrefs;
    public static SharedPreferences jobPrefs;
    public static int pickerSelected;
    public static DateFormat dateText = new SimpleDateFormat("EEE, MMM dd", Locale.getDefault());
    public static Calendar calDif;
    public static Calendar calEnd;
    public static Calendar calStart;
    public static DateFormat timeText;
    public static Button endDate;
    public static Button endTime;
    public static Button startDate;
    public static Button startTime;
    public static EditText totalTime;

    private boolean updateMode = false;

    public static Calendar timeRound(Calendar cal) {
        return cal;
    }

    public static void updateHours(String updHours) {
        calEnd.set(Calendar.DAY_OF_WEEK, 0);
        calStart.set(Calendar.DAY_OF_WEEK, 0);
        calDif.setTimeInMillis(calEnd.getTimeInMillis() - calStart.getTimeInMillis());
        startDate.setText(dateText.format(calStart.getTime()));
        endDate.setText(dateText.format(calEnd.getTime()));
        startTime.setText(timeText.format(calStart.getTime()));
        endTime.setText(timeText.format(calEnd.getTime()));
        for (; ; ) {
            totalTime.setText(calDif.getTimeInMillis() / 3600000L + "h " + calDif.get(Calendar.DAY_OF_WEEK) + "m");
            return;
        }
    }

    public NewShift() {

    }

    public void onClick(View view) {
        startTime = (Button) findViewById(R.id.startTime);
        Log.d("Clicked", "Clicked positive");
    }
}
