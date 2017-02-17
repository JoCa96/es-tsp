package test.mutation;

import base.Population;
import base.Tour;
import random.MersenneTwisterFast;
import test.AbstractTest;

import java.util.ArrayList;
import java.util.Collections;

public class HeuristicMutation  extends AbstractTest{
    double mutationRatio = 0.0005;

    MersenneTwisterFast randomGen = new MersenneTwisterFast();
    static ArrayList<ArrayList<Integer>> listOfList = new ArrayList<>();

    public Tour tourCopy(Tour tour){
        Tour tourDummy = new Tour();
        for(int i = 0; i < tour.getCities().size(); i++){
            tourDummy.addCity(tour.getCity(i));
        }
        return tourDummy;
    }

    public Tour doMutation(Tour tour,int howMany, ArrayList<Integer> index) {

        // Lambda is fixed in test
        int lambda = howMany;
        double fitnessValue = tour.getFitness();
        // So is the list of indizes
        ArrayList<Integer> indizes = index;

        Tour tourDummy = tourCopy(tour);
        Tour bestTour = tourCopy(tour);
        listOfList.clear();
        permute(indizes,0);
        for(ArrayList<Integer> list : listOfList ){
            for(int i = 0; i < (list.size()/2); i++) {
                    int indexOne = list.get(i);
                    int indexTwo = list.get(++i);
                    Collections.swap(tourDummy.getCities(),indexOne,indexTwo);
            }
            System.out.println("Fitness :"+tourDummy.getFitness()+" in this Order :"+tourDummy.toString());
            if(tourDummy.getFitness() > fitnessValue) {
                fitnessValue = tourDummy.getFitness();
                bestTour = tourCopy(tourDummy);
            }
            tourDummy = tourCopy(tour);
        }

        System.out.println("Die beste Tour :"+bestTour.toString());
        return bestTour;
    }
    static void permute(ArrayList<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            ArrayList<Integer> dummy = new ArrayList<>(arr);
            listOfList.add(dummy);
        }
    }


    public String toString() {
        return getClass().getSimpleName();
    }
}