package bruteforce;


import base.City;
import data.InstanceReader;
import data.TSPLIBReader;
import random.MersenneTwisterFast;
import java.util.ArrayList;

public class Bruteforce {

    private TSPLIBReader tspReader;
    private InstanceReader instReader;
    private int numberOfCities;
    private ArrayList<City> cityList;

    public Bruteforce(TSPLIBReader x, InstanceReader y, int z){
        this.tspReader=x;
        this.instReader=y;
        this.numberOfCities=z;
    }

    private void getRandomCities(){
        ArrayList<City> cityListAll=new ArrayList<City>();

        MersenneTwisterFast mt = new MersenneTwisterFast();

        cityListAll = tspReader.getCities();

        for(int i=0; i<numberOfCities; i++) {
            this.cityList.add(cityListAll.get(mt.nextInt(numberOfCities)));
        }
    }

    public ... run(){
        this.getRandomCities();


    }



}
