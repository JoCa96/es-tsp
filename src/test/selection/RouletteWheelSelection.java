package test.selection;

import base.Population;
import base.Tour;
import main.Application;
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
        Tour[][] tours = selector.doSelection(population);

        // Array length test
        assertEquals(tours.length, 25);

        for (Tour[] tour : tours) {
            assertEquals(2, tour.length);
            assertNotNull(tour[0]);
            assertNotNull(tour[1]);
        }
    }
}