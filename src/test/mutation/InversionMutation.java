package test.mutation;

import base.Population;
import base.Tour;
import random.MersenneTwisterFast;
import test.AbstractTest;

import java.util.Collections;

public class InversionMutation extends AbstractTest{
    double mutationRatio = 0.001;
    // For Test case the random part is cut out and lower and upperBorder are given
    public Tour doMutation(Tour tour,int lowerBorder,int upperBorder) {
      /*  MersenneTwisterFast randomGenerator = new MersenneTwisterFast();
        int lowerBorder= randomGenerator.nextInt((tour.getSize()-2));
        randomGenerator = new MersenneTwisterFast();
        int upperBorder = 1+lowerBorder+randomGenerator.nextInt(((tour.getSize()-2)-lowerBorder)); */
        System.out.println(upperBorder+" "+lowerBorder);
        while(upperBorder > lowerBorder)
            Collections.swap(tour.getCities(),upperBorder--,lowerBorder++);
        return tour;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}