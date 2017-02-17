package crossover;


import java.util.ArrayList;
import base.City;
import base.Pair;
import base.Tour;
import main.Configuration;


public class PositionBasedCrossover implements ICrossover {
    public Pair<Tour, Tour> doCrossover(Tour tour01, Tour tour02) {
        return new Pair<Tour, Tour>(doSingleCrossover(tour01,tour02), doSingleCrossover(tour02,tour01));
    }

    private Tour doSingleCrossover(Tour tour01, Tour tour02) {

        int[] positions = new int[tour01.getSize()];

        for(int i: positions){
            positions[i] = (Configuration.instance.randomSeed.nextInt() > 0.5) ? 1:0;
        }

        Tour offspring = new Tour();
        for (int i : positions) {
           if (positions[i] == 1)
           {offspring.addCity(tour01.getCity(positions[i]));

           }
        }
        ArrayList<City> parent2Cities = new ArrayList<>();
        for (
                int i = 0; i < offspring.getSize(); i++)

        {
            for (City j : offspring.getCities()) {
                if (!tour02.containsCity(j)) {
                    parent2Cities.add(j);
                }
            }
        }

        int cityCount = 0;
        for (
                int i : positions)

        {
            if (positions[i] == 0) {
                offspring.addCity(parent2Cities.get(cityCount));
                cityCount++;
            }
        }
        return offspring;

    }

    public String toString() {
        return getClass().getSimpleName();
    }
}