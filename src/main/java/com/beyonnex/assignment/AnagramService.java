package com.beyonnex.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramService {

    private static int CHARACTER_RANGE= 256;

    final Map<Map<Character, Integer>, List<String>> allAnagramsMap = new HashMap<>();

    public List<String> retrieveAllAnagrams(final String text) {
        if (allAnagramsMap.isEmpty()) {
            return Collections.emptyList();
        }

        final Map<Character, Integer> anagramKey = toAnagramKey(text);

        if (!allAnagramsMap.containsKey(anagramKey)) {
            return Collections.emptyList();
        }

        final List<String> result = allAnagramsMap.get(anagramKey);

        return result.stream()
                     .filter(c -> !c.equals(text))
                     .toList();
    }

    private void saveAnagramsToMap(final String text1, final String text2) {
        final Map<Character, Integer> anagramKey1 = toAnagramKey(text1);

        if (allAnagramsMap.containsKey(anagramKey1)) {
            if (!allAnagramsMap.get(anagramKey1).contains(text2)) {
                allAnagramsMap.get(anagramKey1).add(text2);
            }

            if (!allAnagramsMap.get(anagramKey1).contains(text1)) {
                allAnagramsMap.get(anagramKey1).add(text1);
            }
        } else {
            final List<String> anagramTexts = new ArrayList<>();
            anagramTexts.add(text1);
            anagramTexts.add(text2);
            allAnagramsMap.put(anagramKey1, anagramTexts);
        }
    }

    public boolean isAnagram(final String text1, final String text2) {
        if (text1.length() != text2.length()) {
            return false;
        }
        int count[] = new int[CHARACTER_RANGE];
        for (int i = 0; i < text1.length(); i++) {
            count[text1.charAt(i)]++;
            count[text2.charAt(i)]--;
        }
        for (int i = 0; i < CHARACTER_RANGE; i++) {
            if (count[i] != 0) {
                return false;
            }
        }

        saveAnagramsToMap(text1, text2);

        return true;
    }

    private Map<Character, Integer> toAnagramKey(final String text) {
        final Map<Character, Integer> anagramKey = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            if (anagramKey.containsKey(text.charAt(i))) {
                int x = anagramKey.get(text.charAt(i));
                anagramKey.put(text.charAt(i), ++x);
            }
            else {
                anagramKey.put(text.charAt(i), 1);
            }
        }
        return anagramKey;
    }
}
