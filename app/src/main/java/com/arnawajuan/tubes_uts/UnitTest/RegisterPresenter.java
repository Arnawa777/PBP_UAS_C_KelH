package com.arnawajuan.tubes_uts.UnitTest;

import androidx.core.util.PatternsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterPresenter {
    private RegisterView view;
    private RegisterService service;
    private RegisterCallback callback;
    //FirebaseAuth fAuth;

    public RegisterPresenter(RegisterView view, RegisterService service) {
        this.view = view;
        this.service = service;
    }

    public void onRegisterClicked() {

        boolean flag = true;
        String checkName = "\\A\\w{3,20}\\z";
        String checkNumber = "\\A\\w{10,13}\\z";

        if (view.getName().isEmpty()) {
            view.showNameError("Name is Empty");
            flag = false;
        } else if (view.getName().matches(checkName)) {
            view.showNameError("Name must be 3-20 character");
            flag = false;
        } else view.showNameError(null);

        if (view.getEmail().isEmpty()) {
            view.showEmailError("Email is Empty");
            flag = false;
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(view.getEmail()).matches()) {
            view.showEmailError("Email Invalid");
            flag = false;
        } else view.showEmailError(null);

        if (view.getPhone().isEmpty()) {
            view.showPhoneError("Phone is Empty");
            flag = false;
        } else if (view.getPassword().matches(checkNumber)) {
            view.showPhoneError("Phone must have 10-13 digit");
            flag = false;
        } else view.showPhoneError(null);

        if (view.getPassword().isEmpty()) {
            view.showPasswordError("Password is Empty");
            flag = false;
        } else if (view.getPassword().length() < 6) {
            view.showPasswordError("Password must be 6 character");
            flag = false;
        } else view.showPasswordError(null);

        if (flag){
            service.register(view, view.getName(), view.getEmail(), view.getPhone(), view.getPassword(), new RegisterCallback() {
                @Override
                public void onSuccess(boolean value) {
                    view.startUserLogin();
                }

                @Override
                public void onError() {
                }
            });
            return;
        }
    }
}
