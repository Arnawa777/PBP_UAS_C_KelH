package com.arnawajuan.rumah_makan_cilik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arnawajuan.rumah_makan_cilik.api.ApiClient;
import com.arnawajuan.rumah_makan_cilik.api.ApiInterface;
import com.arnawajuan.rumah_makan_cilik.api.UserResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int CAMERA_PERM_CODE = 101;
    private static final String USER_SHARED_NAME = "User";
    String id, sId;
    SharedPreferences userShared;
    TextView inputName, inputEmail, inputPhone;
    ImageView backButton, photoButton;
    ImageView selectedImage;
    String name, email, phone;
    private SharePref sharePref;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharePref = new SharePref(this);
        if (sharePref.loadDarkModeState()) {
            setTheme(R.style.SettingDark);
        } else {
            setTheme(R.style.SettingLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        backButton = findViewById(R.id.back_button);

        mLoadingBar = new ProgressDialog(UserProfile.this);
        //Image
        photoButton = findViewById(R.id.camera_button);
        selectedImage = findViewById(R.id.view_foto);

        inputEmail = findViewById(R.id.inputEmail);
        inputName = findViewById(R.id.inputName);
        inputPhone = findViewById(R.id.inputNumber);

//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if(extras == null) {
//                name= null;
//                email= null;
//                phone= null;
//            } else {
//                email= extras.getString("email");
//                name= extras.getString("name");
//                phone= extras.getString("phone_number");
//            }
//        } else {
//            name= (String) savedInstanceState.getSerializable("name");
//            phone= (String) savedInstanceState.getSerializable("phone_number");
//        }
//
//        inputEmail.setText(email);
//        inputName.setText(name);
//        inputPhone.setText(phone);

        loadPreferences();
        if (!sId.isEmpty()) {
            loadUser();
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, MainActivity.class));
            }
        });

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
            }
        });
    }



    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length < 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera Permission is Required to Use Camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            selectedImage.setImageBitmap(image);
        }
    }

    public void loadUser()

    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> getUser = apiService.showUser(id);
        getUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String userEmail = response.body().getUser().getEmail();
                String userName = response.body().getUser().getName();
                String userPhone = response.body().getUser().getPhone_number();

                inputEmail.setText(userEmail);
                inputName.setText(userName);
                inputPhone.setText(userPhone);
                Toast.makeText(UserProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(UserProfile.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                mLoadingBar.dismiss();
                Log.i("banana", t.getMessage());
            }
        });

    }

    private void loadPreferences() {
        userShared = getSharedPreferences(USER_SHARED_NAME, MODE_PRIVATE);
        sId = userShared.getString("id", "");
    }
}