package com.simpleauth;

import java.util.regex.*;


class checkEmail {
    public boolean checkEmail(String email)
    {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean matchFound = matcher.find();

        return matchFound;
    }

}
