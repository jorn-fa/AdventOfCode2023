package jorn.hiel.general;

public class ResultPrinter {

    public static  void printResult(int source){
        System.out.println("result is "+ source);
    }

    public static void printResult(Object source){
        System.out.println("result is "+ source);
    }

    public static void printChallenge(int number){
        System.out.print ("Challenge -> "+number+": ");
    }



}
