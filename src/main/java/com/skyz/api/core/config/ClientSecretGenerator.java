package com.skyz.api.core.config;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ClientSecretGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    private static final String ALL_CHARS = LOWER + UPPER + DIGITS + PUNCTUATION;

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate() {
        log.info("Client secret is being generated");
        StringBuilder pswd = new StringBuilder();
        pswd.append(LOWER.charAt(RANDOM.nextInt(LOWER.length())));
        pswd.append(UPPER.charAt(RANDOM.nextInt(UPPER.length())));
        pswd.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        pswd.append(PUNCTUATION.charAt(RANDOM.nextInt(PUNCTUATION.length())));

        for (int i = 4; i < 75; i++) {
            pswd.append(ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length())));
        }

        List<Character> letters = pswd.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(letters);

        return letters.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
