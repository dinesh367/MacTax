package com.mactec.mactax.service.mail;

import java.util.Random;

/**
 * 
 * @author akshaylap
 *
 */
public final class OTPGeneratorUtil {

    private static final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    private OTPGeneratorUtil() {
    }

    public static String generate() {
        Random rand = new Random();
        StringBuilder builder = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            builder.append(CHARS[rand.nextInt(CHARS.length)]);
        }
        return builder.toString();
    }

}
