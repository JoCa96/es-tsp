package selection;

import java.util.ArrayList;

import base.Population;
import base.Tour;
import main.Configuration;
import random.MersenneTwisterFast;

public class RouletteWheelSelection implements ISelection {

    private MersenneTwisterFast mersenneTwisterFast;
    private double totalFitness = 0, totalProbability = 0;
    private int totalTours = 0, tourCount = 0;
    private ArrayList<Double> probability = new ArrayList<>();
    private ArrayList<Double> border = new ArrayList<>();
    private Tour[][] selectedTours;

    public RouletteWheelSelection(MersenneTwisterFast mersenneTwisterFast) {
        this.mersenneTwisterFast = mersenneTwisterFast;
    }

    public Tour[][] doSelection(Population population) {

        ArrayList<Tour> tours = population.getTours();

        tours.forEach(tour -> {
            totalFitness += tour.getFitness();
            totalTours++;
        });

        tours.forEach(tour -> probability.add(Math.round(tour.getFitness() / totalFitness*10000000000.0)/10000000000.0));

        for(int i = 0; i < probability.size(); i++) {
            totalProbability += probability.get(i);
            if(i == 0) {
                border.add(probability.get(i));
            } else {
                border.add(border.get(i - 1) + probability.get(i));
            }
        }

        tourCount = (int)((Configuration.instance.tourBorder * 0.01) * totalTours);
        if(tourCount%2 != 0) tourCount++;
        selectedTours = new Tour[tourCount/2][2];

        for(int j = 0; j < tourCount; j++) {
            double selector = mersenneTwisterFast.nextDouble(true, true);
            for(int i = 0; i < border.size(); i++) {
                if(i == 0) {
                    if(0 < selector && selector <= border.get(i)) {
                        selectedTours[j/2][j%2] = tours.get(i);
                        break;
                    }
                } else {
                    if(border.get(i-1) < selector && selector <= border.get(i)) {
                        selectedTours[j/2][j%2] = tours.get(i);
                        break;
                    }
                }
            }
        }

        return selectedTours;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}