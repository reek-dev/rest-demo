package com.example.restdemo.util;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final String phoneRegex =
            "\\d{10}";

    public static boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phone).matches();
    }
}
