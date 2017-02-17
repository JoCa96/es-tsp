package crossover;

import base.City;
import base.Pair;
import base.Tour;
import main.Configuration;

import java.util.*;

public class OrderedCrossover implements ICrossover {
    public Pair<Tour, Tour> doCrossover(Tour tour01, Tour tour02) {
        List<City> tour1cities = tour01.getCities();
        List<City> tour2cities = tour02.getCities();

        int seperatorBegin = Configuration.instance.randomSeed.nextInt(tour1cities.size() - 1) + 1;
        int seperatorEnd = Configuration.instance.randomSeed.nextInt(tour1cities.size() - 1) + 1;
        if (seperatorEnd < seperatorBegin) {
            int tmp = seperatorBegin;
            seperatorBegin = seperatorEnd;
            seperatorEnd = tmp;
        }

        List<City> middle1 = tour1cities.subList(seperatorBegin, seperatorEnd);
        List<City> middle2 = tour1cities.subList(seperatorBegin, seperatorEnd);

        return new Pair<Tour, Tour>(
                mergeTour(tour2cities, middle1, middle2, seperatorBegin, seperatorEnd),
                mergeTour(tour1cities, middle2, middle1, seperatorBegin, seperatorEnd)
        );
    }

    public Tour mergeTour(List<City> partnerCities, List<City> middle1, List<City> middle2, int seperatorBegin, int seperatorEnd) {
        ArrayList<City> childCities = new ArrayList<City>(Collections.nCopies(partnerCities.size(), null));

        for (int i = 0; i < middle1.size(); i++) {
            childCities.set(seperatorBegin + i, middle1.get(i));
        }

        ArrayList<City> others = new ArrayList<City>(middle2);
        others.addAll(partnerCities.subList(0, seperatorBegin));
        others.addAll(partnerCities.subList(seperatorEnd, partnerCities.size()));

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