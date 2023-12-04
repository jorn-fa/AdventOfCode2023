package jorn.hiel.pojo;

public class Game {

    int gameNumber;
    int blue=0;
    int green=0;
    int red=0;


    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }

    public int getRed() {
        return red;
    }

    public Game() {

    }

    public void addDice(String source){

        int number = Integer.parseInt(source.trim().split(" ")[0]);

        switch (source.trim().split(" ")[1].toLowerCase()){

            case "blue":blue+=number;break;
            case "red":red+=number;break;
            case "green":green+=number;break;
            default:
                System.out.println("problem @" + source);
        }

    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public int getGameNumber() {
        return gameNumber;
    }


    @Override
    public String toString() {
        return "Game{" +
                "gameNumber=" + gameNumber +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
