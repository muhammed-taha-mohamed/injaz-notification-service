package com.trust.util;

import java.util.Random;

public  class OtpUtil {

    public static String generateLogin () {
        try {
            // Generate random six-digit number
            return String.format("%06d", new Random().nextInt(1000000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
