package selection;

import base.Population;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;

public class RouletteWheelSelection implements ISelection {

    public RouletteWheelSelection(MersenneTwisterFast mersenneTwisterFast) {
        this.mersenneTwisterFast = mersenneTwisterFast;
    }

    private MersenneTwisterFast mersenneTwisterFast;

    public ArrayList<Tour> doSelection(Population population) {
        /*
            ############### Roulette Selection ###############

            - Population -> Touren -> Fitnesswerte
            - Anhand dieser Fitnesswerte wird die Wahrscheinlichkeit berechnet
            - Aufstellen einer "Drehscheibe", von der zufällig (MersenneTwiserFast)
              eine Population ausgewählt und zurückgegeben wird

            Rückgabe von 33% der übergebenen Touren wird in einer ArrayList zurückgeben
         */

        ArrayList<Tour> tours = population.getTours();

        tours.forEach(tour -> {

        });
        return null;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}