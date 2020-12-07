package com.arnawajuan.rumah_makan_cilik;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {
    private SharedPreferences myShare;

    public SharePref (Context context){
        myShare =context.getSharedPreferences("filename",Context.MODE_PRIVATE);

    }
public void setDarkModeState(Boolean state){
        SharedPreferences.Editor editor =myShare.edit();
        editor.putBoolean("darkTheme",state);
        editor.apply();
}
public Boolean loadDarkModeState(){
        return myShare.getBoolean("darkTheme",false);
}
}
