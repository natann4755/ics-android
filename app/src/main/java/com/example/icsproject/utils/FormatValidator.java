package com.example.icsproject.utils;

public class FormatValidator {

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean isPasswordValid(CharSequence password) {
        return (password.toString().length() > 5);
    }
}
