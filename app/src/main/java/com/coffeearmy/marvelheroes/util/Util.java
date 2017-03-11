package com.coffeearmy.marvelheroes.util;

import java.util.Random;

/**
 *
 */

public class Util {


    public static int getRandom(int min, int max) {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        return (r.nextInt(max - min) + min);

    }
}
