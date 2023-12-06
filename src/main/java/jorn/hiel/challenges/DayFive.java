package jorn.hiel.challenges;

import jorn.hiel.general.DayGiver;
import jorn.hiel.general.enums.Day;
import jorn.hiel.general.enums.Type;
import jorn.hiel.pojo.IsleMap;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static jorn.hiel.general.ResultPrinter.printChallenge;
import static jorn.hiel.general.ResultPrinter.printResult;


/**
 * Day five challenges Advent of Code 2023
 */
public class DayFive {


    public static void main(String[] args) {
        try {
            //challengeOne();
            //challengeTwo();
            tester();

        } catch (URISyntaxException | IOException e) {
            //coarse solution to predictable problem
            System.out.println("Something went wrong , files copied over from website ?");
        }
    }

    static HashMap<String, Integer> sections = new HashMap<>();
    static List<String> source = new ArrayList<>();

    private static void challengeOne() throws URISyntaxException, IOException {

        source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.five));

        //read seeds from file
        List<BigInteger> seeds = Arrays.stream(source.get(0).substring(7).split("\\s+\\D*"))
                .map(BigInteger::new)
                .toList();

        sections = new HashMap<>();
        List<String> titles = new ArrayList<>();
        titles.add("seed-to-soil map:");
        titles.add("soil-to-fertilizer map:");
        titles.add("fertilizer-to-water map:");
        titles.add("water-to-light map:");
        titles.add("light-to-temperature map:");
        titles.add("temperature-to-humidity map:");
        titles.add("humidity-to-location map:");


        for (int i = 0; i < source.size(); i++) {
            for (String title : titles) {
                if (source.get(i).contains(title)) {
                    sections.put(title, i);
                }
            }
        }
        sections.put("end", source.size() + 1);


        List<IsleMap> seedSoil = getSection("seed-to-soil map:", "soil-to-fertilizer map:");

        List<IsleMap> soilFert = getSection("soil-to-fertilizer map:", "fertilizer-to-water map:");


        List<IsleMap> fertWater = getSection("fertilizer-to-water map:", "water-to-light map:");

        List<IsleMap> waterLight = getSection("water-to-light map:", "light-to-temperature map:");


        List<IsleMap> lightTemp = getSection("light-to-temperature map:", "temperature-to-humidity map:");


        List<IsleMap> tempHumi = getSection("temperature-to-humidity map:", "humidity-to-location map:");


        List<IsleMap> humiLoca = getSection("humidity-to-location map:", "end");


        List<BigInteger> result = new ArrayList<>();

        for (BigInteger tester : seeds) {
            BigInteger step1 = findResult(seedSoil, tester);
            BigInteger step2 = findResult(soilFert, step1);
            BigInteger step3 = findResult(fertWater, step2);
            BigInteger step4 = findResult(waterLight, step3);
            BigInteger step5 = findResult(lightTemp, step4);
            BigInteger step6 = findResult(tempHumi, step5);
            BigInteger step7 = findResult(humiLoca, step6);
            result.add(step7);


        }


        printChallenge(1);
        printResult(result.stream()
                .min(BigInteger::compareTo).get());


    }

    private static BigInteger findResult(List<IsleMap> maps, BigInteger number) {
        for (IsleMap map : maps) {
            BigInteger result;
            result = map.getMatchingNumber(number);
            if (result != number) {
                return result;
            }
        }

        return number;
    }

    private static IsleMap convertStringToIsleMap(String source) {
        String[] split = source.split(" ");
        return new IsleMap(
                new BigInteger(split[0]),
                new BigInteger(split[1]),
                Integer.parseInt(split[2])
        );
    }

    private static List<IsleMap> getSection(String startTitle, String endTitle) {

        List<IsleMap> isleMaps = new ArrayList<>();
        int start = sections.get(startTitle);
        int end = sections.get(endTitle);
        for (int i = start + 1; i < end - 1; i++) {
            isleMaps.add(convertStringToIsleMap(source.get(i)));
        }

        return isleMaps;
    }

    private static void challengeTwo() throws URISyntaxException, IOException {

        source = Files.readAllLines(DayGiver.getFile(Type.EXAMPLE, Day.five));

        //read seeds from file
        List<BigInteger> seeds = Arrays.stream(source.get(0).substring(7).split("\\s+\\D*"))
                .map(BigInteger::new)
                .toList();



        sections = new HashMap<>();
        List<String> titles = new ArrayList<>();
        titles.add("seed-to-soil map:");
        titles.add("soil-to-fertilizer map:");
        titles.add("fertilizer-to-water map:");
        titles.add("water-to-light map:");
        titles.add("light-to-temperature map:");
        titles.add("temperature-to-humidity map:");
        titles.add("humidity-to-location map:");


        for (int i = 0; i < source.size(); i++) {
            for (String title : titles) {
                if (source.get(i).contains(title)) {
                    sections.put(title, i);
                }
            }
        }
        sections.put("end", source.size() + 1);


        List<IsleMap> seedSoil = getSection("seed-to-soil map:", "soil-to-fertilizer map:");
        List<IsleMap> soilFert = getSection("soil-to-fertilizer map:", "fertilizer-to-water map:");
        List<IsleMap> fertWater = getSection("fertilizer-to-water map:", "water-to-light map:");
        List<IsleMap> waterLight = getSection("water-to-light map:", "light-to-temperature map:");
        List<IsleMap> lightTemp = getSection("light-to-temperature map:", "temperature-to-humidity map:");
        List<IsleMap> tempHumi = getSection("temperature-to-humidity map:", "humidity-to-location map:");
        List<IsleMap> humiLoca = getSection("humidity-to-location map:", "end");


        List<BigInteger> result = new ArrayList<>();

        for (BigInteger tester : seeds) {
            BigInteger step1 = findResult(seedSoil, tester);
            BigInteger step2 = findResult(soilFert, step1);
            BigInteger step3 = findResult(fertWater, step2);
            BigInteger step4 = findResult(waterLight, step3);
            BigInteger step5 = findResult(lightTemp, step4);
            BigInteger step6 = findResult(tempHumi, step5);
            BigInteger step7 = findResult(humiLoca, step6);
            result.add(step7);
        }
        printChallenge(1);
        printResult(result.stream()
                .min(BigInteger::compareTo).get());


    }

    private static void tester() throws URISyntaxException, IOException
    {
        source = Files.readAllLines(DayGiver.getFile(Type.CHALLENGEONE, Day.five));

        //read seeds from file
        List<BigInteger> seeds = Arrays.stream(source.get(0).substring(7).split("\\s+\\D*"))
                .map(BigInteger::new)
                .toList();
        List<BigInteger>toGet = new ArrayList<>();

        for (int j = 0; j < 4; j+=2) {

            int starter=Integer.valueOf(String.valueOf(seeds.get(j)));
            int amount =Integer.valueOf(String.valueOf(seeds.get(j+1)));


            int counter = Integer.valueOf(String.valueOf(seeds.get(j)));
            toGet.add(new BigInteger(String.valueOf(counter)));
            for (int i = (starter); i<(starter+amount-1); i++) {
                counter += 1;
                toGet.add(new BigInteger(String.valueOf(counter)));
            }
        }

        System.out.println(toGet.size());
        toGet.forEach(System.out::println);

    }



}
