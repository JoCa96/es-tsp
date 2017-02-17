package mutation;

import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;

public class HeuristicMutation implements IMutation {
    MersenneTwisterFast randomGen = new MersenneTwisterFast();
    static ArrayList<ArrayList<Integer>> listOfList = new ArrayList<>();


    public Tour doMutation(Tour tour) {

        // Lambda has the max. value of 5 for testing
        int lambda = randomGen.nextInt(10);
        double fitnessValue = 0;
        ArrayList<Integer> indizes = new ArrayList<>();
        for(int i = 0; i < lambda; i++) {
            indizes.add(randomGen.nextInt(tour.getSize()-1));
        }
        Tour tourDummy = tour;
        Tour bestTour = new Tour();
        listOfList.clear();
        permute(indizes,0);
        System.out.println(listOfList.toString());
        for(ArrayList<Integer> list : listOfList ){
            for(int i = 0; i < (list.size()/2); i++) {
                Collections.swap(tourDummy.getCities(),list.get(i),list.get(i++));
            }
            if(tourDummy.getFitness() > fitnessValue) {
                fitnessValue = tourDummy.getFitness();
                bestTour = tourDummy;
            }
            tourDummy = tour;
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