package mutation;

import base.Population;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.Collections;

public class InsertionMutation implements IMutation {
    double mutationRatio = 0.001;
    public Tour doMutation(Tour tour) {
        MersenneTwisterFast randomGenerator = new MersenneTwisterFast();
        int firstRandomNumber = randomGenerator.nextInt(tour.getSize()-1);
        int secondRandumNumber = randomGenerator.nextInt(tour.getSize()-1);
        Collections.swap(tour.getCities(),firstRandomNumber,secondRandumNumber);
        return tour;
    }

    public Population executeMutation(Population popu) {
        popu = new Population();
        popu.generateRandom();
        System.out.println(popu.toString());
        MersenneTwisterFast randomGen = new MersenneTwisterFast();
        for(Tour tour : popu.getTours()){
            double randomNumber = randomGen.nextDouble(true,true);
            if(randomNumber <= mutationRatio){
                doMutation(tour);
            }
        }
        return popu;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}