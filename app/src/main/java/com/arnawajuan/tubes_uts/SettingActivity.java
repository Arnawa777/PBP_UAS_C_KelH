package com.arnawajuan.tubes_uts;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SettingActivity extends AppCompatActivity {
    private Switch switcher;
    ConstraintLayout Switchview;
    private SharePref sharePref;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharePref =new SharePref(this);
        if(sharePref.loadDarkModeState()) {
            setTheme(R.style.SettingDark);
        }else{
            setTheme(R.style.SettingLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Switchview = findViewById(R.id.switchcard);
        switcher = findViewById(R.id.switcher);

        backButton = findViewById(R.id.back_button);

        if(sharePref.loadDarkModeState()){
            switcher.setChecked(true);
        }
        switcher.setClickable(false);
        Switchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!switcher.isChecked()){
                    switcher.setChecked(true);
                    sharePref.setDarkModeState(true);
                    restartApp();
                }else{
                    switcher.setChecked(false);
                    sharePref.setDarkModeState(false);
                    restartApp();
                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void restartApp() {
        Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
        startActivity(intent);
        finish();
    }
}
