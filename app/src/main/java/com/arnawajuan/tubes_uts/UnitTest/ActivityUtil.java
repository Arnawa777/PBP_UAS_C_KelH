package com.arnawajuan.tubes_uts.UnitTest;

import android.content.Context;
import android.content.Intent;

import com.arnawajuan.tubes_uts.MainActivity;
import com.arnawajuan.tubes_uts.UserLogin;

public class ActivityUtil {
    private Context context;

    public ActivityUtil(Context context) {
        this.context = context;
    }

    public void startUserLogin() {
        context.startActivity(new Intent(context, UserLogin.class));
    }
}