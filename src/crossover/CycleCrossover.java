package crossover;

import java.util.List;
import java.util.Vector;

import base.City;
import base.Tour;
import main.Configuration;

/**
 * 
 * @author 3818468
 *
 */
public class CycleCrossover implements ICrossover {
	public Tour[] doCrossover(Tour tour01, Tour tour02) {
		final List<Integer> cI = new Vector<Integer>();

		int t1 = Configuration.instance.randomSeed.nextInt(tour01.getSize() - 1);
		cI.add(t1);
		City t2 = tour02.getCities().get(t1);

		for (t1 = tour01.getCities().indexOf(t2); t1 != cI.get(0); t1 = tour01.getCities().indexOf(t2)) {
			cI.add(t1);
			t2 = tour02.getCities().get(t1);
		}

		for (int index : cI) {
			City tmp = tour01.getCities().get(index);
			tour01.getCities().set(index, tour02.getCities().get(index));
			tour02.getCities().set(index, tmp);
		}
		return new Tour[] { tour01, tour02 };
	}

	public String toString() {
		return getClass().getSimpleName();
	}
}