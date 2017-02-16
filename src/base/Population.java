package base;

import main.Configuration;

import java.util.ArrayList;

public class Population {
    private ArrayList<Tour> tours = new ArrayList<Tour>();

    public Population() {
        generateRandom();
    }

    public void generateRandom() {
        for (int i = 0; i < Configuration.instance.populataionSize; i++) {
            tours.add(Tour.generateRandom());
        }
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public void setTours(ArrayList<Tour> tours) {
        this.tours = tours;
    }
}