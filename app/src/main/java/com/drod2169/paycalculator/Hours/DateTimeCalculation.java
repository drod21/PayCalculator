package com.drod2169.paycalculator.Hours;

import android.content.Context;

import com.drod2169.paycalculator.Constants;
import com.drod2169.paycalculator.Storage.SharedPrefs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeCalculation {
    Context context;
    SharedPrefs pref;

    public DateTimeCalculation(Context paramContext) {
        this.context = paramContext;
        this.pref = new SharedPrefs(this.context);
    }

    private static String pad(int paramInt) {
        if (paramInt >= 10) {
            return String.valueOf(paramInt);
        }
        return "0" + String.valueOf(paramInt);
    }

    public Date getDateFromString(String paramString) {
        try {
            if (this.pref.getDataFromSharedPref(Constants.DATE_FORMAT).equals("dd/mm/yyyy")) {
            }
            for (SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); ; localSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm")) {
                return localSimpleDateFormat.parse(paramString);
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    public String[] getDifference(String paramString1, String paramString2) {
        try {
            String str1 = paramString1.replace("\n", " ");
            String str2 = paramString2.replace("\n", " ");
            String[] arrayOfString = new String[2];
            if (this.pref.getDataFromSharedPref(Constants.DATE_FORMAT).equals("dd/mm/yyyy")) {
            }
            for (SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); ; localSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm")) {
                Date localDate = localSimpleDateFormat.parse(str1);
                long l1 = localSimpleDateFormat.parse(str2).getTime();
                long l2 = localDate.getTime() - l1;
                arrayOfString[0] = String.valueOf(l2);
                int i = (int) (l2 / 86400000L);
                int j = (int) ((l2 - 86400000 * i) / 3600000L);
                int k = (int) (l2 - 86400000 * i - 3600000 * j) / 60000;
                arrayOfString[1] = (pad(j + i * 24) + ":" + pad(k));
                return arrayOfString;
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    public Date getMinFromString(String paramString) {
        try {
            if (this.pref.getDataFromSharedPref(Constants.DATE_FORMAT).equals("dd/mm/yyyy")) {
            }
            for (SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); ; localSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy")) {
                return localSimpleDateFormat.parse(paramString);
            }
        } catch (Exception localException) {
            localException.printStackTrace();
            return null;
        }
    }
}