package com.arnawajuan.rumah_makan_cilik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arnawajuan.rumah_makan_cilik.makanan.MenuMakanan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    LinearLayout menumak, reserv, geo, setting, profile;

    private SharePref sharePref;
    LinearLayout logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharePref = new SharePref(this);
        if(sharePref.loadDarkModeState()){
            setTheme(R.style.AppThemeDark);
        }else{
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menumak = findViewById(R.id.cardMenu);
        reserv = findViewById(R.id.cardReservation);
        geo = findViewById(R.id.cardLocation);
        profile = findViewById(R.id.cardProfile);
        setting = findViewById(R.id.cardSetting);
        logout = findViewById(R.id.cardLogout);


        menumak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuMakanan();

            }
        });

        reserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReservation();

            }
        });


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openSetting();

            }
        });

        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGeolocation();

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();

                finish();
                startActivity(new Intent(getApplicationContext(), UserLogin.class));
                finish();
            }
        });


        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O)

        {
            String CHANNEL_ID = "Channel 1";
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().

                subscribeToTopic("promo")
                .

                        addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete (@NonNull Task < Void > task) {
                                String mag = "Successful";
                                if (!task.isSuccessful()) {
                                    mag = "failed";
                                }
                                Toast.makeText(MainActivity.this, mag, Toast.LENGTH_SHORT).show();
                            }
                        });

    }

    public void openMenuMakanan() {
        Intent intent = new Intent(this, MenuMakanan.class);
        startActivity(intent);
    }

    public void openReservation() {
        Intent intent = new Intent(this, ReservationActivity.class);
        startActivity(intent);
    }

    public void openProfile() {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }

    public void openGeolocation() {
        Intent intent = new Intent(this, GeolocationActivity.class);
        startActivity(intent);
    }


    public void openSetting() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }



}