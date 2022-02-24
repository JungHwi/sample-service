package com.breeze.core.util;

import java.util.regex.Pattern;

public class FormatValidator {

    private static final String PHONE_FORMAT = "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$";
    private static final String EMAIL_FORMAT = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; // RFC5322 정규식

    public static boolean validPhoneNumber(String phoneNumber) {
        return validFormat(phoneNumber, PHONE_FORMAT);
    }

    public static boolean validEmail(String email) {
        return validFormat(email, EMAIL_FORMAT);
    }

    public static boolean validFormat(String test, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(test)
                .matches();
    }
}
