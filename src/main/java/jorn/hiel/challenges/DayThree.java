package jorn.hiel.challenges;

import jorn.hiel.general.DayGiver;
import jorn.hiel.general.enums.Day;
import jorn.hiel.general.enums.Type;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jorn.hiel.general.ResultPrinter.printChallenge;
import static jorn.hiel.general.ResultPrinter.printResult;

/**
 * Day three challenges Advent of Code 2023
 */
public class DayThree {

    private static List<String> source;

    public static void main(String[] args) {
        try {
            challengeOne();
            challengeTwo();
        } catch (Exception e) {
            //coarse solution to predictable problem
            System.out.println("Something went wrong , files copied over from website ?");
        }
    }

    /**
     * Find the sum of numbers where in the schematic the number is adjacent to a symbol
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    private static void challengeOne() throws URISyntaxException, IOException {
        source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.three));
        cleanupSource();
        int counter = 0;

        for (int line = 0; line < source.size(); line++) {


            String test = source.get(line);


            List<Integer> numbers = getIntegers(test);

            if (numbers.size() > 0) {

                for (int i = 0; i < numbers.size(); i++) {


                    int whereFound = test.indexOf(String.valueOf(numbers.get(i)));

                    if (findSymbol(line, whereFound, numbers.get(i))) {
                        System.out.println(numbers.get(i));
                        counter += numbers.get(i);



                    }
                }

            }


        }
        printChallenge(1);
        printResult(counter);

        System.out.println(counter>539257);
        System.out.println(counter<539741);
    }

    /**
     * to lazy to code index out of bounds corrections
     */
    private static void cleanupSource() {
        int length = source.get(0).length();
        String toAdd = ".".repeat(length);
        source.add(0, toAdd);
        source.add(toAdd);

        for (int i = 0; i < source.size(); i++) {
            String replace = "." + source.get(i) + ".";
            source.remove(i);
            source.add(i, replace);
        }


    }

    /**
     * read square based around coordinates of found number
     *
     * @param line       line number of the line
     * @param whereFound start location
     * @param number     number that has been found ( to determine length )
     */
    private static boolean findSymbol(int line, int whereFound, int number) {


        int lengthOfNumber = String.valueOf(number * 100).length();
        int start = whereFound - 1;

        StringBuilder toCheck = new StringBuilder();

        toCheck.append(source.get(line - 1), start, start + lengthOfNumber);
        toCheck.append(source.get(line), start, start + lengthOfNumber);
        toCheck.append(source.get(line + 1), start, start + lengthOfNumber);


        char[] charactersToCheck = "*%@+/&$=-#".toCharArray();


        for (char symbol : charactersToCheck) {

            if (toCheck.toString().contains(String.valueOf(symbol))) {


                return true;
            }

        }


        return false;
    }

    private static List<Integer> getIntegers(String test) {
        List<Integer> numbers = new ArrayList<>();
        Pattern p = Pattern.compile("\\d*[^\\D]");
        Matcher m = p.matcher(test);
        while (m.find()) {
            numbers.add(Integer.parseInt(m.group()));
        }
        return numbers;
    }

    private static void challengeTwo() throws URISyntaxException, IOException {
    }


}
