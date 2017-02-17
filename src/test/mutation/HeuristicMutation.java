package test.mutation;

import base.Population;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;

public class HeuristicMutation  {
    double mutationRatio = 0.1;
    MersenneTwisterFast randomGen = new MersenneTwisterFast();


    public Tour doMutation(Tour tour) {

        // Lambda has the max. value of 3 for testing
        int lambda = randomGen.nextInt(5);
        double fitnessValue = 100;
        System.out.println("Lambda "+lambda);
        ArrayList<Integer> indizes = new ArrayList<>();
        for(int i = 0; i < lambda; i++) {
            indizes.add(randomGen.nextInt(tour.getSize()-1));
        }
        Tour tourDummy = tour;
        Tour bestTour = new Tour();
        ArrayList<ArrayList<Integer>> myList = permute(indizes,0);
        System.out.println("Liste nach Permutation :"+myList.toString());
        for(ArrayList<Integer> list : myList ){
            for(int i = 0; i < (list.size()/2); i++) {
                Collections.swap(tourDummy.getCities(),list.get(i),list.get(i++));
            }
            if(tourDummy.getFitness() < fitnessValue) {
                fitnessValue = tourDummy.getFitness();
                bestTour = tourDummy;
            }
            tourDummy = tour;
        }
        return bestTour;
    }
    static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            System.out.println(arr.toString());

        }
        return null;
    }

    public Population executeMutation(Population popu) {
        popu = new Population();
        popu.generateRandom();
        System.out.println(popu.toString());
        for(Tour tour : popu.getTours()){
            double randomNumber = randomGen.nextDouble(true,true);
            System.out.println(randomNumber);
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