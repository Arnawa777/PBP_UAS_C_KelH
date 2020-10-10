package com.arnawajuan.tubes_uts;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {

    TextView btnSignIn;

    private EditText inputEmail, inputPass, inputNumber;
    Button btnNext, btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        btnSignIn = findViewById(R.id.signIn);

        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPassword);
        inputNumber = findViewById(R.id.inputNumber);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(RegisterUser.this);
       btnRegister = findViewById(R.id.register);




    }

    public void callRegisterUser(View view) {

        Intent intent = new Intent(getApplicationContext(), UserLogin.class);

        //Transisition
        Pair[] pairs = new Pair[2];

        pairs[0] = new Pair<View, String>(btnRegister, "transition_next");
        pairs[1] = new Pair<View, String>(btnSignIn, "transition_sign_in");

        //call activity
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterUser.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), UserLogin.class));
        finish();
    }


    private void checkCredentials() {
        String email = inputEmail.getText().toString();
        String pass = inputPass.getText().toString();
        String number = inputNumber.getText().toString();
        String checkspaces = "Aw{1,20}z";

        if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Email is not valid");
        } else if (pass.isEmpty() || pass.length() < 6) {
            showError(inputPass, "Password must be 6 character");
        } else if (number.isEmpty() || !number.matches(checkspaces)) {
            showError(inputNumber, "Number not Allowed");
        } else {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait, while check");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterUser.this, "Register Successfull", Toast.LENGTH_SHORT).show();

                        mLoadingBar.dismiss();
                        Intent intent = new Intent(RegisterUser.this, UserLogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterUser.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();
                    }


                }
            });
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}