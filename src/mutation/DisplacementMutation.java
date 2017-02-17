package mutation;

import base.*;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplacementMutation implements IMutation {

    private int arrLenght, ran1,ran2,ran3,diff;

    private City[] arrMid,arrBound,tourArray;

    public DisplacementMutation(int pran1, int pran2, int pran3){
        ran1 = pran1;
        ran2 = pran2;
        ran3 = pran3;
    }

    public Tour doMutation(Tour tour) {

        arrLenght = tour.getCities().toArray().length;
        tourArray = new City[arrLenght];
        tour.getCities().toArray(tourArray);


        if(ran1>ran2)
        {
            int chache;
            chache = ran2;
            ran2 = ran1;
            ran1 = chache;
        }

        diff = ran2-ran1;
        arrMid = new City[diff];
        arrBound = new City[tourArray.length-diff];

        //Copy bounds in Array
        for(int x=0;x<ran1;x++)
        {
            arrBound[x] = tourArray[x];
        }
        for(int x=0;x<arrLenght-ran2;x++)
        {
            arrBound[x+ran1] = tourArray[ran2+x];
        }

        //Copy mid in Array
        for(int i=0;i<diff;i++)
        {
           arrMid[i] = tourArray[ran1+i];
        }


        for(int i=0;i<ran3;i++)
        {
            tourArray[i]= arrBound[i];
        }

        int count =0;
        for (City city : arrMid)
        {
          tourArray[count+ran3] = city;
          count++;
        }

        for(int i=0;i<(arrBound.length-ran3);i++)
        {
            tourArray[ran1+arrMid.length+i+1] = arrBound[ran3+i];
        }

        ArrayList mutList = new ArrayList<City>();
        for(int i=0;i<tourArray.length;i++)
        {
            mutList.add(i,tourArray[i]);
        }
        tour.setCities(mutList);

        return tour;
    }



    public String toString() {
        return getClass().getSimpleName();
    }
}