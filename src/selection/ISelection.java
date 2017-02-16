package selection;

import base.Population;
import base.Tour;

import java.util.ArrayList;

public interface ISelection {
    /**
     * @param population Population from which the Tours are selected from
     * @return A straight number of Tours according to the config
     */
    ArrayList<Tour> doSelection(Population population);
}