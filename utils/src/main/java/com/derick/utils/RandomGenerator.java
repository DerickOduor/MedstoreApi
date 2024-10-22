package com.derick.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomGenerator {
    private static final String CHAR_LOWER = "0123456789";
    private static final String CHAR_UPPER = "9876543210";
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public /*static*/ String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        try{
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                // 0-62 (exclusive), random returns 0-61
                int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
                char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
                // debug
                System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);
                sb.append(rndChar);
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "989014";
    }
}
