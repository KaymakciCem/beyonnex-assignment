package com.beyonnex.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AnagramServiceTest {

    private AnagramService anagramService = new AnagramService();

    @Test
    void retrieveAllAnagrams_returns_empty_when_no_anagrams_calculated() {
        List<String> result = anagramService.retrieveAllAnagrams(Mockito.anyString());
        assertThat(result).isEmpty();
    }


    @Test
    void retrieveAllAnagrams_returns_empty_when_key_not_found() {
        List<String> result = anagramService.retrieveAllAnagrams("cat");
        assertThat(result).isEmpty();
    }

    @Test
    void retrieveAllAnagrams_success() {
        anagramService.saveAnagramsToMap("cat", "tac");
        anagramService.saveAnagramsToMap("cat", "act");
        anagramService.saveAnagramsToMap("cat", "cta");

        List<String> result = anagramService.retrieveAllAnagrams("cat");
        assertThat(result).containsExactlyElementsOf(List.of("tac", "act", "cta"));
    }

    @Test
    void isAnagram_fail() {
        boolean result = anagramService.isAnagram("cat", "taa");
        assertThat(result).isFalse();
    }

    @Test
    void isAnagram_success() {
        boolean result = anagramService.isAnagram("cat", "tac");
        assertThat(result).isTrue();
    }
}