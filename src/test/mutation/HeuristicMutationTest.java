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
        City thirdCity = new City(3,5,3);
        City fourthCity = new City(4,3,4);
        City fifthCity = new City(5,4,5);
        City sixthCity = new City(6,2,6);
        Tour myTestTour = new Tour();
        myTestTour.addCity(firstCity);
        myTestTour.addCity(sixthCity);
        myTestTour.addCity(thirdCity);
        myTestTour.addCity(secondCity);
        myTestTour.addCity(fifthCity);
        myTestTour.addCity(fourthCity);
        ArrayList<Integer> toSwitch = new ArrayList<>();

        System.out.println("Initial Fitness :"+myTestTour.getFitness()+" in this Order :"+myTestTour.toString());
        // Switch index 1 and 2 -> should shorten the distance = better value
        toSwitch.add(1);
        toSwitch.add(2);
        Tour bestTour = test.doMutation( myTestTour,2,toSwitch);
        double fitOne = myTestTour.getFitness();
        double fitTwo = bestTour.getFitness();
        System.out.println(fitTwo+" "+fitOne);

        assertTrue(fitTwo > fitOne);

        // Switch index 3 and 5 -> should improve the fitness = better
        toSwitch.clear();
        toSwitch.add(3);
        toSwitch.add(5);
        bestTour = test.doMutation(myTestTour,2,toSwitch);
        fitOne = myTestTour.getFitness();
        fitTwo = bestTour.getFitness();
        System.out.println(fitTwo+" "+fitOne);

        assertTrue(fitTwo > fitOne);

        // Switch index 2 and 4 -> should improve the fitness = better
        toSwitch.clear();
        toSwitch.add(2);
        toSwitch.add(4);
        bestTour = test.doMutation(myTestTour,2,toSwitch);
        fitOne = myTestTour.getFitness();
        fitTwo = bestTour.getFitness();
        System.out.println(fitTwo+" "+fitOne);

        assertTrue(fitTwo > fitOne);

        // Switch index 0 and 1 -> reduces the fitness - old tour was better
        toSwitch.clear();
        toSwitch.add(0);
        toSwitch.add(1);
        fitOne = myTestTour.getFitness();
        fitTwo = test.doMutation(myTestTour,2,toSwitch).getFitness();
        assertTrue(fitTwo == fitOne);
    }

}