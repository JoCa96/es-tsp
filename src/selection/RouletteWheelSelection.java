package selection;

import java.util.ArrayList;

import base.Population;
import base.Tour;
import main.Configuration;
import random.MersenneTwisterFast;

public class RouletteWheelSelection implements ISelection {

    private MersenneTwisterFast mersenneTwisterFast;
    private double totalFitness = 0, totalProbability;
    private int totalTours = 0, tourCount;
    private ArrayList<Double> probability = new ArrayList<>();
    private ArrayList<Double> border = new ArrayList<>();
    private Tour[][] selectedTours;

    public RouletteWheelSelection(MersenneTwisterFast mersenneTwisterFast) {
        this.mersenneTwisterFast = mersenneTwisterFast;
    }

    public Tour[][] doSelection(Population population) {
        /*
            - Population -> Touren -> Fitnesswerte
            - Rückgabe von 33% der übergebenen Touren wird in einer ArrayList zurückgeben
         */

        ArrayList<Tour> tours = population.getTours();

        // Gesamtfitness und Anzahl Touren berechnen
        tours.forEach(tour -> {
            totalFitness += tour.getFitness();
            totalTours++;
        });

        System.out.println("Totalfitness: " + totalFitness);

        // Wahrscheinlichkeit einer Tour berechnen
        tours.forEach(tour -> probability.add(Math.round(tour.getFitness() / totalFitness*10000000000.0)/10000000000.0));
        System.out.println(probability);

        for(int i = 0; i < probability.size(); i++) {
            totalProbability += probability.get(i).doubleValue();
            System.out.println(totalProbability);
            if(i == 0) {
                border.add(probability.get(i).doubleValue());
            } else {
                border.add(border.get(i - 1) + probability.get(i).doubleValue());
            }
        }

        tourCount = (int)((Configuration.instance.tourBorder * 0.01) * totalTours);
        if(tourCount%2 != 0) tourCount++;
        selectedTours = new Tour[tourCount/2][2];

        for(int j = 0; j < tourCount; j++) {
            double selector = mersenneTwisterFast.nextDouble(true, true);
            for(int i = 0; i < border.size(); i++) {
                if(i == 0) {
                    if(0 <= selector && selector <= border.get(i)) {
                        System.out.println("Selektierte Tour: " + border.indexOf(border.get(i)));

                    }
                } else {
                    if(border.get(i-1) < selector && selector <= border.get(i)) {
                        System.out.println("Selektierte Tour: " + border.indexOf(border.get(i)));
                    }
                }
            }
        }

        System.out.println("Totalprobability: " + totalProbability);

        return null;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}