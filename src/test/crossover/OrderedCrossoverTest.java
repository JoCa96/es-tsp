package test.crossover;

import base.City;
import base.Pair;
import base.Tour;
import crossover.ICrossover;
import crossover.OrderedCrossover;
import org.junit.Test;
import test.AbstractTest;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class OrderedCrossoverTest extends AbstractTest {

    private Tour generateTour(boolean reverse) {
        ArrayList<City> cities = new ArrayList<City>();
        for (int i = 0; i < 10; i++) {
            cities.add(new City(i, i, i));
        }
        if (reverse) {
            Collections.reverse(cities);
        }
        return new Tour(cities);
    }

    @Test
    public void test() {
        Tour t1 = generateTour(true);
        Tour t2 = generateTour(false);

        ICrossover crossover = new OrderedCrossover();
        Pair<Tour, Tour> tours = crossover.doCrossover(t1, t2);

        assertEquals(tours.getFirst().getCities().size(), tours.getSecond().getCities().size());
        assertFalse(tours.getFirst() == tours.getSecond());
    }
}