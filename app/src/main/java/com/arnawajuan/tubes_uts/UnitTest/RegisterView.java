package com.arnawajuan.tubes_uts.UnitTest;

public interface RegisterView {

    String getName();

    void showNameError(String message);

    String getEmail();

    void showEmailError(String message);

    String getPhone();

    void showPhoneError(String message);

    String getPassword();

    void showPasswordError(String message);

    void startUserLogin();

    void showRegisterError(String message);

    void showRegisterMessage(String message);
}
