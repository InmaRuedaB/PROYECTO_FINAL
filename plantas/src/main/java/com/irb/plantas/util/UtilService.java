package com.irb.plantas.util;

import java.util.regex.Pattern;

public class UtilService {

    private static final String REGEX_MAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";


    public static boolean validarMail(String mail) {
        return Pattern.compile(REGEX_MAIL)
                .matcher(mail)
                .matches();
    }
}
