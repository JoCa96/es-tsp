package test.mutation;

import base.City;
import base.Population;
import base.Tour;
import main.Configuration;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Filip on 16.02.2017.
 */
public class InversionMutationTest {
    @Test
    public void doMutation() throws Exception {
        Tour myStandardTour = new Tour();
        Tour testTour = new Tour();
        // Initialize 7 cities
        City city1 = new City(1,1,2);
        City city2 = new City(2,3,4);
        City city3 = new City(3,1,5);
        City city4 = new City(4,1,5);
        City city5 = new City(5,8,9);
        City city6 = new City(6,1,8);
        City city7 = new City(7,9,3);
        myStandardTour.addCity(city1);
        myStandardTour.addCity(city2);
        myStandardTour.addCity(city3);
        myStandardTour.addCity(city4);
        myStandardTour.addCity(city5);
        myStandardTour.addCity(city6);
        myStandardTour.addCity(city7);

        // Create Mutation Object
        InversionMutation myMutation = new InversionMutation();


        // Add Cities in the order which they should be with given Borders
        testTour.addCity(city6);
        testTour.addCity(city5);
        testTour.addCity(city4);
        testTour.addCity(city3);
        testTour.addCity(city2);
        testTour.addCity(city1);
        testTour.addCity(city7);
        // Mutate the tour
        Tour mutatedTour = myMutation.doMutation(myStandardTour,0,5);
        // Testing with lowerBorder 0 , upperBorder 5
        assertTrue(mutatedTour.getCities().equals((testTour).getCities()));

        // Re-Initialize all values
        testTour = new Tour();
        myStandardTour = new Tour();
        mutatedTour = new Tour();

        // Adding cities in correct order for lowerBorder = 0 and upperBorder = 0

        testTour.addCity(city1);
        testTour.addCity(city2);
        testTour.addCity(city3);
        testTour.addCity(city4);
        testTour.addCity(city5);
        testTour.addCity(city6);
        testTour.addCity(city7);
        myStandardTour.addCity(city1);
        myStandardTour.addCity(city2);
        myStandardTour.addCity(city3);
        myStandardTour.addCity(city4);
        myStandardTour.addCity(city5);
        myStandardTour.addCity(city6);
        myStandardTour.addCity(city7);
        mutatedTour = myMutation.doMutation(myStandardTour,0,0);

        // Testing with lowerBorder == upperBorder
        assertTrue(mutatedTour.getCities().equals(testTour.getCities()));

        // Re-Initialize all values
        testTour = new Tour();
        myStandardTour = new Tour();
        mutatedTour = new Tour();

        // Adding cities in correct order for lowerBorder = 0 and upperBorder = 0

        testTour.addCity(city1);
        testTour.addCity(city2);
        testTour.addCity(city3);
        testTour.addCity(city6);
        testTour.addCity(city5);
        testTour.addCity(city4);
        testTour.addCity(city7);
        myStandardTour.addCity(city1);
        myStandardTour.addCity(city2);
        myStandardTour.addCity(city3);
        myStandardTour.addCity(city4);
        myStandardTour.addCity(city5);
        myStandardTour.addCity(city6);
        myStandardTour.addCity(city7);
        mutatedTour = myMutation.doMutation(myStandardTour,3,6);
        Population pop = new Population();
        pop.generateRandom();
        for(Tour tour:pop.getTours()){
            System.out.println(tour.toString());
        }

        }

}