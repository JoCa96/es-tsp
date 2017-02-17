package mutation;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;

public class ExchangeMutation implements IMutation {

    MersenneTwisterFast twist;
    int ran1,ran2;
    City city1,city2;

    public ExchangeMutation(MersenneTwisterFast twist) {
        this.twist = twist;
    }

    public Tour doMutation(Tour tour) {

        ArrayList<City> mutList;
        mutList = tour.getCities();
        ran1 = twist.nextInt(mutList.size());
        ran2 = twist.nextInt(mutList.size());
        city1 = mutList.get(ran1);
        city2 = mutList.get(ran2);
        mutList.set(ran2,city1);
        mutList.set(ran1,city2);
        tour.setCities(mutList);

        return tour;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}