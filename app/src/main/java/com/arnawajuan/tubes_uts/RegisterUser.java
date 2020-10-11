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

public class RegisterUser extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView btnSignIn;

    private TextInputEditText inputName, inputEmail, inputPass, inputNumber;
    Button btnRegister;
    private FirebaseAuth mAuth;
    FirebaseFirestore mStore;
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
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
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
        } else if (pass.isEmpty() || pass.length() > 6) {
            showError(inputPass, "Password must be 6 character");
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
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = mStore.collection("users").document(userID);
                        Map<String, Object> user = new HashMap<>();
                        user.put("name",name);
                        user.put("email",email);
                        user.put("number",number);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "user Profile is created for " + userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.toString());
                            }
                        });



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