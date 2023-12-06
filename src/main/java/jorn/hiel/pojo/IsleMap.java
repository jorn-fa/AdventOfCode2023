package jorn.hiel.pojo;

import java.math.BigInteger;

public class IsleMap {

    BigInteger source;
    BigInteger destination;
    int range;

    public IsleMap(BigInteger destination, BigInteger source, int range) {
        this.source = source;
        this.destination = destination;
        this.range = range;
    }

    @Override
    public String toString() {
        return "IsleMap{" +
                "source=" + source +
                ", destination=" + destination +
                ", range=" + range +
                '}';
    }



    public BigInteger getMatchingNumber(BigInteger number) {

        BigInteger difference = number.subtract(source);

        if(difference.compareTo(new BigInteger("0"))==-1){return number;}
        if(difference.compareTo(BigInteger.valueOf(range))>-1){return number;}

        BigInteger result=destination.add(difference);
        return(result);

    }
}
