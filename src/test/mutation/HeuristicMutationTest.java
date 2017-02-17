package test.mutation;

import base.Population;
import base.Tour;
import org.junit.Test;
import test.AbstractTest;

import static org.junit.Assert.*;

/**
 * Created by Filip on 17.02.2017.
 */
public class HeuristicMutationTest extends AbstractTest{
    @Test
    public void doMutation() throws Exception {
        HeuristicMutation test = new HeuristicMutation();
        Population testPopulation = new Population();
        testPopulation.generateRandom();
        Tour myTestTour = testPopulation.getTours().get(0);
        test.doMutation( myTestTour);
    }

}