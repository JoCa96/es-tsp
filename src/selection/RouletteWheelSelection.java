package selection;

import java.util.ArrayList;
import java.util.List;

import base.Pair;
import base.Population;
import base.Tour;
import main.Configuration;
import random.MersenneTwisterFast;

public class RouletteWheelSelection implements ISelection {

    private MersenneTwisterFast mersenneTwisterFast;

    public RouletteWheelSelection(MersenneTwisterFast mersenneTwisterFast) {
        this.mersenneTwisterFast = mersenneTwisterFast;
    }

    public List<Pair<Tour, Tour>> doSelection(Population population) {
        double totalFitness = 0;
        int totalTours = 0, tourCount = 0;
        ArrayList<Double> probability = new ArrayList<>();
        ArrayList<Double> border = new ArrayList<>();
        List<Pair<Tour, Tour>> selectedTours = new ArrayList<>();
        Tour tempTour = null;
        ArrayList<Tour> tours = population.getTours();

        for (Tour tour : tours) {
            totalFitness += tour.getFitness();
        }

        totalTours = Configuration.instance.populataionSize;

        for (Tour tour : tours) {
            probability.add(Math.round(tour.getFitness() / totalFitness*10000000000.0)/10000000000.0);
        }

        for(int i = 0; i < probability.size(); i++) {
            if(i == 0) {
                border.add(probability.get(i));
            } else {
                border.add(border.get(i - 1) + probability.get(i));
            }
        }

        tourCount = (int)((Configuration.instance.tourBorder * 0.01) * totalTours);
        if(tourCount%2 != 0) tourCount++;

        for(int j = 0; j < tourCount; j++) {
            double selector = mersenneTwisterFast.nextDouble(true, true);
            for(int i = 0; i < border.size(); i++) {
                if(i == 0) {
                    if(0 < selector && selector <= border.get(i)) {
                        if((j%2) == 0) tempTour = tours.get(i);
                        else selectedTours.add(new Pair<>(tempTour, tours.get(i)));
                        break;
                    }
                } else {
                    if(border.get(i-1) < selector && selector <= border.get(i)) {
                        if((j%2) == 0) tempTour = tours.get(i);
                        else selectedTours.add(new Pair<>(tempTour, tours.get(i)));
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
