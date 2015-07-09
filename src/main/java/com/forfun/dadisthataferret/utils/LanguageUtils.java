package com.forfun.dadisthataferret.utils;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.addAll;
import static java.util.Collections.unmodifiableSet;

public class LanguageUtils {

    private static final Set<Character> vowels = vowels();

    private static Set<Character> vowels() {
        Set<Character> vowels = new HashSet<>();
        addAll(vowels, 'a', 'e', 'i', 'o', 'u');
        return unmodifiableSet(vowels);
    }

    public static String articleFor(String word) {
        return startsWithVowel(word) ? "an" : "a";
    }

    private static boolean startsWithVowel(String word) {
        return vowels.contains(word.charAt(0));
    }

}
