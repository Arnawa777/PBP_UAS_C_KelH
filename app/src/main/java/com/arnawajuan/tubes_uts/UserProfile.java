package com.arnawajuan.tubes_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class UserProfile extends AppCompatActivity {

    TextView name,email,number;
    ImageView backButton;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    String userID;
    private SharePref sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharePref =new SharePref(this);
        if(sharePref.loadDarkModeState()) {
            setTheme(R.style.SettingDark);
        }else{
            setTheme(R.style.SettingLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        backButton = findViewById(R.id.back_button);

        name = findViewById(R.id.tv_name);
        email = findViewById(R.id.tv_email);
        number = findViewById(R.id.tv_number);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        userID = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = mStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText(documentSnapshot.getString("name"));
                email.setText(documentSnapshot.getString("email"));
                number.setText(documentSnapshot.getString("number"));
            }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, MainActivity.class));
            }
        });
    }
}