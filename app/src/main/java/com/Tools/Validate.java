package com.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rjesture on 1/14/2022.
 */
public class Validate {
    public static boolean isValidUserName(String w) {
        return w.matches("[a-zA-Z0-9]*");
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();

    }

    public static boolean isValidPhoneNumber(String mobile) {
        Pattern ptrn = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher match = ptrn.matcher(mobile);
        return (match.find() && match.group().equals(mobile));
    }


}
