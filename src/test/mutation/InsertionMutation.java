package test.mutation;

import base.Tour;
import random.MersenneTwisterFast;

import java.util.Collections;

public class InsertionMutation {
    public Tour doMutation(Tour tour,int firstRandomNumber,int secondRandumNumber) {

        // For Testing randomNumbers are Hardcoded

        // MersenneTwisterFast randomGenerator = new MersenneTwisterFast();
        // int firstRandomNumber = randomGenerator.nextInt(tour.getSize()-1);
        // int secondRandumNumber = randomGenerator.nextInt(tour.getSize()-1);
        Collections.swap(tour.getCities(),firstRandomNumber,secondRandumNumber);
        return tour;
    }

}