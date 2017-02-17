package test.selection;

import base.Population;
import main.Application;
import org.junit.Test;
import random.MersenneTwisterFast;

public class RouletteWheelSelection {
    @Test
    public void test() {
        Application application = new Application();
        application.loadData();
        selection.RouletteWheelSelection rouletteWheelSelection = new selection.RouletteWheelSelection(new MersenneTwisterFast());
        Population population = new Population();
        population.generateRandom();
        rouletteWheelSelection.doSelection(population);
    }
}