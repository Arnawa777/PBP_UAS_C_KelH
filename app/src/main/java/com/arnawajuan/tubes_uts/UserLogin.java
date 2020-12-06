package com.arnawajuan.tubes_uts;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.arnawajuan.tubes_uts.api.ApiClient;
import com.arnawajuan.tubes_uts.api.ApiInterface;
import com.arnawajuan.tubes_uts.api.UserResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLogin extends AppCompatActivity {
    private String CHANNEL_ID = "Channel 1";

    TextView btnSignUp;

    private TextInputEditText inputEmail, inputPass;
    Button loginBtn;
    CheckBox remember;
//    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPassword);
        remember = findViewById(R.id.rememberMe);
//        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(UserLogin.this);
        btnSignUp = findViewById(R.id.signUp);
        loginBtn = findViewById(R.id.btnLogin);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")) {
            Intent intent = new Intent(UserLogin.this, MainActivity.class);
            startActivity(intent);
        } else if (checkbox.equals("false")) {
            Toast.makeText(this, "Please Sign In", Toast.LENGTH_SHORT).show();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, RegisterUser.class));
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(UserLogin.this, "Checked", Toast.LENGTH_SHORT).show();
                } else if (!compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(UserLogin.this, "Unhecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void checkCredentials() {
        String email = inputEmail.getText().toString();
        String pass = inputPass.getText().toString();

        if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Email Invalid");
        } else if (pass.isEmpty() || pass.length() < 6) {
            showError(inputPass, "Password must be 6 character");
        } else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please Wait");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<UserResponse> userDAOCall = apiService.loginUser(email,pass);

            userDAOCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.body().getMessage().equalsIgnoreCase("Authenticated" )){
                            mLoadingBar.dismiss();
//                            UserDAO user = response.body().getUsers().get(0);
                            Intent i = new Intent(UserLogin.this, MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            createNotificationChannel();
                            addNotification();
//                            i.putExtra("id",user.getId());
//                            i.putExtra("name",user.getName());
//                            i.putExtra("nim",user.getEmail());
//                            i.putExtra("prodi",user.getPhone_number());
                            startActivity(i);
                            finish();
                            Toast.makeText(UserLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(UserLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    //failure
                }
            });


//            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(UserLogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
//                        mLoadingBar.dismiss();
//                        Intent intent = new Intent(UserLogin.this, MainActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        createNotificationChannel();
//                        addNotification();
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(UserLogin.this, "Email or Password not Valid", Toast.LENGTH_LONG).show();
//                        mLoadingBar.dismiss();
//                    }
//                }
//            });
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    //Notifikasi
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Welcome")
                .setContentText("いらっしゃいませ (ɔ◔‿◔)ɔ ♥")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}


