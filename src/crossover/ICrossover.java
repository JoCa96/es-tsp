package crossover;

import base.Pair;
import base.Tour;

public interface ICrossover {
    Pair<Tour, Tour> doCrossover(Tour tour01, Tour tour02);
}