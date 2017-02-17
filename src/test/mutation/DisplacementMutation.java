package test.mutation;

import base.City;
import base.Tour;
import junit.framework.TestCase;
import main.Application;
import mutation.IMutation;
import org.junit.Assert;
import org.junit.Test;
import random.MersenneTwisterFast;

public class DisplacementMutation extends TestCase {
@Test
    public void test() throws Exception{
    MersenneTwisterFast twist = new MersenneTwisterFast();
        Application application = new Application();
        application.loadData();
        City city1 = new City(0,5,7);
        City city2 = new City(1,6,8);
        City city3 = new City(2,56,345);
        City city4 = new City(3,35,23);
        City city5 = new City(4,45,32);
        Tour tour = new Tour();
        tour.addCity(city1);
        tour.addCity(city2);
        tour.addCity(city3);
        tour.addCity(city4);
        tour.addCity(city5);
        Tour testTour = new Tour();
        testTour.addCity(city1);
        testTour.addCity(city4);
        testTour.addCity(city5);
        testTour.addCity(city2);
        testTour.addCity(city3);
        IMutation mut = new mutation.DisplacementMutation(1,3,3);
        tour = mut.doMutation(tour);

        assertEquals(testTour.toString(),tour.toString());
    }





}