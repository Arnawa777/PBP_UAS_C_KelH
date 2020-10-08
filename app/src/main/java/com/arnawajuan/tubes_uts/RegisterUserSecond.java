package com.arnawajuan.tubes_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterUserSecond extends AppCompatActivity {

    TextView btnSignIn;
    Button btnRegister;

    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_second);


        btnRegister = findViewById(R.id.btnRegister);
        btnSignIn = findViewById(R.id.signIn);

        //mAuth = FirebaseAuth.getInstance();
      //  mLoadingBar = new ProgressDialog(RegisterUser.this);

    }

    public void callButtonRegister(View view) {

        Intent intent = new Intent(getApplicationContext(), UserLogin.class);

        //Transisition
        Pair[] pairs = new Pair[2];

        pairs[0] = new Pair<View, String>(btnRegister, "transition_register");
        pairs[1] = new Pair<View, String>(btnSignIn, "transition_sign_in");

        //call activity
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterUserSecond.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), UserLogin.class));
        finish();
    }

}