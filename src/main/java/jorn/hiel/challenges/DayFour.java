package jorn.hiel.challenges;


import jorn.hiel.general.DayGiver;
import jorn.hiel.general.enums.Day;
import jorn.hiel.general.enums.Type;
import jorn.hiel.pojo.ScratchCard;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Stream;

import static jorn.hiel.general.ResultPrinter.printChallenge;
import static jorn.hiel.general.ResultPrinter.printResult;

/**
 * Day four challenges Advent of Code 2023
 */
public class DayFour {

    public static void main(String[] args) {
        try {
            challengeOne();
            challengeTwo();
        } catch (URISyntaxException | IOException e) {
            //coarse solution to predictable problem

            System.out.println("Something went wrong , files copied over from website ?");
        }
    }

    /**
     * read scratchcards and find out their max points
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    private static void challengeOne() throws URISyntaxException, IOException {
        int score = 0;
        List<String> source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.four));

        List<ScratchCard> cards = source.stream()
                .map(DayFour::processLine)
                .toList();

        for (ScratchCard card : cards) {
            score += processScore(card);
        }

        printChallenge(1);
        printResult(score);

    }


    /**
     * read scratch cards, find out how many actual winning cards according to backside rules
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    private static void challengeTwo() throws URISyntaxException, IOException {
        List<String> source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.four));

        List<ScratchCard> cards = source.stream()
                .map(DayFour::processLine)
                .toList();


        ConcurrentLinkedQueue<ScratchCard> toProcess= new ConcurrentLinkedQueue<>(cards);

        Iterator<ScratchCard> iterator = toProcess.iterator();



        while (iterator.hasNext()) {
            ScratchCard card = iterator.next();
            //System.out.println("parsing card ->" + card.getCardNumber());

            Set<Integer> unique = new HashSet<>(card.getNumbers());
            unique.addAll(card.getWinningNumbers());
            Integer matches = (card.getWinningNumbers().size() + card.getNumbers().size()) - unique.size();
            //System.out.println(matches);

            //cards to add to queue
            if (matches > 0) {
                for (int i = 1; i < matches + 1; i++) {
                    int toAdd = card.getCardNumber() + i-1 ;
                    //System.out.println("need to add extra " + (card.getCardNumber() + i));

                    if (toAdd < cards.size()) {
                        toProcess.add(cards.get(toAdd));
                    }

                }

            }


        }


        printChallenge(1);
        printResult(toProcess.size());




    }

    private static ScratchCard processLine(String source) {

        List<String> split = Arrays.asList(source.split("[:|]"));
        ScratchCard card = new ScratchCard();
        card.setCardNumber(Integer.parseInt(split.get(0).replaceAll("\\D", "")));

        Stream.of(split.get(1).trim().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(card::addWinningNumber);


        Stream.of(split.get(2).trim().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(card::addNumber);

        return card;
    }

    private static int processScore(ScratchCard card) {
        int score = 0;

        List<Integer> cardNumbers = card.getNumbers();
        cardNumbers.addAll(card.getWinningNumbers());
        Set<Integer> unique = new HashSet<>(cardNumbers);
        int matches = cardNumbers.size() - unique.size();

        if (matches > 0) {
            score = 1;
        }

        for (int i = 1; i < matches; i++) {
            score = score * 2;
        }
        return score;
    }

}
