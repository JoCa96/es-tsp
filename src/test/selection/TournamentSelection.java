package test.selection;

import base.Population;
import base.Tour;
import junit.framework.TestCase;
import main.Application;
import main.Configuration;
import random.MersenneTwisterFast;
import selection.ISelection;

import java.util.ArrayList;

public class TournamentSelection extends TestCase {
    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    public void testDoSelection() throws Exception {
        Application application = new Application();
        application.loadData();
        Population population = new Population();
        ISelection selector = new selection.TournamentSelection(new MersenneTwisterFast());
        population.generateRandom();
        Tour[][] winners = selector.doSelection(population);
        ArrayList<Tour> all = new ArrayList<>();
        //Check correct Length
        assertEquals(winners.length, 25);
        //Check correct internal array length and not null of all elements
        for (Tour[] winner : winners) {
            assertEquals(winner.length, 2);
            assertNotNull(winner[0]);
            assertNotNull(winner[1]);
            all.add(winner[0]);
            all.add(winner[1]);
        }
        //Check unique
        this.checkUnique(all);
    }

    private void checkUnique(ArrayList<Tour> all) {
        all.forEach(item -> {
            final int[] count = {-1};
            all.forEach(comparable -> {
                if (item.equals(comparable)) count[0]++;
            });
            assertTrue(count[0] == 0);
        });
    }
}