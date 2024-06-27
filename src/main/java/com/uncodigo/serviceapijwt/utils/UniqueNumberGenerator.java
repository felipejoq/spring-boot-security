package com.uncodigo.serviceapijwt.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueNumberGenerator {
    private final Set<Integer> generatedNumbers = new HashSet<>();
    private final Random random = new Random();

    public int generateUniqueNumber() {
        int number;
        do {
            number = 100000000 + random.nextInt(900000000);
        } while (generatedNumbers.contains(number));
        generatedNumbers.add(number);
        return number;
    }
}