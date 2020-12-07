package com.arnawajuan.tubes_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.arnawajuan.tubes_uts.makanan.MenuMakanan;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;

    Animation topAnim, bottomAnim;
    ImageView image;
    TextView namaRestaurant,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Animations
        topAnim= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.bottom_animation);


        //Hooks
        image=findViewById(R.id.logo);
        namaRestaurant = findViewById(R.id.namaRestaurant);
        slogan = findViewById(R.id.slogan);

        image.setAnimation(topAnim);
        namaRestaurant.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, UserLogin.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}