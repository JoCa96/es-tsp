package bruteforce;


import base.City;
import base.Tour;
import data.InstanceReader;
import data.TSPLIBReader;
import random.MersenneTwisterFast;
import java.util.ArrayList;

public class Bruteforce {

    private TSPLIBReader tspReader;
    private InstanceReader instReader;
    private ArrayList<Double> resultList;

    public Bruteforce(TSPLIBReader x, InstanceReader y) {
        this.tspReader = x;
        this.instReader = y;
    }

    private ArrayList<City> generateCityList(){

    }

    private Tour tryTour(){

    }

    public double getFitnessAll(){

    }

    public double getFitnessTop25(){

    }

    public double getFitnessLast25(){

    }

    public double getFitnessMid50(){

    }

   /* private TSPLIBReader tspReader;
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

    */

}
