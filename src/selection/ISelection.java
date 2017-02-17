package selection;

import base.Population;
import base.Tour;

public interface ISelection {
    /**
     * @param population Population from which the Tours are selected from
     * @return 2 dimensional array of tours with a straight number of tours according to the config
     */
    Tour[][] doSelection(Population population);
}