package mutation;

import base.Population;
import base.Tour;

public interface IMutation {
    Tour doMutation(Tour tour);
    //Population executeMutation(Population popu);
}