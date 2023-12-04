package jorn.hiel.challenges;

import jorn.hiel.general.DayGiver;
import jorn.hiel.general.enums.Day;
import jorn.hiel.general.enums.Type;
import jorn.hiel.pojo.Game;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static jorn.hiel.general.ResultPrinter.printChallenge;
import static jorn.hiel.general.ResultPrinter.printResult;

/**
 * Day two challenges Advent of Code 2023
 */
public class DayTwo {



    public static void main(String[] args)  {
        try {
            challengeOne();
            challengeTwo();
        } catch (Exception e) {
            //coarse solution to predictable problem
            System.out.println("Something went wrong , files copied over from website ?");
        }
    }

    /**
     * Find number of games possible with given dice numbers
     * @throws URISyntaxException File may not have been copied over from site
     * @throws IOException File may not have been copied over from site
     */

    private static void challengeOne() throws URISyntaxException, IOException {
        int counter = 0;
        int maxRed=12;
        int maxGreen=13;
        int maxBlue=14;


        List<String> source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.two));


        for (String s : source) {
            List<Game> games = new ArrayList<>();

            String[] temp = s.split(":");
            int gameNumber = (Integer.parseInt(temp[0].replaceAll("\\D", "")));

            // isolate individual games
            String[] draws = s.substring(s.indexOf(":") + 1).split(";");
            for (String draw : draws) {
                Game game = new Game();

                //isolate individual dice
                String[] dices = draw.split(",");
                for (String dice : dices) {
                    game.addDice(dice);
                }
                games.add(game);
            }

            boolean isValidPlay = true;
            for (Game game : games) {
                if ((game.getBlue() > maxBlue) || (game.getRed() > maxRed) || (game.getGreen() > maxGreen)) {
                    isValidPlay = false;
                    break;
                }
            }
            if (isValidPlay) {
                counter += gameNumber;
            }


        }
        printChallenge(1);
        printResult(counter);

    }

    /**
     * find the number of lowest dice to make a valid play, and then
     * multiply the number of dice together to get a number. Return the sum
     * of that
     * @throws URISyntaxException
     * @throws IOException
     */
    private static void challengeTwo() throws URISyntaxException, IOException {

        int counter = 0;


        List<String> source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.two));


        for (String s : source) {

            List<Game> games = new ArrayList<>();

            String[] temp = s.split(":");
            int gameNumber = (Integer.parseInt(temp[0].replaceAll("\\D", "")));

            // isolate individual games
            String[] draws = s.substring(s.indexOf(":") + 1).split(";");
            for (String draw : draws) {
                Game game = new Game();

                //isolate individual dice
                String[] dices = draw.split(",");
                for (String dice : dices) {
                    game.addDice(dice);
                }
                games.add(game);

            }

            int possibleRed = games.stream().mapToInt(Game::getRed).max().orElse(0);
            int possibleBlue = games.stream().mapToInt(Game::getBlue).max().orElse(0);
            int possibleGreen = games.stream().mapToInt(Game::getGreen).max().orElse(0);

            counter+=(possibleRed*possibleGreen)*possibleBlue;


        }
        printChallenge(2);
        printResult(counter);
    }





}
