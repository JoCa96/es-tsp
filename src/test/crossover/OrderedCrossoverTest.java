package test.crossover;

import base.City;
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
        Tour[] tours = crossover.doCrossover(t1, t2);

        assertEquals(tours[0].getCities().size(), tours[1].getCities().size());
        assertFalse(tours[0] == tours[1]);
    }
}