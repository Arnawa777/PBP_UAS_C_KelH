package com.arnawajuan.tubes_uts.UnitTest;

import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.arnawajuan.tubes_uts.MainActivity;
import com.arnawajuan.tubes_uts.R;
import com.arnawajuan.tubes_uts.RegisterUser;
import com.arnawajuan.tubes_uts.UserLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserRegister extends AppCompatActivity implements RegisterView {
    Button registerBtn;
    TextView btnSignIn;
    TextInputLayout inputLayoutName,inputLayoutEmail,inputLayoutPhone, inputLayoutPassword;
    TextInputEditText inputName,inputEmail,inputPhone, inputPassword;
    RegisterPresenter presenter;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        getWindow().setEnterTransition(null);
        getWindow().setExitTransition(null);

        registerBtn = findViewById(R.id.register);
        btnSignIn = findViewById(R.id.signIn);
        inputLayoutName = findViewById(R.id.inputLayoutName);
        inputLayoutEmail = findViewById(R.id.inputLayoutEmail);
        inputLayoutPhone = findViewById(R.id.inputLayoutPhone);
        inputLayoutPassword = findViewById(R.id.inputLayoutPassword);
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputNumber);
        inputPassword = findViewById(R.id.inputPassword);

        presenter = new RegisterPresenter(this, new RegisterService());
        btnSignIn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                presenter.onRegisterClicked();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(UserRegister.this, UserLogin.class);
                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View, String>(registerBtn, "main_button");
                pairs[1] = new Pair<View, String>(btnSignIn, "sub_button");
                pairs[2] = new Pair<View, String>(inputLayoutName, "name");
                pairs[3] = new Pair<View, String>(inputLayoutEmail, "email");
                pairs[4] = new Pair<View, String>(inputLayoutPhone, "phone");
                pairs[5] = new Pair<View, String>(inputLayoutPassword, "password");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(UserRegister.this, pairs);
                startActivity(intent, options.toBundle());

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1000);
            }
        });
        }
    @Override
    public String getName() {
        return inputName.getText().toString();
    }

    @Override
    public void showNameError(String message) {
        inputLayoutName.setError(message);
    }
    @Override
    public String getEmail() {
        return inputName.getText().toString();
    }

    @Override
    public void showEmailError(String message) {
        inputLayoutEmail.setError(message);
    }

    @Override
    public String getPhone() {
        return inputPhone.getText().toString();
    }

    @Override
    public void showPhoneError(String message) {
        inputLayoutPhone.setError(message);
    }

    @Override
    public String getPassword() {
        return inputPassword.getText().toString();
    }

    @Override
    public void showPasswordError(String message) {
        inputLayoutPassword.setError(message);
    }

    @Override
    public void startUserLogin() {
        new ActivityUtil(this).startUserLogin();
    }

    @Override
    public void showRegisterError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegisterMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    }
