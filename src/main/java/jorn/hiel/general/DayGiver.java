package jorn.hiel.general;

import jorn.hiel.general.enums.Day;
import jorn.hiel.general.enums.Type;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DayGiver {



    public static Path getFile(Type type, Day day) throws URISyntaxException{

        return switch (type) {
            case EXAMPLE -> getExample(day, Type.EXAMPLE);
            case EXAMPLEB -> getExample(day, type);
            case CHALLENGEONE -> getFirstChallenge(day);
            default -> null;
        };

    }

    private static Path getExample(Day day,Type type) throws URISyntaxException {
        String fileName = "examples/ExampleDay"+day.name();
        if(type==Type.EXAMPLEB){fileName+="B";}
        fileName+=".txt";

        URL url = (DayGiver.class.getClassLoader().getResource(fileName));
        return Paths.get(url.toURI());

    }

    private static Path getFirstChallenge(Day day) throws URISyntaxException {

        URL url = (DayGiver.class.getClassLoader().getResource("firstChallenge/FirstDay"+day.name()+".txt"));
        return Paths.get(url.toURI());

    }




}
