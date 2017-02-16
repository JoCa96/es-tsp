package mutation;

import base.Tour;
import random.MersenneTwisterFast;

public class InversionMutation implements IMutation {
    public Tour doMutation(Tour tour) {
        System.out.println(tour.toString());
        System.out.println(tour.getSize());
        MersenneTwisterFast randomGenerator = new MersenneTwisterFast();
        int lowerBorder= randomGenerator.nextInt((tour.getSize()-2));
        randomGenerator = new MersenneTwisterFast();
        int upperBorder = 1+lowerBorder+randomGenerator.nextInt(((tour.getSize()-2)-lowerBorder));
        int borderDifference = upperBorder-lowerBorder;
        System.out.println(lowerBorder+" "+upperBorder+" "+borderDifference);
        Tour myTour = new Tour();
        for(int i = 0; i < tour.getSize(); i++){
           if(i == lowerBorder) {
               for(int j = 0; j <= borderDifference; j++){
                   myTour.addCity(tour.getCity(upperBorder--));
                   i++;
               }
           }
           myTour.addCity(tour.getCity(i));
        }
        return myTour;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}