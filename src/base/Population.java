package base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import main.Configuration;

public class Population {
    private ArrayList<Tour> tours = new ArrayList<Tour>();

    public Population() {
        generateRandom();
    }

    public void generateRandom() {
        tours.clear();
        while (tours.size() != Configuration.instance.populataionSize) {
            Tour tour = Tour.generateRandom();
            if (!tours.contains(tour)) {
                tours.add(tour);
            }
        }
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public void setTours(ArrayList<Tour> tours) {
        this.tours = tours;
    }

    public void addChildren(List<Tour> children) {
        tours.addAll(children);
        Collections.sort(children);
        tours = new ArrayList<Tour>(tours.subList(0, Configuration.instance.populataionSize));
    }
}