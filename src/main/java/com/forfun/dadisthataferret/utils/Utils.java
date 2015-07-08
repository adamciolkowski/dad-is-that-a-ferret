package com.forfun.dadisthataferret.utils;

import java.util.List;
import java.util.Random;

public class Utils {

    private static Random random;

    public static <E> E randomElement(List<E> list) {
        if (random == null)
            random = new Random();
        return randomElement(list, random);
    }

    public static <E> E randomElement(List<E> list, Random r) {
        if(list.isEmpty())
            throw new IllegalArgumentException("Cannot get element from list: list is empty");
        return list.get(r.nextInt(list.size()));
    }
}
