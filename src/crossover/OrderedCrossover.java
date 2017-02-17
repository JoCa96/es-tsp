package crossover;

import base.City;
import base.Tour;
import main.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OrderedCrossover implements ICrossover {
    public Tour[] doCrossover(Tour tour01, Tour tour02) {
        List<City> tour1cities = tour01.getCities();
        List<City> tour2cities = tour02.getCities();

        int seperatorBegin = Configuration.instance.randomSeed.nextInt(tour1cities.size());
        int seperatorEnd = Configuration.instance.randomSeed.nextInt(tour1cities.size());
        if (seperatorEnd < seperatorBegin) {
            int tmp = seperatorBegin;
            seperatorBegin = seperatorEnd;
            seperatorEnd = tmp;
        }

        List<City> part1 = tour1cities.subList(seperatorBegin, seperatorEnd);
        List<City> part2 = tour1cities.subList(seperatorBegin, seperatorEnd);

        return new Tour[] {
                mergeTour(tour2cities, part1, part2, seperatorBegin, seperatorEnd),
                mergeTour(tour1cities, part2, part1, seperatorBegin, seperatorEnd)
        };
    }

    public Tour mergeTour(List<City> prtnerCities, List<City> part1, List<City> part2, int seperatorBegin, int seperatorEnd) {
        ArrayList<City> childCities = new ArrayList<City>();
        for (int i = 0; i < part1.size(); i++) {
            childCities.set(seperatorBegin + i, part1.get(0));
        }

        ArrayList<City> others = new ArrayList<City>(part2);
        others.addAll(prtnerCities.subList(0, seperatorBegin));
        others.addAll(prtnerCities.subList(seperatorEnd, prtnerCities.size()));

        int index = 0;
        Iterator<City> iterator = others.iterator();
        for (City city : childCities) {
            if (city == null) {
                City newCity = iterator.next();
                while (childCities.contains(newCity)) {
                    newCity = iterator.next();
                }
                childCities.set(index, newCity);
            }
            index++;
        }

        return new Tour(childCities);
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}