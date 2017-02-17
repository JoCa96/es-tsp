package test.mutation;

import base.City;
import base.Population;
import base.Tour;
import org.junit.Test;
import test.AbstractTest;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Filip on 17.02.2017.
 */
public class HeuristicMutationTest extends AbstractTest{
    @Test
    public void doMutation() throws Exception {
        HeuristicMutation test = new HeuristicMutation();
        City firstCity = new City(1,1,1);
        City secondCity = new City(2,1,2);
        City thirdCity = new City(3,1,3);
        City fourthCity = new City(4,1,4);
        City fifthCity = new City(5,1,5);
        City sixthCity = new City(6,2,1);
        Tour myTestTour = new Tour();
        myTestTour.addCity(thirdCity);
        myTestTour.addCity(sixthCity);
        myTestTour.addCity(firstCity);
        myTestTour.addCity(secondCity);
        myTestTour.addCity(fifthCity);
        myTestTour.addCity(fourthCity);
        test.doMutation( myTestTour);
    }

}