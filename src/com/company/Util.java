package com.company;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Util {
    final static Random r = new Random();

    public static String makeName(int length) {
        return IntStream.generate(Util::makeNumber)
                .limit(length).mapToObj(Character::toString)
                .collect(Collectors.joining());
    }

    private static int makeNumber() {
        return r.nextInt(25) + 65;
    }
}