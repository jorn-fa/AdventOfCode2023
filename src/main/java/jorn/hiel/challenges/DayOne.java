package jorn.hiel.challenges;

import jorn.hiel.general.DayGiver;
import jorn.hiel.general.enums.Day;
import jorn.hiel.general.enums.Type;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;

import static jorn.hiel.general.ResultPrinter.printChallenge;
import static jorn.hiel.general.ResultPrinter.printResult;


/**
 * Day one challenges Advent of Code 2023
 */
public class DayOne   {

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
     * Find first and last number in given strings and outputs the total sum
     * @throws URISyntaxException File may not have been copied over from site
     * @throws IOException File may not have been copied over from site
     */

    private static void challengeOne() throws URISyntaxException, IOException {

        List<String> source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.one));
        int counter = 0;

        for (String line : source) {
            String cleanedInput = line.replaceAll("\\D","");
            // only take first and last number found
            if(cleanedInput.length()>2){
                cleanedInput=(cleanedInput.charAt(0)+cleanedInput.substring(cleanedInput.length()-1));
            }
            //double number in case of only one
            if(cleanedInput.length()==1) {
                cleanedInput=cleanedInput+cleanedInput;

            }
            counter+=Integer.parseInt(cleanedInput);
        }

        printChallenge(1);
        printResult(counter);
    }

    /**
     * Find first and last number in given strings and outputs the total sum
     * However numbers might be written as words aswell
     * @throws URISyntaxException  File may not have been copied over from site
     * @throws IOException File may not have been copied over from site
     */
    private static void challengeTwo()throws URISyntaxException, IOException{

        List<String> source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.one));
        int counter = 0;
        for (String line : source) {

            //edge cases - example was not very clear

            line=line.replaceAll("oneight","18");
            line=line.replaceAll("eightwo","82");
            line=line.replaceAll("twone","21");
            line=line.replaceAll("eighthree","83");
            line=line.replaceAll("sevenine","79");
            line=line.replaceAll("nineight","98");

            line=line.replaceAll("two","2");
            line=line.replaceAll("one","1");
            line=line.replaceAll("four","4");
            line=line.replaceAll("five","5");
            line=line.replaceAll("six","6");
            line=line.replaceAll("seven","7");
            line=line.replaceAll("nine","n9ne");
            line=line.replaceAll("three","3");
            line=line.replaceAll("eight","8");


            String cleanedInput = line.replaceAll("\\D","");
            // only take first and last number found
            if(cleanedInput.length()>2){
                cleanedInput=(cleanedInput.charAt(0)+cleanedInput.substring(cleanedInput.length()-1));
            }
            //double number in case of only one
            if(cleanedInput.length()==1) {
                cleanedInput=cleanedInput+cleanedInput;

            }
            counter+=Integer.parseInt(cleanedInput);



        }
        printChallenge(2);
        printResult(counter);
    }
}
