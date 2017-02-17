package mutation;

import base.Population;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;

public class HeuristicMutation implements IMutation {
    double mutationRatio = 0.001;
    MersenneTwisterFast randomGen = new MersenneTwisterFast();

    public int recursiveNumber(int n){
        if(n == 1) return 1;
        return recursiveNumber(n-1) * n;
    }

    public Tour doMutation(Tour tour) {
        // Lambda has the max. value of 10
        int lambda = randomGen.nextInt(10);
        ArrayList<Integer> indizes = new ArrayList<>();
        for(int i = 0; i < lambda; i++) {
            indizes.add(randomGen.nextInt(tour.getSize()-1));
        }
        permute(indizes,0);
        return null;
    }
    static void permute(java.util.List<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            System.out.println(java.util.Arrays.toString(arr.toArray()));
        }
    }
    public Population executeMutation(Population popu) {
        popu = new Population();
        popu.generateRandom();
        System.out.println(popu.toString());
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