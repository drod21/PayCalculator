package com.drod2169.paycalculator.Storage;

import android.app.Activity;
import android.content.Context;

import java.io.File;

public class FileHelper
        extends Activity {
    static final String KEY_payLengthPrefs = "payLength";
    static final String KEY_payModePrefs = "payMode";
    static final String KEY_payStartPrefs = "payStart";
    static Context baseContext;
    static File externalFile = null;
    static File externalFileFolder = null;
    static final String externalFolder = "Work Log";
    static final String externalPath = "Work Log/worklog.db";
    static File internalFile = null;
    static final String internalFolder = "//data//com.drod2169.paycalculator//databases";
    static final String internalPath = "//data//com.drod2169.paycalculator//databases//worklog.db";
    static int passedMode = 0;
    final String FTYPE = ".db";
    String mChosenFile;
    String[] mFileList;

    public FileHelper(Context mContext) {
        baseContext = mContext;
    }
}
