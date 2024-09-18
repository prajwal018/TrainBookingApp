package com.prajwal.TrainBookingApp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyUtil {

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private static final Logger log = LoggerFactory.getLogger(ApiKeyUtil.class);

    public static String generateApiKey() {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashApiKey(String apiKey) {
        return passwordEncoder.encode(apiKey);
    }

    public static boolean matches(String rawApiKey, String encodedApiKey) {
        return encodedApiKey.equals(passwordEncoder.encode(rawApiKey));
    }
}
