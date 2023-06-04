package com.trust.util;

import java.util.Random;

public  class UserKeyUtil {

    public static String generateCustomerKey(String customerName) {
        // Replace spaces with underscores
        String formattedName = customerName.toLowerCase().replace(" ", "_");

        // Generate random six-digit number
        String randomNumber = String.format("%09d", new Random().nextInt(1000000));

        // Concatenate name and number
        return formattedName + randomNumber;
    }

    public static String generateBrandKey() {
        return  String.format("%010d", new Random().nextInt(10000000));
    }
}
