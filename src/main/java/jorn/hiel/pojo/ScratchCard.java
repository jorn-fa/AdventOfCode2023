package jorn.hiel.pojo;

import java.util.ArrayList;
import java.util.List;

public class ScratchCard {

    int cardNumber=0;
    List<Integer> winningNumbers;
    List<Integer> numbers;


    public ScratchCard() {
        winningNumbers=new ArrayList<>();
        numbers=new ArrayList<>();
    }

    public boolean addWinningNumber(int number){
        return winningNumbers.add(number);
    }

    public boolean addNumber(int number){
        return numbers.add(number);
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "ScratchCard{" +
                "cardNumber=" + cardNumber +
                ", winningNumbers=" + winningNumbers.size() +
                ", numbers=" + numbers.size() +
                '}';
    }
}
