package com.arnawajuan.tubes_uts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arnawajuan.tubes_uts.api.ApiClient;
import com.arnawajuan.tubes_uts.api.ApiInterface;
import com.arnawajuan.tubes_uts.api.UserResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUser extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView btnSignIn;

    private TextInputEditText inputName, inputEmail, inputPass, inputNumber;
    Button btnRegister;
//    private FirebaseAuth mAuth;
//    FirebaseFirestore mStore;
    private ProgressDialog mLoadingBar;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        btnSignIn = findViewById(R.id.signIn);
        btnRegister = findViewById(R.id.register);

        //Input
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPassword);
        inputNumber = findViewById(R.id.inputNumber);


        //Firebase
//        mAuth = FirebaseAuth.getInstance();
//        mStore = FirebaseFirestore.getInstance();
        mLoadingBar = new ProgressDialog(RegisterUser.this);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterUser.this, UserLogin.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });


    }


    private void checkCredentials() {
        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String number = inputNumber.getText().toString();
        String pass = inputPass.getText().toString();
        String checkName = "\\A\\w{3,20}\\z";
        String checkNumber = "\\A\\w{10,13}\\z";

        if (name.isEmpty() || !name.matches(checkName)) {
            showError(inputName, "Name must 3-20 character");
        } else if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Email is not valid");
        } else if (number.isEmpty() || !number.matches(checkNumber)) {
            showError(inputNumber, "Must have 10-13 digit");
        } else if (pass.isEmpty() || pass.length() < 6) {
            showError(inputPass, "Password must be 6 character");
        } else {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait, while check");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<UserResponse> add = apiService.register(name, email, number, pass);

            add.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    Log.i("banana",response.message()+"header : "+ response.headers().toString());
                    Toast.makeText(RegisterUser.this, "Berhasil menambah user", Toast.LENGTH_SHORT).show();
                    mLoadingBar.dismiss();
                    onBackPressed();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(RegisterUser.this, "Gagal menambah user", Toast.LENGTH_SHORT).show();
                    mLoadingBar.dismiss();
                    Log.i("banana",t.getMessage());
                }
            });

        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}