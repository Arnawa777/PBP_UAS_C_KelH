package com.arnawajuan.rumah_makan_cilik.UnitTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterTest {

        @Mock
        private RegisterView view;
        @Mock private
        RegisterService service;
        private RegisterPresenter presenter;

        @Before
        public void setUp() throws Exception {
            presenter = new RegisterPresenter(view, service);
    }
    @Test
    public void shouldShowErrorMessageWhenNameIsEmpty() throws Exception {
        when(view.getName()).thenReturn("");
        System.out.println("Name : "+view.getName());

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPhone()).thenReturn("1234512345");
        System.out.println("Phone Number : "+view.getPhone());

        when(view.getPassword()).thenReturn("test12");
        System.out.println("password : "+view.getPassword());

        presenter.onRegisterClicked();
        verify(view).showNameError("Name is Empty");
    }
    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception {
        when(view.getName()).thenReturn("tester");
        System.out.println("Name : "+view.getName());

        when(view.getEmail()).thenReturn("");
        System.out.println("Email : "+view.getEmail());

        when(view.getPhone()).thenReturn("1234512345");
        System.out.println("Phone Number : "+view.getPhone());

        when(view.getPassword()).thenReturn("test12");
        System.out.println("password : "+view.getPassword());

        presenter.onRegisterClicked();
        verify(view).showEmailError("Email is Empty");
    }
    @Test
    public void shouldShowErrorMessageWhenPhoneIsEmpty() throws Exception {
        when(view.getName()).thenReturn("tester");
        System.out.println("Name : "+view.getName());

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPhone()).thenReturn("");
        System.out.println("Phone Number : "+view.getPhone());

        when(view.getPassword()).thenReturn("test12");
        System.out.println("password : "+view.getPassword());

        presenter.onRegisterClicked();
        verify(view).showPhoneError("Phone is Empty");
    }
    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        when(view.getName()).thenReturn("tester");
        System.out.println("Name : "+view.getName());

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPhone()).thenReturn("1234512345");
        System.out.println("Phone Number : "+view.getPhone());

        when(view.getPassword()).thenReturn("");
        System.out.println("password : "+view.getPassword());

        presenter.onRegisterClicked();
        verify(view).showPasswordError("Password is Empty");
    }
    @Test
    public void shouldShowRegisterValid() throws Exception {
        when(view.getName()).thenReturn("tester");
        System.out.println("Name : "+view.getName());

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : "+view.getEmail());

        when(view.getPhone()).thenReturn("1234512345");
        System.out.println("Phone Number : "+view.getPhone());

        when(view.getPassword()).thenReturn("test12");
        System.out.println("password : "+view.getPassword());

        when(service.getValid(view, view.getName(),view.getEmail(),
                view.getPhone(),view.getPassword())).thenReturn(true);
        System.out.println("Hasil : "+service.getValid(view,view.getName(),view.getEmail(),
                view.getPhone(),view.getPassword())); presenter.onRegisterClicked();
        //verify(view).startUserLogin();
    }
}