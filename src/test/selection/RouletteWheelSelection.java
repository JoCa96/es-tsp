package test.selection;

import base.Population;
import base.Tour;
import main.Application;
import main.Configuration;
import org.junit.Test;
import random.MersenneTwisterFast;
import selection.ISelection;
import static org.junit.Assert.*;

public class RouletteWheelSelection {
    @Test
    public void doSelection() throws Exception {
        Application application = new Application();
        application.loadData();
        Population population = new Population();
        ISelection selector = new selection.TournamentSelection(new MersenneTwisterFast());
        population.generateRandom();
        //Tour[][] tours = selector.doSelection(population);
        /*
        int arrayLength = (int)(Configuration.instance.populataionSize / (Configuration.instance.tourBorder*0.01));
        if(arrayLength%2 != 0) arrayLength++;

        // Array length test
        assertEquals(arrayLength, tours.length);

        // Internal Array Test
        for (Tour[] tour : tours) {
            assertEquals(2, tour.length);
            assertNotNull(tour[0]);
            assertNotNull(tour[1]);
        }
        */
    }
}