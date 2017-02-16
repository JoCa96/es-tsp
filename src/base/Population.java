package base;

import java.util.ArrayList;

public class Population {
    private ArrayList<Tour> tours;

    public Population() {
        generateRandom();
    }

    public void generateRandom() {
        // TODO Zufällige anfangs population erstellen.
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public void setTours(ArrayList<Tour> tours) {
        this.tours = tours;
    }
}