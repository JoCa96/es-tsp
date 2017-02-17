package selection;

import base.Pair;
import base.Population;
import base.Tour;

import java.util.List;

public interface ISelection {
    /**
     * @param population Population from which the Tours are selected from
     * @return 2 dimensional array of tours with a straight number of tours according to the config
     */
    List<Pair<Tour, Tour>> doSelection(Population population);
}