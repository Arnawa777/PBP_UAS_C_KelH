package com.arnawajuan.rumah_makan_cilik.UnitTest;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterService {
    FirebaseAuth fAuth;

    public void register(final RegisterView view, String name, String email, String phone,String password, final RegisterCallback callback) {
        fAuth = FirebaseAuth.getInstance();

        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    view.showRegisterMessage("Register success!");
                    callback.onSuccess(true);
                } else {
                    view.showRegisterError("Register failed!");
                    callback.onError();
                }
            }
        });
    }

    public Boolean getValid(final RegisterView view, String name, String email, String phone,String password) {
        final Boolean[] bool = new Boolean[1];

        register(view,name, email,phone, password, new RegisterCallback() {
            @Override
            public void onSuccess(boolean value) {
                bool[0] = true;
            }

            @Override
            public void onError() {
                bool[0] = false;
            }
        });

        return bool[0];
    }
}
