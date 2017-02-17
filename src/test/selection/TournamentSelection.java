package test.selection;

import base.Pair;
import base.Population;
import base.Tour;
import junit.framework.TestCase;
import main.Application;
import main.Configuration;
import random.MersenneTwisterFast;
import selection.ISelection;

import java.util.ArrayList;
import java.util.List;

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
        List<Pair<Tour, Tour>> winners = selector.doSelection(population);
        ArrayList<Tour> all = new ArrayList<>();
        //Check correct Length
        int arrayLength = (int) (Configuration.instance.populataionSize * (Configuration.instance.tourBorder * 0.01));
        if (arrayLength % 2 != 0) arrayLength = (arrayLength + 1) / 2;
        assertEquals(winners.size(), arrayLength);
        //Check correct internal array length and not null of all elements
        for (Pair<Tour, Tour> winner : winners) {
            assertNotNull(winner.getFirst());
            assertNotNull(winner.getSecond());
            all.add(winner.getFirst());
            all.add(winner.getSecond());
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