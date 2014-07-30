package com.drod2169.paycalculator.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.drod2169.paycalculator.Constants;

public class SharedPrefs {
    SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    public SharedPrefs(Context mContext) {
        this.preferences = mContext.getSharedPreferences("Pref", 0);
        this.editor = this.preferences.edit();
    }

    public void addJson(String mString) {
        this.editor.putString(Constants.SHEET_LIST, mString);
        this.editor.commit();
    }

    public String getDataFromSharedPref(String mDataFromPref) {
        try {
            String str = this.preferences.getString(mDataFromPref, "");
            return str;
        } catch (Exception localException) {
            Log.d("Exception in SharedPreferences", localException.getMessage());
        }
        return "";
    }

    public void saveDataInSharedPref(String mSavedData1, String mSavedData2) {
        this.editor.putString(mSavedData1, mSavedData2);
        this.editor.commit();
    }
}
