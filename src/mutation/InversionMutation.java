package mutation;

import base.Tour;
import random.MersenneTwisterFast;

import java.util.Collections;

public class InversionMutation implements IMutation {
    public Tour doMutation(Tour tour) {
        MersenneTwisterFast randomGenerator = new MersenneTwisterFast();
        int lowerBorder= randomGenerator.nextInt((tour.getSize()-2));
        randomGenerator = new MersenneTwisterFast();
        int upperBorder = 1+lowerBorder+randomGenerator.nextInt(((tour.getSize()-2)-lowerBorder));
        System.out.println(upperBorder+" "+lowerBorder);
        while(upperBorder > lowerBorder)
                Collections.swap(tour.getCities(),upperBorder--,lowerBorder++);
        return tour;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}