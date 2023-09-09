package com.beyonnex.assignment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssignmentApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(AssignmentApplication.class);

    @Autowired
    private AnagramService anagramService;


    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(AssignmentApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");

        anagramService = new AnagramService();

        if (anagramService.isAnagram("table", "atble")) {
            anagramService.saveAnagramsToMap("table", "atble");
        }

        if (anagramService.isAnagram("table", "atcle")) {
            anagramService.saveAnagramsToMap("table", "atcle");
        }

        if (anagramService.isAnagram("table", "latbe")) {
            anagramService.saveAnagramsToMap("table", "latbe");
        }

        final List<String> anagrams1 = anagramService.retrieveAllAnagrams("table"); // atble, latbe
        final List<String> anagrams2 = anagramService.retrieveAllAnagrams("atble"); // table, latbe
        final List<String> anagrams3 = anagramService.retrieveAllAnagrams("atcle"); // empty

        System.out.println(anagrams1.toString());
        System.out.println(anagrams2.toString());
        System.out.println(anagrams3.toString());
    }
}
