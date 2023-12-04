package jorn.hiel.challenges;


import jorn.hiel.general.DayGiver;
import jorn.hiel.general.enums.Day;
import jorn.hiel.general.enums.Type;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;

/**
 * Day four challenges Advent of Code 2023
 */
public class DayFour {

    public static void main(String[] args) {
        try {
            challengeOne();
            challengeTwo();
        } catch (Exception e) {
            //coarse solution to predictable problem
            System.out.println("Something went wrong , files copied over from website ?");
        }
    }

    private static void challengeOne() throws URISyntaxException, IOException {
        List source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.three));

    }
    private static void challengeTwo() throws URISyntaxException, IOException {

    }
}
