package com.arnawajuan.tubes_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arnawajuan.tubes_uts.makanan.MenuMakanan;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    LinearLayout menumak, geo;
    TextView text;
    BottomNavigationView bottomNavigationView;
    View view;

    LinearLayout logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menumak = findViewById(R.id.cardMenu);
        geo = findViewById(R.id.cardLocation);
        text = findViewById(R.id.textRecommend);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        view = findViewById(R.id.viewDivider);

        menumak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuMakanan();

            }
        });

        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGeolocation();

            }
        });

        logout = findViewById(R.id.cardLogout);

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

    }

    public void openMenuMakanan() {
        Intent intent = new Intent(this, MenuMakanan.class);
        startActivity(intent);
    }

    public void openGeolocation() {
        Intent intent = new Intent(this, GeolocationActivity.class);
        startActivity(intent);
    }

    public void callLoginFromLogout(View view) {
        startActivity(new Intent(getApplicationContext(), UserLogin.class));
        finish();
    }
}