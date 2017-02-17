package mutation;

import base.*;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplacementMutation implements IMutation {

    private int arrLenght, ran1,ran2,diff;
    private MersenneTwisterFast twist;
    private City[] arrMid,arrBound,tourArray;

    public DisplacementMutation(MersenneTwisterFast twist) {
        this.twist = twist;
    }

    public Tour doMutation(Tour tour) {

        arrLenght = tour.getCities().toArray().length;
        tourArray = new City[arrLenght];
        tour.getCities().toArray(tourArray);
        ran1 = twist.nextInt(arrLenght);
        ran2 = twist.nextInt(arrLenght);
        if(ran1>ran2)
        {
            int chache;
            chache = ran2;
            ran2 = ran1;
            ran1 = chache;
        }

        diff = ran2-ran1;

        //Copy bounds in Array
        for(int x=0;x<ran1;x++)
        {
            arrBound[x] = tourArray[x];
        }
        for(int x=0;x<arrLenght-ran2;x++)
        {
            arrBound[x+ran1] = tourArray[ran2+x+1];
        }

        //Copy mid in Array
        for(int i=0;i<=diff;i++)
        {
           arrMid[i] = tourArray[ran1+i];
        }

        ran1 = twist.nextInt(arrBound.length);

        for(int i=0;i<=ran1;i++)
        {
            tourArray[i]= arrBound[i];
        }
        
        int count =0;
        for (City city : arrMid)
        {
          tourArray[count+ran1] = city;
          count++;
        }

        for(int i=0;i<arrBound.length-ran1;i++)
        {
            tourArray[ran1+arrMid.length+i] = arrMid[ran1+i+1];
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