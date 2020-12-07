package com.arnawajuan.rumah_makan_cilik.UnitTest;

import android.content.Context;
import android.content.Intent;

import com.arnawajuan.rumah_makan_cilik.UserLogin;

public class ActivityUtil {
    private Context context;

    public ActivityUtil(Context context) {
        this.context = context;
    }

    public void startUserLogin() {
        context.startActivity(new Intent(context, UserLogin.class));
    }
}