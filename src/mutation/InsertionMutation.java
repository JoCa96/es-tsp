package mutation;

import base.Population;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.Collections;

public class InsertionMutation implements IMutation {
    public Tour doMutation(Tour tour) {
        MersenneTwisterFast randomGenerator = new MersenneTwisterFast();
        int firstRandomNumber = randomGenerator.nextInt(tour.getSize()-1);
        int secondRandumNumber = randomGenerator.nextInt(tour.getSize()-1);
        Collections.swap(tour.getCities(),firstRandomNumber,secondRandumNumber);
        return tour;
    }


    public String toString() {
        return getClass().getSimpleName();
    }
}