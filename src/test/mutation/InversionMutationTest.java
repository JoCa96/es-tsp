package test.mutation;

import base.City;
import base.Tour;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Filip on 16.02.2017.
 */
public class InversionMutationTest {
    @Test
    public void doMutation() throws Exception {
        Tour myTour = new Tour();
        Tour mutatedTour = new Tour();
        City city1 = new City(1,1,2);
        City city2 = new City(2,3,4);
        City city3 = new City(3,1,5);
        City city4 = new City(3,1,5);
        City city5 = new City(5,8,9);
        City city6 = new City(6,1,8);
        City city7 = new City(7,9,3);
        myTour.addCity(city1);
        myTour.addCity(city2);
        myTour.addCity(city3);
        myTour.addCity(city4);
        myTour.addCity(city5);
        myTour.addCity(city6);
        myTour.addCity(city7);
        InversionMutation myMutation = new InversionMutation();
        myTour = myMutation.doMutation(myTour,0,5);
        mutatedTour.addCity(city6);
        mutatedTour.addCity(city5);
        mutatedTour.addCity(city4);
        mutatedTour.addCity(city3);
        mutatedTour.addCity(city2);
        mutatedTour.addCity(city1);
        mutatedTour.addCity(city7);
        assertTrue(myTour.getCities().equals((mutatedTour).getCities()));
        }

}