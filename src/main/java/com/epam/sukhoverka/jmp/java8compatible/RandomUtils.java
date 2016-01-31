package com.epam.sukhoverka.jmp.java8compatible;

import java.util.Random;

public class RandomUtils {

    private static Random rnd = new Random();

    public static Integer generatePositiveRandom(int mod) {
        return rnd.nextInt(Integer.MAX_VALUE) % mod;
    }
}
