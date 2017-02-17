package mutation;

import base.City;
import base.Population;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.Collections;

public class InversionMutation implements IMutation {
    double mutationRatio = 0.001;
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